<template>
  <div id="sessionPage" class="wrapper">
		&nbsp;&nbsp;&nbsp;
	  <h1>SESSIONS</h1>
		&nbsp;
		
		<div class="tab">
  			<button class="tablinks" @click="toAvailabilityPage">Availabilities</button>
  			<button class="tablinks" @click="toSessionPage">Sessions</button>
			<button class="tablinks" @click="toCoursePage">Courses</button>
        	<button class="tablinks" style="float:right" v-on:click="toLoginPage">Logout</button>
        	<button class="tablinks" @click="toTutorReviewsPage">Received Reviews</button>
        	<button class="tablinks" @click="toAllTutorsPage">All Tutors</button>
  			<button class="tablinks" @click="toMainPage">Main Menu</button>		
		</div>
		&nbsp;&nbsp;&nbsp;
		
		<form class="form">
	  <div class="container">
		<table class="table" align="center">
			<thead class="cuter">
				<tr>
				<th scope="col">Date</th>
				<th scope="col">Start Time</th>
				<th scope="col">End Time</th>
			  <th scope="col">Approved</th>
				<th scope="col"></th>
				<th scope="col"></th>
				<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<tr :style="{ background: session.isApproved == true ? '#ABEBC6' : 'white' }" v-for="session in sessions" :key="session" >
					<td >{{session.date}}</td>
					<td >{{session.startTime}}</td>
					<td >{{session.endTime}}</td>
					<td>{{session.isApproved}}</td>
					<td><button  :disabled="session.isApproved == true" @click="ApproveSession(session.date, session.startTime, session.endTime)" class="btn btn-success">Approve</button></td>
					<td><button  @click="CancelSession(session.date, session.startTime, session.endTime)" class="btn btn-danger" >Decline</button></td>
					<td><button  @click="WriteReview(session.date, session.startTime, session.endTime)" class="btn btn-warning" >Write Review</button></td>
				</tr>
			</tbody>
		</table>
	 </div>
	 </form>
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
	//name: SessionPage,

	data() {
    return {
			email:'',
			sessions:[]
    }
	},
	created : function() {
		AXIOS.get(`/user`)
		.then(response => {
			var email = response.data.email
			AXIOS.post(`/sessions/`+email+`/?date=2020-05-05&startTime=13:00&endTime=14:00`)
			.then(response => {
				AXIOS.post(`/sessions/`+email+`/?date=2020-07-05&startTime=15:00&endTime=16:00`)
				.then(response => {
					this.sessions.push(response.data)
				})
				.catch(e => {
					window.alert(e.response.data.message)
				})
				this.sessions.push(response.data)
			})
			.catch(e => {
				window.alert(e.response.data.message)
			})

			this.email=response.data.email
		})

		.catch(e => {
			var errorMsg = e.response.data.message
      window.alert(errorMsg)
		})
	},

	methods: {
		toMainPage(){
      this.$router.push('MainPage')
	},
	toSessionPage(){
      this.$router.push('SessionPage')
    },
	  toAllTutorsPage() {
		  this.$router.push('AllTutorsPage')
    },

    toAvailabilityPage() {
      this.$router.push('AvailTemporaryPage')
    },

    toTutorReviewsPage() {
		  this.$router.push('TutorReviewsPage')
    },

    toCoursePage() {
		  this.$router.push('CoursePage2')
    },

    toLoginPage() {
      AXIOS.put(`/logout`)
		  .then(response => {
        this.$router.push({ path: '/' })
		  })
		  .catch(e => {
        var errorMsg = e.response.data.message
        window.alert(errorMsg)
      })
    },

		ApproveSession: function(date, startTime, endTime){
      AXIOS.put(`/sessions/`+this.email+`?requestedDate=`+date+`&qStartTime=`+startTime.slice(0,5)+`&qEndTime=`+endTime.slice(0,5),{},{})
      .then(response => {
				AXIOS.get(`/sessions/`+this.email)
				.then(response => {
					this.sessions=response.data
			  })
			  .catch(e => {
					var errorMsg = e.response.data.message
        	window.alert(errorMsg)
        });  
      })
      .catch(e => {
        window.alert(e.response.data.message)
      });  
		},

		CancelSession: function(date, startTime, endTime){
      AXIOS.delete(`/sessions/`+this.email+`?date=`+date+`&startTime=`+startTime.slice(0,5)+`&endTime=`+endTime.slice(0,5),{},{})
      .then(response => {
				AXIOS.get(`/sessions/`+this.email)
				.then(response => {
					this.sessions=response.data
			  })
			  .catch(e => {
					var errorMsg = e.response.data.message
       	 	window.alert(errorMsg)
        }); 
       })
      .catch(e => {
        var errorMsg = e.response.data.message
        window.alert(errorMsg)
      });  
		},

		WriteReview: function(date, startTime, endTime) {
			var message = window.prompt()
			AXIOS.post(`/reviews/`+this.email+`?textualReview=`+message+`&date=`+date+`&startTime=`+startTime.slice(0,5)+`&endTime=`+endTime.slice(0,5),{},{})
      .then(response => {
			})
      .catch(e => {
        var errorMsg = e.response.data.message
        window.alert(errorMsg)
      });
		}
	}
}

</script>

<style scoped>
@import '../style/stylesheet.css';
@import '../style/blackandwhitebb.css';

#sessionPage {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #F2F3F4;
  margin-top: 0px;
}
.list-group-item:hover {
  background: #EEE;
  cursor: pointer;
}
.approved {
		background-color: #f2f2f2;
}

</style>