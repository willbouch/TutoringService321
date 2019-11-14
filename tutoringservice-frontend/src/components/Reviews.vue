<template>
    <div>   


    <div>
      <div class="tab">
  			<button class="tablinks" onclick="openCity(event, 'Paris')">Availabilities</button>
  			<button class="tablinks" onclick="openCity(event, 'Tokyo')">Sessions</button>
			<button class="tablinks" onclick="openCity(event, 'Tokyo')">Courses</button>
            <button class="tablinks" style="float:right" onclick="openCity(event, 'Tokyo)">Logout</button>
            <button class="tablinks" style="float:right" onclick="openCity(event, 'Tokyo)">Write a review</button>
		</div>
       
                <div>
                    <div class="container">
                        <form action="action_page.php">

                        <label for="Sname">Student's name</label>
                        <input type="text" id="Sname" name="Student's Name" placeholder="Students name..">

                        <label for="Semail">Student's email</label>
                        <input type="text" id="Semail" name="Student's Email" placeholder="e.g. xyz@gmail.com">

                        <label for="subject">Subject</label>
                        <select id="subject" name="subject">
                        <option value="other">Other..</option>    
                        <option value="mathematics">Mathematics</option>
                        <option value="computer Science">Computer Science</option>
                        
                        </select>

                        <label for="review">Write A Review</label>
                        <textarea id="review" name="review" placeholder="Let us know what you think.." style="height:200px"></textarea>

                        <input type="submit" value="Submit">

                        </form>
                        </div>

                </div>
                <div class="btn-group-toggle" data-toggle="buttons">
                    <label class="btn btn-secondary active">
                    <input type="checkbox" checked autocomplete="off"> Post anonymously
                    </label>
            </div>     
    </div>

    </div>

</template>



<style>
*{
    margin-top: 0;
}
/* Style inputs with type="text", select elements and textareas */
input[type=text], select, textarea {
  width: 100%; /* Full width */
  padding: 12px; /* Some padding */ 
  border: 1px solid #ccc; /* Gray border */
  border-radius: 4px; /* Rounded borders */
  box-sizing: border-box; /* Make sure that padding and width stays in place */
  margin-top: 6px; /* Add a top margin */
  margin-bottom: 16px; /* Bottom margin */
  resize: vertical /* Allow the user to vertically resize the textarea (not horizontally) */
}

/* Style the submit button with a specific background color etc */
input[type=submit] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

/* When moving the mouse over the submit button, add a darker green color */
input[type=submit]:hover {
  background-color: #45a049;
}

/* Add a background color and some padding around the form */
.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
  
}

.tab{
    margin-bottom: 5%;
    margin-left: 0%;
}
</style>