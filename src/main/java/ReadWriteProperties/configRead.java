package ReadWriteProperties;

import java.io.*;
import java.util.Properties;

class configRead {

    final static String PROPERTIESFILE = "config/config.properties";
    private static configRead instance = null;
    private Properties props = new Properties();


    private configRead() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(PROPERTIESFILE)) {
            this.props.load(in);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    static configRead getInstance() {
        if (instance == null) {
            instance = new configRead();
        }
        return instance;
    }

    Properties getProperty() {
        return this.props;
    }
}
