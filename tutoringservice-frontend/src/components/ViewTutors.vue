<template>

	<div id="viewTutors">
		<h1>Tutors</h1>
		<div class="topPage">
			<img src="default profile picture.png" alt="Avatar" class="Avatar">
			<button clas="button">My Profile</button>
		</div>

		<table class="table" align="center">
			<thead class="thead-dark">
				<tr>
				<th scope="col">Tutor Name</th>
				<th scope="col">Contact</th>
				<th scope="col">Courses</th>
				<th scope="col">Profile</th>
				</tr>
			</thead>
			<tbody>
				<tr>
				<th scope="row">Hadi Zia</th>
				<td>hadi.zia@gmail.com</td>
				<td><div class="dropdown">
						<button class="dropbtn">Courses</button>
						<div class="dropdown-content">
							<a href="#">COMP 202</a>
							<a href="#">COMP 250</a>
							<a href="#">COMP 251</a>
						</div>
				</div></td>
				<td><a href="#">Hadi's Profile</a></td>
				</tr>
				<tr>
				<th scope="row">Jonathan Roy</th>
				<td>jonathan.roy@gmail.com</td>
				<td><div class="dropdown">
						<button class="dropbtn">Courses</button>
						<div class="dropdown-content">
							<a href="#">CIVE 207</a>
							<a href="#">CIVE 323</a>
							<a href="#">CIVE 527</a>
						</div>
				</div></td>
				<td><a href="#">Jonathan's Profile</a></td>
				</tr>
				<tr>
				<th scope="row">Sharon Kattar</th>
				<td>sharon.kattar@gmail.com</td>
				<td><div class="dropdown">
						<button class="dropbtn">Courses</button>
						<div class="dropdown-content">
							<a href="#">ECSE 205</a>
							<a href="#">MATH 263</a>
						</div>
				</div></td>
				<td><a href="#">Sharon's Profile</a></td>
				</tr>
				<tr>
				<th scope="row">Julia London</th>
				<td>julia.london@gmail.com</td>
				<td><div class="dropdown">
						<button class="dropbtn">Courses</button>
						<div class="dropdown-content">
							<a href="#">ECSE 326</a>
							<a href="#">ECSE 301</a>
							<a href="#">ECSE 251</a>
						</div>
				</div></td>
				<td><a href="#">Julia's Profile</a></td>
				</tr>
			</tbody>
		</table>
	</div>
</template>

#<style>
#mainPage {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.topPage button {
  background-color: #ddd;
  border: none;
  color: black;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  margin: 6px 4px;
  cursor: pointer;
  border-radius: 16px;
  position: absolute;
  top: 10px;
  right: 15px;
}

.topPage avatar {
  vertical-align: middle;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  /* position: absolute;
  top: 5px;
  right: 10px; */
}

/* Table */
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: center;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

/* Dropdown Button */
.dropbtn {
  background-color: #20B2AA	;
  color: black;
  padding: 12px;
  font-size: 16px;
  border: none;
}

/* The container <div> - needed to position the dropdown content */
.dropdown {
  position: relative;
  display: inline-block;
}

/* Dropdown Content (Hidden by Default) */
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 100%;
  overflow:auto;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

/* Links inside the dropdown */
.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {background-color: #ddd;}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {display: block;}

/* Change the background color of the dropdown button when the dropdown content is shown */
.dropdown:hover .dropbtn {background-color: #48D1CC;}

</style>