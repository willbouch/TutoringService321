<!-- This page represents all the tutors in the database. Included are their
     names, email, ratings (-1 if no ratings), hourly rate, the subjects they
     teach featured in a drop down menu, and the links to their individual
     profile pages. The links are empty and will only be used for the demo.
     Also to note is that they are not associated to any subjects because that
     is done in the Manager's perspective. There is room for dummy information
     for the demo if needed. -->

<template>

	<div id="viewTutors" class="wrapper">
    <img src="../assets/321tutor.png">
		<div class="tab">
  			<button class="tablinks" @click="toMainPage">Profile</button>	
        <button class="tablinks" @click="toCoursePage">Courses</button>
  			<button class="tablinks" @click="toAvailabilityPage">Availabilities</button>
  			<button class="tablinks" @click="toSessionPage">Sessions</button>
        <button class="tablinks" style="float:right" v-on:click="toLoginPage">Logout</button>
        <button class="tablinks" @click="toTutorReviewsPage">Received Reviews</button>
        <button class="tablinks" @click="toAllTutorsPage">All Tutors</button>	
	  </div>
    &nbsp;
    <h1>TUTORS</h1>
    &nbsp;
    <form class="form">
    <div class="container">
		<table class="table table-light" align="center">
			<thead class="cuter">
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
  }
}
</script>

<style>
@import '../style/stylesheet.css';
@import '../style/blackandwhitebb.css';
#viewTutors {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #F8F9F9;
  margin-top: 0px;
}

</style>