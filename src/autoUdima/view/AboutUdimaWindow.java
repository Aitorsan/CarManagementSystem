package autoUdima.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutUdimaWindow extends JDialog {

	/**
	 * Default serial id
	 */
	private static final long serialVersionUID = 1L;
   // constants
	private static final int WIDTH = 300;
	private static final  int HEIGHT = 200;
	
	// componentes
	private JPanel logoPanel;
	private JPanel decorationEast;
	private JPanel decorationWest;
	private JLabel versionLabel;
	private JLabel imageCopyRightLabel;
	private JLabel developmentCopyRightLabel;
	private JLabel iconLabel;
	private ImageIcon logoIcon;
	
	  public AboutUdimaWindow() throws IOException {
		
		setTitle("AUTOUDIMA");	
		setModal(true);
		initComponents();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
	    setResizable(false);
		pack();
		setLocationRelativeTo(null);
	    setVisible(false);
	  }
	
	
	private void initComponents() throws IOException {
		
		getContentPane().setLayout(new BorderLayout());
		logoPanel = new JPanel(new FlowLayout());
		decorationEast = new JPanel();
		decorationEast.setBackground(new Color(34,35,49));
		decorationWest = new JPanel();
		decorationWest.setBackground(new Color(34,35,49));
		logoIcon = new ImageIcon(ImageIO.read(new File("Resources/autocon.png")));
		iconLabel = new JLabel(logoIcon);
		
	
		
		versionLabel = new JLabel("Version: AutoUdima.2.0 - 2018 ");
		versionLabel.setFont(new Font("verdata",Font.PLAIN,15));
		
		imageCopyRightLabel = new JLabel("© Image design: Carolin Nothof" );
		developmentCopyRightLabel = new JLabel("© Developed: Aitor Sanmartin Ferreia");
		logoPanel.add(iconLabel);
		logoPanel.add(versionLabel);
		logoPanel.add(developmentCopyRightLabel);
		logoPanel.add(imageCopyRightLabel);
		this.add(decorationEast, BorderLayout.LINE_START);
		this.add(decorationWest, BorderLayout.LINE_END);
		this.add(logoPanel,BorderLayout.CENTER);
		
	}
	
}
