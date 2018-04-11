package Message_Objs;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class person_obj implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -333782942241201565L;
	public String name;
	public ObjectOutputStream sock;
	
	person_obj()
	{
		name = "default";
	}
	public person_obj(String n, ObjectOutputStream oUT)
	{
		name = n;
		sock = oUT;
	}
	
}
