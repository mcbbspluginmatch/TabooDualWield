package me.skymc.taboodualwield.listener;

import com.ilummc.tlib.resources.TLocale;
import me.skymc.taboodualwield.TabooDualWield;
import me.skymc.taboodualwield.api.events.OffhandAnimatonEvent;
import me.skymc.taboodualwield.api.events.OffhandAttackEvent;
import me.skymc.taboodualwield.managers.AttackManager;
import me.skymc.taboodualwield.managers.CooldownManager;
import me.skymc.taboolib.listener.TListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.FixedMetadataValue;

import static me.skymc.taboodualwield.asm.AsmHandler.getImpl;
import static me.skymc.taboodualwield.managers.CooldownManager.getItemCooldownRequireIfExist;
/**
 * @Author CziSKY
 * @Since 2019-06-19 5:59
 */
@TListener
public class PlayerListener implements Listener {
    // 造成攻击动画，不造成伤害。
    @EventHandler
    public void onClickOnAir(PlayerInteractEvent e){

        if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)){
            return;
        }
        Player player = e.getPlayer();

        OffhandAnimatonEvent animatonEvent = new OffhandAnimatonEvent(player, player.getInventory().getItemInOffHand());
        Bukkit.getPluginManager().callEvent(animatonEvent);
        if (animatonEvent.isCancelled()){
            return;
        }

        if (!AttackManager.attackRequireCheck(player) || CooldownManager.isOffhandHasCoolDown(player)){
            if (TabooDualWield.getConf().getBoolean("ATTACK-COOLDOWN.CHAT-BAR-DISPLAY")){
                TLocale.sendTo(player, "COOLDOWN-CHATBAR", Double.toString(TabooDualWield.getInst().getCooldownTimer().get(player)));
            }
            return;
        }

        getImpl().offhandDisplay(player);

        double cooldownTicks = getItemCooldownRequireIfExist(player);

        if (cooldownTicks > 0.0D){
            CooldownManager.makeOffhandCooldown(player, cooldownTicks);
        }

    }

    // 造成攻击伤害。
    @EventHandler
    public void onClickOnEntity(PlayerInteractEntityEvent e){
        if (!(e.getRightClicked() instanceof LivingEntity)){
            return;
        }
        Player player = e.getPlayer();
        Double damage = 0.0D;
        OffhandAttackEvent attackEvent = new OffhandAttackEvent(player, player.getInventory().getItemInOffHand(), damage);
        Bukkit.getPluginManager().callEvent(attackEvent);
        if (attackEvent.isCancelled()){
            return;
        }
        e.getRightClicked().setMetadata("OFFHAND-ATTACK-DAMAGE", new FixedMetadataValue(TabooDualWield.getInst(), attackEvent.getDamage()));
        if (!AttackManager.attackRequireCheck(player) || CooldownManager.isOffhandHasCoolDown(player)){
            return;
        }
        player.setMetadata("OFFHAND-ATTACKING", new FixedMetadataValue(TabooDualWield.getInst(), "0"));
        getImpl().toggleHand(player);
        getImpl().attack(player, e.getRightClicked(), 0D);
        getImpl().toggleHand(player);
        player.updateInventory();
        player.removeMetadata("OFFHAND-ATTACKING", TabooDualWield.getInst());
    }
    // 数据清理
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        if (CooldownManager.isOffhandHasCoolDown(player)){
            TabooDualWield.getInst().getIsCooldown().remove(player);
            TabooDualWield.getInst().getCooldownTimer().remove(player);
        }
        if (e.getPlayer().hasMetadata(player.getName())){
            e.getPlayer().removeMetadata("OFFHAND-ATTACKING", TabooDualWield.getInst());
        }
    }
    // 数据清理
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        if (player.hasMetadata(player.getName())){
            player.removeMetadata("OFFHAND-ATTACKING", TabooDualWield.getInst());
        }
    }
}
