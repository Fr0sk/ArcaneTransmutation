package com.ksynergy.arcanetransmutation.blocks;

import net.minecraft.block.Block;

public interface ICustomBlock
{
    String getName();
    Block getBlock();
    void addBlockRecipes();
}
