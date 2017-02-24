package dictionary;

public class Dictionary implements interfaceDictionary {
	Node root; // root of the tree
	// constructor
	public Dictionary() {
		this.root = new Node();
	}

	public void insert(String word, String definition) {
		insert(this.root, word, 0, definition); // start from position 0 and use
												// the method
										// recursively to insert each character
	}

	public void insert(Node currentNode, String word, Integer currentIndex, String definition) {

		// check if whole string is consumed up-to this point. if such, make
		// Node.isValid = true.
		// return from this point, as a breaking condition for the recursion.
		if (word.length() == currentIndex) {
			currentNode.setDefinition(definition);
			return;

		}

		Character ch = word.charAt(currentIndex);
		Node nextChild = currentNode.keys.get(ch);
		if (nextChild == null) {
			// insert the new node
			nextChild = new Node();
			currentNode.addChild(ch, nextChild);
		}

		// call the method recursively for next position
		insert(nextChild, word, currentIndex + 1, definition);

	}

	public void delete(String word) {

		delete(this.root, word, 0);
	}

	private boolean delete(Node currentNode, String word, Integer currentIndex) {
		// case1:
		// when the word is fully consumed, we check if it is a valid word.
		// if yes, we set the isValid flag to false.
		// if there is no more children, then this node can be deleted. hence
		// return true
		// else return false, since this node still contains some other valid
		// words down the tree

		if (currentIndex == word.length()) {
			// reached to the end of the string.
			if (currentNode.isValid == false) {
				return false;
			}
			// set the flag to false
			currentNode.isValid = false;

			if (currentNode.isEmpty())
				return true;

		}

		Character ch = word.charAt(currentIndex);
		Node nextChild = currentNode.keys.get(ch);

		// if the key is not found, it's a mismatch. return false
		if (nextChild == null)
			return false;

		boolean deleteChild = delete(nextChild, word, currentIndex + 1);

		// case2 :
		// if a child can be deleted, delete it.
		// after deletion, if the currentNode child-list is empty, then return
		// true so that
		// current node can be further deleted
		// by its parents node.

		if (deleteChild == true) {
			currentNode.removeChild(ch);
			if (currentNode.isEmpty() == true)
				return true;
		}

		// default case, return false
		return false;


	}

	public void debug() {
		this.debug(this.root);
	}

	private void debug(Node current) {
		System.out.println(current.toString());
		for (Character ch : current.keys.keySet()) {
			Node nextCurrent = current.getChild(ch);
			this.debug(nextCurrent);
		}

	}

	@Override
	public String retrive(String word) {

		return search(this.root, word, 0);
	}

	// The private method that will search for the word.
	// if found, returns definition, else null

	private String search(Node currentNode, String word, Integer currentIndex) {

		// base case: all the characters of the word is consumed. We return what
		// is stored in definition.
		// if invalid, the default value of definition=null will be returned
		// else, the intended definition of the word will be returned.

		if (currentIndex == word.length()) {
			return currentNode.definition;
		}

		Character ch = word.charAt(currentIndex);
		Node nextChild = currentNode.keys.get(ch);
		// if the current character is not found, no match.
		if (nextChild == null) {
			return null;
		}

		// recursively go to the next node, as long as it matches or consumed.

		return search(nextChild, word, currentIndex + 1);

	}



	private String searchPrefix(Node currentNode, String prefix, Integer currentIndex) {

		// base case: all the characters of the word is consumed. We return what
		// is stored in definition.
		// if invalid, the default value of definition=null will be returned
		// else, the intended definition of the word will be returned.
		// System.out.println("d" + prefix);
		if (currentIndex == prefix.length()) {

			String result = "";

			// if current node is valid, the result must contain this word
			if (currentNode.isValid == true)
				result = prefix + ":" + currentNode.definition + ",";
			// case1: no children. Just return string upto this point.
			if (currentNode.isEmpty())
				return result;
			// case2: the node has children. Explore each children recursively
			for (Character ch : currentNode.keys.keySet()) {
				result = result + searchPrefix(currentNode.getChild(ch), prefix + ch, currentIndex + 1);
			}

			return result;
		}

		Character ch = prefix.charAt(currentIndex);
		Node nextChild = currentNode.keys.get(ch);
		// if the current character is not found, no match.
		if (nextChild == null) {
			return null;
		}

		// recursively go to the next node, as long as it matches or consumed.

		return searchPrefix(nextChild, prefix, currentIndex + 1);

	}

	@Override
	public String retriveAll(String prefix) {

		// TODO Auto-generated method stub
		return searchPrefix(this.root, prefix, 0);
	}

}
