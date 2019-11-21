<template>
	<div id="mainPage">
		<h1>TUTOR PROFILE</h1>
		<div class="tab">
  			<button class="tablinks" v-on:click="toAvailabilityPage">Availabilities</button>
  			<button class="tablinks" v-on:click="toSessionPage">Sessions</button>
			  <button class="tablinks" v-on:click="toCoursePage">Courses</button>
        <button class="tablinks" style="float:right" v-on:click="toLoginPage">Logout</button>
        <button class="tablinks" v-on:click="toTutorReviewsPage">Received Reviews</button>
        <button class="tablinks" v-on:click="toAllTutorsPage">All Tutors</button>
		</div>

		<div>
			<img src="@/assets/profile-picture.png" width=200>
  		<label> {{ email }} </label>
			<label>{{ rating }}</label>
		  <div>
        <input type="text" v-model="name" placeholder="CURRENT NAME">
		  </div>
      <div>
			  <input type="text" v-model="phoneNumber" placeholder="CURRENT PHONE NUMBER">
      </div>
      <div>
 			  <input type="text" v-model="hourlyRate" placeholder="CURRENT HOURLY RATE">
      </div>
      <div>
  		  <button class="glow-on-hover" v-on:click="updateProfile">Update Profile</button>
      </div>
      <div>
			  <input type="text" v-model="oldPassword" placeholder="Old Password">
      </div>
      <div>
			  <input type="text" v-model="newPassword" placeholder="New Password">
      </div>
      <div>
			  <button class="glow-on-hover" v-on:click="changePassword">Change Password</button>
      </div>

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

export default {
  name: 'MainPage',

  data() {
	  return {
      email:'',
      rating:'',
      name:'',
      phoneNumber:'',
      hourlyRate:'',
      oldPassword:'',
      newPassword:''
    }
  },

  created: function() {
		AXIOS.get(`/user`)
		.then(response => {
      this.email = response.data.email
      
      if(response.data.rating == -1) {
        this.rating = 'No rating yet'
      }
      else {
        this.rating = response.data.rating
      }
      
      this.name = response.data.name
      this.phoneNumber = response.data.phoneNumber
      this.hourlyRate = response.data.hourlyrate
      this.oldPassword = response.data.password
		})
		.catch(e => {
      var errorMsg = e.response.data.message
      window.alert(errorMsg)
    })
	},

  methods: {
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

    updateProfile() {
      AXIOS.put(`/tutors/profile/`+this.email+`?name=`+this.name+`&phoneNumber=`+this.phoneNumber+`&hourlyRate=`+this.hourlyRate)
		  .then(response => {
        this.phoneNumber = response.data.phoneNumber
        this.name = response.data.name
        this.hourlyRate = response.data.hourlyrate
		  })
		  .catch(e => {
        var errorMsg = e.response.data.message
        window.alert(errorMsg)
      })
    },

    changePassword() {
      AXIOS.put(`/tutors/password/`+this.email+`?oldPassword=`+this.oldPassword+`&newPassword=`+this.newPassword)
		  .then(response => {
        this.oldPassword = response.data.password
        this.newPassword = ''
		  })
		  .catch(e => {
        var errorMsg = e.response.data.message
        window.alert(errorMsg)
      })
    }
  }
}
</script>

<style>
@import '../style/stylesheet.css';
#mainPage {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
}

img {
  margin: 8px 0;
}

label {
  font-weight: bold;
  width: 100%;
  margin: 8px 0;
  display: inline-block;
}

</style>