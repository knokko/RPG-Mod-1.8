package knokko.rpg.entity.effect;

import knokko.rpg.effect.EnergyBall;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityEnergyBall extends Entity{
	public int size;
	public int lifeTime;
	
	public double colorRed;
	public double colorGreen;
	public double colorBlue;
	public EntityEnergyBall(World world) {
		super(world);
	}
	public EntityEnergyBall(World world, double x, double y, double z, double red, double green, double blue, int size, int lifetime){
		super(world);
		this.setPosition(x,y,z);
		this.colorRed = red;
		this.colorGreen = green;
		this.colorBlue = blue;
		this.size = size;
		this.lifeTime = lifetime;
	}

	@Override
	protected void entityInit() {	
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		size = nbt.getInteger("size");
		lifeTime = nbt.getInteger("lifetime");
		colorRed = nbt.getDouble("red");
		colorGreen = nbt.getDouble("green");
		colorBlue = nbt.getDouble("blue");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setInteger("size", size);
		nbt.setInteger("lifetime", lifeTime);
		nbt.setDouble("red", colorRed);
		nbt.setDouble("green", colorGreen);
		nbt.setDouble("blue", colorBlue);
	}
	@Override
	public void onUpdate(){
		if(ticksExisted >= lifeTime){
			setDead();
		}
		EnergyBall.makeTinyBall(worldObj, posX, posY, posZ, colorRed, colorGreen, colorBlue);
	}
}
