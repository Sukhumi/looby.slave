package Commands;

import net.dv8tion.jda.core.entities.MessageChannel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Help {

    static void listCommands(MessageChannel channel) {

        File directory = new File(Help.class.getResource(".").toString().substring(6));

            List<String> commandList = findClasses(directory);
            String[] hide = hiddenCommands.getHidden();

            Arrays.stream(hide).forEach(commandList::remove);

            channel.sendMessage("Currently implemented commands: " + commandList.toString()).queue();

    }

    static void help(MessageChannel channel)
    {
        channel.sendMessage("Usage: help <command> gives you the help option of a given command").queue();
    }

    private static List<String> findClasses(File directory) {

        List<String> classes = new ArrayList<>();

        if (!directory.exists() || directory.listFiles() == null) {

            return classes;

        }

        for (File file : directory.listFiles()) {

            if (file.getName().endsWith(".class")) {

                classes.add(file.getName().substring(0, file.getName().length() - 6));

            }

        }

        return classes;

    }
}

