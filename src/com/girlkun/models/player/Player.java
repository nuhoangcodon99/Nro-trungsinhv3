package com.girlkun.models.player;

import BoMong.BoMong;
import com.arriety.card.Card;
import com.girlkun.models.map.MapMaBu.MapMaBu;
import com.girlkun.models.skill.PlayerSkill;

import java.util.List;

import com.girlkun.models.clan.Clan;
import com.girlkun.models.intrinsic.IntrinsicPlayer;
import com.girlkun.models.boss.list_boss.MiNuong;
import com.girlkun.models.item.Item;
import com.girlkun.models.item.ItemTime;
import com.girlkun.models.npc.specialnpc.MagicTree;
import com.girlkun.consts.ConstPlayer;
import com.girlkun.consts.ConstTask;
import com.girlkun.models.npc.specialnpc.MabuEgg;
import com.girlkun.models.mob.MobMe;
import com.girlkun.data.DataGame;
import com.girlkun.models.clan.ClanMember;
import com.girlkun.models.map.TrapMap;
import com.girlkun.models.map.Zone;
import com.girlkun.models.map.blackball.BlackBallWar;
import com.girlkun.models.map.doanhtrai.DoanhTraiService;
import com.girlkun.models.map.gas.GasService;
import com.girlkun.models.map.doanhtrai.ZombieService;
import com.girlkun.models.map.doanhtrai.Zombie;
import com.girlkun.models.map.mapMabu13h.MapMaBu13h;
import com.girlkun.models.map.nguhanhson.nguhs;
import com.girlkun.models.matches.IPVP;
import com.girlkun.models.matches.TYPE_LOSE_PVP;
import com.girlkun.models.matches.TYPE_PVP;
import com.girlkun.models.matches.pvp.DaiHoiVoThuat;
import com.girlkun.models.npc.specialnpc.BillEgg;
import com.girlkun.models.npc.specialnpc.cr1;
import com.girlkun.models.npc.specialnpc.cr2;
import com.girlkun.models.npc.specialnpc.cr3;
import com.girlkun.models.npc.specialnpc.cr4;
import com.girlkun.models.skill.Skill;
import com.girlkun.server.Manager;
import com.girlkun.services.Service;
import com.girlkun.server.io.MySession;
import com.girlkun.models.task.TaskPlayer;
import com.girlkun.network.io.Message;
import com.girlkun.server.Client;
import com.girlkun.services.EffectSkillService;
import com.girlkun.services.FriendAndEnemyService;
import com.girlkun.services.MapService;
import com.girlkun.services.NpcService;
import com.girlkun.services.PetService;
import com.girlkun.services.TaskService;
import com.girlkun.services.func.ChangeMapService;
import com.girlkun.services.func.ChonAiDay;
import com.girlkun.services.func.CombineNew;
import com.girlkun.services.func.SummonDragon;
import com.girlkun.services.func.TopService;
import com.girlkun.utils.Logger;
import com.girlkun.utils.Util;
import com.girlkun.models.boss.*;
import com.zaxxer.hikari.util.ConcurrentBag;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;

public class Player {

    //naubanh
    public int BanhChung;
    public int BanhTet;
    public int DuaHau;
    public int point_cauca;
    public int point_gapthu;
    public int point_vip;
    public boolean nhanquatanthu;
    public boolean nhanquavip1;
    public boolean nhanquavip2;
    public boolean nhanquavip3;
    public boolean nhanquavip4;
    public boolean nhanquavip5;
    public BoMong achievement;
    public long diemdanh;
    public byte vip;
    public byte typetrain;
     public byte pointKarin;
    public int expoff;
    public boolean istrain;
    public boolean istry;
    public boolean istry1;
    public boolean isfight;
    public boolean isfight1;
    public boolean seebossnotnpc;
    public long lastTimeHoiPhuc;
    public float DucNTdamethanmeo;
    public long timeoff = 0;
    public boolean isfake;
    public boolean isdem = false;
    public boolean lockPK;
    public Timer timerDHVT;
    public long rankSieuHang;
    public long numKillSieuHang;
    public boolean isBot = false;
    public boolean isReferee;
     public boolean isReferee2;
    public boolean isReferee1;
    public boolean playerTarger;
    public int vnd;
        public boolean haveBeQuynh;
         public boolean haveDuongTank;
          public int mapCongDuc;
                public boolean haveDuongTang;
    public boolean haveTauPayPay;
    public int diemhotong = 0; 
    public int mapHoTong;
    public long lastTimeHoTong;
      public boolean autodrop = false;
    public boolean autohoisinh = false;
    public boolean autodosat = false;
    public boolean batco = false;
    public Player _friendGiaoDich;
    public int goldChallenge;
    public boolean receivedWoodChest;
    public List<String> textRuongGo = new ArrayList<>();
    public MySession session;

