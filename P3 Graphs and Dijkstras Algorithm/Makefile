runTests: BackendDeveloperTests.class
	java -jar junit5.jar -cp . --select-class=BackendDeveloperTests

BackendDeveloperTests.class: BackendDeveloperTests.java FlightSystemBackendBD.class
	javac -cp .:junit5.jar BackendDeveloperTests.java

FlightSystemBackendBD.class: FlightSystemBackendBD.java FlightSystemBackendInterface.java DijkstraGraphWithMSTBD.class FlightReaderBD.class FlightBD.class
	javac FlightSystemBackendBD.java FlightSystemBackendInterface.java

DijkstraGraphWithMSTBD.class: DijkstraGraphWithMSTBD.java DijkstraGraphWithMSTInterfaceAE.java DijkstraGraph.class
	javac DijkstraGraphWithMSTBD.java DijkstraGraphWithMSTInterfaceAE.java

DijkstraGraph.class: DijkstraGraph.java BaseGraph.java GraphADT.java
	javac  DijkstraGraph.java BaseGraph.java GraphADT.java

FlightReaderBD.class: FlightReaderBD.java FlightReaderInterface.java
	javac FlightReaderBD.java FlightReaderInterface.java

FlightBD.class: FlightBD.java FlightInterface.java
	javac FlightBD.java FlightInterface.java

clean:
	rm -f *.class
	rm -f *~
