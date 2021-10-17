package it.prova.service.automobile;

import java.util.List;

import it.prova.dao.automobile.AutomobileDAO;
import it.prova.model.Automobile;

public interface AutomobileService {

	public List<Automobile> listAllAutomobili() throws Exception;

	public Automobile caricaSingolaAutomobile(Long id) throws Exception;

	public void aggiorna(Automobile automobileInstance) throws Exception;

	public void inserisciNuovo(Automobile automobileInstance) throws Exception;

	public void rimuovi(Automobile automobileInstance) throws Exception;

	public List<Automobile> cercaAutomobileByProprietarioCfIniziale(String iniziale) throws Exception;

	public void setAutomobileDAO(AutomobileDAO automobileDAO);

}
