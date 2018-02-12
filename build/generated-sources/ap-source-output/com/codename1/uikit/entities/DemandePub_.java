package com.codename1.uikit.entities;

import com.codename1.uikit.entities.Boutique;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-10T15:09:07")
@StaticMetamodel(DemandePub.class)
public class DemandePub_ { 

    public static volatile SingularAttribute<DemandePub, String> path;
    public static volatile SingularAttribute<DemandePub, Integer> idPub;
    public static volatile SingularAttribute<DemandePub, Date> dateDebut;
    public static volatile SingularAttribute<DemandePub, Double> prix;
    public static volatile SingularAttribute<DemandePub, Boutique> idBoutique;
    public static volatile SingularAttribute<DemandePub, Date> dateFin;
    public static volatile SingularAttribute<DemandePub, String> page;
    public static volatile SingularAttribute<DemandePub, Date> updatedAt;

}