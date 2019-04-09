package Commands;

import ReadWriteProperties.configValues;
import net.dv8tion.jda.core.entities.MessageChannel;

public class Identify {
    public static void identify(MessageChannel channel) {
        channel.sendMessage("**My command prefix is: `" + configValues.getPrefix() + "`**").queue();
    }
}
