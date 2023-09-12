import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlgorithmEngineerTest {
	DatabaseRBTAE<String> testTree; // defining a DatabaseRBTAE String object named testTree

	/**
	 * SetUp method to do before each test - initializes the testTree object
	 * 
	 */
	@BeforeEach
	public void setUp() {
		testTree = new DatabaseRBTAE<String>(); // initializes the testTree object
	}

	/**
	 * Testing insert
	 * 
	 */
	@Test
	public void test1() {

		// inserting 5 values into the testTree object
		testTree.insert("65");
		testTree.insert("66");
		testTree.insert("54");
		testTree.insert("76");
		testTree.insert("26");

		assertEquals(testTree.toLevelOrderString(), "[ 65, 54, 66, 26, 76 ]"); // using Assertions JUnit Class to test
																				// the toLevelOrderString()

	}

	/**
	 * Testing insert multiple
	 * 
	 */
	@Test
	public void test2() {
		List<String> multiInsertTest = new ArrayList<String>(); // creating a List object that will multiple String
																// values

		// adding values to the List
		multiInsertTest.add("65");
		multiInsertTest.add("66");
		multiInsertTest.add("54");
		multiInsertTest.add("76");
		multiInsertTest.add("26");

		testTree.insertMultiple(multiInsertTest); // using the insertMultiple method to try to add all the data as new
													// nodes

		assertEquals(testTree.toLevelOrderString(), "[ 65, 54, 66, 26, 76 ]"); // using assertEquals to test the
																				// toLevelOrderString()
	}

	/**
	 * Testing eraseAll()
	 */
	@Test
	public void test3() {

		List<String> multiInsertTest = new ArrayList<String>(); // creating a List object that will multiple String values

		// adding values to the List
		multiInsertTest.add("65");
		multiInsertTest.add("66");
		multiInsertTest.add("54");
		multiInsertTest.add("76");
		multiInsertTest.add("26");
		
		testTree.insertMultiple(multiInsertTest);

		testTree.eraseAll(); // using eraseAll() method to clear the RBT

		assertEquals(testTree.root.data, null); // checking if the root's data has been reset to null
		assertEquals(testTree.root.context[1], null); // checking if the contexts have been correctly reset
		assertEquals(testTree.root.context[2], null);
	}
	
	
	
	/**
	 * Testing getToString()
	 */
	@Test 
	public void test4() {
		List<String> multiInsertTest = new ArrayList<String>(); // creating a List object that will multiple String values

		// adding values to the List
		multiInsertTest.add("65");
		multiInsertTest.add("66");
		multiInsertTest.add("54");
		multiInsertTest.add("76");
		multiInsertTest.add("26");
		
		testTree.insertMultiple(multiInsertTest);

		String testString = "65"; // testing an accurate case

		assertEquals(testTree.get(testString), "65");

		String testString1 = "33"; // testing an invalid case; should returun null 

		assertEquals(testTree.get(testString1),null);
		
		
		testTree.eraseAll();
		// attempting the same on a null tree; should return null	
		String testString2 = "65";
		assertEquals(testTree.get(testString2),null);
	}
	
	
	/**
	 * Tests the filter method
	 */
	@Test
	public void test5() {
		
		List<String> multiInsertTest = new ArrayList<String>(); // creating a List object that will multiple String values

		// adding values to the List
		multiInsertTest.add("65");
		multiInsertTest.add("66");
		multiInsertTest.add("54");
		multiInsertTest.add("76");
		multiInsertTest.add("26");
		
		testTree.insertMultiple(multiInsertTest);
		
		List<String> expectedOutput = new ArrayList<String>(); // creating a List object with the expected output
		expectedOutput.add("26");
		expectedOutput.add("65");
		expectedOutput.add("66");
		expectedOutput.add("76");
		
		List<String> actualOutput = testTree.filter("6"); // tries filtering all strings to see if they contain "6"
		
		assertEquals(actualOutput,expectedOutput);
		
		
	}





	/**
	 * Testing DW role code - Test 1 
	 *
	 * Tests the NetflicDataReaderDW readPostsFromFile() method
	 */ 
	@Test
	public void codeReviewOfDataWranglerTest1(){
		
		// testing NetflixDataReaderDW for false call, must throw FileNotFoundException
		
	       NetflixDataReaderDW reader = new NetflixDataReaderDW();
	       List<NetflixDataInterfaceDW> list = new ArrayList<NetflixDataInterfaceDW>();

	       assertThrows(FileNotFoundException.class, ()-> {reader.readPostsFromFile("abcd.txt");});
	       

		// testing the same for an accurate call, should return size as 7 for netflix_titles2.csv
		
	       try{
		       list = reader.readPostsFromFile("netflix_titles.csv");
		       assertEquals(list.size(),8807);
	       }catch(FileNotFoundException e){
	       		e.printStackTrace();
	       }
	}





	/**
	 * Testing DW role code - Test 2 
	 *
	 * tests the overriden compareTo() method in the NetflixDataDW class between similar objects
	 */ 
	@Test
	public void codeReviewOfDataWranglerTest2(){
			
		NetflixDataDW data = new NetflixDataDW("a","b","c","d","e","f","g");
		NetflixDataDW data2 = new NetflixDataDW("b","b","b","b","b","b","b");

		assertEquals(data.compareTo(data2),0);

		NetflixDataDW data3 = new NetflixDataDW("a","b","c","d","e","f","g");
		NetflixDataDW data4 = new NetflixDataDW("a","c","d","e","f","g","h");

		assertEquals(data3.compareTo(data4),-1);
	}



	/**
	 * Integration test 1 - adding NetflixDataDW objects to rbt through frontend, and checking the size from both sides 
	 *
	 */
	@Test
	public void integrationTest1(){
		

		// creating tester object, then instantiating all role code	
		TextUITester tester = new TextUITester("L\nnetflix_titles.csv\nS\nQ\n");
		NetflixDataReaderInterfaceDW dw1 = new NetflixDataReaderDW();

		DatabaseRBTInterfaceAE<NetflixDataInterfaceDW> rbt;
		rbt = new DatabaseRBTAE<>();

		NetflixRBTBackendInterface bd = new NetflixRBTBackendBD(rbt,dw1);

		NetflixFrontendInterfaceFD fd = new NetflixFrontendFD(bd);

		fd.runCommandLoop(); // running command loop
		String[] output = tester.checkOutput().split("\n"); // using TextUITester to check output 
								    // and splitting based on new line

		assertEquals(output[11],"The tree contains: 8807 shows."); // checking if the desired output from
									   // FD is met
		assertEquals(rbt.numVals(),8807); // checking if the rbt gives the same result when checking the 
						  // total number of numVals() - the total number of nodes in the RBT


	}

	
	




	/**
	 * Integration test 2 - adding NetflixDataDW objects to rbt through frontend, and checking the size from both sides 
	 *
	 */
	@Test
	public void integrationTest2(){
		// creating tester object, then instantiating all role code
		TextUITester tester = new TextUITester("L\nnetflix_titles.csv\nT\nZombieland\nQ\n");
		NetflixDataReaderInterfaceDW dw1 = new NetflixDataReaderDW();

		DatabaseRBTInterfaceAE<NetflixDataInterfaceDW> rbt;
		rbt = new DatabaseRBTAE<>();

		NetflixRBTBackendInterface bd = new NetflixRBTBackendBD(rbt,dw1);

		NetflixFrontendInterfaceFD fd = new NetflixFrontendFD(bd);

		fd.runCommandLoop(); // running command loop from frontend

		String[] output = tester.checkOutput().split("\n"); // using TextUITester to check output
								    // and splitting based on new line
		
		// storing the same component (finding the title from the toString() and 
		// comparing it with the output from FD
		String outputFromRBT = rbt.filter("Zombieland").get(0).toString().split("\n")[1].split(" ")[1];
		assertEquals(output[12].split(" ")[1],outputFromRBT);


		// doing the same thing for director
		outputFromRBT = rbt.filter("Zombieland").get(0).getDirector();
		assertTrue(output[14].contains(outputFromRBT));	
	}
		


}
