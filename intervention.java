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
public class intervention {
              private int idinter;
              private String type;



         public intervention() {
    }

    public intervention(int idinter) {
        this.idinter = idinter;
       
        
    }
    
        public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getIdinter() {
        return idinter;
    }

    public void setIdinter(int idinter) {
        this.idinter = idinter;
    }

    @Override
    public String toString() {
        return type ;
    }

   

    
}
