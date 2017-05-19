package donne;

import java.util.ArrayList;

public class Lieu extends CommunAuxDonne {
	private final String nom;
	private ArrayList<Annee> listedeDonne;
	public Lieu(String nom) {
		// TODO Auto-generated constructor stub
		super(0);
		this.nom=nom;
		listedeDonne = new ArrayList<>();
	}
	public ArrayList<Annee> getListeDeDonne() {
		// TODO Auto-generated method stub
		return listedeDonne;
	}

	public Annee getListeDeDonne(int position) {
		// TODO Auto-generated method stub
		return listedeDonne.get(position);
	}
	public void setListeDeDonne(ArrayList<Annee> listedeDonne) {
		// TODO Auto-generated method stub
		this.listedeDonne=listedeDonne;
	}

	
	public void setListeDeDonne(Annee listeDeMois) {
		// TODO Auto-generated method stub
		this.listedeDonne.add(listeDeMois);
	}

	
	public void setListeDeDonne(int position, Annee Donnee) {
		// TODO Auto-generated method stub
		this.listedeDonne.set(position, Donnee);
	}
	
	public String getNom() {
		return nom;
	}
	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		System.out.println("Jours " + getNumero());
		System.out.println("Jours listedeDonne2" + listedeDonne.size());
		/*System.out.println("Maree minimun ");
		getMarreBasseExceprion().afficher();
		System.out.println("Maree maximun ");
		getMarreHauteExceprion().afficher();*/
		for (Annee Arbre : listedeDonne) {
			Arbre.afficher();
		}
	}
	
	public int getListeDeDonne(Annee entree) {
		// TODO Auto-generated method stub
		int compteur=0;
		for (Annee Arbre : listedeDonne) {
			if (entree.getNumero()==Arbre.getNumero()) {
				break;
			} else {
				 compteur=compteur+1;
			}
		}
		return compteur;
	}
	@Override
	public ArrayList<CommunAuxDonne> getListeDeDonne2() {
		// TODO Auto-generated method stub
		ArrayList<CommunAuxDonne> test= new ArrayList<>(listedeDonne.size());
		for (Annee releveDateHeureEau : listedeDonne) {
			test.add(releveDateHeureEau);
		}
		return  test;
	}
	@Override
	public void setListeDeDonne2(ArrayList<CommunAuxDonne> listedeDonne) {
		// TODO Auto-generated method stub
		for (CommunAuxDonne communAuxDonne : listedeDonne) {
			Annee listedeDonne2 = (Annee) communAuxDonne;
			setListeDeDonne(listedeDonne2);
		}
	}
	
}
