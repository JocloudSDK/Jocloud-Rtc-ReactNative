package com.jocloud.rn.rtc;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.thunder.livesdk.IThunderLogCallback;
import com.thunder.livesdk.IThunderMediaExtraInfoCallback;
import com.thunder.livesdk.ThunderEventHandler;
import com.thunder.livesdk.ThunderNotification;
import com.thunder.livesdk.ThunderVideoEncodeParam;
import com.thunder.livesdk.ThunderVideoEncoderConfiguration;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.facebook.react.bridge.UiThreadUtil.runOnUiThread;

/**
 * Thunder模块
 *
 * @author huangcanbin@yy.com
 * @date 2020年9月24日
 */
public class ThunderModule extends ReactContextBaseJavaModule implements IThunderMediaExtraInfoCallback {

    public ThunderModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    private ThunderEventHandler thunderEventHandler = new ThunderEventHandler() {

        @Override
        public void onJoinRoomSuccess(String room, String uid, int elapsed) {
            super.onJoinRoomSuccess(room, uid, elapsed);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putString("room", room);
                map.putString("uid", uid);
                map.putInt("elapsed", elapsed);
                sendEvent(getReactApplicationContext(), "onJoinRoomSuccess", map);
            });
        }

        @Override
        public void onLeaveRoom(RoomStats status) {
            super.onLeaveRoom(status);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                sendEvent(getReactApplicationContext(), "onLeaveRoom", map);
            });
        }

        @Override
        public void onSdkAuthResult(int result) {
            super.onSdkAuthResult(result);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putInt("result", result);
                sendEvent(getReactApplicationContext(), "onSdkAuthResult", map);
            });
        }

        @Override
        public void onUserBanned(boolean status) {
            super.onUserBanned(status);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putBoolean("status", status);
                sendEvent(getReactApplicationContext(), "onUserBanned", map);
            });
        }

        @Override
        public void onUserJoined(String uid, int elapsed) {
            super.onUserJoined(uid, elapsed);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putString("uid", uid);
                map.putInt("elapsed", elapsed);
                sendEvent(getReactApplicationContext(), "onUserJoined", map);
            });
        }

        @Override
        public void onUserOffline(String uid, int reason) {
            super.onUserOffline(uid, reason);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putString("uid", uid);
                map.putInt("reason", reason);
                sendEvent(getReactApplicationContext(), "onUserOffline", map);
            });
        }

        @Override
        public void onRoomStats(ThunderNotification.RoomStats stats) {
            super.onRoomStats(stats);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                WritableMap writableMap = Arguments.createMap();
                writableMap.putInt("txBitrate", stats.txBitrate);
                writableMap.putInt("rxBitrate", stats.rxBitrate);
                writableMap.putInt("txBytes", stats.txBytes);
                writableMap.putInt("rxBytes", stats.rxBytes);
                writableMap.putInt("txAudioBytes", stats.txAudioBytes);
                writableMap.putInt("rxAudioBytes", stats.rxAudioBytes);
                writableMap.putInt("txVideoBytes", stats.txVideoBytes);
                writableMap.putInt("rxVideoBytes", stats.rxVideoBytes);
                writableMap.putInt("txAudioBitrate", stats.txAudioBitrate);
                writableMap.putInt("rxAudioBitrate", stats.rxAudioBitrate);
                writableMap.putInt("txVideoBitrate", stats.txVideoBitrate);
                writableMap.putInt("rxVideoBitrate", stats.rxVideoBitrate);
                writableMap.putInt("lastmileDelay", stats.lastmileDelay);
                writableMap.putInt("txPacketLossRate", stats.txPacketLossRate);
                writableMap.putInt("rxPacketLossRate", stats.rxPacketLossRate);
                map.putMap("stats", writableMap);

                sendEvent(getReactApplicationContext(), "onRoomStats", map);
            });
        }

        @Override
        public void onBizAuthResult(boolean bPublish, int result) {
            super.onBizAuthResult(bPublish, result);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putInt("result", result);
                map.putBoolean("bPublish", bPublish);
                sendEvent(getReactApplicationContext(), "onBizAuthResult", map);
            });
        }

        @Override
        public void onTokenWillExpire(byte[] token) {
            super.onTokenWillExpire(token);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putString("token", new String(token));
                sendEvent(getReactApplicationContext(), "onTokenWillExpire", map);
            });
        }

        @Override
        public void onTokenRequested() {
            super.onTokenRequested();
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                sendEvent(getReactApplicationContext(), "onTokenRequested", map);
            });
        }

        @Override
        public void onFirstLocalAudioFrameSent(int elapsed) {
            super.onFirstLocalAudioFrameSent(elapsed);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putInt("elapsed", elapsed);
                sendEvent(getReactApplicationContext(), "onFirstLocalAudioFrameSent", map);
            });
        }

        @Override
        public void onLocalAudioStats(LocalAudioStats stats) {
            super.onLocalAudioStats(stats);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                WritableMap writableMap = Arguments.createMap();
                writableMap.putInt("encodedBitrate", stats.encodedBitrate);
                writableMap.putInt("numChannels", stats.numChannels);
                writableMap.putInt("sendSampleRate", stats.sendSampleRate);
                writableMap.putInt("sendBitrate", stats.sendBitrate);
                writableMap.putInt("enableVad", stats.enableVad);
                map.putMap("stats", writableMap);
                sendEvent(getReactApplicationContext(), "onLocalAudioStats", map);
            });
        }

        @Override
        public void onLocalAudioStatusChanged(int status, int errorReason) {
            super.onLocalAudioStatusChanged(status, errorReason);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putInt("status", status);
                map.putInt("errorReason", errorReason);
                sendEvent(getReactApplicationContext(), "onLocalAudioStatusChanged", map);
            });
        }

        @Override
        public void onRemoteAudioStopped(String uid, boolean stop) {
            super.onRemoteAudioStopped(uid, stop);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putString("uid", uid);
                map.putBoolean("stop", stop);
                sendEvent(getReactApplicationContext(), "onRemoteAudioStopped", map);
            });
        }

        @Override
        public void onRemoteAudioStatsOfUid(String uid, RemoteAudioStats stats) {
            super.onRemoteAudioStatsOfUid(uid, stats);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putString("uid", uid);
                WritableMap writableMap = Arguments.createMap();
                writableMap.putInt("quality", stats.quality);
                writableMap.putInt("networkTransportDelay", stats.networkTransportDelay);
                writableMap.putInt("jitterBufferDelay", stats.jitterBufferDelay);
                writableMap.putInt("totalDelay", stats.totalDelay);
                writableMap.putInt("frameLossRate", stats.frameLossRate);
                writableMap.putInt("numChannels", stats.numChannels);
                writableMap.putInt("receivedSampleRate", stats.receivedSampleRate);
                writableMap.putInt("receivedBitrate", stats.receivedBitrate);
                writableMap.putInt("totalFrozenTime", stats.totalFrozenTime);
                writableMap.putInt("frozenRate", stats.frozenRate);
                map.putMap("stats", writableMap);
                sendEvent(getReactApplicationContext(), "onRemoteAudioStatsOfUid", map);
            });
        }

        @Override
        public void onRemoteAudioPlay(String uid, int elapsed) {
            super.onRemoteAudioPlay(uid, elapsed);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putString("uid", uid);
                map.putInt("elapsed", elapsed);
                sendEvent(getReactApplicationContext(), "onRemoteAudioPlay", map);
            });
        }

        @Override
        public void onRemoteAudioStateChangedOfUid(String uid, int state, int reason, int elapsed) {
            super.onRemoteAudioStateChangedOfUid(uid, state, reason, elapsed);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putString("uid", uid);
                map.putInt("state", state);
                map.putInt("reason", reason);
                map.putInt("elapsed", elapsed);
                sendEvent(getReactApplicationContext(), "onRemoteAudioStateChangedOfUid", map);
            });
        }

        @Override
        public void onFirstLocalVideoFrameSent(int elapsed) {
            super.onFirstLocalVideoFrameSent(elapsed);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putInt("elapsed", elapsed);
                sendEvent(getReactApplicationContext(), "onFirstLocalVideoFrameSent", map);
            });
        }

        @Override
        public void onLocalVideoStats(LocalVideoStats stats) {
            super.onLocalVideoStats(stats);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                WritableMap writableMap = Arguments.createMap();
                writableMap.putInt("sentBitrate", stats.sentBitrate);
                writableMap.putInt("sentFrameRate", stats.sentFrameRate);
                writableMap.putInt("renderOutputFrameRate", stats.renderOutputFrameRate);
                writableMap.putInt("targetBitRate", stats.targetBitRate);
                writableMap.putInt("targetFrameRate", stats.targetFrameRate);
                writableMap.putInt("qualityAdaptIndication", stats.qualityAdaptIndication);
                writableMap.putInt("encoderOutputFrameRate", stats.encoderOutputFrameRate);
                writableMap.putInt("encodedBitrate", stats.encodedBitrate);
                writableMap.putInt("encodedFrameWidth", stats.encodedFrameWidth);
                writableMap.putInt("encodedFrameHeight", stats.encodedFrameHeight);
                writableMap.putInt("encodedFrameCount", stats.encodedFrameCount);
                writableMap.putInt("encodedType", stats.encodedType);
                writableMap.putInt("codecType", stats.codecType);
                writableMap.putInt("configBitRate", stats.configBitRate);
                writableMap.putInt("configFrameRate", stats.configFrameRate);
                writableMap.putInt("configWidth", stats.configWidth);
                writableMap.putInt("configHeight", stats.configHeight);
                map.putMap("stats", writableMap);
                sendEvent(getReactApplicationContext(), "onLocalVideoStats", map);
            });
        }

        @Override
        public void onDeviceStats(DeviceStats stats) {
            super.onDeviceStats(stats);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                WritableMap writableMap = Arguments.createMap();
                writableMap.putDouble("cpuTotalUsage", stats.cpuTotalUsage);
                writableMap.putDouble("cpuAppUsage", stats.cpuAppUsage);
                writableMap.putDouble("memoryAppUsage", stats.memoryAppUsage);
                writableMap.putDouble("memoryTotalUsage", stats.memoryTotalUsage);
                map.putMap("stats", writableMap);
                sendEvent(getReactApplicationContext(), "onDeviceStats", map);
            });
        }

        @Override
        public void onLocalVideoStatusChanged(int status, int errorReason) {
            super.onLocalVideoStatusChanged(status, errorReason);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putInt("status", status);
                map.putInt("errorReason", errorReason);
                sendEvent(getReactApplicationContext(), "onLocalVideoStatusChanged", map);
            });
        }

        @Override
        public void onRemoteVideoStopped(String uid, boolean stop) {
            super.onRemoteVideoStopped(uid, stop);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putString("uid", uid);
                map.putBoolean("stop", stop);
                sendEvent(getReactApplicationContext(), "onRemoteVideoStopped", map);
            });
        }

        @Override
        public void onRemoteVideoStatsOfUid(String uid, RemoteVideoStats stats) {
            super.onRemoteVideoStatsOfUid(uid, stats);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putString("uid", uid);
                WritableMap writableMap = Arguments.createMap();
                writableMap.putInt("delay", stats.delay);
                writableMap.putInt("width", stats.width);
                writableMap.putInt("height", stats.height);
                writableMap.putInt("receivedBitrate", stats.receivedBitrate);
                writableMap.putInt("decoderOutputFrameRate", stats.decoderOutputFrameRate);
                writableMap.putInt("rendererOutputFrameRate", stats.rendererOutputFrameRate);
                writableMap.putInt("packetLossRate", stats.packetLossRate);
                writableMap.putInt("rxStreamType", stats.rxStreamType);
                writableMap.putInt("totalFrozenTime", stats.totalFrozenTime);
                writableMap.putInt("frozenRate", stats.frozenRate);
                writableMap.putInt("codecType", stats.codecType);
                writableMap.putInt("decodedType", stats.decodedType);
                map.putMap("stats", writableMap);
                sendEvent(getReactApplicationContext(), "onRemoteVideoStatsOfUid", map);
            });
        }

        @Override
        public void onRemoteVideoPlay(String uid, int width, int height, int elapsed) {
            super.onRemoteVideoPlay(uid, width, height, elapsed);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putString("uid", uid);
                map.putInt("width", width);
                map.putInt("height", height);
                map.putInt("elapsed", elapsed);
                sendEvent(getReactApplicationContext(), "onRemoteVideoPlay", map);
            });
        }

        @Override
        public void onVideoSizeChanged(String uid, int width, int height, int rotation) {
            super.onVideoSizeChanged(uid, width, height, rotation);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putString("uid", uid);
                map.putInt("width", width);
                map.putInt("height", height);
                map.putInt("rotation", rotation);
                sendEvent(getReactApplicationContext(), "onVideoSizeChanged", map);
            });
        }

        @Override
        public void onRemoteVideoStateChangedOfUid(String uid, int state, int reason, int elapsed) {
            super.onRemoteVideoStateChangedOfUid(uid, state, reason, elapsed);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putString("uid", uid);
                map.putInt("state", state);
                map.putInt("reason", reason);
                map.putInt("elapsed", elapsed);
                sendEvent(getReactApplicationContext(), "onRemoteVideoStateChangedOfUid", map);
            });
        }

        @Override
        public void onNetworkQuality(String uid, int txQuality, int rxQuality) {
            super.onNetworkQuality(uid, txQuality, rxQuality);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putString("uid", uid);
                map.putInt("txQuality", txQuality);
                map.putInt("rxQuality", rxQuality);
                sendEvent(getReactApplicationContext(), "onNetworkQuality", map);
            });
        }

        @Override
        public void onConnectionStatus(int status) {
            super.onConnectionStatus(status);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putInt("status", status);
                sendEvent(getReactApplicationContext(), "onConnectionStatus", map);
            });
        }

        @Override
        public void onConnectionLost() {
            super.onConnectionLost();
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                sendEvent(getReactApplicationContext(), "onConnectionLost", map);
            });
        }

        @Override
        public void onPublishStreamToCDNStatus(String url, int errorCode) {
            super.onPublishStreamToCDNStatus(url, errorCode);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putString("url", url);
                map.putInt("errorCode", errorCode);
                sendEvent(getReactApplicationContext(), "onPublishStreamToCDNStatus", map);
            });
        }

        @Override
        public void onCaptureVolumeIndication(int totalVolume, int cpt, int micVolume) {
            super.onCaptureVolumeIndication(totalVolume, cpt, micVolume);
            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                map.putInt("totalVolume", totalVolume);
                map.putInt("cpt", cpt);
                map.putInt("micVolume", micVolume);
                sendEvent(getReactApplicationContext(), "onCaptureVolumeIndication", map);
            });
        }

        @Override
        public void onPlayVolumeIndication(AudioVolumeInfo[] speakers, int totalVolume) {
            super.onPlayVolumeIndication(speakers, totalVolume);

            runOnUiThread(() -> {
                WritableMap map = Arguments.createMap();
                WritableArray array = Arguments.createArray();
                for (AudioVolumeInfo speaker : speakers) {
                    WritableMap mapTemp = Arguments.createMap();
                    mapTemp.putString("uid", speaker.uid);
                    mapTemp.putInt("volume", speaker.volume);
                    mapTemp.putInt("pts", speaker.pts);
                    array.pushMap(mapTemp);
                }
                map.putArray("speakers", array);
                map.putInt("totalVolume", totalVolume);
                sendEvent(getReactApplicationContext(), "onPlayVolumeIndication", map);
            });
        }
    };

    @NonNull
    @Override
    public String getName() {
        return ThunderModule.class.getSimpleName();
    }

    @ReactMethod
    private void createEngine(ReadableMap options, Promise promise) {
        if (ThunderManager.getInstance().getEngine() != null) {
            promise.resolve("已经初始化");
            return;
        }
        String appId = options.getString("appId");
        long sceneId = options.getInt("sceneId");
        ThunderManager.getInstance().createEngine(getReactApplicationContext(), appId, sceneId);
        ThunderManager.getInstance().addThunderEventHandler(thunderEventHandler);
        promise.resolve(0);
    }

    @ReactMethod
    private void destroyEngine(ReadableMap options, Promise promise) {
        if (ThunderManager.getInstance().getEngine() == null) {
            promise.resolve("未初始化");
            return;
        }
        ThunderManager.getInstance().destroyEngine();
        ThunderManager.getInstance().removeThunderEventHandler(thunderEventHandler);
        promise.resolve(0);
    }

    @ReactMethod
    public void getVersion(ReadableMap options, Promise promise) {
        promise.resolve(ThunderManager.getVersion());
    }

    @ReactMethod
    private void setArea(int area, Promise promise) {
        promise.resolve(ThunderManager.getInstance().setArea(area));
    }

    @ReactMethod
    private void setMediaMode(int mode, Promise promise) {
        promise.resolve(ThunderManager.getInstance().setMediaMode(mode));
    }

    @ReactMethod
    private void setRoomMode(int mode, Promise promise) {
        promise.resolve(ThunderManager.getInstance().setRoomMode(mode));
    }

    @ReactMethod
    private void joinRoom(ReadableMap options, Promise promise) {
        String uid = options.getString("uid");
        String roomName = options.getString("roomName");
        String token = options.getString("token");
        int result = ThunderManager.getInstance()
                .joinRoom(token.getBytes(), roomName, uid);
        promise.resolve(result);
    }

    @ReactMethod
    private void leaveRoom(ReadableMap options, Promise promise) {
        int ret = ThunderManager.getInstance().leaveRoom();
        promise.resolve(ret);
    }

    @ReactMethod
    private void updateToken(String token, Promise promise) {
        int ret = ThunderManager.getInstance().updateToken(token.getBytes());
        promise.resolve(ret);
    }

    @ReactMethod
    private void setLogFilePath(String path, Promise promise) {
        int ret = ThunderManager.getInstance().setLogFilePath(path);
        promise.resolve(ret);
    }

    @ReactMethod
    private void setAudioConfig(ReadableMap options, Promise promise) {
        int profile = options.getInt("profile");
        int commutMode = options.getInt("commutMode");
        int scenarioMode = options.getInt("scenarioMode");
        promise.resolve(
                ThunderManager.getInstance().setAudioConfig(profile, commutMode, scenarioMode));
    }

    @ReactMethod
    private void setAudioSourceType(int type, Promise promise) {
        ThunderManager.getInstance().setAudioSourceType(type);
        promise.resolve(0);
    }

    @ReactMethod
    private void setMicVolume(int volume, Promise promise) {
        promise.resolve(ThunderManager.getInstance().setMicVolume(volume));
    }

    @ReactMethod
    private void stopLocalAudioStream(boolean stop, Promise promise) {
        promise.resolve(ThunderManager.getInstance().stopLocalAudioStream(stop));
    }

    @ReactMethod
    private void stopAllRemoteAudioStreams(boolean stop, Promise promise) {
        promise.resolve(ThunderManager.getInstance().stopAllRemoteAudioStreams(stop));
    }

    @ReactMethod
    private void stopRemoteAudioStream(ReadableMap options, Promise promise) {
        String uid = options.getString("uid");
        boolean stop = options.getBoolean("stop");
        promise.resolve(ThunderManager.getInstance().stopRemoteAudioStream(uid, stop));
    }

    @ReactMethod
    private void setVideoEncoderConfig(ReadableMap options, Promise promise) {
        int playType = options.getInt("playType");
        int publishMode = options.getInt("publishMode");
        promise.resolve(ThunderManager.getInstance().setVideoEncoderConfig(
                new ThunderVideoEncoderConfiguration(playType, publishMode)));
    }

    @ReactMethod
    private void enableLocalVideoCapture(boolean enable, Promise promise) {
        promise.resolve(ThunderManager.getInstance().enableLocalVideoCapture(enable));
    }

    @ReactMethod
    private void startVideoPreview(ReadableMap options, Promise promise) {
        promise.resolve(ThunderManager.getInstance().startVideoPreview());
    }

    @ReactMethod
    private void stopVideoPreview(ReadableMap options, Promise promise) {
        promise.resolve(ThunderManager.getInstance().stopVideoPreview());
    }

    @ReactMethod
    private void setLocalCanvasScaleMode(int mode, Promise promise) {
        promise.resolve(ThunderManager.getInstance().setLocalCanvasScaleMode(mode));
    }

    @ReactMethod
    private void setLocalVideoMirrorMode(int mode, Promise promise) {
        promise.resolve(ThunderManager.getInstance().setLocalVideoMirrorMode(mode));
    }

    @ReactMethod
    private void stopLocalVideoStream(boolean stop, Promise promise) {
        promise.resolve(ThunderManager.getInstance().stopLocalVideoStream(stop));
    }

    @ReactMethod
    private void getVideoEncoderParam(ReadableMap options, Promise promise) {
        int playType = options.getInt("playType");
        int publishMode = options.getInt("publishMode");
        ThunderVideoEncodeParam thunderVideoEncodeParam =
                ThunderManager.getInstance()
                        .getVideoEncoderParam(new ThunderVideoEncoderConfiguration(playType,
                                publishMode));
        WritableMap writableMap = Arguments.createMap();
        writableMap.putInt("width", thunderVideoEncodeParam.width);
        writableMap.putInt("height", thunderVideoEncodeParam.height);
        writableMap.putInt("frameRate", thunderVideoEncodeParam.frameRate);
        writableMap.putInt("codeRate", thunderVideoEncodeParam.codeRate);
        writableMap.putInt("encodedType", thunderVideoEncodeParam.encodedType);
        writableMap.putInt("codecType", thunderVideoEncodeParam.codecType);
        promise.resolve(writableMap);
    }

    @ReactMethod
    private void stopAllRemoteVideoStreams(boolean stop, Promise promise) {
        promise.resolve(ThunderManager.getInstance().stopAllRemoteVideoStreams(stop));
    }

    @ReactMethod
    private void stopRemoteVideoStream(ReadableMap options, Promise promise) {
        String uid = options.getString("uid");
        boolean stop = options.getBoolean("stop");
        promise.resolve(ThunderManager.getInstance().stopRemoteVideoStream(uid, stop));
    }

    @ReactMethod
    private void setRemoteCanvasScaleMode(ReadableMap options, Promise promise) {
        String uid = options.getString("uid");
        int mode = options.getInt("mode");
        promise.resolve(ThunderManager.getInstance().setRemoteCanvasScaleMode(uid, mode));
    }

    private static final String TAG_VIEWID = "mViewId";
    private static final String TAG_VIEW = "mView";
    private static final String TAG_BGBITMAP = "bgImageName";
    private static final String TAG_VIDEOVIEWPOSITIONS = "mVideoViewPositions";
    private static final String TAG_BGVIEWPOSITION = "mBgViewPosition";

    @ReactMethod
    public void setMultiVideoViewLayout(ReadableMap options, Promise promise) {
        promise.resolve(ThunderManager.getInstance().setMultiVideoViewLayout());
    }

    @ReactMethod
    private void setRemotePlayType(int remotePlayType, Promise promise) {
        promise.resolve(ThunderManager.getInstance().setRemotePlayType(remotePlayType));
    }

    @ReactMethod
    private void getConnectionStatus(ReadableMap options, Promise promise) {
        promise.resolve(ThunderManager.getInstance().getConnectionStatus());
    }

    private static final String TAG_TRANSCODINGMODE = "transCodingMode";
    private static final String TAG_AUDIOURL = "audioUrl";
    private static final String TAG_LYRICURL = "lyricUrl";
    private static final String TAG_MEDIAURL = "mediaUrl";
    private static final String TAG_MEDIASTREAMLAYOUT = "mediaStreamLayout";
    private static final String TAG_LAYOUTX = "layoutX";
    private static final String TAG_LAYOUTY = "layoutY";
    private static final String TAG_LAYOUTW = "layoutW";
    private static final String TAG_LAYOUTH = "layoutH";
    private static final String TAG_ZORDER = "zOrder";
    private static final String TAG_BCROP = "bCrop";
    private static final String TAG_CROPX = "cropX";
    private static final String TAG_CROPY = "cropY";
    private static final String TAG_CROPW = "cropW";
    private static final String TAG_CROPH = "cropH";
    private static final String TAG_ALPHA = "alpha";
    private static final String TAG_USERS = "users";
    private static final String TAG_UID = "uid";
    private static final String TAG_ROOMID = "roomId";
    private static final String TAG_BSTANDARD = "bStandard";
    private static final String TAG_AUDIOROOM = "audioRoom";

    @ReactMethod
    public void setLiveTranscodingTask(ReadableMap options, Promise promise) {
//        String taskId = options.getString("taskId");
//        int transCodingMode = options.getInt(TAG_TRANSCODINGMODE);
//        String audioUrl = options.getString(TAG_AUDIOURL);
//        String lyricUrl = options.getString(TAG_LYRICURL);
//        String mediaUrl = options.getString(TAG_MEDIAURL);
//        ReadableMap mediaStreamLayoutMap = options.getMap(TAG_MEDIASTREAMLAYOUT);
//        ReadableArray users = options.getArray(TAG_USERS);
//
//        LiveTranscoding transcoding = new LiveTranscoding();
//        transcoding.setTransCodingMode(transCodingMode);
//        transcoding.setAudioOnlyStandardSreamUrl(audioUrl, lyricUrl);
//
//        if (mediaStreamLayoutMap != null) {
//            LiveTranscoding.MediaStreamLayout mMediaStreamLayout =
//                    new LiveTranscoding.MediaStreamLayout();
//            mMediaStreamLayout.layoutX = mediaStreamLayoutMap.getInt(TAG_LAYOUTX);
//            mMediaStreamLayout.layoutY = mediaStreamLayoutMap.getInt(TAG_LAYOUTY);
//            mMediaStreamLayout.layoutW = mediaStreamLayoutMap.getInt(TAG_LAYOUTW);
//            mMediaStreamLayout.layoutH = mediaStreamLayoutMap.getInt(TAG_LAYOUTH);
//            mMediaStreamLayout.zOrder = mediaStreamLayoutMap.getInt(TAG_ZORDER);
//            mMediaStreamLayout.bCrop = mediaStreamLayoutMap.getBoolean(TAG_BCROP);
//            mMediaStreamLayout.cropX = mediaStreamLayoutMap.getInt(TAG_CROPX);
//            mMediaStreamLayout.cropY = mediaStreamLayoutMap.getInt(TAG_CROPY);
//            mMediaStreamLayout.cropW = mediaStreamLayoutMap.getInt(TAG_CROPW);
//            mMediaStreamLayout.cropH = mediaStreamLayoutMap.getInt(TAG_CROPH);
//            mMediaStreamLayout.alpha = mediaStreamLayoutMap.getInt(TAG_ALPHA);
//            transcoding.setMediaStandardSream(mediaUrl, mMediaStreamLayout);
//        }
//
//        if (users != null) {
//            ArrayList<LiveTranscoding.TranscodingUser> mUserList = new ArrayList<>();
//            for (int i = 0; i < users.size(); i++) {
//                ReadableMap usermap = users.getMap(i);
//                if (usermap != null) {
//                    LiveTranscoding.TranscodingUser user = new LiveTranscoding.TranscodingUser();
//                    user.uid = usermap.getString(TAG_UID);
//                    user.roomId = usermap.getString(TAG_ROOMID);
//                    user.bStandard = usermap.getBoolean(TAG_BSTANDARD);
//                    user.layoutX = usermap.getInt(TAG_LAYOUTX);
//                    user.layoutY = usermap.getInt(TAG_LAYOUTY);
//                    user.layoutW = usermap.getInt(TAG_LAYOUTW);
//                    user.layoutH = usermap.getInt(TAG_LAYOUTH);
//                    user.zOrder = usermap.getInt(TAG_ZORDER);
//                    user.bCrop = usermap.getBoolean(TAG_BCROP);
//                    user.cropX = usermap.getInt(TAG_CROPX);
//                    user.cropY = usermap.getInt(TAG_CROPY);
//                    user.cropW = usermap.getInt(TAG_CROPW);
//                    user.cropH = usermap.getInt(TAG_CROPH);
//                    user.alpha = usermap.getInt(TAG_ALPHA);
//                    user.audioRoom = usermap.getInt(TAG_AUDIOROOM);
//                    mUserList.add(user);
//                }
//            }
//            transcoding.setUsers(mUserList);
//        }

        promise.resolve(ThunderManager.getInstance().setLiveTranscodingTask());
    }

    @ReactMethod
    private void removeLiveTranscodingTask(String taskId, Promise promise) {
        promise.resolve(ThunderManager.getInstance().removeLiveTranscodingTask());
    }

    @ReactMethod
    private void addPublishTranscodingStreamUrl(ReadableMap options, Promise promise) {
        String taskId = options.getString("taskId");
        String url = options.getString("url");
        promise.resolve(ThunderManager.getInstance().addPublishTranscodingStreamUrl(taskId, url));
    }

    @ReactMethod
    private void removePublishTranscodingStreamUrl(ReadableMap options, Promise promise) {
        String taskId = options.getString("taskId");
        String url = options.getString("url");
        promise.resolve(
                ThunderManager.getInstance().removePublishTranscodingStreamUrl(taskId, url));
    }

    @ReactMethod
    public void sendMediaExtraInfo(String msg, Promise promise) {
        ByteBuffer data = ByteBuffer.wrap(msg.getBytes());
        promise.resolve(ThunderManager.getInstance().sendMediaExtraInfo(data, data.remaining()));
    }

    @ReactMethod
    private void registerVideoCaptureTextureObserver(ReadableMap options, Promise promise) {
        promise.resolve(ThunderManager.getInstance().registerVideoCaptureTextureObserver());
    }

    @ReactMethod
    private void registerVideoDecodeFrameObserver(String uid, Promise promise) {
        promise.resolve(ThunderManager.getInstance().registerVideoDecodeFrameObserver(uid));
    }

    @ReactMethod
    private void registerVideoCaptureFrameObserver(ReadableMap options, Promise promise) {
        promise.resolve(ThunderManager.getInstance().registerVideoCaptureFrameObserver());
    }

    @ReactMethod
    private void addPublishOriginStreamUrl(String url, Promise promise) {
        promise.resolve(ThunderManager.getInstance().addPublishOriginStreamUrl(url));
    }

    @ReactMethod
    private void removePublishOriginStreamUrl(String url, Promise promise) {
        promise.resolve(ThunderManager.getInstance().removePublishOriginStreamUrl(url));
    }

    @ReactMethod
    private void enableMixVideoExtraInfo(boolean enable, Promise promise) {
        promise.resolve(ThunderManager.getInstance().enableMixVideoExtraInfo(enable));
    }

    @ReactMethod
    private void addSubscribe(ReadableMap options, Promise promise) {
        String roomId = options.getString("roomId");
        String uid = options.getString("uid");
        promise.resolve(ThunderManager.getInstance().addSubscribe(roomId, uid));

    }

    @ReactMethod
    private void removeSubscribe(ReadableMap options, Promise promise) {
        String roomId = options.getString("roomId");
        String uid = options.getString("uid");
        promise.resolve(ThunderManager.getInstance().removeSubscribe(roomId, uid));
    }

    @ReactMethod
    private void switchFrontCamera(boolean bFront, Promise promise) {
        promise.resolve(ThunderManager.getInstance().switchFrontCamera(bFront));
    }

    @ReactMethod
    private void setVideoCaptureOrientation(int orientation, Promise promise) {
        promise.resolve(ThunderManager.getInstance().setVideoCaptureOrientation(orientation));
    }

    @ReactMethod
    private void enableCaptureVolumeIndication(ReadableMap options, Promise promise) {
        int interval = options.getInt("interval");
        int moreThanThd = options.getInt("moreThanThd");
        int lessThanThd = options.getInt("lessThanThd");
        int smooth = options.getInt("smooth");
        promise.resolve(ThunderManager.getInstance()
                .enableCaptureVolumeIndication(interval, moreThanThd, lessThanThd
                        , smooth));
    }

    @ReactMethod
    private void setAudioVolumeIndication(ReadableMap options, Promise promise) {
        int interval = options.getInt("interval");
        int moreThanThd = options.getInt("moreThanThd");
        int lessThanThd = options.getInt("lessThanThd");
        int smooth = options.getInt("smooth");
        promise.resolve(ThunderManager.getInstance()
                .setAudioVolumeIndication(interval, moreThanThd, lessThanThd,
                        smooth));
    }

    @ReactMethod
    private void isLoudspeakerEnabled(ReadableMap options, Promise promise) {
        promise.resolve(ThunderManager.getInstance().isLoudspeakerEnabled());
    }

    @ReactMethod
    private void enableLoudspeaker(boolean enable, Promise promise) {
        promise.resolve(ThunderManager.getInstance().enableLoudspeaker(enable));
    }

    @ReactMethod
    private void setLoudSpeakerVolume(int volume, Promise promise) {
        promise.resolve(ThunderManager.getInstance().setLoudSpeakerVolume(volume));
    }

    @ReactMethod
    private void setLogLevel(int filter, Promise promise) {
        promise.resolve(ThunderManager.getInstance().setLogLevel(filter));
    }

    @ReactMethod
    private void setMediaExtraInfoCallback(boolean enable, Promise promise) {
        if (enable) {
            promise.resolve(ThunderManager.getInstance().setMediaExtraInfoCallback(this));
        } else {
            promise.resolve(ThunderManager.getInstance().setMediaExtraInfoCallback(null));
        }
    }

    @ReactMethod
    private void setLogCallback(ReadableMap options, Promise promise) {
        promise.resolve(ThunderManager.getInstance().setLogCallback(new IThunderLogCallback() {
            @Override
            public void onThunderLogWithLevel(int level, String tag, String msg) {
                WritableMap map = Arguments.createMap();
                map.putInt("level", level);
                map.putString("tag", tag);
                map.putString("msg", msg);
                sendEvent(getReactApplicationContext(), "onLogCallback", map);
            }
        }));
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(TAG_TRANSCODINGMODE, TAG_TRANSCODINGMODE);
        constants.put(TAG_AUDIOURL, TAG_AUDIOURL);
        constants.put(TAG_LYRICURL, TAG_LYRICURL);
        constants.put(TAG_MEDIAURL, TAG_MEDIAURL);
        constants.put(TAG_MEDIASTREAMLAYOUT, TAG_MEDIASTREAMLAYOUT);
        constants.put(TAG_LAYOUTX, TAG_LAYOUTX);
        constants.put(TAG_LAYOUTY, TAG_LAYOUTY);
        constants.put(TAG_LAYOUTW, TAG_LAYOUTW);
        constants.put(TAG_LAYOUTH, TAG_LAYOUTH);
        constants.put(TAG_ZORDER, TAG_ZORDER);
        constants.put(TAG_BCROP, TAG_BCROP);
        constants.put(TAG_CROPX, TAG_CROPX);
        constants.put(TAG_CROPY, TAG_CROPY);
        constants.put(TAG_CROPW, TAG_CROPW);
        constants.put(TAG_CROPH, TAG_CROPH);
        constants.put(TAG_ALPHA, TAG_ALPHA);
        constants.put(TAG_USERS, TAG_USERS);
        constants.put(TAG_UID, TAG_UID);
        constants.put(TAG_ROOMID, TAG_ROOMID);
        constants.put(TAG_BSTANDARD, TAG_BSTANDARD);
        constants.put(TAG_AUDIOROOM, TAG_AUDIOROOM);
        return constants;
    }

    private void sendEvent(ReactContext reactContext,
                           String eventName,
                           @Nullable WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(ThunderConst.THUNDER_PREFIX + eventName, params);
    }

    @Override
    public void onSendMediaExtraInfoFailedStatus(int status) {
        runOnUiThread(() -> {
            WritableMap map = Arguments.createMap();
            map.putInt("status", status);
            sendEvent(getReactApplicationContext(), "onSendMediaExtraInfoFailedStatus", map);
        });
    }

    @Override
    public void onRecvMediaExtraInfo(String uid, ByteBuffer data, int dataLen) {
        runOnUiThread(() -> {
            byte[] extraInfo = new byte[dataLen];
            data.get(extraInfo, 0, extraInfo.length);
            String strInfo = new String(extraInfo);
            WritableMap map = Arguments.createMap();
            map.putString("uid", uid);
            map.putString("data", strInfo);
            map.putInt("dataLen", dataLen);
            sendEvent(getReactApplicationContext(), "onRecvMediaExtraInfo", map);
        });
    }

    @Override
    public void onRecvMixAudioInfo(String uid, ArrayList<ThunderEventHandler.MixAudioInfo> infos) {
        runOnUiThread(() -> {
            WritableMap map = Arguments.createMap();
            WritableArray array = Arguments.createArray();
            for (ThunderEventHandler.MixAudioInfo info : infos) {
                WritableMap map2 = Arguments.createMap();
                map2.putInt("volume", info.volume);
                map2.putString("uid", info.uid);
                array.pushMap(map2);
            }
            map.putArray("infos", array);
            map.putString("uid", uid);
            sendEvent(getReactApplicationContext(), "onRecvMixAudioInfo", map);
        });
    }

    @Override
    public void onRecvMixVideoInfo(String uid, ArrayList<ThunderEventHandler.MixVideoInfo> infos) {
        runOnUiThread(() -> {
            WritableMap map = Arguments.createMap();
            WritableArray array = Arguments.createArray();
            for (ThunderEventHandler.MixVideoInfo info : infos) {
                WritableMap map2 = Arguments.createMap();
                map2.putInt("width", info.width);
                map2.putInt("height", info.height);
                map2.putInt("cropX", info.cropX);
                map2.putInt("cropY", info.cropY);
                map2.putInt("cropW", info.cropW);
                map2.putInt("cropH", info.cropH);
                map2.putInt("layoutX", info.layoutX);
                map2.putInt("layoutY", info.layoutY);
                map2.putInt("layoutW", info.layoutW);
                map2.putInt("layoutH", info.layoutH);
                map2.putInt("zOrder", info.zOrder);
                map2.putDouble("alpha", info.alpha);
                map2.putString("uid", info.uid);
                array.pushMap(map2);
            }
            map.putArray("infos", array);
            map.putString("uid", uid);
            sendEvent(getReactApplicationContext(), "onRecvMixVideoInfo", map);
        });
    }
}
