package knokko.rpg.entity.projectile;

import knokko.rpg.entity.creature.EntityEye;
import knokko.util.EntityUtils;
import knokko.util.Line;
import knokko.util.Position;
import knokko.util.bag.BlockBag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityLaser extends Entity{
	
	public Entity shooter;
	public String shooterId = "";
	
	public EntityLaser(World world) {
		super(world);
	}
	
	public EntityLaser(Entity entity){
		super(entity.worldObj);
		Vec3 look = entity.getLookVec();
		setPosition(entity.posX, entity.getEntityBoundingBox().minY + entity.height * 0.8, entity.posZ);
		motionX = look.xCoord;
		motionY = look.yCoord;
		motionZ = look.zCoord;
		shooter = entity;
		shooterId = entity.getUniqueID().toString();
	}

	@Override
	protected void entityInit() {
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		shooterId = nbt.getString("shooter");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		if(shooter != null){
			nbt.setString("shooter", shooter.getUniqueID().toString());
		}
	}
	
	public void onUpdate(){
		super.onUpdate();
		if(shooter == null && !shooterId.isEmpty()){
			shooter = EntityUtils.getEntityByUUID(worldObj, shooterId);
		}
		Line line = new Line(new Position(this), new Position(posX + motionX, posY + motionY, posZ + motionZ));
		BlockBag block = line.getNearestBlockInLine(worldObj, false, new Position(this));
		EntityLivingBase entity = (EntityLivingBase) line.getNearestEntity(worldObj, EntityLivingBase.class, new Position(this), this);
		if(entity != null && !(entity instanceof EntityEye)){
			onEntityHit(entity);
			//line.toNearestEntity(worldObj, EntityLivingBase.class, 1, this).spawnParticles(worldObj, 1, 0, 0, 0.01, 5);
			return;
		}
		if(block != null){
			onBlockHit(block);
			//line.toNearestBlock(worldObj, false, 1).spawnParticles(worldObj, 1, 0, 0, 0.01, 5);
			return;
		}
		//line.spawnParticles(worldObj, 1, 0, 0, 0.1, 5);
		setPosition(posX + motionX, posY + motionY, posZ + motionZ);
	}
	public void onEntityHit(EntityLivingBase entity){
		if(shooter == null){
			shooter = this;
		}
		if(!(entity instanceof EntityEye) && !entity.isImmuneToFire()){
			DamageSource source = new EntityDamageSourceIndirect("laser", this, shooter).setFireDamage();
			entity.attackEntityFrom(source, 5);
			setDead();
		}
	}
	
	public void onBlockHit(BlockBag block){
		setDead();
	}
}
