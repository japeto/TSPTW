
# Makefile by JAPeTo <jeffersonamado@gmail.com>
#

#~ JFlags = -g -Xlint -verbose
JFlags = -g -nowarn -deprecation 
RM = rm -rf
MK = mkdir -p
JavaCompiler = javac
JavadocGenerator =javadoc
JavaExecute = java
JavadocWindowTitle = TSPTW
JavadocDocTitle = TSPTW Documentation

editor= geany

SourceDir = ./src/
TestDir = ./src/test
LibDir = ./lib/lpsolve55j.jar
BinDir = ./bin/
DocsDir = ./doc/
ClassDir = ./class/

MainClass = view/TSPTW.java
MainRun = view/TSPTW

JavadocOptions  =-d $(DocsDir) \
		 -sourcepath $(SourceDir) algorithms data view \
		 -author \
		 -package \
		 -use \
		 -splitIndex \
		 -version \
		 -windowtitle $(JavadocWindowTitle) \
		 -doctitle $(JavadocDocTitle) 
		 
JavaCOptions =  -d $(ClassDir)\
		 -classpath $(SourceDir):$(LibDir) \
		 $(SourceDir)/$(MainClass)
		 
JavaOptions = -Xmx1g -XX:MaxPermSize=512m -classpath $(LibDir):$(ClassDir) \
		$(MainRun)

build: 
	$(MK) $(ClassDir)
	$(JavaCompiler) $(JFlags) $(JavaCOptions)
#~ #javac -cp .:./:../lib/lpsolve55j.jar view/TSPTWMainWindow.java -d ../class/

run:
	make build
	$(JavaExecute) $(JavaOptions)
	
debug:
	make build
	$(JavaExecute) $(JavaOptions) --debug

doc:
	$(JavadocGenerator) $(JavadocOptions)
#~ javadoc -d ./doc/ -sourcepath src/ algorithms data view 

clean:
	$(RM) $(DocsDir) $(ClassDir) $(BinDir)
