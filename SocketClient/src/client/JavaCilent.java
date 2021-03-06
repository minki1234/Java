package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class JavaCilent {
	public static void main(String[] args) {
		Myclient mc = new Myclient();
		while(true) {
			mc.mystart();
		}
	}
}


class Myclient{
	InputStream is;
	OutputStream os;
	Socket socket;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Scanner sc;
	String rMsg;
	
	public void mystart() {
		try {
			//192.168.3.6
			socket = new Socket("192.168.3.6", 5000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sendMessage(socket);
		receiveMessage(socket);
	}
	public void sendMessage(Socket socket) {
		try {
			os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
			sc = new Scanner(System.in);
			System.out.println("보낼 메시지>>");
			String sMsg = sc.nextLine();
			oos.writeObject(sMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void receiveMessage(Socket socket) {
		try {
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
			rMsg = (String)ois.readObject();
			System.out.println(rMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}	