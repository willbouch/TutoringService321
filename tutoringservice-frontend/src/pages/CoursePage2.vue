<template>
<div id ="show-courses" class="wrapper">
    &nbsp;&nbsp;&nbsp;
		<h1>COURSES</h1>
    &nbsp;
		<div class="tab">
        <button class="tablinks" v-on:click="toMainPage">Main Menu</button>
  			<button class="tablinks" v-on:click="toAvailabilityPage">Availabilities</button>
  			<button class="tablinks" v-on:click="toSessionPage">Sessions</button>
				<button class="tablinks" v-on:click="toCoursePage">Courses</button>
        <button class="tablinks" style="float:right" v-on:click="toLoginPage">Logout</button>
        <button class="tablinks" v-on:click="toTutorReviewsPage">Received Reviews</button>
        <button class="tablinks" v-on:click="toAllTutorsPage">All Tutors</button>
		</div>
		<br><br>

		<form class="form">
		<input type="text" v-model="search" placeholder="search by Subject" class="form-group-input"/>
    &nbsp;&nbsp;&nbsp;
		<div class="container">
			<table class="table table-light" align="center">
			<thead class="cuter">
				<tr>
				<th scope="col">Subject</th>
				<th scope="col">Course Description</th>
				<th scope="col">Course Code</th>
				<th scope="col">School</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for ="course in filteredCourses" :key="course" class = "cute">
				<td >{{course.Subject}}</td>
				<td >{{course.description}}</td>
				<td >{{course.coursecode}}</td>
				<td >{{course.school}}</td>
			</tr>
		</tbody>
		</table>
		</div>
		</form>
		<div>
		<input type="text" v-model="username" class="form-group-input" placeholder="Course Code">&nbsp;&nbsp;&nbsp;
		<button @click="loginTutor(username, password)" class="glow-on-hover">REQUEST COURSE</button>
	</div>
  &nbsp;&nbsp;&nbsp;
	</div>
</template>

<script>
import courses from '@/assets/courseData'
import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'https://' + config.build.host + ':' + config.build.port
var backendUrl = 'https://' + config.build.backendHost + ':' + config.build.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})
export default{
	data() {
		return {
			courses,
			search: ''
		}
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

	},

	computed:{
		filteredCourses: function(){
			return this.courses.filter((course) => {
				return course.Subject.match(this.search);
			});
		}
	}
}

</script>

<style scoped>
@import '../style/stylesheet.css';
@import url(https://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300);
@import '../style/blackandwhitebb.css';

#show-courses {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #F8F9F9;
  margin-top: 0px;
}

form {
  padding: 10px 0;
  position: relative;
  z-index: 2;
}
form input {
  -webkit-appearance: none;
     -moz-appearance: none;
          appearance: none;
  outline: 0;
  border: 1px solid rgba(255, 255, 255, 0.4);
  background-color: rgba(255, 255, 255, 0.2);
  width: 225px;
  border-radius: 3px;
  padding: 10px 15px;
  margin: 0 auto 10px auto;
  display: block;
  text-align: center;
  font-size: 18px;
  color: white;
  -webkit-transition-duration: 0.25s;
          transition-duration: 0.25s;
  font-weight: 300;
}
form input:hover {
  background-color: rgba(255, 255, 255, 0.4);
}
form input:focus {
  background-color: white;
  width: 300px;
  color: #283747;
}
.form-group-input {
    text-transform: uppercase;
}
.container{
  margin:center;
  height: 800px;
}
.wrapper {
  background: #151515;
 
}
.cute{
	text-transform: uppercase;
}
.cuter{
  background: #151515;
  text-transform: uppercase;
  color: #FDFEFE;
}

</style>