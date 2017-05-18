package donne;

import java.util.ArrayList;
/**
 * Un ensemble de 24 relevés
 * @author jeanpaul
 * */
public final class Jours {
	private ArrayList<ReleveDateHeureEau> listedeDonnes=new ArrayList<>(31);
	private final int numeroJours;
	private ReleveDateHeureEau marreHauteExceprion;
	private boolean marreHautExp=true,marreBasseExp =true;
	private ReleveDateHeureEau marreBasseExceprion;
	
	/**
	 * On entre le jour dans le mois
	 * */
	public Jours(int numeroJours) {
		// TODO Auto-generated constructor stub
		this.numeroJours=numeroJours;
	}
	/**Renvoie la collection complete*/
	public ArrayList<ReleveDateHeureEau> getListedeDonnes() {
		return listedeDonnes;
	}
	/** Renvoie un element à la position X*/
	public ReleveDateHeureEau getListedeDonnes(int position) throws IndexOutOfBoundsException{
		return listedeDonnes.get(position);
	}
	/** Renvoie la position Xde l'élément Y*/
	public int getListedeDonnes(ReleveDateHeureEau listedeDonnes)  throws IndexOutOfBoundsException {
		int compteur = 0;
		for (ReleveDateHeureEau releveDateHeureEau : this.listedeDonnes) {
			if (listedeDonnes.getHauteurEau()==releveDateHeureEau.getHauteurEau()) {
				break;
			}else {
				compteur=compteur+1;
			}
		}
		return compteur;
	}
	/**
	 * Renvoie le numero du jour
	 * */
	public int getNumeroJours() {
		return numeroJours;
	}
	/**
	 * Renvoie le MarreHauteExceprion
	 * @return {@link ReleveDateHeureEau} relevé de la maree exceptionnel haute
	 * */
	public ReleveDateHeureEau getMarreHauteExceprion() {
		return marreHauteExceprion;
	}
	/**
	 *  Renvoie le marreBasseExceprion
	 * @return {@link ReleveDateHeureEau} relevé de la maree exceptionnel Basse
	 * */
	public ReleveDateHeureEau getMarreBasseExceprion() {
		return marreBasseExceprion;
	}
	
	/** change l'ensemble de la collection*/
	public void setListedeDonnes(ArrayList<ReleveDateHeureEau> listedeDonnes) {
		this.listedeDonnes = listedeDonnes;
	}
	/**Ajoute un élément*/
	public void setListedeDonnes(ReleveDateHeureEau listedeDonnes) {
		this.listedeDonnes.add(listedeDonnes);
	}
	/**Remplace un élément à la postion*/
	public void setListedeDonnes(ReleveDateHeureEau listedeDonnes, int position) throws IndexOutOfBoundsException{
		this.listedeDonnes.set(position, listedeDonnes);
	}
	/**
	 * Ajoute la marrée haute excpetionnel
	 * 1 seul insertion possible
	 * @param marreHauteExceprion releve de la marée haute
	 * */
	public void addMarreeHaute(ReleveDateHeureEau marreHauteExceprion) {
		// TODO Auto-generated method stub
		if (marreHautExp==true) {
			this.marreHauteExceprion=marreHauteExceprion;
			marreHautExp=false;
		}
		
	}
	/**
	 * Ajoute la marrée basse exceptionnel 
	 * 1 seul insertion possible
	 * @param marreBasseExceprion relevé de la marré basse
	 * */
	public void addMarreeBasse(ReleveDateHeureEau marreBasseExceprion) {
		// TODO Auto-generated method stub
		if (marreBasseExp==true) {
			this.marreBasseExceprion=marreBasseExceprion;
			marreBasseExp=false;
		}
	}
	
}
