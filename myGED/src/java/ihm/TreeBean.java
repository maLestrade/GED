/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import controller.DocController;
import hbn.Documents;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author mama
 */
@Named(value = "treeBean")
@SessionScoped
public class TreeBean implements Serializable {

    private DocController docController;
    private TreeNode root;

    public TreeBean() {

        System.out.println("dans le treeeeeeeeeeeeeeeeee");
        docController = new DocController();
        ArrayList<Documents> listDoc = docController.getDocList();
        
        if (listDoc != null) {
            root = new DefaultTreeNode("RACINE", null);
            
            TreeNode node0 = new DefaultTreeNode("ETATS DES COMPTES", root);
            TreeNode node1 = new DefaultTreeNode("PRETS", root);
            TreeNode node11 = new DefaultTreeNode("MAISON", node1);
            TreeNode node12 = new DefaultTreeNode("VOITURE", node1);

            
             for(Iterator it=listDoc.iterator(); it.hasNext();) {
                Documents doc = (Documents) it.next();
                if (doc.getNomDocument().contains("etat")) {
                    new DefaultTreeNode(doc.getNomDocument(), node0);
                } else if (doc.getNomDocument().contains("pret")) {
                    if(doc.getNomDocument().contains("maison")) {
                        new DefaultTreeNode(doc.getNomDocument(), node11);
                    }
                    else if(doc.getNomDocument().contains("voiture")) {
                        new DefaultTreeNode(doc.getNomDocument(), node12);
                    }
                }
            }
        }

    }

    public TreeNode getRoot() {
        return root;
    }
}
