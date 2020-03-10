package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;

public class Blackout {
	private Nerc nerc;
	private int customer_affected;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	private int oredifferenza;
	private int id;
	
	
	public int getOredifferenza() {
		return oredifferenza;
	}
	public void setOredifferenza(int oredifferenza) {
		this.oredifferenza = oredifferenza;
	}
	public Blackout(Nerc nerc,int id, int customer_affected, LocalDate localDate, LocalDate localDate2,int ore) {
		super();
		this.id=id;
		this.nerc = nerc;
		this.customer_affected = customer_affected;
		this.dataInizio = localDate;
		this.dataFine = localDate2;
		this.oredifferenza=ore;
	}
	public Nerc getNerc() {
		return nerc;
	}
	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}

	public int getCustomer_affected() {
		return customer_affected;
	}
	public void setCustomer_affected(int customer_affected) {
		this.customer_affected = customer_affected;
	}
	public LocalDate getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	public LocalDate getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}
	@Override
	public String toString() {
		return "Blackout "+id+" " + dataInizio+"  "+dataFine+" "+this.oredifferenza;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blackout other = (Blackout) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
}
