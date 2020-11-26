<template>
  <div>
    <div class="wrapper">
      <!-- 标题部分 -->
      <header class="screen-header">
        <div>
          <img src="/static/img/header_border_light.png" alt="">
        </div>
        <span class="title">Trump's Tweet Visualization</span>
      </header>
      <!-- 主体部分 -->
      <div class="mian-wrap">
        <!-- 主体部分 -->
        <div class="mian-box">
          <!-- 先两栏布局 -->
          <div class="mian">
            <div class="mian-inner">
              <div class="inner-lf">
                <div class="theme">
                  <!-- 主题图表 -->
                  <Theme ref="theme"></Theme>
                </div>
                <div class="emotion">
                  <!-- 情感分析图表 -->
                  <Emotion ref="emotion"></Emotion>
                </div>
              </div>
              <div class="inner-cer">
                <!-- 发推数量变化趋势图表 -->
                <Trend ref="trend"></Trend>
              </div>
            </div>
            <div class="mian-bottom">
              <!-- 日历视图 -->
              <Calendar ref="calendar"></Calendar>
            </div>
          </div>
          <div class="box-rt">
            <div class="mobule">
              <!-- 关键词关系图表 -->
              <Relation ref="relation"></Relation>
            </div>
            <div class="app">
              <!-- 词云图表 -->
              <WordCloud ref="wordcloud"></WordCloud>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Trend from '@/components/Trend.vue'
import WordCloud from '@/components/WordCloud.vue'
import Emotion from '@/components/Emotion.vue'
import Calendar from '@/components/Calendar.vue'
import Relation from '@/components/Relation.vue'
import Theme from '@/components/Theme.vue'
export default {
  components: {
    Trend,
    WordCloud,
    Emotion,
    Calendar,
    Relation,
    Theme
  }
}
</script>
<style lang="less" scoped>
* {
  margin: 0;
  padding: 0;
}
div {
  color: #fff;
  font-size: 16px;
  position: relative;
}
html,
body {
  width: 100%;
  height: 100%;
}
.wrapper {
  width: 100%;
  height: 100%;
  background-color: #fff;
  color: #fff;
  box-sizing: border-box;
  border: 1px solid #000;
}
.screen-header {
  width: 100%;
  height: 64px;
  font-size: 20px;
  position: relative;
  > div {
      img {
        width: 100%;
      }
    }
  .title {
    position: absolute;
    left: 50%;
    top: 50%;
    font-size: 20px;
    transform: translate(-50%, -50%);
    color: black;
  }
}
/* 主体部分 */
.wrapper .mian-wrap {
  // width: 1276px;
  // height: 695px;
  width: 100%;
  height: 100%;
  display: flex;
  // border: 1px solid #000;
}
.wrapper .mian-wrap .mian-box {
  width: 100%;
  height: 100%;
}
.wrapper .mian-wrap .mian-box .mian {
  // width: 890px;
  // height: 695px;
  width: 69.8%;
  height: 100%;
  float: left;
}
.mian-wrap .mian-box .mian .mian-inner {
  // width: 890px;
  // height: 460px;
  width: 100%;
  height: 460px;
  float: left;
  // background-color: pink;
}
.mian-wrap .mian-box .mian .mian-inner .inner-lf {
  // width: 350px;
  width: 39%;
  height: 100%;
  float: left;
  // background-color:white;
}
.mian-wrap .mian-box .mian .mian-inner .inner-lf .theme {
  // width: 350px;
  width: 100%;
  // height: 230px;
  height: 50%;
  float: left;
  // background-color: gray;
}
.mian-wrap .mian-box .mian .mian-inner .inner-lf .emotion {
  // width: 350px;
  width: 100%;
  // height: 230px;
  height: 50%;
  float: left;
  // background-color: #ff5500;
}
.mian-wrap .mian-box .mian .mian-inner .inner-cer {
  // width: 540px;
  width: 60%;
  height: 100%;
  float: left;
  // background-color: yellow;
}
.mian-wrap .mian .mian-bottom {
  // width: 890px;
  // height: 235px;
  width: 100%;
  height: 235px;
  // background-color: purple;
}
.wrapper .mian-box .box-rt {
  // width: 386px;
  width: 30%;
  // height: 695px;
  height: 100%;
  float: left;
  // background-color: blue;
}
.wrapper .mian-box .box-rt .mobule {
  // width: 386px;
  width: 100%;
  height: 350px;
  // height: 50%;
  float: left;
  // background-color: red;
}
.wrapper .mian-box .box-rt .app {
  // width: 386px;
  width: 100%;
  height: 345px;
  // height: 50%;
  float: left;
  // background-color:green;
}
</style>
