package knokko.rpg.entity.projectile;

import knokko.rpg.entity.minion.EntityNecromancerMinion;
import knokko.rpg.main.KnokkoRPG;
import knokko.util.EntityUtils;
import knokko.util.Line;
import knokko.util.Position;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityDarkPulse extends EntityThrowable{
	
	public float power;
	public Position start;
	
	public EntityDarkPulse(World world) {
		super(world);
		start = new Position(this);
	}
	
	public EntityDarkPulse(World world, EntityLivingBase thrower, float strengt){
		super(world, thrower);
		power = strengt;
		start = new Position(this);
	}

	@Override
	public void onImpact(MovingObjectPosition mop) {
		if(mop.entityHit instanceof EntityLivingBase && mop.entityHit != getThrower()){
			if(getThrower() instanceof EntityNecromancerMinion){
				if(((EntityNecromancerMinion)getThrower()).master == mop.entityHit){
					return;
				}
			}
			EntityLivingBase entity = (EntityLivingBase) mop.entityHit;
			entity.addPotionEffect(new PotionEffect(15, 20));
			EntityDamageSourceIndirect source = new EntityDamageSourceIndirect("dark pulse", this, getThrower());
			entity.attackEntityFrom(source, power);
		}
		if(mop.entityHit != getThrower()){
			setDead();
		}
	}
	
	public float getGravityVelocity(){
		return 0;
	}
	
	public void onUpdate(){
		super.onUpdate();
		if(start != null){
			Line line = new Line(new Position(this), start);
			line.spawnParticles(worldObj, 0.1, 0.1, 0.1, 0.1, 5);
		}
		start = new Position(this);
		if(ticksExisted > 1000){
			setDead();
		}
		motionX *= 1.02;
		motionY *= 1.02;
		motionZ *= 1.02;
	}
	
	public void writeEntityToNBT(NBTTagCompound nbt){
		nbt.setFloat("power", power);
		super.writeEntityToNBT(nbt);
	}
	public void readEntityFromNBT(NBTTagCompound nbt){
		power = nbt.getFloat("power");
		super.readEntityFromNBT(nbt);
	}
}
