package donne;

public class Mois extends CommunAuxDonne implements CommunAuDonne{

	private final String nomDuLieu;
	private final int annee;

	public Mois(int numero) {
		// TODO Auto-generated constructor stub
		super(numero);
		nomDuLieu="";
		annee=0;
	}
	
	public Mois(int mois, int annee, String nomDuLieu) {
		// TODO Auto-generated constructor stub
		super(mois);
		this.annee=annee;
		this.nomDuLieu=nomDuLieu;
	}

	public String getNomDuLieu() {
		return nomDuLieu;
	}

	public int getAnnee() {
		return annee;
	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		System.out.println("Mois");
		
	}
	

}
