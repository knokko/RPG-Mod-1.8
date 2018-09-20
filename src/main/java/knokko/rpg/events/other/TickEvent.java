package knokko.rpg.events.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import knokko.rpg.data.WorldData;
import knokko.rpg.entity.data.UndeadTeam;
import knokko.rpg.main.KnokkoRPG;
import knokko.rpg.packet.LineMessage;
import knokko.util.Line;
import knokko.util.Position;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class TickEvent {
	
	public static List poison_arrows = new ArrayList();
	public static List cursed_arrows = new ArrayList();
	public static List power_arrows = new ArrayList();
	public static NBTTagCompound data = new NBTTagCompound();
	
	@SubscribeEvent
	public void tickEvent(WorldTickEvent event){
		int times = 0;
		Random random = new Random();
		World world = event.world;
		while(times < poison_arrows.size()){
			EntityArrow arrow = (EntityArrow) poison_arrows.get(times);
			Line line = new Line(new Position(arrow), new Position(arrow.prevPosX, arrow.prevPosY, arrow.prevPosZ));
			KnokkoRPG.network.sendToAllAround(new LineMessage(0.1, 0.6, 0.2, arrow.getUniqueID(), line), new TargetPoint(arrow.dimension, arrow.posX, arrow.posY, arrow.posZ, 50));
			if(arrow.isDead){
				poison_arrows.remove(times);
			}
			++times;
		}
		times = 0;
		while(times < cursed_arrows.size()){
			EntityArrow arrow = (EntityArrow) cursed_arrows.get(times);
			Line line = new Line(new Position(arrow), new Position(arrow.prevPosX, arrow.prevPosY, arrow.prevPosZ));
			KnokkoRPG.network.sendToAllAround(new LineMessage(0.1, 0.1, 0.1, arrow.getUniqueID(), line), new TargetPoint(arrow.dimension, arrow.posX, arrow.posY, arrow.posZ, 50));
			if(arrow.isDead){
				cursed_arrows.remove(times);
			}
			++times;
		}
		times = 0;
		while(times < power_arrows.size()){
			EntityArrow arrow = (EntityArrow) power_arrows.get(times);
			Line line = new Line(new Position(arrow), new Position(arrow.prevPosX, arrow.prevPosY, arrow.prevPosZ));
			KnokkoRPG.network.sendToAllAround(new LineMessage(2, 0.1, 0.1, arrow.getUniqueID(), line), new TargetPoint(arrow.dimension, arrow.posX, arrow.posY, arrow.posZ, 50));
			if(arrow.isDead){
				power_arrows.remove(times);
			}
			++times;
		}
		WorldData data = WorldData.get(event.world);
		if(data == null)
			return;
		List datas = data.undeadTeams;
		times = 0;
		while(times < datas.size()){
			Object object = datas.get(times);
			if(object instanceof UndeadTeam){
				UndeadTeam team = (UndeadTeam) object;
				team.onUpdate();
			}
			++times;
		}
		/*
		if(world.provider.dimensionId == KnokkoRPG.fireLandsId){
			List entities = world.loadedEntityList;
			if(entities.size() < 100 && world.playerEntities.size() > 0){
				Entity entity = FireLandsProvider.getRandomSpawnAbleEntity(world, random);
				EntityPlayer player = (EntityPlayer) world.playerEntities.get(random.nextInt(world.playerEntities.size()));
				int x = (int) (player.posX - 100 + random.nextInt(200));
				int z = (int) (player.posZ - 100 + random.nextInt(200));
				int y = BlockUtils.getHighestBlock(world, x, z);
				entity.setPosition(x, y + 1, z);
				world.spawnEntityInWorld(entity);
			}
		}
		*/
	}
}
