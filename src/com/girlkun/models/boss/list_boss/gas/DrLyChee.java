package com.girlkun.models.boss.list_boss.gas;
import com.girlkun.consts.ConstPlayer;
import com.girlkun.models.boss.*;
import static com.girlkun.models.boss.BossStatus.ACTIVE;
import static com.girlkun.models.boss.BossStatus.JOIN_MAP;
import static com.girlkun.models.boss.BossStatus.RESPAWN;
import com.girlkun.models.boss.list_boss.cell.SieuBoHung;
import static com.girlkun.models.item.ItemTime.BAN_DO_KHO_BAU;
import static com.girlkun.models.item.ItemTime.KHI_GASS;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.map.Zone;
import com.girlkun.models.map.challenge.MartialCongressService;
import com.girlkun.models.player.Player;
import com.girlkun.models.skill.Skill;
import com.girlkun.services.EffectSkillService;
import com.girlkun.services.PlayerService;
import com.girlkun.services.Service;
import com.girlkun.server.Maintenance;
import com.girlkun.server.Manager;
import com.girlkun.services.ItemTimeService;
import com.girlkun.services.SkillService;
import com.girlkun.services.func.ChangeMapService;
import com.girlkun.utils.SkillUtil;
import com.girlkun.utils.Util;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author BTH sieu cap vippr0
 */
