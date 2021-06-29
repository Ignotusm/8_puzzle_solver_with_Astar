package zadanie.vlkolensky02;

public class Generator {
	
	public short[][] genStav(short[][] currStav,int predTah, int tah,int row, int col){
		
		short[][] newStav = new short[row][col];
		int x=0;
		int y=0;
	
		
		//najprv najdem pozicu na co mozem urobit operacie
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				
				if(currStav[i][j]==0) {
					x=i;
					y=j;
				}
				
				newStav[i][j]=currStav[i][j];
				
				
			}
		}
		
		
		//up ked tah rovna sa jedna
		
		if(tah == 1) {
			//ked chcem up a predcahzajuce bol down tak vratim null aby som nerobil cykli
			if(predTah == 3 || (x-1)<0) {
				return null;
			}else {
				//vymenim cislo s nulom
				newStav[x][y]=newStav[x-1][y];
				newStav[x-1][y]=0;
				//returnujem novy stav
				return newStav;
			}
			
		}
		
		//right ked tah rovna sa 2
				if(tah == 2 ) {
					//ked predhadzajuce stav rovna bol left a ja chcem right tak to nevygenerujem a este pozrem ci 
					//tah co chcem este je v poli 
					if(predTah ==4  || y+1>=col) {
						return null;
					}else {
						//vymenim cisla
						newStav[x][y]=newStav[x][y+1];
						newStav[x][y+1]=0;
						//vratim novy stav
						return newStav;
					}
				}
		
		//down ked chceme spravit tah 3
		if( tah == 3) {
			//ked chcem is dole a predchadzajuce bol hore tak to nerobime a vratime null
			//kontrulujem ci je este v rozmery 
			if(predTah == 1|| x+1>=row) {
				return null;
			}else {
				//vyemenim cisla
				newStav[x][y]=newStav[x+1][y];
				newStav[x+1][y]=0;
				//vratim novy stav
				return newStav;
			}
		}
		

		//left ked chcem spravit tah 4
		if( tah == 4) {
			//ked chcem left ale predchadzajuci tah bol right tak vratim null
			//a kontrolujem ci ten operaciu mozem este spravit
			if(predTah == 2 || y-1<0) {
				return null;
			}else {
				//vymenim cisla
				newStav[x][y]=newStav[x][y-1];
				newStav[x][y-1]=0;
				//vratim novy stav
				return newStav;
			}
		}
		
		return null;
		
	}

}
