<template>
  <div>
	  <h1>SESSIONS</h1>
		<div class="tab">
  			<button class="tablinks" v-on:click="toMainPage">Main Menu</button>			
		</div>
	  
		<table class="table" align="center">
			<thead class="thead-dark">
				<tr>
				<th scope="col">Date</th>
				<th scope="col">Start Time</th>
				<th scope="col">End Time</th>
			  <th scope="col">Approved</th>
				<th scope="col"></th>
				<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="session in sessions" :key="session">
					<td >{{session.date}}</td>
					<td >{{session.startTime}}</td>
					<td >{{session.endTime}}</td>
					
					<td>{{session.isApproved}}</td>
					<td><button  @click="ApproveSession(session.date, session.startTime, session.endTime)" class="btn btn-success">Approve</button></td>
					<td><button  @click="CancelSession(session.date, session.startTime, session.endTime)" class="btn btn-danger" >Decline</button></td>
					<td><button  @click="WriteReview(session.date, session.startTime, session.endTime)" class="btn btn-warning" >Write Review</button></td>
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
			AXIOS.post(`/sessions/`+response.data.email+`/?date=2020-05-05&startTime=13:00&endTime=14:00`)
			.then(response => {
				this.sessions.push(response.data)
				console.log(this.sessions)
			})
			.catch(e => {
				console.log(e.response.data.message)
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
      this.$router.go(-1)
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
				window.alert(response.data)
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
	.items{
		background-color: lightgreen;
	}
	.selected {
  background-color: lightblue;
}
.list-group-item:hover {
  background: #EEE;
  cursor: pointer;
}
	.approved {
		background-color: #f2f2f2;
}
	tr.approved td{
		background-color: lightgreen;
	}

  .tab {
  overflow: hidden;
  border: 1px solid #ccc;
  background-color: #f1f1f1;
	
}
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
</style>