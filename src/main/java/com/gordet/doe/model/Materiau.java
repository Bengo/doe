package com.gordet.doe.model;

import java.util.List;

public class Materiau {

	 private String categorie;
	 private String marque;
	 private List<String> references;
	 private String fichier;
	/**
	 * @return the categorie
	 */
	public String getCategorie() {
		return categorie;
	}
	/**
	 * @param categorie the categorie to set
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
	 * @param marque the marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}

	/**
	 * @return the references
	 */
	public List<String> getReferences() {
		return references;
	}
	/**
	 * @param references the references to set
	 */
	public void setReferences(List<String> references) {
		this.references = references;
	}
	/**
	 * @return the fichier
	 */
	public String getFichier() {
		return fichier;
	}
	/**
	 * @param fichier the fichier to set
	 */
	public void setFichier(String fichier) {
		this.fichier = fichier;
	}
	 
	 
}
