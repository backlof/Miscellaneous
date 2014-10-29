$(document).ready(function() {
	$(".hidden").removeClass("hidden"); //slik at det ikke er flere bilder for brukere uten js

	$(function() {
	    $('#slideshow').cycle({
	       	fx:        'fade',
	       	delay:    -6000,
			before: function(curr, next, opts) {
				opts.animOut.opacity = 0;
			}
	    });
	});
});