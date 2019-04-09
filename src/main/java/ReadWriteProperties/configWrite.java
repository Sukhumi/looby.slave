package ReadWriteProperties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class configWrite {

    public static void setPropValue(String property, String value) {

        File file = new File(configWrite.class.getClassLoader().getResource(configRead.PROPERTIESFILE).toString().substring(6));

        try (OutputStream out = new FileOutputStream(file)) {

            Properties props = configRead.getInstance().getProperty();
            props.setProperty(property, value);
            props.store(out, null);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
