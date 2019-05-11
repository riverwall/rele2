package sk.hlavco.rele;

import by.creepid.jusbrelay.*;

public class OvladanieRele{

    public UsbRelayDeviceHandler openDevice() {

        UsbRelayManager manager = NativeUsbRelayManager.getInstance();
        try {
            //init manager
            manager.relayInit();
            //enumerate devices
            UsbRelayDeviceInfo[] devices = manager.deviceEnumerate();

            if (devices == null || devices.length <= 0) {
                return null;
            }

            UsbRelayDeviceInfo usbRelayDeviceInfo = devices[0];
            //retrieve device handler
            UsbRelayDeviceHandler handler = manager.deviceOpen(usbRelayDeviceInfo.getSerialNumber());

            return handler;

        } catch (UsbRelayException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void closeDevice(UsbRelayDeviceHandler handler){

        UsbRelayManager manager = NativeUsbRelayManager.getInstance();
        try {
            //init manager
            manager.relayInit();
            manager.closeRelay(handler);
            manager.relayExit();

        } catch (UsbRelayException ex) {
            ex.printStackTrace();
        }
    }

    public boolean impulzSInicializaciou(int indexRele) throws UsbRelayException, InterruptedException {

        UsbRelayManager manager = NativeUsbRelayManager.getInstance();
        try {
            //init manager
            manager.relayInit();
            //enumerate devices
            UsbRelayDeviceInfo[] devices = manager.deviceEnumerate();

            if (devices == null || devices.length <= 0) {
                return false;
            }

            UsbRelayDeviceInfo usbRelayDeviceInfo = devices[0];
            //retrieve device handler
            UsbRelayDeviceHandler handler = manager.deviceOpen(usbRelayDeviceInfo.getSerialNumber());

            manager.openRelayChannel(handler, 0);

            Thread.sleep(200);

            manager.closeRelayChannel(handler, 0);

            manager.closeRelay(handler);

            return true;

        } catch (UsbRelayException ex) {
            ex.printStackTrace();
            return false;

        } finally {
        //close manager
        try {
            manager.relayExit();
        } catch (UsbRelayException e) {
            e.printStackTrace();
        }
    }
    }

    public boolean impulz(UsbRelayDeviceHandler handler, int indexRele){

        UsbRelayManager manager = NativeUsbRelayManager.getInstance();

        try {
            manager.openRelayChannel(handler, indexRele);

            Thread.sleep(100);

            manager.closeRelayChannel(handler, indexRele);

        } catch (UsbRelayException e) {
            e.printStackTrace();
            return false;

        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

//    public void stukajRelatkami () {
//
//        UsbRelayManager manager = NativeUsbRelayManager.getInstance();
//        try {
//            //init manager
//            manager.relayInit();
//            //enumerate devices
//            UsbRelayDeviceInfo[] devices = manager.deviceEnumerate();
//
//            for (int i = 0; i < devices.length; i++) {
//                UsbRelayDeviceInfo usbRelayDeviceInfo = devices[i];
//                //retrieve device handler
//                UsbRelayDeviceHandler handler = manager.deviceOpen(usbRelayDeviceInfo.getSerialNumber());
//                //change relay status
//                //turning on the relay, index - channel number
//                //Get device status
//                UsbRelayStatus[] statuses = manager.getStatus(usbRelayDeviceInfo.getSerialNumber(), handler);
//
//                //MAJO
//                for (int j = 0; j < 10; j++) {
//
//
//                    manager.openRelayChannel(handler, 0);
//
//                    Thread.sleep(100);
//
//                    manager.closeRelayChannel(handler, 0);
//
//
//                    Thread.sleep(400);
//
//                    manager.openRelayChannel(handler, 1);
//
//
//                    Thread.sleep(100);
//
//                    manager.closeRelayChannel(handler, 1);
//
//                    Thread.sleep(400);
//                }
//
//                //MAJO
//
//                //close relay
//                manager.closeRelay(handler);
//            }
//        } catch (UsbRelayException ex) {
//            //catch exceptions
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            //close manager
//            try {
//                manager.relayExit();
//            } catch (UsbRelayException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }

}
