//
//  ThunderRemoteViewManager.m
//  thunderReactNative
//
//  Created by yy on 2020/8/18.
//
#import "ThunderRemoteViewManager.h"
#import "ThunderRemoteVideoView.h"

@implementation ThunderRemoteViewManager


RCT_EXPORT_MODULE(ThunderRemoteVideoView)

RCT_CUSTOM_VIEW_PROPERTY(setRemoteVideoCanvas, NSString, ThunderRemoteVideoView) {
  NSString *uid = json[@"uid"];
  int mode = [json[@"renderMode"] intValue];
  view.backgroundColor = [UIColor blackColor];
  [view setRemoteVideoCanvas:mode uid:uid];
}

- (ThunderRemoteVideoView *)view {
  return [[ThunderRemoteVideoView alloc] init];
}



@end
