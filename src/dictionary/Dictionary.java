package dictionary;

public class Dictionary {
	Node root; // root of the tree

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dictionary d = new Dictionary();
		d.addKey("abcd");
		d.addKey("abc");
		d.addKey("lamia");
		d.addKey("lama");
		d.display(d.root);

	}

	// constructor
	public Dictionary() {
		this.root = new Node();
	}

	public void addKey(String input) {
		insert(this.root, input, 0); // start from position 0 and use the method
										// recursively to insert each character
	}

	public void insert(Node currentNode, String word, Integer currentIndex) {

		// check if whole string is consumed up-to this point. if such, make
		// Node.isValid = true.
		// return from this point, as a breaking condition for the recursion.
		if (word.length() == currentIndex) {
			currentNode.isValid = true;
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
		insert(nextChild, word, currentIndex + 1);

	}

	public void display(Node current) {
		System.out.println(current.toString());
		for (Character ch : current.keys.keySet()) {
			Node nextCurrent = current.getChild(ch);
			display(nextCurrent);
		}

	}

}
