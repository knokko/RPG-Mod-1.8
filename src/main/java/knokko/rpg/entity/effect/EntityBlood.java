package knokko.rpg.entity.effect;

import java.util.Random;

import knokko.rpg.main.KnokkoRPG;
import knokko.util.BlockUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.client.particle.EntityReddustFX.Factory;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityBlood extends Entity{
	public int lifeTime;
	public int livingTicks;
	public EntityBlood(World world) {
		super(world);
	}
	public EntityBlood(World world, double x, double y, double z, int lifetime){
		super(world);
		setPosition(x, y, z);
		lifeTime = lifetime;
	}
	@Override
	protected void entityInit() {}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {}
	
	public void onUpdate(){
		++livingTicks;
		if(livingTicks >= lifeTime){
			setDead();
		}
		if(worldObj.isRemote){
			blood(posX, posY, posZ);
		}
	}
	
	public void blood(double x, double y, double z) {
		Random random = new Random();
		int times = 0;
		while(times < 30){
			KnokkoRPG.proxy.spawnParticle(worldObj, x - 0.5 + random.nextDouble(), y - 0.5 + random.nextDouble(), z - 0.5 + random.nextDouble(), 1, 0, 0);
			++times;
		}
	}
	public void onEntityUpdate(){}
}
