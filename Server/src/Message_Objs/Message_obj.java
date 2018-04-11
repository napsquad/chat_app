package Message_Objs;

public class Message_obj extends Generic_Object{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7387353978095814112L;
	String contents;
	boolean trade;
	
	public Message_obj(boolean trad, String cont, String sook, String myname)
	{
		   this.trade = trad;
		   this.contents = cont;
		   super.IP = sook;
		   super.name = myname;

	}
	
	public String get_contents() {
		return contents;
	}
	public boolean get_bool()
	{
		return trade;
	}
}
