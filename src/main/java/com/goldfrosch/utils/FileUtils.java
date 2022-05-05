package com.goldfrosch.utils;

import com.goldfrosch.MMOBridge;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class FileUtils {
  private final MMOBridge plugin;

  public static FileUtils getInstance(MMOBridge plugin) {
    return new FileUtils(plugin);
  }

  public Optional<FileConfiguration> getGUIItem(String fileName) {
    File file = new File(plugin.getDataFolder() + "/gui", fileName + ".yml");

    if(!file.exists()) {
      file.getParentFile().mkdirs();
      plugin.saveResource("/gui" + fileName + ".yml", true);
    }

    FileConfiguration guiFile = new YamlConfiguration();
    try {
      guiFile.load(file);
      return Optional.of(guiFile);
    } catch (IOException | InvalidConfigurationException e) {
      return Optional.empty();
    }
  }
}
