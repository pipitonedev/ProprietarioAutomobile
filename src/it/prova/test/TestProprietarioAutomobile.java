package it.prova.test;

import java.util.Date;
import java.util.List;

import it.prova.dao.EntityManagerUtil;
import it.prova.model.Automobile;
import it.prova.model.Proprietario;
import it.prova.service.MyServiceFactory;
import it.prova.service.automobile.AutomobileService;
import it.prova.service.proprietario.ProprietarioService;

public class TestProprietarioAutomobile {

	public static void main(String[] args) {

		ProprietarioService proprietarioService = MyServiceFactory.getProprietarioServiceInstance();
		AutomobileService automobileService = MyServiceFactory.getAutomobileServiceInstance();

		try {

			testInserisciProprietario(proprietarioService);
			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

			testInserisciAutomobile(automobileService, proprietarioService);
			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

			testUpdateProprietario(proprietarioService);
			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

			testCercaAutomobileByProprietarioCfIniziale(automobileService, proprietarioService);
			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

			testRimuoviAutomobile(automobileService, proprietarioService);
			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

			testContaProprietariAutomobileDopoDuemila(automobileService, proprietarioService);
			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			EntityManagerUtil.shutdown();
		}
	}

	private static void testInserisciProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println(".......Inizio Test Insert Proprietario.............");

		Proprietario nuovoProprietario = new Proprietario("vincenzo", "pipitone", "PPTVCN97L10E974Q",
				new Date(1997 / 07 / 10));
		if (nuovoProprietario.getId() != null)
			throw new RuntimeException("Test Insert Proprietario fallito: Record già presente! ");

		proprietarioService.inserisciNuovo(nuovoProprietario);
		if (nuovoProprietario.getId() == null)
			throw new RuntimeException("Test Insert Proprietario fallito! ");

