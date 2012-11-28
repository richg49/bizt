import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.security.cert.X509Certificate;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import hu.groupama.portal.product.biztositas.AdatFelhasználó;
import hu.groupama.portal.product.biztositas.AdatSzerződés;
import hu.groupama.portal.product.biztositas.AdatSzerződés.Vagyontárgy;
import hu.groupama.portal.product.biztositas.AdatSzerződés.Vásárló;
import hu.groupama.portal.product.biztositas.Biztosítás;
import hu.groupama.portal.product.biztositas.BlokkCím;
import hu.groupama.portal.product.log.AdatEredmény;
import hu.groupama.portal.product.log.Biztosítások;
import hu.groupama.portal.product.service.ProductWS;
import hu.groupama.portal.product.service.ProductWSService;
import hu.groupama.portal.product.setup.AdatFelhasználós;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import com.sun.xml.internal.ws.client.ClientTransportException;
import com.sun.xml.internal.ws.util.Pool.Unmarshaller;

public class biztositas {
	protected Shell shlTermkgarancia;
	private Text szamlaszam;
	private Text vnev;
	private Text vlakcim;
	private Text bteljes;
	private Text bdij;
	private Text aruhaz_kod;
	private Text aruhaz_nev;
	private Text vagy_cikk_kod;
	private Text vagy_kategoria;
	private Text vagy_marka;
	private Text vagy_tipus;
	private Text vagy_gyarto_gar_hossz;
	private Text vagy_nagyk_ar;
	private Text vagy_fogy_ar;
	private Text virszam;
	private Text vvaros;
	private int ve;
	private int vh;
	private int vn;
	private Label label_3;
	private String azonositos;
	private String piramistorzsszams;
	private String tesztbejelentkezess;
	private Text textAzonosito;
//	private String ws = "https://agentportal.garancia-online.hu/ProductCalculator/services/ProductWS";
//	private String tns = "http://service.product.portal.groupama.hu"; 
//	private String serviceName = "ProductWSService";

