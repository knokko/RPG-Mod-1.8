package knokko.rpg.entity.creature;

import knokko.rpg.entity.projectile.EntityLaser;
import knokko.util.ExtraUtils;
import net.minecraft.world.World;

public class EntityFireEye extends EntityEye{
	
	public int cooldown = 0;
	
	public EntityFireEye(World world) {
		super(world);
		isImmuneToFire = true;
	}
	public void onUpdate(){
		super.onUpdate();
		shootLasers();
	}
	public void shootLasers(){
		if(entityToAttack != null && !worldObj.isRemote){
			if(cooldown <= 0){
				worldObj.spawnEntityInWorld(new EntityLaser(this));
				cooldown = 30;
			}
			else {
				--cooldown;
			}
		}
	}
}
