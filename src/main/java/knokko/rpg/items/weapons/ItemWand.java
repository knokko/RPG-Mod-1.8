package knokko.rpg.items.weapons;

import java.util.List;
import java.util.Random;

import knokko.rpg.RPG;
import knokko.rpg.data.WorldData;
import knokko.rpg.effect.EnergyBall;
import knokko.rpg.entity.minion.EntityChow;
import knokko.rpg.entity.minion.EntityDreadLord;
import knokko.rpg.entity.minion.EntitySoar;
import knokko.rpg.entity.projectile.EntityDarkPulse;
import knokko.rpg.entity.projectile.EntityExplosiveSpell;
import knokko.rpg.entity.projectile.EntityFireSpell;
import knokko.rpg.entity.projectile.EntityIceSpell;
import knokko.rpg.entity.projectile.EntityMeteor;
import knokko.rpg.main.KnokkoRPG;
import knokko.rpg.main.s;
import knokko.util.EntityUtils;
import knokko.util.ExtraUtils;
import knokko.util.Line;
import knokko.util.Position;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemWand extends Item{
	
	public ItemWand(double power, double manaUse, String name){
		setUnlocalizedName(name);
		setCreativeTab(CreativeTabs.tabCombat);
		this.power = power;
		this.manaUse = manaUse;
	}
	
	public double power;
	public double manaUse;
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player){
	String name = item.getDisplayName();
		if(!WorldData.isIntegerPositive(player, "cooldown")){
			player.swingItem();
			String Class = WorldData.getString(player, "class", "hunter");
			int mana = WorldData.getInteger(player, "mana");
			double spirit = RPG.getPlayerSpirit(player);
			float healPower = (float) (power * spirit * 20);
			if(Class.matches("mage")){
				if(name.matches("ice")){
					if(100 * manaUse <= mana && !world.isRemote){
						world.spawnEntityInWorld(new EntityIceSpell(world, player, (float) (power * 5 * spirit)));
						WorldData.removeFromInteger(player, "mana", (int) (100 * manaUse), 0);
						WorldData.setInteger(player, 10, "cooldown");	
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("fire")){
					if(500 * manaUse <= mana && !world.isRemote){
						world.spawnEntityInWorld(new EntityFireSpell(world, player, (float) (power * 15 * spirit)));
						WorldData.removeFromInteger(player, "mana", (int) (500 * manaUse), 0);
						WorldData.setInteger(player, 10, "cooldown");
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("explosive")){
					if(2000 * manaUse <= mana){
						world.spawnEntityInWorld(new EntityExplosiveSpell(world, player, (float) (power * spirit)));
						WorldData.removeFromInteger(player, "mana", (int) (2000 * manaUse), 0);
						if(!world.isRemote){
							WorldData.setInteger(player, 20, "cooldown");
						}		
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("lightning")){
					if(mana >= 500 * manaUse){
						Line line = Line.fromRaytrace(player, 300).toNearestBlock(world, false, 1).toNearestEntity(world, EntityLivingBase.class, 1, player);
						Position pos = line.getPosition(2);
						Line lightning = new Line(pos, new Position(pos.x, pos.y + 100, pos.z));
						if(world.isRemote){
							world.addWeatherEffect(new EntityLightningBolt(world, pos.x, pos.y, pos.z));
						}
						List entities = lightning.getEntities(world, Entity.class, false);
						int times = 0;
						DamageSource source = new EntityDamageSource("lightning", player);
						while(times < entities.size()){
							Entity entity = (Entity) entities.get(times);
							entity.attackEntityFrom(source, (float) spirit);
							++times;
						}
						List entities2 = world.getEntitiesWithinAABBExcludingEntity(player, AxisAlignedBB.fromBounds(pos.x - spirit, pos.y - spirit, pos.z - spirit, pos.x + spirit, pos.y + spirit, pos.z + spirit));
						times = 0;
						while(times < entities2.size()){
							Entity entity = (Entity) entities2.get(times);
							double distance = pos.getDistance(new Position(entity));
							if(distance <= spirit){
								if(distance < 2){
									distance = 2;
								}
								entity.attackEntityFrom(source, (float) ExtraUtils.divineAccurate(spirit * 4, distance));
							}
							++times;
						}
						if(!world.isRemote){
							WorldData.removeFromInteger(player, "mana", (int) (500 * manaUse), 0);
							EntityUtils.sendManaMessage(player);
						}
						world.playSound(pos.x, pos.y, pos.z, "ambient.weather.thunder", 3, 1, true);
					}
				}
				else if(name.matches("weakregen")){
					if(mana >= 2000 * manaUse){
						player.addPotionEffect(new PotionEffect(10, 200));
						WorldData.removeFromInteger(player, "mana", (int) (2000 * manaUse), 0);
						if(!world.isRemote){
							WorldData.setInteger(player, 10, "cooldown");
							EntityUtils.sendManaMessage(player);
						}
					}
				}
				else if(name.matches("darkpulse") && !world.isRemote){
					if(mana >= 200 * manaUse){
						world.spawnEntityInWorld(new EntityDarkPulse(world, player, (float) (power * 5 * spirit)));
						WorldData.removeFromInteger(player, "mana", (int) (200 * manaUse), 0);
						WorldData.setInteger(player, 10, "cooldown");
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("spark") && mana >= 1000 * manaUse){
					Line line1 = Line.fromRaytrace(player, 300).toNearestBlock(world, false, 1);
					EntityLivingBase entity = (EntityLivingBase) line1.getNearestEntity(world, EntityLivingBase.class, line1.getPosition(1), player);
					Line line = line1.toNearestEntity(world, EntityLivingBase.class, 1, player);
					line.spawnParticles2(world, 1.1, 1, 0.1, ExtraUtils.divineAccurate(0.1, line.distance), 5);
					Position target = line.getPosition(2);
					world.playSound(target.x, target.y, target.z, "fireworks.blast", 3, 1, true);
					if(entity != null){
						DamageSource source = new EntityDamageSource("spark", player);
						entity.attackEntityFrom(source, (float) (5 * spirit));
					}
					if(!world.isRemote){
						WorldData.removeFromInteger(player, "mana", (int) (1000 * manaUse), 0);
						EntityUtils.sendManaMessage(player);
					}
				}
			}
			else if(Class.matches("magicarcher")){
				if(name.matches("summonarrow") && !world.isRemote){
					if(mana >= 3000 * manaUse){
						player.inventory.addItemStackToInventory(new ItemStack(Items.arrow, 10));
						WorldData.removeFromInteger(player, "mana", (int) (3000 * manaUse), 0);
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("lightningarrow") && !world.isRemote){
					if(WorldData.isIntegerPositive(player, "lightningarrow")){
						WorldData.removeTimer(player, "lightningarrow");
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will not shoot lightning arrows anymore."));
					}
					else {
						WorldData.setInteger(player, 600, "lightningarrow");
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will now shoot lightning arrows."));
					}
				}
				else if(name.matches("poisonarrow") && !world.isRemote){
					if(WorldData.isIntegerPositive(player, "poisonarrow")){
						WorldData.removeTimer(player, "poisonarrow");
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will not shoot poisoned arrows anymore."));
					}
					else {
						WorldData.setInteger(player, 600, "poisonarrow");
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will now shoot poisoned arrows."));
					}
				}
				else if(name.matches("cursedarrow") && !world.isRemote){
					if(WorldData.isIntegerPositive(player, "cursedarrow")){
						WorldData.removeTimer(player, "cursedarrow");
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will not shoot cursed arrows anymore."));
					}
					else {
						WorldData.setInteger(player, 600, "cursedarrow");
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will now shoot cursed arrows."));
					}
				}
				else if(name.matches("powershot") && !world.isRemote){
					if(WorldData.isIntegerPositive(player, "powershot")){
						WorldData.removeTimer(player, "powershot");
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will not shoot power arrows anymore."));
					}
					else {
						WorldData.setInteger(player, 600, "powershot");
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will now shoot power arrows."));
					}
				}
			}
			else if(Class.matches("archer")){
				if(name.matches("summonarrow")){
					if(mana >= 3000 * manaUse){
						ItemStack arrows = new ItemStack(Items.arrow, 10);
						player.inventory.addItemStackToInventory(arrows);
						WorldData.removeFromInteger(player, "mana", (int) (3000 * manaUse), 0);
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("poisonarrow") && !world.isRemote){
					if(WorldData.isIntegerPositive(player, "poisonarrow")){
						WorldData.removeTimer(player, "poisonarrow");
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will not shoot poisoned arrows anymore."));
					}
					else {
						WorldData.setInteger(player, 600, "poisonarrow");
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will now shoot poisoned arrows."));
					}
				}
				else if(name.matches("powershot") && !world.isRemote){
					if(WorldData.isIntegerPositive(player, "powershot")){
						WorldData.removeTimer(player, "powershot");
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will not shoot power arrows anymore."));
					}
					else {
						WorldData.setInteger(player, 600, "powershot");
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will now shoot power arrows."));
					}
				}
			}
			else if(Class.matches("shooter")){
				if(name.matches("summonarrow") && !world.isRemote){
					if(mana >= 3000 * manaUse){
						player.inventory.addItemStackToInventory(new ItemStack(Items.arrow, 10));
						WorldData.removeFromInteger(player, "mana", (int) (3000 * manaUse), 0);
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("powershot") && !world.isRemote){
					if(WorldData.isIntegerPositive(player, "powershot")){
						WorldData.removeTimer(player, "powershot");
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will not shoot power arrows anymore."));
					}
					else {
						WorldData.setInteger(player, 600, "powershot");
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will now shoot power arrows."));
					}
				}
			}
			else if(Class.matches("warrior")){
				if(name.matches("shield")){
					if(mana >= 2000 * manaUse && !player.isPotionActive(11)){
						player.addPotionEffect(new PotionEffect(11, 600));
						WorldData.removeFromInteger(player, "mana", ExtraUtils.fromDouble(2000 * manaUse), 0);
						WorldData.setInteger(player, 50, "cooldown");
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("powerattack") && !world.isRemote){
					if(WorldData.isIntegerPositive(player, "powerattack")){
						WorldData.removeTimer(player, "powerattack");
						if(!world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will not use power attack anymore."));
						}
					}
					else {
						WorldData.setInteger(player, 1, "powerattack");
						if(!world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will now use power attack."));
						}
					}
				}
			}
			else if(Class.matches("hunter")){
				if(name.matches("poisonblade")){
					if(mana >= 3000 * manaUse){
						WorldData.setInteger(player, 600, name);
						WorldData.removeFromInteger(player, "mana", (int) (3000 * manaUse), 0);
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("dog") && mana >= 5000 * manaUse){
					EntityWolf wolf = new EntityWolf(world);
					NBTTagCompound nbt = new NBTTagCompound();
					nbt.setString("Owner", player.getName());
					wolf.readEntityFromNBT(nbt);
					wolf.setPosition(player.posX, player.posY, player.posZ);
					if(!world.isRemote){
						world.spawnEntityInWorld(wolf);
					}
					wolf.fallDistance = 0;
					wolf.heal(20);
					WorldData.removeFromInteger(player, "mana", (int) (5000 * manaUse), 0);
					EntityUtils.sendManaMessage(player);
				}
			}
			else if(Class.matches("brawler")){
				if(name.matches("windpunch")){
					if(mana >= 3000 * manaUse){
						WorldData.setInteger(player, 600, "windpunch");
						WorldData.removeFromInteger(player, "mana", (int) (3000 * manaUse), 0);
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("firepunch")){
					if(mana >= 3000 * manaUse){
						WorldData.setInteger(player, 600, "firepunch");
						WorldData.removeFromInteger(player, "mana", (int) (1500 * manaUse), 0);
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("icepunch")){
					if(mana >= 3000 * manaUse){
						WorldData.setInteger(player, 600, "icepunch");
						WorldData.removeFromInteger(player, "mana", (int) (1500 * manaUse), 0);
						EntityUtils.sendManaMessage(player);
					}
				}
			}
			else if(Class.matches("tank") && !world.isRemote){
				if(name.matches("shield")){
					if(mana >= 2000 * manaUse && !player.isPotionActive(11)){
						player.addPotionEffect(new PotionEffect(11, 600));
						WorldData.removeFromInteger(player, "mana", ExtraUtils.fromDouble(2000 * manaUse), 0);
						WorldData.setInteger(player, 50, "cooldown");
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("heavyshield")){
					if(mana >= 10000 * manaUse){
						player.addPotionEffect(new PotionEffect(11, 1200, 1));
						WorldData.removeFromInteger(player, "mana", ExtraUtils.fromDouble(10000 * manaUse), 0);
						WorldData.setInteger(player, 50, "cooldown");
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("megabarrier")){
					if(mana >= 20000 * manaUse){
						player.addPotionEffect(new PotionEffect(11, 1200, 2));
						WorldData.removeFromInteger(player, "mana", ExtraUtils.fromDouble(20000 * manaUse), 0);
						WorldData.setInteger(player, 50, "cooldown");
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("gigabarrier")){
					if(mana >= 5000 * manaUse){
						player.addPotionEffect(new PotionEffect(11, 40, 4));
						WorldData.removeFromInteger(player, "mana", ExtraUtils.fromDouble(5000 * manaUse), 0);
						WorldData.setInteger(player, 50, "cooldown");
						EntityUtils.sendManaMessage(player);
					}
				}
			}
			else if(Class.matches("berserker")){
				if(name.matches("powerattack") && !world.isRemote){
					if(WorldData.isIntegerPositive(player, "powerattack")){
						WorldData.removeTimer(player, "powerattack");
						if(!world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will not use power attack anymore."));
						}
					}
					else {
						WorldData.setInteger(player, 1, "powerattack");
						if(!world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will now use power attack."));
						}
					}
				}
				else if(name.matches("berserk") && !world.isRemote && !player.isPotionActive(5)){
					if(mana >= 5000 * manaUse){
						player.addPotionEffect(new PotionEffect(5, 1200));
						WorldData.removeFromInteger(player, "mana", ExtraUtils.fromDouble(5000 * manaUse), 0);
						WorldData.setInteger(player, 50, "cooldown");
						EntityUtils.sendManaMessage(player);
					}
				}
			}
			else if(Class.matches("magicwarrior")){
				if(name.matches("powerattack") && !world.isRemote){
					if(WorldData.isIntegerPositive(player, "powerattack")){
						WorldData.removeTimer(player, "powerattack");
						if(!world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will not use power attack anymore."));
						}
					}
					else {
						WorldData.setInteger(player, 1, "powerattack");
						if(!world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will now use power attack."));
						}
					}
				}
				else if(name.matches("fireslash") && !world.isRemote){
					if(WorldData.isIntegerPositive(player, "fireslash")){
						WorldData.removeTimer(player, "fireslash");
						if(!world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will not use fire slash anymore."));
						}
					}
					else {
						WorldData.setInteger(player, 1, "fireslash");
						if(!world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will now use fire slash."));
						}
					}
				}
				else if(name.matches("electricslash") && !world.isRemote){
					if(WorldData.isIntegerPositive(player, "electricslash")){
						WorldData.removeTimer(player, "electricslash");
						if(!world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will not use electric slash anymore."));
						}
					}
					else {
						WorldData.setInteger(player, 1, "electricslash");
						if(!world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will now use electric slash."));
						}
					}
				}
				else if(name.matches("blooddrain") && !world.isRemote){
					if(WorldData.isIntegerPositive(player, "blooddrain")){
						WorldData.removeTimer(player, "blooddrain");
						if(!world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will not drain blood anymore."));
						}
					}
					else {
						WorldData.setInteger(player, 1, "blooddrain");
						if(!world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will now drain blood."));
						}
					}
				}
				else if(name.matches("darkslash") && !world.isRemote){
					if(WorldData.isIntegerPositive(player, "darkslash")){
						WorldData.removeTimer(player, "darkslash");
						if(!world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will not use dark slash anymore."));
						}
					}
					else {
						WorldData.setInteger(player, 1, "darkslash");
						if(!world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will now use dark slash."));
						}
					}
				}
				else if(name.matches("spiritslash") && !world.isRemote){
					if(WorldData.isIntegerPositive(player, "spiritslash")){
						WorldData.removeTimer(player, "spiritslash");
						if(!world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will not use spirit slash anymore."));
						}
					}
					else {
						WorldData.setInteger(player, 1, "spiritslash");
						if(!world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will now use spirit slash."));
						}
					}
				}
			}
			else if(Class.matches("paladin")){
				if(name.matches("heal") && mana >= 5000 * manaUse){
					if(!world.isRemote){
						player.heal(healPower);
						WorldData.removeFromInteger(player, "mana", (int) (5000 * manaUse), 0);
						WorldData.setInteger(player, 10, "cooldown");
						EntityUtils.sendManaMessage(player);
					}
					else {
						int times = 0;
						while(times < healPower * 0.4){
							Random random = new Random();
							world.spawnParticle(EnumParticleTypes.HEART, player.posX - 0.5 + random.nextDouble(), player.posY - 0.5 + random.nextDouble(), player.posZ - 0.5 + random.nextDouble(), 0, 0, 0);
							++times;
						}
					}
				}
				else if(name.matches("spiritslash") && !world.isRemote){
					if(WorldData.isIntegerPositive(player, "spiritslash")){
						WorldData.removeTimer(player, "spiritslash");
						if(!world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will not use spirit slash anymore."));
						}
					}
					else {
						WorldData.setInteger(player, 1, "spiritslash");
						if(!world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will now use spirit slash."));
						}
					}
				}
				else if(name.matches("smite") && !world.isRemote){
					if(WorldData.isIntegerPositive(player, "smite")){
						WorldData.removeTimer(player, "smite");
						if(!world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will not use smite anymore."));
						}
					}
					else {
						WorldData.setInteger(player, 1, "smite");
						if(!world.isRemote){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will now use smite."));
						}
					}
				}
			}
			else if(Class.matches("healer")){
				if(name.matches("ice")){
					if(100 * manaUse <= mana && !world.isRemote){
						world.spawnEntityInWorld(new EntityIceSpell(world, player, (float) (power * 5 * spirit)));
						WorldData.removeFromInteger(player, "mana", (int) (100 * manaUse), 0);
						WorldData.setInteger(player, 10, "cooldown");
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("fire")){
					if(500 * manaUse <= mana && !world.isRemote){
						world.spawnEntityInWorld(new EntityFireSpell(world, player, (float) (power * 15 * spirit)));
						WorldData.removeFromInteger(player, "mana", (int) (500 * manaUse), 0);
						WorldData.setInteger(player, 10, "cooldown");
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("weakregen") && !world.isRemote){
					if(mana >= 2000 * manaUse){
						player.addPotionEffect(new PotionEffect(10, 200));
						WorldData.removeFromInteger(player, "mana", (int) (2000 * manaUse), 0);
						if(!world.isRemote){
							WorldData.setInteger(player, 10, "cooldown");
							EntityUtils.sendManaMessage(player);
						}
					}
				}
				else if(name.matches("heal") && mana >= 5000 * manaUse && player.shouldHeal()){
					if(!world.isRemote){
						player.heal(healPower);
						WorldData.removeFromInteger(player, "mana", (int) (5000 * manaUse), 0);
						WorldData.setInteger(player, 10, "cooldown");
						EntityUtils.sendManaMessage(player);
					}
					else {
						int times = 0;
						while(times < healPower * 0.4){
							Random random = new Random();
							world.spawnParticle(EnumParticleTypes.HEART, player.posX - 0.5 + random.nextDouble(), player.posY - 0.5 + random.nextDouble(), player.posZ - 0.5 + random.nextDouble(), 0, 0, 0);
							++times;
						}
					}
				}
				else if(name.matches("teamheal")){
					List partners = WorldData.getOnlinePlayersInTeam(player);
					int times = 0;
					while(times < partners.size()){
						EntityPlayer partner = (EntityPlayer) partners.get(times);
						if(mana >= 5000 * manaUse && partner.shouldHeal()){
							partner.heal(healPower);
							if(!world.isRemote){
								WorldData.removeFromInteger(player, "mana", (int) (5000 * manaUse), 0);
							}
							else {
								int times2 = 0;
								while(times2 < healPower * 0.4){
									Random random = new Random();
									world.spawnParticle(EnumParticleTypes.HEART, partner.posX - 0.5 + random.nextDouble(), partner.posY - 0.5 + random.nextDouble(), partner.posZ - 0.5 + random.nextDouble(), 0, 0, 0);
									++times2;
								}
							}
						}
						++times;
					}
					if(!world.isRemote){
						WorldData.setInteger(player, 20, "cooldown");
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("helpheal")){
					Line sight = Line.fromRaytrace(player, 50);
					EntityLivingBase entity = (EntityLivingBase) sight.getNearestEntity(world, EntityLivingBase.class, sight.getPosition(1), player);
					if(entity != null && mana >= 5000 * manaUse && entity.getHealth() < entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth).getBaseValue()){
						if(!world.isRemote){
							entity.heal(healPower);
							WorldData.removeFromInteger(player, "mana", (int) (5000 * manaUse), 0);
							WorldData.setInteger(player, 20, "cooldown");
								EntityUtils.sendManaMessage(player);
						}
						else {
							int times = 0;
							while(times < healPower * 0.4){
								Random random = new Random();
								world.spawnParticle(EnumParticleTypes.HEART, entity.posX - 0.5 + random.nextDouble(), entity.posY + 0.5 + random.nextDouble(), entity.posZ - 0.5 + random.nextDouble(), 0, 0, 0);
								++times;
							}
						}
					}
				}
				else if(name.matches("regen") && !world.isRemote && mana >= 5000 * manaUse){
					player.addPotionEffect(new PotionEffect(10, 600, 2));
					WorldData.setInteger(player, 10, "cooldown");
					WorldData.removeFromInteger(player, "mana", (int) (5000 * manaUse), 0);
					EntityUtils.sendManaMessage(player);
				}
				else if(name.matches("teamregen")){
					List partners = WorldData.getOnlinePlayersInTeam(player);
					int times = 0;
					while(times < partners.size()){
						EntityPlayer partner = (EntityPlayer) partners.get(times);
						if(mana >= 5000 * manaUse && !world.isRemote){
							partner.addPotionEffect(new PotionEffect(10, 600, 2));
							WorldData.removeFromInteger(player, "mana", (int) (5000 * manaUse), 0);
						}
						++times;
					}
					EntityUtils.sendManaMessage(player);
				}
				else if(name.matches("helpregen") && !world.isRemote){
					Line sight = Line.fromRaytrace(player, 50);
					EntityLivingBase entity = (EntityLivingBase) sight.getNearestEntity(world, EntityLivingBase.class, sight.getPosition(1), player);
					if(entity != null && mana >= 5000 * manaUse){
						WorldData.removeFromInteger(player, "mana", (int) (5000 * manaUse), 0);
						WorldData.setInteger(player, 10, "cooldown");
						entity.addPotionEffect(new PotionEffect(10, 600, 2));
						EntityUtils.sendManaMessage(player);
					}
				}
			}
			else if(Class.matches("galactic")){
				if(name.matches("fire")){
					if(500 * manaUse <= mana && !world.isRemote){
						world.spawnEntityInWorld(new EntityFireSpell(world, player, (float) (power * 15 * spirit)));
						WorldData.removeFromInteger(player, "mana", (int) (500 * manaUse), 0);
						WorldData.setInteger(player, 10, "cooldown");
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("explosive")){
					if(2000 * manaUse <= mana){
						world.spawnEntityInWorld(new EntityExplosiveSpell(world, player, (float) (power * spirit)));
						WorldData.removeFromInteger(player, "mana", (int) (2000 * manaUse), 0);
						if(!world.isRemote){
							WorldData.setInteger(player, 20, "cooldown");
						}		
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("lightning")){
					if(mana >= 500 * manaUse){
						Line line = Line.fromRaytrace(player, 300).toNearestBlock(world, false, 1).toNearestEntity(world, EntityLivingBase.class, 1, player);
						Position pos = line.getPosition(2);
						Line lightning = new Line(pos, new Position(pos.x, pos.y + 100, pos.z));
						if(world.isRemote){
							world.addWeatherEffect(new EntityLightningBolt(world, pos.x, pos.y, pos.z));
						}
						List entities = lightning.getEntities(world, Entity.class, false);
						int times = 0;
						DamageSource source = new EntityDamageSource("lightning", player);
						while(times < entities.size()){
							Entity entity = (Entity) entities.get(times);
							entity.attackEntityFrom(source, (float) spirit);
							++times;
						}
						List entities2 = world.getEntitiesWithinAABBExcludingEntity(player, AxisAlignedBB.fromBounds(pos.x - spirit, pos.y - spirit, pos.z - spirit, pos.x + spirit, pos.y + spirit, pos.z + spirit));
						times = 0;
						while(times < entities2.size()){
							Entity entity = (Entity) entities2.get(times);
							double distance = pos.getDistance(new Position(entity));
							if(distance <= spirit){
								if(distance < 2){
									distance = 2;
								}
								entity.attackEntityFrom(source, (float) ExtraUtils.divineAccurate(spirit * 4, distance));
							}
							++times;
						}
						if(!world.isRemote){
							WorldData.removeFromInteger(player, "mana", (int) (500 * manaUse), 0);
							EntityUtils.sendManaMessage(player);
						}
						world.playSound(pos.x, pos.y, pos.z, "ambient.weather.thunder", 3, 1, true);
					}
				}
				else if(name.matches("spark") && mana >= 1000 * manaUse){
					Line line1 = Line.fromRaytrace(player, 100).toNearestBlock(world, false, 1);
					EntityLivingBase entity = (EntityLivingBase) line1.getNearestEntity(world, EntityLivingBase.class, line1.getPosition(1), player);
					Line line = line1.toNearestEntity(world, EntityLivingBase.class, 1, player);
					if(world.isRemote){
						line.spawnParticles2(world, 1, 1, 0.1, ExtraUtils.divineAccurate(0.1, line.distance), 1);
					}
					Position target = line.getPosition(2);
					world.playSound(target.x, target.y, target.z, "fireworks.blast", 3, 1, true);
					if(entity != null){
						DamageSource source = new EntityDamageSource("spark", player);
						entity.attackEntityFrom(source, (float) (5 * spirit));
					}
					if(!world.isRemote){
						WorldData.removeFromInteger(player, "mana", (int) (1000 * manaUse), 0);
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("meteor") && mana >= 5000 * manaUse && !world.isRemote){
					Line line = Line.fromRaytrace(player, 200).toNearestBlock(world, false, 1).toNearestEntity(world, EntityLivingBase.class, 1, player);
					Position target = line.getPosition(2);
					world.spawnEntityInWorld(new EntityMeteor(world, player, target.x, target.y + 100, target.z, 0, 0, 0, (float) spirit * 0.5F));
					WorldData.removeFromInteger(player, "mana", (int) (5000 * manaUse), 0);
					EntityUtils.sendManaMessage(player);
				}
				else if(name.matches("starrain") && mana >= 20000 * manaUse && !world.isRemote){
					Line line = Line.fromRaytrace(player, 200).toNearestBlock(world, false, 1).toNearestEntity(world, EntityLivingBase.class, 1, player);
					Position target = line.getPosition(2);
					int times = 0;
					while(times < spirit){
						double randomX = 10 * Math.random() - 5;
						double randomY = 40 * Math.random() - 20;
						double randomZ = 10 * Math.random() - 5;
						double startX = target.x + randomX;
						double startY = target.y + 100 + randomY;
						double startZ = target.z + randomZ;
						world.spawnEntityInWorld(new EntityMeteor(world, player, startX, startY, startZ, 0, 0, 0, (float) (spirit * 0.5)));
						++times;
					}
					WorldData.removeFromInteger(player, "mana", (int) (20000 * manaUse), 0);
					EntityUtils.sendManaMessage(player);
				}
				else if(name.matches("fly") && !world.isRemote){
					if(WorldData.getBooleanOption(player, "fly", false)){
						WorldData.removeTimer(player, "fly");;
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will not fly anymore."));
					}
					else {
						WorldData.setBooleanOption(player, "fly", true);
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You will fly."));
					}
				}
				else if(name.matches("thunder")){
					if(WorldData.getInteger(player, "mana") >= 10000 * manaUse){
						Line line = Line.fromRaytrace(player, 200).toNearestBlock(world, false, 1).toNearestEntity(world, EntityLivingBase.class, 1, player);
						Position target = line.getPosition(2);
						Line thunder = new Line(new Position(target.x, target.y + 100, target.z), target);
						if(world.isRemote){
							world.addWeatherEffect(new EntityLightningBolt(world, target.x, target.y, target.z));
							world.addWeatherEffect(new EntityLightningBolt(world, target.x, target.y, target.z));
							world.addWeatherEffect(new EntityLightningBolt(world, target.x, target.y, target.z));
						}
						DamageSource source = new EntityDamageSource("thunder", player);
						List entities = thunder.getEntities(world, Entity.class, false);
						List entities2 = world.getEntitiesWithinAABBExcludingEntity(player, AxisAlignedBB.fromBounds(target.x - spirit, target.y - spirit, target.z - spirit, target.x + spirit, target.y + spirit, target.z + spirit));
						int times = 0;
						while(times < entities.size()){
							Entity entity = (Entity) entities.get(times);
							entity.attackEntityFrom(source, (float) (spirit * 10));
							++times;
						}
						times = 0;
						while(times < entities2.size()){
							Entity entity = (Entity) entities2.get(times);
							double distance = target.getDistance(new Position(entity));
							if(distance <= spirit){
								if(distance < 2){
									distance = 2;
								}
								entity.attackEntityFrom(source, (float) (ExtraUtils.divineAccurate(spirit * 10, distance)));
							}
							++times;
						}
						if(!world.isRemote){
							WorldData.removeFromInteger(player, "mana", (int) (10000 * manaUse), 0);
							WorldData.setInteger(player, 20, "cooldown");
						}
						EnergyBall.makeBall(world, target.x, target.y, target.z, spirit, 1, 0.9, 0.1, (int) (5000 * spirit));
						world.playSound(target.x, target.y, target.z, "ambient.weather.thunder", 3, 1, true);
						EntityUtils.sendManaMessage(player);
					}
				}
			}
			else if(Class.matches("necromancer")){
				if(name.matches("darkpulse") && !world.isRemote){
					if(mana >= 200 * manaUse){
						world.spawnEntityInWorld(new EntityDarkPulse(world, player, (float) (power * 5 * spirit)));
						WorldData.removeFromInteger(player, "mana", (int) (200 * manaUse), 0);
						WorldData.setInteger(player, 10, "cooldown");
						EntityUtils.sendManaMessage(player);
					}
				}
				else if(name.matches("chow") && !world.isRemote && mana >= 5000 * manaUse){
					world.spawnEntityInWorld(new EntityChow(player));
					WorldData.removeFromInteger(player, "mana", (int) (5000 * manaUse), 0);
					EntityUtils.sendManaMessage(player);
				}
				else if(name.matches("dreadlord") && !world.isRemote && mana >= 10000 * manaUse){
					world.spawnEntityInWorld(new EntityDreadLord(player));
					WorldData.removeFromInteger(player, "mana", (int) (10000 * manaUse), 0);
					EntityUtils.sendManaMessage(player);
				}
				else if(name.matches("soar") && !world.isRemote && mana >= 10000 * manaUse){
					world.spawnEntityInWorld(new EntitySoar(player));
					WorldData.removeFromInteger(player, "mana", (int) (10000 * manaUse), 0);
					EntityUtils.sendManaMessage(player);
				}
			}
			WorldData.updateClient(player);
		}
		return item;
	}
}
