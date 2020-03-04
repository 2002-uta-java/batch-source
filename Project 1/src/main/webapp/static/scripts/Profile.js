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
	console.log(newBday, newBio);
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
	url += `?birthday=${newBday}&bio=${newBio}&email=${user.employee_email}`;
	//set PUT request and url
	xhr.open("PUT", url);
	//send request
	xhr.send();
}

//render editable profile layout
let handleProfileEdit = (user) =>{
	//get profile div
	let profile = document.querySelector("#profile");
	//render appropriate layout to edit content
	profile.innerHTML = 
	`
		<div class="content image">
			<img rel="Identifies the user." src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png"/>
		</div>
		<div class="content">
			<table>
				<tr>
					<td><h3><strong>${user.employee_email}</strong></h3></td>
					<td>
						<div class="button fancy-button small" id="save-profile">
							<div class="slide"></div>
							<a>Save Profile</a>
						</div>
					</td>
				</tr>
				<tr>
					<td><h4><strong>Position: </strong></h4><pre>${user.role}</pre></td>
					<td><h4><strong>Birthday: </strong></h4><input type="text" id="profile-bday" value="${user.birthday}"/></td>
				</tr>
				<tr>
					<td><h4><strong>${user.name}'s Bio:</strong></h4></td>
				</tr>
				<tr>
					<td><input type="text" id="profile-bio" value="${user.bio}"/></td>
				</tr>
			</table>
		</div>
	`;
	//get save button
	let saveBtn = document.querySelector("#save-profile");
	//add click event listener
	saveBtn.addEventListener("click", ()=>{updateProfile(user)});
}

//render user profile
let renderProfile = (user) =>{
	// get profile div
	let profile = document.querySelector("#profile");
	// render user info
	profile.innerHTML = 
	`
		<div class="content image">
			<img rel="Identifies the user." src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png"/>
		</div>
		<div class="content">
			<table>
				<tr>
					<td><h3><strong>${user.employee_email}</strong></h3></td>
					<td>
						<div class="button fancy-button small">
							<div class="slide"></div>
							<a>Edit Profile</a>
						</div>
					</td>
				</tr>
				<tr>
					<td><h4><strong>Position: </strong></h4><pre>${user.role}</pre></td>
					<td><h4><strong>Birthday: </strong></h4><pre>${user.birthday}</pre></td>
				</tr>
				<tr>
					<td><h4><strong>${user.name}'s Bio:</strong></h4></td>
				</tr>
				<tr>
					<td><pre>${user.bio}</pre></td>
				</tr>
			</table>
		</div>
	`;
	//get edit profile button
	let editProfile = document.querySelector("#profile .fancy-button");
	//add click event listener
	editProfile.addEventListener("click", ()=>{handleProfileEdit(user)});
}