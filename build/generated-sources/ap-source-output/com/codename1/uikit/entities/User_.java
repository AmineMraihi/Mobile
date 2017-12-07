package com.codename1.uikit.entities;

import com.codename1.uikit.entities.Boutique;
import com.codename1.uikit.entities.CarteFidelite;
import com.codename1.uikit.entities.Evenement;
import com.codename1.uikit.entities.Reclamation;
import com.codename1.uikit.entities.Review11;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-07T22:18:28")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Date> lastLogin;
    public static volatile SingularAttribute<User, Date> dateNaissance;
    public static volatile SingularAttribute<User, String> roles;
    public static volatile CollectionAttribute<User, Reclamation> reclamationCollection1;
    public static volatile SingularAttribute<User, String> sexe;
    public static volatile SingularAttribute<User, String> emailCanonical;
    public static volatile SingularAttribute<User, String> nom;
    public static volatile SingularAttribute<User, Boolean> enabled;
    public static volatile SingularAttribute<User, Integer> idUser;
    public static volatile SingularAttribute<User, String> path;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Date> dateEmbauche;
    public static volatile SingularAttribute<User, Date> dateExpiration;
    public static volatile SingularAttribute<User, String> confirmationToken;
    public static volatile CollectionAttribute<User, Review11> review11Collection;
    public static volatile SingularAttribute<User, String> prenom;
    public static volatile SingularAttribute<User, Integer> numeroTelephone;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> salt;
    public static volatile CollectionAttribute<User, CarteFidelite> carteFideliteCollection;
    public static volatile SingularAttribute<User, Boutique> idBoutique;
    public static volatile SingularAttribute<User, Double> salaire;
    public static volatile SingularAttribute<User, Date> passwordRequestedAt;
    public static volatile CollectionAttribute<User, Evenement> evenementCollection;
    public static volatile SingularAttribute<User, String> adresse;
    public static volatile CollectionAttribute<User, Reclamation> reclamationCollection;
    public static volatile SingularAttribute<User, String> usernameCanonical;
    public static volatile SingularAttribute<User, String> username;

}