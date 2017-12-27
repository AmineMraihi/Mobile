/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.tunisiamall.service;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.uikit.cleanmodern.DescriptionForm;
import com.codename1.uikit.entities.Produit;
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
public class ProduitService {
        private ConnectionRequest connectionRequest;
    public static Form listOfDiscussions;
     public static Container ii;
    public  Button but1;
    
    
    public void findallProduits(){
        but1 = new Button("det");
        connectionRequest = new ConnectionRequest() {
        List<Produit> produits = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    produits.clear();
                  
                    for (Map<String, Object> obj : content) {
                        
                        produits.add(new Produit(Integer.parseInt((String)obj.get("id_produit")),
                                (String) obj.get("nom"),(String) obj.get("type"),
                                (double)Float.parseFloat((String)obj.get("prix")),
                                (String) obj.get("path"),
                                (String) obj.get("description"))
                     
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
                //System.out.println(libs.size());
            listOfDiscussions = new Form();
            ii = new Container();
            Container c1 = new Container();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
             Container list = new Container(BoxLayout.y());
          list.setScrollableY(true);
                for(Produit e :produits){
                   MultiButton mb = new MultiButton();
               
             mb.setTextLine1(e.getNom());
             mb.setTextLine2(e.getType());
            
                  String brochure = e.getPath();
                  System.out.println(".actionPerformed()jfufytrtst"+brochure);
                  EncodedImage img = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(),150), true);
                  
                  URLImage imgg= URLImage.createToStorage(img, "http://localhost/TestUser/web/images/ahmed/"+brochure, "http://localhost/TestUser/web/images/ahmed/"+brochure);
                  imgg.fetch();
                  ImageViewer imgv = new ImageViewer(imgg);
                 ///// listOfDiscussions.add(imgg);
                  int fiveMM = Display.getInstance().convertToPixels(20);
                  final Image finalDuke = imgg.scaledWidth(fiveMM);
                  mb.setIcon(finalDuke);
                  
          ///        mb.add(but1);
                 
               
            
            list.add(mb);
                    ////libsNoms.add(l.getNom());
                

               listOfDiscussions.add(list);
               //// com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
               /// uiLibsList.setModel(listModel);
              
               mb.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                    ////    Produit v = produits.get(uiLibsList.getCurrentSelected());
                      /*  new DescriptionForm(e.getIdProduit(),
                                e.getNom(),
                           
                                e.getDescription(),
                                e.getPath());*/
                    }
                });
                
                
                
             /////  listOfDiscussions.setLayout(new BorderLayout());
              ////   listOfDiscussions.add(BorderLayout.NORTH,uiLibsList);
             ////  listOfDiscussions.add(BorderLayout.SOUTH,Statics.createBackBtn());
                 listOfDiscussions.show();         
            }
        };
        };
        connectionRequest.setUrl("http://localhost/crud/getallProduits.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);

    }
   
    
}
