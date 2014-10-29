<form action="<?php echo CONTROLLERS_PATH."submit_ticket.php"; ?>" method="POST">
	<div class="inputrow">
		<label for="first">Fornavn</label>
		<input type="text" name="first" class="justify" />
	</div>
	<div class="inputrow">
		<label for="last">Etternavn</label>
		<input type="text" name="last" class="justify" />
	</div>
	<div class="inputrow">
		<label for="adress">Adresse</label>
		<input type="text" name="adress" class="justify" />
	</div>
	<div class="inputrow">
		<label for="phone">Telefonnummer</label>
		<input type="text" name="phone" class="justify" />
	</div>
	<div class="inputrow">
		<label for="nationality_id">Land</label>
		<select name="nationality_id" class="justify">
			<?php
			$sql = "SELECT ID as id, NATION as nation FROM NATIONALITY ORDER BY NATION ASC";
			$query = mysql_query($sql) or die(mysql_error());

			while( $res = mysql_fetch_array($query)) {
				$nation = new Nationality($res);
				echo '<option value="'. $nation->id .'">'. $nation->nation .'</option>';
			}
			?>
		</select>
	</div>
	<div class="inputrow">
		<label for="event_id">Øvelse</label>
		<select name="event_id" class="justify">
			<?php
			$sql = "SELECT EVENT.ID as id, TYPE as type, DATE as date FROM EVENT INNER JOIN TYPE ON (TYPE_ID = TYPE.ID) ORDER BY DATE ASC";
			$query = mysql_query($sql) or die(mysql_error());

			while( $res = mysql_fetch_array($query)) {
				$event = new Event($res);
				echo '<option value="'. $event->id .'">'. $event->type . ' ' . $event->date .'</option>';
			}
			?>
		</select>
	</div>
	<input type="submit" name="SubmitEvent" id="submitButton" value="Registrer" />
</form>