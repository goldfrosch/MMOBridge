package com.goldfrosch.commands;

import com.goldfrosch.MMOBridge;
import com.goldfrosch.utils.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public class Commands extends AbstractCommand {
    private final ChatUtils chatUtils = new ChatUtils();
    private final ConfigurationSection config = MMOBridge.plugin.getConfig();

    public Commands(MMOBridge plugin, String Command) {
        super(plugin,Command);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        String prefix = chatUtils.getMessageInMinecraftColor(
            Objects.requireNonNull(config.getString("message.prefix"))
        );

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(label.equalsIgnoreCase("cmd")){
                if(args.length == 0){
                    player.sendMessage(prefix + "어쩔 티비");
                }
            }
        }
        return false;
    }
}
