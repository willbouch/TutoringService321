<template>
    <div id="tutorReviewsPage" class="wrapper">
    <img src="../assets/321tutor.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <div class="tab">
        <button class="tablinks" @click="toMainPage">Profile</button>	
        <button class="tablinks" @click="toCoursePage">Courses</button>
  			<button class="tablinks" @click="toAvailabilityPage">Availabilities</button>
  			<button class="tablinks" @click="toSessionPage">Sessions</button>
        <button class="tablinks" style="float:right" v-on:click="toLoginPage">Logout</button>
        <button class="tablinks" @click="toTutorReviewsPage">Received Reviews</button>
        <button class="tablinks" @click="toAllTutorsPage">All Tutors</button>	
	  </div>
    &nbsp;
    <h1>RECEIVED REVIEWS</h1>
      &nbsp;
    <div class="container">
	    <div class="card">
	      <div class="card-body">
	        <div class="row">
        	    <div class="col-md-10">
        	       <div class="clearfix"></div>
                   <ul>
                       <li><p>A very caring TA who's readily accessible. Gives the best explanations. 
                           Helps out in assignments and I definitely feel like I learned a lot in this class. 
                           Solid dude</p> 
                                <span class="fa fa-star checked"></span>
                                <span class="fa fa-star checked"></span>
                                <span class="fa fa-star checked"></span>
                                <span class="fa fa-star checked"></span>
                                <span class="fa fa-star"></span>
                       </li>
                       <li>
                             <p>He has a thick french accent and he talks slow, but explains everything very well. 
                        His tests really help me keep up with my class. I learned a lot of algorithms from him, from the very basics.</p>

                                <span class="fa fa-star checked"></span>
                                <span class="fa fa-star checked"></span>
                                <span class="fa fa-star checked"></span>
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                       </li>
                       <li>
                             <p>He is personally my type of teacher. If you like a person that works methodically (like a robot) then you will love him. He's fair, 
                                 straightforward, organized and actually aims for the students' success. 
                                 The only downside would be his thick accent.
                             </p>
                                
                                <span class="fa fa-star checked"></span>
                                <span class="fa fa-star checked"></span>
                                <span class="fa fa-star checked"></span>
                                <span class="fa fa-star checked"></span>
                                <span class="fa fa-star checked"></span>        
                       </li>  
                   </ul>
        	       </div>
	            </div>  	
	        </div>
	    </div>
    </div>
    </div>    
</template>

<script>
export default {
  name: 'TutorReviewsPage',

  methods: {
	  toMainPage(){
      this.$router.push('MainPage')
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
  }
}
</script>


<style scoped>
@import '../style/blackandwhitebb.css';
#tutorReviewsPage {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #F8F9F9;
  margin-top: 0px;
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

.rating-block{
	background-color:#FAFAFA;
	border:1px solid #EFEFEF;
	padding:15px 15px 20px 15px;
	border-radius:3px;
}
.bold{
	font-weight:700;
}
.padding-bottom-7{
	padding-bottom:7px;
}

.review-block{
	background-color:#FAFAFA;
	border:1px solid #EFEFEF;
	padding:15px;
	border-radius:3px;
	margin-bottom:15px;
}
.review-block-name{
	font-size:12px;
	margin:10px 0;
}
.review-block-date{
	font-size:12px;
}
.review-block-rate{
	font-size:13px;
	margin-bottom:15px;
}
.review-block-title{
	font-size:15px;
	font-weight:700;
	margin-bottom:10px;
}
.review-block-description{
	font-size:13px;
}
.checked {
  color: orange;
}
.container{
  margin: 0 auto;
  color: #1B2631;
}
</style>