package dictionary;

public class Helper {
	
	// the function of isSantized is to verify if an input 
	// contains only alphabets[a-z]
	static boolean isSanitized(String word) {
		
		for (int i=0;i<word.length();i++)
		{
			if (Character.isLetter(word.charAt(i))==false)
				return false;

		}
		
		return true;
	}

}
