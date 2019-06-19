package autoUdima.control;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import autoUdima.model.AutoUdimaSystem;
import autoUdima.systemUtilities.Validator;
import autoUdima.view.AboutUdimaWindow;
import autoUdima.view.NewCarWindow;
import autoUdima.view.RemoveCarWindow;
import autoUdima.view.StockListWindow;
import autoUdima.view.WindowApp;

/** CLass this class its the handler of all the windows.Its the control part of the all system.<br>
 * Its responsable of the management of the data stream, between the different modules,
 * @author aitorSf */
public class WindowHandlerControl 
{
	private static int MAX_DESCRIPTION_LENGTH = 20;
	private Toolkit kit;
	// view 
	private WindowApp mainWindow;
	private NewCarWindow newCarWindow;
	private StockListWindow stockListWindow;
	private RemoveCarWindow removeCarWindow;
	private AboutUdimaWindow aboutWindow;
	//model 
	private AutoUdimaSystem systemModel;
	private ImageIcon icon;
	//Listeners 
	private FrameListener windowListener;
	private ButtonActionListener buttonActionListener;
	private TextFieldDocumentListener textFieldDocumentListener;
	private FocusTextFieldListener textFieldFocusListener;
	private KeyTypedListener keyListener;

	public WindowHandlerControl() 
	{
		//Initialize all windows
		this.systemModel = new AutoUdimaSystem("car-data.txt");
		this.kit = Toolkit.getDefaultToolkit();
		this.mainWindow= new WindowApp();
		this.removeCarWindow = new RemoveCarWindow();
		// Initialize all MemberListeners
		this.windowListener = new FrameListener();
		this.buttonActionListener = new ButtonActionListener();
		this.textFieldDocumentListener = new TextFieldDocumentListener();
		this.textFieldFocusListener = new FocusTextFieldListener();
		this.keyListener = new KeyTypedListener();
		
		try 
		{
		   icon = new ImageIcon(ImageIO.read(new File("Resources/autocon.png")));
		} 
		catch (IOException e) 
		{
		   kit.beep();
                   JOptionPane.showMessageDialog(mainWindow, "Sorry an error has happened, some images couldn't be loaded","Error",JOptionPane.ERROR_MESSAGE);
		}
		this.addListeners();
		this.mainWindow.setVisible(true);
	}

	private void addListeners() 
	{
		//windows events
		this.mainWindow.addWindowListener(windowListener);
		this.removeCarWindow.addWindowListener(windowListener);
		//buttons events
		this.mainWindow.getAddCarButton().addActionListener(buttonActionListener);
		this.mainWindow.getShowStockButton().addActionListener(buttonActionListener);
		this.mainWindow.getRemoveCarButton().addActionListener(buttonActionListener);
		this.mainWindow.getAddCarMenuItem().addActionListener(buttonActionListener);
		this.mainWindow.getRemoveCarMenuItem().addActionListener(buttonActionListener);
		this.mainWindow.getShowStockMenuItem().addActionListener(buttonActionListener);
		this.mainWindow.getExitSubItem().addActionListener(buttonActionListener);
		this.mainWindow.getAboutSubItem().addActionListener(buttonActionListener);
		this.removeCarWindow.getConfirmOperationButton().addActionListener(buttonActionListener);
		this.removeCarWindow.getCancelOperationButton().addActionListener(buttonActionListener);
		// other listeners
		this.removeCarWindow.getSearchAndRemoveDocument().addDocumentListener(textFieldDocumentListener);
		this.removeCarWindow.getSearchAndRemoveTextField().addKeyListener(keyListener);
	}
	/**
	 * Private class that extends WindowAdapter, in charge of all window events happened<br>
	 * during the application lifetime.
	 * @author aitorSf
	 */
	private class FrameListener extends WindowAdapter
	{
		@Override//this.disposed()
		public void windowClosed(WindowEvent eventWindow)
		{

			if(eventWindow.getSource() == newCarWindow) 
			{
				newCarWindow.resetTextFields();
			}
			if(eventWindow.getSource() == removeCarWindow) 
			{
				removeCarWindow.resetSearchAndRemoveTextField();
			}	
		}
       
