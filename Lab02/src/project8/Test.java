package project8;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		// TODO 自动生成的方法存根
		System.out.println("Input the round num：");
		Scanner scanner = new Scanner(System.in);
		int round = scanner.nextInt();
		while(round<1) {
			System.out.println("input wrong!");
			System.out.println("Input the round num：");
			round = scanner.nextInt();
		}
		int race[][] = new int[round][2];
		Judgment judgment = new Judgment(race);
		CompetitorA competitorA = new CompetitorA(race);
		CompetitorB competitorB = new CompetitorB(race);
		
		Thread A = new Thread(competitorA);
		Thread B = new Thread(competitorB);
		judgment.start();
		A.start();
		B.start();
		
		judgment.join();
		A.join();
		B.join();
		
		System.out.println("Race is over");
		scanner.close();
	}

}
