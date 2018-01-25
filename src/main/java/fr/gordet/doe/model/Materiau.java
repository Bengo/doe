package fr.gordet.doe.model;

import org.springframework.data.annotation.Id;

public class Materiau {

	@Id
	private String id;

	private String categorie;
	private String marque;
	private String reference;
	private byte[] fichier;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the categorie
	 */
	public String getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie
	 *            the categorie to set
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * @return the marque
	 */
	public String getMarque() {
		return marque;
	}

	/**
	 * @param marque
	 *            the marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the fichier
	 */
	public byte[] getFichier() {
		return fichier;
	}

	/**
	 * @param fichier
	 *            the fichier to set
	 */
	public void setFichier(byte[] fichier) {
		this.fichier = fichier;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Materiau [id=" + id + ", categorie=" + categorie + ", marque=" + marque + ", reference=" + reference
				+"]";
	}

}
