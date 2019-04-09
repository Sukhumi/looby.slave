package Commands;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Suggest {

    private final static String suggestFile = "src/main/resources/Suggests";

    static void suggest(MessageChannel channel, User author, String argument, String text) {
        switch (argument.toLowerCase()) {
            case "add":
                text = text.substring(13);
                add(channel, author, text);
                break;
            case "list":
                list(channel);
                break;
            case "reset":
                reset(channel, author);
                break;
            case "help":
                help(channel);
                break;
            default:
                channel.sendMessage("Wrong arguments, currently implemented add, list and reset").queue();
        }
    }


    private static void add(MessageChannel channel, User author, String text) {

        try (FileWriter out = new FileWriter(suggestFile, true)) {
            out.write("***" + text + "*** - by " + author.getName() + "\n");

        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
        channel.sendMessage("I'll talk to the boss about it!").queue();

    }

    private static void list(MessageChannel channel) {

        try (FileReader input = new FileReader(suggestFile)) {

            StringBuilder messageOutput = new StringBuilder();
            int i;

            while ((i = input.read()) != -1) {

                messageOutput.append((char) i);
            }

            if (messageOutput.length() == 0) {
                channel.sendMessage("I'd suggest more kawaii emotes by who am I to say...").queue();
                return;
            }

            channel.sendMessage("People's ideas: \n" + messageOutput.toString() + "They are cool!").queue();


        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

    private static void reset(MessageChannel channel, User author) {

        if (author.getId().equals("140192612270211072")) {

            File file = new File(suggestFile);
            boolean success = false;

            try {
                success = file.delete();
                success = file.createNewFile();

            } catch (IOException e) {
                System.out.println("Exception: " + e);
            }

            if (success) {

                channel.sendMessage("Success!").queue();

            } else channel.sendMessage("Error").queue();

        } else channel.sendMessage("Only Boss can tell me to throw out your ideas").queue();

    }

    static void help(MessageChannel channel) {
        channel.sendMessage("Manages boss' suggestion list. I can add, list or reset it!").queue();
    }
}
