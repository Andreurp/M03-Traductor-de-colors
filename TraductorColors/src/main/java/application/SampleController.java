package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

import javafx.scene.input.MouseEvent;

public class SampleController implements Initializable{
	@FXML
	private ComboBox<String> idioma;
	@FXML
	private TextField nomColor;
	@FXML
	private Button btnCercar;
	@FXML
	private Label idioma1;
	@FXML
	private Label color1;
	@FXML
	private Label idioma2;
	@FXML
	private Label color2;
	@FXML
	private Label idioma3;
	@FXML
	private Label color3;


	public void initialize(URL arg0, ResourceBundle arg1) {
		idioma.getItems().addAll(
				"Català",
				"Castellà",
				"Francès",
				"Anglès"
				);
	}
	
	// Event Listener on Button[#btnCercar].onMouseClicked
	@FXML
	public void traduirColor(MouseEvent event) {
		
		String idiomaTriat=idioma.getValue();
		String nom=nomColor.getText();
		
		if(idiomaTriat.equals("Català")){
			idiomaTriat="nom";
		}
		
		try {
			Class.forName("com.mysql.jbc.Driver");
			Connection con = null;
			
			try {
				con = DriverManager.getConnection("jdbc:mysql://192.168.4.1/traductor", "foot", "ball");
				
				if(con!=null){
					Statement consulta = con.createStatement();
					ResultSet resultat = consulta.executeQuery("SELECT * FROM colors WHERE " + idiomaTriat + " = '" + nom + "'");
					
					
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
