package principal;

public class Personne
{
	private int matricule ;
	private String nom ;
	private String prenom ;
	private int codeService ;
	private String nomService ;
	private boolean modifie ;
	    // false suite à la construction, mis à true ensuite si l'objet n'est
	    // pas à jour par rapport à la base de données
	public int getMatricule() { return matricule; }
	public String getNom() { return nom; }
	public String getPrenom() { return prenom; }
	public int getCodeService() { return codeService; }
	public String getLibelleService() { return nomService ; }
	public boolean getModifie() { return modifie ; }
	public void setNom(String nom)
	{ this.nom = nom ;
	  setModifie(true);
	}
	public void setPrenom(String prenom)
	{ this.prenom = prenom ;
	  setModifie(true);
	}
	public void setService(int code, String lib)
	{ codeService = code ;
	  nomService = lib ;
	  setModifie(true);
	}
	public void setModifie(boolean m) { modifie = m ; }
	public Personne
	   (int matricule, String nom, String prenom, int code, String service)
	{
	this.matricule = matricule ;
	this.nom = nom ;
	this.prenom = prenom ;
	this.codeService = code ;
	this.nomService = service ;
	this.setModifie(false) ;
	}
	public String toString()
	{
	return Integer.toString(getMatricule())
	 + ", " + getNom()
	 + ", " + getPrenom()
	 + ", " + getCodeService()
	 + ", " + getLibelleService()
	 + ", " + getModifie() ;
	}
}