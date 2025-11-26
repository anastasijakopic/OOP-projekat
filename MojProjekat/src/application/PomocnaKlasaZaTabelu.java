package application;

import javafx.beans.property.SimpleStringProperty;

public class PomocnaKlasaZaTabelu {
	
		//Scena_posjetilacSpisakOSoblja.fxml
		private SimpleStringProperty tip,ime,prezime,predstava,zanr;
		
		public PomocnaKlasaZaTabelu(String tip,String ime, String prezime, String predstava, String zanr) {
			this.tip = new SimpleStringProperty(tip);
			this.ime = new SimpleStringProperty(ime);
			this.prezime = new SimpleStringProperty(prezime);
			this.predstava = new SimpleStringProperty(predstava);
			this.zanr = new SimpleStringProperty(zanr);
		}

		public String getTip() {
			return tip.get();
		}

		public String getIme() {
			return ime.get();
		}

		public String getPrezime() {
			return prezime.get();
		}

		public String getZanr() {
			return zanr.get();
		}

		public String getPredstava() {
			return predstava.get();
		}
}
