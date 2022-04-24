package com.goldfrosch.commands;

import com.goldfrosch.MMOBridge;
import com.goldfrosch.gui.InventoryGUI;
import com.goldfrosch.utils.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Commands extends AbstractCommand {
    private final ChatUtils chatUtils = new ChatUtils();

    public Commands(MMOBridge plugin, String Command) {
        super(plugin,Command);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(label.equalsIgnoreCase("mmobridge")){
                if(args.length == 0){
                    player.sendMessage(chatUtils.getMessageInMinecraftColor("어쩔 티비"));
                }
                else {
                    if(args[0].equalsIgnoreCase("test")) {
                        InventoryGUI gui = new InventoryGUI.Builder().player(player).guiLine(5).guiTitle("TEST").build();
                        player.openInventory(gui.getInventoryGUI());
                    }
                }
            }
        }
        return false;
    }
}
