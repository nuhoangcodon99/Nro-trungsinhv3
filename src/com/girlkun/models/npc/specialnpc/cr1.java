package com.girlkun.models.npc.specialnpc;

import com.girlkun.services.func.ChangeMapService;
import com.girlkun.services.PetService;
import com.girlkun.models.player.Player;
import com.girlkun.utils.Util;
import com.girlkun.network.io.Message;
import com.girlkun.services.Service;
import com.girlkun.utils.Logger;


public class cr1 {

//    private static final long DEFAULT_TIME_DONE = 7776000000L;
    private static final long DEFAULT_TIME_DONE = 86400000L;

    private Player player;
    public long lastTimeCreate;
    public long timeDone;

    private final short id = 51;

    public cr1(Player player, long lastTimeCreate, long timeDone) {
        this.player = player;
        this.lastTimeCreate = lastTimeCreate;
        this.timeDone = timeDone;
    }

    public static void createcr1(Player player) {
        player.cr1 = new cr1(player, System.currentTimeMillis(), DEFAULT_TIME_DONE);
    }

    public void sendCr1() {
          Message msg;
        try {
//            Message msg = new Message(-117);
//            msg.writer().writeByte(100);
//            player.sendMessage(msg);
//            msg.cleanup();

            msg = new Message(-122);
            msg.writer().writeShort(this.id);
            msg.writer().writeByte(1);
            msg.writer().writeShort(4664);
            msg.writer().writeByte(0);
            msg.writer().writeInt(this.getSecondDone());
            this.player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            Logger.logException(cr1.class, e);
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
                player.billEgg = null;
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
        this.player.cr1 = null;
    }

    public void subTimeDone(int d, int h, int m, int s) {
        this.timeDone -= ((d * 24 * 60 * 60 * 1000) + (h * 60 * 60 * 1000) + (m * 60 * 1000) + (s * 1000));
        this.sendCr1();
    }
    
    public void dispose(){
        this.player = null;
    }
}
