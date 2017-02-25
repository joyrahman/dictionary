/* DictionaryTest.java
 * 
 *  a simple class to use the dictionary with multiple test input
 *  
 * */


package dictionary;

public class DictionaryTest {
	public static void main(String[] args) 
	{
		Dictionary d = new Dictionary();
		/* test: insert words */
		d.insert("cricket", "a wonderful bat-ball game");
		d.insert("soccer", "a game played worldwide");
		
		/* test: insert similar words */
		d.insert("terradata", "a silicon valley bigdata company");
		d.insert("terrific", "extraordinarily great or intense");
		d.insert("terrible", "severe");
		d.insert("terrace", "a raised level with a vertical or sloping front");
		d.insert("tea", "healthy drink");
		


		
		/* test: retrieving definition of tea */
		System.out.println(d.retrieve("tea"));
        
		/* test: delete entry "tea" */
		d.delete("tea");
		
		/* test: retrieving definition of tea after deletion*/
		System.out.println(d.retrieve("tea"));  //shall generate error as key is deleted.

		
		/* test: retrieving key:definition of all the words matching prefix "ter" */
		System.out.println(d.retrieveAll("ter"));
		
		
		/* test: inserting an invalid input, containing non-letters */
		d.insert("123java", "intro to java");  //a bad input. shall generate error


	}

}
