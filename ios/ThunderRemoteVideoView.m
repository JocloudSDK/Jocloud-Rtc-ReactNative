//
//  ThunderRemoteVideoView.m
//  thunderReactNative
//
//  Created by yy on 2020/8/18.
//

#import "ThunderRemoteVideoView.h"
#import <React/RCTBridgeModule.h>
#import <UIKit/UIKit.h>

@implementation ThunderRemoteVideoView

- (void)setRemoteVideoCanvas:(NSInteger)renderMode uid:(NSString *)uid
{
  [[ThunderModule sharedModule] setRemoteVideoCanvas:self renderMode:renderMode uid:uid];
}



@end
