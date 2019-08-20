package com.example.lp.lpdesignpatterns.observerMode;

public class TestObeserver {
    public static void main(String[] args) {
        //创建被观察者
        Observable postman=new Postman();
        //创建观察者
        Observer boy=new Boy("Tom");
        Observer gril=new Girl("Mary");
        //将观察者添加到被观察者中
        postman.add(boy);
        postman.add(gril);

        //被观察者发出订阅消息，通知所有观察者
        postman.notify("快递到了");
    }
}
