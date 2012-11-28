import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;


public class Oldview extends Shell {

	private Table table;

	public static void OldviewM() {
		try {
			Display display = Display.getDefault();
			Oldview shell = new Oldview(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Oldview(final Display display) {
		super(display, SWT.SHELL_TRIM);
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setBounds(10, 0, 891, 44);
		
		Label lblBztostsokAdatai = new Label(composite_1, SWT.CENTER);
		lblBztostsokAdatai.setBounds(363, 10, 165, 25);
		lblBztostsokAdatai.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblBztostsokAdatai.setAlignment(SWT.CENTER);
		lblBztostsokAdatai.setText("Bíztosítások adatai");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 50, 891, 430);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(69);
		tblclmnNewColumn.setText("Sorszám");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(107);
		tblclmnNewColumn_1.setText("Számla sorszáma");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_6.setWidth(47);
		tblclmnNewColumn_6.setText("Törölt");
		
		TableColumn tblclmnNv = new TableColumn(table, SWT.NONE);
		tblclmnNv.setWidth(49);
		tblclmnNv.setText("Név");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("Irányítószám");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("Település");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("Utca_házszám_emelet_ajtó");
		
		TableColumn tblclmnCikkkd = new TableColumn(table, SWT.NONE);
		tblclmnCikkkd.setWidth(100);
		tblclmnCikkkd.setText("Cikk_kód");
		
		TableColumn tblclmnKategria = new TableColumn(table, SWT.NONE);
		tblclmnKategria.setWidth(100);
		tblclmnKategria.setText("Kategória");
		
		TableColumn tblclmnMrka = new TableColumn(table, SWT.NONE);
		tblclmnMrka.setWidth(65);
		tblclmnMrka.setText("Márka");
		
		TableColumn tblclmnTpus = new TableColumn(table, SWT.NONE);
		tblclmnTpus.setWidth(100);
		tblclmnTpus.setText("Típus");
		
		TableColumn tblclmnGyrtigaranciahossza = new TableColumn(table, SWT.NONE);
		tblclmnGyrtigaranciahossza.setWidth(100);
		tblclmnGyrtigaranciahossza.setText("Gyártói_garancia_hossza");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_5.setWidth(100);
		tblclmnNewColumn_5.setText("Nagykereskedelmi_ár");
		
		TableColumn tblclmnFogyasztir = new TableColumn(table, SWT.NONE);
		tblclmnFogyasztir.setWidth(100);
		tblclmnFogyasztir.setText("Fogyasztói_ár");
		
		TableColumn tblclmnTeljestartam = new TableColumn(table, SWT.NONE);
		tblclmnTeljestartam.setWidth(100);
		tblclmnTeljestartam.setText("Teljes_tartam");
		
		TableColumn tblclmnBiztostsidj = new TableColumn(table, SWT.NONE);
		tblclmnBiztostsidj.setWidth(100);
		tblclmnBiztostsidj.setText("Biztosítási_díj");
		
		TableColumn tblclmnruhz = new TableColumn(table, SWT.NONE);
		tblclmnruhz.setWidth(100);
		tblclmnruhz.setText("Áruház Kód");
		
		TableColumn tblclmnruhz_1 = new TableColumn(table, SWT.NONE);
		tblclmnruhz_1.setWidth(100);
		tblclmnruhz_1.setText("Áruház Név");
		scrolledComposite.setContent(table);
		scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		for (int i = 0 ; i<= 50 ; i++){
		      TableItem item = new TableItem(table, SWT.NONE);
		      item.setText (0, "Person " +i);
			  item.setText (1, "LastName " +i);
			  item.setText (2, String.valueOf(i));
		}
		createContents();
	}
	protected void createContents() {
		setSize(927, 568);
		setText("Log nézegető");
		setLayout(null);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
