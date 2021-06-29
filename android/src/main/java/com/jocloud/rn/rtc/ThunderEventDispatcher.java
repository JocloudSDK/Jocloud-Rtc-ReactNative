package com.jocloud.rn.rtc;

import com.thunder.livesdk.ThunderEventHandler;
import com.thunder.livesdk.ThunderNotification;
import com.yy.mediaframework.model.Rect;

import java.util.LinkedList;
import java.util.List;

/**
 * 事件传递
 *
 * @author huangcanbin@yy.com
 * @date 2020年9月24日
 */
public class ThunderEventDispatcher extends ThunderEventHandler {
    private static final String TAG = "ThunderEventDispatcher";
    private List<ThunderEventHandler> mHandlers = new LinkedList<>();

    public void addHandler(ThunderEventHandler handler) {
        mHandlers.add(handler);
    }

    public void removeHandler(ThunderEventHandler listener) {
        mHandlers.remove(listener);
    }

    @Override
    public void onError(int error) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onError(error);
        }
    }

    @Override
    public void onJoinRoomSuccess(String room, String uid, int elapsed) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onJoinRoomSuccess(room, uid, elapsed);
        }
    }

    @Override
    public void onLeaveRoom(ThunderEventHandler.RoomStats status) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onLeaveRoom(status);
        }
    }

    @Override
    public void onBizAuthResult(boolean bPublish, int result) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onBizAuthResult(bPublish, result);
        }
    }

    @Override
    public void onSdkAuthResult(int result) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onSdkAuthResult(result);
        }
    }

    @Override
    public void onUserBanned(boolean status) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onUserBanned(status);
        }
    }

    @Override
    public void onUserJoined(String uid, int elapsed) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onUserJoined(uid, elapsed);
        }
    }

    @Override
    public void onUserOffline(String uid, int reason) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onUserOffline(uid, reason);
        }
    }

    @Override
    public void onTokenWillExpire(byte[] token) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onTokenWillExpire(token);
        }
    }

    @Override
    public void onTokenRequested() {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onTokenRequested();
        }
    }

    @Override
    public void onNetworkQuality(String uid, int txQuality, int rxQuality) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onNetworkQuality(uid, txQuality, rxQuality);
        }
    }

    @Override
    public void onRoomStats(ThunderNotification.RoomStats stats) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onRoomStats(stats);
        }
    }

    @Override
    public void onDeviceStats(DeviceStats stats) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onDeviceStats(stats);
        }
    }

    @Override
    public void onPlayVolumeIndication(ThunderEventHandler.AudioVolumeInfo[] speakers,
                                       int totalVolume) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onPlayVolumeIndication(speakers, totalVolume);
        }
    }

    @Override
    public void onCaptureVolumeIndication(int totalVolume, int cpt, int micVolume) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onCaptureVolumeIndication(totalVolume, cpt, micVolume);
        }
    }

    @Override
    public void onAudioQuality(String uid, int quality, short delay, short lost) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onAudioQuality(uid, quality, delay, lost);
        }
    }

    @Override
    public void onConnectionLost() {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onConnectionLost();
        }
    }

    @Override
    public void onRemoteAudioPlay(String uid, int elapsed) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onRemoteAudioPlay(uid, elapsed);
        }
    }

    @Override
    public void onConnectionInterrupted() {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onConnectionInterrupted();
        }
    }

    @Override
    public void onAudioRouteChanged(int routing) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onAudioRouteChanged(routing);
        }
    }

    @Override
    public void onAudioPlayData(byte[] data, long cpt, long pts, String uid, long duration) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onAudioPlayData(data, cpt, pts, uid, duration);
        }
    }

    @Override
    public void onAudioPlaySpectrumData(byte[] data) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onAudioPlaySpectrumData(data);
        }
    }

    @Override
    public void onAudioCapturePcmData(byte[] data, int dataSize, int sampleRate, int channel) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onAudioCapturePcmData(data, dataSize, sampleRate, channel);
        }
    }

    @Override
    public void onAudioRenderPcmData(byte[] data, int dataSize, long duration, int sampleRate,
                                     int channel) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onAudioRenderPcmData(data, dataSize, duration, sampleRate, channel);
        }
    }

    @Override
    public void onRecvUserAppMsgData(byte[] data, String uid) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onRecvUserAppMsgData(data, uid);
        }
    }

    @Override
    public void onSendAppMsgDataFailedStatus(int status) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onSendAppMsgDataFailedStatus(status);
        }
    }

    @Override
    public void onRemoteAudioStopped(String uid, boolean stop) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onRemoteAudioStopped(uid, stop);
        }
    }

    @Override
    public void onRemoteAudioStateChangedOfUid(String uid, int state, int reason, int elapsed) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onRemoteAudioStateChangedOfUid(uid, state, reason, elapsed);
        }
    }

    @Override
    public void onRemoteAudioStatsOfUid(String uid, RemoteAudioStats stats) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onRemoteAudioStatsOfUid(uid, stats);
        }
    }

    @Override
    public void onRemoteVideoStopped(String uid, boolean stop) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onRemoteVideoStopped(uid, stop);
        }
    }

    @Override
    public void onRemoteVideoStatsOfUid(String uid, RemoteVideoStats stats) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onRemoteVideoStatsOfUid(uid, stats);
        }
    }

    @Override
    public void onRemoteVideoStateChangedOfUid(String uid, int state, int reason, int elapsed) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onRemoteVideoStateChangedOfUid(uid, state, reason, elapsed);
        }
    }


    @Override
    public void onRemoteVideoPlay(String uid, int width, int height, int elapsed) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onRemoteVideoPlay(uid, width, height, elapsed);
        }
    }

    @Override
    public void onVideoSizeChanged(String uid, int width, int height, int rotation) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onVideoSizeChanged(uid, width, height, rotation);
        }
    }


    @Override
    public void onLocalAudioStatusChanged(int status, int errorReason) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onLocalAudioStatusChanged(status, errorReason);
        }
    }

    @Override
    public void onFirstLocalAudioFrameSent(int elapsed) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onFirstLocalAudioFrameSent(elapsed);
        }
    }

    @Override
    public void onLocalAudioStats(LocalAudioStats stats) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onLocalAudioStats(stats);
        }
    }

    @Override
    public void onFirstLocalVideoFrameSent(int elapsed) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onFirstLocalVideoFrameSent(elapsed);
        }
    }

    @Override
    public void onLocalVideoStats(LocalVideoStats stats) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onLocalVideoStats(stats);
        }
    }

    @Override
    public void onLocalVideoStatusChanged(int status, int errorReason) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onLocalVideoStatusChanged(status, errorReason);
        }
    }

    @Override
    public void onPublishStreamToCDNStatus(String url, int errorCode) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onPublishStreamToCDNStatus(url, errorCode);
        }
    }

    @Override
    public void onNetworkTypeChanged(int type) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onNetworkTypeChanged(type);
        }
    }

    @Override
    public void onConnectionStatus(int status) {
        for (ThunderEventHandler listener : mHandlers) {
            listener.onConnectionStatus(status);
        }
    }

    @Override
    public void onAudioCaptureStatus(int status) {
        super.onAudioCaptureStatus(status);
        for (ThunderEventHandler listener : mHandlers) {
            listener.onAudioCaptureStatus(status);
        }
    }

    @Override
    public void onEchoDetectResult(boolean bEcho) {
        super.onEchoDetectResult(bEcho);
        for (ThunderEventHandler listener : mHandlers) {
            listener.onEchoDetectResult(bEcho);
        }
    }

    @Override
    public void onHowlingDetectResult(boolean bHowling) {
        super.onHowlingDetectResult(bHowling);
        for (ThunderEventHandler listener : mHandlers) {
            listener.onHowlingDetectResult(bHowling);
        }
    }

    @Override
    public void onVideoCaptureStatus(int status) {
        super.onVideoCaptureStatus(status);
        for (ThunderEventHandler listener : mHandlers) {
            listener.onVideoCaptureStatus(status);
        }
    }

    @Override
    public void onAudioInputDeviceTestVolume(int volume) {
        super.onAudioInputDeviceTestVolume(volume);
        for (ThunderEventHandler listener : mHandlers) {
            listener.onAudioInputDeviceTestVolume(volume);
        }
    }

    @Override
    public void onAudioOutputDeviceTestVolume(int volume) {
        super.onAudioOutputDeviceTestVolume(volume);
        for (ThunderEventHandler listener : mHandlers) {
            listener.onAudioOutputDeviceTestVolume(volume);
        }
    }

    @Override
    public void onBizAuthStreamResult(boolean bPublish, int bizAuthStreamType, int bizAuthResult) {
        super.onBizAuthStreamResult(bPublish, bizAuthStreamType, bizAuthResult);
        for (ThunderEventHandler listener : mHandlers) {
            listener.onBizAuthStreamResult(bPublish, bizAuthStreamType, bizAuthResult);
        }
    }

    @Override
    public void onCameraExposureAreaChanged(Rect rect) {
        super.onCameraExposureAreaChanged(rect);
        for (ThunderEventHandler listener : mHandlers) {
            listener.onCameraExposureAreaChanged(rect);
        }
    }

    @Override
    public void onCameraFocusAreaChanged(Rect rect) {
        super.onCameraFocusAreaChanged(rect);
        for (ThunderEventHandler listener : mHandlers) {
            listener.onCameraFocusAreaChanged(rect);
        }
    }
}
