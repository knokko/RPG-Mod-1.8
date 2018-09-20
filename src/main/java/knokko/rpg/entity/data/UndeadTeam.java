package knokko.rpg.entity.data;

import java.util.ArrayList;
import java.util.List;

import knokko.rpg.data.WorldData;
import knokko.rpg.entity.minion.EntityNecromancerMinion;
import knokko.rpg.entity.minion.TargetType;
import knokko.util.EntityUtils;
import knokko.util.Position;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class UndeadTeam extends EntityData{
	
	public String customId = "";
	public int updateCooldown = 0;
	
	public UndeadTeam(World world) {
		super(world);
	}
	
	public UndeadTeam(Entity owner){
		super(owner.worldObj);
		setPosition(owner.posX, owner.posY, owner.posZ);
		customId = owner.getUniqueID().toString();
		master = owner;
		WorldData.get(worldObj).undeadTeams.add(this);
		members.add(this);
		members.add(owner);
	}
	
	private List targets = new ArrayList();
	private List attackers = new ArrayList();
	private List masterAttackers = new ArrayList();
	private List targetIds = new ArrayList();
	private List attackersIds = new ArrayList();
	private List masterAttackersIds = new ArrayList();
	public List members = new ArrayList();
	public Entity master;
	private String masterId = "";
	
	@Override
	protected void entityInit() {}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		masterId = nbt.getString("master");
		customId = masterId;
		int times = 0;
		while(nbt.hasKey("target" + times)){
			targetIds.add(nbt.getString("target" + times));
			++times;
		}
		times = 0;
		while(nbt.hasKey("attacker" + times)){
			attackersIds.add(nbt.getString("attacker" + times));
			++times;
		}
		times = 0;
		while(nbt.hasKey("masterAttacker" + times)){
			masterAttackersIds.add(nbt.getString("masterAttacker" + times));
			++times;
		}
		WorldData.get(worldObj).undeadTeams.add(this);
	}
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		if(master != null){
			nbt.setString("master", master.getUniqueID().toString());
		}
		if(targets.size() > 0){
			int times = 0;
			while(times < targets.size()){
				Entity entity = (Entity) targets.get(times);
				nbt.setString("target" + times, entity.getUniqueID().toString());
				++times;
			}
		}
		if(attackers.size() > 0){
			int times = 0;
			while(times < attackers.size()){
				Entity entity = (Entity) attackers.get(times);
				nbt.setString("attacker" + times, entity.getUniqueID().toString());
				++times;
			}
		}
		if(masterAttackers.size() > 0){
			int times = 0;
			while(times < masterAttackers.size()){
				Entity entity = (Entity) masterAttackers.get(times);
				nbt.setString("masterAttacker" + times, entity.getUniqueID().toString());
				++times;
			}
		}
	}
	
	public void onUpdate(){
		super.onUpdate();
		if(targetIds.size() > 0){
			String string = (String) targetIds.get(targetIds.size() - 1);
			Entity entity = EntityUtils.getEntityByUUID(worldObj, string);
			if(entity != null){
				targets.add(entity);
				targetIds.remove(targetIds.size() - 1);
			}
		}
		if(attackersIds.size() > 0){
			String string = (String) attackersIds.get(attackersIds.size() - 1);
			Entity entity = EntityUtils.getEntityByUUID(worldObj, string);
			if(entity != null){
				attackers.add(entity);
				attackersIds.remove(attackersIds.size() - 1);
			}
		}
		if(masterAttackersIds.size() > 0){
			String string = (String) masterAttackersIds.get(masterAttackersIds.size() - 1);
			Entity entity = EntityUtils.getEntityByUUID(worldObj, string);
			if(entity != null){
				masterAttackers.add(entity);
				masterAttackersIds.remove(masterAttackersIds.size() - 1);
			}
		}
		if(master == null && !masterId.isEmpty()){
			master = EntityUtils.getEntityByUUID(worldObj, masterId);
		}
		if(master != null){
			setPosition(master.posX, master.posY, master.posZ);
		}
		if(updateCooldown <= 0){
			updateCooldown = 20;
			if(targets.size() > 0){
				int times = 0;
				while(times < targets.size()){
					Entity target = (Entity) targets.get(times);
					if(target == null){
						targets.remove(times);
					}
					else if(target.isDead){
						targets.remove(times);
					}
					++times;
				}
			}
			if(attackers.size() > 0){
				int times = 0;
				while(times < attackers.size()){
					Entity target = (Entity) attackers.get(times);
					if(target == null){
						attackers.remove(times);
					}
					else if(target.isDead){
						attackers.remove(times);
					}
					++times;
				}
			}
			if(masterAttackers.size() > 0){
				int times = 0;
				while(times < masterAttackers.size()){
					Entity target = (Entity) masterAttackers.get(times);
					if(target == null){
						masterAttackers.remove(times);
					}
					else if(target.isDead){
						masterAttackers.remove(times);
					}
					++times;
				}
			}
		}
		else {
			--updateCooldown;
		}
	}
	/**
	 * Used when an entity attacks an entity in this team.
	 * @param target
	 */
	public void addTarget(Entity target, boolean attackedOwner, boolean attackedTeam){
		if(target instanceof EntityLivingBase){
			if(!targets.contains(target)){
				targets.add(target);
			}
			if(attackedTeam && !attackers.contains(target)){
				attackers.add(target);
			}
			if(attackedOwner && !masterAttackers.contains(target)){
				masterAttackers.add(target);
			}
		}
	}
	
	public Entity getTarget(EntityNecromancerMinion minion){
		TargetType tt = minion.targetType;
		Position position = new Position(minion);
		if(master != null && position.getDistance(new Position(master)) < 48){
			double d = minion.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue();
			if(tt.equals(TargetType.DEFENSIVE)){
				return EntityUtils.getNearestEntityInList(position, attackers);
			}
			else if(tt.equals(TargetType.GUARDIAN)){
				return EntityUtils.getNearestEntityInList(new Position(master), masterAttackers);
			}
			else if(tt.equals(TargetType.HELPER)){
				return EntityUtils.getNearestEntityInList(position, targets);
			}
			else if(tt.equals(TargetType.PASSIVE)){
				return null;
			}
			else if(tt.equals(TargetType.MONSTERKILLER)){
				return EntityUtils.getNearestEntityInList(position, worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.fromBounds(minion.posX - d, minion.posY - d, minion.posZ - d, minion.posX + d, minion.posY + d, minion.posZ + d), IMob.mobSelector));
			}
			else if(tt.equals(TargetType.OFFENSIVE)){
				List list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.fromBounds(minion.posX - d, minion.posY - d, minion.posZ - d, minion.posX + d, minion.posY + d, minion.posZ + d));
				list.removeAll(members);
				list.removeAll(WorldData.getOnlinePlayersInTeam(master));
				return EntityUtils.getNearestEntityInList(position, list);
			}
		}
		return null;
	}
	
	public List getAllTargets(){
		return targets;
	}
	
	public static UndeadTeam getTeam(Entity leader){
		WorldData data = WorldData.get(leader.worldObj);
		int times = 0;
		while(times < data.undeadTeams.size()){
			UndeadTeam team = (UndeadTeam) data.undeadTeams.get(times);
			if(team.customId.matches(leader.getUniqueID().toString())){
				return team;
			}
			++times;
		}
		return null;
	}
}
