package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

//pocetni prozor

public class ControllerSample {
	
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Button dugme1;

    @FXML
    private Button dugme2;

    @FXML
    private Label naslov1;

    @FXML
    void odabranPosjetilac(ActionEvent event) {
    	try {
			root = FXMLLoader.load(getClass().getResource("Scena_posjetilac.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("Dobrodošli posjetioče, ukoliko imate nalog, prijavite se!");
			stage.setScene(scene);
			stage.show();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void odabranRadnik(ActionEvent event) {
    	try {
			root = FXMLLoader.load(getClass().getResource("Scena_radnik.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("Dobrodošli radniče, prijavite se");
			stage.setScene(scene);
			stage.show();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
    }

}

