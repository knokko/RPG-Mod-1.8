package knokko.rpg.entity.monster;

import knokko.util.Position;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityLavaShark extends EntityFlying implements IMob{
	
	public double distanceToTarget;
	
	public EntityLavaShark(World world) {
		super(world);
		isImmuneToFire = true;
		setSize(4, 2);
	}
	
	public boolean canBreatheUnderwater(){
		return true;
	}
	
	public void onUpdate(){
		super.onUpdate();
		if(isInWater() || isInLava()){
			onSwimmingUpdate();
		}
		else {
			motionY = -0.2;
		}
		if(getAttackTarget() == null){
			setAttackTarget(worldObj.getClosestPlayerToEntity(this, getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue()));
		}
		else {
			distanceToTarget = new Position(this).getDistance(new Position(getAttackTarget()));
			if(distanceToTarget < 2){
				getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this), (float) getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
			}
		}
	}
	
	public void applyEntityAttributes(){
		super.applyEntityAttributes();
		getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.1);
	}
	
	public void onSwimmingUpdate(){
		EntityLivingBase target = getAttackTarget();
		double speed = getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
		if(target != null){
			faceEntity(target, 10, 5);
			if(target.posX > posX + 1){
				motionX += speed;
			}
			if(target.posX < posX - 1){
				motionX -= speed;
			}
			if(target.posY > posY + 1){
				motionY += speed;
			}
			if(target.posY < posY - 1){
				motionY -= speed;
			}
			if(target.posZ > posZ + 1){
				motionZ += speed;
			}
			if(target.posZ < posZ - 1){
				motionZ -= speed;
			}
		}
		else {
			motionX = rand.nextDouble() * 0.5 * speed;
			motionY = rand.nextDouble() * 0.5 * speed;
			motionZ = rand.nextDouble() * 0.5 * speed;
		}
	}
	
	public boolean isCreatureType(EnumCreatureType type, boolean b){
		return type == EnumCreatureType.WATER_CREATURE;
	}
}
