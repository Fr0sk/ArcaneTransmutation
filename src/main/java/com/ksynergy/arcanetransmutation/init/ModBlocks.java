package com.ksynergy.arcanetransmutation.init;

import com.ksynergy.arcanetransmutation.blocks.BlockAlchemicalContainer;
import com.ksynergy.arcanetransmutation.blocks.BlockAlchemyDesk;
import com.ksynergy.arcanetransmutation.blocks.CustomBlock;
import com.ksynergy.arcanetransmutation.blocks.ICustomBlock;
import com.ksynergy.arcanetransmutation.utils.ModReference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

public class ModBlocks
{
    public static ICustomBlock alchemyDesk;
    public static ICustomBlock alchemicalContainer;
    public static ArrayList<ICustomBlock> blocks;

    public static void init()
    {
        blocks = new ArrayList<ICustomBlock>();

        blocks.add(alchemyDesk = new BlockAlchemyDesk(Material.wood));
        blocks.add(alchemicalContainer = new BlockAlchemicalContainer(Material.cloth));
    }

    public static void register()
    {
        for (ICustomBlock block : blocks)
        {
            GameRegistry.registerBlock(block.getBlock(), block.getName());
        }
    }

    public static void registerRenders()
    {
        ItemModelMesher imm = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        Item item;
        for (ICustomBlock block : blocks)
        {
            item = Item.getItemFromBlock(block.getBlock());
            imm.register(item, 0, new ModelResourceLocation(ModReference.ID + ":" + block.getName(), "inventory"));
        }
    }

    public static void registerRecipes()
    {
        for (ICustomBlock block : blocks)
        {
            block.addBlockRecipes();
        }
    }
}
