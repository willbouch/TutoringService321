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

    <div id="container">
      <div>
        <div>
			    <input id="password-input-current" type="password" v-model="oldPassword" placeholder="Old Password">
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

      <div>
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

function myFunction() {
  var x = document.getElementById("password-input-current");
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}

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

#container {
  display: flex;
  top: 50%;
  position: absolute;
  left: 50%;
  -ms-transform: translateX(-50%) translateY(-50%);
  -webkit-transform: translate(-50%,-50%);
  transform: translate(-50%,-50%);
  display: flex;
}

label {
  font-weight: bold;
  width: 100%;
  margin: 8px 0;
  display: inline-block;
}

</style>