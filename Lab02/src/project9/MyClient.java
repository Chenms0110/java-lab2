package project9;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MyClient {

	public static void main(String[] args) throws InterruptedException {
		// TODO 自动生成的方法存根
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input the round num: ");
		int round = scanner.nextInt();
		scanner.close();
		
		CompetitorA competitorA = new CompetitorA(round);
		CompetitorB competitorB = new CompetitorB(round);
		
		Thread t1 = new Thread(competitorA);
		Thread t2 = new Thread(competitorB);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		
	//	System.out.println("Over");
	}
}

class CompetitorA implements Runnable{

	InetAddress server;
	DatagramPacket sndPkt;
	DatagramPacket rcvPkt;
	Random forpose;
	Random forsleep;
	int round;
	int pose;
	int sleeptime;
	byte[] buf;
	
	public CompetitorA(int round) {
		this.round=round;
		forpose = new Random();
		forsleep = new Random();
	}
	
	public String changetoPose(int i) {
		switch(i) {
		case 0:return "scissors";
		case 1:return "rock";
		case 2:return "paper";
		default:return "hahaha";
		}
	}
	
	@Override
	public void run() {
		 try(DatagramSocket socket = new DatagramSocket()) {
			 for(int i=0;i<round;i++) {
				 
				   pose = forpose.nextInt(3);
				   sleeptime = forsleep.nextInt(1001);
				   
				   buf = new byte[1024];
				   server  = InetAddress.getByName("localhost");
				   sndPkt = new DatagramPacket(buf, buf.length);
				   sndPkt.setAddress(server);
				   sndPkt.setPort(2333);
				   buf = Integer.valueOf(sleeptime).toString().getBytes();
				   sndPkt.setData(buf);
				   socket.send(sndPkt);
				   try {
					   Thread.sleep(sleeptime);
				   } catch (InterruptedException e) {
					   // TODO 自动生成的 catch 块
					   e.printStackTrace();
				   }
				   buf = new byte[1024];
				   server  = InetAddress.getByName("localhost");
				   sndPkt = new DatagramPacket(buf, buf.length);
				   sndPkt.setAddress(server);
				   sndPkt.setPort(2333);
				   buf = Integer.valueOf(pose).toString().getBytes();
				   sndPkt.setData(buf);
				   socket.send(sndPkt);
				 //  System.out.println("Round" + (i+1) + ": " + "A: sleeptime is " + sleeptime + "ms, and pose is " + changetoPose(pose));
				   //发送完毕
				   
				   buf = new byte[1024];
				   Arrays.fill(buf, (byte)0);
				   rcvPkt = new DatagramPacket(buf, buf.length);
				   socket.receive(rcvPkt);
			 }
		 } catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
	}
}

class CompetitorB implements Runnable{
	
	Socket socket = null;
	Random forpose;
	Random forsleep;
	int round;
	int pose;
	int sleeptime;
	
	
	public CompetitorB(int round) {
		this.round = round;
		forpose = new Random();
		forsleep = new Random();
		try {
			socket = new Socket("localhost",2333);
			DataOutputStream out1 = new DataOutputStream(socket.getOutputStream());
			out1.writeInt(round);
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public String changetoPose(int i) {
		switch(i) {
		case 0:return "scissors";
		case 1:return "rock";
		case 2:return "paper";
		default:return "hahaha";
		}
	}
	
	@Override
	public void run() {
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());	
			for(int i=0;i<round;i++) {
				pose = forpose.nextInt(3);
				sleeptime = forsleep.nextInt(1001);
				out.writeInt(sleeptime);
				try {
					Thread.sleep(sleeptime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				out.writeInt(pose);
				//System.out.println("Round" + (i+1) + ": " + "B: sleeptime is " + sleeptime + "ms, and pose is " + changetoPose(pose));
				in.readInt();
			}
		}catch(IOException ex){
			
		}
		
	}
}