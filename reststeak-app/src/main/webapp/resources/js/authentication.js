var currentLocation = location.href;

function authCheck() {
	if (currentLocation == "http://localhost:8080/reststeak-app/employee.html") {
		if (sessionStorage.getItem("userRole") != "Employee") {
			redirect();
		}
	} else if (currentLocation == "http://localhost:8080/reststeak-app/customer.html") {
		if (sessionStorage.getItem("userRole") != "Customer") {
			redirect();
		}
	}
}

function redirect() {
	location.replace("index.html")
}

$(document).ready(function() {
	authCheck();
});