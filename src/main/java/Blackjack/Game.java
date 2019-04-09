package Blackjack;

import ReadWriteProperties.configValues;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Game extends ListenerAdapter {
    private Player dealer;
    private Player player;
    private Deck deck;
    private boolean reshuffleFlag = false;
    private MessageChannel channel;
    private Player currentplayer;
    private User user;

    public Game(User player, MessageChannel channel) {
        this.user = player;
        this.channel = channel;
        this.player = new Player(player.getName());
        this.dealer = new Player("Dealer");
        this.currentplayer = this.player;
        this.deck = new Deck(3);
        drawCard();
        currentplayer = this.dealer;
        drawCard();
        currentplayer = this.player;
        drawCard();
        printGamestate();
    }

    private void drawCard() {
        Card card = this.deck.drawCard();

        if (card.getID() == 0) {
            reshuffleFlag = true;
            drawCard();
            return;
        }

        this.currentplayer.getCard(card);
    }

    private void gameLogicUpdate() {
        if (this.player.getblackJackValue() == 21) {
            gameWin();
            return;
        }
        if (this.player.getblackJackValue() > 21) {
            gameLost();
            return;
        }
        if (this.currentplayer == this.player && this.player.getblackJackValue() < 21) {
            printGamestate();
            return;
        }
        if (this.currentplayer == this.dealer && this.dealer.getblackJackValue() < 16 && this.dealer.getblackJackValue() < this.player.getblackJackValue()) {
            drawCard();
            gameLogicUpdate();
            return;
        }
        if (this.currentplayer == this.dealer && this.dealer.getblackJackValue() > 21) {
            gameWin();
            return;
        }
        if (this.currentplayer == this.dealer && this.dealer.getblackJackValue() >= this.player.getblackJackValue()) {
            gameLost();
            return;
        }
        gameWin();

    }

    private void printGamestate() {
        this.channel.sendMessage(String.format("%s %n%s", dealer.toString(), player.toString())).queue();
    }

    private void gameWin() {
        printGamestate();
        this.channel.sendMessage("\nYOU WIN!").queue();
        init.removePlayer(this.user);
        configValues.builder.removeEventListener(this);
    }

    private void gameLost() {
        printGamestate();
        this.channel.sendMessage("\nYou lost :(").queue();
        init.removePlayer(this.user);
        configValues.builder.removeEventListener(this);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        System.out.println(
                "blackjack" +
                        event.getAuthor().getName() + ": " +
                        event.getMessage().getContentRaw()
        );

        if (event.getMessage().getAuthor().isBot() || !event.getMessage().getAuthor().equals(this.user)) return;


        if (event.getMessage().getContentRaw().equals("hit")) {
            drawCard();
            gameLogicUpdate();
        }

        if (event.getMessage().getContentRaw().equals("hold")) {
            this.currentplayer = this.dealer;
            gameLogicUpdate();
        }

        if (event.getMessage().getContentRaw().equals("print")) {
            printGamestate();
        }
    }
}
