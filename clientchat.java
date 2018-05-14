package chat;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class clientchat{
JFrame a;
Container c;
JTextArea textn;
JTextField textsend;
JButton gui;

Socket s;
OutputStream os;
InputStream is;
inputstream threadnhap;
outputstream threadxuat;
PrintWriter pw;

public clientchat(){
    GUI(); 
    KhoiTaoSocKet();   
}

private void GUI(){
    a = new JFrame("Client");
    a.setBounds(10, 10, 500, 500);
    a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    textn = new JTextArea();
    textn.setBounds(10, 10, 480, 300);
    textn.setEditable(false);
    textsend = new JTextField();
    textsend.setBounds(10, 320, 300, 30);
    entertextgui keygui = new entertextgui();
    textsend.addKeyListener(keygui);
    
    gui = new JButton("Gui");
    gui.setBounds(320, 320, 80, 30);
    guidi gd = new guidi();
    gui.addActionListener(gd);
            
    
    c= a.getContentPane();
    c.setLayout(null);
    c.add(textn);
    c.add(textsend);
    c.add(gui);
    
    a.setVisible(true);
}
private void KhoiTaoSocKet(){
    try {
        s = new Socket("localhost",8005);
        os = s.getOutputStream();
        is = s.getInputStream();
    } catch (IOException ex) {
        System.out.println("Loi phan socket: " +ex.getMessage());
        if("Connection refused".equals(ex.getMessage())){
            System.out.println("Chua bat server!");
            textn.setText("Không có kết nối với SERVER!");
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex1) {
            Logger.getLogger(clientchat.class.getName()).log(Level.SEVERE, null, ex1);
        }
        System.exit(0);
    }
    
    threadnhap = new inputstream();
    threadnhap.start(); 
}

private void nutgui(){
    
    pw =new PrintWriter(os);
    String gui=textsend.getText();
    System.out.println("Gui: "+gui);
    textsend.setText(textn.getText()+"Me: "+gui+"\n");
    textsend.setText("");
    pw.println(gui); pw.flush();
}


class entertextgui implements KeyListener{
   
    public void keyTyped(KeyEvent e) {
    }

  
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            nutgui();
        }
    }

    public void keyReleased(KeyEvent e) {
    }
}

class inputstream extends Thread{

    public void run() {
        Scanner sc = new Scanner(is);
        String nhan ="";
        while(true){
            nhan = sc.nextLine();
            textn.setText(textn.getText()+"Server: "+nhan+"\n");
            System.out.println("\n---- Nhan: " + nhan);
        }
    }
    
}

class outputstream extends Thread{
   
    public void run() {
        pw =new PrintWriter(os);
        Scanner sc = new Scanner(System.in);
        String gui="";
        while(true){
            System.out.print("Gui: ");
            gui = sc.nextLine();
            pw.println(gui); pw.flush();
        }
    }
}

class guidi implements ActionListener{

    public void actionPerformed(ActionEvent e) {
        pw =new PrintWriter(os);
        String gui=textsend.getText();
        System.out.println("Gui: "+gui);
        textn.setText(textn.getText()+"Me: "+gui+"\n");
        textsend.setText("");
        pw.println(gui); pw.flush();
    }
}

public static void main(String[] args) {
    clientchat b = new clientchat();
}
}