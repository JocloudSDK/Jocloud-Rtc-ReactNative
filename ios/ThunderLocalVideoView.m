//
//  ThunderLocalVideoView.m
//  thunderReactNative
//
//  Created by yy on 2020/8/18.
//

#import "ThunderLocalVideoView.h"
#import <React/RCTBridgeModule.h>
#import <UIKit/UIKit.h>
#import "ThunderModule.h"


@implementation ThunderLocalVideoView


- (void)setLocalVideoCanvas:(NSInteger)renderMode uid:(NSString *)uid
{
  [[ThunderModule sharedModule] setLocalVideoCanvas:self renderMode:renderMode uid:uid];
}

@end
