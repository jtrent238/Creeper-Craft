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
import java.util.Random;

public class mcreator_biome {

public static BiomeGenbiome biome = new BiomeGenbiome();

public Object instance;

public mcreator_biome(){}

public void load(){
BiomeDictionary.registerBiomeType(biome, BiomeDictionary.Type.FOREST);
BiomeManager.addSpawnBiome(biome);
BiomeManager.warmBiomes.add(new BiomeManager.BiomeEntry(biome, 10));
}

public void generateNether(World world, Random random, int chunkX, int chunkZ){}
public void generateSurface(World world, Random random, int chunkX, int chunkZ){}
public void registerRenderers(){}
public int addFuel(ItemStack fuel){
	return 0;
}
public void serverLoad(FMLServerStartingEvent event){}
public void preInit(FMLPreInitializationEvent event){}

static class BiomeGenbiome extends BiomeGenBase
{
	@SuppressWarnings("unchecked")
    public BiomeGenbiome()
    {
        super(41);
        setBiomeName("biome");
        topBlock = Blocks.diamond_ore;
        fillerBlock = Blocks.diamond_ore;
        theBiomeDecorator.generateLakes = true;
	theBiomeDecorator.treesPerChunk = 10;
	theBiomeDecorator.flowersPerChunk = 10;
	theBiomeDecorator.grassPerChunk = 10;
	theBiomeDecorator.deadBushPerChunk = 10;
	theBiomeDecorator.mushroomsPerChunk = 10;
	theBiomeDecorator.reedsPerChunk = 10;
	theBiomeDecorator.cactiPerChunk = 10;
   	theBiomeDecorator.sandPerChunk = 10;
   	rainfall = 0.5F;
   	setHeight(new BiomeGenBase.Height(0.1F, 0.3F));
   	waterColorMultiplier = 0xff0000;

   	
    }

    public WorldGenerator getRandomWorldGenForTrees(Random par1Random){
return new Tree();
}
class Tree extends WorldGenerator
{
    /** The minimum height of a generated tree. */
    private final int minTreeHeight;

    /** True if this tree should grow Vines. */
    private final boolean vinesGrow;

    /** The metadata value of the wood to use in tree generation. */
    private final int metaWood;

    /** The metadata value of the leaves to use in tree generation. */
    private final int metaLeaves;