    public boolean beforeDispose;
 public boolean autoHP = false;
    public boolean autoKI = false;
    public boolean autoSD = false;
    public boolean autoGiap = false;
    public boolean titleitem;
    public boolean titlett;
    public boolean isTitleUse;
    public boolean isTitleUse1;
    public long lastTimeTitle1;
     public boolean isTitleUse2;
    public long lastTimeTitle2;
    public boolean isTitleUse3;
    public boolean isTitleUse4;
    public long lastTimeTitle3;
     public long lastTimeTitle4;
    public boolean haveMiNuong;
     public Taixiu taixiu;
   
    public long lasttimechat;
    public boolean dhtang1;
    public boolean dhtang2;
    public boolean dhhanhtinh;

    public boolean isPet;
    public boolean isNewPet;
    public boolean isNewPet1;
    public boolean isBoss;
    public int NguHanhSonPoint = 0;
     public int carot = 0;
    public IPVP pvp;
    public int pointPvp;
    public byte maxTime = 30;
    public byte type = 0;

    public int mapIdBeforeLogout;
    public List<Zone> mapBlackBall;
    public List<Zone> mapMaBu;

    public Zone zone;
    public Zone mapBeforeCapsule;
    public List<Zone> mapCapsule;
    public List<Zone> mapMabu13h;
    public Pet pet;
    public NewPet newpet;
    public NewPet newpet1;
    public MobMe mobMe;
    public Location location;
    public SetClothes setClothes;
    public EffectSkill effectSkill;
    public MabuEgg mabuEgg;
    public BillEgg billEgg;
     public cr1 cr1;
     public cr2 cr2;
     public cr3 cr3;
     public cr4 cr4;
    public TaskPlayer playerTask;
    public ItemTime itemTime;
    public Fusion fusion;
    public MagicTree magicTree;
    public IntrinsicPlayer playerIntrinsic;
    public Inventory inventory;
    public PlayerSkill playerSkill;
  
   
    public NhiemvuChienthan chienthan;
    public CombineNew combineNew;
    public IDMark iDMark;
    public Charms charms;
    public EffectSkin effectSkin;
    public Gift gift;
    public NPoint nPoint;
    public RewardBlackBall rewardBlackBall;
    public EffectFlagBag effectFlagBag;
    public FightMabu fightMabu;

    public Clan clan;
    public ClanMember clanMember;

    public List<Friend> friends;
    public List<Enemy> enemies;

    public long id;
    public String name;
    public byte gender;
    public boolean isNewMember;
    public short head;
    public int capboss = 0;
    public long capvithu;
    public byte typePk;
    public int goldTai;
    public long last_time_dd;
    public int goldXiu;
    public byte cFlag;
    public SkillSpecial skillSpecial;

    public boolean haveTennisSpaceShip;

    public boolean justRevived;
    public long lastTimeRevived;

    public int violate;
    public byte totalPlayerViolate;
    public long timeChangeZone;
    public long lastTimeUseOption;

    public short idNRNM = -1;
    public short idGo = -1;
    public long lastTimePickNRNM;
    public int goldNormar;
    public int goldVIP;
    public long lastTimeWin;
    public boolean isWin;
    public List<Card> Cards = new ArrayList<>();
    public short idAura = -1;
    public int levelWoodChest;
    public List<Integer> idEffChar = new ArrayList<>();

    public Player() {
        lastTimeUseOption = System.currentTimeMillis();
        location = new Location();
        nPoint = new NPoint(this);
        inventory = new Inventory();
        playerSkill = new PlayerSkill(this);
        setClothes = new SetClothes(this);
        effectSkill = new EffectSkill(this);
        fusion = new Fusion(this);
        playerIntrinsic = new IntrinsicPlayer();
        rewardBlackBall = new RewardBlackBall(this);
        effectFlagBag = new EffectFlagBag();
        fightMabu = new FightMabu(this);
        //----------------------------------------------------------------------
        iDMark = new IDMark();
        combineNew = new CombineNew();
        playerTask = new TaskPlayer();
        friends = new ArrayList<>();
        enemies = new ArrayList<>();
        itemTime = new ItemTime(this);
        charms = new Charms();
        gift = new Gift(this);
        effectSkin = new EffectSkin(this);
        skillSpecial = new SkillSpecial(this);
        achievement = new BoMong(this);
    }

    //--------------------------------------------------------------------------
    public boolean isDie() {
        if (this.nPoint != null) {
            return this.nPoint.hp <= 0;
        }
        return true;
    }

    //--------------------------------------------------------------------------
    public void setSession(MySession session) {
        this.session = session;
    }

    public void sendMessage(Message msg) {
        if (this.session != null) {
            session.sendMessage(msg);
        }
    }

    public MySession getSession() {
        return this.session;
    }

