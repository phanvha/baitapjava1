
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.Naming;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class thongtin2 extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btAll;
	private JTextField tfSearch;
	private JButton btsearch;
	private JButton btThem;
	private JButton btUpdate;
	private JButton btXoa;
	private JComboBox Loai;
	private JLabel lblmakh;
	private JTextField tfmakh;
	private JLabel lbltenkh;
	private JTextField tftenkh;
	private JLabel lblngsinh;
	private JTextField tfngsinh;
	
	ResultSet kq ;
	ResultSetMetaData kqm;
	//them JTable de chua du lieu lay tu bang san pham
	JTable tbtt;
	
	JScrollPane ScrTT;
	//khai bao cac doi tuong lam viáº¹c cá»›i mysql
	Connection conn ;
	// Tao doi tuong lop statement de thuc hien cac cau lenh
	java.sql.Statement st ;
	
	String[] columnHeader = {"mã khách hàng", "tên khách hàng", "ngày sinh", "giới tính", "địa chỉ", "số điện thoại"};
	DefaultTableModel model = new DefaultTableModel(columnHeader, 0);
	//ham ket noi csdl
	public void KetNoiDb () {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/khachhang", "root","");
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	public thongtin2() {
		setTitle("thông tin khách hàng");
		setLayout(new BorderLayout());
		
		btAll = new JButton("tất cả ");
		btAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					khachhangitf tv = (khachhangitf)Naming.lookup("rmi://localhost/khObj");//đăng kí máy chủ với registry
					String n = tv.exesql("select * from thongtin where makh like '%"+tfSearch.getText()+"%'");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/khachhang", "root", "");
					PreparedStatement ps = (PreparedStatement)conn.prepareStatement(n);
					ResultSet rs = ps.executeQuery();
					model.setRowCount(0);
					while(rs.next()){
						String makh = rs.getString("makh");
						String tenkh = rs.getString("tenkh");
						String ngsinh = rs.getString("ngsinh");
						String gtinh = rs.getString("gioitinh");
						String diachi = rs.getString("diachi");
						String sodt = rs.getString("sodt");
						Object[] rowData = new Object[]{ makh, tenkh, ngsinh, gtinh, diachi, sodt};
						model.addRow(rowData);
						
						// thêm 1 dòng vào table model
					}
					tbtt.setModel(model); // thêm dữ liệu vào table

					
				} catch (Exception ex) {
					// TODO: handle exception
					System.out.println(ex.getMessage());
				}		
			}
		});


		btThem = new JButton("thêm ");
		btThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				them1 obj = new them1();
				obj.setVisible(true);
				obj.setSize(200, 400);
			}
		});
		btUpdate = new JButton("cap nhat");
		btUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Update obj = new Update();
				//obj.setSize(200, 400);
				//obj.setVisible(true);
			}
		});
		
		btXoa = new JButton("xoa");
		btXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					khachhangitf tv = (khachhangitf)Naming.lookup("rmi://localhost/khObj");//đăng kí máy chủ với registry
					String n = tv.exesql("delete from thongtin where makh = '\"+tfmakh.getText()+\"'");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/khachhang", "root", "");
					PreparedStatement ps = (PreparedStatement)conn.prepareStatement(n);
					ResultSet rs = ps.executeQuery();
					JOptionPane.showConfirmDialog(null, "bạn có muốn xóa"+tfmakh.getText()+"không");
					if(rs!=null) JOptionPane.showConfirmDialog(null, "xóa thành công");
					else JOptionPane.showConfirmDialog(null, "xóa không thành công");
					rs.close();
					conn.close();
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		
		tbtt = new JTable(90, 5);
		tbtt.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				KetNoiDb();
				tbtt = (JTable)e.getSource();
				int rowdata = tbtt.getSelectedRow();
				String makh = (String)tbtt.getValueAt(rowdata, 0);
				String tenkh = (String)tbtt.getValueAt(rowdata, 1);
				String ngsinh = (String)tbtt.getValueAt(rowdata, 2);
				tfmakh.setText(makh);
				tftenkh.setText(tenkh);
				tfngsinh.setText(ngsinh);
			}
		});
		ScrTT = new JScrollPane(tbtt);
		tfSearch = new JTextField(15);
		lblmakh = new JLabel("mã khách hàng");
		tfmakh = new JTextField(12);
		lblngsinh = new JLabel("ngày sinh");
		tfngsinh = new JTextField(12);
		lbltenkh  = new JLabel("tên khách hàng");
		tftenkh = new JTextField(12);
		setLayout(new FlowLayout());
		Container cont = this.getContentPane();
		cont.add(btAll);
		cont.add(tfSearch);
		cont.add(btsearch);
		cont.add(btThem);
		cont.add(btUpdate);
		cont.add(btXoa);
		cont.add(ScrTT);
		cont.add(lblmakh);
		cont.add(tfmakh);
		cont.add(lbltenkh);
		cont.add(tftenkh);
		cont.add(lblngsinh);
		cont.add(tfngsinh);
		
		setLocation(100, 50);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		thongtin2 obj = new thongtin2();
		obj.setSize(500, 600);
		obj.setVisible(true);
	}


}
