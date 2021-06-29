package zadanie.vlkolensky02;

public class HashTable {
	
	private Uzol[] table;
	private int size;
	private int numberOfItems;
	
	
	//konstruktor pre hashovej tabulku
	public HashTable(int col , int row) {
		
		int length = size(row,col);
		
		table = new Uzol[length];
		this.size=length;
	}
	
	//vypocitame velkost tabulku
	public int size(int col , int row) {
		
		int fakt = 1;
		int num = row*col;
		
		for(int i = 2 ; i<=num;i++) {
			
			fakt*=i;
			
		}
		
		fakt=fakt*2 + 1;
		
		if(fakt>Integer.MAX_VALUE-10 || fakt < 0) {
			return (Integer.MAX_VALUE-10000)+1;
		}
		else {
			return fakt;
		}
		
	}
	
	
	//vrati cislo ked nasiel poziciu kde mozeme dat novy stav , ked stav uz existuje tak vrati -1
	public int hash(short[][] stav,int cost,int row,int col) {
		
		int times=1; 			//potrebujem pre double hash
		int pom = 0;			//potrebujem keby nestacilo velkost tabulku a aby som mohol rehashovat to
		int position;			// pomocne integer
		int newSize = size*2+1;	//novy rozmory pre tabluku
		
		
		//vypocitam kolko percanta tabulky uz je obsadene
		double alfa = (double)numberOfItems/(double)size; 
		//ked viac ako 75% tak to budem rehashovat
		if(alfa>0.75) {
			
			//vytvorime novu tabulku pre vacsiu velkost
			Uzol[] newTable = new Uzol[size*2+1]; 
			
			//zaciname rehashovat 
			while(pom<size) {
				// v malom tabulky hladame obsadene miesta co mozem rehashovat
				if(table[pom]!=null) {
					//vypocitam novu poziciu pre uzla
					position = (getHashed(table[pom].getStav(), times,row,col))%newSize;
					//ked je v novom tabulku je prazdna 
					if(newTable[position]==null) {
						//tak dam tam moj uzol z starsieho tabulky
						newTable[position]=table[pom];
						//ked nie tak urobim double hashovanie 
					}else {
						while(true) {
							//urobim double hash kym nenajdem volnu niesto
							position = (getHashed(table[pom].getStav(),times,row,col))%newSize;
							//ked najdem tak nastavim times na 1 a dam do novej tabulku uzla z starsieho tabulku
							if(newTable[position]==null) {
								newTable[position]=table[pom];
								times=1;
								break;
							}
							//ked nenajdem volnu tak inkrementujem times
							times = times+1;
						}
					}	
				}//inkrementujem pom a tak prehladavam celu starsiu tabulku
				pom++;
			}//stari size zvolim na novu a stary table zvolim na novy tabulu
			size=size*2+1;
			table=newTable;
		} 
			//hladam miesto do tabulky
			while(true) {//vypocitam poziciu 
				position = (getHashed(stav,times,row,col))%size;
				//kontroljem poziciu ci je volny
				if(table[position]==null) {
					//ked ano tak returnujem tu poziciu
					times=1;
					numberOfItems++;
					return position;
					//ked nie je volny tak urobim compare aby som vedel ci je to ten isti alebo musim este raz hashovat
				}else if(compareArray(table[position].getStav(), stav, row, col)){
					//ked je to rovnaky a ma mensiu cenu tak dam novy uzol do tabuly 
					if(table[position].getCost()>cost) {
						return position;
						
					}
				//	ked nie je dobry tak iba vratim -1
					return -1;
					
				}else {
				//ked nebol rovnaky a nebol volny tak times nasobim a hashujem znova
				times=times*times+1;
				}	
			}
	}
	
	
	//vrati poziciu kde mozme dat uzla cize to je hashovacia funkcia
	public int getHashed(short[][] stav,int times,int row,int col) {
		
		long sum=1;
		int num;
		
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				
				sum=sum*31+(stav[i][j]);		
				
			}
		}
		
		num = (int) (sum+times);
		
		if(num<0) {
			num=num*(-1);
		}
		
		return num;
		
	}
	
	// pomocou tohto dam uzla do tabulu na zvolenu poziciu
	public void addToTable(int position,Uzol uzol) {
		
		table[position]=uzol;
		
	}
	
	// pomocou pozrem ci poli su rovnake
	public boolean compareArray(short[][]array1,short[][]array2,int row,int col) {
		
		boolean same = true;
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				
				if(array1[i][j]!=array2[i][j]) {
					same = false;
				}
				
			}
		}
		
		return same;
	}

}
