import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '@/pages/LoginPage'
import RegisterPage from '@/pages/RegisterPage'
import MainPage from '@/pages/MainPage'
import SessionPage from '@/pages/SessionPage'
import AllTutorsPage from '@/pages/AllTutorsPage'
import AvailTemporaryPage from '@/pages/AvailTemporaryPage'
import TutorReviewsPage from '@/pages/TutorReviewsPage'
import CoursePage from '@/pages/CoursePage'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: "/",
      name: 'LoginPage',
      component: LoginPage
    },
    {
      path: "/registerpage",
      name: 'RegisterPage',
      component: RegisterPage
    },
    {
      path: "/mainpage",
      name: 'MainPage',
      component: MainPage
    },
    {
      path: "/sessionpage",
      name: 'SessionPage',
      component: SessionPage
    },
    {
      path: "/alltutorspage",
      name: 'AllTutorsPage',
      component: AllTutorsPage
    },
    {

      path: "/availtemporarypage",
      name: 'AvailTemporaryPage',
      component: AvailTemporaryPage
    },
    {
      path: "/coursepage",
      name: 'CoursePage',
      component: CoursePage
    },
    {
      path: "/tutorreviewspage",
      name: 'TutorReviewsPage',
      component: TutorReviewsPage
    }
  ]
})

