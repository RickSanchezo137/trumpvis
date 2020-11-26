import Vue from 'vue'
import VueRouter from 'vue-router'
import SellerPage from '@/views/SellerPage'
import HomePage from '@/views/HomePage'
import CalendarPage from '@/views/CalendarPage'
import TrendPage from '@/views/TrendPage'
import ThemePage from '@/views/ThemePage'
import RelationPage from '@/views/RelationPage'
import WordCloudPage from '@/views/WordCloudPage'
import EmotionPage from '@/views/EmotionPage'
import ScreenPage from '@/views/ScreenPage'
import TestPage from '@/views/TestPage'

Vue.use(VueRouter)

const routes = [
  {
    path: '/sellerpage',
    component: SellerPage
  },
  {
    path: '/homepage',
    component: HomePage
  },
  {
    path: '/calendarpage',
    component: CalendarPage
  },
  {
    path: '/trendpage',
    component: TrendPage
  },
  {
    path: '/themepage',
    component: ThemePage
  },
  {
    path: '/relationpage',
    component: RelationPage
  },
  {
    path: '/wordcloudpage',
    component: WordCloudPage
  },
  {
    path: '/emotionpage',
    component: EmotionPage
  },
  {
    path: '/screenpage',
    component: ScreenPage
  },
  {
    path: '/testpage',
    component: TestPage
  }
]

const router = new VueRouter({
  routes
})

export default router
