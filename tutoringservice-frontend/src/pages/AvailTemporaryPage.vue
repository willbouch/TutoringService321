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
#loginPage {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  position: absolute;
  top: 50%;
  left: 50%;
  -ms-transform: translateX(-50%) translateY(-50%);
  -webkit-transform: translate(-50%,-50%);
  transform: translate(-50%,-50%);
}
.tab {
  overflow: hidden;
  border: 1px solid #ccc;
  background-color: #f1f1f1;
}
/* Style the buttons that are used to open the tab content */
.tab button {
  background-color: inherit;
  float: left;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  transition: 0.3s;
}
/* Change background color of buttons on hover */
.tab button:hover {
  background-color: #ddd;
}
/* Create an active/current tablink class */
.tab button.active {
  background-color: #ccc;
}
/* Style the tab content */
.tabcontent {
  display: none;
  padding: 6px 12px;
  border: 1px solid #ccc;
  border-top: none;
}
/* Style inputs */
input[type=text] {
  width: 220px;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}
/* Style the submit button */
.glow-on-hover {
    width: 220px;
    height: 50px;
    border: none;
    outline: none;
    color: #fff;
    background: #111;
    cursor: pointer;
    position: relative;
    z-index: 0;
    border-radius: 10px;
	font-weight: bold;
}
.glow-on-hover:before {
    content: '';
    background: linear-gradient(45deg, #ff0000, #ff7300, #fffb00, #48ff00, #00ffd5, #002bff, #7a00ff, #ff00c8, #ff0000);
    position: absolute;
    top: -2px;
    left:-2px;
    background-size: 400%;
    z-index: -1;
    filter: blur(5px);
    width: calc(100% + 4px);
    height: calc(100% + 4px);
    animation: glowing 20s linear infinite;
    opacity: 0;
    transition: opacity .3s ease-in-out;
    border-radius: 10px;
}
.glow-on-hover:active {
    color: #000
}
.glow-on-hover:active:after {
    background: transparent;
}
.glow-on-hover:hover:before {
    opacity: 1;
}
.glow-on-hover:after {
    z-index: -1;
    content: '';
    position: absolute;
    width: 100%;
    height: 100%;
    background: #111;
    left: 0;
    top: 0;
    border-radius: 10px;
}
@keyframes glowing {
    0% { background-position: 0 0; }
    50% { background-position: 400% 0; }
    100% { background-position: 0 0; }
}
</style> 