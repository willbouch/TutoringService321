<template>
  <div>
	  <h1>SESSIONS</h1>
		<div class="tab">
  			<button class="tablinks" onclick="openCity(event, 'Paris')">Availabilities</button>
  			<button class="tablinks" onclick="openCity(event, 'Tokyo')">Sessions</button>
			  <button class="tablinks" onclick="openCity(event, 'Tokyo')">Courses</button>
        <button class="tablinks" onclick="openCity(event, 'Tokyo')">All Tutors</button>
		</div>
	  
		<table class="table" align="center">
			<thead class="thead-dark">
				<tr>
				<th scope="col">Student</th>
				<th scope="col">Time</th>
				<th scope="col">Date</th>
				<th scope="col">Room</th>
				<th scope="col">Email</th>
				<th scope="col">Course</th>
				<th scope="col"></th>
				<th scope="col"></th>
				<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<tr v-bind:class="{approved : Session.variant}" v-for="Session in Sessions" :key="Session">
				
					<td >{{Session.startTime}}</td>
					<td >{{Session.endTime}}</td>
					<td >{{Session.date}}</td>
		
					<td><button  @click="approvedClass(Session)" class="btn btn-success">Approve</button></td>
					<td><button  @click="declineClass(Session)" class="btn btn-danger" >Decline</button></td>
				</tr>
			</tbody>
		</table>
	 
  </div>
</template>

<script>
//import Sessions from '@/data/Sessions'
import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})
export default {
	//name: SessionPage,
	
  //data : function()  {
	 // return{
		//	Sessions,
		//	_rowVarient: this.variant,
		//	approved: null
	  //}
	//},

	data() {
    return {
      tutorname: '',
			starTime: '',
			endTime: '',
			Sessions:[]
    }
	},
	created : function() {
		AXIOS.get(`/user`)
		.then(response => {
			AXIOS.post(`/sessions/`+response.data.email+`/?date=2020-05-05&startTime=13:00&endTime=14:00`)
			.then(response =>{
				this.Sessions=response.data
			})
			.catch(e => {
      })
		})
		.catch(e => {
    })
	},
	
	computed: {
  	isEnabled: function(){
    	return !this.approved;
		},
     cptItems(){
        return this.Sessions.map((Sessions)=>{
							 let tmp=Sessions;
							 _rowVarient:{
								Sessions.Session==this.approved==true?tmp.variant=null:tmp.variant='success';
							 }
                return tmp;

        })  
        }
	},

	methods: {

		ApproveSession: function(tutor, date, startTime, endTime){
      AXIOS.post(`/login/`+username+`?password=`+password,{},{})
      .then(response => {
      })
      .catch(e => {
        
      });  
    },
  	declineClass(Session) {
			for(var i = 0; i < this.Sessions.length; i++){
				if(Sessions[i].student == Session.student){
					Sessions.splice(i,1)
				}
			}
		},
		
		approvedClass(Session) {
			for(var i = 0; i < this.Sessions.length; i++){
				if(Sessions[i].student == Session.student){
				this.Session=true 
		   console.log(Session);
		    }
	    }
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