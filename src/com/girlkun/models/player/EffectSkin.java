package com.girlkun.models.player;

import com.girlkun.models.boss.BossData;
import com.girlkun.models.item.Item;
import com.girlkun.models.mob.Mob;
import com.girlkun.models.skill.Skill;
import com.girlkun.services.ItemService;
import com.girlkun.services.PlayerService;
import com.girlkun.services.Service;
import com.girlkun.services.EffectSkillService;
import static com.girlkun.services.EffectSkillService.SLEEP_EFFECT;
import com.girlkun.services.InventoryServiceNew;
import com.girlkun.services.ItemTimeService;
import com.girlkun.services.MapService;
import com.girlkun.utils.Logger;
import com.girlkun.utils.Util;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class EffectSkin {

    private static final String[] textOdo = new String[]{
        "Hôi quá", "Tránh ra đi thằng ở dơ", "Mùi gì kinh quá vậy?",
        "Kinh tởm quá", "Biến đi thằng ở dơ", "Kính ngài ở dơ"
    };
     private static final String[] xuongkho = new String[]{
       "Hình dạng gì đây ?"
    };
    private static final String[] textHoaDa = new String[]{
        "Chết rồi", "Tránh ra đi tên khốn!", "Bị hóa đá ròi",
        "Kinh tởm quá", "Thấy ảo chưa nè!", "Kính ngài nước dãi"
    };
    private static final String[] textDraburaFrost = new String[]{ //Cải trang Drabura Frost
        "Ui lạnh quá..", "Đông cứng rồi", "Tránh xa ta ra",
    };
     private static final String[] tm = new String[]{ //Cải trang Drabura Frost
        "Z Z Z",
    };
      private static final String[] mabu = new String[]{ //Cải trang Drabura Frost
        " Hình Dạng Gì Thế Này", "Kẹo Ư?", "Tránh xa ta ra",
    };
    private static final String[] textDrabura = new String[]{ //Cải trang Drabura
        "AAA Nặng Quá", "Chết tiệt", "Hóa đá rồi", "Tránh xa ta ra"
    };
    private static final String[] thodaica = new String[]{
        "Em Chào Anh", "Anh Thỏ Đại Ca", "Bị Hoá Cà Rốt Rồi..."

    };
        private static final String[] vohieu = new String[]{
       "Trời, Chưởng toàn lực toàn vô dụng với hắn"

    };
    private static final String[] test = new String[]{
        "Người gì mà đẹp zai zậy", "Ui anh Béo :3", "Sao anh đẹp zoai zị?"

    };
    
    private Player player;
    protected boolean isBiHoaXuong;

    public EffectSkin(Player player) {
        this.player = player;
        this.xHPKI = 1;
    }
 public long vohieuchuong;
    public long lastTimeAttack;
    private long lastTimeOdo;
    private long lastTimeTest;
    private long lastTimeXenHutHpKi;

    public long lastTimeAddTimeTrainArmor;
    public long lastTimeSubTimeTrainArmor;

    public boolean isVoHinh;

    public long lastTimeXHPKI;
    public int xHPKI;

    public long lastTimeUpdateCTHT;

    public long lastTimeThucHienHoaDa;
    public long lastTimeBiHoaDa;
    //Thỏ Đại Ca
    public long lastTimeCarot;
    
    //Xương khô
      public long lastTimexuong;
    
     private long lastTimemabu;  //Cải trang socola
    private long lastTimetm; //Cải trang thôi miên
    //Cải trang Dracula Frost
    public boolean isBang;
    
    public int timeBang;
    private long lastTimeHoaBang;  //Cải trang Dracula Frost
    private long lastTimeHoaDa; //Cải trang Dracula 
    private long lastTimeHoaCaRot; //Cải trang Thỏ Đại Ca
    //Cải trang Dracula hóa đá
    public boolean isDa;
    
    public int timeDa;
    public long lastTimeBienCaRot;
    private final int TIME_HOA_DA = 10000;
    private static final int TIME_HOA_DA_PER_SECOND = 20000;

    //Effect ma troi
    public long lastTimeHoaXuong;
    public int timeHoaXuong = 1800;

    public void update() {
        updateVoHinh();
        updateHoaDa();
        updateHoaXuong();
         updateDraburaFrost();
         updatevohieu();
        updateDrabura();
        if (this.player.zone != null && !MapService.gI().isMapOffline(this.player.zone.map.mapId)) {
            updateOdo();
            updatebiencarot();
            updatebosscarot();
            updatetm();
            updatemabu();
            updateXenHutXungQuanh();
            
        }
        if (!this.player.isBoss && !this.player.isPet && !player.isNewPet) {
            updateTrainArmor();
        }
        if (xHPKI != 1 && Util.canDoWithTime(lastTimeXHPKI, 1800000)) {
            xHPKI = 1;
            Service.gI().point(player);
        }
        updateCTHaiTac();
    }
      private void updatetm() {
        try {
           int param = this.player.nPoint.istm;
            if (param > 0) {
                if (Util.canDoWithTime(lastTimetm, 30000)) {
                    List<Player> players = new ArrayList<>();
                    List<Player> playersMap = this.player.zone.getNotBosses();
                    for (Player pl : playersMap) {
                        if (!this.player.equals(pl)  && !pl.isBoss && !pl.isDie()
                                && Util.getDistance(this.player, pl) <= 100) {
                            players.add(pl);
                        }
                    }
                    for (Player pl : players) {
                         
                         EffectSkillService.gI().setThoiMien(pl, System.currentTimeMillis(), 5000);
                    EffectSkillService.gI().sendEffectPlayer(player, pl, EffectSkillService.TURN_ON_EFFECT, (byte) EffectSkillService.SLEEP_EFFECT);
                         Service.gI().Send_Caitrang(pl);
                    ItemTimeService.gI().sendItemTime(pl, 3782, 5000 / 1000);
                    Service.gI().chat(player, " Ngủ đi nào..");
                    Service.gI().chat(pl, tm[Util.nextInt(0, tm.length - 1)]);
                    PlayerService.gI().sendInfoHpMpMoney(pl);
                    Service.gI().Send_Info_NV(pl);
                    }
                    this.lastTimetm = System.currentTimeMillis();
                }
            } else {
            }
        } catch (Exception e) {
            Logger.error("");
        }
    }
     private void updatevohieu() {
        try {
           int param = this.player.nPoint.voHieuChuong;
            if (param > 0) {
                if (Util.canDoWithTime(lastTimetm, 10000)) {
                    List<Player> players = new ArrayList<>();
                    List<Player> playersMap = this.player.zone.getNotBosses();
                    for (Player pl : playersMap) {
                        if (!this.player.equals(pl)  && !pl.isBoss && !pl.isDie()
                                && Util.getDistance(this.player, pl) <= 100) {
                            players.add(pl);
                        }
                    }
                    for (Player pl : players) {
                         
                        
                    EffectSkillService.gI().sendEffectPlayer(player, pl, EffectSkillService.TURN_ON_EFFECT, (byte) EffectSkillService.CAROT);
                         Service.gI().Send_Caitrang(pl);
                   
                    
                    Service.gI().chat(pl, vohieu[Util.nextInt(0, vohieu.length - 1)]);
                    PlayerService.gI().sendInfoHpMpMoney(pl);
                    Service.gI().Send_Info_NV(pl);
                    }
                    this.lastTimetm = System.currentTimeMillis();
                }
            } else {
            }
        } catch (Exception e) {
            Logger.error("");
        }
    }
     
      private void updateDraburaFrost() {
        try {
            if (this.player.nPoint.iscarot == true) {
                if (Util.canDoWithTime(lastTimeHoaCaRot, 30000)) {
                    List<Player> players = new ArrayList<>();
                    List<Player> playersMap = this.player.zone.getNotBosses();
                    for (Player pl : playersMap) {
                        if (!this.player.equals(pl)  && !pl.isBoss && !pl.isDie()
                                && Util.getDistance(this.player, pl) <= 500) {
                            players.add(pl);
                        }
                    }
                    for (Player pl : players) {
                         EffectSkillService.gI().SetHoaCarot(pl, System.currentTimeMillis(), 300000);
                         EffectSkillService.gI().setBlindDCTT(pl, System.currentTimeMillis(), 5000);
                    EffectSkillService.gI().sendEffectPlayer(player, pl, EffectSkillService.TURN_ON_EFFECT, (byte) EffectSkillService.CAROT);
                         Service.gI().Send_Caitrang(pl);
                    ItemTimeService.gI().sendItemTime(pl, 4075, 300000 / 1000);
                    Service.gI().chat(player, "Bắt Tay Cái Nào");
                    Service.gI().chat(pl, thodaica[Util.nextInt(0, thodaica.length - 1)]);
                    PlayerService.gI().sendInfoHpMpMoney(pl);
                    Service.gI().Send_Info_NV(pl);
                    }
                    this.lastTimeHoaCaRot = System.currentTimeMillis();
                }
            } else {
            }
        } catch (Exception e) {
            Logger.error("");
        }
    }
       private void updatebosscarot() {
        try {
            if (this.player.nPoint.iscarott == true) {
                if (Util.canDoWithTime(lastTimeHoaCaRot, 100)) {
                    List<Player> players = new ArrayList<>();
                    List<Player> playersMap = this.player.zone.getNotBosses();
                    for (Player pl : playersMap) {
                        if (!this.player.equals(pl)  && !pl.isBoss && !pl.isDie()
                                && Util.getDistance(this.player, pl) <= 200) {
                            players.add(pl);
                        }
                    }
                    for (Player pl : players) {
                         EffectSkillService.gI().SetHoaCarot(pl, System.currentTimeMillis(), 300000);
                         EffectSkillService.gI().setBlindDCTT(pl, System.currentTimeMillis(), 5000);
                    EffectSkillService.gI().sendEffectPlayer(player, pl, EffectSkillService.TURN_ON_EFFECT, (byte) EffectSkillService.CAROT);
                         Service.gI().Send_Caitrang(pl);
                    ItemTimeService.gI().sendItemTime(pl, 4075, 300000 / 1000);
                    Service.gI().chat(player, "");
                    Service.gI().chat(pl, thodaica[Util.nextInt(0, thodaica.length - 1)]);
                    PlayerService.gI().sendInfoHpMpMoney(pl);
                    Service.gI().Send_Info_NV(pl);
                    }
                    this.lastTimeHoaCaRot = System.currentTimeMillis();
                }
            } else {
            }
        } catch (Exception e) {
            Logger.error("");
        }
    }
     private void updatemabu() {
        try {
            if (this.player.nPoint.ismabu == true) {
                if (Util.canDoWithTime(lastTimemabu, 30000)) {
                    List<Player> players = new ArrayList<>();
                    List<Player> playersMap = this.player.zone.getNotBosses();
                    for (Player pl : playersMap) {
                        if (!this.player.equals(pl)  && !pl.isBoss && !pl.isDie()
                                && Util.getDistance(this.player, pl) <= 500) {
                            players.add(pl);
                        }
                    }
                    for (Player pl : players) {
                         EffectSkillService.gI().setMabu(pl, System.currentTimeMillis(), 30000);
                         EffectSkillService.gI().setBlindDCTT(pl, System.currentTimeMillis(), 5000);
                    EffectSkillService.gI().sendEffectPlayer(player, pl, EffectSkillService.TURN_ON_EFFECT, (byte) EffectSkillService.CAROT);
                         Service.gI().Send_Caitrang(pl);
                    ItemTimeService.gI().sendItemTime(pl, 4127, 30000 / 1000);
                    Service.gI().chat(player, " Hoá Kẹo..");
                    Service.gI().chat(pl, mabu[Util.nextInt(0, mabu.length - 1)]);
                    PlayerService.gI().sendInfoHpMpMoney(pl);
                    Service.gI().Send_Info_NV(pl);
                    }
                    this.lastTimemabu = System.currentTimeMillis();
                }
            } else {
            }
        } catch (Exception e) {
            Logger.error("");
        }
    }
  private void updatebiencarot() {
        try {
            if (this.player.nPoint.isDraburaFrost == true) {
                if (Util.canDoWithTime(lastTimeHoaBang, 30000)) {
                    List<Player> players = new ArrayList<>();
                    List<Player> playersMap = this.player.zone.getNotBosses();
                    for (Player pl : playersMap) {
                        if (!this.player.equals(pl)  && !pl.isBoss && !pl.isDie()
                                && Util.getDistance(this.player, pl) <= 200) {
                            players.add(pl);
                        }
                    }
                    for (Player pl : players) {
                         EffectSkillService.gI().SetHoaBang(pl, System.currentTimeMillis(), 90000);
                         EffectSkillService.gI().setBlindDCTT(pl, System.currentTimeMillis(), 5000);
                    EffectSkillService.gI().sendEffectPlayer(player, pl, EffectSkillService.TURN_ON_EFFECT, (byte) EffectSkillService.BLIND_EFFECT);
                         Service.gI().Send_Caitrang(pl);
                    ItemTimeService.gI().sendItemTime(pl, 11085, 9000 / 1000);
                    Service.gI().chat(player, "Ối Bạn Ơi , Hic !");
                    Service.gI().chat(pl, textDraburaFrost[Util.nextInt(0, textDraburaFrost.length - 1)]);
                    PlayerService.gI().sendInfoHpMpMoney(pl);
                    Service.gI().Send_Info_NV(pl);
                    }
                    this.lastTimeHoaBang = System.currentTimeMillis();
                }
            } else {
            }
        } catch (Exception e) {
            Logger.error("");
        }
    }
    
    private void updateDrabura() { // Dracula Hóa Đá
        try {
            if (this.player.nPoint.isDrabura == true) {
                if (Util.canDoWithTime(lastTimeHoaDa, 30000)) {
                    List<Player> players = new ArrayList<>();
                    List<Player> playersMap = this.player.zone.getNotBosses();
                    for (Player pl : playersMap) {
                        if (!this.player.equals(pl) && !pl.isBoss && !pl.isDie()
                                && Util.getDistance(this.player, pl) <= 200) {
                            players.add(pl);
                        }
                    }
                    for (Player pl : players) {
                         EffectSkillService.gI().SetHoaDa(pl, System.currentTimeMillis(),80000);
                         EffectSkillService.gI().setBlindDCTT(pl, System.currentTimeMillis(),15000);
                    EffectSkillService.gI().sendEffectPlayer(player, pl, EffectSkillService.TURN_ON_EFFECT, EffectSkillService.BLIND_EFFECT);
                         Service.gI().Send_Caitrang(pl);
                         BossData bossDataClone = new BossData(
                                                "Nhân Bản" + player.name,
                                                player.gender,
                                                new short[]{player.getHead(), player.getBody(), player.getLeg(), player.getFlagBag(), player.idAura, player.getEffFront()},
                                                player.nPoint.dame,
                                                new int[]{player.nPoint.hpMax},
                                                new int[]{140},
                                                new int[][]{
                                               {Skill.MASENKO, 3, 1000},
                                               {Skill.LIEN_HOAN, 7, 1000}},
                                                new String[]{"|-2|Boss nhân bản đã xuất hiện rồi"}, //text chat 1
                                                new String[]{"|-1|Ta sẽ chiếm lấy thân xác của ngươi hahaha!"}, //text chat 2
                                                new String[]{"|-1|Lần khác ta sẽ xử đẹp ngươi"}, //text chat 3
                                                60
                                        );
                    ItemTimeService.gI().sendItemTime(pl, 4392, 15000 / 1000);
                    Service.gI().chat(player, "Bạn Bị Hoá Đá");
                        Service.gI().chat(pl, textDrabura[Util.nextInt(0, textDrabura.length - 1)]);
                        PlayerService.gI().sendInfoHpMpMoney(pl);
                        Service.gI().Send_Info_NV(pl);
                    }
                    this.lastTimeHoaDa = System.currentTimeMillis();
                }
            } else {
            }
        } catch (Exception e) {
            Logger.error("");
        }
    }
    private void updateHoaDa() {
        try {
            boolean CoCaiTrangHoaDa = this.player.nPoint.CoCaiTrangHoaDa;
            if (CoCaiTrangHoaDa) {
                boolean Check = System.currentTimeMillis() - lastTimeThucHienHoaDa > TIME_HOA_DA_PER_SECOND;
                if (Check) { // thời gian hóa đá mỗi lần
                    for (Player pl : this.player.zone.getNotBosses()) {
                        if (!this.player.equals(pl) && !pl.isBoss && !pl.isDie() && Util.getDistance(this.player, pl) <= 200 && !pl.nPoint.IsBiHoaDa) {
                            pl.nPoint.IsBiHoaDa = true;
                            Service.gI().SendMsgUpdateHoaDa(pl, (byte) 1, (byte) 0, (byte) 42);
                            Service.gI().Send_Caitrang(pl);
                              
                            ItemTimeService.gI().sendItemTime(pl, 4392, (int) TIME_HOA_DA / 1000);
                            pl.effectSkin.lastTimeBiHoaDa = System.currentTimeMillis();
                        }
                    }
                    this.lastTimeThucHienHoaDa = System.currentTimeMillis();
                }
            }
            if (this.player.nPoint.IsBiHoaDa && (System.currentTimeMillis() - lastTimeBiHoaDa > TIME_HOA_DA)) {
                this.player.nPoint.IsBiHoaDa = false;
                Service.gI().SendMsgUpdateHoaDa(this.player, (byte) 2, (byte) 0, (byte) 42);
                Service.gI().Send_Caitrang(this.player);
                ItemTimeService.gI().removeItemTime(this.player, 4392);
                Service.gI().chat(this.player, "Phẹt Phẹt Phẹt...");
            }
        } catch (Exception e) {
            Logger.error("");
        }
    }

    private void updateCTHaiTac() {
        if (this.player.setClothes.ctHaiTac != -1
                && this.player.zone != null
                && Util.canDoWithTime(lastTimeUpdateCTHT, 5000)) {
            int count = 0;
            int[] cts = new int[9];
            cts[this.player.setClothes.ctHaiTac - 618] = this.player.setClothes.ctHaiTac;
            List<Player> players = new ArrayList<>();
            players.add(player);
            try {
                for (Player pl : player.zone.getNotBosses()) {
                    if (!player.equals(pl) && pl.setClothes.ctHaiTac != -1 && Util.getDistance(player, pl) <= 300) {
                        cts[pl.setClothes.ctHaiTac - 618] = pl.setClothes.ctHaiTac;
                        players.add(pl);
                    }
                }
            } catch (Exception e) {
            }
            for (int i = 0; i < cts.length; i++) {
                if (cts[i] != 0) {
                    count++;
                }
            }
            for (Player pl : players) {
                Item ct = pl.inventory.itemsBody.get(5);
                if (ct.isNotNullItem() && ct.template.id >= 618 && ct.template.id <= 626) {
                    for (Item.ItemOption io : ct.itemOptions) {
                        if (io.optionTemplate.id == 147
                                || io.optionTemplate.id == 77
                                || io.optionTemplate.id == 103) {
                            io.param = count * 3;
                        }
                    }
                }
                if (!pl.isPet && !pl.isNewPet && Util.canDoWithTime(lastTimeUpdateCTHT, 5000)) {
                    InventoryServiceNew.gI().sendItemBody(pl);
                }
                pl.effectSkin.lastTimeUpdateCTHT = System.currentTimeMillis();
            }
        }
    }

    private void updateXenHutXungQuanh() {
        try {
            int param = this.player.nPoint.tlHutHpMpXQ;
            if (param > 0) {
                if (!this.player.isDie() && Util.canDoWithTime(lastTimeXenHutHpKi, 5000)) {
                    int hpHut = 0;
                    int mpHut = 0;
                    List<Player> players = new ArrayList<>();
                    List<Player> playersMap = this.player.zone.getNotBosses();
                    for (Player pl : playersMap) {
                        if (!this.player.equals(pl) && !pl.isBoss && !pl.isDie()
                                && Util.getDistance(this.player, pl) <= 200) {
                            players.add(pl);
                        }

                    }
                    for (Mob mob : this.player.zone.mobs) {
                        if (mob.point.gethp() > 1) {
                            if (Util.getDistance(this.player, mob) <= 200) {
                                int subHp = mob.point.getHpFull() * param / 100;
                                if (subHp >= mob.point.gethp()) {
                                    subHp = mob.point.gethp() - 1;
                                }
                                hpHut += subHp;
                                mob.injured(null, subHp, false);
                            }
                        }
                    }
                    for (Player pl : players) {
                        int subHp = pl.nPoint.hpMax * param / 100;
                        int subMp = pl.nPoint.mpMax * param / 100;
                        if (subHp >= pl.nPoint.hp) {
                            subHp = pl.nPoint.hp - 1;
                        }
                        if (subMp >= pl.nPoint.mp) {
                            subMp = pl.nPoint.mp - 1;
                        }
                        hpHut += subHp;
                        mpHut += subMp;
                        PlayerService.gI().sendInfoHpMpMoney(pl);
                        Service.gI().Send_Info_NV(pl);
                        pl.injured(null, subHp, true, false);
                    }
                    this.player.nPoint.addHp(hpHut);
                    this.player.nPoint.addMp(mpHut);
                    PlayerService.gI().sendInfoHpMpMoney(this.player);
                    Service.gI().Send_Info_NV(this.player);
                    this.lastTimeXenHutHpKi = System.currentTimeMillis();
                }
            }
        } catch (Exception e) {
            Logger.error("");
        }
    }

    private void updateOdo() {
        try {
            int param = this.player.nPoint.tlHpGiamODo;
            if (param > 0) {
                if (Util.canDoWithTime(lastTimeOdo, 10000)) {
                    List<Player> players = new ArrayList<>();

                    List<Player> playersMap = this.player.zone.getNotBosses();
                    for (Player pl : playersMap) {
                        if (!this.player.equals(pl) && !pl.isBoss && !pl.isDie()
                                && Util.getDistance(this.player, pl) <= 200) {
                            players.add(pl);
                        }

                    }
                    for (Player pl : players) {
                        int subHp = pl.nPoint.hpMax * (param / 100);
                        if (subHp >= pl.nPoint.hp) {
                            subHp = pl.nPoint.hp - 1;
                        }
                        Service.gI().chat(pl, textOdo[Util.nextInt(0, textOdo.length - 1)]);
                        PlayerService.gI().sendInfoHpMpMoney(pl);
                        Service.gI().Send_Info_NV(pl);
                        pl.injured(null, subHp, true, false);
                    }
                    this.lastTimeOdo = System.currentTimeMillis();
                }
            }
        } catch (Exception e) {
            Logger.error("");
        }
    }

    private void Test() {
        try {
            int param = this.player.nPoint.test;
            if (param > 0) {
                if (Util.canDoWithTime(lastTimeTest, 10000)) {
                    List<Player> players = new ArrayList<>();

                    for (Player pl : players) {
                        int subHp = pl.nPoint.hpMax * param * 100;
                        if (subHp >= pl.nPoint.hp) {
                            subHp = pl.nPoint.hp + 1;
                        }
                        Service.gI().chat(pl, test[Util.nextInt(0, test.length + 1)]);
                        PlayerService.gI().sendInfoHpMpMoney(pl);
                        Service.gI().Send_Info_NV(pl);
                        pl.injured(null, subHp, true, false);
                    }
                    this.lastTimeTest = System.currentTimeMillis();
                }
            }
        } catch (Exception e) {
            Logger.error("");
        }
    }

    //giáp tập luyện
    private void updateTrainArmor() {
        if (Util.canDoWithTime(lastTimeAddTimeTrainArmor, 60000) && !Util.canDoWithTime(lastTimeAttack, 30000)) {
            if (this.player.nPoint.wearingTrainArmor) {
                for (Item.ItemOption io : this.player.inventory.trainArmor.itemOptions) {
                    if (io.optionTemplate.id == 9) {
                        if (io.param < 1000) {
                            io.param++;
                            InventoryServiceNew.gI().sendItemBody(player);
                        }
                        break;
                    }
                }
            }
            this.lastTimeAddTimeTrainArmor = System.currentTimeMillis();
        }
        if (Util.canDoWithTime(lastTimeSubTimeTrainArmor, 60000)) {
            for (Item item : this.player.inventory.itemsBag) {
                if (item.isNotNullItem()) {
                    if (ItemService.gI().isTrainArmor(item)) {
                        for (Item.ItemOption io : item.itemOptions) {
                            if (io.optionTemplate.id == 9) {
                                if (io.param > 0) {
                                    io.param--;
                                }
                            }
                        }
                    }
                } else {
                    break;
                }
            }
            for (Item item : this.player.inventory.itemsBox) {
                if (item.isNotNullItem()) {
                    if (ItemService.gI().isTrainArmor(item)) {
                        for (Item.ItemOption io : item.itemOptions) {
                            if (io.optionTemplate.id == 9) {
                                if (io.param > 0) {
                                    io.param--;
                                }
                            }
                        }
                    }
                } else {
                    break;
                }
            }
            this.lastTimeSubTimeTrainArmor = System.currentTimeMillis();
            InventoryServiceNew.gI().sendItemBags(player);
            Service.gI().point(this.player);
        }
    }

    private void updateVoHinh() {
//        if (this.player.nPoint.wearingVoHinh) {
        if (Util.canDoWithTime(lastTimeAttack, 10000) && this.player.nPoint.wearingVoHinh) {
            isVoHinh = true;
        } else {
            isVoHinh = false;
        }
//        }
    }

    public void dispose() {
        this.player = null;
    }

    private void updateHoaXuong() {
       
       try {
            if (this.player.nPoint.isxuong == true) {
                if (Util.canDoWithTime(lastTimeHoaXuong, 3000)) {
                    List<Player> players = new ArrayList<>();
                    List<Player> playersMap = this.player.zone.getNotBosses();
                    for (Player pl : playersMap) {
                        if (!this.player.equals(pl) && !pl.isPet && !pl.isDie()
                                && Util.getDistance(this.player, pl) <= 600) {
                            players.add(pl);
                        }
                    }
                    for (Player pl : players) {
                         EffectSkillService.gI().Setxuong(pl, System.currentTimeMillis(),1800000);
                         Service.gI().Send_Caitrang(pl);
                    ItemTimeService.gI().sendItemTime(pl, 5101, 1800000 / 1000);
                    Service.gI().chat(player, "Bạn Bị Hoá Xương");
                        Service.gI().chat(pl, xuongkho[Util.nextInt(0, xuongkho.length - 1)]);
                        PlayerService.gI().sendInfoHpMpMoney(pl);
                        Service.gI().Send_Info_NV(pl);
                    }
                    this.lastTimeHoaXuong = System.currentTimeMillis();
                }
            } else {
            }
        } catch (Exception e) {
            Logger.error("");
        }
    }}