		@Override
		public void windowOpened(WindowEvent eventWindow) 
		{
			if(eventWindow.getSource() == mainWindow) 
			{
				try 
				{
					mainWindow.getInicialMessageLabel().setText(systemModel.cargarDatos());

				} catch (Exception e) 
				{
					if( e instanceof ClassNotFoundException)  
						mainWindow.getInicialMessageLabel().setText("La clase no ha podido ser extraida del fichero" +e.getMessage());

					if(e instanceof FileNotFoundException)
						mainWindow.getInicialMessageLabel().setText("Se ha creado un archivo con el nombre: " + e.getMessage() );

					else if( e instanceof IOException)
						mainWindow.getInicialMessageLabel().setText("Ha habido un problema en la extraccion de datos:" + e.getMessage());
				}
			}
		}

		@Override
		public void windowClosing(WindowEvent event) 
		{
			if (event.getWindow() == newCarWindow || event.getWindow() == mainWindow) 
			{
				try 
				{
					systemModel.saveData();
				} 
				catch (IOException e) 
				{
					System.out.println(e.getMessage());
				}
			}
			if(event.getWindow() == mainWindow) 
			{
				kit.beep();
				JOptionPane.showMessageDialog(null, "Hasta La proxima !!", "Despedida", JOptionPane.CLOSED_OPTION,icon);		
			}
		}
	}
	/**
	 * Private class <b> ButtonActionListener</b>.Implements ActionListener,this class manage all the action<br>
	 * perform by all the buttons components of the application.
	 * @author aitorSf
	 */
	private class ButtonActionListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent buttonEvent) 
		{
			if(buttonEvent.getSource() == mainWindow.getExitSubItem()) 
			{
				kit.beep();
				int election = JOptionPane.showConfirmDialog(mainWindow,"Estas seguro que desea salir?","Salir",JOptionPane.OK_CANCEL_OPTION);
				if( election == 0) 
				{
					JOptionPane.showMessageDialog(null, "Hasta La proxima !!", "Despedida", JOptionPane.CLOSED_OPTION,icon);
					mainWindow.dispose();
					System.exit(0);
				}
			}

			if( buttonEvent.getSource() == mainWindow.getAboutSubItem())
			{
				try 
				{
					aboutWindow = new AboutUdimaWindow();
					aboutWindow.setVisible(true);
				} 
				catch (IOException e) 
				{
					kit.beep();
					JOptionPane.showMessageDialog(aboutWindow, "Error al cargar la imagen","Error", JOptionPane.ERROR_MESSAGE,icon);
				}
			}

			if(buttonEvent.getSource() == mainWindow.getAddCarButton() || buttonEvent.getSource() == mainWindow.getAddCarMenuItem()) 
			{
				createNewCarWindow();
			}

			if(buttonEvent.getSource()== mainWindow.getShowStockButton() || buttonEvent.getSource() == mainWindow.getShowStockMenuItem()) 
			{
				createStockListWindow();
			}

			if(buttonEvent.getSource()== mainWindow.getRemoveCarButton() || buttonEvent.getSource()== mainWindow.getRemoveCarMenuItem() ) 
			{
				removeCarWindow.getSearchAndRemoveTextField().setBackground(Color.WHITE);
				removeCarWindow.setVisible(true);
			}

			/*
			 *  WARNING!!
			 *  If we press the button that is part of the new Car window before perform any operation,
			 *  we have to be careful checking for the button inside the
			 *  newCarWindow, because could not be instantiate at the moment we are trying
			 *  to verify which event has occurred.
			 *  To solve this problem we only check if the button was press inside the newCarWindow, if this variable was instantiate 
			 *  it before we acces to them elements, and has a value different to null.
			 *  If we don't do this testing, a NullPointerException will be thrown.
			 *  Another solution would be make different actionListener objects for each window.
			 *  
			 */
			if( newCarWindow != null) 
			{
				if(buttonEvent.getSource() == newCarWindow.getCreateCarButton()) 
				{
					validateCreationOfNewCar();
				}
				else if(buttonEvent.getSource() == newCarWindow.getCancelButton()) 
				{
					kit.beep();
					int election = JOptionPane.showConfirmDialog(newCarWindow,"Estas seguro que desea salir?","Salir",JOptionPane.OK_CANCEL_OPTION);
					if( election == 0)
						newCarWindow.dispose();
				}
			}

			if(buttonEvent.getSource() == removeCarWindow.getCancelOperationButton()) 
			{
				removeCarWindow.setVisible(false);
				removeCarWindow.resetSearchAndRemoveTextField();
			}

			if(buttonEvent.getSource() == removeCarWindow.getConfirmOperationButton()) 
			{
				if( !(removeCarWindow.getCarId().isEmpty())) 
				{
					int index = systemModel.searchCarIndex(removeCarWindow.getCarId());

					if( index == -1) 
					{
						kit.beep();
						JOptionPane.showMessageDialog(removeCarWindow, "EL coche con el numero de identificacion: "
								+ removeCarWindow.getCarId()+ " no existe.", 
								"Informacion",JOptionPane.INFORMATION_MESSAGE);
					}
					else 
					{
						int option = JOptionPane.showConfirmDialog(removeCarWindow,"Esta Seguro que desea continuar?\nUna vez realizada la operación los datos se borraran definitivamente de la base de datos.","Advertencia",JOptionPane.OK_CANCEL_OPTION);
						if(option == 0) 
						{
							try 
							{
								systemModel.deleteCar(index);
							} 
							catch (IOException e) 
							{
								kit.beep();
								JOptionPane.showMessageDialog(removeCarWindow, "Ha habido un error modificando la base de datos "+ e.getMessage(),"ERROR",JOptionPane.ERROR); 
							}
							kit.beep();
							JOptionPane.showMessageDialog(removeCarWindow, "EL coche con el numero de identificacion: "
									+ removeCarWindow.getCarId()+ " ha sido borrado correctamente.", 
									"Informacion",JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
				else 
				{
					kit.beep();
					JOptionPane.showMessageDialog(removeCarWindow, "Por Favor introduce el numero de identificador "
							+ removeCarWindow.getCarId(), 
							"Informacion",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}

		private void createNewCarWindow() 
		{
			newCarWindow = new NewCarWindow();	
			addNewCarWindowListeners();
			newCarWindow.setVisible(true);
		}
	
		private void addNewCarWindowListeners() 
		{
			newCarWindow.addWindowListener(windowListener);
			/* because this Action listener is an inner class. And I added already an action listener to the main frame that contains
			 *  as part of them attributes an object of the class NewCarWindow. When i add an actionListener to this member with the keyWord
			 * this, i'm assigning the same actionListener object that the main frame has.
			 * 
			 * I could also write:
			 *        
			 *        newCarWindow.getCancelButton().addActionListener(buttonActionListener);
			 *  
			 * The code above its equivalent to the code below
			 *   
			 */
			newCarWindow.getCancelButton().addActionListener(this);
			newCarWindow.getCreateCarButton().addActionListener(this);
			newCarWindow.getDescriptionTextField().addFocusListener(textFieldFocusListener);
			newCarWindow.getFrameNumberTextField().addFocusListener(textFieldFocusListener);
			newCarWindow.getBasePriceTextField().addFocusListener(textFieldFocusListener);
			newCarWindow.getFrameNumberDocument().addDocumentListener(textFieldDocumentListener);
			newCarWindow.getDescriptionDocument().addDocumentListener(textFieldDocumentListener);
			newCarWindow.getDescriptionTextField().addKeyListener(keyListener);
			newCarWindow.getBasePriceTextField().addKeyListener(keyListener);
			newCarWindow.getFrameNumberTextField().addKeyListener(keyListener);
		}
		/**
		 * If the button of newCarWindow its pressed we validate the data and create a new car
		 * @throws BadLocationException 
		 */
		private void validateCreationOfNewCar()  
		{
			try 
			{
				boolean createCar = true;
				String [] emptyFields = new String[4];

				if( newCarWindow.getDescriptionTextField().getText().isEmpty()) 
				{
					emptyFields[0] = "- Descripcion";
					createCar = false;
				}

				if( newCarWindow.getFrameNumberTextField().getText().isEmpty()) 
				{
					emptyFields[1] = "- Numero de bastidor";
					createCar = false;
				}

				if(newCarWindow.getBasePriceTextField().getText().isEmpty())
				{
					emptyFields[2] = "- Precio base"; 
					createCar = false;
				}

				if( newCarWindow.getMatriculationDate() == null)
				{
					emptyFields[3] = "- Fecha de matriculacion";
					createCar = false;
				}
				
				if(createCar) 
				{
					String wrongFields = "Errores:\n";
					boolean invalidData = false;
					//Before we proceed, we check if the sesible entries are valid ( description, frame number and base price)
					if(!(Validator.matchPattern(NewCarWindow.largeFramePatter, newCarWindow.getFrameNumber()))) 
					{
						wrongFields += "- EL numero de bastidor no es válido\n";
						invalidData = true;
					}

					if(newCarWindow.getDescription().length() > MAX_DESCRIPTION_LENGTH) 
					{
						wrongFields += "- La descripción introducida es demasiado larga";
						invalidData = true;
					}

					if(!(Validator.matchPattern("[0-9]+", newCarWindow.getBasePrice()))) 
					{
						wrongFields += " - El precio Base debe ser un numero";
						invalidData = true;
					}

					// if some of the fields has wrong data after we press the button throw an exception and a dialog window will show
					if( invalidData) 
					{
						throw new IllegalArgumentException(wrongFields);
					}

					// last we check if the frame number already exist on the data base
					if(!(Validator.checkFrameNumberOriginality(newCarWindow.getFrameNumber(),systemModel.getDataFromList())))
					{
						throw new IllegalArgumentException("EL numero de bastidor: "+newCarWindow.getFrameNumber() + ", ya existe en la base de datos.");
					}
					kit.beep();
					int option = JOptionPane.showConfirmDialog(newCarWindow, "Esta seguro de que desea continuar con la creacion del nuevo coche?","Informacion",JOptionPane.YES_NO_OPTION);

					switch(option) 
					{
					case 0:
						String[] datos = 
						{
								newCarWindow.getDescription(),
								newCarWindow.getTypology(),
								newCarWindow.getCarState(),
								newCarWindow.getFrameNumber(),
								newCarWindow.getMatriculationDate(),
								newCarWindow.getBasePriceTextField().getText()
						};
						systemModel.addNewCar(datos);
						kit.beep();
						JOptionPane.showMessageDialog(newCarWindow, "La operación ha sido realizada con éxito","Proceso completado",JOptionPane.INFORMATION_MESSAGE);
						newCarWindow.resetTextFields();
						break;
					default:
						kit.beep();
						JOptionPane.showMessageDialog(newCarWindow, "La operación ha sido cancelada con éxito","Operacion Cancelada",JOptionPane.CANCEL_OPTION);
						break;
					}
				}
				else 
				{
					String finalEmptyFields="";
					for ( String field : emptyFields) 
					{
						if( field != null) 
						{
							finalEmptyFields += field + "\n";
						}
					}
					JOptionPane.showMessageDialog(newCarWindow, "Faltan datos :\n" + finalEmptyFields ,"Operacion Cancelada",JOptionPane.CANCEL_OPTION);
				}
			}
			catch(Exception e) 
			{
				if(e instanceof IllegalArgumentException) 
				{
					kit.beep();
					JOptionPane.showMessageDialog(newCarWindow, e.getMessage(),"Error operacion Cancelada",JOptionPane.ERROR_MESSAGE);
				}
				else if(e instanceof NumberFormatException )
				{
					System.out.println("Some entry of data have failed, parsing :line 234 to line 245");
				}
			}
		}

		private void createStockListWindow() 
		{
			stockListWindow = new StockListWindow(systemModel.getCarInformation());
			addStockListWindowListeners();
			stockListWindow.setVisible(true);
		}
	
		private void addStockListWindowListeners() 
		{
			stockListWindow.addWindowListener(windowListener);
		}
	}
	/******************************************************************
	 * Class<b>TextFieldDocumentListener<b>.
	 * Implements DocumentListener for the textFields.
	 * @author aitorSf
	 ******************************************************************/
	private class TextFieldDocumentListener implements DocumentListener
	{
		@Override
		public void changedUpdate(DocumentEvent event) 
		{
			udpateDocument( event);
		}

		@Override
		public void insertUpdate(DocumentEvent event) 
		{
			udpateDocument( event);
		}

		@Override
		public void removeUpdate(DocumentEvent event) 
		{
			udpateDocument(event);
		}
		/** Update the document where the event has ocurred @param event */
		private void udpateDocument(DocumentEvent event) 
		{
			/* because we use the same document listener for other windows and them compononets.
			 * There is a chance that the newCarWindow its not instantiate it so we need to check
			 * if its not null, before get call any method */
			if(newCarWindow != null)	
			{
				if(( event.getDocument() == newCarWindow.getFrameNumberDocument() ) && ( !(newCarWindow.getFrameNumber().isEmpty()) ) ) 
				{
					newCarWindow.getFrameNumberTextField().setForeground(Validator.getValidationColor(NewCarWindow.shortFramePattern, newCarWindow.getFrameNumber(),true));
				}
				else 
				{
					newCarWindow.setFrameNumberTextFieldFontColor(Color.WHITE);
				}
			}
			
			if(event.getDocument() == removeCarWindow.getSearchAndRemoveDocument() ) 
			{
				removeCarWindow.setTextFieldColor(Validator.getValidationColor(removeCarWindow.getIdPattern(), removeCarWindow.getCarId(),false));
			}
		}
	}
	/**************************************************************************
	 * Class FocusTextFieldListener implements FocusListner for the textFields
	 * @author aitorSf
	 ******************************************************************/
	private class FocusTextFieldListener implements FocusListener
	{
		@Override
		public void focusGained(FocusEvent eventFocus) 
		{
			if(newCarWindow != null) 
			{
				if(eventFocus.getComponent() == newCarWindow.getDescriptionTextField()) 
				{
					newCarWindow.getDescriptionTextField().setBackground(Color.WHITE);

				}
				if(eventFocus.getComponent() == newCarWindow.getFrameNumberTextField()) 
				{
					newCarWindow.getFrameNumberTextField().setBackground(Color.WHITE);
					newCarWindow.setFrameNumberTextFieldFontColor(Validator.getValidationColor(NewCarWindow.shortFramePattern, newCarWindow.getFrameNumber(), true));
				}
				if(eventFocus.getComponent() == newCarWindow.getBasePriceTextField()) 
				{
					newCarWindow.getBasePriceTextField().setBackground(Color.WHITE);
				}
			}
		}
		@Override
		public void focusLost(FocusEvent eventFocusLost) 
		{
			if(newCarWindow != null) 
			{
				if(eventFocusLost.getComponent() == newCarWindow.getFrameNumberTextField()) 
				{
					newCarWindow.getFrameNumberTextField().setBackground(Color.lightGray);
					newCarWindow.setFrameNumberTextFieldFontColor(Color.BLACK);
				}

				if(eventFocusLost.getComponent() == newCarWindow.getDescriptionTextField()) 
				{
					newCarWindow.getDescriptionTextField().setBackground(Color.lightGray);
				}

				if(eventFocusLost.getComponent() == newCarWindow.getBasePriceTextField()) 
				{
					newCarWindow.getBasePriceTextField().setBackground(Color.lightGray);
				}
			}
		}
	}
	/*******************************************************************
	 * Class<b> KeyTypeListener</b> extendes <b> KeyAdapter</b>.<br>
	 * Each time a key is pressede inside any textField this class<br>
	 * Will take care of it, and take the corresponding actions.
	 * @author aitorSf
	 ******************************************************************/
	private class KeyTypedListener extends KeyAdapter
	{
		@Override
		public void keyTyped(KeyEvent event) 
		{
			if(newCarWindow != null) 
			{
				if( event.getSource() == newCarWindow.getDescriptionTextField()) 
				{
					consume(newCarWindow.getDescription(),event,15);
				}
				else if( event.getSource() == newCarWindow.getBasePriceTextField()) 
				{
					consume(newCarWindow.getBasePrice(),event,10);

				}
				else if( event.getSource() == newCarWindow.getFrameNumberTextField()) 
				{
					consume(newCarWindow.getFrameNumber(),event,9);
				}
			}

			if( event.getSource() == removeCarWindow.getSearchAndRemoveTextField())
			{
				consume(removeCarWindow.getSearchAndRemoveTextField().getText(),event,3);
			}
		}
		/**
		 * Method that consume the exceeded text in the textFields.
		 * @param text text of introduced on the textField
		 * @param offset the starting point of consume
		 * @param event the event object where the event have ocurred
		 */
		private void consume(String text, KeyEvent event, int offset) 
		{
			if( text.length() > offset) 
			{
				kit.beep();
				event.consume();
			}
		}
	}
}
