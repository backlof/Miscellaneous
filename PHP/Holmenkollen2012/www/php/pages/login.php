<?php

include RESOURCES_PATH.'validate.php';

$email = validateEmail();
$password = validatePassword();

if($GLOBALS['properly_filled_in'])
{
    $admin = new Admin($_POST);

    $sql = 'SELECT PERSON_ID AS person_id FROM ADMIN WHERE PASSWORD = "'.$admin->password.'" AND EMAIL = "'.prepare($admin->email).'";';
    $query = mysql_query($sql) or die(mysql_error());

    if(mysql_affected_rows() == 1 && $res = mysql_fetch_array($query))
    { 
        $_SESSION["person_id"] = $res["person_id"];
        header('Location: .');
    }
    else
    {
        echo '<p id="feedback">Feil brukernavn eller passord.</p>';
    }
}

?>
<script src="js/validation.js"></script>
<form action="?page=login" method="POST">
    <div id="loginField">
        <label for="email" class="label">E-mail</label>
        <input type="text" id="emailBox" name="email" class="justify textbox" <?php if(isset($_POST['email'])) { echo 'value="'.$_POST['email'].'"'; } ?> />
        <p id="emailValid" class="valid"><?php echo $email; ?></p>

        <label for="password"  class="label secondLabel">Passord</label>
        <input type="password" id="passwordBox" name="password" class="justify textbox" />
        <p id="passwordValid" class="valid"><?php echo $password; ?></p>

        
        <div id="submit">
            <a href="?page=registrer" id="registrer">Registrer bruker</a>
            <input type="submit" class="button" id="submitButton" value="Logg inn" />
        </div>
    </div>
</form>