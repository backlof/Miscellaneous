<?php
class Nationality 
{
	public $id;
	public $nation;

	function __construct($input)
	{
		if(isset($input['id']))
		{
			$this->id = $input['id'];
		}

		if(isset($input['nation']))
		{
			$this->nation = $input['nation'];
		}
	}
}
?>