package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Controller_posjetilacSpisakOsoblja implements Initializable{

    @FXML
    private TableColumn<PomocnaKlasaZaTabelu, SimpleStringProperty> imeKolona;

    @FXML
    private TableColumn<PomocnaKlasaZaTabelu, SimpleStringProperty> predstavaKolona;

    @FXML
    private TableColumn<PomocnaKlasaZaTabelu, SimpleStringProperty> prezimeKolona;

    @FXML
    private TableView<PomocnaKlasaZaTabelu> tabela;

    @FXML
    private TableColumn<PomocnaKlasaZaTabelu, SimpleStringProperty> tipKolona;

    @FXML
    private TableColumn<PomocnaKlasaZaTabelu, SimpleStringProperty> zanrKolona;

    @FXML
    void switchToNakonPrijavePosjetioca(ActionEvent event) {
    	Alert alert0 = new Alert(Alert.AlertType.CONFIRMATION);
	 	alert0.setTitle("IZLAZAK");
	 	alert0.setContentText("Da li ste sigurni da želite da se vratite na početnu stranicu?");
	 	
	 	Optional <ButtonType>result = alert0.showAndWait();
	 	if(result.get()==ButtonType.OK) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Scena_nakonprijavePosjetioca.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			String imePrezime;
    		if(Controller_posjetilac.prijava) {
    			imePrezime = Controller_posjetilac.vratiImePrezimePosjetioca();
    		}
    		else {
    			imePrezime = Controller_posjetilacRegistracija.vratiImePrezimePosjetiocaRegistracija();
    		}
    		stage.setTitle("Dobrodošli posjetioče " + imePrezime);
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
		tipKolona.setCellValueFactory(new PropertyValueFactory<PomocnaKlasaZaTabelu, SimpleStringProperty>("tip"));
		imeKolona.setCellValueFactory(new PropertyValueFactory<PomocnaKlasaZaTabelu, SimpleStringProperty>("ime"));
		prezimeKolona.setCellValueFactory(new PropertyValueFactory<PomocnaKlasaZaTabelu, SimpleStringProperty>("prezime"));
		predstavaKolona.setCellValueFactory(new PropertyValueFactory<PomocnaKlasaZaTabelu, SimpleStringProperty>("predstava"));
		zanrKolona.setCellValueFactory(new PropertyValueFactory<PomocnaKlasaZaTabelu, SimpleStringProperty>("zanr"));
		
		ArrayList<Integer> kontrola = new ArrayList<>();
		ObservableList <PomocnaKlasaZaTabelu> dodajUTabelu = FXCollections.observableArrayList();
		
		for(Osoblje o : Osoblje.getLista_osoblja()) {
			
			String tip = " ";
			String ime = " ";
			String prezime = " " ;
			String predstava= "";
			String zanr = "";
		
			
			boolean osobaKontrola = false;
			
			for(Osoblje_predstave op : Osoblje_predstave.getLista_osoblja_predstave()) {
			
					if(op.getOsobolje_ID() == o.getID()) {
						osobaKontrola = true;
						if(!kontrola.contains(op.getOsobolje_ID())) {
							tip = o.getStringTip(o.getTip());
							ime = o.getIme();
							prezime = o.getPrezime();
				
							kontrola.add(op.getOsobolje_ID());
						
							predstava = Predstava.dajNazivPredstave(op.getPredstava_ID());
							zanr=Predstava.dajNazivZanra(op.getPredstava_ID());
							
							dodajUTabelu.add(new PomocnaKlasaZaTabelu(tip,ime,prezime,predstava,zanr));
							
						}
						else {
							 tip = " ";
							 ime = " ";
							 prezime = " " ;
		
							predstava = Predstava.dajNazivPredstave(op.getPredstava_ID());
							zanr=Predstava.dajNazivZanra(op.getPredstava_ID());
							
							dodajUTabelu.add(new PomocnaKlasaZaTabelu(tip,ime,prezime,predstava,zanr));
						}
					}
					
			}
			if(!osobaKontrola) {
				tip = o.getStringTip(o.getTip());
				ime = o.getIme();
				prezime = o.getPrezime();
	
				predstava ="";
				zanr ="";
				dodajUTabelu.add(new PomocnaKlasaZaTabelu(tip,ime,prezime,predstava,zanr));
			}			
		}
		tabela.setItems(dodajUTabelu);
	}
}
