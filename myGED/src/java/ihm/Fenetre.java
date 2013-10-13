/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

/**
 *
 * @author Cogotch
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Fenetre extends JFrame {

    private JButton bouton = new JButton("Go");
    private JButton bouton2 = new JButton("Stop");
    private JPanel container = new JPanel();
    private JLabel label = new JLabel("Choix de la forme");
    private int compteur = 0;
    private boolean animated = true;
    private boolean backX, backY;
    private int x, y;
    private Thread t;
    private JComboBox combo = new JComboBox();
    private JCheckBox morph = new JCheckBox("Morphing");

    public Fenetre() {
        //Rien de changé ici
    }

    private void go() {
        //Cette méthode n'a pas changé non plus
    }

    public class BoutonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            JOptionPane jop = new JOptionPane();
            int option = jop.showConfirmDialog(null,
                    "Voulez-vous lancer l'animation ?",
                    "Lancement de l'animation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (option == JOptionPane.OK_OPTION) {
                animated = true;
                t = new Thread(new PlayAnimation());
                t.start();
                bouton.setEnabled(false);
                bouton2.setEnabled(true);
            }
        }
    }

    class Bouton2Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane jop = new JOptionPane();
            int option = jop.showConfirmDialog(null,
                    "Voulez-vous arrêter l'animation ?",
                    "Arrêt de l'animation",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (option != JOptionPane.NO_OPTION
                    && option != JOptionPane.CANCEL_OPTION
                    && option != JOptionPane.CLOSED_OPTION) {
                animated = false;
                bouton.setEnabled(true);
                bouton2.setEnabled(false);
            }
        }
    }

    class PlayAnimation implements Runnable {

        @Override
        public void run() {
            go();
        }
    }

    class FormeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        //Rien de changé
    }

    class MorphListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        //Rien de changé
    }
}