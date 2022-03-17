package principal;

import java.sql.Connection;
import java.sql.DriverManager;

public class Exam{
	public static void main(String[] args)
	{
	    Annuaire annuaire = new Annuaire () ;
	    try
	    {
	     Class.forName("com.mysql.jdbc.Driver") ;
	    // Connection connexion = DriverManager.getConnection("jdbc:mysql://anteros.ifsic.univ­rennes1.fr/base_exam","user_exam", "truc");
	     Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost/base_exam","root", "");
		annuaire.chargerBase(connexion) ;
		System.out.println(annuaire.getPersonne(4121)) ;
		// imprime la chaîne :
		// 4121, SINACO, Keveen, 15, Couverture, false
		     Personne nouveau
		     = new Personne
		(3333, "DUPONT", "Jeanne", 12, "Peinture") ;
		     nouveau.setModifie(true) ;
		     annuaire.ajouter(nouveau) ;
		     Personne existant = annuaire.getPersonne(4116) ;
		     existant.setService(14, "Electricité") ;
		     existant.setModifie(true) ;
		System.out.println(annuaire.getPersonne(4116)) ;
		// imprime la chaîne :
		// 4116, FERERE, Jean­Anthony, 14, Electricité, true
		     existant = annuaire.getPersonne(4123) ;
		     existant.setService(99, "Solaire") ;
		     existant.setModifie(true) ;
		     annuaire.sauvegarder(connexion) ;
		     System.out.println() ;
		     annuaire.afficher() ;
		     connexion.close() ;
	    }
	    catch (Exception e) {System.out.println(e.getMessage()) ; }
	}
}
