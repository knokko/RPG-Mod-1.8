package knokko.rpg.tileentity;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class RPGTileEntities {
	public static void registerTileEntities(){
		GameRegistry.registerTileEntity(TileEntityTable.class, "TileEntityTable");
		GameRegistry.registerTileEntity(TileEntityMobSpawner.class, "TileEntityMobSpawner");
		GameRegistry.registerTileEntity(TileEntityTest.class, "TileEntityTest");
	}
}
