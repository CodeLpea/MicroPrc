package com.example.lpnetstatus.annotation.type;

/**
 * 监听网络状态的模式
 * Mode
 */
public enum Mode {

    /**
     * 默认的订阅模式，任何网络变化均会回调使用此模式的订阅方法
     */
    AUTO,

    /**
     * 针对 wifi 的订阅模式，当 wifi 网络变化(包括网络丢失)时会回调使用此模式的订阅方法
     */
    WIFI,

    /**
     * 只有当 wifi 连接时才会回调使用此模式的订阅方法
     */
    WIFI_CONNECT,

    /**
     * 针对 移动网络 的订阅模式，当 移动网络 网络变化(包括网络丢失)时会回调使用此模式的订阅方法
     */
    MOBILE,

    /**
     * 只有当 移动网络 连接时才会回调使用此模式的订阅方法
     */
    MOBILE_CONNECT,

    /**
     * 当网络丢失时会调用使用该模式的订阅方法
     */
    NONE

}
