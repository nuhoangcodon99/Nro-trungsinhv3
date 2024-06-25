package com.girlkun.models.player;


import com.girlkun.models.shop.ShopServiceNew;
import com.girlkun.services.MapService;
import com.girlkun.consts.ConstMap;
import com.girlkun.models.item.Item;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.map.Map;
import com.girlkun.models.map.Zone;
import com.girlkun.models.player.Player;
import com.girlkun.server.Manager;
import com.girlkun.services.MapService;
import com.girlkun.services.PlayerService;
import com.girlkun.services.Service;
import com.girlkun.services.func.ChangeMapService;
import static com.girlkun.services.func.ChangeMapService.NON_SPACE_SHIP;
import com.girlkun.utils.Util;

import java.util.ArrayList;
import java.util.List;


public class Referee2 extends Player {

    private long lastTimeChat;
    private long lastTimeInit;
    private Player playerTarget;

    private long lastTimeTargetPlayer;
    private long timeTargetPlayer = 10000;
    private long lastZoneSwitchTime;
    private long zoneSwitchInterval;
    private List<Zone> availableZones;
    private long lastTimeMove;
    
    public void initReferee2() {
        init();
    }

    @Override
    public short getHead() {
        return 1348;
    }

    @Override
    public short getBody() {
        return 1349;
    }

    @Override
    public short getLeg() {
        return 1350;
    }
    
    

    private void joinMap(Zone z, Player player) {
        MapService.gI().goToMap(player, z);
        z.load_Me_To_Another(player);
    }
    private void moveTo(int x, int y) {
        byte dir = (byte) (this.location.x - x < 0 ? 1 : -1);
        byte move = (byte) Util.nextInt(40, 60);
        PlayerService.gI().playerMove(this, this.location.x + (dir == 1 ? move : -move), y + (Util.isTrue(3, 10) ? -50 : 0));
    }
    @Override
    public void update() {
       
        if (Util.canDoWithTime(lastTimeChat, 3000)) {
             Service.getInstance().chat(this, "Dắt Ta Đi Chơi Nào");
             
                this.moveTo(Util.nextInt(450,850), 288);
            ItemMap it = new ItemMap(this.zone, 648, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), -1);
            it.options.add(new Item.ItemOption(30,0));
            Service.getInstance().dropItemMap(this.zone, it);
            ItemMap it2 = new ItemMap(this.zone, 648, 1, this.location.x - 15, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), -1);
            it2.options.add(new Item.ItemOption(30,0));
            Service.getInstance().dropItemMap(this.zone, it2);
            ItemMap it3 = new ItemMap(this.zone, 648, 1, this.location.x + 15, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), -1);
            it3.options.add(new Item.ItemOption(30,0));
            Service.getInstance().dropItemMap(this.zone, it3);
            
            lastTimeChat = System.currentTimeMillis();
        }
            
        
        
        if (Util.canDoWithTime(lastTimeInit, 30000)) {
            int[] mp ={0,1,2};
            int hh = Util.nextInt(18);
            for (Map m : Manager.MAPS) {
            if (m.mapId == mp[hh]) {
            int kk = Util.nextInt(9);
                for (Zone z : m.zones) {
                    if (z.zoneId == kk) {
                    this.nPoint.setFullHpMp();
                    this.location.x = 700;
                    this.location.y = 288;
                    joinMap(z, this);
                    break;}
                }
                break;
            }
        }
            lastTimeInit = System.currentTimeMillis();
        }
}

    private void init() {
        int id = -1000000;
       // int[][] mp={{0,1,2,3,24,42},{7,8,9,10,25,43},{14,15,16,17,26,44}}; 
            int hh = Util.nextInt(18);
            int[] mp = {0,1,2,3,42,14,15,16,17,26,44,7,8,9,10,25,43};
        for (Map m : Manager.MAPS) {
            if (m.mapId == mp[hh]) {
            int kk = Util.nextInt(9);
                for (Zone z : m.zones) {
                    if (z.zoneId == kk) {
                    Referee2 pl = new Referee2();
                    pl.name = "Zeno Sama";
                    pl.gender = 0;
                    pl.id = id++;
                    pl.nPoint.hpMax = 1100;
                    pl.nPoint.hpg = 1100;
                    pl.nPoint.hp = 1100;
                    pl.nPoint.setFullHpMp();
                    pl.location.x = 700;
                    pl.location.y = 288;
                    pl.type = 34;
                    joinMap(z, pl);
                    z.setReferee2(pl);
                    break;
                }}
                break;
            }
        
        }
    }
}