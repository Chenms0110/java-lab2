package project8;

public class Judgment extends Thread {

	private int a; //��¼A�÷�
	private int b; //��¼B�÷�
	int round; //�غ���
	int race[][];
	int sleeptime[][];
	
	public Judgment(int array[][],int S[][]) {
		race = array;
		sleeptime = S;
		round = race.length;
		a = 0;
		b = 0;
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
			System.out.println("���������������������Щ������������������������������������������������������������������������������������������������������������Щ�������������������������������������������������������������������������������������������������������������");
			System.out.println("��         ��                     Thread A                         ��                     Thread B                         ��");
			System.out.println("��  Round  �����������������������������Щ����������������������������������������Щ��������������������������������������੤�������������������������Щ����������������������������������������Щ���������������������������������������");
			System.out.println("��         ��  Sleeptime  ��  Random character  ��  Points obtained  ��  Sleeptime  ��  Random character  ��  Points obtained  ��");
			System.out.println("���������������������੤�������������������������੤���������������������������������������੤�������������������������������������੤�������������������������੤���������������������������������������੤��������������������������������������");
			for(int i=0;i<round;i++) {
				while(race[i][0]==0||race[i][1]==0) {
					try {
						race.wait();
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
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
				if(i!=0) System.out.println("���������������������੤�������������������������੤���������������������������������������੤�������������������������������������੤�������������������������੤���������������������������������������੤��������������������������������������");
				System.out.printf("��   %3d   ��   %4d ms   ��          %c         ��        %3d        ��   %4d ms   ��          %c         ��        %3d        ��\n",i+1,sleeptime[i][0],race[i][0],a,sleeptime[i][1],race[i][1],b);
				//System.out.println("���������������������੤�������������������������੤���������������������������������������੤�������������������������������������੤�������������������������੤���������������������������������������੤��������������������������������������");
			//	System.out.println("now A gets " + a + " points and B gets " + b + " points.\n");
				race[i][0]=1;
				race[i][1]=1;
				race.notifyAll();
			}
		}
		System.out.println("���������������������ة��������������������������ة����������������������������������������ة��������������������������������������ة��������������������������ة����������������������������������������ة���������������������������������������");
		judge();
	}
}
