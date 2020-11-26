<template>
  <div class="com-container">
    <div class="com-chart" ref="calendar_ref"></div>
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
      this.chartInstance = this.$echarts.init(this.$refs.calendar_ref)
      const initOption = {
        title: {
          text: 'Daily Tweet',
          textStyle: {
            fontSize: 12
          },
          left: 10,
          top: 15
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: 10,
          data: ['count', 'top 3']
        },
        calendar: [
          {
            top: 50,
            left: 'center',
            range: ['2019-01-01', '2019-06-30'],
            splitLine: {
              show: true,
              lineStyle: {
                color: '#000',
                width: 4,
                type: 'solid'
              }
            },
            yearLabel: {
              formatter: '{start}',
              fontSize: 15
            },
            itemStyle: {
              color: '#323c48',
              borderWidth: 1,
              borderColor: '#111'
            }
          }
        ],
        series: [
          {
            name: 'count',
            type: 'scatter',
            coordinateSystem: 'calendar',
            itemStyle: {
              color: '#ddb926'
            }
          },
          {
            name: 'top 3',
            type: 'effectScatter',
            coordinateSystem: 'calendar',
            showEffectOn: 'render',
            rippleEffect: {
              brushType: 'stroke'
            },
            hoverAnimation: true,
            itemStyle: {
              color: '#f4e925',
              shadowBlur: 10,
              shadowColor: '#333'
            },
            zlevel: 1
          }
        ]
      }
      this.chartInstance.setOption(initOption)
    },
    async getData () {
      const { data: ret } = await this.$http.get('/help/calendar', {
        params: {
          startTime: '2019-01-01',
          endTime: '2019-06-30'
        }
      })
      this.allData = ret
      // console.log(this.allData)
      this.updateChart()
    },
    updateChart () {
      const dataArr = this.allData.map(item => {
        return [
          item.time,
          item.count
        ]
      })
      console.log(dataArr)
      const dataOption = {
        series: [
          {
            data: dataArr,
            symbolSize: function (val) {
              return val[1] / 1.5
            }
          },
          {
            data: dataArr.sort(function (a, b) {
              return b[1] - a[1]
            }).slice(0, 3),
            symbolSize: function (val) {
              return val[1] / 1.5
            }
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
