<template>
	<div id="mainPage">
		<h1>TUTOR PROFILE</h1>
		<div class="tab">
  			<button class="tablinks" onclick="openCity(event, 'Paris')">Availabilities</button>
  			<button class="tablinks" onclick="openCity(event, 'Tokyo')">Sessions</button>
			  <button class="tablinks" onclick="openCity(event, 'Tokyo')">Courses</button>
        <button class="tablinks" onclick="openCity(event, 'Tokyo')">All Tutors</button>
		</div>

		<div>
			<img src="@/assets/profile-picture.png" width=200>
  		<label>EMAIL</label>
			<label>RATING</label>
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
  		  <button class="glow-on-hover">Update Profile</button>
      </div>
      <div>
			  <input type="text" v-model="newPassword" placeholder="New Password">
      </div>
      <div>
			  <input type="text" v-model="confirmationPassword" placeholder="Re-enter Password">
      </div>
      <div>
			  <button class="glow-on-hover">Change Password</button>
      </div>

		</div>

	</div>
</template>

<script>
function TutorDto(email, rating, name, phoneNumber, hourlyRate) {
    this.name = name
    this.rating = rating
    this.name = name
    this.phoneNumber = phoneNumber
    this.hourlyRate = hourlyRate
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
      newPassword:'',
      confirmationPassword:''
    }
  },

  created: function() {
    const tutor = new TutorDto('w@gmail.com', 4.5, 'William Bouchard', '418-573-0193', 18)
  },

  methods: {
	  
  }
}
</script>

<style>
#mainPage {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
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

/* Style inputs */
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