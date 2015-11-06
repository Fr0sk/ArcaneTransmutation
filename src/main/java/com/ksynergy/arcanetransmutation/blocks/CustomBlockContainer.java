package com.ksynergy.arcanetransmutation.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

public abstract class CustomBlockContainer extends BlockContainer implements ICustomBlock
{
    public CustomBlockContainer(Material materialIn)
    {
        super(materialIn);
        this.setUnlocalizedName(this.getName());
    }
}