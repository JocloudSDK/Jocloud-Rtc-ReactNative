import { NativeModules } from 'react-native';

import ThunderLocalVideoView from './api/ThunderLocalVideoView';
import ThunderRemoteVideoView from './api/ThunderRemoteVideoView';
export { ThunderLocalVideoView, ThunderRemoteVideoView };

export * from './api/LiveTranscoding';
export * from './api/VideoPosition';

const { ThunderModule } = NativeModules;
export default ThunderModule;