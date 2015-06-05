package DriveWriter.DriveWriter;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;




/**
 * Hello world!
 *
 */
public class App extends JFrame 
{
	private JPanel contentPane;

	
    	public static void main(String[] args) {
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					App frame = new App();
    					frame.setVisible(true);
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});
    		
    		
    	}
    	
    	String barcode = null;
    	String fName = null;
    	String lName = null;
    	String identNum = null;
    	String newline = System.getProperty("line.separator");
    	
    	
    	/**
    	 * Create the frame.
    	 */
    	public App() {			
    		
    		
    		
    		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		setBounds(100, 100, 350, 177);
    		contentPane = new JPanel();
    		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    		setContentPane(contentPane);
    		contentPane.setLayout(null);
    		
    		JButton btnCheckin = new JButton("Check-IN");
    		btnCheckin.addMouseListener(new MouseAdapter() {
    			@Override
    			public void mouseClicked(MouseEvent arg0) {
    				try {
						CheckIn();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				//JOptionPane.showConfirmDialog(null,CheckIn(),"Please Enter Barcode", JOptionPane.OK_CANCEL_OPTION);
 catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				}
    		});
    		btnCheckin.setBounds(10, 114, 103, 23);
    		contentPane.add(btnCheckin);
    		
    		JButton btnCheckout = new JButton("Check-OUT");
    		btnCheckout.addMouseListener(new MouseAdapter() {
    			@Override
    			public void mouseClicked(MouseEvent arg0){
    				CheckOut();
    			}
    			});
    		
    		btnCheckout.setBounds(221, 114, 103, 23);
    		contentPane.add(btnCheckout);
    		
    		JLabel lblDepauwUniversitySds = new JLabel("DePauw University SDS Equipment Inventory System");
    		lblDepauwUniversitySds.setBounds(20, 11, 314, 14);
    		contentPane.add(lblDepauwUniversitySds);
    	}
    	
    	public void CheckOut(){	
    		JTextField codeField = new JTextField(10);
    		codeField.setBounds(102, 36, 86, 20);
    		JTextField firstNameField = new JTextField(10);
    		firstNameField.setBounds(102, 61, 86, 20);
    		JTextField lastNameField = new JTextField(10);
    		lastNameField.setBounds(102, 86, 86, 20);
    		JTextField idNumField = new JTextField(6);
    		idNumField.setBounds(102, 111, 54, 20);
    		
    		JPanel myPanel = new JPanel();
    		myPanel.setLayout(null);
    		myPanel.setPreferredSize(new Dimension(300,150));
    		JLabel codelabel = new JLabel("Barcode: ");
    		codelabel.setBounds(18, 39, 58, 14);
    		myPanel.add(codelabel);
    		myPanel.add(codeField);
    		JLabel label = new JLabel("First Name: ");
    		label.setBounds(18, 64, 72, 14);
    		myPanel.add(label);
    		myPanel.add(firstNameField);
    		JLabel label_1 = new JLabel("Last Name: ");
    		label_1.setBounds(18, 89, 72, 14);
    		myPanel.add(label_1);
    		myPanel.add(lastNameField);
    		JLabel label_2 = new JLabel("ID Number:");
    		label_2.setBounds(18, 114, 72, 14);
    		myPanel.add(label_2);
    		myPanel.add(idNumField);
    		
    		JLabel lblPleaseFillOut = new JLabel("Please fill out all fields.");
    		lblPleaseFillOut.setBounds(18, 11, 150, 14);
    		myPanel.add(lblPleaseFillOut);
    		
    		int result = JOptionPane.showConfirmDialog(null,myPanel,"Please Enter Barcode", JOptionPane.OK_CANCEL_OPTION);
    		if(result == JOptionPane.OK_OPTION){
    			barcode = codeField.getText();
    			fName = firstNameField.getText();
    			lName = lastNameField.getText();
    			identNum = idNumField.getText();
    			
    			
    			
    			
    			
    		}
    		
    	}
    	
    	public void CheckIn() throws IOException, URISyntaxException{
    		
    		
    		
    		JTextField codeField = new JTextField(10);
    		codeField.setBounds(102, 36, 86, 20);
    		JTextField firstNameField = new JTextField(10);
    		firstNameField.setBounds(102, 61, 86, 20);
    		JTextField lastNameField = new JTextField(10);
    		lastNameField.setBounds(102, 86, 86, 20);
    		JTextField idNumField = new JTextField(6);
    		idNumField.setBounds(102, 111, 54, 20);
    		
    		JPanel myPanel = new JPanel();
    		myPanel.setLayout(null);
    		myPanel.setPreferredSize(new Dimension(300,150));
    		JLabel codelabel = new JLabel("Barcode: ");
    		codelabel.setBounds(18, 39, 58, 14);
    		myPanel.add(codelabel);
    		myPanel.add(codeField);
    		JLabel label = new JLabel("First Name: ");
    		label.setBounds(18, 64, 72, 14);
    		myPanel.add(label);
    		myPanel.add(firstNameField);
    		JLabel label_1 = new JLabel("Last Name: ");
    		label_1.setBounds(18, 89, 72, 14);
    		myPanel.add(label_1);
    		myPanel.add(lastNameField);
    		JLabel label_2 = new JLabel("ID Number:");
    		label_2.setBounds(18, 114, 72, 14);
    		myPanel.add(label_2);
    		myPanel.add(idNumField);
    		
    		JLabel lblPleaseFillOut = new JLabel("Please fill out all fields.");
    		lblPleaseFillOut.setBounds(18, 11, 150, 14);
    		myPanel.add(lblPleaseFillOut);
    		
    		int result = JOptionPane.showConfirmDialog(null,myPanel,"Please Enter Barcode", JOptionPane.OK_CANCEL_OPTION);
    		if(result == JOptionPane.OK_OPTION){
    			
    			TestAPI test = new TestAPI();
    			test.main();
    			
    			barcode = codeField.getText();
    			fName = firstNameField.getText();
    			lName = lastNameField.getText();
    			identNum = idNumField.getText();
    			barcode += ",";
    			fName += ",";
    			lName += ",";				
    			BufferedWriter writer = null;
    			
    			
    	        try {
    	            //create a temporary file
    	            String timeLog = "TestFile.txt";
    	            String homefolder = System.getProperty("user.home");
    	            File logFile = new File(homefolder,timeLog);
    	            
    	            
    	            // This will output the full path where the file will be written to...
    	            System.out.println(logFile.getCanonicalPath());

    	            writer = new BufferedWriter(new FileWriter(logFile,true));
    	            writer.write(barcode+fName+lName+identNum+newline);
    	            
    	            
    	        } finally {
    	            try {
    	                // Close the writer regardless of what happens...
    	                writer.close();
    	            } catch (Exception e) {
    	            }
    	        }
    			 
    			
    			
    			
    		}
    		
    			
    			
    		}
    		
    		
    	

    	
}
    
