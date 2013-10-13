/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.HibernateUtil;
import hbn.Documents;
import hbn.MetadataDoc;
import hbn.Users;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mama
 */
public class DocDAO {

    public DocDAO() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    private Session session = null;

    /**
     * renvoi des users de la base
     *
     * @return
     */
    public ArrayList getMyDocs() {
        ArrayList<Documents> listdocs = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select doc from Documents doc INNER JOIN doc.metadataDocs meta where meta.metatype.intituleMetadata='createur'");
            listdocs = (ArrayList<Documents>) q.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listdocs;
    }
	
	
	public ArrayList getDocsSpeAuteur() {
        String nomUser = Users.NOM_USER;
        ArrayList<Documents> listdocs = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select doc from Documents doc INNER JOIN doc.metadataDocs meta where meta.metatype.intituleMetadata='createur' AND meta.valeurDoc='"+nomUser+"'");
            listdocs = (ArrayList<Documents>) q.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listdocs;
    }
        
        
        
        public ArrayList getDocsSecretaire() {
        ArrayList<Documents> listdocs = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select doc from Documents doc INNER JOIN doc.metadataDocs meta where meta.metatype.intituleMetadata='type' AND meta.valeurDoc='SECRETARIAT'");
            listdocs = (ArrayList<Documents>) q.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listdocs;
    }
	
	
	
	
    public ArrayList getThisDocument(String nomDoc) {
        ArrayList<String> listdocs = null;
        MetadataDoc metadata = null;
//        ajouter les controles d'accès aux ressources documentaires
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select mD.valeurDoc from MetadataDoc as mD inner join mD.metatype as meta where mD.documents=(select doc.idDocuments from Documents as doc where doc.nomDocument='"+nomDoc+"')");
            listdocs = (ArrayList<String>) q.list();
            System.out.println(listdocs.get(0));
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listdocs;
    }
}
