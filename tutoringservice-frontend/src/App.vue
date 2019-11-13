<template>
    <div id="app">
        <router-view></router-view>
        <Review />
    </div>
</template>

<script>
import Review from './components/Reviews'
export default {
  name: 'app',
  components:{
    Review
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
  margin-top: 60px;
}
</style>
