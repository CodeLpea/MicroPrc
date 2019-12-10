package com.example.lpnetstatus;



import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.lpnetstatus.annotation.NetSubscribe;
import com.example.lpnetstatus.annotation.type.NetType;
import com.example.lpnetstatus.annotation.type.ThreadWhere;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 网络变化接收，执行
 * NetStatusReceiver
 */

public class NetStatusReceiver {

    //用于线程切换
    private Handler handler = new Handler(Looper.getMainLooper());


    private NetType mNetType;//网络类型

    private Map<Object, List<MethodManager>> networkList;


    protected NetStatusReceiver() {
        mNetType = NetType.NONE;
        networkList = new HashMap<>();
    }

    /**
     * 分发
     */
    protected void post(NetType netType) {
        //所有的注册类
        Set<Object> subscribeClazzSet = networkList.keySet();
        this.mNetType = netType;
        for (Object subscribeClazz : subscribeClazzSet) {
            List<MethodManager> methodManagerList = networkList.get(subscribeClazz);
            executeInvoke(subscribeClazz, methodManagerList);
        }
    }

    private void executeInvoke(final Object subscribeClazz, List<MethodManager> methodManagerList) {
        if (methodManagerList != null) {
            for (final MethodManager subscribeMethod : methodManagerList) {

                switch (subscribeMethod.getMode()) {
                    case AUTO:
                        if(subscribeMethod.getThreadWhere()== ThreadWhere.BACKGURAND){
                            Log.e("invoke", "BACKGURAND: ");
                            invoke(subscribeMethod, subscribeClazz, mNetType);
                        }else {
                            Log.e("invoke", "UI: ");
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    invoke(subscribeMethod, subscribeClazz, mNetType);
                                }
                            });
                        }
                        break;

                    case WIFI:
                        if (mNetType == NetType.WIFI || mNetType == NetType.NONE){
                            if(subscribeMethod.getThreadWhere()== ThreadWhere.BACKGURAND){
                                Log.e("WIFI invoke", "BACKGURAND: ");
                                invoke(subscribeMethod, subscribeClazz, mNetType);
                            }else {
                                Log.e("WIFI invoke", "UI: ");
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        invoke(subscribeMethod, subscribeClazz, mNetType);
                                    }
                                });
                            }
                        }
                        break;

                    case WIFI_CONNECT:
                        if (mNetType == NetType.WIFI){
                            if(subscribeMethod.getThreadWhere()== ThreadWhere.BACKGURAND){
                                Log.e("WIFI_CONNECT invoke", "BACKGURAND: ");
                                invoke(subscribeMethod, subscribeClazz, mNetType);
                            }else {
                                Log.e(" WIFI_CONNECT invoke", "UI: ");
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        invoke(subscribeMethod, subscribeClazz, mNetType);
                                    }
                                });
                            }
                        }
                        break;

                    case MOBILE:
                        if (mNetType == NetType.MOBILE || mNetType == NetType.NONE){
                            if(subscribeMethod.getThreadWhere()== ThreadWhere.BACKGURAND){
                                Log.e("MOBILE Dis invoke", "BACKGURAND: ");
                                invoke(subscribeMethod, subscribeClazz, mNetType);
                            }else {
                                Log.e("MOBILE DIs invoke", "UI: ");
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        invoke(subscribeMethod, subscribeClazz, mNetType);
                                    }
                                });
                            }
                        }

                        break;

                    case MOBILE_CONNECT:
                        if (mNetType == NetType.MOBILE) {
                            if(subscribeMethod.getThreadWhere()== ThreadWhere.BACKGURAND){
                                Log.e("MOBILE_CONNECT invoke", "BACKGURAND: ");
                                invoke(subscribeMethod, subscribeClazz, mNetType);
                            }else {
                                Log.e("MOBILE_CONNECT invoke", "UI: ");
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        invoke(subscribeMethod, subscribeClazz, mNetType);
                                    }
                                });
                            }
                        }
                        break;

                    case NONE:
                        if (mNetType == NetType.NONE){
                            if(subscribeMethod.getThreadWhere()== ThreadWhere.BACKGURAND){
                                Log.e("NONE invoke", "BACKGURAND: ");
                                invoke(subscribeMethod, subscribeClazz, mNetType);
                            }else {
                                Log.e("NONE invoke", "UI: ");
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        invoke(subscribeMethod, subscribeClazz, mNetType);
                                    }
                                });
                            }
                        }

                    default:
                }
            }
        }
    }

    private void invoke(MethodManager subscribeMethod, Object subscribeClazz, NetType netType) {

        Method execute = subscribeMethod.getMethod();
        // 调用private方法的关键一句话
        execute.setAccessible(true);
        try {
            //有参数时
            if (subscribeMethod.getParameterClazz() != null) {
                if (subscribeMethod.getParameterClazz().isAssignableFrom(mNetType.getClass())) {
                    execute.invoke(subscribeClazz, netType);
                }
            } else {
                execute.invoke(subscribeClazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void registerObserver(Object mContext) {
        List<MethodManager> methodList = networkList.get(mContext);
        if (methodList == null) {
//        开始添加
            methodList = findAnnotationMethod(mContext);
            networkList.put(mContext, methodList);
        }
        //执行一次
        executeInvoke(mContext, networkList.get(mContext));
    }

    private List<MethodManager> findAnnotationMethod(Object mContext) {
        List<MethodManager> methodManagerList = new ArrayList<>();
//        获取到activity fragment
        Class<?> clazz = mContext.getClass();
        Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                NetSubscribe netSubscribe = method.getAnnotation(NetSubscribe.class);
                if (netSubscribe == null) {
                    continue;
                }
                //注解方法校验返回值
                Type genericReturnType = method.getGenericReturnType();
                if (!"void".equalsIgnoreCase(genericReturnType.toString())) {
                    throw new IllegalArgumentException("you " + method.getName() + "method return value must be void");
                }

                //判断参数
                Class<?>[] parameterTypes = method.getParameterTypes();
                MethodManager methodManager;
                if (parameterTypes.length == 0) {
                    methodManager = new MethodManager(null, netSubscribe.mode(),netSubscribe.threadwhere(), method);
                } else if (parameterTypes.length == 1) {
                    methodManager = new MethodManager(parameterTypes[0], netSubscribe.mode(),netSubscribe.threadwhere(), method);
                } else {
                    throw new IllegalArgumentException("Your method " + method.getName() + " can have at most one parameter of type NetType ");
                }

                methodManagerList.add(methodManager);
            }

        return methodManagerList;
    }

    public void unRegisterObserver(Object mContext) {
        if (!networkList.isEmpty()) {
            networkList.remove(mContext);
        }
    }

    public void unRegisterAllObserver() {
        if (!networkList.isEmpty()) {
            networkList.clear();
            networkList = null;
        }
    }
}
