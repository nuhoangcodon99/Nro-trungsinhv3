package com.girlkun.models.map.doanhtrai;

import com.girlkun.models.boss.BossID;
import com.girlkun.models.item.Item;
import static com.girlkun.models.map.doanhtrai.Zombie.DOANH_TRAI2;
import static com.girlkun.models.map.doanhtrai.Zombie.TIME_DOANH_TRAI;
import com.girlkun.models.boss.list_boss.doanh_trai.ZombieBoss;
import com.girlkun.models.player.Player;
import com.girlkun.services.InventoryServiceNew;
import com.girlkun.services.MapService;
import com.girlkun.services.Service;
import com.girlkun.services.func.ChangeMapService;
import com.girlkun.utils.Logger;
import com.girlkun.utils.Util;
import java.util.List;


public class ZombieService {
    private static ZombieService i;
    private ZombieService() {
    }
    
    public static ZombieService gI() {
        if (i == null) {
            i = new ZombieService();
        }
        return i;
    }
    
    public void openBanDoKhoBau(Player player) {
        if (player.clan != null && player.clan.doanhTrai2 == null) {
            Zombie doanhtrai2 = null;
                for (Zombie dt2 : Zombie.DOANH_TRAI2) 
                    if (!dt2.isOpened) {
                doanhtrai2 = dt2;
                break;
            }//{
            //        
             //       doanhtrai2 = null;
              //      }
            if (doanhtrai2 != null) {
                          doanhtrai2.openBanDoKhoBau2(player, player.clan);
                        try {
                            
                    new ZombieBoss(player.clan.doanhTrai2.getMapById(183),BossID.MI_NUONG1);   
                    new ZombieBoss(player.clan.doanhTrai2.getMapById(183),BossID.MI_NUONG2);   
                    new ZombieBoss(player.clan.doanhTrai2.getMapById(183),BossID.MI_NUONG3);   
                } catch (Exception e) {
                }
            } else {
                Service.getInstance().sendThongBao(player, "Rừng Ma Quái đã đầy, vui lòng quay lại sau");
            }
        } else {
            Service.getInstance().sendThongBao(player, "NO CLAN NO MN");
        }
    }
}