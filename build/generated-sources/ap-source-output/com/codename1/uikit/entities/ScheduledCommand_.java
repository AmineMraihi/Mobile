package com.codename1.uikit.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-12T13:30:13")
@StaticMetamodel(ScheduledCommand.class)
public class ScheduledCommand_ { 

    public static volatile SingularAttribute<ScheduledCommand, String> cronExpression;
    public static volatile SingularAttribute<ScheduledCommand, Integer> lastReturnCode;
    public static volatile SingularAttribute<ScheduledCommand, Boolean> bLocked;
    public static volatile SingularAttribute<ScheduledCommand, Integer> idScheduledCommand;
    public static volatile SingularAttribute<ScheduledCommand, String> logFile;
    public static volatile SingularAttribute<ScheduledCommand, Boolean> bExecuteImmediately;
    public static volatile SingularAttribute<ScheduledCommand, String> name;
    public static volatile SingularAttribute<ScheduledCommand, String> arguments;
    public static volatile SingularAttribute<ScheduledCommand, Integer> priority;
    public static volatile SingularAttribute<ScheduledCommand, Date> dhLastExecution;
    public static volatile SingularAttribute<ScheduledCommand, Boolean> bDisabled;
    public static volatile SingularAttribute<ScheduledCommand, String> command;

}