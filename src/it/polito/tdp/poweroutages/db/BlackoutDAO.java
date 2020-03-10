package it.polito.tdp.poweroutages.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.time.*;

import it.polito.tdp.poweroutages.model.Blackout;
import it.polito.tdp.poweroutages.model.Nerc;

public class BlackoutDAO {	
	public List<Blackout> getBlackout(Nerc nerc) {

	String sql = "SELECT id, customers_affected, date_event_finished AS DATAFINE,date_event_began AS DATAINIZIO, TIMESTAMPDIFF(hour,date_event_began,date_event_finished) AS DIFFERENZAORA\r\n" + 
			"FROM poweroutages p\r\n" + 
			"WHERE nerc_id=?";
	List<Blackout> blackouts = new ArrayList<>();

	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, nerc.getId());
		ResultSet res = st.executeQuery();

		while (res.next()) {
			Blackout b= new Blackout(nerc,res.getInt("id"),res.getInt("customers_affected"),res.getDate("dataInizio").toLocalDate(),res.getDate("dataFine").toLocalDate(), res.getInt("DIFFERENZAORA"));
			blackouts.add(b);
		}

		conn.close();

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}

	return blackouts;
}

	public Collection<? extends Blackout> getBlackoutVuoti(Nerc nerc) {

		String sql = "SELECT id, customers_affected, date_event_finished AS DATAFINE,date_event_began AS DATAINIZIO, TIMESTAMPDIFF(hour,date_event_began,date_event_finished) AS DIFFERENZAORA\r\n" + 
				"FROM poweroutages p\r\n" + 
				"WHERE nerc_id=? AND TIMESTAMPDIFF(hour,date_event_began,date_event_finished)=0";
		List<Blackout> blackouts = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nerc.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Blackout b= new Blackout(nerc,res.getInt("id"),res.getInt("customers_affected"),res.getDate("dataInizio").toLocalDate(),res.getDate("dataFine").toLocalDate(), res.getInt("DIFFERENZAORA"));
				blackouts.add(b);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return blackouts;
}
	}
