package knokko.rpg.events.other;

import java.util.List;

import knokko.rpg.data.WorldData;
import knokko.rpg.items.TestItem;
import knokko.rpg.items.main.RPGItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatEvent {
	@SubscribeEvent
	public void chatEvent(ServerChatEvent event){
		World world = event.player.worldObj;
		if(WorldData.getString(event.player, "chat", "public").matches("team")){
			event.setCanceled(true);
			List players = world.playerEntities;
			int times = 0;
			while(times < players.size()){
				EntityPlayer receiver = (EntityPlayer) players.get(times);
				if(WorldData.isOnSameTeam(event.player, receiver)){
					receiver.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.BLUE + "<" + event.username + "> " + event.message));
				}
				++times;
			}
		}
		if(event.message.matches("lasered")){
			event.setCanceled(true);
		}
	}
}
