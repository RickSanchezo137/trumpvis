<template>
  <div class="com-container">
    <div class="com-chart" ref="calendar_ref"></div>
    <span class="iconfont arr-left" @click="toLeft">&#xe6ef;</span>
    <span class="iconfont arr-right" @click="toRight">&#xe6ed;</span>
  </div>
</template>

<script>
export default {
  data () {
    return {
      allData: null,
      // 当前显示时间的区间段
      currentRange: 0
    }
  },
  mounted () {
    this.initChart()
    this.getData()
    window.addEventListener('resize', this.screenAdapter)
    this.screenAdapter()
    this.chartInstance.on('click', function (params) {
        console.log(params);
    })
  },
  props: {
    'startAndEndArrToCalendar':{
      start: String,
      end: String
    }
  },
  watch: {
    startAndEndArrToCalendar(newStartAndEndArr){
      console.log("Calendar has got startAndEndArr from Testpage, which is " + newStartAndEndArr['start'] + " to " + newStartAndEndArr['end'])
      let startTime = newStartAndEndArr['start']
      let startTimeYear = parseInt(startTime.split('-')[0])
      let startTimeMonth = parseInt(startTime.split('-')[1])
      let currentRange = (startTimeYear - 2009) * 2
      if(startTimeMonth > 6)
            currentRange += 1
      this.currentRange = currentRange
      this.updateChart()
    }
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
            // 必须给初始值，否则会报错
            range: ['2009-01-01', '2009-06-30'],
            splitLine: {
              show: true,
              lineStyle: {
                color: '#000',
                width: 2,
                type: 'solid'
              }
            },
            yearLabel: {
              // formatter: '{start}',
              fontSize: 15
            },
            itemStyle: {
              color: '#fff',
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
              color: '#66cccc'
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
              color: '#33cccc',
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
      // const { data: ret } = await this.$http.get('calendar')
      const { data: ret } = await this.$http({
        method: 'get',
        url: '/help/calendar',
      })
      this.allData = ret
      this.updateChart()
    },
    updateChart () {
      const yearLabelFormatter = this.allData[this.currentRange].name
      const dateRange = this.allData[this.currentRange].range
      var dataArr = this.allData[this.currentRange].data.map(item => {
        return [
          item.time,
          item.count
        ]
      })
      var dataOption = {
        calendar: [
          {
            range: dateRange,
            yearLabel: {
              formatter: yearLabelFormatter
            }
          }
        ],
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
    },
    toLeft () {
      this.currentRange--
      if (this.currentRange < 0) {
        this.currentRange = this.allData.length - 1
      }
      this.updateChart()
    },
    toRight () {
      this.currentRange++
      if (this.currentRange > this.allData.length - 1) {
        this.currentRange = 0
      }
      this.updateChart()
    }
  }
}
    // this.on('click', function (params) {
    //     alert(params.name);
    // })
</script>
<style lang='less' scoped>
.arr-left{
  position: absolute;
  left: 10%;
  top: 50%;
  transform: translateY(-50%);
  cursor: pointer; // 移上去变小手
  color: black
}
.arr-right{
  position: absolute;
  right: 10%;
  top: 50%;
  transform: translateY(-50%);
  cursor: pointer;
  color: black
}
</style>
