#ifndef ThunderModule_h
#define ThunderModule_h
#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>
#import "thunderboltdynamic/ThunderEngine.h"

@interface ThunderModule : RCTEventEmitter <RCTBridgeModule>

@property (strong, nonatomic, readwrite) id<ThunderVideoCaptureFrameObserver> _Nullable thunderVideoCaptureFrameObserver;
@property (strong, nonatomic, readwrite) id<ThunderVideoDecodeFrameObserver> _Nullable thunderVideoDecodeFrameObserver;

@property (strong, nonatomic, readwrite) ThunderMultiVideoViewParam * _Nullable thunderMultiVideoViewParam;

@property (strong, nonatomic, readwrite) NSString* _Nullable taskId;

@property (strong, nonatomic, readwrite) LiveTranscoding* _Nullable transcoding;

+ (instancetype _Nullable )sharedModule;

- (int)setLocalVideoCanvas:(UIView *_Nonnull)view renderMode:(NSInteger)renderMode uid:(NSString *_Nullable)uid;

- (int)setRemoteVideoCanvas:(UIView *_Nullable)view renderMode:(NSInteger)renderMode uid:(NSString *_Nullable)uid;

@end
#endif /* ThunderLocalViewManager_h */
