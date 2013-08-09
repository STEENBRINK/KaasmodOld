package nl.steenbrink.kaasmod.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import nl.steenbrink.kaasmod.lib.Reference;
import nl.steenbrink.kaasmod.tile.TileKaascreator;
import nl.steenbrink.kaasmod.tile.TilePurifier;
import nl.steenbrink.kaasmod.tile.TileToaster;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {
    @Override
    public void onPacketData(INetworkManager network, Packet250CustomPayload packet, Player player) {
        ByteArrayDataInput dat = ByteStreams.newDataInput(packet.data);
        int x = dat.readInt();
        int y = dat.readInt();
        int z = dat.readInt();

        World world = FMLClientHandler.instance().getClient().theWorld;
        TileEntity te = world.getBlockTileEntity(x, y, z);
        world.markBlockForRenderUpdate(x, y, z);
        if (te instanceof TileKaascreator) {
            TileKaascreator tileKaascreator = (TileKaascreator) te;
            tileKaascreator.handlePacketData(dat);
        }
        if (te instanceof TilePurifier) {
            TilePurifier tilePurifier = (TilePurifier) te;
            tilePurifier.handlePacketData(dat);
        }
        if (te instanceof TileToaster) {
            TileToaster tileToaster = (TileToaster) te;
            tileToaster.handlePacketData(dat);
        }
    }

    public static Packet getPacket(TileEntity tileEntity) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(140);
        DataOutputStream dos = new DataOutputStream(bos);

        try {
            dos.writeInt(tileEntity.xCoord);
            dos.writeInt(tileEntity.yCoord);
            dos.writeInt(tileEntity.zCoord);

            if (tileEntity instanceof TileKaascreator) {
                TileKaascreator kaascreatorTE = (TileKaascreator) tileEntity;
                dos.writeInt(kaascreatorTE.processTime);
            }
            if (tileEntity instanceof TilePurifier) {
                TilePurifier purifierTE = (TilePurifier) tileEntity;
                dos.writeInt(purifierTE.processTime);
            }
            if (tileEntity instanceof TileToaster) {
                TileToaster toasterTE = (TileToaster) tileEntity;
                dos.writeInt(toasterTE.processTime);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Packet250CustomPayload pkt = new Packet250CustomPayload();
        pkt.channel = Reference.CHANNEL_NAME;
        pkt.data = bos.toByteArray();
        pkt.length = bos.size();
        pkt.isChunkDataPacket = true;
        return pkt;
    }
}
