package mod.mcreator;//based on master condiguration

import cpw.mods.fml.client.*;
import cpw.mods.fml.client.registry.*;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.asm.*;
import cpw.mods.fml.common.asm.transformers.*;
import cpw.mods.fml.common.discovery.*;
import cpw.mods.fml.common.discovery.asm.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.functions.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.common.registry.*;
import cpw.mods.fml.common.toposort.*;
import cpw.mods.fml.common.versioning.*;
import cpw.mods.fml.relauncher.*;
import cpw.mods.fml.server.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.client.*;
import net.minecraft.client.audio.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.achievement.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.model.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.culling.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.client.settings.*;
import net.minecraft.command.*;
import net.minecraft.crash.*;
import net.minecraft.creativetab.*;
import net.minecraft.dispenser.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.boss.*;
import net.minecraft.entity.effect.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.item.crafting.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.network.rcon.*;
import net.minecraft.pathfinding.*;
import net.minecraft.potion.*;
import net.minecraft.profiler.*;
import net.minecraft.server.*;
import net.minecraft.server.dedicated.*;
import net.minecraft.server.gui.*;
import net.minecraft.server.integrated.*;
import net.minecraft.server.management.*;
import net.minecraft.src.*;
import net.minecraft.stats.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.village.*;
import net.minecraft.world.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.chunk.*;
import net.minecraft.world.chunk.storage.*;
import net.minecraft.world.demo.*;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.layer.*;
import net.minecraft.world.gen.structure.*;
import net.minecraft.world.storage.*;
import net.minecraftforge.classloading.*;
import net.minecraftforge.client.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.event.sound.*;
import net.minecraftforge.common.*;
import net.minecraftforge.event.*;
import net.minecraftforge.event.entity.*;
import net.minecraftforge.event.entity.item.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.minecart.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.event.terraingen.*;
import net.minecraftforge.event.world.*;
import net.minecraftforge.oredict.*;
import net.minecraftforge.transformers.*;
import net.minecraft.init.*;
import java.util.*;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.*;
//import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.*;
import net.minecraftforge.common.*;
import cpw.mods.fml.common.eventhandler.Event.*;
import net.minecraftforge.event.terraingen.*;

//import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.*;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.*;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.*;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.*;

import net.minecraftforge.common.util.*;
import net.minecraft.client.renderer.texture.*;

@SuppressWarnings("unchecked")
public class mcreator_creeperDimention {

public Object instance;
public static int DIMID = 4;

public static BlockTutorialPortal portal;
public static ModTrigger block;

static{

portal = (BlockTutorialPortal)(new BlockTutorialPortal()
.setBlockName("creeperDimention_portal")
.setBlockTextureName("Block"));
Block.blockRegistry.addObject(187, "creeperDimention_portal", portal);
block = (ModTrigger)(new ModTrigger().setUnlocalizedName("creeperDimention_trigger").setTextureName("CowSpawn"));
Item.itemRegistry.addObject(458, "creeperDimention_trigger", block);
}

public mcreator_creeperDimention(){}

public void load(){
GameRegistry.registerBlock(portal, "creeperDimention_portal");
DimensionManager.registerProviderType(DIMID, mcreator_creeperDimention.WorldProviderMod.class, false);
DimensionManager.registerDimension(DIMID, DIMID);

}

public void registerRenderers(){}
public void generateNether(World world, Random random, int chunkX, int chunkZ){}
public void generateSurface(World world, Random random, int chunkX, int chunkZ){}
public int addFuel(ItemStack fuel){
	return 0;
}
public void serverLoad(FMLServerStartingEvent event){}
public void preInit(FMLPreInitializationEvent event){}

public static class WorldProviderMod extends WorldProvider{

    public void registerWorldChunkManager(){
        this.worldChunkMgr = new WorldChunkManagerHell(mcreator_creeperBiome.biome, 0.0F);
        this.isHellWorld = true;
        this.hasNoSky = true;
        this.dimensionId = DIMID;
    }

    @SideOnly(Side.CLIENT)
	public Vec3 getFogColor(float par1, float par2){
	    return Vec3.createVectorHelper(0.0D,0.6D,0.0D);
    }

    public IChunkProvider createChunkGenerator(){
        return new ChunkProviderModded(this.worldObj, this.worldObj.getSeed()-21922);
    }

    public boolean isSurfaceWorld(){
        return false;
    }

    public boolean canCoordinateBeSpawn(int par1, int par2){
        return false;
    }

    public boolean canRespawnHere(){
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int par1, int par2){
        return false;
    }

    public String getDimensionName(){
        return "creeperDimention";
    }

    @Override protected void generateLightBrightnessTable() {float f = 0.5F;for(int i = 0; i <= 15; ++i) {float f1 = 1.0F - (float) i / 15.0F;this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;}}

}

public class TutorialPortalPosition extends ChunkCoordinates{
	public long field_85087_d;
	final TeleporterDimensionMod field_85088_e;
	public TutorialPortalPosition(TeleporterDimensionMod tutorialTeleporter, int par2, int par3, int par4, long par5){
		super(par2, par3, par4);
		this.field_85088_e = tutorialTeleporter;
		this.field_85087_d = par5;
	}
}

public static class TeleporterDimensionMod extends Teleporter {

	private final WorldServer worldServerInstance;
    /**
     * A private Random() function in Teleporter
     */
    private final Random random;
    /**
     * Stores successful portal placement locations for rapid lookup.
     */
    private final LongHashMap destinationCoordinateCache = new LongHashMap();
    /**
     * A list of valid keys for the destinationCoordainteCache. These are based on the X & Z of the players initial
     * location.
     */
    private final List destinationCoordinateKeys = new ArrayList();
    private static final String __OBFID = "CL_00000153";




