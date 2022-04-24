package com.goldfrosch.gui;

import com.goldfrosch.utils.ChatUtils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

@Getter
public class InventoryGUI {
  private final Inventory inventoryGUI;
  private final Player player;
  private final int guiLine;
  private final String guiTitle;

  public static class Builder {
    private Player player = null;
    private int guiLine = 1;
    private String guiTitle = new ChatUtils().getMessageInMinecraftColor("&f제목 없음");

    public Builder player(Player value) {
      this.player = value;
      return this;
    }

    public Builder guiLine(int value) {
      guiLine = value;
      return this;
    }

    public Builder guiTitle(String value) {
      guiTitle = value;
      return this;
    }

    public InventoryGUI build() {
      return new InventoryGUI(this);
    }
  }
  
  private InventoryGUI(Builder builder) {
    player = builder.player;
    guiLine = builder.guiLine;
    guiTitle = builder.guiTitle;
    inventoryGUI = Bukkit.createInventory(null, guiLine * 9, guiTitle);
  }
}
