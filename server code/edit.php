<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="form.css" />
</head>
<body>
<form action="save.php" method="post">
<?php
/*
Prints a form. If a drink was selected from form.php, the recipe is filled in
for the user to edit. Otherwise a new drink is created in the database.
Ingredients are separated by colons(:) and each ingredient is seperated
its units with a hash mark (#). The first ingredient is listed as the
cocktail's base so order accordingly.
*/
require "link.php";
$id = $_GET['drink'];
print "<input type='hidden' name='id' value={$id} />";

function getRecipe($id, $link){
	if (!($result = @ mysql_query("SELECT drink_name, drink_ingredients, instructions, glass FROM drinks WHERE drink_id = {$id}",
 		$link))) die("Error Querying Database");
 		
 	$row = @ mysql_fetch_array($result, MYSQL_ASSOC);
	return $row;
}

function drinkname($name){
	print "<div>";
	print "Drink Name:<br/> <input type='text' name='drink_name' value='{$name}' />";
	print "</div>";
	print "<br/>";
}

function ingredients($ingreds){
	print "<div>";
	print "Ingredients:<br/> <textarea name='ingredients' rows=2 cols=45>";
	print "$ingreds";
	print "</textarea>";
	print "</div>";
	print "<br/>";
}

function instructions($instruct){
	print "<div>";
	print "Instructions:<br/> <textarea name='instruct' rows=4 cols=45>";
	print "$instruct";
	print "</textarea>";
	print "</div>";
	print "<br/>";
}

function glass($glass){
	print "<div>";
	print "Drinking Vessel:<br/> <input type='text' name='glass' value='{$glass}' />";
	print "</div>";
	print "<br/>";
}

if (is_null($id) or $id=='0'){
	$drink['drink_name']="";
	$drink['drink_ingredients']="";
	$drink['instructions']="";
	$drink['glass']="";
} else {
	$drink = getRecipe($id, $link); 
}
drinkname($drink['drink_name']);
ingredients($drink['drink_ingredients']);
instructions($drink['instructions']);
glass($drink['glass']);
?>
<input type='submit' value='Submit' /></br>
</form>
</body>
</html>