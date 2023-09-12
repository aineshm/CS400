import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CHSearchFrontendXX class implements a frontend implementation using a hard-coded backend placeholder class
 *
 */
public class CHSearchFrontendXX implements CHSearchFrontendInterface {
	
	// private data fields
	
	private Scanner scan; // Scanner input to read input from user
	private CHSearchBackendInterface backend; // backend variable to run commands from a backend object

	
	/**
	 * Constructor - constructs frontend object based on a userInput and backend object
	 * @param userInput - Scanner object which takes input 
	 * @param backend - backend class that is used to implement methods
	 */
	public CHSearchFrontendXX(Scanner userInput, CHSearchBackendInterface backend) {
		this.scan = userInput; 
		this.backend = backend;
	}
	
	/**
	 * Method that runs the commands based on given input indefinitely until Q is pressed
	 */
	@Override
	public void runCommandLoop() {
		// runs a while loop forever until Q is pressed, which breaks out
		// Each command method corresponding to the input letter is run
		while (true) {
			char userPrompt = mainMenuPrompt();
			if (userPrompt == 'L') {
				this.loadDataCommand();
			} else if (userPrompt == 'T') {
				this.searchTitleCommand(chooseSearchWordsPrompt());
			} else if (userPrompt == 'B') {
				this.searchBodyCommand(chooseSearchWordsPrompt());
			} else if (userPrompt == 'P') {
				this.searchPostCommand(chooseSearchWordsPrompt());
			} else if (userPrompt == 'S') {
				this.displayStatsCommand();
			} else if (userPrompt == 'Q') {
				System.out.println("Program ended.");
				break;
			} else {
				// invalid commands lead to this
				System.out.println("Invalid selection, try again: ");
			}
		}

	}

	
	/**
	 * Displays a prompt menu for the user to choose based on
	 * 
	 * @return the char value that is input
	 */
	@Override
	public char mainMenuPrompt() {
		System.out.println(
				"\n[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\nSearch [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit");

		System.out.print("Enter a valid character to run the corresponding command: ");
		char input = scan.next().toUpperCase().charAt(0); // takes in an uppercase value from user
		
		
		// switch statement to organize all possible cases
		switch (input) {
		case 'L':
			return 'L';
		case 'T':
			return 'T';
		case 'B':
			return 'B';
		case 'P':
			return 'P';
		case 'S':
			return 'S';
		case 'Q':
			return 'Q';
		default:
			return '\0';
		}

	}

	
	/**
	 * Loads data based on Backend method
	 * Includes a try-catch based on possible propagated errors from backend method
	 */
	@Override
	public void loadDataCommand() {
		System.out.print("Enter file name to load data from: ");
		scan.nextLine();
		String file = scan.nextLine();
		try {
			backend.loadData(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Concatenates words based on input and converts to a List<String> object
	 * 
	 * @return a list of search words
	 */
	@Override
	public List<String> chooseSearchWordsPrompt() {
		System.out.println("Enter search words: ");
		scan.nextLine();
		String searchWords = scan.nextLine();
		System.out.println("Request made successfully");

		String[] splitString = searchWords.split(" ");
		List<String> outputFormat = new ArrayList<String>();
		for (int i = 0; i < splitString.length; i++) {
			outputFormat.add(splitString[i]);
		}

		return outputFormat;
	}

	
	/**
	 * Searches based on title given search words
	 * 
	 * @param words - takes in a list of words based on which titles need to be searched
	 * 
	 */
	@Override
	public void searchTitleCommand(List<String> words) {
		// creates and iterates through words to create a string of search words
		String wordsToConcat = "";
		if (words.size() > 0) {
			for (int i = 0; i < words.size(); i++) {
				wordsToConcat += (words.get(i) + " ");
			}
		}
		wordsToConcat = wordsToConcat.substring(0, wordsToConcat.length() - 1);
		System.out.println("Searching titles");
		// uses backend method to produce an output
		List<String> output = backend.findPostsByTitleWords(wordsToConcat);
		if(output.size()!=0){	
			System.out.println("Results from search: ");
			// prints output based on results
			for(int i = 0; i < output.size(); i++){
				System.out.println(output.get(i));
			}
		}else{
			System.out.println("No results found...");
	
        	}
	}
	
	
	
	/**
	 * Searches based on Body text given search words
	 * 
	 * @param words - takes in a list of words based on which body text need to be searched
	 */
	@Override
	public void searchBodyCommand(List<String> words) {
		// creates and iterates through words to create a string of search words
		String wordsToConcat = "";
		if (words.size() > 0) {
			for (int i = 0; i < words.size(); i++) {
				wordsToConcat += (words.get(i) + " ");
			}
		}
		wordsToConcat = wordsToConcat.substring(0, wordsToConcat.length() - 1);
		System.out.println("Searching body text");
		// uses backend method to produce an output
		List<String> output = backend.findPostsByBodyWords(wordsToConcat);
		if(output.size()!=0){	
			System.out.println("Results from search: ");
			// prints the results based on search
			for (int i = 0; i < output.size(); i++){
				System.out.println(output.get(i));
			}
        	}else{
			System.out.println("No results found...");
		}
	}

	
	/**
	 * Searches based on Body text and title given search words
	 * 
	 * @param words - takes in a list of words based on which body text and title need to be searched
	 */
	@Override
	public void searchPostCommand(List<String> words) {
		// creates and iterates through words to create a string of search words
		String wordsToConcat = "";
		if (words.size() > 0) {
			for (int i = 0; i < words.size(); i++) {
				wordsToConcat += (words.get(i) + " ");
			}
		}
		wordsToConcat = wordsToConcat.substring(0, wordsToConcat.length() - 1);
		System.out.println("Searching by post\'s bodies and titles");
		// uses backend method to produce an output
		List<String> output = backend.findPostsByTitleOrBodyWords(wordsToConcat);
				
		if(words.size()!=0){
			System.out.println("Results from search: ");
			// prints output based on results
			for(int i = 0; i < output.size(); i++) {
            			System.out.println(output.get(i));
			}
		}else{
			System.out.println("No results found...");
		}

	}

	
	/**
	 * Provides statistics based on backend method
	 */
	@Override
	public void displayStatsCommand() {
		// prints out backend based statistics
		System.out.println("Statistics: \n"+backend.getStatisticsString());
	}

}
