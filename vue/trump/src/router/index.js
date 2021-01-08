import Vue from 'vue'
import VueRouter from 'vue-router'
import LayoutPage from '@/views/LayoutPage'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/vis/layoutpage'
  },
  {
    path: '/vis/layoutpage',
    component: LayoutPage
  }
]

const router = new VueRouter({
  routes
})

export default router
