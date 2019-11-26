<template>
	<div id="AvailabilitiesPage">
			<h1 id="header">Availabilities</h1>

			<div class="tab">
				<button class="tablinks" v-on:click="toMainPage">Main Menu</button>
  				<button class="tablinks" v-on:click="toAvailabilityPage">Availabilities</button>
  				<button class="tablinks" v-on:click="toSessionPage">Sessions</button>
				<button class="tablinks" v-on:click="toCoursePage">Courses</button>
        		<button class="tablinks" style="float:right" v-on:click="toLoginPage">Logout</button>
        		<button class="tablinks" v-on:click="toTutorReviewsPage">Received Reviews</button>
        		<button class="tablinks" v-on:click="toAllTutorsPage">All Tutors</button>
  			</div>
	<div id="next-previous" style="float: right;">
			<button @click="decrease()">Last Week</button>
			<button @click="increase()">Next Week</button>
	</div>
	<div id="weekly-availabilities"></div>
			<table id="schedule" style="width:100%">
				<tr>
					<th></th>
					<th v-for="day in days" :key="day"> {{monthSwitch(day.getMonth())}} {{day.getDate()}}</th> 
				</tr>
				<tr id="9am">
					<td align="right"> 9:00 am </td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
				</tr>
				<tr id="10am">
					<td align="right"> 10:00 am </td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
				</tr>
				<tr id="11am">
					<td align="right"> 11:00 am </td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
				</tr>
				<tr id="12pm">
					<td align="right"> 12:00 pm </td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
				</tr>
				<tr id="1pm">
					<td align="right"> 1:00 pm </td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
				</tr>
				<tr id="2pm">
					<td align="right"> 2:00 pm </td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
				</tr>
				<tr id="3pm">
					<td align="right"> 3:00 pm </td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
				</tr>
				<tr id="4pm">
					<td align="right"> 4:00 pm </td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
				</tr>
				<tr id="5pm">
					<td align="right"> 5:00 pm </td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
				</tr>
				<tr id="6pm">
					<td align="right"> 6:00 pm </td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
				</tr>
				<tr id="7pm">
					<td align="right"> 7:00 pm </td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
				</tr>
				<tr id="8pm">
					<td align="right"> 8:00 pm </td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
					<td class='notavailable' v-on:click="toggle"></td>
				</tr>
				</table>
				<button class='button' v-on:click="setAvailability"> Set Availability </button>
				<button class='button' v-on:click="goToMainPage"> Back to Profile </button>
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

	function availabilityDto (date, startTime, endTime) {
		this.date = date
		this.startTime = startTime
		this.endTime = endTime
	}

	export default {
		name: 'AvailabilitiesPage', 
		
	data() {
		 return {
			 availabilities: [],
			 days: [],
			 n : 0,
			 email: ''
		 	}
		},
		
		 created: function() {
			this.n=0;
			this.setDays();

			AXIOS.get(`/user/`)
				.then(response => {
      				this.email = response.data.email
			})
			.catch(e => {
			var errorMsg = e.response.data.message
			window.alert(errorMsg)
			})

			console.log(this.email);

			this.getAvailability(this.email);

			//this.availabilities = AXIOS.get('/availabilities/{tutorEmail}/');
		 },
		
		mounted: function(){
			var a;
			for(var i=0; i < this.availabilities.length; i++){
				a=this.availabilities[i];
				this.displayAvailability(a);
			}
		},
		
		methods: {
			addAvailability(email, date, startTime, endTime){
				AXIOS.post(`/availabilities/`+email+`?date=`+date+`&startTime=`+startTime+`&endTime=`+endTime)
				.then(response => {
					//this.availabilities=response.data;
				})
				.catch(e => {
					var errorMsg = e.response.data.message
        			window.alert(errorMsg)
				})
			},

			getAvailability(email){
				AXIOS.get(`/availabilities/`+email)
				.then(response =>{
					this.availabilities=response.data
				})
				.catch(e => {
				var errorMsg = e.response.data.message
        		window.alert(errorMsg)
				})	
			},

			deleteAvailability(email, date, startTime, endTime){
				AXIOS.delete(`/availabilities/`+email+`?date=`+date+`&startTime=`+startTime+`&endTime=`+endTime)
				.then(response => {
					//this.availabilities=response.data;
				})
				.catch(e => {
					var errorMsg = e.response.data.message
        			window.alert(errorMsg)
				})
			},

			setDays(){
				var today=new Date();
				//today.setDate(today.getDate()+this.n)

				var nextday0=new Date();
				nextday0.setDate(today.getDate()+this.n)

				var nextday1=new Date();
				nextday1.setDate(today.getDate()+this.n+1);

				var nextday2=new Date();
				nextday2.setDate(today.getDate()+this.n+2);

				var nextday3=new Date();
				nextday3.setDate(today.getDate()+this.n+3);

				var nextday4=new Date();
				nextday4.setDate(today.getDate()+this.n+4);

				var nextday5=new Date();
				nextday5.setDate(today.getDate()+this.n+5);

				var nextday6=new Date();
				nextday6.setDate(today.getDate()+this.n+6);;

				this.days=[nextday0, nextday1, nextday2, nextday3, nextday4, nextday5, nextday6]
				
			},

			monthSwitch(a){
				var month;
				switch(a){
					case 0:
						month="Jan";
						break;
					case 1: 
						month="Feb";
						break;
					case 2:
						month="March";
						break;
					case 3:
						month="April";
						break;
					case 4:
						month="May";
						break;
					case 5:
						month="June";
						break;
					case 6: 
						month="July";
						break;
					case 7: 
						month="Aug";
						break;
					case 8: 
						month="Sept";
						break;
					case 9: 
						month="Oct";
						break;
					case 10: 
						month="Nov";
						break;
					case 11: 
						month="Dec";
						break;
				}
				return month;
			},

			monthSwitchInt(a){
				var month;
				switch(a){
					case 0:
						month=1;
						break;
					case 1: 
						month=2
						break;
					case 2:
						month=3;
						break;
					case 3:
						month=4;
						break;
					case 4:
						month=5;
						break;
					case 5:
						month=6;
						break;
					case 6: 
						month=7;
						break;
					case 7: 
						month=8;
						break;
					case 8: 
						month=9;
						break;
					case 9: 
						month=10;
						break;
					case 10: 
						month=11;
						break;
					case 11: 
						month=12;
						break;
				}
				return month;
			},

			displayAvailability(a){
			var col;
			var row;
			var td;
			var table;
				switch(a.date){
					case this.days[0]:
						col=1;
						break;
					case this.days[1]: 
						col=2;
						break;
					case this.days[2]:
						col=3;
						break;
					case this.days[3]:
						col=4;
						break;
					case this.days[4]:
						col=5;
						break;
					case this.days[5]:
						col=6;
						break;
					case this.days[6]: 
						col=7;
						break;
				}

				row=a.startTime-8;
				table=document.getElementById("schedule");
				td=table.rows[row].cells[col];
				td.className="available";
			},
			
			goToMainPage(){
				this.$router.push('MainPage')
			},

			decrease(){
				if (this.n>6){
					this.n=this.n-7;
				}
				this.setDays();
				var a;
				for(var i=0; i < this.availabilities.length; i++){
					a=this.availabilities[i];
					this.displayAvailability(a);
				}
			},

			increase(){
				this.n=this.n+7;
				this.setDays();
				var a;
				for(var i=0; i < this.availabilities.length; i++){
					a=this.availabilities[i];
					this.displayAvailability(a);
				}
			},
			toggle(event){
				var cell=event.target;			 
					if (cell.className == "available")
						cell.className = "notavailable";
					else if(cell.className == "notavailable")
						cell.className = "available";
			},

			dateToString(date){
				
				var string = date.getFullYear() + "-" + this.monthSwitchInt(date.getMonth()) + "-" + this.getDate();
				return string;	
				
			}, 
			
			setAvailability(){
				this.getAvailability(this.email);
				var a;
				for(var i=0; i < this.availabilities.length; i++){
					a=this.availabilities[i];
					//deleteAvailability(this.email, a.date.getTime(), a.startTime, a.endTime);
				}

				var i;
				var k;
				var date;
				var sTime;
				var eTime;
				var selections=new Array();

				for (i=1;i<13;i++){
					for(k=1; k<8; k++){
						if (document.getElementById("schedule").rows[i].cells[k].className=="available"){
						switch(k){
							case 1:
								date=this.days[0];
								break;
							case 2: 
								date=this.days[1];
								break;
							case 3:
								date=this.days[2];
								break;
							case 4:
								date=this.days[3];
								break;
							case 5:
								date=this.days[4];
								break;
							case 6:
								date=this.days[5];
								break;
							case 7: 
								date=this.days[6];
								break;
						}

						sTime=i+8;
						var startTime=sTime+":00";
						var endTime=sTime+":59";
						var sdate = this.dateToString(date);
						
						console.log(sdate);
						selections.push(new availabilityDto(sdate, startTime, endTime));
					}
				}
			}

			this.availabilities=selections;

			for(var i=0; i < this.availabilities.length; i++){
					a=this.availabilities[i];
					this.addAvailability(this.email, a.date, a.startTime, a.endTime);
					this.displayAvailability(a);
			}
		}
	},
}
</script>

<style scoped>

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

	/* Styling for Previous-Next Buttons */
	a {
		text-decoration: none;
		display: inline-block;
		padding: 6px 16px;
	}

	a:hover {
		background-color: #ddd;
		color: black;
	}

	.previous {
		background-color: #f1f1f1;
		color: black;
	}

	.next {
		background-color: black;
		color: white;
	}

	.round {
		border-radius: 50%;
	}
</style>