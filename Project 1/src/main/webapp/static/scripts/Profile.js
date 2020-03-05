let updateProfileImage = (user) =>{
	let newImg = prompt("Please enter a URL for your new image: ", 
			"https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png");
	user.image = newImg;
	updateProfile(user);
}

//save new profile details in database
let updateProfile = (user) =>{
	//url for employee api endpoint
	let url = "http://localhost:8080/project1/api/employee";
	//home url
	let home = "http://localhost:8080/project1/home";
	//get updated birthday
	let newBday = document.querySelector("#profile-bday").value;
	//get updated bio
	let newBio = document.querySelector("#profile-bio").value;
	//strip of invalid values
	newBday = newBday.replace(/[^\d/-]/g, '');
	//validate input
	if(!isNaN(Date.parse(newBday)) && newBday.length === 10){
		//send ajax request to update employee
		let xhr = new XMLHttpRequest();
		//refresh the page if update is successful
		xhr.onreadystatechange = () =>{
			if(xhr.readyState === 4 && xhr.status === 200){
				let userJson = xhr.getResponseHeader("Data");
				if(userJson.length !== 0){
					console.log(userJson);
					sessionStorage.setItem("token", userJson);
					window.location = home;
				}
			}
		}
		//set parameters
		url += `?birthday=${newBday}&bio=${newBio}&email=${user.employee_email}&image=${user.image}`;
		//set PUT request and url
		xhr.open("PUT", url);
		//send request
		xhr.send();
	} else{
		alert("Use the DD/MM/YYYY birth date format.");
	}
}

//render editable profile layout
let handleProfileEdit = (user) =>{
	//get profile div
	let profile = document.querySelector("#profile");
	//render appropriate layout to edit content
	profile.innerHTML = 
	`
		<div class="content image">
			<img rel="Identifies the user." src="${user.image}" style="cursor:pointer;"/>
		</div>
		<div class="content main">
			<div class="item email">
				<h3>
					<strong>
						${user.employee_email}
					</strong>
				</h3>
			</div>
			<div class="item edit">
				<div class="button fancy-button small" id="save-profile">
					<div class="slide"></div>
					<a>Save Profile</a>
				</div>
			</div>
			<div class="item role">
				<h4><strong>Position: </strong></h4><pre>${user.role}</pre>
			</div>
			<div class="item bday">
				<h4><strong>Birthday: </strong></h4><input type="text" id="profile-bday" value="${user.birthday}" maxlength="10"/>
			</div>
			<div class="item bio">
				<h4><strong>${user.name}'s Bio:</strong></h4><br>
				<input type="text" id="profile-bio" value="${user.bio}" maxlength="160"/>
			</div>
		</div>
	`;
	//get save button
	let saveBtn = document.querySelector("#save-profile");
	//add click event listener
	saveBtn.addEventListener("click", ()=>{updateProfile(user)});
	//change profile image
	let profileImg = document.querySelector(".content img");
	//add click event listener
	profileImg.addEventListener("click", ()=>{updateProfileImage(user)});
}

//render user profile
let renderProfile = (user) =>{
	// get profile div
	let profile = document.querySelector("#profile");
	// render user info
	profile.innerHTML = 
	`
		<div class="content image">
			<img rel="Identifies the user." src="${user.image}"/>
		</div>
		<div class="content main">
			<div class="item email">
				<h3>
					<strong>
						${user.employee_email}
					</strong>
				</h3>
			</div>
			<div class="item edit">
				<div class="button fancy-button small" id="edit-profile">
					<div class="slide"></div>
					<a>Edit Profile</a>
				</div>
			</div>
			<div class="item role">
				<h4><strong>Position: </strong></h4><pre>${user.role}</pre>
			</div>
			<div class="item bday">
				<h4><strong>Birthday: </strong></h4><pre>${user.birthday}</pre>
			</div>
			<div class="item bio">
				<h4><strong>${user.name}'s Bio:</strong></h4><br>
				<div id="bio-container">
					<p>${user.bio}</p>
				</div>
			</div>
		</div>
	`;
	//get edit profile button
	let editProfile = document.querySelector("#edit-profile");
	//add click event listener
	editProfile.addEventListener("click", ()=>{handleProfileEdit(user)});
}