<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
</head>
<body>
<form action="edit.php">
<select name="drink">
<option value="0">Add new recipe</option>
<?php
/* 
User selects to edit existing recipe or add their own via save.php script
*/
require "link.php";

if (!($result = @ mysql_query("SELECT drink_name as dname, drink_id as id FROM drinks ORDER BY dname",
 		$link))) die("Couldn't insert right");

	while ($row = @ mysql_fetch_array($result, MYSQL_ASSOC))
	{
	print "<option value={$row["id"]}>{$row["dname"]}</option>";
	}	 		
?>
</select>
<input type='submit' value='Submit' /></br>
</form>
</body>
</html>
