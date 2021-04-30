/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class offre {
                  private int idoffre;
                    private medecin med;
    private intervention inter ;
    private clinique clinique;
    private int prix;
    private Date date;

    public offre() {
    }

    public offre(int idoffre, medecin med, intervention inter, clinique clinique, int prix, Date date) {
        this.idoffre = idoffre;
        this.med = med;
        this.inter = inter;
        this.clinique = clinique;
        this.prix = prix;
        this.date = date;
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

    public int getPrix() {
        return prix;
    }

    public Date getDate() {
        return date;
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

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setDate(Date date) {
        this.date = date;
    }

 

    public int getIdoffre() {
        return idoffre;
    }

    public void setIdoffre(int idoffre) {
        this.idoffre = idoffre;
    }

    
    
    
    @Override
    public String toString() {
        return ""+ idoffre;
                
    }
                  
                  

    
}
