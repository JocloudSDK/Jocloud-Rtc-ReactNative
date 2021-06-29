package com.jocloud.rn.rtc;

import android.content.Context;
import android.util.Log;

import com.thunder.livesdk.IThunderLogCallback;
import com.thunder.livesdk.IThunderMediaExtraInfoCallback;
import com.thunder.livesdk.LiveTranscoding;
import com.thunder.livesdk.ThunderAudioFilePlayer;
import com.thunder.livesdk.ThunderBoltImage;
import com.thunder.livesdk.ThunderCustomVideoSource;
import com.thunder.livesdk.ThunderEngine;
import com.thunder.livesdk.ThunderEventHandler;
import com.thunder.livesdk.ThunderMultiVideoViewParam;
import com.thunder.livesdk.ThunderRtcConstant;
import com.thunder.livesdk.ThunderVideoCanvas;
import com.thunder.livesdk.ThunderVideoEncodeParam;
import com.thunder.livesdk.ThunderVideoEncoderConfiguration;
import com.thunder.livesdk.audio.IAudioFrameObserver;
import com.thunder.livesdk.video.IVideoCaptureObserver;
import com.thunder.livesdk.video.IVideoDecodeObserver;
import com.yy.mediaframework.gpuimage.custom.IGPUProcess;

import java.nio.ByteBuffer;

/**
 * Thunder功能封装单例
 *
 * @author huangcanbin@yy.com
 * @date 2020年9月24日
 */
public final class ThunderManager {
    private static final String TAG = ThunderManager.class.getSimpleName();

    public static final int RTC_CALL_NO_INIT = Integer.MIN_VALUE;

    private static volatile ThunderManager instance;
    private ThunderEngine mThunderEngine = null;
    private ThunderEventDispatcher mThunderEventDispatcher = new ThunderEventDispatcher();

    public static ThunderManager getInstance() {
        if (instance == null) {
            synchronized (ThunderManager.class) {
                if (instance == null) {
                    instance = new ThunderManager();
                }
            }
        }
        return instance;
    }

    public void addThunderEventHandler(ThunderEventHandler handler) {
        if (mThunderEventDispatcher == null) {
            return;
        }
        mThunderEventDispatcher.addHandler(handler);
    }

    public void removeThunderEventHandler(ThunderEventHandler handler) {
        if (mThunderEventDispatcher == null) {
            return;
        }
        mThunderEventDispatcher.removeHandler(handler);
    }

    public ThunderEngine getEngine() {
        return mThunderEngine;
    }

    /**
     * 初始化LiveSDK
     *
     * @param context
     * @param appId
     * @param sceneId
     */
    public void createEngine(Context context, String appId, long sceneId) {
        if (mThunderEngine != null) {
            return;
        }
        mThunderEngine =
                ThunderEngine.createEngine(context, appId, sceneId, mThunderEventDispatcher);
    }

    public void destroyEngine() {
        ThunderEngine.destroyEngine();
        mThunderEngine = null;
    }

    public static String getVersion() {
        return ThunderEngine.getVersion();
    }

    public void setSceneId(int sceneId) {
        mThunderEngine.setSceneId(sceneId);
    }

