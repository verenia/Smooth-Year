package com.rubik.game.smy.npc;

import asciiPanel.AsciiPanel;
import com.rubik.game.smy.world.World;
import com.rubik.game.smy.npc.ai.FungusAI;
import com.rubik.game.smy.npc.ai.PlayerAI;

import java.util.List;

/**
 * Created by oleksandrve on 06.07.2015.
 */
public class CreatureFactory {
    private World world;

    public CreatureFactory(World world){
        this.world = world;
    }

    public Creature newPlayer(List<String> messages){
        Creature player = new Creature(world, '@', AsciiPanel.brightWhite, 100, 20, 5);
        world.addAtEmptyLocation(player, 1);
        new PlayerAI(player, messages);
        return player;
    }

    public Creature newFungus(int depth){
        Creature fungus = new Creature(world, 'f', AsciiPanel.green, 10, 0, 0);
        world.addAtEmptyLocation(fungus, depth);
        new FungusAI(fungus, this);
        return fungus;
    }
}
