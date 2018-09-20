package knokko.rpg.entity.minion;

import knokko.rpg.RPG;
import knokko.rpg.entity.projectile.EntityDarkPulse;
import knokko.util.Line;
import knokko.util.Position;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntitySoar extends EntityNecromancerMinion {
	
	protected int cooldown;
	
	public EntitySoar(World world) {
		super(world);
	}

	@Override
	public void applyEntityAttributes(Entity master) {
		if(master instanceof EntityPlayer){
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4 * RPG.getPlayerSpirit((EntityPlayer) master));
			getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(RPG.getPlayerSpirit((EntityPlayer) master));
		}
		else {
			getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5);
		}
	}
	
	public EntitySoar(Entity master, Position spawn){
		super(master, spawn);
	}
	
	public EntitySoar(Entity master){
		super(master);
	}
	
	@Override
	public void rangedUpdate(){
		if(cooldown <= 0){
			EntityDarkPulse pulse = new EntityDarkPulse(worldObj, this, (float) getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
			worldObj.spawnEntityInWorld(pulse);
			cooldown = 40;
		}
		else {
			--cooldown;
		}
	}
	
	@Override
	public FightType getFightType(){
		return FightType.RANGED;
	}
	
	public void applyEntityAttributes(){
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6);
	}
}
