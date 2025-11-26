package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller_radnik implements Initializable{
	
	private static String pomocnoKorisnicko;
    private static String ime;
    private static String prezime;
    public static String vratiKorisnicko(){
    	return pomocnoKorisnicko;
    }
    public static String vratiImePrezime() {
    	return ime + ", " + prezime;
    }
    
    private Connection connect = null;
    private PreparedStatement statement = null;
    private ResultSet result = null;

    @FXML
    private Button button_prijava;

    @FXML
    private TextField tf_koriscnicko;

    @FXML
    private PasswordField tf_lozinka;

    @FXML
    void povratak(ActionEvent event) {
    	Alert alert0 = new Alert(Alert.AlertType.CONFIRMATION);
	 	alert0.setTitle("ODJAVA");
	 	alert0.setContentText("Da li ste sigurni da želite da se vratite na prethodnu stranicu?");
	 	
	 	Optional <ButtonType>result = alert0.showAndWait();
	 	if(result.get()==ButtonType.OK) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setTitle("DOBRODOŠLI!");
			stage.setScene(scene);
			stage.show();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	 	}
    }

    @FXML
    void prijaviSe(ActionEvent event) {
    	//System.out.println("Entered Password: " + tf_lozinka.getText());
    	//System.out.println("Hashed Password from Database: " + retrievedHashedPassword); // Replace with your actual variable

    	if(tf_koriscnicko.getText().equals("") && tf_lozinka.getText().equals("")) {
    		Alert alert1 = new Alert(Alert.AlertType.ERROR);
    		alert1.setTitle("GREŠKA");
			alert1.setContentText("Popunite polja");
			alert1.show();
    	}
    	else if(tf_koriscnicko.getText().equals("")) {
    		Alert alert3 = new Alert(Alert.AlertType.ERROR);
    		alert3.setTitle("GREŠKA");
			alert3.setContentText("Prvo unesite korisničko ime!");
			alert3.show();
    	}
    	else if(tf_lozinka.getText().equals("")) {
    		Alert alert2 = new Alert(Alert.AlertType.ERROR);
    		alert2.setTitle("GREŠKA");
			alert2.setContentText("Unesite lozinku za: " + tf_koriscnicko.getText() + " korisničko ime");
			alert2.show();
    	}
    	
    	else {
    		boolean postojiRadnik = false;
    		boolean tacnaSifra = true;
    		//String ime="";
    		//String prezime="";
    		for(Radnik_pozorista rp : Radnik_pozorista.getLista_radnika_pozorista()) {
    			if(rp.getKorisnicko_ime().equals(tf_koriscnicko.getText())) {
    				postojiRadnik = true;
    				ime = rp.getIme();
    				prezime = rp.getPrezime();
    					if(!rp.getLozinka().equals(Radnik_pozorista.getMd5(tf_lozinka.getText())))
    						tacnaSifra=false;
    				}
    			}
    			    		
    		if(!postojiRadnik) {
    			Alert alert1 = new Alert(Alert.AlertType.WARNING);
    			alert1.setTitle("UPOZORENJE!");
				alert1.setContentText("Korisničko ime radnika " + tf_koriscnicko.getText() + " ne postoji. Probajte ponovo!");
				alert1.show();
				tf_lozinka.setText("");
				 tf_koriscnicko.setText("");
    		}
    		//else if(postojiRadnik && !tacnaSifra) {
    		else if(postojiRadnik && !tacnaSifra) {
    			tf_lozinka.setText("");
    			Alert alert1 = new Alert(Alert.AlertType.WARNING);
    			alert1.setTitle("UPOZORENJE!");
				alert1.setContentText("Neispravna lozinka za " + tf_koriscnicko.getText() +". Pokušajte ponovo!");
				alert1.show();
    		}
    		else {
    			try {
    				pomocnoKorisnicko = tf_koriscnicko.getText();
    				Parent root = FXMLLoader.load(getClass().getResource("Scena_nakonprijaveRadnika.fxml"));
    				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    				Scene scene = new Scene(root);
    				stage.setTitle("Dobrodošli radniče " + ime+ " " + prezime);
    				stage.setScene(scene);
    				stage.show();
    			}
    			catch(IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
