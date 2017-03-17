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

import FirstPageComponent from './FirstPageComponent';

export default class SecondPageComponent extends React.Component{


  constructor(props: any) {
      super(props);
      this.state = {

      };
  }


  _pressButton() {
          const { navigator } = this.props;
          if(navigator) {
              //很熟悉吧，入栈出栈~ 把当前的页面pop掉，这里就返回到了上一个页面:FirstPageComponent了
              navigator.pop();
          }
  }

  render() {
    return (

      <View style={styles.container}>

       <Animated.Text style={styles.hello,
         {transform: [{scale:this.state.bounceValue}]}}>Hello,World</Animated.Text>

         <Animated.Image
         source={{uri: 'https://facebook.github.io/react/img/logo_og.png'}}
         style={{
           flex: 1,

           transform: [                        // `transform`是一个有序数组（动画按顺序执行）
             {scale: this.state.bounceValue},  // 将`bounceValue`赋值给 `scale`
           ]
         }}
       />
     <TouchableOpacity onPress={this._pressButton.bind(this)}>
                    <Text>点我跳回去</Text>
      </TouchableOpacity>

     </View>

    );
  }

}


var styles = StyleSheet.create({
  container: {
    flex: 1,
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
