import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class them1 extends JFrame{
	JLabel lblmakh;
	JTextField tfmakh;
	JLabel lbltenkh;
	JTextField tftenkh;
	JLabel lblngsinh;
	JTextField tfngsinh;
	JLabel lblgtinh;
	JTextField tfgtinh;
	JLabel lbldiachi;
	JTextField tfdiachi;
	JLabel lblsodt;
	JTextField tfsodt;
	JButton btthem;
	
	Connection conn;
	Statement st;
	ResultSet rs;
	public void connectdb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/khachhang", "root","");
		}
		catch(Exception ex) {
		}
	}
	public them1() {
		lblmakh = new JLabel("ma khach hang");
		tfmakh = new JTextField();
		tfmakh.setColumns(15);
		lbltenkh = new JLabel("ten khach hang");
		tftenkh = new JTextField();
		tftenkh.setColumns(15);
		lblngsinh = new JLabel("ngay sinh");
		tfngsinh = new JTextField();
		tfngsinh.setColumns(15); 
		lblgtinh = new JLabel("gioi tinh");
		tfgtinh = new JTextField();
		tfgtinh.setColumns(15);
		lbldiachi = new JLabel("dia chi");
		tfdiachi = new JTextField();
		tfdiachi.setColumns(15);
		lblsodt = new JLabel("so dien thoai");
		tfsodt = new JTextField();
		tfsodt.setColumns(15);
		btthem = new JButton("them moi");
		btthem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					khachhangitf tv = (khachhangitf)Naming.lookup("rmi://localhost/khObj");//đăng kí máy chủ với registry
					String n = tv.exesql("Insert into thongtin(makh, tenkh, ngsinh, gioitinh, diachi, sodt) values('"+tfmakh.getText()+"','"+tftenkh.getText()+"','"+tfngsinh.getText()+"','"+tfgtinh.getText()+"','"+tfdiachi.getText()+"','"+tfsodt.getText()+"')");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/khachhang", "root", "");
					PreparedStatement ps = (PreparedStatement)conn.prepareStatement(n);
					ResultSet rs = ps.executeQuery();
					if(rs!=null) JOptionPane.showConfirmDialog(null,"success");
					else JOptionPane.showConfirmDialog(null, "fail");
				}
				catch(Exception ex){
					System.out.println(ex.getMessage());
				}
			}
		});
		java.awt.Container cont = this.getContentPane();
		setLayout(new FlowLayout());
		cont.add(lblmakh);
		cont.add(tfmakh);
		cont.add(lbltenkh);
		cont.add(tftenkh);
		cont.add(lblngsinh);
		cont.add(tfngsinh);
		cont.add(lblgtinh);
		cont.add(tfgtinh);
		cont.add(lbldiachi);
		cont.add(tfdiachi);
		cont.add(lblsodt);
		cont.add(tfsodt);
		cont.add(btthem);
		this.setSize(300,300);
		this.setLocation(250,250);			
		pack();
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		them1 obj = new them1();
		obj.setSize(320, 350);
		obj.setVisible(true);
	}

}