package com.utils;
@FunctionalInterface
public interface AnonymousFunction<ReturnType, ParamType> {
    public abstract ReturnType execute(ParamType param);
}

