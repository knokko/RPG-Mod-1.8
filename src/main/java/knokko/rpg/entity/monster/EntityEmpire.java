package knokko.rpg.entity.monster;

import knokko.rpg.entity.projectile.EntityExplosiveSpell;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityEmpire extends EntityMob{
	
	public int cooldown = 100;
	public ItemStack wand = new ItemStack(Items.blaze_rod);
	
	public EntityEmpire(World world) {
		super(world);
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(7, new EntityAIWander(this, 1.0D));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		ItemStack boots = new ItemStack(Items.diamond_boots);
		ItemStack leggings = new ItemStack(Items.diamond_leggings);
		ItemStack chestplate = new ItemStack(Items.diamond_chestplate);
		ItemStack helmet = new ItemStack(Items.diamond_helmet);
		boots.addEnchantment(Enchantment.blastProtection, 4);
		leggings.addEnchantment(Enchantment.blastProtection, 4);
		chestplate.addEnchantment(Enchantment.blastProtection, 4);
		helmet.addEnchantment(Enchantment.blastProtection, 4);
		wand.addEnchantment(Enchantment.fireAspect, 2);
		setCurrentItemOrArmor(1, boots);
		setCurrentItemOrArmor(2, leggings);
		setCurrentItemOrArmor(3, chestplate);
		setCurrentItemOrArmor(4, helmet);
	}
	
	public EntityEmpire(World world, int xCoord, int yCoord, int zCoord) {
		this(world);
		setPosition(xCoord, yCoord, zCoord);
	}

	public void onUpdate(){
		super.onUpdate();
		EntityExplosiveSpell spell = new EntityExplosiveSpell(worldObj, this, 4);
		spell.power = 3;
		if(cooldown > 0 && !worldObj.isRemote){
			--cooldown;
		}
		if(cooldown == 0 && !worldObj.isRemote && getAttackTarget() != null){
			cooldown = 100;
			worldObj.spawnEntityInWorld(spell);
			swingItem();
		}
		if(getAttackTarget() instanceof EntityEmpire){
			setAttackTarget(null);
		}
	}
	
	public ItemStack getHeldItem(){
		return wand;
	}
	
	public void applyEntityAttributes(){
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(48);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1);
	}
	
	@Override
	public boolean canDespawn(){
		return false;
	}
}
