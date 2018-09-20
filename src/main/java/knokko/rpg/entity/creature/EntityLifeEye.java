package knokko.rpg.entity.creature;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityLifeEye extends EntityEye{
	public List partners;
	public List closeMobs;
	public EntityLivingBase closeMob;
	public int cooldown;
	
	
	public EntityLifeEye(World world) {
		super(world);
	}
	public EntityLifeEye(World world, double x, double y, double z, int type){
		super(world);
		setPosition(x, y, z);
	}
	public void onUpdate(){
		super.onUpdate();
		recoverEyes();
	}
	public void recoverEyes(){
		if(groupdata != null && !worldObj.isRemote){
			if(groupdata.hurtEye != null && cooldown == 0){
				groupdata.hurtEye.heal(1);
				cooldown = 400;
			}
		}
		if(cooldown > 0){
			--cooldown;
		}
	}
}
