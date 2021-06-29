package com.jocloud.rn.rtc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.thunder.livesdk.video.ThunderPlayerView;

/**
 * 远端视频view封装
 */
public class ThunderRemoteVideoView extends RelativeLayout {
    public ThunderRemoteVideoView(Context context) {
        this(context, null, 0);
    }

    public ThunderRemoteVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThunderRemoteVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ini(context, attrs, defStyleAttr);
    }

    private ThunderPlayerView mThunderPlayerView;

    private void ini(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.view_remote, this);

        mThunderPlayerView = findViewById(R.id.view);
    }

    public ThunderPlayerView getThunderPlayerView() {
        return mThunderPlayerView;
    }

}
