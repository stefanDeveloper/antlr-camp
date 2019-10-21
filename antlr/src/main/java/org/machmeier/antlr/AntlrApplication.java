package org.machmeier.antlr;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.machmeier.antlr.math.ExpressionLexer;
import org.machmeier.antlr.math.ExpressionParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AntlrApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntlrApplication.class, args);
    }
}
