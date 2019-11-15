<template>

	<div id="CoursePage">
		<h1>COURSES</h1>
		<div class="topPage">
			<button class="button" v-on:click="toMainPage">My Profile</button>
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
	  toMainPage() {
		  this.$router.push('MainPage')
	  }
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
</style>