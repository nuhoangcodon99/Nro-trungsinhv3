package com.girlkun.models.boss.list_boss;
import com.girlkun.models.boss.list_boss.bojack.*;
import com.girlkun.models.item.Item;
import com.girlkun.models.boss.Boss;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossStatus;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.player.Player;
import com.girlkun.services.EffectSkillService;
import com.girlkun.services.Service;
import com.girlkun.services.TaskService;
import com.girlkun.utils.Util;
import java.util.Random;


public class s1 extends Boss {

    public s1() throws Exception {
        super(BossID.s1, BossesData.s1);
    }
     @Override
    public void reward(Player plKill) {
        
        ItemMap itemMap;
        if (Util.isTrue(100, 100)) {
        itemMap = Util.ratiItem(zone,615, 1, this.location.x, this.location.y, plKill.id);
        itemMap.options.add(new Item.ItemOption(50,Util.nextInt(20,22)));
        itemMap.options.add(new Item.ItemOption(77,Util.nextInt(20,22)));
        itemMap.options.add(new Item.ItemOption(103,Util.nextInt(20,22)));
        itemMap.options.add(new Item.ItemOption(93,Util.nextInt(1,7)));
        itemMap.options.add(new Item.ItemOption(30,0));
        int[] HoaBiNgan = new int[]{861};
        int randomNR = new Random().nextInt(HoaBiNgan.length);
        if (Util.isTrue(1, 5)) {
        Service.getInstance().dropItemMap(this.zone, new ItemMap(zone, HoaBiNgan[randomNR], 1, this.location.x, this.location.y, plKill.id));
        } else {    
        
        Service.gI().dropItemMap(this.zone, itemMap);
    }
    } 
    }
@Override
    public void doneChatE() {
        if (this.parentBoss == null || this.parentBoss.bossAppearTogether == null
                || this.parentBoss.bossAppearTogether[this.parentBoss.currentLevel] == null) {
            return;
        }
        for (Boss boss : this.parentBoss.bossAppearTogether[this.parentBoss.currentLevel]) {
            if (boss.id == BossID.s2 && !boss.isDie()) {
                boss.changeToTypePK();
                break;
            }
        }
    }
    @Override
    public void active() {
        super.active(); //To change body of generated methods, choose Tools | Templates.
        if(Util.canDoWithTime(st,900000)){
            this.changeStatus(BossStatus.LEAVE_MAP);
        }
    }
    @Override
    public void joinMap() {
        super.joinMap(); //To change body of generated methods, choose Tools | Templates.
        st= System.currentTimeMillis();
    }
    private long st;
    
}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - GirlBeo
 */
