package zadanie.vlkolensky02;

import java.util.PriorityQueue;

public class AstarWithHeuTwo {

	public AstarWithHeuTwo(short[][] start,short[][] ciel,int row,int col) {
		
		//vytvorime objekty co budem potrebovat pre a star algoritumus
		HashTable table = new HashTable(row,col);
		Generator gen = new Generator();
		PriorityQueue<Uzol> minHeap = new PriorityQueue<Uzol>();
		Heuristic2 heu2=new Heuristic2(ciel,row,col);
		
		//vytvorim pomocne premenna
		int position=0;
		int cost =0;
		short[][] newStav=null;
		Uzol parent=null;
		int mennyiszer= 0;
		boolean win=false;
		
		//vytvorim startovaci uzol
		Uzol newUzol = new Uzol(start,null,0,0,0);
		//hned dam ze novy uzol je aj parent
		parent = newUzol;
		//vypocitam poziciu kde dam do tabulu
		position=table.hash(start, 0, row, col);
		//dam do tabluku 
		table.addToTable(position, newUzol);
		//vytvorim nasledujuce stavy
		for(int i = 1;i<=4;i++) {
			//vygenerujem novy stav
			newStav=gen.genStav(parent.getStav(),parent.getCurrTah(), i, row, col);
			//ked vrateni stav nie je null takze mozem spravit zvolenu tah
			if(newStav != null) {
				//vypocitam cenu
				cost=(parent.getStep()+1)+heu2.calculateCost(newStav);
				//vypocitam poziciu
				position = table.hash(newStav, cost, row, col);
				//ked nie je -1 takze mozem dat do tabulu
				if(position!=-1) {
					//vytvorim novy uzol
					newUzol = new Uzol(newStav,parent,i,cost,parent.getStep()+1);
					// dam do tabulu
					table.addToTable(position, newUzol);
					// dam novy uzol do heap
					minHeap.add(newUzol);
					//inkrementujem pocitadlo aby som vedel ze kolko uzlov som vytvoril
					mennyiszer++;
				}
			}
		}
		
		
		//zacinam hladat najlacnejsiu cestu
		while(minHeap.isEmpty()!=true) {
			//pre parenta zvolim najlacnejsiu uzlu co som vybral z heap
			parent = minHeap.poll();
			//pozriem ci som nedostal cielovy stav
			if(table.compareArray(ciel, parent.getStav(), row, col)) {
				//ked ano tak win je true a koncim hladanie 
				win=true;
				break;
			}
			//vytvorim nasledovnikov pre parenta
			for(int i = 1;i<=4;i++) {
				//vytvorim novy stav
				newStav=gen.genStav(parent.getStav(),parent.getCurrTah(), i, row, col);
				//ked tah je legalny tak
				if(newStav != null) {
						//vypocitam cost pre s danou hieristikou
					cost=(parent.getStep()+1)+heu2.calculateCost(newStav);
						//vypocitam poziciu 
					position = table.hash(newStav, cost, row, col);
					//ked este taky stav neexistuje tak dam do tabulu a aj ked existuje a ma 
					// lepsiu cenu tak prepisem uz existujuci
					if(position!=-1) {
						//vytvorim novy uzol
						newUzol = new Uzol(newStav,parent,i,cost,parent.getStep()+1);
						//dam do tabulky
						table.addToTable(position, newUzol);
						//dam do min heap
						minHeap.add(newUzol);
						//inkrementujem pocitadlo
						mennyiszer++;
					}
				}
			}
		}
		
		//vypisujem pocet vytvorenych uzlov
		System.out.println("Pocet vytvorenych uzlov"+mennyiszer);
		
		//tieto pouzivam ako pomocne variable
		newUzol=parent;
		parent = newUzol;
		mennyiszer=0;
		
		//ked nasli sme cestu 
		if(win) {
			
			int[] operatory = new int[parent.getStep()];
			
			while(newUzol.getParent()!=null) {
			
			//skladam do tejto poli operatory
			operatory[mennyiszer]=newUzol.getCurrTah();
			//newUzol teraz bude ukazovat na jeho parenta a to opakuje kym nedostaneme zacitak
			newUzol=newUzol.getParent();
			mennyiszer++;
		}
			
			
			//pomocne var
			int num=0;
			
			
			//vypisujem operatory od zaciatku do konci
			for(int i=mennyiszer-1 ;i>=0;i--) {
				
				num++;
				
				if(operatory[i]==1) {
					System.out.println(num+".   UP");
				}else if(operatory[i]==2) {
					System.out.println(num+".  RIGHT");
				}else if(operatory[i]==3) {
					System.out.println(num+".  DOWN");
				}else if(operatory[i]==4) {
					System.out.println(num+".  LEFT");
				}
				
			}
		
			
		//vypisujem dlzku cesty
		System.out.println();
		System.out.println("Dlzka cesty : "+mennyiszer);
		System.out.println();
		
		}else {
			
			//ked nenasli sme cestu
			System.out.println("Neexistuje cesta");
		}
	}
	
}
