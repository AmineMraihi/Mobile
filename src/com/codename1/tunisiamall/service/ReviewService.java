/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.tunisiamall.service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.entities.Review11;
import com.codename1.uikit.entities.User;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ahmed
 */
public class ReviewService {
  private ConnectionRequest connectionRequest;
         public static Form listOfDiscussions;
            
            
           Resources res ;
           User u; 
               public ReviewService(Resources ress ,User uu) {
         res=ress;
        u=uu;
       
    }
               
     public void AddReview(Review11 a){
         

        connectionRequest=new ConnectionRequest(){
            @Override
            protected void postResponse() {
            Dialog d = new Dialog();
            TextArea popupBody = new TextArea("review ajouté avec succées");
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            d.setLayout(new BorderLayout());
            d.add(BorderLayout.CENTER, popupBody);
            d.show("", "ajout avec succes", "ok", null);
            }
        };
        
        
        connectionRequest.setUrl("http://localhost/crud/insertreview.php?emailt=" + a.getEmail()+ 
                "&contenu=" + a.getContenu()+"&username=" + a.getAb()+   "&id_produit="+a.getIdProduit()
                );
      
        
        
       
        NetworkManager.getInstance().addToQueue(connectionRequest);
}
     
     
    
}

    