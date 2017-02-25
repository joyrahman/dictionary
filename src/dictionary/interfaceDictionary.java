/*
 * InterfaceDictionary.java
 * Author: Joy Rahman
 * 
 * Purpose:
 *   public api to dictionary
 */
package dictionary;



public interface InterfaceDictionary {
	public void insert(String word, String definition);

	public void delete(String word);

	public String retrieve(String word);

	public String retrieveAll(String prefix);

}
