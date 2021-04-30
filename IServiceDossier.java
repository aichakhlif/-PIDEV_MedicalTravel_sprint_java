/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesLinda;

import entitiesLinda.dossier;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public interface IServiceDossier {

    public ObservableList<dossier> afficherDossierList();
    public void addDossier(dossier d );
    public void modifierDossier(dossier d);
     public int getIdParDesc(String id ) ;
     public void supprimerDossier(int id) ;
         public ObservableList<dossier> rechercherDossier(String s);
         public ObservableList<dossier> trierDossier();


}


