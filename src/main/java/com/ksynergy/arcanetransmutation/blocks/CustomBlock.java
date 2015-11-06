package com.ksynergy.arcanetransmutation.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class CustomBlock extends Block implements ICustomBlock
{
    public CustomBlock(Material materialIn)
    {
        super(materialIn);
        this.setUnlocalizedName(this.getName());
    }
}
