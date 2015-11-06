package com.ksynergy.arcanetransmutation.items;

import com.ksynergy.arcanetransmutation.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ItemDustSulfur extends CustomItem
{
    private final String name = "dustSulfur";

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public void addItemRecipes()
    {
        ItemStack coal = new ItemStack(net.minecraft.init.Items.coal);
        ItemStack sulfur = new ItemStack(ModItems.dustSulfur);
        GameRegistry.addSmelting(coal, sulfur, 0);

        OreDictionary.registerOre(this.getName(), ModItems.dustSulfur);
    }
}
