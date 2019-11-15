import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '@/pages/LoginPage'
import RegisterPage from '@/pages/RegisterPage'
import MainPage from '@/pages/MainPage'
import AllTutorsPage from '@/pages/AllTutorsPage'
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
      path: "/alltutorspage",
      name: 'AllTutorsPage',
      component: AllTutorsPage
    },
    {
      path: "/loginpage",
      name: 'LoginPage',
      component: LoginPage
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
