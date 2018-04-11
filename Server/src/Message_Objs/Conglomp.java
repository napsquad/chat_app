package Message_Objs;

import java.io.Serializable;

public class Conglomp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1719744727854676730L;
		public String type;
		public String s1;
		public String s2;
		public String user;
		
	Conglomp(){
		this.type = "message";
		this.s1 = "item1";
		this.s2 = "item2";
		this.user = "name";
	}

	public Conglomp(String message, String item1, String item2, String name){
		this.type = message;
		this.s1 = item1;
		this.s2 = item2;
		this.user = name;
	}
}
