package lh.content;


import arc.graphics.Color;
import lh.classes.types.SigilStatusEffect;
import lh.graphics.LHPal;
import mindustry.content.Fx;
import mindustry.type.StatusEffect;

public class LHStatuses {
    public static StatusEffect
    speedSigil, damageSigil, healthSigil, reloadSigil, crawlerSigil, disabled;

    public static void load(){
        speedSigil = new SigilStatusEffect("sigils-speed"){{
            color = LHPal.collapse;
            speedMultiplier = 1.5f;
            dragMultiplier = 1.5f;
        }};
        damageSigil = new SigilStatusEffect("sigils-damage"){{
            color = LHPal.collapse;
            damageMultiplier = 1.5f;
        }};
        healthSigil = new SigilStatusEffect("sigils-health"){{
            color = LHPal.collapse;
            healthMultiplier = 1.5f;
            damage = -0.5f;
        }};
        reloadSigil = new SigilStatusEffect("sigils-reload"){{
            color = LHPal.collapse;
            reloadMultiplier = 1.5f;
            buildSpeedMultiplier = 1.5f;
        }};
        disabled = new StatusEffect("disabled"){{
            color = Color.valueOf("b33caa");
            effect = Fx.regenSuppressParticle;
            disarm = true;
            speedMultiplier = 0;
        }};

    }
}
