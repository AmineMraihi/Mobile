/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
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
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
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
import com.codename1.uikit.entities.OffreEmploi;
import com.codename1.uikit.entities.User;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Amine
 */
public class OffreEmploiPage extends BaseForm {

    List<OffreEmploi> listOffres = new ArrayList<>();
    public static OffreEmploi StaticOffre;
    public static OffreEmploi Offreposte;
    //public static String post = null;
  //  public static User staticUser;
    
    
    public OffreEmploiPage(Resources res) {
 
      super("", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
//        getTitleArea().setUIID("Container");
        setTitle("");
       // getContentPane().setScrollVisible(false);

           Container c = new Container(new BoxLayout( BoxLayout.y().Y_AXIS) );
           
          Button b = new Button("Chercher");
          TextField p = new TextField("", "Chercher par poste", 20, TextArea.ANY);
          p.getStyle().setFgColor(0x000000);
        
          
             super.addSideMenu(res);

          
       c.add(p).add(b);
       
       
        b.addActionListener(new ActionListener() {
          
            @Override
            public void actionPerformed(ActionEvent evt) {
                   
              Offreposte = new OffreEmploi();
              
               Offreposte.setPoste(p.getText());
            //    System.out.println(Offreposte);
                  OffreEmploiParPoste poste = new OffreEmploiParPoste(res);
                  poste.show();
                
                
            }
            
             
        });


        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("profile-background.jpg"), spacer1, "", "", "");
      //  addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");

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
        });

        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();

        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        for (OffreEmploi e : getAllOffres()) {
            Label l = new Label("Poste:");
            MultiButton mb = new MultiButton(e.getPoste());
            
            Label l1 = new Label("Specialité:");
            MultiButton mb1 = new MultiButton(e.getSpecialite());
           
            
            Label l2 = new Label("Nombre demandé:");
            MultiButton mb2 = new MultiButton(e.getNbrDemande().toString());
            
            Button bt = new Button("Poster demande");

            // MultiButton mb5 = new MultiButton(e.getDateExpiration().toString());
            list.add(l);
            list.add(mb);

            list.add(l1);
            list.add(mb1);

            list.add(l2);
            list.add(mb2);

            list.add(bt);

            bt.addActionListener(new ActionListener() {
                @Override

                public void actionPerformed(ActionEvent evt) {
                    StaticOffre = new OffreEmploi();
                    StaticOffre = e;
                   System.out.println(StaticOffre);
                    DemandePage demandepage = new DemandePage(res);
                    demandepage.show();
                   
                }
            });

        }
        add(c);
        add(list);
    }

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

    public List<OffreEmploi> getAllOffres() {
        //    List<Map<String, Object>> all = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest("http://localhost/crud/listOffres.php");
        NetworkManager.getInstance().addToQueueAndWait(request);
        Map<String, Object> result = null;

        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));

            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");
            System.out.println();
            for (Map<String, Object> obj : response) {
              /*  OffreEmploi e = new OffreEmploi((int) Float.parseFloat(obj.get("id_offre").toString()), (int) Float.parseFloat(obj.get(SignInForm.staticUser.getIdUser()).toString()), (String) obj.get("poste"),
                        (String) obj.get("specialite"), ((int) Float.parseFloat(obj.get("nbr_demande").toString()))
                );*/
              OffreEmploi e = new OffreEmploi();
              e.setIdOffre((int) Float.parseFloat(obj.get("id_offre").toString()));
                System.out.println(e.getIdOffre());
               User u = SignInForm.staticUser;
               
         //   e.setIdUserFk((int) Float.parseFloat(obj.get(u.getIdUser()).toString()));
         int i = u.getIdUser();
       
         e.setIdUserFk(i);
             
             System.out.println(e.getIdUserFk());
             
              e.setPoste((String) obj.get("poste"));
                System.out.println(e.getPoste());
              
              e.setSpecialite((String) obj.get("specialite"));
               System.out.println(e.getSpecialite());
              
              e.setNbrDemande(((int) Float.parseFloat(obj.get("nbr_demande").toString())));
               System.out.println(e.getNbrDemande());
               
              
               //e.setId((int) Float.parseFloat(o.get("id").toString()));
               
              //  Date myDate = new Date(temp * 1000L);
               
               //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
             //  System.out.println(sdf.format(new Date()));

              // String mydate = (String) obj.get("date_expiration") ;
               
               
             //   e.setDateExpiration(((Date) (obj.get("date_expiration"))));
               System.out.println(e.getDateExpiration());
               
              
                listOffres.add(e);
            }
            System.out.println(listOffres.size());
        } catch (IOException ex) {
            System.out.println("EXCEPTION : " + ex);

        }
        return listOffres;

    }
    
  /* public java.sql.Date convert (String date) throws ParseException, com.codename1.l10n.ParseException{
      
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    java.util.Date date1 = sdf1.parse(date);
    java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
   
    return sqlDate ;  
    }
   
   
    public static String convert(java.sql.Date d) {
        DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String text = df.format(d);
        return text;
    }
    */
}
