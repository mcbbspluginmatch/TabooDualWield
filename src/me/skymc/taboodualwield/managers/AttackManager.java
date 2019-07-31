package me.skymc.taboodualwield.managers;

import me.skymc.taboodualwield.TabooDualWield;
import me.skymc.taboolib.common.packet.TPacketHandler;
import me.skymc.taboolib.common.packet.TPacketListener;
import me.skymc.taboolib.permission.PermissionUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * @Author CziSKY
 * @Since 2019-07-04 6:16
 */
public class AttackManager {
    public AttackManager(){
        TPacketHandler.addListener(TabooDualWield.getInst(), new TPacketListener() {
            @Override
            public boolean onSend(Player player, Object packet) {
                String name = packet.getClass().getSimpleName();
                if (AttackManager.isOffhandAttacking(player)){
                    return !name.equals("PacketPlayOutSetSlot") && !name.equals("PacketPlayOutWindowItems");
                }
                return true;
            }
        });
    }

    public static void grantPermissionForPlayer(Player player){
        for (String Permissions : TabooDualWield.getConf().getStringList("GRANT-PERMISSIONS")) {
            if (Permissions.equalsIgnoreCase("NONE")){
                return;
            }
            PermissionUtils.addPermission(player, Permissions);
        }
    }
    public static void removePermissionForPlayer(Player player){
        for (String Permissions : TabooDualWield.getConf().getStringList("GRANT-PERMISSIONS")) {
            if (Permissions.equalsIgnoreCase("NONE")){
                return;
            }
            PermissionUtils.removePermission(player, Permissions);
        }
    }
    public static boolean attackRequireCheck(Player player){
        // 材质检测
        if (player.getInventory().getItemInOffHand().getType() == Material.AIR){
            return false;
        }
        // 权限检测
        if (!TabooDualWield.getConf().getString("ATTACK-REQUIRE.PERMISSION").equalsIgnoreCase("NONE") && !PermissionUtils.hasPermission(player, TabooDualWield.getConf().getString("ATTACK-REQUIRE.PERMISSION"))){
            return false;
        }
        // 饥饿度检测
        if (TabooDualWield.getConf().getBoolean("ATTACK-REQUIRE.FOOD-LEVEL") && player.getInventory().getItemInMainHand().getType().isEdible() && !(player.getFoodLevel() == 20)){
            return false;
        }
        // 描述检测
        if (!TabooDualWield.getConf().getString("ATTACK-REQUIRE.LORE-KEY").equalsIgnoreCase("NONE") && !player.getInventory().getItemInOffHand().getItemMeta().hasLore()){
            return false;
        }
        if (!TabooDualWield.getConf().getString("ATTACK-REQUIRE.LORE-KEY").equalsIgnoreCase("NONE") && player.getInventory().getItemInOffHand().getItemMeta().hasLore()){
            return player.getInventory().getItemInOffHand().getItemMeta().getLore().toString().contains(TabooDualWield.getConf().getString("ATTACK-REQUIRE.LORE-KEY"));
        }
        return true;
    }

    public static boolean isOffhandAttacking(Player player){
        return player.hasMetadata("OFFHAND-ATTACKING");
    }

}

