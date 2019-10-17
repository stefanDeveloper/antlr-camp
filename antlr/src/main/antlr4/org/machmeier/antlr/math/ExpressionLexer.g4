lexer grammar ExpressionLexer;

/* Ignore whitespaces, tabs and new lines: */
WS: [ \r\n\t]+ -> skip;

INT: [0-9]+;
PLUS: '+';
MINUS: '-';
OPEN_BRACKET: '(';
CLOSE_BRACKET: ')';
