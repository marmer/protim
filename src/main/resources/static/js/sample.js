/**
 * 
 */
function loadSample() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var json = JSON.parse(this.response);			
			document.getElementById("label").innerHTML = json.niceProperty;
		}
	};
	xhttp.open("GET", "/sample/json", true);
	xhttp.send();
}