    public boolean isPl() {
        return !isPet && !isBoss && !isNewPet && !isNewPet1 && !isfake;
    }

    public void update() {
        final Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(11);
        if (!this.beforeDispose) {
            try {
                if (this.istrain && !MapService.gI().isMapTrainOff(this, this.zone.map.mapId) && this.timeoff >= 30) {
                    ChangeMapService.gI().changeMapBySpaceShip(this, MapService.gI().getMapTrainOff(this), -1, 250);
                    congExpOff();
                    this.timeoff = 0;
                }
                if (!isdem && (hour >= 18 || hour < 5)) {
                    SummonDragon.gI().activeNight(this);
                    isdem = true;
                    Service.getInstance().sendThongBao(this, "Chúc bạn buổi tối vui vẻ");
                } else if (isdem && (hour >= 5 && hour < 18)) {
                    SummonDragon.gI().activeDay(this);
                    isdem = false;
                    Service.getInstance().sendThongBao(this, "Chúc bạn buổi sáng tốt lành");
                }
                if (!iDMark.isBan()) {

                    if (nPoint != null) {
                        nPoint.update();
                    }
                    if (fusion != null) {
                        fusion.update();
                    }
                    if (effectSkin != null) {
                        effectSkill.update();
                    }
                    if (mobMe != null) {
                        mobMe.update();
                    }
                    if (effectSkin != null) {
                        effectSkin.update();
                    }
                    if (pet != null) {
                        pet.update();
                    }
                    if (newpet != null) {
                        newpet.update();
                    }

                    if (newpet1 != null) {
                        newpet1.update();
                    }
                    if (magicTree != null) {
                        magicTree.update();
                    }
                    if (itemTime != null) {
                        itemTime.update();
                    }
                    if (this.lastTimeTitle1 != 0 && Util.canDoWithTime(this.lastTimeTitle1, 6000)) {
                        lastTimeTitle1 = 0;
                        isTitleUse1 = false;
                    }
                    if (this.lastTimeTitle2 != 0 && Util.canDoWithTime(this.lastTimeTitle2, 6000)) {
                        lastTimeTitle2 = 0;
                        isTitleUse2 = false;
                    }

                    if (this.lastTimeTitle3 != 0 && Util.canDoWithTime(this.lastTimeTitle3, 6000)) {
                        lastTimeTitle3 = 0;
                        isTitleUse3 = false;
                    }
                    if (this.lastTimeTitle4 != 0 && Util.canDoWithTime(this.lastTimeTitle4, 6000)) {
                        lastTimeTitle4 = 0;
                        isTitleUse4 = false;
                    }
                    DoanhTraiService.gI().updatePlayer(this);
                    nguhs.gI().update(this);
                    BlackBallWar.gI().update(this);
                    MapMaBu.gI().update(this);
                    MapMaBu13h.gI().update(this);
                    if (!isBoss && this.iDMark.isGotoFuture() && Util.canDoWithTime(this.iDMark.getLastTimeGoToFuture(), 6000)) {
                        ChangeMapService.gI().changeMapBySpaceShip(this, 102, -1, Util.nextInt(60, 200));
                        this.iDMark.setGotoFuture(false);
                    }
                    if (this.iDMark.isGoToBDKB() && Util.canDoWithTime(this.iDMark.getLastTimeGoToBDKB(), 6000)) {
                        ChangeMapService.gI().changeMapBySpaceShip(this, 135, -1, 35);
                        this.iDMark.setGoToBDKB(false);
                    }
                    if (!isBoss && this.iDMark.isGoToCDRD() && Util.canDoWithTime(this.iDMark.getLastTimeGoToBDKB(), 10000)) {
                        ChangeMapService.gI().changeMapInYard(this, 143, -1, 1108);
                        this.iDMark.setGoToCDRD(false);
                    }
                    if (this.isPl() && this.clan != null && this.clan.khiGas != null) {
                        GasService.gI().update(this);
                    }
                    if (this.iDMark.isGoToGas() && Util.canDoWithTime(this.iDMark.getLastTimeGotoGas(), 6000)) {
                        ChangeMapService.gI().changeMapBySpaceShip(this, 149, -1, 163);
                        this.iDMark.setGoToGas(false);
                    }
                    if (this.zone != null) {
                        TrapMap trap = this.zone.isInTrap(this);
                        if (trap != null) {
                            trap.doPlayer(this);
                        }
                    }
                    if (this.isPl() && this.inventory.itemsBody.get(7) != null) {
                        Item it = this.inventory.itemsBody.get(7);
                        if (it != null && it.isNotNullItem() && this.newpet == null) {
                            PetService.Pet2(this, it.template.head, it.template.body, it.template.leg);
                            Service.getInstance().point(this);
                        }
                    } else if (this.isPl() && newpet != null && newpet1 != null && !this.inventory.itemsBody.get(7).isNotNullItem()) {
                        newpet.dispose();
                        newpet = null;
                        newpet1.dispose();
                        newpet1 = null;
                    }
                     if ((this.isPl() || this.isPet) && this.inventory.itemsBody.size() == 16
                            && this.inventory.itemsBody.get(12) != null) {
                        Item it = this.inventory.itemsBody.get(12);
                        Item it1 = this.inventory.itemsBody.get(13);
                        Item it2 = this.inventory.itemsBody.get(14);
                        Item it3 = this.inventory.itemsBody.get(15);
                        Item it4 = this.inventory.itemsBody.get(16);
                        if (it != null && it.isNotNullItem()) {
                            if (it.template.type == 5) {
                                Service.gI().sendTitle(this, it.template.part);
                            }
                            Service.getInstance().sendFlagBag(this);
                        }
                        if (it1 != null && it1.isNotNullItem() && it1.template.type == 36) {                           
                            Service.gI().sendTitle(this, it1.template.part);
                            
                        }
                        if (it2 != null && it.isNotNullItem() && it2.template.type == 37) {                           
                            Service.gI().sendTitle(this, it2.template.part);
                            
                        }
                        if (it3 != null && it3.isNotNullItem() && it3.template.type == 38) {                           
                            Service.gI().sendTitle(this, it3.template.part);
                        }
                        if (it4 != null && it4.isNotNullItem() && it4.template.type == 102) {                           
                            Service.gI().sendTitle(this, it4.template.part);
                            
                        }
                    }
                    if (this.isPl() && isWin && this.zone.map.mapId == 51 && Util.canDoWithTime(lastTimeWin, 2000)) {
                        ChangeMapService.gI().changeMapBySpaceShip(this, 52, 0, -1);
                        isWin = false;
                    }
                } else {
                    if (Util.canDoWithTime(iDMark.getLastTimeBan(), 5000)) {
                        Client.gI().kickSession(session);
                    }
                }
            } catch (Exception e) {
                e.getStackTrace();
                Logger.logException(Player.class, e, "Lỗi tại player: " + this.name);
            }
        }
    }

