'use strict';

import React from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  Image,
  TouchableHighlight,
  Animated,
  TouchableOpacity,
  Navigator,
} from 'react-native';

import FirstPageComponent from './FirstPageComponent'

class HelloWorld extends React.Component{

  

  render() {

    const routes = [
    {title: 'First Scene', index: 0},
    {title: 'Second Scene', index: 1},
    ];
    return (
      <Navigator
        initialRoute={routes[0]}
        initialRouteStack={routes}
        configureScene={(route, routeStack) =>
          Navigator.SceneConfigs.FloatFromBottom}
        renderScene={(route, navigator) =>

          <TouchableHighlight onPress={() => {
            if (route.index === 0) {
              navigator.push(routes[1]);
            } else {
              navigator.pop();
            }
          }}>
          <Text>Hello {route.title}!</Text>
          </TouchableHighlight>
        }

        navigationBar={
           <Navigator.NavigationBar
             routeMapper={{
               LeftButton: (route, navigator, index, navState) =>
                {
                  if (route.index === 0) {
                    return null;
                  } else {
                    return (
                      <TouchableHighlight onPress={() => navigator.pop()}>
                        <Text>Back</Text>
                      </TouchableHighlight>
                    );
                  }
                },
               RightButton: (route, navigator, index, navState) =>
                 { return (<Text>Done</Text>); },
               Title: (route, navigator, index, navState) =>
                 { return (<Text>Awesome Nav Bar</Text>); },
             }}


             style={{backgroundColor: 'blue '}}
           />


          }


        style={{padding: 100}}

      />
    );


  }
}




var styles = StyleSheet.create({
  container: {
    flex: 0,
    justifyContent: 'center',
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  img:{
    margin: 10,
  },
});

AppRegistry.registerComponent('AndroidFastDevTool', () => HelloWorld);
