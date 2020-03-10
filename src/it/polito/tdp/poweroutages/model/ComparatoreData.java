package it.polito.tdp.poweroutages.model;

import java.util.Comparator;


public class ComparatoreData implements Comparator {

	public int compare (Object o1, Object o2) {
		Blackout a1=(Blackout) o1;
		Blackout a2 =(Blackout) o2;
		return a1.getDataInizio().compareTo(a2.getDataInizio());
		
	}

}
