package zadanie.vlkolensky02;

public class Heuristic1 {
	
	short[][] ciel;
	int row;
	int col;
	
	//konstruktor
	public Heuristic1(short[][] ciel,int row, int col) {
		this.ciel=ciel;
		this.row=row;
		this.col=col;
	}

	//vypoctime heuristiku
	public int heuCost(short[][] stav) {
		
		int cost=0;
		
		for(int i = 0 ; i<row;i++) {
			for(int j= 0; j<col;j++) {
				//ked cislo je nie je na jeho finalnu miestu tak inkrementuje cenu
				if(stav[i][j]!=ciel[i][j]) {
					cost++;
				}
			}
		}
		//vrati cenu
		return cost;
	}

}
