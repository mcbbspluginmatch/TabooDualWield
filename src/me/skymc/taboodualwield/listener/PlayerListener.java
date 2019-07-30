package me.skymc.taboodualwield.listener;

import me.skymc.taboodualwield.TabooDualWield;
import me.skymc.taboodualwield.managers.AttackManager;
import me.skymc.taboodualwield.managers.CooldownManager;
import me.skymc.taboolib.listener.TListener;
import org.bukkit.entity.LivingEntity;
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

        if (!AttackManager.attackRequireCheck(e.getPlayer()) || CooldownManager.ifOffhandHasCoolDown(e.getPlayer())){
            return;
        }

        getImpl().offhandDisplay(e.getPlayer());

        double cooldownTicks = getItemCooldownRequireIfExist(e.getPlayer());

        if (cooldownTicks > 0.0D){
            CooldownManager.makeOffhandCooldown(e.getPlayer(), cooldownTicks);
        }

    }

    // 造成攻击伤害。
    @EventHandler
    public void onClickOnEntity(PlayerInteractEntityEvent e){
        if (!(e.getRightClicked() instanceof LivingEntity)){
            return;
        }
        if (!AttackManager.attackRequireCheck(e.getPlayer()) || CooldownManager.ifOffhandHasCoolDown(e.getPlayer())){
            return;
        }
        e.getPlayer().setMetadata("OFFHAND-ATTACKING", new FixedMetadataValue(TabooDualWield.getInst(), "0"));
        getImpl().toggleHand(e.getPlayer());
        getImpl().attack(e.getPlayer(), e.getRightClicked(), 0D);
        getImpl().toggleHand(e.getPlayer());
        e.getPlayer().updateInventory();
        e.getPlayer().removeMetadata("OFFHAND-ATTACKING", TabooDualWield.getInst());
    }
    // 数据清理
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        if (CooldownManager.ifOffhandHasCoolDown(e.getPlayer())){
            TabooDualWield.getInst().getIsCooldown().remove(e.getPlayer());
            TabooDualWield.getInst().getCooldownTimer().remove(e.getPlayer());
        }
        if (e.getPlayer().hasMetadata(e.getPlayer().getName())){
            e.getPlayer().removeMetadata("OFFHAND-ATTACKING", TabooDualWield.getInst());
        }
    }
    // 数据清理
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if (e.getPlayer().hasMetadata(e.getPlayer().getName())){
            e.getPlayer().removeMetadata("OFFHAND-ATTACKING", TabooDualWield.getInst());
        }
    }
}
