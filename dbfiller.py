# dbfiller.py

# fills my database from a CSV file

#import MySQLdb

class dbfiller:
	def __init__(self,file):
		self.file=open(file,'r')
		self.recipes=[]
		self.drinks=[]
		self.ingredients=[]
		self.drinkDict={}
		self.drinkcount=1
		self.ingredientDict={}
		self.ingredcount=1
		#self.data=self.file.readlines()
		self.db = MySQLdb.connect(read_default_file='~/.my.cnf',db='drinkbeta')
	
	def parseData(self):
		for line in self.file:
			#print line[:-1]
			self.parseLine(line[:-1])
		
	def parseLine(self,line):
		data = line.split(':')
		self.addNewDrink(data)
		drink = data[0]
		for item in data[4:]:
			#MySQL code to insert drink, ingredient into RECIPES table
			self.addIngredientIfNew(item)
			self.recipes.append((self.drinkDict[drink],self.ingredientDict[item]))
		
	def addNewDrink(self,line):
	#drink_name, glass, base, instructions, drink_ingredients, drink_id
		self.addIndex(line[0],self.drinkDict,self.drinkcount)
		self.drinkcount+=1
		newdrink = (line[0],line[1],line[2],line[3],line[4],self.drinkDict[line[0]])
		self.drinks.append(newdrink)
	
	def addIngredientIfNew(self,ingredient):
		if ingredient not in self.ingredientDict.keys():
			self.addIndex(ingredient,self.ingredientDict,self.ingredcount)
			self.ingredcount+=1
			self.ingredients.append((ingredient,self.ingredientDict[ingredient]))

	
	def addIndex(self,thing,dict,counter):
		dict[thing]=counter
		#counter=counter+1
	
	def printresults(self):
		print self.recipes
	
	def addToDB(self):
		cur = self.db.cursor()
		cur.executemany('INSERT INTO drinks (drink_name, glass, base, instructions, drink_ingredients, ingredient_id) VALUES (%s,%s,%s,%s,%s,%s)',self.drinks)
		cur.executemany('INSERT INTO ingredients (ingredient_name, ingredient_id) VALUES (%s, %s)', self.ingredients)
		cur.executemany('INSERT INTO recipes (drink_id, ingredient_id) VALUES (%s, %s)', self.recipes)
		cur.close()
		db.close()

def main():
	filler = dbfiller('datafile.txt')
	filler.parseData()
	filler.addToDB()
	
main()
	