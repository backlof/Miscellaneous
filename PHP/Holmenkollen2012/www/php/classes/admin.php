<?php
class Admin 
{
	public $email;
	public $password;

	function __construct($input)
	{
		if(isset($input['email']))
		{
			$this->email = $input['email'];
		}

		if(isset($input['password']))
		{
			$this->password = sha1($input['password']);
		}
	}
}
?>