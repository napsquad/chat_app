package Message_Objs;
import java.io.Serializable;
public class Generic_Object implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8000312793090760877L;
	public String IP;
	public String name;

    // four constructors 
	public Generic_Object() {
    
 }
    public Generic_Object(String w, String x) {
       IP = w;
       name = x;
    }
    
}
