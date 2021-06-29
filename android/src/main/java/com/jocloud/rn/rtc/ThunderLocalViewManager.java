package com.jocloud.rn.rtc;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.thunder.livesdk.ThunderVideoCanvas;

import androidx.annotation.NonNull;

public class ThunderLocalViewManager extends SimpleViewManager<ThunderLocalVideoView> {
    @NonNull
    @Override
    public String getName() {
        return "ThunderLocalVideoView";
    }

    @NonNull
    @Override
    protected ThunderLocalVideoView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new ThunderLocalVideoView(reactContext);
    }

    @ReactProp(name = "setLocalVideoCanvas")
    public void setLocalVideoCanvas(final ThunderLocalVideoView thunderVideoView,
                                    ReadableMap data) {
        int renderMode = data.getInt("renderMode");
        String uid = data.getString("uid");
        ThunderVideoCanvas thunderVideoCanvas =
                new ThunderVideoCanvas(thunderVideoView.getThunderPreviewView(),
                        renderMode, uid);
        ThunderManager.getInstance().setLocalVideoCanvas(thunderVideoCanvas);
    }
}
