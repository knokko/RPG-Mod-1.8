package knokko.rpg.entity.monster;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.world.World;

public class FireDragon extends Dragon{

	public int cooldown;
	
	public FireDragon(World worldIn) {
		super(worldIn);
		setSize(30,3);
	}

	@Override
	public void shootingUpdate() {
		if(!worldObj.isRemote){
			if(cooldown <= 0){
				worldObj.spawnEntityInWorld(new EntitySmallFireball(worldObj, this, 2, 2, 2));
				cooldown = 40;
			}
			else {
				--cooldown;
			}
		}
	}
	
	@Override
	public void applyEntityAttributes(){
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(400);
	}

}
