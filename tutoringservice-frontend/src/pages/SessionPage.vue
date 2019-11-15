<template>
  <div>
	  <h1>SESSIONS</h1>
	  
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
					<td><button type="button" class="btn btn-success">Approve</button></td>
					<td><button @click="deleteData(Session)" class="btn btn-danger" >Decline</button></td>
					<td><button @click="deleteData(Session)" class="btn btn-warning">Cancel</button></td>
				</tr>
			</tbody>
		</table>
	 
  </div>
</template>

<script>
import Sessions from '@/data/Sessions'
export default {
	
  data() {
	  return{
		  Sessions
	  }
	},
	selectAnswer(index) {
      this.selectedIndex = index
		},
		methods: {
    deleteData (Session) {
			for(var i = 0; i < this.Sessions.length; i++){
				if(Sessions[i].student == Session.student){
					Sessions.splice(i,1)
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
</style>