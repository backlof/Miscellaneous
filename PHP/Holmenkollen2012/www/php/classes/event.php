<?php
class Event 
{
	public $id;
	public $type;
	public $date;
	public $type_id;
	public $gender;

	function __construct($input)
	{
		if(isset($input['id']))
		{
			$this->id = $input['id'];
		}

		if(isset($input['type_id']))
		{
			$this->type_id = $input['type_id'];
		}

		if(isset($input['type']))
		{
			$this->type = $input['type'];
		}
		
		if(isset($input['date']))
		{
			$this->date = $input['date'];
		}

		if(isset($input['ismales']))
		{
			if($input['ismales'])
			{
				$this->gender = 'Menn';
			}
			else
			{
				$this->gender = 'Kvinner';
			}
		}
	}
}
?>