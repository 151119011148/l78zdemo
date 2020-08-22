package com.scofen.algorithms.book.part1;

import java.util.Stack;

/**
 * Create by  GF  in  9:10 2020/7/11
 * Description:1.1基础模型编程
 * Modified  By:
 */
public class Test1 {

    public static void main(String[] args) {
//        System.out.println(toTenString(1082864));
        System.out.println(log(2, 2));
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////1.1
    //将一个正整数N用二进制表示并转换为一个String类型的值
    public static String toBinaryString(int N){

        StringBuilder result = new StringBuilder();
        for (int i = N; i > 0 ; i /= 2){
            result.insert(0, (i % 2));
        }
        return result.toString();
    }

    //将一个正整数N用十进制表示并转换为一个String类型的值
    public static String toTenString(int N){

        StringBuilder result = new StringBuilder();
        for (int i = N; i > 0 ; i /= 10){
            result.insert(0, (i % 10));
        }
        return result.toString();
    }

    //接受一个正整数N，返回不大于log2 N的最大整数。
    public static int log(int low, int N){
        int result = 0;
        int mi = N;
        while(( mi = mi / low) >= 1){
            result ++;
        }
        return result;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////1.2

    //////////////////////////////////////////////////////////////////////////////////////////////////1.3
    //算术表达式求值
    //双栈求值算法
    public static double getValue(String expression){
        //运算符栈
        Stack<String> ops = new Stack<>();
        //操作数栈
        Stack<Double> vals = new Stack<>();
        char[] chars = expression.toCharArray();
        for (int i = 0; i < chars.length; i ++){
            String current = String.valueOf(chars[i]);
            if (current.equals("(")){

            }else if(current.equals("+")){
                ops.push(current);
            }else if(current.equals("-")){
                ops.push(current);
            }else if(current.equals("*")){
                ops.push(current);
            }else if(current.equals("/")){
                ops.push(current);
            }else if(current.equals(")")){
                String op = ops.pop();
                double v = vals.pop();
                if (op.equals("+")){
                    v = vals.pop() + v;
                }else if(op.equals("-")) {
                    v = vals.pop() - v;
                }else if(op.equals("*")) {
                    v = vals.pop() * v;
                }else if(op.equals("/")) {
                    v = vals.pop() / v;
                    vals.push(v);
                }else {
                    //既不是运算符也不是括号
                    vals.push(Double.parseDouble(current));
                }
            }
        }
        return vals.pop();
    }


}
