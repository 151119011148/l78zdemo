package com.scofen.util.antlr4.math;

import com.scofen.util.antlr4.math.generate.MathBaseVisitor;
import com.scofen.util.antlr4.math.generate.MathParser;

import java.util.HashMap;
import java.util.Map;

public class MathVisitorImp extends MathBaseVisitor<Integer> {
    // 存储变量的值
    private Map<String, Integer> variable;

    public MathVisitorImp() {
        variable = new HashMap<>();
    }

    // 当遇到printExpr节点，计算出exrp的值，然后打印出来
    @Override
    public Integer visitPrintExpr(MathParser.PrintExprContext ctx) {
        Integer result  = ctx.expr().accept(this);
        System.out.println(result);
        return null;
    }

    // 分别获取子节点expr的值，然后做加减运算
    @Override
    public Integer visitAddSub(MathParser.AddSubContext ctx) {
        Integer param1 = ctx.expr(0).accept(this);
        Integer param2 = ctx.expr(1).accept(this);
        if (ctx.op.getType() == MathParser.ADD) {
            return param1 + param2;
        }else {
            return param1 - param2;
        }
    }

    // 分别获取子节点expr的值，然后做乘除运算
    @Override
    public Integer visitMulDiv(MathParser.MulDivContext ctx) {
        Integer param1 = ctx.expr(0).accept(this);
        Integer param2 = ctx.expr(1).accept(this);
        if (ctx.op.getType() == MathParser.MUL) {
            return param1 * param2;
        }else {
            return param1 / param2;
        }
    }

    // 当遇到int节点，直接返回数据
    @Override
    public Integer visitInt(MathParser.IntContext ctx) {
        return Integer.parseInt(ctx.getText());
    }

    // 当遇到Id节点，从变量表获取值
    @Override
    public Integer visitId(MathParser.IdContext ctx) {
        return variable.get(ctx.getText());
    }

    // 当遇到赋值语句，获取右边expr的值，然后将变量的值保存到variable集合
    @Override
    public Integer visitAssign(MathParser.AssignContext ctx) {
        String name = ctx.ID().getText();
        Integer value = ctx.expr().accept(this);
        variable.put(name, value);
        return null;
    }

}