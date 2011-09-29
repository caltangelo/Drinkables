# cabinet.py
# this script ask the user for any number of ingredient_ids to populate the cabinet table
# then tests the query

import MySQLdb

input = raw_input('List any number of integers less than 107, separated by commas: \n')
ingredients = input.split(',')
toAdd=[]
for i in ingredients:
	toAdd.append((i,))

db = MySQLdb.connect(read_default_file='~/.my.cnf',db='drinkbeta')
cur = db.cursor()
cur.execute("""DELETE FROM cabinet""")

cur.executemany("""INSERT INTO cabinet VALUES (%s)""", toAdd)

cur.execute("""SELECT DISTINCT drink_id from drinks d
WHERE d.drink_id NOT IN (
SELECT drink_id from recipes r1 LEFT OUTER JOIN cabinet c1 on c1.ingredient_id=r1.ingredient_id
WHERE c1.ingredient_id IS NULL)""")

blah = list(cur.fetchall())
for i in blah:
	cur.execute("""SELECT drink_name from drinks where drink_id = (%s)""", i[0])
	print cur.fetchone()[0]

cur.close()
db.close()
