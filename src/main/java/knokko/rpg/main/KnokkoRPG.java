package knokko.rpg.main;

import knokko.rpg.blocks.main.RPGBlocks;
import knokko.rpg.command.CommandNecromancy;
import knokko.rpg.command.CommandRPG;
import knokko.rpg.command.CommandUUID;
import knokko.rpg.entity.main.RPGentities;
import knokko.rpg.events.main.RPGEvents;
import knokko.rpg.gui.GuiHandler;
import knokko.rpg.items.main.RPGItems;
import knokko.rpg.packet.LineMessage;
import knokko.rpg.packet.NBTMessage;
import knokko.rpg.packet.NecromancyMessage;
import knokko.rpg.proxy.ServerProxy;
import knokko.rpg.recipes.RecipeHandler;
import knokko.rpg.tileentity.RPGTileEntities;
import knokko.rpg.worldgen.RPGGenerator;
import knokko.rpg.worldgen.firelands.FireLandsProvider;
import knokko.rpg.worldgen.voidworld.VoidWorldProvider;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid= s.i, name= s.n, version= s.v)
public class KnokkoRPG {
	
	public static SimpleNetworkWrapper network;
	public static int voidWorldId;
	public static int fireLandsId;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		network = NetworkRegistry.INSTANCE.newSimpleChannel("knokko RPG");
		network.registerMessage(LineMessage.Handler.class, LineMessage.class, 0, Side.CLIENT);
		network.registerMessage(NBTMessage.Handler.class, NBTMessage.class, 1, Side.CLIENT);
		network.registerMessage(NecromancyMessage.Handler.class, NecromancyMessage.class, 2, Side.SERVER);
		RPGItems.load();
		RPGItems.registerItems();
		RPGBlocks.registerBlocks();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		RPGEvents.registerEvents();
		RPGentities.registerEntities();
		RPGTileEntities.registerTileEntities();
		RecipeHandler.load();
		GuiHandler.register();
		proxy.registerRenderThings();
		proxy.registerBlocksAndItems();
		GameRegistry.registerWorldGenerator(new RPGGenerator(), 1);
		voidWorldId = DimensionManager.getNextFreeDimId();
		DimensionManager.registerProviderType(voidWorldId, VoidWorldProvider.class, false);
		DimensionManager.registerDimension(voidWorldId, voidWorldId);
		fireLandsId = DimensionManager.getNextFreeDimId();
		DimensionManager.registerProviderType(fireLandsId, FireLandsProvider.class, false);
		DimensionManager.registerDimension(fireLandsId, fireLandsId);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
	}
	
	@EventHandler
	public void serverEvent(FMLServerStartingEvent event){
		event.registerServerCommand(new CommandRPG());
		event.registerServerCommand(new CommandUUID());
		event.registerServerCommand(new CommandNecromancy());
	}
	
	@Instance(s.i)
	public static KnokkoRPG modInstance;
	
	@SidedProxy(clientSide = "knokko.rpg.proxy.ClientProxy", serverSide = "knokko.rpg.proxy.ServerProxy")
	public static ServerProxy proxy;
	
	public static NBTTagCompound data = new NBTTagCompound();
}
