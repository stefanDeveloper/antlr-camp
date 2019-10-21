# ANTLR4 Camp

> ANTLR4 Camp to get into touch with Lexer, Parser, Visitor and Listener

In this example you'll learn how to use Lexer and Parser to build up an expression grammar to calculate following results:

```python
a = 5
b = 6
c = 7
((a+b)/3-c)
```

## Agenda

* Grammar
* Lexer
* Parser
* Listener
* Visitor
* Error Strategy

## Grammar

A grammar is a subset of a language. Its following the syntax of a language and its limited by it as well.
![alt text][grammar]

A language is a set of valid sentences. A sentence is made up phrases. A Phrase is made up of subphrases and vocabulary symbols.
![alt text][language]

## Lexer

Lexer is the process of grouping characters into words or symbols. Its tokenizes the input.

To define tokens:

```python
INT: [0-9]+;
NEWLINE: '\r'? '\n';
```

Lexer can be separated in own `Lexer.g4` file. Its defined by `lexer grammar name`.

## Parser

Parser is the process of building up the data structure of a sentence. Its recognizes languages.

```python
stat:
    expr NEWLINE            # printExpr
    | ID EQUAL expr NEWLINE # assign
    | NEWLINE               # blank;
```

Parser can be separated in own `parser.g4` file. Its defined by `parser grammar name`. Don't forget to reference the corresponding lexer file.

```python
options {
    tokenVocab = ExpressionLexer;
    language = Java;
}
```

## Listener

```java
public class ExpressionParserListener extends ExpressionParserBaseListener
```

## Visitor

```java
public class ExpressionParserVisitor extends ExpressionParserBaseVisitor<Integer>
```

Implementing visit funtions:

```java
@Override
public Integer visitPrintExpr(ExpressionParser.PrintExprContext ctx) {
    Integer value = visit(ctx.expr());
    log.info("Value:" + value);
    return 0;
}
```

## Error Strategy

```java
public class ExpressionErrorStrategy extends BaseErrorListener
```



[grammar]: ./internals/grammar.png "Grammar and Language set"
[language]: ./internals/language.png "Language content"
