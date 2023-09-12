import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
/**
 * FrontendDevleoperTests class tests the CHSearchFrontend class
 *
 */
public class FrontendDeveloperTests {

	/**
	 * Tests the loadDataCommand() based on the hard-coded CHSearchBackendFD class
	 * 
	 * @return true if all tests pass, false otherwise
	 */
	public static boolean test1() {
		{
			// instantiates TextUITester, Scanner, CHSearchBackendFD, CHSearchFrontendXX
			TextUITester tester = new TextUITester("L\ntest.txt\nQ\n");
			Scanner scan = new Scanner(System.in);
			CHSearchBackendFD backend = new CHSearchBackendFD();
			CHSearchFrontendXX frontend = new CHSearchFrontendXX(scan, backend);
			// runs command loop based on given input of tester
			frontend.runCommandLoop();
			// gets output from TextUITester
			String output = tester.checkOutput();
			if (!output.contains("data")) { // checks if output contains hard-coded values
				return false;
			}
		}
		{
			// instantiates TextUITester, Scanner, CHSearchBackendFD, CHSearchFrontendXX
			TextUITester tester = new TextUITester("L\ninvalid.txt\nQ\n");
			Scanner scan = new Scanner(System.in);
			CHSearchBackendFD backend = new CHSearchBackendFD();
			CHSearchFrontendXX frontend = new CHSearchFrontendXX(scan, backend);
			// runs command loop based on given input of tester
			frontend.runCommandLoop();
			// gets output from TextUITester
			String output = tester.checkOutput();
			if (!output.contains("data")) { // checks if output contains hard-coded values
				return false;
			}
		}
		return true;
	}

	// tests the displayStatsCommand() method
	/**
	 * tests the displayStatsCommand() method
	 * 
	 * @return true if tests pass, false otherwise
	 */
	public static boolean test2() {
		// instantiates TextUITester, Scanner, CHSearchBackendFD, CHSearchFrontendXX
		TextUITester tester = new TextUITester("S\nQ\n");
		Scanner scan = new Scanner(System.in);
		CHSearchBackendFD backend = new CHSearchBackendFD();
		CHSearchFrontendXX frontend = new CHSearchFrontendXX(scan, backend);
		// runs command loop based on given input of tester
		frontend.runCommandLoop();
		// gets output from TextUITester
		String output = tester.checkOutput();
		if (!output.contains("Statistics: \n") || !output.contains("Placeholder")) { // checks if output contains
																						// hard-coded values
			return false;
		}
		return true;
	}

	/**
	 * tests the searchTitleCommand() method
	 * 
	 * @return true if tests pass, false otherwise
	 */
	public static boolean test3() {
		// instantiates TextUITester, Scanner, CHSearchBackendFD, CHSearchFrontendXX
		TextUITester tester = new TextUITester("T\nabcd\nQ\n");
		Scanner scan = new Scanner(System.in);
		CHSearchBackendFD backend = new CHSearchBackendFD();
		CHSearchFrontendXX frontend = new CHSearchFrontendXX(scan, backend);
		// runs command loop based on given input of tester
		frontend.runCommandLoop();
		// gets output from TextUITester
		String output = tester.checkOutput();
		if (!output.contains("Title\nTitle1\nTitle2")) { // checks if output contains hard-coded values
			return false;
		}

		return true;
	}

	/**
	 * tests the searchBodyCommand() method
	 * 
	 * @return true if tests pass, false otherwise
	 */
	public static boolean test4() {
		// instantiates TextUITester, Scanner, CHSearchBackendFD, CHSearchFrontendXX
		TextUITester tester1 = new TextUITester("B\nabcd\nQ\n");
		Scanner scan = new Scanner(System.in);
		CHSearchBackendFD backend = new CHSearchBackendFD();
		CHSearchFrontendXX frontend = new CHSearchFrontendXX(scan, backend);
		// runs command loop based on given input of tester
		frontend.runCommandLoop();
		// gets output from TextUITester
		String output = tester1.checkOutput();
		if (!output.contains("Body\nBody1\nBody2")) { // checks if output contains hard-coded values
			return false;
		}
		return true;
	}

