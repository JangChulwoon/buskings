function access(response) {
	console.log('login 로직입니다.');
	console.log("resonpse :" + response);
	if (response.status === 'connected') {
		console.log(response.authResponse.accessToken);
		testAPI();

	} else if (response.status === 'not_authorized') {
		document.getElementById('status').innerHTML = 'Please log '
				+ 'into this app.';
	} else {
		document.getElementById('status').innerHTML = 'Please log '
				+ 'into Facebook.';
	}
}
function checkLoginState() {
	// getLoginStatus에서 로그인 체크를 함!

	FB.login(function(response) {
		access(response);
	}, {
		scope : 'public_profile,email'
	});

}
function checklogout() {
	// getLoginStatus에서 로그인 체크를 함!

	FB.logout(function(response) {
		// Person is now logged out
	});

}
window.fbAsyncInit = function() {
	FB.init({
		appId : '225973124471432',
		cookie : true, // enable cookies to allow the server to access
		// the session
		xfbml : true, // parse social plugins on this page
		version : 'v2.7'
	});
	
};
// Load the SDK asynchronously
(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id))
		return;
	js = d.createElement(s);
	js.id = id;
	js.src = "//connect.facebook.net/en_US/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// Here we run a very simple test of the Graph API after login is
// successful. See statusChangeCallback() for when this call is made.
function testAPI() {
	console.log('Welcome!  Fetching your information.... ');

	FB.api('/me', {
		fields : 'name,email'
	}, function(response) {
		document.location.href='/controller/'+response.email+'/'+response.name; 	
	});
	
}