


class addtags:
	def __init__(self,file):
		self.file=open(file,'r')
		
		
		
	def parseFile(self):
		for line in self.file:
			line = line.split()
			line[0]="<item>"
			line[-1]="</item>"
			print " ".join(line)
			

tagadder = addtags('ingredients.txt')
tagadder.parseFile()
			