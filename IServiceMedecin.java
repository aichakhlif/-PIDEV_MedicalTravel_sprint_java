/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesLinda;

import entitiesLinda.medecin;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public interface IServiceMedecin {
     public ObservableList<String> getValuesMedecin() ;
         public medecin load_data(String nom );
          public String load_data2(int id) ;
                     public medecin load_data_modify(String id);
                                   public medecin load_data_modify3(int id);


}
