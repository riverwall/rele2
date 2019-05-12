package sk.hlavco.clock;

import sk.hlavco.rele.OvladanieRele;
import sk.hlavco.rele.PropertyFile;

public class Delegator {

    public OvladanieRele getOvladanieRele() {
        return new OvladanieRele();
    }

    public Procesy getProcesy() {return new Procesy(); }

    public PropertyFile getPropertyFile() {return new PropertyFile(); }
}