    public TeleporterDimensionMod(WorldServer par1WorldServer){
        super(par1WorldServer);
        this.worldServerInstance = par1WorldServer;
        this.random = new Random(par1WorldServer.getSeed());
    }

    /**
     * Place an entity in a nearby portal, creating one if necessary.
     */
    public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
    {
        if (this.worldServerInstance.provider.dimensionId != 1)
        {
            if (!this.placeInExistingPortal(par1Entity, par2, par4, par6, par8))
            {
                this.makePortal(par1Entity);
                this.placeInExistingPortal(par1Entity, par2, par4, par6, par8);
            }
        }
        else
        {
            int i = MathHelper.floor_double(par1Entity.posX);
            int j = MathHelper.floor_double(par1Entity.posY) - 1;
            int k = MathHelper.floor_double(par1Entity.posZ);
            byte b0 = 1;
            byte b1 = 0;

            for (int l = -2; l <= 2; ++l)
            {
                for (int i1 = -2; i1 <= 2; ++i1)
                {
                    for (int j1 = -1; j1 < 3; ++j1)
                    {
                        int k1 = i + i1 * b0 + l * b1;
                        int l1 = j + j1;
                        int i2 = k + i1 * b1 - l * b0;
                        boolean flag = j1 < 0;
                        this.worldServerInstance.setBlock(k1, l1, i2, flag ? Blocks.tnt : Blocks.air);
                    }
                }
            }

            par1Entity.setLocationAndAngles((double)i, (double)j, (double)k, par1Entity.rotationYaw, 0.0F);
            par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
        }
    }

