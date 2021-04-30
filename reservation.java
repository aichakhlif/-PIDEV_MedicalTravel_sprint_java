/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;



/**
 *
 * @author admin
 */
public class reservation {
      private int idres;
    private medecin med;
    private intervention inter ;
    private clinique clinique;
    private String nom;
    private String email;
    private String pays;
    
       public reservation() {
    }

    public reservation(int idres, medecin med, intervention inter , clinique clinique, String nom,String email,String pays) {
        this.idres = idres;
        this.med = med;
        this.inter = inter;
        this.clinique = clinique;
        this.nom = nom;
        this.email=email;
        this.pays=pays;
        
    }

    public int getIdres() {
        return idres;
    }

    public medecin getMed() {
        return med;
    }

    public intervention getInter() {
        return inter;
    }

    public clinique getClinique() {
        return clinique;
    }

    public String getNom() {
        return nom;
    }

  
    public String getEmail() {
        return email;
    }

    public String getPays() {
        return pays;
    }

    public void setIdres(int idres) {
        this.idres = idres;
    }

    public void setMed(medecin med) {
        this.med = med;
    }

    public void setInter(intervention inter) {
        this.inter = inter;
    }

    public void setClinique(clinique clinique) {
        this.clinique = clinique;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

   

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
    
      @Override
    public String toString() {
        return "reservation{" + "id=" + idres + ", medecin=" + med + ", intervention=" + inter + ", clinique=" + clinique + ", nom=" + nom + ", email=" + email +", pays=" + pays + '}';
    }

    
}
