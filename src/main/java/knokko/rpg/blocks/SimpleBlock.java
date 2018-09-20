package knokko.rpg.blocks;

import knokko.rpg.main.s;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;

public class SimpleBlock extends Block{

	protected SimpleBlock(Material material) {
		super(material);
	}
	
	public SimpleBlock(Material material, String name, float hardness, float resistance, CreativeTabs tab){
		super(material);
		setCreativeTab(tab);
		setHardness(hardness);
		setResistance(resistance);
		setUnlocalizedName(name);
	}
	
	public SimpleBlock(Material material, String name, float hardness, float resistance, CreativeTabs tab, String harvesttool, int harvestlevel){
		this(material, name, hardness, resistance, tab);
		setHarvestLevel(harvesttool, harvestlevel);
	}
}