    /**
     * Place an entity in a nearby portal which already exists.
     */
    public boolean placeInExistingPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
    {
        short short1 = 128;
        double d3 = -1.0D;
        int i = 0;
        int j = 0;
        int k = 0;
        int l = MathHelper.floor_double(par1Entity.posX);
        int i1 = MathHelper.floor_double(par1Entity.posZ);
        long j1 = ChunkCoordIntPair.chunkXZ2Int(l, i1);
        boolean flag = true;
        double d7;
        int l3;

        if (this.destinationCoordinateCache.containsItem(j1))
        {
            Teleporter.PortalPosition portalposition = (Teleporter.PortalPosition)this.destinationCoordinateCache.getValueByKey(j1);
            d3 = 0.0D;
            i = portalposition.posX;
            j = portalposition.posY;
            k = portalposition.posZ;
            portalposition.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
            flag = false;
        }
        else
        {
            for (l3 = l - short1; l3 <= l + short1; ++l3)
            {
                double d4 = (double)l3 + 0.5D - par1Entity.posX;

                for (int l1 = i1 - short1; l1 <= i1 + short1; ++l1)
                {
                    double d5 = (double)l1 + 0.5D - par1Entity.posZ;

                    for (int i2 = this.worldServerInstance.getActualHeight() - 1; i2 >= 0; --i2)
                    {
                        if (this.worldServerInstance.getBlock(l3, i2, l1) == portal)
                        {
                            while (this.worldServerInstance.getBlock(l3, i2 - 1, l1) == portal)
                            {
                                --i2;
                            }

                            d7 = (double)i2 + 0.5D - par1Entity.posY;
                            double d8 = d4 * d4 + d7 * d7 + d5 * d5;

                            if (d3 < 0.0D || d8 < d3)
                            {
                                d3 = d8;
                                i = l3;
                                j = i2;
                                k = l1;
                            }
                        }
                    }
                }
            }
        }

        if (d3 >= 0.0D)
        {
            if (flag)
            {
                this.destinationCoordinateCache.add(j1, new Teleporter.PortalPosition(i, j, k, this.worldServerInstance.getTotalWorldTime()));
                this.destinationCoordinateKeys.add(Long.valueOf(j1));
            }

            double d11 = (double)i + 0.5D;
            double d6 = (double)j + 0.5D;
            d7 = (double)k + 0.5D;
            int i4 = -1;

            if (this.worldServerInstance.getBlock(i - 1, j, k) == portal)
            {
                i4 = 2;
            }

            if (this.worldServerInstance.getBlock(i + 1, j, k) == portal)
            {
                i4 = 0;
            }

            if (this.worldServerInstance.getBlock(i, j, k - 1) == portal)
            {
                i4 = 3;
            }

            if (this.worldServerInstance.getBlock(i, j, k + 1) == portal)
            {
                i4 = 1;
            }

            int j2 = par1Entity.getTeleportDirection();

            if (i4 > -1)
            {
                int k2 = Direction.rotateLeft[i4];
                int l2 = Direction.offsetX[i4];
                int i3 = Direction.offsetZ[i4];
                int j3 = Direction.offsetX[k2];
                int k3 = Direction.offsetZ[k2];
                boolean flag1 = !this.worldServerInstance.isAirBlock(i + l2 + j3, j, k + i3 + k3) || !this.worldServerInstance.isAirBlock(i + l2 + j3, j + 1, k + i3 + k3);
                boolean flag2 = !this.worldServerInstance.isAirBlock(i + l2, j, k + i3) || !this.worldServerInstance.isAirBlock(i + l2, j + 1, k + i3);

                if (flag1 && flag2)
                {
                    i4 = Direction.rotateOpposite[i4];
                    k2 = Direction.rotateOpposite[k2];
                    l2 = Direction.offsetX[i4];
                    i3 = Direction.offsetZ[i4];
                    j3 = Direction.offsetX[k2];
                    k3 = Direction.offsetZ[k2];
                    l3 = i - j3;
                    d11 -= (double)j3;
                    int k1 = k - k3;
                    d7 -= (double)k3;
                    flag1 = !this.worldServerInstance.isAirBlock(l3 + l2 + j3, j, k1 + i3 + k3) || !this.worldServerInstance.isAirBlock(l3 + l2 + j3, j + 1, k1 + i3 + k3);
                    flag2 = !this.worldServerInstance.isAirBlock(l3 + l2, j, k1 + i3) || !this.worldServerInstance.isAirBlock(l3 + l2, j + 1, k1 + i3);
                }

                float f1 = 0.5F;
                float f2 = 0.5F;

                if (!flag1 && flag2)
                {
                    f1 = 1.0F;
                }
                else if (flag1 && !flag2)
                {
                    f1 = 0.0F;
                }
                else if (flag1 && flag2)
                {
                    f2 = 0.0F;
                }

                d11 += (double)((float)j3 * f1 + f2 * (float)l2);
                d7 += (double)((float)k3 * f1 + f2 * (float)i3);
                float f3 = 0.0F;
                float f4 = 0.0F;
                float f5 = 0.0F;
                float f6 = 0.0F;

                if (i4 == j2)
                {
                    f3 = 1.0F;
                    f4 = 1.0F;
                }
                else if (i4 == Direction.rotateOpposite[j2])
                {
                    f3 = -1.0F;
                    f4 = -1.0F;
                }
                else if (i4 == Direction.rotateRight[j2])
                {
                    f5 = 1.0F;
                    f6 = -1.0F;
                }
                else
                {
                    f5 = -1.0F;
                    f6 = 1.0F;
                }

                double d9 = par1Entity.motionX;
                double d10 = par1Entity.motionZ;
                par1Entity.motionX = d9 * (double)f3 + d10 * (double)f6;
                par1Entity.motionZ = d9 * (double)f5 + d10 * (double)f4;
                par1Entity.rotationYaw = par8 - (float)(j2 * 90) + (float)(i4 * 90);
            }
            else
            {
                par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
            }

            par1Entity.setLocationAndAngles(d11, d6, d7, par1Entity.rotationYaw, par1Entity.rotationPitch);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean makePortal(Entity par1Entity)
    {
        byte b0 = 16;
        double d0 = -1.0D;
        int i = MathHelper.floor_double(par1Entity.posX);
        int j = MathHelper.floor_double(par1Entity.posY);
        int k = MathHelper.floor_double(par1Entity.posZ);
        int l = i;
        int i1 = j;
        int j1 = k;
        int k1 = 0;
        int l1 = this.random.nextInt(4);
        int i2;
        double d1;
        double d2;
        int k2;
        int i3;
        int k3;
        int j3;
        int i4;
        int l3;
        int k4;
        int j4;
        int i5;
        int l4;
        double d3;
        double d4;

        for (i2 = i - b0; i2 <= i + b0; ++i2)
        {
            d1 = (double)i2 + 0.5D - par1Entity.posX;

            for (k2 = k - b0; k2 <= k + b0; ++k2)
            {
                d2 = (double)k2 + 0.5D - par1Entity.posZ;
                label274:

                for (i3 = this.worldServerInstance.getActualHeight() - 1; i3 >= 0; --i3)
                {
                    if (this.worldServerInstance.isAirBlock(i2, i3, k2))
                    {
                        while (i3 > 0 && this.worldServerInstance.isAirBlock(i2, i3 - 1, k2))
                        {
                            --i3;
                        }

                        for (j3 = l1; j3 < l1 + 4; ++j3)
                        {
                            k3 = j3 % 2;
                            l3 = 1 - k3;

                            if (j3 % 4 >= 2)
                            {
                                k3 = -k3;
                                l3 = -l3;
                            }

                            for (i4 = 0; i4 < 3; ++i4)
                            {
                                for (j4 = 0; j4 < 4; ++j4)
                                {
                                    for (k4 = -1; k4 < 4; ++k4)
                                    {
                                        l4 = i2 + (j4 - 1) * k3 + i4 * l3;
                                        i5 = i3 + k4;
                                        int j5 = k2 + (j4 - 1) * l3 - i4 * k3;

                                        if (k4 < 0 && !this.worldServerInstance.getBlock(l4, i5, j5).getMaterial().isSolid() || k4 >= 0 && !this.worldServerInstance.isAirBlock(l4, i5, j5))
                                        {
                                            continue label274;
                                        }
                                    }
                                }
                            }

                            d4 = (double)i3 + 0.5D - par1Entity.posY;
                            d3 = d1 * d1 + d4 * d4 + d2 * d2;

                            if (d0 < 0.0D || d3 < d0)
                            {
                                d0 = d3;
                                l = i2;
                                i1 = i3;
                                j1 = k2;
                                k1 = j3 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (d0 < 0.0D)
        {
            for (i2 = i - b0; i2 <= i + b0; ++i2)
            {
                d1 = (double)i2 + 0.5D - par1Entity.posX;

                for (k2 = k - b0; k2 <= k + b0; ++k2)
                {
                    d2 = (double)k2 + 0.5D - par1Entity.posZ;
                    label222:

                    for (i3 = this.worldServerInstance.getActualHeight() - 1; i3 >= 0; --i3)
                    {
                        if (this.worldServerInstance.isAirBlock(i2, i3, k2))
                        {
                            while (i3 > 0 && this.worldServerInstance.isAirBlock(i2, i3 - 1, k2))
                            {
                                --i3;
                            }

                            for (j3 = l1; j3 < l1 + 2; ++j3)
                            {
                                k3 = j3 % 2;
                                l3 = 1 - k3;

                                for (i4 = 0; i4 < 4; ++i4)
                                {
                                    for (j4 = -1; j4 < 4; ++j4)
                                    {
                                        k4 = i2 + (i4 - 1) * k3;
                                        l4 = i3 + j4;
                                        i5 = k2 + (i4 - 1) * l3;

                                        if (j4 < 0 && !this.worldServerInstance.getBlock(k4, l4, i5).getMaterial().isSolid() || j4 >= 0 && !this.worldServerInstance.isAirBlock(k4, l4, i5))
                                        {
                                            continue label222;
                                        }
                                    }
                                }

                                d4 = (double)i3 + 0.5D - par1Entity.posY;
                                d3 = d1 * d1 + d4 * d4 + d2 * d2;

                                if (d0 < 0.0D || d3 < d0)
                                {
                                    d0 = d3;
                                    l = i2;
                                    i1 = i3;
                                    j1 = k2;
                                    k1 = j3 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int k5 = l;
        int j2 = i1;
        k2 = j1;
        int l5 = k1 % 2;
        int l2 = 1 - l5;

        if (k1 % 4 >= 2)
        {
            l5 = -l5;
            l2 = -l2;
        }

        boolean flag;

        if (d0 < 0.0D)
        {
            if (i1 < 70)
            {
                i1 = 70;
            }

            if (i1 > this.worldServerInstance.getActualHeight() - 10)
            {
                i1 = this.worldServerInstance.getActualHeight() - 10;
            }

            j2 = i1;

            for (i3 = -1; i3 <= 1; ++i3)
            {
                for (j3 = 1; j3 < 3; ++j3)
                {
                    for (k3 = -1; k3 < 3; ++k3)
                    {
                        l3 = k5 + (j3 - 1) * l5 + i3 * l2;
                        i4 = j2 + k3;
                        j4 = k2 + (j3 - 1) * l2 - i3 * l5;
                        flag = k3 < 0;
                        this.worldServerInstance.setBlock(l3, i4, j4, flag ? Blocks.tnt : Blocks.air);
                    }
                }
            }
        }

        for (i3 = 0; i3 < 4; ++i3)
        {
            for (j3 = 0; j3 < 4; ++j3)
            {
                for (k3 = -1; k3 < 4; ++k3)
                {
                    l3 = k5 + (j3 - 1) * l5;
                    i4 = j2 + k3;
                    j4 = k2 + (j3 - 1) * l2;
                    flag = j3 == 0 || j3 == 3 || k3 == -1 || k3 == 3;
                    this.worldServerInstance.setBlock(l3, i4, j4, (Block)(flag ? Blocks.tnt : portal), 0, 2);
                }
            }

            for (j3 = 0; j3 < 4; ++j3)
            {
                for (k3 = -1; k3 < 4; ++k3)
                {
                    l3 = k5 + (j3 - 1) * l5;
                    i4 = j2 + k3;
                    j4 = k2 + (j3 - 1) * l2;
                    this.worldServerInstance.notifyBlocksOfNeighborChange(l3, i4, j4, this.worldServerInstance.getBlock(l3, i4, j4));
                }
            }
        }

        return true;
    }

    /**
     * called periodically to remove out-of-date portal locations from the cache list. Argument par1 is a
     * WorldServer.getTotalWorldTime() value.
     */
    public void removeStalePortalLocations(long par1)
    {
        if (par1 % 100L == 0L)
        {
            Iterator iterator = this.destinationCoordinateKeys.iterator();
            long j = par1 - 600L;

            while (iterator.hasNext())
            {
                Long olong = (Long)iterator.next();
                Teleporter.PortalPosition portalposition = (Teleporter.PortalPosition)this.destinationCoordinateCache.getValueByKey(olong.longValue());

                if (portalposition == null || portalposition.lastUpdateTime < j)
                {
                    iterator.remove();
                    this.destinationCoordinateCache.remove(olong.longValue());
                }
            }
        }
    }

    public class PortalPosition extends ChunkCoordinates
    {
        /**
         * The worldtime at which this PortalPosition was last verified
         */
        public long lastUpdateTime;
        private static final String __OBFID = "CL_00000154";

        public PortalPosition(int par2, int par3, int par4, long par5)
        {
            super(par2, par3, par4);
            this.lastUpdateTime = par5;
        }
    }
}

///FIRE BLOCK

static class BlockFireMod extends Block{

protected BlockFireMod(){
        super(Material.ground);
}

public void onBlockAdded(World par1World, int par2, int par3, int par4){
/*TutorialPortal.tryToCreatePortal(par1World, par2, par3, par4);*/
}

}//fire block end


///PORTAL BLOCK

public static class BlockTutorialPortal extends BlockBreakable
{
	IIcon gor = null, dol = null, st1 = null, st2 = null, st3 = null, st4 = null;
public BlockTutorialPortal()
{
super("", Material.portal, false);
this.setTickRandomly(true);
this.setHardness(-1.0F);
this.setLightLevel(0.75F);
}

@SideOnly(Side.CLIENT)
@Override
public IIcon getIcon(int i, int par2){

if (i == 0)
return gor;

else if (i == 1)
return dol;

else if (i == 2)
return st1;

else if (i == 3)
return st2;

else if (i == 4)
return st4;

else if (i == 5)
return st3;

else
return gor;

}

@SideOnly(Side.CLIENT)
@Override
public void registerBlockIcons(IIconRegister reg){
this.gor = reg.registerIcon("Block");
this.dol = reg.registerIcon("Block");
this.st1 = reg.registerIcon("Block");
this.st2 = reg.registerIcon("Block");
this.st3 = reg.registerIcon("Block");
this.st4 = reg.registerIcon("Block");
}

/**
* Ticks the block if it's been scheduled
*/
public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
{
super.updateTick(par1World, par2, par3, par4, par5Random);
if (par1World.provider.isSurfaceWorld())
{
int l;
for (l = par3; !par1World.doesBlockHaveSolidTopSurface(par1World, par2, l, par4) && l > 0; --l)
{
;
}
if (l > 0 && !par1World.isBlockNormalCubeDefault(par2, l + 1, par4, true))
{
Entity entity = ItemMonsterPlacer.spawnCreature(par1World, 57, (double)par2 + 0.5D, (double)l + 1.1D, (double)par4 + 0.5D);
if (entity != null)
{
         entity.timeUntilPortal = entity.getPortalCooldown();
}
}
}
}
/**
* Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
* cleared to be reused)
*/
public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
{
return null;
}
/**
* Updates the blocks bounds based on its current state. Args: world, x, y, z
*/
public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
{
float f;
float f1;
if (par1IBlockAccess.getBlock(par2 - 1, par3, par4) != this && par1IBlockAccess.getBlock(par2 + 1, par3, par4) != this)
{
f = 0.125F;
f1 = 0.5F;
this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
}
else
{
f = 0.5F;
f1 = 0.125F;
this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
}
}
/**
* Is this block (a) opaque and (B) a full 1m cube? This determines whether or not to render the shared face of two
* adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
*/
public boolean isOpaqueCube()
{
return false;
}
/**
* If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
*/
@Override
public boolean renderAsNormalBlock()
{
return false;
}
/**
* Checks to see if this location is valid to create a portal and will return True if it does. Args: world, x, y, z
*/
public boolean tryToCreatePortal(World par1World, int par2, int par3, int par4)
{
byte b0 = 0;
byte b1 = 0;
if (par1World.getBlock(par2 - 1, par3, par4) == Blocks.tnt || par1World.getBlock(par2 + 1, par3, par4) == Blocks.tnt)
{
b0 = 1;
}
if (par1World.getBlock(par2, par3, par4 - 1) == Blocks.tnt || par1World.getBlock(par2, par3, par4 + 1) == Blocks.tnt)
{
b1 = 1;
}
if (b0 == b1)
{
return false;
}
else
{
if (par1World.getBlock(par2 - b0, par3, par4 - b1) == Blocks.air)
{
par2 -= b0;
par4 -= b1;
}
int l;
int i1;
for (l = -1; l <= 2; ++l)
{
for (i1 = -1; i1 <= 3; ++i1)
{
         boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;
         if (l != -1 && l != 2 || i1 != -1 && i1 != 3)
         {
         Block j1 = par1World.getBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l);
         if (flag)
         {
         if (j1 != Blocks.tnt)
         {
         return false;
         }
         }
         /*else if (j1 != 0 && j1 != Main.TutorialFire.blockID)
         {
         return false;
         }*/
         }
}
}
for (l = 0; l < 2; ++l)
{
for (i1 = 0; i1 < 3; ++i1)
{
         par1World.setBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l, this, 0, 2);
}
}
return true;
}
}
/**
* Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
* their own) Args: x, y, z, neighbor blockID
*/
public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
{
byte b0 = 0;
byte b1 = 1;
if (par1World.getBlock(par2 - 1, par3, par4) == this || par1World.getBlock(par2 + 1, par3, par4) == this)
{
b0 = 1;
b1 = 0;
}
int i1;
for (i1 = par3; par1World.getBlock(par2, i1 - 1, par4) == this; --i1)
{
;
}
if (par1World.getBlock(par2, i1 - 1, par4) != Blocks.tnt)
{
par1World.setBlockToAir(par2, par3, par4);
}
else
{
int j1;
for (j1 = 1; j1 < 4 && par1World.getBlock(par2, i1 + j1, par4) == this; ++j1)
{
;
}
if (j1 == 3 && par1World.getBlock(par2, i1 + j1, par4) == Blocks.tnt)
{
boolean flag = par1World.getBlock(par2 - 1, par3, par4) == this || par1World.getBlock(par2 + 1, par3, par4) == this;
boolean flag1 = par1World.getBlock(par2, par3, par4 - 1) == this || par1World.getBlock(par2, par3, par4 + 1) == this;
if (flag && flag1)
{
         par1World.setBlockToAir(par2, par3, par4);
}
else
{
         if ((par1World.getBlock(par2 + b0, par3, par4 + b1) != Blocks.tnt || par1World.getBlock(par2 - b0, par3, par4 - b1) != this) && (par1World.getBlock(par2 - b0, par3, par4 - b1) != Blocks.tnt || par1World.getBlock(par2 + b0, par3, par4 + b1) != this))
         {
         par1World.setBlockToAir(par2, par3, par4);
         }
}
}
else
{
par1World.setBlockToAir(par2, par3, par4);
}
}
}
@SideOnly(Side.CLIENT)
/**
* Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
* coordinates. Args: blockAccess, x, y, z, side
*/
public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
{
if (par1IBlockAccess.getBlock(par2, par3, par4) == this)
{
return false;
}
else
{
boolean flag = par1IBlockAccess.getBlock(par2 - 1, par3, par4) == this && par1IBlockAccess.getBlock(par2 - 2, par3, par4) != this;
boolean flag1 = par1IBlockAccess.getBlock(par2 + 1, par3, par4) == this && par1IBlockAccess.getBlock(par2 + 2, par3, par4) != this;
boolean flag2 = par1IBlockAccess.getBlock(par2, par3, par4 - 1) == this && par1IBlockAccess.getBlock(par2, par3, par4 - 2) != this;
boolean flag3 = par1IBlockAccess.getBlock(par2, par3, par4 + 1) == this && par1IBlockAccess.getBlock(par2, par3, par4 + 2) != this;
boolean flag4 = flag || flag1;
boolean flag5 = flag2 || flag3;
return flag4 && par5 == 4 ? true : (flag4 && par5 == 5 ? true : (flag5 && par5 == 2 ? true : flag5 && par5 == 3));
}
}
/**
* Returns the quantity of items to drop on block destruction.
*/
public int quantityDropped(Random par1Random)
{
return 0;
}
/**
* Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
*/
public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
{
if ((par5Entity.ridingEntity == null) && (par5Entity.riddenByEntity == null) && ((par5Entity instanceof EntityPlayerMP)))
         {
         EntityPlayerMP thePlayer = (EntityPlayerMP)par5Entity;
         if (thePlayer.timeUntilPortal > 0)
         {
                 thePlayer.timeUntilPortal = 10;
         }
         else if (thePlayer.dimension != DIMID)
         {
                 thePlayer.timeUntilPortal = 10;
                 thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, DIMID, new TeleporterDimensionMod(thePlayer.mcServer.worldServerForDimension(DIMID)));
         }
         else {
                 thePlayer.timeUntilPortal = 10;
                 thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new TeleporterDimensionMod(thePlayer.mcServer.worldServerForDimension(0)));
         }
         }
}
@SideOnly(Side.CLIENT)
/**
* Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
*/
public int getRenderBlockPass()
{
return 1;
}
@SideOnly(Side.CLIENT)
/**
* A randomly called display update to be able to add particles or other items for display
*/
public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
{
if (par5Random.nextInt(100) == 0)
{
par1World.playSound((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "portal.portal", 0.5F, par5Random.nextFloat() * 0.4F + 0.8F, false);
}
for (int l = 0; l < 4; ++l)
{
double d0 = (double)((float)par2 + par5Random.nextFloat());
double d1 = (double)((float)par3 + par5Random.nextFloat());
double d2 = (double)((float)par4 + par5Random.nextFloat());
double d3 = 0.0D;
double d4 = 0.0D;
double d5 = 0.0D;
int i1 = par5Random.nextInt(2) * 2 - 1;
d3 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
d4 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
d5 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
if (par1World.getBlock(par2 - 1, par3, par4) != this && par1World.getBlock(par2 + 1, par3, par4) != this)
{
d0 = (double)par2 + 0.5D + 0.25D * (double)i1;
d3 = (double)(par5Random.nextFloat() * 2.0F * (float)i1);
}
else
{
d2 = (double)par4 + 0.5D + 0.25D * (double)i1;
d5 = (double)(par5Random.nextFloat() * 2.0F * (float)i1);
}
par1World.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
}
}
@SideOnly(Side.CLIENT)
/**
* only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
*/
public int idPicked(World par1World, int par2, int par3, int par4)
{
return 0;
}
}

//portal block

public static class ModTrigger extends Item
{
public ModTrigger()
{
super();
this.maxStackSize = 1;
setMaxDamage(64);
setCreativeTab(CreativeTabs.tabTools);
}
public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
{
if (par7 == 0)
{
par5--;
}
if (par7 == 1)
{
par5++;
}
if (par7 == 2)
{
par6--;
}
if (par7 == 3)
{
par6++;
}
if (par7 == 4)
{
par4--;
}
if (par7 == 5)
{
par4++;
}
if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
{
return false;
}
Block i1 = par3World.getBlock(par4, par5, par6);
if (i1 == Blocks.air)
{
par3World.playSoundEffect(par4 + 0.5D, par5 + 0.5D, par6 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
portal.tryToCreatePortal(par3World, par4, par5, par6);
}
par1ItemStack.damageItem(1, par2EntityPlayer);
return true;
}
}



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public static class ChunkProviderModded implements IChunkProvider{

    private Random endRNG;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    public NoiseGeneratorOctaves noiseGen4;
    public NoiseGeneratorOctaves noiseGen5;
    private World endWorld;
    private double[] densities;

    private World worldObj;
    private Random rand;
    /**
     * The biomes that are used to generate the chunk
     */
    private BiomeGenBase[] biomesForGeneration;
    double[] noiseData1;
    double[] noiseData2;
    double[] noiseData3;
    double[] noiseData4;
    double[] noiseData5;
    int[][] field_73203_h = new int[32][32];
    private static final String __OBFID = "CL_00000397";

    public ChunkProviderModded(World par1World, long par2)
    {
        this.endWorld = par1World;
        this.endRNG = new Random(par2);
        rand = endRNG;
        worldObj = par1World;
        this.noiseGen1 = new NoiseGeneratorOctaves(this.endRNG, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.endRNG, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.endRNG, 8);
        this.noiseGen4 = new NoiseGeneratorOctaves(this.endRNG, 10);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.endRNG, 16);

        NoiseGenerator[] noiseGens = {noiseGen1, noiseGen2, noiseGen3, noiseGen4, noiseGen5};
        noiseGens = TerrainGen.getModdedNoiseGenerators(par1World, this.endRNG, noiseGens);
        this.noiseGen1 = (NoiseGeneratorOctaves)noiseGens[0];
        this.noiseGen2 = (NoiseGeneratorOctaves)noiseGens[1];
        this.noiseGen3 = (NoiseGeneratorOctaves)noiseGens[2];
        this.noiseGen4 = (NoiseGeneratorOctaves)noiseGens[3];
        this.noiseGen5 = (NoiseGeneratorOctaves)noiseGens[4];
    }

    public void func_147420_a(int p_147420_1_, int p_147420_2_, Block[] p_147420_3_, BiomeGenBase[] p_147420_4_)
    {
        byte b0 = 2;
        int k = b0 + 1;
        byte b1 = 33;
        int l = b0 + 1;
        this.densities = this.initializeNoiseField(this.densities, p_147420_1_ * b0, 0, p_147420_2_ * b0, k, b1, l);

        for (int i1 = 0; i1 < b0; ++i1)
        {
            for (int j1 = 0; j1 < b0; ++j1)
            {
                for (int k1 = 0; k1 < 32; ++k1)
                {
                    double d0 = 0.25D;
                    double d1 = this.densities[((i1 + 0) * l + j1 + 0) * b1 + k1 + 0];
                    double d2 = this.densities[((i1 + 0) * l + j1 + 1) * b1 + k1 + 0];
                    double d3 = this.densities[((i1 + 1) * l + j1 + 0) * b1 + k1 + 0];
                    double d4 = this.densities[((i1 + 1) * l + j1 + 1) * b1 + k1 + 0];
                    double d5 = (this.densities[((i1 + 0) * l + j1 + 0) * b1 + k1 + 1] - d1) * d0;
                    double d6 = (this.densities[((i1 + 0) * l + j1 + 1) * b1 + k1 + 1] - d2) * d0;
                    double d7 = (this.densities[((i1 + 1) * l + j1 + 0) * b1 + k1 + 1] - d3) * d0;
                    double d8 = (this.densities[((i1 + 1) * l + j1 + 1) * b1 + k1 + 1] - d4) * d0;

                    for (int l1 = 0; l1 < 4; ++l1)
                    {
                        double d9 = 0.125D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i2 = 0; i2 < 8; ++i2)
                        {
                            int j2 = i2 + i1 * 8 << 11 | 0 + j1 * 8 << 7 | k1 * 4 + l1;
                            short short1 = 128;
                            double d14 = 0.125D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;

                            for (int k2 = 0; k2 < 8; ++k2)
                            {
                                Block block = null;

                                if (d15 > 0.0D)
                                {
                                    block = mcreator_creeprStone.block;
                                }

                                p_147420_3_[j2] = block;
                                j2 += short1;
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void func_147421_b(int p_147421_1_, int p_147421_2_, Block[] p_147421_3_, BiomeGenBase[] p_147421_4_)
    {
        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, p_147421_1_, p_147421_2_, p_147421_3_, p_147421_4_);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Result.DENY) return;

        for (int k = 0; k < 16; ++k)
        {
            for (int l = 0; l < 16; ++l)
            {
                byte b0 = 1;
                int i1 = -1;
                Block block = mcreator_creeprStone.block;
                Block block1 = mcreator_creeprStone.block;

                for (int j1 = 127; j1 >= 0; --j1)
                {
                    int k1 = (l * 16 + k) * 128 + j1;
                    Block block2 = p_147421_3_[k1];

                    if (block2 != null && block2.getMaterial() != Material.air)
                    {
                        if (block2 == Blocks.stone)
                        {
                            if (i1 == -1)
                            {
                                if (b0 <= 0)
                                {
                                    block = null;
                                    block1 = mcreator_creeprStone.block;
                                }

                                i1 = b0;

                                if (j1 >= 0)
                                {
                                    p_147421_3_[k1] = block;
                                }
                                else
                                {
                                    p_147421_3_[k1] = block1;
                                }
                            }
                            else if (i1 > 0)
                            {
                                --i1;
                                p_147421_3_[k1] = block1;
                            }
                        }
                    }
                    else
                    {
                        i1 = -1;
                    }
                }
            }
        }
    }

    /**
     * loads or generates the chunk at the chunk location specified
     */
    public Chunk loadChunk(int par1, int par2)
    {
        return this.provideChunk(par1, par2);
    }

    /**
     * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
     * specified chunk from the map seed and chunk seed
     */
    public Chunk provideChunk(int par1, int par2)
    {
        this.endRNG.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
        Block[] ablock = new Block[32768];
        this.biomesForGeneration = this.endWorld.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.func_147420_a(par1, par2, ablock, this.biomesForGeneration);
        this.func_147421_b(par1, par2, ablock, this.biomesForGeneration);
        Chunk chunk = new Chunk(this.endWorld, ablock, par1, par2);
        byte[] abyte = chunk.getBiomeArray();

        for (int k = 0; k < abyte.length; ++k)
        {
            abyte[k] = (byte)this.biomesForGeneration[k].biomeID;
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    /**
     * generates a subset of the level's terrain data. Takes 7 arguments: the [empty] noise array, the position, and the
     * size.
     */
    private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7)
    {
        ChunkProviderEvent.InitNoiseField event = new ChunkProviderEvent.InitNoiseField(this, par1ArrayOfDouble, par2, par3, par4, par5, par6, par7);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Result.DENY) return event.noisefield;

        if (par1ArrayOfDouble == null)
        {
            par1ArrayOfDouble = new double[par5 * par6 * par7];
        }

        double d0 = 684.412D;
        double d1 = 684.412D;
        this.noiseData4 = this.noiseGen4.generateNoiseOctaves(this.noiseData4, par2, par4, par5, par7, 1.121D, 1.121D, 0.5D);
        this.noiseData5 = this.noiseGen5.generateNoiseOctaves(this.noiseData5, par2, par4, par5, par7, 200.0D, 200.0D, 0.5D);
        d0 *= 2.0D;
        this.noiseData1 = this.noiseGen3.generateNoiseOctaves(this.noiseData1, par2, par3, par4, par5, par6, par7, d0 / 80.0D, d1 / 160.0D, d0 / 80.0D);
        this.noiseData2 = this.noiseGen1.generateNoiseOctaves(this.noiseData2, par2, par3, par4, par5, par6, par7, d0, d1, d0);
        this.noiseData3 = this.noiseGen2.generateNoiseOctaves(this.noiseData3, par2, par3, par4, par5, par6, par7, d0, d1, d0);
        int k1 = 0;
        int l1 = 0;

        for (int i2 = 0; i2 < par5; ++i2)
        {
            for (int j2 = 0; j2 < par7; ++j2)
            {
                double d2 = (this.noiseData4[l1] + 256.0D) / 512.0D;

                if (d2 > 1.0D)
                {
                    d2 = 1.0D;
                }

                double d3 = this.noiseData5[l1] / 8000.0D;

                if (d3 < 0.0D)
                {
                    d3 = -d3 * 0.3D;
                }

                d3 = d3 * 3.0D - 2.0D;
                float f = (float)(i2 + par2 - 0) / 1.0F;
                float f1 = (float)(j2 + par4 - 0) / 1.0F;
                float f2 = 100.0F - MathHelper.sqrt_float(f * f + f1 * f1) * 8.0F;

                if (f2 > 80.0F)
                {
                    f2 = 80.0F;
                }

                if (f2 < -100.0F)
                {
                    f2 = -100.0F;
                }

                if (d3 > 1.0D)
                {
                    d3 = 1.0D;
                }

                d3 /= 8.0D;
                d3 = 0.0D;

                if (d2 < 0.0D)
                {
                    d2 = 0.0D;
                }

                d2 += 0.5D;
                d3 = d3 * (double)par6 / 16.0D;
                ++l1;
                double d4 = (double)par6 / 2.0D;

                for (int k2 = 0; k2 < par6; ++k2)
                {
                    double d5 = 0.0D;
                    double d6 = ((double)k2 - d4) * 8.0D / d2;

                    if (d6 < 0.0D)
                    {
                        d6 *= -1.0D;
                    }

                    double d7 = this.noiseData2[k1] / 512.0D;
                    double d8 = this.noiseData3[k1] / 512.0D;
                    double d9 = (this.noiseData1[k1] / 10.0D + 1.0D) / 2.0D;

                    if (d9 < 0.0D)
                    {
                        d5 = d7;
                    }
                    else if (d9 > 1.0D)
                    {
                        d5 = d8;
                    }
                    else
                    {
                        d5 = d7 + (d8 - d7) * d9;
                    }

                    d5 -= 8.0D;
                    d5 += (double)f2;
                    byte b0 = 2;
                    double d10;

                    if (k2 > par6 / 2 - b0)
                    {
                        d10 = (double)((float)(k2 - (par6 / 2 - b0)) / 64.0F);

                        if (d10 < 0.0D)
                        {
                            d10 = 0.0D;
                        }

                        if (d10 > 1.0D)
                        {
                            d10 = 1.0D;
                        }

                        d5 = d5 * (1.0D - d10) + -3000.0D * d10;
                    }

                    b0 = 8;

                    if (k2 < b0)
                    {
                        d10 = (double)((float)(b0 - k2) / ((float)b0 - 1.0F));
                        d5 = d5 * (1.0D - d10) + -30.0D * d10;
                    }

                    par1ArrayOfDouble[k1] = d5;
                    ++k1;
                }
            }
        }

        return par1ArrayOfDouble;
    }

    /**
     * Checks to see if a chunk exists at x, y
     */
    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    /**
     * Populates chunk with ores etc etc
     */
    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
    {
        BlockFalling.fallInstantly = true;

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(par1IChunkProvider, endWorld, endWorld.rand, par2, par3, false));

        int k = par2 * 16;
        int l = par3 * 16;
        //BiomeGenBase biomegenbase = this.endWorld.getBiomeGenForCoords(k + 16, l + 16);
        //biomegenbase.decorate(this.endWorld, this.endWorld.rand, k, l);

        int var4 = par2 * 16;
		int var5 = par3 * 16;
        

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(par1IChunkProvider, endWorld, endWorld.rand, par2, par3, false));

        BlockFalling.fallInstantly = false;
    }

    /**
     * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
     * Return true if all chunks have been saved.
     */
    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }

    /**
     * Save extra data not associated with any Chunk.  Not saved during autosave, only during world unload.  Currently
     * unimplemented.
     */
    public void saveExtraData() {}

    /**
     * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
     */
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    public boolean canSave()
    {
        return true;
    }

    /**
     * Converts the instance data to a readable string.
     */
    public String makeString()
    {
        return "RandomLevelSource";
    }

    /**
     * Returns a list of creatures of the specified type that can spawn at the given location.
     */
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
    {
        BiomeGenBase biomegenbase = this.endWorld.getBiomeGenForCoords(par2, par4);
        return biomegenbase.getSpawnableList(par1EnumCreatureType);
    }

    public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
    {
        return null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void recreateStructures(int par1, int par2) {}
}


}
