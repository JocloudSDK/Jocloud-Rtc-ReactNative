import PropTypes from 'prop-types';
import { requireNativeComponent } from 'react-native';

var iface = {
  name: 'ThunderRemoteVideoView',
  propTypes: {
    joinRoom: PropTypes.string
  },
};

module.exports = requireNativeComponent('ThunderRemoteVideoView', iface);
