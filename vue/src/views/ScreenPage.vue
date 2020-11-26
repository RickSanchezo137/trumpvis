<template>
  <div class="screen-container">
    <header class="screen-header">
      <div>
        <img src="/static/img/header_border_light.png" alt="">
      </div>
      <span class="logo">
      </span>
      <span class="title">Trump's Tweet Visualization</span>
      <div class="title-right">
      </div>
    </header>
    <div class="screen-body">
      <!-- 新的布局 -->
      <!-- <section class="screen-left">
        <section class="screen-left-top">
          <section class="screen-left-top-left"></section>
          <section class="screen-left-top-right"></section>
        </section>
        <section class="screen-left-bottom"></section>
      </section> -->
      <section class="screen-left">
        <div id="left-top">
          <!-- 主题图表 -->
          <Theme ref="theme"></Theme>
        </div>
        <div id="left-bottom">
          <!-- 情感分析图表 -->
          <Emotion ref="emotion"></Emotion>
        </div>
      </section>
      <section class="screen-middle">
        <div id="middle-top">
          <!-- 发推数量变化趋势图表 -->
          <Trend ref="trend"></Trend>
        </div>
        <!-- <div id="middle-bottom"> -->
          <!-- 日历图表 -->
          <!-- <Calendar ref="calendar"></Calendar> -->
        <!-- </div> -->
      </section>
      <section class="screen-bottom">
        <div id="bottom">
          <Calendar ref="calendar"></Calendar>
        </div>
      </section>
      <section class="screen-right">
        <div id="right-top">
          <!-- 关键词关系图表 -->
          <Relation ref="relation"></Relation>
        </div>
        <div id="right-bottom">
          <!-- 词云图表 -->
          <WordCloud ref="wordcloud"></WordCloud>
        </div>
      </section>
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
  data () {
    return {
      // 定义每一个图表的全屏状态
      fullScreenStatus: {
        trend: false,
        wordcloud: false,
        emotion: false,
        calendar: false,
        relation: false,
        theme: false
      }
    }
  },
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
// 全屏样式的定义
.fullscreen {
  position: fixed!important;
  top: 0 !important;
  left: 0 !important;
  width: 100% !important;
  height: 100% !important;
  margin: 0 !important;
  z-index: 100;
}
.screen-container {
  width: 100%;
  height: 100%;
  padding: 0 10px;
  background-color: #fff;
  color: #fff;
  box-sizing: border-box;
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
    color: #000;
  }
  .title-right {
    display: flex;
    align-items: center;
    position:absolute;
    right: 0px;
    top: 50%;
    transform: translateY(-80%);
  }
  .qiehuan {
    width: 28px;
    height: 21px;
    cursor: pointer;
  }
  .datetime {
    font-size: 15px;
    margin-left: 10px;
  }
  .logo {
    position: absolute;
    left: 0px;
    top: 50%;
    transform: translateY(-80%);
    img {
      height: 35px;
      width: 128px;
    }
  }
}
.screen-body {
  width: 100%;
  height: 100%;
  display: flex;
  .screen-left {
    height: 56%;
    width: 27.6%;
    #left-top {
      height: 53%;
      position: relative;
    }
    #left-bottom {
      height: 40.5%;
      margin-top: 25px;
      position: relative;
    }
  }
  .screen-middle {
    height: 56%;
    width: 41.5%;
    margin-left: 1%;
    margin-right: 1%;
    #middle-top {
      width: 100%;
      height: 100%;
      position: relative;
    }
    #middle-bottom {
      margin-top: 25px;
      width: 100%;
      height: 28%;
      position: relative;
    }
  }
  .screen-right {
    height: 100%;
    width: 27.6%;
    #right-top {
      height: 45%;
      position: relative;
    }
    #right-bottom {
      height: 45%;
      margin-top: 20px;
      position: relative;
    }
  }
}
.resize{
  position: absolute;
  right: 20px;
  top: 20px;
  cursor: pointer;
}
</style>
