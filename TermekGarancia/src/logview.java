import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class logview {

	private JFrame frmLogNzeget;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void mainlogview(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logview window = new logview();
					window.frmLogNzeget.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public logview() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogNzeget = new JFrame();
		frmLogNzeget.setTitle("Log nézegető");
		frmLogNzeget.setBounds(100, 100, 1001, 686);
		frmLogNzeget.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogNzeget.getContentPane().setLayout(null);
		{
			JLabel lblBztostsokAdatai = new JLabel("Bíztosítások adatai");
			lblBztostsokAdatai.setBounds(428, 0, 129, 17);
			lblBztostsokAdatai.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblBztostsokAdatai.setHorizontalAlignment(SwingConstants.CENTER);
			frmLogNzeget.getContentPane().add(lblBztostsokAdatai);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPane.setBounds(0, 28, 975, 523);
			frmLogNzeget.getContentPane().add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
				table.setColumnSelectionAllowed(true);
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{new Integer(111), "1", "1", "1", "1", "1", "1", "1", "1", "1", new Integer(1), new Integer(1), "1", new Integer(1), "1", "1", "1", "1"},
					},
					new String[] {
						"Sorsz\u00E1m", "Sz\u00E1mla sorsz\u00E1ma", "N\u00E9v", "Ir\u00E1ny\u00EDt\u00F3sz\u00E1m", "Telep\u00FCl\u00E9s", "Utca h\u00E1zsz\u00E1m", "Cikk k\u00F3d", "Kateg\u00F3ria", "T\u00EDpus", "M\u00E1rka", "Gy\u00E1rt\u00F3i_garancia_hossza", "Nagykereskedelmi_\u00E1r", "Fogyaszt\u00F3i_\u00E1r", "Teljes_tartam", "Biztos\u00EDt\u00E1si_d\u00EDj", "\u00C1ruh\u00E1z K\u00F3d", "\u00C1ruh\u00E1z N\u00E9v", "Aj\u00E1nlatsz\u00E1m"
					}
				) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					Class[] columnTypes = new Class[] {
						Integer.class, String.class, String.class, String.class, String.class, Object.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class, Object.class, Integer.class, Object.class, String.class, Object.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				table.setBorder(new LineBorder(new Color(0, 0, 0)));
			}
		}
	}
}
