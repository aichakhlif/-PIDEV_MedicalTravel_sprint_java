/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author ZIED
 */
public class Article {
    
    Integer id ;
    String Auteur ;
    String Titre;
    String Contenu ;
    Date Date ;
    String Image ;
    private CategorieArticle Categorie ;

    public Article(Integer id, String Auteur, String Titre, String Contenu, Date Date, String Image, CategorieArticle Categorie) {
        this.id = id;
        this.Auteur = Auteur;
        this.Titre = Titre;
        this.Contenu = Contenu;
        this.Date = Date;
        this.Image = Image;
        this.Categorie = Categorie;
    }

    public Article() {
        
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", Auteur=" + Auteur + ", Titre=" + Titre + ", Contenu=" + Contenu + ", Date=" + Date + ", Image=" + Image + ", Categorie=" + Categorie + '}';
    }

    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuteur() {
        return Auteur;
    }

    public void setAuteur(String Auteur) {
        this.Auteur = Auteur;
    }

    public String getTitre() {
        return Titre;
    }

    public CategorieArticle getCategorie() {
        return Categorie;
    }

    public void setCategorie(CategorieArticle Categorie) {
        this.Categorie = Categorie;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
    
    
}
