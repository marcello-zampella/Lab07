package it.polito.tdp.poweroutages;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Blackout;
import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PowerOutagesController {

    @FXML
    private TextArea txtOutput;

    @FXML
    private ComboBox<Nerc> nercMenu;

    @FXML
    private TextField txtYears;

    @FXML
    private TextField txtMaxHours;

	private Model model;

    @FXML
    void doAnalysis(ActionEvent event) {
    	this.txtOutput.clear();
    	List<Blackout> soluzioni= model.trovaSoluzioni(Integer.parseInt(this.txtYears.getText()),Integer.parseInt(this.txtMaxHours.getText()),this.nercMenu.getValue());
    	this.txtOutput.appendText("IL NUMERO DI PERSONE COINVOLTE E': "+personecoinvolte(soluzioni)+"\n");
    	this.txtOutput.appendText("IL NUMERO DI ORE CONSIDERATO E' "+contegioOre(soluzioni) +"\n");
    	String s="";
    	for(Blackout b:soluzioni) {
    		s+=b.toString()+"\n";
    	}
    	this.txtOutput.appendText(s);
    }
    
	private int personecoinvolte(List<Blackout> parziale) {
		int temp=0;
		for(Blackout b:parziale) {
			temp+=b.getCustomer_affected();
		}
		return temp;
	}

	private int contegioOre(List<Blackout> soluzioni) {
		int ore=0;
		for(Blackout b:soluzioni) {
			ore+=b.getOredifferenza();
		}
		return ore;
	}

	public void setModel(Model model) {
		this.model=model;
		this.popolaMenu();
	}

	private void popolaMenu() {
		this.nercMenu.getItems().setAll(model.getNercList());
		
	}

}
