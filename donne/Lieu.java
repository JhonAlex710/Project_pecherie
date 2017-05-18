package donne;

public class Lieu extends CommunAuxDonne implements CommunAuDonne{
	private final String nom;
	public Lieu(String nom) {
		// TODO Auto-generated constructor stub
		super(0);
		this.nom=nom;
	}
	public String getNom() {
		return nom;
	}
	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		System.out.println("Lieux");
	}
	
}
