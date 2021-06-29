package zadanie.vlkolensky02;

import java.util.Arrays;

public class Uzol implements Comparable<Uzol> {
	
	//potrebne informacie o uzloch
	private short[][] stav;
	private Uzol parent;
	private int currTah;
	private int cost;
	private int step;
	//konstruktor
	public Uzol(short[][] stav,Uzol parent,int currTah,int cost,int step) {
		this.setStav(stav);
		this.setParent(parent);
		this.setCurrTah(currTah);
		this.setCost(cost);
		this.setStep(step);
	}
	
	//getters and setters

	public short[][] getStav() {
		return stav;
	}

	public void setStav(short[][] stav) {
		this.stav = stav;
	}

	public Uzol getParent() {
		return parent;
	}

	public void setParent(Uzol parent) {
		this.parent = parent;
	}

	public int getCurrTah() {
		return currTah;
	}

	public void setCurrTah(int currTah) {
		this.currTah = currTah;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}
	//to je pre PriorityQueu 
	public int compareTo(Uzol iny) {
		if(this.equals(iny))
			return 0;
		else if(getCost()>= iny.getCost())
			return 1;
		else
			return -1;
	}
	
}
