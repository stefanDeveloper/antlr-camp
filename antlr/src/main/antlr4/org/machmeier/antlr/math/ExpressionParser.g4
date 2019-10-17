parser grammar ExpressionParser;

options {
	tokenVocab = ExpressionLexer;
	language = Java;
}
@members {
    private int __value;
}

expression:
	| expression operation expression
	| OPEN_BRACKET expression CLOSE_BRACKET
	| INT;

operation: PLUS | MINUS;
