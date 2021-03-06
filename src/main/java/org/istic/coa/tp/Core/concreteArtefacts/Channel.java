package org.istic.coa.tp.Core.concreteArtefacts;

import org.istic.coa.tp.Core.implementationHelpers.AbstractSubject;
import org.istic.coa.tp.Core.interfaces.AsyncCaptor;
import org.istic.coa.tp.Core.interfaces.Captor;
import org.istic.coa.tp.Core.interfaces.Observer;
import org.istic.coa.tp.Core.utils.ValuesContainer;

import java.util.concurrent.*;

/**
 * Created by stephane on 06/01/16.
 */
public class Channel extends AbstractSubject implements AsyncCaptor, Observer<Captor> {

    protected String name;
    private Captor captor;
    private int delay = 0;
    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(100);
    private static int identifier = 0;

    /**
     * Constrcutor
     *
     * @param captor connected to
     */
    public Channel(Captor captor) {
        name = "Canal_" + ++identifier;
        captor.attach(this);
        this.captor = captor;
        System.out.println(this + ".captor = " + captor);
    }

    public Future<ValuesContainer> getValues() {
        Callable<ValuesContainer> methodRequest= () -> captor.getValues();
        return executorService.schedule(methodRequest, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * Returns the delay in milliseconds
     *
     * @return
     */
    public int getDelay() {
        return delay;
    }

    public void tick() {
        captor.tick();
    }

    /**
     * Configure the transmission delay in milliseconds
     */
    public void setDelay(int delay) {
        this.delay = delay;
        System.out.println(this + ".delay = " + delay);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Void update(Captor subject) {
        observers.forEach(observer -> observer.update(this));
        return null;
    }
}
