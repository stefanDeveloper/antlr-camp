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

Lexer is the process of grouping characters into words or symbols. It tokenizes the input.

To define tokens:

```python
INT: [0-9]+;
NEWLINE: '\r'? '\n';
```

Lexer can be separated in own `Lexer.g4` file. Its defined by `lexer grammar name`.

## Parser

Parser is the process of building up the data structure of a sentence. It recognizes languages.

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

The generated Listener class provides enter and exit methods for each rule. It also defines where in the walk `ParseTreeWalker` calls the enter and exit method. 

```java
public class ExpressionParserListener extends ExpressionParserBaseListener
```

## Visitor

The generated Visitor class provides a controlled walk through our tree. It explicitly calls methods to visit children.

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

Be aware to enable visitor creation of grammars.

```xml
<configuration>
    <sourceDirectory>${basedir}/src/main/antlr4</sourceDirectory>
    <visitor>true</visitor>
</configuration>
```

## Error Strategy

To define our on error strategy, we can extend the `BaseErrorListener. In general, only 4 different types of exceptions are thrown during the lexer and parser process.

![alt text][error]

```java
public class ExpressionErrorStrategy extends BaseErrorListener
```

To set a custom error message, we can override the `syntaxError` function and handle each error message individually.

```java
@Override
public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
    super.syntaxError(recognizer, o, i, i1, "Message", e);
}
```

Be aware, that the RecognitionException **can be null**!

To retrieve the offending and expected token of an exception, we can use the `Parser` or the `CommonToken`, see example below:

```java
handleInputMismatchException((CommonToken) o, (Parser) recognizer);

final String offendingToken = offendingSymbol.getText();
final String expectedToken = parser.getExpectedTokens().toString(parser.getVocabulary());
```

[grammar]: ./internals/grammar.png "Grammar and Language set"
[language]: ./internals/language.png "Language content"
[error]: ./internals/error.png "Throwable Exceptions"
