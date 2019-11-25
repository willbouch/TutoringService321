<template>
	<div id="mainPage" class="wrapper">
    &nbsp;&nbsp;&nbsp;
		<h1>PROFILE</h1>
    &nbsp;
		<div class="tab">
  			<button class="tablinks" v-on:click="toAvailabilityPage">Availabilities</button>
  			<button class="tablinks" v-on:click="toSessionPage">Sessions</button>
			  <button class="tablinks" v-on:click="toCoursePage">Courses</button>
        <button class="tablinks" style="float:right" v-on:click="toLoginPage">Logout</button>
        <button class="tablinks" v-on:click="toTutorReviewsPage">Received Reviews</button>
        <button class="tablinks" v-on:click="toAllTutorsPage">All Tutors</button>
		</div>
    &nbsp;&nbsp;&nbsp;
    <form class="form">
	  <div class="container">
    <h1>{{ name }}</h1>
    <h3>Email address : {{ email }}</h3>
    <h3>Your current rating is : {{ rating }}</h3>

    <div id="container">
      <div id="password-container">
        <h3>Change your password</h3>
        <div>
			    <input id="password-input-current" type="password" v-model="oldPassword" placeholder="Current Password">
        </div>
        <div>
          <input type="checkbox" v-on:click="setCurrentVisible">Show Password
        </div>
        <div>
			    <input id="password-input-new" type="password" v-model="newPassword" placeholder="New Password">
        </div>
        <div>
          <input type="checkbox" v-on:click="setNewVisible">Show Password
        </div>
        <div>
			    <button class="glow-on-hover" v-on:click="changePassword">Change Password</button>
        </div>
      </div>

      <div id="information-container">
        <h3>Update your information</h3>
		    <div>
          <input type="text" v-model="name" placeholder="Name">
		    </div>
        <div>
			    <input type="text" v-model="phoneNumber" placeholder="Phone Number">
        </div>
        <div>
 			    <input type="text" v-model="hourlyRate" placeholder="Hourly Rate">
        </div>
        <div>
  		    <button class="glow-on-hover" v-on:click="updateProfile">Update Profile</button>
       </div>
		  </div>
    </div>
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
    setCurrentVisible() {
      var x = document.getElementById("password-input-current");
      if (x.type === "password") {
        x.type = "text";
      } else {
        x.type = "password";
      }
    },

    setNewVisible() {
      var x = document.getElementById("password-input-new");
      if (x.type === "password") {
        x.type = "text";
      } else {
        x.type = "password";
      }
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

<style scoped>
@import '../style/stylesheet.css';
@import '../style/blackandwhitebb.css';

#mainPage {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #D6EAF8;
  margin-top: 0px;
}

#container {
  display: inline-flex;
}

#information-container {
  margin-top: 75px;
  margin-left: 50px;
}

#password-container {
  margin-top: 75px;
  margin-right: 50px;
}

label {
  font-weight: bold;
  width: 100%;
  margin: 8px 0;
  display: inline-block;
}

</style>