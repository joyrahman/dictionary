/*
 * Dictionary.java
 * Author: Joy Rahman
 * 
 * Purpose:
 *   This class implements the InterfaceDictionary.java that provides public api
 *   to use this dictionary.
 * 
 * Design:
 *   To implement the dictionary, this code uses Trie(https://en.wikipedia.org/wiki/Trie) data structure. 
 *   It's a tree like data structure, with a particular benefit for prefix based search.
 *   To compare, a straight HashMap based dictionary implement would have take O(N) runtime to find all the strings that matches a given prefix.  
 *   Whereas, a Trie based implementation will only explore the branches of the tree that matches the prefix, reducing runtime significantly.   
 *   
 *   Looking up data in a Trie is faster in the worst case, O(m) time (where m is the length of a search string), 
 *   compared to an imperfect hash table. An imperfect hash table can have key collisions resulting worst case run time as O(N), 
 *   but far more typically is O(1), with O(m) time spent evaluating the hash.
 *   
 * Performance:  
 *   Building the dictionary takes O(k*N) times where k is the average length of each word, and N is the number of entries.
 *   Retrieving, Deleting can be done in O(log m) where, m is the length of the word. 
 *   
 * Assumptions/Limitations: 
 *   only supports words with English letters [a-z]. Non letter inputs are discarded.
 *   all the upper-case letters are converted to lower-case. The dictionary stores all the word (as key) in lower-case
 * Dependency: 
 *   Node.java : Node class represents each node of the tree
 *   SimpleMap.java : A simple HashMap class used by Node.java to store the key-values per node.
 *   Helper.java : Implements some helper functions
 * */

package dictionary;



public class Dictionary implements InterfaceDictionary {
	Node root; // root of the tree
	
	public Dictionary() {
		this.root = new Node();
	}
	
	/* public void insert(String word, String definition) 
	 *   public method to insert word into the dictionary.
	 *   
	 *   steps:
	 *   - convert the input to lower case
	 *   - verify if the input is valid (only English letters[a-z] are supported) 
	 *   - call the private method insert that will insert the word recursively, character by character into the trie
	 *   
	 * */
	
	
	
	@Override
	public void insert(String word, String definition) {		
			
		word = word.toLowerCase();		//convert to lower-case before insertion
		
        if (Helper.isSanitized(word)==false){  // check input for unsupported characters & error handling
        	System.out.println(word+ ": " +"[ERROR: Bad input. Item not inserted]");
        	return;	 //do not insert the item
        }
		
        this.insert(this.root, word, 0, definition); // start from position 0, recursively to insert each character
	}
	
	
    /*	public void retrieve(String word)
	 *      public method to search for a word into the dictionary.
	 *      returns the definition or null, if not found
	 *      
	 */
	
	@Override
	public String retrieve(String word) {
		String result = this.search(this.root, word, 0);
		if (result != null)
			return result;
		return (word +": " + "[ERROR: Entry Not found]");
		
	}
    /*	public void retrieveAll(String prefix)
	 *      public method to search for all the key words into the dictionary
	 *      that matches the prefix
	 *      returns null or a string with word:definition pairs, separated by comma
	 */
	
	@Override
	public String retrieveAll(String prefix) {
		return this.searchPrefix(this.root, prefix, 0);
	}

	/* private void insert(Node currentNode, String word, Integer currentIndex, String definition)
	 *   private method to insert word into the trie, recursively, character by character
	 *   
	 *   steps:
	 *   - convert the input to lower case
	 *   - verify if the input is valid (only English letters[a-z] are supported) 
	 *   - call the private method insert that will insert the word recursively, character by character into the trie
	 *   
	 * */

	private void insert(Node currentNode, String word, Integer currentIndex, String definition) {
    	
		if (word.length() == currentIndex) { //check if whole string is consumed up-to this point. 
			currentNode.setDefinition(definition); //if such, make Node.isValid = true and store the definition
			return;  //break the recursion

		}

		Character ch = word.charAt(currentIndex);  // current character 
		Node nextChild = currentNode.keys.get(ch);  // check if already exist
		if (nextChild == null) {   		// if not, insert the new node	
			nextChild = new Node();
			currentNode.addChild(ch, nextChild);
		}
		insert(nextChild, word, currentIndex + 1, definition);		// call the method recursively for next position
	}
	
	
    /*	public void delete(String word)
	 *      public method to insert word into the dictionary.
	 */
	@Override
	public void delete(String word) {

		delete(this.root, word, 0);
	}
	