	/**
	 * tests the searchPostCommand() method
	 * 
	 * @return true if tests pass, false otherwise
	 */
	public static boolean test5() {
		// instantiates TextUITester, Scanner, CHSearchBackendFD, CHSearchFrontendXX
		TextUITester tester = new TextUITester("P\nabcd\nQ\n");
		Scanner scan = new Scanner(System.in);
		CHSearchBackendFD backend = new CHSearchBackendFD();
		CHSearchFrontendXX frontend = new CHSearchFrontendXX(scan, backend);
		// runs command loop based on given input of tester
		frontend.runCommandLoop();
		// gets output from TextUITester
		String output = tester.checkOutput();
		if (!output.contains("Post\nPost1\nPost2")) { // checks if output contains hard-coded values
			return false;
		}
		return true;
	}
	

	/**
	 * tests the relationship between loadData-loadDataCommand methods between BD and FD
	 * also tests the relationship between getStatisticsString-displayStatsCommand methods between BD and FD
	 * 
	 * test case checks for a valid file and tries to load data from it
	 *
	 * @return - true if all tests pass, false otherwise
	 *
	 */ 
	public static boolean integrationTest1() {
		{
			// instantiates TextUITester, Scanner, CHSearchBackendFD, CHSearchFrontendXX, HashtableWithDuplicaateKeysAE, PostReaderDW
			TextUITester tester = new TextUITester("L\ndata/fake.txt\nS\nQ\n");
			Scanner scan = new Scanner(System.in);
			HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<String, PostInterface>(8);
			PostReaderInterface postReader = new PostReaderDW();
			CHSearchBackendBD backend = new CHSearchBackendBD(hashtable,postReader);
			CHSearchFrontendXX frontend = new CHSearchFrontendXX(scan, backend);
			
			// runs command loop based on given input of tester
			frontend.runCommandLoop();
		
			//  if not, the if statement will return false
			String output = tester.checkOutput();
		
			if(output.isEmpty()){
				return false;
			}
			if(!output.contains("Dataset contains:\n" +
                                "    " + 4 + " posts\n" +
                                "    " + 49 + " unique words\n" +
                                "    " + 108 + " total word-post pairs\n" +
                                "    note that words in titles vs bodies are counted separately")){
				return false;
			}

		}
		return true;
	}
	

	/**
	 * tests the search commands through frontend
	 * Attempts to use a valid key word from fake.txt in the data directory to try to extract a link 
	 * Tests searching by title and body
	 *
	 * @return true if all tests pass, false otherwise 
	 */
	public static boolean integrationTest2() {
		{
			// instantiates TextUITester, Scanner, CHSearchBackendFD, CHSearchFrontendXX, HashtableWithDuplicaateKeysAE, PostReaderDW
                	TextUITester tester = new TextUITester("L\ndata/fake.txt\nT\nMadison\nQ\n");
                	Scanner scan = new Scanner(System.in);
                	HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<String, PostInterface>(8);
                	PostReaderInterface postReader = new PostReaderDW();
                	CHSearchBackendBD backend = new CHSearchBackendBD(hashtable,postReader);
                	CHSearchFrontendXX frontend = new CHSearchFrontendXX(scan, backend);
  
  			// runs command loop based on given input of tester
                	frontend.runCommandLoop();

                	//  if not, the if statement will return false
                	String output = tester.checkOutput();

                	if(output.isEmpty()){
				return false;
                	}

			if(!output.contains("Can I grow stevia in Madison?   -  https://no_real_post")){
				return false;
			}

		}

		{
			TextUITester tester = new TextUITester("L\ndata/fake.txt\nB\nhungry\nQ\n");
                        Scanner scan = new Scanner(System.in);
                        HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<String, PostInterface>(8);
                        PostReaderInterface postReader = new PostReaderDW();
                        CHSearchBackendBD backend = new CHSearchBackendBD(hashtable,postReader);
                        CHSearchFrontendXX frontend = new CHSearchFrontendXX(scan, backend);

                        // runs command loop based on given input of tester
                        frontend.runCommandLoop();

                        //  if not, the if statement will return false
                        String output = tester.checkOutput();

                        if(output.isEmpty()){
                                return false;
                        }

                        if(!output.contains("Where can I find the best burgers in the midwest?  -  https://no_real_post")){
                                return false;
                        }
		}


		return true;
	}
	

