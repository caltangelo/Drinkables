<?php

$hostname = "mysql.calebsantangelo.com";
$username = "androiduser";
$password = "securepassword";
$database = "drinkables_db";

$link = mysql_connect($hostname,$username,$password);
mysql_select_db($database) or die("Unable to select database");

?>
