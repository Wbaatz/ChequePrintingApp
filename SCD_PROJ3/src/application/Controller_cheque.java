package application;

import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import PrintingWork.EnvelopePrinting;
import PrintingWork.TestPrinting01;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.CharacterStringConverter;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.application.HostServices;
import javafx.application.Application;



public class Controller_cheque {
	
    @FXML
    private Label change_word_length;
	
    @FXML
    private TextArea GiveComment_field;
	
    @FXML
    private Button BackFromFeed;
	
	  Application App;
	   @FXML
	    private Button Go_Feedback_btn;
	   @FXML
	    private TextField State_name;

	    @FXML
	    private Button Submit_feedback_btn;

	    @FXML
	    private AnchorPane Feedback_Form;
	
	
	//......................
	@FXML
	Button Print_to_Excelbtn;
	//........................................................
	@FXML
	Button back_to_Cheque;

	@FXML
	TableView<cheque_H> check_history_table;

	@FXML
	TableColumn<cheque_H, String> check_history_dateCol;
	@FXML
	TableColumn<cheque_H, String> check_history_bankCol;
	@FXML
	TableColumn<cheque_H, String> check_history_payeeCol;

	@FXML
	TableColumn<cheque_H, String> check_history_amountCol;

	@FXML
	TableColumn<cheque_H, String> check_history_wordingsCol;

	@FXML
	TableColumn<cheque_H, String> check_history_typesCol;

	@FXML
	Button Create_btn;
	@FXML
	Button AddPayee;
	@FXML
	Button Add_payee_btn;
	@FXML
	Button CheckHistoryBtn;

	@FXML
	Button SelectAddressBtn;
	@FXML
	Button PrintChequebtn;

	@FXML
	Button PrintEnvelopebtn;

	@FXML
	Button SelectEnvelopeBtn;

	@FXML
	AnchorPane ChequeHistory;

	@FXML
	AnchorPane EnvelopeName;

	@FXML
	AnchorPane SelectAddress;

	@FXML
	AnchorPane Home;
	@FXML
	AnchorPane ChequeReady;

	@FXML
	AnchorPane PrintEnvelope;

	@FXML
	AnchorPane Payee_page;

	// .............................

	@FXML
	ChoiceBox<String> bankName;
	@FXML
	ChoiceBox<String> types;

	@FXML
	DatePicker datePicker;
	@FXML
	TextField WordingTextField;

	@FXML
	TextField Amount_textfield;

	@FXML
	Button Print;

	Stage viewStage;
	Stage PayeeStage;

	@FXML
	TextField payee_textfield;
	/// .......................
	@FXML
	TextField txtEnvelope;
	@FXML
	TextField txtAddress;
	@FXML
	Button printOutEnvelopebtn;

	@FXML
	Button BackToCheque;

	@FXML
	TextField envelopetextSelect;

	@FXML
	Button EnvelopeSelectedBtn;

	@FXML
	Button EnvelopeAddedBtn;

	@FXML
	TableView<String> EnvelopeTable;

	@FXML
	TableColumn<String, String> Envelope_Col_Name;

	// ...........................
	@FXML
	TextField AddresstextSelect;

	@FXML
	Button AddressSelectedBtn;

	@FXML
	Button AddressAddBtn;

	@FXML
	TableView<String> AddressTable;

	@FXML
	TableColumn<String, String> Address_Col_Address;

	// ..............................
	ObservableList<cheque_H> list ;
	private ObservableList<String> P_names;

	private ObservableList<String> H_payee;
	private ObservableList<String> H_date;
	private ObservableList<String> H_bank;
	private ObservableList<String> H_amount;
	private ObservableList<String> H_wordings;
	private ObservableList<String> H_types;

	private ObservableList<String> E_names;
	private ObservableList<String> A_Address;
	int CountQtr = 0;
	int CountQtr1 = 0;
	@FXML
	TextField payee_tf_name;
	@FXML
	Button Select_name;
	@FXML
	Button closepayeform;

	@FXML
	TableView<String> payee_table;
	@FXML
	TableColumn<String, String> payee_column;

	
	
