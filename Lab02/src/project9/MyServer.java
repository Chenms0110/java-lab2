package project9;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Date;

public class MyServer {

	static int A; //a得分
	static int B; //b得分
	
	public static void main(String[] args) {
		//TCP
		try {
	    ServerSocket serverSocket = new ServerSocket(2333);
	    System.out.println("Game Started");
	    Socket socket = serverSocket.accept();
	    DataInputStream in = new DataInputStream(socket.getInputStream());
	    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	    int round = in.readInt();
	    System.out.println("round is :" + round);
	    
	    //UDP
	    byte[] buf = new byte[1024];
	    DatagramSocket socketA = new DatagramSocket(2333);
	    DatagramPacket rcvPkt = new DatagramPacket(buf, buf.length);
	    DatagramPacket sndPkt = new DatagramPacket(buf, buf.length);
	    
	    
	    System.out.println("┌─────────┬──────────────────────────────────────────────────────┬──────────────────────────────────────────────────────┐");
		System.out.println("│         │                     Thread A                         │                     Thread B                         │");
		System.out.println("│  Round  ├─────────────┬────────────────────┬───────────────────┼─────────────┬────────────────────┬───────────────────┤");
		System.out.println("│         │  Sleeptime  │  Random selection  │  Points obtained  │  Sleeptime  │  Random character  │  Points obtained  │");
		System.out.println("├─────────┼─────────────┼────────────────────┼───────────────────┼─────────────┼────────────────────┼───────────────────┤");
		for(int i=0;i<round;i++) {
			buf = new byte[1024];
			Arrays.fill(buf,(byte)0);
			rcvPkt.setData(buf);
			socketA.receive(rcvPkt);
			int sleeptime[] = new int[2];
			sleeptime[0] = Integer.parseInt(new String(buf).trim());
			sleeptime[1] = in.readInt();
			
			buf = new byte[1024];
			Arrays.fill(buf,(byte)0);
			rcvPkt.setData(buf);
			socketA.receive(rcvPkt);
			int a = Integer.parseInt(new String(buf).trim());
			int b = in.readInt();
	        
	        sndPkt.setAddress(rcvPkt.getAddress());
	        sndPkt.setPort(rcvPkt.getPort());
	        buf = Integer.valueOf(1).toString().getBytes();
	        sndPkt.setData(buf, 0, buf.length); 
	        judge(a,b);
	        if(i!=0) System.out.println("├─────────┼─────────────┼────────────────────┼───────────────────┼─────────────┼────────────────────┼───────────────────┤");
			System.out.printf("│   %3d   │   %4d ms   │       %-8s     │        %3d        │   %4d ms   │       %-8s     │        %3d        │\n",i+1,sleeptime[0],changetoPose(a),A,sleeptime[1],changetoPose(b),B);
	        socketA.send(sndPkt);
	        out.writeInt(round);
		}
		System.out.println("└─────────┴─────────────┴────────────────────┴───────────────────┴─────────────┴────────────────────┴───────────────────┘");
		Victory();
		socket.close();
		socketA.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public static String changetoPose(int i) {
		switch(i) {
		case 0:return "scissors";
		case 1:return "rock";
		case 2:return "paper";
		default:return "hahaha";
		}
	}
	
	public static void Victory() {
		 //A (or B or None) is the winner 
		if(A>B) {
			System.out.println("A is the winner!");
		}
		else if(A<B) {
			System.out.println("B is the winner!");
		}
		else {
			System.out.println("None is the winner.");
		}
	}
	
	
	public static void judge(int a,int b) {
		if(a==b) {
			A += 1;
			B += 1;
			return;
		}
		
		switch(a) {
		case 0: //剪刀
			if(b == 1) {
				B += 2;
			}
			else if(b == 2) {
				A += 2;
			}break;
		case 1: //石头
			if(b == 2) {
				B += 2;
			}
			else if(b == 0) {
				A += 2;
			}break;
		case 2: //布
			if(b == 0) {
				B += 2;
			}
			else if(b == 1) {
				A += 2;
			}break;
		}
	}
	
}
