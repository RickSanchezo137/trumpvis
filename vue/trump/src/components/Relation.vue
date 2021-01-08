<template>
  <div class="com-container">
    <div class="com-chart" ref="relation_ref"></div>
  </div>
</template>

<script>
// import axios from 'axios'
// import $ from 'jquery'
export default {
  data () {
    return {
      allData: null
    }
  },
  mounted () {
    this.initChart()
    this.getData('2009-05', '2020-01')
    window.addEventListener('resize', this.screenAdapter)
    this.screenAdapter()
  },
  props: {
    'startAndEndArrToRelation': {
      start: String,
      end: String
    }
  },
  watch: {
    startAndEndArrToRelation(newStartAndEndArr){
      console.log("Relation has got startAndEndArr from Testpage, which is " + newStartAndEndArr['start'] + " to " + newStartAndEndArr['end'])
      this.getData(newStartAndEndArr['start'], newStartAndEndArr['end'])
    }
  },
  destroyed () {
    window.removeEventListener('resize', this.screenAdapter)
  },
  methods: {
    initChart () {
      // 初始化图表方法
      this.chartInstance = this.$echarts.init(this.$refs.relation_ref)
      // this.chartInstance.showLoading()
      // 首先需要引入 jquery 并且要在 index.html 中引入 dataTool.js
      // $.get('http://localhost:8999/static/gexf/les-miserables.gexf', xml => {
      //   this.chartInstance.hideLoading()
      //   // 解析出来的 graph 是两个数组：nodes[] 和 links[]，分别表示节点和节点之间边的关系
      //   // nodes和links数组中每个元素又是一个个对象
      //   // nodes中每个元素对象包含的信息：category, id, name, symbolSize, value, x, y
      //   // links中每个元素对象包含的信息：id, source, target
      //   const graph = this.$echarts.dataTool.gexf.parse(xml)
      //   // console.log(graph)
      //   // 用于显示图例
      //   const categories = []
      //   for (var i = 0; i < 9; i++) {
      //     categories[i] = {
      //       name: '类目' + i
      //     }
      //   }
      //   // console.log(categories)
      //   graph.nodes.forEach(function (node) {
      //     node.itemStyle = null
      //     node.value = node.symbolSize
      //     node.symbolSize /= 2.5
      //     // node.label = {
      //     //   normal: {
      //     //     show: node.symbolSize > 10
      //     //   }
      //     // }
      //     node.category = node.attributes.modularity_class
      //   })
      //   const option = {
      //     title: {
      //       text: 'Keywords Relation',
      //       textStyle: {
      //         fontSize: 12
      //       },
      //       top: 15,
      //       left: 10
      //     },
      //     tooltip: {},
      //     // legend: [{
      //     //   data: categories.map(function (a) {
      //     //     return a.name
      //     //   })
      //     // }],
      //     animationDurationUpdate: 1500,
      //     animationEasingUpdate: 'quinticInOut',
      //     series: [
      //       {
      //         name: 'Les Miserables',
      //         type: 'graph',
      //         layout: 'circular',
      //         circular: {
      //           rotateLabel: true
      //         },
      //         data: graph.nodes,
      //         links: graph.links,
      //         categories: categories,
      //         roam: true,
      //         focusNodeAdjacency: true,
      //         label: {
      //           position: 'right',
      //           formatter: '{b}'
      //         },
      //         lineStyle: {
      //           color: 'source',
      //           curveness: 0.3
      //         },
      //         emphasis: {
      //           lineStyle: {
      //             width: 5
      //           }
      //         }
      //       }
      //     ]
      //   }
      //   this.chartInstance.setOption(option)
      // }, 'xml')
      const initOption = {
        title: {
          text: 'Keywords Relation',
          textStyle: {
            fontSize: 12
          },
          top: 15,
          left: 10
        },
        tooltip: {},
        animationDurationUpdate: 1500,
        animationEasingUpdate: 'quinticInOut',
        series: [
          {
            name: 'Keywords Relation',
            type: 'graph',
            layout: 'circular',
            circular: {
              rotateLabel: true
            },
            roam: true,
            focusNodeAdjacency: true,
            label: {
              normal: {
                show: true
              },
              position: 'right',
              formatter: '{b}'
            },
            lineStyle: {
              color: 'source',
              curveness: 0.3
            },
            emphasis: {
              lineStyle: {
                width: 5
              }
            }
          }
        ]
      }
      this.chartInstance.setOption(initOption)
    },
    async getData (startTime, endTime) {
      // 获取数据
      // const ret = await axios.get('http://localhost:8999/static/map/province/sichuan.json')
      // const ret = await axios.get('http://localhost:8999/static/lib/les-miserables.gexf.xml')
      // console.log(ret)
      // const { data: ret } = await this.$http.get('relation')
      const { data: ret } = await this.$http({
        method: 'post',
        url: '/help/relation',
        params: {
          startTime: startTime,
          endTime: endTime
        }
      })
      this.allData = ret
      console.log("Relation allData:", this.allData)
      this.updateChart()
    },
    updateChart () {
      // 获取数据之后更新图表
      const legendArr = this.allData.categories
      const dataArr = this.allData.points.map(item => {
        return {
          id: item.id,
          name: item.name,
          symbolSize: item.symbolSize / 100,
          value: item.value,
          category: item.category
        }
      })
      const linksArr = this.allData.links
      const dataOption = {
        // 图例显得太拥挤了，如果图例少的话再加上
        // legend: [{
        //   data: legendArr
        // }],
        series: [{
          data: dataArr,
          links: linksArr,
          categories: legendArr
        }]
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
