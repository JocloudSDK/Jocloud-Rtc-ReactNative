#import "ThunderModule.h"
#import <thunderboltdynamic/ThunderEngine.h>
#import "ThunderLocalVideoView.h"
#import "ThunderRemoteVideoView.h"

@interface ThunderModule()<ThunderEventDelegate,ThunderRtcLogDelegate,ThunderMediaExtraInfoDelegate,ThunderVideoCaptureFrameObserver,ThunderVideoDecodeFrameObserver>

@property (nonatomic, strong) ThunderEngine *engine;

@end

@implementation ThunderModule

RCT_EXPORT_MODULE();

- (NSArray<NSString *> *)supportedEvents
{
  return @[@"thunder_rtconJoinRoomSuccess",
           @"thunder_rtconLeaveRoom",
           @"thunder_rtconSdkAuthResult",
           @"thunder_rtconUserBanned",
           @"thunder_rtconUserJoined",
           @"thunder_rtconUserOffline",
           @"thunder_rtconRoomStats",
           @"thunder_rtconBizAuthResult",
           @"thunder_rtconTokenWillExpire",
           @"thunder_rtconTokenRequested",
           @"thunder_rtconFirstLocalAudioFrameSent",
           @"thunder_rtconLocalAudioStats",
           @"thunder_rtconLocalAudioStatusChanged",
           @"thunder_rtconRemoteAudioStopped",
           @"thunder_rtconRemoteAudioStatsOfUid",
           @"thunder_rtconRemoteAudioPlay",
           @"thunder_rtconRemoteAudioStateChangedOfUid",
           @"thunder_rtconFirstLocalVideoFrameSent",
           @"thunder_rtconLocalVideoStats",
           @"thunder_rtconLocalVideoStatusChanged",
           @"thunder_rtconRemoteVideoStateChangedOfUid",
           @"thunder_rtconRemoteVideoStopped",
           @"thunder_rtconRemoteVideoStatsOfUid",
           @"thunder_rtconRemoteVideoPlay",
           @"thunder_rtconVideoSizeChanged",
           @"thunder_rtconNetworkQuality",
           @"thunder_rtconConnectionLost",
           @"thunder_rtconConnectionStatus",
           @"thunder_rtconPublishStreamToCDNStatus",
           @"thunder_rtconCaptureVolumeIndication",
           @"thunder_rtconPlayVolumeIndication",
           @"thunder_rtconSendMediaExtraInfoFailedStatus",
           @"thunder_rtconRecvMediaExtraInfo",
           @"thunder_rtconRecvMixAudioInfo",
           @"thunder_rtconRecvMixVideoInfo",
           @"thunder_rtconDeviceStats"];
}

+ (instancetype)sharedModule
{
    static ThunderModule* module = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        module = [[ThunderModule alloc] init];
    });
    return module;
}


#pragma mark Method

RCT_EXPORT_METHOD(createEngine:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  NSString *appId = options[@"appId"];
  int sceneId = [options[@"sceneId"] intValue];
  [ThunderModule sharedModule].engine = [ThunderEngine createEngine:appId sceneId:sceneId delegate:self];
  resolve(0);
}

RCT_EXPORT_METHOD(destroyEngine:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  [ThunderEngine destroyEngine];
  resolve(0);
}

RCT_EXPORT_METHOD(getVersion:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  NSString *version = [ThunderEngine getVersion];
  resolve(version);
}

RCT_EXPORT_METHOD(setArea:(NSInteger)area resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine setArea:area];
  resolve(@(result));
}

RCT_EXPORT_METHOD(setMediaMode:(NSInteger)mode resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine setMediaMode:mode];
  resolve(@(result));
}

RCT_EXPORT_METHOD(setRoomMode:(NSInteger)mode resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine setRoomMode:mode];
  resolve(@(result));
}

RCT_EXPORT_METHOD(joinRoom : (NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  NSString *token = options[@"token"];
  NSString *roomName = options[@"roomName"];
  NSString *uid = options[@"uid"];
  int result = [[ThunderModule sharedModule].engine joinRoom:token roomName:roomName uid:uid];
  resolve(@(result));
}

RCT_EXPORT_METHOD(leaveRoom:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine leaveRoom];
  resolve(@(result));
}

RCT_EXPORT_METHOD(updateToken:(NSString *)token resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine updateToken:token];
  resolve(@(result));
}

