package Blackjack;

import ReadWriteProperties.configValues;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;

public class init {
    static private ArrayList<User> currentPlayers = new ArrayList<>();


    public static void newGame(User messageAuthor, MessageChannel sendChannel){
        if(currentPlayers.contains(messageAuthor)){
            sendChannel.sendMessage("You already have a running game, use 'print' to get state!").queue();
            return;
        }

        currentPlayers.add(messageAuthor);
        configValues.builder.addEventListener(new Game(messageAuthor, sendChannel));
    }

    static void removePlayer(User user){
        currentPlayers.remove(user);
    }
}
