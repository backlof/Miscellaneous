<script src="js/datepicker.js"></script>

<form action="<?php echo CONTROLLERS_PATH."submit_event.php"; ?>" method="POST">
	<div class="inputrow">
		<label for="type_id">Øvelse</label>
		<select name="type_id" class="justify">
			<?php
			$sql = "SELECT ID as id, TYPE as type FROM TYPE ORDER BY TYPE ASC";
			$query = mysql_query($sql) or die(mysql_error());

			while( $res = mysql_fetch_array($query)) {
				$type = new Type($res);
				echo '<option value="'. $type->id .'">'. $type->type .'</option>';
			}
			?>
		</select>
	</div>
	<div class="inputrow">
		<label for="date">Dato</label>
		<input type="date" name="date" class="justify" id="datetime" placeholder="YYYY-MM-DD" />
	</div>
	<input type="submit" name="SubmitEvent" id="submitButton" value="Registrer" />
</form>