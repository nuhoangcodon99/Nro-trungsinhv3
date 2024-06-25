package com.girlkun.models.npc.specialnpc;

import com.girlkun.services.func.ChangeMapService;
import com.girlkun.services.PetService;
import com.girlkun.models.player.Player;
import com.girlkun.utils.Util;
import com.girlkun.network.io.Message;
import com.girlkun.services.Service;
import com.girlkun.utils.Logger;


public class cr3 {

//    private static final long DEFAULT_TIME_DONE = 7776000000L;
    private static final long DEFAULT_TIME_DONE = 86400000L;

    private Player player;
    public long lastTimeCreate;
    public long timeDone;

    private final short id = 51;

    public cr3(Player player, long lastTimeCreate, long timeDone) {
        this.player = player;
        this.lastTimeCreate = lastTimeCreate;
        this.timeDone = timeDone;
    }

    public static void createcr3(Player player) {
        player.cr3 = new cr3(player, System.currentTimeMillis(), DEFAULT_TIME_DONE);
    }

    public void sendcr3() {
        Message msg;
if(player.cr3 != null){
        try {
            msg = new Message(-122);
            msg.writer().writeShort(this.id);
            msg.writer().writeByte(1);
            msg.writer().writeShort(26448);
            msg.writer().writeByte(0);
            msg.writer().writeInt(this.getSecondDone());
            this.player.sendMessage(msg);
          //  sendMessAllPlayerInMap(player, msg);
            msg.cleanup();
        } catch (Exception e) {
            Logger.logException(cr1.class, e);
        }}
else{
Service.gI().sendThongBao(player, "Yêu cầu phải có đệ tử");
}
    }

    public int getSecondDone() {
        int seconds = (int) ((lastTimeCreate + timeDone - System.currentTimeMillis()) / 1000);
        return seconds > 0 ? seconds : 0;
    }

    public void openEgg(int gender) {
        if (this.player.pet != null) {
            try {
                destroyEgg();
                Thread.sleep(4000);
                if (this.player.pet == null) {
                    PetService.gI().createBerusPet(this.player, gender);
                } else {
                    PetService.gI().changeBerusPet(this.player, gender);
                }
                ChangeMapService.gI().changeMapInYard(this.player, this.player.gender * 7, -1, Util.nextInt(300, 500));
                player.cr2 = null;
            } catch (Exception e) {
            }
        } else {
            Service.gI().sendThongBao(player, "Yêu cầu phải có đệ tử");
        }
    }

    public void destroyEgg() {
        try {
            Message msg = new Message(-117);
            msg.writer().writeByte(101);
            player.sendMessage(msg);
            msg.cleanup();
            Service.gI().sendThongBao(player, "Xóa Dưa Thành Công Vui Lòng Đổi Lại Khu Khởi Tạo Lại..");
        } catch (Exception e) {
        }
        this.player.cr3 = null;
    }

    public void subTimeDone(int d, int h, int m, int s) {
        this.timeDone -= ((d * 24 * 60 * 60 * 1000) + (h * 60 * 60 * 1000) + (m * 60 * 1000) + (s * 1000));
        this.sendcr3();
    }
    
    public void dispose(){
        this.player = null;
    }
}
