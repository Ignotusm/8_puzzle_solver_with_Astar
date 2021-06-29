package zadanie.vlkolensky02;

import java.util.Arrays;
import java.util.PriorityQueue;

public class AstarWithHeuOne {
	
	public AstarWithHeuOne(short[][] start,short[][] ciel,int row,int col) {
		
		//vytvorime objekty co budem potrebovat pre a star algoritmus
		HashTable table = new HashTable(row,col);
		Generator gen = new Generator();
		PriorityQueue<Uzol> minHeap = new PriorityQueue<Uzol>();
		Heuristic1 heu1=new Heuristic1(ciel,row,col);
		
		//definujem pomocne premenna
		boolean win=false;
		int position=0;
		int cost =0;
		short[][] newStav=null;
		Uzol parent=null;
		int mennyiszer= 0;
		
		//vytvorim startocaci uzol
		Uzol newUzol = new Uzol(start,null,0,0,0);
		//hned dam ze novy uzol je aj parent
		parent = newUzol;
		//vypocitam poziciu kde budem dat do hashovacej tabulku
		position=table.hash(start, 0, row, col);
		//dam do tabulku
		table.addToTable(position, newUzol);
		//vytvorim nasledujuce stavy
		for(int i = 1;i<=4;i++) {
			//vygenerujem novy stav
			newStav=gen.genStav(parent.getStav(),parent.getCurrTah(), i, row, col);
			//ked vrateni stav nie je null takze mozem taku tah spravit 
			if(newStav != null) {
				//vypocitam cenu pre stav
				cost=(parent.getStep()+1)+heu1.heuCost(newStav);
				//vypocitam poziciu
				position = table.hash(newStav, cost, row, col);
				//ked pozicia nie je -1 tak 
				if(position!=-1) {
					// urobim novy uzol pre danu stav 
					newUzol = new Uzol(newStav,parent,i,cost,parent.getStep()+1);
					// dam do tabulu
					table.addToTable(position, newUzol);
					//dam do minHeap
					minHeap.add(newUzol);
					//inkrementujem pocitadlo ze kolko aby som vedel kolko uzol vytvoril 
					mennyiszer++;
				}
			}
		}
		
		//zacinam hladat najlacnejsiu cestu
		while(minHeap.isEmpty()!=true) {
			//pre parenta zvolim najlacnejsiu uzlu co som vybral z heapu
			parent = minHeap.poll();
			//pozrem ci nenasiel som koncujuci stav
			if(table.compareArray(ciel, parent.getStav(), row, col)) {
				//ked nasiel som koncovy tak koncim hladanie
				win=true;
				break;
			}
			//vygenerujem nasledovnikov
			for(int i = 1;i<=4;i++) {
				//vytvorim novy stav
				newStav=gen.genStav(parent.getStav(),parent.getCurrTah(), i, row, col);
				//ked stav nie je null tak 
				if(newStav != null) {
						//vypocitam cenu
					cost=(parent.getStep()+1)+heu1.heuCost(newStav);
						// vypocitam poziciu 
					position = table.hash(newStav, cost, row, col);
						//ked este taky stav neexistuje tak dam do tabulu a aj ked existuje a ma 
						// lepsiu cenu tak prepisem uz existujuci
					if(position!=-1) {
						//vytvorim novy uzol
						newUzol = new Uzol(newStav,parent,i,cost,parent.getStep()+1);
						//dam do tabulky
						table.addToTable(position, newUzol);
						// dam do minheap
						minHeap.add(newUzol);
						//inkrementujem pocitadlo
						mennyiszer++;
					}
				}
			}
		}
		
		//vypisujem kolko uzlov vytvoril
		System.out.println("Pocet vytvorenych uzlov  "+ mennyiszer);
		
		//tieto variable teraz budu pomocne
		newUzol=parent;
		parent = newUzol;
		mennyiszer=0;
		//ked som nasiel cestu tak vypisem operatort
		if(win) {
			
		int[] operatory = new int[parent.getStep()];
		
		while(newUzol.getParent()!=null) {
		
			
			//dam operatory do poli
			operatory[mennyiszer]=newUzol.getCurrTah();
			//newuzol bude ukazovat na parenta
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
