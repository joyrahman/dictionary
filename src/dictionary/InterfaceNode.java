/*
 * InterfaceNode.java
 * Author: Joy Rahman
 * 
 * Purpose:
 *   public api to Trie Node
 */

package dictionary;

public interface InterfaceNode {
	public void addChild(Character ch, Node next);

	Node getChild(Character ch);

	void removeChild(Character ch);

	boolean isEmpty();

	void setDefinition(String definition);
	

}
