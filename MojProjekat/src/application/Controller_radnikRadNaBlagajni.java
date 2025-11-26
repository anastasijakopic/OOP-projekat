package application;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Controller_radnikRadNaBlagajni implements Initializable{

    @FXML
    void otkazivanje(ActionEvent event) {
    	try {
			   Konekcija.ucitajKartu();
				Parent root = FXMLLoader.load(getClass().getResource("Scena_radnikOtkazujeRez.fxml"));
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene scene = new Scene(root);
				stage.setTitle("Radniče " + Controller_radnik.vratiImePrezime() + ", izvršite otkazivanje rezervacije karte u " +  Pozoriste.dajPozoristeNaOsnovuId(Controller_nakonPrijaveRadnika.vratiPozoristeID()).getNaziv() + "!");
				stage.setScene(scene);
				stage.show();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
    }

    @FXML
    void predaja(ActionEvent event) {
    	try {
   		 Konekcija.ucitajKartu();
				Parent root = FXMLLoader.load(getClass().getResource("Scena_radnikPredajaKarte.fxml"));
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene scene = new Scene(root);
				stage.setTitle("Radniče " + Controller_radnik.vratiImePrezime() + ", izvršite predaju karte u " +  Pozoriste.dajPozoristeNaOsnovuId(Controller_nakonPrijaveRadnika.vratiPozoristeID()).getNaziv() + "!");
				stage.setScene(scene);
				stage.show();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
    }

    @FXML
    void prodaja(ActionEvent event) {
    	if(!Izvodjenje_predstave.daLiPostojePredstaveKojeSeIzvodeUNarednomPeriodu(Controller_nakonPrijaveRadnika.vratiPozoristeID())) {
    		Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
			alert4.setTitle("OBAVJEŠTENJE!");
			alert4.setContentText("U pozorištu " + Pozoriste.dajPozoristeNaOsnovuId(Controller_nakonPrijaveRadnika.vratiPozoristeID()).getNaziv() + " u narednom periodu nema izvođenja nijedne predstave!" + 
			"\nTe nije moguće prodavati karte!");
			alert4.show();
    	}
    	else {
    	try {
    		Konekcija.ucitajKartu();
			Parent root = FXMLLoader.load(getClass().getResource("Scena_radnikProdajaKarte.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setTitle("Radniče " + Controller_radnik.vratiImePrezime() + ", izvršite prodaju karte u " +  Pozoriste.dajPozoristeNaOsnovuId(Controller_nakonPrijaveRadnika.vratiPozoristeID()).getNaziv() + "!");
			stage.setScene(scene);
			stage.show();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
    	}
    }

    @FXML
    void switchToNakonPrijaveRadnika(ActionEvent event) {
    	Alert alert0 = new Alert(Alert.AlertType.CONFIRMATION);
	 	alert0.setTitle("IZLAZAK");
	 	alert0.setContentText("Da li ste sigurni da želite da se vratite na početnu stranicu?");
	 	
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
