package autoUdima.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

/** CLass WindowApp main window for the application.<br>
 * @author aitorSf */
public class WindowApp extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static final int MIN_WIDTH=800;
	private static final int MIN_HEIGHT=400;
	private static final int NUM_MENU_ITEMS = 3;
	private static final int NUM_SUBMENU_ITEMS = 5;
	private static final int AUTOUDIMA = 0;
	private static final int FILE = 1;
	private static final int HELP = 2;
	private static final int ADD_NEW_CAR = 0;
	private static final int SEE_STOCK = 1;
	private static final int DELETE_CAR = 2;
	private static final int EXIT = 3;
	private static final int ABOUT = 4;
	//Components
	private JPanel imagenEmpresaPanel;
	private JPanel panelComponentes;
	private JButton addCarButton;
	private JButton removeCarButton;
	private JButton showStockButton;
	private ImageIcon iconCar;
	private ImageIcon iconRemove;
	private ImageIcon iconStock;
	private ImageIcon iconLogo;
	JLabel inicialMessageLabel;
	private JMenuBar menuBar;
	private JMenu [] menuItems;
	private JMenuItem []menuSubItems;
	private JToolBar toolBar;

	public WindowApp() 
	{
	   super("AutoUdima 2.0");
	   initializeComponents();
	   this.setIconImage(createImage("Resources/autocon2.png"));
	   this.setResizable(true);
           this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	   this.setMinimumSize(new Dimension(MIN_WIDTH,MIN_HEIGHT));
	   this.pack();//this method has to be call after the dimensions, to center the JFRAME
	   this.setLocationRelativeTo(null);
	   this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private Image createImage(String path)
	{
	   ImageIcon temp =  new ImageIcon(path);
	   Image tempImage = temp.getImage();//.getScaledInstance(20,22,  java.awt.Image.SCALE_SMOOTH);
	   return tempImage;
	}

	private void initializeComponents() 
	{
	   getContentPane().setLayout(new BorderLayout() );
	   menuBar = new JMenuBar();
	   menuItems = new JMenu[NUM_MENU_ITEMS] ;
	   menuItems[AUTOUDIMA]= new JMenu("AutoUdima");
	   menuItems[FILE]= new JMenu("File");
	   menuItems[HELP]=new JMenu("Ayuda");
	   
	   menuSubItems = new  JMenuItem[NUM_SUBMENU_ITEMS];
	   menuSubItems[ADD_NEW_CAR] = new  JMenuItem("Añadir coche");
	   menuSubItems[SEE_STOCK] = new JMenuItem("Mirar stock");
	   menuSubItems[DELETE_CAR] = new JMenuItem("Elminiar coche");
	   menuSubItems[EXIT] = new JMenuItem("Salir");
	   menuSubItems[ABOUT] = new JMenuItem("About AutoUdima");
	   
	   menuItems[FILE].add(menuSubItems[ADD_NEW_CAR]);
	   menuItems[FILE].add(menuSubItems[SEE_STOCK]);
	   menuItems[FILE].add(menuSubItems[DELETE_CAR]);
	   menuItems[AUTOUDIMA].add(menuSubItems[ABOUT]);
	   menuItems[AUTOUDIMA].add(menuSubItems[EXIT]);
	   
	   for ( JMenu m : menuItems)
	   {  	
		menuBar.add(m);  
	   }
	   toolBar = new JToolBar();
	   toolBar.setFloatable(false);

	   imagenEmpresaPanel = new JPanel();
	   imagenEmpresaPanel.setLayout(new FlowLayout());
	   imagenEmpresaPanel.setBorder(new LineBorder(new Color(228,225,223),14));
	   imagenEmpresaPanel.setBackground(new Color(228,225,223));
	   try 
	   {
		imagenEmpresaPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("Resources/AutoUdima.png")) )));

	   } 
	   catch (IOException e) 
	   {
		System.out.println("image doesn't exist");
	   }
	   inicialMessageLabel = new JLabel();
	   inicialMessageLabel.setFont(new Font("Heveltica",Font.PLAIN,18));
	   imagenEmpresaPanel.add(inicialMessageLabel);
	
	   panelComponentes = new JPanel();
	   panelComponentes.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
	   panelComponentes.setBackground(new Color(34,35,49));
	   panelComponentes.setPreferredSize(new Dimension(MIN_WIDTH/4, MIN_HEIGHT));

	   JPanel panelComponentesDos = new JPanel();
	   panelComponentesDos.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
	   panelComponentesDos.setBackground(new Color(34,35,49));
	   panelComponentesDos.setPreferredSize(new Dimension(MIN_WIDTH/4, MIN_HEIGHT));
	   addCarButton = new JButton();
	   iconCar = new ImageIcon("Resources/addCarrButton.png");
	   Image imagen = iconCar.getImage();
	   Image newimg = imagen.getScaledInstance(30,32,  java.awt.Image.SCALE_SMOOTH);
	   iconCar = new ImageIcon(newimg);
	   addCarButton.setIcon(iconCar);
           //Add car button text font
	   addCarButton.setText("add Car");
	   addCarButton.setFont(new Font("Heveltica",Font.BOLD,14));
	   //	//Add car button text color
	   addCarButton.setForeground(new Color(34,35,49));
	   iconRemove = new ImageIcon("Resources/removeCar.png");
	   Image img= iconRemove.getImage();
	   Image nImg = img.getScaledInstance(30,32,java.awt.Image.SCALE_SMOOTH);
	   iconRemove = new ImageIcon(nImg);
	   // Remove car button text font
	   removeCarButton = new JButton("remove Car",iconRemove);
	   removeCarButton.setText("remove");
	   removeCarButton.setFont(new Font("Heveltica",Font.BOLD,14));
	   // Remove car button text color
	   removeCarButton.setForeground(new Color(34,35,49));
	   removeCarButton.setIcon(iconRemove);
	   // Show stock button configuration
	   showStockButton = new JButton();
	   iconStock = new ImageIcon("Resources/listIcon.png");
	   Image imgS= iconStock.getImage();
	   Image nImgS = imgS.getScaledInstance(35,32, java.awt.Image.SCALE_SMOOTH);
	   iconStock = new ImageIcon(nImgS);
	   showStockButton.setIcon(iconStock);
	   //Show stock button text font
	   showStockButton.setText("show stock");
	   showStockButton.setFont(new Font("Heveltica",Font.BOLD,14));
	   //Show stock button text color
	   showStockButton.setForeground(new Color(34,35,49));
	   iconLogo = new ImageIcon("Resources/mainIcon.png");
	   Image logoImg= iconLogo.getImage();
	   Image logo = logoImg.getScaledInstance(35,32, java.awt.Image.SCALE_SMOOTH);
	   iconLogo = new ImageIcon(logo);	
	   
	   menuSubItems[ADD_NEW_CAR].setIcon(iconCar); 
	   menuSubItems[SEE_STOCK].setIcon(iconStock);
	   menuSubItems[DELETE_CAR].setIcon(iconRemove);
	   menuSubItems[ABOUT].setIcon(iconLogo);
	   //Add the components to the frame
	   toolBar.add(addCarButton);
	   toolBar.add(removeCarButton);
	   toolBar.add(showStockButton);
	   getContentPane().add(imagenEmpresaPanel,BorderLayout.CENTER);
	   getContentPane().add(panelComponentes,BorderLayout.LINE_START);
	   getContentPane().add(panelComponentesDos,BorderLayout.LINE_END);
	   getContentPane().add(toolBar,BorderLayout.SOUTH);
	   getContentPane().add(menuBar,BorderLayout.NORTH);
	}
	
	public JMenuItem getAddCarMenuItem() 
	{
	   return menuSubItems[ADD_NEW_CAR];
	}

	public JMenuItem getRemoveCarMenuItem()
	{
	   return menuSubItems[DELETE_CAR];
	}
	
	public JMenuItem getShowStockMenuItem() 
	{
	   return menuSubItems[SEE_STOCK];
	}

	public JButton getAddCarButton() 
	{
	   return addCarButton;
	}

	public JButton getRemoveCarButton()
	{
	   return removeCarButton;
	}

	public JButton getShowStockButton() 
	{
	   return showStockButton;
	}

	public JMenuItem getExitSubItem() 
	{
	   return menuSubItems[EXIT];
	}
	
	public JMenuItem getAboutSubItem()
	{
	   return menuSubItems[ABOUT];
	}

	public JLabel getInicialMessageLabel() 
	{
	   return inicialMessageLabel;
	}

	public void setInicialMessage(String mensaje)
	{
	   this.inicialMessageLabel.setText(mensaje);
	}
}
