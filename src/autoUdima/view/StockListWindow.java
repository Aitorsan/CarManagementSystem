package autoUdima.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

public class StockListWindow extends JDialog 
{
	private static final long serialVersionUID = 3L;
	private static final int WIDTH = 1200;
	private static final int HEIGHT =300;
	private JTable table;
	private JScrollPane scrollPane;
	private SpringLayout layout;
	private Container mainFrame;

	public StockListWindow(String[][]data) 
	{
		this.setTitle("Stock disponible");
		this.setModal(true);
		initComponents(data);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void initComponents(String[][]data)  
	{
		mainFrame = getContentPane();
		layout = new SpringLayout();
		mainFrame.setBackground(new Color(228, 225, 223));
		mainFrame.setLayout(layout);
		String[] columns = new String[] { "ID", "Descripción", "Tipología", "Núm.Bastidor", "Prim.Matriculación",
					         "Estado", "Antiguedad", "P.Base", "P.Venta", "Fecha alta" };
		//I set some properties inside the class to not be able to drag columns.
		// the easiest way is to override the method isCellEditable inside the class
		table = new JTable(data, columns)
		{
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int co, int row) 
			{
				return false;
			}
		};
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setFont(new Font("Helvetica", Font.BOLD, 14));
		table.setFillsViewportHeight(true);
		table.setBackground(new Color(34, 35, 49));
		table.setFont(new Font("Heveltica", Font.BOLD, 14));
		table.setForeground(Color.WHITE);
		table.getColumnModel().getColumn(0).setMinWidth(50);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setMinWidth(300);
		table.getColumnModel().getColumn(1).setMaxWidth(300);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.getColumnModel().getColumn(2).setMaxWidth(100);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(3).setMaxWidth(100);
		table.getColumnModel().getColumn(4).setMinWidth(100);
		table.getColumnModel().getColumn(4).setMaxWidth(100);
		table.getColumnModel().getColumn(5).setMinWidth(100);
		table.getColumnModel().getColumn(5).setMaxWidth(100);
		table.getColumnModel().getColumn(6).setMaxWidth(200);
		table.getColumnModel().getColumn(6).setMaxWidth(200);
		table.getColumnModel().getColumn(7).setMaxWidth(100);
		table.getColumnModel().getColumn(7).setMaxWidth(100);
		table.getColumnModel().getColumn(8).setMaxWidth(100);
		table.getColumnModel().getColumn(8).setMaxWidth(100);
		table.getColumnModel().getColumn(9).setMinWidth(200);
		table.getColumnModel().getColumn(9).setMaxWidth(200);
		
		layout.putConstraint(SpringLayout.EAST, table, 0, SpringLayout.EAST, mainFrame);
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(200,200));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		layout.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, mainFrame);
		layout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, mainFrame);
		layout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, mainFrame);
		layout.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, mainFrame);
		getContentPane().add(scrollPane);
	}
}
