package com.processexample.process.excutor;

public interface ProcessExecutorHandler {

    public abstract void paramOutToInnerTranslate();

    public abstract String handleFirstStep();

    public abstract Short getNextIndex(String string);

    public abstract String executeByIndex(Short index);

    public abstract void paramInnerToOutTranslate();


}
