<?php
class Person 
{
	public $id;
	public $nation;
	public $nationality_id;
	public $first;
	public $last;
	public $adress;
	public $phone;
	public $ticket;
	public $event_id;
	public $password;

	function __construct($input)
	{
		if(isset($input['id']))
		{
			$this->id = $input['id'];
		}

		if(isset($input['nationality']))
		{
			$this->nationality = $input['nationality'];
		}

		if(isset($input['first']))
		{
			$this->first = $input['first'];
		}

		if(isset($input['last']))
		{
			$this->last = $input['last'];
		}

		if(isset($input['adress']))
		{
			$this->adress = $input['adress'];
		}

		if(isset($input['phone']))
		{
			$this->phone = $input['phone'];
		}
		
		if(isset($input['ticket']))
		{
			$this->ticket = $input['ticket'];
		}

		if(isset($input['event_id']))
		{
			$this->event_id = $input['event_id'];
		}

		if(isset($input['nationality_id']))
		{
			$this->nationality_id = $input['nationality_id'];
		}

		if(isset($input['password']))
		{
			$this->password = sha1($input['password']);
		}
	}
}
?>