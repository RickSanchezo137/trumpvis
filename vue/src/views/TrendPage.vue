<template>
  <div class="com-page">
    <trend @getBeginAndEnd="getMsgFromTrend"></trend>
  </div>
</template>

<script>
import Trend from '@/components/Trend'
export default {
  data () {
    return {}
  },
  methods: {
      getMsgFromTrend(beginAndEndArr){
          // console.log(beginAndEndArr)
      }
  },
  components: {
    trend: Trend
  }
}
</script>
<style lang='less' scoped>

</style>
