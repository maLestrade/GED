/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import hbn.Users;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import config.HibernateUtil;

/**
 *
 * @author mama
 */
@Named(value = "usersDAO")
@SessionScoped
public class UsersDAO implements Serializable {

    public UsersDAO() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    private Session session = null;

    /**
     * renvoi des users de la base
     *
     * @return
     */
    public ArrayList getUsers() {
       ArrayList<Users> listusers = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Users");
            listusers = (ArrayList<Users>) q.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listusers;
    }
}

   
        
    

//    public Integer ajouteUtilisateur(Utilisateur utilisateur) {
//        Integer idUser = null;
//        try {
//            session = NewHibernateUtil.getSessionFactory().getCurrentSession();
//            Transaction tx = session.beginTransaction();
//            idUser = (Integer) session.save(utilisateur);
//            tx.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return idUser;
//    }
