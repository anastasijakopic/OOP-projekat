package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller_radnikKreiraPozoriste implements Initializable{
	
	private Connection connect = null;
	private PreparedStatement statement = null;

    @FXML
    private Button button_kreirajte;

    @FXML
    private TextField tf_brSjedista;

    @FXML
    private TextField tf_grad;

    @FXML
    private TextField tf_naziv;

    @FXML
    void kreirajte(ActionEvent event) {
    	if(tf_naziv.getText().equals("") && tf_grad.getText().equals("") && tf_brSjedista.getText().equals("")) {
    		Alert alert1 = new Alert(Alert.AlertType.ERROR);
			alert1.setContentText("Popunite polja");
			alert1.show();
    	}
    	else if(tf_naziv.getText().equals("") || tf_grad.getText().equals("") || tf_brSjedista.getText().equals("")) {
    		Alert alert4 = new Alert(Alert.AlertType.ERROR);
			alert4.setContentText("Sva polja moraju biti popunjena");
			alert4.show();
    	}
    	else {
	    	connect = Konekcija.getConnection();
	    	try {
	    			if(Pozoriste.daLiPostojiPozoriste(tf_naziv.getText(),tf_grad.getText(),Integer.parseInt(tf_brSjedista.getText()))) {
	    				Alert alert = new Alert(Alert.AlertType.ERROR);
	    				alert.setContentText("Pozorište" + tf_naziv.getText() + " je već zabiljezeno!\nUnesite novo pozorište!");
	    				alert.show();
	    			}
	    			else {
	    				statement = connect.prepareStatement("INSERT INTO pozoriste (naziv, grad, broj_sjedista) VALUE (?,?,?)");
	    				statement.setString(1, tf_naziv.getText());
	    				statement.setString(2, tf_grad.getText());
	    				statement.setInt(3, Integer.parseInt(tf_brSjedista.getText()));
	    				statement.executeUpdate();
	    				
	    				Konekcija.ucitajPozoriste();
	    				
	    				Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
		        		alert4.setTitle("OBAVJEŠTENJE!");
		        		alert4.setContentText("Uspješno ste dodali novo pozorište!");
		        		Optional <ButtonType>result1 = alert4.showAndWait();
		    		 	if(result1.get()==ButtonType.OK) {
		    		 		try {
		    					Parent root = FXMLLoader.load(getClass().getResource("Scena_nakonprijaveRadnika.fxml"));
		    					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		    					Scene scene = new Scene(root);
		    					stage.setTitle("Dobrodošli radniče " + Controller_radnik.vratiImePrezime());
		    					stage.setScene(scene);
		    					stage.show();
		    				}
		    				catch(IOException e) {
		    					e.printStackTrace();
		    				}
		    		 	}	    				
	    			}  			
	    		}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    		}
	    	if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	    	}
    }

    @FXML
    void switchToNakonPrijaveRadnika(ActionEvent event) {
    	Alert alert0 = new Alert(Alert.AlertType.CONFIRMATION);
	 	alert0.setTitle("IZLAZAK");
	 	alert0.setContentText("Da li ste sigurni da želite napustite stranicu?");
	 	
	 	Optional <ButtonType>result = alert0.showAndWait();
	 	if(result.get()==ButtonType.OK) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Scena_nakonprijaveRadnika.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setTitle("Dobrodošli radniče " + Controller_radnik.vratiImePrezime());
			stage.setScene(scene);
			stage.show();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	 	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}

