package com.goldfrosch.events;

import com.goldfrosch.MMOCoreBridge;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class PlayerCastingEvent implements Listener {
    private final MMOCoreBridge plugin;

    @EventHandler
    public void onPlayerChangeHotSlotIfCasting(PlayerItemHeldEvent e) {
        Player player = e.getPlayer();
        ItemStack[] invList = player.getInventory().getContents();

        plugin.consoleLog(
            String.valueOf(invList[e.getNewSlot()].getItemMeta().getAttributeModifiers())
        );
    }
}