    //--------------------------------------------------------------------------
    /*
     * {380, 381, 382}: ht lưỡng long nhất thể xayda trái đất
     * {383, 384, 385}: ht porata xayda trái đất
     * {391, 392, 393}: ht namếc
     * {870, 871, 872}: ht c2 trái đất
     * {873, 874, 875}: ht c2 namếc
     * {867, 878, 869}: ht c2 xayda
     */
    private static final short[][] idOutfitFusion = {
        {380, 381, 382}, {383, 384, 385}, {391, 392, 393},//bt1
        {873, 874, 875}, {870, 871, 872}, {867, 868, 869},
        {569, 570, 571}, {566, 567, 568}, {563, 564, 565},
        {1270, 1271, 1272}, {1273, 1274, 1275}, {1276, 1277, 1278},
        {1342, 1343, 1344}
    };
    private static final short[][] idhoaxuong = {
        {546, 548, 549}, {545, 548, 549},{547, 548, 549},
    };

    // Sua id vat pham muon co aura lai
    public byte getAura() {
        if (this.inventory.itemsBody.isEmpty() || this.inventory.itemsBody.size() < 10) {
            return -1;
        }
        Item item = this.inventory.itemsBody.get(5);
        if (!item.isNotNullItem()) {
            return -1;
        }
        if (item.template.id == 1121) {
            return 10;
        } else if (item.template.id == 1128) {

            return 15;
        } else if (item.template.id == 884) {

            return 14;
             } else if (item.template.id == 945) {

            return 34;
             } else if (item.template.id == 946) {

            return 35;
             } else if (item.template.id == 947) {

            return 36;
             } else if (item.template.id == 948) {

            return 37;
        } else if (item.template.id == 1125 || item.template.id == 2058 || item.template.id == 2055) {
            return 14;
        } else if (item.template.id == 2045) {
            return 13;
        } else {
            return -1;
        }

    }

