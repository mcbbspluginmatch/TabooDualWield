package me.skymc.taboodualwield.listener;

import me.skymc.taboodualwield.TabooDualWield;
import me.skymc.taboolib.listener.TListener;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * @Author CziSKY
 * @Since 2019-07-31 18:15
 */
@TListener
public class EntityListener implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent e){
        Entity entity = e.getEntity();
        if (entity.hasMetadata("OFFHAND-ATTACK-DAMAGE")){
            e.setDamage(e.getDamage() + entity.getMetadata("OFFHAND-ATTACK-DAMAGE").get(0).asDouble());
            entity.removeMetadata("OFFHAND-ATTACK-DAMAGE", TabooDualWield.getInst());
        }
    }

}
