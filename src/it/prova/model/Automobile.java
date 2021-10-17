package it.prova.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "automobile")
public class Automobile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "marca")
	private String marca;
	@Column(name = "modello")
	private String modello;
	@Column(name = "targa")
	private String targa;
	@Column(name = "annoimmatricolazione")
	private Date annoimmatricolazione;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_proprietario")
	private Proprietario proprietario;

	public Automobile() {
		super();
	}

	public Automobile(String marca, String modello, String targa, Date annoimmatricolazione,
			Proprietario proprietario) {
		super();
		this.marca = marca;
		this.modello = modello;
		this.targa = targa;
		this.annoimmatricolazione = annoimmatricolazione;
		this.proprietario = proprietario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public Date getAnnoimmatricolazione() {
		return annoimmatricolazione;
	}

	public void setAnnoimmatricolazione(Date annoimmatricolazione) {
		this.annoimmatricolazione = annoimmatricolazione;
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

}
