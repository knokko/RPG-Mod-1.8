package knokko.rpg.events.living;

import knokko.rpg.RPG;
import knokko.rpg.data.WorldData;
import knokko.rpg.main.KnokkoRPG;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class UpdateEvent {
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void updateEvent(LivingUpdateEvent event){
		event.entityLiving.fallDistance = 0;
		World world = event.entityLiving.worldObj;
		WorldData.updateTimers(event.entityLiving);
		if(event.entityLiving instanceof EntityZombie){
		}
		if(WorldData.isIntegerPositive(event.entityLiving, "paralyzed")){
			event.entityLiving.motionX = 0;
			event.entityLiving.motionZ = 0;
			event.entityLiving.setJumping(false);
		}
		if(event.entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.entityLiving;
			WorldData.updateClient(player);
			if(WorldData.getString(player, "race", "human").matches("monkey")){
				player.addPotionEffect(new PotionEffect(8, 10, 3));
				player.jumpMovementFactor = 0.05F;
			}
			PotionEffect jump = player.getActivePotionEffect(Potion.jump);
			if(jump != null && !WorldData.getString(player, "race", "human").matches("monkey")){
				if(jump.getDuration() == 0){
					player.removePotionEffect(8);
				}
			}
			if(WorldData.getBooleanOption(player, "fly", false) && WorldData.getString(player, "class", "hunter").matches("galactic")){
				if(WorldData.getInteger(player, "mana") >= 300){
					Vec3 look = player.getLookVec();
					if(player.isSneaking()){
						player.motionX = 0;
						player.motionY = look.yCoord;
						player.motionZ = 0;
					}
					else {
						player.jumpMovementFactor = 0.25F;
						if(player.motionX >= 0.2 || player.motionZ >= 0.2 || player.motionX <= -0.2 || player.motionZ <= -0.2){
							player.motionY = look.yCoord;
						}
						else {
							player.motionY = 0;
						}
					}
					if(!world.isRemote){
						WorldData.removeFromInteger(player, "mana", 300, 0);
					}
				}
			}
			String id = player.getUniqueID().toString();
			WorldData data = WorldData.get(world);
			if(world.isRemote){
				if(data.entities != null && WorldData.nbt != null){
					if(WorldData.nbt.hasKey(id) && data.entities.getCompoundTag(id) != WorldData.nbt.getCompoundTag(id)){
						data.entities.setTag(id, WorldData.nbt.getCompoundTag(id));
						data.markDirty();
					}
				}
			}
			WorldData.setInteger(player, (int) (10000 * RPG.getPlayerMana(player)), "maxmana");
			WorldData.addToInteger(player,  "mana", (int) (WorldData.getInteger(player, "maxmana") * 0.001), WorldData.getInteger(player, "maxmana"));
			player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20 * RPG.getPlayerHealth(player));
			player.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(RPG.getBasePower(player));
			KnokkoRPG.proxy.setPlayerWalkSpeed(player, (float) (0.1 * RPG.getPlayerSpeed(player)));
			player.jumpMovementFactor *= RPG.getPlayerSpeed(player);
			WorldData.addToInteger(player, "lifetime", 1);
			if(WorldData.getInteger(player, "lifetime") >= 22 && WorldData.getInteger(player, "lifetime") <= 30){
				player.heal((float) (20 * RPG.getPlayerHealth(player)));
			}
			int life1 = (int) (WorldData.getInteger(player, "lifetime") * 0.005);
			double life2 = WorldData.getInteger(player, "lifetime") * 0.005;
			if(life1 == life2){
				player.heal((float) RPG.getPlayerHealth(player));
			}
			if(WorldData.getString(player, "race", "human").matches("enderman")){
				if(player.isWet()){
					DamageSource water = new DamageSource("water");
					player.attackEntityFrom(water, 1);
				}
			}
		}
	}
}
