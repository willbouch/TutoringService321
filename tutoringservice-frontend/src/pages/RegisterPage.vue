<template>
  <div id="registerPage" class="wrapper">
    
		<div class="container">
      <h1>REGISTER AS A TUTOR</h1>
      &nbsp;&nbsp;&nbsp;
      <form class="form" id="register">
			<input type="text" v-model="email" placeholder="Email address">
			<input type="text" v-model="name" placeholder="Name">	
			<input type="password" v-model="password" placeholder="Password (8 characters)">		
			<input type="text" v-model="phoneNumber" placeholder="Phone Number (xxx-xxx-xxxx)">				
			<input type="text" v-model="hourlyRate" placeholder="Hourly Rate">
			<button @click="registerTutor(email, name, password, phoneNumber, hourlyRate)" class="glow-on-hover">REGISTER</button>
      </form>
      <a href="#/LoginPage" class="backbutton">back</a>
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
  name: 'RegisterPage',

  data() {
    return {
	  email: '',
	  name: '',
	  password: '',
	  phoneNumber:'',
	  hourlyRate:''
    }
  },

  methods: {
    registerTutor: function(email, name, password, phoneNumber, hourlyRate){
      phoneNumber = phoneNumber.split('-').join('')
      AXIOS.post(`/register/`+email+`?name=`+name+`&password=`+password+`&phoneNumber=`+phoneNumber+`&hourlyRate=`+hourlyRate,{},{})
      .then(response => {
		  this.$router.go(-1)
      })
      .catch(e => {
        var errorMsg = e.response.data.message
        window.alert(errorMsg)
      });
    },
  }
}
</script>

<style scoped>
@import url(https://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300);
* {
  -webkit-box-sizing: border-box;
          box-sizing: border-box;
  margin: 0;
  padding: 0;
  font-weight: 300;
}

#registerPage {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #F2F3F4;
  margin-top: 0px;
}

.wrapper {
  background: #151515;
 
}
.wrapper.form-success .container h1 {
  -webkit-transform: translateY(85px);
          transform: translateY(85px);
}
.container {
  max-width: 600px;
  margin: 0 auto;
  padding: 140px 0;
  height: 750px;
  text-align: center;
}
.container h1 {
  font-size: 40px;
  -webkit-transition-duration: 1s;
          transition-duration: 1s;
  -webkit-transition-timing-function: ease-in-put;
          transition-timing-function: ease-in-put;
  font-weight: 300;
}
form {
  padding: 0px 0;
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
  width: 250px;
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

form button:hover {
  background-color: #f5f7f9;
}
.fadeout {
  visibility: hidden;
  opacity: 0;
  transition: visibility 0s .5s, opacity .5s linear;
}

.fadeout2 {
  visibility: hidden;
  opacity: 0;
  transition: visibility 0s .5s, opacity .5s linear;
}

@keyframes fadeIn { from { opacity:0; } to { opacity:1; } }
.fadein {
  opacity: 0;
  animation:fadeIn ease-in 1;
  animation-fill-mode: forwards;
  animation-duration: 0.5s;
  animation-delay: 0.5s;
}

.backbutton {
  padding-top: 40px;
  color: #EBF5FB;
  border-radius: 3px;
  width: 125px;
  cursor: pointer;
  font-size: 18px;
  -webkit-transition-duration: 0.25s;
          transition-duration: 0.25s;
}

.error {
    color: #FDFEFE;
}


.hidden {
  display:none;
}
.glow-on-hover {
    padding: 10px 15px;
    width: 220px;
    height: 50px;
    border: none;
    outline: none;
    color: #FBFCFC;
    background: #151515;
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
    opacity: 1;
    transition: opacity .3s ease-in-out;
    border-radius: 10px;
}
.glow-on-hover:active {
    color: #151515
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
    background: #151515;
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