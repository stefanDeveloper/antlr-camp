package org.machmeier.antlr;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.machmeier.antlr.math.ExpressionParserBaseListener;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExpressionParserListener extends ExpressionParserBaseListener {

    public static final ExpressionParserListener INSTANCE = new ExpressionParserListener();

}
