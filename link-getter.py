#!/usr/bin/python
from datetime import datetime
from os import mkdir

import sys,urllib2
from pyquery import PyQuery as pq


def load_page(link):
    '''
    Loads the target link, returns a string of the page contents
    '''
    user_agent = "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.2.18) Gecko/20110628 Ubuntu/10.10 (maverick) Firefox/3.6.18"

    req = urllib2.Request(url=link)
    req.add_header('User-agent',user_agent)
    f = urllib2.urlopen(req).read()
    return f

def get_element(page):
    f = page
    d = pq(f)
    result_selector = '.content tr td a'
    elements = d(result_selector)
    results = []
    elements.each(lambda i,result: results.append(pq(result)))
    return results

def get_links(page,source):
    from urlparse import urljoin
    e = get_element(page)
    urls = []
    for link in e:
        urls.append( link.attr('href'))
    urls = [urljoin(source,url) for url in urls]
    return urls

    

drink_page = "http://www.drinknation.com/drinks/best"
page = load_page(drink_page)

links = get_links(page,drink_page)
print links