	@FXML
	public void initialize() throws ClassNotFoundException, SQLException {
//		
//		 GiveComment_field.addActionListener(new ActionListener() {           
//			@Override
//			public void actionPerformed(java.awt.event.ActionEvent e) {
//				 String text =  GiveComment_field.getText();
//	                int charCount = text.length();
//	                change_word_length.setText(String.valueOf(charCount));
//				
//			}
//        });

		
		 GiveComment_field.textProperty().addListener((observable, oldValue, newValue) -> {
	            int charCount = GiveComment_field.getText().length();
	            change_word_length.setText(String.valueOf(charCount)+"/100");
	        });
		
		
		
		
		
		
		
		
		State_name.addEventFilter(KeyEvent.KEY_TYPED, event -> {
	            if (!event.getCharacter().matches("[a-zA-Z]")) {
	                event.consume(); // Consume the event to prevent the character from being typed
	            }
	        });
		payee_tf_name.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[a-zA-Z]")) {
                event.consume(); // Consume the event to prevent the character from being typed
            }
        });
		AddresstextSelect.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[a-zA-Z]")) {
                event.consume(); // Consume the event to prevent the character from being typed
            }
        });
		envelopetextSelect.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[a-zA-Z]")) {
                event.consume(); // Consume the event to prevent the character from being typed
            }
        });
		WordingTextField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[a-zA-Z ]")) {
                event.consume(); // Consume the event to prevent the character from being typed
            }
        });
		
		
		
		
		
		
		
		
		datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0);
            }
        });
		 
		payee_textfield.setEditable(false);
		txtEnvelope.setEditable(false);
		txtAddress.setEditable(false);
		 Object[][] bookData ;
	                
	     
		
		
		
		
		check_history_amountCol.setCellValueFactory(new PropertyValueFactory<cheque_H,String>("H_amount"));
		check_history_bankCol.setCellValueFactory(new PropertyValueFactory<cheque_H,String>("H_bank"));
		check_history_dateCol.setCellValueFactory(new PropertyValueFactory<cheque_H,String>("H_date"));
		check_history_typesCol.setCellValueFactory(new PropertyValueFactory<cheque_H,String>("H_types"));
		check_history_wordingsCol.setCellValueFactory(new PropertyValueFactory<cheque_H,String>("H_wordings"));
		check_history_payeeCol.setCellValueFactory(new PropertyValueFactory<cheque_H,String>("H_payee"));

		System.out.println("hello me");
		list=FXCollections.observableArrayList();
		P_names = FXCollections.observableArrayList();
		E_names = FXCollections.observableArrayList();
		A_Address = FXCollections.observableArrayList();
		bankName.getItems().addAll("Bank Alhabib", "bank Alfalah", "bank Islamic");
		types.getItems().addAll("Cross A/C Payee+Or Bearer", "Cross A/C Payee +Not Negotiable+Or Bearer",
				"Cross A/C Payee", "Cross only", "No CROSSING");

		
		//sql work starts here to initialize.
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ChequeData","root","0590");
	      
	      Statement stat = con.createStatement();
	      String sql = "SELECT * FROM Payee"; // Assuming 'users' is your table name
	      ResultSet resultSet = stat.executeQuery(sql);
	      //resultSet.next() means saying whether another row is available or not.
	      while (resultSet.next()) {
	          // Retrieve data by column name or index
	          String retrievedUsername = resultSet.getString("Names");
	         
	      	P_names.add(retrievedUsername);
			payee_table.setItems(P_names);
			payee_column.setCellValueFactory(
					cellData -> javafx.beans.binding.Bindings.createObjectBinding(() -> cellData.getValue()));
	          // Do something with the retrieved data (print it here)
	          System.out.println("Username: "+ retrievedUsername);
	      }
	      //now sql for reciept
	      String sql2 = "SELECT * FROM Envelope";
	       resultSet = stat.executeQuery(sql2);
	       while (resultSet.next()) {
		          // Retrieve data by column name or index
		          String retrievedname = resultSet.getString("Names");
		          String retrievedAddress = resultSet.getString("Address");
		          E_names.add(retrievedname);
		          A_Address.add(retrievedAddress);
				EnvelopeTable.setItems(E_names);
				AddressTable.setItems(A_Address);
				Address_Col_Address.setCellValueFactory(
						cellData -> javafx.beans.binding.Bindings.createObjectBinding(() -> cellData.getValue()));
				Envelope_Col_Name.setCellValueFactory(
						cellData -> javafx.beans.binding.Bindings.createObjectBinding(() -> cellData.getValue()));
//		          System.out.println("Username: "+ retrievedUsername);
		      }
	      //now sql for cheque History
	       String sql3 = "SELECT * FROM ChequeHistory";
	       resultSet = stat.executeQuery(sql3);
	       while (resultSet.next()) {
	    	   String name = resultSet.getString("bankName");
	    	   String Types= resultSet.getString("types");
	    	   String Datepicker= resultSet.getString("datepicker");
	    	   String Payee= resultSet.getString("payee");
	    	   String wording= resultSet.getString("Wording");
	    	   String amount= resultSet.getString("Amount");
	    	   list.add(new cheque_H(Payee, Datepicker, name, amount, wording, Types));
				check_history_table.setItems(list);
	       }
	      
	      
	       
	     
	       
	       
	      
	      
	      con.close();
	      
	      //..........................................
	

		
	}

	public void GoFunctions(ActionEvent e) {
		if (e.getSource().equals(Create_btn)) {
			Home.setVisible(false);
			ChequeReady.setVisible(true);
		}
		if (e.getSource().equals(AddPayee)) {
			ChequeReady.setVisible(false);
			Payee_page.setVisible(true);
		}

	}

	public void AddToTable(ActionEvent e) {
		if (e.getSource().equals(Add_payee_btn)) {

			if(payee_tf_name.getText()=="" ) {
				Alert A= new Alert(Alert.AlertType.WARNING);
				A.setContentText("Enter the Name,must not be null");
				A.showAndWait().filter(response ->response==ButtonType.OK);
				System.out.println("alert!");
			}
			else if(CheckInteger(payee_tf_name.getText())==true) {
				Alert A= new Alert(Alert.AlertType.WARNING);
				A.setContentText("No Numeric Allowed,Enter Alphabets only!");
				A.showAndWait().filter(response ->response==ButtonType.OK);
				System.out.println("alert!");
			}
			else {
				P_names.add(payee_tf_name.getText());
				payee_table.setItems(P_names);

				payee_column.setCellValueFactory(
						cellData -> javafx.beans.binding.Bindings.createObjectBinding(() -> cellData.getValue()));
			}
			

		}
		if (e.getSource().equals(EnvelopeAddedBtn)) {
			if(envelopetextSelect.getText()=="" ) {
				Alert A= new Alert(Alert.AlertType.WARNING);
				A.setContentText("Enter the Name,must not be null");
				A.showAndWait().filter(response ->response==ButtonType.OK);
				System.out.println("alert!");
			}
			else if(CheckInteger(envelopetextSelect.getText())==true) {
				Alert A= new Alert(Alert.AlertType.WARNING);
				A.setContentText("No Numeric Allowed,Enter Alphabets only!");
				A.showAndWait().filter(response ->response==ButtonType.OK);
				System.out.println("alert!");
			}
			else {
				System.out.println("btnenvelopeclikced");
				E_names.add(envelopetextSelect.getText());
				EnvelopeTable.setItems(E_names);
				Envelope_Col_Name.setCellValueFactory(
						cellData -> javafx.beans.binding.Bindings.createObjectBinding(() -> cellData.getValue()));
			}
	

		}
		if (e.getSource().equals(EnvelopeSelectedBtn)) {
			
			if(EnvelopeTable.getSelectionModel().getSelectedItem()==null) {
				Alert A= new Alert(Alert.AlertType.WARNING);
				A.setContentText("Select the names to proceed");
				A.showAndWait().filter(response ->response==ButtonType.OK);
				System.out.println("alert!");
			}
			else {
				
			
			String name = EnvelopeTable.getSelectionModel().getSelectedItem();
			EnvelopeName.setVisible(false);
			txtEnvelope.setText(name);
			PrintEnvelope.setVisible(true);
			}
		}
		if (e.getSource().equals(AddressAddBtn)) {

			if(AddresstextSelect.getText()=="" ) {
				Alert A= new Alert(Alert.AlertType.WARNING);
				A.setContentText("Enter the Name,must not be null");
				A.showAndWait().filter(response ->response==ButtonType.OK);
				System.out.println("alert!");
			}
			else if(CheckInteger(AddresstextSelect.getText())==true) {
				Alert A= new Alert(Alert.AlertType.WARNING);
				A.setContentText("No Numeric Allowed,Enter Alphabets only!");
				A.showAndWait().filter(response ->response==ButtonType.OK);
				System.out.println("alert!");
			}
			else {
				
			
			
			
			
			System.out.println("btnenvelopeclikced");
			A_Address.add(AddresstextSelect.getText());
			AddressTable.setItems(A_Address);
			
			Address_Col_Address.setCellValueFactory(
					cellData -> javafx.beans.binding.Bindings.createObjectBinding(() -> cellData.getValue()));
			}
		}
		if (e.getSource().equals(AddressSelectedBtn)) {
			
			
			if(AddressTable.getSelectionModel().getSelectedItem()==null) {
				Alert A= new Alert(Alert.AlertType.WARNING);
				A.setContentText("Select the names to proceed");
				A.showAndWait().filter(response ->response==ButtonType.OK);
				System.out.println("alert!");
			}
			else {
				
			String name = AddressTable.getSelectionModel().getSelectedItem();
			txtAddress.setText(name);
			PrintEnvelope.setVisible(true);
			SelectAddress.setVisible(false);
			}
		}
		if (e.getSource().equals(Select_name)) {
			if(payee_table.getSelectionModel().getSelectedItem()==null) {
				Alert A= new Alert(Alert.AlertType.WARNING);
				A.setContentText("Select the names to proceed");
				A.showAndWait().filter(response ->response==ButtonType.OK);
				System.out.println("alert!");
			}
			else {
				String name = payee_table.getSelectionModel().getSelectedItem();
				Payee_page.setVisible(false);
				payee_textfield.setText(name);
				ChequeReady.setVisible(true);
			}
			

		}
		if (e.getSource().equals(closepayeform)) {
			Payee_page.setVisible(false);
			ChequeReady.setVisible(true);
		}
	}

	public void CheckHistory(ActionEvent e) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {
		if (e.getSource().equals(CheckHistoryBtn)) {
			ChequeHistory.setVisible(true);
			ChequeReady.setVisible(false);
		}
		if (e.getSource().equals(back_to_Cheque)) {
			ChequeHistory.setVisible(false);
			ChequeReady.setVisible(true);
		}
		if(e.getSource().equals(Print_to_Excelbtn)) {
			XSSFWorkbook wb= new XSSFWorkbook(new FileInputStream("C:\\Users\\khanz_yni3mzj\\OneDrive\\Documents\\PrintedExcel\\MyExcel.xlsx"));
			XSSFSheet sh=wb.getSheet("Sheet1");
			  Class.forName("com.mysql.cj.jdbc.Driver");
			  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ChequeData","root","0590");
		      
		      Statement stat = con.createStatement();
	       
	       String sql3 = "SELECT * FROM ChequeHistory";
	       ResultSet  resultSet = stat.executeQuery(sql3);
	       int rowCount = 0;
	       Cell cell;
	       Row row=sh.createRow(rowCount);
	       cell=row.createCell(1);
	       cell.setCellValue("BankName");
	       cell=row.createCell(2);
	       cell.setCellValue("Types");
	       cell=row.createCell(3);
	       cell.setCellValue("DatePicker");
	       cell=row.createCell(4);
	       cell.setCellValue("Payee");
	       cell=row.createCell(5);
	       cell.setCellValue("Wording");
	       cell=row.createCell(6);
	       cell.setCellValue("Amount");
	       while (resultSet.next()) {
	    	    row = sh.createRow(++rowCount);
	    	   int columnCount = 0;
	    	   String name = resultSet.getString("bankName");
	    	   cell = row.createCell(++columnCount);
	    	   cell.setCellValue((String) name);
	    	   String Types= resultSet.getString("types");
	    	   cell = row.createCell(++columnCount);
	    	   cell.setCellValue((String) Types);
	    	   String Datepicker= resultSet.getString("datepicker");
	    	   cell = row.createCell(++columnCount);
	    	   cell.setCellValue((String) Datepicker);
	    	   String Payee= resultSet.getString("payee");
	    	   cell = row.createCell(++columnCount);
	    	   cell.setCellValue((String) Payee);
	    	   String wording= resultSet.getString("Wording");
	    	   cell = row.createCell(++columnCount);
	    	   cell.setCellValue((String) wording);
	    	   String amount= resultSet.getString("Amount");
	    	   cell = row.createCell(++columnCount);
	    	   cell.setCellValue((String) amount);
	    	  
	       }
	       con.close();
	       System.out.println("Excel file Successfully Created and Saved");
			wb.write(new FileOutputStream("C:\\Users\\khanz_yni3mzj\\OneDrive\\Documents\\PrintedExcel\\MyExcel.xlsx"));
			
	}
	}
	
	
	   @FXML
	    void FeedbackMethod(ActionEvent e) {
            if(e.getSource().equals(Go_Feedback_btn)) {
            	Feedback_Form.setVisible(true);
            	ChequeReady.setVisible(false);
            }
            if(e.getSource().equals(Submit_feedback_btn)) {
            	
         
            	
            	
            	if(State_name.getText()=="" || GiveComment_field.getText()=="") {
    				Alert A= new Alert(Alert.AlertType.WARNING);
    				A.setContentText("Enter all the info to fill the feedback form");
    				A.showAndWait().filter(response ->response==ButtonType.OK);
    				System.out.println("alert!");
    			}
            	   else  if(GiveComment_field.getText().length()>100) {
                   	Alert A= new Alert(Alert.AlertType.WARNING);
       				A.setContentText("the character limit exceeds 100 !");
       				A.showAndWait().filter(response ->response==ButtonType.OK);
       				System.out.println("alert!");
                   }
            	else if(CheckInteger(State_name.getText())==true) {
    				Alert A= new Alert(Alert.AlertType.WARNING);
    				A.setContentText("No Numeric Allowed,Enter AlphaNumeric only!");
    				A.showAndWait().filter(response ->response==ButtonType.OK);
    				System.out.println("alert!");
    			}else {
    				 System.out.println("Button Clicked!");
    	                sendDataToServlet(State_name.getText(),GiveComment_field.getText());
//    	                HostServices hostServices =  App.getHostServices();  
//    	                hostServices.showDocument("http://localhost:8081/Cheque_Server/Servlet2");
    			}
            	 
              
               
               
            }
            if(e.getSource().equals(BackFromFeed)) {
            	Feedback_Form.setVisible(false);
            	ChequeReady.setVisible(true);
            }
	    }
	
	
	
	   public void sendDataToServlet(String Name,String Comment) {
	        try {
	         
	            URL url = new URL("http://localhost:8081/Cheque_Server/Servlet2");

	          
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	          
	            connection.setRequestMethod("GET");
	            connection.setDoOutput(true);

	           
//	            String data = "key1=Murtaza&key2=i love this"; 
	            String data = "key1="+Name+"&key2="+Comment;
	            byte[] postData = data.getBytes(StandardCharsets.UTF_8);

	           
	            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	            connection.setRequestProperty("Content-Length", String.valueOf(postData.length));

	          
	            try (OutputStream os = connection.getOutputStream()) {
	                os.write(postData);
	            }

	          
	            int responseCode = connection.getResponseCode();
	            System.out.println("Response Code: " + responseCode);

	            // Close the connection
	            connection.disconnect();

	        } catch (Exception e) {
	        	Alert A= new Alert(Alert.AlertType.WARNING);
				A.setContentText("live the Server to proceed !");
				A.showAndWait().filter(response ->response==ButtonType.OK);
				System.out.println("alert!");
	        }
	    }
	
	public boolean CheckInteger(String I) {
		System.out.println(I);
try {

    int number = Integer.parseInt(I);
    System.out.println("yes its an integer");
  return true;
} catch (NumberFormatException e) {
	 System.out.println("its not an integer");
	return false;
}
		
	}
	
	public void GoPrinting(ActionEvent e) throws ClassNotFoundException, SQLException {
		if (e.getSource().equals(PrintEnvelopebtn)) {
			ChequeReady.setVisible(false);
			PrintEnvelope.setVisible(true);
		}
		if (e.getSource().equals(SelectEnvelopeBtn)) {
			EnvelopeName.setVisible(true);
			PrintEnvelope.setVisible(false);
		}
		if (e.getSource().equals(SelectAddressBtn)) {
			PrintEnvelope.setVisible(false);
			SelectAddress.setVisible(true);
		}
		if (e.getSource().equals(printOutEnvelopebtn)) {
			if(txtEnvelope.getText()=="" || txtAddress.getText()=="") {
				Alert A= new Alert(Alert.AlertType.WARNING);
				A.setContentText("Enter all the info to print the Envelope!");
				A.showAndWait().filter(response ->response==ButtonType.OK);
				System.out.println("alert!");
			}
			else {
				System.out.println("Envelope Name:" + txtEnvelope.getText());
				System.out.println("Address:" + txtAddress.getText());
				EnvelopePrinting testPrinting = new EnvelopePrinting(txtEnvelope.getText(),txtAddress.getText());
	               
	                testPrinting.printCheque();
			}
		
		}
		if (e.getSource().equals(BackToCheque)) {
			PrintEnvelope.setVisible(false);
			ChequeReady.setVisible(true);

		}
		if (e.getSource().equals(PrintChequebtn)) {
			EnglishNumberToWords checkWord = new EnglishNumberToWords();
			if(bankName.getValue()==null ||types.getValue()==null ||datePicker.getValue()==null ||payee_textfield.getText()==""||WordingTextField.getText()==""||Amount_textfield.getText()==""||CheckInteger(Amount_textfield.getText())==false||CheckInteger(WordingTextField.getText())==true||(checkWord.convert(Integer.parseInt(Amount_textfield.getText())).equals(WordingTextField.getText()))==false) {
				Alert A= new Alert(Alert.AlertType.WARNING);
//				int n=Integer.parseInt(Amount_textfield.getText());
//				String check=checkWord.convert(n);
				if (bankName.getValue()==null ||types.getValue()==null ||datePicker.getValue()==null ||payee_textfield.getText()==""||WordingTextField.getText()==""||Amount_textfield.getText()==""){
					A.setContentText("Enter all the info to print the cheque!");
					A.showAndWait().filter(response ->response==ButtonType.OK);
					System.out.println("alert!");
				}
				else if(CheckInteger(Amount_textfield.getText())==false) {
					A.setContentText("Enter Numeric only in Amount!");
					A.showAndWait().filter(response ->response==ButtonType.OK);
					System.out.println("alert!");
				}
				else if(CheckInteger(WordingTextField.getText())==true) {
					A.setContentText("Enter Alphabets  only in Wording field!");
					A.showAndWait().filter(response ->response==ButtonType.OK);
					System.out.println("alert!");
				}
				else if((checkWord.convert(Integer.parseInt(Amount_textfield.getText())).equals(WordingTextField.getText()+" "))==false) {
					A.setContentText("the amount field words does not match.");
					System.out.println(checkWord.convert(Integer.parseInt(Amount_textfield.getText())));
					A.showAndWait().filter(response ->response==ButtonType.OK);
					System.out.println("alert!");
				
				}
				
				
				
			}
			else {
				System.out.println(bankName.getValue());
				System.out.println(types.getValue());
				System.out.println(datePicker.getValue().toString());
				System.out.println(payee_textfield.getText());
				System.out.println(WordingTextField.getText());
				System.out.println(Amount_textfield.getText());
				
				
				//sql work here for cheque history.
				 System.out.println("go db!");
					
					Class.forName("com.mysql.cj.jdbc.Driver");
				
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ChequeData","root","0590");
					 Statement stat=con.createStatement();
					  String sql = "INSERT INTO ChequeHistory (bankName, types,datepicker,payee,Wording,Amount) VALUES (?, ?,?,?,?,?)";
				      PreparedStatement preparedStatement = con.prepareStatement(sql);
				      preparedStatement.setString(1, bankName.getValue());
				      preparedStatement.setString(2, types.getValue());
				      preparedStatement.setString(3, datePicker.getValue().toString());
				      preparedStatement.setString(4, payee_textfield.getText());
				      preparedStatement.setString(5, WordingTextField.getText());
				      preparedStatement.setString(6, Amount_textfield.getText());
				      
				      // Execute the query
				      int rowsAffected = preparedStatement.executeUpdate();
				      if (rowsAffected > 0) {
				          System.out.println("Saved successfully!");
				      } else {
				          System.out.println("Failed to save data.");
				      }
				
				
				
				
				
				//..............
				
				
				list.add(new cheque_H(payee_textfield.getText(), datePicker.getValue().toString(), bankName.getValue(), Amount_textfield.getText(), WordingTextField.getText(), types.getValue()));
				check_history_table.setItems(list);
				
				  
                TestPrinting01 testPrinting = new TestPrinting01(payee_textfield.getText(),datePicker.getValue().toString(),Amount_textfield.getText(),WordingTextField.getText());
               
                testPrinting.printCheque();
			}
			
			
		}

	}

	
	
	
}

