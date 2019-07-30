package me.skymc.taboodualwield.asm;

import me.skymc.taboodualwield.TabooDualWield;
import me.skymc.taboolib.common.function.TFunction;
import me.skymc.taboolib.common.versioncontrol.SimpleVersionControl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * @Author CziSKY
 * @Since 2019-06-19 6:04
 */
@TFunction(enable = "init")
public abstract class AsmHandler {

    private static AsmHandler impl;

    public static AsmHandler getImpl() {
        return AsmHandler.impl;
    }

    static void init(){
        try {
            AsmHandler.impl = (AsmHandler) SimpleVersionControl.createNMS("me.skymc.taboodualwield.asm.AsmHandlerImpl").useCache().translate(TabooDualWield.getInst()).newInstance();
        }catch (Throwable t){
            t.printStackTrace();
        }
    }
    public abstract void attack(Player player, Entity entity, Double damage);
    public abstract void offhandDisplay(Player player);
    public abstract void toggleHand(Player player);

}
