let checkSessionURL = "http://localhost:8082/P1-Maxwell-Mooney/session";

function sendAjaxGet(url, func) {
	let xhr = new XMLHttpRequest() || new ActiveX("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		if(this.readyState===4 && this.status===200) {
			func(this);
		}
	}
	
	xhr.open("GET", url);
	xhr.send();
}

sendAjaxGet(checkSessionURL, populateUser)

function populateUser(xhr) {
	let response = JSON.parse(xhr.response);
	if(response.username != "null") {
		document.getElementById("user").innerHTML = "You are logged in as: " + response.username;
	} else {
		window.location = "http://localhost:8082/P1-Maxwell-Mooney/login"
	}
}