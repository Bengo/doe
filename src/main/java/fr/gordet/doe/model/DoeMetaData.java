package fr.gordet.doe.model;

import java.util.List;

public class DoeMetaData {

	private String date;
	private List<String> lignesTitre;
	private String moaNom;
	private String moaAdresse;
	private String moaVille;
	private String moeNom;
	private String moeAdresse;
	private String moeVille;
	private String lot;
	private List<Materiau> materiaux;
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the lignesTitre
	 */
	public List<String> getLignesTitre() {
		return lignesTitre;
	}

	/**
	 * @param lignesTitre
	 *            the lignesTitre to set
	 */
	public void setLignesTitre(List<String> lignesTitre) {
		this.lignesTitre = lignesTitre;
	}

	/**
	 * @return the moaNom
	 */
	public String getMoaNom() {
		return moaNom;
	}

	/**
	 * @param moaNom
	 *            the moaNom to set
	 */
	public void setMoaNom(String moaNom) {
		this.moaNom = moaNom;
	}

	/**
	 * @return the moaAdresse
	 */
	public String getMoaAdresse() {
		return moaAdresse;
	}

	/**
	 * @param moaAdresse
	 *            the moaAdresse to set
	 */
	public void setMoaAdresse(String moaAdresse) {
		this.moaAdresse = moaAdresse;
	}

	/**
	 * @return the moaVille
	 */
	public String getMoaVille() {
		return moaVille;
	}

	/**
	 * @param moaVille
	 *            the moaVille to set
	 */
	public void setMoaVille(String moaVille) {
		this.moaVille = moaVille;
	}

	/**
	 * @return the moeNom
	 */
	public String getMoeNom() {
		return moeNom;
	}

	/**
	 * @param moeNom
	 *            the moeNom to set
	 */
	public void setMoeNom(String moeNom) {
		this.moeNom = moeNom;
	}

	/**
	 * @return the moeAdresse
	 */
	public String getMoeAdresse() {
		return moeAdresse;
	}

	/**
	 * @param moeAdresse
	 *            the moeAdresse to set
	 */
	public void setMoeAdresse(String moeAdresse) {
		this.moeAdresse = moeAdresse;
	}

	/**
	 * @return the moeVille
	 */
	public String getMoeVille() {
		return moeVille;
	}

	/**
	 * @param moeVille
	 *            the moeVille to set
	 */
	public void setMoeVille(String moeVille) {
		this.moeVille = moeVille;
	}

	/**
	 * @return the lot
	 */
	public String getLot() {
		return lot;
	}

	/**
	 * @param lot
	 *            the lot to set
	 */
	public void setLot(String lot) {
		this.lot = lot;
	}

	/**
	 * @return the materiaux
	 */
	public List<Materiau> getMateriaux() {
		return materiaux;
	}

	/**
	 * @param materiaux the materiaux to set
	 */
	public void setMateriaux(List<Materiau> materiaux) {
		this.materiaux = materiaux;
	}

	
}
