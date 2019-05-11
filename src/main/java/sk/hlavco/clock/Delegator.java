package sk.hlavco.clock;

import sk.hlavco.rele.OvladanieRele;

public class Delegator {

    public OvladanieRele getOvladanieRele() {
        return new OvladanieRele();
    }

    public Procesy getProcesy() {return new Procesy(); }
}
