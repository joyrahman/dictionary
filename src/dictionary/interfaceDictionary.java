package dictionary;

public interface interfaceDictionary {
	public void insert(String word, String definition);

	public void delete(String word);

	public String retrive(String word);

	public String retriveAll(String prefix);

}