public class DrLyChee extends Boss {
    private static final int[][] FULL_DEMON = new int[][]{{Skill.DEMON, 1}, {Skill.DEMON, 2}, {Skill.DEMON, 3}, {Skill.DEMON, 4}, {Skill.DEMON, 5}, {Skill.DEMON, 6}, {Skill.DEMON, 7}};
  private long lastTimeHapThu;
    private int timeHapThu;
       private long lastUpdate = System.currentTimeMillis();
    private long timeJoinMap;
    private int levell;
    private int initSuper = 0;
    protected Player playerAtt;
    private int timeLive = 200000000;
    public DrLyChee(Zone zone ,int level, int dame, long hp,int id) throws Exception {
        super(id, new BossData(
            "Drabura Frost",
            ConstPlayer.XAYDA,
            new short[]{1428, 1429, 1430, -1, -1, -1}, //outfit {head, body, leg, bag, aura, eff}
               ((5000 * level)), //dame    
            new int[]{((100000000 * level))}, //hp
            new int[]{52},
            new int[][]{
                {Skill.DEMON, 3, 1}, {Skill.DEMON, 6, 2}, {Skill.DRAGON, 7, 3}, {Skill.DRAGON, 1, 4}, {Skill.GALICK, 5, 5},
                {Skill.KAMEJOKO, 7, 6}, {Skill.KAMEJOKO, 6, 7}, {Skill.KAMEJOKO, 5, 8}, {Skill.KAMEJOKO, 4, 9}, {Skill.KAMEJOKO, 3, 10}, {Skill.KAMEJOKO, 2, 11},{Skill.KAMEJOKO, 1, 12},
              {Skill.ANTOMIC, 1, 13},  {Skill.ANTOMIC, 2, 14},  {Skill.ANTOMIC, 3, 15},{Skill.ANTOMIC, 4, 16},  {Skill.ANTOMIC, 5, 17},{Skill.ANTOMIC, 6, 19},  {Skill.ANTOMIC, 7, 20},
                {Skill.MASENKO, 1, 21}, {Skill.MASENKO, 5, 22}, {Skill.MASENKO, 6, 23},
                    {Skill.KAMEJOKO, 7, 1000},},
            new String[]{"|-2|Ma nhân Bư đã xuất hiện rồi"}, //text chat 1
            new String[]{"|-1|Thấy ảo chưa nè!"}, //text chat 2
            new String[]{"|-1|Nhớ mặt tao đấy",
                "|-1|Tobe continue.."}, //text chat 3
            60
        ));
        
        this.zone = zone;
        this.levell = level;
    }
    @Override
    public void reward(Player plKill) {
        if ( levell < 2000){
                      ItemMap it = new ItemMap(this.zone, 2048, (levell/4), this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it);
//            a+=10;
//                }
                    ItemMap it1 = new ItemMap(this.zone, 1244, (levell/10), this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it1);
            
            ItemMap it2 = new ItemMap(this.zone, 722, (levell/20), this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it2);
            
//            int a=0;
//            for (int i=0; i<(levell/3); i++)
//                {
//                    ItemMap it2 = new ItemMap(this.zone, 722, 1, this.location.x + a, this.zone.map.yPhysicInTop(this.location.x,
//                    this.location.y - 24), -1);
//            Service.getInstance().dropItemMap(this.zone, it2);
//            a+=15;
//                }
        }
        else if ( levell >= 2000){
                      ItemMap it = new ItemMap(this.zone, 2048, (levell/4), this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it);
            
            ItemMap it3 = new ItemMap(this.zone, 2049, (levell/400), this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it3);
//            a+=10;
//                }
                    ItemMap it1 = new ItemMap(this.zone, 1244, (levell/10), this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it1);
            ItemMap it2 = new ItemMap(this.zone, 722, (levell/20), this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it2);
            
//            int a=0;
//            for (int i=0; i<(levell/3); i++)
//                {
//                    ItemMap it2 = new ItemMap(this.zone, 722, 1, this.location.x + a, this.zone.map.yPhysicInTop(this.location.x,
//                    this.location.y - 24), -1);
//            Service.getInstance().dropItemMap(this.zone, it2);
//            a+=15;
//                }
        }
        plKill.clan.pointGas += 1;
        if ( plKill.clan.pointGas >= 2){ 
            for (Player pl : plKill.clan.membersInGame) {
            pl.clan.haveGoneGas = true;
            pl.clan.khiGas = null;
            ItemTimeService.gI().sendTextTime(plKill, (byte) KHI_GASS, "Khí gas hủy diệt sắp kết thúc : ", 30);
            ChangeMapService.gI().goToHome(plKill);
            }
        }
    }
            
    
    @Override
    public void active() {
    super.active();
    }
    
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
        if ( levell <= 30000){
        if (Util.isTrue((levell/1000), 100) && plAtt != null) {//tỉ lệ hụt của thiên sứ
            Util.isTrue(this.nPoint.tlNeDon, 1000000);
            if (Util.isTrue(1, 100)) {
                this.chat("Đạt Gà đánh mạnh lên");
                this.chat("Hiếu Gà đánh mạnh lên");
            } else if (Util.isTrue(1, 100)) {
                this.chat("Ngậm hành đi kkkkk");
                this.chat("Anh Đức đẹp trai nhất SV");
                this.chat("Các ngươi sẽ tránh được mọi nguy hiểm");
            } else if (Util.isTrue(1, 100)) {
                this.chat("Anh Đức đẹp trai nhất SV");
            }
            damage = 0;
            }
        } else {
        if (Util.isTrue(40, 100) && plAtt != null) {//tỉ lệ hụt của thiên sứ
            Util.isTrue(this.nPoint.tlNeDon, 1000000);
            if (Util.isTrue(1, 100)) {
                this.chat("Đạt Gà đánh mạnh lên");
                this.chat("Hiếu Gà đánh mạnh lên");
            } else if (Util.isTrue(1, 100)) {
                this.chat("Ngậm hành đi kkkkk");
                this.chat("Anh Đức đẹp trai nhất SV");
                this.chat("Các ngươi sẽ tránh được mọi nguy hiểm");
            } else if (Util.isTrue(1, 100)) {
                this.chat("Anh Đức đẹp trai nhất SV");
            }
            damage = 0;
            }

        }
        if (!this.isDie()) {
            if (!piercing && Util.isTrue(this.nPoint.tlNeDon, 1)) {
                this.chat("Xí hụt");
                return 0;
            }
            damage = (int) this.nPoint.subDameInjureWithDeff(damage/2);
            if (!piercing && effectSkill.isShielding) {
                if (damage > nPoint.hpMax) {
                    EffectSkillService.gI().breakShield(this);
                }
                damage = 1;
            }
            this.nPoint.subHP(damage);
            if (isDie()) {
                this.setDie(plAtt);
                die(plAtt);
            }
            if ( levell <= 30000){
            damage -= damage * (levell/1000)/100;
            } else {
            damage -= damage * 40/100;
            }
            return damage;
        } else {
            return 0;
        }
    }
     
        @Override
    public void leaveMap() {
        super.leaveMap();
        BossManager.gI().removeBoss(this);
    }

}







