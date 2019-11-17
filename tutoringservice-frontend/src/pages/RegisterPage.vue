<template>
  <div id="registerPage">
		<h1>REGISTER</h1>
		<div>
			<input type="text" v-model="email" placeholder="Email address">
		</div>
		<div>				
			<input type="text" v-model="name" placeholder="Name">
		</div>
		<div>				
			<input type="password" v-model="password" placeholder="Password (8 characters)">
		</div>
		<div>				
			<input type="text" v-model="phoneNumber" placeholder="Phone Number (xxx-xxx-xxxx)">
		</div>
		<div>				
			<input type="text" v-model="hourlyRate" placeholder="Hourly Rate">
		</div>
		<div>
			<button @click="registerTutor(email, name, password, phoneNumber, hourlyRate)" class="glow-on-hover">Register</button>
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

<style>
#registerPage {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  position: absolute;
  top: 50%;
  left: 50%;
  -ms-transform: translateX(-50%) translateY(-50%);
  -webkit-transform: translate(-50%,-50%);
  transform: translate(-50%,-50%);
}

/* Style inputs */
input[type=text] {
  width: 300px;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=password] {
  width: 300px;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

/* Style the submit button */
.glow-on-hover {
    width: 300px;
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