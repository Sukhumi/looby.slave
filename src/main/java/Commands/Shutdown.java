package Commands;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

class Shutdown {
    static void shutdown(MessageChannel channel, User author) {
        if (author.getId().equals("140192612270211072")) {

            channel.sendMessage(":wave:" +
                    "<:peepoLove:532612837735006210>").queue();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);

        } else channel.sendMessage("Only Looby can take me down!" + "<:AYAYA:534381557100838913>").queue();
    }
    static void help (MessageChannel channel)
    {
        channel.sendMessage("Turns the bot off! Only usable by Looby!").queue();
    }
}
