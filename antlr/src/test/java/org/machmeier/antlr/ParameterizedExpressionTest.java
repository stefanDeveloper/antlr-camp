package org.machmeier.antlr;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.machmeier.antlr.math.ExpressionLexer;
import org.machmeier.antlr.math.ExpressionParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = AntlrApplication.class)
public class ParameterizedExpressionTest {

    public Collection<Object[]> data() {
        ArrayList<Object[]> params = new ArrayList<>();

        params.add(new Object[]{"a=2\nb=3\na+b\n", 5});
        params.add(new Object[]{"a=7\nb=5\na-b\n", 2});
        params.add(new Object[]{"a=2\nb=3\na*b\n", 6});
        params.add(new Object[]{"a=9\nb=3\na/b\n", 3});
        params.add(new Object[]{"a=9\nb=3\n(a+b)/3\n", 4});
        params.add(new Object[]{"a=2\nb=3\n(a+b)\n", 5});

        return params;
    }

    @Test
    @Parameters(method = "data")
    public void testExpression(String expression, double result) {

        final ExpressionLexer lexer = new ExpressionLexer(CharStreams.fromString(expression));
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final ExpressionParser expressionParser = new ExpressionParser(tokens);
        expressionParser.addErrorListener(new ExpressionErrorStrategy());

        ParseTree tree = expressionParser.prog();
        ExpressionParserVisitor visitor = new ExpressionParserVisitor();
        Assert.assertEquals((long) result, (long) visitor.visit(tree));
    }
}
