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
  private final String fileName;
  private final MMOBridge plugin;

  public Optional<FileConfiguration> getGUIList() {
    File file = new File(plugin.getDataFolder() + "/gui", fileName + ".yml");

    if(!file.exists()) {
//      file.getParentFile().mkdirs();
      plugin.saveResource(fileName + ".yml", false);
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
