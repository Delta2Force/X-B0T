package me.Delta2Force.xbot;

import java.util.Scanner;

import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;

public class Main {
	private static JDA jda;
	
	public static void main(String[] args) {
		try {
			//Initialize the Bot using JDA
			jda = new JDABuilder().addListener(new Listener()).setBotToken("TOKEN").buildBlocking(); //TODO: Censor Bottoken before Pushing
			jda.setAutoReconnect(true); //So i dont have to manually Reconnect.
			//Change the "Game" Text
			jda.getAccountManager().setGame("X-B0T // Type x-help");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
