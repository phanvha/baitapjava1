
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;

public class taikhoan extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					taikhoan frame = new taikhoan();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public taikhoan() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 371);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 191, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblID = new JLabel("Username");
		lblID.setForeground(Color.WHITE);
		lblID.setBackground(Color.WHITE);
		lblID.setFont(new Font("UTM American Sans", Font.PLAIN, 16));
		lblID.setBounds(89, 96, 99, 31);
		contentPane.add(lblID);
		
		JLabel lblMatKhau = new JLabel("Password");
		lblMatKhau.setForeground(Color.WHITE);
		lblMatKhau.setFont(new Font("UTM American Sans", Font.PLAIN, 16));
		lblMatKhau.setBackground(Color.WHITE);
		lblMatKhau.setBounds(89, 173, 99, 31);
		contentPane.add(lblMatKhau);
		
		txtID = new JTextField();
		txtID.setBounds(198, 100, 196, 31);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					try {
						
						khachhangitf tv = (khachhangitf)Naming.lookup("rmi://localhost/khObj");
						String n = tv.exesql("select * from account\n" + "where username=? and password=?" );
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/khachhang", "root", "");
						PreparedStatement ps = (PreparedStatement)conn.prepareStatement(n);
						ps.setString(1, txtID.getText());
						ps.setString(2, txtPassword.getText());
						ResultSet rs = ps.executeQuery();
						if (rs.next()) {
							thongtin2 obj = new thongtin2();
							JOptionPane.showMessageDialog(null, "đăng nhập thành công");
							obj.setVisible(true);
							obj.setSize(550, 600);
						} else {
							JOptionPane.showMessageDialog(null, "đăng nhập thất bại!");
							return;
						}
						// TODO: handle exception
					}
					catch (Exception ex) {
						// TODO: handle exception		
						ex.printStackTrace();
					}
				}
			}
		});
		txtPassword.setBounds(198, 173, 196, 31);
		contentPane.add(txtPassword);
		
		JButton btnDangKy = new JButton("Signin");
		btnDangKy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Thong bao neu User con trong
				if(txtID.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Vui lòng nhập tài khoản!");
					return;
				}
				// Thong bao neu Password con trong
				else if(txtPassword.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu!");
					return;
				}				
				try {
					khachhangitf tv = (khachhangitf)Naming.lookup("rmi://localhost/khObj");
					String n = tv.exesql("insert into account(username, password) values('"+txtID.getText()+"','"+txtPassword.getText()+"')");
					
										
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Dang ky thanh cong");
			}
		});
		btnDangKy.setForeground(new Color(0, 153, 0));
		btnDangKy.setFont(new Font("UTM American Sans", Font.PLAIN, 15));
		btnDangKy.setBounds(118, 237, 117, 36);
		contentPane.add(btnDangKy);
		
		JButton btnDangNhap = new JButton("Login");
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					khachhangitf tv = (khachhangitf)Naming.lookup("rmi://localhost/khObj");
					String n = tv.exesql("select * from account\n" + "where username=? and password=?" );
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/khachhang", "root", "");
					PreparedStatement ps = (PreparedStatement)conn.prepareStatement(n);
					ps.setString(1, txtID.getText());
					ps.setString(2, txtPassword.getText());
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						thongtin2 obj = new thongtin2();
						JOptionPane.showMessageDialog(null, "đăng nhập thành công");
						obj.setVisible(true);
						obj.setSize(550, 600);
					} else {
						JOptionPane.showMessageDialog(null, "đăng nhập thất bại!");
						return;
					}
					// TODO: handle exception
				}
				catch (Exception ex) {
					// TODO: handle exception		
					ex.printStackTrace();
				}
								
			}
		});
		btnDangNhap.setForeground(new Color(0, 153, 0));
		btnDangNhap.setFont(new Font("UTM American Sans", Font.PLAIN, 15));
		btnDangNhap.setBounds(289, 237, 139, 36);
		contentPane.add(btnDangNhap);
		
		JLabel lblGrab = new JLabel("Customer");
		lblGrab.setForeground(new Color(255, 255, 255));
		lblGrab.setFont(new Font("UTM Cafeta", Font.PLAIN, 44));
		lblGrab.setBounds(30, 11, 283, 57);
		contentPane.add(lblGrab);
	}
}

