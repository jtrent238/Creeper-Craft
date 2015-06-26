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

@SuppressWarnings("unchecked")
public class mcreator_fatCreeper {

	public static int mobid = 0;
	public Object instance;

    public void load(){}

    public void generateNether(World world, Random random, int chunkX, int chunkZ){}
	public void generateSurface(World world, Random random, int chunkX, int chunkZ){}
	public int addFuel(ItemStack fuel){
		return 0;
	}
	@SideOnly(Side.CLIENT)
	public void registerRenderers(){
		RenderingRegistry.registerEntityRenderingHandler(mcreator_fatCreeper.EntityfatCreeper.class, new RenderLiving(new mcreator_fatCreeper.ModelNew(), 0){protected ResourceLocation getEntityTexture(Entity par1Entity){return new ResourceLocation("Fat Creeper.png");}});
	}
	public void serverLoad(FMLServerStartingEvent event){}
	public void preInit(FMLPreInitializationEvent event){
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
		mobid = entityID;
		EntityRegistry.registerGlobalEntityID(mcreator_fatCreeper.EntityfatCreeper.class, "fatCreeper", entityID);
		EntityRegistry.registerModEntity(mcreator_fatCreeper.EntityfatCreeper.class, "fatCreeper", entityID, instance, 64, 1, true);
		EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID, (0 << 16) + (204 << 8) + 0, (204 << 16) + (255 << 8) + 204));
		EntityRegistry.addSpawn(mcreator_fatCreeper.EntityfatCreeper.class, 20, 3, 30, EnumCreatureType.monster , new BiomeGenBase[]{BiomeGenBase.ocean, BiomeGenBase.plains, BiomeGenBase.desert, BiomeGenBase.extremeHills, BiomeGenBase.forest, BiomeGenBase.taiga, BiomeGenBase.swampland, BiomeGenBase.river, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver, BiomeGenBase.icePlains, BiomeGenBase.iceMountains, BiomeGenBase.mushroomIsland, BiomeGenBase.mushroomIslandShore, BiomeGenBase.beach, BiomeGenBase.desertHills, BiomeGenBase.forestHills, BiomeGenBase.taigaHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.jungleEdge, BiomeGenBase.deepOcean, BiomeGenBase.stoneBeach, BiomeGenBase.coldBeach, BiomeGenBase.birchForest, BiomeGenBase.birchForestHills, BiomeGenBase.roofedForest, BiomeGenBase.coldTaiga, BiomeGenBase.coldTaigaHills, BiomeGenBase.megaTaiga, BiomeGenBase.megaTaigaHills, BiomeGenBase.extremeHillsPlus, BiomeGenBase.savanna, BiomeGenBase.savannaPlateau, BiomeGenBase.mesa, BiomeGenBase.mesaPlateau_F, BiomeGenBase.mesaPlateau});

        
	}

    /*public Entity spawnEntity(int var1, World var2, double var3, double var5, double var7)
    {
        if(var1==mobid)
                return new mcreator_fatCreeper.EntityfatCreeper(var2);
        else
                return null;
    }*/


   public static class EntityfatCreeper extends EntityCreeper 
	{
		World world = null;
	    public EntityfatCreeper(World var1)
	    {
	        super(var1);
	        world = var1;
	        experienceValue = 5;
	        this.isImmuneToFire = false;
	        addRandomArmor();
        	
	    }

	    

	    

	    
protected void addRandomArmor(){

}

protected void dropRareDrop(int par1){
this.dropItem(mcreator_death.block, 1);
}


    	public boolean isAIEnabled()
		{
			   return true;
    	}

	    /**
	     * Drop 0-2 items of this living's type
	     */
	    protected void dropFewItems(boolean var1, int var2)
	    {
	        this.entityDropItem(new ItemStack(mcreator_battleStone.block), 0.0F);
	    }

	    /**
	     * Returns the sound this mob makes while it's alive.
	     */
	    protected String getLivingSound()
	    {
	        return "mob.creeper.say";
	    }

	    /**
	     * Returns the sound this mob makes when it is hurt.
	     */
	    protected String getHurtSound()
	    {
	        return "mob.creeper.say1";
	    }

	    /**
	     * Returns the sound this mob makes on death.
	     */
	    protected String getDeathSound()
	    {
	        return "mob.creeper.death";
	    }

	    public void onStruckByLightning(EntityLightningBolt entityLightningBolt){
			int i = (int)this.posX;
			int j = (int)this.posY;
			int k = (int)this.posZ;
			Entity entity = this;
			
		}

		protected void fall(float l){
			int i = (int)this.posX;
			int j = (int)this.posY;
			int k = (int)this.posZ;
			super.fall(l);
			Entity entity = this;
			
		}

    	public void onCriticalHit(Entity entity) {
			int i = (int)this.posX;
			int j = (int)this.posY;
			int k = (int)this.posZ;
			
		}

		public void onKillEntity(EntityLiving entityLiving){
			int i = (int)this.posX;
			int j = (int)this.posY;
			int k = (int)this.posZ;
			Entity entity = this;
			
		}

		public boolean interact(EntityPlayer entity){
			int i = (int)this.posX;
			int j = (int)this.posY;
			int k = (int)this.posZ;
			
			return true;
		}

		public String getEntityName(){
			return "fatCreeper";
		}

	}

	
