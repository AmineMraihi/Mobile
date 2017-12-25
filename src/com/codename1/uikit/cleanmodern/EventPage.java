/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.facebooksdk.FBConnect;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.tunisiamall.service.EventService;
import com.codename1.ui.AutoCompleteTextField;
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
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.entities.Evenement;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Amine
 */
public class EventPage extends BaseForm {

    List<Evenement> listevenement = new ArrayList<>();
    EncodedImage enc;
    Image img;
    ImageViewer Imgv;
    private Image circleLineImage;
    private Object circleMask;
    private int circleMaskWidth;
    private int circleMaskHeight;
    private Font letterFont;
    private boolean finishedLoading;
    private long lastScroll;
    private boolean messageShown;
//byte data[] ;

    private Resources theme;

    public EventPage(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        setTitle("TunisiaMall Events");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });

        AutoCompleteTextField ac = new AutoCompleteTextField("Short", "Shock", "Sholder", "Shrek");
        ac.setMinimumElementsShownInPopup(5);
        ac.setUIID("aminerecherche");
        add(ac);

        Tabs swipe = new Tabs();
        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("background.png"), spacer1, null, null, null);

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

        EventService es = new EventService();

        System.out.println(es.getAllEvents().size());

//started here this to show a better ui 
//        theme = UIManager.initFirstTheme("/theme");
//        Toolbar.setGlobalToolbar(true);
//        Toolbar.setGlobalToolbar(false);
//        Form shoppingList = new Form("Shop", BoxLayout.y());
//Toolbar tb = new Toolbar(true);
//        shoppingList.setToolbar(tb);
//        tb.setUIID("ShopToolbar");
//        
//        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
//        if(!fab.getUIManager().isThemeConstant("paintsTitleBarBool", false)) {
//            fab.getAllStyles().setMarginTop(18);
//        }
//        fab.bindFabToContainer(shoppingList.getContentPane(), Component.RIGHT, Component.TOP);
//        
//        Button back = new Button("", "Command");
//        FontImage.setMaterialIcon(back, FontImage.MATERIAL_ARROW_BACK);
//        Button search = new Button("", "Command");
//        FontImage.setMaterialIcon(search, FontImage.MATERIAL_SEARCH);
//        Button reply = new Button("", "Command");
//        FontImage.setMaterialIcon(reply, FontImage.MATERIAL_REPLY);
//        
//        Label titleLabel = new Label("Shop", "Title");
//        
//        Container titleAndBack = LayeredLayout.encloseIn(titleLabel,
//                FlowLayout.encloseIn(back));
//        Container titleComponent = BorderLayout.west(titleAndBack).
//                add(BorderLayout.EAST, 
//                        FlowLayout.encloseRight(reply, search)
//                        );
//        tb.setTitleComponent(titleComponent);
//        
//        ComponentAnimation c1 = tb.createStyleAnimation("ShopToolbarShrunk", 200);
//        ComponentAnimation c2 = titleLabel.createStyleAnimation("TitleShrunk", 200);
//        ComponentAnimation c3 = fab.createStyleAnimation("FloatingActionButtonShrunk", 200);
//        shoppingList.getAnimationManager().onTitleScrollAnimation(c1, c2, c3);
//        
//        Label filler = new Label(" ");
//        filler.setPreferredSize(tb.getPreferredSize());
//        shoppingList.add(filler);
//        shoppingList.show();
//ended here
        Container list = new Container(BoxLayout.y());
//        list.setScrollableY(true);

        for (Evenement e : es.getAllEvents()) {
            String brochure = e.getPath();
            EncodedImage img = EncodedImage.createFromImage(
                    Image.createImage(Display.getInstance().getDisplayWidth(), 150), true
            );
            URLImage imgg = URLImage.createToStorage(img, "http://localhost/TestUser/web/images/amine/"
                    + brochure, "http://localhost/TestUser/web/images/amine/" + brochure);
            imgg.fetch();
            ImageViewer imgv = new ImageViewer(imgg);
            int fiveMM = Display.getInstance().convertToPixels(5);
            final Image finalDuke = imgg.scaledWidth(fiveMM);

            list.add(createRankWidget(e.getIdEvenement(), imgg, finalDuke, e.getNom(), e.getDescription()));

            theme = res;

        }
        add(list);

    }

