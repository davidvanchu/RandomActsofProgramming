/**
 * @author David Chu
 * @version 1.0
 * 
 * Printer GUI which displays the current ink levels and paper supply level (specifically for the DA office printer)
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PrinterStatusGUI {
	private static final boolean debug = false;
	private static final String IPADDRESSOFPRINTER = "udp:192.168.3.2/161";
	private static JLabel blackTonerLabel;
	private static JLabel cyanTonerLabel;
	private static JLabel magentaTonerLabel;
	private static JLabel yellowTonerLabel;
	private static JLabel drawer1Label;
	private static JLabel blackTonerData;
	private static JLabel cyanTonerData;
	private static JLabel magentaTonerData;
	private static JLabel yellowTonerData;
	private static JLabel drawer1Data;
	private static JPanel bPanel;
	private static JPanel cPanel;
	private static JPanel mPanel;
	private static JPanel yPanel;
	private static JPanel dPanel;
	private static JPanel mainPane;
	private static JFrame frame;
	
	private static String sysDesc;
	private static int blackToner;
	private static int cyanToner;
	private static int magentaToner;
	private static int yellowToner;
	private static int drawer1;
	private static SNMPManager client;
	private static boolean bTonerEmailOKToSend = true;
	private static boolean cTonerEmailOKToSend = true;
	private static boolean mTonerEmailOKToSend = true;
	private static boolean yTonerEmailOKToSend = true;
	private static boolean dEmailOKToSend = true;
	
	private static String creds;
	private static String credsp;
	private static String env;
	
	public PrinterStatusGUI() throws IOException {
		super();
		client.update();
	}
	public static void main(String[] args) throws IOException {
		env = System.getenv("APPDATA");
		enterNewCredentials();
		sendEmailNowBoolean();
		client = new SNMPManager(IPADDRESSOFPRINTER);
		initComponents();
		Runnable helloRunnable = new Runnable() {
		    public void run() {
		    	try {
					updateLevels(false);
					System.out.println("Updated.");
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Not updated.");
				}
		    }
		};

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(helloRunnable, 0, 10, TimeUnit.SECONDS);
	}
	
	protected static void sendEmailNowBoolean() {
		Object[] options = {"Yes", "No"};
		int sendEmailNowInt = JOptionPane.showOptionDialog(frame,"Would you like to send alert emails now?","Send email alert?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,null,options,options[1]);
		if (sendEmailNowInt == 0) setEmailOkayToSend(true);
		else setEmailOkayToSend(false);
	}
	
	private static void setEmailOkayToSend(boolean b) {
		bTonerEmailOKToSend = b;
		cTonerEmailOKToSend = b;
		mTonerEmailOKToSend = b;
		yTonerEmailOKToSend = b;
		dEmailOKToSend = b;
	}
	protected static void enterNewCredentials() throws IOException {
		boolean enterNewCreds = false;
		Object[] options = {"Yes", "No"};
		int enter = JOptionPane.showOptionDialog(frame, "Would you like to enter new credentials?", "Enter New Credentials?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
		if (enter == 0) {
			enterNewCreds = true;
		}
		
		File file = new File(env + "creds");
		String creds = "";
		if (!file.isFile() || enterNewCreds) {
			file.createNewFile();
			FileWriter fw = new FileWriter(file);
			creds = (String)JOptionPane.showInputDialog(frame,"Enter a GMail username (without \"@gmail.com\")","Enter Username",JOptionPane.PLAIN_MESSAGE);
			PrinterStatusGUI.creds = creds;
			System.out.println("Global " + PrinterStatusGUI.creds);
			if (creds == null){
				enterNewCredentials();
				fw.flush();
				fw.close();
				return;
			}
			fw.write(creds);
			fw.flush();
			fw.close();
		}
		FileReader fr = new FileReader(file);
		char[] a = new char[50];
		fr.read(a);
		fr.close();
		creds = String.valueOf(a).trim();
		PrinterStatusGUI.creds = creds;
		System.out.println("Global " + PrinterStatusGUI.creds);
		System.out.println(creds);

		String credsp = "";
		File file2 = new File(env + "credsp");
		if (!file2.isFile() || enterNewCreds) {
			file2.createNewFile();
			FileWriter fw2 = new FileWriter(file2);
			credsp = (String)JOptionPane.showInputDialog(frame,"Enter the password","Enter Password",JOptionPane.PLAIN_MESSAGE);
			PrinterStatusGUI.credsp = credsp;
			System.out.println("Global " + PrinterStatusGUI.credsp);
			if (credsp == null){
				enterNewCredentials();
				fw2.flush();
				fw2.close();
				return;
			}
			fw2.write(credsp);
			fw2.flush();
			fw2.close();
		}		
		FileReader fr2 = new FileReader(file2);
		char[] a2 = new char[50];
		fr2.read(a2);
		fr2.close();
		credsp = String.valueOf(a2).trim();
		PrinterStatusGUI.credsp = credsp;
		System.out.println("Global " + PrinterStatusGUI.credsp);
		System.out.println(credsp);
	}
	private static void initComponents() throws IOException {
		blackTonerLabel = new JLabel("Black Toner");
		cyanTonerLabel = new JLabel("Cyan Toner");
		magentaTonerLabel = new JLabel("Magenta Toner");
		yellowTonerLabel = new JLabel("Yellow Toner");
		drawer1Label = new JLabel("Paper");
		//JFrame frame = new JFrame("Printah");
		blackTonerData = new JLabel(blackToner + "");
		cyanTonerData = new JLabel(cyanToner + "");
		magentaTonerData = new JLabel(magentaToner + "");
		yellowTonerData = new JLabel(yellowToner + "");
		drawer1Data = new JLabel(drawer1 + "");
		updateLevels(true);
		frame = new JFrame(sysDesc);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,200);
		frame.setLocation(200,200);

		bPanel = new JPanel(new BorderLayout());
		cPanel = new JPanel(new BorderLayout());
		mPanel = new JPanel(new BorderLayout());
		yPanel = new JPanel(new BorderLayout());
		dPanel = new JPanel(new BorderLayout());
		
		bPanel.add(blackTonerLabel, BorderLayout.WEST);
		bPanel.add(blackTonerData, BorderLayout.EAST);
		cPanel.add(cyanTonerLabel, BorderLayout.WEST);
		cPanel.add(cyanTonerData,  BorderLayout.EAST);
		mPanel.add(magentaTonerLabel, BorderLayout.WEST);
		mPanel.add(magentaTonerData, BorderLayout.EAST);
		yPanel.add(yellowTonerLabel, BorderLayout.WEST);
		yPanel.add(yellowTonerData, BorderLayout.EAST);
		dPanel.add(drawer1Label, BorderLayout.WEST);
		dPanel.add(drawer1Data, BorderLayout.EAST);
		
		mainPane = new JPanel();
		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
		mainPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	        mainPane.add(Box.createRigidArea(new Dimension(0, 5)));
		mainPane.add(bPanel);
		mainPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        mainPane.add(Box.createRigidArea(new Dimension(0, 5)));
		mainPane.add(cPanel);
		mainPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        mainPane.add(Box.createRigidArea(new Dimension(0, 5)));
		mainPane.add(mPanel);
		mainPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        mainPane.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPane.add(yPanel);
        mainPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        mainPane.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPane.add(dPanel);
        mainPane.add(Box.createGlue());
		frame.setContentPane(mainPane);
        frame.setVisible(true);
	}
	private static void updateLevels(boolean initOrNah) throws IOException{
		client.update();
		sysDesc = client.getSysDesc();
		blackToner = Integer.parseInt(client.getBlackToner());
		cyanToner = Integer.parseInt(client.getCyanToner());
		magentaToner = Integer.parseInt(client.getMagentaToner());
		yellowToner = Integer.parseInt(client.getYellowToner());
		drawer1 = Integer.parseInt(client.getDrawer1());
		
		blackTonerData.setText(blackToner + "%");
		cyanTonerData.setText(cyanToner + "%");
		magentaTonerData.setText(magentaToner + "%");
		yellowTonerData.setText(yellowToner + "%");
		drawer1Data.setText(drawer1 + " sheets");
		
		if (!initOrNah)
			checkLevels();
	}
	protected static void checkLevels() throws IOException {
		if (blackToner <= 5 || debug){ 
			System.out.println("Black toner is low!");
			if (bTonerEmailOKToSend) {
				SendEmail email = new SendEmail(creds,credsp);
				email.sendMessage("Black toner is low! " + blackToner + "% left.", "We're going to need to replace the black toner soon.");
				bTonerEmailOKToSend = false;
			}
		}
		else {
				bTonerEmailOKToSend = true;
		}
		if (cyanToner <= 5 || debug){ 
			System.out.println("Cyan toner is low!");
			if (cTonerEmailOKToSend) {
				SendEmail email = new SendEmail(creds,credsp);
				email.sendMessage("Cyan toner is low! " + cyanToner + "% left.", "We're going to need to replace the cyan toner soon.");
				cTonerEmailOKToSend = false;
			}
		}
		else {
			cTonerEmailOKToSend = true;
		}
		if (magentaToner <= 5 || debug){ 
			System.out.println("Magenta toner is low!");
			if (mTonerEmailOKToSend) {
				SendEmail email = new SendEmail(creds,credsp);
				email.sendMessage("Magenta toner is low! " + magentaToner + "% left.", "We're going to need to replace the magenta toner soon.");
				mTonerEmailOKToSend = false;
			}
		}
		else {
			mTonerEmailOKToSend = true;
		}
		if (yellowToner <= 5 || debug){ 
			System.out.println("Yellow toner is low!");
			if (yTonerEmailOKToSend) {
				SendEmail email = new SendEmail(creds,credsp);
				email.sendMessage("Yellow toner is low! " + yellowToner + "% left.", "We're going to need to replace the yellow toner soon.");
				yTonerEmailOKToSend = false;
			}
		}
		else {
			yTonerEmailOKToSend = true;
		}
		if (drawer1 < 20 || debug){ 
			System.out.println("Paper is low!");
			if (dEmailOKToSend) {
				SendEmail email = new SendEmail(creds,credsp);
				email.sendMessage("Paper is low! " + drawer1 + " sheets left.", "We're going to need to add more paper to the drawer soon.");
				dEmailOKToSend = false;
			}
		}
		else {
			dEmailOKToSend = true;
		}
	}
}