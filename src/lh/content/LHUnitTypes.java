package lh.content;

import arc.graphics.Color;
import lh.graphics.LHPal;
import mindustry.ai.types.BuilderAI;
import mindustry.content.*;
import mindustry.entities.abilities.SuppressionFieldAbility;
import mindustry.entities.bullet.*;
import mindustry.entities.part.ShapePart;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.*;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.type.ammo.ItemAmmoType;
import mindustry.type.unit.ErekirUnitType;
import mindustry.world.meta.BlockFlag;

import static mindustry.Vars.tilesize;
import static mindustry.content.Items.*;

public class LHUnitTypes {
    public static UnitType
            //core
            observer,
            //air
            trap, seal, cage, prison, chamber,
            spark, flick, ignition, frame, burndown,
            bit, byteU, kilobyte, megabyte, gigabyte,
            //mechs
            arrive, leave, again, comeback, reverse,
            //tonks
            halo;
    public static void load(){
        observer = new ErekirUnitType("observer"){{
            coreUnitDock = true;
            controller = u -> new BuilderAI(true, 350);
            isEnemy = false;
            constructor = UnitEntity::create;

            range = 65f;
            faceTarget = true;
            targetPriority = -2;
            lowAltitude = false;
            mineWalls = true;
            mineFloor = true;
            mineHardnessScaling = false;
            flying = true;
            mineSpeed = 10f;
            mineTier = 3;
            buildSpeed = 1f;
            drag = 0.08f;
            speed = 6f;
            rotateSpeed = 8f;
            accel = 0.08f;
            itemCapacity = 80;
            health = 1000f;
            armor = 5f;
            hitSize = 12f;
            trailLength = 7;
            buildBeamOffset = 6f;
            payloadCapacity = 2f * 2f * tilesize * tilesize;
            pickupUnits = false;
            vulnerableWithPayloads = false;

            fogRadius = 5f;
            targetable = false;
            hittable = false;

            engineOffset = 6.6f;
            engineSize = 3.4f;

            weapons.add(new Weapon("stun-beam-weapon"){{
                top = false;
                shake = 2f;
                shootY = 0f;
                x = 0f;
                reload = 55f;
                recoil = 4f;
                shootSound = Sounds.laser;

                bullet = new LaserBulletType(){{
                    damage = 0.1f;
                    recoil = 1f;
                    sideAngle = 20f;
                    sideWidth = 1f;
                    sideLength = 40f;
                    length = 60f;
                    status = LHStatuses.disabled;
                    statusDuration = 1 * 60f;
                    pierce = true;
                    pierceCap = 1;
                    colors = new Color[]{LHPal.coreLight.cpy().a(0.4f), LHPal.coreLight, Color.white};
                }};
            }});
        }};
        trap = new ErekirUnitType("trap"){{
            speed = 2.15f;
            accel = 0.09f;
            drag = 0.055f;
            flying = true;
            health = 240;
            armor = 2;
            engineOffset = 3.95f;
            targetFlags = new BlockFlag[]{BlockFlag.generator, null};
            hitSize = 11;
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
            ammoType = new ItemAmmoType(coal);

            weapons.add(new Weapon(){{
                y = 3f;
                x = 0f;
                mirror = false;
                reload = 40f;
                shootCone = 30;
                ejectEffect = Fx.none;
                shootSound = Sounds.tractorbeam;
                continuous = alwaysContinuous = true;
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
        cage = new ErekirUnitType("cage"){{
            speed = 1.54f;
            accel = 0.12f;
            drag = 0.085f;
            flying = true;
            health = 1630;
            armor = 7;
            engineOffset = 7.3f;
            engineSize = 4;
            targetFlags = new BlockFlag[]{BlockFlag.storage, null};
            hitSize = 19;
            itemCapacity = 30;
            constructor = UnitEntity::create;
            ammoType = new ItemAmmoType(oxide);

            weapons.add(new Weapon(){{
                    y = -1f;
                    x = 0f;
                    mirror = false;
                    reload = 90f;
                    shootCone = 40;
                    ejectEffect = Fx.casing1;
                    shootSound = Sounds.pulseBlast;
                    bullet = new BasicBulletType(5f, 65) {{
                        width = 10f;
                        height = 15f;
                        recoil = 3;
                        lifetime = 19f;
                        shootEffect = Fx.shootSmall;
                        smokeEffect = Fx.shootSmallSmoke;
                        frontColor = trailColor = Pal.sapBullet;
                        backColor = Pal.sapBulletBack;
                        trailLength = 8;
                        trailWidth = 3f;
                        fragBullets = 5;
                        fragBullet = intervalBullet = new LightningBulletType() {{
                            damage = 15;
                            collidesAir = false;
                            ammoMultiplier = 1f;
                            lightningColor = Pal.sapBullet;
                            lightningLength = 4;
                            lightningLengthRand = 2;

                            //for visual stats only.
                            buildingDamageMultiplier = 0.7f;

                            lightningType = new BulletType(0.0001f, 0f) {{
                                lifetime = Fx.lightning.lifetime;
                                hitEffect = Fx.hitLancer;
                                despawnEffect = Fx.none;
                                status = StatusEffects.shocked;
                                statusDuration = 10f;
                                hittable = false;
                                lightColor = Color.white;
                                buildingDamageMultiplier = 0.7f;
                            }};
                        }};
                        intervalBullets = 3;
                        bulletInterval = 5f;
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
        bit = new UnitType("bit"){{
            speed = 3f;
            accel = 0.07f;
            drag = 0.035f;
            flying = true;
            health = 190;
            armor = 1;
            engineOffset = 3.45f;
            targetFlags = new BlockFlag[]{BlockFlag.turret, null};
            hitSize = 11;
            itemCapacity = 0;
            constructor = UnitEntity::create;

            weapons.add(new Weapon(){{
                y = 3f;
                x = 0f;
                mirror = false;
                reload = 110f;
                shootCone = 30;
                ejectEffect = Fx.none;
                parts.add(new ShapePart(){{
                    circle = true;
                    radius = stroke = 2.5f;
                    radiusTo = 0;
                    color = LHPal.regardLight;
                    colorTo = Color.valueOf("00000000");
                    progress = PartProgress.reload;
                }});
                bullet = new EmpBulletType(){{
                    damage = 50;
                    radius = 40;
                    timeIncrease = 1.5f;
                    timeDuration = 375;
                    recoil = 4;
                    knockback = 4.5f;
                    speed = 5;
                    width = 8f;
                    height = 8f;
                    sprite = "large-orb";
                    lifetime = 8f;
                    shootEffect = Fx.blastExplosion;
                    smokeEffect = Fx.shootBigSmoke;
                    frontColor = trailColor = hitColor = LHPal.regardLight;
                    backColor = LHPal.regardMid;
                    trailLength = 8;
                    trailWidth = 1.8f;
                    despawnEffect = hitEffect = Fx.hitSquaresColor;
                }};
                shootSound = Sounds.shockBlast;
            }});
        }};
        arrive = new ErekirUnitType("arrive"){{
            speed = 0.38f;
            hitSize = 9f;
            health = 380;
            armor = 2;
            mechLegColor = Pal.darkOutline;
            constructor = MechUnit::create;
            ammoType = new ItemAmmoType(graphite);
            range = 35;
            weapons.add(new Weapon("lh-arrive-weapon"){{
                reload = 25f;
                x = 5f;
                y = 0f;
                shootY = 1;
                top = false;
                ejectEffect = Fx.casing1;
                bullet = new ShrapnelBulletType(){{
                    length = 9;
                    width = 6f;
                    serrations = 0;
                    damage = 15f;
                    toColor = LHPal.plexLightlish;
                    shootEffect = smokeEffect = Fx.shootSmallColor;
                }};
            }});
        }};

        reverse = new ErekirUnitType("reverse"){{
            speed = 0.65f;
            hitSize = 26f;
            mechLegColor = Pal.darkOutline;
            rotateSpeed = 2f;
            health = 19750;
            armor = 25f;
            mechStepParticles = true;
            stepShake = 0.5f;
            drownTimeMultiplier = 6f;
            mechFrontSway = 1.9f;
            mechSideSway = 0.6f;
            ammoType = new ItemAmmoType(tungsten);
            constructor = MechUnit::create;

            weapons.add(
                    new Weapon("lh-reverse-weapon"){{
                        top = false;
                        y = 0f;
                        x = 24.25f;
                        shootCone = 55;
                        rotate = true;
                        rotateSpeed = 1.25f;
                        rotationLimit = 45;
                        shootY = 9f;
                        reload = 20f;
                        recoil = 3f;
                        shake = 2f;
                        ejectEffect = Fx.none;
                        shootSound = Sounds.none;
                        loopSound = Sounds.torch;
                        continuous = true;
                        parts.add(new ShapePart(){{
                            sides = 4;
                            radius = stroke = 0;
                            radiusTo = 6f;
                            hollow = true;
                            strokeTo = 2.35f;
                            y = -7;
                            color = LHPal.plexLight;
                            rotateSpeed = 3;
                        }});

                        bullet = new ContinuousFlameBulletType(){{
                            damage = 135f;
                            length = 160;
                            lifetime = 18f;
                            knockback = 3f;
                            pierceCap = 7;
                            buildingDamageMultiplier = 1.2f;
                            flareColor = hitColor = LHPal.plexLightlish;

                            colors = new Color[]{LHPal.plexMid, LHPal.plexLight, LHPal.plexLightlish, LHPal.plexLightlish, Color.white};
                        }};
                    }}

            );
        }};
    }
}

