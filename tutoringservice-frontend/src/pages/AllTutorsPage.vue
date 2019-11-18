<!-- This page represents all the tutors in the database. Included are their
     names, email, ratings (-1 if no ratings), hourly rate, the subjects they
     teach featured in a drop down menu, and the links to their individual
     profile pages. The links are empty and will only be used for the demo.
     Also to note is that they are not associated to any subjects because that
     is done in the Manager's perspective. There is room for dummy information
     for the demo if needed. -->

<template>

	<div id="viewTutors">
		<h1>TUTORS</h1>
		<div class="tab">
  		<button class="tablinks" v-on:click="toMainPage">Main Menu</button>			
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
					<td>{{tutor.hourlyrate}}</td>
          <td><div class="dropdown">
              <button class="dropbtn">Subjects</button>
              <div class="dropdown-content" v-for="subject in subjects" :key="subject">
                <a href="#">{{subject}}</a>
              </div>
          </div></td>
          <td><a href="#">{{tutor.name}}'s Profile</a></td>
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

function TutorDto (name, email, rating, hourlyRate, subjects) {
	this.name = name
  this.email = email
  this.subjects = subjects
	if(rating == -1) {
    this.rating = 'No rating yet'
  }
  else {
    this.rating = rating
  }
	this.hourlyrate = hourlyrate
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
      this.tutors = response.data
      for(var i = 0; i < this.tutors.length; i++){
        if (this.tutors[i].rating == -1) {
          this.tutors[i].rating = 'No rating yet'
        } 
      }
    })
    .catch(e => {

    })

	},

  methods: {
	  toMainPage() {
      this.$router.go(-1)
	  }
  }
}
</script>

<style>
#viewTutors {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
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