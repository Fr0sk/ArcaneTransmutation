package com.ksynergy.arcanetransmutation.init;

import com.ksynergy.arcanetransmutation.blocks.BlockAlchemyDesk;
import com.ksynergy.arcanetransmutation.blocks.CustomBlock;
import com.ksynergy.arcanetransmutation.utils.ModReference;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

public class ModBlocks
{
    public static CustomBlock alchemyDesk;
    public static ArrayList<CustomBlock> blocks;

    public static void init()
    {
        blocks = new ArrayList<CustomBlock>();
        blocks.add(alchemyDesk = new BlockAlchemyDesk(Material.wood));
    }

    public static void register()
    {
        for (CustomBlock block : blocks)
        {
            GameRegistry.registerBlock(block, block.getName());
        }
    }

    public static void registerRenders()
    {
        ItemModelMesher imm = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        Item item;
        for (CustomBlock block : blocks)
        {
            item = Item.getItemFromBlock(block);
            imm.register(item, 0, new ModelResourceLocation(ModReference.ID + ":" + block.getName(), "inventory"));
        }
    }

    public static void registerRecipes()
    {
        for (CustomBlock block : blocks)
        {
            block.addBlockRecipes();
        }
    }
}
