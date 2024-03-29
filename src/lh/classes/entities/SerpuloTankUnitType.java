package lh.classes.entities;

import mindustry.type.*;
import mindustry.world.meta.*;

public class SerpuloTankUnitType extends UnitType {

    public SerpuloTankUnitType(String name){
        super(name);

        squareShape = true;
        omniMovement = false;
        rotateMoveFirst = true;
        rotateSpeed = 1.3f;
        envDisabled = Env.none;
        speed = 0.8f;
    }

}
