<!DOCTYPE HTML PUBLIC
"-//W3C//DTD HTML 4.0 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd" >
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html;" />
      <title>Drinks</title>
   </head>
   <body>

      <table>

<?php
require "link.php";

$opcode = $_GET['op'];
$column = $_GET['col'];
$table = $_GET['table'];
$cabinet = $_GET['cab'];

if ($cabinet != null){
	if (!($result = @ mysql_query("DELETE FROM cabinet",
 		$link))) die("Couldn't run query");
} 		
 	

if (!($result = @ mysql_query("SELECT $column from $table",
 $link)))
 die("Couldn't run query");

while ($row = @ mysql_fetch_array($result, MYSQL_ASSOC))
{
print "<tr>\n";
foreach($row as $data)
 print "\t<td>{$data}</td>\n";

print "</tr>\n";
}
?>
</table>
</body>
</html>
