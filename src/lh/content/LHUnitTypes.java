package lh.content;

import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.geom.Rect;
import lh.classes.entities.SerpuloTankUnitType;
import lh.classes.entities.abilities.StaticFieldAbility;
import lh.classes.entities.weapons.TractorBeamWeapon;
import lh.graphics.LHPal;
import mindustry.ai.types.BuilderAI;
import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.entities.abilities.SuppressionFieldAbility;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.part.ShapePart;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.ItemAmmoType;
import mindustry.type.unit.ErekirUnitType;
import mindustry.world.meta.BlockFlag;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.stroke;
import static mindustry.Vars.tilesize;
import static mindustry.content.Items.*;
import static lh.content.LHItems.*;

public class LHUnitTypes {
    public static UnitType
            //core
            observer,
            //air
            trap, seal, cage, prison, chamber,
            spark, flick, ignition, frame, burndown,
            bit, byteU, kilobyte, megabyte, gigabyte,
            //mechs
            arrive, leave, comeback, returnU, reverse,
            //spider
            action, result, fault, fate, torture,
            //tonks
            halo, savior, ascension, heaven, angel;
    public static void load(){
        observer = new ErekirUnitType("observer"){{
            coreUnitDock = true;
            controller = u -> new BuilderAI(true, 350);
            isEnemy = false;
            constructor = PayloadUnit::create;

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
                    ejectEffect = Fx.none;
                    shootSound = Sounds.pulseBlast;
                    bullet = new BasicBulletType(5f, 80) {{
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
                            damage = 20;
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
        prison = new ErekirUnitType("prison"){{
            speed = 1.12f;
            accel = 0.15f;
            drag = 0.1f;
            rotateSpeed = 3.8f;
            flying = true;
            health = 4985;
            armor = 10;
            engineOffset = 14.5f;
            engineSize = 6;
            targetFlags = new BlockFlag[]{BlockFlag.repair, BlockFlag.battery, null};
            hitSize = 26;
            itemCapacity = 55;
            constructor = UnitEntity::create;
            ammoType = new ItemAmmoType(alphaChip);

            weapons.add(new Weapon(){{
                y = -4.5f;
                x = 0f;
                mirror = false;
                reload = 110f;
                shootCone = 20;
                ejectEffect = Fx.none;
                shootSound = Sounds.pulseBlast;
                shoot.shots = 5;
                shoot.shotDelay = 4;
                bullet = new BasicBulletType(8f, 40) {{
                    width = 14f;
                    height = 8f;
                    sprite = "lh-sound-wave";
                    recoil = 0.5f;
                    lifetime = 13.75f;
                    shootEffect = Fx.shootSmall;
                    smokeEffect = Fx.shootSmallSmoke;
                    frontColor = trailColor = Pal.sapBullet;
                    backColor = Pal.sapBulletBack;
                    pierce = true;
                    trailLength = 4;
                    trailWidth = 1.5f;
                    fragBullets = 3;
                    fragBullet = new LightningBulletType() {{
                        damage = 20;
                        collidesAir = false;
                        ammoMultiplier = 1f;
                        lightningColor = Pal.sapBullet;
                        lightningLength = 3;
                        lightningLengthRand = 1;

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
                }};
            }});
        }};
        chamber = new ErekirUnitType("chamber"){{
            speed = 0.68f;
            accel = 0.15f;
            drag = 0.125f;
            rotateSpeed = 2.2f;
            flying = true;
            health = 18500;
            armor = 16;
            engineOffset = 16.75f;
            engineSize = 9;
            targetFlags = new BlockFlag[]{BlockFlag.factory, BlockFlag.drill, null};
            hitSize = 42;
            itemCapacity = 80;
            constructor = UnitEntity::create;
            ammoType = new ItemAmmoType(surgeAlloy);
            abilities.add(new StaticFieldAbility(22, 10 * 8, 20, 4){{

            }});

            weapons.add(new Weapon(){{
                y = 0f;
                x = 15.25f;
                mirror = true;
                alternate = false;
                reload = 160f;
                shootCone = 20;
                ejectEffect = Fx.none;
                shootSound = Sounds.pulseBlast;
                shoot.shots = 7;
                shoot.shotDelay = 4;
                bullet = new BasicBulletType(8f, 10) {{
                    width = 11f;
                    height = 6f;
                    sprite = "lh-sound-wave";
                    lifetime = 15f;
                    shootEffect = Fx.shootSmall;
                    smokeEffect = Fx.shootSmallSmoke;
                    frontColor = trailColor = Pal.sapBullet;
                    backColor = Pal.sapBulletBack;
                    pierce = true;
                    trailLength = 3;
                    trailWidth = 1.5f;
                    fragBullets = 2;
                    fragBullet = new LightningBulletType() {{
                        damage = 10;
                        collidesAir = false;
                        ammoMultiplier = 1f;
                        lightningColor = Pal.sapBullet;
                        lightningLength = 3;
                        lightningLengthRand = 1;

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
                }};
            }}, new Weapon(){{
                            y = 4.5f;
                            x = 0f;
                            mirror = false;
                            reload = 220f;
                            shootCone = 30;
                            ejectEffect = Fx.none;
                            shootSound = Sounds.plasmaboom;
                            bullet = new BasicBulletType(6f, 500) {{
                                width = 14f;
                                height = 8f;
                                sprite = "circle-bullet";
                                recoil = 4;
                                lifetime = 100f;
                                drag = 0.1f;
                                collides = false;
                                shootEffect = Fx.shootSmall;
                                smokeEffect = Fx.shootSmallSmoke;
                                frontColor = trailColor = Pal.sapBullet;
                                backColor = Pal.sapBulletBack;
                                pierce = true;
                                trailLength = 4;
                                trailWidth = 1.5f;
                                intervalBullet = new LightningBulletType() {{
                                    damage = 55;
                                    collidesAir = false;
                                    ammoMultiplier = 1f;
                                    lightningColor = Pal.sapBullet;
                                    lightningLength = 5;
                                    lightningLengthRand = 4;

                                    lightningType = new BulletType(0.0001f, 0f) {{
                                        lifetime = Fx.lightning.lifetime;
                                        hitEffect = Fx.hitLancer;
                                        despawnEffect = Fx.none;
                                        status = StatusEffects.shocked;
                                        statusDuration = 10f;
                                        hittable = false;
                                        lightColor = Color.white;
                                    }};
                                }};
                                intervalBullets = 5;
                                bulletInterval = 7f;
                            }};
                        }}
            );
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
        action = new ErekirUnitType("action"){{
            speed = 0.65f;
            drag = 0.2f;
            hitSize = 8f;
            rotateSpeed = 3f;
            targetAir = false;
            health = 470;

            legCount = 4;
            legLength = 7f;
            legForwardScl = 0.7f;
            legMoveSpace = 1.2f;
            hovering = true;
            armor = 3f;
            ammoType = new ItemAmmoType(silicon);
            constructor = LegsUnit::create;

            shadowElevation = 0.2f;
            groundLayer = Layer.legUnit - 1f;

            weapons.add(new Weapon("action-weapon"){{
                top = false;
                shootY = 0f;
                reload = 25f;
                ejectEffect = Fx.none;
                recoil = 0f;
                mirror = false;
                x = 0;
                y = 3f;
                shootSound = Sounds.lasershoot;

                bullet = new LaserBulletType(20){{
                    length = 65;
                    lifetime = 38f;
                    pierceCap = 2;
                    colors = new Color[]{LHPal.collapse.cpy().a(0.4f), LHPal.collapse, Color.white};
                    collidesAir = false;
                    shootEffect = new MultiEffect(Fx.shootSmallColor, new Effect(9, e -> {
                        color(Color.white, e.color, e.fin());
                        stroke(0.7f + e.fout());
                        Lines.square(e.x, e.y, e.fin() * 5f, e.rotation + 45f);

                        Drawf.light(e.x, e.y, 23f, e.color, e.fout() * 0.7f);
                    }));
                }};
            }});
        }};
        halo = new SerpuloTankUnitType("halo"){{
            hitSize = 14f;
            treadPullOffset = 0;
            speed = 1.3f;
            rotateSpeed = 4.6f;
            health = 410;
            armor = 5f;
            itemCapacity = 0;
            treadRects = new Rect[]{new Rect(18 - 36f, 5 - 36f, 11, 59)};
            constructor = TankUnit::create;

            weapons.add(new Weapon("lh-halo-weapon"){{
                layerOffset = 0.0001f;
                reload = 7f;
                shootY = 6.25f;
                recoil = 1f;
                rotate = true;
                rotateSpeed = 3.4f;
                mirror = false;
                x = 0f;
                y = 0f;
                heatColor = Color.valueOf("f9350f");
                cooldownTime = 8f;

                bullet = new BasicBulletType(5.5f, 6){{
                    sprite = "missile-large";
                    smokeEffect = Fx.shootSmallSmoke;
                    shootEffect = Fx.shootSmallColor;
                    width = 3f;
                    height = 6f;
                    lifetime = 32.72f;
                    hitSize = 4f;
                    hitColor = backColor = trailColor = Color.valueOf("feb380");
                    frontColor = Color.white;
                    trailWidth = 1f;
                    trailLength = 5;
                    despawnEffect = hitEffect = Fx.hitBulletColor;
                }};
            }});
        }};
        ascension = new SerpuloTankUnitType("ascension"){{
            hitSize = 21f;
            treadPullOffset = 2;
            speed = 0.82f;
            rotateSpeed = 3.2f;
            health = 2460;
            armor = 8f;
            itemCapacity = 0;
            treadRects = new Rect[]{new Rect(25 - 55f, 11 - 55f, 17, 84)};
            constructor = TankUnit::create;

            weapons.add(new TractorBeamWeapon("lh-ascension-parallax"){{
                layerOffset = 0.0001f;
                reload = 3f;
                shootY = 5.25f;
                recoil = 1f;
                rotate = true;
                rotateSpeed = 3.5f;
                shootCone = 15;
                mirror = false;
                targetGround = false;
                x = 0f;
                y = -5.75f;
                heatColor = Color.valueOf("f9350f");
                cooldownTime = 8f;
                damage = 2f;
                force = 6.5f;
            }}, new Weapon("lh-ascension-weapon"){{
                top = true;
                layerOffset = 0.0001f;
                shootSound = Sounds.flame;
                shootY = 5.25f;
                mirror = false;
                rotate = true;
                reload = 6.5f;
                recoil = 1f;
                x = 0;
                y = 6;
                ejectEffect = Fx.none;
                bullet = new BulletType(5f, 25f){{
                    ammoMultiplier = 3f;
                    hitSize = 7f;
                    lifetime = 9f;
                    pierce = true;
                    pierceBuilding = true;
                    pierceCap = 3;
                    statusDuration = 60f * 4;
                    shootEffect = Fx.shootSmallFlame;
                    hitEffect = Fx.hitFlameSmall;
                    despawnEffect = Fx.none;
                    status = StatusEffects.burning;
                    keepVelocity = false;
                    hittable = false;
                }};
            }});
        }};
    }
}

