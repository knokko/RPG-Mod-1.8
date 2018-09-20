package knokko.rpg.entity.creature;

import knokko.rpg.data.WorldData;
import knokko.rpg.entity.data.EntityMonkeyGroupData;
import knokko.rpg.items.main.RPGItems;
import knokko.util.EntityUtils;
import knokko.util.Position;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMonkey extends EntityCreature{
	
	public EntityMonkey(World world) {
		super(world);
		setCurrentItemOrArmor(0, new ItemStack(RPGItems.bananaSword));
		tasks.addTask(5, new EntityAIWander(this, 0.8D));
	}
	
	public EntityMonkeyGroupData data;
	public Position position;
	public String groupId;
	private int strikeCooldown;
	public Entity entityToAttack;
	
	public void onUpdate(){
		super.onUpdate();
		attackTarget();
		if(data == null && !worldObj.isRemote){
			if(groupId != null && !groupId.isEmpty()){
				data = (EntityMonkeyGroupData) EntityUtils.getEntityByUUID(worldObj, groupId);
			}
			else {
				if(ticksExisted > 10){
					EntityMonkeyGroupData group = (EntityMonkeyGroupData) EntityUtils.getNearestEntityInList(position, WorldData.get(worldObj).monkeyTeams);
					if(group == null && ticksExisted > 50){
						group = new EntityMonkeyGroupData(worldObj);
						position.spawnEntity(group, worldObj);
						data = group;
					}
					else {
						data = group;
					}
				}
			}
		}
		if(strikeCooldown > 0){
			--strikeCooldown;
		}
		double speed = getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
		if(data != null && position != null){
			entityToAttack = EntityUtils.getNearestEntityInList(position, data.targets);
			if(entityToAttack == null && position.getDistance(new Position(data)) > 32 && !hasPath()){
				navigator.tryMoveToEntityLiving(data, speed);
			}
		}
		if(entityToAttack != null && (!this.hasPath() || rand.nextInt(20) == 5)){
			navigator.tryMoveToEntityLiving(entityToAttack, speed);
		}
		position = new Position(this);
	}
	public boolean attackEntityFrom(DamageSource damage, float amount){
		if(damage.getEntity() instanceof EntityLivingBase && data != null){
			if(data.targets != null){
				data.targets.add(damage.getEntity());
			}
		}
		return super.attackEntityFrom(damage, amount);
	}
	public void applyEntityAttributes(){
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.7);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32);
	}
	public void writeEntityToNBT(NBTTagCompound nbt){
		super.writeEntityToNBT(nbt);
		if(data != null){
			nbt.setString("group", data.getUniqueID().toString());
		}
	}
	public void readEntityFromNBT(NBTTagCompound nbt){
		super.readEntityFromNBT(nbt);
		groupId = nbt.getString("group");
	}
	public void attackTarget(){
		if(entityToAttack != null && position != null && !worldObj.isRemote){
			if(position.getDistance(new Position(entityToAttack)) <= 3 && strikeCooldown <= 0){
				entityToAttack.attackEntityFrom(DamageSource.causeMobDamage(this), 5);
				strikeCooldown = 10;
			}
		}
	}
}
