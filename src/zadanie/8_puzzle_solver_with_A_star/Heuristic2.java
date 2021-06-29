package zadanie.vlkolensky02;

import java.util.Arrays;

public class Heuristic2 {
	
	short[][] ciel;
	int row;
	int col;
	private int[] x ;
	private int[] y ;
	//konstruktor
	public Heuristic2(short[][] ciel,int row,int col){
	
		this.ciel=ciel;
		this.row=row;
		this.col=col;
		
		x=new int[(row*col)];
		y=new int[(row*col)];
		// vypocitem x a y koordinata pre jednotlive cisla
		searchCoordinates();
	}
	
	//vypocitam kordinacia pre jednotlive cisla
	public void searchCoordinates() {
		
		int position;
		//x je i a y je j a podta toho to hashujem do jeho miestu
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				//vypocitam cisla
				position = ciel[i][j]%(row*col);
				//dam x a y koordinaty na jeho miesto
				x[position]=i;
				y[position]=j;
			}
		}
	}
	
	//vykalkulujem cenu 
	public int calculateCost(short[][] stav) {
		
		int cost = 0;
		int position;
		int thisX;
		int thisY;
		
		for(int i=0;i<row;i++) {
			for(int j=0; j <col ;j++) {
				
				//pre vsetky cisla pozrem kde su v cielovom poli
				position = stav[i][j];
				//vykalkulujem vzdialenost
				thisX=Math.abs((x[position]-i));
				thisY=Math.abs((y[position]-j));
				//vypocitam sumu
				cost=cost+thisX+thisY;
			}
		}
		//vratim cenu 
		return cost;
	}
}
