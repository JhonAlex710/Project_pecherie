package donne;

import java.util.ArrayList;

public class Jours extends CommunAuxDonne {
	private ArrayList<ReleveDateHeureEau> listedeDonne2;
	public Jours(int numero) {
		super(numero);
		// TODO Auto-generated constructor stub
		listedeDonne2 = new ArrayList<ReleveDateHeureEau>();
	}
	
	public ArrayList<CommunAuxDonne> getListeDeDonne2() {
		// TODO Auto-generated method stub
		ArrayList<CommunAuxDonne> test= new ArrayList<>(listedeDonne2.size());
		for (ReleveDateHeureEau releveDateHeureEau : listedeDonne2) {
			test.add(releveDateHeureEau);
		}
		return  test;
	}
	public ArrayList<ReleveDateHeureEau> getListeDeDonne() {
		// TODO Auto-generated method stub
		return listedeDonne2;
	}

	public ReleveDateHeureEau getListeDeDonne(int position) {
		// TODO Auto-generated method stub
		return listedeDonne2.get(position);
	}
	public void setListeDeDonne(ArrayList<ReleveDateHeureEau> listedeDonne) {
		// TODO Auto-generated method stub
		this.listedeDonne2=listedeDonne;
	}

	
	public void setListeDeDonne(ReleveDateHeureEau listeDeMois) {
		// TODO Auto-generated method stub
		this.listedeDonne2.add(listeDeMois);
	}

	
	public void setListeDeDonne(int position, ReleveDateHeureEau Donnee) {
		// TODO Auto-generated method stub
		this.listedeDonne2.set(position, Donnee);
	}

	
	public void afficher() {
		// TODO Auto-generated method stub
		System.out.println("Jours " + getNumero());
		System.out.println("Jours listedeDonne2" + listedeDonne2.size());
		System.out.println("Maree minimun ");
		getMarreBasseExceprion().afficher();
		System.out.println("Maree maximun ");
		getMarreHauteExceprion().afficher();
		for (ReleveDateHeureEau Arbre : listedeDonne2) {
			ReleveDateHeureEau jour = (ReleveDateHeureEau)Arbre;
			jour.afficher();
		}
	}

	public int getListeDeDonne(ReleveDateHeureEau entree) {
		// TODO Auto-generated method stub
		ReleveDateHeureEau mois = (ReleveDateHeureEau)entree;
		int compteur=0;
		for (ReleveDateHeureEau Arbre : listedeDonne2) {
			ReleveDateHeureEau test = (ReleveDateHeureEau)Arbre;
			if (mois.getHauteurEau()==test.getHauteurEau() && test.getNomDuLieu() == mois.getNomDuLieu() && 
					test.getDateDuReleve().getAnnee() == mois.getDateDuReleve().getAnnee() &&
					test.getDateDuReleve().getMois() == mois.getDateDuReleve().getMois() &&
					test.getDateDuReleve().getJour() == mois.getDateDuReleve().getJour() &&
					test.getDateDuReleve().getHeure() == mois.getDateDuReleve().getHeure() ) {
				break;
			} else {
				 compteur=compteur+1;
			}
		}
		return compteur;
	}

	@Override
	public void setListeDeDonne2(ArrayList<CommunAuxDonne> listedeDonne) {
		// TODO Auto-generated method stub
		for (CommunAuxDonne communAuxDonne : listedeDonne) {
			ReleveDateHeureEau listedeDonne2 = (ReleveDateHeureEau) communAuxDonne;
			setListeDeDonne(listedeDonne2);
		}
	}
}
