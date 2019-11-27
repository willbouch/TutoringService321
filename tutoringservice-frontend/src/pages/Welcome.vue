<template>
	<div id="welcome" class="wrapper">
		<div class="container">
			<img src="@/assets/stillcube.png" width=300>
				<img src="@/assets/321tutor.png" width=500>
		<div>
		<h2> I am ... </h2>
		&nbsp;&nbsp;&nbsp;
	<div>
		<router-link to="/LoginPage" tag="button" class="glow-on-hover">A TUTOR</router-link>
		&nbsp;&nbsp;&nbsp;
		<router-link to="/StudentPage" tag="button" class="glow-on-hover">A STUDENT</router-link>
	</div>
	</div>
	</div>
	</div>


</template>

<script>
export default {
	methods: {
		timeout: function(){
			setTimeout(function(){
                    this.basketAddSuccess = false;
                }, 2000);
		}
	},
	toLoginPage(){
      this.$router.push('LoginPage')
	},
	toMeme(){
      this.$router.push('RegisterPage')
	},
	
}
</script>
 
<style scoped>
@import '../style/blackandwhitebb.css';
@import '../style/stylesheet.css';

#welcome {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #FDFEFE;
  margin-top: 0px;
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
.container {
  max-width: 600px;
  margin: 0 auto;
  padding: 30px 0;
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

</style>