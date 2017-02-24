
/**
 * 
 */
package dictionary;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author joy
 *
 */
public class Node {
	HashMap <Character,Node> keys;
	boolean isValid;
	
	//constructor
	Node()
	{
		this.keys = new HashMap<Character, Node>();
		this.isValid = false;

	}

	public void addChild(Character ch, Node next) {
		this.keys.put(ch, next);
		
	}

	public Node getChild(Character ch) {
		return this.keys.get(ch);

	}

	public String toString() {
		String output = "";
		for (Character ch : this.keys.keySet()) {
			output = output + " " + ch;
		}
		return output;

	}
	

}
