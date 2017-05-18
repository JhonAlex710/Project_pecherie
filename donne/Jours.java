package donne;

import java.util.ArrayList;

public class Jours extends CommunAuxDonne implements CommunAuDonne {
	private ArrayList<ReleveDateHeureEau>listedeDonne2;
	public Jours(int numero) {
		super(numero);
		// TODO Auto-generated constructor stub
		listedeDonne2 = new ArrayList<ReleveDateHeureEau>();
	}
	
	public ArrayList<ReleveDateHeureEau> getListeDeDonne2() {
		// TODO Auto-generated method stub
		return listedeDonne2;
	}

	public ReleveDateHeureEau getListeDeDonne2(int position) {
		// TODO Auto-generated method stub
		return listedeDonne2.get(position);
	}

	public int getListeDeDonne2(ReleveDateHeureEau moi) {
		int compteur=0;
		for (ReleveDateHeureEau releveDateHeureEau : this.listedeDonne2) {
			if (moi.getHauteurEau()==releveDateHeureEau.getHauteurEau()) {
				break;
			}else {
				compteur=compteur+1;
			}
		}
		return compteur;
	}
	public void setListeDeDonne2(ArrayList<ReleveDateHeureEau> listedeDonne) {
		// TODO Auto-generated method stub
		this.listedeDonne2=listedeDonne;
	}

	
	public void setListeDeDonne2(ReleveDateHeureEau listeDeMois) {
		// TODO Auto-generated method stub
		this.listedeDonne2.add(listeDeMois);
	}

	
	public void setListeDeDonne2(int position, ReleveDateHeureEau Donnee) {
		// TODO Auto-generated method stub
		this.listedeDonne2.set(position, Donnee);
	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		System.out.println("Jours");
	}
}