	/**
	 * directly  tests the backend method loadData() and getStatisticsString() 
	 *
	 * @return true if all tests pass, false otherwise
	 *
	 */ 
	public static boolean bdTest1() {
		
		// initializing Hashtable object, PostReader object and backend object.
		HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<String, PostInterface>(8);
                PostReaderInterface postReader = new PostReaderDW();
               	CHSearchBackendBD backend = new CHSearchBackendBD(hashtable,postReader);

		// trying to load file named "fake.txt" in the "data" directory
		try{
			backend.loadData("data/fake.txt");
		} catch(FileNotFoundException fnfe){
			fnfe.printStackTrace();
		}
		
		String output = backend.getStatisticsString();

		if(!output.contains("Dataset contains:\n" +
                                "    " + 4 + " posts\n" +
                                "    " + 49 + " unique words\n" +
                                "    " + 108 + " total word-post pairs\n" +
                                "    note that words in titles vs bodies are counted separately")){
                                return false;
                        }
	
		return true;
	}

	/**
	 * directly tests the backend methods findPostsByTitleWords(), findPostsByBodyWords()
	 *
	 * @return true if all tests pass, false otherwise
	 * 
	 */ 
	public static boolean bdTest2() {
		
		// initializing Hashtable object, PostReader object and backend object.
                HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<String, PostInterface>(8);
                PostReaderInterface postReader = new PostReaderDW();
                CHSearchBackendBD backend = new CHSearchBackendBD(hashtable,postReader);

                // trying to load file named "fake.txt" in the "data" directory
                try{
                        backend.loadData("data/fake.txt");
                } catch(FileNotFoundException fnfe){
                        fnfe.printStackTrace();
                }
		
		String outputByTitle = "Can I grow stevia in Madison?   -  https://no_real_post";
		String outputByBody = "Where can I find the best burgers in the midwest?  -  https://no_real_post";

		if(!backend.findPostsByTitleWords("Madison").get(0).contains(outputByTitle)){
			System.out.println("Hello");
			return false;
		}

		if(!backend.findPostsByBodyWords("hungry").get(0).contains(outputByBody)){
                        return false;
                }

		return true;
	}
	

	/**
	 * main method runs all tests and prints passed or failed based on test
	 * 
	 */ 
	public static void main(String[] args) {
		if (test1()) {
			System.out.println("Frontend Developer Individual Test 1: passed");
		} else {
			System.out.println("Frontend Developer Individual Test 1: failed");
		}

		if (test2()) {
			System.out.println("Frontend Developer Individual Test 2: passed");
		} else {
			System.out.println("Frontend Developer Individual Test 2: failed");
		}

		if (test3()) {
			System.out.println("Frontend Developer Individual Test 3: passed");
		} else {
			System.out.println("Frontend Developer Individual Test 3: failed");
		}

		if (test4()) {
			System.out.println("Frontend Developer Individual Test 4: passed");
		} else {
			System.out.println("Frontend Developer Individual Test 4: failed");
		}

		if (test5()) {
			System.out.println("Frontend Developer Individual Test 5: passed");
		} else {
			System.out.println("Frontend Developer Individual Test 5: failed");
		}

		if (integrationTest1()) {
			System.out.println("Frontend Developer Integration Test 1: passed");
		} else {
			System.out.println("Frontend Developer Integration Test 1: failed");
		}
		if (integrationTest2()) {
			System.out.println("Frontend Developer Integration Test 2: passed");
		} else {
			System.out.println("Frontend Developer Integration Test 2: failed");
		}
		if (bdTest1()) {
			System.out.println("Frontend Developer Partner (BackendDeveloper) Test 1: passed");
		} else {
			System.out.println("Frontend Developer Partner (BackendDeveloper) Test 1: failed");
		}
		if (bdTest2()) {
			System.out.println("Frontend Developer Partner (BackendDeveloper) Test 2: passed");
		} else {
			System.out.println("Frontend Developer Partner (BackendDeveloper) Test 2: failed");
		}
	}

}
