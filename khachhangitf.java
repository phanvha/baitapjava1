	import java.rmi.Remote;
	import java.rmi.RemoteException;

	public interface khachhangitf extends Remote{
		public String exesql(String sql) throws RemoteException;
		
	}
