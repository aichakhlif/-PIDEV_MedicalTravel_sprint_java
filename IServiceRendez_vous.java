/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesLinda;

import entitiesLinda.dossier;
import entitiesLinda.rendez_vous;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public interface IServiceRendez_vous {
    public void addRendezvous(rendez_vous r );
    public ObservableList<rendez_vous> afficherRendezvousList();
     public void modifierRendezvous(rendez_vous r);
     public int getIdParDesc(String id ) ;
     public void supprimerRendezvous(int id) ;
     public ObservableList<rendez_vous> trierDate();
    public ObservableList<rendez_vous> trierHeure();
}
