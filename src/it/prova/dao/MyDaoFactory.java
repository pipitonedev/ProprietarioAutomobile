package it.prova.dao;

import it.prova.dao.automobile.AutomobileDAO;
import it.prova.dao.automobile.AutomobileDAOImpl;
import it.prova.dao.proprietario.ProprietarioDAO;
import it.prova.dao.proprietario.ProprietarioDAOImpl;

public class MyDaoFactory {

	private static ProprietarioDAO proprietarioDAOInstance = null;
	private static AutomobileDAO automobileDAOInstance = null;

	public static ProprietarioDAO getProprietarioDAOInstance() {
		if (proprietarioDAOInstance == null)
			proprietarioDAOInstance = new ProprietarioDAOImpl();
		return proprietarioDAOInstance;
	}

	public static AutomobileDAO getAutomobileDAOInstance() {
		if (automobileDAOInstance == null)
			automobileDAOInstance = new AutomobileDAOImpl();
		return automobileDAOInstance;
	}

}
