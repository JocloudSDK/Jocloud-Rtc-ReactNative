import PropTypes from 'prop-types';
import { requireNativeComponent } from 'react-native';

var iface = {
  name: 'ThunderLocalVideoView',
  propTypes: {
    joinRoom: PropTypes.string
  },
};

module.exports = requireNativeComponent('ThunderLocalVideoView', iface);
