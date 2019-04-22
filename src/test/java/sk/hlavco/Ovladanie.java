package sk.hlavco;

import by.creepid.jusbrelay.*;
import org.junit.Test;

public class Ovladanie {

    public Ovladanie() {
    }

    @Test
    public void testujem () {

        UsbRelayManager manager = NativeUsbRelayManager.getInstance();
        try {
            //init manager
            manager.relayInit();
            //enumerate devices
            UsbRelayDeviceInfo[] devices = manager.deviceEnumerate();

            for (int i = 0; i < devices.length; i++) {
                UsbRelayDeviceInfo usbRelayDeviceInfo = devices[i];
                //retrieve device handler
                UsbRelayDeviceHandler handler = manager.deviceOpen(usbRelayDeviceInfo.getSerialNumber());
                //change relay status
                //turning on the relay, index - channel number
                //Get device status
                UsbRelayStatus[] statuses = manager.getStatus(usbRelayDeviceInfo.getSerialNumber(), handler);

                //MAJO
                for (int j = 0; j < 10; j++) {


                    manager.openRelayChannel(handler, 0);

                    Thread.sleep(100);

                    manager.closeRelayChannel(handler, 0);


                    Thread.sleep(400);

                    manager.openRelayChannel(handler, 1);


                    Thread.sleep(100);

                    manager.closeRelayChannel(handler, 1);

                    Thread.sleep(400);
                }

                //MAJO

                //close relay
                manager.closeRelay(handler);
            }
        } catch (UsbRelayException ex) {
            //catch exceptions
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //close manager
            try {
                manager.relayExit();
            } catch (UsbRelayException e) {
                e.printStackTrace();
            }
        }

    }
}
