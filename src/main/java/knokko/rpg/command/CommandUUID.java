package knokko.rpg.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

public class CommandUUID implements ICommand{
	public List alliases = new ArrayList();
	public CommandUUID(){
		alliases.add("id");
		alliases.add("uuid");
		alliases.add("ID");
	}
	@Override
	public int compareTo(Object o) {
		return 0;
	}

	@Override
	public String getName() {
		return "UUID";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/UUID (player)";
	}

	@Override
	public List getAliases() {
		return alliases;
	}

	@Override
	public void execute(ICommandSender sender, String[] string) {
		if(string.length == 0){
			if(sender instanceof EntityPlayer){
				sender.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GOLD + "Your UUID is " + ((EntityPlayer) sender).getUniqueID()));
			}
		}
		if(string.length == 1){
			EntityPlayer player = sender.getEntityWorld().getPlayerEntityByName(string[0]);
			if(player == null){
				sender.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "That player cannot be found."));
			}
			else {
				sender.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GOLD + "" + player.getDisplayName() + "'s UUID is " + EnumChatFormatting.GOLD + player.getUniqueID()));
			}
		}
	}

	@Override
	public boolean canCommandSenderUse(ICommandSender sender) {
		return true;
	}


	@Override
	public boolean isUsernameIndex(String[] string, int i) {
		return false;
	}
	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		return null;
	}

}
