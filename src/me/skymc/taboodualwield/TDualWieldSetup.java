package me.skymc.taboodualwield;

import me.skymc.taboolib.TabooLib;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.NumberConversions;

/**
 * @Author CziSKY
 * @Since 2018-09-25 23:42
 */
public class TDualWieldSetup {
    public static boolean checkTLibVersion(Plugin plugin, double requiredVersion) {
        if (NumberConversions.toDouble(TabooLib.instance().getDescription().getVersion()) <= requiredVersion) {
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "#################### 错误 ####################");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "  插件无法正常启动!");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "  因为您的 §4TabooLib §c插件版本过低.");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "  插件最低要求版本为 §4v" + requiredVersion);
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "  下载地址:");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "  §4https://github.com/Bkm016/TabooLib/releases");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "#################### 错误 ####################");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "");
            return false;
        }
        return true;
    }
    public static boolean checkServerVersion(Plugin plugin, double requiredVersion) {
        if (TabooLib.getVersionNumber() <= requiredVersion) {
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "#################### 错误 ####################");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "  插件无法正常启动!");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "  因为您的 §4服务器 §c核心版本过低.");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "  核心最低要求版本为 §4v" + "1.9");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "  请更新您的服务端版本亦能使用本插件!");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "#################### 错误 ####################");
            Bukkit.getConsoleSender().sendMessage("§8[§4§l" + plugin.getName() + "§8] §c" + "");
            return false;
        }
        return true;
    }

}
