<template>
  <div class="main-all">
    <!-- 用于显示标题等头信息 -->
    <div class="main-header">
      <img src="/static/img/header_border_light.png" alt="">
      <span class="title">Trump's Tweet Visualization</span>
    </div>
    <div class="main-box">
      <!-- 视图展示区域左右两栏布局 -->
      <div class="box-l">
        <!-- 顶部还要继续拆分 -->
        <div class="box-l-top">
          <div class="inner-l">
            <!-- 分别展示河流图和情感分析图 -->
            <div class="theme">
              <!-- 主题河流图 -->
              <Theme ref="theme" v-bind:startAndEndArrToTheme="startAndEndArr"></Theme>
            </div>
            <div class="emotion">
              <!-- 情感分析视图 -->
              <Emotion ref="emotion" v-bind:startAndEndArrToEmotion="startAndEndArr"></Emotion>
            </div>
          </div>
          <div class="inner-r">
            <!-- 发推趋势视图 -->
            <Trend ref="trend" @getStartAndEnd="getMsgFromTrend"></Trend>
          </div>
        </div>
        <!-- 底部用于展示日历视图 -->
        <div class="box-l-bottom">
          <!-- 日历视图 -->
          <Calendar ref="calendar" v-bind:startAndEndArrToCalendar="startAndEndArr"></Calendar>
        </div>
      </div>
      <div class="box-r">
        <div class="relation">
          <!-- 关系视图 -->
          <Relation ref="relation" v-bind:startAndEndArrToRelation="startAndEndArr"></Relation>
        </div>
        <div class="wordcloud">
          <!-- 词云视图 -->
          <WordCloud ref="wordCloud" v-bind:startAndEndArrToWordCloud="startAndEndArr"></WordCloud>
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
  },
  data () {
    return {
      startAndEndArr: {}
    }
  },
  methods: {
        getMsgFromTrend(startAndEndArr){
          // console.log(startAndEndArr)
          this.startAndEndArr = startAndEndArr
        }
  }
}
</script>
<style lang='less' scoped>
div {
  position: relative;
}
html, body {
    width: 100%;
    height: 100%;
}
.main-all {
  width: 100%;
  height: 100%;
}
.main-all .main-header {
  width: 100%;
  height: 10%;
  background-color: #e8e8e8;
  position: relative;
  > img {
    width: 100%;
  }
  .title {
    position: absolute;
    left: 50%;
    top: 40%;
    font-size: 20px;
    transform: translate(-50%, -50%);
    color: #495464;
  }
}
.main-all .main-box {
  width: 100%;
  height: 90%;
    background-color: #e8e8e8;
    // background-color: white;
}
.main-box .box-l {
  width: 70%;
  height: 100%;
  float: left;
  // background-color: gray;
}
.box-l .box-l-top {
  width: 100%;
  height: 70%;
  // background-color: blue;
}
.box-l-top .inner-l {
  width: 40%;
  height: 100%;
  float: left;
  // background-color: gray;
}
.inner-l .theme {
    background-color: #f4f4f2;
    margin-top: 2%;
    margin-bottom: 2%;
    margin-left: 2%;
  width: 95%;
  height: 50%;
  // background-color: brown;
}
.inner-l .emotion {
    background-color: #f4f4f2;
    margin-top: 2%;
    margin-bottom: 2%;
    margin-left: 2%;
    width: 95%;
  height: 50%;
  // background-color: darkgreen;
}
.box-l-top .inner-r {
  width: 60%;
  height: 100%;
  float: left;
  //background-color: blue;
    background-color: #f4f4f2;
    margin-top: 1%;
    margin-bottom: 1%;
}

.box-l .box-l-bottom {
    background-color: #f4f4f2;
    margin-top: 2%;
    margin-bottom: 2%;
    margin-left: 0.8%;
    width: 95%;
    height: 30%;
}
.main-box .box-r {
  width: 30%;
  height: 100%;
  float: left;
  // background-color: red;
}

.trend {
    background-color: #f4f4f2;
    margin-top: 2%;
    margin-bottom: 2%;
    margin-left: 2%;
}

.box-r .relation {
  width: 100%;
  height: 50%;
    background-color: #f4f4f2;
    margin-top: 2%;
    margin-bottom: 2%;
    margin-left: 2%;
  // background-color: gray;
}
.box-r .wordcloud {
    background-color: #f4f4f2;
    margin-top: 2%;
    margin-bottom: 2%;
    margin-left: 2%;
  width: 100%;
  height: 50%;
  // background-color: darkorange;
}
</style>
