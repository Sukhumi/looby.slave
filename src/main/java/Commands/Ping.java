package Commands;

import net.dv8tion.jda.core.entities.MessageChannel;

class Ping {
    static void ping(MessageChannel channel) {
        channel.sendMessage("Pong!").queue();

    }
    static void help(MessageChannel channel){
        channel.sendMessage("Pings le bot!").queue();
    }
}
