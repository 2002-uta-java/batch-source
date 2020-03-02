// update status when change is detected
let handleChange = (event, id) =>{
	//url for reimbursement update
	let url = "http://localhost:8080/project1/api/reimbursement";
	//home page url
	let home = "http://localhost:8080/project1/home";
	//send ajax request to update reimbursement based on id
	let xhr = new XMLHttpRequest();
	//refresh page if successful
	xhr.onreadystatechange = () =>{
		if(xhr.readyState === 4 && xhr.status === 200){
			let result = xhr.getResponseHeader("Status");
			console.log(xhr.reponse);
			if(result.length !== 0){
				window.location = home;
			}
		}
	}
	//set id parameter
	url += `?id=${id}&status=${event.target.value}`;
	//set verb and url
	xhr.open("PUT", url);
	//send request
	xhr.send();
}

// onclick function for editing reimbursement
let editReimbursement = (event, id) =>{
	// reimbursement div id
	let rmbId = event.target.parentElement.id;
	//get select element
	let options = document.querySelector(`#${rmbId} select`);
	//enable updates
	options.removeAttribute("disabled");
	//listen for changes in select element
	options.addEventListener("change", (event)=>{handleChange(event, id)});
}