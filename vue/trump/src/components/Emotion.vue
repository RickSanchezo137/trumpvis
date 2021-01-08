<template>
  <div class='com-container'>
    <div class='com-chart' ref='emotion_ref'></div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      allData: null // 从服务器中获取的所有数据
    }
  },
  mounted () {
    this.initChart()
    this.getData("2009-05", "2020-01")
    window.addEventListener('resize', this.screenAdapter)
    this.screenAdapter()
  },
  props: {
    'startAndEndArrToEmotion':{
      start: String,
      end: String
    }
  },
  watch: {
    startAndEndArrToEmotion(newStartAndEndArr){
      console.log("Emotion has got startAndEndArr from Testpage, which is " + newStartAndEndArr['start'] + " to " + newStartAndEndArr['end'])
      this.getData(newStartAndEndArr['start'], newStartAndEndArr['end'])
    }
  },
  destroyed () {
    window.removeEventListener('resize', this.screenAdapter)
  },
  methods: {
    initChart () {
      this.chartInstance = this.$echarts.init(this.$refs.emotion_ref)
      const initOption = {
        grid: {
          top: '10%',
          bottom: '15%',
          containLabel: true // 包含坐标轴上的文字
        },
        xAxis: {
          scale: true,
          name: 'Sentiment',
          nameLocation: 'middle',
          nameTextStyle: {
            fontWeight: 'bold'
          },
          nameGap: 25
        },
        yAxis: {
          scale: true,
          name: 'Subjectivity',
          nameLocation: 'middle',
          nameTextStyle: {
            fontWeight: 'bold'
          },
          nameGap: 35
        },
        series: [
          {
            type: 'scatter'
          }
        ]
      }
      this.chartInstance.setOption(initOption)
    },
    async getData (startTime, endTime) {
      // await this.$http.get()
      // const { data: ret } = await this.$http.get('emotion')
      const { data: ret } = await this.$http({
        method: 'post',
        url: '/help/emotion',
        params: {
          startTime: startTime,
          endTime: endTime
        }
      })
      console.log(ret)
      // 对allData进行赋值
      this.allData = ret
      this.updateChart()
    },
    updateChart () {
      const dataArr = this.allData.map(item => {
        return [
          item.sentiment,
          item.subjectivity
        ]
      })
      const dataOption = {
        series: [
          {
            data: dataArr
          }
        ]
      }
      this.chartInstance.setOption(dataOption)
    },
    screenAdapter () {
      const adapterOption = {}
      this.chartInstance.setOption(adapterOption)
      this.chartInstance.resize()
    }
  }
}
</script>
<style lang='less' scoped>

</style>
