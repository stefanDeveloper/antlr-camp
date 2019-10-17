package org.machmeier.antlr;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.machmeier.antlr.math.ExpressionLexer;
import org.machmeier.antlr.math.ExpressionParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AntlrApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntlrApplication.class, args);
        final ExpressionLexer lexer = new ExpressionLexer(CharStreams.fromString("((5+5)-5+200)"));
        final TokenStream tokens = new CommonTokenStream(lexer);
        final ExpressionParser expressionParser = new ExpressionParser(tokens);
        ExpressionParserListener listener = new ExpressionParserListener();
        ExpressionParser.ExpressionContext context = expressionParser.expression();

        System.out.println(visit(context));
    }

    private static int visit(final ExpressionParser.ExpressionContext context) {
        if (context.INT() != null) { //Just a number
            return Integer.parseInt(context.INT().getText());
        } else if (context.CLOSE_BRACKET() != null) { //Expression between brackets
            return visit(context.expression(0));
        } else if (context.operation().PLUS() != null) { //Expression + expression
            return visit(context.expression(0)) + visit(context.expression(1));
        } else if (context.operation().MINUS() != null) { //Expression - expression
            return visit(context.expression(0)) - visit(context.expression(1));
        } else {
            throw new IllegalStateException();
        }
    }

}
