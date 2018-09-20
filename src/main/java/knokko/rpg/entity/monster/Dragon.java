package knokko.rpg.entity.monster;

import java.util.List;

import knokko.util.ExtraUtils;
import knokko.util.Line;
import knokko.util.Position;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class Dragon extends EntityFlying implements IMob{
	
	protected EntityLivingBase target;
	
	public Dragon(World worldIn) {
		super(worldIn);
		setSize(30, 3);
		isImmuneToFire = true;
	}
	
	public void onUpdate(){
		super.onUpdate();
		if(target == null){
			target = worldObj.getClosestPlayerToEntity(this, getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue());
		}
		if(target != null){
			followTarget();
			shootingUpdate();
			faceEntity(target, 30, 30);
		}
		attackEntities();
	}
	
	public abstract void shootingUpdate();
	
	public void attackEntities(){
		AxisAlignedBB aabb = getEntityBoundingBox();
		List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, aabb);
		int times = 0;
		while(times < list.size()){
			Entity entity = (Entity) list.get(times);
			if(!(entity instanceof EntityItem)){
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
			}
			++times;
		}
	}
	
	public void followTarget(){
		if(!worldObj.isRemote){
			Line line = new Line(new Position(this), new Position(target));
			double speed = getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
			motionX = ExtraUtils.divineAccurate(line.distanceXT, line.distance) * speed;
			motionY = ExtraUtils.divineAccurate(line.distanceYT, line.distance) * speed;
			motionZ = ExtraUtils.divineAccurate(line.distanceZT, line.distance) * speed;
		}
	}
	
	public void setAttackTarget(EntityLivingBase entity){
		target = entity;
	}
	
	public void setRevengeTarget(EntityLivingBase entity){
		target = entity;
	}
	
	public EntityLivingBase getAttackTarget(){
		return target;
	}
	
	public EntityLivingBase getAITarget(){
		return target;
	}
	
	public void applyEntityAttributes(){
		super.applyEntityAttributes();
		getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
	}
	
	public AxisAlignedBB getCollisionBox(Entity in){
		return getEntityBoundingBox();
	}
	
	public AxisAlignedBB getBoundingBox(){
		return getEntityBoundingBox();
	}
}
