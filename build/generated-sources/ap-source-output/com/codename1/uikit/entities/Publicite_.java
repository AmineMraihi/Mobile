package com.codename1.uikit.entities;

import com.codename1.uikit.entities.Boutique;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-27T19:53:07")
@StaticMetamodel(Publicite.class)
public class Publicite_ { 

    public static volatile SingularAttribute<Publicite, String> path;
    public static volatile SingularAttribute<Publicite, Integer> idPub;
    public static volatile SingularAttribute<Publicite, Date> dateDebut;
    public static volatile SingularAttribute<Publicite, Double> prix;
    public static volatile SingularAttribute<Publicite, Boutique> idBoutique;
    public static volatile SingularAttribute<Publicite, Date> dateFin;
    public static volatile SingularAttribute<Publicite, String> page;
    public static volatile SingularAttribute<Publicite, Date> updatedAt;

}