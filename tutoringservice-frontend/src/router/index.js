import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '@/pages/LoginPage'
import RegisterPage from '@/pages/RegisterPage'
import MainPage from '@/pages/MainPage'
import SessionPage from '@/pages/SessionPage'
import AllTutorsPage from '@/pages/AllTutorsPage'
import AvailTemporaryPage from '@/pages/AvailTemporaryPage'
import AvailabilitiesPage from '@/pages/AvailabilitiesPage'
import TutorReviewsPage from '@/pages/TutorReviewsPage'
import CoursePage2 from '@/pages/CoursePage2'
import Welcome from '@/pages/Welcome'
import StudentPage from '@/pages/StudentPage'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: "/",
      name: 'Welcome',
      component: Welcome
    },
    {
      path: "/studentpage",
      name: 'StudentPage',
      component: StudentPage
    },
    {
      path: "/loginpage",
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
      path: "/coursepage2",
      name: 'CoursePage2',
      component: CoursePage2
    },
    {
      path: "/tutorreviewspage",
      name: 'TutorReviewsPage',
      component: TutorReviewsPage
    }
  ]
})