    /*	private boolean delete(Node currentNode, String word, Integer currentIndex)
	 *      private method to delete word from the dictionary
	 *      case1:
	 *          when the word is fully consumed, we check if it is a valid word. if yes, we set the isValid flag to false.
	 *          if there is no more children, then this node can be deleted. hence, return true
	 *          else return false, since this node still contains some other valid words down the tree
	 *      case2:
	 *          if a child can be deleted, delete it. 
	 *          if the currentNode child-list is empty, then return true so that current node can be further deleted by its parents node.
	 *  
	 * */

	private boolean delete(Node currentNode, String word, Integer currentIndex) {

		if (currentIndex == word.length()) // reached to the end of the string.
		{  
			if (currentNode.isValid == false)
				return false;
			
			currentNode.isValid = false;  // set the flag to false, so that it can be deleted if necessary
			
			if (currentNode.isEmpty())    // no children, hence can be deleted safely
				return true;

		}

		Character ch = word.charAt(currentIndex);
		
		Node nextChild = currentNode.keys.get(ch);

		if (nextChild == null)  // char is not found, it's a mismatch. return false
			return false;

		boolean deleteChild = delete(nextChild, word, currentIndex + 1);

		
		if (deleteChild == true) // case2 : delete the child
		{ 
			currentNode.removeChild(ch);
			
			if (currentNode.isEmpty() == true)
				return true;
		}

		
		return false;  // default case, return false


	}

    /*	private String search(Node currentNode, String word, Integer currentIndex)
	 *      private method to search word from the dictionary
	 *      case1:
	 *          all the characters of the word is consumed. We return what is stored in the definition or null.
	 *      case2:
	 *          otherwise, get next character. 
	 *          if not found, return null
	 *          else search recursively from next position
	 *  
	 * */



	private String search(Node currentNode, String word, Integer currentIndex) {


		if (currentIndex == word.length()) {  //base case: all the characters of the word is consumed. We return what is stored in definition or null.
			return word + ": " +currentNode.definition;
		}

		Character ch = word.charAt(currentIndex); //else: get the next character. if not found, return null
		Node nextChild = currentNode.keys.get(ch);
		if (nextChild == null) {
			return null;
		}


		return search(nextChild, word, currentIndex + 1);		// recursively go to the next node, as long as it matches or consumed.


	}
	
    /*	private String searchPrefix(Node currentNode, String prefix, Integer currentIndex)
	 *      private method to search for prefix from the dictionary
	 *      case1:
	 *          keep calling recursively until the whole prefix is consumed.
	 *      case2:
	 *          once the prefix is matched, 
	 *              check if the current node isValid, if such, add the currentPrefix to result
	 *              else search recursively from next position, appending current character to the prefix
	 *  
	 * */



	private String searchPrefix(Node currentNode, String prefix, Integer currentIndex) {

		
		if (currentIndex == prefix.length())  //case2: the input prefix is matched completely
		{

			String result = "";			
			if (currentNode.isValid == true)   // if current node is valid, the result must contain this word
				result = prefix + ": " + currentNode.definition + "\n";
			
			
			if (currentNode.isEmpty())    // no more children, just return the prefix
				return result;
			
			for (Character ch : currentNode.keys.keySet()) //the node has children. Explore each children recursively
			{
				if (ch!=null)
					result = result + searchPrefix(currentNode.getChild(ch), prefix + ch, currentIndex + 1);
			}

			return result;
		}
		
		

		Character ch = prefix.charAt(currentIndex);  //case1 : keep matching initial prefix
		
		Node nextChild = currentNode.keys.get(ch);
		
		if (nextChild == null) 		// if the current character is not found, no match.
			return null;

		return searchPrefix(nextChild, prefix, currentIndex + 1);  // recursively go to the next node, as long as it matches or consumed.

	}



}
