import React from 'react';
import {View,Navigator} from  'react-native';
import FirstPageComponent from './FirstPageComponent';

export default class SamplePageComponent extends React.Component {
        render() {
            let defaultName = 'FirstPageComponent';
            let defaultComponent = FirstPageComponent;
            return (
              <Navigator
                initialRoute={{ name: defaultName, component: defaultComponent }}
                configureScene={(route) => {
                  return Navigator.SceneConfigs.VerticalDownSwipeJump;
                }}
                renderScene={(route, navigator) => {
                  let Component = route.component;
                  return <Component {...route.params} navigator={navigator} />
                }} />
              );
        }
}
