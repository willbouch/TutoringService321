<template>
  <div id="AvailabilitiesPage">
	  	<h1 id="header">Availabilities</h1>
		  
  <div id="weekly-availabilities"></div>
		<table id="schedule" style="width:80%">
			<col id="time" width="80">
			<col width="140" id="monday">
			<col width="140" id="tuesday">
			<col width="140" id="wednesday">
			<col width="140" id="thursday">
			<col width="140" id="friday">
			<col width="140" id="saturday">
			<col width="140" id="sunday">

			<tr id="days">
				<th> </th>
				<th> Monday </th>
				<th> Tuesday </th>
				<th> Wednesday </th>
				<th> Thursday </th>
				<th> Friday </th>
				<th> Saturday </th>
				<th> Sunday </th>
			</tr>
			<tr id="9am">
				<th align="right"> 9:00 am </th>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
			</tr>
			<tr id="10am">
				<th align="right"> 10:00 am </th>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
			</tr>
			<tr id="11am">
				<th align="right"> 11:00 am </th>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
			</tr>
			<tr id="12pm">
				<th align="right"> 12:00 pm </th>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
			</tr>
			<tr id="1pm">
				<th align="right"> 1:00 pm </th>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
			</tr>
			<tr id="2pm">
				<th align="right"> 2:00 pm </th>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
			</tr>
			<tr id="3pm">
				<th align="right"> 3:00 pm </th>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
			</tr>
			<tr id="4pm">
				<th align="right"> 4:00 pm </th>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
			</tr>
			<tr id="5pm">
				<th align="right"> 5:00 pm </th>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
			</tr>
			<tr id="6pm">
				<th align="right"> 6:00 pm </th>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
			</tr>
			<tr id="7pm">
				<th align="right"> 7:00 pm </th>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
			</tr>
			<tr id="8pm">
				<th align="right"> 8:00 pm </th>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
				<td class='notavailable' v-on:click="toggle"></td>
			</tr>
			</table>
			<button class="button" v-on:click=setAvailability> Set Availability </button>
			<button class="button" v-on:click="goToMainPage"> Back to Profile </button>
	</div>
</template>

<script>
import axios from 'axios'
var config = require('../../config')
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function availabilityDto (day, startTime, endTime) {
	this.day = day
	this.startTime = startTime
	this.endTime = endTime

	}

export default {
	name: 'AvailabilitiesPage', 
	  
 data() {
    return {
      availabilities: []
		}
	},
	
	created: function() {
		const a1 = new availabilityDto("Monday", 10, 11);
		const a2 = new availabilityDto("Friday", 10, 11);
		const a3 = new availabilityDto("Thursday", 16, 17);

		this.availabilities = [a1,a2,a3,4];
	},
	
	mounted: function(){
			var a;
			//console.log(this.availabilities)
			for(var i=0; i < this.availabilities.length; i++){
				a=this.availabilities[i];
				this.displayAvailability(a);
			}
	},
	
	methods: {
		displayAvailability(a){
		var col;
		var row;
		var td;
		var table;
			switch(a.day){
				case "Monday":
					col=1;
					break;
				case "Tuesday": 
					col=2;
					break;
				case "Wednesday":
					col=3;
					break;
				case "Thursday":
					col=4;
					break;
				case "Friday":
					col=5;
					break;
				case "Saturday":
					col=6;
					break;
				case "Sunday": 
					col=7;
					break;
			}
			row=a.sTime-8;
			table=document.getElementById("schedule");
			td=table.rows[row].cells[col];
			td.className="available";
		}
		},
		
		goToMainPage(){
			this.$router.push('MainPage')
		},

 		toggle(event){
			var cell=event.target;			 
				if (cell.className == "available")
					cell.className = "notavailable";
				else if(cell.className == "notavailable")
					cell.className = "available";
		},

		setAvailability(){
			var i;
			var j;
			var day;
			var sTime;
			var eTime;
			for (i=1;i<13;i++){
				availabilities = this.getElementById("schedule").row[i].getElementsByClassName("available");
				for (j=0; j<availabilities.length; j++){
				
				switch(availabilities[i].cellIndex){
					case 1:
						day="Monday";
						break;
					case 2: 
						day="Tuesday";
						break;
					case 3:
						day="Wednesday"
						break;
					case 4:
						day="Thursday";
						break;
					case 5:
						day="Friday";
						break;
					case 6:
						day="Saturday"
						break;
					case 7: 
						day="Sunday";
						break;
				}
				sTime=i+8;
				eTime=i+9;
				//some function to add each availability to the database
				availabilityDto(day, sTime, eTime);
				}
			}
		},


	}
</script>

<style>

#AvailabilitiesPage {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}


/* Table */
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: center;
	height: 10px;
  padding: 10px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

button {
  background-color: #ddd;
  border: none;
  color: black;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  margin: 6px 4px;
  cursor: pointer;
  border-radius: 16px;
  top: 0px;
}

.available{
	background-color: greenyellow;
}

.notavailable{
	background-color: #ff6961;
}

</style>