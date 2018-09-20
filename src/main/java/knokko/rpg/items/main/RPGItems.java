package knokko.rpg.items.main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.fml.common.registry.GameRegistry;
import knokko.rpg.items.SimpleItem;
import knokko.rpg.items.SkullRod;
import knokko.rpg.items.TestItem;
import knokko.rpg.items.tools.RubyAxe;
import knokko.rpg.items.tools.RubyPickaxe;
import knokko.rpg.items.tools.RubyShovel;
import knokko.rpg.items.weapons.BananaSword;
import knokko.rpg.items.weapons.ItemWand;
import knokko.rpg.items.weapons.RubySword;

public class RPGItems {
	
	public static TestItem testitem;
	public static ItemWand woodWand;
	public static BananaSword bananaSword;
	public static SkullRod skullRod;
	public static SimpleItem ruby;
	public static RubySword ruby_sword;
	public static RubyAxe ruby_axe;
	public static RubyPickaxe ruby_pickaxe;
	public static RubyShovel ruby_shovel;
	
	public static void load(){
		testitem = new TestItem();
		woodWand = new ItemWand(0.5, 1, "woodwand");
		bananaSword = new BananaSword(4, 0, false);
		skullRod = new SkullRod();
		ruby = new SimpleItem("ruby", CreativeTabs.tabMaterials);
		ruby_sword = new RubySword();
		ruby_axe = new RubyAxe();
		ruby_pickaxe = new RubyPickaxe();
		ruby_shovel = new RubyShovel();
	}

	public static void registerItems(){
		GameRegistry.registerItem(testitem, "testitem");
		GameRegistry.registerItem(woodWand, "woodwand");
		GameRegistry.registerItem(bananaSword, "bananasword");
		GameRegistry.registerItem(skullRod, "skullrod");
		GameRegistry.registerItem(ruby, "ruby");
		GameRegistry.registerItem(ruby_sword, "rubysword");
		GameRegistry.registerItem(ruby_axe, "rubyaxe");
		GameRegistry.registerItem(ruby_pickaxe, "rubypickaxe");
		GameRegistry.registerItem(ruby_shovel, "rubyshovel");
	}
}
