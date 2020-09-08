package cn.decision01.danmakuvote.Utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class FileUtil {
    public static FileConfiguration getFileConfig(Plugin _plugin, String _fileName) {
        File f = new File(_plugin.getDataFolder(), _fileName);
        if (!f.exists()) {
            _plugin.saveResource(_fileName, true);
            f = new File(_plugin.getDataFolder(), _fileName);
        }
        return YamlConfiguration.loadConfiguration(f);
    }
}
