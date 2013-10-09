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

/**
 *
 * @author mama
 */
@Named(value = "docController")
@Dependent
public class DocController {

    private ArrayList<Documents> docList = new ArrayList<>();
    private DocDAO docDao;

    /**
     * Creates a new instance of DocController
     */
    public DocController() {
        docDao = new DocDAO();
        recupListDoc();
    }

    private void recupListDoc() {
        if (docList != null) {
            setDocList(docDao.getMyDocs());
        }
    }

    public ArrayList<Documents> getDocList() {
        return docList;
    }

    public void setDocList(ArrayList<Documents> docList) {
        this.docList = docList;
    }

    public DocDAO getDocDao() {
        return docDao;
    }

    public void setDocDao(DocDAO docDao) {
        this.docDao = docDao;
    }

    public void editDocument(Documents doc) {
        EditorBean editor;
        
    }
    
   
}
