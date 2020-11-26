<template>
  <div class="com-container">
    <div class="com-chart" ref="seller_ref"></div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      chartInstance: null,
      allData: null,
      currentPage: 1, // 当前页码 1
      totalPage: 0, // 总页数
      timerId: null
    }
  },
  mounted () {
    this.initChart()
    this.getData()
    window.addEventListener('resize', this.screenAdapter)
    this.screenAdapter()
  },
  destroyed () {
    clearInterval(this.timerId)
    window.removeEventListener('resize', this.screenAdapter)
  },
  methods: {
    initChart () {
      this.chartInstance = this.$echarts.init(this.$refs.seller_ref, 'chalk')
      const initOption = {
        title: {
          text: '▎ Sales Rank',
          left: 20,
          top: 20
        },
        grid: {
          top: '20%',
          left: '3%',
          right: '6%',
          bottom: '3%',
          containLabel: true
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'line',
            z: 0,
            lineStyle: {
              width: 66,
              color: '#2D3443'
            }
          }
        },
        xAxis: {
          type: 'value'
        },
        yAxis: {
          type: 'category'
        },
        series: [
          {
            type: 'bar',
            // 每个柱的值的显示和位置
            label: {
              show: true,
              position: 'right',
              textStyle: {
                color: '#fff'
              }
            },
            // 控制柱宽度和柱圆角
            barWidth: 66,
            itemStyle: {
              barBorderRadius: [0, 33, 33, 0],
              color: new this.$echarts.graphic.LinearGradient(0, 0, 1, 0, [
                {
                  offset: 0,
                  color: '#5052EE'
                },
                {
                  offset: 1,
                  color: '#AB6EE5'
                }
              ])
            }
          }
        ]
      }
      this.chartInstance.setOption(initOption)
      this.chartInstance.on('mouseover', () => {
        clearInterval(this.timerId)
      })
      this.chartInstance.on('mouseout', () => {
        this.startInterval()
      })
    },
    async getData () {
      // 获取数据
      const { data: ret } = await this.$http.get('seller')
      this.allData = ret
      // console.log(this.allData)
      this.allData.sort((a, b) => {
        // 根据value值从大到小排序
        return a.value - b.value
      })
      // 计算总页数
      this.totalPage = (this.allData.length % 5) === 0 ? parseInt(this.allData.length / 5) : (parseInt(this.allData.length / 5) + 1)
      this.updateChart()
      this.startInterval()
    },
    updateChart () {
      const start = (this.currentPage - 1) * 5
      const end = this.currentPage * 5
      const showData = this.allData.slice(start, end)
      const categoryArr = showData.map(item => {
        return item.name
      })
      const valueArr = showData.map(item => {
        return item.value
      })
      const dataOption = {
        yAxis: {
          data: categoryArr
        },
        series: [
          {
            data: valueArr
          }
        ]
      }
      this.chartInstance.setOption(dataOption)
    },
    screenAdapter () {
      // console.log(this.$refs.seller_ref.offsetWidth)
      const titleFontSize = this.$refs.seller_ref.offsetWidth / 100 * 3.6
      const adapterOption = {
        title: {
          textStyle: {
            fontSize: titleFontSize
          }
        },
        tooltip: {
          axisPointer: {
            lineStyle: {
              width: titleFontSize
            }
          }
        },
        series: [
          {
            barWidth: titleFontSize,
            itemStyle: {
              barBorderRadius: [0, titleFontSize / 2, titleFontSize / 2, 0]
            }
          }
        ]
      }
      this.chartInstance.setOption(adapterOption)
      this.chartInstance.resize()
    },
    startInterval () {
      if (this.timerId) {
        // 如果存在定时器，应该先停止定时器
        clearInterval(this.timerId)
      }
      this.timerId = setInterval(() => {
        this.currentPage++
        if (this.currentPage > this.totalPage) {
          this.currentPage = 1
        }
        this.updateChart()
      }, 3000)
    }
  }
}
</script>
<style lang='less' scoped>

</style>
