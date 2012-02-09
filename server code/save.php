<?php
/*
Adds any new ingredients not already in database, then adds the recipe 
to both the recipes and drinks tables. If it was an existing recipe 
(it has a drink_id from the database) the process is the same: new ingredients
are added, all entries in the recipes table are deleted and replaced. The
only difference is the last step, which is a MySQL UPDATE instead of 
INSERT. Displays confirmation message in addition to giving relevant error
messages for malformed input.
*/
require "link.php";


$name=cleanAndQuote(strip_tags($_POST['drink_name']));
$ingredients=strip_tags($_POST['ingredients']);
$instructions=cleanAndQuote(strip_tags($_POST['instruct']));
$glass=cleanAndQuote(strip_tags($_POST['glass']));
$id=strip_tags($_POST['id']);

function cleanAndQuote($str){
	return "'".mysql_real_escape_string($str)."'";
}

function sqlSelect($columns,$tables,$con){
	if (!($result = @ mysql_query("SELECT {$columns} from {$tables}",
 		$con))) die("select error: ".mysql_error($con));		
 	return $result;
}

function sqlInsert($columns,$values,$table,$con){
	if (!($result = @ mysql_query("INSERT INTO {$table} {$columns} VALUES {$values}",
 		$con))) die("insert error: ".mysql_error($con));
 		 return $result;
}

function sqlUpdate($name,$ingreds,$glass,$instruct,$id,$base,$con){
	if (!($result = @ mysql_query("UPDATE drinks SET drink_name={$name}, glass={$glass},
	drink_ingredients={$ingreds}, instructions={$instruct}, base_id={$base} WHERE drink_id={$id}",
 		$con))) die("update error: ".mysql_errno($con).mysql_error($con));		
 	return $result;
}

function recipesClear($id,$con){
	if (!($result = @ mysql_query("DELETE FROM recipes WHERE drink_id={$id}",
 		$con))) die("delete error: ".mysql_error($con));		
 	return $result;
}

//Returns the max value in a certain column when it shares a name with its table
//(drink_id in drinks, or ingredient_id in ingredients)
function getMaxID($root,$con){
	$col = $root."_id";
	$foo = sqlSelect("MAX(".$col.")",$root."s",$con);
	$row = @ mysql_fetch_array($foo, MYSQL_BOTH);
	return $row[0];
}

function parseIngredients($ingredInput){
	//echo $ingredInput, substr_count($ingredInput,":");
	if(substr_count($ingredInput,":")<1){
	throw new Exception("all ingredients must be separated by a colon (:).");
	}
	$foo = explode(":",$ingredInput);
	return array_map("removeUnits", $foo);
}

function removeUnits($string){
	$tp = explode("#",$string);
	if(!$tp[1]){
	throw new Exception("remember to separate each ingredient from its units with a pound sign (#).");
	}
	return $tp[1];
}

function formatRecipe($drink,$ingredient){
	return "(".$drink.",".$ingredient.")";
}

//pulls all ingredients from db and converts to lowercase to make comparisons easier
function getIngredients($con){
	$results = sqlSelect("ingredient_name as name, ingredient_id as id","ingredients ORDER BY ingredient_id",$con);
	while ($row = @ mysql_fetch_array($results, MYSQL_ASSOC)){
	$nameLowered = mb_strtolower($row['name']);
	$list[$nameLowered]=$row['id'];
	}
	return $list;
}

function addIngredients($con,$ingredientsToAdd){
	$allIngredients = getIngredients($con);
	$maxid = end(array_values($allIngredients));
	try {
	$currentRecipeIngredients = parseIngredients($ingredientsToAdd);
	} catch(Exception $e) {
	die('Not Formatted correctly: '.$e->getMessage());
	}
	foreach($currentRecipeIngredients as $currentIngred){
		$lower = mb_strtolower($currentIngred);
		//if not in db, Inc id counter, add ingredient to ingredients table
		if(!in_array($lower,array_keys($allIngredients))){
		$maxid+=1;
		sqlInsert("(ingredient_name, ingredient_id)","('".$currentIngred."',".$maxid.")","ingredients",$con);
		$allIngredients[$lower] = $maxid;
		}
	} return nameToIDConvert($currentRecipeIngredients,$allIngredients);
}

function nameToIDConvert($currList,$map){
	$result = array();
	foreach($currList as $i){
		$lower = mb_strtolower($i);
		if(in_array($lower, array_keys($map))){
		$result[]=$map[$lower];
		}
	}
	return $result;
}

function buildRecipeInsert($drinkID,$ingredientIDs){
	$recipes = array();
	foreach($ingredientIDs as $ingredient){
	$recipes[] = "(".$drinkID.",".$ingredient.")";
	}
	return implode(",",$recipes);
}

//Calculate drink_id, add with relevant data to drinks table
function addToDrinks($name,$instructions,$glass,$ingredients,$baseID,$con){
	$drink_id = getMaxID("drink",$con) + 1;
	$insert = implode(",",array($name,$drink_id, $ingredients,$instructions,$glass,$baseID));
	sqlInsert("(drink_name, drink_id, drink_ingredients, instructions, glass, base_id)","(".$insert.")","drinks",$con);
	
	return $drink_id;
}

function addToRecipes($drink_id,$ingredient_idList,$con){
	$recipeAsString = buildRecipeInsert($drink_id,$ingredient_idList);
	sqlInsert("(drink_id,ingredient_id)",$recipeAsString,"recipes",$con);
}

function newRecipe($name,$instructions,$glass,$ingredients,$con){
	$ingredIDs = addIngredients($con,$ingredients);
	$id = addToDrinks($name,$instructions,$glass,cleanAndQuote($ingredients),$ingredIDs[0],$con);
	addToRecipes($id,$ingredIDs,$con);
	success($id);
}

function updateRecipe($name,$instructions,$glass,$ingredients,$id,$con){
	$ingredIDs = addIngredients($con,$ingredients);
	sqlUpdate($name,cleanAndQuote($ingredients),$glass,$instructions,$id,$ingredIDs[0],$con);
	//delete from recipes, then add
	recipesClear($id,$con);
	addToRecipes($id,$ingredIDs,$con);
	success($id);
}

function main($name,$instructions,$glass,$ingredients,$link,$id){
	if($id>0){
	updateRecipe($name,$instructions,$glass,$ingredients,$id,$link);
	}else{
	newRecipe($name,$instructions,$glass,$ingredients,$link);
	}
}

function success($id){
	echo "<h1>drink #{$id} successfully added</h1>";
}

main($name,$instructions,$glass,$ingredients,$link,$id);
?>