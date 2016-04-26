package com.rubik.game.smy.npc;

import com.rubik.game.smy.world.Tile;
import com.rubik.game.smy.world.World;
import com.rubik.game.smy.npc.ai.CreatureAI;

import java.awt.*;

/**
 * Created by oleksandrve on 06.07.2015.
 */
public class Creature {
    private World world;

    public int x;
    public int y;
    public int z;

    private char glyph;
    private Color color;

    private CreatureAI ai;

    private int maxHp;
    private int hp;

    private int attackValue;
    private int defenceValue;

    public Creature(World world, char glyph, Color color, int maxHp, int attack, int defence) {
        this.world = world;
        this.glyph = glyph;
        this.color = color;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attackValue = attack;
        this.defenceValue = defence;
    }

    public int maxHp() {
        return maxHp;
    }

    public int hp() {
        return hp;
    }

    public int attackValue() {
        return attackValue;
    }

    public int defenceValue() {
        return defenceValue;
    }


    public void setCreatureAI(CreatureAI ai) {
        this.ai = ai;
    }

    public char glyph() {
        return glyph;
    }

    public Color color() {
        return color;
    }

    public void dig(int x, int y, int z) {
        world.dig(x, y, z);
    }

    public void moveBy(int dx, int dy, int dz) {
        Tile tile = world.tile(dx + x, dy + y, dz + z);

        if (dz == -1) {
            if (tile == Tile.STAIRS_DOWN) {
                doAction("walk up the stairs to level %d", z + dz + 1);
            } else {
                doAction("try to go up but are stopped by the cave ceiling");
                return;
            }
        } else if (dz == 1) {
            if (tile == Tile.STAIRS_UP) {
                doAction("walk down the stairs to level %d", z + dz + 1);
            } else {
                doAction("try to go down but are stopped by the cave floor");
                return;
            }
        }

        Creature other = world.creature(x + dx, y + dy, z + dz);

        if (other == null)
            ai.onEnter(x + dx, y + dy, z + dz, tile);
        else
            attack(other);
    }

    private void attack(Creature other) {
        int amount = Math.max(0, attackValue() - other.defenceValue());

        amount = (int) (Math.random() * amount) + 1;
        notify("You attack the '%s' for %d damage.", other.glyph, amount);
        other.notify("The '%s' attacks you for %d damage.", glyph, amount);

        other.modifyHP(-amount);
    }

    private void modifyHP(int amount) {
        hp += amount;

        if (hp < 1) {
            world.remove(this);
        }
    }

    public void notify(String message, Object... params) {
        ai.onNotify(String.format(message, params));
    }

    public void doAction(String message, Object... params) {
        int radius = 9;
        for (int dx = -radius; dx < radius + 1; dx++) {
            for (int dy = -radius; dy < radius; dy++) {
                if (dx * dx + dy * dy > radius * radius)
                    continue;

                Creature other = world.creature(x + dx, y + dy, z);

                if (other == null)
                    continue;

                if (other == this)
                    other.notify("You " + message + ".", params);
                else
                    other.notify(String.format("The '%s' %s.", glyph, message), params);
            }
        }
    }

    public void update() {
        ai.onUpdate();
    }

    public boolean canEnter(int wx, int wy, int wz) {
        return world.tile(wx, wy, wz).isGround() && world.creature(wx, wy, wz) == null;
    }
}
