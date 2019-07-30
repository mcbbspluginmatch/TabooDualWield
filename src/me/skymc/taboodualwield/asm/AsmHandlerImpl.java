package me.skymc.taboodualwield.asm;

import me.skymc.taboodualwield.managers.AttackManager;
import me.skymc.taboolib.common.packet.TPacketHandler;
import net.minecraft.server.v1_11_R1.EntityPlayer;
import net.minecraft.server.v1_11_R1.ItemStack;
import net.minecraft.server.v1_11_R1.PacketPlayOutAnimation;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
/**
 * @Author CziSKY
 * @Since 2019-06-19 13:57
 */
public class AsmHandlerImpl extends AsmHandler {
    public void attack(Player player, Entity entity, Double damage){
        AttackManager.grantPermissionForPlayer(player);
        AttackManager.removePermissionForPlayer(player);
        ((CraftPlayer) player).getHandle().attack(((CraftLivingEntity) entity).getHandle());
    }

    public void offhandDisplay(Player player){
        TPacketHandler.sendPacket(player, new PacketPlayOutAnimation(((CraftPlayer) player).getHandle(), 3));
    }

    public void toggleHand(Player player){
        try {
            Object entityPlayer = ((CraftPlayer) player).getHandle();
            Object itemInMainHand = org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand());
            Object itemInOffHand = org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack.asNMSCopy(player.getInventory().getItemInOffHand());
            ((EntityPlayer) entityPlayer).inventory.items.set(((EntityPlayer) entityPlayer).inventory.itemInHandIndex, (ItemStack) itemInOffHand);
            ((EntityPlayer) entityPlayer).inventory.extraSlots.set(0, (ItemStack) itemInMainHand);
        } catch (Throwable t){
            t.printStackTrace();
        }
    }

}