RCT_EXPORT_METHOD(setAudioConfig:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int config = [options[@"profile"] intValue];
  int commuMode = [options[@"commutMode"] intValue];
  int scenarioMode = [options[@"scenarioMode"] intValue];
  int result = [[ThunderModule sharedModule].engine setAudioConfig:config commutMode:commuMode scenarioMode:scenarioMode];
  resolve(@(result));
}

RCT_EXPORT_METHOD(setAudioSourceType:(NSInteger)type resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  [[ThunderModule sharedModule].engine setAudioSourceType:type];
  resolve(0);
}

RCT_EXPORT_METHOD(setMicVolume:(NSInteger)volume resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine setMicVolume:volume];
  resolve(@(result));
}

RCT_EXPORT_METHOD(stopLocalAudioStream:(BOOL)stop resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine stopLocalAudioStream:stop];
  resolve(@(result));
}

RCT_EXPORT_METHOD(stopRemoteAudioStream:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  NSString *uid = options[@"uid"];
  BOOL stopped = [options[@"stop"] boolValue];
  int result = [[ThunderModule sharedModule].engine stopRemoteAudioStream:uid stopped:stopped];
  resolve(@(result));
}

RCT_EXPORT_METHOD(stopAllRemoteAudioStreams:(BOOL)stopped resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine stopAllRemoteAudioStreams:stopped];
  resolve(@(result));
}

RCT_EXPORT_METHOD(setVideoEncoderConfig:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int playType = [options[@"playType"] intValue];
  int pubishMode = [options[@"publishMode"] intValue];
  ThunderVideoEncoderConfiguration *videoEncoderConfiguration = [[ThunderVideoEncoderConfiguration alloc] init];
  videoEncoderConfiguration.playType = playType;
  videoEncoderConfiguration.publishMode = pubishMode;
  int result = [[ThunderModule sharedModule].engine setVideoEncoderConfig:videoEncoderConfiguration];
  resolve(@(result));
}

RCT_EXPORT_METHOD(enableLocalVideoCapture:(BOOL)enable resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine enableLocalVideoCapture:enable];
  resolve(@(result));
}

RCT_EXPORT_METHOD(startVideoPreview:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine startVideoPreview];
  resolve(@(result));
}

RCT_EXPORT_METHOD(stopVideoPreview:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine stopVideoPreview];
  resolve(@(result));
}

RCT_EXPORT_METHOD(setLocalCanvasScaleMode:(NSInteger)renderMode resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine setLocalCanvasScaleMode:renderMode];
  resolve(@(result));
}

RCT_EXPORT_METHOD(setLocalVideoMirrorMode:(NSInteger)mode resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine setLocalVideoMirrorMode:mode];
  resolve(@(result));
}

RCT_EXPORT_METHOD(stopLocalVideoStream:(BOOL)stopped resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine stopLocalVideoStream:stopped];
  resolve(@(result));
}

RCT_EXPORT_METHOD(getVideoEncoderParam:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int playType = [options[@"playType"] intValue];
  int pubishMode = [options[@"publishMode"] intValue];
  ThunderVideoEncoderConfiguration *videoEncoderConfiguration = [[ThunderVideoEncoderConfiguration alloc] init];
  videoEncoderConfiguration.playType = playType;
  videoEncoderConfiguration.publishMode = pubishMode;
  ThunderVideoEncodeParam *encodeParam = [[ThunderModule sharedModule].engine getVideoEncoderParam:videoEncoderConfiguration];
  resolve(@{@"width" : @(encodeParam.width), @"height" : @(encodeParam.height), @"frameRate" : @(encodeParam.frameRate), @"codeRate" : @(encodeParam.codeRate), @"encodedType" : @(encodeParam.encodedType), @"codecType" : @(encodeParam.codecType)});
}

RCT_EXPORT_METHOD(stopAllRemoteVideoStreams:(BOOL)stopped resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine stopAllRemoteVideoStreams:stopped];
  resolve(@(result));
}

RCT_EXPORT_METHOD(stopRemoteVideoStream:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  NSString *uid = options[@"uid"];
  BOOL stopped = [options[@"stop"] boolValue];
  int result = [[ThunderModule sharedModule].engine stopRemoteVideoStream:uid stopped:stopped];
  resolve(@(result));
}

RCT_EXPORT_METHOD(setRemoteCanvasScaleMode:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  NSString *uid = options[@"uid"];
  int renderMode = [options[@"mode"] intValue];
  int result = [[ThunderModule sharedModule].engine setRemoteCanvasScaleMode:uid mode:renderMode];
  resolve(@(result));
}

