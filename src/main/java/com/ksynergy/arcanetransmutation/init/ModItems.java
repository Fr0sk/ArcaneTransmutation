package com.ksynergy.arcanetransmutation.init;

import com.ksynergy.arcanetransmutation.items.CustomItem;
import com.ksynergy.arcanetransmutation.items.ItemDustSulfur;
import com.ksynergy.arcanetransmutation.utils.ModReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

public class ModItems
{
    public static CustomItem dustSulfur;
    public static ArrayList<CustomItem> items;

    public static void init()
    {
        items = new ArrayList<CustomItem>();
        items.add(dustSulfur = new ItemDustSulfur());
    }

    public static void register()
    {
        for (CustomItem item : items)
        {
            GameRegistry.registerItem(item, item.getName());
        }
    }

    public static void registerRenders()
    {
        ItemModelMesher imm = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        for (CustomItem item : items)
        {
            imm.register(item, 0, new ModelResourceLocation(ModReference.ID + ":" + item.getName(), "inventory"));
        }
    }

    public static void registerRecipes()
    {
        for (CustomItem item : items)
        {
            item.addItemRecipes();
        }
    }
}
