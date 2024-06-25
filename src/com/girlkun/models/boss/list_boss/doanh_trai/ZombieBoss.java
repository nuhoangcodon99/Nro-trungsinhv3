package com.girlkun.models.boss.list_boss.doanh_trai;

import com.girlkun.consts.ConstPlayer;
import com.girlkun.models.boss.*;
import com.girlkun.models.item.Item;
import com.girlkun.models.item.Item.ItemOption;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.map.Zone;
import com.girlkun.models.mob.Mob;
import com.girlkun.models.player.Player;
import com.girlkun.models.skill.Skill;
import com.girlkun.services.EffectSkillService;
import com.girlkun.services.InventoryServiceNew;
import com.girlkun.services.Service;
import com.girlkun.utils.Util;


public class ZombieBoss extends Boss {
    protected Player playerAtt;
    
    public ZombieBoss(Zone zone, int id) throws Exception {
        super(id, new BossData(
                "Mị Nương " + (id + 114), //name
                ConstPlayer.TRAI_DAT, //gender
                new short[]{841, 842, 843, -1, -1, -1}, //outfit {head, body, leg, bag, aura, eff}
                ((500000)), //dame
                new int[]{((50000000))}, //hp
                new int[]{183}, //map join
                new int[][]{
                {Skill.DEMON, 3, 1}, {Skill.DEMON, 6, 2}, {Skill.DRAGON, 7, 3}, {Skill.DRAGON, 1, 4}, {Skill.GALICK, 5, 5},
                {Skill.KAMEJOKO, 7, 6}, {Skill.KAMEJOKO, 6, 7}, {Skill.KAMEJOKO, 5, 8}, {Skill.KAMEJOKO, 4, 9}, {Skill.KAMEJOKO, 3, 10}, {Skill.KAMEJOKO, 2, 11},{Skill.KAMEJOKO, 1, 12},
              {Skill.ANTOMIC, 1, 13},  {Skill.ANTOMIC, 2, 14},  {Skill.ANTOMIC, 3, 15},{Skill.ANTOMIC, 4, 16},  {Skill.ANTOMIC, 5, 17},{Skill.ANTOMIC, 6, 19},  {Skill.ANTOMIC, 7, 20},
                {Skill.MASENKO, 1, 21}, {Skill.MASENKO, 5, 22}, {Skill.MASENKO, 6, 23},
                    {Skill.KAMEJOKO, 7, 1000},},
                new String[]{}, //text chat 1
                new String[]{"|-1|Nhóc con"}, //text chat 2
                new String[]{}, //text chat 3
                2000000
        ));
        
        this.zone = zone;
    }
    @Override
    public void reward(Player plKill) {
            ItemMap it2 = new ItemMap(this.zone, 1517, Util.nextInt(10,20), this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            it2.options.add(new ItemOption(30,0));
            Service.getInstance().dropItemMap(this.zone, it2);
            ItemMap it3 = new ItemMap(this.zone, 15+Util.nextInt(2), 1, this.location.x-15, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it3);
            Item nond = InventoryServiceNew.gI().findItemBody(plKill, 421);
            Item nonx = InventoryServiceNew.gI().findItemBody(plKill, 422);
            if (nond!= null || nonx!=null) 
            {
                ItemMap it4 = new ItemMap(this.zone, 1215, 1, this.location.x - 15, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it4);
            }
        super.reward(plKill);
    }
    @Override
    public void active() {
    super.active();
    }
}
