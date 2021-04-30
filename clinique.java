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
public class clinique {
              private int idclinique;
              private String nomclinique;

   
              

    public clinique() {
    }

    public clinique(int idclinique) {
        this.idclinique = idclinique;
    }
    
     public String getNomclinique() {
        return nomclinique;
    }

    public void setNomclinique(String nomclinique) {
        this.nomclinique = nomclinique;
    }

    public void setIdclinique(int idclinique) {
        this.idclinique = idclinique;
    }

    public int getIdclinique() {
        return idclinique;
    }
              
   
    @Override
    public String toString() {
        return nomclinique;
    }           

}
