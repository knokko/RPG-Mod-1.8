package knokko.rpg.gui;

import knokko.rpg.data.WorldData;
import knokko.rpg.main.KnokkoRPG;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler {
	
	public static void register(){
		NetworkRegistry.INSTANCE.registerGuiHandler(KnokkoRPG.modInstance, new GuiHandler());
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == 0 && WorldData.getString(player, "class", "hunter").equals("necromancer"))
			return new GuiSkullRod(player, player.getHeldItem());
		return null;
	}
}
