package knokko.rpg.packet;

import io.netty.buffer.ByteBuf;

import java.util.UUID;

import knokko.util.ExtraUtils;
import knokko.util.Line;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class LineMessage implements IMessage{
	@Override
	public void fromBytes(ByteBuf buf) {
		red = Double.parseDouble(ByteBufUtils.readUTF8String(buf));
		green = Double.parseDouble(ByteBufUtils.readUTF8String(buf));
		blue = Double.parseDouble(ByteBufUtils.readUTF8String(buf));
		id = ByteBufUtils.readUTF8String(buf);
		line = Line.createLineFromNBT(ByteBufUtils.readTag(buf));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, Double.toString(red));
		ByteBufUtils.writeUTF8String(buf, Double.toString(green));
		ByteBufUtils.writeUTF8String(buf, Double.toString(blue));
		ByteBufUtils.writeUTF8String(buf, id);
		NBTTagCompound nbt = new NBTTagCompound();
		line.writeToNBT(nbt);
		ByteBufUtils.writeTag(buf, nbt);
	}
	
	public double red;
	public double green;
	public double blue;
	
	public String id;
	
	public Line line;
	
	public LineMessage() {}
	public LineMessage(double red, double green, double blue, UUID uuid, Line line){
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.line = line;
		id = uuid.toString();
	}
	
	public static class Handler implements IMessageHandler<LineMessage, IMessage> {

		@Override
		public IMessage onMessage(LineMessage message, MessageContext ctx) {
			World world = Minecraft.getMinecraft().thePlayer.worldObj;
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setDouble("red", message.red);
			nbt.setDouble("green", message.green);
			nbt.setDouble("blue", message.blue);
			nbt.setString("id", message.id);
			message.line.writeToNBT(nbt);
			message.line.spawnParticles(world, message.red, message.green, message.blue, ExtraUtils.divineAccurate(0.2, message.line.distance), 5);
			return null;
		}
	}
}
