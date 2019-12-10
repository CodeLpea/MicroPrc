该模块基于反射，实现了网络状态监听AOP
使用说明:
在目标Myapplication中初始化
NetStatusBus.getInstance().init;

然后在需要监听的activity或者fragment中注册
NetStatusBus.register(context);
在不需要使用的时候注销
NetStatusBus.unRegister(context);
或者注销全部：
NetStatusBus.unRegisterAllObserver()

在需要监听网路变化的方法上注解:
``
 @NetSubscribe(mode =Mode.AUTO,threadwhere=ThreadWhere.UI)
    public void netChange(NetType netType) {
        Log.d(Constrants.LOG_TAG, netType.name() + "<<<<<<<<<<BlankFragment");
        switch (netType) {
            case NONE:
                tvTips.setText("网络连接中断...");
                break;
            case WIFI:
                tvTips.setText("wifi已连接");   
                break;
            case MOBILE:
                tvTips.setText("移动网络已连接");
                break;
            default:
        }
    }
``

