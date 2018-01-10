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
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import java.util.ArrayList;
import java.util.List;
import com.codename1.uikit.entities.Reclamation;
import com.codename1.uikit.entities.User;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.ReclamationDescriptionForm;
import com.codename1.uikit.cleanmodern.SignInForm;
import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import util.Statics;

/**
 *
 * @author ASUS
 */
public class ReclamationService {
    public static Form listofreclamations;
       
       
        
     private ConnectionRequest connectionRequest;
     
     public void addReclamation(String type,String text,Integer id_reclamant,Integer id_P_reclame)
     {
         
       connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog.show("Succes", "Reclamation envoye avec succes", "ok", null);

            }
        };  
       connectionRequest.setUrl("http://localhost/crud/insertreclamation.php?"
                + "type=" + type
                + "&text=" + text
                + "&id_reclamant=" + id_reclamant
                + "&id_P_reclame=" + id_P_reclame
                
        );
        NetworkManager.getInstance().addToQueue(connectionRequest);
     }
     
     public void findAllReclamation(){
        connectionRequest = new ConnectionRequest() {
        List<Reclamation> reclamations = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    reclamations.clear();
                  
                    for (Map<String, Object> obj : content) {
                        reclamations.add(new Reclamation((String) obj.get("type"),(String) obj.get("description")));
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

          @Override
           protected void postResponse() {
                //System.out.println(libs.size());
                listofreclamations = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList libsNoms = new ArrayList<>();
                ArrayList libsDesc = new ArrayList<>();
                
                for(Reclamation l : reclamations){
                    libsNoms.add(l.getType());
                    
                    
                }
                com.codename1.ui.list.DefaultListModel listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                  /*uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Reclamation c = reclamations.get(uiLibsList.getCurrentSelected());
                        new ReclamationDescriptionForm(c.getType(),c.getText()).show();
                    }
                });*/
                
                listofreclamations.setLayout(new BorderLayout());
                listofreclamations.add(BorderLayout.NORTH,uiLibsList);
            try {
                listofreclamations.add(BorderLayout.SOUTH,Statics.createBackBtn());
            } catch (IOException ex) {
                //Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
            }
                listofreclamations.show();             
            }
        };
        int id_reclamant= SignInForm.staticUser.getIdUser();
        connectionRequest.setUrl("http://localhost/crud/afficherreclamation.php?id_reclamant="+id_reclamant);
        NetworkManager.getInstance().addToQueue(connectionRequest);
     
     }

}
