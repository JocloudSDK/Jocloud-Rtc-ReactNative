//
//  ThunderLocalViewManager.m
//  thunderReactNative
//
//  Created by yy on 2020/8/18.
//
#import "ThunderLocalViewManager.h"
#import "ThunderLocalVideoView.h"

@implementation ThunderLocalViewManager

RCT_EXPORT_MODULE(ThunderLocalVideoView)

RCT_CUSTOM_VIEW_PROPERTY(setLocalVideoCanvas, NSString, ThunderLocalVideoView)
{
  NSString *uid = json[@"uid"];
  int mode = [json[@"renderMode"] intValue];
  view.backgroundColor = [UIColor blackColor];
  [view setLocalVideoCanvas:mode uid:uid];
}

- (ThunderLocalVideoView *)view {
  return [[ThunderLocalVideoView alloc] init];
}

@end
