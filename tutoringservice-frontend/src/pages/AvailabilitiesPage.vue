<template>
  <div id="AvailabilitiesPage">
	  	<h1>Availabilities</h1>
		  	<div id="weekly-schedule"></div>
				<Schedule> class="AvailabilityBox" </Schedule>
	</div>
</template>

<script>
export default {
	name: 'AvailabilitiesPage',

	methods: {
		goToMainPage(){
			this.$router.push('MainPage')
		}
	}
}
</script>

<style>
#loginPage {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

</style>
