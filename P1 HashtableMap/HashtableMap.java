// --== CS400 Project One File Header ==--
// Name: Ainesh Mohan
// CSL Username: ainesh
// Email: amohan28@wisc.edu
// Lecture #: 002
// Notes to Grader: <any optional extra notes to your grader>
import java.util.NoSuchElementException;

/**
 * Class that designs a generic HashtableMap design
 *
 * @param <KeyType>   - Generic type for key of value
 * @param <ValueType> - Generic type for value associated to key
 */
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

	// creating a helper class
	/**
	 * Helper class that stores both a key and its designated mapped value
	 *
	 * @param <Key>   - key type
	 * @param <Value> - value type
	 */
	private class Pairs<Key, Value> {

		// initializing private data fields
		private Key key;
		private Value value;

		// constructor for helper

		/**
		 * constructs an object using a key and value parameter thus associated one with
		 * the other
		 * 
		 * @param key - key associated to the value
		 * @param val - the actual value that the key represents
		 */
		private Pairs(Key key, Value val) {
			this.key = key;
			this.value = val;
		}
	}

	// data fields
	protected Pairs<KeyType, ValueType> hashedTable[]; // creates an array of Pairs objects - this makes it easier to
														// store the pairs of keys and values
	private int size; // maintains the current size of the array
	private int capacity; // the total capacity of the array
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private final Pairs SENTINEL_VAL = new Pairs((KeyType) new Object(), (ValueType) new Object());

	/**
	 * HashtableMap constructor - initializes all fields
	 * 
	 * @param capacity - initial total capacity of array; array length
	 */
	public HashtableMap(int capacity) {
		this.capacity = capacity;
		size = 0;
		@SuppressWarnings("unchecked")
		Pairs<KeyType, ValueType>[] temp = (Pairs<KeyType, ValueType>[]) new Pairs[this.capacity];
		hashedTable = temp;
	}

	/**
	 * HashtableMap constructor - initializes all fields
	 * 
	 */
	public HashtableMap() {
		this.capacity = 8;
		size = 0;
		@SuppressWarnings("unchecked")
		Pairs<KeyType, ValueType>[] temp = (Pairs<KeyType, ValueType>[]) new Pairs[8];
		hashedTable = temp;
	}

	// accessor methods

	/**
	 * getter for the size variable
	 * 
	 * @return size of hashtable array
	 */
	@Override
	public int getSize() {
		return this.size;
	}

	/**
	 * getter for capacity variable
	 * 
	 * @return capacity of the hashtable array
	 */
	@Override
	public int getCapacity() {
		return this.capacity;
	}

	/**
	 * Method retrieves the value based on a key from the hashtable array
	 * 
	 * @param key - key from key-value pair
	 * @return value associated to the key from the hashtable
	 * @throws NoSuchElementException if key is null, or doesn't exist in the array
	 */
	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		if (key == null || !containsKey(key)) {
			throw new NoSuchElementException("Null key input");
		}
		int indexOfKey = indexGenerator(key);
		while (hashedTable[indexOfKey] != null && !hashedTable[indexOfKey].equals(SENTINEL_VAL)) {
			if (hashedTable[indexOfKey].key.equals(key)) {
				return hashedTable[indexOfKey].value;
			}
			indexOfKey = (indexOfKey + 1) % this.capacity;
		}
		throw new NoSuchElementException();
	}

	// mutator methods

	/**
	 * Put method adds a key,value pair to the Hashtable using indexGenerator The
	 * array is resized using resize() if required
	 * 
	 * @param key   - key part of a key-value pair
	 * @param value - value part of a key-value pair
	 * @throws IllegalArgumentException - if key is null or already exists in the
	 *                                  array
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void put(KeyType key, ValueType value) throws IllegalArgumentException {
		if (key == null || containsKey(key)) {
			throw new IllegalArgumentException("Error - key is null or already exists");
		}
		// generating an index for the key
		int indexOfPair = indexGenerator(key);

		// finds a slot for the pair in the Hashtable
		while (hashedTable[indexOfPair] != null) {
			indexOfPair = (indexOfPair + 1) % this.capacity;
		}
		// adds the pair to hashtable
		hashedTable[indexOfPair] = new Pairs(key, value);
		size++; // size increments

		// resize if required
		if (size >= capacity * 0.7) {
			resizeArray();
		}

	}

	/**
	 * Removes a Pair based on a key input from the hashtable array
	 * 
	 * @param key - key from key-value pair
	 * @return value associated to the key from the hashtable
	 * @throws NoSuchElementException if key is null or doesn't exist in the array
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ValueType remove(KeyType key) throws NoSuchElementException {
		if (key == null || !containsKey(key)) {
			throw new NoSuchElementException("Null key input");
		}
		int indexOfKey = indexGenerator(key);
		while (hashedTable[indexOfKey] != null) {
			if (hashedTable[indexOfKey].key.equals(key)) {
				ValueType returnVal = hashedTable[indexOfKey].value;
				size--;
				hashedTable[indexOfKey] = new Pairs((KeyType) new Object(), (ValueType) new Object());
				return returnVal;
			}
			indexOfKey = (indexOfKey + 1) % this.capacity;
		}
		throw new NoSuchElementException();
	}

	/**
	 * Clears the entire hashtable array
	 */
	@Override
	public void clear() {

		for (int i = 0; i < capacity; i++) {
			hashedTable[i] = null;
		}
		this.size = 0;

	}

	// other methods
	/**
	 * This method checks if the key exists in the array
	 * 
	 * @param key - the key associated to a value that is to be checked throughout
	 *            the array
	 * @return true if the key exists in the array, false otherwise
	 */
	@Override
	public boolean containsKey(KeyType key) {
		int indexOfKey = indexGenerator(key);
		while (hashedTable[indexOfKey] != null) {
			if (hashedTable[indexOfKey].key.equals(key)) {
				return true;
			}
			indexOfKey = (indexOfKey + 1) % this.capacity;
		}
		return false;
	}

	// helper methods

	/**
	 * helper method to generate an index based on a hashcode
	 * 
	 * @param key - the key for the given Pairs object
	 * @return returns an index for the key
	 */
	private int indexGenerator(KeyType key) {
		return Math.abs(key.hashCode() % this.capacity);
	}

	/**
	 * Helper method to resize an array by 2 times
	 */
	private void resizeArray() {
		HashtableMap<KeyType, ValueType> temp = new HashtableMap<KeyType, ValueType>(capacity * 2);
		for (Pairs<KeyType, ValueType> pair : hashedTable) {
			if (pair != null) {
				temp.put(pair.key, pair.value);
			}
		}
		this.capacity *= 2;
		hashedTable = temp.hashedTable;
	}

}
