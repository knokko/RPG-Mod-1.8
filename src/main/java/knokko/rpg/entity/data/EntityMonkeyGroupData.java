package knokko.rpg.entity.data;

import java.util.ArrayList;
import java.util.List;

import knokko.rpg.data.WorldData;
import knokko.rpg.entity.creature.EntityEye;
import knokko.rpg.entity.creature.EntityMonkey;
import knokko.util.EntityUtils;
import knokko.util.ExtraUtils;
import knokko.util.Position;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityMonkeyGroupData extends EntityData{

	public EntityMonkeyGroupData(World world) {
		super(world);
		WorldData.get(worldObj).monkeyTeams.add(this);
	}
	
	public EntityMonkey leader;
	public String leaderId;
	public List targets = new ArrayList();
	public List targetIds = new ArrayList();
	
	public void onUpdate(){
		if(targetIds.size() > 0){
			String id = (String) targetIds.get(targetIds.size() - 1);
			Entity entity = EntityUtils.getEntityByUUID(worldObj, id);
			if(entity instanceof EntityLivingBase){
				targets.add(entity);
				targetIds.remove(id);
			}
		}
		if(targets != null && !targets.isEmpty() && ExtraUtils.divineAccurate(ticksExisted, 10) == ticksExisted / 10){
			int times = 0;
			while(times < targets.size()){
				EntityLivingBase target = (EntityLivingBase) targets.get(times);
				if(target == null){
					targets.remove(target);
				}
				if(target != null && target.isDead){
					targets.remove(target);
				}
				++times;
			}
		}
		if(leader == null && !worldObj.isRemote){
			if(leaderId != null && !leaderId.isEmpty()){
				leader = (EntityMonkey) EntityUtils.getEntityByUUID(worldObj, leaderId);
			}
			else {
				leader = (EntityMonkey) worldObj.findNearestEntityWithinAABB(EntityMonkey.class, AxisAlignedBB.fromBounds(posX - 64, posY - 64,  posZ - 64,  posX + 64, posY + 64, posZ + 64), this);
			}
			if(ticksExisted > 40){
				setDead();
			}
		}
		if(leader != null){
			setPosition(leader.posX, leader.posY, leader.posZ);
			if(leader.isDead){
				leader = null;
				leaderId = null;
				ticksExisted = 0;
			}
		}
	}

	@Override
	protected void entityInit() {	
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		int times = 0;
		while(nbt.hasKey("target" + times)){
			targetIds.add(nbt.getString("target" + times));
			++times;
		}
		leaderId = nbt.getString("leader");
		WorldData.get(worldObj).monkeyTeams.add(this);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		int times = 0;
		while(times < targets.size()){
			EntityLivingBase entity = (EntityLivingBase) targets.get(times);
			nbt.setString("target" + times, entity.getUniqueID().toString());
			++times;
		}
		if(leader != null){
			nbt.setString("leader", leader.getUniqueID().toString());
		}
	}
}
