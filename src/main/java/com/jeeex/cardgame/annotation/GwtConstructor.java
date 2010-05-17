package com.jeeex.cardgame.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Documentation purpose annotation.
 * <p>
 * Used to annotate no-arg constructors of {@link IsSerializable} with nonpublic
 * access (since no-arg constructors are required for GWT).
 * 
 * @author Jeeyoung Kim
 */
@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.SOURCE)
public @interface GwtConstructor {

}