RCT_EXPORT_METHOD(setMultiVideoViewLayout:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine setMultiVideoViewLayout:[ThunderModule sharedModule].thunderMultiVideoViewParam];
  resolve(@(result));
}

RCT_EXPORT_METHOD(setRemotePlayType:(NSInteger)remotePlayType resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine setRemotePlayType:remotePlayType];
  resolve(@(result));
}

RCT_EXPORT_METHOD(getConnectionStatus:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  ThunderConnectionStatus status = [[ThunderModule sharedModule].engine getConnectionStatus];
  resolve(@(status));
}

RCT_EXPORT_METHOD(setLiveTranscodingTask:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{

//  NSString *taskId = options[@"taskId"];

  int status = [[ThunderModule sharedModule].engine setLiveTranscodingTask:[ThunderModule sharedModule].taskId transcoding:[ThunderModule sharedModule].transcoding];
  resolve(@(status));
}

RCT_EXPORT_METHOD(removeLiveTranscodingTask:(NSString *)taskId resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine removeLiveTranscodingTask:taskId];
  resolve(@(result));
}


RCT_EXPORT_METHOD(addPublishTranscodingStreamUrl:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  NSString *taskId = options[@"taskId"];
  NSString *url = options[@"url"];
  int result = [[ThunderModule sharedModule].engine addPublishTranscodingStreamUrl:taskId url:url];
  resolve(@(result));
}

RCT_EXPORT_METHOD(removePublishTranscodingStreamUrl:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  NSString *taskId = options[@"taskId"];
  NSString *url = options[@"url"];
  int result = [[ThunderModule sharedModule].engine removePublishTranscodingStreamUrl:taskId url:url];
  resolve(@(result));
}

RCT_EXPORT_METHOD(registerVideoCaptureFrameObserver:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine registerVideoCaptureFrameObserver:[ThunderModule sharedModule].self];
  resolve(@(result));
}

RCT_EXPORT_METHOD(registerVideoDecodeFrameObserver:(NSString *)uid resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine registerVideoDecodeFrameObserver:[ThunderModule sharedModule].self uid:uid];
  resolve(@(result));
}

RCT_EXPORT_METHOD(addPublishOriginStreamUrl:(NSString *)url resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine addPublishOriginStreamUrl:url];
  resolve(@(result));
}

RCT_EXPORT_METHOD(removePublishOriginStreamUrl:(NSString *)url resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine removePublishOriginStreamUrl:url];
  resolve(@(result));
}

RCT_EXPORT_METHOD(enableMixVideoExtraInfo:(BOOL)enable resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine enableMixVideoExtraInfo:enable];
  resolve(@(result));
}

RCT_EXPORT_METHOD(addSubscribe:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  NSString *roomId = options[@"roomId"];
  NSString *uid = options[@"uid"];
  int result = [[ThunderModule sharedModule].engine addSubscribe:roomId uid:uid];
  resolve(@(result));
}

RCT_EXPORT_METHOD(removeSubscribe:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  NSString *roomId = options[@"roomId"];
  NSString *uid = options[@"uid"];
  int result = [[ThunderModule sharedModule].engine removeSubscribe:roomId uid:uid];
  resolve(@(result));
}

RCT_EXPORT_METHOD(switchFrontCamera:(BOOL)bFront resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine switchFrontCamera:bFront];
  resolve(@(result));
}

RCT_EXPORT_METHOD(setVideoCaptureOrientation:(NSInteger)orientation resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine setVideoCaptureOrientation:orientation];
  resolve(@(result));
}

RCT_EXPORT_METHOD(enableCaptureVolumeIndication:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int interval = [options[@"interval"] intValue];
  int moreThanThd = [options[@"moreThanThd"] intValue];
  int lessThanThd = [options[@"lessThanThd"] intValue];
  int smooth = [options[@"smooth"] intValue];
  int result = [[ThunderModule sharedModule].engine enableCaptureVolumeIndication:interval moreThanThd:moreThanThd lessThanThd:lessThanThd smooth:smooth];
  resolve(@(result));
}

RCT_EXPORT_METHOD(setAudioVolumeIndication:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int interval = [options[@"interval"] intValue];
  int moreThanThd = [options[@"moreThanThd"] intValue];
  int lessThanThd = [options[@"lessThanThd"] intValue];
  int smooth = [options[@"smooth"] intValue];
  int result = [[ThunderModule sharedModule].engine setAudioVolumeIndication:interval moreThanThd:moreThanThd lessThanThd:lessThanThd smooth:smooth];
  resolve(@(result));
}

