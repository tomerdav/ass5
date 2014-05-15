# 208949008	316122316
# blachag	davidot2

compile: bin
	javac -d bin -cp biuoop-1.2.jar src/*/*.java src/*.java

run:
	java -cp biuoop-1.2.jar:bin Ass5Game
 
bin:
	mkdir bin
