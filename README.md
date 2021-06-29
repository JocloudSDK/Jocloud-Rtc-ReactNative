# 说明

React Native Jocloud RTC Library。

# 使用
## 安装
```
yarn add react-native-jocloud-rtc
```
or
```
npm i --save react-native-jocloud-rtc
```

iOS Podfile
```
    platform :ios, '9.0' #platform need 9.0 or upper
    
    ...
    source 'https://github.com/CocoaPods/Specs.git'    #添加项
    source 'https://github.com/yyqapm/specs.git'       #添加项
    ...
```

## 使用

```js
import ThunderModule from "react-native-jocloud-rtc"
import { ThunderRemoteVideoView, ThunderLocalVideoView } from 'react-native-jocloud-rtc'
const react_native = require("react-native")
const ThunderEventEmitter = new react_native.NativeEventEmitter(ThunderModule)
...

ThunderModule.createEngine({'appId': appId, 'sceneId': sceneId}).then((value) => {
}).catch((code, err) => {
})
...

ThunderEventEmitter.addListener(`ThunderCallbackEvent`, (args) => {
    listener(args);
});

```