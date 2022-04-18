package com.goldfrosch.events;

import com.goldfrosch.MMOBridge;
import com.goldfrosch.utils.ChatUtils;
import io.lumine.mythic.lib.api.item.NBTItem;
import lombok.NoArgsConstructor;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.Indyuce.mmoitems.api.interaction.Consumable;
import net.Indyuce.mmoitems.api.interaction.UseItem;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor
public class PlayerCastingEvent implements Listener {
    private ChatUtils chatUtils;

    private final ConfigurationSection config = MMOBridge.plugin.getConfig();

    @EventHandler
    public void onPlayerChangeHotSlotIfCasting(PlayerItemHeldEvent e) {
        Player player = e.getPlayer();
        PlayerData playerData = PlayerData.get(player);

        if(playerData.isCasting() && e.getNewSlot() >= 5 && e.getNewSlot() <= 8) {
            ItemStack[] invList = player.getInventory().getContents();

            Optional.ofNullable(invList[e.getNewSlot()]).ifPresent(selectItem -> {
                NBTItem nbtItem = NBTItem.get(selectItem);
                if(nbtItem.getType() != null) {
                    UseItem useItem = UseItem.getItem(player, nbtItem, nbtItem.getType());

                    if(useItem instanceof Consumable) {
                        if(useItem.getPlayerData().isOnCooldown(useItem.getMMOItem())) {
                            player.sendMessage(
                                chatUtils.replaceMessagePlaceholderToData(
                                    Objects.requireNonNull(config.getString("message.cast.cooldown")),
                                    "{cooldown}",
                                    String.valueOf(useItem.getPlayerData().getItemCooldown(useItem.getMMOItem()))
                                )
                            );
                        } else {
                            if (useItem.checkItemRequirements()) {
                                Consumable.ConsumableConsumeResult result = ((Consumable) useItem).useOnPlayer(player.getHandRaised(), false);
                                // 아이템 사용 -1
                                if (result == Consumable.ConsumableConsumeResult.CONSUME) {
                                    selectItem.setAmount(selectItem.getAmount() - 1);
                                }
                                // 아이템 실행
                                useItem.getPlayerData().applyItemCooldown(useItem.getMMOItem(), useItem.getNBTItem().getStat("ITEM_COOLDOWN"));
                                useItem.executeCommands();
                            }
                        }
                    }
                }
            });
        }
    }
}
