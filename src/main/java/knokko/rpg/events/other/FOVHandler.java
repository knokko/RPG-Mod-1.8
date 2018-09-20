package knokko.rpg.events.other;

import knokko.rpg.data.WorldData;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FOVHandler {
	@SubscribeEvent
	public void fovEvent(FOVUpdateEvent event){
		if(WorldData.getString(event.entity, "race", "human").matches("eyeman")){
			if(WorldData.getBooleanOption(event.entity, "zoom", false)){
				event.newfov = 0.1F;
			}
		}
	}
}
