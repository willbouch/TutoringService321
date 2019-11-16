<template>

	<div id="viewTutors">
		<h1>TUTORS</h1>
		<div class="topPage">
			<button class="button" v-on:click="toMainPage">My Profile</button>
		</div>

		<table class="table" align="center">
			<thead class="thead-dark">
				<tr>
				<th scope="col">Tutor Name</th>
				<th scope="col">Contact</th>
				<th scope="col">Rating</th>
				<th scope="col">Hourly Rate</th>
				</tr>
			</thead>
			<tbody>

				<tr v-for="tutor in tutors" :key="tutor">
					<td>{{tutor.name}}</td>
					<td>{{tutor.email}}</td>
					<td>{{tutor.rating}}
						<img src="@/assets/rating-star.png" width=20>
					</td>
					<td>{{tutor.hourlyRate}}</td>
				</tr>
			</tbody>
		</table>
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

function TutorDto (name, email, rating, hourlyRate) {
	this.name = name
	this.email = email
	if(rating == -1) {
    this.rating = 'No rating yet'
  }
  else {
    this.rating = rating
  }
	this.hourlyRate = hourlyRate
}

export default {
  name: 'AllTutorsPage',

  data() {
    return {
      tutors: []
    }
	},
	
	created: function() {
		AXIOS.get(`/tutors`)
		.then(response => {
      for(var i = 0; i < response.data.length; i++) {
        this.tutors.push(new TutorDto(response.data[i].name,response.data[i].email,response.data[i].rating,response.data[i].hourlyrate))
      }
		})
		.catch(e => {
      
    })
	},

  methods: {
	  toMainPage() {
		  this.$router.push('MainPage')
	  }
  }
}
</script>

<style>
#mainPage {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.topPage {
  margin-left: 90%
}

.topPage button {
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

.topPage .avatar {
  vertical-align: middle;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  top: 0px;
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
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

/* Dropdown Button */
.dropbtn {
  background-color: #20B2AA	;
  color: black;
  padding: 12px;
  font-size: 16px;
  border: none;
}

/* The container <div> - needed to position the dropdown content */
.dropdown {
  position: relative;
  display: inline-block;
}

/* Dropdown Content (Hidden by Default) */
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 100%;
  overflow:auto;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

/* Links inside the dropdown */
.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {background-color: #ddd;}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {display: block;}

/* Change the background color of the dropdown button when the dropdown content is shown */
.dropdown:hover .dropbtn {background-color: #48D1CC;}

</style>