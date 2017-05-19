package donne;

import java.util.ArrayList;

/**
 * Sert à afficher un système de données lié aux marée
 * @author jeanpaul
 * */
public final class ReleveDateHeureEau extends CommunAuxDonne{
	private final double HauteurEau;
	private final String nomDuLieu;
	private final Calendrier dateDuReleve;
	
	public ReleveDateHeureEau(double HauteurEau, String nomDuLieu,Calendrier dateDuReleve) {
		// TODO Auto-generated constructor stub
		super(-1);
		this.HauteurEau=HauteurEau;
		this.nomDuLieu=nomDuLieu;
		this.dateDuReleve = dateDuReleve;
	}
	/**
	 * Affiche à l'écrant tous ce qui est contenu dans l'object
	 * */
	public void afficher() {
		System.out.println("nomDuLieu "+nomDuLieu+" HauteurEau "+HauteurEau);
		dateDuReleve.afficher();
	}

	
	
	/*Getter*/
	public double getHauteurEau() {
		return HauteurEau;
	}
	public String getNomDuLieu() {
		return nomDuLieu;
	}
	public Calendrier getDateDuReleve() {
		return dateDuReleve;
	}
	
	
	@Override
	public ArrayList<CommunAuxDonne> getListeDeDonne2() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setListeDeDonne2(ArrayList<CommunAuxDonne> listedeDonne) {
		// TODO Auto-generated method stub
		
	}
	
	
}
