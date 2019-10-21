parser grammar ExpressionParser;

options {
	tokenVocab = ExpressionLexer;
	language = Java;
}

prog: stat+;

stat:
	expr NEWLINE			# printExpr
	| ID EQUAL expr NEWLINE	# assign
	| NEWLINE				# blank;

expr:
	expr op = (MUL | DIV) expr			# MulDiv
	| expr op = (ADD | SUB) expr		# AddSub
	| INT								# int
	| ID								# id
	| OPEN_BRACKET expr CLOSE_BRACKET	# parens;
