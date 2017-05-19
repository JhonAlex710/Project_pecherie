package donne;

import java.util.ArrayList;

public class Mois extends CommunAuxDonne {

	private final String nomDuLieu;
	private final int annee;
	private ArrayList<Jours> listedeDonne2 = new ArrayList<>();

	/*public Mois(int numero) {
		// TODO Auto-generated constructor stub
		super(numero);
		nomDuLieu="";
		annee=0;
	}*/
	
	public Mois(int mois, int annee, String nomDuLieu) {
		// TODO Auto-generated constructor stub
		super(mois);
		this.annee=annee;
		this.nomDuLieu=nomDuLieu;
		//super(mois);
	}

	public String getNomDuLieu() {
		return nomDuLieu;
	}

	public int getAnnee() {
		return annee;
	}
	/*
	public ArrayList<Jours> getListeDeDonne() {
		// TODO Auto-generated method stub
	}*/
	@Override
	public void afficher() {
		// TODO Auto-generated method stub

		System.out.println("Jours " + getNumero());
		System.out.println("Jours listedeDonne2" + listedeDonne2.size());
		System.out.println("Maree minimun ");
		getMarreBasseExceprion().afficher();
		System.out.println("Maree maximun ");
		getMarreHauteExceprion().afficher();
		for (Jours Arbre : listedeDonne2) {
			Arbre.afficher();
		}
	}

	public int getListeDeDonne(Jours entree) {
		// TODO Auto-generated method stub
		int compteur=0;
		for (Jours Arbre : listedeDonne2) {
			if (entree.getNumero()==Arbre.getNumero()) {
				break;
			} else {
				 compteur=compteur+1;
			}
		}
		return compteur;
	}
	
	public ArrayList<Jours> getListeDeDonne() {
		// TODO Auto-generated method stub
		return listedeDonne2;
	}
	
	public ArrayList<CommunAuxDonne> getListeDeDonne2() {
		// TODO Auto-generated method stub
		ArrayList<CommunAuxDonne> test= new ArrayList<>(listedeDonne2.size());
		for (Jours releveDateHeureEau : listedeDonne2) {
			test.add(releveDateHeureEau);
		}
		return  test;
	}

	public Jours getListeDeDonne(int position) {
		// TODO Auto-generated method stub
		return listedeDonne2.get(position);
	}
	public void setListeDeDonne(ArrayList<Jours> listedeDonne) {
		// TODO Auto-generated method stub
		this.listedeDonne2=listedeDonne;
	}

	
	public void setListeDeDonne(Jours listeDeMois) {
		// TODO Auto-generated method stub
		this.listedeDonne2.add(listeDeMois);
	}

	
	public void setListeDeDonne(int position, Jours Donnee) {
		// TODO Auto-generated method stub
		this.listedeDonne2.set(position, Donnee);
	}

	@Override
	public void setListeDeDonne2(ArrayList<CommunAuxDonne> listedeDonne) {
		// TODO Auto-generated method stub
		for (CommunAuxDonne communAuxDonne : listedeDonne) {
			Jours listedeDonne2 = (Jours) communAuxDonne;
			setListeDeDonne(listedeDonne2);
		}
	}
	

}
