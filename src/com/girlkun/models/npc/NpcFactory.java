package com.girlkun.models.npc;

import com.arriety.MaQuaTang.MaQuaTangManager;
import com.girlkun.consts.ConstDataEvent;
import com.girlkun.consts.ConstMap;
import com.girlkun.services.*;
import com.girlkun.consts.ConstNpc;
import com.girlkun.consts.ConstPlayer;
import com.girlkun.consts.ConstTask;
import com.girlkun.consts.cn;
import com.girlkun.models.boss.list_boss.MiNuong;
import com.girlkun.models.boss.list_boss.DuongTank;
import com.girlkun.data.DataGame;
import com.girlkun.database.GirlkunDB;
import com.girlkun.models.map.ConDuongRanDoc.ConDuongRanDoc;
import com.girlkun.models.map.doanhtrai.Zombie;

import com.girlkun.models.map.doanhtrai.ZombieService;
import com.girlkun.models.map.ConDuongRanDoc.ConDuongRanDocService;
import com.girlkun.jdbc.daos.PlayerDAO;
import com.girlkun.models.boss.Boss;
import com.girlkun.models.boss.BossData;
import com.girlkun.models.boss.BossData;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossManager;
import com.girlkun.models.boss.list_boss.Broly.nvt;
import com.girlkun.models.boss.list_boss.Broly.ThachDauGoJo;
import com.girlkun.models.boss.list_boss.NhanBan;

//import com.girlkun.models.boss.list_boss.TrainOffline.Bill;
import com.girlkun.models.boss.list_boss.TrainOffline.MeoThan;
import com.girlkun.models.boss.list_boss.TrainOffline.ThanVuTru;
import com.girlkun.models.boss.list_boss.TrainOffline.Thuongde;
import com.girlkun.models.boss.list_boss.TrainOffline.ToSuKaio;
import com.girlkun.models.boss.list_boss.TrainOffline.Yajiro;
import com.girlkun.models.clan.Clan;
import com.girlkun.models.clan.ClanMember;
import com.girlkun.services.func.TaiXiu;

import java.util.HashMap;
import java.util.List;

import com.girlkun.services.func.ChangeMapService;
import com.girlkun.services.func.SummonDragon;

import static com.girlkun.services.func.SummonDragon.SHENRON_1_STAR_WISHES_1;
import static com.girlkun.services.func.SummonDragon.SHENRON_1_STAR_WISHES_2;
import static com.girlkun.services.func.SummonDragon.SHENRON_SAY;
//import static com.girlkun.services.func.CombineServiceNew.NANG_HUY_DIET_LEN_SKH_VIP;

import com.girlkun.models.player.Player;
import com.girlkun.models.item.Item;
import com.girlkun.models.item.Item.ItemOption;
import com.girlkun.models.map.ConDuongRanDoc.ConDuongRanDoc;
import com.girlkun.models.map.challenge.MartialCongressService;
import com.girlkun.models.map.vodai.MartialCongressServices;
import com.girlkun.models.map.Map;
import com.girlkun.models.map.Zone;
import com.girlkun.models.map.blackball.BlackBallWar;
import com.girlkun.models.map.MapMaBu.MapMaBu;
import com.girlkun.models.map.bando.BanDoKhoBau;
import com.girlkun.models.map.bando.BanDoKhoBauService;
import com.girlkun.models.map.daihoi.DaiHoiManager;
import com.girlkun.models.map.doanhtrai.DoanhTrai;
import com.girlkun.models.map.doanhtrai.DoanhTraiService;
import com.girlkun.models.map.gas.Gas;
import com.girlkun.models.map.gas.GasService;
import com.girlkun.models.map.mapMabu13h.MapMaBu13h;
import com.girlkun.models.map.nguhanhson.nguhs;
import com.girlkun.models.player.Inventory;
import com.girlkun.models.player.NPoint;
import com.girlkun.models.matches.PVPService;
import com.girlkun.models.matches.TOP;
import com.girlkun.models.matches.pvp.DaiHoiVoThuat;
import com.girlkun.models.matches.pvp.DaiHoiVoThuatService;
import com.girlkun.models.mob.Mob;
import com.girlkun.models.shop.ShopServiceNew;
import com.girlkun.models.skill.Skill;
import com.girlkun.network.io.Message;
import com.girlkun.result.GirlkunResultSet;
import com.girlkun.server.Client;
import com.girlkun.server.Maintenance;
import com.girlkun.server.Manager;
import com.girlkun.server.ServerManager;
import com.girlkun.server.ServerNotify;
import com.girlkun.services.func.CombineServiceNew;
import com.girlkun.services.func.Input;
import com.girlkun.services.func.LuckyRound;
import com.girlkun.services.func.TopService;
import com.girlkun.utils.Logger;
import com.girlkun.utils.TimeUtil;
import com.girlkun.utils.Util;
import java.util.ArrayList;
import com.girlkun.services.func.ChonAiDay;
import static com.girlkun.services.func.CombineServiceNew.DOI_DIEM;
import static com.girlkun.services.func.CombineServiceNew.NANG_HUY_DIET_LEN_SKH_VIP;
//import static com.girlkun.services.func.CombineServiceNew.NANG_TL_LEN_HUY_DIET;
import com.girlkun.utils.SkillUtil;
import com.kygui.ItemKyGui;
import com.kygui.ShopKyGuiService;
import com.kygui.ShopKyGuiManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

import java.util.logging.Level;

public class NpcFactory {

    private static final int COST_HD = 50000000;
protected Player playerTarger;
    private static boolean nhanVang = false;
    private static boolean nhanDeTu = false;

    //playerid - object
   
     public static final java.util.Map<Long, Object> PLAYERID_OBJECT = new HashMap<Long, Object>();
    private NpcFactory() {

    }

    private static Npc kyGui(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    createOtherMenu(player, 0, "Cửa hàng chúng tôi chuyên mua bán hàng hiệu, hàng độc, cảm ơn bạn đã ghé thăm.", "Hướng\ndẫn\nthêm", "Mua bán\nKý gửi", "Từ chối");
                }
            }

            @Override
            public void confirmMenu(Player pl, int select) {
                if (canOpenNpc(pl)) {
                    switch (select) {
                        case 0:
                            Service.getInstance().sendPopUpMultiLine(pl, tempId, avartar, "Cửa hàng chuyên nhận ký gửi mua bán vật phẩm\bChỉ với 5 hồng ngọc\bGiá trị ký gửi 10k-200Tr vàng hoặc 2-2k ngọc\bMột người bán, vạn người mua, mại dô, mại dô");
                            break;
                        case 1:
                            ShopKyGuiService.gI().openShopKyGui(pl);
                            break;

                    }
                }
            }
        };
    }

    private static Npc poTaGe(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 140) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Đa vũ trụ song song \b|7|Con muốn gọi con trong đa vũ trụ \b|1|Với giá 200tr vàng không?", "Gọi Boss\nNhân bản", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 140) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0: {
                                    Boss oldBossClone = BossManager.gI().getBossById(Util.createIdBossClone((int) player.id));
                                    if (oldBossClone != null) {
                                        this.npcChat(player, "Nhà ngươi hãy tiêu diệt Boss lúc trước gọi ra đã, con boss đó đang ở khu " + oldBossClone.zone.zoneId);
                                    } else if (player.inventory.gold < 200_000_000) {
                                        this.npcChat(player, "Nhà ngươi không đủ 200 Triệu vàng ");
                                    } else {
                                        List<Skill> skillList = new ArrayList<>();
                                        for (byte i = 0; i < player.playerSkill.skills.size(); i++) {
                                            Skill skill = player.playerSkill.skills.get(i);
                                            if (skill.point > 0) {
                                                skillList.add(skill);
                                            }
                                        }
                                        int[][] skillTemp = new int[skillList.size()][3];
                                        for (byte i = 0; i < skillList.size(); i++) {
                                            Skill skill = skillList.get(i);
                                            if (skill.point > 0) {
                                                skillTemp[i][0] = skill.template.id;
                                                skillTemp[i][1] = skill.point;
                                                skillTemp[i][2] = skill.coolDown;
                                            }
                                        }
                                        BossData bossDataClone = new BossData(
                                                "Nhân Bản" + player.name,
                                                player.gender,
                                                new short[]{player.getHead(), player.getBody(), player.getLeg(), player.getFlagBag(), player.idAura, player.getEffFront()},
                                                player.nPoint.dame,
                                                new int[]{player.nPoint.hpMax},
                                                new int[]{140},
                                                skillTemp,
                                                new String[]{"|-2|Boss nhân bản đã xuất hiện rồi"}, //text chat 1
                                                new String[]{"|-1|Ta sẽ chiếm lấy thân xác của ngươi hahaha!"}, //text chat 2
                                                new String[]{"|-1|Lần khác ta sẽ xử đẹp ngươi"}, //text chat 3
                                                60
                                        );

                                        try {
                                            new NhanBan(Util.createIdBossClone((int) player.id), bossDataClone, player.zone);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        //trừ vàng khi gọi boss
                                        player.inventory.gold -= 200_000_000;
                                        Service.gI().sendMoney(player);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        };
    }

    private static Npc quyLaioKame(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        if (TaskService.gI().getIdTask(player) == ConstTask.TASK_18_4) {
                            TaskService.gI().sendNextTaskMain(player);
                        } else if (TaskService.gI().getIdTask(player) == ConstTask.TASK_16_4) {
                            TaskService.gI().sendNextTaskMain(player);
                        }
                        if (player.getSession().is_gift_box) {
//                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Chào con, con muốn ta giúp gì nào?", "Giải tán bang hội", "Nhận quà\nđền bù");
                        } else {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Chào con, con muốn ta giúp gì nào?",
                                    "Giải tán bang hội", "Lãnh địa Bang Hội", "Bản Đồ Kho Báu", "Map Cung Trăng", "Mừng Quà Tết");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0:
                                Clan clan = player.clan;
                                if (clan != null) {
                                    ClanMember cm = clan.getClanMember((int) player.id);
                                    if (cm != null) {
                                        if (clan.members.size() > 1) {
                                            Service.gI().sendThongBao(player, "Bang phải còn một người");
                                            break;
                                        }
                                        if (!clan.isLeader(player)) {
                                            Service.gI().sendThongBao(player, "Phải là bang chủ");
                                            break;
                                        }
//                                        
                                        NpcService.gI().createMenuConMeo(player, ConstNpc.CONFIRM_DISSOLUTION_CLAN, -1, "Con có chắc chắn muốn giải tán bang hội không? Ta cho con 2 lựa chọn...",
                                                "Yes you do!", "Từ chối!");
                                    }
                                    break;
                                }
                                Service.gI().sendThongBao(player, "Có bang hội đâu ba!!!");
                                break;
                            case 1:
                                if (player.getSession().player.nPoint.power >= 39999999999L) {

                                    ChangeMapService.gI().changeMapBySpaceShip(player, 153, -1, 432);
                                } else {
                                    this.npcChat(player, "Bạn chưa đủ 40 tỷ sức mạnh để vào");
                                }
                                break;
                            case 2:
                                if (player.clan != null) {
                                    if (player.clan.banDoKhoBau != null) {
                                        this.createOtherMenu(player, ConstNpc.MENU_OPENED_DBKB,
                                                "Bang hội của con đang đi tìm kho báu dưới biển cấp độ "
                                                + player.clan.banDoKhoBau.level
                                                + "\nCon có muốn đi theo không?",
                                                "Đồng ý", "Từ chối");
                                    } else {

                                        this.createOtherMenu(player, ConstNpc.MENU_OPEN_DBKB,
                                                "Đây là bản đồ kho báu \nCác con cứ yên tâm lên đường\n"
                                                + "Ở đây có ta lo\nNhớ chọn cấp độ vừa sức mình nhé",
                                                "Chọn\ncấp độ", "Từ chối");
                                    }
                                } else {
                                    this.npcChat(player, "Con phải có bang hội ta mới có thể cho con đi");
                                }
                                break;
                            case 3:
                                ChangeMapService.gI().changeMap(player, 174, -1, 60, 384);
                                break;
                            case 4:
                                this.npcChat(player, "Con Muốn Tặng Quà Tết Cho Ta Sao?");
                                Item hopquamungtet = null;

                                try {
                                    hopquamungtet = InventoryServiceNew.gI().findItemBag(player, 1449);
                                } catch (Exception e) {
                                }
                                if (hopquamungtet == null || hopquamungtet.quantity < 2) {
                                    this.npcChat(player, "Con Làm Gì Có Hộp Quà Nào");
                                } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                    this.npcChat(player, "Hành Trang Của Con Không Đủ Chỗ Trống");
                                } else {
                                    InventoryServiceNew.gI().subQuantityItemsBag(player, hopquamungtet, 2);
                                    Item ct = ItemService.gI().createNewItem((short) 1447);
                                    ct.itemOptions.add(new ItemOption(93, 5));
                                    InventoryServiceNew.gI().addItemBag(player, ct);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    Service.gI().sendThongBao(player, "Bạn nhận được 1 Bao Lì Xì");
                                    this.npcChat(player, "Cảm Ơn Con! Chúc Con Năm Mới Vui Vẻ");
                                }
                                break;
                        }

                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPENED_DBKB) {
                        switch (select) {
                            case 0:
                                if (player.isAdmin() || player.nPoint.power >= BanDoKhoBau.POWER_CAN_GO_TO_DBKB) {
                                    ChangeMapService.gI().goToDBKB(player);
                                } else {
                                    this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                            + Util.numberToMoney(BanDoKhoBau.POWER_CAN_GO_TO_DBKB));
                                }
                                break;

                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPEN_DBKB) {
                        switch (select) {
                            case 0:
                                if (player.isAdmin() || player.nPoint.power >= BanDoKhoBau.POWER_CAN_GO_TO_DBKB) {
                                    Input.gI().createFormChooseLevelBDKB(player);
                                } else {
                                    this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                            + Util.numberToMoney(BanDoKhoBau.POWER_CAN_GO_TO_DBKB));
                                }
                                break;
                        }

                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_ACCEPT_GO_TO_BDKB) {
                        switch (select) {
                            case 0:
                                BanDoKhoBauService.gI().openBanDoKhoBau(player,
                                        Byte.parseByte(String.valueOf(PLAYERID_OBJECT.get(player.id))));
                                break;
                        }
                    }
                }
            }
        };
    }

    public static Npc truongLaoGuru(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        super.openBaseMenu(player);
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {

                }
            }
        };
    }

    public static Npc vuaVegeta(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        super.openBaseMenu(player);
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {

                }
            }
        };
    }

   public static Npc onggianoel(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            public void Npcchat(Player player) {
                String[] chat = {
                    "Hãy Dẫn Ta Về Nhà",
                    "Ngày Hôm Ấy Em Đi Trong Mưa",
                    "Thế Nhưng Lại Quên Tim Không Khóa Cửa",
                    "Để Cho Mưa Lân La Hỏi Thăm, Lẻn Vào Trộm Đi",
                    "Khế Ước Trăm Năm"
                };
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    int index = 0;

                    @Override
                    public void run() {
                        npcChat(player, chat[index]);
                        index = (index + 1) % chat.length;
                    }
                }, 6000, 6000);
            }

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Ta Đang Đi Phát Quà Vô Tình Bị Lạc Ngươi Có Thể Đẫn Ta Về Được Không\nTa Sẽ Tặng Quà Cho Ngươi",
                            "Hướng dẫn\n Hộ Tống\nÔng Già Noel\nVề Nhà", "Hộ Tống", "Đóng");

                }

            }

            @Override

            public void confirmMenu(Player player, int select) {
                Npcchat(player);
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 1:
                                    Boss oldDuongTank = BossManager.gI().getBossById(Util.createIdDuongTank((int) player.id));
                                    if (oldDuongTank != null) {
                                        this.npcChat(player, "Ông Già Noel đang được hộ tống" + oldDuongTank.zone.zoneId);
                                    } else if (player.inventory.ruby < 10000) {
                                        this.npcChat(player, "Nhà ngươi không đủ 10K Hồng Ngọc ");
                                    } else {
                                        BossData bossDataClone = new BossData(
                                                "Ông Già Noel Do" + " " + player.name + " hộ tống",
                                                (byte) 2,
                                                new short[]{657, 658, 659, -1, -1, -1},
                                                100000,
                                                new int[]{player.nPoint.hpMax * 2},
                                                new int[]{103},
                                                new int[][]{
                                                    {Skill.TAI_TAO_NANG_LUONG, 7, 15000}},
                                                new String[]{}, //text chat 1
                                                new String[]{}, //text chat 2
                                                new String[]{}, //text chat 3
                                                60
                                        );

                                        try {
                                            MiNuong co = new MiNuong(Util.createIdDuongTank((int) player.id), bossDataClone, player.zone, player.location.x - 20, player.location.y);
                                            co.playerTarger = player;
                                            int[] map = {6, 29, 30, 4, 5, 27, 28};
                                           co.mapHoTong = map[Util.nextInt(map.length)];
                                            player.haveBeQuynh = true;
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        //trừ vàng khi gọi boss
                                        player.inventory.ruby -= 10000;
                                        Service.getInstance().sendMoney(player);
                                        break;
                                    }
                                case 0:
                                    Service.getInstance().sendThongBaoFromAdmin(player, " Gặp Npc Chị Hằng , Chọn Hộ Tống Rồi Dắt Thỏ Ngọc đến Vị Trí được chỉ định \n "
                                            + "Phần quà 30k Ngọc Hồng , Ran Dom 5 -10 Đồng Bạc , Phí dắt 10k Ngọc Hồng ..");
                                    break;

                            }
                        }
                    }
                }
            }
        };
    }
   public static Npc duongtank(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (mapId == 0) {
                        this.createOtherMenu(player, 0,
                                "Hãy hộ tống ta đến Đông Karin, ta sẽ tặng con quả hồng đào !", "Ngũ hành sơn",
                                "Hộ tống thỉnh kinh", "Shop hồng đào", "Oéo");
                    }
                    if (mapId == 122) {
                        this.createOtherMenu(player, 0, "Bạn Muốn Quay Trở Lại Làng Aru?", "OK", "Từ chối");

                    }
                    if (mapId == 124) {
                        this.createOtherMenu(player, 0,
                                "Xia xia thua phùa\b|7|Thí chủ đang có: " + player.NguHanhSonPoint
                                        + " điểm ngũ hành sơn\b|1|Thí chủ muốn đổi cải trang x4 chưởng ko?",
                                "Âu kê", "Top Ngu Hanh Son", "No");
                    }

                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (select) {
                        case 0:
                            if (mapId == 0) {
                                if (player.nPoint.power <= 1500000) {
                                    Service.gI().sendThongBao(player,
                                            "Sức mạnh bạn không phù hợp để qua map! 1tr5 require");
                                    return;
                                }
                                ChangeMapService.gI().changeMapInYard(player, 123, -1, 174);
                                break;
                            }
                            if (mapId == 122) {
                                ChangeMapService.gI().changeMapInYard(player, 0, -1, 469);
                                break;
                            }
                            if (mapId == 124) {
                                if (select == 0) {
                                    if (player.NguHanhSonPoint >= 500) {
                                        player.NguHanhSonPoint -= 500;
                                        Item item = ItemService.gI().createNewItem((short) (711));
                                        item.itemOptions.add(new Item.ItemOption(50, 20));
                                        item.itemOptions.add(new Item.ItemOption(77, 20));
                                        item.itemOptions.add(new Item.ItemOption(103, 20));
                                        item.itemOptions.add(new Item.ItemOption(33, 0));
                                        item.itemOptions.add(new Item.ItemOption(159, 4));
                                        item.itemOptions.add(new Item.ItemOption(93, 14));
                                        //
                                        InventoryServiceNew.gI().addItemBag(player, item);
                                        Service.gI().sendThongBao(player, "Chúc Mừng Bạn Đổi Vật Phẩm Thành Công !");
                                    } else {
                                        Service.gI().sendThongBao(player,
                                                "Không đủ điểm, bạn còn " + (500 - player.NguHanhSonPoint)
                                                        + " điểm nữa");
                                    }
                                    break;
                                }
                                if (select == 1) {
                                    Service.gI().sendThongBao(player,
                                            "Adi phò phò");
                                    Service.gI().showListTop(player, Manager.topNHS);
                                    break;

                                }

                            }
                        case 1:
                            if (mapId == 0) {
                                Boss oldBossClone = BossManager.gI()
                                        .getBossById(BossID.DUONG_TANG_HOTONG);
                                if (oldBossClone != null) {
                                    this.npcChat(player,
                                            "Đường tăng đang được ai đó hộ tống !");
                                } else if (player.haveDuongTang == true) {
                                    this.npcChat(player, "Nhà người hãy hộ tống Đường tăng cũ đi đã ");
                                } else if (player.inventory.gem < 50000) {
                                    this.npcChat(player, "Nhà ngươi không đủ 50k Ngọc ");
                                } else {

                                    try {
                                        DuongTank dt = new DuongTank(BossID.DUONG_TANG_HOTONG,
                                                BossesData.DUONG_TANG_HO_TONG, player.zone, player.location.x - 20,
                                                player.location.y);
                                        dt.playerTarger = player;
                                        int[] map = { 6 };
                                        dt.mapCongDuc = map[Util.nextInt(map.length)];
                                        player.haveDuongTang = true;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Logger.logException(Manager.class, e, "Lỗi tạo đường tăng");
                                    }
                                    // trừ vàng khi gọi boss
                                    player.inventory.gem -= 50000;
                                    if (player.typePk == ConstPlayer.NON_PK) {
                                        PlayerService.gI().changeAndSendTypePK(player, ConstPlayer.PK_ALL);
                                    }
                                    Service.getInstance().sendMoney(player);
                                }
                            }
                            break;
                        case 2:
                            if (player.nPoint.power >= 2000000000) {

                                ShopServiceNew.gI().opendShop(player, "DUONGTANK", true);
                            } else {

                                Service.gI().sendThongBaoOK(player,
                                        "Yêu cầu 2 tỉ sức mạnh");

                            }
                            break;

                    }
                }
            }
        };
    }
   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static Npc ongGohan_ongMoori_ongParagus(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        if (TaskService.gI().getIdTask(player) == ConstTask.TASK_4_3) {
                            TaskService.gI().sendNextTaskMain(player);
                            this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                    "Tốt lắm, con hoàn thành rất xuất xắc", "Đóng");
                        } else if (TaskService.gI().getIdTask(player) == ConstTask.TASK_10_1) {
                            TaskService.gI().sendNextTaskMain(player);
                        }
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "|7|Chào con thầy còn dữ lại " + player.getSession().vnd + " Coin của con!\n|6|Src By Ánh Ngọc\n|2| Nhận Fix Lỗi Code Sự Kiện Nro Giá Siêu Rẻ\nZalo:0329082757"
                                .replaceAll("%1", player.gender == ConstPlayer.TRAI_DAT ? "Quy lão Kamê"
                                                : player.gender == ConstPlayer.NAMEC ? "Trưởng lão Guru" : "Vua Vegeta"),
                               "Quà Tân Thủ", "Đổi Mật Khẩu",  "Mở Thành Viên", "GiftCode","Cộng Chỉ Số","Hồi Skill\n80%");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                             case 0:
                                createOtherMenu(player, ConstNpc.MIEN_PHI,
                                        "Con muốn nhận miễn phí phần thưởng gì?",
                                        "Quà\n Tân Thủ 1",//case 0
                                        "Quà\nTân Thủ 2",//case 1 cần mtv mới nhận được
                                        "Quà\nThành Viên",//case 2 quà thành viên
                                        "Điểm Danh\nHằng Ngày",// điểm danh hằng ngày
                                        "Bỏ Qua \n Nhiệm Vụ",// next nv kb + vào bang
                                        "Nhận Ngọc Xanh",// nhận 20k ngọc xanh + cần gọi rồng ước thêm
                                        "Nhận Đệ Tử"
                                        );//code by Ánh Ngọc zalo:0343214443
                                break;//code by Ánh Ngọc zalo:0343214443
                            case 1:
                                Input.gI().createFormChangePassword(player);
                                break;
                           
                            case 2:
                                if (!player.getSession().actived) {
                                    if (player.getSession().vnd >= 10000) {
                                        player.getSession().actived = true;
                                        if (PlayerDAO.subvnd(player, 10000)) {
                                            Service.gI().sendThongBao(player, "Bạn đã mở thành viên");
                                        }
                                    } else {
                                        this.npcChat(player, "Bạn còn thiếu " + (10000 - player.getSession().vnd) + " để mở thành viên");
                                    }
                                }
                                break;
                            case 3:
                                Input.gI().createFormGiftCode(player);
                                break;
                                case 4:
                                  this.createOtherMenu(player, ConstNpc.MENU_PLAYER,
                                        "|7|AUTO CỘNG CHỈ SỐ NHANH"
                                        + "\n\n|2| Bạn muốn cộng nhanh chỉ số nào?",
                                        "HP\n" + (player.autoHP == true ? "[ BẬT ]" : "[ TẮT ]"),
                                        "KI\n" + (player.autoKI == true ? "[ BẬT ]" : "[ TẮT ]"),
                                        "SD\n" + (player.autoSD == true ? "[ BẬT ]" : "[ TẮT ]"),
                                        "Giáp\n" + (player.autoGiap == true ? "[ BẬT ]" : "[ TẮT ]"));
                                break;  
                                     case 5:
                                this.createOtherMenu(player, ConstNpc.HOI_SKILL,
                                        "Đưa ta 50 thỏi vàng để hồi skill cấp tốc",
                                        "Dạ", "Hông");
                                break;
                        }//code by Ánh Ngọc zalo:0343214443
                         } else if (player.iDMark.getIndexMenu() == ConstNpc.HOI_SKILL) {
                        switch (select) {
                            case 0:
                                if (InventoryServiceNew.gI().findItemBag(player, 457).isNotNullItem()
                                        && InventoryServiceNew.gI().findItemBag(player, 457).quantity >= 50) {
                                    InventoryServiceNew.gI().subQuantityItemsBag(player,
                                            InventoryServiceNew.gI().findItemBag(player, 457), 50);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    int subTimeParam=80;
                                    int coolDown = player.playerSkill.skillSelect.coolDown;
                                    player.playerSkill.skillSelect.lastTimeUseThisSkill = System.currentTimeMillis() - (coolDown * subTimeParam / 100);
                                    if (subTimeParam != 0) {
                                        Service.getInstance().sendTimeSkill(player);
                                    }
                                    return;
                                } else {
                                    Service.gI().sendThongBaoOK(player, "Không có tiền mà đòi hít ... thơm");

                                }
                                break;

                        }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_PLAYER) {
                        switch (select) {
                           case 0:
                                player.autoHP = !player.autoHP;
                                Service.gI().sendThongBao(player, "|1|Đã " + (player.autoHP == true ? "Bật" : "Tắt") + " Auto cộng chỉ số HP");
                                try {
                                    while (player.autoHP == true) {
                                        if (player.nPoint != null && player.nPoint.hpg + 20000 <= player.nPoint.getHpMpLimit()) {
                                            player.nPoint.increasePoint((byte) 0, (short) 1000);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng HP!\n|2|" + player.nPoint.hpg);
                                        } else if (player.nPoint != null && player.nPoint.hpg + 2000 <= player.nPoint.getHpMpLimit()) {
                                            player.nPoint.increasePoint((byte) 0, (short) 100);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng HP!\n|2|" + player.nPoint.hpg);
                                        } else if (player.nPoint != null && player.nPoint.hpg + 200 <= player.nPoint.getHpMpLimit()) {
                                            player.nPoint.increasePoint((byte) 0, (short) 10);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng HP!\n|2|" + player.nPoint.hpg);
                                        } else if (player.nPoint != null && player.nPoint.hpg + 20 <= player.nPoint.getHpMpLimit()) {
                                            player.nPoint.increasePoint((byte) 0, (short) 1);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng HP!\n|2|" + player.nPoint.hpg);
                                        } else {
                                            player.autoHP = false;
                                            Service.gI().sendThongBaoFromAdmin(player, "|8|Auto cộng HP đã dừng!\n|2|Bạn đã đạt giới hạn sức mạnh");
                                            break;
                                        }
                                    }
                                } catch (Exception e) {
                                }
                                break;
                            case 1:
                                player.autoKI = !player.autoKI;
                                Service.gI().sendThongBao(player, "|1|Đã " + (player.autoKI == true ? "Bật" : "Tắt") + " Auto cộng chỉ số KI");
                                if (player.autoKI == true) {
                                    try {
                                        while (player.autoKI == true) {
                                            if (player.nPoint != null && player.nPoint.mpg + 20000 <= player.nPoint.getHpMpLimit()) {
                                                player.nPoint.increasePoint((byte) 1, (short) 1000);
                                                Service.gI().sendThongBao(player, "|8|Auto cộng KI!\n|2|" + player.nPoint.mpg);
                                            } else if (player.nPoint != null && player.nPoint.mpg + 2000 <= player.nPoint.getHpMpLimit()) {
                                                player.nPoint.increasePoint((byte) 1, (short) 100);
                                                Service.gI().sendThongBao(player, "|8|Auto cộng KI!\n|2|" + player.nPoint.mpg);
                                            } else if (player.nPoint != null && player.nPoint.mpg + 200 <= player.nPoint.getHpMpLimit()) {
                                                player.nPoint.increasePoint((byte) 1, (short) 10);
                                                Service.gI().sendThongBao(player, "|8|Auto cộng KI!\n|2|" + player.nPoint.mpg);
                                            } else if (player.nPoint != null && player.nPoint.mpg + 20 <= player.nPoint.getHpMpLimit()) {
                                                player.nPoint.increasePoint((byte) 1, (short) 1);
                                                Service.gI().sendThongBao(player, "|8|Auto cộng KI!\n|2|" + player.nPoint.mpg);
                                            } else {
                                                player.autoKI = false;
                                                Service.gI().sendThongBaoFromAdmin(player, "|8|Auto cộng KI đã dừng!\n|2|Bạn đã đạt giới hạn sức mạnh");
                                                break;
                                            }
                                        }
                                    } catch (Exception e) {
                                    }
                                }
                                break;
                            case 2:
                                player.autoSD = !player.autoSD;
                                Service.gI().sendThongBao(player, "|1|Đã " + (player.autoSD == true ? "Bật" : "Tắt") + " Auto cộng chỉ số SĐ");
                                try {
                                    while (player.autoSD == true) {
                                        if (player.nPoint != null && player.nPoint.dameg + 1000 <= player.nPoint.getDameLimit()) {
                                            player.nPoint.increasePoint((byte) 2, (short) 1000);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng SĐ!\n|2|" + player.nPoint.dameg);
                                        } else if (player.nPoint != null && player.nPoint.dameg + 100 <= player.nPoint.getDameLimit()) {
                                            player.nPoint.increasePoint((byte) 2, (short) 100);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng SĐ!\n|2|" + player.nPoint.dameg);
                                        } else if (player.nPoint != null && player.nPoint.dameg + 10 <= player.nPoint.getDameLimit()) {
                                            player.nPoint.increasePoint((byte) 2, (short) 10);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng SĐ!\n|2|" + player.nPoint.dameg);
                                        } else if (player.nPoint != null && player.nPoint.dameg + 1 <= player.nPoint.getDameLimit()) {
                                            player.nPoint.increasePoint((byte) 2, (short) 1);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng SĐ!\n|2|" + player.nPoint.dameg);
                                        } else {
                                            player.autoSD = false;
                                            Service.gI().sendThongBaoFromAdmin(player, "|8|Auto cộng SĐ đã dừng!\n|2|Bạn đã đạt giới hạn sức mạnh");
                                            break;
                                        }
                                    }
                                } catch (Exception e) {
                                }
                                break;
                            case 3:
                                player.autoGiap = !player.autoGiap;
                                Service.gI().sendThongBao(player, "|1|Đã " + (player.autoGiap == true ? "Bật" : "Tắt") + " Auto cộng chỉ số DEF");
                                try {
                                    while (player.autoGiap == true) {
                                        if (player.nPoint != null && player.nPoint.defg + 100 <= player.nPoint.getDefLimit()) {
                                            player.nPoint.increasePoint((byte) 3, (short) 100);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng DEF!\n|2|" + player.nPoint.defg);
                                        } else if (player.nPoint != null && player.nPoint.defg + 10 <= player.nPoint.getDefLimit()) {
                                            player.nPoint.increasePoint((byte) 3, (short) 10);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng DEF!\n|2|" + player.nPoint.defg);
                                        } else if (player.nPoint != null && player.nPoint.defg + 1 <= player.nPoint.getDefLimit()) {
                                            player.nPoint.increasePoint((byte) 3, (short) 1);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng DEF!\n|2|" + player.nPoint.defg);
                                        } else {
                                            player.autoGiap = false;
                                            Service.gI().sendThongBaoFromAdmin(player, "|8|Auto cộng DEF đã dừng!\n|2|Bạn đã đạt giới hạn sức mạnh");
                                            break;
                                        }
                                    }
                                } catch (Exception e) {
                                }
                                break;
                        }
                        
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MIEN_PHI) {
                        switch (select) {
                            case 0://code by Ánh Ngọc zalo:0343214443
                                if (player.capboss == 0) {
                                    int hongngoc = 1;
                                    int tv = 10;
                                    int cskb = 1;
                                    int gem = 10000;
                                     int ctrang = 10000;
                                    player.inventory.gem += gem;
                                    Service.gI().sendMoney(player);

                                    Item thoivang2 = ItemService.gI().createNewItem((short) 396, cskb);
                                    thoivang2.itemOptions.add(new Item.ItemOption(84, 0));
                                     thoivang2.itemOptions.add(new Item.ItemOption(30, 0));
                                      thoivang2.itemOptions.add(new Item.ItemOption(93, 7));
                                    InventoryServiceNew.gI().addItemBag(player, thoivang2);
                                    InventoryServiceNew.gI().sendItemBags(player);

                                    Item thoivang1 = ItemService.gI().createNewItem((short) 647, hongngoc);
                                     thoivang1.itemOptions.add(new Item.ItemOption(50, 25));
                                    thoivang1.itemOptions.add(new Item.ItemOption(77, 25));
                                    thoivang1.itemOptions.add(new Item.ItemOption(103, 25));
                                    thoivang1.itemOptions.add(new Item.ItemOption(93, 7));
                                    thoivang1.itemOptions.add(new Item.ItemOption(30, 0));
                                    InventoryServiceNew.gI().addItemBag(player, thoivang1);
                                    InventoryServiceNew.gI().sendItemBags(player);

                                     for (int i = 14; i <= 20; i++) {
                                    Item item = ItemService.gI().createNewItem((short) i);
                                    InventoryServiceNew.gI().addItemBag(player, item);
                                   }
                                     InventoryServiceNew.gI().sendItemBags(player);
                                     
                                     player.capboss++;
                                      Service.gI().sendThongBaoOK(player, "Chức mừng bạn đã nhận được quà tân thủ");
                                } else {//code by Ánh Ngọc zalo:0343214443
                               
                                    Service.getInstance().sendThongBao(player, "Đã nhận thưởng rồi");
                                }
                                break;//code by Ánh Ngọc zalo:0343214443
                            case 1:
                                if (!player.getSession().actived) {
                                        Service.getInstance().sendThongBao(player, "Vui lòng kích hoạt tài khoản để sử dụng chức năng này");
                                   } else
                                if (player.point_cauca == 0) {
                                    int hongngoc = 10;
                                    int tv = 10;
                                    int cskb = 30;
                                    int ruby = 5000;
                                     int ctrang = 10000;
                                    player.inventory.ruby += ruby;
                                    Service.gI().sendMoney(player);

                                    Item thoivang2 = ItemService.gI().createNewItem((short) 457, cskb);
                                     thoivang2.itemOptions.add(new Item.ItemOption(30, 0));
                                    InventoryServiceNew.gI().addItemBag(player, thoivang2);
                                    InventoryServiceNew.gI().sendItemBags(player);
//code by Ánh Ngọc zalo:0343214443
                                    Item thoivang1 = ItemService.gI().createNewItem((short) 987, hongngoc);
                                    
                                    thoivang1.itemOptions.add(new Item.ItemOption(93, 7));
                                    thoivang1.itemOptions.add(new Item.ItemOption(30, 0));
                                    InventoryServiceNew.gI().addItemBag(player, thoivang1);
                                    InventoryServiceNew.gI().sendItemBags(player);

                                     for (int i = 14; i <= 20; i++) {
                                    Item item = ItemService.gI().createNewItem((short) i);
                                    InventoryServiceNew.gI().addItemBag(player, item);
                                   }
                                     InventoryServiceNew.gI().sendItemBags(player);
                                     
                                     player.point_cauca++;
                                      Service.gI().sendThongBaoOK(player, "Chức mừng bạn đã nhận được quà tân thủ 2\n"
                                              + "|7|Gồm:"
                                              + "\n 5000 hồng ngọc + 30 thỏi vàng + 1 Bộ Nro + 10 Đá Bảo Vệ");
                                } else {
                               
                                    Service.getInstance().sendThongBao(player, "Đã nhận thưởng rồi");
                                }
                                break;
                                 case 2://code by Ánh Ngọc zalo:0343214443
                            if (player.getSession().actived){
                                if (player.point_vip == 0){
                                    short[] rdpet1 = new short[]{1428,1429,1430,1431};
                                        Item _item = ItemService.gI().createNewItem((short)rdpet1[Util.nextInt(rdpet1.length-1)], 1);                                            
                                        _item.itemOptions.add(new Item.ItemOption(50, Util.nextInt(5,10)));
                                        _item.itemOptions.add(new Item.ItemOption(77, Util.nextInt(5,10)));
                                        _item.itemOptions.add(new Item.ItemOption(103, Util.nextInt(5,10)));
                                        _item.itemOptions.add(new Item.ItemOption(101, Util.nextInt(40,50))); 
                                        _item.itemOptions.add(new Item.ItemOption(106, 0));
                                        InventoryServiceNew.gI().addItemBag(player, _item);                                        
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        Service.getInstance().sendThongBao(player, "Chúc mừng bạn nhận được " + _item.template.name);                                        
                                        ServerNotify.gI().notify("Chúc mừng " + player.name + " nhận được "+_item.template.name+" " );
                                    player.point_vip++;
                                }else{
                                    Service.getInstance().sendThongBao(player, "Đã nhận thưởng rồi");
                                }
                            }else{
                                if(player.gender == 0) {
                                    createOtherMenu(player, ConstNpc.IGNORE_MENU, "Trở về nhà gặp ông Gohan mở thành viên. Sau đó hãy quay lại đây", "Tạm biệt");
                                }if(player.gender == 1) {
                                    createOtherMenu(player, ConstNpc.IGNORE_MENU, "Trở về nhà gặp ông Moori mở thành viên. Sau đó hãy quay lại đây", "Tạm biệt");
                                }if(player.gender == 2) {
                                    createOtherMenu(player, ConstNpc.IGNORE_MENU, "Trở về nhà gặp ông Paragus mở thành viên. Sau đó hãy quay lại đây", "Tạm biệt");
                                }
                            }
                                    break;
                             case 3://code by Ánh Ngọc zalo:0343214443
                                      if (player.diemdanh == 0){
                                    short[] rdpet1 = new short[]{1428,1429,1430,1431};
                                        Item _item = ItemService.gI().createNewItem((short)rdpet1[Util.nextInt(rdpet1.length-1)], 1);                                            
                                        _item.itemOptions.add(new Item.ItemOption(50, Util.nextInt(5,10)));
                                        _item.itemOptions.add(new Item.ItemOption(77, Util.nextInt(5,10)));
                                        _item.itemOptions.add(new Item.ItemOption(103, Util.nextInt(5,10)));
                                        _item.itemOptions.add(new Item.ItemOption(101, Util.nextInt(40,50))); 
                                        _item.itemOptions.add(new Item.ItemOption(106, 0));
                                        InventoryServiceNew.gI().addItemBag(player, _item);                                        
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        Service.getInstance().sendThongBao(player, "Chúc mừng bạn nhận được " + _item.template.name);                                        
                                        ServerNotify.gI().notify("Chúc mừng " + player.name + " nhận được "+_item.template.name+" " );
                                    player.diemdanh++;
                                }else{
                                    Service.getInstance().sendThongBao(player, "Đã nhận thưởng rồi");
                                }
                                      break;
                            case 4://code by Ánh Ngọc zalo:0343214443
                                if (player.playerTask.taskMain.id == 11) {
                                    if (player.playerTask.taskMain.index == 0) {
                                        TaskService.gI().doneTask(player, ConstTask.TASK_11_0);
                                    } else if (player.playerTask.taskMain.index == 1) {
                                        for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 25; i++) {
                                            TaskService.gI().doneTask(player, ConstTask.TASK_11_1);
                                        }
                                    } else if (player.playerTask.taskMain.index == 2) {
                                        for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 25; i++) {
                                            TaskService.gI().doneTask(player, ConstTask.TASK_11_2);
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Ta đã giúp con hoàn thành nhiệm vụ rồi mau đi trả nhiệm vụ");
                                    }
                                } else if (player.playerTask.taskMain.id == 13) {
                                    if (player.playerTask.taskMain.index == 0) {
                                        TaskService.gI().doneTask(player, ConstTask.TASK_13_0);
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Ta đã giúp con hoàn thành nhiệm vụ rồi mau đi trả nhiệm vụ");
                                    }
                                } else if (player.playerTask.taskMain.id == 27) {
                                    if (player.playerTask.taskMain.index == 0) {
                                        TaskService.gI().doneTask(player, ConstTask.TASK_27_0);
                                    } else if (player.playerTask.taskMain.index == 1) {
                                        for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 25; i++) {
                                            TaskService.gI().doneTask(player, ConstTask.TASK_27_1);
                                        }
                                    } else if (player.playerTask.taskMain.index == 2) {
                                        for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 25; i++) {
                                            TaskService.gI().doneTask(player, ConstTask.TASK_27_2);
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Ta đã giúp con hoàn thành nhiệm vụ rồi mau đi trả nhiệm vụ");
                                    }
                                } else {
                                    Service.getInstance().sendThongBao(player, "Nhiệm vụ hiện tại không thuộc diện hỗ trợ");
                                }
                                break;
                            case 5://code by Ánh Ngọc zalo:0343214443
                                if (player.inventory.gem == 20000) {
                                    this.npcChat(player, "Tham Lam");
                                    break;
                                }
                                player.inventory.gem = 20000;
                                Service.getInstance().sendMoney(player);
                                Service.getInstance().sendThongBao(player, "Bạn vừa nhận được 20k ngọc xanh");
                                break;
                            case 6:
                                if (player.pet == null) {
                                    PetService.gI().createNormalPet(player, player.gender);
                                    Service.getInstance().sendThongBao(player, "Bạn vừa nhận được đệ tử");
                                } else {
                                    this.npcChat(player, "Đã có đệ tử rồi");
                                }
                                break;
                        }
                    }

                }
            }
        };
    }

    public static Npc bulmaQK(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Cậu cần trang bị gì cứ đến chỗ tôi nhé", "Cửa\nhàng");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0://Shop
                                if (player.gender == ConstPlayer.TRAI_DAT) {
                                    ShopServiceNew.gI().opendShop(player, "BUNMA", true);
                                } else {
                                    this.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Xin lỗi cưng, chị chỉ bán đồ cho người Trái Đất", "Đóng");
                                }
                                break;
                        }
                    }
                }
            }
        };
    }

    public static Npc dende(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        if (player.idNRNM != -1) {
                            if (player.zone.map.mapId == 7) {
                                this.createOtherMenu(player, 1, "Ồ, ngọc rồng namếc, bạn thật là may mắn\nnếu tìm đủ 7 viên sẽ được Rồng Thiêng Namếc ban cho điều ước", "Hướng\ndẫn\nGọi Rồng", "Gọi rồng", "Từ chối");
                            }
                        } else {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                    "Anh cần trang bị gì cứ đến chỗ em nhé", "Cửa\nhàng");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0://Shop
                                if (player.gender == ConstPlayer.NAMEC) {
                                    ShopServiceNew.gI().opendShop(player, "DENDE", true);
                                } else {
                                    this.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Xin lỗi anh, em chỉ bán đồ cho dân tộc Namếc", "Đóng");
                                }
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == 1) {
                        if (player.zone.map.mapId == 7 && player.idNRNM != -1) {
                            if (player.idNRNM == 353) {
                                NgocRongNamecService.gI().tOpenNrNamec = System.currentTimeMillis() + 86400000;
                                NgocRongNamecService.gI().firstNrNamec = true;
                                NgocRongNamecService.gI().timeNrNamec = 0;
                                NgocRongNamecService.gI().doneDragonNamec();
                                NgocRongNamecService.gI().initNgocRongNamec((byte) 1);
                                NgocRongNamecService.gI().reInitNrNamec((long) 86399000);
                                SummonDragon.gI().summonNamec(player);
                            } else {
                                Service.gI().sendThongBao(player, "Anh phải có viên ngọc rồng Namếc 1 sao");
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc appule(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Ngươi cần trang bị gì cứ đến chỗ ta nhé", "Cửa\nhàng");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0://Shop
                                if (player.gender == ConstPlayer.XAYDA) {
                                    ShopServiceNew.gI().opendShop(player, "APPULE", true);
                                } else {
                                    this.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Về hành tinh hạ đẳng của ngươi mà mua đồ cùi nhé. Tại đây ta chỉ bán đồ cho người Xayda thôi", "Đóng");
                                }
                                break;
                        }
                    }
                }
            }
        };
    }

    public static Npc drDrief(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player pl) {
                if (canOpenNpc(pl)) {
                    if (this.mapId == 84) {
                        this.createOtherMenu(pl, ConstNpc.BASE_MENU,
                                "Tàu Vũ Trụ của ta có thể đưa cậu đến hành tinh khác chỉ trong 3 giây. Cậu muốn đi đâu?",
                                pl.gender == ConstPlayer.TRAI_DAT ? "Đến\nTrái Đất" : pl.gender == ConstPlayer.NAMEC ? "Đến\nNamếc" : "Đến\nXayda");
                    } else if (!TaskService.gI().checkDoneTaskTalkNpc(pl, this)) {
                        if (pl.playerTask.taskMain.id == 7) {
                            NpcService.gI().createTutorial(pl, this.avartar, "Hãy lên đường cứu đứa bé nhà tôi\n"
                                    + "Chắc bây giờ nó đang sợ hãi lắm rồi");
                        } else {
                            this.createOtherMenu(pl, ConstNpc.BASE_MENU,
                                    "Tàu Vũ Trụ của ta có thể đưa cậu đến hành tinh khác chỉ trong 3 giây. Cậu muốn đi đâu?",
                                    "Đến\nNamếc", "Đến\nXayda", "Siêu thị");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 84) {
                        ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 24, -1, -1);
                    } else if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0:
                                ChangeMapService.gI().changeMapBySpaceShip(player, 25, -1, -1);
                                break;
                            case 1:
                                ChangeMapService.gI().changeMapBySpaceShip(player, 26, -1, -1);
                                break;
                            case 2:
                                ChangeMapService.gI().changeMapBySpaceShip(player, 84, -1, -1);
                                break;
                        }
                    }
                }
            }
        };
    }

    public static Npc cargo(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player pl) {
                if (canOpenNpc(pl)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(pl, this)) {
                        if (pl.playerTask.taskMain.id == 7) {
                            NpcService.gI().createTutorial(pl, this.avartar, "Hãy lên đường cứu đứa bé nhà tôi\n"
                                    + "Chắc bây giờ nó đang sợ hãi lắm rồi");
                        } else {
                            this.createOtherMenu(pl, ConstNpc.BASE_MENU,
                                    "Tàu Vũ Trụ của ta có thể đưa cậu đến hành tinh khác chỉ trong 3 giây. Cậu muốn đi đâu?",
                                    "Đến\nTrái Đất", "Đến\nXayda", "Siêu thị");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0:
                                ChangeMapService.gI().changeMapBySpaceShip(player, 24, -1, -1);
                                break;
                            case 1:
                                ChangeMapService.gI().changeMapBySpaceShip(player, 26, -1, -1);
                                break;
                            case 2:
                                ChangeMapService.gI().changeMapBySpaceShip(player, 84, -1, -1);
                                break;
                        }
                    }
                }
            }
        };
    }

    public static Npc noibanh(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (this.mapId == 5) {
                    if (NauBanh.gI().NauXong == true) {
                        this.createOtherMenu(player, 0, "Bánh đã chín, Bạn có "
                                + Util.tinhgio(NauBanh.gI().ThoiGianChoLayBanh) + " để lấy\n|2|Nếu offline số bánh có thể được lấy vào đợt sau!",
                                "Lấy Bánh");
                    } else if (NauBanh.gI().ChoXong == true) {
                        this.createOtherMenu(player, 1, "|2|Nồi Bánh Toàn Server Đợt " + NauBanh.gI().Count
                                + "\n|-1|Đang trong thời gian nấu, bạn có thể cho thêm bánh vào nấu ké"
                                + "\nSố lượng bánh nấu sẽ không giới hạn"
                                + "\nThời gian nấu bánh còn: " + Util.tinhgio(NauBanh.gI().ThoiGianNau)
                                + "\nHiện tại có: " + (NauBanh.gI().ListPlNauBanh.size()) + " bánh đang nấu"
                                + "\nTrong đó bạn có: " + (NauBanh.gI().plBanhChung + NauBanh.gI().plBanhTet) + " bánh mới\n("
                                + (player.BanhChung + player.BanhTet) + " bánh trước đó chưa lấy)", "Nấu bánh chưng", "Nấu bánh tét", "Hướng dẫn");
                    } else if (NauBanh.gI().ChoXong == false) {
                        this.createOtherMenu(player, 4, "|2|Nồi Bánh Toàn Server Đợt " + NauBanh.gI().Count
                                + "\n|-1|Thời gian chờ nấu còn: " + Util.tinhgio(NauBanh.gI().ThoiGianCho)
                                + "\nMực nước trong nồi: " + Util.format(NauBanh.gI().Nuoc) + " % "
                                + (NauBanh.gI().Nuoc >= 50 && NauBanh.gI().Nuoc < 100 ? "(Trung bình)" : NauBanh.gI().Nuoc >= 100 ? "(Đã đầy)" : "(Thấp)")
                                + "\nSố củi đã thêm: " + NauBanh.gI().Cui
                                + "\nĐủ củi vả nước sẽ bắt đầu nấu"
                                + "\nThêm đủ nước để nồi không bị cháy và nhận đủ số bánh nấu"
                                + "\nThêm củi lửa để tăng tốc thời gian nấu bánh",
                                "Thêm nước nấu", "Thêm củi lữa", "Hướng dẫn");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        switch (player.iDMark.getIndexMenu()) {
                            case 1:
                                switch (select) {
                                    case 0:
                                        this.createOtherMenu(player, 2, "Bánh chưng: 12 Lá giong, 2 Gạo nếp, 1 Đậu xanh, 12 Gióng tre, 3 Thịt lợn, 1 Muối và 03 Nước nấu.", "Nấu", "Đóng");
                                        break;
                                    case 1:
                                        this.createOtherMenu(player, 3, "Bánh tết: 12 Lá chuối, 2 Gạo nếp, 1 Đậu xanh, 12 Giống tre, 3 Thịt lợn, 1 Muối và 03 Nước nấu.", "Nấu", "Đóng");
                                        break;
                                }
                                break;
                            case 2:
                                Input.gI().createFormNauBanhChung(player);
                                break;
                            case 3:
                                Input.gI().createFormNauBanhTet(player);
                                break;
                        }
                        if (player.iDMark.getIndexMenu() == 0) {
                            if (player.BanhChung == 0 && player.BanhTet == 0) {
                                Service.gI().sendThongBao(player, "Có nấu gì đéo đâu mà đòi nhận");
                                return;
                            }
                            if (player.BanhTet != 0) {
                                Item BanhChung = ItemService.gI().createNewItem((short) 1446, player.BanhTet);
                                InventoryServiceNew.gI().addItemBag(player, BanhChung);
                                InventoryServiceNew.gI().sendItemBags(player);
                                Service.gI().sendThongBao(player, "Bạn đã nhận được bánh tét");
                                player.BanhTet = 0;
                            }
                            if (player.BanhChung != 0) {
                                Item BanhTet = ItemService.gI().createNewItem((short) 1445, player.BanhChung);
                                InventoryServiceNew.gI().addItemBag(player, BanhTet);
                                InventoryServiceNew.gI().sendItemBags(player);
                                Service.gI().sendThongBao(player, "Bạn đã nhận được bánh chưng");
                                player.BanhChung = 0;
                            }
                        } else if (player.iDMark.getIndexMenu() == 4) {
                            switch (select) {
                                case 0:
                                    Item nuocNau = InventoryServiceNew.gI().findItemBag(player, 1443);
                                    if (nuocNau == null) {
                                        Service.gI().sendThongBao(player, "Có nước nấu đâu cu");
                                        return;
                                    }
                                    if (NauBanh.gI().Nuoc < 100) {
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, nuocNau, 1);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        NauBanh.gI().Nuoc++;
                                    } else {
                                        Service.gI().sendThongBao(player, "Đủ nước rồi cu");
                                    }
                                    break;
                                case 1:
                                    Item cuiLua = InventoryServiceNew.gI().findItemBag(player, 1444);
                                    if (cuiLua == null) {
                                        Service.gI().sendThongBao(player, "Có cui lửa đâu cú");
                                        return;
                                    }
                                    if (NauBanh.gI().Cui < 100) {
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, cuiLua, 1);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        NauBanh.gI().Cui++;
                                        NauBanh.gI().ThoiGianNau -= (1000);
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc DuaHau(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (this.mapId == 21 || this.mapId == 22 || this.mapId == 23) {
                    if (DuaHau.gI().NauXong == true) {
                        this.createOtherMenu(player, 0, "Dưa Hấu đã chín, Bạn có "
                                + Util.tinhgio(DuaHau.gI().ThoiGianChoDuaHau) + " để lấy\n|2|Nếu offline số dưa hấu có thể được lấy vào đợt sau!",
                                "Lấy Dưa Hấu");
                    } else if (DuaHau.gI().ChoXong == true) {
                        this.createOtherMenu(player, 1, "|2|Trồng Dưa Hấu Toàn Server Đợt " + DuaHau.gI().Count
                                + "\n|-1|Đang trong thời gian trồng, bạn có thể cho thêm hạt giống vào trồng ké"
                                + "\nSố lượng dưa hấu trồng sẽ không giới hạn"
                                + "\nThời gian dưa hấu chín còn: " + Util.tinhgio(DuaHau.gI().ThoiGianTrong)
                                + "\nHiện tại có: " + (DuaHau.gI().ListPlDuaHau.size()) + " dưa hấu đang trồng"
                                + "\nTrong đó bạn có: " + (DuaHau.gI().plDuaHau) + " dưa hấu mới\n("
                                + (player.DuaHau) + " dưa hấu trước đó chưa lấy)", "Trồng Dưa Hấu", "Hướng dẫn");
                    } else if (DuaHau.gI().ChoXong == false) {
                        this.createOtherMenu(player, 4, "|2|Trồng Dưa Hấu Toàn Server Đợt " + DuaHau.gI().Count
                                + "\n|-1|Thời gian chờ trồng còn: " + Util.tinhgio(DuaHau.gI().ThoiGianChoDH)
                                + "\nPhân Bón: " + Util.format(DuaHau.gI().Phanbon) + " % "
                                + (DuaHau.gI().BinhNuoc >= 50 && DuaHau.gI().BinhNuoc < 100 ? "(Trung bình)" : DuaHau.gI().BinhNuoc >= 100 ? "(Đã đầy)" : "(Thấp)")
                                + "\nSố nuóc đã thêm: " + DuaHau.gI().BinhNuoc
                                + "\nĐủ nước và phân bón sẽ bắt đầu trồng"
                                + "\nThêm đủ nước để đất không bị khô và nhận đủ số bánh trồng"
                                + "\nThêm nuóc để tăng tốc thời gian trồng dưa hấu",
                                "Thêm phân bón", "Thêm nước", "Hướng dẫn");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 21 || this.mapId == 22 || this.mapId == 23) {
                        switch (player.iDMark.getIndexMenu()) {
                            case 1:
                                switch (select) {
                                    case 0:
                                        this.createOtherMenu(player, 2, "Trồng Dưa Hấu: 1 Hạt Giống, 1 Bình Nước, 1 Phân Bón", "Trồng", "Đóng");
                                        break;
                                }
                                break;
                            case 2:
                                Input.gI().createFormTrongDuaHau(player);
                                break;
                        }
                        if (player.iDMark.getIndexMenu() == 0) {
                            if (player.DuaHau == 0) {
                                Service.gI().sendThongBao(player, "Có trồng gì đéo đâu mà đòi nhận");
                                return;
                            }
                            if (player.DuaHau != 0) {
                                Item DuaHau = ItemService.gI().createNewItem((short) 569, player.DuaHau);
                                InventoryServiceNew.gI().addItemBag(player, DuaHau);
                                InventoryServiceNew.gI().sendItemBags(player);
                                Service.gI().sendThongBao(player, "Bạn đã nhận được Dưa Hấu");
                                player.DuaHau = 0;
                            }
                        } else if (player.iDMark.getIndexMenu() == 4) {
                            switch (select) {
                                case 0:
                                    Item phanbon = InventoryServiceNew.gI().findItemBag(player, 1516);
                                    if (phanbon == null) {
                                        Service.gI().sendThongBao(player, "Có phân bón đâu cu");
                                        return;
                                    }
                                    if (DuaHau.gI().Phanbon < 100) {
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, phanbon, 1);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        DuaHau.gI().Phanbon++;
                                    } else {
                                        Service.gI().sendThongBao(player, "Đủ phân bón rồi cu");
                                    }
                                    break;
                                case 1:
                                    Item binhnuoc = InventoryServiceNew.gI().findItemBag(player, 1517);
                                    if (binhnuoc == null) {
                                        Service.gI().sendThongBao(player, "Có nước đâu cu");
                                        return;
                                    }
                                    if (DuaHau.gI().BinhNuoc < 100) {
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, binhnuoc, 1);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        DuaHau.gI().BinhNuoc++;
                                        DuaHau.gI().ThoiGianTrong -= (900000);
                                    }
                                    break;
                                case 2:
                                    NpcService.gI().createTutorial(player, this.avartar, ConstNpc.TRONGDUAHAU);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc cui(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            private final int COST_FIND_BOSS = 50000000;

            @Override
            public void openBaseMenu(Player pl) {
                if (canOpenNpc(pl)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(pl, this)) {
                        if (pl.playerTask.taskMain.id == 7) {
                            NpcService.gI().createTutorial(pl, this.avartar, "Hãy lên đường cứu đứa bé nhà tôi\n"
                                    + "Chắc bây giờ nó đang bị bắt cóc rồi");
                        } else {
                            if (this.mapId == 19) {

                                int taskId = TaskService.gI().getIdTask(pl);
                                switch (taskId) {
                                    case ConstTask.TASK_19_0:
                                        this.createOtherMenu(pl, ConstNpc.MENU_FIND_KUKU,
                                                "Đội quân của Fide đang ở Thung lũng Nappa, ta sẽ đưa ngươi đến đó",
                                                "Đến chỗ\nKuku\n(" + Util.numberToMoney(COST_FIND_BOSS) + " vàng)",
                                                "Đến Cold", "Đến\nNappa", "Từ chối");
                                        break;
                                    case ConstTask.TASK_19_1:
                                        this.createOtherMenu(pl, ConstNpc.MENU_FIND_MAP_DAU_DINH,
                                                "Đội quân của Fide đang ở Thung lũng Nappa, ta sẽ đưa ngươi đến đó",
                                                "Đến chỗ\nMập đầu đinh\n(" + Util.numberToMoney(COST_FIND_BOSS)
                                                + " vàng)",
                                                "Đến Cold", "Đến\nNappa", "Từ chối");
                                        break;
                                    case ConstTask.TASK_19_2:
                                        this.createOtherMenu(pl, ConstNpc.MENU_FIND_RAMBO,
                                                "Đội quân của Fide đang ở Thung lũng Nappa, ta sẽ đưa ngươi đến đó",
                                                "Đến chỗ\nRambo\n(" + Util.numberToMoney(COST_FIND_BOSS) + " vàng)",
                                                "Đến Cold", "Đến\nNappa", "Từ chối");
                                        break;
                                    default:
                                        this.createOtherMenu(pl, ConstNpc.BASE_MENU,
                                                "Đội quân của Fide đang ở Thung lũng Nappa, ta sẽ đưa ngươi đến đó",
                                                "Đến Cold", "Đến\nNappa", "Từ chối");

                                        break;
                                }
                            } else if (this.mapId == 68) {
                                this.createOtherMenu(pl, ConstNpc.BASE_MENU,
                                        "Ngươi muốn về Thành Phố Vegeta", "Đồng ý", "Từ chối");
                            } else {
                                this.createOtherMenu(pl, ConstNpc.BASE_MENU,
                                        "Tàu vũ trụ Xayda sử dụng công nghệ mới nhất, "
                                        + "có thể đưa ngươi đi bất kỳ đâu, chỉ cần trả tiền là được.",
                                        "Đến\nTrái Đất", "Đến\nNamếc", "Siêu thị");
                            }
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 26) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 24, -1, -1);
                                    break;
                                case 1:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 25, -1, -1);
                                    break;
                                case 2:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 84, -1, -1);
                                    break;
                            }
                        }
                    }
                    if (this.mapId == 19) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    if (player.getSession().player.nPoint.power >= 41000000000L
                                            && player.playerTask.taskMain.id > 26) {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 109, -1, 295);
                                    } // break;
                                    else {
                                        Service.gI().sendThongBaoOK(player, "Làm Nhiệm Vụ 26 Và Đạt 41 Tỷ Sức Mạnh");
                                    }
                                    break;
                                case 1:
                                    if (player.playerTask.taskMain.id >= 17) {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 68, -1, 90);
                                    } else {
                                        Service.gI().sendThongBaoOK(player, "Làm Nhiệm Vụ Đi");
                                    }
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_FIND_KUKU) {
                            switch (select) {
                                case 0:
                                    Boss boss = BossManager.gI().getBossById(BossID.KUKU);
                                    if (boss != null && !boss.isDie()) {
                                        if (player.inventory.gold >= COST_FIND_BOSS) {
                                            Zone z = MapService.gI().getMapCanJoin(player, boss.zone.map.mapId,
                                                    boss.zone.zoneId);
                                            if (z != null && z.getNumOfPlayers() < z.maxPlayer) {
                                                player.inventory.gold -= COST_FIND_BOSS;
                                                ChangeMapService.gI().changeMap(player, boss.zone, boss.location.x,
                                                        boss.location.y);
                                                Service.gI().sendMoney(player);
                                            } else {
                                                Service.gI().sendThongBao(player, "Khu vực đang full.");
                                            }
                                        } else {
                                            Service.gI().sendThongBao(player, "Không đủ vàng, còn thiếu "
                                                    + Util.numberToMoney(COST_FIND_BOSS - player.inventory.gold)
                                                    + " vàng");
                                        }
                                        break;
                                    }
                                    Service.gI().sendThongBao(player, "Chết rồi ba...");
                                    break;
                                case 1:
                                    if (player.getSession().player.nPoint.power >= 41000000000L
                                            && player.playerTask.taskMain.id > 26) {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 109, -1, 295);
                                    } // break;
                                    else {
                                        Service.gI().sendThongBaoOK(player, "Làm Nhiệm Vụ 26 Và Đạt 41 Tỷ Sức Mạnh");
                                    }
                                    break;
                                case 2:
                                    if (player.playerTask.taskMain.id >= 17) {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 68, -1, 90);
                                    } else {
                                        Service.gI().sendThongBaoOK(player, "Làm Nhiệm Vụ Đi");
                                    }
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_FIND_MAP_DAU_DINH) {
                            switch (select) {
                                case 0:
                                    Boss boss = BossManager.gI().getBossById(BossID.MAP_DAU_DINH);
                                    if (boss != null && !boss.isDie()) {
                                        if (player.inventory.gold >= COST_FIND_BOSS) {
                                            Zone z = MapService.gI().getMapCanJoin(player, boss.zone.map.mapId,
                                                    boss.zone.zoneId);
                                            if (z != null && z.getNumOfPlayers() < z.maxPlayer) {
                                                player.inventory.gold -= COST_FIND_BOSS;
                                                ChangeMapService.gI().changeMap(player, boss.zone, boss.location.x,
                                                        boss.location.y);
                                                Service.gI().sendMoney(player);
                                            } else {
                                                Service.gI().sendThongBao(player, "Khu vực đang full.");
                                            }
                                        } else {
                                            Service.gI().sendThongBao(player, "Không đủ vàng, còn thiếu "
                                                    + Util.numberToMoney(COST_FIND_BOSS - player.inventory.gold)
                                                    + " vàng");
                                        }
                                        break;
                                    }
                                    Service.gI().sendThongBao(player, "Chết rồi ba...");
                                    break;
                                case 1:
                                    if (player.getSession().player.nPoint.power >= 41000000000L
                                            && player.playerTask.taskMain.id > 26) {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 109, -1, 295);
                                    } // break;
                                    else {
                                        Service.gI().sendThongBaoOK(player, "Làm Nhiệm Vụ 26 Và Đạt 41 Tỷ Sức Mạnh");
                                    }
                                    break;
                                case 2:
                                    if (player.playerTask.taskMain.id >= 17) {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 68, -1, 90);
                                    } else {
                                        Service.gI().sendThongBaoOK(player, "Làm Nhiệm Vụ Đi");
                                    }
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_FIND_RAMBO) {
                            switch (select) {
                                case 0:
                                    Boss boss = BossManager.gI().getBossById(BossID.RAMBO);
                                    if (boss != null && !boss.isDie()) {
                                        if (player.inventory.gold >= COST_FIND_BOSS) {
                                            Zone z = MapService.gI().getMapCanJoin(player, boss.zone.map.mapId,
                                                    boss.zone.zoneId);
                                            if (z != null && z.getNumOfPlayers() < z.maxPlayer) {
                                                player.inventory.gold -= COST_FIND_BOSS;
                                                ChangeMapService.gI().changeMap(player, boss.zone, boss.location.x,
                                                        boss.location.y);
                                                Service.gI().sendMoney(player);
                                            } else {
                                                Service.gI().sendThongBao(player, "Khu vực đang full.");
                                            }
                                        } else {
                                            Service.gI().sendThongBao(player, "Không đủ vàng, còn thiếu "
                                                    + Util.numberToMoney(COST_FIND_BOSS - player.inventory.gold)
                                                    + " vàng");
                                        }
                                        break;
                                    }
                                    Service.gI().sendThongBao(player, "Chết rồi ba...");
                                    break;
                                case 1:
                                    if (player.getSession().player.nPoint.power >= 41000000000L
                                            && player.playerTask.taskMain.id > 26) {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 109, -1, 295);
                                    } // break;
                                    else {
                                        Service.gI().sendThongBaoOK(player, "Làm Nhiệm Vụ 26 Và Đạt 41 Tỷ Sức Mạnh");
                                    }
                                    break;
                                case 2:
                                    if (player.playerTask.taskMain.id >= 17) {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 68, -1, 90);
                                    } else {
                                        Service.gI().sendThongBaoOK(player, "Làm Nhiệm Vụ Đi");
                                    }
                                    break;
                            }
                        }
                    }
                    if (this.mapId == 68) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 19, -1, 1100);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }
 public static Npc zombie(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (player.clan == null) {
                        this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Chỉ tiếp các bang hội, miễn tiếp khách vãng lai", "Đóng");
                        return;
                    }
                        
                    String tt = TimeUtil.getTimeNow("dd/MM/yyyy");
                    String dtt = TimeUtil.formatTime(player.clan.timeOpenDoanhTrai2, "dd-MM-yyyy");
                    if (tt.charAt(0) != dtt.charAt(0) || tt.charAt(1) != dtt.charAt(1) || tt.charAt(3) != dtt.charAt(3) || tt.charAt(4) != dtt.charAt(4) || tt.charAt(8) != dtt.charAt(8) || tt.charAt(9) != dtt.charAt(9))
                    {
                            player.clan.haveGoneDoanhTrai2 = false;
                            player.clan.doanhTrai2 = null;
                    }
                    if (player.clan.getMembers().size() < DoanhTrai.N_PLAYER_CLAN) {
                        this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Bang hội phải có ít nhất 5 thành viên mới có thể mở", "Đóng");
                        return;
                    }
                    int nPlSameClan = 0;
                    for (Player pl : player.zone.getPlayers()) {
                        if (!pl.equals(player) && pl.clan != null
                                && pl.clan.equals(player.clan) && pl.location.x >= 1285
                                && pl.location.x <= 1645) {
                            nPlSameClan++;
                        }
                    }
                    if (nPlSameClan < DoanhTrai.N_PLAYER_MAP) {
                        createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Ngươi phải có ít nhất " + DoanhTrai.N_PLAYER_MAP + " đồng đội cùng bang đứng gần mới có thể\nvào\n"
                                + "tuy nhiên ta khuyên ngươi nên đi cùng với 3-4 người để khỏi chết.\n"
                                + "Hahaha.", "OK", "Hướng\ndẫn\nthêm");
                        return;
                    }
                    if (player.clanMember.getNumDateFromJoinTimeToToday() < 0) {
                        createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Doanh trại chỉ cho phép những người ở trong bang trên 1 ngày. Hẹn ngươi quay lại vào lúc khác",
                                "OK", "Hướng\ndẫn\nthêm");
                        return;
                    }
                    if (player.clan.haveGoneDoanhTrai2) {
                        createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Bang hội của ngươi đã đi trại lúc " + TimeUtil.formatTime(player.clan.timeOpenDoanhTrai2, "HH:mm:ss") + " hôm nay. Người mở\n"
                                + "(" + player.clan.playerOpenDoanhTrai2 + "). Hẹn ngươi quay lại vào ngày mai", "OK", "Hướng\ndẫn\nthêm");
                        return;
                    }
                    if (player.clan.doanhTrai2 != null) {
                        createOtherMenu(player, ConstNpc.MENU_OPENED_DOANH_TRAI,
                                "Bang hội của ngươi đang đánh phụ nữ\n"
                                + "Thời gian còn lại là "
                                + TimeUtil.getMinLeft(player.clan.timeOpenDoanhTrai2, Zombie.TIME_DOANH_TRAI / 1000)
                                + " phút. Ngươi có muốn tham gia không?",
                                "Tham gia", "Không", "Hướng\ndẫn\nthêm");
                        return;
                    }
                    createOtherMenu(player, ConstNpc.MENU_JOIN_DOANH_TRAI,
                            "Hôm nay bang hội của ngươi chưa vào trại lần nào. Ngươi có muốn vào\n"
                            + "không?\nĐể vào, ta khuyên ngươi nên có 3-4 người cùng bang đi cùng",
                            "Vào\n(miễn phí)", "Không", "Hướng\ndẫn\nthêm");
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.MENU_JOIN_DOANH_TRAI:
                            
                            switch (select)  {
                                  
                                case 0:
                                ZombieService.gI().openBanDoKhoBau(player); break;
                                case 2:
                                NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_DOANH_TRAI);break;
                        
                            }
                            break;
                        case ConstNpc.IGNORE_MENU:
                            switch (select)  {
                                  
                                case 1:
                                NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_DOANH_TRAI); break;
                            }
                            break;
                            case ConstNpc.MENU_OPENED_DOANH_TRAI:
                            switch (select)  {
                                  
                                case 0: ChangeMapService.gI().changeMapInYard(player, 182, player.clan.doanhTrai2.id, 60);
                                break;                                  
                            }
                            break;
                    }
                }
            }
        };
    }
    public static Npc santa(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Xin chào, ta có một số vật phẩm đặt biệt cậu có muốn xem không?",
                            "Cửa hàng", "Mở rộng\nHành Trang\nRương Đồ", "Nhập Mã\nQuà Tặng", "Cửa Hàng\nHạn Sử Dụng", "Tiệm\nHớt Tóc", "Danh\nHiệu");
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5 || this.mapId == 13 || this.mapId == 20) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0: //shop
                                    ShopServiceNew.gI().opendShop(player, "santa", true);
                                    break;

                                case 1: //shoptrue
                                    ShopServiceNew.gI().opendShop(player, "MRHT", true);
                                    break;
                                case 2:
                                Input.gI().createFormGiftCodevip(player);
                                break;
                                case 3:
                                    ShopServiceNew.gI().opendShop(player, "HSD", true);
                                    break;
                                case 4:
                                    ShopServiceNew.gI().opendShop(player, "SANTA_HEAD", true);
                                    break;
                                 case 5:
                                    ShopServiceNew.gI().opendShop(player, "DH", true);
                                    break;
                                case 6:
                                    this.createOtherMenu(player, ConstNpc.QUY_DOI, "|7|Số tiền của bạn còn : " + player.getSession().vnd + "\n"
                                            + "Muốn quy đổi không", "Quy Đổi\n10.000\n 20 Thỏi Vàng", "Quy Đổi\n20.000\n 40 Thỏi Vàng", "Quy Đổi\n30.000\n 60 Thỏi Vàng", "Quy Đổi\n50.000\n 100 Thỏi Vàng", "Quy Đổi\n100.000 \n200 Thỏi Vàng", "Đóng");
                                    break;
                                case 7:
                                    ShopServiceNew.gI().opendShop(player, "BUNMA_LINHTHU", true);
                                    break;
                               
                                case 9:
                                    try {
                                        GirlkunResultSet checkmocnap = GirlkunDB.executeQuery(
                                                "select * from account where id = ? and username = ?", player.getSession().userId, player.name);
                                        if (checkmocnap.first()) {
                                            int mocnap = checkmocnap.getInt("mocnap");
                                            if (player.getSession().tongnap >= 100000 && mocnap < 100000) {
                                                GirlkunDB.executeUpdate(
                                                        "update account set mocnap = ? where id = ? and username = ?", 100000, player.getSession().userId, player.name);
                                                Item mocnap1 = ItemService.gI().createNewItem((short) 1478);
                                                Item mocnapp1 = ItemService.gI().createNewItem((short) 457);
                                                mocnapp1.quantity += 49;
                                                player.inventory.ruby += 19999;
                                                mocnap1.itemOptions.add(new Item.ItemOption(50, 40));
                                                mocnap1.itemOptions.add(new Item.ItemOption(77, 40));
                                                mocnap1.itemOptions.add(new Item.ItemOption(103, 40));
                                                mocnap1.itemOptions.add(new Item.ItemOption(101, 40));
                                                InventoryServiceNew.gI().addItemBag(player, mocnap1);
                                                InventoryServiceNew.gI().addItemBag(player, mocnapp1);
                                                InventoryServiceNew.gI().sendItemBags(player);
                                                Service.gI().sendThongBao(player, "Tiến Hành Nhận Mốc Nạp 100K");
                                                Thread.sleep(3000);
                                                Service.gI().sendThongBao(player, "Bạn Đã Nhận Được " + mocnap1.template.name + mocnapp1.template.name + " 20k Hồng Ngọc");
                                            } else if (player.getSession().tongnap >= 200000 && mocnap < 200000) {
                                                GirlkunDB.executeUpdate(
                                                        "update account set mocnap = ? where id = ? and username = ?", 200000, player.getSession().userId, player.name);
                                                Item mocnap2 = ItemService.gI().createNewItem((short) 1428);
                                                Item mocnapp2 = ItemService.gI().createNewItem((short) 457);
                                                mocnapp2.quantity += 69;
                                                player.inventory.ruby += 39999;
                                                mocnap2.itemOptions.add(new Item.ItemOption(50, 45));
                                                mocnap2.itemOptions.add(new Item.ItemOption(77, 45));
                                                mocnap2.itemOptions.add(new Item.ItemOption(103, 45));
                                                mocnap2.itemOptions.add(new Item.ItemOption(101, 45));
                                                InventoryServiceNew.gI().addItemBag(player, mocnap2);
                                                InventoryServiceNew.gI().addItemBag(player, mocnapp2);
                                                InventoryServiceNew.gI().sendItemBags(player);
                                                Service.gI().sendThongBao(player, "Tiến Hành Nhận Mốc Nạp 200K");
                                                Thread.sleep(3000);
                                                Service.gI().sendThongBao(player, "Bạn Đã Nhận Được " + mocnap2.template.name + mocnapp2.template.name + " 40k Hồng Ngọc");
                                            } else if (player.getSession().tongnap >= 300000 && mocnap < 300000) {
                                                GirlkunDB.executeUpdate(
                                                        "update account set mocnap = ? where id = ? and username = ?", 300000, player.getSession().userId, player.name);
                                                Item mocnap3 = ItemService.gI().createNewItem((short) 1479);
                                                Item mocnapp3 = ItemService.gI().createNewItem((short) 457);
                                                mocnapp3.quantity += 99;
                                                player.inventory.ruby += 69999;
                                                mocnap3.itemOptions.add(new Item.ItemOption(50, 50));
                                                mocnap3.itemOptions.add(new Item.ItemOption(77, 50));
                                                mocnap3.itemOptions.add(new Item.ItemOption(103, 50));
                                                mocnap3.itemOptions.add(new Item.ItemOption(101, 50));
                                                InventoryServiceNew.gI().addItemBag(player, mocnap3);
                                                InventoryServiceNew.gI().addItemBag(player, mocnapp3);
                                                InventoryServiceNew.gI().sendItemBags(player);
                                                Service.gI().sendThongBao(player, "Tiến Hành Nhận Mốc Nạp 300K");
                                                Thread.sleep(3000);
                                                Service.gI().sendThongBao(player, "Bạn Đã Nhận Được " + mocnap3.template.name + mocnapp3.template.name + " 70k Hồng Ngọc");
                                            } else if (player.getSession().tongnap >= 500000 && mocnap < 500000) {
                                                GirlkunDB.executeUpdate(
                                                        "update account set mocnap = ? where id = ? and username = ?", 500000, player.getSession().userId, player.name);
                                                Item mocnap4 = ItemService.gI().createNewItem((short) 1105);
                                                Item mocnapp4 = ItemService.gI().createNewItem((short) 1321);
                                                Item mocnappp4 = ItemService.gI().createNewItem((short) 457);
                                                mocnappp4.quantity += 149;
                                                player.inventory.ruby += 199;
                                                mocnapp4.itemOptions.add(new Item.ItemOption(50, 60));
                                                mocnapp4.itemOptions.add(new Item.ItemOption(77, 60));
                                                mocnapp4.itemOptions.add(new Item.ItemOption(103, 60));
                                                mocnapp4.itemOptions.add(new Item.ItemOption(101, 50));
                                                mocnapp4.itemOptions.add(new Item.ItemOption(14, 30));
                                                mocnapp4.itemOptions.add(new Item.ItemOption(5, 30));
                                                InventoryServiceNew.gI().addItemBag(player, mocnap4);
                                                InventoryServiceNew.gI().addItemBag(player, mocnapp4);
                                                InventoryServiceNew.gI().addItemBag(player, mocnappp4);
                                                InventoryServiceNew.gI().sendItemBags(player);
                                                Service.gI().sendThongBao(player, "Tiến Hành Nhận Mốc Nạp 500K");
                                                Thread.sleep(3000);
                                                Service.gI().sendThongBao(player, "Bạn Đã Nhận Được " + mocnap4.template.name + mocnapp4.template.name + mocnappp4.template.name + " 200k Hồng Ngọc");
                                            } else {
                                                this.npcChat(player, "Bạn đã nhận mốc nạp " + Util.format(mocnap) + "\nHãy Nạp Thêm Để Nhận Mốc Nạp");
                                            }
                                        }
                                    } catch (Exception iam_vietthanh) {
                                    }
                                    break;

                            }

                        } else if (player.iDMark.getIndexMenu() == ConstNpc.QUY_DOI) {
                            PreparedStatement ps = null;
                            try (Connection con = GirlkunDB.getConnection();) {
                                switch (select) {
                                    case 0:
                                        Item thoivang = ItemService.gI().createNewItem((short) (457));
                                        thoivang.quantity += 19;
                                        if (player.getSession().vnd < 10000) {
                                            Service.gI().sendThongBao(player, "Bạn không có đủ 10k coin");
                                            return;
                                        }
                                        player.getSession().vnd -= 10000;
                                        InventoryServiceNew.gI().addItemBag(player, thoivang);
                                        Service.gI().sendThongBao(player, "Bạn Nhận Được 20 " + thoivang.template.name + " Nhớ out game vô lại");
                                        break;
                                    case 1:
                                        Item thoivangg = ItemService.gI().createNewItem((short) (457));
                                        thoivangg.quantity += 39;
                                        if (player.getSession().vnd < 20000) {
                                            Service.gI().sendThongBao(player, "Bạn không có đủ 20k coin");
                                            return;
                                        }
                                        player.getSession().vnd -= 20000;
                                        InventoryServiceNew.gI().addItemBag(player, thoivangg);
                                        Service.gI().sendThongBao(player, "Bạn Nhận Được 40 " + thoivangg.template.name + " Nhớ out game vô lại");
                                        break;
                                    case 2:
                                        Item thoivanggg = ItemService.gI().createNewItem((short) (457));
                                        thoivanggg.quantity += 59;
                                        if (player.getSession().vnd < 30000) {
                                            Service.gI().sendThongBao(player, "Bạn không có đủ 30k coin");
                                            return;
                                        }
                                        player.getSession().vnd -= 30000;
                                        InventoryServiceNew.gI().addItemBag(player, thoivanggg);
                                        Service.gI().sendThongBao(player, "Bạn Nhận Được 60 " + thoivanggg.template.name + " Nhớ out game vô lại");
                                        break;
                                    case 3:
                                        Item thoivangggg = ItemService.gI().createNewItem((short) (457));
                                        thoivangggg.quantity += 99;
                                        if (player.getSession().vnd < 50000) {
                                            Service.gI().sendThongBao(player, "Bạn không có đủ 50k coin");
                                            return;
                                        }
                                        player.getSession().vnd -= 50000;
                                        InventoryServiceNew.gI().addItemBag(player, thoivangggg);
                                        Service.gI().sendThongBao(player, "Bạn Nhận Được 1000 " + thoivangggg.template.name + " Nhớ out game vô lại");
                                        break;
                                    case 4:
                                        Item thoivanggggg = ItemService.gI().createNewItem((short) (457));
                                        thoivanggggg.quantity += 199;
                                        if (player.getSession().vnd < 100000) {
                                            Service.gI().sendThongBao(player, "Bạn không có đủ 100k coin");
                                            return;
                                        }
                                        player.getSession().vnd -= 100000;
                                        InventoryServiceNew.gI().addItemBag(player, thoivanggggg);
                                        Service.gI().sendThongBao(player, "Bạn Nhận Được 200 " + thoivanggggg.template.name + " Nhớ out game vô lại");
                                        break;
                                }

                                ps = con.prepareStatement("update account set coin = ? where id = ?");
                                ps.setInt(1, player.getSession().vnd);
                                ps.setInt(2, player.getSession().userId);
                                ps.executeUpdate();
                                ps.close();

                            } catch (Exception e) {
                                Logger.logException(NpcFactory.class, e, "Lỗi update coin " + player.name);
                            } finally {
                                try {
                                    if (ps != null) {
                                        ps.close();
                                    }
                                } catch (SQLException ex) {
                                    System.out.println("Lỗi khi update coin");

                                }
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc nvt(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                "Đến Map Leo Tháp");
                    } else if (this.mapId == 181) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Ngươi muốn tiếp tục leo tháp chứ!\n Tháp hiện tại của ngươi là :" + player.capboss,
                                "Thách Đấu", "Xem Top Lep Tháp", "Về Đảo Kame", "Từ chối");
                    }
                }
            }

            @Override

            public void confirmMenu(Player player, int select
            ) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        if (this.mapId == 181) {
                            switch (select) {
                                case 0:
                                    if (player.inventory.gem < 5) {
                                        this.npcChat(player, "Cần 5 ngọc xanh");
                                        return;
                                    }
                                    if (player.nPoint.hpMax + player.nPoint.dame < 20000) {
                                        this.npcChat(player, "Bạn còn quá yếu vui lòng quay lại sau");
                                        return;
                                    }
                                    Boss oldBossClone = BossManager.gI().getBossById(Util.createIdBossLV(player.id));
                                    if (oldBossClone != null) {
                                        oldBossClone.setDieLv(oldBossClone);
                                        this.npcChat(player, "Ấn thách đấu lại xem!");
                                    } else {
                                        int hp = 0;

//                                double dk = (player.xuatsu + 1) * 10;
                                        int dk = (player.capboss + 1) * 2;
                                        int hptong = (player.nPoint.hpMax + hp) * dk
                                                * (player.capboss >= 5 ? 2 * dk : 1);
                                        BossData bossDataClone = new BossData(
                                                "Yaritobe Super Red [Lv: " + player.capboss + "]",
                                                ConstPlayer.NAMEC,
                                                new short[]{1441, 1442, 1443, player.getFlagBag(), player.idAura,
                                                    player.getEffFront()},
                                                10_000 * dk,
                                                new int[]{10_000_000 * dk},
                                                new int[]{174},
                                                new int[][]{
                                                    {Skill.LIEN_HOAN, 7, 500},
                                                    {Skill.MASENKO, 7, 3000},
                                                    {Skill.DICH_CHUYEN_TUC_THOI, 7, 60000},
                                                    {Skill.BIEN_KHI, 1, 60000}
                                                },
                                                new String[]{"|-2|Ta sẽ tiêu diệt ngươi"}, // text
                                                // chat 1
                                                new String[]{"|-1|Ta Sẽ đập nát đầu ngươi!"}, // text chat 2
                                                new String[]{"|-1|Hẹn người lần sau"}, // text chat 3
                                                1);
                                        try {
                                            new ThachDauGoJo(Util.createIdBossLV(player.id), bossDataClone, player.zone,
                                                    player.name, player.capboss, player);
                                        } catch (Exception e) {
                                            Logger.logException(NpcFactory.class, e);
                                        }
                                        player.inventory.gem -= 5;
                                        Service.gI().sendMoney(player);
                                    }
                                    break;
                                case 1:
                                    Service.gI().showListTop(player, Manager.TopLeoThap);
                                    break;
                                case 2:
                                    ChangeMapService.gI().changeMap(player, 5, -1, 1043, 168);
                            }
                        } else if (this.mapId == 5) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMap(player, 181, -1, 513, 480);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }
//
//    public static Npc haha(int mapId, int status, int cx, int cy, int tempId, int avartar) {
//        return new Npc(mapId, status, cx, cy, tempId, avartar) {
//
//            @Override
//            public void openBaseMenu(Player player) {
//                if (canOpenNpc(player)) {
//                    if (this.mapId == 181) {
//                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
//                                "Ngươi muốn tiếp tục thách đấu cữu vĩ chứ!\nĐiểm thách đấu cửu vĩ hiện tại của ngươi là :" + player.capvithu,
//                                "Thách Đấu", "Đổi Pet Cửu Vĩ", "Về Đảo Kame", "Từ chối");
//                    }
//                }
//            }
//
//            @Override
//
//            public void confirmMenu(Player player, int select
//            ) {
//                if (canOpenNpc(player)) {
//                    if (player.iDMark.isBaseMenu()) {
//                        if (this.mapId == 181) {
//                            switch (select) {
//                                case 0:
//                                    if (player.inventory.gem < 5) {
//                                        this.npcChat(player, "Cần 5 ngọc xanh");
//                                        return;
//                                    }
//                                    if (player.nPoint.hpMax + player.nPoint.dame < 20000) {
//                                        this.npcChat(player, "Bạn còn quá yếu vui lòng quay lại sau");
//                                        return;
//                                    }
//                                    Boss oldBossClone = BossManager.gI().getBossById(Util.createIdBossLV(player.id));
//                                    if (oldBossClone != null) {
//                                        oldBossClone.setDieLv(oldBossClone);
//                                        this.npcChat(player, "Ấn thách đấu lại xem!");
//                                    } else {
//                                        int hp = 0;
//                                        int dk = (player.capvithu + 1) * 2;
//                                        int hptong = (player.nPoint.hpMax + hp) * dk
//                                                * (player.capvithu >= 5 ? 2 * dk : 1);
//                                        BossData bossDataClone = new BossData(
//                                                "Cửu Vĩ [Lv: " + player.capvithu + "]",
//                                                ConstPlayer.NAMEC,
//                                                new short[]{1456, 1457, 1458, player.getFlagBag(), player.idAura,
//                                                    player.getEffFront()},
//                                                10_000 * dk,
//                                                new int[]{10_000_000 * dk},
//                                                new int[]{174},
//                                                new int[][]{
//                                                    {Skill.LIEN_HOAN, 7, 500},
//                                                    {Skill.MASENKO, 7, 3000},
//                                                    {Skill.DICH_CHUYEN_TUC_THOI, 7, 60000},
//                                                    {Skill.THAI_DUONG_HA_SAN, 1, 60000}
//                                                },
//                                                new String[]{"|-2|Ta sẽ tiêu diệt ngươi"}, // text
//                                                // chat 1
//                                                new String[]{"|-1|Ta Sẽ đập nát đầu ngươi!"}, // text chat 2
//                                                new String[]{"|-1|Hẹn người lần sau"}, // text chat 3
//                                                1);
//                                        try {
//                                            new nvt(Util.createIdBossLV(player.id), bossDataClone, player.zone,
//                                                    player.name, player.capvithu, player);
//                                        } catch (Exception e) {
//                                            Logger.logException(NpcFactory.class, e);
//                                        }
//                                        player.inventory.gem -= 5;
//                                        Service.gI().sendMoney(player);
//                                    }
//                                    break;
//                                case 1:
//                                    if (player.capvithu >= 100) {
//                                        player.capvithu -= 100;
//                                        Item vithu = ItemService.gI().createNewItem((short) (1477));
//                                        vithu.itemOptions.add(new Item.ItemOption(50, 28));
//                                        vithu.itemOptions.add(new Item.ItemOption(77, 28));
//                                        vithu.itemOptions.add(new Item.ItemOption(103, 28));
//                                        vithu.itemOptions.add(new Item.ItemOption(207, 0));
//                                        if (Util.isTrue(30, 100)) {
//                                            vithu.itemOptions.add(new Item.ItemOption(93, Util.nextInt(1, 9)));
//                                        }
////                                      
//                                        InventoryServiceNew.gI().addItemBag(player, vithu);
//                                        Service.gI().sendThongBao(player, "Chúc Mừng Bạn Đổi Pet Thành Công !");
//                                    } else {
//                                        Service.gI().sendThongBao(player, "Không đủ điểm bạn còn " + (100 - player.point_cauca) + " Điểm nữa");
//                                    }
//                                    break;
//                                case 2:
//                                    ChangeMapService.gI().changeMap(player, 5, -1, 1043, 168);
//                            }
//                        }
//                    }
//                }
//            }
//        };
//    }

    public static Npc uron(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player pl) {
                if (canOpenNpc(pl)) {
                    ShopServiceNew.gI().opendShop(pl, "URON", false);
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {

                }
            }
        };
    }

    public static Npc caythong(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Ngươi Hãy Mang Chuông Tới Trang Trí Cho Ta", "Trang Trí\nCây Thông");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0:
                                this.npcChat(player, "Merry Christmas");
                                Item caichuong = null;

                                try {
                                    caichuong = InventoryServiceNew.gI().findItemBag(player, 1418);
                                } catch (Exception e) {
                                }
                                if (caichuong == null || caichuong.quantity < 5) {
                                    this.npcChat(player, "Ngươi Làm Gì Có Chuông Mà Trang Trí");
                                } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                    this.npcChat(player, "Hành Trang Của Ngươi Không Đủ Chỗ Trống");
                                } else {
                                    InventoryServiceNew.gI().subQuantityItemsBag(player, caichuong, 5);
                                    Item hopqua = ItemService.gI().createNewItem((short) 1389);
                                    hopqua.itemOptions.add(new Item.ItemOption(30, 0));
                                    InventoryServiceNew.gI().addItemBag(player, hopqua);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    Service.gI().sendThongBao(player, "Bạn nhận được hạt thông");
                                    this.npcChat(player, "Cảm Ơn Ngươi! Ta Rất Thích");
                                }
                                break;
                        }
                    }
                }
            }
        };
    }

    public static Npc billbingo(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                createOtherMenu(player, 0, "\b|8|Trò chơi Tài Xỉu đang được diễn ra\n\n|6|Thử vận may của bạn với trò chơi Tài Xỉu! Đặt cược và dự đoán đúng"
                        + "\n kết quả, bạn sẽ được nhận thưởng lớn. Hãy tham gia ngay và\n cùng trải nghiệm sự hồi hộp, thú vị trong trò chơi này!"
                        + "\n\n|7|(Điều kiện tham gia : mở thành viên)\n\n|2|Đặt tối thiểu: 1.000 Hồng ngọc\n Tối đa: 1.000.000.000.000 Hồng ngọc"
                        + "\n\n|7| Lưu ý : Thoát game khi chốt Kết quả sẽ MẤT Tiền cược và Tiền thưởng", "Thể lệ", "Tham gia");
            }

            @Override
            public void confirmMenu(Player pl, int select) {
                if (canOpenNpc(pl)) {
                    String time = ((TaiXiu.gI().lastTimeEnd - System.currentTimeMillis()) / 1000) + " giây";
                    if (pl.iDMark.getIndexMenu() == 0) {
                        if (select == 0) {
                            createOtherMenu(pl, ConstNpc.IGNORE_MENU, "|5|Có 2 nhà cái Tài và Xĩu, bạn chỉ được chọn 1 nhà để tham gia"
                                    + "\n\n|6|Sau khi kết thúc thời gian đặt cược. Hệ thống sẽ tung xí ngầu để biết kết quả Tài Xỉu"
                                    + "\n\nNếu Tổng số 3 con xí ngầu <=10 : XỈU\nNếu Tổng số 3 con xí ngầu >10 : TÀI\nNếu 3 Xí ngầu cùng 1 số : TAM HOA (Nhà cái lụm hết)"
                                    + "\n\n|7|Lưu ý: Số Hồng ngọc nhận được sẽ bị nhà cái lụm đi 20%. Trong quá trình diễn ra khi đặt cược nếu thoát game trong lúc phát thưởng phần quà sẽ bị HỦY", "Ok");
                        } else if (select == 1) {
                            if (TaiXiu.gI().baotri == false) {
                                if (pl.goldTai == 0 && pl.goldXiu == 0) {
                                    createOtherMenu(pl, 1, "\n|7|---ĐỠ THẾ LỒN NÀO ĐƯỢC CÁC ÔNG À---\n\n|3|Kết quả kì trước:  " + TaiXiu.gI().x + " : " + TaiXiu.gI().y + " : " + TaiXiu.gI().z
                                            + "\n\n|6|Tổng nhà TÀI: " + Util.format(TaiXiu.gI().goldTai) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt TÀI: " + TaiXiu.gI().PlayersTai.size() + " người"
                                            + "\n\n|6|Tổng nhà XỈU: " + Util.format(TaiXiu.gI().goldXiu) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt XỈU: " + TaiXiu.gI().PlayersXiu.size() + " người"
                                            + "\n\n|5|Thời gian còn lại: " + time, "Cập nhập", "Theo TÀI", "Theo XỈU", "Đóng");
                                } else if (pl.goldTai > 0) {
                                    createOtherMenu(pl, 1, "\n|7|---NHÀ CÁI TÀI XỈU---\n\n|3|Kết quả kì trước:  " + TaiXiu.gI().x + " : " + TaiXiu.gI().y + " : " + TaiXiu.gI().z
                                            + "\n\n|6|Tổng nhà TÀI: " + Util.format(TaiXiu.gI().goldTai) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt TÀI: " + TaiXiu.gI().PlayersTai.size() + " người"
                                            + "\n\n|6|Tổng nhà XỈU: " + Util.format(TaiXiu.gI().goldXiu) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt XỈU: " + TaiXiu.gI().PlayersXiu.size() + " người"
                                            + "\n\n|5|Thời gian còn lại: " + time, "Cập nhập", "Theo TÀI", "Theo XỈU", "Đóng");
                                } else {
                                    createOtherMenu(pl, 1, "\n|7|---ĐỠ THẾ LỒN NÀO ĐƯỢC CÁC ÔNG À---\n\n|3|Kết quả kì trước:  " + TaiXiu.gI().x + " : " + TaiXiu.gI().y + " : " + TaiXiu.gI().z
                                            + "\n\n|6|Tổng nhà TÀI: " + Util.format(TaiXiu.gI().goldTai) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt TÀI: " + TaiXiu.gI().PlayersTai.size() + " người"
                                            + "\n\n|6|Tổng nhà XỈU: " + Util.format(TaiXiu.gI().goldXiu) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt XỈU: " + TaiXiu.gI().PlayersXiu.size() + " người"
                                            + "\n\n|5|Thời gian còn lại: " + time, "Cập nhập", "Theo TÀI", "Theo XỈU", "Đóng");
                                }
                            } else {
                                if (pl.goldTai == 0 && pl.goldXiu == 0) {
                                    createOtherMenu(pl, 1, "\n|7|---ĐỠ THẾ LỒN NÀO ĐƯỢC CÁC ÔNG À---\n\n|3|Kết quả kì trước:  " + TaiXiu.gI().x + " : " + TaiXiu.gI().y + " : " + TaiXiu.gI().z
                                            + "\n\n|6|Tổng nhà TÀI: " + Util.format(TaiXiu.gI().goldTai) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt TÀI: " + TaiXiu.gI().PlayersTai.size() + " người"
                                            + "\n\n|6|Tổng nhà XỈU: " + Util.format(TaiXiu.gI().goldXiu) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt XỈU: " + TaiXiu.gI().PlayersXiu.size() + " người"
                                            + "\n\n|5|Thời gian còn lại: " + time, "Cập nhập", "Theo TÀI", "Theo XỈU", "Đóng");
                                } else if (pl.goldTai > 0) {
                                    createOtherMenu(pl, 1, "\n|7|---ĐỠ THẾ LỒN NÀO ĐƯỢC CÁC ÔNG À---\n\n|3|Kết quả kì trước:  " + TaiXiu.gI().x + " : " + TaiXiu.gI().y + " : " + TaiXiu.gI().z
                                            + "\n\n|6|Tổng nhà TÀI: " + Util.format(TaiXiu.gI().goldTai) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt TÀI: " + TaiXiu.gI().PlayersTai.size() + " người"
                                            + "\n\n|6|Tổng nhà XỈU: " + Util.format(TaiXiu.gI().goldXiu) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt XỈU: " + TaiXiu.gI().PlayersXiu.size() + " người"
                                            + "\n\n|5|Thời gian còn lại: " + time, "Cập nhập", "Theo TÀI", "Theo XỈU", "Đóng");
                                } else {
                                    createOtherMenu(pl, 1, "\n|7|---ĐỠ THẾ LỒN NÀO ĐƯỢC CÁC ÔNG À---\n\n|3|Kết quả kì trước:  " + TaiXiu.gI().x + " : " + TaiXiu.gI().y + " : " + TaiXiu.gI().z + "\n\n|6|Tổng nhà TÀI: " + Util.format(TaiXiu.gI().goldTai) + " Hồng ngọc"
                                            + "\n\nTổng nhà XỈU: " + Util.format(TaiXiu.gI().goldXiu) + " Hồng ngọc\n\n|5|Thời gian còn lại: " + time + "\n\n|7|Bạn đã cược Xỉu : " + Util.format(pl.goldXiu) + " Hồng ngọc" + "\n\n|7|Hệ thống sắp bảo trì", "Cập nhập", "Đóng");
                                }
                            }
                        }
                    } else if (pl.iDMark.getIndexMenu() == 1) {
                        if (((TaiXiu.gI().lastTimeEnd - System.currentTimeMillis()) / 1000) > 0 && pl.goldTai == 0 && pl.goldXiu == 0 && TaiXiu.gI().baotri == false) {
                            switch (select) {
                                case 0:
                                    createOtherMenu(pl, 1, "\n|7|---ĐỠ THẾ LỒN NÀO ĐƯỢC CÁC ÔNG À---\n\n|3|Kết quả kì trước:  " + TaiXiu.gI().x + " : " + TaiXiu.gI().y + " : " + TaiXiu.gI().z
                                            + "\n\n|6|Tổng nhà TÀI: " + Util.format(TaiXiu.gI().goldTai) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt TÀI: " + TaiXiu.gI().PlayersTai.size() + " người"
                                            + "\n\n|6|Tổng nhà XỈU: " + Util.format(TaiXiu.gI().goldXiu) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt XỈU: " + TaiXiu.gI().PlayersXiu.size() + " người"
                                            + "\n\n|5|Thời gian còn lại: " + time, "Cập nhập", "Theo TÀI", "Theo XỈU", "Đóng");
                                    break;
                                case 1:
                                    if (!pl.getSession().actived) {
                                        Service.gI().sendThongBao(pl, "Vui lòng kích hoạt tài khoản để sử dụng chức năng này");
                                    } else {
                                        Input.gI().TAI_taixiu(pl);
                                    }
                                    break;
                                case 2:
                                    if (!pl.getSession().actived) {
                                        Service.gI().sendThongBao(pl, "Vui lòng kích hoạt tài khoản để sử dụng chức năng này");
                                    } else {
                                        Input.gI().XIU_taixiu(pl);
                                    }
                                    break;
                            }
                        } else if (((TaiXiu.gI().lastTimeEnd - System.currentTimeMillis()) / 1000) > 0 && pl.goldTai > 0 && TaiXiu.gI().baotri == false) {
                            switch (select) {
                                case 0:
                                    createOtherMenu(pl, 1, "\n|7|---ĐỠ THẾ LỒN NÀO ĐƯỢC CÁC ÔNG À---\n\n|3|Kết quả kì trước:  " + TaiXiu.gI().x + " : " + TaiXiu.gI().y + " : " + TaiXiu.gI().z
                                            + "\n\n|6|Tổng nhà TÀI: " + Util.format(TaiXiu.gI().goldTai) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt TÀI: " + TaiXiu.gI().PlayersTai.size() + " người"
                                            + "\n\n|6|Tổng nhà XỈU: " + Util.format(TaiXiu.gI().goldXiu) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt XỈU: " + TaiXiu.gI().PlayersXiu.size() + " người"
                                            + "\n\n|5|Thời gian còn lại: " + time, "Cập nhập", "Theo TÀI", "Theo XỈU", "Đóng");

                                    break;
                            }
                        } else if (((TaiXiu.gI().lastTimeEnd - System.currentTimeMillis()) / 1000) > 0 && pl.goldXiu > 0 && TaiXiu.gI().baotri == false) {
                            switch (select) {
                                case 0:
                                    createOtherMenu(pl, 1, "\n|7|---ĐỠ THẾ LỒN NÀO ĐƯỢC CÁC ÔNG À---\n\n|3|Kết quả kì trước:  " + TaiXiu.gI().x + " : " + TaiXiu.gI().y + " : " + TaiXiu.gI().z
                                            + "\n\n|6|Tổng nhà TÀI: " + Util.format(TaiXiu.gI().goldTai) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt TÀI: " + TaiXiu.gI().PlayersTai.size() + " người"
                                            + "\n\n|6|Tổng nhà XỈU: " + Util.format(TaiXiu.gI().goldXiu) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt XỈU: " + TaiXiu.gI().PlayersXiu.size() + " người"
                                            + "\n\n|5|Thời gian còn lại: " + time, "Cập nhập", "Theo TÀI", "Theo XỈU", "Đóng");
                                    break;
                            }
                        } else if (((TaiXiu.gI().lastTimeEnd - System.currentTimeMillis()) / 1000) > 0 && pl.goldTai > 0 && TaiXiu.gI().baotri == true) {
                            switch (select) {
                                case 0:
                                    createOtherMenu(pl, 1, "\n|7|---ĐỠ THẾ LỒN NÀO ĐƯỢC CÁC ÔNG À---\n\n|3|Kết quả kì trước:  " + TaiXiu.gI().x + " : " + TaiXiu.gI().y + " : " + TaiXiu.gI().z
                                            + "\n\n|6|Tổng nhà TÀI: " + Util.format(TaiXiu.gI().goldTai) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt TÀI: " + TaiXiu.gI().PlayersTai.size() + " người"
                                            + "\n\n|6|Tổng nhà XỈU: " + Util.format(TaiXiu.gI().goldXiu) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt XỈU: " + TaiXiu.gI().PlayersXiu.size() + " người"
                                            + "\n\n|5|Thời gian còn lại: " + time, "Cập nhập", "Theo TÀI", "Theo XỈU", "Đóng");

                                    break;
                            }
                        } else if (((TaiXiu.gI().lastTimeEnd - System.currentTimeMillis()) / 1000) > 0 && pl.goldXiu > 0 && TaiXiu.gI().baotri == true) {
                            switch (select) {
                                case 0:
                                    createOtherMenu(pl, 1, "\n|7|---ĐỠ THẾ LỒN NÀO ĐƯỢC CÁC ÔNG À---\n\n|3|Kết quả kì trước:  " + TaiXiu.gI().x + " : " + TaiXiu.gI().y + " : " + TaiXiu.gI().z
                                            + "\n\n|6|Tổng nhà TÀI: " + Util.format(TaiXiu.gI().goldTai) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt TÀI: " + TaiXiu.gI().PlayersTai.size() + " người"
                                            + "\n\n|6|Tổng nhà XỈU: " + Util.format(TaiXiu.gI().goldXiu) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt XỈU: " + TaiXiu.gI().PlayersXiu.size() + " người"
                                            + "\n\n|5|Thời gian còn lại: " + time, "Cập nhập", "Theo TÀI", "Theo XỈU", "Đóng");

                                    break;
                            }
                        } else if (((TaiXiu.gI().lastTimeEnd - System.currentTimeMillis()) / 1000) > 0 && pl.goldXiu == 0 && pl.goldTai == 0 && TaiXiu.gI().baotri == true) {
                            switch (select) {
                                case 0:
                                    createOtherMenu(pl, 1, "\n|7|---ĐỠ THẾ LỒN NÀO ĐƯỢC CÁC ÔNG À---\n\n|3|Kết quả kì trước:  " + TaiXiu.gI().x + " : " + TaiXiu.gI().y + " : " + TaiXiu.gI().z
                                            + "\n\n|6|Tổng nhà TÀI: " + Util.format(TaiXiu.gI().goldTai) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt TÀI: " + TaiXiu.gI().PlayersTai.size() + " người"
                                            + "\n\n|6|Tổng nhà XỈU: " + Util.format(TaiXiu.gI().goldXiu) + " Hồng ngọc"
                                            + "\n|1|Tổng người đặt XỈU: " + TaiXiu.gI().PlayersXiu.size() + " người"
                                            + "\n\n|5|Thời gian còn lại: " + time, "Cập nhập", "Theo TÀI", "Theo XỈU", "Đóng");

                                    break;
                            }
                        }
                    }
                }
            }
        };
    }
  private static Npc quyLaoKame(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
           @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        if (TaskService.gI().getIdTask(player) == ConstTask.TASK_18_4) {
                            TaskService.gI().sendNextTaskMain(player);
                        } else if (TaskService.gI().getIdTask(player) == ConstTask.TASK_16_4) {
                            TaskService.gI().sendNextTaskMain(player);
                        }
                        if (player.getSession().is_gift_box) {
//                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Chào con, con muốn ta giúp gì nào?", "Giải tán bang hội", "Nhận quà\nđền bù");
                        } else {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Con muốn bỏi gì nào?",
                                   "Nói Chuyện","Từ Chối");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                             case 0:
                                this.createOtherMenu(player, ConstNpc.QUY_LAO,
                                        "Chào con, ta rất vui khi gặp con"
                                         + "\nCon muốn làm gì nào?",
                                        "Về Khu Vực Bang", "Giải tán\nBang hội","Kho báu dưới biển", "Từ chối");
                                break;
                             case 10:
                                if (player.clan != null) {
                                    if (player.clan.banDoKhoBau != null) {
                                        this.createOtherMenu(player, ConstNpc.MENU_OPENED_DBKB,
                                                "Bang hội của con đang đi tìm kho báu dưới biển cấp độ "
                                                + player.clan.banDoKhoBau.level
                                                + "\nCon có muốn đi theo không?",
                                                "Đồng ý", "Từ chối");
                                    } else {

                                        this.createOtherMenu(player, ConstNpc.MENU_OPEN_DBKB,
                                                "Đây là bản đồ kho báu \nCác con cứ yên tâm lên đường\n"
                                                + "Ở đây có ta lo\nNhớ chọn cấp độ vừa sức mình nhé",
                                                "Chọn\ncấp độ", "Từ chối");
                                    }
                                } else {
                                    this.npcChat(player, "Con phải có bang hội ta mới có thể cho con đi");
                                }
                                break;
                            case 111:
                                this.createOtherMenu(player, ConstNpc.CHUC_NANG_BANG_HOI,
                                        "Ta có hỗ trợ những chức năng Bang hội, nhà ngươi cần gì?", "Giải tán\nBang Hội", "Nâng cấp\nBang Hội", "Quyên Góp\nCapsule", "Lãnh địa\nBang Hội", "Từ chối");
                                break;
//                            case 2:
//                                if (player.getSession().player.nPoint.power == 1) {
//                                    Input.gI().createFormChooseLevelBDKB(player);
//                                } else {
//                                    this.npcChat(player, "Con đã lấy được thứ ta cần chưa?");
//                                }
//                                break;
                            case 22:
                                switch (player.playerTask.taskMain.id) {
                                    case 11:
                                        switch (player.playerTask.taskMain.index) {
                                            case 0:
                                                TaskService.gI().doneTask(player, ConstTask.TASK_11_0);
                                                Service.gI().sendThongBao(player, "Nhiệm vụ hiện tại là "
                                                        + player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).name);
                                                break;
                                            case 1:
                                                for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 25; i++) {
                                                    TaskService.gI().doneTask(player, ConstTask.TASK_11_1);
                                                    Service.gI().sendThongBao(player, "Nhiệm vụ hiện tại là "
                                                            + player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).name);
                                                }
                                                break;
                                            case 2:
                                                for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 25; i++) {
                                                    TaskService.gI().doneTask(player, ConstTask.TASK_11_2);
                                                    Service.gI().sendThongBao(player, "Nhiệm vụ hiện tại là "
                                                            + player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).name);
                                                }
                                                break;
                                            default:
                                                Service.getInstance().sendThongBao(player, "Ta đã giúp con hoàn thành nhiệm vụ rồi mau đi trả nhiệm vụ");
                                                break;
                                        }
                                        break;
                                    case 13:
                                        if (player.playerTask.taskMain.index == 0) {
                                            TaskService.gI().doneTask(player, ConstTask.TASK_13_0);
                                            Service.gI().sendThongBao(player, "Nhiệm vụ hiện tại là "
                                                    + player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).name);
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Ta đã giúp con hoàn thành nhiệm vụ rồi mau đi trả nhiệm vụ");
                                        }
                                        break;
                                    case 27:
                                        switch (player.playerTask.taskMain.index) {
                                            case 0:
                                                for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 15; i++) {
                                                    TaskService.gI().doneTask(player, ConstTask.TASK_27_0);
                                                    Service.gI().sendThongBao(player, "Nhiệm vụ hiện tại là "
                                                            + player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).name);
                                                }
                                                break;
                                            case 1:
                                                for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 15; i++) {
                                                    TaskService.gI().doneTask(player, ConstTask.TASK_27_1);
                                                    Service.gI().sendThongBao(player, "Nhiệm vụ hiện tại là "
                                                            + player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).name);
                                                }
                                                break;
                                            case 2:
                                                for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 15; i++) {
                                                    TaskService.gI().doneTask(player, ConstTask.TASK_27_2);
                                                    Service.gI().sendThongBao(player, "Nhiệm vụ hiện tại là "
                                                            + player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).name);
                                                }
                                                break;
                                            default:
                                                Service.getInstance().sendThongBao(player, "Nhiệm vụ hiện tại không thuộc diện hỗ trợ");
                                                break;
                                        }
                                        break;
                                    default:
                                        Service.getInstance().sendThongBao(player, "Nhiệm vụ hiện tại không thuộc diện hỗ trợ");
                                        break;
                                }
                        }
                          } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPENED_DBKB) {
                        switch (select) {
                            case 0:
                                if (player.isAdmin() || player.nPoint.power >= BanDoKhoBau.POWER_CAN_GO_TO_DBKB) {
                                    ChangeMapService.gI().goToDBKB(player);
                                } else {
                                    this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                            + Util.numberToMoney(BanDoKhoBau.POWER_CAN_GO_TO_DBKB));
                                }
                                break;

                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPEN_DBKB) {
                        switch (select) {
                            case 0:
                                if (player.isAdmin() || player.nPoint.power >= BanDoKhoBau.POWER_CAN_GO_TO_DBKB) {
                                    Input.gI().createFormChooseLevelBDKB(player);
                                } else {
                                    this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                            + Util.numberToMoney(BanDoKhoBau.POWER_CAN_GO_TO_DBKB));
                                }
                                break;
                        }

                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_ACCEPT_GO_TO_BDKB) {
                        switch (select) {
                            case 0:
                                BanDoKhoBauService.gI().openBanDoKhoBau(player,
                                        Byte.parseByte(String.valueOf(PLAYERID_OBJECT.get(player.id))));
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.QUY_LAO) {
                        switch (select) {
                             case 2:
                                if (player.clan != null) {
                                    if (player.clan.banDoKhoBau != null) {
                                        this.createOtherMenu(player, ConstNpc.MENU_OPENED_DBKB,
                                                "Bang hội của con đang đi tìm kho báu dưới biển cấp độ "
                                                + player.clan.banDoKhoBau.level
                                                + "\nCon có muốn đi theo không?",
                                                "Đồng ý", "Từ chối");
                                    } else {

                                        this.createOtherMenu(player, ConstNpc.MENU_OPEN_DBKB,
                                                "Đây là bản đồ kho báu \nCác con cứ yên tâm lên đường\n"
                                                + "Ở đây có ta lo\nNhớ chọn cấp độ vừa sức mình nhé",
                                                "Chọn\ncấp độ", "Từ chối");
                                    }
                                } else {
                                    this.npcChat(player, "Con phải có bang hội ta mới có thể cho con đi");
                                }
                                break;
                            case 190:
                                Clan clan = player.clan;
                                if (clan != null) {
                                    ClanMember cm = clan.getClanMember((int) player.id);
                                    if (cm != null) {
                                        if (!clan.isLeader(player)) {
                                            Service.gI().sendThongBao(player, "Yêu cầu phải là bang chủ!");
                                            break;
                                        }
                                        if (clan.members.size() > 1) {
                                            Service.gI().sendThongBao(player, "Yêu cầu bang hội chỉ còn một thành viên!");
                                            break;
                                        }
                                        NpcService.gI().createMenuConMeo(player, ConstNpc.CONFIRM_DISSOLUTION_CLAN, -1, "Bạn có chắc chắn muốn giải tán bang hội?\n( Yêu cầu sẽ không thể hoàn tác )",
                                                "Đồng ý", "Từ chối!");
                                        break;
                                    }
                                    break;
                                }
                                Service.gI().sendThongBao(player, "Yêu câu tham gia bang hội");
                                break;
                            case 1:
                                if (player.clan != null) {
                                    if (!player.clan.isLeader(player)) {
                                        Service.gI().sendThongBao(player, "Yêu cầu phải là bang chủ!");
                                        break;
                                    }
                                    if (player.clan.level >= 0 && player.clan.level <= 10) {
                                        this.createOtherMenu(player, ConstNpc.CHUC_NANG_BANG_HOI2,
                                                "Bạn có muốn Nâng cấp lên " + (player.clan.maxMember + 1) + " thành viên không?\n"
                                                + "Cần 2000 Capsule Bang\n"
                                                + "(Thu thập Capsule Bang bằng cách tiêu diệt quái tại Map Ngũ Hành Sơn \n"
                                                + "cùng các thành viên khác)", "Nâng cấp\n(20K Ruby)", "Từ chối");
                                    } else {
                                        Service.gI().sendThongBao(player, "Bang của bạn đã đạt cấp tối đa!");
                                        break;
                                    }
                                    break;
                                } else if (player.clan == null) {
                                    Service.gI().sendThongBao(player, "Yêu câu tham gia bang hội");
                                    break;
                                }
                                break;
                            case 9:
                                if (player.clan == null) {
                                    Service.gI().sendThongBao(player, "Yêu câu tham gia bang hội");
                                    break;
                                }
                                Input.gI().DonateCsbang(player);
                                break;
                            case 0:
                                if (player.getSession().player.nPoint.power >= 80000000000L) {
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 153, -1, 432);
                                } else if (player.clan == null) {
                                    Service.gI().sendThongBao(player, "Yêu câu tham gia bang hội");
                                    break;
                                } else {
                                    this.npcChat(player, "Bạn chưa đủ 80 tỷ sức mạnh để vào");
                                }
                                break;
                            case 4:
                                if (player.getSession().player.nPoint.power >= 80000000000L) {
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 153, -1, 432);
                                } else if (player.clan == null) {
                                    Service.gI().sendThongBao(player, "Yêu câu tham gia bang hội");
                                    break;
                                } else {
                                    this.npcChat(player, "Bạn chưa đủ 80 tỷ sức mạnh để vào");
                                }
                                break;
                        }
                    
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.CHUC_NANG_BANG_HOI2) {
                        Clan clan = player.clan;
                        switch (select) {
                            case 0:
                                if (player.clan.capsuleClan >= 2000 && clan.isLeader(player) && player.inventory.ruby >= 20000) {
                                    player.clan.level += 1;
                                    player.clan.maxMember += 1;
                                    player.clan.capsuleClan -= 2000;
                                    player.inventory.subRuby(20000);
                                    player.clan.update();
                                    Service.gI().sendThongBao(player, "Yêu cầu nâng cấp bang hội thành công");
                                    break;
                                } else if (player.inventory.ruby < 20000) {
                                    Service.gI().sendThongBaoOK(player, "Bạn còn thiều " + (20000 - player.inventory.ruby) + " Hồng Ngọc");
                                    break;
                                } else if (player.clan.capsuleClan < 1000) {
                                    Service.gI().sendThongBaoOK(player, "Bang của bạn còn thiều " + (2000 - player.clan.capsuleClan) + " Capsule bang");
                                    break;
                                }
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPENED_DBKB) {
                        switch (select) {
                            case 0:
                                if ( player.nPoint.power >= BanDoKhoBau.POWER_CAN_GO_TO_DBKB) {
                                    ChangeMapService.gI().goToDBKB(player);
                                } else {
                                    this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                            + Util.numberToMoney(BanDoKhoBau.POWER_CAN_GO_TO_DBKB));
                                }
                                break;

                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPEN_DBKB) {
                        switch (select) {
                            case 0:
                                if ( player.nPoint.power >= BanDoKhoBau.POWER_CAN_GO_TO_DBKB) {
                                    Input.gI().createFormChooseLevelBDKB(player);
                                } else {
                                    this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                            + Util.numberToMoney(BanDoKhoBau.POWER_CAN_GO_TO_DBKB));
                                }
                                break;
                        }

                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_ACCEPT_GO_TO_BDKB) {
                        switch (select) {
                            case 0:
                                BanDoKhoBauService.gI().openBanDoKhoBau(player, Byte.parseByte(String.valueOf(PLAYERID_OBJECT.get(player.id))));
                                break;
                        }
                  
                                            }
                }
            }
        };
    }
    public static Npc cauca1(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 176) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                "Cửa Hàng", "Top Câu Cá", "Về Lại Đảo Kame", "Đổi Pet Along", "Từ chối");
                    } else if (this.mapId == 5) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                "Đi Câu Cá", "Từ chối");

                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        if (this.mapId == 176) {
                            switch (select) {
                                case 0:
                                    ShopServiceNew.gI().opendShop(player, "CAUCA", true);
                                    break;
                                case 1:
                                    Service.gI().showListTop(player, Manager.TopCauCa);
                                    break;
                                case 2:
                                    ChangeMapService.gI().changeMap(player, 5, -1, 777, 408);
                                    break;
                                case 3:
                                    if (player.point_cauca >= 100) {
                                        player.point_cauca -= 100;
                                        Item item = ItemService.gI().createNewItem((short) (1307));
                                        item.itemOptions.add(new Item.ItemOption(50, 25));
                                        item.itemOptions.add(new Item.ItemOption(77, 25));
                                        item.itemOptions.add(new Item.ItemOption(103, 25));
                                        item.itemOptions.add(new Item.ItemOption(207, 0));
                                        item.itemOptions.add(new Item.ItemOption(33, 0));
//                                      
                                        InventoryServiceNew.gI().addItemBag(player, item);
                                        Service.gI().sendThongBao(player, "Chúc Mừng Bạn Đổi Pet Thành Công !");
                                    } else {
                                        Service.gI().sendThongBao(player, "Không đủ điểm bạn còn " + (100 - player.point_cauca) + " Điểm nữa");
                                    }
                                    break;

                            }
                        } else if (this.mapId == 5) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMap(player, 176, -1, 348, 240);
                                    break;
                            }
                        }
                    }
                }

            }
        };
    }
public static Npc cauca(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
             @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5 || this.mapId == 13) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Ngươi tìm ta có việc gì?",
                               "Nâng Chỉ Số HP","Nâng Chỉ Số Sức Đánh","Nâng Chỉ Số KI","Nâng Chỉ Số SĐCM");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        if (this.mapId == 5) {
                            switch (select) {
                                case 0:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.HP);
                                    break;
                                    case 1:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.SD);
                                    break;
                                    case 2:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.KI);
                                    break;
                                    case 3:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.GIAP);
                                    break;

                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                            switch (player.combineNew.typeCombine) {
                                case CombineServiceNew.HP:
                                     case CombineServiceNew.KI:
                                          case CombineServiceNew.SD:
                                               case CombineServiceNew.GIAP:
                                    if (select == 0) {
                                        CombineServiceNew.gI().startCombine(player);
                                    }
                                    break;
                            }
                        }
                    }
                }

            }
        };
    }
    private static Npc caychuoi(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        this.createOtherMenu(player, 1234, "|7|- •⊹٭Trùng Sinh٭⊹• -\nCắt Lá Chuối, Lá Dong" + "\n Sử Dụng Kéo Để Có Thể Cắt",
                                "Cắt Lá Chuối", "Rương Đồ");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.getIndexMenu() == 1234) {
                            switch (select) {
                                case 0:
                                    this.createOtherMenu(player, 12345, "|6|Cắt Lá Chuối" + "\n|7|Tiến Hành Cắt",
                                            "Cắt x100", "Rương Đồ");
                                    break;
                                case 1:
                                    this.createOtherMenu(player, ConstNpc.TET,
                                            "|1|Tình yêu như một dây đàn\n"
                                            + "Tình vừa được thì đàn đứt dây\n"
                                            + "Đứt dây này anh thay dây khác\n"
                                            + "Mất em rồi anh biết thay ai?",
                                            "Rương Phụ\n(" + (player.inventory.itemsBoxCrackBall.size()
                                            - InventoryServiceNew.gI().getCountEmptyListItem(player.inventory.itemsBoxCrackBall))
                                            + " món)",
                                            "Xóa Hết\nRương Phụ", "Đóng");
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == 12345) {
                            switch (select) {
                                case 0:
                                    if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1456) == null) {
                                        this.createOtherMenu(player, 12345, "|7|KHÔNG CÓ KÉO!",
                                                "Cắt x100", "Rương Đồ");
                                        break;
                                    }
                                    try {
                                        Service.gI().sendThongBao(player, "Tiến hành auto cắt x100 lần");
                                        int timex100 = 100;
                                        int count = 0;
                                        while (timex100 > 0) {
                                            timex100--;
                                            count++;
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1456), 1);
                                            InventoryServiceNew.gI().sendItemBags(player);
                                            Thread.sleep(100);
                                            if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1456) == null) {
                                                this.createOtherMenu(player, 12345, "|7|KHÔNG CÓ KÉO!\nSỐ LƯỢT ĐÃ CẮT : " + count,
                                                        "Cắt x100", "Rương Đồ");
                                                break;
                                            }
                                            if (1 + player.inventory.itemsBoxCrackBall.size() > 200) {
                                                this.createOtherMenu(player, 12345, "|7|DỪNG AUTO CẮT, RƯƠNG PHỤ ĐÃ ĐẦY!\n" + "|2|TỔNG LƯỢT CẮT : " + count + " LƯỢT" + "\n|7|VUI LÒNG LÀM TRỐNG RƯƠNG PHỤ!",
                                                        "Cắt x100", "Rương Đồ");
                                                break;
                                            }
                                            short[] nvt = {1440, 1439};
                                            Item gapx100 = Util.lachuoiladong(nvt[Util.nextInt(nvt.length)]);
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                                if (Util.isTrue(50, 100)) {
                                                    InventoryServiceNew.gI().addItemBag(player, gapx100);
                                                    this.createOtherMenu(player, 12345, "|7|ĐANG TIẾN HÀNH CẮT AUTO X1\nSỐ LƯỢT CÒN : " + timex100 + " LƯỢT\n" + "|2|Đã cắt được : " + gapx100.template.name + "\nSố kéo còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1456).quantity + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Cắt x100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12345, "|7|ĐANG TIẾN HÀNH CẮT AUTO X1\nSỐ LƯỢT CÒN : " + timex100 + " LƯỢT\n" + "|2|Cắt hụt rồi!" + "\nSố kéo còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1456).quantity + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Cắt x100", "Rương Đồ");
                                                }
                                            }
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                                if (Util.isTrue(50, 100)) {
                                                    player.inventory.itemsBoxCrackBall.add(gapx100);
                                                    this.createOtherMenu(player, 12345, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH CẮT AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex100 + " LƯỢT\n" + "|2|Đã cắt được : " + gapx100.template.name + "\nSố kéo còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1456).quantity + "\n",
                                                            "Cắt x100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12345, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH CẮT AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex100 + " LƯỢT\n" + "|2|Cắt hụt rồi!" + "\nSố kéo còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1456).quantity + "\n",
                                                            "Cắt x100", "Rương Đồ");
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                    }
                                    break;
                                case 3:
                                    this.createOtherMenu(player, ConstNpc.TET,
                                            "|1|Tình yêu như một dây đàn\n"
                                            + "Tình vừa được thì đàn đứt dây\n"
                                            + "Đứt dây này anh thay dây khác\n"
                                            + "Mất em rồi anh biết thay ai?",
                                            "Rương Phụ\n(" + (player.inventory.itemsBoxCrackBall.size()
                                            - InventoryServiceNew.gI().getCountEmptyListItem(player.inventory.itemsBoxCrackBall))
                                            + " món)",
                                            "Xóa Hết\nRương Phụ", "Đóng");
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.TET) {
                            switch (select) {
                                case 0:
                                    ShopServiceNew.gI().opendShop(player, "TET", true);
                                    break;
                                case 1:
                                    NpcService.gI().createMenuConMeo(player,
                                            ConstNpc.CONFIRM_REMOVE_ALL_ITEM_LUCKY_ROUND, this.avartar,
                                            "|3|Bạn chắc muốn xóa hết vật phẩm trong rương phụ?\n"
                                            + "|7|Sau khi xóa sẽ không thể khôi phục!",
                                            "Đồng ý", "Hủy bỏ");
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    private static Npc caytre(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        this.createOtherMenu(player, 1234, "|7|- •⊹٭Trùng Sinh٭⊹• -\nCắt Cành Tre" + "\n Sử Dụng Cái Cưa Để Có Thể Cắt",
                                "Cắt Cành Tre", "Rương Đồ");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.getIndexMenu() == 1234) {
                            switch (select) {
                                case 0:
                                    this.createOtherMenu(player, 12345, "|6|Cắt Cành Tre" + "\n|7|Tiến Hành Cắt",
                                            "Cắt x100", "Rương Đồ");
                                    break;
                                case 1:
                                    this.createOtherMenu(player, ConstNpc.TET,
                                            "|1|Tình yêu như một dây đàn\n"
                                            + "Tình vừa được thì đàn đứt dây\n"
                                            + "Đứt dây này anh thay dây khác\n"
                                            + "Mất em rồi anh biết thay ai?",
                                            "Rương Phụ\n(" + (player.inventory.itemsBoxCrackBall.size()
                                            - InventoryServiceNew.gI().getCountEmptyListItem(player.inventory.itemsBoxCrackBall))
                                            + " món)",
                                            "Xóa Hết\nRương Phụ", "Đóng");
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == 12345) {
                            switch (select) {
                                case 0:
                                    if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1457) == null) {
                                        this.createOtherMenu(player, 12345, "|7|KHÔNG CÓ CƯA!",
                                                "Cắt x100", "Rương Đồ");
                                        break;
                                    }
                                    try {
                                        Service.gI().sendThongBao(player, "Tiến hành auto cắt x100 lần");
                                        int timex100 = 100;
                                        int count = 0;
                                        while (timex100 > 0) {
                                            timex100--;
                                            count++;
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1457), 1);
                                            InventoryServiceNew.gI().sendItemBags(player);
                                            Thread.sleep(100);
                                            if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1457) == null) {
                                                this.createOtherMenu(player, 12345, "|7|KHÔNG CÓ CƯA!\nSỐ LƯỢT ĐÃ CẮT : " + count,
                                                        "Cắt x100", "Rương Đồ");
                                                break;
                                            }
                                            if (1 + player.inventory.itemsBoxCrackBall.size() > 200) {
                                                this.createOtherMenu(player, 12345, "|7|DỪNG AUTO CẮT, RƯƠNG PHỤ ĐÃ ĐẦY!\n" + "|2|TỔNG LƯỢT CẮT : " + count + " LƯỢT" + "\n|7|VUI LÒNG LÀM TRỐNG RƯƠNG PHỤ!",
                                                        "Cắt x100", "Rương Đồ");
                                                break;
                                            }
                                            short[] nvt = {1441};
                                            Item gapx100 = Util.lachuoiladong(nvt[Util.nextInt(nvt.length)]);
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                                if (Util.isTrue(50, 100)) {
                                                    InventoryServiceNew.gI().addItemBag(player, gapx100);
                                                    this.createOtherMenu(player, 12345, "|7|ĐANG TIẾN HÀNH CẮT AUTO X1\nSỐ LƯỢT CÒN : " + timex100 + " LƯỢT\n" + "|2|Đã cắt được : " + gapx100.template.name + "\nSố kéo còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1457).quantity + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Cắt x100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12345, "|7|ĐANG TIẾN HÀNH CẮT AUTO X1\nSỐ LƯỢT CÒN : " + timex100 + " LƯỢT\n" + "|2|Cắt hụt rồi!" + "\nSố kéo còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1457).quantity + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Cắt x100", "Rương Đồ");
                                                }
                                            }
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                                if (Util.isTrue(50, 100)) {
                                                    player.inventory.itemsBoxCrackBall.add(gapx100);
                                                    this.createOtherMenu(player, 12345, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH CẮT AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex100 + " LƯỢT\n" + "|2|Đã cắt được : " + gapx100.template.name + "\nSố kéo còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1457).quantity + "\n",
                                                            "Cắt x100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12345, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH CẮT AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex100 + " LƯỢT\n" + "|2|Cắt hụt rồi!" + "\nSố kéo còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1457).quantity + "\n",
                                                            "Cắt x100", "Rương Đồ");
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                    }
                                    break;
                                case 3:
                                    this.createOtherMenu(player, ConstNpc.TET,
                                            "|1|Tình yêu như một dây đàn\n"
                                            + "Tình vừa được thì đàn đứt dây\n"
                                            + "Đứt dây này anh thay dây khác\n"
                                            + "Mất em rồi anh biết thay ai?",
                                            "Rương Phụ\n(" + (player.inventory.itemsBoxCrackBall.size()
                                            - InventoryServiceNew.gI().getCountEmptyListItem(player.inventory.itemsBoxCrackBall))
                                            + " món)",
                                            "Xóa Hết\nRương Phụ", "Đóng");
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.TET) {
                            switch (select) {
                                case 0:
                                    ShopServiceNew.gI().opendShop(player, "TET", true);
                                    break;
                                case 1:
                                    NpcService.gI().createMenuConMeo(player,
                                            ConstNpc.CONFIRM_REMOVE_ALL_ITEM_LUCKY_ROUND, this.avartar,
                                            "|3|Bạn chắc muốn xóa hết vật phẩm trong rương phụ?\n"
                                            + "|7|Sau khi xóa sẽ không thể khôi phục!",
                                            "Đồng ý", "Hủy bỏ");
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc ka(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Xin chào, Em có thể giúp gì được cho anh, chị ạ",
                                "Đến Map Sự Kiện", "Đóng");
                    } else if (this.mapId == 180) {
                        createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Xin chào, Em có thể giúp gì được cho anh, chị ạ",
                                "Quay Về Đảo Kame", "Đống");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select
            ) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0: //shop
                                    if (player.getSession().player.nPoint.power >= 80000000000L) {
                                        ChangeMapService.gI().changeMap(player, 180, -1, 167, 288);
                                    } // break;
                                    else {
                                        Service.gI().sendThongBaoOK(player, "Đạt 80 Tỷ Sức Mạnh Mới Có Thể Tới");
                                    }
                                    break;
                            }
                        }
                    } else if (this.mapId == 180) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0: //shop
                                    ChangeMapService.gI().changeMap(player, 5, -1, 266, 288);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    private static Npc gapthu(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        this.createOtherMenu(player, 1234, "|7|- •⊹٭Trùng Sinh٭⊹• -\nMÁY GẮP LINH THÚ\n" + "|3|GẮP THƯỜNG : 5-10% CHỈ SỐ\nGẮP CAO CẤP : 10-20% CHỈ SỐ\nGẮP VIP : 15-25% CHỈ SỐ" + "\nGẮP X1 : GẮP THỦ CÔNG\nGẮP X10 : AUTO X10 LẦN GẮP\nGẮP X100 : AUTO X100 LẦN GẮP\n" + "|7|LƯU Ý : MỌI CHỈ SỐ ĐỀU RANDOM KHÔNG CÓ OPTION NHẤT ĐỊNH\nNẾU MUỐN NGƯNG AUTO GẤP CHỈ CẦN THOÁT GAME VÀ VÀO LẠI!",
                                "Gắp Thường", "Gắp Cao Cấp", "Gắp VIP", "Xem Top", "Rương Đồ");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.getIndexMenu() == 1234) {
                            switch (select) {
                                case 0:
                                    this.createOtherMenu(player, 12345, "|6|Gắp Thú Thường" + "\n|7|Tiến Hành Gắp",
                                            "Gắp x1", "Gắp x10", "Gắp x100", "Rương Đồ");
                                    break;
                                case 1:
                                    this.createOtherMenu(player, 12346, "|6|Gắp Thú Cao Cấp" + "\n|7|Tiến Hành Gắp",
                                            "Gắp x1", "Gắp x10", "Gắp x100", "Rương Đồ");
                                    break;
                                case 2:
                                    this.createOtherMenu(player, 12347, "|6|Gắp Thú VIP" + "\n|7|Tiến Hành Gắp",
                                            "Gắp x1", "Gắp x10", "Gắp x100", "Rương Đồ");
                                    break;
                                case 3:
                                    Service.gI().showListTop(player, Manager.TopGapThu);
                                    break;
                                case 4:
                                    this.createOtherMenu(player, ConstNpc.RUONG_PHU,
                                            "|1|Tình yêu như một dây đàn\n"
                                            + "Tình vừa được thì đàn đứt dây\n"
                                            + "Đứt dây này anh thay dây khác\n"
                                            + "Mất em rồi anh biết thay ai?",
                                            "Rương Phụ\n(" + (player.inventory.itemsBoxCrackBall.size()
                                            - InventoryServiceNew.gI().getCountEmptyListItem(player.inventory.itemsBoxCrackBall))
                                            + " món)",
                                            "Xóa Hết\nRương Phụ", "Đóng");
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == 12345) {
                            switch (select) {
                                case 0:
                                    if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394) == null) {
                                        this.createOtherMenu(player, 12345, "|7|HẾT XU!",
                                                "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                        break;
                                    }
                                    try {
                                        Service.gI().sendThongBao(player, "Tiến hành auto gắp x1 lần");
                                        int timex1 = 1;
                                        int count = 0;
                                        while (timex1 > 0) {
                                            timex1--;
                                            count++;
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394), 1);
                                            InventoryServiceNew.gI().sendItemBags(player);
                                            Thread.sleep(100);
                                            if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394) == null) {
                                                this.createOtherMenu(player, 12345, "|7|HẾT XU!\nSỐ LƯỢT ĐÃ GẮP : " + count,
                                                        "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                break;
                                            }
                                            if (1 + player.inventory.itemsBoxCrackBall.size() > 200) {
                                                this.createOtherMenu(player, 12345, "|7|DỪNG AUTO GẮP, RƯƠNG PHỤ ĐÃ ĐẦY!\n" + "|2|TỔNG LƯỢT GẮP : " + count + " LƯỢT" + "\n|7|VUI LÒNG LÀM TRỐNG RƯƠNG PHỤ!",
                                                        "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                break;
                                            }
                                            player.point_gapthu += 1;
                                            short[] nvt = {2019, 2020, 2021};
                                            Item gapx1 = Util.petrandom(nvt[Util.nextInt(nvt.length)]);
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                                if (Util.isTrue(10, 100)) {
                                                    InventoryServiceNew.gI().addItemBag(player, gapx1);
                                                    this.createOtherMenu(player, 12345, "|7|ĐANG TIẾN HÀNH GẮP AUTO X1\nSỐ LƯỢT CÒN : " + timex1 + " LƯỢT\n" + "|2|Đã gắp được : " + gapx1.template.name + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12345, "|7|ĐANG TIẾN HÀNH GẮP AUTO X1\nSỐ LƯỢT CÒN : " + timex1 + " LƯỢT\n" + "|2|Gắp hụt rồi!" + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                }
                                            }
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                                if (Util.isTrue(10, 100)) {
                                                    player.inventory.itemsBoxCrackBall.add(gapx1);
                                                    this.createOtherMenu(player, 12345, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex1 + " LƯỢT\n" + "|2|Đã gắp được : " + gapx1.template.name + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu,
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12345, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex1 + " LƯỢT\n" + "|2|Gắp hụt rồi!" + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu,
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                    }
                                    break;
                                case 1:
                                    if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394) == null) {
                                        this.createOtherMenu(player, 12345, "|7|HẾT XU!",
                                                "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                        break;
                                    }
                                    try {
                                        Service.gI().sendThongBao(player, "Tiến hành auto gắp x10 lần");
                                        int timex10 = 10;
                                        int count = 0;
                                        while (timex10 > 0) {
                                            timex10--;
                                            count++;
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394), 1);
                                            InventoryServiceNew.gI().sendItemBags(player);
                                            Thread.sleep(100);
                                            if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394) == null) {
                                                this.createOtherMenu(player, 12345, "|7|HẾT XU!\nSỐ LƯỢT ĐÃ GẮP : " + count,
                                                        "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                break;
                                            }
                                            if (1 + player.inventory.itemsBoxCrackBall.size() > 200) {
                                                this.createOtherMenu(player, 12345, "|7|DỪNG AUTO GẮP, RƯƠNG PHỤ ĐÃ ĐẦY!\n" + "|2|TỔNG LƯỢT GẮP : " + count + " LƯỢT" + "\n|7|VUI LÒNG LÀM TRỐNG RƯƠNG PHỤ!",
                                                        "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                break;
                                            }
                                            player.point_gapthu += 1;
                                            short[] bkt = {2019, 2020, 2021};
                                            Item gapx10 = Util.petrandom(bkt[Util.nextInt(bkt.length)]);
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                                if (Util.isTrue(10, 100)) {
                                                    InventoryServiceNew.gI().addItemBag(player, gapx10);
                                                    this.createOtherMenu(player, 12345, "|7|ĐANG TIẾN HÀNH GẮP AUTO X10\nSỐ LƯỢT CÒN : " + timex10 + " LƯỢT\n" + "|2|Đã gắp được : " + gapx10.template.name + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12345, "|7|ĐANG TIẾN HÀNH GẮP AUTO X10\nSỐ LƯỢT CÒN : " + timex10 + " LƯỢT\n" + "|2|Gắp hụt rồi!" + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                }
                                            }
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                                if (Util.isTrue(10, 100)) {
                                                    player.inventory.itemsBoxCrackBall.add(gapx10);
                                                    this.createOtherMenu(player, 12345, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex10 + " LƯỢT\n" + "|2|Đã gắp được : " + gapx10.template.name + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu,
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12345, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex10 + " LƯỢT\n" + "|2|Gắp hụt rồi!" + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu,
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                    }
                                    break;
                                case 2:
                                    if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394) == null) {
                                        this.createOtherMenu(player, 12345, "|7|HẾT XU!",
                                                "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                        break;
                                    }
                                    try {
                                        Service.gI().sendThongBao(player, "Tiến hành auto gắp x100 lần");
                                        int timex100 = 100;
                                        int count = 0;
                                        while (timex100 > 0) {
                                            timex100--;
                                            count++;
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394), 1);
                                            InventoryServiceNew.gI().sendItemBags(player);
                                            Thread.sleep(100);
                                            if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394) == null) {
                                                this.createOtherMenu(player, 12345, "|7|HẾT XU!\nSỐ LƯỢT ĐÃ GẮP : " + count,
                                                        "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                break;
                                            }
                                            if (1 + player.inventory.itemsBoxCrackBall.size() > 200) {
                                                this.createOtherMenu(player, 12345, "|7|DỪNG AUTO GẮP, RƯƠNG PHỤ ĐÃ ĐẦY!\n" + "|2|TỔNG LƯỢT GẮP : " + count + " LƯỢT" + "\n|7|VUI LÒNG LÀM TRỐNG RƯƠNG PHỤ!",
                                                        "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                break;
                                            }
                                            player.point_gapthu += 1;
                                            short[] bkt = {2019, 2020, 2021};
                                            Item gapx100 = Util.petrandom(bkt[Util.nextInt(bkt.length)]);
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                                if (Util.isTrue(10, 100)) {
                                                    InventoryServiceNew.gI().addItemBag(player, gapx100);
                                                    this.createOtherMenu(player, 12345, "|7|ĐANG TIẾN HÀNH GẮP AUTO X1\nSỐ LƯỢT CÒN : " + timex100 + " LƯỢT\n" + "|2|Đã gắp được : " + gapx100.template.name + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12345, "|7|ĐANG TIẾN HÀNH GẮP AUTO X1\nSỐ LƯỢT CÒN : " + timex100 + " LƯỢT\n" + "|2|Gắp hụt rồi!" + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                }
                                            }
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                                if (Util.isTrue(10, 100)) {
                                                    player.inventory.itemsBoxCrackBall.add(gapx100);
                                                    this.createOtherMenu(player, 12345, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex100 + " LƯỢT\n" + "|2|Đã gắp được : " + gapx100.template.name + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu,
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12345, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex100 + " LƯỢT\n" + "|2|Gắp hụt rồi!" + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1394).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu,
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                    }
                                    break;
                                case 3:
                                    this.createOtherMenu(player, ConstNpc.RUONG_PHU,
                                            "|1|Tình yêu như một dây đàn\n"
                                            + "Tình vừa được thì đàn đứt dây\n"
                                            + "Đứt dây này anh thay dây khác\n"
                                            + "Mất em rồi anh biết thay ai?",
                                            "Rương Phụ\n(" + (player.inventory.itemsBoxCrackBall.size()
                                            - InventoryServiceNew.gI().getCountEmptyListItem(player.inventory.itemsBoxCrackBall))
                                            + " món)",
                                            "Xóa Hết\nRương Phụ", "Đóng");
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == 12346) {
                            switch (select) {
                                case 0:
                                    if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395) == null) {
                                        this.createOtherMenu(player, 12346, "|7|HẾT XU!",
                                                "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                        break;
                                    }
                                    try {
                                        Service.gI().sendThongBao(player, "Tiến hành auto gắp x1 lần");
                                        int timex1 = 1;
                                        int count = 0;
                                        while (timex1 > 0) {
                                            timex1--;
                                            count++;
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395), 2);
                                            InventoryServiceNew.gI().sendItemBags(player);
                                            Thread.sleep(100);
                                            if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395) == null) {
                                                this.createOtherMenu(player, 12346, "|7|HẾT XU!\nSỐ LƯỢT ĐÃ GẮP : " + count,
                                                        "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                break;
                                            }
                                            if (1 + player.inventory.itemsBoxCrackBall.size() > 200) {
                                                this.createOtherMenu(player, 12346, "|7|DỪNG AUTO GẮP, RƯƠNG PHỤ ĐÃ ĐẦY!\n" + "|2|TỔNG LƯỢT GẮP : " + count + " LƯỢT" + "\n|7|VUI LÒNG LÀM TRỐNG RƯƠNG PHỤ!",
                                                        "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                break;
                                            }
                                            player.point_gapthu += 1;
                                            short[] bkt = {2022, 2023, 2024};
                                            Item gapx1 = Util.petccrandom(bkt[Util.nextInt(bkt.length)]);
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                                if (Util.isTrue(10, 100)) {
                                                    InventoryServiceNew.gI().addItemBag(player, gapx1);
                                                    this.createOtherMenu(player, 12346, "|7|ĐANG TIẾN HÀNH GẮP AUTO X1\nSỐ LƯỢT CÒN : " + timex1 + " LƯỢT\n" + "|2|Đã gắp được : " + gapx1.template.name + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12346, "|7|ĐANG TIẾN HÀNH GẮP AUTO X1\nSỐ LƯỢT CÒN : " + timex1 + " LƯỢT\n" + "|2|Gắp hụt rồi!" + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                }
                                            }
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                                if (Util.isTrue(10, 100)) {
                                                    player.inventory.itemsBoxCrackBall.add(gapx1);
                                                    this.createOtherMenu(player, 12346, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex1 + " LƯỢT\n" + "|2|Đã gắp được : " + gapx1.template.name + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu,
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12346, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex1 + " LƯỢT\n" + "|2|Gắp hụt rồi!" + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu,
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                    }
                                    break;
                                case 1:
                                    if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395) == null) {
                                        this.createOtherMenu(player, 12346, "|7|HẾT XU!",
                                                "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                        break;
                                    }
                                    try {
                                        Service.gI().sendThongBao(player, "Tiến hành auto gắp x10 lần");
                                        int timex10 = 10;
                                        int count = 0;
                                        while (timex10 > 0) {
                                            timex10--;
                                            count++;
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395), 2);
                                            InventoryServiceNew.gI().sendItemBags(player);
                                            Thread.sleep(100);
                                            if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395) == null) {
                                                this.createOtherMenu(player, 12346, "|7|HẾT XU!\nSỐ LƯỢT ĐÃ GẮP : " + count,
                                                        "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                break;
                                            }
                                            if (1 + player.inventory.itemsBoxCrackBall.size() > 200) {
                                                this.createOtherMenu(player, 12346, "|7|DỪNG AUTO GẮP, RƯƠNG PHỤ ĐÃ ĐẦY!\n" + "|2|TỔNG LƯỢT GẮP : " + count + " LƯỢT" + "\n|7|VUI LÒNG LÀM TRỐNG RƯƠNG PHỤ!",
                                                        "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                break;
                                            }
                                            player.point_gapthu += 1;
                                            short[] bkt = {2022, 2023, 2024};
                                            Item gapx10 = Util.petccrandom(bkt[Util.nextInt(bkt.length)]);
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                                if (Util.isTrue(10, 100)) {
                                                    InventoryServiceNew.gI().addItemBag(player, gapx10);
                                                    this.createOtherMenu(player, 12346, "|7|ĐANG TIẾN HÀNH GẮP AUTO X10\nSỐ LƯỢT CÒN : " + timex10 + " LƯỢT\n" + "|2|Đã gắp được : " + gapx10.template.name + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12346, "|7|ĐANG TIẾN HÀNH GẮP AUTO X10\nSỐ LƯỢT CÒN : " + timex10 + " LƯỢT\n" + "|2|Gắp hụt rồi!" + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                }
                                            }
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                                if (Util.isTrue(10, 100)) {
                                                    player.inventory.itemsBoxCrackBall.add(gapx10);
                                                    this.createOtherMenu(player, 12346, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex10 + " LƯỢT\n" + "|2|Đã gắp được : " + gapx10.template.name + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu,
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12346, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex10 + " LƯỢT\n" + "|2|Gắp hụt rồi!" + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu,
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                    }
                                    break;
                                case 2:
                                    if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395) == null) {
                                        this.createOtherMenu(player, 12346, "|7|HẾT XU!",
                                                "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                        break;
                                    }
                                    try {
                                        Service.gI().sendThongBao(player, "Tiến hành auto gắp x100 lần");
                                        int timex100 = 100;
                                        int count = 0;
                                        while (timex100 > 0) {
                                            timex100--;
                                            count++;
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395), 2);
                                            InventoryServiceNew.gI().sendItemBags(player);
                                            Thread.sleep(100);
                                            if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395) == null) {
                                                this.createOtherMenu(player, 12346, "|7|HẾT XU!\nSỐ LƯỢT ĐÃ GẮP : " + count,
                                                        "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                break;
                                            }
                                            if (1 + player.inventory.itemsBoxCrackBall.size() > 200) {
                                                this.createOtherMenu(player, 12346, "|7|DỪNG AUTO GẮP, RƯƠNG PHỤ ĐÃ ĐẦY!\n" + "|2|TỔNG LƯỢT GẮP : " + count + " LƯỢT" + "\n|7|VUI LÒNG LÀM TRỐNG RƯƠNG PHỤ!",
                                                        "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                break;
                                            }
                                            player.point_gapthu += 1;
                                            short[] bkt = {2022, 2023, 2024};
                                            Item gapx100 = Util.petccrandom(bkt[Util.nextInt(bkt.length)]);
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                                if (Util.isTrue(10, 100)) {
                                                    InventoryServiceNew.gI().addItemBag(player, gapx100);
                                                    this.createOtherMenu(player, 12346, "|7|ĐANG TIẾN HÀNH GẮP AUTO X1\nSỐ LƯỢT CÒN : " + timex100 + " LƯỢT\n" + "|2|Đã gắp được : " + gapx100.template.name + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12346, "|7|ĐANG TIẾN HÀNH GẮP AUTO X1\nSỐ LƯỢT CÒN : " + timex100 + " LƯỢT\n" + "|2|Gắp hụt rồi!" + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                }
                                            }
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                                if (Util.isTrue(10, 100)) {
                                                    player.inventory.itemsBoxCrackBall.add(gapx100);
                                                    this.createOtherMenu(player, 12346, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex100 + " LƯỢT\n" + "|2|Đã gắp được : " + gapx100.template.name + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu,
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12346, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex100 + " LƯỢT\n" + "|2|Gắp hụt rồi!" + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1395).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu,
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                    }
                                    break;
                                case 3:
                                    this.createOtherMenu(player, ConstNpc.RUONG_PHU,
                                            "|1|Tình yêu như một dây đàn\n"
                                            + "Tình vừa được thì đàn đứt dây\n"
                                            + "Đứt dây này anh thay dây khác\n"
                                            + "Mất em rồi anh biết thay ai?",
                                            "Rương Phụ\n(" + (player.inventory.itemsBoxCrackBall.size()
                                            - InventoryServiceNew.gI().getCountEmptyListItem(player.inventory.itemsBoxCrackBall))
                                            + " món)",
                                            "Xóa Hết\nRương Phụ", "Đóng");
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == 12347) {
                            switch (select) {
                                case 0:
                                    if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396) == null) {
                                        this.createOtherMenu(player, 12347, "|7|HẾT XU!",
                                                "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                        break;
                                    }
                                    try {
                                        Service.gI().sendThongBao(player, "Tiến hành auto gắp x1 lần");
                                        int timex1 = 1;
                                        int count = 0;
                                        while (timex1 > 0) {
                                            timex1--;
                                            count++;
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396), 3);
                                            InventoryServiceNew.gI().sendItemBags(player);
                                            Thread.sleep(100);
                                            if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396) == null) {
                                                this.createOtherMenu(player, 12347, "|7|HẾT XU!\nSỐ LƯỢT ĐÃ GẮP : " + count,
                                                        "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                break;
                                            }
                                            if (1 + player.inventory.itemsBoxCrackBall.size() > 200) {
                                                this.createOtherMenu(player, 12347, "|7|DỪNG AUTO GẮP, RƯƠNG PHỤ ĐÃ ĐẦY!\n" + "|2|TỔNG LƯỢT GẮP : " + count + " LƯỢT" + "\n|7|VUI LÒNG LÀM TRỐNG RƯƠNG PHỤ!",
                                                        "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                break;
                                            }
                                            player.point_gapthu += 1;
                                            short[] bkt = {1397, 1398, 1399, 1400, 1401, 1402, 1377};
                                            Item gapx1 = Util.petviprandom(bkt[Util.nextInt(bkt.length)]);
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                                if (Util.isTrue(10, 100)) {
                                                    InventoryServiceNew.gI().addItemBag(player, gapx1);
                                                    this.createOtherMenu(player, 12347, "|7|ĐANG TIẾN HÀNH GẮP AUTO X1\nSỐ LƯỢT CÒN : " + timex1 + " LƯỢT\n" + "|2|Đã gắp được : " + gapx1.template.name + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12347, "|7|ĐANG TIẾN HÀNH GẮP AUTO X1\nSỐ LƯỢT CÒN : " + timex1 + " LƯỢT\n" + "|2|Gắp hụt rồi!" + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                }
                                            }
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                                if (Util.isTrue(10, 100)) {
                                                    player.inventory.itemsBoxCrackBall.add(gapx1);
                                                    this.createOtherMenu(player, 12347, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex1 + " LƯỢT\n" + "|2|Đã gắp được : " + gapx1.template.name + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu,
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12347, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex1 + " LƯỢT\n" + "|2|Gắp hụt rồi!" + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu,
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                    }
                                    break;
                                case 1:
                                    if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396) == null) {
                                        this.createOtherMenu(player, 12347, "|7|HẾT XU!",
                                                "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                        break;
                                    }
                                    try {
                                        Service.gI().sendThongBao(player, "Tiến hành auto gắp x10 lần");
                                        int timex10 = 10;
                                        int count = 0;
                                        while (timex10 > 0) {
                                            timex10--;
                                            count++;
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396), 3);
                                            InventoryServiceNew.gI().sendItemBags(player);
                                            Thread.sleep(100);
                                            if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396) == null) {
                                                this.createOtherMenu(player, 12347, "|7|HẾT XU!\nSỐ LƯỢT ĐÃ GẮP : " + count,
                                                        "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                break;
                                            }
                                            if (1 + player.inventory.itemsBoxCrackBall.size() > 200) {
                                                this.createOtherMenu(player, 12347, "|7|DỪNG AUTO GẮP, RƯƠNG PHỤ ĐÃ ĐẦY!\n" + "|2|TỔNG LƯỢT GẮP : " + count + " LƯỢT" + "\n|7|VUI LÒNG LÀM TRỐNG RƯƠNG PHỤ!",
                                                        "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                break;
                                            }
                                            player.point_gapthu += 1;
                                            short[] bkt = {1397, 1398, 1399, 1400, 1401, 1402, 1377};
                                            Item gapx10 = Util.petviprandom(bkt[Util.nextInt(bkt.length)]);
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                                if (Util.isTrue(10, 100)) {
                                                    InventoryServiceNew.gI().addItemBag(player, gapx10);
                                                    this.createOtherMenu(player, 12347, "|7|ĐANG TIẾN HÀNH GẮP AUTO X10\nSỐ LƯỢT CÒN : " + timex10 + " LƯỢT\n" + "|2|Đã gắp được : " + gapx10.template.name + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12347, "|7|ĐANG TIẾN HÀNH GẮP AUTO X10\nSỐ LƯỢT CÒN : " + timex10 + " LƯỢT\n" + "|2|Gắp hụt rồi!" + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                }
                                            }
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                                if (Util.isTrue(10, 100)) {
                                                    player.inventory.itemsBoxCrackBall.add(gapx10);
                                                    this.createOtherMenu(player, 12347, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex10 + " LƯỢT\n" + "|2|Đã gắp được : " + gapx10.template.name + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu,
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12347, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex10 + " LƯỢT\n" + "|2|Gắp hụt rồi!" + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu,
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                    }
                                    break;
                                case 2:
                                    if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396) == null) {
                                        this.createOtherMenu(player, 12347, "|7|HẾT XU!",
                                                "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                        break;
                                    }
                                    try {
                                        Service.gI().sendThongBao(player, "Tiến hành auto gắp x100 lần");
                                        int timex100 = 100;
                                        int count = 0;
                                        while (timex100 > 0) {
                                            timex100--;
                                            count++;
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396), 3);
                                            InventoryServiceNew.gI().sendItemBags(player);
                                            Thread.sleep(100);
                                            if (InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396) == null) {
                                                this.createOtherMenu(player, 12347, "|7|HẾT XU!\nSỐ LƯỢT ĐÃ GẮP : " + count,
                                                        "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                break;
                                            }
                                            if (1 + player.inventory.itemsBoxCrackBall.size() > 200) {
                                                this.createOtherMenu(player, 12347, "|7|DỪNG AUTO GẮP, RƯƠNG PHỤ ĐÃ ĐẦY!\n" + "|2|TỔNG LƯỢT GẮP : " + count + " LƯỢT" + "\n|7|VUI LÒNG LÀM TRỐNG RƯƠNG PHỤ!",
                                                        "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                break;
                                            }
                                            player.point_gapthu += 1;
                                            short[] bkt = {1397, 1398, 1399, 1400, 1401, 1402, 1377};
                                            Item gapx100 = Util.petviprandom(bkt[Util.nextInt(bkt.length)]);
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                                if (Util.isTrue(10, 100)) {
                                                    InventoryServiceNew.gI().addItemBag(player, gapx100);
                                                    this.createOtherMenu(player, 12347, "|7|ĐANG TIẾN HÀNH GẮP AUTO X1\nSỐ LƯỢT CÒN : " + timex100 + " LƯỢT\n" + "|2|Đã gắp được : " + gapx100.template.name + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12347, "|7|ĐANG TIẾN HÀNH GẮP AUTO X1\nSỐ LƯỢT CÒN : " + timex100 + " LƯỢT\n" + "|2|Gắp hụt rồi!" + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu + "\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                }
                                            }
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                                if (Util.isTrue(10, 100)) {
                                                    player.inventory.itemsBoxCrackBall.add(gapx100);
                                                    this.createOtherMenu(player, 12347, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex100 + " LƯỢT\n" + "|2|Đã gắp được : " + gapx100.template.name + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu,
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                } else {
                                                    this.createOtherMenu(player, 12347, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : " + timex100 + " LƯỢT\n" + "|2|Gắp hụt rồi!" + "\nSố xu còn : " + InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1396).quantity + "\n|7|TỔNG ĐIỂM : " + player.point_gapthu,
                                                            "Gắp X1", "Gắp X10", "Gắp X100", "Rương Đồ");
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                    }
                                    break;
                                case 3:
                                    this.createOtherMenu(player, ConstNpc.RUONG_PHU,
                                            "|1|Tình yêu như một dây đàn\n"
                                            + "Tình vừa được thì đàn đứt dây\n"
                                            + "Đứt dây này anh thay dây khác\n"
                                            + "Mất em rồi anh biết thay ai?",
                                            "Rương Phụ\n(" + (player.inventory.itemsBoxCrackBall.size()
                                            - InventoryServiceNew.gI().getCountEmptyListItem(player.inventory.itemsBoxCrackBall))
                                            + " món)",
                                            "Xóa Hết\nRương Phụ", "Đóng");
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.RUONG_PHU) {
                            switch (select) {
                                case 0:
                                    ShopServiceNew.gI().opendShop(player, "RUONG_PHU", true);
                                    break;
                                case 1:
                                    NpcService.gI().createMenuConMeo(player,
                                            ConstNpc.CONFIRM_REMOVE_ALL_ITEM_LUCKY_ROUND, this.avartar,
                                            "|3|Bạn chắc muốn xóa hết vật phẩm trong rương phụ?\n"
                                            + "|7|Sau khi xóa sẽ không thể khôi phục!",
                                            "Đồng ý", "Hủy bỏ");
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc baHatMit(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Ngươi tìm ta có việc gì?",
                                "Võ đài\nSinh Tử",
                                "Ép sao\ntrang bị",
                                "Pha lê\nhóa\ntrang bị",
                               "Nâng Cấp\nVật Phẩm",
                                "Gia Hạn Vật Phẩm");
                    } else if (this.mapId == 121) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Ngươi tìm ta có việc gì?",
                                "Về đảo\nrùa");

                    } else {

                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Ngươi tìm ta có việc gì?",
                                "Thưởng\n10 Đá nâng cấp\n Ngẫu Nhiên",
                                "Cửa hàng\nBùa",
                                "Nâng cấp\nVật phẩm",
                                "Bông tai\nPorata",
                                "Nhập\nNgọc Rồng");
//                                "Nâng cấp đồ Thiên Sứ",
//                                "Nâng cấp đồ thần linh");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                 case 0:
                                    ChangeMapService.gI().changeMapNonSpaceship(player, 112, 203, 408);
                                    break;
                                case 1:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.EP_SAO_TRANG_BI);
                                    break;
                                case 2:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.PHA_LE_HOA_TRANG_BI);
                                    break;
                                     case 3:
                                createOtherMenu(player, ConstNpc.NANGCAITRANG,
                                        "Con muốn nâng cấp chỉ số nào\nLưu ý khi nâng cấp cần chọn đúng chỉ số có trên cải trang \n Trùng với id trên chức năng nâng cấp",
                                        "Nâng Chỉ Số\n Sức Đánh","Nâng Chỉ Số\nHP","Nâng Chỉ Số\nKI","Nâng Chỉ Số\nSĐCM","Nâng SKH\nTừ Đồ HD","Đập \nSét Kích Hoạt","Nâng Cấp\nChân Mệnh");
                                break;
                               
                              
                                case 4:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.GIA_HAN_VAT_PHAM);
                                    break;
                            }
                        }
                            else if (player.iDMark.getIndexMenu() == ConstNpc.NANGCAITRANG) {
                        switch (select) {
                           case 0:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.SD);
                                    break;
                           case 1:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.HP);
                                    break;
                           case 2:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.KI);
                                    break;
                           case 3:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.GIAP);
                                    break;
                                     case 4:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_HUY_DIET_LEN_SKH_VIP);
                                    break;
                                case 5:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.DAP_SET_KICH_HOAT);
                                    break;
                                case 6:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_CHAN_MENH);
                                    break;
                        }
                    
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                            switch (player.combineNew.typeCombine) {
                                case CombineServiceNew.EP_SAO_TRANG_BI:
                                case CombineServiceNew.PHA_LE_HOA_TRANG_BI:
                                case CombineServiceNew.NANG_HUY_DIET_LEN_SKH_VIP:
                                case CombineServiceNew.DAP_SET_KICH_HOAT:
                                case CombineServiceNew.NANG_CAP_CHAN_MENH:
                                     case CombineServiceNew.SD:
                                         case CombineServiceNew.GIA_HAN_VAT_PHAM:
                                    switch (select) {
                                        case 0:
                                            if (player.combineNew.typeCombine == CombineServiceNew.PHA_LE_HOA_TRANG_BI) {
                                                player.combineNew.quantities = 1;
                                            }
                                            break;
                                        case 1:
                                            if (player.combineNew.typeCombine == CombineServiceNew.PHA_LE_HOA_TRANG_BI) {
                                                player.combineNew.quantities = 10;
                                            }
                                            break;
                                        case 2:
                                            if (player.combineNew.typeCombine == CombineServiceNew.PHA_LE_HOA_TRANG_BI) {
                                                player.combineNew.quantities = 100;
                                            }
                                            break;
                                    }
                                    CombineServiceNew.gI().startCombine(player);
                                    break;
                            }
                        }
                    } else if (this.mapId == 42 || this.mapId == 43 || this.mapId == 44 || this.mapId == 84) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:                              
                                if (player.capvithu == 0){
                                    short[] rdpet1 = new short[]{220,221,222,223,224};
                                        Item _item = ItemService.gI().createNewItem((short)rdpet1[Util.nextInt(rdpet1.length-1)], 10);                                            
                                        _item.itemOptions.add(new Item.ItemOption(30, Util.nextInt(1,24)));
                                        _item.itemOptions.add(new Item.ItemOption(93, Util.nextInt(1,24)));
                                        InventoryServiceNew.gI().addItemBag(player, _item);                                        
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        Service.getInstance().sendThongBao(player, "Chúc mừng bạn nhận được " + _item.template.name);                                        
                                        ServerNotify.gI().notify("Chúc mừng " + player.name + " nhận được "+_item.template.name+" " );
                                    player.capvithu++;
                                }else{
                                    Service.getInstance().sendThongBao(player, "Đã nhận thưởng rồi");
                                }
                           
                            break;
                               
                                case 1: //shop bùa
                                    createOtherMenu(player, ConstNpc.MENU_OPTION_SHOP_BUA,
                                            "Bùa của ta rất lợi hại, nhìn ngươi yếu đuối thế này, chắc muốn mua bùa để "
                                            + "mạnh mẽ à, mua không ta bán cho, xài rồi lại thích cho mà xem.",
                                            "Bùa\n1 giờ", "Bùa\n8 giờ", "Bùa\n1 tháng", "Đóng");
                                    break;
                                case 2:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_VAT_PHAM);
                                    break;
                                case 3:
                                    createOtherMenu(player, ConstNpc.MENU_NANG_BONGTAI,
                                            "Ngươi muốn nâng bông tai à",
                                           "Nâng Cấp\nBông Tai","Mở Chỉ Số\nBông Tai", "Nâng Bông Tai\nCấp 3", "Mở Chỉ Số\nBông Tai\nCấp 3", "Nâng Bông Tai\nCấp 4", "Mở Chỉ Số\nBông Tai\nCấp 3", "Đóng");
                                    break;
                                case 4:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NHAP_NGOC_RONG);
                                    break;
                                case 5:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.CHE_TAO_TRANG_BI_TS);
                                    break;
                                case 6:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_DO_THAN);
                                    break;

                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPTION_SHOP_BUA) {
                            switch (select) {
                                case 0:
                                    ShopServiceNew.gI().opendShop(player, "BUA_1H", true);
                                    break;
                                case 1:
                                    ShopServiceNew.gI().opendShop(player, "BUA_8H", true);
                                    break;
                                case 2:
                                    ShopServiceNew.gI().opendShop(player, "BUA_1M", true);
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_NANG_BONGTAI) {
                            switch (select) {
                                case 0:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_BONG_TAI);
                                    break;
                                case 1:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.MO_CHI_SO_BONG_TAI);
                                    break;
                                case 2:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_BONG_TAI_CAP3);
                                    break;
                                case 3:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.MO_CHI_SO_BONG_TAI_CAP3);
                                    break;
                                case 4:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_BONG_TAI_CAP4);
                                    break;
                                case 5:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.MO_CHI_SO_BONG_TAI_CAP4);
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                            switch (player.combineNew.typeCombine) {
                                case CombineServiceNew.NANG_CAP_VAT_PHAM:
                                case CombineServiceNew.NANG_CAP_BONG_TAI:
                                case CombineServiceNew.MO_CHI_SO_BONG_TAI:
                                case CombineServiceNew.NANG_CAP_BONG_TAI_CAP3:
                                case CombineServiceNew.MO_CHI_SO_BONG_TAI_CAP3:
                                case CombineServiceNew.NANG_CAP_BONG_TAI_CAP4:
                                case CombineServiceNew.MO_CHI_SO_BONG_TAI_CAP4:
                                case CombineServiceNew.NHAP_NGOC_RONG:
                                case CombineServiceNew.CHE_TAO_TRANG_BI_TS:
                                case CombineServiceNew.NANG_CAP_DO_THAN:
                                    if (select == 0) {
                                        CombineServiceNew.gI().startCombine(player);
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc nangdothan(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Ngươi tìm ta có việc gì?",
                                "Nâng cấp\nđồ thần");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_DO_THAN);
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                            switch (player.combineNew.typeCombine) {
                                case CombineServiceNew.NANG_CAP_DO_THAN:
                                    if (select == 0) {
                                        CombineServiceNew.gI().startCombine(player);
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc Whis(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (this.mapId == 154) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Thử đánh với ta xem nào.\nNgươi còn 1 lượt cơ mà.",
                            "Nói chuyện", "Học tuyệt kỹ", "Từ chối");
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu() && this.mapId == 154) {
                        switch (select) {
                            case 0:
                                this.createOtherMenu(player, 5, "Ta sẽ giúp ngươi chế tạo trang bị thiên sứ", "Chế tạo", "Shop Thiên Sứ", "Từ chối");
                                break;
                            case 1:
                                Item BiKiepTuyetKy = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1433);
                                if (BiKiepTuyetKy != null) {
                                    if (player.gender == 0) {
                                        this.createOtherMenu(player, 6, "|1|Ta sẽ dạy ngươi tuyệt kỹ Super kamejoko\n" + "|7|Bí kiếp tuyệt kỹ: " + BiKiepTuyetKy.quantity + "/9999\n" + "|2|Giá vàng: 10.000.000\n" + "|2|Giá ngọc: 99",
                                                "Đồng ý", "Từ chối");
                                    }
                                    if (player.gender == 1) {
                                        this.createOtherMenu(player, 6, "|1|Ta sẽ dạy ngươi tuyệt kỹ Ma phông ba\n" + "|7|Bí kiếp tuyệt kỹ: " + BiKiepTuyetKy.quantity + "/9999\n" + "|2|Giá vàng: 10.000.000\n" + "|2|Giá ngọc: 99",
                                                "Đồng ý", "Từ chối");
                                    }
                                    if (player.gender == 2) {
                                        this.createOtherMenu(player, 6, "|1|Ta sẽ dạy ngươi tuyệt kỹ "
                                                + "đíc chưởng liên hoàn\n" + "|7|Bí kiếp tuyệt kỹ: " + BiKiepTuyetKy.quantity + "/9999\n" + "|2|Giá vàng: 10.000.000\n" + "|2|Giá ngọc: 99",
                                                "Đồng ý", "Từ chối");
                                    }
                                } else {
                                    this.npcChat(player, "Hãy tìm bí kíp rồi quay lại gặp ta!");
                                }
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == 5) {
                        switch (select) {
                            case 0:
                                CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.CHE_TAO_TRANG_BI_TS);
                                break;
                            case 1:
                                ShopServiceNew.gI().opendShop(player, "THIEN_SU", false);
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                        switch (player.combineNew.typeCombine) {
                            case CombineServiceNew.CHE_TAO_TRANG_BI_TS:
                                if (select == 0) {
                                    CombineServiceNew.gI().startCombine(player);
                                }
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_DAP_DO) {
                        if (select == 0) {
                            CombineServiceNew.gI().startCombine(player);
                        }
                    } else if (player.iDMark.getIndexMenu() == 6) {
                        switch (select) {
                            case 0:
                                Item sach = InventoryServiceNew.gI().findItemBag(player, 1433);
                                if (sach != null && sach.quantity >= 9999 && player.inventory.gold >= 10000000 && player.inventory.gem > 99 && player.nPoint.power >= 1000000000L) {

                                    if (player.gender == 2) {
                                        SkillService.gI().learSkillSpecial(player, Skill.SUPER_ANTOMIC);
                                    }
                                    if (player.gender == 0) {
                                        SkillService.gI().learSkillSpecial(player, Skill.SUPER_KAME);
                                    }
                                    if (player.gender == 1) {
                                        SkillService.gI().learSkillSpecial(player, Skill.MAFUBA);
                                    }
                                    InventoryServiceNew.gI().subQuantityItem(player.inventory.itemsBag, sach, 9999);
                                    player.inventory.gold -= 10000000;
                                    player.inventory.gem -= 99;
                                    InventoryServiceNew.gI().sendItemBags(player);
                                } else if (player.nPoint.power < 1000000000L) {
                                    Service.getInstance().sendThongBao(player, "Ngươi không đủ sức mạnh để học tuyệt kỹ");
                                    return;
                                } else if (sach.quantity <= 9999) {
                                    int sosach = 9999 - sach.quantity;
                                    Service.getInstance().sendThongBao(player, "Ngươi còn thiếu " + sosach + " bí kíp nữa.\nHãy tìm đủ rồi đến gặp ta.");
                                    return;
                                } else if (player.inventory.gold <= 10000000) {
                                    Service.getInstance().sendThongBao(player, "Hãy có đủ vàng thì quay lại gặp ta.");
                                    return;
                                } else if (player.inventory.gem <= 99) {
                                    Service.getInstance().sendThongBao(player, "Hãy có đủ ngọc xanh thì quay lại gặp ta.");
                                    return;
                                }

                                break;
                        }
                    }
                }
            }

        };
    }

    public static Npc ruongDo(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    InventoryServiceNew.gI().sendItemBox(player);
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {

                }
            }
        };
    }

    public static Npc duongtannk(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (mapId == 0) {
                        nguhs.gI().setTimeJoinnguhs();
                        long now = System.currentTimeMillis();
                        if (now > nguhs.TIME_OPEN_NHS && now < nguhs.TIME_CLOSE_NHS) {
                            this.createOtherMenu(player, 0, "Ngũ Hàng Sơn x10 Tnsm\nHỗ trợ cho Ae trên 80 Tỷ SM?\nThời gian từ 13h - 18h, 250 hồng ngọc 1 lần vào", "Éo", "OK");
                        } else {
                            this.createOtherMenu(player, 0, "Ngũ Hàng Sơn x10 Tnsm\nHỗ trợ cho Ae trên 80 Tỷ SM?\nThời gian từ 13h - 18h, 250 hồng ngọc 1 lần vào", "Éo");
                        }
                    }
                    if (mapId == 122) {
                        this.createOtherMenu(player, 0, "Bạn Muốn Quay Trở Lại Làng Ảru?", "OK", "Từ chối");

                    }
                    if (mapId == 124) {
                        this.createOtherMenu(player, 0, "Xia xia thua phùa\b|7|Thí chủ đang có: " + player.NguHanhSonPoint + " điểm ngũ hành sơn\b|1|Thí chủ muốn đổi cải trang x4 chưởng ko?", "Âu kê", "Top Ngu Hanh Son", "No");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {

                    if (mapId == 0) {
                        switch (select) {
                            case 0:
                                break;
                            case 1:
                                if (player.nPoint.power < 80000000000L) {
                                    Service.getInstance().sendThongBao(player, "Sức mạnh bạn Đéo đủ để qua map!");
                                    return;
                                } else if (player.inventory.ruby < 500) {
                                    Service.getInstance().sendThongBao(player, "Phí vào là 500 hồng ngọc một lần bạn ey!\nBạn đéo đủ!");
                                    return;
                                } else {
                                    player.inventory.ruby -= 500;
                                    PlayerService.gI().sendInfoHpMpMoney(player);
                                    ChangeMapService.gI().changeMapInYard(player, 122, -1, -1);
                                }
                                break;
                        }
                    }
                    if (mapId == 122) {
                        if (select == 0) {
                            ChangeMapService.gI().changeMapInYard(player, 0, -1, 469);
                        }
                    }
                    if (mapId == 124) {
                        if (select == 0) {
                            if (player.NguHanhSonPoint >= 500) {
                                player.NguHanhSonPoint -= 500;
                                Item item = ItemService.gI().createNewItem((short) (711));
                                item.itemOptions.add(new Item.ItemOption(49, 25));
                                item.itemOptions.add(new Item.ItemOption(77, 25));
                                item.itemOptions.add(new Item.ItemOption(103, 25));
                                item.itemOptions.add(new Item.ItemOption(207, 0));
                                item.itemOptions.add(new Item.ItemOption(33, 0));
//                                      
                                InventoryServiceNew.gI().addItemBag(player, item);
                                Service.gI().sendThongBao(player, "Chúc Mừng Bạn Đổi Vật Phẩm Thành Công !");
                            } else {
                                Service.gI().sendThongBao(player, "Không đủ điểm, bạn còn " + (500 - player.pointPvp) + " điểm nữa");
                            }

                        }
                    }

                }
            }
        };
    }

    public static Npc dauThan(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    player.magicTree.openMenuTree();
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.MAGIC_TREE_NON_UPGRADE_LEFT_PEA:
                            if (select == 0) {
                                player.magicTree.harvestPea();
                            } else if (select == 1) {
                                if (player.magicTree.level == 10) {
                                    player.magicTree.fastRespawnPea();
                                } else {
                                    player.magicTree.showConfirmUpgradeMagicTree();
                                }
                            } else if (select == 2) {
                                player.magicTree.fastRespawnPea();
                            }
                            break;
                        case ConstNpc.MAGIC_TREE_NON_UPGRADE_FULL_PEA:
                            if (select == 0) {
                                player.magicTree.harvestPea();
                            } else if (select == 1) {
                                player.magicTree.showConfirmUpgradeMagicTree();
                            }
                            break;
                        case ConstNpc.MAGIC_TREE_CONFIRM_UPGRADE:
                            if (select == 0) {
                                player.magicTree.upgradeMagicTree();
                            }
                            break;
                        case ConstNpc.MAGIC_TREE_UPGRADE:
                            if (select == 0) {
                                player.magicTree.fastUpgradeMagicTree();
                            } else if (select == 1) {
                                player.magicTree.showConfirmUnuppgradeMagicTree();
                            }
                            break;
                        case ConstNpc.MAGIC_TREE_CONFIRM_UNUPGRADE:
                            if (select == 0) {
                                player.magicTree.unupgradeMagicTree();
                            }
                            break;
                    }
                }
            }
        };
    }

    public static Npc calick(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            private final byte COUNT_CHANGE = 50;
            private int count;

            private void changeMap() {
                if (this.mapId != 102) {
                    count++;
                    if (this.count >= COUNT_CHANGE) {
                        count = 0;
                        this.map.npcs.remove(this);
                        Map map = MapService.gI().getMapForCalich();
                        this.mapId = map.mapId;
                        this.cx = Util.nextInt(100, map.mapWidth - 100);
                        this.cy = map.yPhysicInTop(this.cx, 0);
                        this.map = map;
                        this.map.npcs.add(this);
                    }
                }
            }

            @Override
            public void openBaseMenu(Player player) {
                if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                    player.iDMark.setIndexMenu(ConstNpc.BASE_MENU);
                    if (TaskService.gI().getIdTask(player) < ConstTask.TASK_20_0) {
                        Service.gI().hideWaitDialog(player);
                        Service.gI().sendThongBao(player, "Không thể thực hiện");
                        return;
                    }
                    if (this.mapId != player.zone.map.mapId) {
                        Service.gI().sendThongBao(player, "Calích đã rời khỏi map!");
                        Service.gI().hideWaitDialog(player);
                        return;
                    }

                    if (this.mapId == 102) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Chào chú, cháu có thể giúp gì?",
                                "Kể\nChuyện", "Quay về\nQuá khứ");
                    } else {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Chào chú, cháu có thể giúp gì?", "Kể\nChuyện", "Đi đến\nTương lai", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (this.mapId == 102) {
                    if (player.iDMark.isBaseMenu()) {
                        if (select == 0) {
                            //kể chuyện
                            NpcService.gI().createTutorial(player, this.avartar, ConstNpc.CALICK_KE_CHUYEN);
                        } else if (select == 1) {
                            //về quá khứ
                            ChangeMapService.gI().goToQuaKhu(player);
                        }
                    }
                } else if (player.iDMark.isBaseMenu()) {
                    if (select == 0) {
                        //kể chuyện
                        NpcService.gI().createTutorial(player, this.avartar, ConstNpc.CALICK_KE_CHUYEN);
                    } else if (select == 1) {
                        //đến tương lai
//                                    changeMap();
                        if (TaskService.gI().getIdTask(player) >= ConstTask.TASK_20_0) {
                            ChangeMapService.gI().goToTuongLai(player);
                        }
                    } else {
                        Service.gI().sendThongBao(player, "Không thể thực hiện");
                    }
                }
            }
        };
    }

    public static Npc jaco(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 24 || this.mapId == 25 || this.mapId == 26) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Gô Tên, Calich và Monaka đang gặp chuyện ở hành tinh Potaufeu \n Hãy đến đó ngay", "Đến \nPotaufeu");
                    } else if (this.mapId == 139) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Người muốn trở về?", "Quay về", "Từ chối");
                    } else if (this.mapId == 172) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Người muốn trở về?", "Quay về", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 24 || this.mapId == 25 || this.mapId == 26) {
                        if (player.getSession().player.nPoint.power >= 800000000L) {

                            ChangeMapService.gI().goToPotaufeu(player);
                        } else {
                            this.npcChat(player, "Bạn chưa đủ 800tr sức mạnh để vào!");
                        }
                    } else if (this.mapId == 139) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                //về trạm vũ trụ
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 24 + player.gender, -1, -1);
                                    break;
                            }
                        }
                    } else if (this.mapId == 172) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                //về trạm vũ trụ
                                case 0:
                                    ChangeMapService.gI().changeMap(player, 5, -1, 1170, 408);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc npclytieunuong54(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                createOtherMenu(player, 0, "Trò chơi Chọn ai đây đang được diễn ra, nếu bạn tin tưởng mình đang tràn đầy may mắn thì có thể tham gia thử", "Thể lệ", "Chọn\nThỏi vàng");
            }

            @Override
            public void confirmMenu(Player pl, int select) {
                if (canOpenNpc(pl)) {
                    String time = ((ChonAiDay.gI().lastTimeEnd - System.currentTimeMillis()) / 1000) + " giây";
                    if (((ChonAiDay.gI().lastTimeEnd - System.currentTimeMillis()) / 1000) < 0) {
                        ChonAiDay.gI().lastTimeEnd = System.currentTimeMillis() + 300000;
                    }
                    if (pl.iDMark.getIndexMenu() == 0) {
                        if (select == 0) {
                            createOtherMenu(pl, ConstNpc.IGNORE_MENU, "Thời gian giữa các giải là 5 phút\nKhi hết giờ, hệ thống sẽ ngẫu nhiên chọn ra 1 người may mắn.\nLưu ý: Số thỏi vàng nhận được sẽ bị nhà cái lụm đi 5%!Trong quá trình diễn ra khi đặt cược nếu thoát game mọi phần đặt đều sẽ bị hủy", "Ok");
                        } else if (select == 1) {
                            createOtherMenu(pl, 1, "Tổng giải thường: " + ChonAiDay.gI().goldNormar + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(0) + "%\nTổng giải VIP: " + ChonAiDay.gI().goldVip + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(1) + "%\nSố thỏi vàng đặt thường: " + pl.goldNormar + "\nSố thỏi vàng đặt VIP: " + pl.goldVIP + "\n Thời gian còn lại: " + time, "Cập nhập", "Thường\n20 thỏi\nvàng", "VIP\n200 thỏi\nvàng", "Đóng");
                        }
                    } else if (pl.iDMark.getIndexMenu() == 1) {
                        if (((ChonAiDay.gI().lastTimeEnd - System.currentTimeMillis()) / 1000) > 0) {
                            switch (select) {
                                case 0:
                                    createOtherMenu(pl, 1, "Tổng giải thường: " + ChonAiDay.gI().goldNormar + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(0) + "%\nTổng giải VIP: " + ChonAiDay.gI().goldVip + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(1) + "%\nSố thỏi vàng đặt thường: " + pl.goldNormar + "\nSố thỏi vàng đặt VIP: " + pl.goldVIP + "\n Thời gian còn lại: " + time, "Cập nhập", "Thường\n20 thỏi\nvàng", "VIP\n200 thỏi\nvàng", "Đóng");
                                    break;
                                case 1: {
                                    try {
                                        if (InventoryServiceNew.gI().findItemBag(pl, 457).isNotNullItem() && InventoryServiceNew.gI().findItemBag(pl, 457).quantity >= 20) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(pl, InventoryServiceNew.gI().findItemBag(pl, 457), 20);
                                            InventoryServiceNew.gI().sendItemBags(pl);
                                            pl.goldNormar += 20;
                                            ChonAiDay.gI().goldNormar += 20;
                                            ChonAiDay.gI().addPlayerNormar(pl);
                                            createOtherMenu(pl, 1, "Tổng giải thường: " + ChonAiDay.gI().goldNormar + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(0) + "%\nTổng giải VIP: " + ChonAiDay.gI().goldVip + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(1) + "%\nSố thỏi vàng đặt thường: " + pl.goldNormar + "\nSố thỏi vàng đặt VIP: " + pl.goldVIP + "\n Thời gian còn lại: " + time, "Cập nhập", "Thường\n20 thỏi\nvàng", "VIP\n200 thỏi\nvàng", "Đóng");
                                        } else {
                                            Service.gI().sendThongBao(pl, "Bạn không đủ thỏi vàng");

                                        }
                                    } catch (Exception ex) {
                                        java.util.logging.Logger.getLogger(NpcFactory.class
                                                .getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;

                                case 2: {
                                    try {
                                        if (InventoryServiceNew.gI().findItemBag(pl, 457).isNotNullItem() && InventoryServiceNew.gI().findItemBag(pl, 457).quantity >= 200) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(pl, InventoryServiceNew.gI().findItemBag(pl, 457), 200);
                                            InventoryServiceNew.gI().sendItemBags(pl);
                                            pl.goldVIP += 200;
                                            ChonAiDay.gI().goldVip += 200;
                                            ChonAiDay.gI().addPlayerVIP(pl);
                                            createOtherMenu(pl, 1, "Tổng giải thường: " + ChonAiDay.gI().goldNormar + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(0) + "%\nTổng giải VIP: " + ChonAiDay.gI().goldVip + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(1) + "%\nSố thỏi vàng đặt thường: " + pl.goldNormar + "\nSố thỏi vàng đặt VIP: " + pl.goldVIP + "\n Thời gian còn lại: " + time, "Cập nhập", "Thường\n20 thỏi\nvàng", "VIP\n200 thỏi\nvàng", "Đóng");
                                        } else {
                                            Service.gI().sendThongBao(pl, "Bạn không đủ thỏi vàng");
                                        }
                                    } catch (Exception ex) {
//                                            java.util.logging.Logger.getLogger(NpcFactory.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;

                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc thuongDe(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (!canOpenNpc(player)) {
                    return;
                }

                String message;
                if (this.mapId == 141) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Hãy nắm lấy tay ta mau!", "Về\nthần điện");
                } else {
                    if (player.typetrain == 1 && !player.istrain) {
                        message = "Pôpô là đệ tử của ta, luyện tập với Pôpô con sẽ có nhiều kinh nghiệm đánh bại được Pôpô ta sẽ dạy võ công cho con";
                    } else if (player.typetrain == 2 && player.istrain) {
                        message = "Từ nay con sẽ là đệ tử của ta. Ta sẽ truyền cho con tất cả tuyệt kĩ";
                    } else if (player.typetrain == 1 && player.istrain) {
                        message = "Pôpô là đệ tử của ta, luyện tập với Pôpô con sẽ có nhiều kinh nghiệm đánh bại được Pôpô ta sẽ dạy võ công cho con";
                    } else if (player.typetrain == 2 && !player.istrain) {
                        message = "Từ nay con sẽ là đệ tử của ta. Ta sẽ truyền cho con tất cả tuyệt kĩ";
                    } else {
                        message = "Con đã mạnh hơn ta, ta sẽ chỉ đường cho con đến Kaio để gặp thần Vũ Trụ Phương Bắc\nNgài là thần cai quản vũ trụ này, hãy theo ngài ấy học võ công";
                    }

                    if (player.typetrain == 1 && !player.istrain) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Đăng ký tập tự động", "Tập luyện với Mr.PôPô", "Thách đấu Mr.PôPô", "Đến Kaio", "Quay số\nmay mắn");
                    } else if (player.typetrain == 2 && player.istrain) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Hủy đăng ký tập tự động", "Tập luyện với Mr.PôPô", "Thách đấu\nvới thượng đế", "Đến Kaio", "Quay số\nmay mắn");
                    } else if (player.typetrain == 1 && player.istrain) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Hủy đăng ký tập tự động", "Tập luyện với Mr.PôPô", "Thách đấu Mr.PôPô", "Đến Kaio", "Quay số\nmay mắn");
                    } else if (player.typetrain == 2 && !player.istrain) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Đăng ký tập tự động", "Tập luyện\nvới Mr.PôPô", "Thách đấu\nvới thượng đế", "Đến Kaio", "Quay số\nmay mắn");
                    } else {
                        if (!player.istrain) {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Đăng ký tập tự động", "Tập luyện với Mr.PôPô", "Tập luyện với thượng đế", "Đến Kaio", "Quay số\nmay mắn");
                        } else {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Hủy đăng ký tập tự động", "Tập luyện với Mr.PôPô", "Tập luyện với thượng đế", "Đến Kaio", "Quay số\nmay mắn");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player) && this.mapId == 45) {
                    if (player.iDMark.isBaseMenu()) {
                        if (select == 0) {
                            if (!player.istrain) {
                                this.createOtherMenu(player, ConstNpc.MENU_TRAIN_OFFLINE, "Đăng ký để mỗi khi Offline quá 30 phút, con sẽ được tự động luyện tập với tốc độ " + player.nPoint.getexp() + " sức mạnh mỗi phút", "Hướng dẫn thêm", "Đồng ý 1 ngọc mỗi lần", "Không đồng ý");
                            } else {
                                player.istrain = false;
                                this.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Con đã hủy thành công đăng ký tập tự động", "Đóng");
                            }
                        } else if (select == 1) {
                            this.createOtherMenu(player, ConstNpc.MENU_TRAIN_OFFLINE_TRY0, "Con có chắc muốn tập luyện?\nTập luyện với " + player.nPoint.getNameNPC(player, this, (byte) select) + " sẽ tăng " + player.nPoint.getExpbyNPC(player, this, (byte) select) + " sức mạnh mỗi phút", "Đồng ý luyện tập", "Không đồng ý");
                        } else if (select == 2) {
                            if (player.typetrain > 2) {
                                this.createOtherMenu(player, ConstNpc.MENU_TRAIN_OFFLINE_TRY1, "Con có chắc muốn tập luyện?\nTập luyện với " + player.nPoint.getNameNPC(player, this, (byte) select) + " sẽ tăng " + player.nPoint.getExpbyNPC(player, this, (byte) select) + " sức mạnh mỗi phút", "Đồng ý luyện tập", "Không đồng ý");
                            } else if (player.typetrain == 1) {
                                player.setfight((byte) 1, (byte) 0);
                                player.zone.load_Me_To_Another(player);
                                player.zone.load_Another_To_Me(player);

                            } else {

                                ChangeMapService.gI().changeMap(player, 49, 0, 578, 456);
                                player.setfight((byte) 1, (byte) 1);
                                try {
                                    new Thuongde(BossID.THUONG_DE, BossesData.THUONG_DE, player.zone, player.location.x - 10, player.location.y);
                                    player.zone.load_Another_To_Me(player);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else if (select == 3) {
                            ChangeMapService.gI().changeMapBySpaceShip(player, 48, -1, 354);
                        } else if (select == 4) {
                            this.createOtherMenu(player, ConstNpc.MENU_CHOOSE_LUCKY_ROUND,
                                    "Con muốn làm gì nào?", "Quay bằng\nvàng", "Quay bằng\nngọc",
                                    "Rương phụ\n("
                                    + (player.inventory.itemsBoxCrackBall.size()
                                    - InventoryServiceNew.gI().getCountEmptyListItem(player.inventory.itemsBoxCrackBall))
                                    + " món)",
                                    "Xóa hết\ntrong rương", "Đóng");

                        }

                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_TRAIN_OFFLINE) {
                        switch (select) {
                            case 0:
                                Service.gI().sendPopUpMultiLine(player, tempId, this.avartar, ConstNpc.INFOR_TRAIN_OFFLINE);
                                break;
                            case 1:
                                player.istrain = true;
                                NpcService.gI().createTutorial(player, this.avartar, "Từ giờ, quá 30 phút Offline con sẽ tự động luyện tập");
                                break;
                            case 3:
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_TRAIN_OFFLINE_TRY0) {
                        switch (select) {
                            case 0:
                                player.setfight((byte) 0, (byte) 0);
                                player.zone.load_Me_To_Another(player);
                                player.zone.load_Another_To_Me(player);

                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_TRAIN_OFFLINE_TRY1) {
                        switch (select) {
                            case 0:
                                player.setfight((byte) 1, (byte) 1);
                                ChangeMapService.gI().changeMap(player, 49, 0, 578, 456);
                                try {
                                    new Thuongde(BossID.THUONG_DE, BossesData.THUONG_DE, player.zone, player.location.x - 10, player.location.y);
                                    player.zone.load_Another_To_Me(player);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_CHOOSE_LUCKY_ROUND) {
                        switch (select) {
                            case 0:
                                LuckyRound.gI().openCrackBallUI(player, LuckyRound.USING_GOLD);
                                break;
                            case 1:
                                LuckyRound.gI().openCrackBallUI(player, LuckyRound.USING_GEM);
                                break;
                            case 2:
                                ShopServiceNew.gI().opendShop(player, "ITEMS_LUCKY_ROUND", true);
                                break;
                            case 3:
                                NpcService.gI().createMenuConMeo(player,
                                        ConstNpc.CONFIRM_REMOVE_ALL_ITEM_LUCKY_ROUND, this.avartar,
                                        "Con có chắc muốn xóa hết vật phẩm trong rương phụ? Sau khi xóa "
                                        + "sẽ không thể khôi phục!",
                                        "Đồng ý", "Hủy bỏ");
                                break;
                        }
                    }
                }
                if (this.mapId == 141) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0:
                                ChangeMapService.gI().changeMapInYard(player, 45, 0, 408);
                                Service.gI().sendThongBao(player, "Hãy xuống dưới gặp thần\nmèo Karin");
                                player.clan.gobosscdrd = true;
                                break;
                        }
                    }
                }
            }
        };
    }

    public static Npc thanVuTru(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    String message;
                    if (this.mapId == 48) {
                        if (player.typetrain == 3 && !player.istrain) {
                            message = "Thượng đế đưa người đến đây, chắc muốn ta dạy võ chứ gì\nBắt được con khỉ Bubbles rồi hãy tính";
                        } else if (player.typetrain == 4 && player.istrain) {
                            message = "Ta là Thần Vũ Trụ Phương Bắc cai quản khu vực Bắc Vũ Trụ\nnếu thắng được ta\nngươi sẽ đến lãnh địa Kaio, nơi ở của thần linh ";
                        } else if (player.typetrain == 3 && player.istrain) {
                            message = "Thượng đế đưa người đến đây, chắc muốn ta dạy võ chứ gì\nBắt được con khỉ Bubbles rồi hãy tính";
                        } else if (player.typetrain == 4 && !player.istrain) {
                            message = "Ta là Thần Vũ Trụ Phương Bắc cai quản khu vực Bắc Vũ Trụ\nnếu thắng được ta\nngươi sẽ đến lãnh địa Kaio, nơi ở của thần linh ";
                        } else {
                            message = "Con mạnh nhất phía bắc vũ trụ này rồi đấy nhưng ngoài vũ trụ bao la kia vẫn có những kẻ mạnh hơn nhiều\ncon cần phải tập luyện để mạnh hơn nữa";
                        }

                        if (player.typetrain == 3 && !player.istrain) {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Đăng ký tập tự động", "Tập luyện với Khỉ Bubbles", "Thách đấu Khỉ Bubbles", "Di chuyển");
                        } else if (player.typetrain == 4 && player.istrain) {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Hủy đăng ký tập tự động", "Tập luyện với Khỉ Bubbles", "Thách đấu\nvới Thần\nVũ Trụ", "Di chuyển");
                        } else if (player.typetrain == 3 && player.istrain) {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Hủy đăng ký tập tự động", "Tập luyện với Khỉ Bubbles", "Thách đấu Khỉ Bubbles", "Di chuyển");
                        } else if (player.typetrain == 4 && !player.istrain) {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Đăng ký tập tự động", "Tập luyện\nvới Khỉ Bubbles", "Thách đấu\nvới Thần\nVũ Trụ", "Di chuyển");
                        } else {
                            if (!player.istrain) {
                                this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Đăng ký tập tự động", "Tập luyện với Khỉ Bubbles", "Tập luyện \nvới Thần\nVũ Trụ", "Di chuyển");
                            } else {
                                this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Hủy đăng ký tập tự động", "Tập luyện với Khỉ Bubbles", "Tập luyện\nvới Thần\nVũ Trụ", "Di chuyển");
                            }
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 48) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                if (!player.istrain) {
                                    this.createOtherMenu(player, ConstNpc.MENU_TRAIN_OFFLINE, "Đăng ký để mỗi khi Offline quá 30 phút, con sẽ được tự động luyện tập với tốc độ " + player.nPoint.getexp() + " sức mạnh mỗi phút", "Hướng dẫn thêm", "Đồng ý 1 ngọc mỗi lần", "Không đồng ý");
                                } else {
                                    player.istrain = false;
                                    this.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Con đã hủy thành công đăng ký tập tự động", "Đóng");
                                }
                            } else if (select == 1) {
                                this.createOtherMenu(player, ConstNpc.MENU_TRAIN_OFFLINE_TRY0, "Con có chắc muốn tập luyện?\nTập luyện với " + player.nPoint.getNameNPC(player, this, (byte) select) + " sẽ tăng " + player.nPoint.getExpbyNPC(player, this, (byte) select) + " sức mạnh mỗi phút", "Đồng ý luyện tập", "Không đồng ý");
                            } else if (select == 2) {
                                if (player.typetrain > 4) {
                                    this.createOtherMenu(player, ConstNpc.MENU_TRAIN_OFFLINE_TRY1, "Con có chắc muốn tập luyện?\nTập luyện với " + player.nPoint.getNameNPC(player, this, (byte) select) + " sẽ tăng " + player.nPoint.getExpbyNPC(player, this, (byte) select) + " sức mạnh mỗi phút", "Đồng ý luyện tập", "Không đồng ý");
                                } else if (player.typetrain == 3) {
                                    player.setfight((byte) 1, (byte) 0);
                                    player.zone.load_Me_To_Another(player);
                                    player.zone.load_Another_To_Me(player);

                                } else {
                                    player.setfight((byte) 1, (byte) 1);
                                    player.zone.mapInfo(player);
                                    DataGame.updateMap(player.getSession());
                                    try {
                                        new ThanVuTru(BossID.THAN_VUTRU, BossesData.THAN_VU_TRU, player.zone, this.cx, this.cy);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else if (select == 3) {
                                this.createOtherMenu(player, ConstNpc.MENU_DI_CHUYEN,
                                        "Con muốn đi đâu?", "Về\nthần điện", "Thánh địa\nKaio", "Con\nđường\nrắn độc", "Từ chối");

                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_TRAIN_OFFLINE) {
                            switch (select) {
                                case 0:
                                    Service.gI().sendPopUpMultiLine(player, tempId, this.avartar, ConstNpc.INFOR_TRAIN_OFFLINE);
                                    break;
                                case 1:
                                    player.istrain = true;
                                    NpcService.gI().createTutorial(player, this.avartar, "Từ giờ, quá 30 phút Offline con sẽ tự động luyện tập");
                                    break;
                                case 3:
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_TRAIN_OFFLINE_TRY0) {
                            switch (select) {
                                case 0:
                                    player.setfight((byte) 0, (byte) 0);
                                    player.zone.load_Me_To_Another(player);
                                    player.zone.load_Another_To_Me(player);

                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_TRAIN_OFFLINE_TRY1) {
                            switch (select) {
                                case 0:
                                    player.setfight((byte) 1, (byte) 1);
                                    player.zone.mapInfo(player);
                                    DataGame.updateMap(player.getSession());
                                    try {
                                        new ThanVuTru(BossID.THAN_VUTRU, BossesData.THAN_VU_TRU, player.zone, this.cx, this.cy);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_DI_CHUYEN) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 45, -1, 354);
                                    break;
                                case 1:
                                    ChangeMapService.gI().changeMap(player, 50, -1, 318, 336);
                                    break;
                                case 2:
                                    if (player.clan != null) {
                                        if (player.clan.ConDuongRanDoc != null) {
                                            this.createOtherMenu(player, ConstNpc.MENU_OPENED_CDRD,
                                                    "Bang hội của con đang đi con đường rắn độc cấp độ "
                                                    + player.clan.ConDuongRanDoc.level + "\nCon có muốn đi theo không?",
                                                    "Đồng ý", "Từ chối");
                                        } else {
                                            this.createOtherMenu(player, ConstNpc.MENU_OPEN_CDRD,
                                                    "Đây là Con đường rắn độc \nCác con cứ yên tâm lên đường\n"
                                                    + "Ở đây có ta lo\nNhớ chọn cấp độ vừa sức mình nhé",
                                                    "Chọn\ncấp độ", "Từ chối");
                                        }
                                     } else {
                                        this.npcChat(player, "Con phải có bang hội ta mới có thể cho con đi");
                                    }
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPENED_CDRD) {
                            switch (select) {
                                case 0:
                                    if (player.isAdmin() || player.nPoint.power >= ConDuongRanDoc.POWER_CAN_GO_TO_CDRD) {
                                        ChangeMapService.gI().goToCDRD(player);
                                    } else {
                                        Service.gI().sendThongBao(player, "Không đủ sức mạnh yêu cầu");
                                    }
                                    if (player.clan.haveGoneConDuongRanDoc) {
                                        createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                                "Bang hội của ngươi đã đi con đường rắn độc lúc " + TimeUtil.formatTime(player.clan.lastTimeOpenConDuongRanDoc, "HH:mm:ss") + " hôm nay. Người mở\n"
                                                + "(" + player.clan.playerOpenDoanhTrai + "). Hẹn ngươi quay lại vào ngày mai", "OK", "Hướng\ndẫn\nthêm");
                                        return;
                                    } else if (player.clanMember.getNumDateFromJoinTimeToToday() < 0) {
                                        Service.gI().sendThongBao(player, "Yêu cầu tham gia bang hội trên 2 ngày!");
                                    } else {
                                        this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                                + Util.numberToMoney(ConDuongRanDoc.POWER_CAN_GO_TO_CDRD));
                                    }
                                    break;

                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPEN_CDRD) {
                            switch (select) {
                                case 0:
                                    if (player.isAdmin() || player.nPoint.power >= ConDuongRanDoc.POWER_CAN_GO_TO_CDRD) {
                                        Input.gI().createFormChooseLevelCDRD(player);
                                    } else {
                                        this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                                + Util.numberToMoney(ConDuongRanDoc.POWER_CAN_GO_TO_CDRD));
                                    }
                                    break;
                            }

                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_ACCEPT_GO_TO_CDRD) {
                            switch (select) {
                                case 0:
                                    ConDuongRanDocService.gI().openConDuongRanDoc(player, Byte.parseByte(String.valueOf(PLAYERID_OBJECT.get(player.id))));
                                    break;
                            }
                        }
                    }
                }
            }

        };
    }

    public static Npc TosuKaio(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    String message;
                    if (this.mapId == 50) {
                        if (player.typetrain >= 5) {
                            message = "Tập luyện với Tổ sư Kaio sẽ tăng " + player.nPoint.getexp() + " sức mạnh mỗi phút, con có muốn đăng ký không?";
                            if (!player.istrain) {
                                this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Đăng ký tập tự động", "Đồng ý\nluyện tập", "Không đồng ý");
                            } else {
                                this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Hủy đăng ký tập tự động", "Đồng ý\nluyện tập", "Không đồng ý");
                            }
                        } else if (player.typetrain < 5) {
                            message = "Hãy đánh bại các cao thủ rồi quay lại đây";
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Vâng ạ");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 50 && player.typetrain == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                if (!player.istrain) {
                                    this.createOtherMenu(player, ConstNpc.MENU_TRAIN_OFFLINE, "Đăng ký để mỗi khi Offline quá 30 phút, con sẽ được tự động luyện tập với tốc độ " + player.nPoint.getexp() + " sức mạnh mỗi phút", "Hướng dẫn thêm", "Đồng ý 1 ngọc mỗi lần", "Không đồng ý");
                                } else {
                                    player.istrain = false;
                                    this.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Con đã hủy thành công đăng ký tập tự động", "Đóng");
                                }
                            } else if (select == 1) {
                                this.createOtherMenu(player, ConstNpc.MENU_TRAIN_OFFLINE_TRY0, "Con có chắc muốn tập luyện?\nTập luyện với " + player.nPoint.getNameNPC(player, this, (byte) select) + " sẽ tăng " + player.nPoint.getExpbyNPC(player, this, (byte) select) + " sức mạnh mỗi phút", "Đồng ý luyện tập", "Không đồng ý");
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_TRAIN_OFFLINE) {
                            switch (select) {
                                case 0:
                                    Service.gI().sendPopUpMultiLine(player, tempId, this.avartar, ConstNpc.INFOR_TRAIN_OFFLINE);
                                    break;
                                case 1:
                                    player.istrain = true;
                                    NpcService.gI().createTutorial(player, this.avartar, "Từ giờ, quá 30 phút Offline con sẽ tự động luyện tập");
                                    break;
                                case 3:
                                    break;
                            }

                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_TRAIN_OFFLINE_TRY0) {
                            switch (select) {
                                case 0:
                                    player.setfight((byte) 1, (byte) 1);
                                    player.zone.mapInfo(player);
                                    DataGame.updateMap(player.getSession());
                                    try {
                                        new ToSuKaio(BossID.TS_KAIO, BossesData.TO_SU_KAIO, player.zone, this.cx, this.cy);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                            }
                        }
                    }
                }
            }

        };
    }

    public static Npc kibit(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 50) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                "Đến\nKaio", "Từ chối");
                    }
                    if (this.mapId == 114) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 50) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMap(player, 48, -1, 354, 240);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc docNhan(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (player.clan == null) {
                        this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Chỉ tiếp các bang hội, miễn tiếp khách vãng lai", "Đóng");
                        return;
                    }
                    if (player.clan.doanhTrai_haveGone) {
                        createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Ta đã thả ngọc rồng ở tất cả các map,mau đi nhặt đi. Hẹn ngươi quay lại vào ngày mai",
                                "OK");
                        return;
                    }

                    boolean flag = true;
                    for (Mob mob : player.zone.mobs) {
                        if (!mob.isDie()) {
                            flag = false;
                        }
                    }
                    for (Player boss : player.zone.getBosses()) {
                        if (!boss.isDie()) {
                            flag = false;
                        }
                    }

                    if (flag) {
                        player.clan.doanhTrai_haveGone = true;
                        player.clan.doanhTrai.setLastTimeOpen(System.currentTimeMillis() + 290_000);
                        player.clan.doanhTrai.DropNgocRong();
                        for (Player pl : player.clan.membersInGame) {
                            ItemTimeService.gI().sendTextTime(pl, (byte) 0, "Doanh trại độc nhãn sắp kết thúc : ", 300);
                        }
                        player.clan.doanhTrai.timePickDragonBall = true;
                        createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Ta đã thả ngọc rồng ở tất cả các map,mau đi nhặt đi. Hẹn ngươi quay lại vào ngày mai",
                                "OK");
                    } else {
                        createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Hãy tiêu diệt hết quái và boss trong map", "OK");
                    }

                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.MENU_JOIN_DOANH_TRAI:
                            if (select == 0) {
                                DoanhTraiService.gI().joinDoanhTrai(player);
                            } else if (select == 2) {
                                NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_DOANH_TRAI);
                            }
                            break;
                        case ConstNpc.IGNORE_MENU:
                            if (select == 1) {
                                NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_DOANH_TRAI);
                            }
                            break;
                    }
                }
            }
        };
    }

    public static Npc osin(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 50) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                "Đến\nKaio", "Đến\nhành tinh\nBill", "Từ chối");
                    } else if (this.mapId == 154) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                "Về thánh địa", "Đến\nhành tinh\nngục tù", "Từ chối");
                    } else if (this.mapId == 155) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                "Quay về", "Từ chối");
                    } else if (this.mapId == 185) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                "Quay về ", "Từ chối ");
                    } else if (this.mapId == 52) {
                        try {
                            MapMaBu.gI().setTimeJoinMapMaBu();
                            if (this.mapId == 52) {
                                long now = System.currentTimeMillis();
                                if (now > MapMaBu.TIME_OPEN_MABU && now < MapMaBu.TIME_CLOSE_MABU) {
                                    this.createOtherMenu(player, ConstNpc.MENU_OPEN_MMB, "Đại chiến Ma Bư đã mở, "
                                            + "ngươi có muốn tham gia không?",
                                            "Hướng dẫn\nthêm", "Tham gia", "Đến\nHành tinh\nBóng tối", "Từ chối");
                                } else {
                                    this.createOtherMenu(player, ConstNpc.MENU_NOT_OPEN_MMB,
                                            "4 vị thần bóng tối Bui bui,Yacôn,Drabura và Mabư đang chờ ngươi đấy\nNgươi có muốn đến hành tinh bóng tối không?", "Đến\nHành Tinh\nBóng tối", "Hướng dẫn", "Từ chối");
                                }

                            }
                        } catch (Exception ex) {
                            Logger.error("Lỗi mở menu osin");
                        }

                    } else if (this.mapId >= 114 && this.mapId < 120 && this.mapId != 116) {
                        if (player.fightMabu.pointMabu >= player.fightMabu.POINT_MAX) {
                            this.createOtherMenu(player, ConstNpc.GO_UPSTAIRS_MENU, "Ta có thể giúp gì cho ngươi ?",
                                    "Lên Tầng!", "Quay về", "Từ chối");
                        } else {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                    "Quay về", "Từ chối");
                        }
                    } else if (this.mapId == 120) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                "Quay về", "Từ chối");
                    } else {
                        super.openBaseMenu(player);
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 50) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMap(player, 48, -1, 354, 240);
                                    break;
                                case 1:
                                    ChangeMapService.gI().changeMap(player, 154, -1, 200, 312);
                                    break;
                            }
                        }
                    } else if (this.mapId == 154) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMap(player, 50, -1, 318, 336);
                                    break;
                                case 1:
                                    ChangeMapService.gI().changeMap(player, 155, -1, 111, 792);
                                    break;
                            }
                        }
                    } else if (this.mapId == 155) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                ChangeMapService.gI().changeMap(player, 154, -1, 200, 312);
                            }
                        }
                    } else if (this.mapId == 185) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                ChangeMapService.gI().changeMap(player, 52, -1, 200, 312);
                            }
                        }
                    } else if (this.mapId == 52) {
                        switch (player.iDMark.getIndexMenu()) {
                            case ConstNpc.MENU_REWARD_MMB:
                                break;
                            case ConstNpc.MENU_OPEN_MMB:
                                if (select == 0) {
                                    NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_MAP_MA_BU);
                                } else if (select == 1) {
                                    ChangeMapService.gI().changeMap(player, 114, -1, 318, 336);
                                } else if (select == 2) {
                                    ChangeMapService.gI().changeMap(player, 185, -1, 318, 336);
                                }
                                break;
                            case ConstNpc.MENU_NOT_OPEN_BDW:
                                if (select == 0) {
                                    ChangeMapService.gI().changeMap(player, 185, -1, 318, 336);
                                } else if (select == 1) {
                                    NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_MAP_MA_BU);
                                }
                                break;
                        }
                    } else if (this.mapId >= 114 && this.mapId < 120 && this.mapId != 116) {
                        if (player.iDMark.getIndexMenu() == ConstNpc.GO_UPSTAIRS_MENU) {
                            if (select == 0) {
                                player.fightMabu.clear();
                                ChangeMapService.gI().changeMap(player, this.map.mapIdNextMabu((short) this.mapId), -1, this.cx, this.cy);
                            } else if (select == 1) {
                                ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 21, 0, -1);
                            }
                        } else {
                            if (select == 0) {
                                ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 21, 0, -1);
                            }
                        }
                    } else if (this.mapId == 120) {
                        if (player.iDMark.getIndexMenu() == ConstNpc.BASE_MENU) {
                            if (select == 0) {
                                ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 21, 0, -1);
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc linhCanh(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (player.clan == null) {
                        this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Chỉ tiếp các bang hội, miễn tiếp khách vãng lai", "Đóng");
                        return;
                    }
                    if (player.clan.getMembers().size() < DoanhTrai.N_PLAYER_CLAN) {
                        this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Bang hội phải có ít nhất 5 thành viên mới có thể mở", "Đóng");
                        return;
                    }
                    if (player.clan.doanhTrai != null) {
                        createOtherMenu(player, ConstNpc.MENU_JOIN_DOANH_TRAI,
                                "Bang hội của ngươi đang đánh trại độc nhãn\n"
                                + "Thời gian còn lại là "
                                + TimeUtil.getMinLeft(player.clan.doanhTrai.getLastTimeOpen(),
                                        DoanhTrai.TIME_DOANH_TRAI / 1000)
                                + " phút . Ngươi có muốn tham gia không?",
                                "Tham gia", "Không", "Hướng\ndẫn\nthêm");
                        return;
                    }
                    int nPlSameClan = 0;
                    for (Player pl : player.zone.getPlayers()) {
                        if (!pl.equals(player) && pl.clan != null
                                && pl.clan.equals(player.clan) && pl.location.x >= 1285
                                && pl.location.x <= 1645) {
                            nPlSameClan++;
                        }
                    }
                    if (nPlSameClan < DoanhTrai.N_PLAYER_MAP) {
                        createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Ngươi phải có ít nhất " + DoanhTrai.N_PLAYER_MAP
                                + " đồng đội cùng bang đứng gần mới có thể\nvào\n"
                                + "tuy nhiên ta khuyên ngươi nên đi cùng với 3-4 người để khỏi chết.\n"
                                + "Hahaha.",
                                "OK", "Hướng\ndẫn\nthêm");
                        return;
                    }
                    if (player.clanMember.getNumDateFromJoinTimeToToday() < 0) {
                        createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Doanh trại chỉ cho phép những người ở trong bang trên 1 ngày. Hẹn ngươi quay lại vào lúc khác",
                                "OK", "Hướng\ndẫn\nthêm");
                        return;
                    }
                    if (player.clan.haveGoneDoanhTrai) {
                        createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Bang hội của ngươi đã đi trại lúc "
                                + TimeUtil.formatTime(player.clan.lastTimeOpenDoanhTrai, "HH:mm:ss")
                                + " hôm nay. Người mở\n"
                                + "(" + player.clan.playerOpenDoanhTrai + "). Hẹn ngươi quay lại vào ngày mai",
                                "OK", "Hướng\ndẫn\nthêm");
                        return;
                    }
                    createOtherMenu(player, ConstNpc.MENU_JOIN_DOANH_TRAI,
                            "Hôm nay bang hội của ngươi chưa vào trại lần nào. Ngươi có muốn vào\n"
                            + "không?\nĐể vào, ta khuyên ngươi nên có 3-4 người cùng bang đi cùng",
                            "Vào\n(miễn phí)", "Không", "Hướng\ndẫn\nthêm");
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.MENU_JOIN_DOANH_TRAI:
                            if (select == 0) {
                                DoanhTraiService.gI().joinDoanhTrai(player);
                            } else if (select == 2) {
                                NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_DOANH_TRAI);
                            }
                            break;
                        case ConstNpc.IGNORE_MENU:
                            if (select == 1) {
                                NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_DOANH_TRAI);
                            }
                            break;
                    }
                }
            }
        };
    }

    public static Npc quaTrung(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            private final int COST_AP_TRUNG_NHANH = 1000000000;

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == (21 + player.gender)) {
                        player.mabuEgg.sendMabuEgg();
                        if (player.mabuEgg.getSecondDone() != 0) {
                            this.createOtherMenu(player, ConstNpc.CAN_NOT_OPEN_EGG, "Bư bư bư...",
                                    "Hủy bỏ\ntrứng", "Ấp nhanh\n" + Util.numberToMoney(COST_AP_TRUNG_NHANH) + " vàng", "Đóng");
                        } else {
                            this.createOtherMenu(player, ConstNpc.CAN_OPEN_EGG, "Bư bư bư...", "Nở", "Hủy bỏ\ntrứng", "Đóng");
                        }
                    }
                    if (this.mapId == 174) {
                        player.billEgg.sendBillEgg();
                        if (player.billEgg.getSecondDone() != 0) {
                            this.createOtherMenu(player, ConstNpc.CAN_NOT_OPEN_EGG, "Bư bư bư...",
                                    "Hủy bỏ\ntrứng", "Ấp nhanh\n" + Util.numberToMoney(COST_AP_TRUNG_NHANH) + " vàng", "Đóng");
                        } else {
                            this.createOtherMenu(player, ConstNpc.CAN_OPEN_EGG, "Bư bư bư...", "Nở", "Hủy bỏ\ntrứng", "Đóng");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == (21 + player.gender)) {
                        switch (player.iDMark.getIndexMenu()) {
                            case ConstNpc.CAN_NOT_OPEN_EGG:
                                if (select == 0) {
                                    this.createOtherMenu(player, ConstNpc.CONFIRM_DESTROY_EGG,
                                            "Bạn có chắc chắn muốn hủy bỏ trứng Mabư?", "Đồng ý", "Từ chối");
                                } else if (select == 1) {
                                    if (player.inventory.gold >= COST_AP_TRUNG_NHANH) {
                                        player.inventory.gold -= COST_AP_TRUNG_NHANH;
                                        player.mabuEgg.timeDone = 0;
                                        Service.gI().sendMoney(player);
                                        player.mabuEgg.sendMabuEgg();
                                    } else {
                                        Service.gI().sendThongBao(player,
                                                "Bạn không đủ vàng để thực hiện, còn thiếu "
                                                + Util.numberToMoney((COST_AP_TRUNG_NHANH - player.inventory.gold)) + " vàng");
                                    }
                                }
                                break;
                            case ConstNpc.CAN_OPEN_EGG:
                                switch (select) {
                                    case 0:
                                        this.createOtherMenu(player, ConstNpc.CONFIRM_OPEN_EGG,
                                                "Bạn có chắc chắn cho trứng nở?\n"
                                                + "Đệ tử của bạn sẽ được thay thế bằng đệ Mabư",
                                                "Đệ mabư\nTrái Đất", "Đệ mabư\nNamếc", "Đệ mabư\nXayda", "Từ chối");
                                        break;
                                    case 1:
                                        this.createOtherMenu(player, ConstNpc.CONFIRM_DESTROY_EGG,
                                                "Bạn có chắc chắn muốn hủy bỏ trứng Mabư?", "Đồng ý", "Từ chối");
                                        break;
                                }
                                break;
                            case ConstNpc.CONFIRM_OPEN_EGG:
                                switch (select) {
                                    case 0:
                                        player.mabuEgg.openEgg(ConstPlayer.TRAI_DAT);
                                        break;
                                    case 1:
                                        player.mabuEgg.openEgg(ConstPlayer.NAMEC);
                                        break;
                                    case 2:
                                        player.mabuEgg.openEgg(ConstPlayer.XAYDA);
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case ConstNpc.CONFIRM_DESTROY_EGG:
                                if (select == 0) {
                                    player.mabuEgg.destroyEgg();
                                }
                                break;
                        }
                    }
                    if (this.mapId == 174) {
                        switch (player.iDMark.getIndexMenu()) {
                            case ConstNpc.CAN_NOT_OPEN_BILL:
                                if (select == 0) {
                                    this.createOtherMenu(player, ConstNpc.CONFIRM_DESTROY_BILL,
                                            "Bạn có chắc chắn muốn hủy bỏ trứng Mabư?", "Đồng ý", "Từ chối");
                                } else if (select == 1) {
                                    if (player.inventory.gold >= COST_AP_TRUNG_NHANH) {
                                        player.inventory.gold -= COST_AP_TRUNG_NHANH;
                                        player.billEgg.timeDone = 0;
                                        Service.gI().sendMoney(player);
                                        player.billEgg.sendBillEgg();
                                    } else {
                                        Service.gI().sendThongBao(player,
                                                "Bạn không đủ vàng để thực hiện, còn thiếu "
                                                + Util.numberToMoney((COST_AP_TRUNG_NHANH - player.inventory.gold)) + " vàng");
                                    }
                                }
                                break;
                            case ConstNpc.CAN_OPEN_EGG:
                                switch (select) {
                                    case 0:
                                        this.createOtherMenu(player, ConstNpc.CONFIRM_OPEN_BILL,
                                                "Bạn có chắc chắn cho trứng nở?\n"
                                                + "Đệ tử của bạn sẽ được thay thế bằng đệ Mabư",
                                                "Đệ mabư\nTrái Đất", "Đệ mabư\nNamếc", "Đệ mabư\nXayda", "Từ chối");
                                        break;
                                    case 1:
                                        this.createOtherMenu(player, ConstNpc.CONFIRM_DESTROY_BILL,
                                                "Bạn có chắc chắn muốn hủy bỏ trứng Mabư?", "Đồng ý", "Từ chối");
                                        break;
                                }
                                break;
                            case ConstNpc.CONFIRM_OPEN_BILL:
                                switch (select) {
                                    case 0:
                                        player.billEgg.openEgg(ConstPlayer.TRAI_DAT);
                                        break;
                                    case 1:
                                        player.billEgg.openEgg(ConstPlayer.NAMEC);
                                        break;
                                    case 2:
                                        player.billEgg.openEgg(ConstPlayer.XAYDA);
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case ConstNpc.CONFIRM_DESTROY_BILL:
                                if (select == 0) {
                                    player.billEgg.destroyEgg();
                                }
                                break;
                        }
                    }

                }
            }
        };
    }
//     public static Npc carot(int mapId, int status, int cx, int cy, int tempId, int avartar) {
//        return new Npc(mapId, status, cx, cy, tempId, avartar) {
//
//            private final int COST_AP_TRUNG_NHANH = 1000000000;
//
//            @Override
//            public void openBaseMenu(Player player) {
//                if (canOpenNpc(player)) {
//                    if (this.mapId == (21 + player.gender)) {
//                        player.carotegg.sendcarotegg();
//                        if (player.carotegg.getSecondDone() != 0) {
//                            this.createOtherMenu(player, ConstNpc.CAN_NOT_OPEN_EGG, "Bư bư bư...",
//                                    "Hủy bỏ\ntrứng", "Ấp nhanh\n" + Util.numberToMoney(COST_AP_TRUNG_NHANH) + " vàng", "Đóng");
//                        } else {
//                            this.createOtherMenu(player, ConstNpc.CAN_OPEN_EGG, "Bư bư bư...", "Nở", "Hủy bỏ\ntrứng", "Đóng");
//                        }
//                    }
//                    if (this.mapId == 154) {
//                        player.billEgg.sendBillEgg();
//                        if (player.billEgg.getSecondDone() != 0) {
//                            this.createOtherMenu(player, ConstNpc.CAN_NOT_OPEN_EGG, "Bư bư bư...",
//                                    "Hủy bỏ\ntrứng", "Ấp nhanh\n" + Util.numberToMoney(COST_AP_TRUNG_NHANH) + " vàng", "Đóng");
//                        } else {
//                            this.createOtherMenu(player, ConstNpc.CAN_OPEN_EGG, "Bư bư bư...", "Nở", "Hủy bỏ\ntrứng", "Đóng");
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void confirmMenu(Player player, int select) {
//                if (canOpenNpc(player)) {
//                     if (this.mapId == 174) {
//                        switch (player.iDMark.getIndexMenu()) {
//                            case ConstNpc.CAN_NOT_OPEN_EGG:
//                                if (select == 0) {
//                                    this.createOtherMenu(player, ConstNpc.CONFIRM_DESTROY_EGG,
//                                            "Bạn có chắc chắn muốn hủy bỏ trứng Mabư?", "Đồng ý", "Từ chối");
//                                } else if (select == 1) {
//                                    if (player.inventory.gold >= COST_AP_TRUNG_NHANH) {
//                                        player.inventory.gold -= COST_AP_TRUNG_NHANH;
//                                        player.carotegg.timeDone = 0;
//                                        Service.gI().sendMoney(player);
//                                        player.carotegg.sendcarotegg();
//                                    } else {
//                                        Service.gI().sendThongBao(player,
//                                                "Bạn không đủ vàng để thực hiện, còn thiếu "
//                                                + Util.numberToMoney((COST_AP_TRUNG_NHANH - player.inventory.gold)) + " vàng");
//                                    }
//                                }
//                                break;
//                            case ConstNpc.CAN_OPEN_EGG:
//                                switch (select) {
//                                    case 0:
//                                        this.createOtherMenu(player, ConstNpc.CONFIRM_OPEN_EGG,
//                                                "Bạn có chắc chắn cho trứng nở?\n"
//                                                + "Đệ tử của bạn sẽ được thay thế bằng đệ Mabư",
//                                                "Đệ mabư\nTrái Đất", "Đệ mabư\nNamếc", "Đệ mabư\nXayda", "Từ chối");
//                                        break;
//                                    case 1:
//                                        this.createOtherMenu(player, ConstNpc.CONFIRM_DESTROY_EGG,
//                                                "Bạn có chắc chắn muốn hủy bỏ trứng Mabư?", "Đồng ý", "Từ chối");
//                                        break;
//                                }
//                                break;
//                            case ConstNpc.CONFIRM_OPEN_EGG:
//                                switch (select) {
//                                    case 0:
//                                        player.mabuEgg.openEgg(ConstPlayer.TRAI_DAT);
//                                        break;
//                                    case 1:
//                                        player.mabuEgg.openEgg(ConstPlayer.NAMEC);
//                                        break;
//                                    case 2:
//                                        player.mabuEgg.openEgg(ConstPlayer.XAYDA);
//                                        break;
//                                    default:
//                                        break;
//                                }
//                                break;
//                            case ConstNpc.CONFIRM_DESTROY_EGG:
//                                if (select == 0) {
//                                    player.mabuEgg.destroyEgg();
//                                }
//                                break;
//                        
//                   
//                        
//                        }}
//
//                }
//            }
//        };
//    }

    public static Npc quocVuong(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                this.createOtherMenu(player, ConstNpc.BASE_MENU,
                        "Con muốn nâng giới hạn sức mạnh cho bản thân hay đệ tử?",
                        "Bản thân", "Đệ tử", "Từ chối");
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0:
                                if (player.nPoint.limitPower < NPoint.MAX_LIMIT) {
                                    this.createOtherMenu(player, ConstNpc.OPEN_POWER_MYSEFT,
                                            "Ta sẽ truyền năng lượng giúp con mở giới hạn sức mạnh của bản thân lên "
                                            + Util.numberToMoney(player.nPoint.getPowerNextLimit()),
                                            "Nâng\ngiới hạn\nsức mạnh",
                                            "Nâng ngay\n" + Util.numberToMoney(OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER) + " vàng", "Đóng");
                                } else {
                                    this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                            "Sức mạnh của con đã đạt tới giới hạn",
                                            "Đóng");
                                }
                                break;
                            case 1:
                                if (player.pet != null) {
                                    if (player.pet.nPoint.limitPower < NPoint.MAX_LIMIT) {
                                        this.createOtherMenu(player, ConstNpc.OPEN_POWER_PET,
                                                "Ta sẽ truền năng lượng giúp con mở giới hạn sức mạnh của đệ tử lên "
                                                + Util.numberToMoney(player.pet.nPoint.getPowerNextLimit()),
                                                "Nâng ngay\n" + Util.numberToMoney(OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER) + " vàng", "Đóng");
                                    } else {
                                        this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                                "Sức mạnh của đệ con đã đạt tới giới hạn",
                                                "Đóng");
                                    }
                                } else {
                                    Service.gI().sendThongBao(player, "Không thể thực hiện");
                                }
                                //giới hạn đệ tử
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.OPEN_POWER_MYSEFT) {
                        switch (select) {
                            case 0:
                                OpenPowerService.gI().openPowerBasic(player);
                                break;
                            case 1:
                                if (player.inventory.gold >= OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER) {
                                    if (OpenPowerService.gI().openPowerSpeed(player)) {
                                        player.inventory.gold -= OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER;
                                        Service.gI().sendMoney(player);
                                    }
                                } else {
                                    Service.gI().sendThongBao(player,
                                            "Bạn không đủ vàng để mở, còn thiếu "
                                            + Util.numberToMoney((OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER - player.inventory.gold)) + " vàng");
                                }
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.OPEN_POWER_PET) {
                        if (select == 0) {
                            if (player.inventory.gold >= OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER) {
                                if (OpenPowerService.gI().openPowerSpeed(player.pet)) {
                                    player.inventory.gold -= OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER;
                                    Service.gI().sendMoney(player);
                                }
                            } else {
                                Service.gI().sendThongBao(player,
                                        "Bạn không đủ vàng để mở, còn thiếu "
                                        + Util.numberToMoney((OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER - player.inventory.gold)) + " vàng");
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc bulmaTL(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 102) {
                        if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Cậu bé muốn mua gì nào?", "Cửa hàng", "Đóng");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 102) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                ShopServiceNew.gI().opendShop(player, "BUNMA_FUTURE", true);
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc rongOmega(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    BlackBallWar.gI().setTime();
                    if (this.mapId == 24 || this.mapId == 25 || this.mapId == 26) {
                        try {
                            long now = System.currentTimeMillis();
                            if (now > BlackBallWar.TIME_OPEN && now < BlackBallWar.TIME_CLOSE) {
                                this.createOtherMenu(player, ConstNpc.MENU_OPEN_BDW, "Đường đến với ngọc rồng sao đen đã mở, "
                                        + "ngươi có muốn tham gia không?",
                                        "Hướng dẫn\nthêm", "Tham gia", "Từ chối");
                            } else {
                                String[] optionRewards = new String[7];
                                int index = 0;
                                for (int i = 0; i < 7; i++) {
                                    if (player.rewardBlackBall.timeOutOfDateReward[i] > System.currentTimeMillis()) {
                                        String quantily = player.rewardBlackBall.quantilyBlackBall[i] > 1 ? "x" + player.rewardBlackBall.quantilyBlackBall[i] + " " : "";
                                        optionRewards[index] = quantily + (i + 1) + " sao";
                                        index++;
                                    }
                                }
                                if (index != 0) {
                                    String[] options = new String[index + 1];
                                    for (int i = 0; i < index; i++) {
                                        options[i] = optionRewards[i];
                                    }
                                    options[options.length - 1] = "Từ chối";
                                    this.createOtherMenu(player, ConstNpc.MENU_REWARD_BDW, "Ngươi có một vài phần thưởng ngọc "
                                            + "rồng sao đen đây!",
                                            options);
                                } else {
                                    this.createOtherMenu(player, ConstNpc.MENU_NOT_OPEN_BDW,
                                            "Ta có thể giúp gì cho ngươi?", "Hướng dẫn", "Từ chối");
                                }
                            }
                        } catch (Exception ex) {
                            Logger.error("Lỗi mở menu rồng Omega");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.MENU_REWARD_BDW:
                            player.rewardBlackBall.getRewardSelect((byte) select);
                            break;
                        case ConstNpc.MENU_OPEN_BDW:
                            if (select == 0) {
                                NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_BLACK_BALL_WAR);
                            } else if (select == 1) {
//                                if (!player.getSession().actived) {
//                                    Service.gI().sendThongBao(player, "Vui lòng kích hoạt tài khoản để sử dụng chức năng này");
//
//                                } else
                                player.iDMark.setTypeChangeMap(ConstMap.CHANGE_BLACK_BALL);
                                ChangeMapService.gI().openChangeMapTab(player);
                            }
                            break;
                        case ConstNpc.MENU_NOT_OPEN_BDW:
                            if (select == 0) {
                                NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_BLACK_BALL_WAR);
                            }
                            break;
                    }
                }
            }

        };
    }

    public static Npc rong1_to_7s(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isHoldBlackBall()) {
                        this.createOtherMenu(player, ConstNpc.MENU_PHU_HP, "Ta có thể giúp gì cho ngươi?", "Phù hộ", "Từ chối");
                    } else {
                        if (BossManager.gI().existBossOnPlayer(player)
                                || player.zone.items.stream().anyMatch(itemMap -> ItemMapService.gI().isBlackBall(itemMap.itemTemplate.id))
                                || player.zone.getPlayers().stream().anyMatch(p -> p.iDMark.isHoldBlackBall())) {
                            this.createOtherMenu(player, ConstNpc.MENU_OPTION_GO_HOME, "Ta có thể giúp gì cho ngươi?", "Về nhà", "Từ chối");
                        } else {
                            this.createOtherMenu(player, ConstNpc.MENU_OPTION_GO_HOME, "Ta có thể giúp gì cho ngươi?", "Về nhà", "Từ chối", "Gọi BOSS");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.getIndexMenu() == ConstNpc.MENU_PHU_HP) {
                        if (select == 0) {
                            this.createOtherMenu(player, ConstNpc.MENU_OPTION_PHU_HP,
                                    "Ta sẽ giúp ngươi tăng HP lên mức kinh hoàng, ngươi chọn đi",
                                    "x3 HP\n" + Util.numberToMoney(BlackBallWar.COST_X3) + " vàng",
                                    "x5 HP\n" + Util.numberToMoney(BlackBallWar.COST_X5) + " vàng",
                                    "x7 HP\n" + Util.numberToMoney(BlackBallWar.COST_X7) + " vàng",
                                    "Từ chối"
                            );
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPTION_GO_HOME) {
                        if (select == 0) {
                            ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 21, -1, 250);
                        } else if (select == 2) {
                            BossManager.gI().callBoss(player, mapId);
                        } else if (select == 1) {
                            this.npcChat(player, "Để ta xem ngươi trụ được bao lâu");
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPTION_PHU_HP) {
                        if (player.effectSkin.xHPKI > 1) {
                            Service.gI().sendThongBao(player, "Bạn đã được phù hộ rồi!");
                            return;
                        }
                        switch (select) {
                            case 0:
                                BlackBallWar.gI().xHPKI(player, BlackBallWar.X3);
                                break;
                            case 1:
                                BlackBallWar.gI().xHPKI(player, BlackBallWar.X5);
                                break;
                            case 2:
                                BlackBallWar.gI().xHPKI(player, BlackBallWar.X7);
                                break;
                            case 3:
                                this.npcChat(player, "Để ta xem ngươi trụ được bao lâu");
                                break;
                        }
                    }
                }
            }
        };
    }

    public static Npc npcThienSu64(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            public void chatWithNpc(Player player) {
                String[] chat = {
                    "|7|Top Máy Chủ",};
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    int index = 0;

                    @Override
                    public void run() {
                        npcChat(player, chat[index]);
                        index = (index + 1) % chat.length;
                    }
                }, 1000, 1000);
            }
            @Override
            public void openBaseMenu(Player player) {
                if (this.mapId == 14) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "|6|Ta sẽ dẫn cậu tới hành tinh Berrus với điều kiện\n 2. đạt 80 tỷ sức mạnh"
                            + "\n 3. chi phí vào cổng  50 triệu vàng"
                            + " |6|\n Sự kiện đua top chào mừng máy chủ Trùng Sinh ra mắt...", "Tới ngay", "Top\nSức Mạnh","Top\nNhiệm Vụ","Top\nSức Đánh","Từ Chối");
                }
                if (this.mapId == 7) {
                     this.createOtherMenu(player, ConstNpc.BASE_MENU, "|6|Ta sẽ dẫn cậu tới hành tinh Berrus với điều kiện\n 2. đạt 80 tỷ sức mạnh"
                            + "\n 3. chi phí vào cổng  50 triệu vàng"
                            + " |6|\n Sự kiện đua top chào mừng máy chủ Trùng Sinh ra mắt...", "Tới ngay", "Top\nSức Mạnh","Top\nNhiệm Vụ","Top\nSức Đánh","Từ Chối");
                }
                if (this.mapId == 0) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "|6|Ta sẽ dẫn cậu tới hành tinh Berrus với điều kiện\n 2. đạt 80 tỷ sức mạnh"
                            + "\n 3. chi phí vào cổng  50 triệu vàng"
                            + " |6|\n Sự kiện đua top chào mừng máy chủ Trùng Sinh ra mắt...", "Tới ngay", "Top\nSức Mạnh","Top\nNhiệm Vụ","Top\nSức Đánh","Từ Chối");
                }
                if (this.mapId == 146) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Cậu không chịu nổi khi ở đây sao?\nCậu sẽ khó mà mạnh lên được", "Trốn về", "Ở lại");
                }
                if (this.mapId == 147) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Cậu không chịu nổi khi ở đây sao?\nCậu sẽ khó mà mạnh lên được", "Trốn về", "Ở lại");
                }
                if (this.mapId == 148) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Cậu không chịu nổi khi ở đây sao?\nCậu sẽ khó mà mạnh lên được", "Trốn về", "Ở lại");
                }
                if (this.mapId == 48) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Đã tìm đủ nguyên liệu cho tôi chưa?\n Tôi sẽ giúp cậu mạnh lên kha khá đấy!", "Hướng Dẫn",
                            "Đổi Thức Ăn\nLấy Điểm", "Từ Chối");
                }
                if (this.mapId == 154) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Đã tìm đủ nguyên liệu cho tôi chưa?\n Tôi sẽ giúp cậu mạnh lên kha khá đấy!",
                            "Chế Tạo trang bị thiên sứ", "Cửa Hàng\nBán Ấy", "Địt Nhau");
                }
            }

            //if (player.inventory.gold < 500000000) {
//                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con", "Đóng");
//                return;
//            }
            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu() && this.mapId == 7) {
                        if (select == 0) {
                            if (player.getSession().player.nPoint.power >= 80000000000L && player.inventory.gold > COST_HD) {
                                player.inventory.gold -= COST_HD;
                                Service.gI().sendMoney(player);
                                ChangeMapService.gI().changeMapBySpaceShip(player, 146, -1, 168);
                            } else {
                                this.npcChat(player, "Bạn chưa đủ điều kiện để vào");
                            }}
                              if (select == 1) {
                                Service.gI().showListTop(player, Manager.topSM);
                              }}
                            if (select == 2) {
                                Service.gI().showListTop(player, Manager.topNV);
                            }}
                             if (select == 3) {
                                Service.gI().showListTop(player, Manager.topSD);
                             
                              if (select == 4) {
                        
                    }
                    if (player.iDMark.isBaseMenu() && this.mapId == 14) {
                        if (select == 0) {
                            if (player.getSession().player.nPoint.power >= 80000000000L && player.inventory.gold > COST_HD) {
                                player.inventory.gold -= COST_HD;
                                Service.gI().sendMoney(player);
                                ChangeMapService.gI().changeMapBySpaceShip(player, 148, -1, 168);
                            } else {
                                this.npcChat(player, "Bạn chưa đủ điều kiện để vào");
                            }
                        }
                              if (select == 1) {
                                Service.gI().showListTop(player, Manager.topSM);
                              }}
                            if (select == 2) {
                                Service.gI().showListTop(player, Manager.topNV);
                            }}
                             if (select == 3) {
                                Service.gI().showListTop(player, Manager.topSD);
                             
                              if (select == 4) {
                        
                    }
                    if (player.iDMark.isBaseMenu() && this.mapId == 0) {
                        if (select == 0) {
                            if (player.getSession().player.nPoint.power >= 80000000000L && player.inventory.gold > COST_HD) {
                                player.inventory.gold -= COST_HD;
                                Service.gI().sendMoney(player);
                                ChangeMapService.gI().changeMapBySpaceShip(player, 147, -1, 168);
                            } else {
                                this.npcChat(player, "Bạn chưa đủ điều kiện để vào");
                            }
                        }
                              if (select == 1) {
                                Service.gI().showListTop(player, Manager.topSM);
                              }}
                            if (select == 2) {
                                Service.gI().showListTop(player, Manager.topNV);
                            }}
                             if (select == 3) {
                                Service.gI().showListTop(player, Manager.topSD);
                             
                              if (select == 4) {
                        
                    }
                    if (player.iDMark.isBaseMenu() && this.mapId == 147) {
                        if (select == 0) {
                            ChangeMapService.gI().changeMapBySpaceShip(player, 0, -1, 450);
                        }
                        if (select == 1) {
                        }
                    }
                    if (player.iDMark.isBaseMenu() && this.mapId == 148) {
                        if (select == 0) {
                            ChangeMapService.gI().changeMapBySpaceShip(player, 14, -1, 450);
                        }
                        if (select == 1) {
                        }
                    }
                    if (player.iDMark.isBaseMenu() && this.mapId == 146) {
                        if (select == 0) {
                            ChangeMapService.gI().changeMapBySpaceShip(player, 7, -1, 450);
                        }
                        if (select == 1) {
                        }

                    }
                    if (player.iDMark.isBaseMenu() && this.mapId == 48) {
                        if (select == 0) {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "x99 Thức Ăn Được 1 Điểm");
                        }
                        if (select == 1) {
                            CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.DOI_DIEM);
                        }

                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                        switch (player.combineNew.typeCombine) {
                            case CombineServiceNew.DOI_DIEM:

                                if (select == 0) {
                                    CombineServiceNew.gI().startCombine(player);
                                }
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_PHAN_RA_DO_THAN_LINH) {
                        if (select == 0) {
                            CombineServiceNew.gI().startCombine(player);
                        }

                    }
                    if (player.iDMark.isBaseMenu() && this.mapId == 154) {
                        if (select == 0) {
                            CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.CHE_TAO_TRANG_BI_TS);
                        }
                        if (select == 1) {
                            ShopServiceNew.gI().opendShop(player, "WHIS", true);
                        }
                        if (select == 2) {
                            Service.gI().sendThongBaoOK(player, "Buồi Bé Tý Địt cc");
                        }

                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                        switch (player.combineNew.typeCombine) {
                            case CombineServiceNew.CHE_TAO_TRANG_BI_TS:

                                if (select == 0) {
                                    CombineServiceNew.gI().startCombine(player);
                                }
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_NANG_CAP_DO_TS) {
                        if (select == 0) {
                            CombineServiceNew.gI().startCombine(player);
                        }

                    }
                }
            }

        };
    }

    public static Npc bill(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Gặp Whis Để Đổi Thức Ăn Lấy Điểm Sau Đó Gặp Ta Để Mua Trang Bị Hủy Diệt",
                            "Điểm",
                            "Shop Hủy Diệt", "Đóng");
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (this.mapId) {
                        case 48:
                            switch (player.iDMark.getIndexMenu()) {
                                case ConstNpc.BASE_MENU:
                                    if (select == 0) {
                                        createOtherMenu(player, ConstNpc.IGNORE_MENU, "Mày Có " + player.inventory.coupon + " Điểm", "Đóng");
                                    }
                                    if (select == 1) {
                                        if (player.inventory.coupon == 0) {
                                            createOtherMenu(player, ConstNpc.IGNORE_MENU, "Ngươi Không Có Điểm Vui Lòng Đổi Điểm Bằng Thức Ăn", "Đóng");
                                        } else {
                                            ShopServiceNew.gI().opendShop(player, "BILL", false);
                                            break;
                                        }
                                    }
                            }
                            break;
                    }
                }
            }
        };
    }

    public static Npc boMong(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 47 || this.mapId == 84) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Xin chào, cậu muốn tôi giúp gì?", "Nhiệm vụ\nhàng ngày", "Nhiệm vụ\nthành tích","Danh Hiệu ","Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 47 || this.mapId == 84) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    if (player.playerTask.sideTask.template != null) {
                                        String npcSay = "Nhiệm vụ hiện tại: " + player.playerTask.sideTask.getName() + " ("
                                                + player.playerTask.sideTask.getLevel() + ")"
                                                + "\nHiện tại đã hoàn thành: " + player.playerTask.sideTask.count + "/"
                                                + player.playerTask.sideTask.maxCount + " ("
                                                + player.playerTask.sideTask.getPercentProcess() + "%)\nSố nhiệm vụ còn lại trong ngày: "
                                                + player.playerTask.sideTask.leftTask + "/" + ConstTask.MAX_SIDE_TASK;
                                        this.createOtherMenu(player, ConstNpc.MENU_OPTION_PAY_SIDE_TASK,
                                                npcSay, "Trả nhiệm\nvụ", "Hủy nhiệm\nvụ");
                                    } else {
                                        this.createOtherMenu(player, ConstNpc.MENU_OPTION_LEVEL_SIDE_TASK,
                                                "Tôi có vài nhiệm vụ theo cấp bậc, "
                                                + "sức cậu có thể làm được cái nào?",
                                                "Dễ", "Bình thường", "Khó", "Siêu khó", "Địa ngục", "Từ chối");
                                    }
                                    break;
                                case 1:
                                    player.achievement.Show();
                                    break;
                                   case 2:
                                    this.createOtherMenu(player, 888,
                                            "|7|CHỨC NĂNG DANH HIỆU"
                                            + "\n\n|2|Đây là danh hiệu mà ngươi có"
                                            + "\n\n|2|Danh hiệu 'Cao Thủ Siêu Hạng' tăng 5% Chỉ số"
                                            + "\n\n|7|Lưu ý: phải bật danh hiệu lên mới tăng chỉ số"
                                            + (player.lastTimeTitle1 > 0 ? "\n\n|4|Danh hiệu 1: " + Util.msToTime(player.lastTimeTitle1) : ""),
                                            ("Nhận danh hiệu"), 
                                            ("Danh hiệu 1\n" + (player.isTitleUse1 == true ? "'ON'" : "'OFF'") + "\n"));
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPTION_LEVEL_SIDE_TASK) {
                            switch (select) {
                                case 0:
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                    TaskService.gI().changeSideTask(player, (byte) select);
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPTION_PAY_SIDE_TASK) {
                            switch (select) {
                                case 0:
                                    TaskService.gI().paySideTask(player);
                                    break;
                                case 1:
                                    TaskService.gI().removeSideTask(player);
                                    break;
                            }
                             } else if (player.iDMark.getIndexMenu() == 888) {
                            switch (select) {
                                case 0:
                                    if (player.nPoint.power >= 100000000000L) {
                                        if (player.lastTimeTitle1 == 0) {
                                            if (player.lastTimeTitle1 == 0) {
                                                player.lastTimeTitle1 += System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 3);
                                            } else {
                                                player.lastTimeTitle1 += (1000 * 60 * 60 * 24 * 360);
                                            }
                                            player.isTitleUse1 = true;
                                            Service.gI().point(player);
                                            Service.gI().sendTitlerv(player, 888);
                                            Service.gI().sendThongBao(player, "Bạn nhận được vĩnh viễn !");
                                        } else {
                                            Service.gI().sendThongBao(player, "bạn dã nhận danh hiệu này rồi !");
                                        }
                                    } else {
                                        this.npcChat(player, "Yều cầu đạt 100 tỷ sức mạnh");
                                    }
                                    break;
                                case 1:
                                    if (player.lastTimeTitle1 > 0) {
                                        Service.gI().removeTitle(player);
                                        player.isTitleUse1 = !player.isTitleUse1;
                                        Service.gI().point(player);
                                        Service.gI().sendThongBao(player, "Đã " + (player.isTitleUse1 ? "Bật" : "Tắt") + " Danh Hiệu!");
                                        Service.gI().sendTitle(player, 891);
                                        Service.gI().sendTitle(player, 890);
                                        Service.gI().sendTitle(player, 889);
                                        Service.gI().sendTitle(player, 888);
                                        Service.gI().removeTitle(player);
                                    } else if (player.lastTimeTitle2 > 0) {
                                        Service.gI().removeTitle(player);
                                        player.isTitleUse2 = !player.isTitleUse2;
                                        Service.gI().point(player);
                                        Service.gI().sendThongBao(player, "Đã " + (player.isTitleUse2 ? "Bật" : "Tắt") + " Danh Hiệu!");
                                        Service.gI().sendTitle(player, 890);
                                        Service.gI().sendTitle(player, 889);
                                        Service.gI().sendTitle(player, 888);
                                        Service.gI().sendTitle(player, 891);
                                        Service.gI().removeTitle(player);
                                    } else if (player.lastTimeTitle3 > 0) {
                                        Service.gI().removeTitle(player);
                                        player.isTitleUse3 = !player.isTitleUse3;
                                        Service.gI().point(player);
                                        Service.gI().sendThongBao(player, "Đã " + (player.isTitleUse3 ? "Bật" : "Tắt") + " Danh Hiệu!");
                                        Service.gI().sendTitle(player, 891);
                                        Service.gI().sendTitle(player, 890);
                                        Service.gI().sendTitle(player, 889);
                                        Service.gI().sendTitle(player, 888);
                                        Service.gI().removeTitle(player);
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc karin(int mapId, int status, int cx, int cy, int tempId, int avatar) {
        return new Npc(mapId, status, cx, cy, tempId, avatar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player) && this.mapId == 46) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        String message;
                        if (player.playerTask.taskMain.id == 5 && player.playerTask.taskMain.index == 5) {
                            if (player.istrain) {
                                message = "Muốn chiến thắng Tàu Pảy Pảy phải đánh bại được ta";
                                this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Hủy đăng ký tập tự động", "Tập luyện với\nThần Mèo", "Thách đấu với\nThần Mèo");
                            } else {
                                message = "Muốn chiến thắng Tàu Pảy Pảy phải đánh bại được ta";
                                this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Đăng ký tập tự động", "Tập luyện với\nThần Mèo", "Thách đấu với\nThần Mèo");
                            }
                        } else if (player.typetrain == 0 && !player.istrain) {
                            message = "Từ giờ Yajirô sẽ luyện tập cùng ngươi. Yajirô đã lên đây đã từng lên đây tập luyện và bây giờ hắn mạnh hơn ta đấy";
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Đăng ký tập tự động", "Tập luyện với Yajirô", "Thách đấu Yajirô");
                        } else if (player.typetrain != 0 && player.istrain) {
                            message = "Con hãy bay theo cây Gậy Như Ý trên đỉnh tháp để đến Thần Điện gặp Thượng Đế\nCon rất xứng đáng để làm đệ tự của ông ấy";
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Hủy đăng ký tập tự động", "Tập luyện với Yajirô", "Tập luyện với thần mèo");
                        } else if (player.typetrain == 0 && player.istrain) {
                            message = "Từ giờ Yajirô sẽ luyện tập cùng ngươi. Yajirô đã lên đây đã từng lên đây tập luyện và bây giờ hắn mạnh hơn ta đấy";
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Hủy đăng ký tập tự động", "Tập luyện với Yajirô", "Thách đấu Yajirô");
                        } else if (player.clan != null && player.clan.ConDuongRanDoc != null && player.clan.gobosscdrd) {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Hãy cầm lấy hai hạt đậu cuối cùng của ta đây\nCố giữ mình nhé " + player.name, "Cám ơn\nsư phụ");
                            Service.gI().sendThongBao(player, "Hãy mau bay xuống\nchân tháp Karin");
                        } else {
                            message = "Con hãy bay theo cây Gậy Như Ý trên đỉnh tháp để đến Thần Điện gặp Thượng Đế\nCon rất xứng đáng để làm đệ tự của ông ấy";
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, message, "Đăng ký tập tự động", "Tập luyện với Yajirô", "Tập luyện với thần mèo");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player) && this.mapId == 46) {
                    if (player.iDMark.isBaseMenu()) {
                        if (select == 0) {
                            if (player.clan != null && player.clan.ConDuongRanDoc != null && player.clan.gobosscdrd) {
                                player.nPoint.setFullHpMp();
                                //                           ChangeMapService.gI().changeMapInYard(player, 144, 0, 131);
                            } else {
                                if (!player.istrain) {
                                    this.createOtherMenu(player, ConstNpc.MENU_TRAIN_OFFLINE, "Đăng ký để mỗi khi Offline quá 30 phút, con sẽ được tự động luyện tập với tốc độ " + player.nPoint.getexp() + " sức mạnh mỗi phút", "Hướng dẫn thêm", "Đồng ý 1 ngọc mỗi lần", "Không đồng ý");
                                } else {
                                    player.istrain = false;
                                    this.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Con đã hủy thành công đăng ký tập tự động", "Đóng");
                                }
                            }
                        } else if (select == 1) {
                            if (player.playerTask.taskMain.id == 5 && player.playerTask.taskMain.index == 5) {
                                this.createOtherMenu(player, ConstNpc.MENU_TRAIN_OFFLINE_TRY0, "Con có chắc muốn tập luyện?\nTập luyện với mèo thần Karin?", "Đồng ý luyện tập", "Không đồng ý");
                            } else {
                                this.createOtherMenu(player, ConstNpc.MENU_TRAIN_OFFLINE_TRY0, "Con có chắc muốn tập luyện?\nTập luyện với " + player.nPoint.getNameNPC(player, this, (byte) select) + " sẽ tăng " + player.nPoint.getExpbyNPC(player, this, (byte) select) + " sức mạnh mỗi phút", "Đồng ý luyện tập", "Không đồng ý");
                            }
                        } else if (select == 2) {
                            if (player.playerTask.taskMain.id == 5 && player.playerTask.taskMain.index == 5) {
                                this.createOtherMenu(player, ConstNpc.MENU_TRAIN_OFFLINE_TRY1, "Con có chắc muốn thách đấu?\nThách đấu với mèo thần Karin?", "Đồng ý thách đấu", "Không đồng ý");
                            } else if (player.typetrain != 0) {
                                this.createOtherMenu(player, ConstNpc.MENU_TRAIN_OFFLINE_TRY1, "Con có chắc muốn tập luyện?\nTập luyện với " + player.nPoint.getNameNPC(player, this, (byte) select) + " sẽ tăng " + player.nPoint.getExpbyNPC(player, this, (byte) select) + " sức mạnh mỗi phút", "Đồng ý luyện tập", "Không đồng ý");
                            } else {
                                player.setfight((byte) 1, (byte) 0);
                                player.zone.load_Another_To_Me(player);

                            }
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_TRAIN_OFFLINE) {
                        switch (select) {
                            case 0:
                                Service.gI().sendPopUpMultiLine(player, tempId, this.avartar, ConstNpc.INFOR_TRAIN_OFFLINE);
                                break;
                            case 1:
                                player.istrain = true;
                                NpcService.gI().createTutorial(player, this.avartar, "Từ giờ, quá 30 phút Offline con sẽ tự động luyện tập");
                                break;
                            case 3:
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_TRAIN_OFFLINE_TRY0) {
                        switch (select) {
                            case 0:
                                if (player.playerTask.taskMain.id == 5 && player.playerTask.taskMain.index == 5) {
                                    player.setfight((byte) 0, (byte) 1);
                                    player.zone.load_Me_To_Another(player);
                                    player.zone.load_Another_To_Me(player);
                                    player.zone.mapInfo(player);
                                    DataGame.updateMap(player.getSession());
                                    try {
                                        new MeoThan(BossID.MEO_THAN, BossesData.THAN_MEO, player.zone, this.cx, this.cy);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    player.setfight((byte) 0, (byte) 0);
                                    player.zone.load_Me_To_Another(player);
                                    player.zone.load_Another_To_Me(player);
                                }

                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_TRAIN_OFFLINE_TRY1) {
                        switch (select) {
                            case 0:
                                if (player.playerTask.taskMain.id == 5 && player.playerTask.taskMain.index == 5) {
                                    player.setfight((byte) 1, (byte) 1);
                                } else {
                                    player.setfight((byte) 0, (byte) 1);
                                }
                                player.zone.load_Me_To_Another(player);
                                player.zone.load_Another_To_Me(player);
                                player.zone.mapInfo(player);
                                DataGame.updateMap(player.getSession());
                                try {
                                    new MeoThan(BossID.MEO_THAN, BossesData.THAN_MEO, player.zone, this.cx, this.cy);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                        }
                    }
                }

            }
        };
    }

    public static Npc vados(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            public void chatWithNpc(Player player) {
                String[] chat = {
                    "|7|Top Máy Chủ",};
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    int index = 0;

                    @Override
                    public void run() {
                        npcChat(player, chat[index]);
                        index = (index + 1) % chat.length;
                    }
                }, 1000, 1000);
            }

            @Override
            public void openBaseMenu(Player player) {
                chatWithNpc(player);
                if (canOpenNpc(player)) {
                    if (this.mapId == 0 || this.mapId == 7 || this.mapId == 14) {
                        createOtherMenu(player, ConstNpc.BASE_MENU,
                                "|2|Ta Vừa Hack Được Top Của Toàn Server\b|7|Mi Muống Xem Tóp Gì?",
                                "Tóp Sức Mạnh", "Top Nhiệm Vụ", "Top Sức Đánh","Top Trồng\n Cà Rốt");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 0 || this.mapId == 7 || this.mapId == 14) {
                        switch (select) {
                            case 0:
                                Service.gI().showListTop(player, Manager.topSM);
                                break;
                            case 1:
                                Service.gI().showListTop(player, Manager.topNV);
                                break;
                            case 2:
                                Service.gI().showListTop(player, Manager.topSD);
                                break;
                            
                        }
                    }
                }
            }
        };
    }

    public static Npc gokuSSJ_1(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {

                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        if (this.mapId == 80) {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Xin chào, tôi có thể giúp gì cho cậu?",
                                    "Tới hành tinh\nYardart", "Từ chối");
                        } else if (this.mapId == 131) {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Xin chào, tôi có thể giúp gì cho cậu?",
                                    "Quay về", "Từ chối");
                        } else {
                            super.openBaseMenu(player);
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.BASE_MENU:
                            if (this.mapId == 80) {
                                if (select == 0) {
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 131, -1, 870);
                                }
                            }
                            break;
                    }
                }
            }
        };
    }
  public static Npc mavuong(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 153) {
                       this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Xin chào, tôi có thể giúp gì cho cậu?", "Tây thánh địa","Chức Năng\n Bang Hội", "Gọi Boss\nBang Hội","Xem Top Bang Hội","Từ chối");
                    } else if (this.mapId == 156) {
                       this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Người muốn trở về?", "Quay về", "Từ chối");

                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        if (this.mapId == 153) {
                            switch (select) {
                                 case 0:
                                   ChangeMapService.gI().changeMapBySpaceShip(player, 156, -1, 360);
                                    break;
                            
                                
                               case 1:
                                this.createOtherMenu(player, ConstNpc.CHUC_NANG_BANG_HOI,
                                        "Ta có hỗ trợ những chức năng Bang hội, nhà ngươi cần gì?", "Giải tán\nBang Hội", "Nâng cấp\nBang Hội", "Quyên Góp\nCapsule", "Từ chối");
                             break;
                            case 3:
                                    Service.gI().showListTop(player, Manager.topNap);
                                    break;
                             case 2: {
                                 Clan clan = player.clan;
                                 if (!clan.isLeader(player)) {
                                            Service.gI().sendThongBao(player, "Phải là bang chủ");
                                            break;
                                        }
                                    Boss oldBossClone = BossManager.gI().getBossById(Util.createIdBossClone((int) player.id));
                                    if (oldBossClone != null) {
                                        this.npcChat(player, "Nhà ngươi hãy tiêu diệt Boss lúc trước gọi ra đã, con boss đó đang ở khu " + oldBossClone.zone.zoneId);
                                    } else if (player.inventory.gold < 200_000_000) {
                                        this.npcChat(player, "Nhà ngươi không đủ 200 Triệu vàng ");
                                    } else {
                                       
                                        BossData bossDataClone = new BossData(
                                                "Boss Của Bang Hội " + clan.name,
                                               
                                                 ConstPlayer.TRAI_DAT, //gender
                                                 new short[]{1489, 1490, 1491, -1, -1, -1}, //outfit {head, body, leg, bag, aura, eff}
                                                 50000, //dame
                                                 new int[]{1000000000}, //hp
                                                new int[]{153},
                                                
                                                new int[][]{
                                                 {Skill.ANTOMIC, 7, 5000},
                                                     {Skill.MASENKO, 7, 5000},
                                                     {Skill.DRAGON, 7, 3000},
                                                        {Skill.DEMON, 7, 3000},
                                                        {Skill.GALICK, 7, 3000},
                                                  },//skill
                                                new String[]{"|-2|Boss nhân bản đã xuất hiện rồi"}, //text chat 1
                                                new String[]{"|-1|Ta sẽ chiếm lấy thân xác của ngươi hahaha!"}, //text chat 2
                                                new String[]{"|-1|Lần khác ta sẽ xử đẹp ngươi"}, //text chat 3
                                                60
                                        );

                                        try {
                                            new NhanBan(Util.createIdBossClone((int) player.id), bossDataClone, player.zone);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        //trừ vàng khi gọi boss
                                        player.inventory.gold -= 200_000_000;
                                        player.point_cauca += 1;
                                        Service.gI().sendMoney(player);
                                        player.point_cauca += 1;
                                    }
                                    break;
                             }
                        }
                        } else if (this.mapId == 156) {
                            switch (select) {
                              case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 153, -1, 432);
                                    break;
                                     }
                        }
                                     } else if (player.iDMark.getIndexMenu() == ConstNpc.CHUC_NANG_BANG_HOI) {
                        switch (select) {
                            case 0:
                                Clan clan = player.clan;
                                if (clan != null) {
                                    ClanMember cm = clan.getClanMember((int) player.id);
                                    if (cm != null) {
                                        if (!clan.isLeader(player)) {
                                            Service.gI().sendThongBao(player, "Yêu cầu phải là bang chủ!");
                                            break;
                                        }
                                        if (clan.members.size() > 1) {
                                            Service.gI().sendThongBao(player, "Yêu cầu bang hội chỉ còn một thành viên!");
                                            break;
                                        }
                                        NpcService.gI().createMenuConMeo(player, ConstNpc.CONFIRM_DISSOLUTION_CLAN, -1, "Bạn có chắc chắn muốn giải tán bang hội?\n( Yêu cầu sẽ không thể hoàn tác )",
                                                "Đồng ý", "Từ chối!");
                                        break;
                                    }
                                    break;
                                }
                                Service.gI().sendThongBao(player, "Yêu câu tham gia bang hội");
                                break;
                            case 1:
                                if (player.clan != null) {
                                    if (!player.clan.isLeader(player)) {
                                        Service.gI().sendThongBao(player, "Yêu cầu phải là bang chủ!");
                                        break;
                                    }
                                    if (player.clan.level >= 0 && player.clan.level <= 10) {
                                        this.createOtherMenu(player, ConstNpc.CHUC_NANG_BANG_HOI2,
                                                "Bạn có muốn Nâng cấp lên " + (player.clan.maxMember + 1) + " thành viên không?\n"
                                                + "Cần 2000 Capsule Bang\n"
                                                + "(Thu thập Capsule Bang bằng cách tiêu diệt quái tại Map Ngũ Hành Sơn \n"
                                                + "cùng các thành viên khác)", "Nâng cấp\n(20K Ruby)", "Từ chối");
                                    } else {
                                        Service.gI().sendThongBao(player, "Bang của bạn đã đạt cấp tối đa!");
                                        break;
                                    }
                                    break;
                                } else if (player.clan == null) {
                                    Service.gI().sendThongBao(player, "Yêu câu tham gia bang hội");
                                    break;
                                }
                                break;
                            case 2:
                                if (player.clan == null) {
                                    Service.gI().sendThongBao(player, "Yêu câu tham gia bang hội");
                                    break;
                                }
                                Input.gI().DonateCsbang(player);
                                break;
                          
                        }
                                     
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.CHUC_NANG_BANG_HOI2) {
                        Clan clan = player.clan;
                        switch (select) {
                            case 0:
                                if (player.clan.capsuleClan >= 2000 && clan.isLeader(player) && player.inventory.ruby >= 20000) {
                                    player.clan.level += 1;
                                    player.clan.maxMember += 1;
                                    player.clan.capsuleClan -= 2000;
                                    player.inventory.subRuby(20000);
                                    player.clan.update();
                                    Service.gI().sendThongBao(player, "Yêu cầu nâng cấp bang hội thành công");
                                    break;
                                } else if (player.inventory.ruby < 20000) {
                                    Service.gI().sendThongBaoOK(player, "Bạn còn thiều " + (20000 - player.inventory.ruby) + " Hồng Ngọc");
                                    break;
                                } else if (player.clan.capsuleClan < 1000) {
                                    Service.gI().sendThongBaoOK(player, "Bang của bạn còn thiều " + (2000 - player.clan.capsuleClan) + " Capsule bang");
                                    break;
                                }
                                             
                    }
                    }
                    }
            }
        
        };
        }    
            
    public static Npc mavuong1(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 153) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Xin chào, tôi có thể giúp gì cho cậu?", "Tây thánh địa","Chức Năng\n Bang Hội", "Gọi Boss\nBang Hội","Từ chối");
                    } else if (this.mapId == 156) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Người muốn trở về?", "Quay về", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                   } else if (this.mapId == 153) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                //về lanh dia bang hoi
                                case 0:
                                   ChangeMapService.gI().changeMapBySpaceShip(player, 156, -1, 360);
                                    break;
                            
                                
                               case 1:
                                this.createOtherMenu(player, ConstNpc.CHUC_NANG_BANG_HOI,
                                        "Ta có hỗ trợ những chức năng Bang hội, nhà ngươi cần gì?", "Giải tán\nBang Hội", "Nâng cấp\nBang Hội", "Quyên Góp\nCapsule", "Từ chối");
                             break;
                            
                             case 2:
                                   if (player.clan != null) {
                                    if (!player.clan.isLeader(player)) {
                                        Service.gI().sendThongBao(player, "Yêu cầu phải là bang chủ!");
                                    }
                                     } else if (player.clanMember.getNumDateFromJoinTimeToToday() < 2) {
                                        Service.gI().sendThongBao(player, "Yêu cầu tham gia bang hội trên 2 ngày!");
                                     }
                                BossData bossDataClone = new BossData(
                                                "Boss Bang Hội" + player.clan,
                                                ConstPlayer.TRAI_DAT, //gender
                                                  new short[]{314, 315, 316, -1, -1, -1}, //outfit {head, body, leg, bag, aura, eff}
                                                 1000, //dame
                                                  new int[]{500}, //hp
                                                new int[]{140},
                                                new int[][]{
                                                  {Skill.ANTOMIC, 7, 5000},
                                                {Skill.MASENKO, 7, 5000},
                                                 {Skill.DRAGON, 7, 3000},
                                                  {Skill.DEMON, 7, 3000},
                                                    {Skill.GALICK, 7, 3000},
                                                      },//skill
                                                new String[]{"|-2|Boss nhân bản đã xuất hiện rồi"}, //text chat 1
                                                new String[]{"|-1|Ta sẽ chiếm lấy thân xác của ngươi hahaha!"}, //text chat 2
                                                new String[]{"|-1|Lần khác ta sẽ xử đẹp ngươi"}, //text chat 3
                                                60
                                        );

                                       
                                    
                                    break;
                        }
                    } else if (this.mapId == 156) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                //về lanh dia bang hoi
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 153, -1, 432);
                                    break;
                            }
                                     } else if (player.iDMark.getIndexMenu() == ConstNpc.CHUC_NANG_BANG_HOI) {
                        switch (select) {
                            case 0:
                                Clan clan = player.clan;
                                if (clan != null) {
                                    ClanMember cm = clan.getClanMember((int) player.id);
                                    if (cm != null) {
                                        if (!clan.isLeader(player)) {
                                            Service.gI().sendThongBao(player, "Yêu cầu phải là bang chủ!");
                                            break;
                                        }
                                        if (clan.members.size() > 1) {
                                            Service.gI().sendThongBao(player, "Yêu cầu bang hội chỉ còn một thành viên!");
                                            break;
                                        }
                                        NpcService.gI().createMenuConMeo(player, ConstNpc.CONFIRM_DISSOLUTION_CLAN, -1, "Bạn có chắc chắn muốn giải tán bang hội?\n( Yêu cầu sẽ không thể hoàn tác )",
                                                "Đồng ý", "Từ chối!");
                                        break;
                                    }
                                    break;
                                }
                                Service.gI().sendThongBao(player, "Yêu câu tham gia bang hội");
                                break;
                            case 1:
                                if (player.clan != null) {
                                    if (!player.clan.isLeader(player)) {
                                        Service.gI().sendThongBao(player, "Yêu cầu phải là bang chủ!");
                                        break;
                                    }
                                    if (player.clan.level >= 0 && player.clan.level <= 10) {
                                        this.createOtherMenu(player, ConstNpc.CHUC_NANG_BANG_HOI2,
                                                "Bạn có muốn Nâng cấp lên " + (player.clan.maxMember + 1) + " thành viên không?\n"
                                                + "Cần 2000 Capsule Bang\n"
                                                + "(Thu thập Capsule Bang bằng cách tiêu diệt quái tại Map Ngũ Hành Sơn \n"
                                                + "cùng các thành viên khác)", "Nâng cấp\n(20K Ruby)", "Từ chối");
                                    } else {
                                        Service.gI().sendThongBao(player, "Bang của bạn đã đạt cấp tối đa!");
                                        break;
                                    }
                                    break;
                                } else if (player.clan == null) {
                                    Service.gI().sendThongBao(player, "Yêu câu tham gia bang hội");
                                    break;
                                }
                                break;
                            case 2:
                                if (player.clan == null) {
                                    Service.gI().sendThongBao(player, "Yêu câu tham gia bang hội");
                                    break;
                                }
                                Input.gI().DonateCsbang(player);
                                break;
                          
                        }
                    
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.CHUC_NANG_BANG_HOI2) {
                        Clan clan = player.clan;
                        switch (select) {
                            case 0:
                                if (player.clan.capsuleClan >= 2000 && clan.isLeader(player) && player.inventory.ruby >= 20000) {
                                    player.clan.level += 1;
                                    player.clan.maxMember += 1;
                                    player.clan.capsuleClan -= 2000;
                                    player.inventory.subRuby(20000);
                                    player.clan.update();
                                    Service.gI().sendThongBao(player, "Yêu cầu nâng cấp bang hội thành công");
                                    break;
                                } else if (player.inventory.ruby < 20000) {
                                    Service.gI().sendThongBaoOK(player, "Bạn còn thiều " + (20000 - player.inventory.ruby) + " Hồng Ngọc");
                                    break;
                                } else if (player.clan.capsuleClan < 1000) {
                                    Service.gI().sendThongBaoOK(player, "Bang của bạn còn thiều " + (2000 - player.clan.capsuleClan) + " Capsule bang");
                                    break;
                                }
                                             
                    }}
                        }
                    }
                }
        
        };
    }

    public static Npc gokuSSJ_2(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                new Thread(() -> {
                    try {
                        while (true) {
                            Thread.sleep(5000);
                            new Thread(() -> {
                                try {
                                    Thread.sleep(3000);
                                    this.npcChat(player, "Up Yardart Đi Em");
                                } catch (Exception e) {
                                }
                            }).start();
                        }
                    } catch (Exception e) {
                    }
                }).start();
                if (canOpenNpc(player)) {
                    {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Bạn đang có 0 bí kiếp.\n"
                                + "Hãy kiếm đủ 10000 bí kiếp tôi sẽ dạy bạn cách dịch chuyển tức thời của người Yardart\nBí kiếp kiếm tại hành tinh Yadart",
                                "Học dịch\nchuyển", "Đóng");
                    }
                    try {
                        Item biKiep = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 590);
                        if (biKiep != null) {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Bạn đang có " + biKiep.quantity
                                    + " bí kiếp.\n"
                                    + "Hãy kiếm đủ 10000 bí kiếp tôi sẽ dạy bạn cách dịch chuyển tức thời của người Yardart\nBí kiếp kiếm tại hành tinh Yadart",
                                    "Học dịch\nchuyển", "Đóng");
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();

                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    try {

                        Item biKiep = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 590);
                        if (biKiep != null) {
                            if (biKiep.quantity >= 10000 && InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                Item yardart = ItemService.gI().createNewItem((short) (player.gender + 592));
                                yardart.itemOptions.add(new Item.ItemOption(47, 400));
                                yardart.itemOptions.add(new Item.ItemOption(108, 10));
                                InventoryServiceNew.gI().addItemBag(player, yardart);
                                InventoryServiceNew.gI().subQuantityItemsBag(player, biKiep, 10000);
                                InventoryServiceNew.gI().sendItemBags(player);
                                Service.gI().sendThongBao(player, "Bạn vừa nhận được trang phục tộc Yardart");
                            }
                        }
                    } catch (Exception ex) {

                    }
                }
            }
        };
    }

    public static Npc GhiDanh(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            String[] menuselect = new String[]{};

            @Override
            public void openBaseMenu(Player pl) {
                if (canOpenNpc(pl)) {
                    if (this.map.mapId == 52) {
                        if (DaiHoiManager.gI().openDHVT && (System.currentTimeMillis() <= DaiHoiManager.gI().tOpenDHVT)) {
                            String nameDH = DaiHoiManager.gI().nameRoundDHVT();
                            this.createOtherMenu(pl, ConstNpc.MENU_DHVT, "Hiện đang có giải đấu " + nameDH + " bạn có muốn đăng ký không? \nSố người đã đăng ký :" + DaiHoiManager.gI().lstIDPlayers.size(), new String[]{"Giải\n" + nameDH + "\n(" + DaiHoiManager.gI().costRoundDHVT() + ")", "Từ chối", "Đại Hội\nVõ Thuật\nLần thứ\n23", "Giải siêu hạng"});
                        } else {
                            this.createOtherMenu(pl, ConstNpc.BASE_MENU, "Đã hết hạn đăng ký thi đấu, xin vui lòng chờ đến giải sau", new String[]{"Thông tin\bChi tiết", "OK", "Đại Hội\nVõ Thuật\nLần thứ\n23", "Giải siêu hạng\n"});
                        }
                    } else if (this.mapId == 129) {
                        int goldchallenge = pl.goldChallenge;
                        if (pl.levelWoodChest == 0) {
                            menuselect = new String[]{"Thi đấu\n" + Util.numberToMoney(goldchallenge) + " vàng", "Về\nĐại Hội\nVõ Thuật"};
                        } else {
                            menuselect = new String[]{"Thi đấu\n" + Util.numberToMoney(goldchallenge) + " vàng", "Nhận thưởng\nRương cấp\n" + pl.levelWoodChest, "Về\nĐại Hội\nVõ Thuật"};
                        }
                        this.createOtherMenu(pl, ConstNpc.BASE_MENU, "Đại hội võ thuật lần thứ 23\nDiễn ra bất kể ngày đêm,ngày nghỉ ngày lễ\nPhần thưởng vô cùng quý giá\nNhanh chóng tham gia nào", menuselect, "Từ chối");

                    } else {
                        super.openBaseMenu(pl);
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.map.mapId == 52) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    Service.getInstance().sendThongBaoFromAdmin(player, "Lịch thi đấu trong ngày\bGiải Nhi đồng: 8,13,18h\bGiải Siêu cấp 1: 9,14,19h\bGiải Siêu cấp 2: 10,15,20h\bGiải Siêu cấp 3: 11,16,21h\bGiải Ngoại hạng: 12,17,22,23h\nGiải thưởng khi thắng mỗi vòng\bGiải Nhi đồng: 2 ngọc\bGiải Siêu cấp 1: 4 ngọc\bGiải Siêu cấp 2: 6 ngọc\bGiải Siêu cấp 3: 8 ngọc\bGiải Ngoại hạng: 10.000 vàng\bVô địch: 5 viên đá nâng cấp\nVui lòng đến đúng giờ để đăng ký thi đấu");
                                    break;
                                case 1:
                                    Service.getInstance().sendThongBaoFromAdmin(player, "Nhớ Đến Đúng Giờ nhé");
                                    break;
                                case 2:
                                    ChangeMapService.gI().changeMapNonSpaceship(player, 129, player.location.x, 360);
                                    break;
                                case 3:
                                    ChangeMapService.gI().changeMapNonSpaceship(player, 113, player.location.x, 360);
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_DHVT) {
                            switch (select) {
                                case 0:
                                    if (DaiHoiManager.gI().lstIDPlayers.size() < 256) {
                                        if (DaiHoiManager.gI().typeDHVT == (byte) 5 && player.inventory.gold >= 10000) {
                                            if (DaiHoiManager.gI().isAssignDHVT(player.id)) {
                                                Service.getInstance().sendThongBao(player, "Bạn đã đăng ký tham gia đại hội võ thuật rồi");
                                            } else {
                                                player.inventory.gold -= 10000;
                                                Service.getInstance().sendMoney(player);
                                                Service.getInstance().sendThongBao(player, "Bạn đã đăng ký thành công, nhớ có mặt tại đây trước giờ thi đấu");
                                                DaiHoiManager.gI().lstIDPlayers.add(player.id);
                                            }
                                        } else if (DaiHoiManager.gI().typeDHVT > (byte) 0 && DaiHoiManager.gI().typeDHVT < (byte) 5 && player.inventory.gem >= (int) (2 * DaiHoiManager.gI().typeDHVT)) {
                                            if (DaiHoiManager.gI().isAssignDHVT(player.id)) {
                                                Service.getInstance().sendThongBao(player, "Bạn đã đăng ký tham gia đại hội võ thuật rồi");
                                            } else {
                                                player.inventory.gem -= (int) (2 * DaiHoiManager.gI().typeDHVT);
                                                Service.getInstance().sendMoney(player);
                                                Service.getInstance().sendThongBao(player, "Bạn đã đăng ký thành công, nhớ có mặt tại đây trước giờ thi đấu");
                                                DaiHoiManager.gI().lstIDPlayers.add(player.id);
                                            }
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Không đủ vàng ngọc để đăng ký thi đấu");
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Hiện tại đã đạt tới số lượng người đăng ký tối đa, xin hãy chờ đến giải sau");
                                    }
                                    break;
                                case 1:
                                    break;
                                case 2:
                                    ChangeMapService.gI().changeMapNonSpaceship(player, 129, player.location.x, 360);
                                    break;
                            }
                        }
                    } else if (this.mapId == 129) {
                        int goldchallenge = player.goldChallenge;
                        if (player.levelWoodChest == 0) {
                            switch (select) {
                                case 0:
                                    if (InventoryServiceNew.gI().finditemWoodChest(player)) {
                                        if (player.inventory.gold >= goldchallenge) {
                                            MartialCongressService.gI().startChallenge(player);
                                            player.inventory.gold -= (goldchallenge);
                                            PlayerService.gI().sendInfoHpMpMoney(player);
                                            player.goldChallenge += 2000000;
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Không đủ vàng, còn thiếu " + Util.numberToMoney(goldchallenge - player.inventory.gold) + " vàng");
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Hãy mở rương báu vật trước");
                                    }
                                    break;
                                case 1:
                                    ChangeMapService.gI().changeMapNonSpaceship(player, 52, player.location.x, 336);
                                    break;
                            }
                        } else {
                            switch (select) {
                                case 0:
                                    if (InventoryServiceNew.gI().finditemWoodChest(player)) {
                                        if (player.inventory.gold >= goldchallenge) {
                                            MartialCongressService.gI().startChallenge(player);
                                            player.inventory.gold -= (goldchallenge);
                                            PlayerService.gI().sendInfoHpMpMoney(player);
                                            player.goldChallenge += 2000000;
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Không đủ vàng, còn thiếu " + Util.numberToMoney(goldchallenge - player.inventory.gold) + " vàng");
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Hãy mở rương báu vật trước");
                                    }
                                    break;
                                case 1:
                                    if (!player.receivedWoodChest) {
                                        if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                            Item it = ItemService.gI().createNewItem((short) 570);
                                            it.itemOptions.add(new Item.ItemOption(72, player.levelWoodChest));
                                            it.itemOptions.add(new Item.ItemOption(30, 0));
                                            it.createTime = System.currentTimeMillis();
                                            InventoryServiceNew.gI().addItemBag(player, it);
                                            InventoryServiceNew.gI().sendItemBags(player);

                                            player.receivedWoodChest = true;
                                            player.levelWoodChest = 0;
                                            Service.getInstance().sendThongBao(player, "Bạn nhận được rương gỗ");
                                        } else {
                                            this.npcChat(player, "Hành trang đã đầy");
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Mỗi ngày chỉ có thể nhận rương báu 1 lần");
                                    }
                                    break;
                                case 2:
                                    ChangeMapService.gI().changeMapNonSpaceship(player, 52, player.location.x, 336);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc Vodai(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            String[] menuselect = new String[]{};

            @Override
            public void openBaseMenu(Player pl) {
                if (canOpenNpc(pl)) {
                    if (this.mapId == 5) {
                        this.createOtherMenu(pl, ConstNpc.BASE_MENU, "|1|Ta Có Thể Giúp Gì Cho Ngươi?",
                                "Đến Võ Đài Bà Hạt Mít", "Từ chối");
                    } else if (this.mapId == 112) {
                        int goldchallenge = pl.goldChallenge;
                        if (pl.levelWoodChest == 0) {
                            menuselect = new String[]{"Thi đấu\n" + Util.numberToMoney(goldchallenge) + " vàng", "Về Đảo Kame"};
                        } else {
                            menuselect = new String[]{"Thi đấu\n" + Util.numberToMoney(goldchallenge) + " vàng", "Nhận thưởng\nRương cấp\n" + pl.levelWoodChest, "Về Đảo Kame"};
                        }
                        this.createOtherMenu(pl, ConstNpc.BASE_MENU, "Võ Đài Bà Hạt Mít\nDiễn ra bất kể ngày đêm,ngày nghỉ ngày lễ\nPhần thưởng vô cùng quý giá\nNhanh chóng tham gia nào", menuselect, "Từ chối");

                    } else {
                        super.openBaseMenu(pl);
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.map.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapNonSpaceship(player, 112, 203, 408);
                                    break;
                            }
                        }
                    } else if (this.mapId == 112) {
                        int goldchallenge = player.goldChallenge;
                        if (player.levelWoodChest == 0) {
                            switch (select) {
                                case 0:
                                    if (InventoryServiceNew.gI().finditemWoodChest(player)) {
                                        if (player.inventory.gold >= goldchallenge) {
                                            MartialCongressServices.gI().startChallenge(player);
                                            player.inventory.gold -= (goldchallenge);
                                            PlayerService.gI().sendInfoHpMpMoney(player);
                                            player.goldChallenge += 2000000;
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Không đủ vàng, còn thiếu " + Util.numberToMoney(goldchallenge - player.inventory.gold) + " vàng");
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Hãy mở rương báu vật trước");
                                    }
                                    break;
                                case 1:
                                    ChangeMapService.gI().changeMap(player, 5, -1, 450, 288);
                                    break;
                            }
                        } else {
                            switch (select) {
                                case 0:
                                    if (InventoryServiceNew.gI().finditemWoodChest(player)) {
                                        if (player.inventory.gold >= goldchallenge) {
                                            MartialCongressServices.gI().startChallenge(player);
                                            player.inventory.gold -= (goldchallenge);
                                            PlayerService.gI().sendInfoHpMpMoney(player);
                                            player.goldChallenge += 2000000;
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Không đủ vàng, còn thiếu " + Util.numberToMoney(goldchallenge - player.inventory.gold) + " vàng");
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Hãy mở rương báu vật trước");
                                    }
                                    break;
                                case 1:
                                    if (!player.receivedWoodChest) {
                                        if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                            Item it = ItemService.gI().createNewItem((short) 1526);
                                            it.itemOptions.add(new Item.ItemOption(72, player.levelWoodChest));
                                            it.itemOptions.add(new Item.ItemOption(30, 0));
                                            it.createTime = System.currentTimeMillis();
                                            InventoryServiceNew.gI().addItemBag(player, it);
                                            InventoryServiceNew.gI().sendItemBags(player);
                                            player.receivedWoodChest = true;
                                            player.levelWoodChest = 0;
                                            Service.getInstance().sendThongBao(player, "Bạn nhận được rương ngọc rồng");
                                        } else {
                                            this.npcChat(player, "Hành trang đã đầy");
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Mỗi ngày chỉ có thể nhận rương báu 1 lần");
                                    }
                                    break;
                                case 2:
                                    ChangeMapService.gI().changeMap(player, 5, -1, 1030, 408);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc unkonw(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        this.createOtherMenu(player, 0,
                                "Éc éc Bạn muốn gì ở tôi :3?", "Đến Võ đài Unknow", "Võ Đài Siêu Cấp");

                    }
                    if (this.mapId == 112) {
                        this.createOtherMenu(player, 0,
                                "Bạn đang còn : " + player.pointPvp + " điểm PvP Point", "Về đảo Kame", "Đổi Cải trang sự kiên", "Top PVP");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.getIndexMenu() == 0) { // 
                            switch (select) {
                                case 0:
                                    if (player.getSession().player.nPoint.power >= 10000000000L) {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 112, -1, 495);
                                        Service.gI().changeFlag(player, Util.nextInt(8));
                                    } else {
                                        this.npcChat(player, "Bạn cần 10 tỷ sức mạnh mới có thể vào");
                                    }
                                    break; // qua vo dai
                                case 1:
                                    if (player.getSession().player.nPoint.power >= 10000000000L) {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 145, -1, 495);
                                        Service.gI().changeFlag(player, Util.nextInt(8));
                                    } else {
                                        this.npcChat(player, "Bạn cần 10 tỷ sức mạnh mới có thể vào");
                                    }
                                    break; // qua vo dai

                            }
                        }
                    }

                    if (this.mapId == 112) {
                        if (player.iDMark.getIndexMenu() == 0) { // 
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 5, -1, 319);
                                    break; // ve dao kame
                                case 1:  // 
                                    this.createOtherMenu(player, 1,
                                            "Bạn có muốn đổi 500 điểm PVP lấy \n|6|Cải trang Mèo Kid Lân với tất cả chỉ số là 80%\n ", "Ok", "Không");
                                    // bat menu doi item
                                    break;

                                case 2:  // 
                                    Service.gI().showListTop(player, Manager.topPVP);
                                    // mo top pvp
                                    break;

                            }
                        }
                        if (player.iDMark.getIndexMenu() == 1) { // action doi item
                            switch (select) {
                                case 0: // trade
                                    if (player.pointPvp >= 500) {
                                        player.pointPvp -= 500;
                                        Item item = ItemService.gI().createNewItem((short) (1104));
                                        item.itemOptions.add(new Item.ItemOption(49, 30));
                                        item.itemOptions.add(new Item.ItemOption(77, 15));
                                        item.itemOptions.add(new Item.ItemOption(103, 20));
                                        item.itemOptions.add(new Item.ItemOption(207, 0));
                                        item.itemOptions.add(new Item.ItemOption(33, 0));
//                                      
                                        InventoryServiceNew.gI().addItemBag(player, item);
                                        Service.gI().sendThongBao(player, "Chúc Mừng Bạn Đổi Cải Trang Thành Công !");
                                    } else {
                                        Service.gI().sendThongBao(player, "Không đủ điểm bạn còn " + (500 - player.pointPvp) + " Điểm nữa");
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    private static Npc popo(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        if (player.getSession().is_gift_box) {
//                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Chào con, con muốn ta giúp gì nào?", "Giải tán bang hội", "Nhận quà\nđền bù");
                        } else {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Thượng đế vừa phát hiện 1 loại khí đang âm thầm\nhủy diệt mọi mầm sống trên Trái Đất,\nnó được gọi là Destron Gas.\nTa sẽ đưa các cậu đến nơi ấy, các cậu sẵn sàng chưa?", "Thông Tin Chi Tiết", "OK", "Từ Chối");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 1:
                                if (player.clan != null) {
                                    if (player.clan.khiGas != null) {
                                        this.createOtherMenu(player, ConstNpc.MENU_OPENED_GAS,
                                                "Bang hội của con đang đi DesTroy Gas cấp độ "
                                                + player.clan.khiGas.level + "\nCon có muốn đi theo không?",
                                                "Đồng ý", "Từ chối");
                                    } else if (player.clan.haveGoneGas) {
                                        createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                                "Bang hội của ngươi đã đi Khí gas hủy diệt lúc " + TimeUtil.formatTime(player.clan.timeOpenKhiGas, "HH:mm:ss") + " hôm nay. Người mở\n"
                                                + "(" + player.clan.playerOpenKhiGas.name + "). Hẹn ngươi quay lại vào ngày mai", "OK", "Hướng\ndẫn\nthêm");
                                        return;
                                    } else {
                                        this.createOtherMenu(player, ConstNpc.MENU_OPENED_GAS,
                                                "Khí Gas Huỷ Diệt đã chuẩn bị tiếp nhận các đợt tấn công của quái vật\n"
                                                + "các con hãy giúp chúng ta tiêu diệt quái vật \n"
                                                + "Ở đây có ta lo\nNhớ chọn cấp độ vừa sức mình nhé",
                                                "Chọn\ncấp độ", "Từ chối");
                                    }
                                } else {
                                    this.npcChat(player, "Con phải có bang hội ta mới có thể cho con đi");
                                }
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPENED_GAS) {
                        switch (select) {
                            case 0:
                                if (player.isAdmin() || player.nPoint.power >= Gas.POWER_CAN_GO_TO_GAS) {
                                    ChangeMapService.gI().goToGas(player);
                                } else if (player.clanMember.getNumDateFromJoinTimeToToday() < 2) {
                                    Service.gI().sendThongBao(player, "Yêu cầu tham gia bang hội trên 2 ngày!");
                                    return;
                                } else {
                                    this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                            + Util.numberToMoney(Gas.POWER_CAN_GO_TO_GAS));
                                }
                                break;

                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPEN_GAS) {
                        switch (select) {
                            case 0:

                                if (player.clan.haveGoneGas) {
                                    createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                            "Bang hội của ngươi đã đi con đường rắn độc lúc " + TimeUtil.formatTime(player.clan.lastTimeOpenConDuongRanDoc, "HH:mm:ss") + " hôm nay. Người mở\n"
                                            + "(" + player.clan.playerOpenConDuongRanDoc.name + "). Hẹn ngươi quay lại vào ngày mai", "OK", "Hướng\ndẫn\nthêm");
                                    return;
                                } else if (player.nPoint.power < Gas.POWER_CAN_GO_TO_GAS) {
                                    this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                            + Util.numberToMoney(ConDuongRanDoc.POWER_CAN_GO_TO_CDRD));
                                    return;
//                                    } else if (player.clanMember.getNumDateFromJoinTimeToToday() < 2) {
//                                        Service.gI().sendThongBao(player, "Yêu cầu tham gia bang hội trên 2 ngày!");
//                                        return;
                                } else {
                                    Input.gI().createFormChooseLevelGas(player);
                                }
                                break;
                        }

                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_ACCPET_GO_TO_GAS) {
                        switch (select) {
                            case 0:
                                GasService.gI().openBanDoKhoBau(player, Integer.parseInt(String.valueOf(PLAYERID_OBJECT.get(player.id))));
                                break;
                        }
                    }
                }
            }
        };
    }

    public static Npc granala(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {

                    if (this.mapId == 171) {
                        this.createOtherMenu(player, 0,
                                "Ngươi!\n Hãy cầm đủ 7 viên ngọc rồng \n Monaito đến đây gặp ta ta sẽ ban cho ngươi\n 1 điều ước ", "Gọi rồng", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {

                    if (this.mapId == 171) {
                        if (player.iDMark.getIndexMenu() == 0) { // 
                            switch (select) {
                                case 0:
                                    this.npcChat(player, "Chức Năng Đang Được Update!");
                                    break; // goi rong

                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc vihop(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Xin chào, ta có một số vật phẩm đặt biệt cậu có muốn xem không?",
                            "Đổi Đệ Vip", "Pet Vip");
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    createOtherMenu(player, ConstNpc.BUY_DE_TU_VIP,
                                            "Xin chào, ta có một số bé pet vip nè!\n|1|Số tiền của bạn còn : " + player.getSession().vnd
                                            + "\n|7|Đệ Thiên Tử: 30k coin, Hợp thể tăng 30% chỉ số"
                                            + "\n|7|Đệ Black Goku: 40k coin, Hợp thể tăng 40% chỉ số"
                                            + "\n|7|Đệ Kaido: 50k coin, Hợp thể tăng 50% chỉ số"
                                            + "\n|7| Lưu Ý Phải Có Đệ Thường Mới Mua Được Đệ Vip",
                                            "Thiên Tử", "Black Goku", "Kaido");
                                    break;

                                case 1:
                                    this.createOtherMenu(player, ConstNpc.BUY_PET_VIP, "|7|Số tiền của bạn còn : " + player.getSession().vnd + "\n"
                                            + "Pet Vip 50k 40% chỉ số all\n"
                                            + "Muốn mua không", "Lụm", "Thôi địt mẹ đắt vãilon");
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.BUY_DE_TU_VIP) {
                            PreparedStatement ps = null;
                            try (Connection con = GirlkunDB.getConnection();) {
                                switch (select) {
                                    case 0:
                                        if (player.getSession().vnd < 30000) {
                                            Service.gI().sendThongBao(player, "Bạn không có đủ 30k coin");
                                            return;
                                        }
                                        if (player.pet == null) {
                                            Service.gI().sendThongBao(player, "Bạn cần phải có đệ tử thường trước");
                                            return;
                                        }

                                        player.getSession().vnd -= 30000;
                                        PetService.gI().createThienTuPetVip(player, true, player.pet.gender);
                                        break;
                                    case 1:
                                        if (player.getSession().vnd < 40000) {
                                            Service.gI().sendThongBao(player, "Bạn không có đủ 30k coin");
                                            return;
                                        }
                                        if (player.pet == null) {
                                            Service.gI().sendThongBao(player, "Bạn cần phải có đệ tử thường trước");
                                            return;
                                        }

                                        player.getSession().vnd -= 40000;
                                        PetService.gI().createBlackGokuPetVip(player, true, player.pet.gender);
                                        break;
                                    case 2:
                                        if (player.getSession().vnd < 50000) {
                                            Service.gI().sendThongBao(player, "Bạn không có đủ 50k coin");
                                            return;
                                        }
                                        if (player.pet == null) {
                                            Service.gI().sendThongBao(player, "Bạn cần phải có đệ tử thường trước");
                                            return;
                                        }

                                        player.getSession().vnd -= 50000;
                                        PetService.gI().createKaidoPetVip(player, player.pet != null, player.pet.gender);
                                        break;
                                }

                                ps = con.prepareStatement("update account set coin = ? where id = ?");
                                ps.setInt(1, player.getSession().vnd);
                                ps.setInt(2, player.getSession().userId);
                                ps.executeUpdate();
                                ps.close();

                            } catch (Exception e) {
                                Logger.logException(NpcFactory.class, e, "Lỗi update coin " + player.name);
                            } finally {
                                try {
                                    if (ps != null) {
                                        ps.close();
                                    }
                                } catch (SQLException ex) {
                                    System.out.println("Lỗi khi update coin");

                                }
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.BUY_PET_VIP) {
                            PreparedStatement ps = null;
                            try (Connection con = GirlkunDB.getConnection();) {
                                switch (select) {
                                    case 0:
                                        Item petvip = ItemService.gI().createNewItem((short) (1434));
                                        if (player.getSession().vnd < 50000) {
                                            Service.gI().sendThongBao(player, "Bạn không có đủ 50k coin");
                                            return;
                                        }
                                        player.getSession().vnd -= 50000;
                                        InventoryServiceNew.gI().addItemBag(player, petvip);
                                        Service.gI().sendThongBao(player, "Bạn Nhận Được 1 " + petvip.template.name + " Nhớ out game vô lại");
                                        break;
                                }

                                ps = con.prepareStatement("update account set coin = ? where id = ?");
                                ps.setInt(1, player.getSession().vnd);
                                ps.setInt(2, player.getSession().userId);
                                ps.executeUpdate();
                                ps.close();

                            } catch (Exception e) {
                                Logger.logException(NpcFactory.class, e, "Lỗi update coin " + player.name);
                            } finally {
                                try {
                                    if (ps != null) {
                                        ps.close();
                                    }
                                } catch (SQLException ex) {
                                    System.out.println("Lỗi khi update coin");

                                }
                            }
                        }

                    }
                }
            }
        };
    }

    public static Npc vip_truongchimto(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 181) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Ngươi tìm ta có việc gì?",
                                "Thức Tỉnh");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 181) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.THUC_TINH_DT);
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                            switch (player.combineNew.typeCombine) {
                                case CombineServiceNew.THUC_TINH_DT:
                                    switch (select) {
                                        case 0:
                                            CombineServiceNew.gI().thuctinhDT(player, 1);
                                            System.out.print("test");
                                            break;
                                        case 1:
                                            CombineServiceNew.gI().thuctinhDT(player, 10);
                                            break;
                                        case 2:
                                            CombineServiceNew.gI().thuctinhDT(player, 100);
                                            break;
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    private static Npc TrongTai(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 113) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Đại hội võ thuật Siêu Hạng\n\ndiễn ra 24/7 kể cả ngày lễ và chủ nhật\nHãy thi đấu để khẳng định đẳng cấp của mình nhé", "Top 100\nCao thủ\n", "Hướng\ndẫn\nthêm", "Đấu ngay\n", "Về\nĐại Hội\nVõ Thuật");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 113) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    try (Connection con = GirlkunDB.getConnection()) {
                                        Manager.topSieuHang = Manager.realTopSieuHang(con);
                                    } catch (Exception ignored) {
                                        Logger.error("Lỗi đọc top");
                                    }
                                    Service.gI().showListTop(player, Manager.topSieuHang, (byte) 1);
                                    break;
                                case 2:
                                    List<TOP> tops = new ArrayList<>();
                                    tops.addAll(Manager.realTopSieuHang(player));
                                    Service.gI().showListTop(player, tops, (byte) 1);
                                    tops.clear();
                                    break;
                                case 3:
                                    ChangeMapService.gI().changeMapNonSpaceship(player, 52, -1, 432);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc createNPC(int mapId, int status, int cx, int cy, int tempId) {
        int avatar = Manager.NPC_TEMPLATES.get(tempId).avatar;
        try {
            switch (tempId) {
                case ConstNpc.UNKOWN:
                    return unkonw(mapId, status, cx, cy, tempId, avatar);
//                case ConstNpc.BARDOCK:
//                    return bardock(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.VIHOP:
                    return vihop(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.GHI_DANH:
                    return GhiDanh(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.POTAGE:
                    return poTaGe(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.QUY_LAO_KAME:
                    return quyLaoKame(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.TRUONG_LAO_GURU:
                    return truongLaoGuru(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.VUA_VEGETA:
                    return vuaVegeta(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.ONG_GOHAN:
                case ConstNpc.ONG_MOORI:
                case ConstNpc.ONG_PARAGUS:
                    return ongGohan_ongMoori_ongParagus(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.BUNMA:
                    return bulmaQK(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.DENDE:
                    return dende(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.APPULE:
                    return appule(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.DR_DRIEF:
                    return drDrief(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.CARGO:
                    return cargo(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.CUI:
                    return cui(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.SANTA:
                    return santa(mapId, status, cx, cy, tempId, avatar);
                     case ConstNpc.minuong:
                    return zombie(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.URON:
                    return uron(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.BA_HAT_MIT:
                    return baHatMit(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.NOI_BANH:
                    return noibanh(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.CANCAU:
                    return cauca(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.MR_POPO:
                    return popo(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.GAPTHU1:
                    return gapthu(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.CAYCHUOI:
                    return caychuoi(mapId, status, cx, cy, tempId, avatar);
              
                case ConstNpc.CAYTHONG:
                    return caythong(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.BILL_BI_NGO:
                    return billbingo(mapId, status, cx, cy, tempId, avatar);
                
                case ConstNpc.RUONG_DO:
                    return ruongDo(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.DAU_THAN:
                    return dauThan(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.CALICK:
                    return calick(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.JACO:
                    return jaco(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.THUONG_DE:
                    return thuongDe(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.CUA_HANG_KY_GUI:
                    return kyGui(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.Granola:
                    return granala(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.GIUMA_DAU_BO:
                    return mavuong(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.VADOS:
                    return vados(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.THAN_VU_TRU:
                    return thanVuTru(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.KIBIT:
                    return kibit(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.OSIN:
                    return osin(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.LY_TIEU_NUONG:
                    return npclytieunuong54(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.LINH_CANH:
                    return linhCanh(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.DOC_NHAN:
                    return docNhan(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.QUA_TRUNG:
                    return quaTrung(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.QUOC_VUONG:
                    return quocVuong(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.BUNMA_TL:
                    return bulmaTL(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.RONG_OMEGA:
                    return rongOmega(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.RONG_1S:
                case ConstNpc.RONG_2S:
                case ConstNpc.RONG_3S:
                case ConstNpc.RONG_4S:
                case ConstNpc.RONG_5S:
                case ConstNpc.RONG_6S:
                case ConstNpc.RONG_7S:
                    return rong1_to_7s(mapId, status, cx, cy, tempId, avatar);
                
                case ConstNpc.NPC_64:
                    return npcThienSu64(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.WHIS:
                    return Whis(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.BILL:
                    return bill(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.BO_MONG:
                    return boMong(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.THAN_MEO_KARIN:
                    return karin(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.GOKU_SSJ:
                    return gokuSSJ_1(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.GOKU_SSJ_:
                    return gokuSSJ_2(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.DUONG_TANG:
                    return duongtank(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.KA:
                    return ka(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.HAHA:
                    return nvt(mapId, status, cx, cy, tempId, avatar);
//                case ConstNpc.BKT:
//                    return haha(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.TRONG_TAI:
                    return TrongTai(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.DUAHAU:
                    return DuaHau(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.VODAI:
                    return Vodai(mapId, status, cx, cy, tempId, avatar);
                default:
                    return new Npc(mapId, status, cx, cy, tempId, avatar) {
                        @Override
                        public void openBaseMenu(Player player) {
                            if (canOpenNpc(player)) {
                                super.openBaseMenu(player);
                            }
                        }

                        @Override
                        public void confirmMenu(Player player, int select) {
                            if (canOpenNpc(player)) {
//                                ShopService.gI().openShopNormal(player, this, ConstNpc.SHOP_BUNMA_TL_0, 0, player.gender);
                            }
                        }
                    };

            }
        } catch (Exception e) {
            Logger.logException(NpcFactory.class, e, "Lỗi load npc");

            return null;
        }
    }

    //girlbeo-mark
    public static void createNpcRongThieng() {
        Npc npc = new Npc(-1, -1, -1, -1, ConstNpc.RONG_THIENG, -1) {
            @Override
            public void confirmMenu(Player player, int select) {
                switch (player.iDMark.getIndexMenu()) {
                    case ConstNpc.IGNORE_MENU:

                        break;
                    case ConstNpc.SHENRON_CONFIRM:
                        if (select == 0) {
                            SummonDragon.gI().confirmWish();
                        } else if (select == 1) {
                            SummonDragon.gI().reOpenShenronWishes(player);
                        }
                        break;
                    case ConstNpc.SHENRON_1_1:
                        if (player.iDMark.getIndexMenu() == ConstNpc.SHENRON_1_1 && select == SHENRON_1_STAR_WISHES_1.length - 1) {
                            NpcService.gI().createMenuRongThieng(player, ConstNpc.SHENRON_1_2, SHENRON_SAY, SHENRON_1_STAR_WISHES_2);
                            break;
                        }
                    case ConstNpc.SHENRON_1_2:
                        if (player.iDMark.getIndexMenu() == ConstNpc.SHENRON_1_2 && select == SHENRON_1_STAR_WISHES_2.length - 1) {
                            NpcService.gI().createMenuRongThieng(player, ConstNpc.SHENRON_1_1, SHENRON_SAY, SHENRON_1_STAR_WISHES_1);
                            break;
                        }
                    default:
                        SummonDragon.gI().showConfirmShenron(player, player.iDMark.getIndexMenu(), (byte) select);
                        break;
                }
            }
        };
    }

    public static void createNpcConMeo() {
        Npc npc = new Npc(-1, -1, -1, -1, ConstNpc.CON_MEO, 351) {
            @Override
            public void confirmMenu(Player player, int select) {
                switch (player.iDMark.getIndexMenu()) {
                    case ConstNpc.IGNORE_MENU:

                        break;
                    case ConstNpc.MAKE_MATCH_PVP: //                        if (player.getSession().actived) 
                    {
                        if (Maintenance.isRuning) {
                            break;
                        }
                        PVPService.gI().sendInvitePVP(player, (byte) select);
                        break;
                    }
//                        else {
//                            Service.gI().sendThongBao(player, "|5|VUI LÒNG KÍCH HOẠT TÀI KHOẢN TẠI\n|7|NROGOD.COM\n|5|ĐỂ MỞ KHÓA TÍNH NĂNG");
//                            break;
//                        }
                    case ConstNpc.MAKE_FRIEND:
                        if (select == 0) {
                            Object playerId = PLAYERID_OBJECT.get(player.id);
                            if (playerId != null) {
                                FriendAndEnemyService.gI().acceptMakeFriend(player,
                                        Integer.parseInt(String.valueOf(playerId)));
                            }
                        }
                        break;
                    case ConstNpc.REVENGE:
                        if (select == 0) {
                            PVPService.gI().acceptRevenge(player);
                        }
                        break;
                    case ConstNpc.TUTORIAL_SUMMON_DRAGON:
                        if (select == 0) {
                            NpcService.gI().createTutorial(player, -1, SummonDragon.SUMMON_SHENRON_TUTORIAL);
                        }
                        break;
                    case ConstNpc.SUMMON_SHENRON:
                        if (select == 0) {
                            NpcService.gI().createTutorial(player, -1, SummonDragon.SUMMON_SHENRON_TUTORIAL);
                        } else if (select == 1) {
                            SummonDragon.gI().summonShenron(player);
                        }
                        break;
                    case ConstNpc.TUTORIAL_SUMMON_DRAGONTRB://TRB
                        if (select == 0) {
                            NpcService.gI().createTutorial(player, -1, SummonDragon.SUMMON_SHENRON_TRB);
                        }
                        break;
                    case ConstNpc.SUMMON_SHENRONTRB:
                        if (select == 0) {
                            NpcService.gI().createTutorial(player, -1, SummonDragon.SUMMON_SHENRON_TRB);
                        } else if (select == 1) {
                            SummonDragon.gI().summonShenronTRB(player);
                        }
                        break;
                    case ConstNpc.MENU_OPTION_USE_ITEM1105:
                        if (select == 0) {
                            IntrinsicService.gI().sattd(player);
                        } else if (select == 1) {
                            IntrinsicService.gI().satnm(player);
                        } else if (select == 2) {
                            IntrinsicService.gI().setxd(player);
                        }
                        break;
                    case ConstNpc.MENU_OPTION_USE_ITEM2000:
                    case ConstNpc.MENU_OPTION_USE_ITEM2001:
                    case ConstNpc.MENU_OPTION_USE_ITEM2002:
                        try {
                            ItemService.gI().OpenSKH(player, player.iDMark.getIndexMenu(), select);
                        } catch (Exception e) {
                            Logger.error("Lỗi mở hộp quà");
                        }
                        break;
                    case ConstNpc.MENU_OPTION_USE_ITEM2003:
                    case ConstNpc.MENU_OPTION_USE_ITEM2004:
                    case ConstNpc.MENU_OPTION_USE_ITEM2005:
                        try {
                            ItemService.gI().OpenDHD(player, player.iDMark.getIndexMenu(), select);
                        } catch (Exception e) {
                            Logger.error("Lỗi mở hộp quà");
                        }
                        break;
                    case ConstNpc.MENU_OPTION_USE_ITEM736:
                        try {
                            ItemService.gI().OpenDHD(player, player.iDMark.getIndexMenu(), select);
                        } catch (Exception e) {
                            Logger.error("Lỗi mở hộp quà");
                        }
                        break;
                    case ConstNpc.INTRINSIC:
                        if (select == 0) {
                            IntrinsicService.gI().showAllIntrinsic(player);
                        } else if (select == 1) {
                            IntrinsicService.gI().showConfirmOpen(player);
                        } else if (select == 2) {
                            IntrinsicService.gI().showConfirmOpenVip(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_OPEN_INTRINSIC:
                        if (select == 0) {
                            IntrinsicService.gI().open(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_OPEN_INTRINSIC_VIP:
                        if (select == 0) {
                            IntrinsicService.gI().openVip(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_LEAVE_CLAN:
                        if (select == 0) {
                            ClanService.gI().leaveClan(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_NHUONG_PC:
                        if (select == 0) {
                            ClanService.gI().phongPc(player, (int) PLAYERID_OBJECT.get(player.id));
                        }
                        break;
                        
                    case ConstNpc.BAN_PLAYER:
                        if (select == 0) {
                            PlayerService.gI().banPlayer((Player) PLAYERID_OBJECT.get(player.id));
                            Service.gI().sendThongBao(player, "Ban người chơi " + ((Player) PLAYERID_OBJECT.get(player.id)).name + " thành công");
                        }
                        break;
                    case ConstNpc.BUFF_PET:
                        if (select == 0) {
                            Player pl = (Player) PLAYERID_OBJECT.get(player.id);
                            if (pl.pet == null) {
                                PetService.gI().createNormalPet(pl);
                                Service.gI().sendThongBao(player, "Phát đệ tử cho " + ((Player) PLAYERID_OBJECT.get(player.id)).name + " thành công");
                            }
                        }
                        break;
                    case ConstNpc.ACTIVE_PLAYER:
                        if (select == 0) {
                            PlayerService.gI().ActivePlayer((Player) PLAYERID_OBJECT.get(player.id));
                            Service.getInstance().sendThongBao(player, "Activated  " + ((Player) PLAYERID_OBJECT.get(player.id)).name + " thành công");
                        }
                        break;
                    case ConstNpc.UP_TOP_ITEM:
                  case ConstNpc.MENU_PLAYER:
                        switch (select) {
                            case 0:
                                Input.gI().createFormGiftCode(player);
                                break;
                            case 1:
                                Input.gI().createFormChangePassword(player);
                                break;
                           
                            case 2:
                                NpcService.gI().createMenuConMeo(player, ConstNpc.CHISO_NHANH, 12639,
                                        "|7|AUTO CỘNG CHỈ SỐ NHANH"
                                        + "\n\n|2| Bạn muốn cộng nhanh chỉ số nào?",
                                        "HP\n" + (player.autoHP == true ? "[ BẬT ]" : "[ TẮT ]"),
                                        "KI\n" + (player.autoKI == true ? "[ BẬT ]" : "[ TẮT ]"),
                                        "SD\n" + (player.autoSD == true ? "[ BẬT ]" : "[ TẮT ]"),
                                        "Giáp\n" + (player.autoGiap == true ? "[ BẬT ]" : "[ TẮT ]"));
                                break;
                        }
                        break;
                         case ConstNpc.CHISO_NHANH:
                        switch (select) {
                            case 0:
                                player.autoHP = !player.autoHP;
                                Service.gI().sendThongBao(player, "|1|Đã " + (player.autoHP == true ? "Bật" : "Tắt") + " Auto cộng chỉ số HP");
                                try {
                                    while (player.autoHP == true) {
                                        if (player.nPoint != null && player.nPoint.hpg + 20000 <= player.nPoint.getHpMpLimit()) {
                                            player.nPoint.increasePoint((byte) 0, (short) 1000);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng HP!\n|2|" + player.nPoint.hpg);
                                        } else if (player.nPoint != null && player.nPoint.hpg + 2000 <= player.nPoint.getHpMpLimit()) {
                                            player.nPoint.increasePoint((byte) 0, (short) 100);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng HP!\n|2|" + player.nPoint.hpg);
                                        } else if (player.nPoint != null && player.nPoint.hpg + 200 <= player.nPoint.getHpMpLimit()) {
                                            player.nPoint.increasePoint((byte) 0, (short) 10);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng HP!\n|2|" + player.nPoint.hpg);
                                        } else if (player.nPoint != null && player.nPoint.hpg + 20 <= player.nPoint.getHpMpLimit()) {
                                            player.nPoint.increasePoint((byte) 0, (short) 1);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng HP!\n|2|" + player.nPoint.hpg);
                                        } else {
                                            player.autoHP = false;
                                            Service.gI().sendThongBaoFromAdmin(player, "|8|Auto cộng HP đã dừng!\n|2|Bạn đã đạt giới hạn sức mạnh");
                                            break;
                                        }
                                    }
                                } catch (Exception e) {
                                }
                                break;
                            case 1:
                                player.autoKI = !player.autoKI;
                                Service.gI().sendThongBao(player, "|1|Đã " + (player.autoKI == true ? "Bật" : "Tắt") + " Auto cộng chỉ số KI");
                                if (player.autoKI == true) {
                                    try {
                                        while (player.autoKI == true) {
                                            if (player.nPoint != null && player.nPoint.mpg + 20000 <= player.nPoint.getHpMpLimit()) {
                                                player.nPoint.increasePoint((byte) 1, (short) 1000);
                                                Service.gI().sendThongBao(player, "|8|Auto cộng KI!\n|2|" + player.nPoint.mpg);
                                            } else if (player.nPoint != null && player.nPoint.mpg + 2000 <= player.nPoint.getHpMpLimit()) {
                                                player.nPoint.increasePoint((byte) 1, (short) 100);
                                                Service.gI().sendThongBao(player, "|8|Auto cộng KI!\n|2|" + player.nPoint.mpg);
                                            } else if (player.nPoint != null && player.nPoint.mpg + 200 <= player.nPoint.getHpMpLimit()) {
                                                player.nPoint.increasePoint((byte) 1, (short) 10);
                                                Service.gI().sendThongBao(player, "|8|Auto cộng KI!\n|2|" + player.nPoint.mpg);
                                            } else if (player.nPoint != null && player.nPoint.mpg + 20 <= player.nPoint.getHpMpLimit()) {
                                                player.nPoint.increasePoint((byte) 1, (short) 1);
                                                Service.gI().sendThongBao(player, "|8|Auto cộng KI!\n|2|" + player.nPoint.mpg);
                                            } else {
                                                player.autoKI = false;
                                                Service.gI().sendThongBaoFromAdmin(player, "|8|Auto cộng KI đã dừng!\n|2|Bạn đã đạt giới hạn sức mạnh");
                                                break;
                                            }
                                        }
                                    } catch (Exception e) {
                                    }
                                }
                                break;
                            case 2:
                                player.autoSD = !player.autoSD;
                                Service.gI().sendThongBao(player, "|1|Đã " + (player.autoSD == true ? "Bật" : "Tắt") + " Auto cộng chỉ số KI");
                                try {
                                    while (player.autoSD == true) {
                                        if (player.nPoint != null && player.nPoint.dameg + 1000 <= player.nPoint.getDameLimit()) {
                                            player.nPoint.increasePoint((byte) 2, (short) 1000);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng SĐ!\n|2|" + player.nPoint.dameg);
                                        } else if (player.nPoint != null && player.nPoint.dameg + 100 <= player.nPoint.getDameLimit()) {
                                            player.nPoint.increasePoint((byte) 2, (short) 100);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng SĐ!\n|2|" + player.nPoint.dameg);
                                        } else if (player.nPoint != null && player.nPoint.dameg + 10 <= player.nPoint.getDameLimit()) {
                                            player.nPoint.increasePoint((byte) 2, (short) 10);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng SĐ!\n|2|" + player.nPoint.dameg);
                                        } else if (player.nPoint != null && player.nPoint.dameg + 1 <= player.nPoint.getDameLimit()) {
                                            player.nPoint.increasePoint((byte) 2, (short) 1);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng SĐ!\n|2|" + player.nPoint.dameg);
                                        } else {
                                            player.autoSD = false;
                                            Service.gI().sendThongBaoFromAdmin(player, "|8|Auto cộng SĐ đã dừng!\n|2|Bạn đã đạt giới hạn sức mạnh");
                                            break;
                                        }
                                    }
                                } catch (Exception e) {
                                }
                                break;
                            case 3:
                                player.autoGiap = !player.autoGiap;
                                Service.gI().sendThongBao(player, "|1|Đã " + (player.autoGiap == true ? "Bật" : "Tắt") + " Auto cộng chỉ số DEF");
                                try {
                                    while (player.autoGiap == true) {
                                        if (player.nPoint != null && player.nPoint.defg + 100 <= player.nPoint.getDefLimit()) {
                                            player.nPoint.increasePoint((byte) 3, (short) 100);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng DEF!\n|2|" + player.nPoint.defg);
                                        } else if (player.nPoint != null && player.nPoint.defg + 10 <= player.nPoint.getDefLimit()) {
                                            player.nPoint.increasePoint((byte) 3, (short) 10);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng DEF!\n|2|" + player.nPoint.defg);
                                        } else if (player.nPoint != null && player.nPoint.defg + 1 <= player.nPoint.getDefLimit()) {
                                            player.nPoint.increasePoint((byte) 3, (short) 1);
                                            Service.gI().sendThongBao(player, "|8|Auto cộng DEF!\n|2|" + player.nPoint.defg);
                                        } else {
                                            player.autoGiap = false;
                                            Service.gI().sendThongBaoFromAdmin(player, "|8|Auto cộng DEF đã dừng!\n|2|Bạn đã đạt giới hạn sức mạnh");
                                            break;
                                        }
                                    }
                                } catch (Exception e) {
                                }
                                break;
                        }
                        break;
                    case ConstNpc.MENU_ADMIN:
                        switch (select) {
                            case 0:
                                this.createMenuConMeo(player, ConstNpc.ADMIN1, 12691, "|7| Admin Trùng Sinh\b|2| ADMIN\b|4| Người Đang Chơi: " + Client.gI().getPlayers().size() + "\n" + "|8|Current thread: " + (Thread.activeCount() - ServerManager.gI().threadMap) + "\n",
                                        "Ngọc Rồng", "Đệ Tử", "Bảo Trì", "Tìm Kiếm\nPlayer", "Đóng");
                                break;
                            case 1:
                                this.createOtherMenu(player, ConstNpc.CALL_BOSS,
                                        "Chọn Boss?", "Full Cụm\nANDROID", "BLACK", "BROLY", "Cụm\nCell",
                                        "Cụm\nDoanh trại", "DOREMON", "FIDE", "FIDE\nBlack", "Cụm\nGINYU", "Cụm\nNAPPA", "Gắp Thú");
                                break;
                            case 2:
                                this.createOtherMenu(player, ConstNpc.BUFFITEM,
                                        "Buff Item", "Buff Item", "Item Option", "Buff Skh");
                                break;
                            case 3:
                                MaQuaTangManager.gI().checkInfomationGiftCode(player);
                                break;
                            case 4:
                                Input.gI().createFormNapCoin(player);
                                break;
                            case 5:
                                Input.gI().ChatAll(player);
                                break;
                            case 6:
                                Input.gI().createFormEXP(player);
                                break;
                            case 7:
                                Input.gI().thuitem(player);
                                break;
                        }
                        break;
                    case ConstNpc.BUFFITEM:
                        switch (select) {
                            case 0:
                                Input.gI().createFormSenditem(player);
                                break;
                            case 1:
                                Input.gI().createFormSenditem1(player);
                                break;
                            case 2:
                                Input.gI().createFormSenditem2(player);
                                break;
                        }
                        break;
                    case ConstNpc.ADMIN1:
                        switch (select) {
                            case 0:
                                for (int i = 14; i <= 20; i++) {
                                    Item item = ItemService.gI().createNewItem((short) i);
                                    InventoryServiceNew.gI().addItemBag(player, item);
                                }
                                InventoryServiceNew.gI().sendItemBags(player);
                                break;
                            case 1:
                                if (player.pet == null) {
                                    PetService.gI().createNormalPet(player);
                                } else {
                                    if (player.pet.typePet == 1) {
                                        PetService.gI().changePicPet(player);
                                    } else if (player.pet.typePet == 2) {
                                        PetService.gI().changeMabuPet(player);
                                    }
                                    PetService.gI().changeBerusPet(player);
                                }
                                break;
                            case 2:
                                if (player.isAdmin()) {
                                    System.out.println(player.name);
                                    Maintenance.gI().start(15);
                                    System.out.println(player.name);
                                }
                                break;
                            case 3:
                                Input.gI().createFormFindPlayer(player);
                                break;
                        }
                        break;
                    case ConstNpc.CALL_BOSS:
                        switch (select) {
                            case 0:
                                BossManager.gI().createBoss(BossID.ANDROID_13);
                                BossManager.gI().createBoss(BossID.ANDROID_14);
                                BossManager.gI().createBoss(BossID.ANDROID_15);
                                BossManager.gI().createBoss(BossID.ANDROID_19);
                                BossManager.gI().createBoss(BossID.DR_KORE);
                                BossManager.gI().createBoss(BossID.KING_KONG);
                                BossManager.gI().createBoss(BossID.PIC);
                                BossManager.gI().createBoss(BossID.POC);
                                break;
                            case 1:
                                BossManager.gI().createBoss(BossID.BLACK);
                                break;
                            case 2:
                                BossManager.gI().createBoss(BossID.BROLY);
                                break;
                            case 3:
                                BossManager.gI().createBoss(BossID.SIEU_BO_HUNG);
                                BossManager.gI().createBoss(BossID.XEN_BO_HUNG);
//                                BossManager.gI().createBoss(BossID.XEN_CON);
                                break;
                            case 4:
                                Service.getInstance().sendThongBao(player, "Không có boss");
                                break;
                            case 5:
                                BossManager.gI().createBoss(BossID.CHAIEN);
                                BossManager.gI().createBoss(BossID.XEKO);
                                BossManager.gI().createBoss(BossID.XUKA);
                                BossManager.gI().createBoss(BossID.NOBITA);
                                BossManager.gI().createBoss(BossID.DORAEMON);
                                break;
                            case 6:
                                BossManager.gI().createBoss(BossID.FIDE);
                                break;
                            case 7:
                                BossManager.gI().createBoss(BossID.FIDE_ROBOT);
                                BossManager.gI().createBoss(BossID.VUA_COLD);
                                break;
                            case 8:
                                BossManager.gI().createBoss(BossID.TDST);
                                break;
                            case 9:
                                BossManager.gI().createBoss(BossID.KUKU);
                                BossManager.gI().createBoss(BossID.MAP_DAU_DINH);
                                BossManager.gI().createBoss(BossID.RAMBO);
                                break;
                        }
                        break;

                    case ConstNpc.menutd:
                        switch (select) {
                            case 0:
                                try {
                                    ItemService.gI().settaiyoken(player);
                                } catch (Exception e) {
                                }
                                break;
                            case 1:
                                try {
                                    ItemService.gI().setgenki(player);
                                } catch (Exception e) {
                                }
                                break;
                            case 2:
                                try {
                                    ItemService.gI().setkamejoko(player);
                                } catch (Exception e) {
                                }
                                break;
                        }
                        break;

                    case ConstNpc.menunm:
                        switch (select) {
                            case 0:
                                try {
                                    ItemService.gI().setgodki(player);
                                } catch (Exception e) {
                                }
                                break;
                            case 1:
                                try {
                                    ItemService.gI().setgoddam(player);
                                } catch (Exception e) {
                                }
                                break;
                            case 2:
                                try {
                                    ItemService.gI().setsummon(player);
                                } catch (Exception e) {
                                }
                                break;
                        }
                        break;

                    case ConstNpc.menuxd:
                        switch (select) {
                            case 0:
                                try {
                                    ItemService.gI().setgodgalick(player);
                                } catch (Exception e) {
                                }
                                break;
                            case 1:
                                try {
                                    ItemService.gI().setmonkey(player);
                                } catch (Exception e) {
                                }
                                break;
                            case 2:
                                try {
                                    ItemService.gI().setgodhp(player);
                                } catch (Exception e) {
                                }
                                break;
                        }
                        break;

                    case ConstNpc.CONFIRM_DISSOLUTION_CLAN:
                        switch (select) {
                            case 0:
                                Clan clan = player.clan;
                                clan.deleteDB(clan.id);
                                Manager.CLANS.remove(clan);
                                player.clan = null;
                                player.clanMember = null;
                                ClanService.gI().sendMyClan(player);
                                ClanService.gI().sendClanId(player);
                                Service.gI().sendThongBao(player, "Đã giải tán bang hội.");
                                break;
                        }
                        break;
//                    case ConstNpc.CONFIRM_ACTIVE:
//                        switch (select) {
//                            case 0:
//                                if (player.getSession().goldBar >= 20) {
//                                    player.getSession().actived = true;
//                                    if (PlayerDAO.subGoldBar(player, 20)) {
//                                        Service.gI().sendThongBao(player, "Đã mở thành viên thành công!");
//                                        break;
//                                    } else {
//                                        this.npcChat(player, "Lỗi vui lòng báo admin...");
//                                    }
//                                }
////                                Service.gI().sendThongBao(player, "Bạn không có vàng\n Vui lòng NROGOD.COM để nạp thỏi vàng");
//                                break;
//                        }
//                        break;
                    case ConstNpc.CONFIRM_REMOVE_ALL_ITEM_LUCKY_ROUND:
                        if (select == 0) {
                            for (int i = 0; i < player.inventory.itemsBoxCrackBall.size(); i++) {
                                player.inventory.itemsBoxCrackBall.set(i, ItemService.gI().createItemNull());
                            }
                            player.inventory.itemsBoxCrackBall.clear();
                            Service.gI().sendThongBao(player, "Đã xóa hết vật phẩm trong rương");
                        }
                        break;
                    case ConstNpc.MENU_FIND_PLAYER:
                        Player p = (Player) PLAYERID_OBJECT.get(player.id);
                        if (p != null) {
                            switch (select) {
                                case 0:
                                    if (p.zone != null) {
                                        ChangeMapService.gI().changeMapYardrat(player, p.zone, p.location.x, p.location.y);
                                    }
                                    break;
                                case 1:
                                    if (p.zone != null) {
                                        ChangeMapService.gI().changeMap(p, player.zone, player.location.x, player.location.y);
                                    }
                                    break;
                                case 2:
                                    Input.gI().createFormChangeName(player, p);
                                    break;
                                case 3:
                                    String[] selects = new String[]{"Đồng ý", "Hủy"};
                                    NpcService.gI().createMenuConMeo(player, ConstNpc.BAN_PLAYER, -1,
                                            "Bạn có chắc chắn muốn ban " + p.name, selects, p);
                                    break;
                                case 4:
                                    Service.gI().sendThongBao(player, "Kik người chơi " + p.name + " thành công");
                                    Client.gI().getPlayers().remove(p);
                                    Client.gI().kickSession(p.getSession());
                                    break;
                               case 5:
                                    Input.gI().thuitem(player);
                                    break;
                                case 6:
                                    if (player.isAdmin()) {
                                        Service.gI().sendThongBao(player, "Bạn không phải Admin Cấp Cao");
                                    } else {
                                        String[] selectsssss = new String[]{"CẤP QUYỀN", "HỦY QUYỀN"};
                                        NpcService.gI().createMenuConMeo(player, ConstNpc.MENU_PLAYER, 12639,
                                                "|7|NÂNG KEY TRỰC TIẾP CHO PLAYER : " + p.name + "?", selectsssss, p);
                                    }
                                    break;
                                    case 7:
                                    String[] selectssss = new String[]{"TRỰC TIẾP\nID < 32", "NEXT MAIN\n" + "ID NV : " + p.playerTask.taskMain.id, "NEXT SUB\n" + "ID SUB : " + p.playerTask.taskMain.index};
                                    NpcService.gI().createMenuConMeo(player, ConstNpc.MENU_PLAYER, 12639,
                                            "|7| [ NEXT TASK PLAYER ]\n" + "Bạn có chắc chắn muốn NEXT NHIỆM VỤ player : " + p.name + " không?"
                                            + "\n" + "|1|TRỰC TIẾP : NEXT TRỰC TIẾP ĐẾN ID ĐÃ CHỌN"
                                            + "\n" + "NEXT MAIN : NEXT 1 NHIỆM VỤ CHÍNH TIẾP THEO"
                                            + "\n" + "NEXT SUB : NEXT 1 NHIỆM VỤ NHÁNH TIẾP THEO",
                                            selectssss, p);
                                    break;
                                   
                            }
                        }
                        break;
                    case ConstNpc.MENU_EVENT:
                        switch (select) {
                            case 0:
                                Service.gI().sendThongBaoOK(player, "Điểm sự kiện: " + player.inventory.event + " ngon ngon...");
                                break;
                            case 1:
                                Service.gI().showListTop(player, Manager.topSK);
                                break;
                            case 2:
                                Service.gI().sendThongBao(player, "Sự kiện đã kết thúc...");
//                                NpcService.gI().createMenuConMeo(player, ConstNpc.MENU_GIAO_BONG, -1, "Người muốn giao bao nhiêu bông...",
//                                        "100 bông", "1000 bông", "10000 bông");
                                break;
                            case 3:
                                Service.gI().sendThongBao(player, "Sự kiện đã kết thúc...");
//                                NpcService.gI().createMenuConMeo(player, ConstNpc.CONFIRM_DOI_THUONG_SU_KIEN, -1, "Con có thực sự muốn đổi thưởng?\nPhải giao cho ta 3000 điểm sự kiện đấy... ",
//                                        "Đồng ý", "Từ chối");
                                break;

                        }
                        break;
//                    case ConstNpc.MENU_GIAO_BONG:
//                        ItemService.gI().giaobong(player, (int) Util.tinhLuyThua(10, select + 2));
//                        break;
                    case ConstNpc.CONFIRM_DOI_THUONG_SU_KIEN:
                        if (select == 0) {
                            ItemService.gI().openBoxVip(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_DOI_DIEM_DUA:
                        if (select == 0) {
                            ItemService.gI().openBoxCongThuc(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_DOI_DIEM_ITEMC2:
                        if (select == 0) {
                            ItemService.gI().openBoxitemc2(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_DOI_ITEM_NR:
                        if (select == 0) {
                            ItemService.gI().openBoxitemnr(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_DOI_DIEM_CT:
                        if (select == 0) {
                            ItemService.gI().openBoxCt(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_TELE_NAMEC:
                        if (select == 0) {
                            NgocRongNamecService.gI().teleportToNrNamec(player);
                            player.inventory.subGemAndRuby(50);
                            Service.gI().sendMoney(player);
                        }
                        break;
                }
            }
        };
    }

}
