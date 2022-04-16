package com.utils;
/**
 * An functional interface allowing us to pass lambdas into function parameters in UserInputViews.java
 * @author DSF 1 Group 1
 */
@FunctionalInterface
public interface AnonymousFunction<ReturnType, ParamType> {
    public abstract ReturnType execute(ParamType param);
}

