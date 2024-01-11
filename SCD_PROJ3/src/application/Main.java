package application;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.w3c.dom.html.HTMLFormElement;
import javafx.stage.StageStyle;
import netscape.javascript.JSObject;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.swing.text.Element;



import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;
// Java class to interact with JavaScript


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
   
    	
    		
        primaryStage.setTitle("Cheque_App");
        FXMLLoader loader= new FXMLLoader(getClass().getResource("ChequeApp.fxml"));
        Parent root = loader.load();
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
    
        primaryStage.setWidth(707);
            primaryStage.setHeight(475);
        
      primaryStage.show();
    	 // Create a button
       Button button = new Button("Click Me");
//
//      
        button.setOnAction(e -> {
           
            System.out.println("Button Clicked!");
            sendDataToServlet();
            HostServices hostServices = getHostServices();  
            hostServices.showDocument("http://localhost:8081/Cheque_Server/Servlet2");
          
        });

        
//        StackPane root = new StackPane();
//        root.getChildren().add(button);
//
//      
//        Scene scene = new Scene(root, 300, 200);
//
//       
//        primaryStage.setTitle("JavaFX Button Example");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        
        
   	
    
    }

    public static void main(String[] strings) {
    	
        launch(STYLESHEET_CASPIAN);
       
        
    }
    public void sendDataToServlet() {
        try {
         
            URL url = new URL("http://localhost:8081/Cheque_Server/Servlet2");

          
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

          
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);

           
            String data = "key1=Murtaza&key2=i love this"; 
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
            e.printStackTrace();
        }
    }
    
   
}
    
    
 




