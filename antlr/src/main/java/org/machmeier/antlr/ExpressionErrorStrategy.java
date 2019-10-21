package org.machmeier.antlr;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class ExpressionErrorStrategy extends BaseErrorListener {

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
        super.syntaxError(recognizer, o, i, i1, "Error occurred in Expression, please check!", e);
    }
}
