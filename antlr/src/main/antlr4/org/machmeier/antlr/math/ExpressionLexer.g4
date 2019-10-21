lexer grammar ExpressionLexer;

ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';
OPEN_BRACKET: '(';
CLOSE_BRACKET: ')';
EQUAL: '=';
/* Ignore whitespaces, tabs and new lines: */
WS: [ \t]+ -> skip;
ID: [a-z]+;
INT: [0-9]+;
NEWLINE: '\r'? '\n';

