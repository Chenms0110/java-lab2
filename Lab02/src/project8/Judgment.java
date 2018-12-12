package project8;

public class Judgment extends Thread {

	private int a; //记录A得分
	private int b; //记录B得分
	int round; //回合数
	int race[][];
	
	public Judgment(int array[][]) {
		race=array;
		round=race.length;
		a=0;
		b=0;
	}
	
	public void judge() {
		 //A (or B or None) is the winner 
		if(a>b) {
			System.out.println("A is the winner!");
		}
		else if(a<b) {
			System.out.println("B is the winner!");
		}
		else {
			System.out.println("None is the winner.");
		}
	}
	
	@Override
	public void run() {
		synchronized(race) {
			for(int i=0;i<round;i++) {
				while(race[i][0]==0||race[i][1]==0) {
					try {
						race.wait();
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				if(race[i][0] == race[i][1]) {
					a += 1;
					b += 1;
				}
				else if(race[i][0] > race[i][1]) {
					a += 2;
				}
				else {
					b += 2;
				}
				System.out.println("now A gets " + a + " points and B gets " + b + " points.\n");
				race[i][0]=1;
				race[i][1]=1;
				race.notifyAll();
			}
		}
		judge();
	}
}
