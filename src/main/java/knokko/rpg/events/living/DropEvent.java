package knokko.rpg.events.living;

import java.util.Random;

import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DropEvent {
	
	@SubscribeEvent
	public void dropEvent(LivingDropsEvent event){
		Random random = new Random();
		if(event.entityLiving instanceof EntitySkeleton){
			if(random.nextInt(10) == 2){
				event.entityLiving.dropItem(Items.skull, 1);
			}
		}
	}
}
