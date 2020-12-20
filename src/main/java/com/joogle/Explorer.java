package com.joogle;

import static com.github.javaparser.ParseStart.COMPILATION_UNIT;
import static com.github.javaparser.Providers.provider;
import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.SKIP_SUBTREE;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.Provider;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.printer.YamlPrinter;
import com.github.javaparser.utils.SourceRoot;
import com.joogle.datamodel.DataBase;
import com.joogle.datamodel.JAccess;
import com.joogle.datamodel.JClass;
import com.joogle.datamodel.JMethod;
import com.joogle.datamodel.JMethod.JParameter;
import com.joogle.datamodel.JType;


public class Explorer {
	private ArrayList<JClass> classes = new ArrayList<JClass>();
	private Path path;

	public Explorer(String path) {
		this.path = Path.of(path);
	}

	String getAST(Node node) {
		YamlPrinter printer = new YamlPrinter(true);
		return printer.output(node);
	}

	void printAST(Node node) {
		System.out.println(getAST(node));
	}
	

	ArrayList<JClass> extract() {
//		SourceRoot rootParser = new SourceRoot(path);

//		List<ParseResult<CompilationUnit>> list = rootParser.tryToParseParallelized();
//
//		try {
//			rootParser.parse("", this);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
			    @Override
			    public FileVisitResult visitFile(Path absolutePath, BasicFileAttributes attrs) throws IOException {
			        if (!attrs.isDirectory() && absolutePath.toString().endsWith(".java")) {
			        	
//			        	attrs.lastModifiedTime();
			        	
			    		ParseResult<CompilationUnit> result = new JavaParser().parse(COMPILATION_UNIT, provider(absolutePath));
			        	process(result);
			        }
			        return CONTINUE;
			    }

			    @Override
			    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			        return CONTINUE;
			    }
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return classes;
	}
	

	JParameter extractParameter(Type type) {
		if (type.isClassOrInterfaceType()) {
			ClassOrInterfaceType cit = type.asClassOrInterfaceType();
			return new JParameter(new JType(cit.getNameAsString()));
		} else if (type.isArrayType()) {
			JParameter tmp = extractParameter(type.asArrayType().getComponentType());
			tmp.arrayDepth = type.getArrayLevel();
			return tmp;
		} else if (type.isVarType()) {
			JParameter tmp = extractParameter(type.asVarType().getElementType());
			tmp.isVararg = true;
			return tmp;
		} else if (type.isPrimitiveType()) {
			return new JParameter(new JType(type.asPrimitiveType().getType().asString()));
		} else {
			System.err.printf("Unhandled parameter case:\n%s\n", getAST(type));
		}
		// TODO handle other types

		return null;
	}

	JMethod extractMethod(MethodDeclaration met) {

		JAccess maccess = new JAccess(met.getAccessSpecifier());
		ArrayList<JParameter> params = new ArrayList<>();
		JMethod jm = new JMethod(met.getNameAsString(), new JType(met.getTypeAsString()), maccess, params);

		if (met.getJavadoc().isPresent())
			jm.setJavaDoc(met.getJavadoc().get().toText());
		NodeList<Parameter> _params = met.getParameters();
		for (Parameter param : _params) {
			JParameter p = extractParameter(param.getType());
			if (p != null) {
				p.name = param.getNameAsString();
				params.add(p);
			}
		}
		return jm;
	}

	JClass extractClass(CompilationUnit cu, TypeDeclaration<?> type, boolean isPublic) {

		String _package = cu.getPackageDeclaration().get().getNameAsString();
		String name = type.getNameAsString();
		JAccess access = new JAccess(type.getAccessSpecifier());
		JClass jc = new JClass(_package, name, access);

		NodeList<BodyDeclaration<?>> members = type.getMembers();
		for (BodyDeclaration<?> mem : members) {
			if (mem.isMethodDeclaration()) {
				JMethod met = extractMethod(mem.asMethodDeclaration());
				met.setClass(jc);
				jc.addMethod(met);
			} else if (mem.isClassOrInterfaceDeclaration()) {
				JClass subc = extractClass(cu, mem.asClassOrInterfaceDeclaration(), false);
				jc.addClass(subc);
			}
			// TODO handle other types
		}

		if (isPublic)
			DataBase.addClass(jc);
		return jc;
	}

	ArrayList<JClass> extractFile(CompilationUnit cu) {
		NodeList<TypeDeclaration<?>> types = cu.getTypes();
		ArrayList<JClass> ans = new ArrayList<JClass>();

		for (TypeDeclaration<?> type : types) {
			ans.add(extractClass(cu, type, true));
		}
		return ans;
	}

	private int progress = 0;
	private int total = 0;
	
	public void process(ParseResult<CompilationUnit> result) {
		CompilationUnit cu = result.getResult().get();
		classes.addAll(extractFile(cu));
		progress++;
		if(progress % 100 == 0) System.out.println("Parsing, currently parsed " + progress + " files");
	}

}