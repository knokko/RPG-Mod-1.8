package knokko.rpg.events.other;

import java.util.List;

import knokko.rpg.data.WorldData;
import knokko.rpg.entity.data.UndeadTeam;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class JoinEvent {
	@SubscribeEvent
	public void joinEvent(EntityJoinWorldEvent event){
		if(event.entity instanceof EntityArrow){
			EntityArrow arrow = (EntityArrow) event.entity;
			Entity shooter = arrow.shootingEntity;
			if(shooter instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer) shooter;
				if(WorldData.getString(player, "class", "hunter").matches("magicarcher")){
					if(WorldData.isIntegerPositive(player, "poisonarrow") && WorldData.getInteger(player, "mana") >= 1000){
						WorldData.setInteger(arrow, 1, "poisoned arrow");
						WorldData.removeFromInteger(player, "mana", 1000, 0);
						if(WorldData.getBooleanOption(player, "manaMessages", true) && !event.world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.BLUE + "Your mana is " + WorldData.getInteger(player, "mana") + "/" + WorldData.getInteger(player, "maxmana")));
						}
						TickEvent.poison_arrows.add(arrow);
					}
					if(WorldData.isIntegerPositive(player, "cursedarrow") && WorldData.getInteger(player, "mana") >= 10000){
						WorldData.setInteger(arrow, 1, "cursed arrow");
						WorldData.removeFromInteger(player, "mana", 10000, 0);
						if(WorldData.getBooleanOption(player, "manaMessages", true) && !event.world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.BLUE + "Your mana is " + WorldData.getInteger(player, "mana") + "/" + WorldData.getInteger(player, "maxmana")));
						}
						TickEvent.cursed_arrows.add(arrow);
					}
					if(WorldData.isIntegerPositive(player, "powershot") && WorldData.getInteger(player, "mana") >= 2000){
						WorldData.removeFromInteger(player, "mana", 2000, 0);
						arrow.setDamage(arrow.getDamage() * 1.5);
						if(WorldData.getBooleanOption(player, "manaMessages", true) && !event.world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.BLUE + "Your mana is " + WorldData.getInteger(player, "mana") + "/" + WorldData.getInteger(player, "maxmana")));
						}
						TickEvent.power_arrows.add(arrow);
					}
				}
				else if(WorldData.getString(player, "class", "hunter").matches("archer")){
					if(WorldData.isIntegerPositive(player, "poisonarrow") && WorldData.getInteger(player, "mana") >= 1000){
						WorldData.setInteger(arrow, 1, "poisoned arrow");
						WorldData.removeFromInteger(player, "mana", 1000, 0);
						if(WorldData.getBooleanOption(player, "manaMessages", true) && !event.world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.BLUE + "Your mana is " + WorldData.getInteger(player, "mana") + "/" + WorldData.getInteger(player, "maxmana")));
						}
						TickEvent.poison_arrows.add(arrow);
					}
					if(WorldData.isIntegerPositive(player, "powershot") && WorldData.getInteger(player, "mana") >= 2000){
						WorldData.removeFromInteger(player, "mana", 2000, 0);
						arrow.setDamage(arrow.getDamage() * 1.5);
						if(WorldData.getBooleanOption(player, "manaMessages", true) && !event.world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.BLUE + "Your mana is " + WorldData.getInteger(player, "mana") + "/" + WorldData.getInteger(player, "maxmana")));
						}
						TickEvent.power_arrows.add(arrow);
					}
				}
				else if(WorldData.getString(player, "class", "hunter").matches("shooter")){
					if(WorldData.isIntegerPositive(player, "powershot") && WorldData.getInteger(player, "mana") >= 2000){
						WorldData.removeFromInteger(player, "mana", 2000, 0);
						arrow.setDamage(arrow.getDamage() * 1.5);
						if(WorldData.getBooleanOption(player, "manaMessages", true) && !event.world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.BLUE + "Your mana is " + WorldData.getInteger(player, "mana") + "/" + WorldData.getInteger(player, "maxmana")));
						}
						TickEvent.power_arrows.add(arrow);
					}
				}
				WorldData.updateClient(player);
			}
		}
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.entity;
			WorldData.updateClient(player);
			if(WorldData.getString(player, "class", "hunter").equals("necromancer")){
				List teams = WorldData.get(event.world).undeadTeams;
				int t = 0;
				while(t < teams.size()){
					UndeadTeam team = ((UndeadTeam)teams.get(t));
					if(team.master != null && team.master.getUniqueID().equals(player.getUniqueID()))
						((UndeadTeam)teams.get(t)).master = player;
					++t;
				}
			}
		}
	}
}
