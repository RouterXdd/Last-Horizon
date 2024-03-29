package lh.classes.entities.weapons;

import arc.Core;
import arc.audio.Sound;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.geom.Vec2;
import arc.util.Nullable;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.entities.Units;
import mindustry.entities.units.WeaponMount;
import mindustry.game.Team;
import mindustry.gen.Groups;
import mindustry.gen.Sounds;
import mindustry.gen.Teamc;
import mindustry.gen.Unit;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.type.StatusEffect;
import mindustry.type.Weapon;
import mindustry.world.blocks.defense.turrets.TractorBeamTurret;

import static mindustry.Vars.*;

public class TractorBeamWeapon extends Weapon {
    public @Nullable Unit target;
    public float strength;
    public float shootCone = 6f;
    public float shootLength = 5f;
    public float laserWidth = 0.6f;
    public float force = 0.3f;
    public float scaledForce = 0.3f;
    public float damage = 0f;
    public boolean targetAir = true, targetGround = false;
    public Color laserColor = Color.white;
    public Effect beamEffect = Fx.pointBeam;
    public StatusEffect status = StatusEffects.none;
    public float statusDuration = 300;

    public Sound shootSound = Sounds.tractorbeam;
    public float shootSoundVolume = 0.9f;
    public String sprite = "point-laser";
    public TextureRegion laser, laserEnd;
    public float oscScl = 2f, oscMag = 0.3f;
    public TractorBeamWeapon(String name){
        super(name);
    }

    public TractorBeamWeapon(){
    }

    {
        predictTarget = false;
        autoTarget = true;
        controllable = false;
        rotate = true;
        useAmmo = false;
        useAttackRange = false;
        targetInterval = 10f;
    }
    @Override
    public void load(){
        super.load();

        laser = Core.atlas.find(sprite);
        laserEnd = Core.atlas.find(sprite + "-end");
    }
    @Override
    protected Teamc findTarget(Unit unit, float x, float y, float range, boolean air, boolean ground){
        return Units.closestEnemy(unit.team, x, y, range + Math.abs(shootY), u -> u.checkTarget(air, ground));
    }
    @Override
    protected boolean checkTarget(Unit unit, Teamc target, float x, float y, float range){
        return !(target.within(unit, range) && target.team() != unit.team && target instanceof Unit enemy);
    }
    @Override
    protected void shoot(Unit unit, WeaponMount mount, float shootX, float shootY, float rotation) {
        if (!(mount.target instanceof Unit target)) return;
        if (!headless) {
            control.sound.loop(shootSound, unit, shootSoundVolume);
        }

        strength = Mathf.lerpDelta(strength, 1f, 0.1f);
        if (damage > 0) {
            target.damageContinuous(damage * state.rules.unitDamage(unit.team) * unit.damageMultiplier);
        }

        if (status != StatusEffects.none) {
            target.apply(status, statusDuration);
        }
        //beamEffect.at(shootX, shootY, rotation, laserColor, new Vec2().set(target));
        target.impulseNet(Tmp.v1.set(unit).sub(target).limit((force + (1f - target.dst(target) / range()) * scaledForce * unit.reloadMultiplier) * Time.delta));

    }
    @Override
    public void draw(Unit unit, WeaponMount mount){
        super.draw(unit, mount);
        Draw.color(laserColor);
        float
                weaponRotation = unit.rotation - 90,
                wx = unit.x + Angles.trnsx(weaponRotation, x, y),
                wy = unit.y + Angles.trnsy(weaponRotation, x, y),
                z = Draw.z();
        Draw.z(z + layerOffset + 1f);
        if (mount.target != null && unit.canShoot()) {
            Drawf.laser(laser, laserEnd, wx, wy, mount.target.x(), mount.target.y(), 1f - oscMag + Mathf.absin(Time.time, oscScl, oscMag));
        }
        Draw.reset();
    }
}
