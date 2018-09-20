package knokko.rpg.entity.creature;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityIceEye extends EntityEye{

	public EntityIceEye(World world) {
		super(world);
	}
	
	public void freezeTarget(){
			if(entityToAttack instanceof EntityLivingBase){
				if(!((EntityLivingBase) entityToAttack).isPotionActive(2) && entityToAttack.isDead){
					((EntityLivingBase) entityToAttack).addPotionEffect(new PotionEffect(2, 200, 2));
				}
			}
	}
	public void onUpdate(){
		super.onUpdate();
		freezeTarget();
	}
}
