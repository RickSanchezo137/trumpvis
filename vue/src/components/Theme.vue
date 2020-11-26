<template>
  <div class="com-container">
    <div class="com-chart" ref="theme_ref"></div>
  </div>
</template>

<script>
export default {
  data () {
    return {
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
      this.chartInstance = this.$echarts.init(this.$refs.theme_ref)
      const initOption = {
        // 提示工具
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'line',
            lineStyle: {
              color: 'rgba(0, 0, 0, 0.2)',
              width: 1,
              type: 'solid'
            }
          }
        },
        singleAxis: {
          top: 50,
          bottom: 50,
          axisTick: {},
          axisLabel: {},
          type: 'time',
          axisPointer: {
            animation: true,
            label: {
              show: true
            }
          },
          splitLine: {
            show: true,
            lineStyle: {
              type: 'dashed',
              opacity: 0.2
            }
          }
        }
      }
      this.chartInstance.setOption(initOption)
    },
    async getData () {
      // 获取数据
      const { data: ret } = await this.$http.get('theme')
      this.allData = ret
      console.log(this.allData)
      this.updateChart()
    },
    updateChart () {
      // 获取数据之后更新图表
      // 图例数组
      const legendArr = []
      const valueArr = this.allData.data
      const seriesArr = valueArr.map(item => {
        return [
          item.time,
          item.count,
          item.theme
        ]
      })
      console.log(seriesArr)
      const dataOption = {
        legend: legendArr,
        series: [
          {
            type: 'themeRiver',
            emphasis: {
              itemStyle: {
                shadowBlur: 20,
                shadowColor: 'rgba(0, 0, 0, 0.8)'
              }
            },
            data: seriesArr
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
