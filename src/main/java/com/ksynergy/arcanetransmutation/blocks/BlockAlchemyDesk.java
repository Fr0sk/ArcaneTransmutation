package com.ksynergy.arcanetransmutation.blocks;

import com.ksynergy.arcanetransmutation.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Random;

public class BlockAlchemyDesk extends CustomBlock implements ICustomBlockDirectional
{
    public static final PropertyBool MAIN = PropertyBool.create("main");
    public static final PropertyEnum PART = PropertyEnum.create("part", BlockAlchemyDesk.EnumPartType.class);

    private final String name = "alchemyDesk";

    public BlockAlchemyDesk(Material materialIn)
    {
        super(materialIn);
        this.setDefaultState(this.blockState.getBaseState()
                .withProperty(PART, EnumPartType.LEFT)
                .withProperty(MAIN, Boolean.valueOf(true)));

        ModelLoader.setCustomStateMapper(this, new StateMap.Builder().addPropertiesToIgnore(MAIN).build());
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public void addBlockRecipes()
    {
        ItemStack alchemyTableStack = new ItemStack(ModBlocks.alchemyDesk, 1);
        Object[] recipe = new Object[]{
                "CBT",
                "SWS",
                "S S",
                'C' , Blocks.chest,
                'B', Items.book,
                'T', Blocks.crafting_table,
                'S', Items.stick,
                'W', Blocks.wooden_slab
        };

        GameRegistry.addRecipe(alchemyTableStack, recipe);
    }

    @Override
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side)
    {
        EnumFacing facing = Minecraft.getMinecraft().thePlayer.getHorizontalFacing();

        ArrayList<BlockPos> blockPos = new ArrayList<BlockPos>();

        // Bottom Layer
        blockPos.add(pos);
        blockPos.add(pos.offset(facing));
        blockPos.add(pos.offset(facing, 2));

        // Top Layer
        pos = pos.offset(EnumFacing.UP);
        blockPos.add(pos);
        blockPos.add(pos.offset(facing, 2));

        for (BlockPos bp : blockPos)
        {
            Block block = worldIn.getBlockState(bp).getBlock();
            if(!block.isReplaceable(worldIn, bp))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        // Place Center Block
        worldIn.setBlockState(pos.offset(placer.getHorizontalFacing()), ModBlocks.alchemyDesk.getDefaultState()
                .withProperty(MAIN, Boolean.valueOf(false))
                .withProperty(FACING, placer.getHorizontalFacing())
                .withProperty(PART, EnumPartType.CENTER));

        // Place Right Block
        worldIn.setBlockState(pos.offset(placer.getHorizontalFacing(), 2), ModBlocks.alchemyDesk.getDefaultState()
                .withProperty(MAIN, Boolean.valueOf(false))
                .withProperty(FACING, placer.getHorizontalFacing())
                .withProperty(PART, EnumPartType.RIGHT));

        // Place Chest block
        worldIn.setBlockState(pos.offset(EnumFacing.UP), ModBlocks.alchemyDesk.getDefaultState()
                .withProperty(MAIN, Boolean.valueOf(false))
                .withProperty(FACING, placer.getHorizontalFacing())
                .withProperty(PART, EnumPartType.CHEST));

        // Place Crafting Block
        worldIn.setBlockState(pos.offset(EnumFacing.UP).offset(placer.getHorizontalFacing(), 2), ModBlocks.alchemyDesk.getDefaultState()
                .withProperty(MAIN, Boolean.valueOf(false))
                .withProperty(FACING, placer.getHorizontalFacing())
                .withProperty(PART, EnumPartType.CRAFTING_TABLE));

        return ModBlocks.alchemyDesk.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        BlockPos mainPos = pos;
        EnumFacing facing = (EnumFacing)state.getValue(FACING);

        if (!(Boolean)state.getValue(MAIN))
        {
            ArrayList<BlockPos> translations = new ArrayList<BlockPos>();
            translations.add(mainPos.offset(facing.getOpposite()));
            translations.add(mainPos.offset(facing.getOpposite(), 2));
            translations.add(mainPos.offset(EnumFacing.DOWN));
            translations.add(mainPos.offset(EnumFacing.DOWN).offset(facing.getOpposite(), 2));

            for (BlockPos blockPos : translations)
            {
                if (worldIn.getBlockState(blockPos).getBlock() == this && (Boolean) worldIn.getBlockState(blockPos).getValue(MAIN))
                {
                    mainPos = blockPos;
                    break;
                }
            }
        }

        ArrayList<BlockPos> blocksPos = new ArrayList<BlockPos>();
        // Bot Layer
        blocksPos.add(mainPos);
        blocksPos.add(mainPos.offset(facing));
        blocksPos.add(mainPos.offset(facing, 2));

        // Top Layer
        mainPos = mainPos.offset(EnumFacing.UP);
        blocksPos.add(mainPos);
        blocksPos.add(mainPos.offset(facing, 2));

        for (BlockPos blockPos : blocksPos)
        {
            worldIn.setBlockToAir(blockPos);
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModBlocks.alchemyDesk);
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getHorizontal(meta);
        boolean isMain = (meta & 8) > 0;
        return this.getDefaultState().withProperty(MAIN, isMain).withProperty(FACING, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        byte b0 = 0;
        int i = b0 | ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();

        if ((Boolean)state.getValue(MAIN))
        {
            i |= 8;
        }
        return i;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        EnumFacing facing = (EnumFacing)state.getValue(FACING);

        if ((Boolean) state.getValue(MAIN))
            return state.withProperty(PART, EnumPartType.LEFT);

        BlockPos posCenterBlock = pos.offset(facing.getOpposite());
        if (worldIn.getBlockState(posCenterBlock).getBlock() == this && (Boolean)worldIn.getBlockState(posCenterBlock).getValue(MAIN))
            return state.withProperty(PART, EnumPartType.CENTER);

        BlockPos posRightBlock = pos.offset(facing.getOpposite(), 2);
        if (worldIn.getBlockState(posRightBlock).getBlock() == this && (Boolean)worldIn.getBlockState(posRightBlock).getValue(MAIN))
            return state.withProperty(PART, EnumPartType.RIGHT);

        BlockPos posChestBlock = pos.offset(EnumFacing.DOWN);
        if (worldIn.getBlockState(posChestBlock).getBlock() == this && (Boolean)worldIn.getBlockState(posChestBlock).getValue(MAIN))
            return state.withProperty(PART, EnumPartType.CHEST);

        BlockPos posCraftingBlock = pos.offset(EnumFacing.DOWN).offset(facing.getOpposite(), 2);
        if (worldIn.getBlockState(posCraftingBlock).getBlock() == this && (Boolean)worldIn.getBlockState(posCraftingBlock).getValue(MAIN))
            return state.withProperty(PART, EnumPartType.CRAFTING_TABLE);

        return state;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[]{FACING, PART, MAIN});
    }

    public enum EnumPartType implements IStringSerializable
    {
        LEFT("left"),
        CENTER("center"),
        RIGHT("right"),
        CHEST("chest"),
        CRAFTING_TABLE("crafting_table");

        private final String name;

        private EnumPartType(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return this.name;
        }

        public String getName()
        {
            return this.name;
        }
    }
}
