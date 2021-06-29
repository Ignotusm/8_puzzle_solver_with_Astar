package zadanie.vlkolensky02;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Reader read = new Reader();
		Uzol uzol;
		
		
		//zacitame informacie o hlavolamu
		//najprv nacitame row
		System.out.println("Row : ");
		int row = scanner.nextInt();
		scanner.nextLine();
		//nacitame collumn
		System.out.println("Collumn : ");
		int col = scanner.nextInt();
		scanner.nextLine();
		//vytvorime startovaci a cielovy stav 
		short[][] start;
		short[][] ciel ;
		
		//pomocou read class zacitame tieto stavy formou "0 1 2 3 4 5 ... "
		start = read.readStart(row, col);
		ciel = read.readFinish(row, col);
		
		
		//mozeme zvolit heuristiku 1 alebo 2
		System.out.println("Zvolte heuristiku 1 alebo 2 :");
		int zvol=0;
		while(true) {
			zvol=scanner.nextInt();
			scanner.nextLine();
			
			//ked zvolime jednotku tak pouzivame heuristiku 1
			if(zvol == 1) {
				
				long startTime = System.nanoTime();
				
				AstarWithHeuOne algWithHeuOne = new AstarWithHeuOne(start,ciel,row,col);
				
				long endTime = System.nanoTime();
				
				long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds
				
				System.out.println("Duration : "+duration);
				
				break;
			}
			
			//ked zvolime dvojku tak pouzivame heuristiku 2
			if(zvol == 2) {
				
				long startTime = System.nanoTime();
				
				AstarWithHeuTwo algWithHeuTwo = new AstarWithHeuTwo(start,ciel,row,col);
				
				long endTime = System.nanoTime();
				
				long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds
				
				System.out.println("Duration : "+duration);
				
				break;
			}
			
			System.out.println("Zvolte heuristiku 1 alebo 2 : ");
			
		}
		
		
		
		
	}

}
