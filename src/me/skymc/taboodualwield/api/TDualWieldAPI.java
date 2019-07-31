package me.skymc.taboodualwield.api;

import me.skymc.taboodualwield.TabooDualWield;
import me.skymc.taboodualwield.managers.AttackManager;
import me.skymc.taboodualwield.managers.CooldownManager;
import org.bukkit.entity.Player;

/**
 * @Author CziSKY
 * @Since 2019-07-31 15:51
 */
public class TDualWieldAPI {
    private TabooDualWield plugin;

    public TDualWieldAPI(TabooDualWield plugin){
        this.plugin = plugin;
    }

    public boolean isOffhandAttacking(Player player){
        return AttackManager.isOffhandAttacking(player);
    }

    public boolean isOffhandHasCooldown(Player player){
        return CooldownManager.isOffhandHasCoolDown(player);
    }

    public double getPlayerOffhandCooldown(Player player){
        if (plugin.getCooldownTimer().get(player) == null){
            return 0.0D;
        }
        return plugin.getCooldownTimer().get(player);
    }



}
