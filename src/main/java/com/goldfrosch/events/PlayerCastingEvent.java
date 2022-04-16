package com.goldfrosch.events;

import com.goldfrosch.MMOBridge;
import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmoitems.api.interaction.Consumable;
import net.Indyuce.mmoitems.api.interaction.UseItem;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

@RequiredArgsConstructor
public class PlayerCastingEvent implements Listener {
    private final MMOBridge plugin;

    @EventHandler
    public void onPlayerChangeHotSlotIfCasting(PlayerItemHeldEvent e) {
        Player player = e.getPlayer();
        ItemStack[] invList = player.getInventory().getContents();

        Optional.ofNullable(invList[e.getNewSlot()]).ifPresent(selectItem -> {
            Optional.ofNullable(NBTItem.get(selectItem)).ifPresent(nbtItem -> {
                UseItem useItem = UseItem.getItem(player, nbtItem, nbtItem.getType());

                if(useItem instanceof Consumable) {
                    Consumable.ConsumableConsumeResult result = ((Consumable) useItem).useOnPlayer(player.getHandRaised(), false);
                    player.sendMessage(String.valueOf(result));
                    if (result == Consumable.ConsumableConsumeResult.CONSUME) {
                        selectItem.setAmount(selectItem.getAmount() - 1);
                    }
                }

                useItem.getPlayerData().applyItemCooldown(useItem.getMMOItem(), useItem.getNBTItem().getStat("ITEM_COOLDOWN"));
                useItem.executeCommands();
            });
        });
    }
}
