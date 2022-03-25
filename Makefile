run: compile

compile:
	javac GraphADT.java
	javac CS400Graph.java
	javac Flight.java
	javac DataAccess.java
	javac IBackEnd.java
	javac BackEnd.java
	javac AppPages.java
	javac FlightFinder.java
	javac -cp .:junit5.jar IOTest.java
	javac -cp .:junit5.jar BackendTest.java

test:
	java -jar junit5.jar --class-path . --scan-classpath --details tree

upload: clean compile upload.sh
	./upload.sh

clean:
	$(RM) *.class
