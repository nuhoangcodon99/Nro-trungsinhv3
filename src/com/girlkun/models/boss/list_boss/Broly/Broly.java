/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girlkun.models.boss.list_boss.Broly;

import com.girlkun.models.boss.list_boss.*;
import com.girlkun.models.boss.Boss;
import com.girlkun.models.boss.BossData;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossStatus;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.player.Player;
import com.girlkun.models.skill.Skill;
import com.girlkun.services.EffectSkillService;
import com.girlkun.services.PetService;
import com.girlkun.services.Service;
import com.girlkun.utils.Util;
import java.util.Random;

/**
 * @@Stole By Ánh Ngọc Zalo0343214443
 */
public class Broly extends Boss {
 private long lastTimeHapThu;
    private int timeHapThu;
    public Broly() throws Exception {
        super(BossID.BROLY, BossesData.BROLY);
    }

    @Override
    public void reward(Player plKill) {
        if (Util.isTrue(100, 100)) {
            this.nPoint.hpMax = 16000000;
            if (this.nPoint.hp <= 0) {
                BossData superBrolyData = new BossData(
                        "Super Broly " + Util.nextInt(100),
                        this.gender,
                        new short[]{294, 295, 296, -1, -1, -1}, //outfit {head, body, leg, bag, aura, eff}
                        Util.nextInt(1000, 20000), //dame
                        new int[]{Util.nextInt(2000000, 16070777)}, //hp
                        new int[]{6}, //map join
                        new int[][]{
                            {Skill.ANTOMIC, 7, 10000}, {Skill.MASENKO, 7, 15100},
                            {Skill.KAMEJOKO, 7, 1000},
                            {Skill.TAI_TAO_NANG_LUONG, 5, 55000}},
                        new String[]{"|-2|SuperBroly"}, //text chat 1
                        new String[]{"|-1|Ngươi làm ta nổi giận rồi " + plKill.name}, //text chat 2
                        new String[]{"|-1|Lần khác ta sẽ xử đẹp ngươi"}, //text chat 3
                        60
                );

                try {
                    // Tạo một đối tượng Super Broly mới
                    new Super(Util.createIdBossClone((int) this.id), superBrolyData, this.zone);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void active() {
        Player player = this.zone.getPlayerInMap(id);
        if (player != null) {
        }
        super.active();
        this.nPoint.dame = this.nPoint.hpMax / 100;
        if (!player.isBoss) {
            if (Util.canDoWithTime(st, 9000)) {
                if (player == null) {
                    this.changeStatus(BossStatus.LEAVE_MAP);
                    if (Util.isTrue(100, 100)) {
                        BossData Super = new BossData(
                                "Super Broly " + Util.nextInt(100),
                                this.gender,
                                new short[]{294, 295, 296, -1, -1, -1},
                               100, //dame
                                new int[]{Util.nextInt(2083220, 16070777)},
                                new int[]{6},
                                new int[][]{
                                    {Skill.ANTOMIC, 7, 10000}, {Skill.MASENKO, 7, 10000},
                                    {Skill.KAMEJOKO, 7, 5100},
                                    {Skill.TAI_TAO_NANG_LUONG, 5, 25000}},
                                new String[]{"|-2|"},
                                new String[]{"|-1|Ngươi làm ta nổi giận rồi " + player.name},
                                new String[]{"|-1|Lần khác ta sẽ xử đẹp ngươi"},
                                60
                        );

                        try {
                            new Super(Util.createIdBossClone((int) this.id), Super, this.zone);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @Override
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack
    ) {
        if (plAtt != null) {
            switch (plAtt.playerSkill.skillSelect.template.id) {
                case Skill.ANTOMIC:
                case Skill.DEMON:
                case Skill.DRAGON:
                case Skill.GALICK:
                case Skill.KAIOKEN:
                case Skill.KAMEJOKO:
                case Skill.DICH_CHUYEN_TUC_THOI:
                case Skill.LIEN_HOAN:
                    damage = this.nPoint.hpMax / 100;
                    if (this.nPoint.hp < 1) {
                        this.setDie(plAtt);
                        die(this);
                    }
                    return super.injured(plAtt, damage, !piercing, isMobAttack);
            }
        }
        return super.injured(plAtt, damage, piercing, isMobAttack);
    }
 private void hapThu() {
        if (!Util.canDoWithTime(this.lastTimeHapThu, this.timeHapThu) || !Util.isTrue(100, 100)) {
            return;
        }
        this.nPoint.voHieuChuong += 100;
        this.lastTimeHapThu = System.currentTimeMillis();
        this.timeHapThu = Util.nextInt(100, 100);
        
    }
    


    @Override
    public void joinMap() {
        super.joinMap(); //To change body of generated methods, choose Tools | Templates.
        st = System.currentTimeMillis();
    }
    private long st;
}
