function sendAjaxGet1(url, func) {
    let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
    xhr.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200) {
            func(this);
        }
    }
    xhr.open("GET", url);
    xhr.send();
}

sendAjaxGet1("http://localhost:8082/P1-Maxwell-Mooney/employee", populateEmployees);

function populateEmployees(xhr) {
    employees = JSON.parse(xhr.responseText);
    employeesArr = employees.employees;

    let table = document.getElementById("table-body");
    for(i in employeesArr) {
        let newRow = document.createElement("tr");
        let click = document.createAttribute("onclick");
        click.value = `select(${employeesArr[i].id})`;
        newRow.setAttributeNode(click);
        newRow.innerHTML = `<td>${employeesArr[i].id}</td>
        <td>${employeesArr[i].name}</td>
        <td>${employeesArr[i].email}</td>
        <td>${employeesArr[i].username}</td>
        <td>${employeesArr[i].isManager}</td>
        <td>${employeesArr[i].phone}</td>`
        table.appendChild(newRow);
    }
}    
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
		document.getElementById("employees").removeAttribute("href")
	}
}