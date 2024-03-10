package lh.classes.types;

import arc.math.Mathf;
import mindustry.entities.Units;
import mindustry.gen.Unit;

public class LHUnitSorts {
    public static Units.Sortf

    slowest = (u, x, y) -> -u.speed() + Mathf.dst2(u.x, u.y, x, y) / 6400f,
    fastest = (u, x, y) -> u.speed() + Mathf.dst2(u.x, u.y, x, y) / 6400f;
}
