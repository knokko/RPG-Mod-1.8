package knokko.rpg.blocks;

import knokko.rpg.main.s;
import knokko.rpg.tileentity.TileEntityTable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Table extends BlockContainer{

	public Table() {
		super(Material.wood);
		setHardness(2);
		setResistance(15);
		setCreativeTab(CreativeTabs.tabDecorations);
		setUnlocalizedName("table");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityTable();
	}
    @Override
    public int getRenderType() {
        return -1;
    }
    
    @Override
    public boolean isOpaqueCube() {
        return false;
    }
    
    public boolean renderAsNormalBlock() {
        return false;
    }
    
}
