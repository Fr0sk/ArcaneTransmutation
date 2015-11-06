package com.ksynergy.arcanetransmutation;

import com.ksynergy.arcanetransmutation.init.ModBlocks;
import com.ksynergy.arcanetransmutation.init.ModItems;
import com.ksynergy.arcanetransmutation.proxy.CommonProxy;
import com.ksynergy.arcanetransmutation.utils.ModReference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(name = ModReference.NAME, modid = ModReference.ID, version = ModReference.VERSION)
public class ArcaneTransmutation
{
    @SidedProxy(clientSide = ModReference.CLIENT_PROXY_CLASS, serverSide = ModReference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.Instance
    public static ArcaneTransmutation instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModBlocks.init();
        ModItems.init();

        ModBlocks.register();
        ModItems.register();

        ModBlocks.registerRecipes();
        ModItems.registerRecipes();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.registerRenders();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }
}