    public int setMediaMode(int mode) {
        if (mThunderEngine != null) {
            return mThunderEngine.setMediaMode(mode);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setRoomMode(int mode) {
        if (mThunderEngine != null) {
            return mThunderEngine.setRoomMode(mode);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setArea(int area) {
        if (mThunderEngine != null) {
            return mThunderEngine.setArea(area);
        }
        return RTC_CALL_NO_INIT;
    }

    public int joinRoom(byte[] token, String roomName, String uid) {
        if (mThunderEngine != null) {
            return mThunderEngine.joinRoom(token, roomName, uid);
        }
        return RTC_CALL_NO_INIT;
    }

    public int leaveRoom() {
        if (mThunderEngine != null) {
            return mThunderEngine.leaveRoom();
        }
        return RTC_CALL_NO_INIT;
    }

    public int updateToken(byte[] token) {
        if (mThunderEngine != null) {
            return mThunderEngine.updateToken(token);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setAudioConfig(int profile, int commutMode, int scenarioMode) {
        if (mThunderEngine != null) {
            return mThunderEngine.setAudioConfig(profile, commutMode, scenarioMode);
        }
        return RTC_CALL_NO_INIT;
    }

    public int enableVoicePosition(boolean enable) {
        if (mThunderEngine != null) {
            return mThunderEngine.enableVoicePosition(enable);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setRemoteUidVoicePosition(String uid, int azimuth, int gain) {
        if (mThunderEngine != null) {
            return mThunderEngine.setRemoteUidVoicePosition(uid, azimuth, gain);
        }
        return RTC_CALL_NO_INIT;
    }

    public int enableLoudspeaker(boolean enabled) {
        if (mThunderEngine != null) {
            return mThunderEngine.enableLoudspeaker(enabled);
        }
        return RTC_CALL_NO_INIT;
    }

    public boolean isLoudspeakerEnabled() {
        if (mThunderEngine != null) {
            return mThunderEngine.isLoudspeakerEnabled();
        }
        return false;
    }

    public int setLoudSpeakerVolume(int volume) {
        if (mThunderEngine != null) {
            return mThunderEngine.setLoudSpeakerVolume(volume);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setAudioVolumeIndication(int interval, int moreThanThd, int lessThanThd,
                                        int smooth) {
        if (mThunderEngine != null) {
            return mThunderEngine.setAudioVolumeIndication(interval, moreThanThd, lessThanThd,
                    smooth);
        }
        return RTC_CALL_NO_INIT;
    }

    public int enableCaptureVolumeIndication(int interval, int moreThanThd, int lessThanThd,
                                             int smooth) {
        if (mThunderEngine != null) {
            return mThunderEngine.enableCaptureVolumeIndication(interval, moreThanThd,
                    lessThanThd, smooth);
        }
        return RTC_CALL_NO_INIT;
    }

    /**
     * 是否静音自己
     *
     * @param stop
     * @return
     */
    public int stopLocalAudioStream(boolean stop) {
        if (mThunderEngine != null) {
            return mThunderEngine.stopLocalAudioStream(stop);
        }
        return RTC_CALL_NO_INIT;
    }

    /**
     * 是否静音某个用户的声音
     *
     * @param uid
     * @param stop
     * @return
     */
    public int stopRemoteAudioStream(String uid, boolean stop) {
        if (mThunderEngine != null) {
            return mThunderEngine.stopRemoteAudioStream(uid, stop);
        }
        return RTC_CALL_NO_INIT;
    }

    public int stopAllRemoteAudioStreams(boolean muted) {
        if (mThunderEngine != null) {
            return mThunderEngine.stopAllRemoteAudioStreams(muted);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setMicVolume(int volume) {
        if (mThunderEngine != null) {
            return mThunderEngine.setMicVolume(volume);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setRemoteAudioStreamsVolume(String uid, int volume) {
        if (mThunderEngine != null) {
            return mThunderEngine.setRemoteAudioStreamsVolume(uid, volume);
        }
        return RTC_CALL_NO_INIT;
    }

    public ThunderAudioFilePlayer createAudioFilePlayer() {
        if (mThunderEngine != null) {
            return mThunderEngine.createAudioFilePlayer();
        }
        return null;
    }

    public ThunderAudioFilePlayer destroyAudioFilePlayer(
            ThunderAudioFilePlayer audioFilePlayer) {
        if (mThunderEngine != null) {
            mThunderEngine.destroyAudioFilePlayer(audioFilePlayer);
        }
        return null;
    }

    public int setEnableEqualizer(boolean enabled) {
        if (mThunderEngine != null) {
            return mThunderEngine.setEnableEqualizer(enabled);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setEqGains(int[] gains) {
        if (mThunderEngine != null) {
            return mThunderEngine.setEqGains(gains);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setEnableReverb(boolean enabled) {
        if (mThunderEngine != null) {
            return mThunderEngine.setEnableReverb(enabled);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setReverbExParameter(ThunderRtcConstant.ReverbExParameter param) {
        if (mThunderEngine != null) {
            return mThunderEngine.setReverbExParameter(param);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setEnableLimiter(boolean enabled) {
        if (mThunderEngine != null) {
            return mThunderEngine.setEnableLimiter(enabled);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setLimiterParam(ThunderRtcConstant.LimterParam param) {
        if (mThunderEngine != null) {
            return mThunderEngine.setLimiterParam(param);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setVoicePitch(float pitch) {
        if (mThunderEngine != null) {
            return mThunderEngine.setVoicePitch(pitch);
        }
        return RTC_CALL_NO_INIT;
    }

    public void enableAudioPlaySpectrum(boolean enable) {
        if (mThunderEngine != null) {
            mThunderEngine.enableAudioPlaySpectrum(enable);
        }
    }

    public void setAudioPlaySpectrumInfo(int spectrumLen, int notifyIntervalMS) {
        if (mThunderEngine != null) {
            mThunderEngine.setAudioPlaySpectrumInfo(spectrumLen, notifyIntervalMS);
        }
    }

    public int sendUserAppMsgData(byte[] msgData) {
        if (mThunderEngine != null) {
            return mThunderEngine.sendUserAppMsgData(msgData);
        }
        return RTC_CALL_NO_INIT;
    }

    public int sendMediaExtraInfo(ByteBuffer data, int dataLen) {
        if (mThunderEngine != null) {
            return mThunderEngine.sendMediaExtraInfo(data, dataLen);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setMediaExtraInfoCallback(IThunderMediaExtraInfoCallback callback) {
        if (mThunderEngine != null) {
            return mThunderEngine.setMediaExtraInfoCallback(callback);
        }
        return RTC_CALL_NO_INIT;
    }

    public int enableMixVideoExtraInfo(boolean enable) {
        if (mThunderEngine != null) {
            return mThunderEngine.enableMixVideoExtraInfo(enable);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setEnableInEarMonitor(boolean enable) {
        if (mThunderEngine != null) {
            return mThunderEngine.setEnableInEarMonitor(enable);
        }

        return RTC_CALL_NO_INIT;
    }

    public int setVideoEncoderConfig(ThunderVideoEncoderConfiguration vConfig) {
        if (mThunderEngine != null) {
            return mThunderEngine.setVideoEncoderConfig(vConfig);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setRemotePlayType(int remotePlayType) {
        if (mThunderEngine != null) {
            return mThunderEngine.setRemotePlayType(remotePlayType);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setMultiVideoViewLayout() {
        if (mThunderEngine != null) {
            return mThunderEngine.setMultiVideoViewLayout(mThunderMultiVideoViewParam);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setLocalCanvasScaleMode(int mode) {
        if (mThunderEngine != null) {
            return mThunderEngine.setLocalCanvasScaleMode(mode);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setRemoteCanvasScaleMode(String uid, int mode) {
        if (mThunderEngine != null) {
            return mThunderEngine.setRemoteCanvasScaleMode(uid, mode);
        }
        return RTC_CALL_NO_INIT;
    }

    public int startVideoPreview() {
        if (mThunderEngine != null) {
            return mThunderEngine.startVideoPreview();
        }

        return RTC_CALL_NO_INIT;
    }

    public int stopVideoPreview() {
        if (mThunderEngine != null) {
            return mThunderEngine.stopVideoPreview();
        }
        return RTC_CALL_NO_INIT;
    }

    public int enableLocalVideoCapture(boolean enable) {
        if (mThunderEngine != null) {
            return mThunderEngine.enableLocalVideoCapture(enable);
        }
        return RTC_CALL_NO_INIT;
    }

    public int stopLocalVideoStream(boolean stop) {
        if (mThunderEngine != null) {
            return mThunderEngine.stopLocalVideoStream(stop);
        }
        return RTC_CALL_NO_INIT;
    }

    public int stopRemoteVideoStream(String uid, boolean stop) {
        if (mThunderEngine != null) {
            return mThunderEngine.stopRemoteVideoStream(uid, stop);
        }
        return RTC_CALL_NO_INIT;
    }

    public int stopAllRemoteVideoStreams(boolean stop) {
        if (mThunderEngine != null) {
            return mThunderEngine.stopAllRemoteVideoStreams(stop);
        }
        return RTC_CALL_NO_INIT;
    }

    private IGPUProcess mGPUProcess;

    private ThunderMultiVideoViewParam mThunderMultiVideoViewParam;

    private String mTaskId;

    private LiveTranscoding mLiveTranscoding;

    public void setLiveTranscoding(String taskId, LiveTranscoding liveTranscoding) {
        mTaskId = taskId;
        mLiveTranscoding = liveTranscoding;
    }

    public void setGPUProcess(IGPUProcess gpuProcess) {
        mGPUProcess = gpuProcess;
    }

    public void setThunderMultiVideoViewParam(ThunderMultiVideoViewParam param) {
        mThunderMultiVideoViewParam = param;
    }

    /**
     * {@link IGPUProcess} 接口对象实例；如果传入 null，则取消注册
     *
     * @param process 用于设置获取处理每一帧video渲染纹理的实例
     * @return <0 返回异常 0正常
     */
    public int registerVideoCaptureTextureObserver(IGPUProcess process) {
        if (mThunderEngine != null) {
            return mThunderEngine.registerVideoCaptureTextureObserver(process);
        }
        return RTC_CALL_NO_INIT;
    }

    public int registerVideoCaptureTextureObserver() {
        if (mThunderEngine != null) {
            return mThunderEngine.registerVideoCaptureTextureObserver(mGPUProcess);
        }
        return RTC_CALL_NO_INIT;
    }

    /**
     * {@link IVideoCaptureObserver} 接口对象实例；如果传入 null，则取消注册
     *
     * @param observer 用于设置分别获取video camera yuv采集和video渲染数据的实例
     * @return <0 返回异常 0正常
     */
    public int registerVideoCaptureFrameObserver(IVideoCaptureObserver observer) {
        if (mThunderEngine != null) {
            return mThunderEngine.registerVideoCaptureFrameObserver(observer);
        }
        return RTC_CALL_NO_INIT;
    }

    IVideoCaptureObserver mVideoCaptureObserver;

    public void setVideoCaptureObserver(IVideoCaptureObserver observer) {
        mVideoCaptureObserver = observer;
    }

    public int registerVideoCaptureFrameObserver() {
        if (mThunderEngine != null) {
            return mThunderEngine.registerVideoCaptureFrameObserver(mVideoCaptureObserver);
        }
        return RTC_CALL_NO_INIT;
    }

    private IVideoDecodeObserver mVideoDecodeObserver;

    public void setVideoDecodeObserver(IVideoDecodeObserver observer) {
        mVideoDecodeObserver = observer;
    }

    public int registerVideoDecodeFrameObserver(String uid) {
        if (mThunderEngine != null) {
            return mThunderEngine.registerVideoDecodeFrameObserver(uid, mVideoDecodeObserver);
        }
        return RTC_CALL_NO_INIT;
    }

    public int registerVideoDecodeFrameObserver(String uid, IVideoDecodeObserver observer) {
        if (mThunderEngine != null) {
            return mThunderEngine.registerVideoDecodeFrameObserver(uid, observer);
        }
        return RTC_CALL_NO_INIT;
    }

    public int registerAudioFrameObserver(IAudioFrameObserver observer) {
        if (mThunderEngine != null) {
            return mThunderEngine.registerAudioFrameObserver(observer);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setMixedAudioFrameParameters(int sampleRate, int channel, int samplesPerCall) {
        if (mThunderEngine != null) {
            return mThunderEngine.setMixedAudioFrameParameters(sampleRate, channel, samplesPerCall);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setRecordingAudioFrameParameters(int sampleRate, int room, int mode,
                                                int samplesPerCall) {
        if (mThunderEngine != null) {
            return mThunderEngine
                    .setRecordingAudioFrameParameters(sampleRate, room, mode, samplesPerCall);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setPlaybackAudioFrameParameters(int sampleRate, int channel, int mode,
                                               int samplesPerCall) {
        if (mThunderEngine != null) {
            return mThunderEngine
                    .setPlaybackAudioFrameParameters(sampleRate, channel, mode, samplesPerCall);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setVideoWatermark(ThunderBoltImage watermark) {
        if (mThunderEngine != null) {
            return mThunderEngine.setVideoWatermark(watermark);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setCustomAudioSource(boolean enabled, int sampleRate, int channel) {
        if (mThunderEngine != null) {
            return mThunderEngine.setCustomAudioSource(enabled, sampleRate, channel);
        }
        return RTC_CALL_NO_INIT;
    }

    public int pushCustomAudioFrame(byte[] data, long timeStamp) {
        if (mThunderEngine != null) {
            return mThunderEngine.pushCustomAudioFrame(data, timeStamp);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setCustomVideoSource(ThunderCustomVideoSource videoSource) {
        if (mThunderEngine != null) {
            return mThunderEngine.setCustomVideoSource(videoSource);
        }
        return RTC_CALL_NO_INIT;
    }

    public int addPublishOriginStreamUrl(String url) {
        if (mThunderEngine != null) {
            return mThunderEngine.addPublishOriginStreamUrl(url);
        }
        return RTC_CALL_NO_INIT;
    }

    public int removePublishOriginStreamUrl(String url) {
        if (mThunderEngine != null) {
            return mThunderEngine.removePublishOriginStreamUrl(url);
        }
        return RTC_CALL_NO_INIT;
    }

    public int switchFrontCamera(boolean bFront) {
        if (mThunderEngine != null) {
            return mThunderEngine.switchFrontCamera(bFront);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setVideoCaptureOrientation(int orientation) {
        if (mThunderEngine != null) {
            return mThunderEngine.setVideoCaptureOrientation(orientation);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setLocalVideoMirrorMode(int mode) {
        if (mThunderEngine != null) {
            return mThunderEngine.setLocalVideoMirrorMode(mode);
        }
        return RTC_CALL_NO_INIT;
    }

    public int enableWebSdkCompatibility(boolean enabled) {
        if (mThunderEngine != null) {
            return mThunderEngine.enableWebSdkCompatibility(enabled);
        }
        return RTC_CALL_NO_INIT;
    }

    public ThunderVideoEncodeParam getVideoEncoderParam(
            ThunderVideoEncoderConfiguration videoConfig) {

        if (mThunderEngine != null) {
            return mThunderEngine.getVideoEncoderParam(videoConfig);
        }
        return null;
    }

    public int getConnectionStatus() {
        if (mThunderEngine != null) {
            return mThunderEngine.getConnectionStatus();
        }
        return RTC_CALL_NO_INIT;
    }

    public int enableHowlingDetector(Boolean enabled) {
        if (mThunderEngine != null) {
            return mThunderEngine.enableHowlingDetector(enabled);
        }
        return RTC_CALL_NO_INIT;
    }

    public int enableEchoDetector(Boolean enabled) {
        if (mThunderEngine != null) {
            return mThunderEngine.enableEchoDetector(enabled);
        }
        return RTC_CALL_NO_INIT;
    }

    public boolean isInitialized() {
        return mThunderEngine != null;
    }

    public void setAudioSourceType(int mode) {
        if (mThunderEngine != null) {
            mThunderEngine.setAudioSourceType(mode);
        }
    }

    public boolean startAudioSaver(String fileName, int saverMode, int fileMode) {
        if (mThunderEngine != null) {
            return mThunderEngine.startAudioSaver(fileName, saverMode, fileMode);
        }
        return false;
    }

    public void setSoundEffect(int mode) {
        if (mThunderEngine != null) {
            mThunderEngine.setSoundEffect(mode);
        }
    }

    public void setVoiceChanger(int mode) {
        if (mThunderEngine != null) {
            mThunderEngine.setVoiceChanger(mode);
        }
    }

    public boolean stopAudioSaver() {
        if (mThunderEngine != null) {
            return mThunderEngine.stopAudioSaver();
        }
        return false;
    }

    public int setLocalVideoCanvas(ThunderVideoCanvas localView) {
        if (mThunderEngine != null) {
            return mThunderEngine.setLocalVideoCanvas(localView);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setRemoteVideoCanvas(ThunderVideoCanvas remoteView) {
        if (mThunderEngine != null) {
            return mThunderEngine.setRemoteVideoCanvas(remoteView);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setExternalAudioSource(boolean enabled, int sampleRate, int channels) {
        if (mThunderEngine != null) {
            return mThunderEngine.setCustomAudioSource(enabled, sampleRate, channels);
        }
        return RTC_CALL_NO_INIT;
    }

    public int pushExternalAudioFrame(byte[] data, long timeStamp) {
        if (mThunderEngine != null) {
            return mThunderEngine.pushCustomAudioFrame(data, timeStamp);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setVideoSource(ThunderCustomVideoSource videoSource) {
        if (mThunderEngine != null) {
            return mThunderEngine.setCustomVideoSource(videoSource);
        }
        return RTC_CALL_NO_INIT;
    }

    public int addVideoWatermark(ThunderBoltImage watermark) {
        if (mThunderEngine != null) {
            return mThunderEngine.setVideoWatermark(watermark);
        }
        return RTC_CALL_NO_INIT;
    }

    public int addSubscribe(String roomId, String uid) {
        if (mThunderEngine != null) {
            return mThunderEngine.addSubscribe(roomId, uid);
        }
        return RTC_CALL_NO_INIT;
    }

    public int removeSubscribe(String roomId, String uid) {
        if (mThunderEngine != null) {
            return mThunderEngine.removeSubscribe(roomId, uid);
        }
        return RTC_CALL_NO_INIT;
    }

    public int addPublishTranscodingStreamUrl(String taskId, String url) {
        if (mThunderEngine != null) {
            return mThunderEngine.addPublishTranscodingStreamUrl(taskId, url);
        }
        return RTC_CALL_NO_INIT;
    }

    public int removePublishTranscodingStreamUrl(String taskId, String url) {
        if (mThunderEngine != null) {
            return mThunderEngine.removePublishTranscodingStreamUrl(taskId, url);
        }
        return RTC_CALL_NO_INIT;
    }

    public int setLiveTranscodingTask() {
        if (mThunderEngine != null) {
//            Log.d(TAG, "setLiveTranscodingTask() called with: taskId = [" + taskId +
//                    "], transcoding = [" + transcoding + "]");
            return mThunderEngine.setLiveTranscodingTask(mTaskId, mLiveTranscoding);
        }
        return RTC_CALL_NO_INIT;
    }

    public int removeLiveTranscodingTask() {
        if (mThunderEngine != null) {
            return mThunderEngine.removeLiveTranscodingTask(mTaskId);
        }
        return RTC_CALL_NO_INIT;
    }

    public static int setLogCallback(IThunderLogCallback callback) {
        return ThunderEngine.setLogCallback(callback);
    }

    public int setLogFilePath(String path) {
        return ThunderEngine.setLogFilePath(path);
    }

    public int setLogLevel(int filter) {
        return ThunderEngine.setLogLevel(filter);
    }
}
