package donne;

public abstract class CommunAuxDonne implements test {

	public CommunAuxDonne(int numero) {
		// TODO Auto-generated constructor stub
		this.numero=numero;
		//listedeDonne = new ArrayList<Arbre>(max);
	}
	public static final int NULL=-1;
	private final int numero;
	private double hauteurEauMoyenne;
	//protected ArrayList<Arbre> listedeDonne;
	private ReleveDateHeureEau marreHauteExceprion=null, marreBasseExceprion=null;
	private boolean testHautExt=true,testHautBas=true,testMoyenn=true;
/*
	@Override
	protected ArrayList<Arbre> getListeDeDonne() {
		// TODO Auto-generated method stub
		return listedeDonne;
	}

	@Override
	protected Arbre getListeDeDonne(int position) {
		// TODO Auto-generated method stub
		return listedeDonne.get(position);
	}

	@Override
	public abstract int getListeDeDonne(Arbre entree) ;
*/
	@Override
	public int getNumero() {
		// TODO Auto-generated method stub
		return numero;
	}

	@Override
	public ReleveDateHeureEau getMarreHauteExceprion() {
		// TODO Auto-generated method stub
		return marreHauteExceprion;
	}

	@Override
	public ReleveDateHeureEau getMarreBasseExceprion() {
		// TODO Auto-generated method stub
		return marreBasseExceprion;
	}

	@Override
	public double getHauteurEauMoyenne() {
		return hauteurEauMoyenne;
	}

	@Override
	public void setHauteurEauMoyenne(double hauteurEauMoyenne) {
		if (testMoyenn==true) {
			this.hauteurEauMoyenne = hauteurEauMoyenne;
			testMoyenn=false;
		}
	}
/*
	@Override
	void setListeDeDonne(ArrayList<Arbre> listedeDonne) {
		// TODO Auto-generated method stub
		this.listedeDonne=listedeDonne;
	}

	@Override
	public void setListeDeDonne(Arbre listeDeMois) {
		// TODO Auto-generated method stub
		this.listedeDonne.add(listeDeMois);
	}

	@Override
	protected void setListeDeDonne(int position, Arbre Donnee) {
		// TODO Auto-generated method stub
		this.listedeDonne.set(position, Donnee);
	}*/

	@Override
	public void addMarreeHaute(ReleveDateHeureEau marreHauteExceprion) {
		// TODO Auto-generated method stub
		if (testHautExt==true) {
			this.marreHauteExceprion=marreHauteExceprion;
			testHautExt=false;
		}
	}

	@Override
	public void addMarreeBasse(ReleveDateHeureEau marreBasseExceprion) {
		// TODO Auto-generated method stub
		if (testHautBas==true) {
			this.marreBasseExceprion=marreBasseExceprion;
			testHautBas=false;
		}
		
	}

	
}
