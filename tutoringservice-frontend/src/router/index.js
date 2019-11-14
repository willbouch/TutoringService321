import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '@/pages/LoginPage'
import MainPage from '@/pages/MainPage'
import AllTutorsPage from '@/pages/AllTutorsPage'
import AvailabilitiesPage from '@/pages/AvailabilitiesPage'

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
      path: "/alltutorspage",
      name: 'AllTutorsPage',
      component: AllTutorsPage
    },

    {
      path: "/availabilitiespage",
      name: 'AvailabilitiesPage',
      component: AvailabilitiesPage
    }
  ]
})
