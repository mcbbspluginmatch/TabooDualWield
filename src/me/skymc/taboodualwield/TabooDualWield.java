package me.skymc.taboodualwield;

import me.skymc.taboodualwield.managers.AttackManager;
import me.skymc.taboolib.common.configuration.TConfiguration;
import me.skymc.taboolib.common.inject.TInject;
import me.skymc.taboolib.plugin.PluginUtils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author CziSKY
 * @Since 2019-06-19 5:53
 */
public class TabooDualWield extends JavaPlugin {

    @TInject
    private static TabooDualWield inst;
    private static TConfiguration conf;
    private Map<Player, Boolean> isCooldown;
    private Map<Player, Double> cooldownTimer;

    public Map<Player, Boolean> getIsCooldown() {
        return isCooldown;
    }

    public Map<Player, Double> getCooldownTimer() {
        return cooldownTimer;
    }
    public static TConfiguration getConf() {
        return conf;
    }
    public static TabooDualWield getInst() {
        return inst;
    }

    public TabooDualWield(){
        this.isCooldown = new HashMap<>();
        this.cooldownTimer = new HashMap<>();
    }

    public void onEnable(){
        if (!(TabooLibSetup.checkVersion(this, 4.86))){
            PluginUtils.unload(this);
            return;
        }
        new TDualWieldCommand();
        new AttackManager();
        conf = TConfiguration.createInResource(this, "config.yml");
        conf.listener(() -> getLogger().info("Configuration reloaded.")).runListener();
        inst = this;
    }

}
