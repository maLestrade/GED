/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.HibernateUtil;
import hbn.Documents;
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
            Query q = session.createQuery("from Documents doc INNER JOIN doc.metadataDocs meta where meta.metatype.intituleMetadata='createur'");
            listdocs = (ArrayList<Documents>) q.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listdocs;
    }
}
