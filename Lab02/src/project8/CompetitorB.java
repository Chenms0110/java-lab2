package project8;

import java.util.Random;

public class CompetitorB implements Runnable {
	Random forcharacter;
	Random forsleep;
	int race[][];
	int Sleeptime[][];
	int round;
	int charnum;
	int sleeptime;
	
	public CompetitorB(int array[][],int S[][]) {
		race = array;
		Sleeptime = S;
		round=race.length;
		forcharacter = new Random();
		forsleep = new Random();
	}
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		for(int i=0;i<round;i++) {
			try {
				sleeptime = forsleep.nextInt(1001);
				Sleeptime[i][1]=sleeptime;
				Thread.sleep(sleeptime);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			charnum = forcharacter.nextInt(26) + 97;
			race[i][1] = charnum;
			synchronized(race) {
				race.notifyAll();
				while(race[i][0]!=1||race[i][1]!=1) {
					try {
						race.wait();
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}
		}
	}
}
