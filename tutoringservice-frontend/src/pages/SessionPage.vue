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
				<tr v-for="Session in Sessions" :key="Session">
					<td>{{Session.student}}</td>
					<td>{{Session.time}}</td>
					<td>{{Session.date}}</td>
					<td>{{Session.room}}</td>
					<td>{{Session.email}}</td>
					<td>{{Session.course}}</td>
					<td><button :class='isApproved' @click="approvedClass(Session)" class="btn btn-success">Approve</button></td>
					<td><button :disabled='isEnabled' @click="declinedClass(Session)" class="btn btn-danger" >Decline</button></td>
					
				</tr>
			</tbody>
		</table>
	 
  </div>
</template>

<script>
import Sessions from '@/data/Sessions'
export default {
	
  data : function()  {
	  return{
			Sessions,
			_rowVariant: 'danger'
	  }
	},
	computed: {
  	isDisabled: function(){
    	return !this.approved;
		},
		Enabled: function(){
			return this.approved;
		},
		isApproved: function(){
			return this._rowVarient
		}
		
	},
	methods: {
  	declineClass(Session) {
			for(var i = 0; i < this.Sessions.length; i++){
				if(Sessions[i].student == Session.student){
					Sessions.splice(i,1)
				}
			}
		},
		approvedClass(Session) {
			console.log(Session)
			return this._rowVariant
		
	},
		cancelClass(Session) {
			for(var i = 0; i < this.Sessions.length; i++){
				if(Sessions[i].student == Session.student){
					Sessions.splice(i,1)
				}
			}
		},
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
		background-color: lightyellow,
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