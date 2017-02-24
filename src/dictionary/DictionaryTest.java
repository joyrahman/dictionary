package dictionary;

public class DictionaryTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dictionary d = new Dictionary();
		d.insert("good", "really good");
		d.insert("goodboy", "some one being good");
		d.insert("goody", "items");
		d.insert("lack", "missing");
		d.insert("larking", "moving");
		d.insert("tea", "food");

		// d.debug();
		System.out.println(d.retrive("tea"));

		d.delete("tea");
		// d.debug();
		System.out.println(d.retrive("tea"));

		d.insert("abal", "good for nothing");
		d.insert("abaloka", "good for nothing");
		d.insert("abalog", "good for nothing");
		d.insert("abalik", "good for nothing");
		d.insert("abali", "good for nothing");
		d.insert("abantika", "good for nothing");

		System.out.println(d.retriveAll("aba"));

	}

}
