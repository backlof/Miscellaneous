<?php
function prepare($string)
{

	$string = stripslashes($string);
   $string = mysql_real_escape_string($string);

   if($string == '')
   {
   	return NULL;
   }
   else
   {
   	return $string;
   }
}

function timeout()
{
   echo '<script>$(document).ready(function() { timeout(".",2000); });</script>';
}

?>