//    started here 
    public SwipeableContainer createRankWidget(int id, URLImage imgg, Image c, String title, String desc) {
        MultiButton button = new MultiButton(title);
//        button.setTextLine2(desc);

        button.setIcon(c);

        Button share = new Button();
        share.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
//                String filename = (String) evt.getSource();
//                MultipartRequest req = new MultipartRequest();
//                String endpoint;
//                endpoint = "https://graph.facebook.com/me/photos?access_token=EAACEdEose0cBAFZB6woF4KZChpkZAlagx2uM7C3zBU9LyU4PzoNgvHH33ICZBgHwLi7e37AcorCxhUqFqwwoor6CZB8Lk35l9Rn1NaUoilImVkAZCVJGk15gFAhWTFXRM2JCODu4P8kYXntzOM93aibRznNE14Q5qgOel3V4xsUX7iZAuEO5PZBeoRIZB6UKe7l1BSzXuEzHE4QZDZD";
//                req.setUrl(endpoint);
//                req.addArgument("message", "test");
//                InputStream is=
//                InputStream is = null;
//                try {
//                    is = FileSystemStorage.getInstance().openInputStream(filename);
//                    req.addData("source", is, FileSystemStorage.getInstance().getLength(filename), "image/jpeg");
//                    NetworkManager.getInstance().addToQueue(req);
//                } catch (IOException ioe) {
//                    ioe.printStackTrace();
//                }

//                String accessToken = "EAACEdEose0cBADg540ggrQ5htVNZBn8AIa7Ej3wjEC7muLoZBOKgKRC1w14QHsQ9qNuR7MpPOSqv4duYZCMPSzg2j5bXnSv98wnKa5ZB46pPhZC9oZCxUNZAz2NYkRaRWjsarbmsRLFNY7yHu0cZBerQ0PPWLthqNTGPjLo8lJhAb7N02xDq5QpAvHd6rkOaHMMfo9u5mQdydwZDZD";
//                FacebookClient fbClient = new DefaultFacebookClient(accessToken);
//                FacebookType response = fbClient.publish("me/feed", FacebookType.class,
//                Parameter.with("test", "test")
//        );
//                Display.getInstance().openImageGallery(new ActionListener() {
//
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt == null) {
//                            return;
//                        }
//                        String filename = (String) evt.getSource();
//                        if (Dialog.show("Send file?", filename, "OK", "Cancel")) {
//                            System.out.println(SignInForm.TOKEN);
//                            MultipartRequest req = new MultipartRequest();
//                            String endpoint;
//                            if (FacebookConnect.getInstance().isFacebookSDKSupported()) {
//                                endpoint = "https://graph.facebook.com/me/photos?access_token=" + FacebookConnect.getInstance().getToken();
//                            } else {
//                                endpoint = "https://graph.facebook.com/me/photos?access_token=" + SignInForm.TOKEN;
//                            }
//                            req.setUrl(endpoint);
//                            req.addArgument("message", "test");
//                            InputStream is = null;
//                            try {
//                                is = FileSystemStorage.getInstance().openInputStream(filename);
//                                req.addData("source", is, FileSystemStorage.getInstance().getLength(filename), "image/jpeg");
//                                NetworkManager.getInstance().addToQueue(req);
//                            } catch (IOException ioe) {
//                                ioe.printStackTrace();
//                            }
//                        }
//
//                    }
//                });
                Message m = new Message("Body of message");
                m.getAttachments().put("text", "text/plain");
                Display.getInstance().sendMessage(new String[]{"destination"}, "this event is great check it out!", m);

//                Display.getInstance().openImageGallery(new ActionListener() {
//
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt == null) {
//                            return;
//                        }
//                        String filename = (String) evt.getSource();
//                        if (Dialog.show("Send file?", filename, "OK", "Cancel")) {
//                            MultipartRequest req = new MultipartRequest();
//                            String endpoint;
//                            if (FacebookConnect.getInstance().isFacebookSDKSupported()) {
//                                endpoint = "https://graph.facebook.com/me/photos?access_token=" + FacebookConnect.getInstance().getToken();
//                            } else {
//                                endpoint = "https://graph.facebook.com/me/photos?access_token=" + com.codename1.facebooksdk.Login.TOKEN;
//                            }
//                            req.setUrl(endpoint);
//                            req.addArgument("message", "test");
//                            InputStream is = null;
//                            try {
//                                is = FileSystemStorage.getInstance().openInputStream(filename);
//                                req.addData("source", is, FileSystemStorage.getInstance().getLength(filename), "image/jpeg");
//                                NetworkManager.getInstance().addToQueue(req);
//                            } catch (IOException ioe) {
//                                ioe.printStackTrace();
//                            }
//                        }
//
//                    }
//                });
            }
        });
        FontImage.setMaterialIcon(share, FontImage.MATERIAL_SHARE, 8);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
//                EventDetailsForm detailsForm=new EventDetailsForm(theme);
//                detailsForm.show();
                EventDetailsForm detailsForm = new EventDetailsForm(id, imgg, c, title, desc, theme);
                detailsForm.show();
            }
        });

//        ShareButton sb = new ShareButton();
//        sb.setUIID("Label");
//        sb.getAllStyles().setAlignment(Component.TOP);
//        String imageFile = FileSystemStorage.getInstance().getAppHomePath()+"goodbadugly.jpg";
//        
//        sb.setImageToShare(imageFile, "image/png");
//        sb.setTextToShare("My Text to share");
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
//            ended here

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
