#Upload.py
#Takes a datafile of form name;unit#ingredient:...:unit#ingredientN;instructions;glassware
#and uploads to drinkables_db using script at calebsantangelo.com/save.php

import urllib,urllib2

class uploader:
	def __init__(self,file):
		self.file=open(file,'r')
		self.url="http://calebsantangelo.com/save.php"
		self.user="Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.2.18) Gecko/20110628 Ubuntu/10.10 (maverick) Firefox/3.6.18"
		self.response = None
		self.html = None
		self.parseData()

	def connect(self,url):
		req = urllib2.Request(url)
		req.add_header('User-agent',self.user)
		res = urllib2.urlopen(req).read()
		return pq(res)
		
	def parseData(self):
		for line in self.file:
			#print line[3:-1]
			self.addDrink(line[:-1])
			return
			
	def addDrink(self,line):
		drink={}
		parsedLine = line.split(";")
		drink['drink_name']=parsedLine[0]
		drink['ingredients']=parsedLine[1]
		drink['instruct']=parsedLine[2]
		drink['glass']=parsedLine[3]
		postdata = urllib.urlencode(drink)
		#print drink['ingredients']
		self.submit(postdata)
		
	def submit(self,string):
		page = urllib2.urlopen(self.url,string)
		pagesource = page.read()
		print pagesource
			

def main():
	upper = uploader('datafile.txt')

	
main()