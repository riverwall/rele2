package sk.hlavco.rele;

import sk.hlavco.clock.Procesy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyFile {

    private static final Logger LOGGER = Logger.getLogger( Procesy.class.getName() );


    private String posledneRelePropety = "posledneRele";
    private String propertyFile = "src/main/resources/Pragotron.properties";

    public int readPosledneReleFromPropertiesFile(){
        try {
            FileInputStream in = new FileInputStream(propertyFile);
            Properties props = new Properties();
            props.load(in);
            in.close();

            String posledneReleS = props.getProperty(posledneRelePropety);

            int posledneRele = Integer.parseInt(posledneReleS);

            LOGGER.log(Level.INFO, "posledne aktivne rele: " + posledneRele);

            return posledneRele;

        }catch (IOException ex){
            ex.printStackTrace();
            return 0;
        }


    }

    public void savePosledneReleToPropsFile(int posledneRele){

        try {
            //read
            FileInputStream in = new FileInputStream(propertyFile);
            Properties props = new Properties();
            props.load(in);
            in.close();

            //write
            FileOutputStream out = new FileOutputStream(propertyFile);
            props.setProperty(posledneRelePropety, String.valueOf(posledneRele));
            props.store(out, null);
            out.close();

            LOGGER.log(Level.INFO, "nove posledne rele ulozene: " + posledneRele);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
