package com.ksynergy.arcanetransmutation.items;

import net.minecraft.item.Item;

public abstract class CustomItem extends Item implements ICustomItem
{
    public CustomItem()
    {
        this.setUnlocalizedName(this.getName());
    }
}
