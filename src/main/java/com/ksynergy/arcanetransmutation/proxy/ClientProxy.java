package com.ksynergy.arcanetransmutation.proxy;

import com.ksynergy.arcanetransmutation.init.ModBlocks;
import com.ksynergy.arcanetransmutation.init.ModItems;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenders()
    {
        ModBlocks.registerRenders();
        ModItems.registerRenders();
    }
}
