package me.Delta2Force.xbot;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import net.dv8tion.jda.Permission;
import net.dv8tion.jda.entities.Message;
import net.dv8tion.jda.entities.Role;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter{
	
	@Override
	//Just for the private x-help command
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
		Message cmd = event.getMessage();
		if(cmd.getRawContent().equals("x-help")){
			//Create the Help String
			String help = "**x-help** Show this!\n**x-ping** Pong.\n**x-avatar** display your Avatar.\n**x-hello** Make me say Hello to you!";
			//Send it
			cmd.getAuthor().getPrivateChannel().sendMessage(help + "\nX-B0T is made by Delta2Force");
		}
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e){
		//Get the message
		Message cmd = e.getMessage();
		//Check what Command is executed
		if(cmd.getRawContent().equals("x-help")){
			//Get roles of user
			List <Role> roles = e.getGuild().getRolesForUser(e.getAuthor());
			//Check if they have the Permission to modify Messages
			boolean permission = false;
			for(Role r : roles){
				if(r.hasPermission(Permission.MESSAGE_MANAGE)){
					permission = true;
				}
			}
			//Create the Help String
<<<<<<< HEAD
			String help = "**x-help** Show this!\n**x-ping** Pong.\n**x-avatar [text]** Send your Avatar, optionally add Text.\n**x-tag-create <tag-name> <tag-content>** Create a Tag.\n**x-tag-use <tag-name>** Use a Tag.\n**x-tag-list** Show all of your Tags.";
=======
			String help = "**x-help** Show this!\n**x-ping** Pong.\n**x-avatar** Display your Avatar.\n**x-hello** Make me say Hello to you!";
>>>>>>> origin/master
			//If they have Permission, show Commands for modifying Messages
			if(permission){
				help = help + "\n**x-clear** Clears the Chat.";
			}
			//Add the Tag Disclaimer
			help = help + "\n\n_All created Tags get removed when the bot gets restarted._";
			//Send it
			cmd.getAuthor().getPrivateChannel().sendMessage(help + "\nX-B0T is made by Delta2Force");
		}
		if(cmd.getRawContent().equals("x-hello")){
			cmd.getChannel().sendMessage("x-Hello " + cmd.getAuthor().getAsMention() + "!");
		}
		if(cmd.getRawContent().startsWith("x-avatar")){
			//Send avatar
			cmd.getChannel().sendMessage(cmd.getAuthor().getAvatarUrl());
		}
		if(cmd.getRawContent().equals("x-ping")){
			//Show "Pong"
			cmd.getChannel().sendMessage("**_Pong._**");
		}
<<<<<<< HEAD
		if(cmd.getRawContent().startsWith("x-tag-create")){
			//Try if the String can be splitted
			try{
				String ok = cmd.getRawContent().substring(13);
				String[] okk = ok.split(" ");
				//Get the name
				String name = okk[0];
				//Checking for errors again
				String ecx = okk[1];
				//Get the Content
				String tagcontent = ok.replaceFirst(name + " ", "");
				//Add the Tag
				xtag.add(cmd.getAuthor(), tagcontent, name, cmd);
				//And print a note to the Console
				System.out.println(cmd.getAuthor().getUsername() + " created the Tag '" + name + "'");
			}catch(Exception exc){
				//Else, display the usage
				cmd.getChannel().sendMessage("**x-tag-create <tag-name> <tag-content>**");
			}
		}
		if(cmd.getRawContent().startsWith("x-tag-use")){
			//Try if the String can be splitted
			try{
				String ok = cmd.getRawContent().substring(10);
				//Get the Name
				String[] okk = ok.split(" ");
				String name = okk[0];
				//Run a XTAG Function
				xtag.get(name, cmd);
			}catch(Exception exc){
				//Else, display the usage
				cmd.getChannel().sendMessage("**x-tag-use <tag-name>**");
			}
		}
		if(cmd.getRawContent().equals("x-tag-list")){
			//Run a function from XTAG
			xtag.list(cmd.getChannel(), cmd.getAuthor());
		}
		if(cmd.getRawContent().equals("x-clear")){
=======
		if(cmd.getRawContent().equals("x-avatar")){
			//Print avatar
			cmd.getChannel().sendMessage(cmd.getAuthor().getAvatarUrl());
		}
		if(cmd.getRawContent().equals("x-clear") && !cmd.isPrivate()){
>>>>>>> origin/master
			//Clear the whole Chat of the Channel
			//Get roles of user
			List <Role> roles = e.getGuild().getRolesForUser(e.getAuthor());
			//Check if they have the Permission to modify Messages
			boolean permission = false;
			for(Role r : roles){
				if(r.hasPermission(Permission.MESSAGE_MANAGE)){
					permission = true;
				}
			}
			//If they have Permission, clear chat
			if(permission){
				//Get the Count of Messages
				int lol = cmd.getChannel().getHistory().retrieveAll().size();
				//Delete all Messages
				for(Message m : cmd.getChannel().getHistory().retrieveAll()){
					m.getChannel().deleteMessageById(m.getId());
				}
				//Delete the last Message
				for(Message m : cmd.getChannel().getHistory().retrieveAll()){
					m.deleteMessage();
				}
				//Show that the messages were deleted
				final Message x = cmd.getChannel().sendMessage(lol + " messages were deleted!");
				final Timer timer = new Timer();
				//Delete the Message in 3 Seconds
				timer.schedule(new TimerTask() {
				    public void run() {
				          x.deleteMessage();
				        timer.cancel();
				    }
				}, 3000);
			}else{
				//Else, dont do it
				final Message x = cmd.getChannel().sendMessage("You cant do that.");
				final Timer timer = new Timer();
				//Delete the Message in 3 Seconds
				timer.schedule(new TimerTask() {
				    public void run() {
				          x.deleteMessage();
				        timer.cancel();
				    }
				}, 3000);
			}
		}
	}
}