    // hieu ung theo set
    public byte getEffFront() {
        if (this.inventory.itemsBody.isEmpty() || this.inventory.itemsBody.size() < 10) {
            return -1;
        }
        int levelAo = 0;
        Item.ItemOption optionLevelAo = null;
        int levelQuan = 0;
        Item.ItemOption optionLevelQuan = null;
        int levelGang = 0;
        Item.ItemOption optionLevelGang = null;
        int levelGiay = 0;
        Item.ItemOption optionLevelGiay = null;
        int levelNhan = 0;
        Item.ItemOption optionLevelNhan = null;
        Item itemAo = this.inventory.itemsBody.get(0);
        Item itemQuan = this.inventory.itemsBody.get(1);
        Item itemGang = this.inventory.itemsBody.get(2);
        Item itemGiay = this.inventory.itemsBody.get(3);
        Item itemNhan = this.inventory.itemsBody.get(4);
        for (Item.ItemOption io : itemAo.itemOptions) {
            if (io.optionTemplate.id == 72) {
                levelAo = io.param;
                optionLevelAo = io;
                break;
            }
        }
        for (Item.ItemOption io : itemQuan.itemOptions) {
            if (io.optionTemplate.id == 72) {
                levelQuan = io.param;
                optionLevelQuan = io;
                break;
            }
        }
        for (Item.ItemOption io : itemGang.itemOptions) {
            if (io.optionTemplate.id == 72) {
                levelGang = io.param;
                optionLevelGang = io;
                break;
            }
        }
        for (Item.ItemOption io : itemGiay.itemOptions) {
            if (io.optionTemplate.id == 72) {
                levelGiay = io.param;
                optionLevelGiay = io;
                break;
            }
        }
        for (Item.ItemOption io : itemNhan.itemOptions) {
            if (io.optionTemplate.id == 72) {
                levelNhan = io.param;
                optionLevelNhan = io;
                break;
            }
        }
        if (optionLevelAo != null && optionLevelQuan != null && optionLevelGang != null && optionLevelGiay != null && optionLevelNhan != null
                && levelAo >= 8 && levelQuan >= 8 && levelGang >= 8 && levelGiay >= 8 && levelNhan >= 8) {
            return 8;
        } else if (optionLevelAo != null && optionLevelQuan != null && optionLevelGang != null && optionLevelGiay != null && optionLevelNhan != null
                && levelAo >= 7 && levelQuan >= 7 && levelGang >= 7 && levelGiay >= 7 && levelNhan >= 7) {
            return 7;
        } else if (optionLevelAo != null && optionLevelQuan != null && optionLevelGang != null && optionLevelGiay != null && optionLevelNhan != null
                && levelAo >= 6 && levelQuan >= 6 && levelGang >= 6 && levelGiay >= 6 && levelNhan >= 6) {
            return 6;
        } else if (optionLevelAo != null && optionLevelQuan != null && optionLevelGang != null && optionLevelGiay != null && optionLevelNhan != null
                && levelAo >= 5 && levelQuan >= 5 && levelGang >= 5 && levelGiay >= 5 && levelNhan >= 5) {
            return 5;
        } else if (optionLevelAo != null && optionLevelQuan != null && optionLevelGang != null && optionLevelGiay != null && optionLevelNhan != null
                && levelAo >= 4 && levelQuan >= 4 && levelGang >= 4 && levelGiay >= 4 && levelNhan >= 4) {
            return 4;
        } else {
            return -1;
        }
    }

