package sk.hlavco.clock;

import by.creepid.jusbrelay.UsbRelayDeviceHandler;

import java.util.Date;
import java.util.EmptyStackException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Procesy {

    private static final Logger LOGGER = Logger.getLogger( Procesy.class.getName() );

//    int INDEX_RELE = 0;

    Delegator dlg = new Delegator();

    int indexRele = 9;


    Timer timerSekundovy = new Timer();

    public static void main(String[] args){

        UsbRelayDeviceHandler handler = null;
        new Procesy().processWithParam(handler, 1000);
    }

    public Timer processWithParam(final UsbRelayDeviceHandler handler, int period) {

        LOGGER.log(Level.INFO, "sekundovy proces bezi");

        TimerTask task = new TimerTask() {

            @Override
            public void run() {

                indexRele = dlg.getPropertyFile().readPosledneReleFromPropertiesFile();

                if(indexRele == 0){
                    indexRele = 1;
                } else if (indexRele == 1){
                    indexRele = 0;
                } else {
                    LOGGER.log(Level.INFO, "chyba v indexe rele");
                    throw new IndexOutOfBoundsException();
                }

                boolean uspesnyImpulz = dlg.getOvladanieRele().impulz(handler, indexRele);
//                boolean uspesnyImpulz = true;


                if(uspesnyImpulz) {
                    dlg.getPropertyFile().savePosledneReleToPropsFile(indexRele);
                }
            }

        };
        timerSekundovy.schedule(task, new Date(), period);

        return timerSekundovy;
    }

    public void closeDevice(UsbRelayDeviceHandler handler){
        dlg.getOvladanieRele().closeDevice(handler);
    }

}
