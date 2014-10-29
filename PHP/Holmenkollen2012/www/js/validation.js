$(document).ready(function()
{
	jQuery.fn.exists = function(){return this.length>0;}

	//first name
	if($("#firstBox").exists())
	{
		$("#firstBox").blur(function()
		{
			validateFirst();
		});
		$("#firstBox").focus(function(){
			$("#firstValid").text("");
		});
	}

	//last name
	if($("#lastBox").exists())
	{
		$("#lastBox").blur(function()
		{
			validateLast();
		});
		$("#lastBox").focus(function(){
			$("#lastValid").text("");
		});
	}

	//email
	if($("#emailBox").exists())
	{
		$("#emailBox").blur(function()
		{
			validateEmail();
		});
		$("#emailBox").focus(function(){
			$("#emailValid").text("");
		});
	}

	//password
	if($("#passwordBox").exists())
	{
		$("#passwordBox").blur(function()
		{
			validateEmail();
		});
		$("#passwordBox").focus(function(){
			$("#passwordValid").text("");
		});
	}

	//adress
	if($("#adressBox").exists())
	{
		$("#adressBox").blur(function()
		{
			validateAdress();
		});
		$("#adressBox").focus(function(){
			$("#adressValid").text("");
		});
	}

	//password
	if($("#nationality_idBox").exists())
	{
		$("#nationality_idBox").blur(function()
		{
			$("#nationality_idValid").text("");
		});
	}

	//phone
	if($("#phoneBox").exists())
	{
		$("#phoneBox").blur(function()
		{
			validatePhone();
		});
		$("#phoneBox").focus(function(){
			$("#phoneValid").text("");
		});
	}
});

function validateFirst()
{
	var regex = /[a-zA-Z]{2,40}/;
	if(!regex.test($("#firstBox").val()))
	{
		$("#firstValid").text("mellom 2 og 40 bokstaver");
	}
	else
	{
		$("#firstValid").text("");
	}
}

function validateLast()
{
	var regex = /[a-zA-Z]{2,40}/;
	if(!regex.test($("#lastBox").val()))
	{
		$("#lastValid").text("mellom 2 og 40 bokstaver");
	}
	else
	{
		$("#lastValid").text("");
	}
}

function validateEmail()
{
	var regex = /^[^0-9][a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[@][a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,4}$/;
	if(!regex.test($("#emailBox").val()))
	{
		$("#emailValid").text("feil format på e-mail");
	}
	else
	{
		$("#emailValid").text("");
	}
}

function validateAdress()
{
	var regex = /.{8,40}/;
	if(!regex.test($("#adressBox").val()))
	{
		$("#adressValid").text("mellom 8 og 40 bokstaver og tall");
	}
	else
	{
		$("#adressValid").text("");
	}
}

function validateNationality()
{
	$("#nationality_idValid").text("");
}