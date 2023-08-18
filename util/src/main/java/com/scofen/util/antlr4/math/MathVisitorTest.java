package com.scofen.util.antlr4.math;

import com.scofen.util.antlr4.math.generate.MathBaseVisitor;
import com.scofen.util.antlr4.math.generate.MathLexer;
import com.scofen.util.antlr4.math.generate.MathParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class MathVisitorTest {
    public static void exec(String input) {
        MathLexer lexer = new MathLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MathParser parser = new MathParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree root = parser.prog();
        MathBaseVisitor<Integer> visitor = new MathVisitorImp();
        root.accept(visitor);
    }

    public static void main(String[] args) {
//        String input = "1+2+3+5-7+1\n";
        String input = "a = 3\n" +
                "b = a*a\n" +
                "a + b\n";
        exec(input);
    }

}