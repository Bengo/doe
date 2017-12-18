package fr.gordet.doe.model;

import org.springframework.data.annotation.Id;

import com.mongodb.gridfs.GridFSDBFile;

public class MateriauDto {

	@Id
	private String id;

	private Categorie categorie;
	private Marque marque;
	private String reference;
	private GridFSDBFile fichier;

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
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie
	 *            the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**
	 * @return the marque
	 */
	public Marque getMarque() {
		return marque;
	}

	/**
	 * @param marque
	 *            the marque to set
	 */
	public void setMarque(Marque marque) {
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
	public GridFSDBFile getFichier() {
		return fichier;
	}

	/**
	 * @param fichier
	 *            the fichier to set
	 */
	public void setFichier(GridFSDBFile fichier) {
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
				+ ", fichier=" + fichier.getFilename() + "]";
	}

}
