package org.machmeier.antlr;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.machmeier.antlr.math.ExpressionParser;
import org.machmeier.antlr.math.ExpressionParserBaseVisitor;

import java.util.HashMap;
import java.util.Map;

public class ExpressionParserVisitor extends ExpressionParserBaseVisitor<Integer> {

    private Map<String, Integer> memory = new HashMap<String, Integer>();

    @Override
    public Integer visitAssign(ExpressionParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        int value = visit(ctx.expr());
        memory.put(id, value);
        return value;
    }

    @Override
    public Integer visitPrintExpr(ExpressionParser.PrintExprContext ctx) {
        Integer value = visit(ctx.expr());
        System.out.println(value);
        return 0;
    }

    @Override
    public Integer visitInt(ExpressionParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    @Override
    public Integer visitId(ExpressionParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) return memory.get(id);
        return 0;
    }

    @Override
    public Integer visitMulDiv(ExpressionParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == ExpressionParser.MUL) return left * right;
        return left / right;
    }

    @Override
    public Integer visitAddSub(ExpressionParser.AddSubContext ctx) {
        int left = visit(ctx.expr(0)); // get value of left subexpression
        int right = visit(ctx.expr(1)); // get value of right subexpression
        if (ctx.op.getType() == ExpressionParser.ADD) return left + right;
        return left - right; // must be SUB
    }

    @Override
    public Integer visitParens(ExpressionParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

}
