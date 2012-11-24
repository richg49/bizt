import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;


public class XMLOutput extends Shell {

	/**
	 * Launch the application.
	 * @param xmloo
	 */
	public static void XMLOutputM(String xmloo) {
		try {
			Display display = Display.getDefault();
			XMLOutput shell = new XMLOutput(display, xmloo);
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

	/**
	 * Create the shell.
	 * @param display
	 * @param xmlout 
	 */
	public XMLOutput(final Display display, String xmlout) {
		super(display, SWT.SHELL_TRIM);

		Button btnBezr = new Button(this, SWT.NONE);
		btnBezr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				display.getActiveShell().dispose();
			}
		});
		btnBezr.setBounds(262, 404, 68, 23);
		btnBezr.setText("Bezár");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(0, 29, 593, 363);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		StyledText styledText_xmloutout = new StyledText(scrolledComposite, SWT.BORDER | SWT.READ_ONLY);
		styledText_xmloutout.setAlignment(SWT.CENTER);
		styledText_xmloutout.setText(xmlout);
		scrolledComposite.setContent(styledText_xmloutout);
		scrolledComposite.setMinSize(styledText_xmloutout.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.BOLD));
		lblNewLabel.setBounds(248, 10, 95, 19);
		lblNewLabel.setText("XML Output");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("XML View");
		setSize(608, 471);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
