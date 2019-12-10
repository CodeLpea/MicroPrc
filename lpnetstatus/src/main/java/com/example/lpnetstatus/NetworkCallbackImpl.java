package com.example.lpnetstatus;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.lpnetstatus.utils.NetworkUtils;

/**
 * 监听网络状态变化的实现类
 * NetworkCallbackImpl
 * */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NetworkCallbackImpl extends ConnectivityManager.NetworkCallback {
    private NetStatusReceiver mReceiver;

    public NetworkCallbackImpl(NetStatusReceiver receiver) {
        mReceiver = receiver;
    }


    @Override
    public void onAvailable(Network network) {
        super.onAvailable(network);
        mReceiver.post(NetworkUtils.getNetType());
    }

    @Override
    public void onLost(Network network) {
        super.onLost(network);
        mReceiver.post(NetworkUtils.getNetType());
    }

    @Override
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities);
    }

}
