package Commands;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


class TODO{

    private final static String todoFile = "src/main/resources/TODO";

    static void todo(MessageChannel channel, User author, String argument, String text) {
        switch (argument.toLowerCase()) {
            case "add":
                text = text.substring(10);
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

        if (author.getId().equals("140192612270211072")) {

            try (FileWriter out = new FileWriter(todoFile, true)) {
                out.write("**" + text + "**\n");

            } catch (IOException e) {
                System.out.println("Exception: " + e);
            }
            channel.sendMessage("Noted boss!").queue();

        } else
            channel.sendMessage("Only Looby can tell himself what to do xD\n Use the suggestion command please.").queue();
    }

    private static void list(MessageChannel channel) {

        try (FileReader input = new FileReader(todoFile)) {

            StringBuilder messageOutput = new StringBuilder();
            int i;

            while ((i = input.read()) != -1) {

                messageOutput.append((char) i);
            }

            if (messageOutput.length() == 0) {
                channel.sendMessage("Boss thinks im perfect! <:uwu:534420148061208597>").queue();
                return;
            }

            channel.sendMessage("Boss' TODO list: \n" + messageOutput.toString()).queue();


        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

    private static void reset(MessageChannel channel, User author) {

        if (author.getId().equals("140192612270211072")) {

            File file = new File(todoFile);
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

        } else channel.sendMessage("Only Boss can tell me if im perfect!").queue();

    }

    static void help(MessageChannel channel) {
        channel.sendMessage("Manages boss' todo list. I can add, list or reset it!").queue();
    }

}
