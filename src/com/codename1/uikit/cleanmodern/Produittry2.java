/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.entities.Produit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ahmed
 */
public class Produittry2  extends BaseForm{
    public Produittry2 (Resources res) {
        
        
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("liste de Produits");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer2 = new Label();
        addTab(swipe, res.getImage("Screenshot_11.png"), spacer2, "", "", "Welcome in Tunisia Mall ");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        
        Component.setSameSize(radioContainer, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/crud/getallProduits.php");
 con.addResponseListener(new ActionListener<NetworkEvent>() {
           
            List<Produit> Voyagepersonalises = new ArrayList<>();

            @Override
            public void actionPerformed(NetworkEvent evt) {
                Voyagepersonalises = getList(new String(con.getResponseData()));
                for (Iterator it = Voyagepersonalises.iterator(); it.hasNext();) {
                    Produit r = (Produit) it.next();
                    String brochure = r.getPath();
                  System.out.println(".actionPerformed()jfufytrtst"+brochure);
                  EncodedImage img = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(),150), true);
                  
                  URLImage imgg= URLImage.createToStorage(img, "http://localhost/TestUser/web/images/ahmed/"+brochure, "http://localhost/TestUser/web/images/ahmed/"+brochure);
                  imgg.fetch();
                  ImageViewer imgv = new ImageViewer(imgg);
                 ///// listOfDiscussions.add(imgg);
                  int fiveMM = Display.getInstance().convertToPixels(20);
                  final Image finalDuke = imgg.scaledWidth(fiveMM);
                 ///// MultiButton mb = new MultiButton();
                addButton(finalDuke,r,res);
                    
                }

            } 
        });
        NetworkManager.getInstance().addToQueue(con);        
    }
    
    
    
    
    
    
     private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
   private void addButton(Image img, Produit v,Resources res) {
       int height = Display.getInstance().convertToPixels(20f);
       int width = Display.getInstance().convertToPixels(20f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
      
       TextArea ta = new TextArea(v.getNom().toString());
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
       Button sb = new Button("détaille");
       sb.setTextPosition(RIGHT);
       
/////        TextArea tp = new TextArea(v.getPrix().toString());
      ///// sb.getUnselectedStyle(0xff2d55);
       Label likes = new Label(v.getNom()+ " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       Label comments = new Label(v.getType()+ " Comments", "NewsBottomLine");
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                   
                       sb
               ));
       add(cnt);
       image.addActionListener(e -> ToastBar.showMessage("Prix de Produit: "+v.getPrix(), FontImage.MATERIAL_INFO));
       sb.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               
                  new DescriptionForm(res,v.getIdProduit(),
                                v.getNom(),
                              
                                v.getDescription(),
                                v.getPath());
           }
       });
   }
   
   
   
     public ArrayList<Produit> getList(String json) {
        ArrayList<Produit> listEtudiants = new ArrayList<>();
        try {
            int i = 0;
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            Form f = new Form();
            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                i++;
                Produit e = new Produit();//id, json, status);
                
                e.setIdProduit((int) Float.parseFloat(obj.get("id_produit").toString()));
                e.setNom(obj.get("nom").toString());
                e.setType(obj.get("type").toString());
                e.setPrix((double) Float.parseFloat(obj.get("prix").toString()));
                e.setQuantite((int) Float.parseFloat(obj.get("quantite").toString()));  
                e.setPrixAchatGros((float) Float.parseFloat(obj.get("prix_achat_gros").toString())); 
                e.setNbVente((int) Float.parseFloat(obj.get("nb_vente").toString()));  
                e.setPath(obj.get("path").toString());      
                e.setDescription(obj.get("description").toString()); 
                e.setIdBoutique((int) Float.parseFloat(obj.get("id_boutique").toString())); 
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
        return listEtudiants;

    }
}
