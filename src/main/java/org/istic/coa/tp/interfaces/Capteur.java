package org.istic.coa.tp.interfaces;

/**
 * Created by stephane on 06/01/16.
 */
public interface Capteur extends Subject {
    int getValue();

    void tick();
}
