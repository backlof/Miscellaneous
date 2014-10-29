<?php
// SQL innloggingsdetaljer
define("SQL_SERVER", "localhost");
define("SQL_USER", "root");
define("SQL_PASS", "");
define("SQL_DB", "oblig");

// Kobler til database
$db_server = mysql_connect(SQL_SERVER, SQL_USER, SQL_PASS) or die("Kunne ikke koble til MySQL");
mysql_select_db(SQL_DB) or die('Kunne ikke koble til databasen "'. SQL_DB .'"');
?>