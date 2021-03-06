/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Storage;
import com.codename1.tunisiamall.service.UserService;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
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
import java.io.IOException;
import java.io.InputStream;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {

    private ConnectionRequest connectionRequest;
    String md5Password;

    private String imgName = "";
    private String imgPath = "";
    Label profile = null;
    private URLImage profilePic;

    boolean imageselected = false;

    public ProfileForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });

        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);

//////////////////////////////////        this will put profile picture in its place 
        String brochure = SignInForm.staticUser.getPath();

        EncodedImage imgprofile = EncodedImage.createFromImage(
                Image.createImage(Display.getInstance().getDisplayWidth(), 150), true
        );
        URLImage imgg = URLImage.createToStorage(
                imgprofile, "http://localhost/TestUser/web/images/amine/" + brochure,
                "http://localhost/TestUser/web/images/amine/" + brochure
        );
        imgg.fetch();
        ImageViewer imgv = new ImageViewer(imgg);
        int fiveMM = Display.getInstance().convertToPixels(20);
        final Image finalDuke = imgg.scaledWidth(fiveMM);
//        addStringValue("image", finalDuke);
//        add(finalDuke);
//////////////////////////////////

        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                facebook,
                                FlowLayout.encloseCenter(
                                        //new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
                                        new Label(finalDuke, "nothing_to_see_here")),
                                twitter
                        )
                )
        ));
//started here   this will allow user to select profile picture
        profile = new Label();
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_IMAGE);
        fab.addActionListener((ActionListener) (ActionEvent evt) -> {
            Display.getInstance().openGallery((ActionListener) (ActionEvent ev) -> {
                if (ev != null && ev.getSource() != null) {
                    imgPath = (String) ev.getSource();
                    int fileNameIndex = imgPath.lastIndexOf("/") + 1;
                    imgName = imgPath.substring(fileNameIndex);

                    try {

                        InputStream is = FileSystemStorage.getInstance().openInputStream(imgPath);
                        Image im = Image.createImage(is);

                        Image listingMask = res.getImage("refimg2.jpg");

//                        profile.setIcon(im.fill(listingMask.getWidth(), listingMask.getHeight()));
//                        refreshTheme();
                        System.out.println("imgpath: " + imgPath);
                        System.out.println("imgname: " + imgName);
                        imageselected = true;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }, Display.GALLERY_IMAGE);
        });
        addStringValue("profile pic*", fab);

//ended here
//        this will show user profile pic
//        Container gui_Image = new Container(new FlowLayout(CENTER, CENTER));
//        String path = "C:/wamp64/www/TestUser/web/images/amine/" + SignInForm.staticUser.getPath();
//        EncodedImage placeholder = EncodedImage.createFromImage(res.getImage("profile-pic.jpg"), false);
//        Image refimg = res.getImage("profile-pic.jpg");
//        Image mask = Image.createImage(refimg.getWidth(), refimg.getHeight(), 0xff000000);
//        Storage.getInstance().clearStorage();
//        Storage.getInstance().clearCache();
//        profilePic = URLImage.createToStorage(placeholder, (String) SignInForm.staticUser.getUsername(), path,
//                URLImage.RESIZE_SCALE_TO_FILL);
//        profilePic.fetch();
//        Graphics gr = mask.getGraphics();
//        gr.setColor(0xffffff);
//        gr.fillArc(0, 0, mask.getWidth(), mask.getWidth(), 0, 360);
//        Object endMask = mask.createMask();
//        gui_Image.add(profilePic.fill(mask.getWidth(), mask.getHeight()).applyMask(endMask));
//        addStringValue("image", gui_Image);
//////////////
        EncodedImage placeholder = EncodedImage.createFromImage(res.getImage("profile-pic.jpg"), false);
        Image refimg = res.getImage("profile_image.png");
//        Image mask = Image.createImage(refimg.getWidth(), refimg.getHeight(), 0xff000000);
        Storage.getInstance().clearStorage();
        Storage.getInstance().clearCache();
        profilePic = URLImage.createToStorage(placeholder, SignInForm.staticUser.getUsername(), "http://localhost/TestUser/web/images/amine/" + brochure,
                URLImage.RESIZE_SCALE_TO_FILL);
        try {
            profilePic.fetch();
        } catch (RuntimeException r) {
        }
//////////////
//end of this
        TextField username = new TextField(SignInForm.staticUser.getUsername(), "Username", 20, TextField.ANY);
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);

        TextField nom = new TextField(SignInForm.staticUser.getNom(), "nom", 20, TextField.ANY);
        nom.setUIID("TextFieldBlack");
        addStringValue("nom", nom);

        TextField prenom = new TextField(SignInForm.staticUser.getPrenom(), "prenom", 20, TextField.ANY);
        prenom.setUIID("TextFieldBlack");
        addStringValue("prenom", prenom);

        TextField email = new TextField(SignInForm.staticUser.getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);

        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password*", password);
        Label InfoAboutPassword1 = new Label("by leaving (*) empty, ");
        add(InfoAboutPassword1);
        Label InfoAboutPassword2 = new Label("you accept the older one");
        add(InfoAboutPassword2);

        Button save = new Button("save");
        Button delete = new Button("delete account");

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if (password.getText() == "") {
                    md5Password = SignInForm.staticUser.getPassword();
                } else {
                    MD5 md5 = new MD5(password.getText());
                    md5Password = md5.asHex();
                }

                if (!imageselected) {
                    imgName = SignInForm.staticUser.getPath();
                    imgPath = "http://localhost/TestUser/web/images/amine/";
                }

                connectionRequest = new ConnectionRequest() {
                    @Override
                    protected void postResponse() {
                        Dialog.show("Succes", "modification avec succes", "ok", null);

                    }
                };
                System.out.println("static user pic: " + SignInForm.staticUser.getPath());
                UserService userService = new UserService();

                if (userService.verifyUsernameforupdate(username.getText(), SignInForm.staticUser.getIdUser())) {
                    System.out.println("username exists");
                    ToastBar.showMessage("username already exists", FontImage.MATERIAL_COMPARE_ARROWS, 2000);

                } else if (userService.verifyMailforupdate(email.getText(), SignInForm.staticUser.getIdUser())) {
                    System.out.println("mail exists");
                    ToastBar.showMessage("mail already exists", FontImage.MATERIAL_COMPARE_ARROWS, 2000);

                } else {
                    userService.updateuser(SignInForm.staticUser.getIdUser(),
                            username.getText(),
                            nom.getText(),
                            prenom.getText(),
                            md5Password,
                            email.getText(),
                            imgName,
                            imgPath
                    );
                }

            }

        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (Dialog.show("delete account", "are you sure", "ok", "cancel")) {
                    UserService userService = new UserService();
                    userService.deleteAccount(SignInForm.staticUser.getIdUser());
                    // ToastBar.showMessage("account deleted", FontImage.MATERIAL_DELETE, 5000);
                    new SignInForm(res).show();

                }
            }
        });

        add(save);
        add(delete);

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
