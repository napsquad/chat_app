package Message_Objs;

public class data_object extends Generic_Object{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8781414489154838914L;
	public String Want_Obj;
	public String Have_Obj;
	public int time;

	public  data_object(String have, String want, String sock, String nam)
	{
		   super.IP = sock;
		   super.name = nam;
		   this.Have_Obj = have;
		   this.Want_Obj = want;
	}
	
	public data_object(String have, String want, String sock, String nam, int tim)
	{
		   super.IP = sock;
		   super.name = nam;
		   this.Have_Obj = have;
		   this.Want_Obj = want;
		   this.time = tim;
	}
	
	
}
