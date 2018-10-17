function sendAjaxGet(url, func) {
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		if(this.readyState === 4 && this.status === 200) {
			func(this);
		}
	}
	xhr.open("GET", url);
	xhr.send();
}

sendAjaxGet("http://localhost:8082/P1-Maxwell-Mooney/session", populateProfile);

function populateProfile(xhr) {
	session = JSON.parse(xhr.responseText);
	//console.log(session.name);
	document.getElementById("emp-header").innerHTML = `Hello, ${session.name}!`;
	document.getElementById("name").value=session.name;
	document.getElementById("email").value=session.email;
	document.getElementById("username").value=session.username;
	document.getElementById("password").value=session.password;
	document.getElementById("phone").value=session.phone;
}

document.getElementById("edit").addEventListener("click", function edit() {
	document.getElementById("edit").style.display = "none";
	document.getElementById("save").style.display = "inline";
	document.getElementById("name").disabled = false;
	document.getElementById("email").disabled = false;
	document.getElementById("username").disabled = false;
	document.getElementById("password").disabled = false;
	document.getElementById("phone").disabled = false;
	document.getElementById("name").focus();

})

function sendAjaxGet2(url, func) {
    let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
    xhr.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200) {
            func(this);
        }
    }
    xhr.open("GET", url);
    xhr.send();
} 

sendAjaxGet2("http://localhost:8082/P1-Maxwell-Mooney/session", isEmployee);

function isEmployee(xhr) {
	session = JSON.parse(xhr.responseText);
	if(session.isManager == "N") {
		document.getElementById("reimbursements").removeAttribute("href");
		document.getElementById("employees").removeAttribute("href");
	}
}

/*document.getElementById("save").addEventListener("click", function save() {
	document.getElementById("save").style.display = "none";
	document.getElementById("edit").style.display = "inline";
})*/
