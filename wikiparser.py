#Datasmart.py
#Caleb Santangelo

# A web crawler to extract cocktail recipe information 

import sys,urllib2
from pyquery import PyQuery as pq

class wikiParser:
	def __init__(self):
		self.url="http://en.wikipedia.org/wiki/IBA_Official_Cocktail"
		self.user="Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.2.18) Gecko/20110628 Ubuntu/10.10 (maverick) Firefox/3.6.18"
		self.links = None
		self.response = None
		self.html = None
		self.selectors = ["caption.fn","td.ingredient li","table.infobox.hrecipe tr"]
		self.onStart()

	
	def onStart(self):
		#self.connect(self.url)
		self.getList()
		
	def getList(self):
		from urlparse import urljoin
		linkpage = self.connect(self.url)
		self.linkSelector(linkpage)
		urls = []
		for link in self.links:
			if '#' not in link.attr('href'):
				urls.append( link.attr('href'))
		urls = [urljoin(self.url,url) for url in urls]
		self.links = urls	
	
	def linkSelector(self,page):
		f = page
		d = pq(f)
		result_selector = "table.multicol a"
		elements = d(result_selector)
		results = []
		elements.each(lambda i,result: results.append(pq(result)))
		self.links = results
		
	def connect(self,url):
		req = urllib2.Request(url)
		req.add_header('User-agent',self.user)
		res = urllib2.urlopen(req).read()
		return pq(res)
				
	def unnamedFunction(self, result):
		glass_head = 'Standard drinkware'
		instructions_head = "Preparation"
		glass = 'any'
		instruc = 'Mix all ingredients and stir. Add ice to taste'
		for i in range(len(result)):
			if result.eq(i)('th').text() == glass_head:
				glass = result.eq(i)('td').text()
			elif result.eq(i)('th').text() == instructions_head:
				instruc = result.eq(i)('td').text()
		return instruc+";"+glass
		
	'''
	applies all selectors to all links and outputs to a file
	'''
	def writeFile(self):
		f = open('datafile.txt','w')
		for link in self.links:
			if not self.assertIBA(link):
				continue
			print link
			out = self.drinkMaker(link)
			f.write(out.encode('utf-16','replace'))
		f.close()

	def drinkMaker(self,link):
		line=""
		for select in self.selectors:
			line = line + self.dataGrab(link,select)+";"
		return line[:-1]+"\n"
		
	def dataGrab(self,link,select):
		html = self.connect(link)
		d = pq(html)
		results = d(select)
		if select=="td.ingredient li":
			ingredients = []
			results.each(lambda i, thing: ingredients.append(pq(thing).text()))
			return ":".join(ingredients)
		elif select=="table.infobox.hrecipe tr":
			return self.unnamedFunction(results)
		else:
			return results.text()

	'''
	makes sure recipe is formatted properly and skips if not
	'''
	def assertIBA(self,page):		
		html = self.connect(page)
		d = pq(html)
		results = d("table.infobox.bordered.hrecipe")
		if len(results)==0:
			return False
		return True

def main():
	foo = wikiParser()
	foo.writeFile()
	
main()
	