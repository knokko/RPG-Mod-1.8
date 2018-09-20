package knokko.rpg.command;

import java.util.ArrayList;
import java.util.List;

import knokko.rpg.data.WorldData;
import knokko.rpg.entity.data.UndeadTeam;
import knokko.rpg.entity.minion.EntityNecromancerMinion;
import knokko.rpg.entity.minion.TargetType;
import knokko.rpg.items.SkullRod;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

public class CommandNecromancy implements ICommand{
	
	public final List aliases;
	
	public static final ChatComponentTranslation HOLD_SKULL_ROD = new ChatComponentTranslation(EnumChatFormatting.RED + "You must hold a skull rod to do this action.");
	public static final ChatComponentTranslation IS_TARGETTYPE = new ChatComponentTranslation(EnumChatFormatting.RED + "/necromancy targetall defensive/offensive/helper/passive/monsterkiller/guardian");
	public static final ChatComponentTranslation OPTIONS = new ChatComponentTranslation("standarttarget");
	
	public CommandNecromancy(){
		aliases = new ArrayList();
		aliases.add("nec");
	}
	
	@Override
	public int compareTo(Object o) {
		return 0;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/necromancy target/option/targetall";
	}

	public void execute(ICommandSender sender, String[] string) {
		if(sender instanceof EntityPlayer){
			if(string.length > 0){
				EntityPlayer player = (EntityPlayer) sender;
				if(string[0].matches("target")){
					if(string.length == 1){
						player.addChatMessage(new ChatComponentTranslation("/necromancy target defensive/guardian/helper/passive/monsterkiller/offensive"));
					}
					else if(TargetType.isTargetType(string[1])){
						ItemStack stack = player.getCurrentEquippedItem();
						if(stack != null){
							Item item = stack.getItem();
							if(item instanceof SkullRod){
								stack.setStackDisplayName(string[1]);
							}
							else {
								player.addChatMessage(HOLD_SKULL_ROD);
							}
						}
						else {
							player.addChatMessage(HOLD_SKULL_ROD);
						}
					}
					else {
						player.addChatMessage(new ChatComponentTranslation("/necromancy target defensive/guardian/helper/passive/monsterkiller/offensive"));
					}
				}
				else if(string[0].matches("targetall")){
					UndeadTeam team = UndeadTeam.getTeam(player);
					if(string.length > 1){
						if(TargetType.isTargetType(string[1])){
							if(team != null){
								List members = team.members;
								int times = 0;
								while(times < members.size()){
									Object object = members.get(times);
									if(object instanceof EntityNecromancerMinion){
										((EntityNecromancerMinion) object).targetType = TargetType.fromString(string[1]);
									}
									++times;
								}
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + "The new targettype of your army is " + string[1]));
							}
							else {
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You don't have an undead army."));
							}
						}
						else {
							player.addChatMessage(IS_TARGETTYPE);
						}
					}
					else {
						player.addChatMessage(IS_TARGETTYPE);
					}
				}
				else if(string[0].matches("options")){
					player.addChatMessage(OPTIONS);
				}
				else if(string[0].matches("option")){
					if(string.length == 1){
						player.addChatMessage(OPTIONS);
					}
					else if(string.length == 2){
						if(string[1].matches("standarttarget")){
							player.addChatMessage(new ChatComponentTranslation("use /necromancy option standarttarget defensive/guardian/helper/offensive/monsterkiller/passive."));
						}
					}
					else {
						if(string[1].matches("standarttarget")){
							if(TargetType.isTargetType(string[2])){
								WorldData.setString(player, string[2], "standartnecromancytarget");
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + string[2] + " is the new standart targettype of your undead army."));
							}
						}
					}
				}
			}
			else {
				sender.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + getCommandUsage(sender)));
			}
		}
		else {
			sender.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "Only players can use this command."));
		}
	}


	@Override
	public boolean isUsernameIndex(String[] string, int i) {
		return false;
	}

	@Override
	public String getName() {
		return "necromancy";
	}

	@Override
	public List getAliases() {
		return aliases;
	}


	@Override
	public boolean canCommandSenderUse(ICommandSender sender) {
		return sender instanceof EntityPlayer;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		return null;
	}

}
