package donne;

import java.util.ArrayList;

public final class Annee extends CommunAuxDonne {

	private ArrayList<Mois>listedeDonne2;
	private final String nomDuLieu;
	public Annee(int numeroAnne) {
		// TODO Auto-generated constructor stub
		super(numeroAnne);
		listedeDonne2= new ArrayList<>();
		nomDuLieu="";
	}
	public Annee(int numeroAnne,String nomDuLieu) {
		// TODO Auto-generated constructor stub
		super(numeroAnne);
		listedeDonne2= new ArrayList<>();
		this.nomDuLieu=nomDuLieu;
	}


	public ArrayList<CommunAuxDonne> getListeDeDonne2() {
		// TODO Auto-generated method stub
		ArrayList<CommunAuxDonne> test= new ArrayList<>(listedeDonne2.size());
		for (Mois releveDateHeureEau : listedeDonne2) {
			test.add(releveDateHeureEau);
		}
		return  test;
	}
	
	public ArrayList<Mois> getListeDeDonne() {
		// TODO Auto-generated method stub
		return listedeDonne2;
	}

	public Mois getListeDeDonne(int position) {
		// TODO Auto-generated method stub
		return listedeDonne2.get(position);
	}
	public void setListeDeDonne(ArrayList<Mois> listedeDonne) {
		// TODO Auto-generated method stub
		this.listedeDonne2=listedeDonne;
	}

	
	public void setListeDeDonne(Mois listeDeMois) {
		// TODO Auto-generated method stub
		this.listedeDonne2.add(listeDeMois);
	}

	
	public void setListeDeDonne(int position, Mois Donnee) {
		// TODO Auto-generated method stub
		this.listedeDonne2.set(position, Donnee);
	}

	public void afficher() {
		// TODO Auto-generated method stub
		System.out.println("Jours " + getNumero());
		System.out.println("Jours listedeDonne2" + listedeDonne2.size());
		System.out.println("Maree minimun ");
		/*getMarreBasseExceprion().afficher();
		System.out.println("Maree maximun ");
		getMarreHauteExceprion().afficher();*/
		for (Mois Arbre : listedeDonne2) {
			Arbre.afficher();
		}
	}

	public int getListeDeDonne(Mois entree) {
		// TODO Auto-generated method stub
		int compteur=0;
		for (Mois Arbre : listedeDonne2) {
			 if (entree.getNumero()==Arbre.getNumero()) {
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
			Mois listedeDonne2 = (Mois) communAuxDonne;
			setListeDeDonne(listedeDonne2);
		}
	}
	public String getNomDuLieu() {
		return nomDuLieu;
	}
	

}
