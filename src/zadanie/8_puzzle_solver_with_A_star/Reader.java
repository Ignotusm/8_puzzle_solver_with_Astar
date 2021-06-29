package zadanie.vlkolensky02;

import java.util.Scanner;

public class Reader {
	
	//pomocou readStart nacitam startovaci stav

	public short[][] readStart(int row,int col) {
		
		Scanner scanner = new Scanner(System.in);
		
		short[][] start = new short[row][col];
		
		
		System.out.println("Add starting elements : ");
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				 start[i][j]=scanner.nextShort();
			}
		}
		
		return start;
		
	}
	
	//pomocou readFinish nacitam cielovy stav
	
	public short[][] readFinish(int row,int col) {
			
			Scanner scanner = new Scanner(System.in);
			
			short[][] finish = new short[row][col];
			
			
			System.out.println("Add starting elements : ");
			for(int i=0;i<row;i++) {
				for(int j=0;j<col;j++) {
					 finish[i][j]=scanner.nextShort();
				}
			}
			
			return finish;
			
		}
	
	
	
}
