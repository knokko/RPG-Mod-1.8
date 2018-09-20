package knokko.rpg.blocks;

import knokko.rpg.main.s;
import knokko.rpg.tileentity.TileEntityTest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TestBlock extends BlockContainer{

	public TestBlock() {
		super(Material.rock);
		setUnlocalizedName("test");
		setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityTest();
	}
}
