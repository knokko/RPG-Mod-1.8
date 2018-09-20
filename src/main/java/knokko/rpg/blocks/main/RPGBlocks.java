package knokko.rpg.blocks.main;

import knokko.rpg.blocks.FirePortal;
import knokko.rpg.blocks.SimpleBlock;
import knokko.rpg.blocks.Table;
import knokko.rpg.blocks.TestBlock;
import knokko.rpg.blocks.VoidPortal;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RPGBlocks {
	
	public static final Table table = new Table();
	public static final VoidPortal voidportal = new VoidPortal();
	public static final FirePortal fireportal = new FirePortal();
	public static final SimpleBlock basalt = new SimpleBlock(Material.rock, "basalt", 4, 15, CreativeTabs.tabBlock, "pickaxe", 1);
	public static final SimpleBlock ruby_ore = new SimpleBlock(Material.rock, "rubyore", 5, 15, CreativeTabs.tabBlock, "pickaxe", 2);
	public static final SimpleBlock paved_obsidian = new SimpleBlock(Material.rock, "paved obsidian", 10, 2000, CreativeTabs.tabBlock, "pickaxe", 3);
	public static final SimpleBlock voidbrick = new SimpleBlock(Material.rock, "void brick", 30, 30, CreativeTabs.tabBlock, "pickaxe", 1);
	
	public static void registerBlocks(){
		GameRegistry.registerBlock(table, "table");
		GameRegistry.registerBlock(voidportal, "voidportal");
		GameRegistry.registerBlock(fireportal, "fireportal");
		GameRegistry.registerBlock(basalt, "basalt");
		GameRegistry.registerBlock(ruby_ore, "rubyore");
		GameRegistry.registerBlock(paved_obsidian, "pavedobsidian");
		GameRegistry.registerBlock(voidbrick, "voidbrick");
	}
}
