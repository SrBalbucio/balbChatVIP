package me.balbucio.chatvip.commands;

import me.balbucio.chatvip.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class AdminCommand extends Command{
	
	public AdminCommand() {
		super("chatvip");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		String prefix = Main.getInstance().messages.getString("prefix").replace("&", "§");
		if(args.length == 0) {
			sender.sendMessage(Main.getInstance().messages.getString("help").replace("&", "§").replace("{prefix}", prefix));
		}
		
		String arg = args[0];
		if(arg.equalsIgnoreCase("silenciar") || arg.equalsIgnoreCase("mute")){
			
		}
	}	
}
