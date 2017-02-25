/*
 * InterfaceSimpleMap.java
 * Author: Joy Rahman
 * 
 * Purpose:
 *   public api to SimpleHashMap
 */
package dictionary;


public interface InterfaceSimpleMap {

	public void put(Character key, Node value);

	public Node get(Character key);

	public void remove(Character key);

	public String toString();
	
	public int size();

}
