<template>
  <div class="com-container">
    <div class="com-chart" ref="trend_ref"></div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      chartInstane: null,
      allData: null, // 从服务器中获取的所有数据
      titleFontSize: 0 // 指明标题的字体大小
    }
  },
  mounted () {
    this.initChart()
    this.getData()
    window.addEventListener('resize', this.screenAdapter)
    this.screenAdapter()
    var that = this
    this.chartInstane.on('dataZoom', function (params) {
          let start = that.chartInstane.getOption().dataZoom[0].startValue
          let end = that.chartInstane.getOption().dataZoom[0].endValue
          let timeArr = that.chartInstane.getOption().xAxis[0].data
          // console.log('start: ', timeArr[start])
          // console.log('end: ', timeArr[end])
          let startAndEnd = {
            start: timeArr[start],
            end: timeArr[end]
          }
          that.$emit('getStartAndEnd', startAndEnd)
      })
  },
  destroyed () {
    window.removeEventListener('resize', this.screenAdapter)
  },
  methods: {
    initChart () {
      this.chartInstane = this.$echarts.init(this.$refs.trend_ref)
      const initOption = {
        title: {
          text: 'Tweet Trend',
          textStyle: {
            fontSize: 12
          },
          left: 10,
          top: 15
        },
        grid: {
          left: '1%',
          top: '20%',
          right: '5%',
          bottom: '15%',
          containLabel: true
        },
        tooltip: {
          trigger: 'axis'
        },
        dataZoom: {
          bottom: '5%',
          realtime: false
        },
        legend: {
          left: 20,
          top: '10%',
          icon: 'circle'
        },
        xAxis: {
          type: 'category',
          boundaryGap: false
        },
        yAxis: {
          type: 'value'
        }
      }
      this.chartInstane.setOption(initOption)
    },
    async getData () {
      // await this.$http.get()
      // 对allData进行赋值
      const { data: ret } = await this.$http.get('trend')
      this.allData = ret
      console.log(this.allData)
      this.updateChart()
    },
    updateChart () {
      // 半透明的颜色值
      const colorArr1 = [
        'rgba(11, 168, 44, 0.5)',
        'rgba(44, 110, 255, 0.5)',
        'rgba(22, 242, 217, 0.5)',
        'rgba(254, 33, 30, 0.5)',
        'rgba(250, 105, 0, 0.5)'
      ]
      // 全透明的颜色值
      const colorArr2 = [
        'rgba(11, 168, 44, 0)',
        'rgba(44, 110, 255, 0)',
        'rgba(22, 242, 217, 0)',
        'rgba(254, 33, 30, 0)',
        'rgba(250, 105, 0, 0)'
      ]
      // 处理数据
      // 类目轴的数据
      const timeArr = this.allData.time.month
      // y轴的数据 series下的数据
      const valueArr = this.allData.data
      const seriesArr = valueArr.map((item, index) => {
        return {
          name: item.name,
          type: 'line',
          smooth: true,
          data: item.data,
          stack: 'trend',
          areaStyle: {
            color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
              {
                offset: 0,
                color: colorArr1[index]
              }, // %0的颜色值
              {
                offset: 1,
                color: colorArr2[index]
              } // 100%的颜色值
            ])
          }
        }
      })
      // 图例的数据
      const legendArr = valueArr.map(item => {
        return item.name
      })
      const dataOption = {
        xAxis: {
          data: timeArr
        },
        legend: {
          data: legendArr
        },
        series: seriesArr
      }
      this.chartInstane.setOption(dataOption)
    },
    screenAdapter () {
      this.titleFontSize = this.$refs.trend_ref.offsetWidth / 100 * 3.6
      const adapterOption = {
        legend: {
          itemWidth: this.titleFontSize / 2,
          itemHeight: this.titleFontSize / 2,
          itemGap: this.titleFontSize,
          textStyle: {
            fontSize: this.titleFontSize / 2
          }
        }
      }
      this.chartInstane.setOption(adapterOption)
      this.chartInstane.resize()
    }
  }
}
</script>

<style lang="less" scoped>
</style>
