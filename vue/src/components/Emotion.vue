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
    this.getData()
    window.addEventListener('resize', this.screenAdapter)
    this.screenAdapter()
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
    async getData () {
      // await this.$http.get()
      const { data: ret } = await this.$http.get('emotion')
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
