package controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cogotch
 */
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
 
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
 
@Named(value = "editorBean")
public class EditorBean extends JDialog implements ActionListener {
 
  /** DOCUMENT_ME */
  private JColorChooser colorChooser = null;
 
  /** DOCUMENT_ME */
  private JComboBox fontName = null;
 
  /** DOCUMENT_ME */
  private JCheckBox fontBold;
 
  /** DOCUMENT_ME */
  private JCheckBox fontItalic = null;
 
  /** DOCUMENT_ME */
  private JSpinner fontSize = null;
 
  /** DOCUMENT_ME */
  private JLabel previewLabel = null;
 
  /** DOCUMENT_ME */
  private SimpleAttributeSet attributes = null;
 
  /** DOCUMENT_ME */
  private Font newFont = null;
 
  /** DOCUMENT_ME */
  private Color newColor = null;
 
  /** DOCUMENT_ME */
  private JComponent compo = null;
 
  /**
   * Creates a new JFontChooser object.
   *
   * @param parent DOCUMENT ME!
   * @param compo DOCUMENT ME!
   */
 
  public EditorBean(Frame parent, JComponent compo) {
    super(parent, "Font Chooser", true);
    this.compo = compo;
 
    setSize(450, 450);
    attributes = new SimpleAttributeSet();
 
    // Make sure that any way the user cancels the window does the right thing
    addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
          closeAndCancel();
        }
      });
 
    // Start the long process of setting up our interface
    Container c = getContentPane();
 
    JPanel fontPanel = new JPanel();
    fontName = new JComboBox(GraphicsEnvironment.getLocalGraphicsEnvironment()
                                                .getAvailableFontFamilyNames());
    fontName.setSelectedItem(compo.getFont().getName());
    fontName.addActionListener(this);
    fontSize = new JSpinner(new SpinnerNumberModel(compo.getFont().getSize(),
          4, 50, 2));
    fontSize.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent arg0) {
          actionPerformed(null);
        }
      });
    fontBold = new JCheckBox("Bold");
    fontBold.setSelected(compo.getFont().isBold());
    fontBold.addActionListener(this);
    fontItalic = new JCheckBox("Italic");
    fontItalic.setSelected(compo.getFont().isItalic());
    fontItalic.addActionListener(this);
 
    fontPanel.add(fontName);
    fontPanel.add(new JLabel(" Size: "));
    fontPanel.add(fontSize);
    fontPanel.add(fontBold);
    fontPanel.add(fontItalic);
 
    c.add(fontPanel, BorderLayout.NORTH);
 
    // Set up the color chooser panel and attach a change listener so that color
    // updates get reflected in our preview label.
    colorChooser = new JColorChooser(compo.getForeground());
    colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
          updatePreviewColor();
        }
      });
    c.add(colorChooser, BorderLayout.CENTER);
 
    JPanel previewPanel = new JPanel(new BorderLayout());
    previewLabel = new JLabel("Le résultat ressemblera un peu à ça");
    previewLabel.setBackground(Color.WHITE);
    previewLabel.setForeground(colorChooser.getColor());
    previewPanel.add(previewLabel, BorderLayout.CENTER);
 
    // Add in the Ok and Cancel buttons for our dialog box
    JButton okButton = new JButton("Ok");
    okButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
          closeAndApply();
        }
      });
 
    JButton cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
          closeAndCancel();
        }
      });
 
    JPanel controlPanel = new JPanel();
    controlPanel.add(okButton);
    controlPanel.add(cancelButton);
    previewPanel.add(controlPanel, BorderLayout.SOUTH);
 
    // Give the preview label room to grow.
    previewPanel.setMinimumSize(new Dimension(100, 100));
    previewPanel.setPreferredSize(new Dimension(100, 100));
 
    c.add(previewPanel, BorderLayout.SOUTH);
    actionPerformed(null);
  }
 
 
  // Ok, something in the font changed, so figure that out and make a
  // new font for the preview label
  @Override
  public void actionPerformed(ActionEvent ae) {
    // Check the name of the font
    if (!StyleConstants.getFontFamily(attributes).equals(fontName.getSelectedItem())) {
      StyleConstants.setFontFamily(attributes,
        (String) fontName.getSelectedItem());
    }
 
    // Check the font size (no error checking yet)
    if (StyleConstants.getFontSize(attributes) != ((Integer) fontSize.getValue()).intValue()) {
      StyleConstants.setFontSize(attributes,
        ((Integer) fontSize.getValue()).intValue());
    }
 
    // Check to see if the font should be bold
    if (StyleConstants.isBold(attributes) != fontBold.isSelected()) {
      StyleConstants.setBold(attributes, fontBold.isSelected());
    }
 
    // Check to see if the font should be italic
    if (StyleConstants.isItalic(attributes) != fontItalic.isSelected()) {
      StyleConstants.setItalic(attributes, fontItalic.isSelected());
    }
 
    // and update our preview label
    updatePreviewFont();
  }
 
  // Get the appropriate font from our attributes object and update
  // the preview label
  protected void updatePreviewFont() {
    String name = StyleConstants.getFontFamily(attributes);
    boolean bold = StyleConstants.isBold(attributes);
    boolean ital = StyleConstants.isItalic(attributes);
    int size = StyleConstants.getFontSize(attributes);
 
    //Bold and italic donâ€™t work properly in beta 4.
    Font f = new Font(name, (bold ? Font.BOLD : 0) + (ital ? Font.ITALIC : 0),
        size);
    previewLabel.setFont(f);
  }
 
  // Get the appropriate color from our chooser and update previewLabel
  protected void updatePreviewColor() {
    previewLabel.setForeground(colorChooser.getColor());
 
    // Manually force the label to repaint
    previewLabel.repaint();
  }
 
  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public Font getNewFont() {
    return newFont;
  }
 
  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public Color getNewColor() {
    return newColor;
  }
 
  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public AttributeSet getAttributes() {
    return attributes;
  }
 
  /**
   * DOCUMENT ME!
   */
  public void closeAndApply() {
    // Save font & color information
    newFont = previewLabel.getFont();
    newColor = previewLabel.getForeground();
 
    // apply the changes to the jcomponent
    compo.setFont(newFont);
    compo.setForeground(newColor);
 
    // Close the window
    setVisible(false);
  }
 
  /**
   * DOCUMENT ME!
   */
  public void closeAndCancel() {
    // Erase any font information and then close the window
    newFont = null;
    newColor = null;
    setVisible(false);
  }
 
    int showDialog(Container parent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
 
}
