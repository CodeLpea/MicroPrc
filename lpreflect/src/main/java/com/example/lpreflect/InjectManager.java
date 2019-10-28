package com.example.lpreflect;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.example.lpreflect.annotation.ClickEventBase;
import com.example.lpreflect.annotation.ContentView;
import com.example.lpreflect.annotation.FindView;
import com.example.lpreflect.annotation.OnClick;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*注入管理类*/
public class InjectManager {
    private static final String TAG = "InjectManager";

    //  完成布局，属性，控件，以及点击事件的注入
    public static void inject(Activity activity) {
        Log.i(TAG, "inject: 绑定ioc注入");
        //布局的注入
        injectLayout(activity);

        //控件的注入
        injectViews(activity);

        //点击事件的注入
        injectEvents(activity);

    }

    /**
     * 点击事件的注入
     */
    private static void injectEvents(Activity activity) {
        Log.i(TAG, "injectEvents: 点击事件的注入");
        Class<? extends Activity> clazz = activity.getClass();
        //遍历clazz所有的方法包括私有
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            //因为一个方法上可能有很多注解，所以要遍历所有的注解
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                //如果方法上的注解不为空
                if (annotationType != null) {
                    //判断是否有我们想要的OnClick->ClickEventBase注解
                    ClickEventBase clickEventBase = annotationType.getAnnotation(ClickEventBase.class);
                    if (clickEventBase != null) {
                        //如果有ClickEventBase的注解，就意味着有Onclick注解
                        //获取注解的属性
                        //获取监听方法名
                        String listener = clickEventBase.listenerSetter();
                        //获取监听接口
                        Class<?> listenerType = clickEventBase.listenerType();
                        //获取监听回调方法名
                        String callBackListener = clickEventBase.callBackListener();

                        //需要使用一个代理，帮助完成点击动作
                        //获取Onclick直接上的值
//                        OnClick onClick = clazz.getAnnotation(OnClick.class);
//                        int[] value = onClick.value();
                        try {
                            Method value = annotationType.getDeclaredMethod("value");
                            int[] viewsId = (int[]) value.invoke(annotation);

                            //拦截+执行
                            ListenerInvocationHandler handler = new ListenerInvocationHandler(activity);

                            handler.add(callBackListener, method);

                            Object listenner = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, handler);
                            for (int viewId : viewsId) {
                                Log.i(TAG, "viewId: " + viewId);
                                //先绑定控件
                                View view = activity.findViewById(viewId);
                                Method onClicMethod = view.getClass().getMethod(listener, listenerType);
                                //执行方法
                                onClicMethod.invoke(view, listenner);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }


                }
            }


        }
    }

    /**
     * 控件的注入
     */
    private static void injectViews(Activity activity) {
        Log.i(TAG, "injectViews: 控件的注入");
        //首先还是获取activity
        Class<? extends Activity> clazz = activity.getClass();
        //由于控件是属性，需要遍历所有的属性包含私有的
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            FindView fieldAnnotation = field.getAnnotation(FindView.class);
            if (fieldAnnotation != null) {
                int id = fieldAnnotation.value();
                //执行findViewById(R.id.xxx);
                try {
                    Method fieldMethod = clazz.getMethod("findViewById", int.class);
                    //执行执行findViewById，并接收返回值
                    Object viewObject = fieldMethod.invoke(activity, id);
                    //开启私有属性赋值权限
                    field.setAccessible(true);
                    //再把这个属性重新赋值为获取到的返回值
                    field.set(activity, viewObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 布局的注入
     */
    private static void injectLayout(Activity activity) {
        Log.i(TAG, "injectLayout: 布局的注入");
        //首先获取activity
        Class<? extends Activity> clazz = activity.getClass();
        //通过反射，获取类至上的注解,getAnnotation（xxx.class）获取到xxx注解
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            //activity.setContentView(R.layout.activity_main);
            int layoutId = contentView.value();

            //获取到父类的方法
            try {
                Method setContentView = clazz.getMethod("setContentView", int.class);

                setContentView.invoke(activity, layoutId);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }


    }

}
