<?php
//SJEKKER AT DE PARAMETERENE SOM BEHØVES ER MED
if(!isset($_GET['id']))
{
    header('location: 404.php');//hvis .htaccess fungerer slik du vil, kan du gå til en adresse som ikke fungerer
}
else
{
	// OM ØVELSE
	$sql = "SELECT EVENT.ID AS id, TYPE.TYPE AS type, DATE as date FROM EVENT INNER JOIN TYPE ON (TYPE.ID = TYPE_ID) ";
	$sql .= "WHERE EVENT.ID = ".$_GET['id']."";
	$query = mysql_query($sql) or die(mysql_error());

	if(mysql_affected_rows() == 1 && $res = mysql_fetch_array($query))
	{
		$event = new Event($res);
		echo '<h2>'.$event->type.'</h2>';
		echo '<p>'.$event->date.'</p>';

		// OM UTØVERE
		$sql = 'SELECT PERSON.ID AS id, NATIONALITY.NATION AS nationality, FIRSTNAME AS first, LASTNAME AS last ';
		$sql .= 'FROM NATIONALITY INNER JOIN PERSON ON (NATIONALITY.ID = NATIONALITY_ID) INNER JOIN PARTICIPANT ON (PERSON.ID = PERSON_ID) ';
		$sql .= 'WHERE PARTICIPANT.EVENT_ID = '.$event->id;
		
		$query = mysql_query($sql) or die(mysql_error());

		echo '<h3>Utøvere</h3>';
		if(mysql_affected_rows() > 0)
		{
			echo '<ul>';
			while($res = mysql_fetch_array($query))
			{
				$person = new Person($res);
				echo '<li>'.$person->first.' '.$person->last.' fra '.$person->nationality.'</li>';
			}
			echo '</ul>';
		}
		else
		{
			echo '<p>Det er ingen deltakere</p>';
		}

		// OM TILSKUERE
		$sql = 'SELECT FIRSTNAME AS first, LASTNAME AS last, TICKET.ID AS ticket ';
		$sql .= 'FROM NATIONALITY INNER JOIN PERSON ON (NATIONALITY.ID = NATIONALITY_ID) INNER JOIN TICKET ON (PERSON.ID = PERSON_ID) ';
		$sql .= 'WHERE TICKET.EVENT_ID = '.$event->id;
		
		$query = mysql_query($sql) or die(mysql_error());

		echo '<h3>Tilskuere</h3>';
		if(mysql_affected_rows() > 0)
		{
			echo '<ul>';
			while($res = mysql_fetch_array($query))
			{
				$person = new Person($res);
				echo '<li>'.$person->first.' '.$person->last.' med billettnummer '.$person->ticket.'</li>';
			}
			echo '</ul>';
		}
		else
		{
			echo '<p>Det er ingen tilskuere</p>';
		}
	}
	else
	{
		echo '<p>';
		echo 'Det er ingen øvelse med ID ';
		echo $_GET['id'];
		echo '</p>';
	}
}
?>






<a href="#">Link</a>