	public static void main(String[] args) {
		try {
			loadXML();
			biztositas window = new biztositas();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlTermkgarancia.open();
		shlTermkgarancia.layout();
		while (!shlTermkgarancia.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlTermkgarancia = new Shell();
		shlTermkgarancia.setMinimumSize(new Point(625, 333));
		shlTermkgarancia.setMinimized(true);
		shlTermkgarancia.setMaximized(true);
		shlTermkgarancia.setLayoutDeferred(true);
		shlTermkgarancia.setTouchEnabled(true);
		shlTermkgarancia.setSize(625, 383);
		shlTermkgarancia.setText("Új termékgarancia");
		
		Label lblNewLabel = new Label(shlTermkgarancia, SWT.NONE);
		lblNewLabel.setBounds(10, 83, 139, 15);
		lblNewLabel.setText("V\u00E1s\u00E1rl\u00E1si sz\u00E1mla sorsz\u00E1ma:");
		
		Label lblNewLabel_1 = new Label(shlTermkgarancia, SWT.NONE);
		lblNewLabel_1.setBounds(10, 106, 89, 15);
		lblNewLabel_1.setText("V\u00E1s\u00E1rl\u00E1s d\u00E1tuma:");
		
		Label lblNewLabel_2 = new Label(shlTermkgarancia, SWT.NONE);
		lblNewLabel_2.setBounds(10, 129, 148, 13);
		lblNewLabel_2.setText("V\u00E1s\u00E1rl\u00F3 adatai");
		
		Label lblNewLabel_3 = new Label(shlTermkgarancia, SWT.NONE);
		lblNewLabel_3.setBounds(20, 148, 49, 13);
		lblNewLabel_3.setText("N\u00E9v:");
		
		Label lblNewLabel_4 = new Label(shlTermkgarancia, SWT.NONE);
		lblNewLabel_4.setBounds(20, 167, 49, 13);
		lblNewLabel_4.setText("Lakc\u00EDm:");

		Button btnElkld = new Button(shlTermkgarancia, SWT.NONE);
		btnElkld.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				create_xml();
			}
		});
		btnElkld.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				create_xml();
			}
		});
		btnElkld.setBounds(276, 294, 68, 23);
		btnElkld.setText("Elk\u00FCld");
		
		szamlaszam = new Text(shlTermkgarancia, SWT.BORDER);
		szamlaszam.setBounds(162, 80, 76, 19);
		
		final DateTime vasarlas_date = new DateTime(shlTermkgarancia, SWT.BORDER | SWT.DROP_DOWN);
		vasarlas_date.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			    ve = vasarlas_date.getYear();
			    vh = vasarlas_date.getMonth()+1;
			    vn = vasarlas_date.getDay();
			}
		});
		vasarlas_date.setBounds(136, 102, 102, 21);
	    ve = vasarlas_date.getYear();
	    vh = vasarlas_date.getMonth()+1;
	    vn = vasarlas_date.getDay();

		vnev = new Text(shlTermkgarancia, SWT.BORDER);
		vnev.setBounds(91, 142, 166, 19);
		
		virszam = new Text(shlTermkgarancia, SWT.BORDER);
		virszam.setBounds(91, 167, 49, 19);
		
		vvaros = new Text(shlTermkgarancia, SWT.BORDER);
		vvaros.setBounds(162, 167, 95, 19);
		
		vlakcim = new Text(shlTermkgarancia, SWT.BORDER);
		vlakcim.setBounds(91, 192, 166, 19);
		
		Composite comp_aruhaz = new Composite(shlTermkgarancia, SWT.NONE);
		comp_aruhaz.setBounds(10, 225, 273, 63);
		
		Label lblNewLabel_16 = new Label(comp_aruhaz, SWT.NONE);
		lblNewLabel_16.setBounds(0, 0, 49, 13);
		lblNewLabel_16.setText("\u00C1ruh\u00E1z");
		
		Label lblNewLabel_17 = new Label(comp_aruhaz, SWT.NONE);
		lblNewLabel_17.setBounds(10, 19, 49, 13);
		lblNewLabel_17.setToolTipText("a term\u00E9ket \u00E9rt\u00E9kes\u00EDt\u0151 bolt sz\u00E1ma");
		lblNewLabel_17.setText("K\u00F3d:");
		
		aruhaz_kod = new Text(comp_aruhaz, SWT.BORDER);
		aruhaz_kod.setBounds(81, 16, 76, 19);
		
		Label lblNewLabel_18 = new Label(comp_aruhaz, SWT.NONE);
		lblNewLabel_18.setBounds(10, 38, 68, 15);
		lblNewLabel_18.setToolTipText("a term\u00E9ket \u00E9rt\u00E9kes\u00EDt\u0151 bolt neve");
		lblNewLabel_18.setText("Megnevez\u00E9s:");
		
		aruhaz_nev = new Text(comp_aruhaz, SWT.BORDER);
		aruhaz_nev.setBounds(81, 35, 166, 19);
		aruhaz_nev.setText("");
		
		Composite comp_vagyontargyadatai = new Composite(shlTermkgarancia, SWT.NONE);
		comp_vagyontargyadatai.setBounds(292, 41, 315, 172);
		
		Label lblNewLabel_6 = new Label(comp_vagyontargyadatai, SWT.NONE);
		lblNewLabel_6.setBounds(10, 29, 49, 15);
		lblNewLabel_6.setText("Cikk k\u00F3d:");
		
		Label lblNewLabel_7 = new Label(comp_vagyontargyadatai, SWT.NONE);
		lblNewLabel_7.setBounds(10, 49, 53, 15);
		lblNewLabel_7.setText("Kateg\u00F3ria:");
		
		Label lblNewLabel_8 = new Label(comp_vagyontargyadatai, SWT.NONE);
		lblNewLabel_8.setBounds(10, 69, 36, 15);
		lblNewLabel_8.setText("M\u00E1rka:");
		
		Label lblNewLabel_12 = new Label(comp_vagyontargyadatai, SWT.NONE);
		lblNewLabel_12.setBounds(10, 149, 72, 15);
		lblNewLabel_12.setToolTipText("a term\u00E9k \u00C1FA-val n\u00F6velt \u00E1ra");
		lblNewLabel_12.setText("Fogyaszt\u00F3i \u00E1r:");
		
		Label lblNewLabel_11 = new Label(comp_vagyontargyadatai, SWT.NONE);
		lblNewLabel_11.setBounds(10, 129, 113, 15);
		lblNewLabel_11.setToolTipText("a term\u00E9k beszerz\u00E9si \u00C1FA-val n\u00F6velt \u00E1ra");
		lblNewLabel_11.setText("Nagykereskedelmi \u00E1r:");
		
		Label lblNewLabel_10 = new Label(comp_vagyontargyadatai, SWT.NONE);
		lblNewLabel_10.setBounds(10, 109, 127, 15);
		lblNewLabel_10.setText("Gy\u00E1rt\u00F3i garancia hossza:");
		
		Label lblNewLabel_9 = new Label(comp_vagyontargyadatai, SWT.NONE);
		lblNewLabel_9.setBounds(10, 89, 32, 15);
		lblNewLabel_9.setText("T\u00EDpus:");
		
		Label lblNewLabel_5 = new Label(comp_vagyontargyadatai, SWT.NONE);
		lblNewLabel_5.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblNewLabel_5.setBounds(10, 8, 106, 15);
		lblNewLabel_5.setText("Vagyont\u00E1rgy adatai");
		
		vagy_cikk_kod = new Text(comp_vagyontargyadatai, SWT.BORDER);
		vagy_cikk_kod.setBounds(144, 25, 131, 19);
		
		vagy_kategoria = new Text(comp_vagyontargyadatai, SWT.BORDER);
		vagy_kategoria.setBounds(144, 45, 131, 19);
		
		vagy_marka = new Text(comp_vagyontargyadatai, SWT.BORDER);
		vagy_marka.setBounds(144, 65, 131, 19);
		
		vagy_tipus = new Text(comp_vagyontargyadatai, SWT.BORDER);
		vagy_tipus.setBounds(144, 85, 131, 19);
		
		vagy_gyarto_gar_hossz = new Text(comp_vagyontargyadatai, SWT.BORDER);
		vagy_gyarto_gar_hossz.setBounds(144, 105, 131, 19);
		
		vagy_nagyk_ar = new Text(comp_vagyontargyadatai, SWT.BORDER);
		vagy_nagyk_ar.setBounds(144, 125, 131, 19);
		
		vagy_fogy_ar = new Text(comp_vagyontargyadatai, SWT.BORDER);
		vagy_fogy_ar.setBounds(144, 145, 131, 19);
		
		Label label = new Label(comp_vagyontargyadatai, SWT.NONE);
		label.setText("Ft");
		label.setBounds(281, 128, 10, 13);
		
		Label label_1 = new Label(comp_vagyontargyadatai, SWT.NONE);
		label_1.setText("Ft");
		label_1.setBounds(281, 148, 10, 13);
		
		Label label_2 = new Label(comp_vagyontargyadatai, SWT.NONE);
		label_2.setText("(1-5)");
		label_2.setBounds(281, 108, 24, 13);
		
		Composite comp_biztositas = new Composite(shlTermkgarancia, SWT.NONE);
		comp_biztositas.setBounds(295, 225, 312, 63);
		
		Label lblNewLabel_13 = new Label(comp_biztositas, SWT.NONE);
		lblNewLabel_13.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblNewLabel_13.setBounds(0, 0, 89, 15);
		lblNewLabel_13.setText("Biztos\u00EDt\u00E1s adatai");
		
		Label lblNewLabel_14 = new Label(comp_biztositas, SWT.NONE);
		lblNewLabel_14.setBounds(10, 19, 71, 15);
		lblNewLabel_14.setToolTipText("a gy\u00E1rt\u00F3i garancia hossza \u00E9s a kiterjeszt\u00E9s hossza");
		lblNewLabel_14.setText("Teljes tartam:");
		
		Label lblNewLabel_15 = new Label(comp_biztositas, SWT.NONE);
		lblNewLabel_15.setBounds(10, 40, 71, 15);
		lblNewLabel_15.setToolTipText("a kisz\u00E1molt d\u00EDj");
		lblNewLabel_15.setText("Biztos\u00EDt\u00E1si d\u00EDj:");
		
		bteljes = new Text(comp_biztositas, SWT.BORDER);
		bteljes.setBounds(90, 16, 76, 19);
		
		bdij = new Text(comp_biztositas, SWT.BORDER);
		bdij.setBounds(90, 37, 76, 19);
		
		Label lblNewLabel_19 = new Label(comp_biztositas, SWT.NONE);
		lblNewLabel_19.setBounds(176, 19, 24, 13);
		lblNewLabel_19.setText("(1-5)");
		
		Label lblNewLabel_20 = new Label(comp_biztositas, SWT.NONE);
		lblNewLabel_20.setBounds(176, 40, 10, 13);
		lblNewLabel_20.setText("Ft");
		
		Label lblDuplaClick = new Label(shlTermkgarancia, SWT.NONE);
		lblDuplaClick.setBounds(358, 299, 60, 15);
		lblDuplaClick.setText("Dupla Click");
		
		final Label lblElesTeszt = new Label(shlTermkgarancia, SWT.NONE);
		lblElesTeszt.setAlignment(SWT.CENTER);
		lblElesTeszt.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblElesTeszt.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lblElesTeszt.setBounds(243, 10, 131, 19);
		lblElesTeszt.setText("Teszt üzemmód");

		Menu menu = new Menu(shlTermkgarancia, SWT.BAR);
		shlTermkgarancia.setMenuBar(menu);
		
		MenuItem mntmsetup = new MenuItem(menu, SWT.CASCADE);
		mntmsetup.setText("Beállítások");
		
		Menu menu_1 = new Menu(mntmsetup);
		mntmsetup.setMenu(menu_1);
		
		MenuItem mntmteszt = new MenuItem(menu_1, SWT.RADIO);
		mntmteszt.setSelection(true);
		mntmteszt.setEnabled(false);
		mntmteszt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblElesTeszt.setText("Teszt üzemmód");
			}
		});
		mntmteszt.setText("Teszt");
		try {
			JAXBContext jaxbSetup;
			jaxbSetup = JAXBContext.newInstance(hu.groupama.portal.product.setup.ObjectFactory.class);
			javax.xml.bind.Unmarshaller unmarshallerSetup = jaxbSetup.createUnmarshaller();
			@SuppressWarnings("unchecked")
			JAXBElement<AdatFelhasználós> felhasznalos = (JAXBElement<AdatFelhasználós>) unmarshallerSetup.unmarshal(new File("tgsetup.xml"));
			
			azonositos =  felhasznalos.getValue().getAzonosítós();
			piramistorzsszams = felhasznalos.getValue().getPiramisTörzsszáms();
			tesztbejelentkezess = felhasznalos.getValue().getTesztBejelentkezéss();
			
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
		
		MenuItem mntmeles = new MenuItem(menu_1, SWT.RADIO);
		mntmeles.setEnabled(false);
		mntmeles.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblElesTeszt.setText("Éles üzemmód");
			}
		});
	
		if ( Integer.valueOf(tesztbejelentkezess) == 0) {
//			mntmeles.setSelection(true);
//			mntmteszt.setSelection(false);
			tesztbejelentkezess = "1";
		} else {
			mntmeles.setSelection(false);
			mntmteszt.setSelection(true);
		}
		mntmeles.setText("Éles");
		
		MenuItem mntmsetups = new MenuItem(menu_1, SWT.NONE);
		mntmsetups.setText("Beállítások");
		
		MenuItem mntmSzervz = new MenuItem(menu, SWT.CASCADE);
		mntmSzervz.setText("Szervíz");
		
		Menu menu_2 = new Menu(mntmSzervz);
		mntmSzervz.setMenu(menu_2);
		
		MenuItem mntmKapcsolatTesztels = new MenuItem(menu_2, SWT.NONE);
		mntmKapcsolatTesztels.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tesztConection();
			}
		});
		mntmKapcsolatTesztels.setText("Kapcsolat tesztelés");
		
		MenuItem mntmBiztostsok = new MenuItem(menu, SWT.NONE);
		mntmBiztostsok.setEnabled(false);
		mntmBiztostsok.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Oldview.OldviewM();
			}
		});
		mntmBiztostsok.setText("Biztosítások");
		
		label_3 = new Label(shlTermkgarancia, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_3.setAlignment(SWT.CENTER);
		label_3.setBounds(0, 33, 617, 1);
		
		Label lblNewLabel_21 = new Label(shlTermkgarancia, SWT.NONE);
		lblNewLabel_21.setBounds(10, 40, 56, 15);
		lblNewLabel_21.setText("Azonosító:");
		
		textAzonosito = new Text(shlTermkgarancia, SWT.BORDER | SWT.READ_ONLY);
		textAzonosito.setBounds(72, 38, 102, 19);
		textAzonosito.setText(azonositos);
		
		if (mntmeles.getSelection()) {
			lblElesTeszt.setText("Éles üzemmód");
		} else {
			lblElesTeszt.setText("Teszt üzemmód");
		}
	}
	
	protected void create_xml() {
		JAXBContext jaxbContext;

		JAXBContext logjaxbContext;
		
		Biztosítás bizt = new Biztosítás();
//log		
		Biztosítások log = new Biztosítások();
		Biztosítások.Biztosítás logbizt = new Biztosítások.Biztosítás();
		
		AdatFelhasználó adf = new AdatFelhasználó();
		adf.setAzonosító(azonositos);
		adf.setPiramisTörzsszám(piramistorzsszams);
		adf.setTesztBejelentkezés(tesztbejelentkezess);
// log
		hu.groupama.portal.product.log.AdatFelhasználó logadf = new hu.groupama.portal.product.log.AdatFelhasználó();
		logadf.setAzonosító(azonositos);
		logadf.setPiramisTörzsszám(piramistorzsszams);
		logadf.setTesztBejelentkezés(tesztbejelentkezess);

		AdatSzerződés adsz = new AdatSzerződés();
		adsz.setTörlendő("0");
		adsz.setSzámlaSorszáma(szamlaszam.getText());
//log
		hu.groupama.portal.product.log.Biztosítások.Biztosítás.Termékgarancia.Szerződés logadsz = new hu.groupama.portal.product.log.Biztosítások.Biztosítás.Termékgarancia.Szerződés();
		logadsz.setTörlendő("0");
		logadsz.setSzámlaSorszáma(szamlaszam.getText());
		
		Biztosítás.Termékgarancia trg = new Biztosítás.Termékgarancia();
		trg.setFelhasználó(adf);
//log
		hu.groupama.portal.product.log.Biztosítások.Biztosítás.Termékgarancia logtrg = new hu.groupama.portal.product.log.Biztosítások.Biztosítás.Termékgarancia();
		logtrg.setFelhasználó(logadf);
		
		DatatypeFactory df = null;
		try {
			df = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e1) {
			e1.printStackTrace();
		}

		String vhs ="";
		String vns ="";

		if (vh < 10) {
			vhs = "0"+String.valueOf(vh);
		} else {
			vhs = String.valueOf(vh);
		}
		if (vn < 10) {
			vns = "0"+String.valueOf(vn);
		} else {
			vns = String.valueOf(vn);
		}
		String sdate = String.valueOf(ve)+"-"+vhs+"-"+vns;
		XMLGregorianCalendar xmlDate = df.newXMLGregorianCalendar(sdate);
		adsz.setVásárlásDátuma( xmlDate );
		logadsz.setVásárlásDátuma( xmlDate );
		
		Vásárló vs = new Vásárló();
		vs.setNév(vnev.getText());
//log
		hu.groupama.portal.product.log.Biztosítások.Biztosítás.Termékgarancia.Szerződés.Vásárló logvs = new hu.groupama.portal.product.log.Biztosítások.Biztosítás.Termékgarancia.Szerződés.Vásárló();
		logvs.setNév(vnev.getText());
		
		BlokkCím bc = new BlokkCím();
		bc.setIrányítószám(virszam.getText());
		bc.setTelepülés(vvaros.getText());
		bc.setUtcaHázszámEmeletAjtó(vlakcim.getText());
		vs.setLakcím(bc);
		adsz.setVásárló(vs);
//log
		hu.groupama.portal.product.log.BlokkCím logbc = new hu.groupama.portal.product.log.BlokkCím();
		logbc.setIrányítószám(virszam.getText());
		logbc.setTelepülés(vvaros.getText());
		logbc.setUtcaHázszámEmeletAjtó(vlakcim.getText());
		logvs.setLakcím(logbc);
		logadsz.setVásárló(logvs);
		
		int jelzo_h = 0;
		String jelzo = "OK";
		try {
			Vagyontárgy vgy = new Vagyontárgy();
			vgy.setCikkKód(vagy_cikk_kod.getText());
			vgy.setKategória(vagy_kategoria.getText());
			vgy.setMárka(vagy_marka.getText());
			vgy.setTípus(vagy_tipus.getText());
//log
			hu.groupama.portal.product.log.Vagyontárgy logvgy = new hu.groupama.portal.product.log.Vagyontárgy();
			logvgy.setCikkKód(vagy_cikk_kod.getText());
			logvgy.setKategória(vagy_kategoria.getText());
			logvgy.setMárka(vagy_marka.getText());
			logvgy.setTípus(vagy_tipus.getText());
			
			jelzo ="Garancia Hossza csak szám lehet 1-5 között!";
			vgy.setGyártóiGaranciaHossza(Integer.parseInt(vagy_gyarto_gar_hossz.getText()));
			logvgy.setGyártóiGaranciaHossza(Integer.parseInt(vagy_gyarto_gar_hossz.getText()));
			if (vgy.getGyártóiGaranciaHossza() < 1 || vgy.getGyártóiGaranciaHossza() > 5) {
				throw new NumberFormatException();
			}
			jelzo = "A nagykereskedelmi ár csak szám lehet!";
			vgy.setNagykereskedelmiÁr(Integer.parseInt(vagy_nagyk_ar.getText()));
			logvgy.setNagykereskedelmiÁr(Integer.parseInt(vagy_nagyk_ar.getText()));
			jelzo = "A fogyasztói ár csak szám lehet!";
			vgy.setFogyasztóiÁr(Integer.parseInt(vagy_fogy_ar.getText()));
			logvgy.setFogyasztóiÁr(Integer.parseInt(vagy_fogy_ar.getText()));
			adsz.setVagyontárgy(vgy);
			logadsz.setVagyontárgy(logvgy);
			
			AdatSzerződés.Biztosítás bz = new AdatSzerződés.Biztosítás();
			hu.groupama.portal.product.log.Biztosítás logbz = new hu.groupama.portal.product.log.Biztosítás();
			jelzo = "A biztosítási díj csak szám lehet!";
			bz.setBiztosításiDíj(Integer.parseInt(bdij.getText()));
			logbz.setBiztosításiDíj(Integer.parseInt(bdij.getText()));
			jelzo = "A Teljes tartam csak szám lehet 1-5 között!";
			bz.setTeljesTartam(Integer.parseInt(bteljes.getText()));
			logbz.setTeljesTartam(Integer.parseInt(bteljes.getText()));
			if (bz.getTeljesTartam() < 1 || bz.getTeljesTartam() > 5) {
				throw new NumberFormatException();
			}
			adsz.setBiztosítás(bz);
			logadsz.setBiztosítás(logbz);
		} catch (NumberFormatException e1) {
			Shell shell = new Shell();
			MessageBox messageDialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			    messageDialog.setText("Kitöltési hiba");
			    messageDialog.setMessage(jelzo);
			    jelzo_h = 1;
			    JOptionPane.showMessageDialog(null, jelzo);
		}
		
		hu.groupama.portal.product.biztositas.AdatSzerződés.Áruház arh = new hu.groupama.portal.product.biztositas.AdatSzerződés.Áruház();
		arh.setKód(aruhaz_kod.getText());
		arh.setNév(aruhaz_nev.getText());
		adsz.setÁruház(arh);
//log
		hu.groupama.portal.product.log.Áruház logarh = new hu.groupama.portal.product.log.Áruház();
		logarh.setKód(aruhaz_kod.getText());
		logarh.setNév(aruhaz_nev.getText());
		logadsz.setÁruház(logarh);
		
		trg.setSzerződés(adsz);
		bizt.setTermékgarancia(trg);

		StringWriter sw = new StringWriter();
//log
		logtrg.setSzerződés(logadsz);
		logbizt.setTermékgarancia(logtrg);

//form to xml
		try {
			jaxbContext = JAXBContext.newInstance(hu.groupama.portal.product.biztositas.ObjectFactory.class);
			Marshaller m = jaxbContext.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(bizt, new FileOutputStream("p.xml"));
			m.marshal(bizt, sw);
			
			if (sw.toString() != null && jelzo_h == 0) {
				hu.groupama.portal.product.log.AdatEredmény adateredmeny = new hu.groupama.portal.product.log.AdatEredmény();
				adateredmeny.setAjánlatszám(storeDataConection(sw.toString()));
				logadsz.setEredmény(adateredmeny);
				logtrg.setSzerződés(logadsz);
				logbizt.setTermékgarancia(logtrg);
			}
//log
			log.setBiztosítás(logbizt);
			log.setID(9999);
			logjaxbContext = JAXBContext.newInstance(hu.groupama.portal.product.log.ObjectFactory.class);
			Marshaller logm = logjaxbContext.createMarshaller();
			logm.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			logm.marshal(log, new FileOutputStream("log.xml",true));
			logm.marshal(bizt, sw);
			XMLOutput.XMLOutputM(sw.toString());
			
		} catch (JAXBException | FileNotFoundException e2) {
			e2.printStackTrace();
		}

	}
	protected void tesztConection() {
        try {
            // Create a trust manager that does not validate certificate chains     
        	final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager()  {         
    			@Override         
    			public void checkClientTrusted( final X509Certificate[] chain, final String authType ) {
    			}         
    			@Override         
    			public void checkServerTrusted( final X509Certificate[] chain, final String authType ) {
    			}         
    			@Override         
    			public X509Certificate[] getAcceptedIssuers() {
    				return null;         
    			}     
        	}
        	};          
        	SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                  return true;
                }
            };
            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        
