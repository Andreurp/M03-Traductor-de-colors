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
		
		switch (idiomaTriat) {
		case "Català":
			idioma1.setText("Castellà");
			idioma2.setText("Francès");
			idioma3.setText("Anglès");
			break;
		case "Castellà":
			idioma1.setText("Català");
			idioma2.setText("Francès");
			idioma3.setText("Anglès");
			break;
		case "Francès":
			idioma1.setText("Català");
			idioma2.setText("Castellà");
			idioma3.setText("Anglès");
			break;
		case "Anglès":
			idioma1.setText("Català");
			idioma2.setText("Castellà");
			idioma3.setText("Francès");
			break;
		}
		
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
					
					int[] posicions = new int[3];

					

						color1.setText(resultat.getString(posicions[0] + 2));
						color2.setText(resultat.getString(posicions[1] + 2));
						color3.setText(resultat.getString(posicions[2] + 2));
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
