package com.girlkun.models.map.doanhtrai;

import com.girlkun.models.clan.Clan;
import com.girlkun.models.map.TrapMap;
import com.girlkun.models.map.Zone;
import com.girlkun.models.mob.Mob;
import com.girlkun.models.player.Player;
import com.girlkun.services.ItemTimeService;
import com.girlkun.services.MapService;
import com.girlkun.services.Service;
import com.girlkun.services.func.ChangeMapService;
import com.girlkun.utils.Logger;
import com.girlkun.utils.Util;

import java.util.ArrayList;
import java.util.List;
//import static jdk.jfr.internal.consumer.EventLog.update;

/**
 *
  @author BTH

 *
 */
public class Zombie { 

   
    public static final List<Zombie> DOANH_TRAI2;
    public static final int MAX_AVAILABLE = 100;
    public static final int TIME_DOANH_TRAI = 1800000;
    public static int N_PLAYER_MAP =0;
    public static final int N_PLAYER_CLAN = 0;
    private Player player;
    
    
 
    static {
        DOANH_TRAI2 = new ArrayList<>();
        for (int i = 0; i < MAX_AVAILABLE; i++) {
            DOANH_TRAI2.add(new Zombie(i));
        }
    }

    public int id;
    public byte level;
    public final List<Zone> zones;

    public Clan clan;
    public boolean isOpened;
    private long lastTimeOpen2;
    private boolean running;
    private long lastTimeUpdate2;

    public Zombie(int id) {
        this.id = id;
        this.zones = new ArrayList<>();
        running = true;
        //new Thread(this).start();
    }
    
    /*public void run() {
        while (running) {
            try {
                Thread.sleep(10000);
                if (Util.canDoWithTime(lastTimeUpdate2, 10000)) {
                    update();
                    lastTimeUpdate2 = System.currentTimeMillis();
                }
            } catch (Exception ignored) {
            }

        }
    }*/

    public void openBanDoKhoBau2(Player plOpen, Clan clan) {
        
        for (Zombie bando : DOANH_TRAI2) {
            if (bando.isOpened) {
                if (Util.canDoWithTime(bando.lastTimeOpen2, TIME_DOANH_TRAI)) {
                    bando.isOpened = false;
                    bando.finish();
                }
            }
        }
        this.lastTimeOpen2 = System.currentTimeMillis();
        this.isOpened = true;
        this.clan = clan;
        this.clan.timeOpenDoanhTrai2 = this.lastTimeOpen2;
        this.clan.playerOpenDoanhTrai2 = plOpen;
        this.clan.doanhTrai2 = this;
            //ChangeMapService.gI().changeMapInYard(plOpen, 53, -1, 60);
            for (Player pl2 : plOpen.clan.membersInGame) {
            if (plOpen.zone.map.mapId == pl2.zone.map.mapId && pl2.clanMember.getNumDateFromJoinTimeToToday() > 0) 
            ChangeMapService.gI().changeMapInYard(pl2, 191, -1,60);
            
            
            
        }
        resetDT2();
        sendTextDoanhTrai2();
    }
    
    public void kickOutOfBDKB(Player player) {
        if (player.zone.map.mapId>=182&&player.zone.map.mapId<=194) {
            Service.getInstance().sendThongBao(player, "Hết Thời Gian, Bạn Đang Được Đưa Ra Ngoài");
            ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 21, -1, 250);
            running = false;
            player.clan.doanhTrai2 = null;
        }
    }
    private void resetDT2() {
         
         for(Zone zone : this.zones){
             if(zone.map.mapId >= 191&&zone.map.mapId<=194)
            for(Mob mob : zone.mobs){
                mob.point.dame = 1000000;
                mob.point.maxHp = 2000000000;
                mob.hoiSinh();
            }
        }
    }
    public Zone getMapById(int mapId) {
        for (Zone zone : zones) {
            if (zone.map.mapId == mapId) {
                return zone;
            }
        }
        return null;
    }

    public static void addZone(int idBanDo, Zone zone) {
        DOANH_TRAI2.get(idBanDo).zones.add(zone);
    }

    private void sendTextDoanhTrai2() {
        for (Player pl : this.clan.membersInGame) {
            ItemTimeService.gI().sendTextDoanhTrai2(pl);
        }
    }
    public void finish() {

        for (Player pl : this.clan.membersInGame) {
                kickOutOfBDKB(pl);
        }



        this.clan.doanhTrai2 = null;
        this.clan = null;
        this.isOpened = false;
    }
    /*private void update() {
        for (MiNuong bando : DOANH_TRAI2) {
            if (bando.isOpened) {
                if (Util.canDoWithTime(bando.lastTimeOpen2, TIME_DOANH_TRAI)) {
                    bando.finish();
                }
            }
        }
    }*/

}