package knokko.util.bag;

import net.minecraft.entity.Entity;

public class EntityBag {
	public Entity entity;
	
	public double x;
	public double y;
	public double z;
	
	public EntityBag(Entity entity, double x, double y, double z){
		this.entity = entity;
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
