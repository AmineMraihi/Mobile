
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.contacts.Contact;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.tunisiamall.service.ProduitService;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.entities.Produit;
import com.codename1.uikit.entities.Publicite;
import com.sun.javafx.text.TextLine;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Amine
 */
public class NewsfeedForm extends BaseForm {
    private Form current;
 public  Button ajout3;
 ///public TextLine TextLine1;
 //// public TextLine setTextLine2;
 List<Produit> listproduit = new ArrayList<>();
  List<Publicite> listpub = new ArrayList<>();
 public Slider slider1;

    public NewsfeedForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
         Button backBtn;
     backBtn = new Button("Retour");
        setToolbar(tb);
//        getTitleArea().setUIID("Container");
        setTitle("Tunisia Mall");
        ajout3 = new Button("Retour");
        
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
            
        });

        Tabs swipe = new Tabs();
                Tabs aa = new Tabs();


        Label spacer1 = new Label();
        Label spacer2 = new Label();
        Label spacer3 = new Label();
        Label spacer4 = new Label();
        Label spacer5 = new Label();
        slider1 = new Slider();
          slider1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 int x = slider1.getProgress();
                 
           }
                    });
                 
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/crud/getallPubs.php");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
           
            List<Publicite> Voyagepersonalises = new ArrayList<>();

            @Override
            public void actionPerformed(NetworkEvent evt) {
                Voyagepersonalises = getList(new String(con.getResponseData()));
                for (Iterator it = Voyagepersonalises.iterator(); it.hasNext();) {
                    Publicite r = (Publicite) it.next();
                    String brochure = r.getPath();
                  System.out.println(".actionPerformed()jfufytrtst"+brochure);
                  EncodedImage img = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(),100), true);
                  
                  URLImage imgg= URLImage.createToStorage(img, "http://localhost/TestUser/web/images/amine/"+brochure, "http://localhost/TestUser/web/images/amine/"+brochure);
                  imgg.fetch();
                  ImageViewer imgv = new ImageViewer(imgg);
                 ///// listOfDiscussions.add(imgg);
                  int fiveMM = Display.getInstance().convertToPixels(10);
                   Image finalDuke = imgg.scaledWidth(fiveMM);
                 ///// MultiButton mb = new MultiButton();
               ////// addButton(finalDuke,r,res);
                ////addTab(swipe, finalDuke, spacer1, " ", "", "");
                     addTab(swipe, imgg, spacer3, " ", "", "Welcome in Tunisia Mall");
                     
       
                   
                }
            } 
        });
        NetworkManager.getInstance().addToQueue(con);  
        
         addTab(swipe, res.getImage("dog.jpg"), spacer1, " ", "", "Welcome in Tunisia Mall");
////     addTab(swipe, res.getImage("Logo.png"), spacer1, " ", "", "");
    //// addTab(swipe, res.getImage("tun1.png"), spacer2, "", "", "");
     ////   addButton(img, title, focusScrolling, LEFT, CENTER);
     
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
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
               if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

   
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
      

        
        
        ButtonGroup barGroup = new ButtonGroup();
    /*    super.add(ajout3);
           ajout3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new ProduitService().findallProduits();
             /// ProduitService pp = new ProduitService (); 
              /// pp.findallProduits();
                /*  Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        for (Produit e :getAllproduits()) {
            MultiButton mb = new MultiButton();
            mb.setTextLine1(e.getNom());
             mb.setTextLine2(e.getType());
            
            String brochure = e.getPath();
                   System.out.println(".actionPerformed()"+brochure);
                  EncodedImage img = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(),150), true);
                  URLImage imgg= URLImage.createToStorage(img, "http://localhost/TestUser/web/images/ahmed/"+brochure, "http://localhost/TestUser/web/images/ahmed/"+brochure);
                  imgg.fetch();
                  ImageViewer imgv = new ImageViewer(imgg);
                ////  add(imgv);
                  int fiveMM = Display.getInstance().convertToPixels(20);
                  final Image finalDuke = imgg.scaledWidth(fiveMM);
                  mb.setIcon(finalDuke);
                 
                
            
            list.add(mb);
            
        }
        ////add(list);
         
            
        });
*/
}
           
           
         ////  TextLine1
           
        /*ProduitService p = new ProduitService();
          p.findallProduits();*/
            
      
      
  
          ////super.show();
 
        
        
        
        
        
    

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }

    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
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
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
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

    private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
       
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
       
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
        likes.setTextPosition(RIGHT);
        if (!liked) {
            FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
        } else {
            Style s = new Style(likes.getUnselectedStyle());
            s.setFgColor(0xff2d55);
            FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
            likes.setIcon(heartImage);
        }
        Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);

        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta,
                        BoxLayout.encloseX(likes, comments)
                ));
        add(cnt);
        image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    
       
    
     
      public ArrayList<Publicite> getList(String json) {
        ArrayList<Publicite> listEtudiants = new ArrayList<>();
        try {
            int i = 0;
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            Form f = new Form();
            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                i++;
                Publicite e = new Publicite();//id, json, status);
                
                e.setIdPub((int) Float.parseFloat(obj.get("id_pub").toString()));
                e.setPath(obj.get("path").toString());
               
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
        return listEtudiants;

    }
     public void stop() {
        current = Display.getInstance().getCurrent();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = Display.getInstance().getCurrent();
        }
    }
        
        
     
     
    
    
    
    
}
