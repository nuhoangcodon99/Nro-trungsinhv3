package com.girlkun.models.boss.list_boss;

import com.girlkun.models.boss.Boss;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossManager;
import com.girlkun.models.boss.BossStatus;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.item.Item;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.player.Player;
import com.girlkun.server.Manager;
import com.girlkun.services.EffectSkillService;
import com.girlkun.services.InventoryServiceNew;
import com.girlkun.services.Service;
import com.girlkun.utils.Util;
import java.util.Random;


public class ThuyTinh extends Boss {

    public ThuyTinh() throws Exception {
        super(BossID.THUY_TINH, BossesData.THUY_TINH);
    }

    @Override
    public void reward(Player plKill) {
            super.reward(plKill);
            ItemMap it = new ItemMap(this.zone, 1214, 1, this.location.x -30, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            Service.gI().dropItemMap(this.zone, it);
        
           
    }
    @Override
    protected void resetBase() {
        super.resetBase();
    }
    @Override
    public void active() {
        this.attack();
    }
    @Override
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
        if (!this.isDie()) {
            if (!piercing && (Util.isTrue(this.nPoint.tlNeDon, 1000) || (plAtt.id == BossID.SON_TINH) || (plAtt.cFlag != 2))) {
                return 0;
            }
            damage = 20;
            this.nPoint.subHP(damage);
            if (isDie() && plAtt != null) {
                this.setDie(plAtt);
                die(plAtt);
            }
            return damage;
        } else {
            return 0;
        }
    }
}






















