package knokko.rpg.items;

import java.util.List;

import knokko.rpg.blocks.main.RPGBlocks;
import knokko.rpg.data.WorldData;
import knokko.rpg.entity.minion.EntityChow;
import knokko.rpg.entity.minion.FightType;
import knokko.rpg.entity.minion.TargetType;
import knokko.rpg.entity.monster.EntityLavaShark;
import knokko.rpg.main.KnokkoRPG;
import knokko.rpg.main.s;
import knokko.util.BlockUtils;
import knokko.util.ExtraUtils;
import knokko.util.Line;
import knokko.util.Position;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityHeartFX;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;

public class TestItem extends Item {
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player){
		player.swingItem();
		MinecraftServer server = MinecraftServer.getServer();
		if(player instanceof EntityPlayerMP)
			server.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) player, KnokkoRPG.fireLandsId, new Teleporter(server.worldServerForDimension(KnokkoRPG.fireLandsId)){
				
				@Override
				public void placeInPortal(Entity entity, float f){
					entity.posY = BlockUtils.getHighestBlock(entity.worldObj, (int)entity.posX, (int) entity.posZ) + 10;
					int y = (int)entity.posY;
					int x = (int) (entity.posX - 5);
					while(x <= entity.posX + 5){
						int z = (int)entity.posZ - 5;
						while(z < entity.posZ + 5){
							entity.worldObj.setBlockState(new BlockPos(x, y - 2, z), RPGBlocks.basalt.getDefaultState());
							++z;
						}
						++x;
					}
				}
			});
		return item;
	}
	
	public TestItem(){
		setUnlocalizedName("testitem");
		setCreativeTab(CreativeTabs.tabMisc);
	}
}
