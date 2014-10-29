<?php
class Type 
{
	public $id;
	public $type;

	function __construct($input)
	{
		if(isset($input['id']))
		{
			$this->id = $input['id'];
		}

		if(isset($input['type']))
		{
			$this->type = $input['type'];
		}
	}
}
?>