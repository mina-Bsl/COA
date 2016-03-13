package org.istic.coa.tp.Core;

import org.istic.coa.tp.Core.concreteArtefacts.CaptorScheduleProcessor;
import org.istic.coa.tp.Core.concreteArtefacts.Display;
import org.istic.coa.tp.Core.concreteArtefacts.Channel;
import org.istic.coa.tp.Core.concreteArtefacts.CaptorImpl;
import org.istic.coa.tp.Core.diffusionStrategies.DiffusionType;

/**
 * Created by stephane on 12/01/16.
 */
public class StandAloneLauncher {

    public static void main(String[] args) {

        // The capteur
        CaptorImpl captor = new CaptorImpl();
        captor.setDiffuseStrategy(DiffusionType.SEQUENTIAL);
        CaptorScheduleProcessor process = new CaptorScheduleProcessor(1, 1500, 1);
        process.incrementWithStepByPeriod(captor, 200);

        // First canal
        Channel channel1 = new Channel(captor);
        Display display1 = new Display();
        channel1.setDelay(150);
        channel1.attach(display1);

        // Second canal
        Channel channel2 = new Channel(captor);
        Display display2 = new Display();
        channel2.setDelay(600);
        channel2.attach(display2);
    }
}
