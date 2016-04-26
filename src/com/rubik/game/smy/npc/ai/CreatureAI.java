package com.rubik.game.smy.npc.ai;

import com.rubik.game.smy.world.Tile;
import com.rubik.game.smy.npc.Creature;

/**
 * Created by oleksandrve on 06.07.2015.
 */
public class CreatureAI {
    Creature creature;

    public CreatureAI(Creature creature){
        this.creature = creature;
        this.creature.setCreatureAI(this);
    }

    public void onEnter(int x, int y, int z, Tile tile){

    }

    public void onUpdate() {

    }

    public void onNotify(String format) {

    }
}
