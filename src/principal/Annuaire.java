package principal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Annuaire {

	private Map<Integer, Personne> person_list; //list of inserted person
	private Statement requete = null;
	
	public Annuaire() {
		// default constructor
		person_list = new HashMap<>();
	}
	
	//read all data from database
	public void chargerBase(Connection  connexion) {
		try {
			requete = connexion.createStatement();
			ResultSet result = requete.executeQuery("SELECT matricule, nom, prenom, service.code_service as code_service, libelle_service FROM  personne, service WHERE personne.code_service = service.code_service");
			while (result.next()) {
				Personne nouveau = new Personne(result.getInt("matricule"), result.getString("nom"), result.getString("prenom"), result.getInt("code_service"), result.getString("libelle_service"));
				person_list.put(nouveau.getMatricule(), nouveau);
			}
				
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	
	}
	
	//create new Personne
	public Personne ajouter(Personne nouveau) {
		return this.person_list.put(nouveau.getMatricule(), nouveau);
	}
	
	//retrieve existing personne by matricule field
	public Personne getPersonne(int matricule) {
		return this.person_list.get(matricule);
	}
	
	//display all person_list
	public void afficher() {
		Iterator<Personne> it =  person_list.values().iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
	
	//store all data
	public void sauvegarder(Connection connexion) {
		Iterator<Personne> it =  person_list.values().iterator();
		while(it.hasNext()) {
			Personne person = it.next();
			if(person.getModifie()) {
				//new person or updated person
				//check if person exist
				try{
					
					//check if record has a new service
					if(countRows(connexion, "service", "WHERE service.code_service="+person.getCodeService()) > 0) {
					//service already exist so update it
						 requete.executeUpdate("UPDATE service SET libelle_service='"+person.getLibelleService()+"' WHERE service.code_service="+person.getCodeService());	
					}else {
						//service doesn't already exist so create new one
						 requete.executeUpdate("INSERT INTO service(code_service,libelle_service) VALUES("+person.getCodeService()+",'"+person.getLibelleService()+"')");	
					}
					
					if(countRows(connexion, "personne", "WHERE personne.matricule="+person.getMatricule()) > 0) {
					//person already exist so update it
						 requete.executeUpdate("UPDATE personne SET nom='"+person.getNom()+"', prenom='"+person.getPrenom()+"', code_service="+person.getCodeService()+" WHERE personne.matricule="+person.getMatricule());	
					}else {
						//person doesn't already exist so create new one
						 requete.executeUpdate("INSERT INTO personne(matricule,nom,prenom,code_service) VALUES("+person.getMatricule()+",'"+person.getNom()+"','"+person.getPrenom()+"',"+person.getCodeService()+")");	
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	
	public static int countRows(Connection conn, String tableName, String where) throws SQLException {
	    // select the number of rows in the table
	    Statement stmt = null;
	    ResultSet rs = null;
	    int rowCount = -1;
	    try {
	      stmt = conn.createStatement();
	      rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName+" "+where);
	      // get the number of rows from the result set
	      rs.next();
	      rowCount = rs.getInt(1);
	    } finally {
	      rs.close();
	      stmt.close();
	    }
	    return rowCount;
	  }
	
}
