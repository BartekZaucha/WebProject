function logout() {
	$("#logout").click(function() {
		sessionStorage.clear();
	});
}

function redirect() {
	location.replace("index.html");
}

$(document).ready(function() {
	logout();
});