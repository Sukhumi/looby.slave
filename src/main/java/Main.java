import ReadWriteProperties.configValues;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;


public class Main extends ListenerAdapter {

    public static void main(String[] args) throws LoginException {

        configValues.builder = new JDABuilder(configValues.getToken()).build();
        configValues.builder.addEventListener(new Main());

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        System.out.println(
                event.getAuthor().getName() + ": " +
                        event.getMessage().getContentRaw()
        );

        if (event.getMessage().getContentRaw().startsWith(configValues.getPrefix())) {
            Commands.executeCommand.handler(event);
            return;
        }

        if (event.getMessage().getContentRaw().equals("<@532565056018186260>"))
            Commands.Identify.identify(event.getMessage().getChannel());
    }

}

