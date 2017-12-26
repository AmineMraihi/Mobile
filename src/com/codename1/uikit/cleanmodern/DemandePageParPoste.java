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
import com.codename1.io.NetworkEvent;
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
import com.codename1.ui.Form;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.entities.DemandeEmploi;
import com.codename1.uikit.entities.OffreEmploi;
import com.codename1.uikit.entities.User;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Amine
 */
public class DemandePageParPoste extends BaseForm {

       //  public static final String ACCOUNT_SID = "ACe70e80a817d26dea089caa976cd7c6e9";
       //  public static final String AUTH_TOKEN = "7640c9fe1b075e89b29624c39d710e31";
    
    
    //  List<DemandeEmploi> listOffres = new ArrayList<>();
    public DemandePageParPoste(Resources res) {
        super("", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
//        getTitleArea().setUIID("Container");
        setTitle("");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        
       /* tb.addSearchCommand( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              //  User u = SignInForm.staticUser;
              //  id = u.getIdUser();
              //  System.out.println(id);
                MesDemandesPage mesdemande = new MesDemandesPage(res);
                mesdemande.show();
            }
             
        });*/

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

        Button btnAjouterD = new Button("Ajouter");

        Container ajoutD = new Container(BoxLayout.y());
        ajoutD.setScrollableY(true);

        User u = SignInForm.staticUser;
        OffreEmploi e = OffreEmploiParPoste.StaticOffre;
        
        System.out.println(e);
        
        int ido = e.getIdOffre();
        System.out.println(ido);

        int idu=u.getIdUser();
        System.out.println(idu);
        
        String n = u.getNom();
        System.out.println(n);

        String p = u.getPrenom();
        System.out.println(p);

        
        
        Label nom = new Label("Nom");
        TextField nomt = new TextField(n);
        nomt.getStyle().setFgColor(0x000000);
        
        Label prenom = new Label("Prenom");
        TextField prenomt = new TextField(p);
        prenomt.getStyle().setFgColor(0x000000);
      
        
        Label adresse = new Label("Adresse");
        TextField adresset = new TextField("", "Adresse", 20, TextArea.ANY);
        adresset.getStyle().setFgColor(0x000000);
        
        Label sexe = new Label("Sexe");
        TextField sexet = new TextField("", "Sexe", 20, TextArea.ANY);
        sexet.getStyle().setFgColor(0x000000);
        
        Label email = new Label("Email");
        TextField emailt = new TextField("", "nom.prenom@hotmail.com", 20, TextArea.ANY);
        emailt.getStyle().setFgColor(0x000000);
        
        Label numtel = new Label("Numero de telephone");
        TextField numt = new TextField("", "Numero de telephone", 20, TextArea.ANY);
        numt.getStyle().setFgColor(0x000000);
        
        Label qualification = new Label("Qualification");
        TextField qualift = new TextField("", "Qualification", 20, TextArea.ANY);
        qualift.getStyle().setFgColor(0x000000);
        
 //    Label exp = new Label("Experience");
 // TextField expt = new TextField("", "Experience", 20, TextArea.ANY);

//   ajoutD.add(nom).add(nomt).add(prenom).add(prenomt).add(adresse).add(adresset).add(sexe).add(sexet).add(email).add(emailt).add(numtel).add(numt).add(qualification).add(qualift).add(exp).add(expt);
ajoutD.add(nom).add(nomt).add(prenom).add(prenomt).add(adresse).add(adresset).add(sexe).add(sexet).add(email).add(emailt).add(numtel).add(numt).add(qualification).add(qualift);

       ajoutD.add(btnAjouterD);

        add(ajoutD);

        btnAjouterD.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                ConnectionRequest req = new ConnectionRequest();
 //      req.setUrl("http://localhost:8082/crud/ajouterDemande.php?id_user_fk=" +idu+ "$id_offre_fk=" +ido+ "$NomEmp=" + nomt.getText() + "&PrenomEmp=" + prenomt.getText() + "&adresse=" + adresset.getText()+ "&sexe=" + sexet.getText() + "&numTel=" + numt.getText() + "&email=" + emailt.getText() + "&qualification=" + qualift.getText() + "&exper="+ expt.getText() + "");

 req.setUrl("http://localhost:8082/crud/ajouterDemande.php?id_user_fk="+idu+"&id_offre_fk="+ido+"&NomEmp="+ nomt.getText()+"&PrenomEmp="+prenomt.getText()+"&adresse="+adresset.getText()+"&sexe="+sexet.getText()+"&numTel="+numt.getText()+"&email="+emailt.getText() + "&qualification=" + qualift.getText() +"");

                
                
                req.addResponseListener(new ActionListener<NetworkEvent>() {

              
                    
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                       
            /*        Twilio.init("ACe70e80a817d26dea089caa976cd7c6e9", "7640c9fe1b075e89b29624c39d710e31");
         com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+21696659302"),
         new PhoneNumber("+14435683958"),"Votre demande a été ajoutée").create();
            */
                    
                    }
                });

                NetworkManager.getInstance().addToQueue(req);
        MesDemandesPage mesdemande = new MesDemandesPage(res);

                 mesdemande.show();
            }
            
               
            
        }  );  

    }

    // public void show(){F.show();}
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

}
