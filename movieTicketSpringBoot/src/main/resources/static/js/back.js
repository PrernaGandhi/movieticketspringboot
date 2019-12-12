window.onbeforeunload = function(evt) {
	if (typeof evt == 'undefined') {
		evt = window.event;

		if (evt) {

			return "If you click back button, you will be logged out!!";
		}
	}
}