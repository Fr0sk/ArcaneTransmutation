package com.ksynergy.arcanetransmutation.blocks;

import com.ksynergy.arcanetransmutation.ArcaneTransmutation;
import com.ksynergy.arcanetransmutation.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.Random;

public class BlockAlchemicalContainer extends CustomBlockContainer
{
    private static final String name = "alchemicalContainer";

    public BlockAlchemicalContainer(Material materialIn)
    {
        super(materialIn);
        this.setUnlocalizedName(this.getName());
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public Block getBlock()
    {
        return this;
    }

    @Override
    public void addBlockRecipes()
    {
        // TODO: Add Alchemical Container Block recipe!
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModBlocks.alchemicalContainer.getBlock());
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote)
        {
            player.openGui(ArcaneTransmutation.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return null;
    }
}
