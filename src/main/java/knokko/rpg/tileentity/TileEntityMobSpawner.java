package knokko.rpg.tileentity;

import knokko.rpg.entity.monster.EntityEmpire;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMobSpawner extends TileEntity{
	public Entity entity;
	public TileEntityMobSpawner(Entity entity){
		if(entity != null){
			this.entity = entity;
		}
		else {
			entity = new EntityZombie(worldObj);
		}
	}
	public TileEntityMobSpawner(int i){
		if(i == 0){
			entity = new EntityZombie(worldObj);
		}
		else if(i == 1){
			entity = new EntityEmpire(worldObj, pos.getX(), pos.getY(), pos.getZ());
		}
	}
}
