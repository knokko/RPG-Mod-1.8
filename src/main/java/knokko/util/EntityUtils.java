package knokko.util;

import java.util.List;
import java.util.UUID;

import knokko.rpg.data.WorldData;
import knokko.rpg.entity.data.UndeadTeam;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class EntityUtils {
	public static void sendManaMessage(EntityPlayer player){
		if(player != null && !player.worldObj.isRemote){
			if(WorldData.getBooleanOption(player, "manaMessages", true)){
				player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.BLUE + "Your mana is " + WorldData.getInteger(player, "mana") + "/" + WorldData.getInteger(player, "maxmana")));
			}
		}
	}
	public static Entity getEntityByUUID(World world, String uuid){
		if(world != null && uuid != null && !uuid.isEmpty()){
			List entities = world.loadedEntityList;
			int times = 0;
			while(times < entities.size()){
				Entity entity = (Entity) entities.get(times);
				if(entity.getUniqueID().toString().matches(uuid)){
					return entity;
				}
				++times;
			}
		}
		return null;
	}
	public static Entity getEntityByUUID(World world, UUID uuid){
		if(uuid != null){
			return getEntityByUUID(world, uuid.toString());
		}
		else {
			return null;
		}
	}
	public static float applyArmorCalculations(EntityLivingBase victim, DamageSource source, float ammount){
		if (!source.isUnblockable())
        {
            int i = 25 - victim.getTotalArmorValue();
            float f1 = ammount * (float)i;
            ammount = f1 / 25.0F;
        }

        return ammount;
	}
	public static float applyPotionCalculations(EntityLivingBase victim, DamageSource source, float ammount){
		 if (source.isDamageAbsolute())
	        {
	            return ammount;
	        }
	        else
	        {

	            int i;
	            int j;
	            float f1;

	            if (victim.isPotionActive(Potion.resistance) && source != DamageSource.outOfWorld)
	            {
	                i = (victim.getActivePotionEffect(Potion.resistance).getAmplifier() + 1) * 5;
	                j = 25 - i;
	                f1 = ammount * (float)j;
	                ammount = f1 / 25.0F;
	            }

	            if (ammount <= 0.0F)
	            {
	                return 0.0F;
	            }
	            else
	            {
	                i = EnchantmentHelper.getEnchantmentModifierDamage(victim.getInventory(), source);
	                if (i > 20)
	                {
	                    i = 20;
	                }

	                if (i > 0 && i <= 20)
	                {
	                    j = 25 - i;
	                    f1 = ammount * (float)j;
	                    ammount = f1 / 25.0F;
	                }

	                return ammount;
	            }
	        }
	}
	public static Entity getNearestEntityInList(Position position, List list){
		Entity entity = null;
		double minDistance = Double.MAX_VALUE;
		if(position != null && list != null){
			int times = 0;
			while(times < list.size()){
				Object object = list.get(times);
				if(object instanceof Entity){
					Entity entity2 = (Entity) object;
					double distance = position.getDistance(new Position(entity2));
					if(distance < minDistance && !(entity2 instanceof UndeadTeam)){
						entity = entity2;
						minDistance = distance;
					}
				}
				++times;
			}
		}
		return entity;
	}
}
