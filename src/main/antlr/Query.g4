grammar Query;
@header {
package com.joogle.query;
}
query: q;
q: classQuery | methodQuery;
classQuery: 'class' name;
methodQuery: 'method' name;
id: ID;
name: id | wc;
wc: '*';
ID  : [a-zA-Z_][a-zA-Z0-9_]+ ;
WS  : [ \t\r\n]+ -> skip ;