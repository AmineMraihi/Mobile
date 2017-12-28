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
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.uikit.entities.Publicite;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ahmed
 */
public class PUBForm extends BaseForm{
     public static Form listOfDiscussions;
    
     public Slider slider1;
      List<Publicite> listpub = new ArrayList<>();
      List allItems = new ArrayList();
    public PUBForm(Resources res) {
        super("PUBForm", BoxLayout.y());
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("news-item.jpg"), spacer1, "15 Likes  ", "85 Comments", "Integer ut placerat purued non dignissim neque. ");
        addTab(swipe, res.getImage("dog.jpg"), spacer2, " ", "", "");
         swipe.setUIID("listOfDiscussions");
        swipe.getContentPane().setUIID("listOfDiscussions");
        swipe.hideTabs();
       
         slider1 = new Slider();
          slider1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                listOfDiscussions = new Form();
                 int x = slider1.getProgress();
                 
           }
                    });
          
            UIBuilder uib = new UIBuilder();
         UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
          Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        for (Publicite e :getAllPubs()) {
          ////  MultiButton mb = new MultiButton();
           
            
            String brochure = e.getPath();
                   System.out.println(".actionPerformed()"+brochure);
                  EncodedImage img = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(),150), true);
                  URLImage imgg= URLImage.createToStorage(img, "http://localhost/TestUser/web/images/amine/"+brochure, "http://localhost/TestUser/web/images/amine/"+brochure);
                 /// imgg.fetch();
                  ImageViewer imgv = new ImageViewer(imgg);
                  /// list.add(imgg);
                  ////imgv.animate();
               add(imgv);
                ///  int fiveMM = Display.getInstance().convertToPixels(20);
                ///  final Image finalDuke = imgg.scaledWidth(fiveMM);
                ////  mb.setIcon(finalDuke);
                 
                
            
           
           
        }
   /////  add(list);
   
   
   
   
   
   
   
         

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
    
          
     public List<Publicite> getAllPubs() {
        List<Map<String, Object>> all = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest("http://localhost/crud/getallPubs.php");
        NetworkManager.getInstance().addToQueueAndWait(request);
        Map<String, Object> result = null;

        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : response) {
                listpub.add(new Publicite((String) obj.get("path")));
            }


        } catch (IOException ex) {
            System.out.println("EXCEPTION : " + ex);

        }
        return listpub ;

    }

}
