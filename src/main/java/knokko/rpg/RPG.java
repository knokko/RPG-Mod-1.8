package knokko.rpg;

import java.util.ArrayList;
import java.util.List;

import knokko.rpg.data.WorldData;
import knokko.rpg.items.main.RPGItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public final class RPG {
	
	public static final IChatComponent noSpecialClass = new ChatComponentTranslation(EnumChatFormatting.RED + "that is not a special class of your class.");
	
	public static final RPGClass WARRIOR = new RPGClass(null, "warrior", 1.25, 0.25, 0.5, 0.7, 1.5, 1, 0.75);
	public static final RPGClass MAGE = new RPGClass(null, "mage", 0.5, 2, 0.5, 1, 0.5, 1, 2);
	public static final RPGClass ARCHER = new RPGClass(null, "archer", 0.5, 0.25, 2, 0.9, 0.5, 1, 0.75);
	public static final RPGClass HUNTER = new RPGClass(null, "hunter", 1, 1, 1, 1, 1, 1, 1);
	public static final RPGClass BRAWLER = new RPGClass(null, "brawler", 1, 0.25, 0.5, 1, 1, 3, 0.75);
	
	public static final RPGClass TANK = new RPGClass(WARRIOR, "tank", 1, 0.25, 0.5, 0.6, 3, 1, 1.5);
	public static final RPGClass BERSERKER = new RPGClass(WARRIOR, "berserker", 2, 0.25, 0.5, 1.5, 0.6, 1, 0.5);
	public static final RPGClass MAGICWARRIOR = new RPGClass(WARRIOR, "magicwarrior", 1, 1, 0.5, 0.8, 1, 1, 2);
	public static final RPGClass PALADIN = new RPGClass(WARRIOR, "paladin", 1, 1.5, 0.5, 0.8, 2, 1, 2);
	
	public static final RPGClass SHOOTER = new RPGClass(ARCHER, "shooter", 0.5, 0.25, 3, 1 , 0.5, 1, 0.5);
	public static final RPGClass MAGICARCHER = new RPGClass(ARCHER, "magicarcher", 0.5, 1, 1.5, 0.9, 0.5, 1, 2);
	
	public static final RPGClass HEALER = new RPGClass(MAGE, "healer", 0.5, 2, 0.5, 1, 0.5, 1, 2.5);
	public static final RPGClass GALACTIC = new RPGClass(MAGE, "galactic", 0.5, 3, 0.5, 1, 0.5, 1, 2);
	public static final RPGClass NECROMANCER = new RPGClass(MAGE, "necromancer", 0.5, 2, 0.5, 1, 0.5, 1, 3);
	
	public static final Race HUMAN = new Race("human", "white gift", 1, 1, 1, 1, 0, 1);
	public static final Race ORC = new Race("orc", "rage", 3, 0.25, 2.5, 0.6, 0, 0.25);
	public static final Race ENDERMAN = new Race("enderman", "teleport", 2, 0.5, 2, 0.75, 0, 0.75);
	public static final Race GOLEM = new Race("golem", "iron bash", 1, 0.25, 5, 0.5, 9, 0.25);
	public static final Race BEAR = new Race("bear", "agility boost", 1.5, 0.2, 2, 2, 5, 0.2);
	public static final Race MONKEY = new Race("monkey", "agility boost", 1.2, 0.75, 1.2, 1.7, 0, 0.75);
	public static final Race ELF = new Race("elf", "white gift", 0.5, 2, 0.5, 1.3, 0, 2);
	public static final Race EYEMAN = new Race("eyeman", "zoom", 0.4, 1, 0.75, 1, 0, 3);
	
	private static final ArrayList<RPGClass> BASE_CLASSES = new ArrayList<RPGClass>();
	private static final ArrayList<RPGClass> SPECIAL_CLASSES = new ArrayList<RPGClass>();
	private static final ArrayList<RPGClass> CLASSES = new ArrayList<RPGClass>();
	
	private static final ArrayList<Race> RACES = new ArrayList<Race>();
	
	static  {
		BASE_CLASSES.add(WARRIOR);
		BASE_CLASSES.add(MAGE);
		BASE_CLASSES.add(ARCHER);
		BASE_CLASSES.add(HUNTER);
		BASE_CLASSES.add(BRAWLER);
		SPECIAL_CLASSES.add(TANK);
		SPECIAL_CLASSES.add(BERSERKER);
		SPECIAL_CLASSES.add(MAGICWARRIOR);
		SPECIAL_CLASSES.add(PALADIN);
		SPECIAL_CLASSES.add(SHOOTER);
		SPECIAL_CLASSES.add(MAGICARCHER);
		SPECIAL_CLASSES.add(HEALER);
		SPECIAL_CLASSES.add(GALACTIC);
		SPECIAL_CLASSES.add(NECROMANCER);
		CLASSES.addAll(BASE_CLASSES);
		CLASSES.addAll(SPECIAL_CLASSES);
		RACES.add(HUMAN);
		RACES.add(ORC);
		RACES.add(ENDERMAN);
		RACES.add(GOLEM);
		RACES.add(BEAR);
		RACES.add(MONKEY);
		RACES.add(ELF);
		RACES.add(EYEMAN);
	}
	
	public static final RPGClass classFromString(String rpgClass){
		int i = 0;
		while(i < CLASSES.size()){
			if(CLASSES.get(i).name.equals(rpgClass))
				return CLASSES.get(i);
			++i;
		}
		return null;
	}
	
	public static final Race raceFromString(String race){
		int t = 0;
		while(t < RACES.size()){
			if(RACES.get(t).equals(race))
				return RACES.get(t);
			++t;
		}
		return null;
	}
	
	public static final boolean isBasicClass(String string){
		int t = 0;
		while(t < BASE_CLASSES.size()){
			if(BASE_CLASSES.get(t).equals(string))
				return true;
			++t;
		}
		return false;
	}
	
	public static boolean isSpecialClass(String string){
		int t = 0;
		while(t < SPECIAL_CLASSES.size()){
			if(SPECIAL_CLASSES.get(t).equals(string))
				return true;
			++t;
		}
		return false;
	}
	
	public static boolean canPlayerChooseSpecialClass(EntityPlayer player, String string){
		RPGClass rpgClass = classFromString(string);
		if(rpgClass == null)
			return false;
		if(rpgClass.baseClass == WARRIOR){
			if(WorldData.getXP(player, "warrior") >= 10000)
				return true;
			else {
				player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You must have 10000 xp for warrior to become this class."));
				return false;
			}
		}
		else if(rpgClass.baseClass == ARCHER){
			if(WorldData.getXP(player, "archer") >= 10000)
				return true;
			else {
				player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You must have 10000 xp for archer to become this class."));
				return false;
			}
		}
		else if(rpgClass.baseClass == MAGE){
			if(WorldData.getXP(player, "mage") >= 10000)
				return true;
			else {
				player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.RED + "You must have 10000 xp for mage to become this class."));
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public static double getBasePower(EntityPlayer player){
		RPGClass Class = classFromString(WorldData.getString(player, "class", "hunter"));
		String race = WorldData.getString(player, "race", "human");
		double power = Class.basePower;
		if(race.matches("golem"))
			power += 9;
		if(race.matches("bear"))
			power += 5;
		return power;
	}
	
	public static boolean isKnownRace(String string){
		int t = 0;
		while(t < RACES.size()){
			if(RACES.get(t).equals(string))
				return true;
			++t;
		}
		return false;
	}
	
	public static boolean isKnownSpell(String spell){
		if(spell.matches("ice"))
			return true;
		else if(spell.matches("summonarrow"))
			return true;
		else if(spell.matches("poisonblade"))
			return true;
		else if(spell.matches("explosive"))
			return true;
		else if(spell.matches("lightning"))
			return true;
		else if(spell.matches("windpunch"))
			return true;
		else if(spell.matches("firepunch"))
			return true;
		else if(spell.matches("icepunch"))
			return true;
		else if(spell.matches("lightningarrow"))
			return true;
		else if(spell.matches("poisonarrow"))
			return true;
		else if(spell.matches("cursedarrow"))
			return true;
		else if(spell.matches("powerattack"))
			return true;
		else if(spell.matches("dog"))
			return true;
		else if(spell.matches("fire"))
			return true;
		else if(spell.matches("weakregen"))
			return true;
		else if(spell.matches("darkpulse"))
			return true;
		else if(spell.matches("shield"))
			return true;
		else if(spell.matches("heavyshield"))
			return true;
		else if(spell.matches("gigabarrier"))
			return true;
		else if(spell.matches("berserk"))
			return true;
		else if(spell.matches("fireslash"))
			return true;
		else if(spell.matches("electricslash"))
			return true;
		else if(spell.matches("blooddrain"))
			return true;
		else if(spell.matches("darkslash"))
			return true;
		else if(spell.matches("heal"))
			return true;
		else if(spell.matches("spiritslash"))
			return true;
		else if(spell.matches("smite"))
			return true;
		else if(spell.matches("powershot"))
			return true;
		else if(spell.matches("spark"))
			return true;
		else if(spell.matches("teamheal"))
			return true;
		else if(spell.matches("helpheal"))
			return true;
		else if(spell.matches("regen"))
			return true;
		else if(spell.matches("teamregen"))
			return true;
		else if(spell.matches("helpregen"))
			return true;
		else if(spell.matches("megabarrier"))
			return true;
		else if(spell.matches("starrain"))
			return true;
		else if(spell.matches("meteor"))
			return true;
		else if(spell.matches("fly"))
			return true;
		else if(spell.matches("thunder"))
			return true;
		else if(spell.matches("dreadlord"))
			return true;
		else if(spell.matches("chow"))
			return true;
		else if(spell.matches("soar"))
			return true;
		return false;
	}
	
	public static boolean isKnownSpell(String spell, EntityPlayer player){
		String Class = WorldData.getString(player, "class", "hunter");
		if(Class.matches("mage"))
			return spell.matches("ice") || spell.matches("explosive") || spell.matches("lightning") || spell.matches("fire") || spell.matches("weakregen") || spell.matches("darkpulse") || spell.matches("spark");
		else if(Class.matches("archer"))
			return spell.matches("summonarrow") || spell.matches("powershot") || spell.matches("summonarrow");
		else if(Class.matches("hunter"))
			return spell.matches("poisonblade") || spell.matches("dog");
		else if(Class.matches("warrior"))
			return spell.matches("shield") || spell.matches("powerattack");
		else if(Class.matches("brawler"))
			return spell.matches("windpunch") || spell.matches("firepunch") || spell.matches("icepunch");
		else if(Class.matches("tank"))
			return spell.matches("shield") || spell.matches("heavyshield") || spell.matches("megabarrier") || spell.matches("gigabarrier");
		else if(Class.matches("berserker"))
			return spell.matches("berserk") || spell.matches("powerattack");
		else if(Class.matches("magicwarrior"))
			return spell.matches("powerattack") || spell.matches("fireslash") || spell.matches("electricslash") || spell.matches("blooddrain") || spell.matches("darkslash") || spell.matches("spiritslash");
		else if(Class.matches("paladin"))
			return spell.matches("heal") || spell.matches("spiritslash") || spell.matches("smite");
		else if(Class.matches("shooter"))
			return spell.matches("powershot") || spell.matches("summonarrow");
		else if(Class.matches("magicarcher"))
			return spell.matches("summonarrow") || spell.matches("cursedarrow") || spell.matches("lightningarrow") || spell.matches("poisonarrow") || spell.matches("powershot");
		else if(Class.matches("healer"))
			return spell.matches("weakregen") || spell.matches("heal") || spell.matches("ice") || spell.matches("fire") || spell.matches("teamheal") || spell.matches("helpheal") || spell.matches("regen") || spell.matches("teamregen") || spell.matches("helpregen");
		else if(Class.matches("galactic"))
			return spell.matches("spark") || spell.matches("fire") || spell.matches("lightning") || spell.matches("explosive") || spell.matches("meteor") || spell.matches("starrain") || spell.matches("fly") || spell.matches("thunder");
		else if(Class.matches("necromancer"))
			return spell.matches("darkpulse") || spell.matches("chow") || spell.matches("dreadlord") || spell.matches("soar");
		else
			return false;
	}
	
	public static String getSpell(int rank, EntityPlayer player){
		String Class = WorldData.getString(player, "class", "hunter");
		if(Class.matches("mage")){
			if(rank <= 1)
				return "ice";
			else if(rank == 2)
				return "explosive";
			else if(rank == 3)
				return "lightning";
			else if(rank == 4)
				return "fire";
			else if(rank == 5)
				return "weakregen";
			else if(rank == 6)
				return "darkpulse";
			else if(rank == 7)
				return "spark";
		}
		else if(Class.matches("archer")){
			if(rank <= 1)
				return "summonarrow";
			else if(rank == 2)
				return "poisonarrow";
			else if(rank == 3)
				return "powershot";
			else if(rank == 4)
				return "cursedarrow";
		}
		else if(Class.matches("hunter")){
			if(rank <= 1)
				return "poisonblade";
			else if(rank == 2)
				return "dog";
		}
		else if(Class.matches("warrior")){
			if(rank <= 1)
				return "lightshield";
			else if(rank == 2)
				return "powerattack";
		}
		else if(Class.matches("brawler")){
			if(rank <= 1)
				return "icepunch";
			else if(rank == 2)
				return "firepunch";
			else if(rank == 3)
				return "windpunch";
		}
		else if(Class.matches("tank")){
			if(rank == 1)
				return "lightshield";
			else if(rank == 2)
				return "shield";
			else if(rank == 3)
				return "heavyshield";
			else if(rank == 4)
				return "megabarrier";
			else if(rank == 5)
				return "gigabarrier";
		}
		else if(Class.matches("berserker")){
			if(rank == 1)
				return "powerattack";
			else if(rank == 2)
				return "berserk";
		}
		else if(Class.matches("magicwarrior")){
			if(rank == 1)
				return "powerattack";
			else if(rank == 2)
				return "fireslash";
			else if(rank == 3)
				return "electricslash";
			else if(rank == 4)
				return "blooddrain";
			else if(rank == 5)
				return "darkslash";
			else if(rank == 6)
				return "spiritslash";
		}
		else if(Class.matches("paladin")){
			if(rank == 1)
				return "heal";
			else if(rank == 2)
				return "spiritslash";
			else if(rank == 3)
				return "smite";
		}
		else if(Class.matches("shooter")){
			if(rank == 1)
				return "powershot";
			else if(rank == 2)
				return "summonarrow";
		}
		else if(Class.matches("magicarcher")){
			if(rank == 1)
				return "summonarrow";
			else if(rank == 2)
				return "poisonarrow";
			else if(rank == 3)
				return "lightningarrow";
			else if(rank == 4)
				return "powershot";
			else if(rank == 5)
				return "cursedarrow";
		}
		else if(Class.matches("healer")){
			if(rank == 1)
				return "heal";
			else if(rank == 2)
				return "weakregen";
			else if(rank == 3)
				return "ice";
			else if(rank == 4)
				return "fire";
			else if(rank == 5)
				return "teamheal";
			else if(rank == 6)
				return "helpheal";
			else if(rank == 7)
				return "regen";
			else if(rank == 8)
				return "teamregen";
			else if(rank == 9)
				return "helpregen";
		}
		else if(Class.matches("galactic")){
			if(rank == 1)
				return "fire";
			else if(rank == 2)
				return "spark";
			else if(rank == 3)
				return "lightning";
			else if(rank == 4)
				return "explosive";
			else if(rank == 5)
				return "meteor";
			else if(rank == 6)
				return "starrain";
			else if(rank == 7)
				return "fly";
			else if(rank == 8)
				return "thunder";
		}
		else if(Class.matches("necromancer")){
			if(rank == 1)
				return "darkpulse";
			else if(rank == 2)
				return "chow";
			else if(rank == 3)
				return "dreadlord";
			else if(rank == 4)
				return "soar";
		}
		return null;
	}
	
	public static int amountSpells(EntityPlayer player){
		String Class = WorldData.getString(player, "class", "hunter");
		if(Class.matches("warrior"))
			return 2;
		else if(Class.matches("mage"))
			return 7;
		else if(Class.matches("hunter"))
			return 2;
		else if(Class.matches("archer"))
			return 3;
		else if(Class.matches("brawler"))
			return 3;
		else if(Class.matches("tank"))
			return 5;
		else if(Class.matches("berserker"))
			return 2;
		else if(Class.matches("magicwarrior"))
			return 6;
		else if(Class.matches("paladin"))
			return 3;
		else if(Class.matches("shooter"))
			return 2;
		else if(Class.matches("magicarcher"))
			return 5;
		else if(Class.matches("healer"))
			return 9;
		else if(Class.matches("galactic"))
			return 8;
		else if(Class.matches("necromancer"))
			return 4;
		else
			return 0;
	}
	
	public static boolean isKnownOption(String option){
		if(option.matches("hurtMessages")){
			return true;
		}
		else if(option.matches("manaMessages")){
			return true;
		}
		else if(option.matches("hitMessages")){
			return true;
		}
		else if(option.matches("killMessages")){
			return true;
		}
		else if(option.matches("deathMessages")){
			return true;
		}
		else {
			return false;
		}
	}
	
	public static ArrayList<Race> getRaces(){
		ArrayList<Race> races = new ArrayList<Race>();
		races.addAll(RACES);
		return races;
	}
	
	public static Race getRace(int rank){
		return getRaces().get(rank);
	}
	
	public static ArrayList<RPGClass> getBaseClasses(){
		ArrayList<RPGClass> classes = new ArrayList<RPGClass>();
		classes.addAll(BASE_CLASSES);
		return classes;
	}
	
	public static RPGClass getBaseClass(int rank){
		return BASE_CLASSES.get(rank);
	}
	
	private static double getClassStrengt(String Class){
		return classFromString(Class).strength;
	}
	
	private static double getClassStrengt(EntityPlayer player){
		return getClassStrengt(WorldData.getString(player, "class", "hunter"));
	}
	
	private static double getRaceStrengt(String race){
		return raceFromString(race).strength;
	}
	
	public static ItemStack getWeapon(EntityPlayer player){
		String Class = WorldData.getString(player, "class", "hunter");
		if(Class.matches("warrior")){
			if(WorldData.getString(player, "race", "human").matches("monkey")){
				return new ItemStack(RPGItems.bananaSword);
			}
			else {
				return new ItemStack(Items.iron_axe);
			}
		}
		else if(Class.matches("berserker")){
			return new ItemStack(Items.diamond_sword);
		}
		else if(Class.matches("tank")){
			ItemStack sword = new ItemStack(Items.iron_sword);
			sword.addEnchantment(Enchantment.knockback, 1);
			return sword;
		}
		else if(Class.matches("magicwarrior")){
			return new ItemStack(Items.golden_sword);
		}
		else if(Class.matches("archer")){
			return new ItemStack(Items.bow);
		}
		else if(Class.matches("brawler")){
			return null;
		}
		else if(Class.matches("hunter")){
			return new ItemStack(Items.bow);
		}
		else if(Class.matches("mage")){
			return new ItemStack(RPGItems.woodWand);
		}
		else if(Class.matches("paladin")){
			ItemStack sword = new ItemStack(Items.iron_sword);
			sword.addEnchantment(Enchantment.smite, 5);
			return sword;
		}
		else if(Class.matches("shooter")){
			ItemStack bow = new ItemStack(Items.bow);
			bow.addEnchantment(Enchantment.power, 3);
			return bow;
		}
		else if(Class.matches("magicarcher")){
			ItemStack bow = new ItemStack(Items.bow);
			bow.addEnchantment(Enchantment.infinity, 1);
			return bow;
		}
		else if(Class.matches("healer")){
			return new ItemStack(RPGItems.woodWand);
		}
		else if(Class.matches("necromancer")){
			return new ItemStack(RPGItems.woodWand);
		}
		else {
			return null;
		}
	}
	
	private static double getRaceHealth(String race){
		return raceFromString(race).health;
	}
	
	private static double getRaceHealth(EntityPlayer player){
		return getRaceHealth(WorldData.getString(player, "race", "human"));
	}
	
	public static double getPlayerHealth(EntityPlayer player){
		double xp = WorldData.getRaceXP(player);
		double raceHealth = getRaceHealth(player);
		return raceHealth * (1 + (Math.sqrt(xp) * 0.0001));
	}
	
	private static double getRaceStrengt(EntityPlayer player){
		return getRaceStrengt(WorldData.getString(player, "race", "human"));
	}
	
	public static double getPlayerStrengt(EntityPlayer player){
		double raceStrengt = getRaceStrengt(player);
		double classStrengt = getClassStrengt(player);
		double xp = WorldData.getXP(player);
		double racexp = WorldData.getXP(player);
		return raceStrengt * (1 + (Math.sqrt(xp) * 0.0001) + (0.0001 * Math.sqrt(racexp))) * classStrengt;
	}
	
	private static double getRaceMana(String race){
		return raceFromString(race).mana;
	}
	
	private static double getClassMana(String Class){
		return classFromString(Class).mana;
	}
	
	private static double getClassMana(EntityPlayer player){
		return getClassMana(WorldData.getString(player, "class", "hunter"));
	}
	
	private static double getRaceMana(EntityPlayer player){
		return getRaceMana(WorldData.getString(player, "race", "human"));
	}
	
	public static double getPlayerMana(EntityPlayer player){
		double raceMana = getRaceMana(player);
		double classMana = getClassMana(player);
		double xp = WorldData.getXP(player);
		double racexp = WorldData.getRaceXP(player);
		return raceMana * (1 + (Math.sqrt(racexp) * 0.0001) + (Math.sqrt(xp) * 0.0001)) * classMana;
	}
	
	public static double getArcherPower(EntityPlayer player){
		double classPower = classFromString(WorldData.getString(player, "class", "huntter")).archerPower;
		double xp = 0;
		return classPower * (1 + (Math.sqrt(xp) * 0.0001));
	}
	
	private static double getRaceSpeed(EntityPlayer player){
		return raceFromString(WorldData.getString(player, "race", "human")).speed;
	}
	
	private static double getClassSpeed(EntityPlayer player){
		return classFromString(WorldData.getString(player, "class", "hunter")).speed;
	}
	
	public static double getPlayerSpeed(EntityPlayer player){
		return getClassSpeed(player) * getRaceSpeed(player);
	}
	
	private static double getRaceSpirit(EntityPlayer player){
		return raceFromString(WorldData.getString(player, "race", "human")).spirit;
	}
	
	private static double getClassSpirit(EntityPlayer player){
		return classFromString(WorldData.getString(player, "class", "hunter")).spirit;
	}
	
	public static double getPlayerSpirit(EntityPlayer player){
		double raceSpirit = getRaceSpirit(player);
		double classSpirit = getClassSpirit(player);
		double raceXp = WorldData.getRaceXP(player);
		double xp = WorldData.getXP(player);
		return raceSpirit * (1 + (Math.sqrt(raceXp) * 0.0001) + (Math.sqrt(xp) * 0.0001)) * classSpirit;
	}
	
	public static String getSpecial(EntityPlayer player){
		return raceFromString(WorldData.getString(player, "race", "human")).special;
	}
	
	private static double getClassDefense(Entity player){
		return classFromString(WorldData.getString(player, "class", "hunter")).defense;
	}
	
	public static double getPlayerDefense(EntityLivingBase entityLiving){
		int xp = WorldData.getXP(entityLiving);
		double defense = getClassDefense(entityLiving);
		return defense * (1 + (Math.sqrt(xp) * 0.0001));
	}
	
	public static class RPGClass {
		
		public final RPGClass baseClass;
		public final String name;
		
		public final double strength;
		public final double spirit;
		public final double archerPower;
		public final double speed;
		public final double defense;
		
		public final double basePower;
		public final double mana;
		
		private RPGClass(RPGClass baseClass, String name, double strength, double spirit, double archerPower, double speed, double defense, double basePower, double mana){
			this.baseClass = baseClass;
			this.name = name;
			this.strength = strength;
			this.spirit = spirit;
			this.speed = speed;
			this.archerPower = archerPower;
			this.defense = defense;
			this.basePower = basePower;
			this.mana = mana;
		}
		
		@Override
		public boolean equals(Object other){
			if(other == this)
				return true;
			if(other instanceof String)
				return other.equals(name);
			return false;
		}
		
		@Override
		public String toString(){
			return name;
		}
	}
	
	public static class Race {
		
		public final String name;
		public final String special;
		
		public final double strength;
		public final double spirit;
		public final double health;
		
		public final double speed;
		public final double extraDamage;
		public final double mana;
		
		public Race(String name, String special, double strength, double spirit, double health, double speed, double extraDamage, double mana){
			this.name = name;
			this.special = special;
			this.strength = strength;
			this.spirit = spirit;
			this.health = health;
			this.speed = speed;
			this.extraDamage = extraDamage;
			this.mana = mana;
		}
		
		@Override
		public boolean equals(Object other){
			if(other == this)
				return true;
			if(other instanceof String)
				return other.equals(name);
			return false;
		}
		
		@Override
		public String toString(){
			return name;
		}
	}
	
}
