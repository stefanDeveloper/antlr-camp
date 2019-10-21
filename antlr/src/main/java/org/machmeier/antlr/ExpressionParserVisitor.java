package org.machmeier.antlr;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.machmeier.antlr.math.ExpressionParser;
import org.machmeier.antlr.math.ExpressionParserBaseVisitor;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ExpressionParserVisitor extends ExpressionParserBaseVisitor<Integer> {

    private final Map<String, Integer> memory = new HashMap<>();

    @Override
    public Integer visitAssign(final ExpressionParser.AssignContext ctx) {
        final String id = ctx.ID().getText();
        final int value = visit(ctx.expr());
        memory.put(id, value);
        return value;
    }

    @Override
    public Integer visitPrintExpr(final ExpressionParser.PrintExprContext ctx) {
        final Integer value = visit(ctx.expr());
        log.info("Value:" + value);
        return 0;
    }

    @Override
    public Integer visitInt(final ExpressionParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    @Override
    public Integer visitId(final ExpressionParser.IdContext ctx) {
        final String id = ctx.ID().getText();
        if (memory.containsKey(id)) {
            return memory.get(id);
        }
        return 0;
    }

    @Override
    public Integer visitMulDiv(final ExpressionParser.MulDivContext ctx) {
        final int left = visit(ctx.expr(0));
        final int right = visit(ctx.expr(1));
        if (ctx.op.getType() == ExpressionParser.MUL) {
            return left * right;
        }
        return left / right;
    }

    @Override
    public Integer visitAddSub(final ExpressionParser.AddSubContext ctx) {
        final int left = visit(ctx.expr(0)); // get value of left subexpression
        final int right = visit(ctx.expr(1)); // get value of right subexpression
        if (ctx.op.getType() == ExpressionParser.ADD) {
            return left + right;
        }
        return left - right; // must be SUB
    }

    @Override
    public Integer visitParens(final ExpressionParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

}
