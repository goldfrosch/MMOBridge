package com.goldfrosch.commands;

import com.goldfrosch.MMOBridge;
import com.goldfrosch.gui.InventoryGUI;
import com.goldfrosch.utils.ChatUtils;
import com.goldfrosch.utils.FileUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

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
                    switch (args[0]) {
                        case "test":
                            InventoryGUI gui = new InventoryGUI.Builder().player(player).guiLine(5).guiTitle("TEST").build();
                            player.openInventory(gui.getInventoryGUI());
                            break;
                        case "tests":
                            new FileUtils(plugin).getGUIItem("refine").ifPresent((item) -> {
                                plugin.consoleLog(item.getString("menu_title"));
                                for (Object key: Objects.requireNonNull(item.getList("slots"))) {
                                    plugin.consoleLog(key.toString());
                                }
                            });
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return false;
    }
}
