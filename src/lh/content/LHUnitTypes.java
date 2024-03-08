package lh.content;

import lh.graphics.LHPal;
import mindustry.content.*;
import mindustry.entities.abilities.SuppressionFieldAbility;
import mindustry.entities.bullet.*;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.*;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.type.ammo.ItemAmmoType;
import mindustry.type.unit.ErekirUnitType;
import mindustry.world.meta.BlockFlag;

public class LHUnitTypes {
    public static UnitType
            //air
            trap, seal, cage, prison, chamber,
            spark, flick, ignition, frame, burndown,
            bit, byteU, kilobyte, megabyte, gigabyte,
            //tonks
            halo;
    public static void load(){
        trap = new ErekirUnitType("trap"){{
            speed = 2.15f;
            accel = 0.09f;
            drag = 0.055f;
            flying = true;
            health = 240;
            armor = 2;
            engineOffset = 3.85f;
            targetFlags = new BlockFlag[]{BlockFlag.generator, null};
            hitSize = 9;
            itemCapacity = 15;
            constructor = UnitEntity::create;

            weapons.add(new Weapon(){{
                y = 1f;
                x = 0f;
                mirror = false;
                reload = 35f;
                shootCone = 25;
                ejectEffect = Fx.casing1;
                bullet = new BasicBulletType(3f, 10){{
                    width = 7.5f;
                    height = 10f;
                    lifetime = 23f;
                    shootEffect = Fx.shootSmall;
                    smokeEffect = Fx.shootSmallSmoke;
                    frontColor = trailColor = Pal.sapBullet;
                    backColor = Pal.sapBulletBack;
                    trailLength = 6;
                    trailWidth = 2;
                    fragRandomSpread = 0;
                    fragSpread = 45;
                    fragBullets = 3;
                    fragBullet = new BasicBulletType(4f, 6){{
                        width = 5.5f;
                        height = 8f;
                        lifetime = 10f;
                        shootEffect = Fx.shootSmall;
                        smokeEffect = Fx.shootSmallSmoke;
                        frontColor = trailColor = Pal.sapBullet;
                        backColor = Pal.sapBulletBack;
                        trailLength = 3;
                        trailWidth = 1.2f;
                    }};
                }};
                shootSound = Sounds.pew;
            }});
        }};
        seal = new ErekirUnitType("seal"){{
            speed = 1.82f;
            accel = 0.1f;
            armor = 5;
            drag = 0.072f;
            flying = true;
            health = 780;
            engineOffset = 5.7f;
            engineSize = 3;
            targetFlags = new BlockFlag[]{BlockFlag.drill, null};
            hitSize = 13;
            itemCapacity = 15;
            constructor = UnitEntity::create;

            weapons.add(new Weapon(){{
                y = 3f;
                x = 0f;
                mirror = false;
                reload = 40f;
                shootCone = 30;
                ejectEffect = Fx.none;
                shootSound = Sounds.tractorbeam;
                continuous = true;
                bullet = new ContinuousFlameBulletType(){{
                    damage = 25f;
                    length = 90;
                    knockback = 0.6f;
                    lifetime = 80f;
                    pierceCap = 2;
                    lengthWidthPans = new float[] {
                            1.3f, 1.3f, 0.3f,
                            1f, 1f, 0.25f,
                            0.7f, 0.7f, 0.15f,
                            0.45f, 0.45f, 0.1f,
                            0.1f, 0.1f, 0.05f,
                    };
                }};
            }});
        }};
        spark = new UnitType("spark"){{
            speed = 2.45f;
            accel = 0.082f;
            drag = 0.05f;
            flying = true;
            health = 90;
            engineOffset = 3.2f;
            targetFlags = new BlockFlag[]{BlockFlag.battery, null};
            hitSize = 9;
            itemCapacity = 10;
            constructor = UnitEntity::create;
            abilities.add(new SuppressionFieldAbility(){{
                orbRadius = 1.1f;
                particleSize = 0.4f;
                y = 0f;
                color = LHPal.plexLight;
                particleColor = LHPal.plexMid;
                particles = 4;
                display = active = false;
            }});

            weapons.add(new Weapon(){{
                y = 0f;
                x = 0f;
                mirror = false;
                reload = 16.5f;
                shootCone = 25;
                ejectEffect = Fx.casing1;
                bullet = new BasicBulletType(4f, 5){{
                    width = 6f;
                    height = 9f;
                    lifetime = 16f;
                    weaveScale = 2.5f;
                    weaveMag = 5.5f;
                    shootEffect = Fx.shootSmall;
                    smokeEffect = Fx.shootSmallSmoke;
                    frontColor = trailColor = LHPal.plexLight;
                    backColor = LHPal.plexMid;
                    trailLength = 8;
                    trailWidth = 1.8f;
                }};
                shootSound = Sounds.pew;
            }});
        }};
        flick = new UnitType("flick"){{
            health = 430;
            speed = 1.18f;
            accel = 0.08f;
            drag = 0.019f;
            flying = true;
            hitSize = 13f;
            targetAir = false;
            engineOffset = 6.85f;
            range = 40f;
            armor = 4f;
            itemCapacity = 20;
            targetFlags = new BlockFlag[]{BlockFlag.factory, null};
            ammoType = new ItemAmmoType(Items.graphite);
            constructor = UnitEntity::create;
            abilities.add(new SuppressionFieldAbility(){{
                orbRadius = 2.4f;
                particleSize = 1;
                y = 0f;
                color = LHPal.plexLight;
                particleColor = LHPal.plexMid;
                particles = 6;
                display = active = false;
            }});

            weapons.add(new Weapon(){{
                x = 0f;
                shootY = 0f;
                reload = 20f;
                shootCone = 80f;
                ejectEffect = Fx.none;
                shoot = new ShootSpread(3, 15);
                inaccuracy = 0f;
                ignoreRotation = true;
                shootSound = Sounds.none;
                bullet = new BombBulletType(8f, 20f){{
                    speed = 3f;
                    width = 10f;
                    height = 14f;
                    frontColor = LHPal.plexLight;
                    backColor = LHPal.plexMid;
                    hitEffect = Fx.flakExplosion;
                    shootEffect = Fx.none;
                    smokeEffect = Fx.none;

                    status = StatusEffects.blasted;
                    statusDuration = 60f;
                }};
            }});
        }};
    }
}

