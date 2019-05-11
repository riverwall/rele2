package sk.hlavco.clock;

import by.creepid.jusbrelay.UsbRelayDeviceHandler;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Procesy {

    int INDEX_RELE = 0;

    Delegator dlg = new Delegator();


    Timer timerSekundovy = new Timer();

    public static void main(String[] args){

        new Procesy().procesSekundovy();
    }

    public void procesSekundovy() {

        //otvor kartu
        UsbRelayDeviceHandler handler = dlg.getOvladanieRele().openDevice();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                boolean uspesnyImpulz = dlg.getOvladanieRele().impulz(handler, INDEX_RELE);

            }
        };
        timerSekundovy.schedule(task, new Date(), 1000);

        //odpoj kartu
        dlg.getOvladanieRele().closeDevice(handler);
    }

}
