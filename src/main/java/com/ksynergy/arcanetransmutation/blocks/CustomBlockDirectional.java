package com.ksynergy.arcanetransmutation.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public abstract class CustomBlockDirectional extends CustomBlock implements ICustomBlockDirectional
{
    public CustomBlockDirectional(Material materialIn)
    {
        super(materialIn);
    }

    public BlockState createBlockState()
    {
        IProperty[] properties = new IProperty[]{FACING};
        return new BlockState(this, properties);
    }

    public abstract IBlockState getStateFromMeta(int meta);
    public abstract int getMetaFromState(IBlockState state);
    public abstract IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos);
}
