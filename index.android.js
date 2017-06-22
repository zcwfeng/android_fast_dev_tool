'use strict';

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  DrawerLayoutAndroid,
  PixelRatio,
  Picker,
  Image,
  ProgressBarAndroid,
  ScrollView,
} from 'react-native';

import TitleBar from './ui-custom-component/TitleBar';
import SlideContentHeader from './ui-custom-component/SlideContentHeader';
import TabNavigator from 'react-native-tab-navigator';
import ScrollableTabView,{ScrollableTabBar,} from 'react-native-scrollable-tab-view';
import VerticalListView from './ui-custom-component/VerticalListView';

var onePT = 1 / PixelRatio.get();
class StarTV extends Component {

    constructor(props) {
        super(props);
        this.state = {
          selectedTab: 'home',
          language : null
        };
    }

  render() {
    var navigationView = (
       <View style={[styles.flex]}>
          <SlideContentHeader/>
       </View>
    );

    var homeView = (
     <View style={[styles.flex, styles.center]}>
       <ScrollableTabView
           initialPage={0}
           renderTabBar={() => <ScrollableTabBar/>}
           >

            <ScrollView tabLabel="我的" style={styles.tabView}>

              <VerticalListView/>
          </ScrollView>
          <ScrollView tabLabel="全新" style={styles.tabView}>
              <View style={styles.card}>
                <Text>Friends</Text>
              </View>
          </ScrollView>

      </ScrollableTabView>
     </View>
   );

   var settingView = (
     <View style={[styles.flex, styles.center, styles.customBg]}>
       <Text style={styles.customText}>日程表</Text>
     </View>
   );

   var channelView = (
     <View style={[styles.flex, styles.center, styles.customBg]}>
       <Text style={styles.customText}>Channel</Text>
     </View>
   );

    return (

        <DrawerLayoutAndroid drawerWidth={260}
                             drawerPosition={DrawerLayoutAndroid.positions.left}
                             renderNavigationView={()=> navigationView}>
       <View style={{flex:1}}>
          <TitleBar/>
          <TabNavigator   tabBarStyle={{ height: 70, overflow: 'hidden' }}   sceneStyle={{ paddingBottom: 10 }}>
            <TabNavigator.Item
              selected={this.state.selectedTab === 'home'}
              renderIcon={() => <Image style={styles.img} source={require('./img/main.png') }/>}
              renderSelectedIcon={() => <Image style={styles.img} source={require('./img/main_down.png') } />}
              onPress={() => this.setState({ selectedTab: 'home' }) }>
              {homeView}
            </TabNavigator.Item>
            <TabNavigator.Item
              selected={this.state.selectedTab === 'schdule'}
              renderIcon={() => <Image style={styles.img} source={require('./img/schdule.png') } />}
              renderSelectedIcon={() => <Image style={styles.img} source={require('./img/schdule_down.png') } />}
              onPress={() => this.setState({ selectedTab: 'schdule' }) }>
              {settingView}
            </TabNavigator.Item>
            <TabNavigator.Item
              selected={this.state.selectedTab === 'channel'}
              renderIcon={() => <Image style={styles.img} source={require('./img/channel.png') } />}
              renderSelectedIcon={() => <Image style={styles.img} source={require('./img/channel_down.png') } />}
              onPress={() => this.setState({ selectedTab: 'channel' }) }>
              {channelView}
            </TabNavigator.Item>
          </TabNavigator>
        </View>
         </DrawerLayoutAndroid>
      );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
  flex: {
     flex:1,
   },

   flexDirection :{
     flexDirection:'row',
   },

   topStatus : {
     marginTop : 25,
   } ,

   input:{
     height:45,
     borderColor:'red',
     borderWidth: 1,
     marginLeft:10,

     borderRadius:5,
     paddingLeft:10,
   },

   btn:{
     height:45,
     width:45,
     marginLeft:-5,
     marginRight:5,
     backgroundColor:'#23BEFF',
     alignItems:'center',
     justifyContent:'center',

   },
   search:{
     color:'#fff',
     fontWeight:'bold',
     fontSize:15,
   },
   result:{
     marginTop:onePT,
     marginLeft:18,
     marginRight:5,
     height:200,

   },
   item:{
   fontSize:16,
   paddingTop:5,
   paddingBottom:10,
   },
   img: {
       width: 40,
       height: 33,
   },
   center: {
      justifyContent: 'center',
      alignItems: 'center',
    },
   customBg: {
     backgroundColor: "#ffff0044",
   },
   customText: {
     fontSize: 22,
   },
   customContainer: {
     textAlign: 'auto'
   },
   card: {
    borderWidth: 1,
    backgroundColor: '#fff',
    borderColor: 'rgba(0,0,0,0.1)',
    margin: 5,
    height: 150,
    padding: 15,
    shadowColor: '#ccc',
    shadowOffset: { width: 2, height: 2, },
    shadowOpacity: 0.5,
    shadowRadius: 3,
  },
  tabView: {
    flex: 1,
    padding: 10,
    backgroundColor: 'rgba(0,0,0,0.01)',
  },
  icon: {
    width: 300,
    height: 300,
    alignSelf: 'center',
  },
});

AppRegistry.registerComponent('AndroidFastDevTool', () => HelloWorld);

