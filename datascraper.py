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
	result_selector = ".content tr td a"
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
        urls.append( link.attr('href'))
    urls = [urljoin(source,url) for url in urls]
    return urls	
	
'''
returns information depending on selector specified. For the ingredients list
more formatting needs to be done
'''
def superFunction(page,selector):
	response = urllib2.urlopen(page)
	html = response.read()
	d = pq(html)
	results = d(selector)
	if selector=="li span":
		ingredients = []
		results.each(lambda i, thing: ingredients.append(pq(thing).text()))
		return ":".join(ingredients)
	else:
		return results.text()

'''
applies all selectors to all links and outputs to a file
'''
def writeFile(selectors,links):
	f = open('datafile.txt','w')
	for link in links:
		print link
		line = ""
		for select in selectors:
			line = line + superFunction(link,select)+":"
		line=line[:-1]+'\n'
		f.write(line.encode('ascii','replace'))
	f.close()


drink_page = "http://www.drinknation.com/drinks/best"
#This isn't used, but they correspond with what the selectors are used for
things_i_want=['name','glass','recipe','instructions','ingredients']
selectors = ["span#r_name","a.glassTip span","ul.small.r_ingredients","div#r_instructions p","li span"]
page = getSite(drink_page)
linklist = get_links(page,drink_page)

writeFile(selectors,linklist)