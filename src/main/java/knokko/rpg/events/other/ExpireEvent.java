package knokko.rpg.events.other;

import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ExpireEvent {
	@SubscribeEvent
	public void itemExpireEvent(ItemExpireEvent event){
		event.setCanceled(true);
	}
}
