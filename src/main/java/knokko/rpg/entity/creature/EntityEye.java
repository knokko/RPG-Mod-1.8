package knokko.rpg.entity.creature;

import knokko.rpg.data.WorldData;
import knokko.rpg.entity.data.EntityEyeGroupData;
import knokko.rpg.entity.data.EntityMonkeyGroupData;
import knokko.util.BlockUtils;
import knokko.util.EntityUtils;
import knokko.util.ExtraUtils;
import knokko.util.Position;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityEye extends EntityCreature{
	
	public EntityEyeGroupData groupdata;
	public String groupId;
	public Position position;
	private int pathCooldown;
	private int strikeCooldown;
	public Entity entityToAttack;
	
	public EntityEye(World world) {
		super(world);
		tasks.addTask(5, new EntityAIWander(this, 0.8D));
	}
	public void onUpdate(){
		super.onUpdate();
		double speed = getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
		followData();
		attackTarget();
		if(entityToAttack != null && pathCooldown <= 0){
			navigator.tryMoveToEntityLiving(entityToAttack, speed);
			pathCooldown = 30;
		}
		if(entityToAttack != null && position != null){
			double height = entityToAttack.posY - posY;
			double distance = position.getDistance(new Position(entityToAttack));
			double pitch = -Math.toDegrees(Math.asin(ExtraUtils.divineAccurate(height, distance)));
			rotationPitch = (float) pitch;
		}
		if(pathCooldown > 0){
			--pathCooldown;
		}
		if(groupdata == null && !worldObj.isRemote){
			if(groupId != null && !groupId.isEmpty()){
				groupdata = (EntityEyeGroupData) EntityUtils.getEntityByUUID(worldObj, groupId);
			}
			else {
				if(ticksExisted > 10){
					EntityEyeGroupData group = (EntityEyeGroupData) EntityUtils.getNearestEntityInList(position, WorldData.get(worldObj).eyeTeams);
					if(group == null && ticksExisted > 50){
						group = new EntityEyeGroupData(worldObj);
						position.spawnEntity(group, worldObj);
						groupdata = group;
					}
					else {
						groupdata = group;
					}
				}
			}
		}
		if(getHealth() < 30){
			if(groupdata != null){
				if(groupdata.hurtEye == null){
					groupdata.hurtEye = this;
				}
			}
		}
		if(strikeCooldown > 0){
			--strikeCooldown;
		}
		position = new Position(this);
	}
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt){
		super.writeEntityToNBT(nbt);
		if(groupdata != null){
			nbt.setString("group", groupdata.getUniqueID().toString());
		}
	}
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt){
		super.readEntityFromNBT(nbt);
		groupId = nbt.getString("group");
	}
	
	public void followData(){
		double speed = getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
		if(groupdata != null && position != null){
			entityToAttack = EntityUtils.getNearestEntityInList(position, groupdata.targets);
			if(entityToAttack == null && position.getDistance(new Position(groupdata)) > 32 && !hasPath()){
				navigator.tryMoveToEntityLiving(groupdata, speed);
			}
		}
	}
	public void attackTarget(){
			if(entityToAttack != null && position != null && !worldObj.isRemote){
				if(position.getDistance(new Position(entityToAttack)) <= 3 && strikeCooldown <= 0){
					entityToAttack.attackEntityFrom(DamageSource.causeMobDamage(this), 5);
					strikeCooldown = 10;
				}
			}
	}
	public boolean attackEntityFrom(DamageSource damage, float f){
		if(damage.getEntity() != null && damage.getEntity() instanceof EntityLivingBase && groupdata != null){
			if(!groupdata.targets.contains(damage.getEntity())){
				groupdata.targets.add(damage.getEntity());
			}
		}
		return super.attackEntityFrom(damage, f);
	}
	
	public void applyEntityAttributes(){
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64);
	}
}
