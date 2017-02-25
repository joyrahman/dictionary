
/**
 * 
 */
package dictionary;
//import java.util.HashMap;


/**
 * @author joy
 *
 */
public class Node {
	//HashMap <Character,Node> keys;
	SimpleMap keys;
	boolean isValid;
	String definition;
	
	//constructor
	Node()
	{
		//this.keys = new HashMap<Character, Node>();
		this.keys = new SimpleMap();
		this.isValid = false;
		this.definition = null;

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

	public void removeChild(Character ch)
	{
		this.keys.remove(ch);
	}

	public boolean isEmpty() {
		return this.keys.size() == 0;
	}
	
	public void setDefinition(String definition) {
		this.isValid = true;
		this.definition = definition;
	}

}
