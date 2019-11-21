<template>
  	<div id="availTemporaryPage">
  	<h1>AVAILABILITIES</h1>
		<div class="tab">
  			<button	class="tablinks" v-on:click="toMainPage">Main Menu</button>			
		</div>

		<div>
    		<input type="text" v-model="date" placeholder="Date (YYYY-MM-DD)">
			<input type="text" v-model="startTime" placeholder="Start Time (HH:MM)">
			<input type="text" v-model="endTime" placeholder="End Time (HH:MM)">
		</div>
		<div>
			<button class="glow-on-hover" v-on:click="addAvailability">Add Availability</button>
		</div>
		<h1></h1>
		<table class="table" align="center">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Start Time</th>
					<th scope="col">End Time</th>
					<th scope="col"></th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="availability in availabilities" :key="availability">
					<td >{{availability.date}}</td>
					<td >{{availability.startTime}}</td>
					<td >{{availability.endTime}}</td>
					<td><button @click="deleteAvailability(availability)" class="btn btn-danger" >Delete</button></td>
					<td><button @click="updateAvailability(availability)" class="btn btn-warning" >Update</button></td>
				</tr>
			</tbody>
		</table>
  </div>

</template>

<script>
import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'https://' + config.build.host + ':' + config.build.port
var backendUrl = 'https://' + config.build.backendHost + ':' + config.build.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  name: 'AvailTemporaryPage',

  	data() {
	  	return {
			startTime: '',
			endTime: '',
			date: '',
			email: '',
			availabilities: []
    	}
  	},

  	created: function() {
		AXIOS.get(`/user`)
		.then(response => {
				this.email = response.data.email
			  	AXIOS.get(`/availabilities/`+this.email, {}, {})
				.then(response => {
					this.availabilities = response.data
				})
				.catch(e => {
      				var errorMsg = e.response.data.message
      				window.alert(errorMsg)
    			})
		})
		.catch(e => {
      		var errorMsg = e.response.data.message
      		window.alert(errorMsg)
    	})
	},

  	methods: {
    	toMainPage() {
      		this.$router.go(-1)
		},

		addAvailability() {
			AXIOS.post(`/availabilities/`+this.email+`?date=`+this.date+`&startTime=`+this.startTime+`&endTime=`+this.endTime, {}, {})
			.then(response => {
				AXIOS.get(`/availabilities/`+this.email, {}, {})
				.then(response => {
					this.availabilities = response.data
				})
				.catch(e => {
      				var errorMsg = e.response.data.message
      				window.alert(errorMsg)
    			})
			})
			.catch(e => {
      			var errorMsg = e.response.data.message
      			window.alert(errorMsg)
    		})
		},

		deleteAvailability(avail) {
			AXIOS.delete(`/availabilities/`+this.email+`?date=`+avail.date+`&startTime=`+avail.startTime.slice(0,5)+`&endTime=`+avail.endTime.slice(0,5), {}, {})
			.then(response => {
				AXIOS.get(`/availabilities/`+this.email, {}, {})
				.then(response => {
					this.availabilities = response.data
				})
				.catch(e => {
      				var errorMsg = e.response.data.message
      				window.alert(errorMsg)
    			})
			})
			.catch(e => {
      			var errorMsg = e.response.data.message
      			window.alert(errorMsg)
    		})
		},

		updateAvailability(avail) {
			var date = window.prompt('Date (YYYY-MM-DD)')
			var startTime = window.prompt('Start Time (HH:MM)')
			var endTime = window.prompt('End Time (HH:MM)')

			AXIOS.put(`/availabilities/`+this.email+`?oldDate=`+avail.date+`&oldStartTime=`+avail.startTime.slice(0,5)+`&oldEndTime=`+avail.endTime.slice(0,5)+`&newDate=`+date+`&newStartTime=`+startTime+`&newEndTime=`+endTime, {}, {})
			.then(response => {
				AXIOS.get(`/availabilities/`+this.email, {}, {})
				.then(response => {
					this.availabilities = response.data
				})
				.catch(e => {
      				var errorMsg = e.response.data.message
      				window.alert(errorMsg)
    			})
			})
			.catch(e => {
      			var errorMsg = e.response.data.message
      			window.alert(errorMsg)
    		})
		}
		}
}
</script>

<style>
@import '../style/stylesheet.css';
#availTemporaryPage {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
}
</style>
