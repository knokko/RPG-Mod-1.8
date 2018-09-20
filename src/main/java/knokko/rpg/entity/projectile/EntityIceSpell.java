package knokko.rpg.entity.projectile;

import knokko.rpg.effect.EnergyBall;
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

public class EntityIceSpell extends EntityThrowable{
	public float power;
	public Position start;

	public EntityIceSpell(World world) {
		super(world);
	}
	public EntityIceSpell(World world, EntityLivingBase living, float damage){
		super(world, living);
		power = damage;
		start = new Position(this);
	}
	@Override
	public void onImpact(MovingObjectPosition mop) {
		EnergyBall.makeBall(worldObj, posX, posY, posZ, 0.5, 1, 10, 5, 5000);
		DamageSource source = new EntityDamageSourceIndirect("ice spell", this, getThrower());
		if(mop.entityHit instanceof EntityLivingBase){
			EntityLivingBase entity = (EntityLivingBase) mop.entityHit;
			entity.addPotionEffect(new PotionEffect(2, (int) (20 * power), 1));
		}
		if(mop.entityHit != null){
			mop.entityHit.attackEntityFrom(source, power);
		}
		setDead();
	}
	public float getGravityVelocity(){
		return 0;
	}
	public void onUpdate(){
		super.onUpdate();
		if(start != null){
			Line line = new Line(new Position(this), start);
			line.spawnParticles(worldObj, 0.5, 1, 1, 0.1, 5);
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
