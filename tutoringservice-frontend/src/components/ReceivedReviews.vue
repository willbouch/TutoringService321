<template>

    <div>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <div class="tab">
  			<button class="tablinks" onclick="openCity(event, 'Paris')">Availabilities</button>
  			<button class="tablinks" onclick="openCity(event, 'Tokyo')">Sessions</button>
			<button class="tablinks" onclick="openCity(event, 'Tokyo')">Courses</button>
            <button class="tablinks" style="float:right" onclick="openCity(event, 'Tokyo)">Logout</button>
            <button class="tablinks" style="float:right" onclick="openCity(event, 'Tokyo)">Received Reviews</button>
		</div>


    <div>
<!------ Include the above in your HEAD tag ---------->

  
    <!-- Fixed navbar -->
   

    <div class="container">
    			
		<div class="row">
			<div class="col-sm-3">
				<div class="rating-block">
					<h2>Averaged rating</h2>
					<h2 class="bold padding-bottom-7">4.0 <small>/ 5.0</small></h2>
                      
        	                    <span class="fa fa-star checked"></span>
                                <span class="fa fa-star checked"></span>
                                <span class="fa fa-star checked"></span>
                                <span class="fa fa-star checked"></span>
                                <span class="fa fa-star"></span>

        	       
				</div>
			</div>
						
		</div>			
		
    </div> <!-- /container -->

    <div>
    <div class="container">
	<h2 class="text-center">Received Reviews</h2><br><br><br>
	
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
    
 

    </div>
    </div>
</template>



<style scoped>
body {
  padding-top: 70px;
}
.btn-grey{
    background-color:#D8D8D8;
	color:#FFF;
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
 
/* .card-inner{
    margin-left: 4rem;
}
   */
   .checked {
  color: orange;
}
</style>