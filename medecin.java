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
public class medecin {
          private int idmed;
         private String nommed;
          
             public medecin() {
    }
          

    public medecin(int idmed) {
        this.idmed = idmed;
    }

    public void setNommed(String nommed) {
        this.nommed = nommed;
    }

    public String getNommed() {
        return nommed;
    }

 

    public void setIdmed(int idmed) {
        this.idmed = idmed;
    }

    public int getIdmed() {
        return idmed;
    }

    @Override
    public String toString() {
        return nommed;
    }
    
    
          

}
