package delete_file;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Frame;
import java.io.File;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class delfile {

	private JFrame frame;
    String t;
	Vector<String> e = new Vector<String>();
	int i=0;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					delfile window = new delfile();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	    public static void delete(File file) {
	 
	        if (file.isDirectory()) {
	            // kiểm tra thư mục trống, sau đó xóa nó
	            if (file.list().length == 0) {
	                file.delete();
	                System.out.println("Directory is deleted : " + file.getAbsolutePath());
	            } else {
	                String files[] = file.list();
	                for (String temp : files) {
	                    File fileDelete = new File(file, temp);
	 
	                    delete(fileDelete);
	                }
	 
	                if (file.list().length == 0) {
	                    file.delete();
	                    System.out.println("Directory is deleted : " + file.getAbsolutePath());
	                }
	            }
	 
	        } else {
	            //nếu là tệp, thì xóa
	            file.delete();
	            System.out.println("File is deleted : " + file.getAbsolutePath());
	        }
	    }

	
	public delfile() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 619, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTree tree = new JTree();
		tree.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
				t = tree.getSelectionPath().toString().replaceAll("[\\[\\]]", "").replace(", ", "\\");
				e.add(t);
				}catch (Exception e) {
					// TODO: handle exception
					
				}
			}
		});
		tree.setFont(new Font("SansSerif", Font.PLAIN, 17));
		tree.setBounds(10, 10, 202, 369);
		frame.getContentPane().add(tree);
		
		tree.setModel(new file(new File("D:\\")));
		
		JScrollPane scrollPane = new JScrollPane(tree);
		scrollPane.setBounds(10, 10, 352, 369);
		frame.getContentPane().add(scrollPane);
		
		JButton btnthem = new JButton("Add");
		btnthem.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnthem.setBounds(437, 120, 100, 42);
		frame.getContentPane().add(btnthem);
		
		JButton btnsua = new JButton("Rename");
		btnsua.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnsua.setBounds(437, 180, 100, 42);
		frame.getContentPane().add(btnsua);
		
		JButton btnXoa = new JButton("Delete");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Enumeration<String> q1 = e.elements();
				while(q1.hasMoreElements()) {
					String n = q1.nextElement();
					try {
						File m = new File(n);
						delete(m);
						System.out.println("Xóa thành công");
						tree.setModel(new file(new File("D:\\")));
						
					}catch (Exception e) {
						
						System.out.println(e);
					 
					}
				}
				
			}
		});
		btnXoa.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnXoa.setBounds(437, 240, 100, 42);
		frame.getContentPane().add(btnXoa);
		
		
	}
}

