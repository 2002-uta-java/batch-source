
window.addEventListener("load",loadNav);

function loadNav(){
	document.getElementById("nav01").innerHTML=
		`<nav class="navbar navbar-expand-sm fixed-top navbar-dark">
		        <a class="navbar-brand" href="/project_one/index">CoinPurse</a>
		        <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#collapsibleNavId" aria-controls="collapsibleNavId"
		            aria-expanded="false" aria-label="Toggle navigation"></button>
		        <div class="collapse navbar-collapse" id="collapsibleNavId">
		            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		                
		                <li class="nav-item active" id="profileNav">
		                    <a class="nav-link" href="/project_one/index">Home <span class="sr-only">(current)</span></a>
		                </li>
		                	                
		                <li class="nav-item active">
		                    <a class="nav-link" load = "profileBtn()" href="/project_one/profile">User <span class="sr-only">(current)</span></a>
		                </li>
		                                     
		                <li class="nav-item active">
		                    <a class="nav-link" href="/project_one/index">Home <span class="sr-only">(current)</span></a>
		                </li>

		                <li class="nav-item dropdown" >
		                    <a class="nav-link dropdown-toggle"style="color: white;" href="#" 
		                    id="dropdownId" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Get Started</a>
		                    <div class="dropdown-menu" aria-labelledby="dropdownId">
		                        <a class="dropdown-item" href="/project_one/login">Login</a>
		                        <a class="dropdown-item" href="/project_one/new_user">Registration</a>
		                    </div>
		                </li>
		                
		                <li class="nav-item active">
								<a class="nav-link" onclick="killsession()">Log Out <span class="sr-only">(current)</span></a>
		                </li> 
		                
		            </ul>
		            <form class="form-inline my-2 my-lg-0">
		                <input class="form-control mr-sm-2" type="text" placeholder="Search">
		                <button class="btn" type="submit" >Search</button>
		            </form>
		        </div>
		    </nav>`;
	
}

function killsession(){
	console.log("session terminated");

	localStorage.clear();
	sessionStorage.clear()
	window.location.href="http://localhost:8080/project_one/index";
}

function profileBtn(){
	console.log("PING");;
}
