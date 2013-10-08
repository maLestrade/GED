/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.io.Serializable;
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
public class TreeBean implements Serializable{

          
        private TreeNode root;  
      
        public TreeBean() {  
            root = new DefaultTreeNode("RACINE", null);  
            TreeNode node0 = new DefaultTreeNode("Node 0", root);  
            TreeNode node1 = new DefaultTreeNode("Node 1", root);  
            TreeNode node2 = new DefaultTreeNode("Node 2", root);  
              
            TreeNode node00 = new DefaultTreeNode("Node 0.0", node0);  
            TreeNode node01 = new DefaultTreeNode("Node 0.1", node0);  
              
            TreeNode node10 = new DefaultTreeNode("Node 1.0", node1);  
            TreeNode node11 = new DefaultTreeNode("Node 1.1", node1);  
              
            TreeNode node000 = new DefaultTreeNode("Node 0.0.0", node00);  
            TreeNode node001 = new DefaultTreeNode("Node 0.0.1", node00);  
            TreeNode node010 = new DefaultTreeNode("Node 0.1.0", node01);  
              
            TreeNode node100 = new DefaultTreeNode("Node 1.0.0", node10);  
        }  
      
        public TreeNode getRoot() {  
            return root;  
        }  
    }  

