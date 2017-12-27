/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.capture.Capture;
import com.codename1.components.MultiButton;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.ShareButton;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.tunisiamall.service.ReviewService;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.cleanmodern.DescriptionForm.id_prod;
import com.codename1.uikit.entities.Review11;
import com.codename1.uikit.entities.User;
import java.io.IOException;
import com.twilio.Twilio; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.rest.api.v2010.account.MessageCreator; 
import com.twilio.type.PhoneNumber;

/**
 *
 * @author ahmed
 */
public class ReviewForm  extends Demo {
     
    private final static String ACCOUNT_SID = "ACdd7bfcbbab3fa3485747ba2c814e0cc0"; 
    private final static String AUTH_TOKEN = "248f809fdd775f9bb1031615b1971714"; 
    private Form current;
 
      public int idd;
    
   private Button back;
    
    public ReviewForm(Resources res,int id_prod){
        
        idd=id_prod;
         Form hi = new Form("Star Slider", new BoxLayout(BoxLayout.Y_AXIS));
       ///  hi.setUIID("merzoug");
  hi.add(FlowLayout.encloseCenter(createStarRankSlider()));
  
  
  
       /// TextField name = new TextField("", "Name", 20, TextField.ANY);
       //// FontImage.setMaterialIcon(name.getHintLabel(), FontImage.MATERIAL_PERSON);
        TextField email = new TextField("", "E-mail", 2,20);
        email.getUnselectedStyle().setFgColor(-16777216);
        
        FontImage.setMaterialIcon(email.getHintLabel(), FontImage.MATERIAL_EMAIL);
       //// TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
      ////  FontImage.setMaterialIcon(password.getHintLabel(), FontImage.MATERIAL_LOCK);
        TextField contenu = new TextField("", "Contenu", 2, 20);
        contenu.getUnselectedStyle().setFgColor(-16777216);
        FontImage.setMaterialIcon(contenu.getHintLabel(), FontImage.MATERIAL_LIBRARY_BOOKS);
        
          contenu.setSingleLineTextArea(false);
          
          
           Button save = new Button("Save");
    
        Container comps = new Container();
        /*   hi.addComps(parent, comps, 
                new Label("Name", "InputContainerLabel"), 
                name,
                new Label("E-Mail", "InputContainerLabel"),
                email,
                new Label("Password", "InputContainerLabel"),
                password,
                BorderLayout.center(new Label("Birthday", "InputContainerLabel")).
                        add(BorderLayout.EAST, birthday),
                new Label("Bio", "InputContainerLabel"),
                bio,
                BorderLayout.center(new Label("Join Mailing List", "InputContainerLabel")).
                        add(BorderLayout.EAST, joinMailingList));
  hi.add(comps)
*/
        
        
        
        
        
        
        
        
////     hi.add(name);
      hi.add(email);
   ////    hi.add(password);
       hi.add(contenu);
          hi.add(save);
           back= new Button("back ");
           hi.add(back);
        save.addActionListener(e -> {
           //// User Usera = new User ();
         Review11 a = new Review11(email.getText(),contenu.getText(),idd);
                  
 Resources ress = null ;
User uu = null; 
          Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
           Message message = Message.creator(new PhoneNumber("+21620644002"),
        new PhoneNumber("+17043264234"),"send review to ahmed merzoug").create();

  new ReviewService(ress, uu).AddReview(a);
  
        ////    ToastBar.showMessage("Save pressed...", FontImage.MATERIAL_INFO);
        });
        
        
        
        
        
            Button avatar = new Button("add a photo");
        avatar.setUIID("InputAvatar");
        Image defaultAvatar = FontImage.createMaterial(FontImage.MATERIAL_CAMERA, "InputAvatarImage", 8);
         Image circleMaskImage = defaultAvatar;
        defaultAvatar = defaultAvatar.scaled(circleMaskImage.getWidth(), circleMaskImage.getHeight());
        defaultAvatar = ((FontImage)defaultAvatar).toEncodedImage();
        Object circleMask = defaultAvatar.createMask();
        defaultAvatar = defaultAvatar.applyMask(circleMask);
        avatar.setIcon(defaultAvatar);
        
        avatar.addActionListener(e -> {
            if(Dialog.show("Camera or Gallery", "Would you like to use the camera or the gallery for the picture?", "Camera", "Gallery")) {
                String pic = Capture.capturePhoto();
                if(pic != null) {
                    try {
                        Image img = Image.createImage(pic).fill(circleMaskImage.getWidth(), circleMaskImage.getHeight());
                        avatar.setIcon(img.applyMask(circleMask));
                    } catch(IOException err) {
                        ToastBar.showErrorMessage("An error occured while loading the image: " + err);
                        Log.e(err);
                    }
                }
            } else {
                Display.getInstance().openGallery(ee -> {
                    if(ee.getSource() != null) {
                        try {
                            Image img = Image.createImage((String)ee.getSource()).fill(circleMaskImage.getWidth(), circleMaskImage.getHeight());
                            avatar.setIcon(img.applyMask(circleMask));
                        } catch(IOException err) {
                            ToastBar.showErrorMessage("An error occured while loading the image: " + err);
                            Log.e(err);
                        }
                    }
                }, Display.GALLERY_IMAGE);
            }
        });
        
        
         
     hi.add(avatar);
     hi.add(defaultAvatar);
     
     

     
        back.addActionListener(e->{
           ////  Resources res = null;
            ////Review2Service p = new Review2Service();
         ////  p.findAllReviews(id);
      ////  ReviewForm f=new ReviewForm(id_prod);
        Produittry2 p = new Produittry2(res);
        p.show();
      //// super.show();  
        });          
        
        
   hi.show();
    }
    public SwipeableContainer createRankWidget(Image c, String title, String year) {
        MultiButton button = new MultiButton(title);
        button.setTextLine2(year);

        button.setIcon(c);
        ShareButton share = new ShareButton();
        FontImage.setMaterialIcon(share, FontImage.MATERIAL_SHARE, 8);
        return new SwipeableContainer(share,
                button);
        
        
    }



    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(10);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }
    
    
    private void addComps(Form parent, Container cnt, Component... cmps) {
        if(Display.getInstance().isTablet() || !Display.getInstance().isPortrait()) {
            TableLayout tl = new TableLayout(cmps.length / 2, 2);
            cnt.setLayout(tl);
            tl.setGrowHorizontally(true);
            for(Component c : cmps) {
                if(c instanceof Container) {
                    cnt.add(tl.createConstraint().horizontalSpan(2), c);
                } else {
                    cnt.add(c);
                }
            }
        } else {
            cnt.setLayout(BoxLayout.y());
            for(Component c : cmps) {
                cnt.add(c);
            }
        }
        if(cnt.getClientProperty("bound") == null) {
            cnt.putClientProperty("bound", "true");
            if(!Display.getInstance().isTablet()) {
                parent.addOrientationListener((e) -> {
                    Display.getInstance().callSerially(() -> {
                        cnt.removeAll();
                        addComps(parent, cnt, cmps);
                        cnt.animateLayout(800);
                    });
                });
            }
        }
    }

      public String getDisplayName() {
        return "ReviewForm";
    }

    public Image getDemoIcon() {
        return getResources().getImage("dog.jpg"); 
    }

    }
  
    

