package com.rubik.game.smy.npc.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by oleksandrve on 13.07.2015.
 */
public class Point {
    public int x;
    public int y;
    public int z;

    public Point(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + x;
        result = prime * result + y;
        result = prime * result + z;

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Point))
            return false;
        Point other = (Point) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        if (z != other.z)
            return false;
        return true;
    }

    public List<Point> neighbours(){
        List<Point> points = new ArrayList<Point>(8);

        for (int dx = -1; dx < 2; dx++) {
            for (int dy = -1; dy < 2; dy++) {
                if(dx == 0 && dy ==0)
                    continue;

                points.add(new Point(x + dx, y + dy, z));

            }
        }
        Collections.shuffle(points);
        return points;
    }
}
