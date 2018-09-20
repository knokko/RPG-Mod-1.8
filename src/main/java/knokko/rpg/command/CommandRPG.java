package knokko.rpg.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import knokko.rpg.RPG;
import knokko.rpg.data.WorldData;
import knokko.rpg.items.main.RPGItems;
import knokko.util.BlockUtils;
import knokko.util.ExtraUtils;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;

public class CommandRPG implements ICommand{
	
	public static final ChatComponentTranslation useAge = new ChatComponentTranslation(EnumChatFormatting.RED + "You must use rpg "  + EnumChatFormatting.RED + "mana/class/help/race/xp/team/special/spell/options/stats");
	
	@Override
	public int compareTo(Object o) {
		return 0;
	}

	@Override
	public String getName() {
		return "rpg";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/rpg xp/mana/class/help/spell/race/options/special/team/stats";
	}

	@Override
	public List getAliases() {
		return new ArrayList();
	}

	@Override
	public void execute(ICommandSender sender, String[] string) {
		Random random = new Random();
		if(sender instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)sender;
			if(string.length > 0){
				if(string[0].matches("mana")){
					if(string.length == 1){
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.DARK_PURPLE + "Your mana is " + WorldData.getInteger(player, "mana") + "/" + WorldData.getInteger(player, "maxmana")));
					}
					else if(string.length == 3){
						if(player.canUseCommand(0, "effect")){
							if(string[1].matches("set")){
								try {
									int amount = Integer.decode(string[2]);
									WorldData.setInteger(player, amount, "mana");
									player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + "Your mana has been changed to " + WorldData.getInteger(player, "mana")));
								} catch(NumberFormatException n){
									player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + string[2] + " should be a number."));
								}
							}
							else if(string[1].matches("add")){
								try {
									int amount = Integer.decode(string[2]);
									WorldData.addToInteger(player, "mana", amount, WorldData.getInteger(player, "maxmana"));
									player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + "Your mana has been changed to " + WorldData.getInteger(player, "mana")));
								} catch(NumberFormatException n){
									player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + string[2] + " should be a number."));
								}
							}
							else if(string[1].matches("remove")){
								try {
									int amount = Integer.decode(string[2]);
									WorldData.removeFromInteger(player, "mana", amount, 0);
									player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + "Your mana has been changed to " + WorldData.getInteger(player, "mana")));
								} catch(NumberFormatException n){
									player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + string[2] + " should be a number."));
								}
							}
							else {
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You must use /rpg mana add/set/remove [amount]"));
							}
						}
						else {
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.DARK_RED + "You are not allowed to use this command."));
						}
					}
					else {
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You must use /rpg mana add/set/remove [amount]"));
					}
				}
				else if(string[0].matches("class")){
					if(string.length > 1){
						if(string[1].matches(WorldData.getString(player, "class", "hunter"))){
							player.addChatMessage(new ChatComponentTranslation("You are already " + string[1]));
						}
						else if(RPG.isBasicClass(string[1])){
							WorldData.setString(player, string[1], "class");
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + "Your new class is " + string[1]));
						}
						else if(RPG.isSpecialClass(string[1])){
							if(RPG.canPlayerChooseSpecialClass(player, string[1])){
								WorldData.setString(player, string[1], "class");
								if(WorldData.getXP(player, string[1]) < 10000){
									WorldData.setXP(player, 10000);
								}
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + string[1] + " is your new class."));
							}
						}
						else {
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + string[1] + " is not a known class. Use /rpg classes for a list of classes."));
						}
					}
					else {
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.AQUA + "Your class is " + WorldData.getString(player, "class", "hunter")));
					}
				}
				else if(string[0].matches("xp")){
					if(string.length == 3){
						if(player.canUseCommand(0, "effect")){
							if(string[1].matches("add")){
								try {
									int amount = Integer.decode(string[2]);
									WorldData.addXP(player, amount);
									player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + "Your xp has been changed to " + WorldData.getXP(player)));
								} catch(NumberFormatException n){
									player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + string[2] + " should be a number."));
								}
							}
							if(string[1].matches("remove")){
								try {
									int amount = Integer.decode(string[2]);
									WorldData.addXP(player, amount * -1);
									player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + "Your xp has been changed to " + WorldData.getXP(player)));
								} catch(NumberFormatException n){
									player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + string[2] + " should be a number."));
								}
							}
							if(string[1].matches("set")){
								try {
									int amount = Integer.decode(string[2]);
									WorldData.setXP(player, amount);
									player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + "Your xp has been changed to " + WorldData.getXP(player)));
								} catch(NumberFormatException n){
									player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + string[2] + " should be a number."));
								}
							}
						}
						else {
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.DARK_RED + "You are not allowed to use this command."));
						}
					}
					else if(string.length == 4){
						if(player.canUseCommand(0, "effect")){
							if(string[3].matches("race")){
								if(string[1].matches("add")){
									try {
										int amount = Integer.decode(string[2]);
										WorldData.addRaceXp(player, amount);
										player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + "Your race xp has been changed to " + WorldData.getRaceXP(player)));
									} catch(NumberFormatException n){
										player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + string[2] + " should be a number."));
									}
								}
								if(string[1].matches("remove")){
									try {
										int amount = Integer.decode(string[2]);
										WorldData.addRaceXp(player, amount * -1);
										player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + "Your race xp has been changed to " + WorldData.getRaceXP(player)));
									} catch(NumberFormatException n){
										player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + string[2] + " should be a number."));
									}
								}
								if(string[1].matches("set")){
									try {
										int amount = Integer.decode(string[2]);
										WorldData.setRaceXP(player, amount);
										player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + "Your race xp has been changed to " + WorldData.getRaceXP(player)));
									} catch(NumberFormatException n){
										player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + string[2] + " should be a number."));
									}
								}
							}
							else {
								player.addChatMessage(new ChatComponentTranslation("You must use /rpg xp add/set/remove (race)"));
							}
						}
						else {
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.DARK_RED + "You are not allowed to use this command."));
						}
					}
					else if(string.length == 1){
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.DARK_GREEN + "Your class xp is " + WorldData.getXP(player) + " and your race xp is " +EnumChatFormatting.DARK_GREEN + WorldData.getRaceXP(player)));
					}
					else {
						player.addChatMessage(new ChatComponentTranslation("You must use /rpg xp add/remove/set [amount]"));
					}
				}
				else if(string[0].matches("race")){
					if(string.length == 1){
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.LIGHT_PURPLE + "Your race is " + WorldData.getString(player, "race", "human")));
					}
					else if(RPG.isKnownRace(string[1])){
						WorldData.setString(player, string[1], "race");
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + string[1] + " is your new race."));
					}
					else {
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You must use /rpg race [racename]. Use /rpg races for a list of races."));
					}
				}
				else if(string[0].matches("spell")){
					if(string.length > 1){
						ItemStack stack = player.getCurrentEquippedItem();
						if(stack != null){
							Item item = stack.getItem();
							if(item == RPGItems.woodWand){
								if(RPG.isKnownSpell(string[1])){
									if(RPG.isKnownSpell(string[1], player)){
										stack.setStackDisplayName(string[1]);
									}
									else {
										player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "That spell does not belong to your class."));
									}
								}
								else {
									player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + string[1] + " is not a know spell. Use /rpg spells for a list of " + EnumChatFormatting.RED + "spells."));
								}
							}
							else {
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You must hold a wand while using this command."));
							}
						}
						else {
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You must hold a wand while using this command."));
						}
					}
					else {
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You must use /rpg spell [spellname]"));
					}
				}
				else if(string[0].matches("spells")){
					int spells = RPG.amountSpells(player);
					int times = 0;
					while(times < spells){
						++times;
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.BLUE + RPG.getSpell(times, player)));
					}
				}
				else if(string[0].matches("classes")){
					int times = 0;
					while(times < RPG.getBaseClasses().size()){
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "" + RPG.getBaseClass(times)));
						++times;
					}
				}
				else if(string[0].matches("weapon")){
					ItemStack weapon = RPG.getWeapon(player);
					if(weapon != null){
						player.inventory.addItemStackToInventory(weapon);
					}
					else {
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "Your class doesn't have a weapon."));
					}
				}
				else if(string[0].matches("stats")){
					player.addChatMessage(new ChatComponentTranslation( EnumChatFormatting.GREEN + "health: " + RPG.getPlayerHealth(player) + EnumChatFormatting.RED + " strengt: " + RPG.getPlayerStrengt(player) + EnumChatFormatting.BLUE + " spirit: " + RPG.getPlayerSpirit(player)));
				}
				else if(string[0].matches("special")){
					if(string.length == 2){
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "Your special is " + RPG.getSpecial(player)));
					}
					else if(string.length == 1){
						String special = RPG.getSpecial(player);
						if(!WorldData.isIntegerPositive(player, "special cooldown")){
							if(special.matches("rage")){
								player.addPotionEffect(new PotionEffect(5, 300));
								player.addPotionEffect(new PotionEffect(1, 300));
								WorldData.setInteger(player, 2400, "special cooldown");
							}
							else if(special.matches("white gift")){
								player.heal((float) (RPG.getPlayerSpirit(player) * 10));
								WorldData.addToInteger(player, "mana", ExtraUtils.fromDouble(RPG.getPlayerSpirit(player) * 1000), WorldData.getInteger(player, "maxmana"));
								WorldData.setInteger(player, 2400, "special cooldown");
							}
							else if(special.matches("teleport")){
								double x = player.posX - 200 + (random.nextDouble() * 400);
								double z = player.posZ - 200 + (random.nextDouble() * 400);
								int y = 0;
								int times = 0;
								while(times < 100 && (y < 1 || player.worldObj.isAnyLiquid(AxisAlignedBB.fromBounds(x-1, y-1, z-1, x+1, y+1, z+1)))){
									y = BlockUtils.getHighestBlock(player.worldObj, (int)x, (int) z);
									++times;
								}
								if(y > 0 && !player.worldObj.isAnyLiquid(AxisAlignedBB.fromBounds(x-1, y-1, z-1, x+1, y+1, z+1))){
									player.setPositionAndUpdate(x, y + 1, z);
									WorldData.setInteger(player, 600, "special cooldown");
								}
							}
							else if(special.matches("iron bash")){
								List entities = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.fromBounds(player.posX - 15, player.posY - 15, player.posZ - 15, player.posX + 15, player.posY + 15, player.posZ + 15));
								int times = 0;
								while(times < entities.size()){
									EntityLivingBase entity = (EntityLivingBase) entities.get(times);
									++times;
									if(entity != player && entity.onGround){
										float impact = (float) (0.1 * (250 - player.getDistanceSqToEntity(entity)));
										System.out.println("distance to entity = " + player.getDistanceSqToEntity(entity));
										System.out.println("impact = " + impact);
										entity.playSound("mob.irongolem.hit", 1, 1);
										entity.attackEntityFrom(DamageSource.causePlayerDamage(player), impact);
										entity.motionY = impact * 0.1;
									}
									player.playSound("mob.irongolem.hit", 1, 1);
								}
								WorldData.setInteger(player, 200, "special cooldown");
							}
							else if(special.matches("agility boost")){
								player.addPotionEffect(new PotionEffect(1, 600));
								player.addPotionEffect(new PotionEffect(8, 600));
								WorldData.setInteger(player, 2400, "special cooldown");
							}
							else if(special.matches("zoom")){
								if(WorldData.getBooleanOption(player, "zoom", false)){
									WorldData.setBooleanOption(player, "zoom", false);
								}
								else {
									WorldData.setBooleanOption(player, "zoom", true);
								}
							}
						}
						else {
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "Your special cooldown is " + WorldData.getInteger(player, "special cooldown") * 0.05));
						}
					}
					else {
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You must use /rpg special (?)"));
					}
				}
				else if(string[0].matches("races")){
					int times = 0;
					while(times < RPG.getRaces().size()){
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.DARK_GREEN + "" + RPG.getRace(times)));
						++times;
					}
				}
				else if(string[0].matches("options")){
					if(string.length == 1){
						player.addChatMessage(new ChatComponentTranslation("manaMessages / hitMessages / hurtMessages"));
					}
					else if(string.length == 2){
						if(RPG.isKnownOption(string[1])){
							player.addChatMessage(new ChatComponentTranslation(string[1] + " = " + WorldData.getBooleanOption(player, string[1], true)));
						}
						else {
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "Unknown option, use /rpg options for a list of options."));
						}
					}
					else if(string.length >= 3){
						if(RPG.isKnownOption(string[1])){
							if(string[2].matches("true")){
								WorldData.setBooleanOption(player, string[1], true);
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.DARK_AQUA + "Option " + string[1] + " is set to true."));
							}
							else if(string[2].matches("false")){
								WorldData.setBooleanOption(player, string[1], false);
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.DARK_AQUA + "Option " + string[1] + " is set to false."));
							}
							else {
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You must use /rpg options [option] true/false"));
							}
						}
						else {
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "Unknown option, use /rpg options for a list of options."));
						}
					}
				}
				else if(string[0].matches("team")){
					String team = WorldData.getString(player, "team", "");
					if(string.length == 1){
						if(team.isEmpty()){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You are not in a team."));
						}
						else {
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.BLUE + "Your team is " + team));
						}
					}
					else {
						if(string[1].matches("help")){
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.BLUE + "Use /rpg team create/invite/promote/kick/options/leave"));
						}
						else if(string[1].matches("create")){
							if(string.length == 2){
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "Use /rpg team create [name]"));
							}
							else if(team.isEmpty()){
								if(WorldData.createTeam(string[2], player)){
									player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + "You have succesfully created team " + string[2]));
								}
								else {
									player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "That team allready exists, you must choose another name."));
								}
							}
							else {
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You must leave your team before you can create a new one."));
							}
						}
						else if(string[1].matches("invite")){
							if(string.length == 2){
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "Use /rpg team invite [player]"));
							}
							else {
								if(WorldData.isLeader(team, player)){
									EntityPlayer player2 = sender.getEntityWorld().getPlayerEntityByName(string[2]);
									if(player2 != null){
										if(!WorldData.getBooleanOption(player2, WorldData.invite + team, false)){
											WorldData.setBooleanOption(player2, WorldData.invite + team, true);
											player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + "You have invited " + player2.getName() + " to your team."));
										}
										else {
											player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "That player is allready invited for your team."));
										}
									}
									else {
										player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "That player cannot be found."));
									}
								}
								else {
									player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You must be a leader to invite other players."));
								}
							}
						}
						else if(string[1].matches("kick")){
							if(string.length == 2){
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "Use /rpg team kick [player]"));
							}
							else if(WorldData.isLeader(team, player)){
								EntityPlayer player2 = player.worldObj.getPlayerEntityByName(string[2]);
								if(player2 != null){
									if(WorldData.isOnSameTeam(player, player2)){
										WorldData.setString(player2, "", "team");
										if(WorldData.isLeader(team, player2)){
											WorldData.get(player.worldObj).teams.getCompoundTag(team).getCompoundTag("leaders").removeTag(player2.getUniqueID().toString());
											WorldData.get(player.worldObj).markDirty();
										}
										player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You have kicked " + player2.getName() + " from your team."));
										player2.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + player.getName() + " has kicked you from you team."));
									}
									else {
										player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You can't kick that player because he is not in your team."));
									}
								}
								else {
									player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "That player cannot be found."));
								}
							}
							else {
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You must be a leader to do this."));
							}
						}
						else if(string[1].matches("promote")){
							if(string.length == 2){
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "Use /rpg team promote [player]"));
							}
							else {
								if(WorldData.isLeader(team, player)){
									EntityPlayer player2 = player.worldObj.getPlayerEntityByName(string[2]);
									if(player2 != null){
										if(!WorldData.isLeader(team, player2)){
											player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + "You have succesfully promoted " + player2.getName() + " to leader."));
											player2.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + player.getName() + " has promoted you to a leader."));
										}
										else {
											player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "That player is allready a leader."));
										}
									}
									else {
										player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "That player cannot be found."));
									}
								}
								else {
									player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You must be a leader to do this."));
								}
							}
						}
						else if(string[1].matches("leave")){
							if(!team.isEmpty()){
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.YELLOW + "You have left team " + team));
								WorldData.setString(player, "", "team");
							}
							else {
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You are not in a team, so you can't leave it."));
							}
						}
						else if(string[1].matches("options")){
							player.addChatMessage(new ChatComponentTranslation("I haven't made options yet."));
						}
						else if(string[1].matches("join")){
							if(string.length == 2){
								player.addChatMessage(new ChatComponentTranslation("use /rpg team join [team]"));
							}
							else {
								if(team.isEmpty()){
									if(WorldData.getBooleanOption(player, WorldData.invite + string[2], false)){
										player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GREEN + "You have joined team " + string[2]));
										WorldData.setString(player, string[2], "team");
									}
								}
								else {
									player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You must leave your current team before you can join another team."));
								}
							}
						}
						else {
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "Use /rpg team create/invite/promote/kick/options/leave"));
						}
					}
				}
				else if(string[0].matches("chat")){
					if(string.length == 1){
						player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.BLUE + "Your chat reach is " + WorldData.getString(player, "chat", "public")));
					}
					else {
						if(!WorldData.getString(player, "chat", "public").matches(string[1])){
							if(WorldData.isChatType(string[1])){
								WorldData.setString(player, string[1], "chat");
								player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.BLUE + "Your new chat reach is " + string[1]));
							}
						}
						else {
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + string[1] +  " is allready your chat reach."));
						}
					}
				}
				else {
					player.addChatMessage(useAge);
				}
			}
			else {
				player.addChatMessage(useAge);
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
