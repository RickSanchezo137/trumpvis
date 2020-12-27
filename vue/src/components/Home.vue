<template>
  <div class="com-container">
    <div class="com-chart" ref="main_ref"></div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      chartInstance: null,
      allData: null
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
      // 初始化图表方法
      this.chartInstance = this.$echarts.init(this.$refs.main_ref, 'chalk')
      const initOption = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        xAxis: {
          type: 'category'
        },
        yAxis: {
          type: 'value',
          splitLine: {
            show: false
          }
        },
        dataZoom: {

        },
        series: [
          {
            name: 'line',
            type: 'line',
            smooth: true,
            showAllSymbol: true,
            symbol: 'emptyCircle',
            symbolSize: 15
          },
          {
            name: 'bar',
            type: 'bar',
            barWidth: 10,
            itemStyle: {
              barBorderRadius: 5,
              color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: '#14c8d4'
                },
                {
                  offset: 1,
                  color: '#43eec6'
                }
              ])
            }
          },
          {
            name: 'line',
            type: 'bar',
            barGap: '-100%',
            barWidth: 10,
            itemStyle: {
              color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1,
                [
                  {
                    offset: 0,
                    color: 'rgba(20,200,212,0.5)'
                  },
                  {
                    offset: 0.2,
                    color: 'rgba(20,200,212,0.2)'
                  },
                  {
                    offset: 1,
                    color: 'rgba(20,200,212,0)'
                  }
                ]
              )
            },
            z: -12
          },
          {
            name: 'dotted',
            type: 'pictorialBar',
            symbol: 'rect',
            itemStyle: {
              color: '#0f375f'
            },
            symbolRepeat: true,
            symbolSize: [12, 4],
            symbolMargin: 1,
            z: -10
          }
        ]
      }
      this.chartInstance.setOption(initOption)
    },
    async getData () {
      // 获取数据
      const { data: ret } = await this.$http.get('tweet')
      this.allData = ret
      console.log(this.allData)
      this.updateChart()
    },
    updateChart () {
      // 获取数据之后更新图表
      // this.allData中的 count, retweets, favorites 分别
      // 用
      const timeArr = this.allData.map(item => {
        return item.name
      })
      const barData = this.allData.map(item => {
        return item.value.count
      })
      const lineData = this.allData.map(item => {
        // 发推量 与 转发量和点赞量 不是一个量级，可以通过数学方法降低数量差异
        return parseInt((item.value.retweets + item.value.favorites) / 50)
      })
      console.log(lineData)
      // const retweetsArr = this.allData.map(item => {
      //   return item.value.retweets
      // })
      // const favoritesArr = this.allData.map(item => {
      //   return item.value.favorites
      // })
      const dataOption = {
        xAxis: {
          data: timeArr
        },
        series: [
          {
            data: lineData
          },
          {
            data: barData
          },
          {
            data: lineData
          },
          {
            data: lineData
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
