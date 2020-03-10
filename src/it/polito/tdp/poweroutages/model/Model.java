package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.model.*;

import it.polito.tdp.poweroutages.db.BlackoutDAO;
import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	private int max_ore;
	private int max_anno;
	private int bestPersoneCoinvolte;
	private ArrayList<Blackout> best;
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}


	public ArrayList<Blackout> trovaSoluzioni(int max_anno, int max_ore, Nerc nerc) {
		this.max_anno=max_anno;
		this.max_ore=max_ore;
		this.bestPersoneCoinvolte=0;
		BlackoutDAO bdao=new BlackoutDAO();
		ArrayList<Blackout> blackouts =new ArrayList<Blackout>(bdao.getBlackout(nerc));
		ArrayList<Blackout> temp=new ArrayList<Blackout>();
		for(Blackout b:blackouts) { //tolgo tutte quelle istanze che hanno un valore di ore maggiore del massimo consentito
			if(b.getOredifferenza()>max_ore) {
				temp.add(b);
			}
		}
		blackouts.removeAll(temp);
		ArrayList<Blackout> bvuoti =new ArrayList<Blackout>(bdao.getBlackoutVuoti(nerc)); //tolgo tutti gli 0 perchè in ogni caso fanno parte della soluzione ottima
		blackouts.removeAll(bvuoti);
		LinkedList<Blackout> parziale= new LinkedList<Blackout>();
		espandi(parziale,0,blackouts);
		best.addAll(bvuoti); //aggiungo gli 0 tolti prima
		return best;
	}

	private void espandi(LinkedList<Blackout> parziale, int livello, ArrayList<Blackout> blackouts) {
		if(parziale.size()>2&& totAnno(parziale)>max_anno) {
			return;
		}
		if(totOre(parziale)>max_ore) {
			return;
		}
		// condizione di fine
		if(totOre(parziale)<=max_ore) {
			int prova=personecoinvolte(parziale);
			
			if(personecoinvolte(parziale)>bestPersoneCoinvolte) {
				bestPersoneCoinvolte=prova;
				best=new ArrayList<Blackout>(parziale);
				
			}
		}
		
		for(int i=0;i<blackouts.size();i++) {
			Blackout b=blackouts.get(i);
			
		//	if(livello==0 ) {

		//		if(!blackouts.get(0).equals(b))
		//			blackouts.remove(0);
		//	}	
			if(!parziale.contains(b)) {
				parziale.add(b);
				espandi(parziale,livello+1,blackouts);
				parziale.removeLast();
			}
		}
		
	}



	private int personecoinvolte(LinkedList<Blackout> parziale) {
		int temp=0;
		for(Blackout b:parziale) {
			temp+=b.getCustomer_affected();
		}
		return temp;
	}

	private int totAnno(LinkedList<Blackout> parziale) {
		LinkedList<Blackout> ordinato= new LinkedList<Blackout>(parziale);
		Collections.sort(ordinato,new ComparatoreData());
		return ordinato.getLast().getDataFine().getYear()-ordinato.getFirst().getDataInizio().getYear();
	}

	private int totOre(LinkedList<Blackout> parziale) {
		int temp=0;
		for(Blackout b:parziale) {
			temp+=b.getOredifferenza();
		}
		return temp;
	}


	
	

}
