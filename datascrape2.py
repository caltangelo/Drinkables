#Datascraper.py
#Caleb Santangelo

# A web crawler to extract cocktail recipe information 

import sys,urllib2
from pyquery import PyQuery as pq

def getSite(url):
    user_agent = "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.2.18) Gecko/20110628 Ubuntu/10.10 (maverick) Firefox/3.6.18"

    req = urllib2.Request(url)
    req.add_header('User-agent',user_agent)
    f = urllib2.urlopen(req).read()
    return f

def get_element(page):
	f = page
	d = pq(f)
	result_selector = "table.multicol a"
	elements = d(result_selector)
	results = []
	elements.each(lambda i,result: results.append(pq(result)))
	return results
'''
returns a list object containing all of the links to be visited
'''
def get_links(page,source):
    from urlparse import urljoin
    e = get_element(page)
    urls = []
    for link in e:
    	if '#' not in link.attr('href'):
        	urls.append( link.attr('href'))
    urls = [urljoin(source,url) for url in urls]
    return urls	
	
'''
returns information depending on selector specified. For the ingredients list
more formatting needs to be done
'''
def superFunction(page,selector):
	html = getSite(page)
	d = pq(html)
	results = d(selector)
	if selector=="td.ingredient li":
		ingredients = []
		results.each(lambda i, thing: ingredients.append(pq(thing).text()))
		return ":".join(ingredients)
	elif selector=="table.infobox.hrecipe td":
		return wikiCollate(results)
	else:
		return results.text()

'''
applies all selectors to all links and outputs to a file
'''
def writeFile(selectors,links):
	f = open('testfile.txt','w')
	for link in links:
		if not assertIBA(link):
			continue
		print link
		out = drinkMaker(link,selectors)
		f.write(out.encode('utf-16','replace'))
	f.close()

def drinkMaker(link,selectors):
	line=""
	for select in selectors:
		line = line + superFunction(link,select)+":"
	return line[:-1]+"\n"

'''
makes sure recipe is formatted properly and skips if not
'''
def assertIBA(page):		
	html = getSite(page)
	d = pq(html)
	results = d("table.infobox.bordered.hrecipe")
	if len(results)==0:
		return False
	return True
	

drink_page = "http://en.wikipedia.org/wiki/IBA_Official_Cocktail"
#This isn't used, but they correspond with what the selectors are used for
things_i_want=['name','glass','instructions','ingredients']
selectors = ["caption.fn","table.infobox.hrecipe td","td.ingredient li"]
page = getSite(drink_page)
linklist = get_links(page,drink_page)

writeFile(selectors,linklist)