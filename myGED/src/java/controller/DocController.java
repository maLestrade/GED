/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import hbn.Documents;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import dao.DocDAO;
import hbn.Users;
import ihm.TreeBean;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author mama
 */
@Named(value = "docController")
@Dependent
public class DocController implements Serializable {

    private ArrayList<Documents> docList = new ArrayList<>();
    private DocDAO docDao;
    private static Boolean docOpened = false;
    private ArrayList<String> metadata;
    private static String cheminStatic;
    private static String inputText;

    /**
     * Creates a new instance of DocController
     */
    public DocController() {
        docDao = new DocDAO();
        if(Users.PROFILE_USER==1){
            recupListDoc();
        }else if(Users.PROFILE_USER==2){
            recupListSpeDoc();
        }else if(Users.PROFILE_USER==3){
            recupListSecretaire();
        }
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public DocController(String nomDoc, String chem) {
        docDao = new DocDAO();
        metadata = docDao.getThisDocument(nomDoc);
        this.cheminStatic = chem;
    }

    private void recupListDoc() {
        if (docList != null) {
            setDocList(docDao.getMyDocs());
        }
    }
    
    private void recupListSpeDoc() {
        if (docList != null) {
            setDocList(docDao.getDocsSpeAuteur());
        }
    }
    
    private void recupListSecretaire(){
        if(docList!=null){
            setDocList(docDao.getDocsSecretaire());
        }
    }

    public static String getCheminStatic() {
        return cheminStatic;
    }

    public static void setCheminStatic(String cheminStatic) {
        DocController.cheminStatic = cheminStatic;
    }

    public ArrayList<Documents> getDocList() {
        return docList;
    }

    public void setDocList(ArrayList<Documents> docList) {
        this.docList = docList;
    }

    public static Boolean getDocOpened() {
        return docOpened;
    }

    public static void setDocOpened(Boolean docOpened) {
        DocController.docOpened = docOpened;
    }

    public DocDAO getDocDao() {
        return docDao;
    }

    public void setDocDao(DocDAO docDao) {
        this.docDao = docDao;
    }

    public ArrayList<String> getMetadata() {
        return metadata;
    }

    public void setMetadata(ArrayList<String> metadata) {
        this.metadata = metadata;
    }

    public String openDocument() {
        String nomDoc = TreeBean.getNomDoc();
        if (nomDoc != "-1") {
            setDocOpened(true);
            System.out.println("openDocument OK2");
            FileInputStream doc;
            DocController dc = new DocController(nomDoc, null);
            ArrayList<String> metadata = dc.getMetadata();
            String texte = null;
            String chemin = null;
            for (Iterator it = metadata.iterator(); it.hasNext();) {
                String aux = (String) it.next();
                if (aux.contains("C:\\Users\\")) {
                    chemin = aux;
                    DocController.setCheminStatic(chemin);
                }
            }
            try {
                doc = new FileInputStream(chemin);
                char caractere;
                int cpt = 0;
                while (doc.available() > 0) {
                    caractere = (char) doc.read();
                    if (cpt == 0) {
                        texte = verifChar(caractere);
                        cpt = cpt + 1;
                    } else {
                        texte = texte + verifChar(caractere);
                        cpt = cpt + 1;
                    }
                }
                System.out.println("openDocument OK3");
                doc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Input texte avant : " + inputText);
            setInputText(texte);
            System.out.println("INPUTTEXT oD : " + inputText);
            return texte;
        } else {
            return null;
        }

    }

    public String verifChar(char c) {
        String s = null;
        if (c == 10) {
            s = "<br>";
        } else {
            s = Character.toString(c);
        }
        return s;
    }

    public void confirmClose(ActionEvent actionEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "System Error", "Please try again later.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void sauvegardeDoc() {
        System.out.println("DRFTYUIKLKGVB : " + this.cheminStatic);
        String cheminAux = getCheminStatic();
        String textToSave = this.inputText;
        System.out.println("TextAera : " + this.inputText);
        if (cheminAux != null) {
            try {
                FileWriter fw = new FileWriter(cheminAux, true);
                BufferedWriter output = new BufferedWriter(fw);
                output.write(textToSave);
                output.flush();
                output.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
