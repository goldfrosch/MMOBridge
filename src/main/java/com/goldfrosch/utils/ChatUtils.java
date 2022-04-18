package com.goldfrosch.utils;

public class ChatUtils {
  public String getMessageInMinecraftColor(String msg) {
    return msg.replace("&", "§");
  }

  public String replaceMessagePlaceholderToData(String msg, String placeholder, String data) {
    return msg.replace(placeholder, data);
  }
}
