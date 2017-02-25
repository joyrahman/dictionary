package dictionary;

class Data{
	Character key;
	Node item;
	
	Data(Character key, Node item)
	{
		this.key = key;
		this.item = item;
		
	}
	Data()
	{
		this.key = null;
		this.item = null;
	}
	
}

public class SimpleMap implements InterfaceHashMap {
	Data[] storage = new Data[Constants.TOTAL_ALPHABET];
	Character[] keySet = new Character[Constants.TOTAL_ALPHABET];
	int size = 0;
	

	@Override
	public void put(Character key, Node value) {
		int index = findHash(key);
		Data temp = new Data(key,value);
		this.storage[index]= temp;
		this.keySet[index] = key;
		this.size ++;
		
		
	}

	@Override
	public Node get(Character key) {
		int index = findHash(key);
		if (this.storage[index] !=null)
		    return this.storage[index].item;
		return null;
		
	}

	@Override
	public void remove(Character key) {
		int index = findHash(key);
		this.storage[index] = null;	
		this.keySet[index] = null;
		this.size--;
	}
	
	private int findHash(Character key)
	{
		// a simple hash function which just find a position based on  modulo
		// since, the code is expected to contain only alphabets and the size 
		// of the storage is also equal to number of possible alphabets, we 
		// avoid the possible collusion. 
		// This is a most simplistic implementation, but not necessarily the most memory
		// efficient program.
		int index = (int) key % Constants.TOTAL_ALPHABET;
		return index;
	}
	
	public Character[] keySet()
	{

		return this.keySet;
		
		
	}
	
	public int size()
	{
		return this.size;
	}
	
	public String toString()
	{
		String result ="";
		for (Character ch: this.keySet)
		{
			if (ch!=null)
				result = result + ch + ",";
		}
		
		return result;
		
	}
	

}
