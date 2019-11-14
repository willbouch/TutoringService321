<template>
  <div id="AvailabilitiesPage">
	  	<h1>Availabilities</h1>
  <div id="weekly-availabilities"> Weekly Availability</div>
		<AvailabilityBox></AvailabilityBox>
	</div>
</template>

<script>
export default {
	name: 'AvailabilitiesPage',
	components:'AvailabilityBox', 
	methods: {
		goToMainPage(){
			this.$router.push('MainPage')
		}
	}
}
</script>

<style>
#AvailabilityPage {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
