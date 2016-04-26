package com.rubik.game.smy.npc.ai;

import com.rubik.game.smy.world.Tile;
import com.rubik.game.smy.npc.Creature;

import java.util.List;

/**
 * Created by oleksandrve on 06.07.2015.
 */
public class PlayerAI extends CreatureAI{

    private List<String> messages;

    public PlayerAI(Creature creature, List<String> messages){
        super(creature);
        this.messages = messages;
    }

    @Override
    public void onEnter(int x, int y, int z, Tile tile) {
        if(tile.isGround()){
            creature.x = x;
            creature.y = y;
            creature.z = z;
        } else  if(tile.isDiggable()){
            creature.dig(x, y, z);
        }
    }

    @Override
    public void onNotify(String message) {
        messages.add(message);
    }
}
