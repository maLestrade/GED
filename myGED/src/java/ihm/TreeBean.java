/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import controller.DocController;
import hbn.Documents;
import hbn.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.inject.Named;
import org.primefaces.event.NodeSelectEvent;
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
    private TreeNode selectedNode;
    private UIComponent panelEditorForm;
    private static String nomDoc = "-1";

    public TreeBean() {

        ArrayList<Documents> listDoc;
        docController = new DocController();
        listDoc = docController.getDocList();

        if (Users.PROFILE_USER == 2 || Users.PROFILE_USER == 1) {
            if (listDoc != null) {
                root = new DefaultTreeNode("RACINE", null);

                TreeNode node0 = new DefaultTreeNode("ETATS DES COMPTES", root);
                TreeNode node1 = new DefaultTreeNode("PRETS", root);
                TreeNode node11 = new DefaultTreeNode("MAISON", node1);
                TreeNode node12 = new DefaultTreeNode("VOITURE", node1);


                for (Iterator it = listDoc.iterator(); it.hasNext();) {
                    Documents doc = (Documents) it.next();
                    if (doc.getNomDocument().contains("etat")) {
                        new DefaultTreeNode(doc.getNomDocument(), node0);
                    } else if (doc.getNomDocument().contains("pret")) {
                        if (doc.getNomDocument().contains("maison")) {
                            new DefaultTreeNode(doc.getNomDocument(), node11);
                        } else if (doc.getNomDocument().contains("voiture")) {
                            new DefaultTreeNode(doc.getNomDocument(), node12);
                        }
                    }
                }
            }
        } else if (Users.PROFILE_USER == 3) {
            if (listDoc != null) {
                root = new DefaultTreeNode("RACINE", null);

                TreeNode node0 = new DefaultTreeNode("FACTURES", root);

                for (Iterator it = listDoc.iterator(); it.hasNext();) {
                    Documents doc = (Documents) it.next();
                    if (doc.getNomDocument().contains("facture")) {
                        new DefaultTreeNode(doc.getNomDocument(), node0);
                    }
                }

                TreeNode node1 = new DefaultTreeNode("FEUILLES SALAIRES", root);
                ArrayList listNomSalarie = recupNomSalarie(listDoc);

                for (int i = 0; i < listNomSalarie.size(); i++) {
                    TreeNode node_aux = new DefaultTreeNode(listNomSalarie.get(i), node1);
                    for (Iterator it = listDoc.iterator(); it.hasNext();) {
                        Documents doc = (Documents) it.next();
                        if (doc.getNomDocument().toUpperCase().contains((CharSequence) listNomSalarie.get(i))) {
                            new DefaultTreeNode(doc.getNomDocument(), node_aux);
                        }
                    }
                }
            }
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public DocController getDocController() {
        return docController;
    }

    public void setDocController(DocController docController) {
        this.docController = docController;
    }

    public UIComponent getPanelEditorForm() {
        return panelEditorForm;
    }

    public void setPanelEditorForm(UIComponent panelEditorForm) {
        this.panelEditorForm = panelEditorForm;
    }

    public static String getNomDoc() {
        return nomDoc;
    }

    public void setNomDoc(String nomDoc) {
        this.nomDoc = nomDoc;
    }

    public void onNodeSelect(NodeSelectEvent event) {
        // if (docController.getDocOpened()==false) {
        if (getSelectedNode().toString() != "ETATS DES COMPTES" && getSelectedNode().toString() != "PRETS" && getSelectedNode().toString() != "MAISON" && getSelectedNode().toString() != "VOITURE") {
            this.nomDoc = getSelectedNode().toString();
            docController.openDocument();
        }
//        }
//        else {
//            System.out.println("DOCUMENT OUVERT");
//        }
    }

    public Boolean showFormEditor(String s) {
//        System.out.println("PARAM : " + s);
//        if (s != "-1"){
//            System.out.println("COOOL");
//            return true;
//        } else {
//            System.out.println("PAS COOOL");
//            return false;
//        } r
        return true;
    }

    public UIComponent getPanelUserForm() {
        return panelEditorForm;
    }

    public void setPanelUserForm(UIComponent panelUserForm) {
        this.panelEditorForm = panelUserForm;
    }

    public static ArrayList recupNomSalarie(ArrayList<Documents> list) {
        ArrayList<String> listNom = new ArrayList<>();
        String nom_aux;
        for (Iterator it = list.iterator(); it.hasNext();) {
            Documents doc = (Documents) it.next();
            if (doc.getNomDocument().contains("salaire")) {
                nom_aux = appliqueRegEx(doc.getNomDocument(), "(.*_.*_)(.*)(\\d{1})", 2);
                if (!listNom.contains(nom_aux.toUpperCase())) {
                    listNom.add(nom_aux.toUpperCase());
                }
            }
        }
        return listNom;
    }

    public static String appliqueRegEx(String line, String pattern, int indice) {
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);
        if (m.find()) {
            return m.group(indice);
        } else {
            return null;
        }
    }
}
