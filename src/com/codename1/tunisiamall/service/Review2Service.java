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
import static com.codename1.tunisiamall.service.ReviewService.listOfDiscussions;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.uikit.cleanmodern.Statics;
import com.codename1.uikit.entities.Review11;
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
public class Review2Service {
     private ConnectionRequest connectionRequest;
    public static Form listOfDiscussions;
  /////  public Button btn;
    
       public void findAllReviews(int id_produit){
        connectionRequest = new ConnectionRequest() {
        List<Review11> voitures = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    voitures.clear();
                  
                    for (Map<String, Object> obj : content) {
                        
                        voitures.add(new Review11(Integer.parseInt((String)obj.get("id_review")),
                                (String) obj.get("email"),(String) obj.get("contenu"),
                                (String) obj.get("ab"),Integer.parseInt((String)obj.get("id_produit"))
                     )
                        ); 
                        
                        
                        /*
                        voitures.add(new Voiture(Integer.parseInt((String)obj.get("id_discussion")),(String) obj.get("titre_discussion"),
                                (String) obj.get("categorie"),(String) obj.get("descriptif"),(String) obj.get("image_name")
                     )
                        );  */
                        
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }
 protected void postResponse() {
            try {
                //System.out.println(libs.size());
                listOfDiscussions = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Review11 l :voitures){
                    libsNoms.add(l.getContenu());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                
                listOfDiscussions.setLayout(new BorderLayout());
                listOfDiscussions.add(BorderLayout.NORTH,uiLibsList);
                listOfDiscussions.add(BorderLayout.SOUTH,Statics.createBackBtn());
                
                
                
                /////    listOfDiscussions.setLayout(new BorderLayout());
                ////////listOfDiscussions.add(BorderLayout.NORTH,uiLibsList);
                /////  listOfDiscussions.add(btn);
                listOfDiscussions.show();
            } catch (IOException ex) {
               
            }
            }
        };

        connectionRequest.setUrl("http://localhost/crud/getallReviews.php?id_produit="+id_produit+"");
        NetworkManager.getInstance().addToQueue(connectionRequest);

    }

}
