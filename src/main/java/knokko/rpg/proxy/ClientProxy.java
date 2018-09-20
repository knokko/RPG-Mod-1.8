package knokko.rpg.proxy;

import knokko.rpg.RPG;
import knokko.rpg.blocks.main.RPGBlocks;
import knokko.rpg.entity.creature.EntityFireEye;
import knokko.rpg.entity.creature.EntityIceEye;
import knokko.rpg.entity.creature.EntityLifeEye;
import knokko.rpg.entity.creature.EntityMonkey;
import knokko.rpg.entity.data.EntityEyeGroupData;
import knokko.rpg.entity.data.EntityMonkeyGroupData;
import knokko.rpg.entity.data.UndeadTeam;
import knokko.rpg.entity.effect.EntityBlood;
import knokko.rpg.entity.effect.EntityEnergyBall;
import knokko.rpg.entity.minion.EntityChow;
import knokko.rpg.entity.minion.EntityDreadLord;
import knokko.rpg.entity.minion.EntitySoar;
import knokko.rpg.entity.model.ModelDragon;
import knokko.rpg.entity.model.ModelDreadLord;
import knokko.rpg.entity.model.ModelEye;
import knokko.rpg.entity.model.ModelShark;
import knokko.rpg.entity.model.ModelTroll;
import knokko.rpg.entity.monster.EntityEmpire;
import knokko.rpg.entity.monster.EntityFireLander;
import knokko.rpg.entity.monster.EntityLavaShark;
import knokko.rpg.entity.monster.EntityTroll;
import knokko.rpg.entity.monster.FireDragon;
import knokko.rpg.entity.projectile.EntityDarkPulse;
import knokko.rpg.entity.projectile.EntityExplosiveSpell;
import knokko.rpg.entity.projectile.EntityFireSpell;
import knokko.rpg.entity.projectile.EntityIceSpell;
import knokko.rpg.entity.projectile.EntityLaser;
import knokko.rpg.entity.projectile.EntityMeteor;
import knokko.rpg.entity.render.RenderChow;
import knokko.rpg.entity.render.RenderDragon;
import knokko.rpg.entity.render.RenderDreadLord;
import knokko.rpg.entity.render.RenderEmpire;
import knokko.rpg.entity.render.RenderEye;
import knokko.rpg.entity.render.RenderFireDragon;
import knokko.rpg.entity.render.RenderFireLander;
import knokko.rpg.entity.render.RenderInvisible;
import knokko.rpg.entity.render.RenderLavaShark;
import knokko.rpg.entity.render.RenderMeteor;
import knokko.rpg.entity.render.RenderMonkey;
import knokko.rpg.entity.render.RenderSoar;
import knokko.rpg.entity.render.RenderTroll;
import knokko.rpg.items.main.RPGItems;
import knokko.rpg.main.s;
import knokko.rpg.tileentity.TileEntityTable;
import knokko.rpg.tileentity.render.TableRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.client.particle.EntityReddustFX.Factory;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class ClientProxy extends ServerProxy{
	
	@Override
	public void registerRenderThings(){
		RenderManager manager = Minecraft.getMinecraft().getRenderManager();
		RenderingRegistry.registerEntityRenderingHandler(EntityLifeEye.class, new RenderEye(manager, new ModelEye(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityIceEye.class, new RenderEye(manager, new ModelEye(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFireEye.class, new RenderEye(manager, new ModelEye(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMeteor.class, new RenderMeteor(manager, 5));
		RenderingRegistry.registerEntityRenderingHandler(EntityEyeGroupData.class, new RenderInvisible(manager));
		RenderingRegistry.registerEntityRenderingHandler(UndeadTeam.class, new RenderInvisible(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityEnergyBall.class, new RenderInvisible(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityIceSpell.class, new RenderInvisible(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityMonkeyGroupData.class, new RenderInvisible(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityTroll.class, new RenderTroll(manager, new ModelTroll(0, 0, 128, 64), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityMonkey.class, new RenderMonkey(manager, new ModelBiped(), 0.3f));
		RenderingRegistry.registerEntityRenderingHandler(EntityExplosiveSpell.class, new RenderInvisible(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityEmpire.class, new RenderEmpire(manager, new ModelBiped(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFireSpell.class, new RenderInvisible(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkPulse.class, new RenderInvisible(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderInvisible(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityChow.class, new RenderChow(manager, new ModelBiped(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityDreadLord.class, new RenderDreadLord(manager, new ModelDreadLord(0, 0, 64, 32), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySoar.class, new RenderSoar(manager, new ModelBiped(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityLavaShark.class, new RenderLavaShark(manager, new ModelShark(), 3));
		RenderingRegistry.registerEntityRenderingHandler(EntityBlood.class, new RenderInvisible(manager));
		RenderingRegistry.registerEntityRenderingHandler(FireDragon.class, new RenderDragon(manager, new ModelDragon(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityFireLander.class, new RenderFireLander(manager, new ModelBiped(), 1));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTable.class, new TableRenderer());
	}
	
	@Override
	public void registerBlocksAndItems(){
		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		mesher.register(RPGItems.bananaSword, 0, new ModelResourceLocation(s.t + "bananasword", "inventory"));
		mesher.register(RPGItems.ruby, 0, new ModelResourceLocation(s.t + "ruby", "inventory"));
		mesher.register(RPGItems.ruby_sword, 0, new ModelResourceLocation(s.t + "rubysword", "inventory"));
		mesher.register(RPGItems.ruby_axe, 0, new ModelResourceLocation(s.t + "rubyaxe", "inventory"));
		mesher.register(RPGItems.testitem, 0, new ModelResourceLocation(s.t + "testitem", "inventory"));
		mesher.register(RPGItems.woodWand, 0, new ModelResourceLocation(s.t + "woodwand", "inventory"));
		mesher.register(RPGItems.skullRod, 0, new ModelResourceLocation(s.t + "skullrod", "inventory"));
		mesher.register(RPGItems.ruby_pickaxe, 0, new ModelResourceLocation(s.t + "rubypickaxe", "inventory"));
		mesher.register(RPGItems.ruby_shovel, 0, new ModelResourceLocation(s.t + "rubyshovel", "inventory"));
		
		mesher.register(Item.getItemFromBlock(RPGBlocks.basalt), 0, new ModelResourceLocation(s.t + "basalt", "inventory"));
		mesher.register(Item.getItemFromBlock(RPGBlocks.table), 0, new ModelResourceLocation(s.t + "table", "inventory"));
		mesher.register(Item.getItemFromBlock(RPGBlocks.ruby_ore), 0, new ModelResourceLocation(s.t + "rubyore", "inventory"));
		mesher.register(Item.getItemFromBlock(RPGBlocks.voidportal), 0, new ModelResourceLocation(s.t + "voidportal", "inventory"));
		mesher.register(Item.getItemFromBlock(RPGBlocks.fireportal), 0, new ModelResourceLocation(s.t + "fireportal", "inventory"));
		mesher.register(Item.getItemFromBlock(RPGBlocks.paved_obsidian), 0, new ModelResourceLocation(s.t + "pavedobsidian", "inventory"));
		mesher.register(Item.getItemFromBlock(RPGBlocks.voidbrick), 0, new ModelResourceLocation(s.t + "voidbrick", "inventory"));
	}
	
	@Override
	public void spawnParticle(World world, double x, double y, double z, double red, double green, double blue, int renderDistance){
		Factory factory = new EntityReddustFX.Factory();
		EntityFX fx = factory.getEntityFX(0, world, x, y, z, red, blue, green, 0);
		if(fx == null)
			return;
		fx.setRBGColorF((float)red, (float)green, (float) blue);
		fx.renderDistanceWeight = renderDistance;
		Minecraft.getMinecraft().effectRenderer.addEffect(fx);
	}
	
	@Override
	public void spawnParticle(World world, double x, double y, double z, double red, double green, double blue){
		spawnParticle(world, x, y, z, red, green, blue, 8);
	}
	
	@Override
	public Side getSide(){
		return Side.CLIENT;
	}
	
	@Override
	public void setPlayerWalkSpeed(EntityPlayer player, float speed){
		player.capabilities.setPlayerWalkSpeed(speed);
	}
}