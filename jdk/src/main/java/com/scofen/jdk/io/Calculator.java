package com.scofen.jdk.io;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Create by  GF  in  9:18 2019/1/23
 * Description:
 * Modified  By:
 */
public class Calculator {
    private final static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
    public static Object cal(String expression) throws ScriptException {
        return jse.eval(expression);
    }
}
