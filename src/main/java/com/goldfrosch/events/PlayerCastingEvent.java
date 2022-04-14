package com.goldfrosch.events;

import com.goldfrosch.MMOBridge;
import lombok.RequiredArgsConstructor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class PlayerCastingEvent implements Listener {
    private final MMOBridge plugin;

    @EventHandler
    public void onPlayerChangeHotSlotIfCasting(PlayerItemHeldEvent e) {
        Player player = e.getPlayer();
        ItemStack[] invList = player.getInventory().getContents();

        if(invList[e.getNewSlot()] != null) {
            String getItemData = String.valueOf(
                invList[e.getNewSlot()].
                getItemMeta().getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED)
            );

            if(!getItemData.isEmpty()) {
                if(getItemData.contains("name=mmoitemsDecoy")) {
                    player.sendMessage("테스트 완료");
                }
            }
        }
    }
}
