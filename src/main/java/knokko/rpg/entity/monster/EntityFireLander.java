package knokko.rpg.entity.monster;

import knokko.rpg.items.main.RPGItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityFireLander extends EntityMob {
	
	public EntityFireLander(World worldIn) {
		super(worldIn);
		ItemStack stack = new ItemStack(RPGItems.ruby_sword, 1);
		stack.addEnchantment(Enchantment.fireAspect, 2);
		setCurrentItemOrArmor(0, stack);
		isImmuneToFire = true;
		tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 1.0D));
        tasks.addTask(3, new EntityAIWander(this, 1.0D));
        tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}
	
	public void applyEntityAttributes(){
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(30);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);
	}
	
	@Override
	public boolean isValidLightLevel(){
	    return true;
	}
	 
	 
	 @Override
	 public float func_180484_a(BlockPos pos)
	 {
	    return 0;
	 }
}
