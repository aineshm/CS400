run: NetflixSearchApp.class
	java NetflixSearchApp

runTests: runAlgorithmEngineerTests runBackendDeveloperTests runFrontendDeveloperTests runDataWranglerTests 

runDataWranglerTests: NetflixDataDW.class NetflixDataReaderDW.class DataWranglerTests.class
	java -jar junit5.jar -cp . --select-class=DataWranglerTests

DataWranglerTests.class: DataWranglerTests.java NetflixDataDW.class NetflixDataReaderDW.java
	javac -cp .:junit5.jar DataWranglerTests.java

NetflixDataDW.class: NetflixDataDW.java NetflixDataInterfaceDW.class NetflixDataReaderDW.class
	javac NetflixDataDW.java

NetflixDataReaderDW.class: NetflixDataReaderDW.java NetflixDataReaderInterfaceDW.class;
	javac NetflixDataReaderDW.java

NetflixDataInterfaceDW.class: NetflixDataInterfaceDW.java
	javac NetflixDataInterfaceDW.java

NetflixDataReaderInterfaceDW.class: NetflixDataReaderInterfaceDW.java
	javac NetflixDataReaderInterfaceDW.java

NetflixSearchApp.class: NetflixSearchApp.java NetflixRBTBackendBD.class NetflixFrontendFD.class NetflixDataDW.class  NetflixDataReaderDW.class
	javac NetflixSearchApp.java

runBackendDeveloperTests: BackendDeveloperTests.class
	java -jar junit5.jar -cp . --select-class=BackendDeveloperTests

BackendDeveloperTests.class: BackendDeveloperTests.java NetflixRBTBackendBD.class test.txt
	javac -cp .:junit5.jar BackendDeveloperTests.java

NetflixRBTBackendBD.class: NetflixRBTBackendBD.java NetflixRBTBackendInterface.java DatabaseRBTBD.class NetflixDataReaderBD.class NetflixDataBD.class
	javac NetflixRBTBackendBD.java NetflixRBTBackendInterface.java

DatabaseRBTBD.class: DatabaseRBTBD.java DatabaseRBTInterface.java RedBlackTree.class
	javac DatabaseRBTBD.java DatabaseRBTInterface.java

NetflixDataReaderBD.class: NetflixDataReaderBD.java NetflixDataReaderInterfaceDW.java
	javac NetflixDataReaderBD.java NetflixDataReaderInterfaceDW.java

NetflixDataBD.class: NetflixDataBD.java NetflixDataInterfaceDW.java
	javac NetflixDataBD.java NetflixDataInterfaceDW.java

runAlgorithmEngineerTests: AlgorithmEngineerTest.class
	java -jar junit5.jar -cp . --select-class=AlgorithmEngineerTest

DatabaseRBTAE.class: DatabaseRBTAE.java DatabaseRBTInterfaceAE.class RedBlackTree.class
	javac DatabaseRBTAE.java

RedBlackTree.class: RedBlackTree.java SortedCollectionInterface.java
	javac -cp .:junit5.jar RedBlackTree.java SortedCollectionInterface.java

DatabaseRBTInterfaceAE.class: DatabaseRBTInterfaceAE.java
	javac DatabaseRBTInterfaceAE.java

NetflixFrontendFD.class: NetflixFrontendFD.java
	javac NetflixFrontendFD.java NetflixFrontendInterfaceFD.java

FrontendDeveloperTests.class: FrontendDeveloperTests.java NetflixFrontendFD.class
	javac -cp .:junit5.jar FrontendDeveloperTests.java

runFrontendDeveloperTests: FrontendDeveloperTests.class
	java -jar junit5.jar -cp . --select-class=FrontendDeveloperTests


AlgorithmEngineerTest.class: AlgorithmEngineerTest.java DatabaseRBTAE.class NetflixRBTBackendBD.class NetflixFrontendFD.class NetflixDataDW.class  NetflixDataReaderDW.class
	javac -cp .:junit5.jar AlgorithmEngineerTest.java
clean:
	rm -f *.class
	rm -f *~