//	        URL u = new URL(ws);
	        
//	        QName qn = new QName(tns, serviceName);
			
//	        ProductWSService ps = new ProductWSService(u,qn);
	        ProductWSService ps = new ProductWSService();
	        ProductWS p = ps.getProductWS();
	        

	        String valasz;
			try {
				valasz = p.testApplication("Teszt üzenet");
		        JOptionPane.showMessageDialog(null, valasz);
			} catch (ClientTransportException e) {
				e.printStackTrace();
		        JOptionPane.showMessageDialog(null, ((ClientTransportException) e).getMessage());
			}


	//		String valasz = 
	         } catch (Exception e) {
		            e.printStackTrace(); 
		         }		
	}
// Store Data
	protected String storeDataConection(String dataXML) {
        String valasz = "Teszt";
		try {
			// Create a trust manager that does not validate certificate chains
			final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				@Override
				public void checkClientTrusted(final X509Certificate[] chain,
						final String authType) {
				}

				@Override
				public void checkServerTrusted(final X509Certificate[] chain,
						final String authType) {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} };
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection
					.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};
			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

			// URL u = new URL(ws);

			// QName qn = new QName(tns, serviceName);

			// ProductWSService ps = new ProductWSService(u,qn);
			ProductWSService ps = new ProductWSService();
			ProductWS p = ps.getProductWS();

			try {
				valasz = p.storeData(dataXML);
				JOptionPane.showMessageDialog(null, valasz);
			} catch (ClientTransportException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
						((ClientTransportException) e).getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return valasz;
	}
