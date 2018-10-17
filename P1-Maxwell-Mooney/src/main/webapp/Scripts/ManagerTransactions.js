function sendAjaxGet(url, func) {
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		if(this.readyState == 4 && this.status === 200) {
			func(this);
		}
	}
	xhr.open("GET", url);
	xhr.send();
}

sendAjaxGet("http://localhost:8082/P1-Maxwell-Mooney/transactions", populateTransactions);

function populateTransactions(xhr) {
	transactions = JSON.parse(xhr.responseText);
	transactionsArr = transactions.transactions;
	
	let table = document.getElementById("table-body");
	for(i in transactionsArr) {
		let newRow = document.createElement("tr");
		switch(transactionsArr[i].status) {
		case "Pending":
			status = `<td style="background-color: dodgerblue; color: white">Pending</td>`;
			actions = `<td><button class="btn btn-success" style="color: white" onclick="approve(${transactionsArr[i].id})">Approve</button>
                           <button class="btn btn-danger" style="color: white" onclick="deny(${transactionsArr[i].id})">Deny</button>
                           <button class="btn btn-primary" style="color: white">More Info</button></td>`;
			break;
		case "Approved":
			status = `<td style="background-color: darkgreen; color: white">Approved</td>`;
			actions = `<td><button class="btn btn-danger" style="color: white" onclick="remove(${transactionsArr[i].id})">Remove</button></td>`;
			break;
		case "Denied":
			status = `<td style="background-color: darkred; color: white">Denied</td>`;
			actions = `<td><button class="btn btn-danger" style="color: white" onclick="remove(${transactionsArr[i].id})">Remove</button></td>`;
			break;
		}
		newRow.innerHTML = `<td>${transactionsArr[i].id}</td>
		<td>${transactionsArr[i].employee.name}</td>
		<td>${transactionsArr[i].date}</td>
		<td>${transactionsArr[i].reason}</td>
		<td>$${transactionsArr[i].amount}</td>
		${status}
		<td>${transactionsArr[i].manager}</td>
		${actions}`
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
		document.getElementById("employees").removeAttribute("href");
	}
}