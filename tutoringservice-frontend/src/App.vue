<template>
    <div id="app">
        <router-view></router-view>
        <ViewTutors />
    </div>
</template>

<script>
import ViewTutors from './components/ViewTutors'
export default {
  name: 'app',
  components:{
    ViewTutors
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
