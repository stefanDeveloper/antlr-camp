package org.machmeier.antlr;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.machmeier.antlr.math.ExpressionLexer;
import org.machmeier.antlr.math.ExpressionParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = AntlrApplication.class)
public class NormalExpressionTest {
    @Test
    public void testExpression() {

        final ExpressionLexer lexer = new ExpressionLexer(CharStreams.fromString("a=1\n5+6-a\n"));
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final ExpressionParser expressionParser = new ExpressionParser(tokens);
        expressionParser.addErrorListener(new ExpressionErrorStrategy());

        ParseTree tree = expressionParser.prog();
        ExpressionParserVisitor visitor = new ExpressionParserVisitor();
        Assert.assertEquals(10, (long) visitor.visit(tree));
    }
}
