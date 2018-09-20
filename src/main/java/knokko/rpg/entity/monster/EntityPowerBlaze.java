package knokko.rpg.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityPowerBlaze extends EntityBlaze {

	public EntityPowerBlaze(World worldIn) {
		super(worldIn);
	}
	
	
	@Override
	public void applyEntityAttributes(){
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(20);
	}
	
	@Override
	 public boolean isValidLightLevel(){
	    return true;
	 }
	
	@Override
	public float func_180484_a(BlockPos p_180484_1_){
	    return 0;
	}
}
