package hbn;
// Generated 9 oct. 2013 16:25:45 by Hibernate Tools 3.2.1.GA

/**
 * Users generated by hbm2java
 */
public class Users implements java.io.Serializable {

    //Variables de session
    public static String NOM_USER = "";
    public static int PROFILE_USER = 0;
    //Autres param
    private Integer idUser;
    private String nomUser;
    private String prenomUser;
    private String mdpUser;
    private int roleUser;

    public Users() {
    }

    public Users(int roleUser) {
        this.roleUser = roleUser;
    }

    public Users(String nomUser, String prenomUser, String mdpUser, int roleUser) {
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.mdpUser = mdpUser;
        this.roleUser = roleUser;
    }

    public Integer getIdUser() {
        return this.idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNomUser() {
        return this.nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return this.prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public String getMdpUser() {
        return this.mdpUser;
    }

    public void setMdpUser(String mdpUser) {
        this.mdpUser = mdpUser;
    }

    public int getRoleUser() {
        return this.roleUser;
    }

    public void setRoleUser(int roleUser) {
        this.roleUser = roleUser;
    }
}
