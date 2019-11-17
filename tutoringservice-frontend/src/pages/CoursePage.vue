<template>

	<div id="CoursePage">
		<h1>COURSES</h1>
		<div class="tab">
  			<button class="tablinks" v-on:click="toMainPage">Main Menu</button>
		</div>
  

  
	<!-- <div class="container">
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
	</div> -->

  
<br><br>
  <div class="container">
  <ul>
    
    <!-- <li><a>Zurich</a></li>
    <li><a href="#">Geneva</a></li>
    <li><a href="#">Winterthur</a></li>
    <li><a href="#">Lausanne</a></li>
    <li><a href="#">Lucerne</a></li> -->
    	<li v-for="subject in subjects" :key="subject"><a><br>
			<b>{{ subject.subjectName }}</b>
			<ul>
			<li v-for="course in subject.courses" :key="course"><a>
				{{ course.courseCode + "  ⇋  " + course.school }}
       </a>
			</li>
			</ul>
     </a> 
     <br><br>
		</li>
  </ul>
  </div>

  
  
  <br><br>
	<div>
		<input type="text" v-model="username" placeholder="Course Code">
		<button @click="loginTutor(username, password)" class="glow-on-hover">Request Course</button>
	</div>
   
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
    const c5 = new CourseDto('COEN231', 'Proofs that are fucked', 'Concordia')
    const c6 = new CourseDto('MATH133', 'Linear algebra', 'McGill')
    const c7 = new CourseDto('COMP302', 'Ocaml is the best', 'McGill')
    const c8 = new CourseDto('ECON208', 'Ocaml is the best', 'McGill')
    const c9 = new CourseDto('ECON225', 'Ocaml is the best', 'McGill')
    const c10 = new CourseDto('ECON221', 'Ocaml is the best', 'Concordia')
    // const c5 = new CourseDto('MATH2340', 'Proofs that are fucked', 'Concordia')
		const progCourses = [c1,c3,c4,c7]
    const mathCourses = [c2,c5,c6]
    const artsCourses = [c8,c9,c10]

		const s1 = new SubjectDto('Programming', progCourses)
    const s2 = new SubjectDto('Math', mathCourses)
    const s3 = new SubjectDto('Arts', artsCourses)
		
		this.subjects = [s1,s2,s3]
	},

  methods: {
	  toMainPage(){
      this.$router.go(-1)
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
  margin-top: 0px;
}

ul {
  text-align: left;
}



 
h2 {
  font: 400 40px/1.5 Helvetica, Verdana, sans-serif;
  margin: 0;
  padding: 0;
}
 
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  width: 400px;
  text-align: center;
  align-self: center;
}
 
li {
  font: 200 20px/1.5 Helvetica, Verdana, sans-serif;
  border-bottom: 1px solid #ccc;
  
}
 
li:last-child {
  border: none;
}
 
li a {
  text-decoration: none;
  color: #000;
  display: block;
 
 
  -webkit-transition: font-size 0.3s ease, background-color 0.3s ease;
  -moz-transition: font-size 0.3s ease, background-color 0.3s ease;
  -o-transition: font-size 0.3s ease, background-color 0.3s ease;
  -ms-transition: font-size 0.3s ease, background-color 0.3s ease;
  transition: font-size 0.3s ease, background-color 0.3s ease;
}
.container{
  margin-left: 36%;
}
 
li a:hover {
  font-size: 30px;
  background: #f6f6f6;
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
  width: 500px;
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