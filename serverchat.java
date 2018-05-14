package chat;

import java.awt.Container;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class serverchat {
// frame
JFrame z;
Container c;
JTextArea textn;
JTextField textsend;
JButton gui;

Socket s;
OutputStream os;
InputStream is;
inputstream threadnhap;

PrintWriter pw;
ServerSocket ss;
private void GUI(){
     z= new JFrame("server");
    z.setBounds(10, 10, 500, 500);
    z.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    textn = new JTextArea();
    textn.setBounds(10, 10, 480, 300);
    textsend = new JTextField();
    textsend.setBounds(10, 320, 300, 30);
    entertextgui entergui = new entertextgui();
    textsend.addKeyListener(entergui);
    // button
    gui = new JButton("Gui");
    gui.setBounds(320, 320, 80, 30);
    guidi gd = new guidi();
    gui.addActionListener(gd);
           
    c= z.getContentPane();
    c.setLayout(null);
    c.add(textn);
    c.add(textsend);
    c.add(gui);
    
    z.setVisible(true);
}
public serverchat(){
    GUI(); 
    KhoiTao();
    
}
private void KhoiTao(){
    
    try {
        ss = new ServerSocket(8005);
        System.out.println("Da tao server port 8005");
        s = ss.accept();
        
        System.out.println("Co client ket noi");
        os = s.getOutputStream();
        is = s.getInputStream();
        threadnhap = new inputstream();
        threadnhap.start();
    } catch (IOException ex) {
        System.out.println("Loi phan socket: " +ex.getMessage());
    }
}

private void nutgui(){
    
    try{
        
        
        pw =new PrintWriter(os);
        String gui=textsend.getText();
        System.out.println("Gui: "+gui);
        textn.setText(textn.getText()+"Server(me): "+gui+"\n");
        textsend.setText("");
        pw.println(gui); pw.flush();
    } catch (NullPointerException e){
        System.out.println("Loi nhap xuat, do chua co client ket noi");
        JOptionPane.showMessageDialog(textn, "Loi");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(serverchat.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }
}


class entertextgui implements KeyListener{
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            nutgui();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}



class inputstream extends Thread{

    @Override
    public void run() {
        Scanner sc = new Scanner(is);
        String nhan ="";
        while(true){
            nhan = sc.nextLine();
            textn.setText(textn.getText()+"Client: "+nhan+"\n");
            System.out.println("\n---- Nhan: " + nhan);
        }
    }
    
}

class guidi implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
       nutgui();
    }
    
}
public static void main(String[] args) {
    serverchat b = new serverchat();
}
}
