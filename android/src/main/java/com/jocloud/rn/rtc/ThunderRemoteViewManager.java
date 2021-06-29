package com.jocloud.rn.rtc;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.thunder.livesdk.ThunderVideoCanvas;

import androidx.annotation.NonNull;

public class ThunderRemoteViewManager extends SimpleViewManager<ThunderRemoteVideoView> {
    @NonNull
    @Override
    public String getName() {
        return "ThunderRemoteVideoView";
    }

    @NonNull
    @Override
    protected ThunderRemoteVideoView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new ThunderRemoteVideoView(reactContext);
    }

    @ReactProp(name = "setRemoteVideoCanvas")
    public void setRemoteVideoCanvas(final ThunderRemoteVideoView thunderVideoView,
                                     ReadableMap data) {
        int renderMode = data.getInt("renderMode");
        String uid = data.getString("uid");
        ThunderVideoCanvas thunderVideoCanvas =
                new ThunderVideoCanvas(thunderVideoView.getThunderPlayerView(),
                        renderMode, uid);
        ThunderManager.getInstance().setRemoteVideoCanvas(thunderVideoCanvas);
    }
}
