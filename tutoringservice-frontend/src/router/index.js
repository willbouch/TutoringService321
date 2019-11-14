import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '@/pages/LoginPage'
import MainPage from '@/pages/MainPage'
import AvailabilitiesPage from '@/pages/AvailabilitiesPage'
import AllTutorsPage from '@/pages/AllTutorsPage'

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
      path: "/availabilitiespage",
      name: 'AvailabilitiesPage',
      component: AvailabilitiesPage
    }, 
    {
      path: "/alltutorspage",
      name: 'AllTutorsPage',
      component: AllTutorsPage
    }

  ]
})