// load logxml	
	protected static int loadXML() {
		try {
//			JAXBContext jaxbSetup;
//			jaxbSetup = JAXBContext.newInstance(hu.groupama.portal.product.setup.ObjectFactory.class);
//			javax.xml.bind.Unmarshaller unmarshallerSetup = jaxbSetup.createUnmarshaller();
//			@SuppressWarnings("unchecked")
//			JAXBElement<AdatFelhasználós> felhasznalos = (JAXBElement<AdatFelhasználós>) unmarshallerSetup.unmarshal(new File("tgsetup.xml"));
//			azonositos =  felhasznalos.getValue().getAzonosítós();
			
			JAXBContext jaxbContextLog = JAXBContext.newInstance(hu.groupama.portal.product.log.ObjectFactory.class);
			javax.xml.bind.Unmarshaller unmarshallerLog = jaxbContextLog.createUnmarshaller();
			Object logs = unmarshallerLog.unmarshal(new File("log.xml"));
//			JAXBElement<Biztosítások> logs = (JAXBElement<Biztosítások>) unmarshallerLog.unmarshal(new File("log.xml"));
			
			int logid = ((Biztosítások)logs).getID();
			return (logid ++);
			
			
		} catch (JAXBException  e) {
			// TODO: handle exception
		}
		return 0;
	}
}
