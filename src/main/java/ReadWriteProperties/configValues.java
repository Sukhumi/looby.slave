package ReadWriteProperties;

import net.dv8tion.jda.core.JDA;

import java.util.Properties;

public class configValues {

    private static Properties props = configRead.getInstance().getProperty();
    private static String prefix = props.getProperty("prefix");
    private static String token = props.getProperty("token");

    public static void setPrefix(String i) {
        prefix = i;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static String getToken() {
        return token;
    }

    public static JDA builder;

}

