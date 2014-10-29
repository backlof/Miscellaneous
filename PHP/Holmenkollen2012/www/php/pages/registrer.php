<?php

include RESOURCES_PATH.'validate.php';

$first = validateFirst();
$last = validateLast();
$email = validateEmail();
$password = validatePassword();
$adress = validateAdress();
$nationality = validateNationality();
$phone = validatePhone();

if($GLOBALS['properly_filled_in'])
{
    $person = new Person($_POST);
    $sql = 'INSERT INTO PERSON (NATIONALITY_ID, FIRSTNAME, LASTNAME, ADRESS, PHONE) VALUES ';
    $sql .= '("'.prepare($person->nationality_id).'", "'.prepare($person->first).'", "'.prepare($person->last).'", "'.prepare($person->adress).'", "'.prepare($person->phone).'");';
    $query = mysql_query($sql) or die(mysql_error());
    $person->id = mysql_insert_id();

    if( mysql_affected_rows() == 1 )
    {
        $admin = new Admin($_POST);
        $sql = 'INSERT INTO ADMIN(PASSWORD, EMAIL, PERSON_ID) VALUES ';
        $sql .= '("'.$admin->password.'", "'.prepare($admin->email).'", "'.$person->id.'");';
        $query = mysql_query($sql) or die(mysql_error());

        if( mysql_affected_rows() == 1 )
        {
            $_SESSION['person_id'] = $person->id;
            echo '<p id="feedback">Du har blitt lagt inn.</p>';
            timeout();
        }
        else
        {
            echo '<p id="feedback">Det har skjedd en feil i databasen.</p>';
        }   
    }
    else
    {
        echo '<p id="feedback">Noen felter er fylt inn feil</p>';
    }
}

?>
<script src="js/validation.js"></script>
<form action="?page=registrer" method="POST">
    <div id="loginField">
        <label for="first" class="label">Fornavn</label>
        <input type="text" id="firstBox" name="first" class="justify textbox" <?php if(isset($_POST['first'])){ echo 'value="'.$_POST['first'].'"'; } ?> />
        <p id="firstValid" class="valid"><?php echo $first; ?></p>

        <label for="last" class="label secondLabel">Etternavn</label>
        <input type="text" id="lastBox" name="last" class="justify textbox" <?php if(isset($_POST['last'])){ echo 'value="'.$_POST['last'].'"'; } ?> />
        <p id="lastValid" class="valid"><?php echo $last; ?></p>

        <label for="email" class="label secondLabel">E-mail</label>
        <input type="text" id="emailBox" name="email" class="justify textbox" <?php if(isset($_POST['email'])){ echo 'value="'.$_POST['email'].'"'; } ?> />
        <p id="emailValid" class="valid"><?php echo $email; ?></p>

        <label for="password"  class="label secondLabel">Passord</label>
        <input type="password" id="passwordBox" name="password" class="justify textbox" />
        <p id="passwordValid" class="valid"><?php echo $password; ?></p>

        <label for="adress"  class="label secondLabel">Adresse</label>
        <input type="text" id="adressBox" name="adress" class="justify textbox" <?php if(isset($_POST['adress'])){ echo 'value="'.$_POST['adress'].'"'; } ?> />
        <p id="adressValid" class="valid"><?php echo $adress; ?></p>

        <label for="nationality_id" class="label secondLabel">Land</label>
        <select name="nationality_id" id="nationality_idBox" class="justify select">
            <?php
            $sql = "SELECT ID as id, NATION as nation FROM NATIONALITY ORDER BY NATION ASC";
            $query = mysql_query($sql) or die(mysql_error());

            while( $res = mysql_fetch_array($query)) {
                $nation = new Nationality($res);
                echo '<option value="'. $nation->id .'">'. $nation->nation .'</option>';
            }
            ?>
        </select>
        <p id="nationality_idValid" class="valid"><?php echo $nationality; ?></p>

        <label for="phone" class="label secondLabel">Telefonnummer</label>
        <input type="text" id="phoneBox" name="phone" class="justify textbox" <?php if(isset($_POST['phone'])){ echo 'value="'.$_POST['phone'].'"'; } ?> />
        <p id="phoneValid" class="valid"><?php echo $phone; ?></p>
        
        <div id="submit">
            <input type="submit" class="button" id="submitButton" value="Logg inn" />
        </div>
    </div>
</form>