// Date: 6/25/2015 10:27:47 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX








public static class ModelNew extends ModelBase
{
//fields
ModelRenderer head;
ModelRenderer body;
ModelRenderer leg3;
ModelRenderer leg4;
ModelRenderer leg1;
ModelRenderer leg2;

public ModelNew()
{
textureWidth = 64;
textureHeight = 32;

head = new ModelRenderer(this, 0, 0);
head.addBox(-4F, -8F, -4F, 8, 8, 8);
head.setRotationPoint(0F, 3F, 0F);
head.setTextureSize(64, 32);
head.mirror = true;
setRotation(head, 0F, 0F, 0F);
body = new ModelRenderer(this, 16, 16);
body.addBox(-4F, 0F, -2F, 13, 16, 10);
body.setRotationPoint(-3F, 3F, 0F);
body.setTextureSize(64, 32);
body.mirror = true;
setRotation(body, 0F, 0F, 0F);
leg3 = new ModelRenderer(this, 0, 16);
leg3.addBox(-2F, 0F, -2F, 4, 6, 4);
leg3.setRotationPoint(-2F, 18F, -4F);
leg3.setTextureSize(64, 32);
leg3.mirror = true;
setRotation(leg3, 0F, 0F, 0F);
leg4 = new ModelRenderer(this, 0, 16);
leg4.addBox(-2F, 0F, -2F, 4, 6, 4);
leg4.setRotationPoint(2F, 18F, -4F);
leg4.setTextureSize(64, 32);
leg4.mirror = true;
setRotation(leg4, 0F, 0F, 0F);
leg1 = new ModelRenderer(this, 0, 16);
leg1.addBox(0F, 0F, -2F, 4, 6, 4);
leg1.setRotationPoint(-4F, 18F, 4F);
leg1.setTextureSize(64, 32);
leg1.mirror = true;
setRotation(leg1, 0F, 0F, 0F);
leg2 = new ModelRenderer(this, 0, 16);
leg2.addBox(-2F, 0F, -2F, 4, 6, 4);
leg2.setRotationPoint(2F, 18F, 4F);
leg2.setTextureSize(64, 32);
leg2.mirror = true;
setRotation(leg2, 0F, 0F, 0F);
}

public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
{
super.render(entity, f, f1, f2, f3, f4, f5);
setRotationAngles(f, f1, f2, f3, f4, f5, entity);

head.render(f5);
body.render(f5);
leg3.render(f5);
leg4.render(f5);
leg1.render(f5);
leg2.render(f5);
}

private void setRotation(ModelRenderer model, float x, float y, float z)
{
model.rotateAngleX = x;
model.rotateAngleY = y;
model.rotateAngleZ = z;
}

public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e)

{
super.setRotationAngles(f, f1, f2, f3, f4, f5, e);

}

}

}