    public short getHead() {
        if (effectSkill != null && effectSkill.isMonkey) {
            return (short) ConstPlayer.HEADMONKEY[effectSkill.levelMonkey - 1];
        } else if (effectSkill != null && effectSkill.isSocola) {
            return 557;
            } else if (effectSkill != null && effectSkill.ismabu) {
            return 412;
             } else if (effectSkill != null && effectSkill.isDa) {
            return 454;
            } else if (effectSkill != null && effectSkill.isxuong) {
            return idhoaxuong[this.gender == ConstPlayer.NAMEC ? 2 : 0][0];
            } else if (effectSkill != null && effectSkill.isCarot) {
            return 406;
        } else if (fusion != null && fusion.typeFusion != ConstPlayer.NON_FUSION) {
            if (fusion.typeFusion == ConstPlayer.LUONG_LONG_NHAT_THE) {
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 0][0];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 1][0];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
                return idOutfitFusion[3 + this.gender][0];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
                return idOutfitFusion[6 + this.gender][0];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
                return idOutfitFusion[9 + this.gender][0];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_GOGETA) {
                return idOutfitFusion[12][0];
            }
        } else if (inventory != null && inventory.itemsBody.get(5).isNotNullItem()) {
            int head = inventory.itemsBody.get(5).template.head;
            if (head != -1) {
                return (short) head;
            }
        }
        return this.head;
    }

    public short getBody() {
        if (effectSkill != null && effectSkill.isMonkey) {
            return 193;
        } else if (effectSkill != null && effectSkill.isSocola) {
            return 558;
            } else if (effectSkill != null && effectSkill.ismabu) {
            return 413;
             } else if (effectSkill != null && effectSkill.isDa) {
            return 455;
             } else if (effectSkill != null && effectSkill.isxuong) {
            return idhoaxuong[this.gender == ConstPlayer.NAMEC ? 2 : 0][1];
            } else if (effectSkill != null && effectSkill.isCarot) {
            return 407;
        } else if (fusion != null && fusion.typeFusion != ConstPlayer.NON_FUSION) {
            if (fusion.typeFusion == ConstPlayer.LUONG_LONG_NHAT_THE) {
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 0][1];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 1][1];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
                return idOutfitFusion[3 + this.gender][1];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
                return idOutfitFusion[6 + this.gender][1];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
                return idOutfitFusion[9 + this.gender][1];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_GOGETA) {
                return idOutfitFusion[12][1];
            }
        } else if (inventory != null && inventory.itemsBody.get(5).isNotNullItem()) {
            int body = inventory.itemsBody.get(5).template.body;
            if (body != -1) {
                return (short) body;
            }
        }
        if (inventory != null && inventory.itemsBody.get(0).isNotNullItem()) {
            return inventory.itemsBody.get(0).template.part;
        }
        return (short) (gender == ConstPlayer.NAMEC ? 59 : 57);
    }

    public short getLeg() {
        if (effectSkill != null && effectSkill.isMonkey) {
            return 194;
        } else if (effectSkill != null && effectSkill.isSocola) {
            return 559;
            } else if (effectSkill != null && effectSkill.ismabu) {
            return 414;
             } else if (effectSkill != null && effectSkill.isDa) {
            return 456;
             } else if (effectSkill != null && effectSkill.isxuong) {
          return idhoaxuong[this.gender == ConstPlayer.NAMEC ? 2 : 0][2];
            } else if (effectSkill != null && effectSkill.isCarot) {
            return 408;
        } else if (fusion != null && fusion.typeFusion != ConstPlayer.NON_FUSION) {
            if (fusion.typeFusion == ConstPlayer.LUONG_LONG_NHAT_THE) {
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 0][2];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 1][2];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
                return idOutfitFusion[3 + this.gender][2];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
                return idOutfitFusion[6 + this.gender][2];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
                return idOutfitFusion[9 + this.gender][2];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_GOGETA) {
                return idOutfitFusion[12][2];
            }
        } else if (inventory != null && inventory.itemsBody.get(5).isNotNullItem()) {
            int leg = inventory.itemsBody.get(5).template.leg;
            if (leg != -1) {
                return (short) leg;
            }
        }
        if (inventory != null && inventory.itemsBody.get(1).isNotNullItem()) {
            return inventory.itemsBody.get(1).template.part;
        }
        return (short) (gender == 1 ? 60 : 58);
    }

    public short getFlagBag() {
        if (this.iDMark.isHoldBlackBall()) {
            return 31;
        } else if (this.idNRNM >= 353 && this.idNRNM <= 359) {
            return 30;
        }
        if (this.inventory.itemsBody.size() >= 10) {
            if (this.inventory.itemsBody.get(8).isNotNullItem()) {
                return this.inventory.itemsBody.get(8).template.part;
            }
        }
        if (TaskService.gI().getIdTask(this) == ConstTask.TASK_3_2) {
            return 28;
        }
        if (this.clan != null) {
            return (short) this.clan.imgId;
        }
        return -1;
    }

    public short getMount() {
        if (this.inventory.itemsBody.isEmpty() || this.inventory.itemsBody.size() < 10) {
            return -1;
        }
        Item item = this.inventory.itemsBody.get(9);
        if (!item.isNotNullItem()) {
            return -1;
        }
        if (item.template.type == 24) {
            if (item.template.gender == 3 || item.template.gender == this.gender) {
                return item.template.id;
            } else {
                return -1;
            }
        } else {
            if (item.template.id < 500) {
                return item.template.id;
            } else {
                return (short) DataGame.MAP_MOUNT_NUM.get(String.valueOf(item.template.id));
            }
        }

    }

    //--------------------------------------------------------------------------
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
        if (!this.isDie()) {
            if (plAtt != null) {
                switch (plAtt.playerSkill.skillSelect.template.id) {
                    case Skill.KAMEJOKO:
                    case Skill.MASENKO:
                    case Skill.ANTOMIC:
                        if (this.nPoint.voHieuChuong > 0) {
                            com.girlkun.services.PlayerService.gI().hoiPhuc(this, 0, damage * this.nPoint.voHieuChuong / 100);
                            return 0;
                        }
                }
            }
            if (!piercing && Util.isTrue(this.nPoint.tlNeDon, 100)) {
                return 0;
            }
            damage = this.nPoint.subDameInjureWithDeff(damage);
            if (!piercing && effectSkill.isShielding) {
                if (damage > nPoint.hpMax) {
                    EffectSkillService.gI().breakShield(this);
                }
                damage = 1;
            }
            if (isMobAttack && this.charms.tdBatTu > System.currentTimeMillis() && damage >= this.nPoint.hp) {
                damage = this.nPoint.hp - 1;
            }

            this.nPoint.subHP(damage);
            if (isDie()) {
                if (this.zone.map.mapId == 112) {
                    plAtt.pointPvp++;
                }
                if (this.isfight || this.isfight1) {
                    this.isfight = false;
                    this.isfight1 = false;
                    this.haveDuongTang = false;
                    this.seebossnotnpc = false;
                    this.zone.load_Me_To_Another(this);
                    this.zone.load_Another_To_Me(this);
                }
                setDie(plAtt);
            }
            return damage;
        } else {
            return 0;
        }
    }
 public void checkAnThan(Player plAtt) {
        if (plAtt != null && (plAtt.isPl() || plAtt.isPet) && plAtt.effectSkill.isAnThan) {
            EffectSkillService.gI().removeAnThan(plAtt);
        }
    }
    protected void setDie(Player plAtt) {
        //xóa phù
        if (this.effectSkin.xHPKI > 1) {
            this.effectSkin.xHPKI = 1;
            Service.gI().point(this);
        }
        //xóa tụ skill đặc biệt
        this.playerSkill.prepareQCKK = false;
        this.playerSkill.prepareLaze = false;
        this.playerSkill.prepareTuSat = false;
        //xóa hiệu ứng skill
        this.effectSkill.removeSkillEffectWhenDie();
        //
        nPoint.setHp(0);
        nPoint.setMp(0);
        //xóa trứng
        if (this.mobMe != null) {
            this.mobMe.mobMeDie();
        }
        Service.gI().charDie(this);
        //add kẻ thù
        if (!this.isPet && !this.isNewPet && !this.isBoss && plAtt != null && !plAtt.isPet
                && !plAtt.isNewPet && !plAtt.isBoss) {
            if (!plAtt.itemTime.isUseAnDanh) {
                FriendAndEnemyService.gI().addEnemy(this, plAtt);
            }
        }
        if (this.isPl() && plAtt != null && plAtt.isPl()) {
            plAtt.achievement.plusCount(3);
        }
        //kết thúc pk
        if (this.pvp != null) {
            this.pvp.lose(this, TYPE_LOSE_PVP.DEAD);
        }
//        PVPServcice.gI().finishPVP(this, PVP.TYPE_DIE);
        BlackBallWar.gI().dropBlackBall(this);
    }

    public void setDieLv(Player plAtt) {
        if (plAtt != null) {
            // xóa phù
            if (this.effectSkin.xHPKI > 1) {
                this.effectSkin.xHPKI = 1;
                Service.gI().point(this);
            }
            // xóa tụ skill đặc biệt
            this.playerSkill.prepareQCKK = false;
            this.playerSkill.prepareLaze = false;
            this.playerSkill.prepareTuSat = false;
            // xóa hiệu ứng skill
            this.effectSkill.removeSkillEffectWhenDie();
            //
            nPoint.setHp(0);
            nPoint.setMp(0);
            // xóa trứng
            if (this.mobMe != null) {
                this.mobMe.mobMeDie();
            }
            Service.gI().charDie(this);
            // add kẻ thù
            if (!this.isPet && !this.isNewPet && !this.isBoss && plAtt != null && !plAtt.isPet
                    && !plAtt.isNewPet && !plAtt.isBoss) {
                if (!plAtt.itemTime.isUseAnDanh) {
                    FriendAndEnemyService.gI().addEnemy(this, plAtt);
                }
            }
            // kết thúc pk
            if (this.pvp != null) {
                this.pvp.lose(this, TYPE_LOSE_PVP.DEAD);
            }
            // PVPServcice.gI().finishPVP(this, PVP.TYPE_DIE);
            BlackBallWar.gI().dropBlackBall(this);
        }
    }

    //--------------------------------------------------------------------------
    public void setClanMember() {
        if (this.clanMember != null) {
            this.clanMember.powerPoint = this.nPoint.power;
            this.clanMember.head = this.getHead();
            this.clanMember.body = this.getBody();
            this.clanMember.leg = this.getLeg();
        }
    }

    public boolean isAdmin() {
        return this.session.isAdmin;
    }

    public void congExpOff() {
        long exp = this.nPoint.getexp() * this.timeoff;
        Service.gI().addSMTN(this, (byte) 2, exp, false);
        NpcService.gI().createTutorial(this, 536, "Bạn tăng được " + exp + " sức mạnh trong thời gian " + this.timeoff + " phút tập luyện Offline");
    }

    public void setJustRevivaled() {
        this.justRevived = true;
        this.lastTimeRevived = System.currentTimeMillis();
    }

    public void preparedToDispose() {

    }

    public void dispose() {
        if (pet != null) {
            pet.dispose();
            pet = null;
        }
        if (newpet != null) {
            newpet.dispose();
            newpet = null;
        }
        if (mapBlackBall != null) {
            mapBlackBall.clear();
            mapBlackBall = null;
        }
        zone = null;
        mapBeforeCapsule = null;
        if (mapMaBu != null) {
            mapMaBu.clear();
            mapMaBu = null;
        }
        if (mapMabu13h != null) {
            mapMabu13h.clear();
            mapMabu13h = null;
        }
        if (billEgg != null) {
            billEgg.dispose();
            billEgg = null;
        }
        if (cr1 != null) {
            cr1.dispose();
            cr1 = null;
        }
        if (cr2 != null) {
            cr2.dispose();
            cr2 = null;
        }
        if (cr3 != null) {
            cr3.dispose();
            cr3 = null;
        }
        if (cr4 != null) {
            cr4.dispose();
            cr4 = null;
        }
        zone = null;
        mapBeforeCapsule = null;
        if (mapCapsule != null) {
            mapCapsule.clear();
            mapCapsule = null;
        }
        if (mobMe != null) {
            mobMe.dispose();
            mobMe = null;
        }
        location = null;
        if (setClothes != null) {
            setClothes.dispose();
            setClothes = null;
        }
        if (effectSkill != null) {
            effectSkill.dispose();
            effectSkill = null;
        }
        if (mabuEgg != null) {
            mabuEgg.dispose();
            mabuEgg = null;
        }
        if (playerTask != null) {
            playerTask.dispose();
            playerTask = null;
        }
        if (itemTime != null) {
            itemTime.dispose();
            itemTime = null;
        }
        if (fusion != null) {
            fusion.dispose();
            fusion = null;
        }
        if (magicTree != null) {
            magicTree.dispose();
            magicTree = null;
        }
        if (skillSpecial != null) {
            skillSpecial.dispose();
            skillSpecial = null;
        }
        if (playerIntrinsic != null) {
            playerIntrinsic.dispose();
            playerIntrinsic = null;
        }
        if (inventory != null) {
            inventory.dispose();
            inventory = null;
        }
        if (playerSkill != null) {
            playerSkill.dispose();
            playerSkill = null;
        }
        if (combineNew != null) {
            combineNew.dispose();
            combineNew = null;
        }
        if (iDMark != null) {
            iDMark.dispose();
            iDMark = null;
        }
        if (charms != null) {
            charms.dispose();
            charms = null;
        }
        if (effectSkin != null) {
            effectSkin.dispose();
            effectSkin = null;
        }
        if (gift != null) {
            gift.dispose();
            gift = null;
        }
        if (nPoint != null) {
            nPoint.dispose();
            nPoint = null;
        }
        if (rewardBlackBall != null) {
            rewardBlackBall.dispose();
            rewardBlackBall = null;
        }
        if (effectFlagBag != null) {
            effectFlagBag.dispose();
            effectFlagBag = null;
        }
        if (pvp != null) {
            pvp.dispose();
            pvp = null;
        }
        effectFlagBag = null;
        clan = null;
        clanMember = null;
        friends = null;
        enemies = null;
        session = null;
        name = null;
    }

    public void setfight(byte typeFight, byte typeTatget) {

        try {
            if (typeFight == (byte) 0 && typeTatget == (byte) 0) {
                this.istry = true;
            }
            if (typeFight == (byte) 0 && typeTatget == (byte) 1) {
                this.istry1 = true;
            }
            if (typeFight == (byte) 1 && typeTatget == (byte) 0) {
                this.isfight = true;
            }
            if (typeFight == (byte) 1 && typeTatget == (byte) 1) {
                this.haveDuongTang = true;
            }
            if (typeFight == (byte) 1 && typeTatget == (byte) 1) {
                this.isfight1 = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean IsActiveMaster() {

        if (this.istry || this.isfight) {
            this.istry = true;
        }

        return false;
    }

    public void rsfight() {
        if (this.istry) {
            this.istry = false;
        }
        if (this.istry1) {
            this.istry1 = false;
        }
        if (this.isfight) {
            this.isfight = false;
        }
        if (this.isfight1) {
            this.isfight1 = false;
        }
    }

    public boolean IsTry0() {
        if (this.istry && this.isfight) {
            return true;
        }
        return false;
    }

    public boolean IsTry1() {
        if (this.istry && this.isfight1) {
            return true;
        }
        return false;
    }

    public boolean IsFigh0() {
        if (this.istry && this.isfight1) {
            return true;
        }
        return false;
    }

    public String percentGold(int type) {
        try {
            if (type == 0) {
                double percent = ((double) this.goldNormar / ChonAiDay.gI().goldNormar) * 100;
                return String.valueOf(Math.ceil(percent));
            } else if (type == 1) {
                double percent = ((double) this.goldVIP / ChonAiDay.gI().goldVip) * 100;
                return String.valueOf(Math.ceil(percent));
            }
        } catch (ArithmeticException e) {
            return "0";
        }
        return "0";
    }
}
