package com.rubik.game.smy.npc.ai;

import com.rubik.game.smy.npc.Creature;
import com.rubik.game.smy.npc.CreatureFactory;

/**
 * Created by oleksandrve on 10.07.2015.
 */
public class FungusAI extends CreatureAI {
    private CreatureFactory factory;
    private int spreadCount;

    public FungusAI(Creature creature, CreatureFactory factory) {
        super(creature);
        this.factory = factory;
    }

    @Override
    public void onUpdate() {
        if(spreadCount < 5 && Math.random() < 0.02){
            creature.doAction("spawns");
            spread();
        }
    }

    private void spread(){
        int x = creature.x + (int)(Math.random() * 11) - 5;
        int y = creature.y + (int)(Math.random() * 11) - 5;
        int z = creature.z;

        if(!creature.canEnter(x, y, z))
            return;

        Creature child = factory.newFungus(creature.z);
        child.x = x;
        child.y = y;
        spreadCount++;
    }
}
