package com.jocloud.rn.rtc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.thunder.livesdk.video.ThunderPreviewView;

/**
 * 本地视频view封装
 *
 * @author huangcanbin@yy.com
 * @date 2020年9月24日
 */
public class ThunderLocalVideoView extends RelativeLayout {
    public ThunderLocalVideoView(Context context) {
        this(context, null, 0);
    }

    public ThunderLocalVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThunderLocalVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ini(context, attrs, defStyleAttr);
    }

    private ThunderPreviewView mThunderPreviewView;

    private void ini(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.view_local, this);

        mThunderPreviewView = findViewById(R.id.view);
    }

    public ThunderPreviewView getThunderPreviewView() {
        return mThunderPreviewView;
    }

}