		System.out.println(".......Test insert Proprietario concluso: TUTTO OK!.............");
	}

	private static void testInserisciAutomobile(AutomobileService automobileService,
			ProprietarioService proprietarioService) throws Exception {
		System.out.println(".......Inizio Test Insert Automobile.............");

		List<Proprietario> listaProprietari = proprietarioService.listAllProprietari();
		if (listaProprietari.isEmpty())
			throw new RuntimeException("Test Insert Automobile fallito: non ci sono proprietari a cui collegarci ");

		Automobile nuovaAutomobile = new Automobile("Toyota", "Yaris", "EJ996AB", new Date(2006 / 05 / 20),
				listaProprietari.get(0));

		automobileService.inserisciNuovo(nuovaAutomobile);

		if (nuovaAutomobile.getId() == null)
			throw new RuntimeException("Test Insert Automobile fallito ");

		if (nuovaAutomobile.getProprietario() == null)
			throw new RuntimeException("Test Insert Automobile fallito: non ha collegato il proprietario ");

		automobileService.rimuovi(nuovaAutomobile);

		System.out.println(".......Test Insert Automobile concluso: TUTTO OKAY!.............");
	}

	private static void testUpdateProprietario(ProprietarioService proprietarioService) throws Exception {

		System.out.println(".......	Inizio test update Proprietario.............");

		List<Proprietario> listaProprietari = proprietarioService.listAllProprietari();
		if (listaProprietari.isEmpty())
			throw new RuntimeException("Test Update Proprietari fallito: non ci sono proprietari nel db!");

		Proprietario daModificare = listaProprietari.get(0);
		Proprietario modificato = new Proprietario("Francesco", "Rossi", "FRCMTC879J7657F", new Date(1980 / 04 / 23));
		modificato.setId(daModificare.getId());

		proprietarioService.aggiorna(modificato);

		listaProprietari = proprietarioService.listAllProprietari();
		if (listaProprietari.isEmpty())
			throw new RuntimeException("Test Update Proprietario: non ci sono proprietari da modificare");

		daModificare = listaProprietari.get(0);

		if (daModificare.equals(modificato))
			throw new RuntimeException("Test Update Proprietario Fallito: il record non è stato aggiornato!");

		System.out.println("Test Update Proprietario Concluso: TUTTO OKAY!......................");
	}

	private static void testRimuoviAutomobile(AutomobileService automobileService,
			ProprietarioService proprietarioService) throws Exception {

		System.out.println(".......Inizio Test Rimuovi Automobile.............");

		List<Proprietario> listaProprietari = proprietarioService.listAllProprietari();
		if (listaProprietari.isEmpty())
			throw new RuntimeException("Test Rimuovi Automobile fallito: non ci sono proprietari a cui collegarci ");

		Automobile nuovaAutomobile = new Automobile("Fiat", "Freemont", "AJ897HB", new Date(1382479200),
				listaProprietari.get(0));

		automobileService.inserisciNuovo(nuovaAutomobile);

		Long idAutomobile = nuovaAutomobile.getId();
		automobileService.rimuovi(automobileService.caricaSingolaAutomobile(idAutomobile));

		if (automobileService.caricaSingolaAutomobile(idAutomobile) != null)
			throw new RuntimeException("testRimuoviAutomobile fallito: record non cancellato ");
		System.out.println(".......Test Rimuovi Automobile Concuso: TUTTO OKAY!.............");
	}

	private static void testContaProprietariAutomobileDopoDuemila(AutomobileService automobileService,
			ProprietarioService proprietarioService) throws Exception {

		System.out.println(".......	Inizio test Conta Proprietari con Automobile dopo il 2000 .............");

		List<Proprietario> listaProprietariPresenti = proprietarioService.listAllProprietari();
		if (listaProprietariPresenti.isEmpty())
			throw new RuntimeException("testContaProprietariAutomobileDopoDuemila fallito: non ci sono proprietari");

		Automobile nuovaAutomobile = new Automobile("Audi", "A5", "FHG765SX", new Date(995148000),
				listaProprietariPresenti.get(3));
		Automobile nuovaAutomobile2 = new Automobile("BMW", "X3", "SUC6754F", new Date(1035324000),
				listaProprietariPresenti.get(3));

		automobileService.inserisciNuovo(nuovaAutomobile);
		automobileService.inserisciNuovo(nuovaAutomobile2);

		if (nuovaAutomobile.getId() == null || nuovaAutomobile2.getId() == null)
			throw new RuntimeException(
					"testContaProprietariAutomobileDopoDuemila fallito: inserimento automobile non riuscito ...........");

		if (nuovaAutomobile.getProprietario() == null || nuovaAutomobile2.getProprietario() == null)
			throw new RuntimeException(
					"testContaProprietariAutomobileDopoDuemila fallito: non è stato collegato il proprietario ...........");

		int proprietari = -1;
		proprietari = proprietarioService.contaProprietariAutomobileDopoDuemila();

		if (proprietari == -1)
			throw new RuntimeException("testContaProprietariAutomobileDopoDuemila fallito: la query non ha funzionato");

		automobileService.rimuovi(nuovaAutomobile);
		automobileService.rimuovi(nuovaAutomobile2);

		System.out.println(
				"testContaProprietariAutomobileDopoDuemila CONCLUSO: TUTTO OKAY! .................................................");
	}

	private static void testCercaAutomobileByProprietarioCfIniziale(AutomobileService automobileService,
			ProprietarioService proprietarioService) throws Exception {

		System.out.println(".......	Inizio test Cerca Automobile con Proprietario il cui Cf Inizia per.............");

		List<Proprietario> listaProprietari = proprietarioService.listAllProprietari();
		if (listaProprietari.isEmpty())
			throw new RuntimeException(
					"Test Automobile By Proprietario con Cf fallito: non ci sono proprietari a cui collegarci ");

		Automobile nuovaAutomobile = new Automobile("Fiat", "Punto", "XSK2F", new Date(2017 / 05 / 30),
				listaProprietari.get(0));
		Automobile nuovaAutomobile2 = new Automobile("Alfa Romeo", "Giulietta", "FH876JH", new Date(1106866800),
				listaProprietari.get(0));

		automobileService.inserisciNuovo(nuovaAutomobile);
		automobileService.inserisciNuovo(nuovaAutomobile2);

		if (nuovaAutomobile.getId() == null || nuovaAutomobile2.getId() == null)
			throw new RuntimeException(
					"testCercaAutomobileByProprietarioCfIniziale fallito: inserimento automobile non riuscito ...........");

		if (nuovaAutomobile.getProprietario() == null || nuovaAutomobile2.getProprietario() == null)
			throw new RuntimeException(
					"testCercaAutomobileByProprietarioCfIniziale fallito: non è stato collegato il proprietario ...........");

		List<Automobile> listaAuto = null;
		listaAuto = automobileService.cercaAutomobileByProprietarioCfIniziale("FRC");

		if (listaAuto == null)
			throw new RuntimeException(
					"testCercaAutomobileByProprietarioCfIniziale fallito: la query non ha funzionato");

		automobileService.rimuovi(nuovaAutomobile);
		automobileService.rimuovi(nuovaAutomobile2);

		System.out.println(
				"testCercaAutomobileByProprietarioCfIniziale CONCLUSO: TUTTO OKAY! ......................................");

	}

}
