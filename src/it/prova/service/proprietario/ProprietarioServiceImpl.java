package it.prova.service.proprietario;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.dao.EntityManagerUtil;
import it.prova.dao.proprietario.ProprietarioDAO;
import it.prova.model.Proprietario;

public class ProprietarioServiceImpl implements ProprietarioService {

	private ProprietarioDAO proprietarioDAO;

	@Override
	public void setProprietarioDAO(ProprietarioDAO proprietarioDAO) {
		this.proprietarioDAO = proprietarioDAO;

	}

	@Override
	public List<Proprietario> listAllProprietari() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			proprietarioDAO.setEntityManager(entityManager);

			return proprietarioDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Proprietario caricaSingoloProprietario(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			proprietarioDAO.setEntityManager(entityManager);

			return proprietarioDAO.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Proprietario proprietarioInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			entityManager.getTransaction().begin();

			proprietarioDAO.setEntityManager(entityManager);

			proprietarioDAO.update(proprietarioInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void inserisciNuovo(Proprietario proprietarioInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			proprietarioDAO.setEntityManager(entityManager);

			proprietarioDAO.insert(proprietarioInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuovi(Proprietario proprietarioInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			entityManager.getTransaction().begin();

			proprietarioDAO.setEntityManager(entityManager);

			proprietarioDAO.delete(proprietarioInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public int contaProprietariAutomobileDopoDuemila() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			proprietarioDAO.setEntityManager(entityManager);

			return proprietarioDAO.quantiProprietariConAutoDopoDuemila();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

}
