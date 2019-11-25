<template>
<div id ="show-courses" class="wrapper">
		<h1>ALL COURSES</h1>
		<div class="tab">
			<button class="tablinks" @click="toAvailabilityPage">Availabilities</button>
  			<button class="tablinks" @click="toSessionPage">Sessions</button>
			<button class="tablinks" @click="toCoursePage">Courses</button>
        	<button class="tablinks" style="float:right" v-on:click="toLoginPage">Logout</button>
        	<button class="tablinks" @click="toTutorReviewsPage">Received Reviews</button>
        	<button class="tablinks" @click="toAllTutorsPage">All Tutors</button>
  			<button class="tablinks" @click="toMainPage">Main Menu</button>
		</div>
		<br><br>

		<form class="form">
		<input type="text" v-model="search" placeholder="search by Subject" class="form-group-input"/>
		<div class="container">
			<table class="table table-light" align="center">
			<thead class="thead-dark">
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
		<div class="margins">
		<input type="text" v-model="username" class="form" placeholder="Course Code">
		<button @click="loginTutor(username, password)" class="glow-on-hover">REQUEST COURSE</button>
	</div>
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
		  this.$router.push('CoursePage')
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


#show-courses {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #FDFEFE;
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
.form-group-input {
    text-transform: uppercase;
}
.container{
  margin:center;
  
  
}
.wrapper {
  background: #151515;
 
}
.cute{
	
	text-transform: uppercase;
}
.glow-on-hover {
    padding: 10px 15px;
    width: 220px;
    height: 50px;
    border: none;
    outline: none;
    color: #000;
    background: #FDFEFE;
    cursor: pointer;
    position: relative;
    z-index: 0;
    border-radius: 10px;
	  font-weight: thin;
}
.glow-on-hover:before {
    content: '';
    background: linear-gradient(45deg, #ff0000, #ff7300, #fffb00, #48ff00, #00ffd5, #002bff, #7a00ff, #ff00c8, #ff0000);
    position: absolute;
    top: -2px;
    left:-2px;
    background-size: 400%;
    z-index: -1;
    filter: blur(5px);
    width: calc(100% + 4px);
    height: calc(100% + 4px);
    animation: glowing 20s linear infinite;
    opacity: 0;
    transition: opacity .3s ease-in-out;
    border-radius: 10px;
}
.glow-on-hover:active {
    color: #FDFEFE
}
.glow-on-hover:active:after {
    background: transparent;
}
.glow-on-hover:hover:before {
    opacity: 1;
}
.glow-on-hover:after {
    z-index: -1;
    content: '';
    position: absolute;
    width: 100%;
    height: 100%;
    background: #FDFEFE;
    left: 0;
    top: 0;
    border-radius: 10px;
}
@keyframes glowing {
    0% { background-position: 0 0; }
    50% { background-position: 400% 0; }
    100% { background-position: 0 0; }
}

.margins{
	padding-left:10px;
	padding-right:10px;
}


</style>