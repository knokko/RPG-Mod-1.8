package knokko.rpg.entity.data;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public abstract class EntityData extends Entity{

	public EntityData(World world) {
		super(world);
	}
	public boolean canTriggerWalking(){
		return false;
	}
	public boolean doesEntityNotTriggerPressurePlate()
    {
        return true;
    }
}
