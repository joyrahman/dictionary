package dictionary;

public interface InterfaceDictionary {
	public void insert(String word, String definition);

	public void delete(String word);

	public String retrive(String word);

	public String retriveAll(String prefix);

}
