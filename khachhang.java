
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class khachhang extends UnicastRemoteObject implements khachhangitf{
	private Connection conn;
	private Statement st;
	public khachhang() throws RemoteException{
		// TODO Auto-generated constructor stub
		super();
	}
	public void KetNoiDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/khachhang", "root", "");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	}
	public String exesql(String sql) throws RemoteException{
		try {
			KetNoiDB();
			System.out.println("Bat dau ket noi CSDL");
			PreparedStatement ps = (PreparedStatement)conn.prepareStatement(sql);
			st = conn.createStatement();
			int n = st.executeUpdate(sql);
			if(n>0) JOptionPane.showMessageDialog(null, "DUOC roi dmm");
			else JOptionPane.showMessageDialog(null, "deo duoc");			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sql;
		
	}		
	
}