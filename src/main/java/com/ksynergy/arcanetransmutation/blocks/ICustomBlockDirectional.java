package com.ksynergy.arcanetransmutation.blocks;

import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.util.EnumFacing;

public interface ICustomBlockDirectional
{
    PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
}