RCT_EXPORT_METHOD(isLoudspeakerEnabled:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  BOOL result = [[ThunderModule sharedModule].engine isLoudspeakerEnabled];
  resolve(@(result));
}

RCT_EXPORT_METHOD(enableLoudspeaker:(BOOL)enable resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine enableLoudspeaker:enable];
  resolve(@(result));
}

RCT_EXPORT_METHOD(setLoudSpeakerVolume:(NSInteger)volume resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine setLoudSpeakerVolume:volume];
  resolve(@(result));
}

RCT_EXPORT_METHOD(sendMediaExtraInfo:(NSString *)msg resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  NSData *extraInfo =[msg dataUsingEncoding:NSUTF8StringEncoding];
  int result = [[ThunderModule sharedModule].engine sendMediaExtraInfo:extraInfo];
  resolve(@(result));
}

RCT_EXPORT_METHOD(setMediaExtraInfoCallback:(BOOL)enable resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result;
  if(enable) {
    result = [[ThunderModule sharedModule].engine setMediaExtraInfoDelegate:self];
  } else {
    result = [[ThunderModule sharedModule].engine setMediaExtraInfoDelegate:nil];
  }

  resolve(@(result));
}

RCT_EXPORT_METHOD(setLogFilePath:(NSString *)path resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{

  int result = [[ThunderModule sharedModule].engine setLogFilePath:path];
  resolve(@(result));
}

RCT_EXPORT_METHOD(setLogLevel:(int)level resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  [[ThunderModule sharedModule].engine setLogLevel:level];
  resolve(0);
}

RCT_EXPORT_METHOD(setLogCallback:(NSDictionary *)options resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
{
  int result = [[ThunderModule sharedModule].engine setLogCallback:[ThunderModule sharedModule].self];
  resolve(@(result));
}


- (int)setLocalVideoCanvas:(ThunderLocalVideoView *)view renderMode:(NSInteger)renderMode uid:(NSString *)uid
{
  // 创建视频视图
  ThunderVideoCanvas *localCanvas = [[ThunderVideoCanvas alloc] init];
  localCanvas.view = view;
  // 设置视频布局
  [localCanvas setRenderMode:renderMode];
  // 设置用户uid
  [localCanvas setUid:uid];
  int result = [[ThunderModule sharedModule].engine setLocalVideoCanvas:localCanvas];
  return result;
}

- (int)setRemoteVideoCanvas:(ThunderRemoteVideoView *)view renderMode:(NSInteger)renderMode uid:(NSString *)uid
{
  ThunderVideoCanvas *canvas = [[ThunderVideoCanvas alloc] init];
  canvas.view = view;
  [canvas setRenderMode:renderMode];
  [canvas setUid:uid];
  int result = [[ThunderModule sharedModule].engine setRemoteVideoCanvas:canvas];
  return result;
}

#pragma mark Delegate
- (void)thunderEngine:(ThunderEngine *)engine onDeviceStats:(ThunderRtcLocalDeviceStats *)stats
{
  NSDictionary *statsDict = @{
    @"cpuTotalUsage" : @(stats.cpuTotalUsage),
    @"cpuAppUsage" : @(stats.cpuAppUsage),
    @"memoryAppUsage" : @(stats.memoryAppUsage),
    @"memoryTotalUsage" : @(stats.memoryTotalUsage)
  };
  [self sendEventWithName:@"thunder_rtconDeviceStats" body:@{@"stats" : statsDict}];
}

- (void)onRecvMixVideoInfo:(NSArray<ThunderMixVideoInfo *> *)infos ofUid:(NSString *)uid
{
  NSMutableArray *infoArray = [NSMutableArray array];
  for (ThunderMixVideoInfo *info in infos) {
    NSDictionary *infoDict = @{
      @"uid" : info.uid,
      @"size" : @{
          @"width" : @(info.size.width),
          @"height" : @(info.size.height)
      },
      @"crop" : @{
          @"x" : @(info.crop.origin.x),
          @"y" : @(info.crop.origin.y),
          @"width" : @(info.crop.size.width),
          @"height" : @(info.crop.size.height)
      },
      @"layout" : @{
          @"x" : @(info.layout.origin.x),
          @"y" : @(info.layout.origin.y),
          @"width" : @(info.layout.size.width),
          @"height" : @(info.layout.size.height)
      },
      @"zOrder" : @(info.zOrder),
      @"alpha" : @(info.alpha)
    };
    [infoArray addObject:infoDict];
  }
  [self sendEventWithName:@"thunder_rtconRecvMixVideoInfo" body:@{@"uid" : uid, @"infos" : infoArray}];
}

- (void)onRecvMixAudioInfo:(NSArray<ThunderMixAudioInfo *> *)infos ofUid:(NSString *)uid
{
  NSMutableArray *infoArray = [NSMutableArray array];
  for (ThunderMixAudioInfo *info in infos) {
    NSDictionary *infoDict = @{
      @"uid" : info.uid,
      @"volume" : @(info.volume)
    };
    [infoArray addObject:infoDict];
  }
  [self sendEventWithName:@"thunder_rtconRecvMixAudioInfo" body:@{@"uid" : uid, @"infos" : infoArray}];
}

- (void)onRecvMediaExtraInfo:(NSData *)data ofUid:(NSString *)uid
{
  NSString *strInfo = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
  [self sendEventWithName:@"thunder_rtconRecvMediaExtraInfo" body:@{@"uid" : uid, @"data" : strInfo}];
}

- (void)onSendMediaExtraInfoFailedStatus:(NSUInteger)status
{
  [self sendEventWithName:@"thunder_rtconSendMediaExtraInfoFailedStatus" body:@{@"status" : @(status)}];
}

- (void)thunderEngine:(ThunderEngine *)engine onPlayVolumeIndication:(NSArray<ThunderRtcAudioVolumeInfo *> *)speakers totalVolume:(NSInteger)totalVolume
{
  NSMutableArray *speakersArray = [NSMutableArray array];
  for (ThunderRtcAudioVolumeInfo * info in speakers) {
    NSDictionary *infoDict = @{
      @"uid" : info.uid,
      @"volume" : @(info.volume),
      @"pts" : @(info.pts)
    };
    [speakersArray addObject:infoDict];
  }
  [self sendEventWithName:@"thunder_rtconPlayVolumeIndication" body:@{@"speakers" : speakersArray, @"totalVolume" : @(totalVolume)}];
}

- (void)thunderEngine:(ThunderEngine *)engine onCaptureVolumeIndication:(NSInteger)totalVolume cpt:(NSUInteger)cpt micVolume:(NSInteger)micVolume
{
  [self sendEventWithName:@"thunder_rtconCaptureVolumeIndication" body:@{@"totalVolume" : @(totalVolume), @"cpt" : @(cpt), @"micVolume" : @(micVolume)}];
}

- (void)thunderEngine:(ThunderEngine *)engine onPublishStreamToCDNStatusWithUrl:(NSString *)url errorCode:(ThunderPublishCDNErrorCode)errorCode
{
  [self sendEventWithName:@"thunder_rtconPublishStreamToCDNStatus" body:@{@"url" : url, @"errorCode" : @(errorCode)}];
}

- (void)thunderEngine:(ThunderEngine *)engine onConnectionStatus:(ThunderConnectionStatus)status
{
  [self sendEventWithName:@"thunder_rtconConnectionStatus" body:@{@"status" : @(status)}];
}

- (void)thunderEngineConnectionLost:(ThunderEngine *)engine
{
  [self sendEventWithName:@"thunder_rtconConnectionLost" body:@{}];
}


- (void)thunderEngine:(ThunderEngine *)engine onNetworkQuality:(NSString *)uid txQuality:(ThunderLiveRtcNetworkQuality)txQuality rxQuality:(ThunderLiveRtcNetworkQuality)rxQuality
{
  [self sendEventWithName:@"thunder_rtconNetworkQuality" body:@{@"uid" : uid, @"txQuality" : @(txQuality), @"rxQuality" : @(rxQuality)}];
}

- (void)thunderEngine:(ThunderEngine *)engine onRemoteVideoStateChangedOfUid:(NSString *)uid state:(ThunderRemoteVideoState)state reason:(ThunderRemoteVideoReason)reason elapsed:(NSInteger)elapsed
{
  [self sendEventWithName:@"thunder_rtconRemoteVideoStateChangedOfUid" body:@{@"uid" : uid, @"state" : @(state), @"reason" : @(reason), @"elapsed" : @(elapsed)}];
}

- (void)thunderEngine:(ThunderEngine *)engine onVideoSizeChangedOfUid:(NSString *)uid size:(CGSize)size rotation:(NSInteger)rotation
{
  [self sendEventWithName:@"thunder_rtconVideoSizeChanged" body:@{@"uid" : uid, @"width":@(size.width), @"heigth":@(size.height), @"rotation" : @(rotation)}];
}

- (void)thunderEngine:(ThunderEngine *)engine onRemoteVideoPlay:(NSString *)uid size:(CGSize)size elapsed:(NSInteger)elapsed
{
  [self sendEventWithName:@"thunder_rtconRemoteVideoPlay" body:@{@"uid" : uid,@"width":@(size.width),@"heigth":@(size.height), @"elapsed" : @(elapsed)}];
}

- (void)thunderEngine:(ThunderEngine *)engine onRemoteVideoStatsOfUid:(NSString *)uid stats:(ThunderRtcRemoteVideoStats *)stats
{
  NSDictionary *statsDict = @{
    @"delay" : @(stats.delay),
    @"width" : @(stats.width),
    @"height" : @(stats.height),
    @"receivedBitrate" : @(stats.receivedBitrate),
    @"decoderOutputFrameRate" : @(stats.decoderOutputFrameRate),
    @"rendererOutputFrameRate" : @(stats.rendererOutputFrameRate),
    @"packetLossRate" : @(stats.packetLossRate),
    @"rxStreamType" : @(stats.rxStreamType),
    @"totalFrozenTime" : @(stats.totalFrozenTime),
    @"frozenRate" : @(stats.frozenRate),
    @"decodedType" : @(stats.decodedType),
    @"codecType" : @(stats.codecType),
  };
  [self sendEventWithName:@"thunder_rtconRemoteVideoStatsOfUid" body:@{@"uid" : uid, @"stats" : statsDict}];
}

- (void)thunderEngine:(ThunderEngine *)engine onRemoteVideoStopped:(BOOL)stopped byUid:(NSString *)uid
{
  [self sendEventWithName:@"thunder_rtconRemoteVideoStopped" body:@{@"stopped" : @(stopped), @"uid" : uid}];
}

- (void)thunderEngine:(ThunderEngine *)engine onLocalVideoStatusChanged:(ThunderLocalVideoStreamStatus)status error:(ThunderLocalVideoStreamErrorReason)error
{
  [self sendEventWithName:@"thunder_rtconLocalVideoStatusChanged" body:@{@"status" : @(status), @"error" : @(error)}];
}

- (void)thunderEngine:(ThunderEngine *)engine onLocalVideoStats:(ThunderRtcLocalVideoStats *)stats
{
  NSDictionary *statsDict = @{
    @"sendBitrate" : @(stats.sendBitrate),
    @"sendFrameRate" : @(stats.sendFrameRate),
    @"encoderOutputFrameRate" : @(stats.encoderOutputFrameRate),
    @"renderOutputFrameRate" : @(stats.renderOutputFrameRate),
    @"targetBitrate" : @(stats.targetBitrate),
    @"targetFrameRate" : @(stats.targetFrameRate),
    @"qualityAdaptIndication" : @(stats.qualityAdaptIndication),
    @"encodedBitrate" : @(stats.encodedBitrate),
    @"encodedFrameWidth" : @(stats.encodedFrameWidth),
    @"encodedFrameHeight" : @(stats.encodedFrameHeight),
    @"encodedFrameCount" : @(stats.encodedFrameCount),
    @"encodedType" : @(stats.encodedType),
    @"codecType" : @(stats.codecType),
    @"configBitRate" : @(stats.configBitRate),
    @"configFrameRate" : @(stats.configFrameRate),
    @"configWidth" : @(stats.configWidth),
    @"configHeight" : @(stats.configHeight)
  };
  [self sendEventWithName:@"thunder_rtconLocalVideoStats" body:@{@"stats" : statsDict}];
}

- (void)thunderEngine:(ThunderEngine *)engine onFirstLocalVideoFrameSent:(NSInteger)elapsed
{
  [self sendEventWithName:@"thunder_rtconFirstLocalVideoFrameSent" body:@{@"elapsed" : @(elapsed)}];
}

- (void)thunderEngine:(ThunderEngine *)engine onRemoteAudioStateChangedOfUid:(NSString *)uid state:(ThunderRemoteAudioState)state reason:(ThunderRemoteAudioReason)reason elapsed:(NSInteger)elapsed
{
  [self sendEventWithName:@"thunder_rtconRemoteAudioStateChangedOfUid" body:@{@"uid" : uid, @"state" : @(state), @"reason" : @(reason), @"elapsed" : @(elapsed)}];
}

- (void)thunderEngine:(ThunderEngine *)engine onRemoteAudioPlay:(NSString *)uid elapsed:(NSInteger)elapsed
{
  [self sendEventWithName:@"thunder_rtconRemoteAudioPlay" body:@{@"uid" : uid, @"elapsed" : @(elapsed)}];
}

- (void)thunderEngine:(ThunderEngine *)engine onRemoteAudioStatsOfUid:(NSString *)uid stats:(ThunderRtcRemoteAudioStats *)stats
{
  NSDictionary *statsDict = @{

    @"quality" : @(stats.quality),
    @"networkTransportDelay" : @(stats.networkTransportDelay),
    @"jitterBufferDelay" : @(stats.jitterBufferDelay),
    @"totalDelay" : @(stats.totalDelay),
    @"frameLossRate" : @(stats.frameLossRate),
    @"numChannels" : @(stats.numChannels),
    @"receivedSampleRate" : @(stats.receivedSampleRate),
    @"receivedBitrate" : @(stats.receivedBitrate),
    @"totalFrozenTime" : @(stats.totalFrozenTime),
    @"frozenRate" : @(stats.frozenRate)
  };
  [self sendEventWithName:@"thunder_rtconRemoteAudioStatsOfUid" body:@{@"uid" : uid, @"stats" : statsDict}];
}

- (void)thunderEngine:(ThunderEngine *)engine onRemoteAudioStopped:(BOOL)stopped byUid:(NSString *)uid
{
  [self sendEventWithName:@"thunder_rtconRemoteAudioStopped" body:@{@"stop" : @(stopped), @"uid" : uid}];
}

- (void)thunderEngine:(ThunderEngine *)engine onLocalAudioStatusChanged:(ThunderLocalAudioStreamStatus)status errorReason:(ThunderLocalAudioStreamErrorReason)errorReason
{
  [self sendEventWithName:@"thunder_rtconLocalAudioStatusChanged" body:@{@"status" : @(status), @"errorReason" : @(errorReason)}];
}

- (void)thunderEngine:(ThunderEngine *)engine onLocalAudioStats:(ThunderRtcLocalAudioStats *)stats
{
  NSDictionary *statsDict = @{
    @"encodedBitrate" : @(stats.encodedBitrate),
    @"numChannels" : @(stats.numChannels),
    @"sendSampleRate" : @(stats.sendSampleRate),
    @"sendBitrate" : @(stats.sendBitrate),
    @"enableVad" : @(stats.enableVad)
  };
  [self sendEventWithName:@"thunder_rtconLocalAudioStats" body:@{@"stats" : statsDict}];
}

- (void)thunderEngine:(ThunderEngine *)engine onFirstLocalAudioFrameSent:(NSInteger)elapsed
{
  [self sendEventWithName:@"thunder_rtconFirstLocalAudioFrameSent" body:@{@"elapsed" : @(elapsed)}];
}

- (void)thunderEngineTokenRequest:(ThunderEngine *)engine
{
  [self sendEventWithName:@"thunder_rtconTokenRequested" body:@{}];
}

- (void)thunderEngine:(ThunderEngine *)engine onTokenWillExpire:(NSString *)token
{
  [self sendEventWithName:@"thunder_rtconTokenWillExpire" body:@{@"token" : token}];
}

- (void)thunderEngine:(ThunderEngine *)engine onJoinRoomSuccess:(NSString *)room withUid:(NSString *)uid elapsed:(NSInteger)elapsed
{
  [self sendEventWithName:@"thunder_rtconJoinRoomSuccess" body:@{@"room" : room,@"uid" : uid,@"elapsed" : @(elapsed)}];
}

- (void)thunderEngine:(ThunderEngine *)engine onLeaveRoomWithStats:(ThunderRtcRoomStats *)stats
{
  [self sendEventWithName:@"thunder_rtconLeaveRoom" body:@{}];
}

- (void)thunderEngine:(ThunderEngine *)engine sdkAuthResult:(ThunderRtcSdkAuthResult)sdkAuthResult
{
  [self sendEventWithName:@"thunder_rtconSdkAuthResult" body:@{@"result": @(sdkAuthResult)}];
}

-(void)thunderEngine:(ThunderEngine *)engine onUserBanned:(BOOL)status
{
  [self sendEventWithName:@"thunder_rtconUserBanned" body:@{@"status" : @(status)}];
}

- (void)thunderEngine:(ThunderEngine *)engine onUserJoined:(NSString *)uid elapsed:(NSInteger)elapsed
{
  [self sendEventWithName:@"thunder_rtconUserJoined" body:@{@"uid" : uid, @"elapsed" : @(elapsed)}];
}

- (void)thunderEngine:(ThunderEngine *)engine onUserOffline:(NSString *)uid reason:(ThunderLiveRtcUserOfflineReason)reason
{
  [self sendEventWithName:@"thunder_rtconUserOffline" body:@{@"uid" : uid, @"reason" : @(reason)}];
}

- (ThunderVideoCaptureFrameDataType)needThunderVideoCaptureFrameDataType
{
  return [[ThunderModule sharedModule].self.thunderVideoCaptureFrameObserver needThunderVideoCaptureFrameDataType];
}

- (CVPixelBufferRef _Nullable)onVideoCaptureFrame:(EAGLContext * _Nonnull)glContext PixelBuffer:(CVPixelBufferRef _Nonnull)pixelBuf {
    if([ThunderModule sharedModule].self.thunderVideoCaptureFrameObserver != nil){
        return [[ThunderModule sharedModule].self.thunderVideoCaptureFrameObserver onVideoCaptureFrame:glContext PixelBuffer:pixelBuf];
    }
    return nil;
}

- (BOOL)onVideoCaptureFrame:(EAGLContext * _Nonnull)context PixelBuffer:(CVPixelBufferRef _Nonnull)pixelBuffer SourceTextureID:(unsigned int)srcTextureID DestinationTextureID:(unsigned int)dstTextureID TextureFormat:(int)textureFormat TextureTarget:(int)textureTarget TextureWidth:(int)width TextureHeight:(int)height {
    if([ThunderModule sharedModule].self.thunderVideoCaptureFrameObserver != nil){
        return [[ThunderModule sharedModule].self.thunderVideoCaptureFrameObserver onVideoCaptureFrame:context PixelBuffer:pixelBuffer SourceTextureID:srcTextureID DestinationTextureID:dstTextureID TextureFormat:textureFormat TextureTarget:textureTarget TextureWidth:width TextureHeight:height];
    }
    return false;
}

- (void)onVideoDecodeFrame:(CVPixelBufferRef _Nonnull)pixelBuf pts:(uint64_t)pts uid:(NSString * _Nonnull)uid {
    if([ThunderModule sharedModule].self.thunderVideoDecodeFrameObserver != nil){
        [[ThunderModule sharedModule].self.thunderVideoDecodeFrameObserver onVideoDecodeFrame:pixelBuf pts:pts uid:uid];
    }
}

- (void)thunderEngine:(ThunderEngine *)engine onRoomStats:(RoomStats *)stats
{
  NSDictionary *statsDict = @{
    @"totalDuration" : @(stats.totalDuration),
    @"txBitrate" : @(stats.txBitrate),
    @"rxBitrate" : @(stats.rxBitrate),
    @"txBytes" : @(stats.txBytes),
    @"rxBytes" : @(stats.rxBytes),
    @"txAudioBytes" : @(stats.txAudioBytes),
    @"rxAudioBytes" : @(stats.rxAudioBytes),
    @"txVideoBytes" : @(stats.txVideoBytes),
    @"rxVideoBytes" : @(stats.rxVideoBytes),
    @"txAudioBitrate" : @(stats.txAudioBitrate),
    @"rxAudioBitrate" : @(stats.rxAudioBitrate),
    @"txVideoBitrate" : @(stats.txVideoBitrate),
    @"rxVideoBitrate" : @(stats.rxVideoBitrate),
    @"lastmileDelay" : @(stats.lastmileDelay),
    @"txPacketLossRate" : @(stats.txPacketLossRate),
    @"rxPacketLossRate" : @(stats.lastmileDelay),
  };
  [self sendEventWithName:@"thunder_rtconRoomStats" body:@{@"stats" : statsDict}];
}

- (void)thunderEngine:(ThunderEngine *)engine bPublish:(BOOL)bPublish bizAuthResult:(NSInteger)bizAuthResult
{
  [self sendEventWithName:@"thunder_rtconBizAuthResult" body:@{@"bPublish" : @(bPublish), @"result" : @(bizAuthResult)}];
}

@end
