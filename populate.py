# populate.py

# populates my drink database from a colon separated value (CSV) file
# required format: name:glass:recipe:instructions:base:ingredient1:...:ingredientN

import sys, MySQLdb

def openDBconnection():
	#code goes here
	db = MySQLdb.connect(read_default_file='~/.my.cnf',db='drinkbeta')
	return db

def openParse(filename):
	f = open(filename,'r')
	for line in f.read():
	# separate by colons and do shit
		parseLine(line[:-1])
	

def parseLine(line):
	cur = conn.cursor()
	data = line.split(':')
	name = (data[0],)
	glass = (data[1],)
	recipe = (data[2],)
	instructions = (data[3],)
	base = (data[4],)
	input=[name,glass,base,instructions,recipe]
	#MySQL code to insert the drink into DRINKS table
	drinkToAdd = queryFromData(name,glass,recipe,instructions)
	cur.execute('INSERT INTO drinks (drink_name, glass, base, instructions, drink_ingredients) VALUES (%s,%s,%s,%s,%s)',input)
	temp = []
	for item in data[4:]:
		temp.append((drink, item))
		#MySQL code to insert drink, ingredient into RECIPES table
		if item not in all_ingredients:
			global all_ingredients
			all_ingredients.append((item,))
		cur.executemany('INSERT INTO recipes (drink_id, ingredient_id) VALUES (%s, %s)',temp)
	cur.close()

def insertTupleList(list):
	toinsert=""
	for tuple in list:
		toinsert = toinsert+" ,"+str(tuple)
	return toinsert

def queryFromData(name,glass,recipe,instructions,base):
	tuple=(name, glass, recipe, instructions, base)
	return 'INSERT INTO drinks VALUES '+str(tuple)
	
def main():
	conn = openDBconnection()
	openParse('datafile.txt')
	cur = conn.cursor()
	cur.executemany('INSERT INTO ingredients (ingredient_name) VALUES (%s)', all_ingredients)
	cur.close()
	conn.close()
	
all_ingredients=[]
