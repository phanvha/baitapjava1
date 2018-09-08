
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class khachhangserver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.setProperty("java.rmi.server.hostname","localhost");
			khachhang obj = new khachhang();
			LocateRegistry.createRegistry(1094);
			Naming.rebind("khObj", obj);
			System.out.println("Dang ky thanh cong");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}