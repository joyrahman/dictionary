// implementation of the interface for utility HashMap data structure.

package dictionary;


public interface InterfaceHashMap {

	// get, put, remove, size, toString
	public void put(Character key, Node value);

	public Node get(Character key);

	public void remove(Character key);

	public String toString();
	
	public int size();

}
