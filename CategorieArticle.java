/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ZIED
 */
public class CategorieArticle {
    
    Integer Id ;
    String Nom ;

    public CategorieArticle(Integer Id, String Nom) {
        this.Id = Id;
        this.Nom = Nom;
    }

    public CategorieArticle() {
       
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    @Override
    public String toString() {
        return "CategorieArticle{" + "Id=" + Id + ", Nom=" + Nom + '}';
    }
    
}
