/*
 * Node.java
 * Author: Joy Rahman
 * 
 * Purpose:
 *   This class implements each node of the Trie tree for dictionary.
 *   Inside each node, a HashMap is used to store all the keys and pointer to associated node
 * 
 * Design:
 *   most of the methods are self explanatory, that helps maintain each node.
 *   addChild: O(1)
 *   getChild: O(1)
 *   isEmpty:  O(1)
 *   removeChild: O(1)
 * Dependency: 
 *   SimpleMap.java : A simple HashMap class used by Node.java to store the key-values per node.
 * */
package dictionary;

public class Node implements InterfaceNode {
	SimpleMap keys;
	boolean isValid;
	String definition;
	
	Node()
	{
		this.keys = new SimpleMap();
		this.isValid = false;
		this.definition = null;

	}
	@Override
	public void addChild(Character ch, Node next) {
		this.keys.put(ch, next);
		
	}
	@Override
	public Node getChild(Character ch) {
		return this.keys.get(ch);

	}
	@Override
	public String toString() {
		String output = "";
		for (Character ch : this.keys.keySet()) {
			output = output + " " + ch;
		}
		return output;

	}
	@Override
	public void removeChild(Character ch)
	{
		this.keys.remove(ch);
	}
	@Override
	public boolean isEmpty() {
		return this.keys.size() == 0;
	}
	@Override	
	public void setDefinition(String definition) {
		this.isValid = true;
		this.definition = definition;
	}

}
