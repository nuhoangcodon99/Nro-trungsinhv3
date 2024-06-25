package com.girlkun.models.boss.list_boss.Broly;

import com.girlkun.models.boss.list_boss.*;
import com.girlkun.consts.ConstPlayer;
import com.girlkun.models.boss.*;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.map.Zone;
import com.girlkun.models.player.Player;
import com.girlkun.models.skill.Skill;
import com.girlkun.services.EffectSkillService;
import com.girlkun.services.PetService;
import com.girlkun.services.PlayerService;
import com.girlkun.services.Service;
import com.girlkun.utils.Util;

/**
 * @Stole By Ánh Ngọc Zalo0343214443
 */
public class Super extends Boss {
 private long lastTimeHapThu;
    private int timeHapThu;
    public Super(int bossID, BossData bossData, Zone zone) throws Exception {
        super(bossID, bossData);
        this.zone = zone;
    }

    @Override
    public void reward(Player plKill) {
        //vật phẩm rơi khi diệt boss nhân bản
        if (plKill.pet == null) {
            PetService.gI().createNormalPet(plKill);
        }
    }

    @Override
    public void active() {
        Player player = this.zone.getPlayerInMap(id);
        if (player != null) {
            
               
                
            }
        
        super.active();
        
        if (!player.isBoss) {
            if (Util.canDoWithTime(st, 500)) {
                if (player == null) {
                    this.changeStatus(BossStatus.LEAVE_MAP);
                    if (Util.isTrue(100, 100)) {
                        BossData Super = new BossData(
                                "Super Broly " + Util.nextInt(100),
                                this.gender,
                                new short[]{294, 295, 296, -1, -1, -1}, //outfit {head, body, leg, bag, aura, eff}
                               100, //dame
                                new int[]{Util.nextInt(2000000, 16070777)}, //hp
                                new int[]{6}, //map join
                                new int[][]{
                                    {Skill.ANTOMIC, 7, 10000}, {Skill.MASENKO, 7, 10000},
                                    {Skill.KAMEJOKO, 7, 10000},
                                    {Skill.TAI_TAO_NANG_LUONG, 5, 55000}},
                                new String[]{"|-2|SuperBroly"}, //text chat 1
                                new String[]{"|-1|Ọc Ọc"}, //text chat 2
                                new String[]{"|-1|Lần khác ta sẽ xử đẹp ngươi"}, //text chat 3
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
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
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
        this.nPoint.voHieuChuong += Util.nextInt(100, 100);
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
