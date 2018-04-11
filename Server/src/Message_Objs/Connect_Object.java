package Message_Objs;
import java.net.*;

public class Connect_Object extends Generic_Object{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4694450520972116359L;
	Socket sock;
	boolean connect;
	
	public Connect_Object(boolean stats, String mySock, String myName)
	{
		   connect = stats;
		   super.IP = mySock;
		   super.name = myName;

	}
	public boolean connStat()
	{
		return connect;
	}
}
