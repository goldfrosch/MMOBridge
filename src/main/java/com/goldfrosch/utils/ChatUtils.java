package com.goldfrosch.utils;

import com.goldfrosch.MMOBridge;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Objects;

public class ChatUtils {
  private final ConfigurationSection config = MMOBridge.plugin.getConfig();

  public String setPrefix(String msg) {
    return Objects.requireNonNull(config.getString("message.prefix")) + msg;
  }

  public String getMessageInMinecraftColor(String msg) {
    return setPrefix(msg.replace("&", "§"));
  }

  public String replaceMessagePlaceholderToData(String msg, String placeholder, String data) {
    return getMessageInMinecraftColor(msg.replace(placeholder, data));
  }
}
