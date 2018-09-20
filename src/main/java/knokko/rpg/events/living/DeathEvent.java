package knokko.rpg.events.living;

import knokko.rpg.data.WorldData;
import knokko.rpg.entity.effect.EntityBlood;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DeathEvent {
	@SubscribeEvent
	public void deathEvent(LivingDeathEvent event){
		if(event.entityLiving instanceof EntityCreature || event.entityLiving instanceof EntityPlayer){
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX + 0, event.entityLiving.posY + 0, event.entityLiving.posZ + 0, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX + 1.5, event.entityLiving.posY + 1.5, event.entityLiving.posZ + 1.5, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX + 1.5, event.entityLiving.posY + 1.5, event.entityLiving.posZ - 1.5, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 1.5, event.entityLiving.posY + 1.5, event.entityLiving.posZ + 0, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX + 1.5, event.entityLiving.posY - 1.5, event.entityLiving.posZ + 1.5, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX + 1.5, event.entityLiving.posY - 1.5, event.entityLiving.posZ - 1.5, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 1.5, event.entityLiving.posY + 1.5, event.entityLiving.posZ + 0, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX + 1.5, event.entityLiving.posY - 0, event.entityLiving.posZ + 1.5, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX + 1.5, event.entityLiving.posY - 0, event.entityLiving.posZ - 1.5, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 1.5, event.entityLiving.posY + 1.5, event.entityLiving.posZ + 0, 1));
			
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 1.5, event.entityLiving.posY + 0, event.entityLiving.posZ + 0, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 1.5, event.entityLiving.posY + 0, event.entityLiving.posZ + 1.5, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 1.5, event.entityLiving.posY + 0, event.entityLiving.posZ - 1.5, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 1.5, event.entityLiving.posY + 1.5, event.entityLiving.posZ + 0, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 1.5, event.entityLiving.posY + 1.5, event.entityLiving.posZ + 1.5, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 1.5, event.entityLiving.posY + 1.5, event.entityLiving.posZ - 1.5, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 1.5, event.entityLiving.posY - 1.5, event.entityLiving.posZ + 1.5, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 1.5, event.entityLiving.posY - 1.5, event.entityLiving.posZ - 1.5, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 1.5, event.entityLiving.posY - 1.5, event.entityLiving.posZ + 0, 1));
			
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 0, event.entityLiving.posY + 0, event.entityLiving.posZ + 0, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 0, event.entityLiving.posY + 0, event.entityLiving.posZ + 1.5, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 0, event.entityLiving.posY + 0, event.entityLiving.posZ - 1.5, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 0, event.entityLiving.posY + 1.5, event.entityLiving.posZ + 0, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 0, event.entityLiving.posY + 1.5, event.entityLiving.posZ + 1.5, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 0, event.entityLiving.posY + 1.5, event.entityLiving.posZ - 1.5, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 0, event.entityLiving.posY - 1.5, event.entityLiving.posZ + 1.5, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 0, event.entityLiving.posY - 1.5, event.entityLiving.posZ - 1.5, 1));
			event.entityLiving.worldObj.spawnEntityInWorld(new EntityBlood(event.entityLiving.worldObj, event.entityLiving.posX - 0, event.entityLiving.posY - 1.5, event.entityLiving.posZ + 0, 1));
		}
		if(event.source.getEntity() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			if(WorldData.getBooleanOption(player, "killMessages", true)){
				player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GOLD + "You have killed " + event.entityLiving.getName() + "."));
			}
		}
		if(event.source.getEntity() != null && event.entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if(WorldData.getBooleanOption(player, "deathMessages", true)){
				player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.DARK_RED + "You are killed by " + event.source.getEntity().getName() + "."));
			}
		}
		if(event.source.getEntity() == null && event.entityLiving instanceof EntityPlayer){
			EntityPlayer dead = (EntityPlayer)event.entityLiving;
			if(WorldData.getBooleanOption(dead, "deathMessages", true)){
				dead.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.DARK_RED + "You have died from " + event.source.damageType + "."));
			}
		}
		if(event.entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.entityLiving;
			WorldData.setInteger(player, 0, "lifetime");
		}
	}
}
