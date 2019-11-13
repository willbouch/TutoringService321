import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '@/pages/LoginPage'
import MainPage from '@/pages/MainPage'
import SessionPage from '@/pages/SessionPage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: "/",
      name: 'LoginPage',
      component: LoginPage
    },
    {
      path: "/mainpage",
      name: 'MainPage',
      component: MainPage
    },
    {
      path: "/SessionPage",
      name: 'SessionPage',
      component: SessionPage
    }

  ]
})
