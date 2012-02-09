<?php
//Facilitates all client/database communication
require "link.php";

$cabinet = $_POST['cab'];
$json_query = $_POST['Query'];


function extractQueryFromJSON($jsonq){
	$query = json_decode($jsonq, true);
	return $query;
}
function cabListToString($cablist){
	$res = implode("),(",$cablist);
	return "(".$res.")";
}

function updateCabinet($values,$con){	
	if (!($result = @ mysql_query("DELETE FROM cabinet",
 		$con))) die("Couldn't delete right");
 	
 	if (!($result = @ mysql_query("INSERT INTO cabinet VALUES {$values}",
 		$con))) die("Couldn't insert right");
}

function runCabQuery($con,$cab){
	updateCabinet($cab,$con);
	
	$innerclause = "SELECT drink_id FROM recipes r LEFT OUTER JOIN cabinet c on 
	c.ingredient_id=r.ingredient_id WHERE c.ingredient_id is null";
	
	if (!($result = @ mysql_query("SELECT DISTINCT drink_id as id, drink_name as name FROM drinks d 
	WHERE d.drink_id NOT IN ($innerclause);", $con))) die("Couldn't run queery");
	
	return $result;
}

function sendResults($results){
	$json= array();
	$count = 0;
	while ($row = @ mysql_fetch_array($results, MYSQL_ASSOC))
	{
	$json[$count]="{$row["name"]}##{$row["id"]}";
	$count++;
	}
	return $json;
}	

function deciderFunction($results){
	if(mysql_num_fields($results)>2){
	$row = mysql_fetch_array($results, MYSQL_ASSOC);
	return $row;
	} else {
	$table = sendResults($results);
	return $table;
	}
}

function assertUnicode(){
	mysql_query('SET CHARACTER SET utf8');
}

assertUnicode();

$query = extractQueryFromJSON($json_query);

if($cabinet!=null){
$result = runCabQuery($link,$cabinet);
} else {
	if (!($result = @ mysql_query("SELECT {$query["column"]} from 
	{$query["table"]} {$query["where"]} ORDER BY name", $link)))
		die($cabinet);}


$tosend = deciderFunction($result);
print json_encode($tosend);
?>