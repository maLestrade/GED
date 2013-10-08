/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import hbn.Users;
import dao.UsersDAO;


/**
 *
 * @author Cogotch
 */
@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {

    //paramètres
    private Users user;
    private ArrayList<Users> listUsers = new ArrayList<>();
    private UsersDAO usersDao;

    
    /**
     * CONSTRUCTOR
     */
    public UserController() {
        user = new Users();
        usersDao = new UsersDAO();
        recupListUsers();
    }

    /**
     * recup la liste des users
     */
    private void recupListUsers() {
        if (listUsers!=null){
            setListUsers(usersDao.getUsers());
        }
    }

    
    //////////////////////////////GET & SET/////////////////////////////////
    
    
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public ArrayList<Users> getListUsers() {
        return listUsers;
    }

    public void setListUsers(ArrayList<Users> listUsers) {
        this.listUsers = listUsers;
    }

    public UsersDAO getUsersDao() {
        return usersDao;
    }

    public void setUsersDao(UsersDAO usersDao) {
        this.usersDao = usersDao;
    }

    
    //////////////////////////////////////////////////////////////////////////////
    
    
    
    /**
     * Permet la connexion à l'appli si le couple login/mdp est contenu dans la base
     * @param mdp
     * @param prenom
     * @param nom
     * @return 
     */
    public String connexion() {
        recupListUsers();
        if (listUsers!=null) {
            for (int i=0 ; listUsers.size()>i ; i++){
                System.out.println("nom : " + this.user.getNomUser());
                if ((listUsers.get(i).getNomUser().equals(this.user.getNomUser())) && (listUsers.get(i).getPrenomUser().equals(this.user.getPrenomUser())) && (listUsers.get(i).getMdpUser().equals(this.user.getMdpUser()))){
                    return "workspace";
                }
            }
        }
        return "index";
    }
    
 
}
