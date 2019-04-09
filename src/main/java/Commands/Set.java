package Commands;

import ReadWriteProperties.configValues;
import ReadWriteProperties.configWrite;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.Arrays;

class Set {


    static void set(MessageChannel channel, String property, String value) {
        switch (property.toLowerCase()) {
            case "prefix":
                prefix(channel, value);
                break;
            default:
                Set.help(channel);
        }
    }


    private static void prefix (MessageChannel channel, String value) {
        final String[] ALLOWEDPREFIXES = {"!", "@", "#", "$", "%", "^", "&", "*",
                "(", ")", "_", "=", "-", "+", ".",
                "<:AYAYA:534381557100838913>","<:vohiyo:245343659724242946>"};
        System.out.println(value);
        if (value.toLowerCase().equals("help")) {

            channel.sendMessage("Changes the command prefixes! Possible prefixes: " + Arrays.toString(ALLOWEDPREFIXES)).queue();
            return;
        }

        if (Arrays.asList(ALLOWEDPREFIXES).contains(value)) {

            configValues.setPrefix(value);
            configWrite.setPropValue("prefix", value);

            channel.sendMessage("Success!").queue();

        } else channel.sendMessage("Prefix not allowed!").queue();
    }

    static void help (MessageChannel channel){
        channel.sendMessage("Usage: set <property> <value>, right now only set prefix implemented.").queue();
    }

}

