<template>
    <div id="app">
        <router-view></router-view>
       <!-- <Review /> -->
        <ReceivedReviews />
    </div>
</template>

<script>
import Review from './components/Reviews'
import ReceivedReviews from './components/ReceivedReviews'
export default {
  name: 'app',
  components:{
    Review,ReceivedReviews
  }

}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #65a1dd;
  margin-top: 0px;
}
</style>
