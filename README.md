# trump
##做的修改

1：main.js中设置Vue.prototype.chartInstance = null，  

然后component里面的data()中去掉对chartInstance的初始化  

可以提高速度  

2：main.js里面设置axios.defaults.baseURL = '/api'  

vue.config.js中添加跨域访问  

3：修改了component里面视图的axios接口，改成自己后端的接口  

4：主视图添加animation：false取消动态效果，要不然跑起来太慢了（后面看看有没有什么其他解决方法）
新增：目前就home视图有这个问题，但已经改用trend了，哈哈  

5：日历视图在axios请求体中加入了年份参数（看看后续怎么实现点击切换）