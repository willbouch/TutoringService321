<!-- This is an empty Tutor Profile page to be used for the demo only. -->

<template>
	<div id="tutorPage">
		<h1>RIJUL'S PROFILE</h1>
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
		</div>

	</div>
</template>

<script>
export default {
	name: 'app'
}
</script>

<style>
#tutorPage {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
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

.review {
 float: right;
}

</style>