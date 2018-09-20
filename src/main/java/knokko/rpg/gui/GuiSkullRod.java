package knokko.rpg.gui;

import java.io.IOException;

import knokko.rpg.data.WorldData;
import knokko.rpg.main.KnokkoRPG;
import knokko.rpg.packet.NecromancyMessage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GuiSkullRod extends GuiScreen {
	
	public static final String DEFAULT = "Default Target Type (the way your army will select targets after they spawn)";
	public static final String ALL = "Set All Target Type (the way your army will select targets after you click a button below)";
	public static final String TARGET = "Skull Rod Target Type (the way the soldier who you click on with a skull rod will select targets)";
	
	public final EntityPlayer player;
	public final ItemStack stack;

	public GuiSkullRod(EntityPlayer player, ItemStack stack) {
		this.player = player;
		this.stack = stack;
	}
	
	@Override
	public void initGui(){
		int w = width / 2;
		int h = height / 2;
		int t = 0;
		while(t < 3){
			buttonList.add(new GuiButton(0 + t * 6, w - 175, h - 30 + t * 50, 55, 20, "passive"));
			buttonList.add(new GuiButton(1 + t * 6, w - 115, h - 30 + t * 50, 55, 20, "guardian"));
			buttonList.add(new GuiButton(2 + t * 6, w - 55, h - 30 + t * 50, 55, 20, "defensive"));
			buttonList.add(new GuiButton(3 + t * 6, w + 5, h - 30 + t * 50, 55, 20, "helper"));
			buttonList.add(new GuiButton(4 + t * 6, w + 65, h - 30 + t * 50, 55, 20, "mobkiller"));
			buttonList.add(new GuiButton(5 + t * 6, w + 125, h - 30 + t * 50, 55, 20, "offensive"));
			++t;
		}
		buttonList.add(new GuiButton(18, w - 200, h + 100, 60, 20, "recall army"));
	}
	
	@Override
	public void drawScreen(int x, int y, float ticks){
		int stringWidth = fontRendererObj.getStringWidth(DEFAULT);
		fontRendererObj.drawString(DEFAULT, width / 2 - stringWidth / 2, height / 2 - 50, 0);
		stringWidth = fontRendererObj.getStringWidth(ALL);
		fontRendererObj.drawString(ALL, width / 2 - stringWidth / 2, height / 2, 0);
		stringWidth = fontRendererObj.getStringWidth(TARGET);
		fontRendererObj.drawString(TARGET, width / 2 - stringWidth / 2, height / 2 + 50, 0);
		String standartTargetType = WorldData.getString(player, "standartnecromancytarget", "helper");
		if(standartTargetType.equals("monsterkiller"))
			standartTargetType = "mobkiller";
		String displayName = stack.getDisplayName();
		if(displayName.equals("monsterkiller"))
			displayName = "mobkiller";
		int t = 0;
		while(t < 6){
			GuiButton button = (GuiButton) buttonList.get(t);
			if(button.displayString.equals(standartTargetType))
				button.packedFGColour = 7986;
			else
				button.packedFGColour = 14737632;
			++t;
		}
		t = 12;
		while(t < 18){
			GuiButton button = (GuiButton) buttonList.get(t);
			if(button.displayString.equals(displayName))
				button.packedFGColour = 7986;
			else
				button.packedFGColour = 14737632;
			++t;
		}
		super.drawScreen(x, y, ticks);
	}
	
	@Override
	public void actionPerformed(GuiButton button) throws IOException{
		super.actionPerformed(button);
		KnokkoRPG.network.sendToServer(new NecromancyMessage(player, button.id));
		if(button.id < 6)
			WorldData.setString(player, button.displayString, "standartnecromancytarget");
		if(button.id >= 12 && button.id < 18)
			stack.setStackDisplayName(button.displayString.equals("mobkiller") ? "monsterkiller" : button.displayString);
	}
}
