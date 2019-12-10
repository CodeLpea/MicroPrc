package com.example.lpnetstatus;

/**
 * 管理使用注解的方法
 * MethodManager
 */
import com.example.lpnetstatus.annotation.type.Mode;
import com.example.lpnetstatus.annotation.type.ThreadWhere;

import java.lang.reflect.Method;

/**
 * 保存符合要求的网络监听注解方法
 */
public class MethodManager {

    //参数类型
    private Class<?> parameterClazz;

    //订阅类型
    private Mode mode;

    //执行位置
    private ThreadWhere threadWhere;

    //需要执行的方法
    private Method method;

    public MethodManager(Class<?> parameterClazz, Mode mode, ThreadWhere threadWhere, Method method) {
        this.parameterClazz = parameterClazz;
        this.mode = mode;
        this.threadWhere = threadWhere;
        this.method = method;
    }

    public Class<?> getParameterClazz() {
        return parameterClazz;
    }

    public void setParameterClazz(Class<?> parameterClazz) {
        this.parameterClazz = parameterClazz;
    }

    public ThreadWhere getThreadWhere() {
        return threadWhere;
    }

    public void setThreadWhere(ThreadWhere threadWhere) {
        this.threadWhere = threadWhere;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
