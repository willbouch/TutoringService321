<template>

	<div id="CoursePage">
		<h1>COURSES</h1>
		<div class="tab">
  			<button class="tablinks" onclick="openCity(event, 'Paris')">Availabilities</button>
  			<button class="tablinks" onclick="openCity(event, 'Tokyo')">Sessions</button>
			  <button class="tablinks" v-on:click="toCoursePage">Courses</button>
        <button class="tablinks" style="float:right" v-on:click="toLoginPage">Logout</button>
        <button class="tablinks" v-on:click="toTutorReviewsPage">Received Reviews</button>
        <button class="tablinks" v-on:click="toAllTutorsPage">All Tutors</button>
		</div>

	<div>
	<ul>
		<li v-for="subject in subjects" :key="subject">
			{{ subject.subjectName }}
			<ul>
			<li v-for="course in subject.courses" :key="course">
				{{ course.courseCode + " ----- " + course.school }}
			</li>
			</ul>
		</li>
	</ul>
	</div>

	<div>
		<input type="text" v-model="username" placeholder="Course Code">
		<button @click="loginTutor(username, password)" class="glow-on-hover">Request Course</button>
	</div>

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

function SubjectDto (subjectName, courses) {
	this.subjectName = subjectName
	this.courses = courses
}

function CourseDto (courseCode, description, school) {
	this.courseCode = courseCode
	this.description = description
	this.school = school
}

export default {
  name: 'CoursePage',

  	data() {
    	return {
      	subjects: []
    	}
  	},
	
	created: function() {
		// AXIOS.get(`/user`)
		// .then(response => {
		// 	  this.subjects = response.data.subjects
		// })
		// .catch(e => {
      
		// })
		const c1 = new CourseDto('ECSE321', 'Intro to software engineering', 'McGill')
		const c2 = new CourseDto('MATH240', 'Proofs that are fucked', 'McGill')
		const c3 = new CourseDto('ECSE223', 'Love Gunter', 'McGill')
		const c4 = new CourseDto('COMP250', 'Intro to not having assignments', 'McGill')
		const c5 = new CourseDto('MATH2340', 'Proofs that are fucked', 'Concordia')
		const javaCourses = [c1,c3,c4]
		const mathCourses = [c2,c5]

		const s1 = new SubjectDto('Java prog', javaCourses)
		const s2 = new SubjectDto('Math', mathCourses)
		
		this.subjects = [s1,s2]
	},

  methods: {
	  toAllTutorsPage() {
		  this.$router.push('AllTutorsPage')
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
        this.$router.push('LoginPage')
		  })
		  .catch(e => {
      })
    },
  }
}
</script>

<style>
#CoursePage {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

ul {
  text-align: left;
}

.tab {
  overflow: hidden;
  border: 1px solid #ccc;
  background-color: #f1f1f1;
}

/* Style the buttons that are used to open the tab content */
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

input[type=text] {
  width: 220px;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

/* Style the submit button */
.glow-on-hover {
    width: 220px;
    height: 50px;
    border: none;
    outline: none;
    color: #fff;
    background: #111;
    cursor: pointer;
    position: relative;
    z-index: 0;
    border-radius: 10px;
	font-weight: bold;
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
    color: #000
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
    background: #111;
    left: 0;
    top: 0;
    border-radius: 10px;
}

@keyframes glowing {
    0% { background-position: 0 0; }
    50% { background-position: 400% 0; }
    100% { background-position: 0 0; }
}
</style>