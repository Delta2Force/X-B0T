package me.Delta2Force.xbot;

import java.util.Timer;
import java.util.TimerTask;

import net.dv8tion.jda.entities.Message;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter{
	@Override
	public void onMessageReceived(MessageReceivedEvent e){
		//Get the message
		Message cmd = e.getMessage();
		//Check what Command is executed
		if(cmd.getRawContent().equals("x-help")){
			//Show help
			cmd.getChannel().sendMessage("**x-help** Show this!");
			cmd.getChannel().sendMessage("**x-ping** Pong.");
		}
		if(cmd.getRawContent().equals("x-ping")){
			//Echo "Pong"
			cmd.getChannel().sendMessage("**_Pong._**");
		}
		if(cmd.getRawContent().equals("x-clear")){
			//Clear the whole Chat of the Channel.
			int lol = cmd.getChannel().getHistory().retrieveAll().size();
			for(Message m : cmd.getChannel().getHistory().retrieveAll()){
				m.getChannel().deleteMessageById(m.getId());
			}
			for(Message m : cmd.getChannel().getHistory().retrieveAll()){
				m.deleteMessage();
			}
			final Message x = cmd.getChannel().sendMessage(lol + " messages were deleted!");
			final Timer timer = new Timer();

			timer.schedule(new TimerTask() {
			    public void run() {
			          x.deleteMessage();
			        timer.cancel();
			    }
			}, 3000);
		}
	}
}
