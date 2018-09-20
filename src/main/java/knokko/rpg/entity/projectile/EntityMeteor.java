package knokko.rpg.entity.projectile;

import knokko.rpg.effect.EnergyBall;
import knokko.util.Line;
import knokko.util.Position;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityMeteor extends Entity {
	
	public Entity summoner;
	
	public EntityMeteor(World world) { 
		super(world);
	}
	
	public EntityMeteor(World world, Entity summoner, double x, double y, double z, double motionx, double motiony, double motionz, float strengt){
		super(world);
		setPosition(x, y, z);
		addVelocity(motionx, motiony, motionz);
		this.summoner = summoner;
		power = strengt;
	}
	
	public float power;
	@Override
	protected void entityInit() {}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		nbt.setFloat("power", power);
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		power = nbt.getFloat("power");
	}
	
	public void onUpdate(){
		motionY -= 0.03;
		Line line1 = new Line(new Position(this), new Position(posX + motionX, posY + motionY, posZ + motionZ));
		Line line2 = line1.toNearestBlock(worldObj, false, 1).toNearestEntity(worldObj, EntityLivingBase.class, 1, this);
		if(line1 != line2){
			Position impact = line2.getPosition(2);
			if(!worldObj.isRemote){
				Entity source = summoner;
				if(source == null){
					source = this;
				}
				worldObj.createExplosion(source, impact.x, impact.y + 1, impact.z, power, true);
			}
			setDead();
		}
		else {
			posX += motionX;
			posY += motionY;
			posZ += motionZ;
		}
		
	}
	
}
