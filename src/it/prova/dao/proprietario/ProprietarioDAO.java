package it.prova.dao.proprietario;

import it.prova.dao.IBaseDAO;
import it.prova.model.Proprietario;

public interface ProprietarioDAO extends IBaseDAO<Proprietario> {

	public int quantiProprietariConAutoDopoDuemila() throws Exception;

	public Proprietario getEagerAutomobili(Long id);

}
