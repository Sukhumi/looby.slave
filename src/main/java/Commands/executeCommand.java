package Commands;

import ReadWriteProperties.configValues;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class executeCommand {
    public static void handler(MessageReceivedEvent event) {


        String[] fullCommand = event.getMessage().getContentRaw().substring(configValues.getPrefix().length()).split(" ");
        String mainCommand = fullCommand[0];
        MessageChannel sendChannel = event.getChannel();
        User messageAuthor = event.getAuthor();
        String messageText = event.getMessage().getContentRaw();

// WTF way of making help work
        if (mainCommand.toLowerCase().equals("help") && fullCommand.length == 2) {
            mainCommand = fullCommand[1];
            fullCommand[0] = fullCommand[1];
            fullCommand[1] = "help";
        }

        switch (mainCommand.toLowerCase()) {

            case "help":
                if (fullCommand.length != 2) {

                    Help.listCommands(sendChannel);
                } else {
                    Help.help(sendChannel);
                }
                break;

            case "ping":
                if (fullCommand.length > 1) {
                    Ping.help(sendChannel);
                    break;
                }
                Ping.ping(sendChannel);
                break;

            case "shutdown":
                if (fullCommand.length > 1) {
                    Shutdown.help(sendChannel);
                    break;
                }
                Shutdown.shutdown(sendChannel, messageAuthor);
                break;

            case "set":

                if (fullCommand.length != 3) {

                    Set.help(sendChannel);
                    return;
                }

                Set.set(sendChannel, fullCommand[1], fullCommand[2]);
                break;

            case "todo":

                if (fullCommand.length < 2) {

                    TODO.help(sendChannel);
                    return;
                }

                TODO.todo(sendChannel, messageAuthor, fullCommand[1], messageText);
                break;

            case "suggest":

                if (fullCommand.length < 2) {

                    Suggest.help(sendChannel);
                    return;
                }

                Suggest.suggest(sendChannel, messageAuthor, fullCommand[1], messageText);
                break;

            case "blackjack":
                Blackjack.init.newGame(messageAuthor, sendChannel);
                break;

        }


    }
}
