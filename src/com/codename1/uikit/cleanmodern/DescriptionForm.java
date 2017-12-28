/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.tunisiamall.service.ProduitService;
import com.codename1.tunisiamall.service.Review2Service;
import com.codename1.tunisiamall.service.ReviewService;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.entities.Produit;
import com.codename1.uikit.entities.User;

/**
 *
 * @author ahmed
 */
public class DescriptionForm extends BaseForm {
    
      public static int id_prod;
    private Label l1,l2,l3,lp;
    private  TextField t2;
    private  Container mainContainer;
    private Button rev,affich,back;
   
    private ImageViewer io ; 
    private SpanLabel tt,t1,tp ;
    
   
    public DescriptionForm (Resources res,int idProduit, String nom, String description,String path)
    {
       super("Newsfeed", BoxLayout.y());
         id_prod=idProduit;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("merzoug");
        setTitle("liste de Produits");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer2 = new Label();
        addTab(swipe, res.getImage("dog.jpg"), spacer2, "", "", "Welcome in Tunisia Mall ");
                
        swipe.setUIID("merzoug");
        swipe.getContentPane().setUIID("merzoug");
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
      
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(1,1));
        l1 = new Label("plus de détaille sur notre : ");
           l1.getUnselectedStyle().setFgColor(110000);
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(0xff0000);
     /////   l2 = new Label("Nom:");
      //////  l2.getUnselectedStyle().setFgColor(-16777216);
        lp = new Label("Nom:");
        lp.getUnselectedStyle().setFgColor(-16777216);
        t1 = new SpanLabel(nom); 
        l3 = new Label("DESCRIPTION:");
        l3.getUnselectedStyle().setFgColor(-16777216);
        tt = new SpanLabel(description);
       
         /// String brochure = null;
     
              /*
                  String brochure = path ;
                  System.out.println(".actionPerformed()"+brochure);
                  EncodedImage img = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(),150), true);
                  URLImage imgg= URLImage.createToStorage(img, "http://localhost/TestUser/web/images/ahmed/"+brochure, "http://localhost/TestUser/web/images/ahmed/"+brochure);
                  imgg.fetch();
                      int fiveMM = Display.getInstance().convertToPixels(25);
                  final Image finalDuke = imgg.scaledWidth(fiveMM);
                  ImageViewer imgv = new ImageViewer(finalDuke);
                  mainContainer.add(imgv);
                   io=new ImageViewer (img);
                   
                mainContainer.add(io);
        */
              back= new Button("back ");
        rev= new Button("add your review ");
    affich= new Button("afficher les review ");
          mainContainer.add(l1);
        mainContainer.add(lp);
        mainContainer.add(t1);
         mainContainer.add(l3);
      mainContainer.add(tt);
     //   Statics.setLabelStyle(l4);
    
  
        
      
         mainContainer.add(rev);
        mainContainer.add(affich);
         mainContainer.add(back);
     ////   backBtn = Statics.createBackBtn(); 
      /////  mainContainer.add(backBtn);
        
     
          
        
        rev.addActionListener(e->{
        
        ReviewForm f=new ReviewForm(res,id_prod);
    
        ///ds.show();  
        }); 
     affich.addActionListener(e->{
            ////Review2Service p = new Review2Service();
         ////  p.findAllReviews(id);new ServiceVoiture().findAllCars();
      ////  ReviewForm f=new ReviewForm(id_prod);
       /*  AllReviewsForm  p = new AllReviewsForm(res,id_prod);
    p.show();
*/
       new Review2Service().findAllReviews(idProduit);
        });
     
        back.addActionListener(e->{
           ////  Resources res = null;
            ////Review2Service p = new Review2Service();
         ////  p.findAllReviews(id);
      ////  ReviewForm f=new ReviewForm(id_prod);
        Produittry2 p = new Produittry2(res);
        p.show();
      //// super.show();  
        });
        
    
        this.add(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE, mainContainer);
        
        
        super.show();
        
       
        

    
 
        
      //  this.add(BorderLayout.NORTH, mainContainer);
        
     

             
             
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
    
    
}
