package org.istic.coa.tp.Core.concreteArtefacts;

import org.istic.coa.tp.Core.diffusionStrategies.DiffusionType;
import org.istic.coa.tp.Core.interfaces.Captor;
import org.istic.coa.tp.Core.interfaces.DiffusionStrategy;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Allow to manage the value computation of a captor
 *
 */
public class CaptorScheduleProcessor {

    private Timer timer = new Timer();
    private int max;
    private int min;
    private int step;

    /**
     * Define the processor
     *
     * @param min
     * @param max
     * @param step
     */
    public CaptorScheduleProcessor(int min, int max, int step) {
        this.min = min;
        this.max = max;
        this.step = step;
    }

    /**
     * Increment the captor by step
     *
     * @param captor
     * @param perdiodInMiliSeconds
     */
    public void incrementWithStepByPeriod(CaptorImpl captor, double perdiodInMiliSeconds) {
        TimerTask task = new TimerTask() {
            public void run() {
                double time = 0;
                if (captor.getDiffusionStrategy().getDiffusionType() == DiffusionType.EPOC) {
                    time = captor.getValues().getTime() + 0.01;
                }
                // Increament value in N by 1 and time by 0.01
                int newValue = captor.getValues().getValue() + step;
                if (newValue >= max) {
                    newValue = min;
                }
                captor.setValues(
                        new CaptorValuesContainer(newValue, time)
                );
                captor.tick();
            }
        };
        timer.schedule(task, 0, (long) perdiodInMiliSeconds);
    }

    /**
     * Randomly change the captor value
     *
     * @param captor
     * @param perdiodInMiliSeconds
     */
    public void randomIncrementByPeriod(CaptorImpl captor, double perdiodInMiliSeconds) {
        TimerTask task = new TimerTask() {
            public void run() {
                double time = 0;
                if (captor.getDiffusionStrategy().getDiffusionType() == DiffusionType.EPOC) {
                    time = captor.getValues().getTime() + 0.01;
                }
                // Increament value in N by 1 and time by 0.01
                captor.setValues(
                        new CaptorValuesContainer((int)(Math.random() * (max - min) + min), time)
                );
                captor.tick();
            }
        };
        timer.schedule(task, 0, (long) perdiodInMiliSeconds);
    }
}