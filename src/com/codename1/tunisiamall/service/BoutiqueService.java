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
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.NewsfeedForm;
import com.codename1.uikit.cleanmodern.ReclamatioForm;
import com.codename1.uikit.entities.Boutique;
import com.codename1.uikit.entities.User;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */


public class BoutiqueService {
    public static Form listOfBoutiquesR;
    Resources res ;
    User u;
     private ConnectionRequest connectionRequest;
     Boutique b;
     List<String> boutiques =new ArrayList<>();
      

    public BoutiqueService() {
        
    }
public Boutique getBoutiqueBynom(String nom) {
        List<Map<String, Object>> all = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest("http://localhost/crud/findboutiquebynom.php?nom="+nom);
        NetworkManager.getInstance().addToQueueAndWait(request);
        Map<String, Object> result = null;

        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : response) {
               // System.out.println("test");
                         b=new Boutique(
                                Integer.parseInt( obj.get("id_boutique").toString())
                              
                        );
            }

        } catch (IOException ex) {
            System.out.println("EXCEPTION : " + ex);

        }
        System.out.println(b);
        return b;

    }
    public List<String> findallboutique()
    {
         List<Map<String, Object>> all = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest("http://localhost/crud/findlistnomboutiques.php");
        NetworkManager.getInstance().addToQueueAndWait(request);
        Map<String, Object> result = null;

        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");
                  
                    for (Map<String, Object> obj : response) {
                        boutiques.add((String) obj.get("nom")
                        );
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
    
             
        return boutiques;
                }   
            
       /* protected void postResponse() {
                //System.out.println(libs.size());
                 listOfBoutiquesR = new ReclamatioForm(res);
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Boutique l :boutiques){
                    libsNoms.add(l.getCategorie());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Discussion currentDiscussion = discussions.get(uiLibsList.getCurrentSelected());
                        new ListeDiscussion(res,u,currentDiscussion.getId_discussion(),currentDiscussion.getTitre_discussion()
                                ,currentDiscussion.getCategorie(),currentDiscussion.getDescriptif()
                                ,currentDiscussion.getImage_name(),currentDiscussion.getNom_utilisateur() ,currentDiscussion.getDate_disc()).show();
                    }
                });
               listOfDiscussions.setLayout(new BorderLayout());
                listOfDiscussions.add(BorderLayout.NORTH,uiLibsList);
                listOfDiscussions.add(BorderLayout.SOUTH,new Button("Back"));
                listOfDiscussions.show();             
            }
        };
        connectionRequest.setUrl("http://localhost/codenameone/getdiscussions.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    
                }    */

    /*public void findUserbyid(int id) {
        connectionRequest = new ConnectionRequest()
        {
            List<Boutique> boutiques = new ArrayList<>();

            @Override
            protected void readResponse(InputStream input) throws IOException 
            {
                JSONParser json = new JSONParser();
                try{
                Reader reader = new InputStreamReader(input, "UTF-8");
                Map<String, Object> data = json.parseJSON(reader);
                List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                 for (Map<String, Object> obj : content) {
                        //boutiques.add(new Boutique((int) obj.get("id_boutique"),(String) obj.get("nom"),(String) obj.get("type"),(User) obj.get("id_reclamant"),(User) obj.get("id_P_reclame")
                        //);
                    }
                }
                catch (IOException err) {
                    
                }
            }
  
    
        };*/
           

}
