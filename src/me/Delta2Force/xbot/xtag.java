package me.Delta2Force.xbot;

import java.util.HashMap;
import java.util.Random;

import net.dv8tion.jda.entities.Message;
import net.dv8tion.jda.entities.MessageChannel;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public class xtag {
	//No comments because this is something not supposed to be copied
	//I know that my Variable names are not creative
	static HashMap<String, User> tags = new HashMap<String, User>();
	
	public static String parse(String tagcont, Message msg){
		String tagcontent = tagcont;
		Random r = new Random();
		tagcontent = tagcontent.replace("%avatar%", msg.getAuthor().getAvatarUrl());
		tagcontent = tagcontent.replace("%name%", msg.getAuthor().getUsername());
		if(tagcontent.contains("%randommention%")){
			int lol = r.nextInt(msg.getJDA().getUsers().size() - 1);
			User random = msg.getJDA().getUsers().get(lol);
			tagcontent = tagcontent.replace("%randommention%", random.getAsMention());
		}
		return tagcontent;
	}
	
	public static void add(User by, String content,String name, Message ok){
		boolean exists = false;
		for(String s : tags.keySet()){
			String[] lol = s.split(":°/°:");
			if(lol[0].equals(name)){
				exists = true;
			}
		}
		if(exists){
			ok.getChannel().sendMessage("That Tag already exists.");
		}else{
			String lool = name + ":°/°:" + content;
			tags.put(lool, by);
			ok.getChannel().sendMessage("Tag created!");
		}
	}
	
	public static void list(MessageChannel tc, User author){
		Message m = tc.sendMessage("Collecting your Tags...");
		String lol = "Tags owned by you:\n\n";
		for(String x : tags.keySet()){
			if(tags.get(x) == author){
				String[] lal = x.split(":°/°:");
				lol = lol + "**" + lal[0] + "**\n";
			}
		}
		m.updateMessage(lol);
	}
	
	public static void edit(String name, User author, MessageChannel mc, String newContent){
		Message m = mc.sendMessage("Searching for the Tag...");
		boolean doesntexist = true;
		for(String x : tags.keySet()){
			if(x.startsWith(name + ":°/°:")){
				doesntexist = false;
				if(tags.get(x) != author){
					m.updateMessage("That Tag was not made by you.");
				}else{
					tags.remove(x);
					tags.put(name + ":°/°:" + newContent, author);
					m.updateMessage("You successfully editted the Tag '" + name + "'!");
				}
			}
		}
		if(doesntexist){
			m.updateMessage("Doesn't exist.");
		}
	}
	
	public static void delete(String name, User author, MessageChannel mc){
		Message m = mc.sendMessage("Searching for the Tag...");
		boolean doesntexist = true;
		for(String x : tags.keySet()){
			if(x.startsWith(name + ":°/°:")){
				doesntexist = false;
				if(tags.get(x) != author){
					m.updateMessage("That Tag was not made by you.");
				}else{
					tags.remove(x);
					m.updateMessage("You successfully removed the Tag '" + name + "'!");
				}
			}
		}
		if(doesntexist){
			m.updateMessage("Doesn't exist.");
		}
	}
	
	public static void get(String what, Message g){
		Message m = g.getChannel().sendMessage("Searching for the Tag...");
		boolean exists = false;
		String lolo = "";
		for(String s : tags.keySet()){
			String[] lol = s.split(":°/°:");
			if(lol[0].equals(what)){
				lolo = lol[1];
				exists = true;
			}
		}
		if(exists){
			m.updateMessage("Tag exists. Running through X-Tag...");
			String xd = parse(lolo, g);
			m.updateMessage(xd);
		}else{
			m.updateMessage("This Tag doesn't exist.");
		}
	}
}
