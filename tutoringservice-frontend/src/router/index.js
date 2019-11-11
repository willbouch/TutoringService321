import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import TutoringService from '@/components/TutoringService'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/app',
      name: 'TutoringService',
      component: TutoringService
    }
  ]
})