    public Tree()
    {
        super(false);
        this.minTreeHeight = 10;
        this.metaWood = 0;
        this.metaLeaves = 0;
        this.vinesGrow = true;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int var6 = par2Random.nextInt(3) + this.minTreeHeight;
        boolean var7 = true;

        if (par4 >= 1 && par4 + var6 + 1 <= 256)
        {
            int var8;
            byte var9;
            int var11;
            int var12;

            for (var8 = par4; var8 <= par4 + 1 + var6; ++var8)
            {
                var9 = 1;

                if (var8 == par4)
                {
                    var9 = 0;
                }

                if (var8 >= par4 + 1 + var6 - 2)
                {
                    var9 = 2;
                }

                for (int var10 = par3 - var9; var10 <= par3 + var9 && var7; ++var10)
                {
                    for (var11 = par5 - var9; var11 <= par5 + var9 && var7; ++var11)
                    {
                        if (var8 >= 0 && var8 < 256)
                        {
                            Block var12s = par1World.getBlock(var10, var8, var11);
                            var12 = Block.getIdFromBlock(var12s);

                            if (var12 != 0 && var12s != Blocks.end_portal && var12s != Blocks.grass && var12s != Blocks.dirt && var12s != Blocks.mob_spawner)
                            {
                                var7 = false;
                            }
                        }
                        else
                        {
                            var7 = false;
                        }
                    }
                }
            }

            if (!var7)
            {
                return false;
            }
            else
            {
				Block var8s = par1World.getBlock(par3, par4 - 1, par5);
                var8 = Block.getIdFromBlock(var8s);

                if ((var8s == Blocks.grass || var8s == Blocks.dirt) && par4 < 256 - var6 - 1)
                {
                    par1World.setBlock(par3, par4 - 1, par5, Blocks.dirt, 0, 2);
                    var9 = 3;
                    byte var18 = 0;
                    int var13;
                    int var14;
                    int var15;

                    for (var11 = par4 - var9 + var6; var11 <= par4 + var6; ++var11)
                    {
                        var12 = var11 - (par4 + var6);
                        var13 = var18 + 1 - var12 / 2;

                        for (var14 = par3 - var13; var14 <= par3 + var13; ++var14)
                        {
                            var15 = var14 - par3;

                            for (int var16 = par5 - var13; var16 <= par5 + var13; ++var16)
                            {
                                int var17 = var16 - par5;

                                if ((Math.abs(var15) != var13 || Math.abs(var17) != var13 || par2Random.nextInt(2) != 0 && var12 != 0))
                                {
                                    par1World.setBlock(var14, var11, var16, Blocks.diamond_ore, this.metaLeaves, 2);
                                }
                            }
                        }
                    }

                    for (var11 = 0; var11 < var6; ++var11)
                    {
						Block var12s = par1World.getBlock(par3, par4 + var11, par5);
                        var12 = Block.getIdFromBlock(var12s);

                        if (var12 == 0 || var12s == Blocks.diamond_ore)
                        {
                            par1World.setBlock(par3, par4 + var11, par5, Blocks.mob_spawner, this.metaWood, 2);

                            if (this.vinesGrow && var11 > 0)
                            {
                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 - 1, par4 + var11, par5))
                                {
                                    par1World.setBlock(par3 - 1, par4 + var11, par5, Blocks.end_portal, 8, 2);
                                }

                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 + 1, par4 + var11, par5))
                                {
                                    par1World.setBlock(par3 + 1, par4 + var11, par5, Blocks.end_portal, 2, 2);
                                }

                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + var11, par5 - 1))
                                {
                                    par1World.setBlock(par3, par4 + var11, par5 - 1, Blocks.end_portal, 1, 2);
                                }

                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + var11, par5 + 1))
                                {
                                    par1World.setBlock(par3, par4 + var11, par5 + 1, Blocks.end_portal, 4, 2);
                                }
                            }
                        }
                    }

                    if (this.vinesGrow)
                    {
                        for (var11 = par4 - 3 + var6; var11 <= par4 + var6; ++var11)
                        {
                            var12 = var11 - (par4 + var6);
                            var13 = 2 - var12 / 2;

                            for (var14 = par3 - var13; var14 <= par3 + var13; ++var14)
                            {
                                for (var15 = par5 - var13; var15 <= par5 + var13; ++var15)
                                {
                                    if (par1World.getBlock(var14, var11, var15) == Blocks.diamond_ore)
                                    {
                                        if (par2Random.nextInt(4) == 0 && Block.getIdFromBlock(par1World.getBlock(var14 - 1, var11, var15)) == 0)
                                        {
                                            this.growVines(par1World, var14 - 1, var11, var15, 8);
                                        }

                                        if (par2Random.nextInt(4) == 0 && Block.getIdFromBlock(par1World.getBlock(var14 + 1, var11, var15)) == 0)
                                        {
                                            this.growVines(par1World, var14 + 1, var11, var15, 2);
                                        }

                                        if (par2Random.nextInt(4) == 0 && Block.getIdFromBlock(par1World.getBlock(var14, var11, var15 - 1)) == 0)
                                        {
                                            this.growVines(par1World, var14, var11, var15 - 1, 1);
                                        }

                                        if (par2Random.nextInt(4) == 0 && Block.getIdFromBlock(par1World.getBlock(var14, var11, var15 + 1)) == 0)
                                        {
                                            this.growVines(par1World, var14, var11, var15 + 1, 4);
                                        }
                                    }
                                }
                            }
                        }

                        if (par2Random.nextInt(5) == 0 && var6 > 5)
                        {
                            for (var11 = 0; var11 < 2; ++var11)
                            {
                                for (var12 = 0; var12 < 4; ++var12)
                                {
                                    if (par2Random.nextInt(4 - var11) == 0)
                                    {
                                        var13 = par2Random.nextInt(3);
                                        par1World.setBlock(par3 + Direction.offsetX[Direction.rotateOpposite[var12]], par4 + var6 - 5 + var11, par5 + Direction.offsetZ[Direction.rotateOpposite[var12]], Blocks.cocoa, var13 << 2 | var12, 2);
                                    }
                                }
                            }
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Grows vines downward from the given block for a given length. Args: World, x, starty, z, vine-length
     */
    private void growVines(World par1World, int par2, int par3, int par4, int par5)
    {
        par1World.setBlock(par2, par3, par4, Blocks.vine, par5, 2);
        int var6 = 4;

        while (true)
        {
            --par3;

            if (Block.getIdFromBlock(par1World.getBlock(par2, par3, par4)) != 0 || var6 <= 0)
            {
                return;
            }

            par1World.setBlock(par2, par3, par4, Blocks.end_portal, par5, 2);
            --var6;
        }
    }
}

//10 - visina
//true - true/false za vin
//Blocks.end_portal - block za vine
//Blocks.mob_spawner - block za cez les
//Blocks.diamond_ore - block za listje
//Blocks.cocoa - block za coce

    @SideOnly(Side.CLIENT)
public int getBiomeGrassColor(){return 0xff0000;}
@SideOnly(Side.CLIENT)
public int getBiomeFoliageColor(){return 0xff0000;}
@SideOnly(Side.CLIENT)
public int getSkyColorByTemp(float par1){return 0xff0000;}

}

}
