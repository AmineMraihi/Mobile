package com.codename1.uikit.entities;

import com.codename1.uikit.entities.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-08T12:36:14")
@StaticMetamodel(Evenement.class)
public class Evenement_ { 

    public static volatile SingularAttribute<Evenement, Date> date;
    public static volatile SingularAttribute<Evenement, User> idUser;
    public static volatile SingularAttribute<Evenement, String> path;
    public static volatile SingularAttribute<Evenement, Integer> idEvenement;
    public static volatile SingularAttribute<Evenement, String> description;
    public static volatile SingularAttribute<Evenement, String> nom;
    public static volatile SingularAttribute<Evenement, Date> updatedAt;

}