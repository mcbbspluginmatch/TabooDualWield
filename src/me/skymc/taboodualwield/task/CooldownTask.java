package me.skymc.taboodualwield.task;

import com.ilummc.tlib.resources.TLocale;
import me.skymc.taboodualwield.TabooDualWield;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @Author CziSKY
 * @Since 2019-07-05 2:57
 */
public class CooldownTask  extends BukkitRunnable {

    private double timer;
    private Player player;
    private boolean actionbarRender;

    public CooldownTask(Player player, Double cooldown, boolean actionbarRender) {
        this.timer = cooldown;
        this.player = player;
        this.actionbarRender = actionbarRender;
        TabooDualWield.getInst().getCooldownTimer().put(player, cooldown);
        TabooDualWield.getInst().getIsCooldown().put(player, true);
    }

    public void run() {
        if (this.timer < 0.2) {
            TabooDualWield.getInst().getIsCooldown().remove(this.player);
            TabooDualWield.getInst().getCooldownTimer().remove(this.player);
            this.cancel();
        }
        this.timer -= 0.1;
        TabooDualWield.getInst().getCooldownTimer().put(this.player, Double.valueOf(String.format("%.2f", this.timer)));
        if (this.actionbarRender){
            TLocale.sendTo(this.player, "COOLDOWN-ACTION-BAR", Double.toString(TabooDualWield.getInst().getCooldownTimer().get(player)));
        }
    }
}
