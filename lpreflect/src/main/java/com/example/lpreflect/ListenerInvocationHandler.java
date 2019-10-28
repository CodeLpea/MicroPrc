package com.example.lpreflect;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ListenerInvocationHandler implements InvocationHandler {
    private static final String TAG = "Handler";
    //需要知道切面的哪个类中的方法（拦截的目标）
    private Object target;
    private HashMap<String ,Method> hashMap=new HashMap<>();

    public ListenerInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //将本应该回调的Onclik方法拦截，去执行我们自定义的含有Onclick注解的方法
        if(target!=null){
            String methodName = method.getName();
            method = hashMap.get(methodName);
            if(method!=null){
                Log.i(TAG, "invoke: "+method.getName());
                if(method.getGenericParameterTypes().length==0){
                    return method.invoke(target);
                }
                return method.invoke(target,args);
            }

        }



        return null;
    }

    /**
     * 拦截回调方法，执行开发者自定义的方法
     * */
    public void add(String callBackListener, Method method) {
        hashMap.put(callBackListener,method);
    }
}
