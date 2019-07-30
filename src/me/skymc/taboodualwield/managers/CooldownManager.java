package me.skymc.taboodualwield.managers;

import com.ilummc.tlib.resources.TLocale;
import me.skymc.taboodualwield.TabooDualWield;
import me.skymc.taboodualwield.task.CooldownTask;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author CziSKY
 * @Since 2019-07-05 23:52
 */
public class CooldownManager {
    public static void makeOffhandCooldown(Player player, Double tick){
        boolean actionbarRender = TabooDualWield.getConf().getBoolean("ATTACK-COOLDOWN.ACTIONBAR-DISPLAY");
        new CooldownTask(player, tick, actionbarRender).runTaskTimerAsynchronously(TabooDualWield.getInst(), 0L, 2L);
        if (TabooDualWield.getConf().getBoolean("ATTACK-COOLDOWN.OFFHAND-BAR-DISPLAY")){
            player.setCooldown(player.getInventory().getItemInOffHand().getType(), Integer.parseInt(new java.text.DecimalFormat("0").format(tick * 20)));
        }
    }
    public static boolean ifOffhandHasCoolDown(Player player){
        if (TabooDualWield.getInst().getIsCooldown().get(player) != null && TabooDualWield.getInst().getIsCooldown().get(player)){
            if (TabooDualWield.getConf().getBoolean("ATTACK-COOLDOWN.CHAT-BAR-DISPLAY")){
                TLocale.sendTo(player, "COOLDOWN-CHATBAR", Double.toString(TabooDualWield.getInst().getCooldownTimer().get(player)));
            }
            return true;
        }
        return false;
    }
    public static double getItemCooldownRequireIfExist(Player player){
        if (!player.getInventory().getItemInOffHand().getItemMeta().hasLore()){
            return 0D;
        }
        List<String> itemLores = player.getInventory().getItemInOffHand().getItemMeta().getLore();
        Pattern triggerPattern = Pattern.compile(TabooDualWield.getConf().getString("ATTACK-COOLDOWN.LORE-KEY").replace("{0}", "(?<num>\\S+)"));
        for (String itemLore : itemLores) {
            Matcher lorePattern = triggerPattern.matcher(itemLore);
            while (lorePattern.find()){
                String eventNumber = lorePattern.group("num");
                if (eventNumber == null){
                    return 0D;
                }
                return Double.parseDouble(eventNumber);
            }
        }
        return 0D;
    }

}
