package com.girlkun.services;

import com.arriety.MaQuaTang.MaQuaTangManager;
import com.girlkun.database.GirlkunDB;
import com.girlkun.consts.ConstNpc;
import com.girlkun.consts.ConstPlayer;
import com.girlkun.utils.FileIO;
import com.girlkun.data.DataGame;
import com.girlkun.jdbc.daos.GodGK;
import com.girlkun.models.boss.Boss;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossManager;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.boss.list_boss.DuongTank;
import com.girlkun.models.boss.list_boss.TrainOffline.Thuongde;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.girlkun.models.item.Item;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.mob.Mob;
import com.girlkun.models.player.Pet;
import com.girlkun.models.item.Item.ItemOption;
import com.girlkun.models.map.Zone;
import com.girlkun.models.matches.TOP;
import com.girlkun.models.player.Player;
import com.girlkun.models.shop.ItemShop;
import com.girlkun.models.shop.Shop;
import com.girlkun.server.io.MySession;
import com.girlkun.models.skill.Skill;
import com.girlkun.network.io.Message;
import com.girlkun.network.server.GirlkunSessionManager;
import com.girlkun.network.session.ISession;
import com.girlkun.network.session.Session;
import com.girlkun.result.GirlkunResultSet;
import com.girlkun.server.Client;
import com.girlkun.server.Manager;
import com.girlkun.server.ServerManager;
import com.girlkun.services.func.ChangeMapService;
import com.girlkun.services.func.Input;
import com.girlkun.utils.Logger;
import com.girlkun.utils.SkillUtil;
import com.girlkun.utils.TimeUtil;
import com.girlkun.utils.Util;
import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;

import java.util.Set;

public class Service {

    private static Service instance;
    public long lasttimechatbanv = 0;
    public long lasttimechatmuav = 0;

    public static Service gI() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public void sendFoot(Player player, int id) {
        Message me;
        try {
            me = new Message(-128);
            me.writer().writeByte(0);
            me.writer().writeInt((int) player.id);
            switch (id) {
                case 1328:
                    me.writer().writeShort(74);
                    break;
                case 1329:
                    me.writer().writeShort(75);
                    break;
                case 1330:
                    me.writer().writeShort(76);
                    break;
                case 1331:
                    me.writer().writeShort(77);
                    break;
                case 1332:
                    me.writer().writeShort(78);
                    break;
                case 1333:
                    me.writer().writeShort(79);
                    break;
                case 1334:
                    me.writer().writeShort(80);
                    break;
                case 1335:
                    me.writer().writeShort(81);
                    break;
                case 1336:
                    me.writer().writeShort(82);
                    break;
                default:
                    break;
            }
            me.writer().writeByte(0);
            me.writer().writeByte(-1);
            me.writer().writeShort(1);
            me.writer().writeByte(-1);
            this.sendMessAllPlayerInMap(player, me);
            me.cleanup();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendFootRv(Player player, Player p2, int id) {
        Message me;
        try {
            me = new Message(-128);
            me.writer().writeByte(0);
            me.writer().writeInt((int) player.id);
            switch (id) {
                case 1328:
                    me.writer().writeShort(74);
                    break;
                case 1329:
                    me.writer().writeShort(75);
                    break;
                case 1330:
                    me.writer().writeShort(76);
                    break;
                case 1331:
                    me.writer().writeShort(77);
                    break;
                case 1332:
                    me.writer().writeShort(78);
                    break;
                case 1333:
                    me.writer().writeShort(79);
                    break;
                case 1334:
                    me.writer().writeShort(80);
                    break;
                case 1335:
                    me.writer().writeShort(81);
                    break;
                case 1336:
                    me.writer().writeShort(82);
                    break;
                default:
                    break;
            }

            me.writer().writeByte(0);
            me.writer().writeByte(-1);
            me.writer().writeShort(1);
            me.writer().writeByte(-1);
            p2.sendMessage(me);
            me.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
public void sendFoott(Player player, int id) {
        Message me;
        try {
            me = new Message(-128);
            me.writer().writeByte(0);
            me.writer().writeInt((int) player.id);
            switch (id) {
                case 1994:
                    me.writer().writeShort(66);
                    break;
                case 1329:
                    me.writer().writeShort(75);
                    break;
                case 1330:
                    me.writer().writeShort(76);
                    break;
                case 1331:
                    me.writer().writeShort(77);
                    break;
                case 1332:
                    me.writer().writeShort(78);
                    break;
                case 1333:
                    me.writer().writeShort(79);
                    break;
                case 1334:
                    me.writer().writeShort(80);
                    break;
                case 1335:
                    me.writer().writeShort(81);
                    break;
                case 1336:
                    me.writer().writeShort(82);
                    break;
                default:
                    break;
            }
            me.writer().writeByte(0);
            me.writer().writeByte(-1);
            me.writer().writeShort(1);
            me.writer().writeByte(-1);
            this.sendMessAllPlayerInMap(player, me);
            me.cleanup();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendFootRvv(Player player, Player p2, int id) {
        Message me;
        try {
            me = new Message(-128);
            me.writer().writeByte(0);
            me.writer().writeInt((int) player.id);
            switch (id) {
                case 1994:
                    me.writer().writeShort(66);
                    break;
                case 1329:
                    me.writer().writeShort(75);
                    break;
                case 1330:
                    me.writer().writeShort(76);
                    break;
                case 1331:
                    me.writer().writeShort(77);
                    break;
                case 1332:
                    me.writer().writeShort(78);
                    break;
                case 1333:
                    me.writer().writeShort(79);
                    break;
                case 1334:
                    me.writer().writeShort(80);
                    break;
                case 1335:
                    me.writer().writeShort(81);
                    break;
                case 1336:
                    me.writer().writeShort(82);
                    break;
                default:
                    break;
            }

            me.writer().writeByte(0);
            me.writer().writeByte(-1);
            me.writer().writeShort(1);
            me.writer().writeByte(-1);
            p2.sendMessage(me);
            me.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void removeTitle(Player player) {
        Message me;
        try {
            me = new Message(-128);
            me.writer().writeByte(2);
            me.writer().writeInt((int) player.id);
            player.getSession().sendMessage(me);
            this.sendMessAllPlayerInMap(player, me);
            me.cleanup();
            if (player.inventory.itemsBody.get(11).isNotNullItem()) {
                Service.getInstance().sendFoot(player, (short) player.inventory.itemsBody.get(11).template.part);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendTitle(Player player, int part) {
        Message me;
        try {
            me = new Message(-128);
            me.writer().writeByte(0);
            me.writer().writeInt((int) player.id);
            if (player.titleitem) {
                me.writer().writeShort(part);
            }
            me.writer().writeShort(part);
            me.writer().writeByte(1);
            me.writer().writeByte(-1);
            me.writer().writeShort(50);
            me.writer().writeByte(-1);
            me.writer().writeByte(-1);
            this.sendMessAllPlayerInMap(player, me);
            me.cleanup();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEffectChar(Player pl, int id, int layer, int loop, int loopcount, int stand) {
        if (!pl.idEffChar.contains(id)) {
            pl.idEffChar.add(id);
        }
        try {
            Message msg = new Message(-128);
            msg.writer().writeByte(0);
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeShort(id);
            msg.writer().writeByte(layer);
            msg.writer().writeByte(loop);
            msg.writer().writeShort(loopcount);
            msg.writer().writeByte(stand);
            sendMessAllPlayerInMap(pl.zone, msg);
        } catch (IOException e) {

        }
    }

    public void removeAlleff(Player player) {
        Message me;
        try {
            me = new Message(-128);
            me.writer().writeByte(2);
            me.writer().writeInt((int) player.id);
            player.getSession().sendMessage(me);
            this.sendMessAllPlayerInMap(player, me);
            me.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 public void sendTitlerv(Player player, int id) {
        Message me;
        try {
            me = new Message(-128);
            me.writer().writeByte(0);
            me.writer().writeInt((int) player.id);
            if (player.titleitem) {
                if (id == (1318 + 5)) {
                    me.writer().writeShort(60);
                } else if (id == (1318 + 6)) {
                    me.writer().writeShort(61);
                } else if (id == (1318 + 7)) {
                    me.writer().writeShort(62);
                } else if (id == (1318 + 8)) {
                    me.writer().writeShort(63);
                } else if (id == (13180 + 9)) {
                    me.writer().writeShort(64);
                }
            } //id danh hiệu
            if (id == 888) {
                if (player.lastTimeTitle1 > 0 && player.isTitleUse1) {
                    if (player.gender == 0) {
                        me.writer().writeShort(88);// tuổi thơ
                    } else if (player.gender == 1) {
                        me.writer().writeShort(88);// tuổi thơ
                    } else {
                        me.writer().writeShort(88);// tuổi thơ
                    }
                }
            }
            if (id == 889) {
                if (player.lastTimeTitle2 > 0 && player.isTitleUse2) {
                    me.writer().writeShort(68); // đại thần
                }
            }
            if (id == 890) {
                if (player.lastTimeTitle3 > 0 && player.isTitleUse3) {
                    me.writer().writeShort(355); // trùm cuối
                }
            }
            if (id == 891) {
                if (player.lastTimeTitle4 > 0 && player.isTitleUse4) {
                    me.writer().writeShort(307); // trùm cuối
                }
            }
            me.writer().writeByte(1);
            me.writer().writeByte(-1);
            me.writer().writeShort(50);
            me.writer().writeByte(-1);
            me.writer().writeByte(-1);
            me.writer().writeByte(-1);

            this.sendMessAllPlayerInMap(player, me);
            me.cleanup();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showListTop(Player player, List<TOP> tops) {
        Message msg;
        try {
            msg = new Message(-96);
            msg.writer().writeByte(0);
            msg.writer().writeUTF("Top");
            msg.writer().writeByte(tops.size());
            for (int i = 0; i < tops.size(); i++) {
                TOP top = tops.get(i);
                Player pl = GodGK.loadById(top.getId_player());
                msg.writer().writeInt(i + 1);
                msg.writer().writeInt((int) pl.id);
                msg.writer().writeShort(pl.getHead());
                if (player.getSession().version > 214) {
                    msg.writer().writeShort(-1);
                }
                msg.writer().writeShort(pl.getBody());
                msg.writer().writeShort(pl.getLeg());
                msg.writer().writeUTF(pl.name);
                msg.writer().writeUTF(top.getInfo1());
                msg.writer().writeUTF(top.getInfo2());
            }
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showListTop(Player player, List<TOP> tops, byte isPVP) {
        Message msg;
        try {
            msg = new Message(-96);
            msg.writer().writeByte(0);
            msg.writer().writeUTF("Top");
            msg.writer().writeByte(tops.size());
            for (int i = 0; i < tops.size(); i++) {
                TOP top = tops.get(i);
                Player pl = GodGK.loadById(top.getId_player());
                msg.writer().writeInt(isPVP != 1 ? (i + 1) : (int) pl.rankSieuHang);
//                msg.writer().writeInt(i + 1);
                msg.writer().writeInt((int) pl.id);
                msg.writer().writeShort(pl.getHead());
                if (player.getSession().version > 214) {
                    msg.writer().writeShort(-1);
                }
                msg.writer().writeShort(pl.getBody());
                msg.writer().writeShort(pl.getLeg());
                msg.writer().writeUTF(pl.name);
                msg.writer().writeUTF(top.getInfo1());
                msg.writer().writeUTF(isPVP == 1 ? top.getInfo2() : top.getInfo2() + pl.numKillSieuHang);
                //               msg.writer().writeUTF(isPVP == 1 ? ("Sức Đánh: " + pl.nPoint.dame +"\n"+ "HP: " + pl.nPoint.hpMax +"\n"+ "KI: " + pl.nPoint.mpMax +"\n" + "Điểm hạng: " + pl.rankSieuHang) : top.getInfo2());
            }
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendRank(Player player) {
        Message msg = null;
        try {
            msg = new Message(-119);
            msg.writer().writeInt((int) player.rankSieuHang);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPopUpMultiLine(Player pl, int tempID, int avt, String text) {
        Message msg = null;
        try {
            msg = new Message(-218);
            msg.writer().writeShort(tempID);
            msg.writer().writeUTF(text);
            msg.writer().writeShort(avt);
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            if (msg != null) {
                msg.cleanup();
                msg = null;
            }
        }
    }

    public void sendPetFollow(Player player, short smallID) {
        Message msg;
        try {
            msg = new Message(31);
            msg.writer().writeInt((int) player.id);
            if (smallID == 0) {
                msg.writer().writeByte(0);
            } else {

                msg.writer().writeByte(1);
                msg.writer().writeShort(smallID);
                msg.writer().writeByte(1);
                int[] fr = new int[]{};
                int[] fr2 = new int[]{0, 1, 2, 3, 4, 5, 6};

                switch (smallID) {

                    case 14420:
                        fr = new int[]{0, 1, 2, 3, 4, 5};
                        break;
                    case 16167:
                    case 16149:
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70};
                        break;
                    case 16147:
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43
                        };
                        break;
                    case 16151:
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
                        break;
                    case 15751:
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
                        break;
                    case 16153:
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47};
                        break;

                    case 16155:
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94};
                        break;
                    case 16157:
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80};
                        break;
                    case 16159:
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};
                        break;
                    case 16161:
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76};
                        break;
                    case 16163:
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47};
                        break;
                    case 16165:
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60};
                        break;
                    case 16169:
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79};
                        break;
                    case 16171:
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60};
                        break;
                    case 15773:
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32};
                        break;
                    case 15775:
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
                        break;
                    case 16278:
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6};
                        break;
                    case 25246:
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6};
                        break;
                    case 25248:
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6};
                        break;
                    case 20210:
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
                        break;
                    case 21937:
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
                        break;
                    default:
//                    fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180 };
                        fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
                        break;
                }
                msg.writer().writeByte(fr.length);
                for (int i = 0; i < fr.length; i++) {
                    msg.writer().writeByte(fr[i]);
                }
                switch (smallID) {
                    case 14420:
                    case 14432:
                    case 14434:
                    case 16278:
                        msg.writer().writeShort(225);
                        msg.writer().writeShort(225);
                        break;
                    case 25246:
                        msg.writer().writeShort(41);
                        msg.writer().writeShort(36);
                        break;
                    case 25248:
                        msg.writer().writeShort(41);
                        msg.writer().writeShort(36);
                        break;
                    case 16147:
                    case 16149:
                    case 16151:
                    case 16161:
                    case 16169:
                        msg.writer().writeShort(70);
                        msg.writer().writeShort(70);
                        break;
                    case 15751:
                        msg.writer().writeShort(289);
                        msg.writer().writeShort(289);
                        break;
                    case 16153:
                        msg.writer().writeShort(86);
                        msg.writer().writeShort(86);
                        break;
                    case 16157:
                    case 16159:
                    case 16171:
                    case 16167:
                        msg.writer().writeShort(96);
                        msg.writer().writeShort(96);
                        break;
                    case 15775:
                        msg.writer().writeShort(175);
                        msg.writer().writeShort(175);
                        break;
                    case 16155:
                    case 16276:
                    case 16282:
                        msg.writer().writeShort(75);
                        msg.writer().writeShort(75);
                        break;
                    case 16163:
                    case 16165:
                        msg.writer().writeShort(50);
                        msg.writer().writeShort(50);
                        break;
                    case 15773:
                        msg.writer().writeShort(150);
                        msg.writer().writeShort(150);
                        break;
                    case 15067:
                        msg.writer().writeShort(65);
                        msg.writer().writeShort(65);
                        break;
                    case 16280:
                        msg.writer().writeShort(112);
                        msg.writer().writeShort(112);
                        break;
                    case 21937:
                        msg.writer().writeShort(48);
                        msg.writer().writeShort(32);
                        break;
                    case 20210:
                        msg.writer().writeShort(32);
                        msg.writer().writeShort(32);
                    default:
                        msg.writer().writeShort(75);
                        msg.writer().writeShort(75);
                        break;
                }
            }
            sendMessAllPlayerInMap(player, msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPetFollowToMe(Player me, Player pl) {
        Item linhThu = pl.inventory.itemsBody.get(10);
        if (!linhThu.isNotNullItem()) {
            return;
        }
        short smallId = (short) (linhThu.template.iconID - 1);
        Message msg;
        try {
            msg = new Message(31);
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeByte(1);
            msg.writer().writeShort(smallId);
            msg.writer().writeByte(1);
            int[] fr = new int[]{};
            switch (smallId) {
                case 14420:
                    fr = new int[]{0, 1, 2, 3, 4, 5};
                    break;
                case 16167:
                case 16149:
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70};
                    break;
                case 16147:
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43};
                    break;
                case 16151:
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
                    break;
                case 15751:
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
                    break;
                case 16153:
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47};
                    break;

                case 16155:
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94};
                    break;
                case 16157:
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80};
                    break;
                case 16159:
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};
                    break;
                case 16161:
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76};
                    break;
                case 16163:
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47};
                    break;
                case 16165:
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60};
                    break;
                case 16169:
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79};
                    break;
                case 16171:
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60};
                    break;
                case 15773:
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32};
                    break;
                case 15775:
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
                    break;
                case 25246:
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6};
                    break;
                case 25248:
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6};
                    break;
                case 16278:
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6};
                    break;
                case 20210:
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
                    break;
                case 21937:
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
                    break;
                default:
//                    fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180 };
                    fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
                    break;
            }
            msg.writer().writeByte(fr.length);
            for (int i = 0; i < fr.length; i++) {
                msg.writer().writeByte(fr[i]);
            }
            switch (smallId) {
                case 14420:
                case 14432:
                case 14434:
                case 16278:
                    msg.writer().writeShort(225);
                    msg.writer().writeShort(225);
                    break;
                case 25248:
                    msg.writer().writeShort(41);
                    msg.writer().writeShort(36);
                    break;
                case 25246:
                    msg.writer().writeShort(32);
                    msg.writer().writeShort(32);
                    break;
                case 16147:
                case 16149:
                case 16151:
                case 16161:
                case 16169:
                    msg.writer().writeShort(70);
                    msg.writer().writeShort(70);
                    break;
                case 15751:
                    msg.writer().writeShort(289);
                    msg.writer().writeShort(289);
                    break;
                case 16153:
                    msg.writer().writeShort(86);
                    msg.writer().writeShort(86);
                    break;
                case 16157:
                case 16159:
                case 16171:
                case 16167:

                    msg.writer().writeShort(96);
                    msg.writer().writeShort(96);
                    break;
                case 15775:
                    msg.writer().writeShort(175);
                    msg.writer().writeShort(175);
                    break;
                case 16155:
                case 16276:
                case 16282:
                    msg.writer().writeShort(75);
                    msg.writer().writeShort(75);
                    break;
                case 16163:
                case 16165:
                    msg.writer().writeShort(50);
                    msg.writer().writeShort(50);
                    break;
                case 15773:
                    msg.writer().writeShort(150);
                    msg.writer().writeShort(150);
                    break;
                case 15067:
                    msg.writer().writeShort(65);
                    msg.writer().writeShort(65);
                    break;
                case 16280:
                    msg.writer().writeShort(112);
                    msg.writer().writeShort(112);
                    break;
                case 20210:
                    msg.writer().writeShort(32);
                    msg.writer().writeShort(32);
                    break;
                case 21937:
                    msg.writer().writeShort(48);
                    msg.writer().writeShort(32);
                    break;
                default:
                    msg.writer().writeShort(75);
                    msg.writer().writeShort(75);
                    break;
            }

            sendMessAllPlayerInMap(pl, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessAllPlayer(Message msg) {
        PlayerService.gI().sendMessageAllPlayer(msg);
    }

    public void sendMessAllPlayerIgnoreMe(Player player, Message msg) {
        PlayerService.gI().sendMessageIgnore(player, msg);
    }

    public void sendMessAllPlayerInMap(Zone zone, Message msg) {
        if (zone == null) {
            msg.dispose();
            return;
        }
        List<Player> players = zone.getPlayers();
        if (players.isEmpty()) {
            msg.dispose();
            return;
        }
        for (Player pl : players) {
            if (pl != null) {
                pl.sendMessage(msg);
            }
        }
        msg.cleanup();
    }

    public void sendMessAllPlayerInMap(Player player, Message msg) {
        if (player == null || player.zone == null) {
            msg.dispose();
            return;
        }
        if (MapService.gI().isMapOffline(player.zone.map.mapId)) {
            if (player.isPet) {
                ((Pet) player).master.sendMessage(msg);
            } else {
                player.sendMessage(msg);
            }
        } else {
            List<Player> players = player.zone.getPlayers();
            if (players.isEmpty()) {
                msg.dispose();
                return;
            }
            for (int i = 0; i < players.size(); i++) {
                Player pl = players.get(i);
                if (pl != null) {
                    pl.sendMessage(msg);
                }
            }
        }
        msg.cleanup();
    }

    public void sendThongBaoBenDuoi(String text) {
        Message msg = null;
        try {
            msg = new Message(93);
            msg.writer().writeUTF(text);
            sendMessAllPlayer(msg);
        } catch (Exception e) {
        } finally {
            if (msg != null) {
                msg.cleanup();
                msg = null;
            }
        }
    }

    public void regisAccount(Session session, Message _msg) {
        try {
            _msg.readUTF();
            _msg.readUTF();
            _msg.readUTF();
            _msg.readUTF();
            _msg.readUTF();
            _msg.readUTF();
            _msg.readUTF();
            String user = _msg.readUTF();
            String pass = _msg.readUTF();
            if (!(user.length() >= 4 && user.length() <= 18)) {
                sendThongBaoOK((MySession) session, "Tài khoản phải có độ dài 4-18 ký tự");
                return;
            }
            if (!(pass.length() >= 5 && pass.length() <= 18)) {
                sendThongBaoOK((MySession) session, "Mật khẩu phải có độ dài 5-18 ký tự");
                return;
            }
            GirlkunResultSet rs = GirlkunDB.executeQuery("select * from account where username = ?", user);
            if (rs.first()) {
                sendThongBaoOK((MySession) session, "Tài khoản đã tồn tại");
            } else {
                GirlkunDB.executeUpdate("insert into account (username, password) values()", user, pass);
                sendThongBaoOK((MySession) session, "Đăng ký tài khoản thành công!");
            }
            rs.dispose();
        } catch (Exception e) {

        }
    }

    public void sendMessAnotherNotMeInMap(Player player, Message msg) {
        if (player == null || player.zone == null) {
            msg.dispose();
            return;
        }
        List<Player> players = player.zone.getPlayers();
        if (players.isEmpty()) {
            msg.dispose();
            return;
        }
        for (Player pl : players) {
            if (pl != null && !pl.equals(player)) {
                pl.sendMessage(msg);
            }
        }
        msg.cleanup();

    }

    public void Send_Info_NV(Player pl) {
        Message msg;
        try {
            msg = Service.gI().messageSubCommand((byte) 14);// Cập nhật máu
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeInt(pl.nPoint.hp);
            msg.writer().writeByte(0);// Hiệu ứng Ăn Đậu
            msg.writer().writeInt(pl.nPoint.hpMax);
            sendMessAnotherNotMeInMap(pl, msg);
            msg.cleanup();
        } catch (Exception e) {

        }
    }

    public void sendInfoPlayerEatPea(Player pl) {
        Message msg;
        try {
            msg = Service.gI().messageSubCommand((byte) 14);
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeInt(pl.nPoint.hp);
            msg.writer().writeByte(1);
            msg.writer().writeInt(pl.nPoint.hpMax);
            sendMessAnotherNotMeInMap(pl, msg);
            msg.cleanup();
        } catch (Exception e) {

        }
    }

    public void loginDe(MySession session, short second) {
        Message msg;
        try {
            msg = new Message(122);
            msg.writer().writeShort(second);
            session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void resetPoint(Player player, int x, int y) {
        Message msg;
        try {
            player.location.x = x;
            player.location.y = y;
            msg = new Message(46);
            msg.writer().writeShort(x);
            msg.writer().writeShort(y);
            player.sendMessage(msg);
            msg.cleanup();

        } catch (Exception e) {
        }
    }

    public void clearMap(Player player) {
        Message msg;
        try {
            msg = new Message(-22);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void switchToRegisterScr(ISession session) {
        try {
            Message message;
            try {
                message = new Message(42);
                message.writeByte(0);
                session.sendMessage(message);
                message.cleanup();
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
    }

    public void test(Player pl) {
        Message m;
        try {
            m = new Message(-126);
            m.writer().writeByte(1);
            m.writer().writeByte(1);
            m.writer().writeUTF("201023"); // con số cần ra
            m.writer().writeUTF("Cờ Bạc Con Cặc Đụ Má Mày"); // đéo biết là cái cc gì nma thiếu thì nó đ chạy ( sửa số khác cũng đc)
            pl.sendMessage(m);
            m.cleanup();
        } catch (Exception e) {
        }
    }

    public void tt(Player pl) {
        sendThongBaoOK(pl, "Thông tin nhân vật " + pl.name
                + "\nSức Mạnh: " + Util.getFormatNumber(pl.nPoint.power)
                + "\nChí Mạng: " + Util.getFormatNumber(pl.nPoint.crit)
                + "\nHp: " + Util.getFormatNumber(pl.nPoint.hp) + "/" + Util.getFormatNumber(pl.nPoint.hpMax)
                + "\nKi: " + Util.getFormatNumber(pl.nPoint.mp) + "/" + Util.getFormatNumber(pl.nPoint.mpMax)
                + "\nSức đánh: " + Util.getFormatNumber(pl.nPoint.dame)
                + "\nThông tin đệ tử " + pl.pet.name
                + "\n\nSức Mạnh: " + Util.getFormatNumber(pl.pet.nPoint.power)
                + "\nChí Mạng: " + Util.getFormatNumber(pl.pet.nPoint.crit)
                + "\nHp: " + Util.getFormatNumber(pl.pet.nPoint.hp) + "/" + Util.getFormatNumber(pl.pet.nPoint.hpMax)
                + "\nKi: " + Util.getFormatNumber(pl.pet.nPoint.mp) + "/" + Util.getFormatNumber(pl.pet.nPoint.mpMax)
                + "\nSức đánh: " + Util.getFormatNumber(pl.pet.nPoint.dame)
                + "\n Trùng Sinh"
        );
    }
     public void datlan(Player pl) {
         Boss oldBossClone = BossManager.gI()
                                        .getBossById(BossID.DUONG_TANG_HOTONG);
                                if (oldBossClone != null) {
                                
                                } else if (pl.nPoint.congduc > 1) {
                                      Service.gI().sendThongBaoOK(pl,
                                        "Yêu cầu 1 điểm công đức để hộ tống");
                                } else if (pl.haveDuongTang == true) {
                                   Service.gI().sendThongBaoOK(pl,
                                        "Bạn đang hộ tống mà");
                                } else {
                                    try {
                                        DuongTank dt = new DuongTank(BossID.DUONG_TANG_HOTONG,
                                                BossesData.DUONG_TANG_HO_TONG, pl.zone, pl.location.x - 20,
                                                pl.location.y);
                                        dt.playerTarger = pl;
                                        int[] map = { 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,33,24,25,26,27,28,29,30,31,32,5 };
                                        dt.mapCongDuc = map[Util.nextInt(map.length)];
                                        pl.haveDuongTang = true;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Logger.logException(Manager.class, e, "Lỗi tạo đường tăng");
                                    }
                                    pl.nPoint.congduc -= 1;
                                   
                                    if (pl.typePk == ConstPlayer.NON_PK) {
                                        PlayerService.gI().changeAndSendTypePK(pl, ConstPlayer.PK_ALL);
                                    }
                                    Service.getInstance().sendMoney(pl);
                                }
                            }

    public void showListPlayer(Player player) {
        Message msg;
        try {
            msg = new Message(-96);
            msg.writer().writeByte(1);
            msg.writer().writeUTF("Player Online : " + Client.gI().getPlayers().size() + " (" + TimeUtil.getTimeNow("HH:mm:ss") + ")");
            msg.writer().writeByte(Client.gI().getPlayers().size());
            for (int i = 0; i < Client.gI().getPlayers().size(); i++) {
                Player pl = Client.gI().getPlayers().get(i);
                msg.writer().writeInt(i + 1);
                msg.writer().writeInt((int) pl.id);
                msg.writer().writeShort(pl.getHead());
                if (player.getSession().version > 214) {
                    msg.writer().writeShort(-1);
                }
                msg.writer().writeShort(pl.getBody());
                msg.writer().writeShort(pl.getLeg());
                msg.writer().writeUTF(pl.name);
                int sl = InventoryServiceNew.gI().findItemBag(pl, (short) 457) == null ? 0 : InventoryServiceNew.gI().findItemBag(pl, (short) 457).quantity;
                msg.writer().writeUTF(pl.isAdmin() ? "Key" : "Member");
                msg.writer().writeUTF("HP: " + Util.numberToMoney(pl.nPoint.hpMax)
                        + "\nKI: " + Util.numberToMoney(pl.nPoint.mpMax)
                        + "\nSD: " + Util.numberToMoney(pl.nPoint.dame)
                        + "\nDEF: " + Util.numberToMoney(pl.nPoint.def)
                        + "\nCM: " + pl.nPoint.crit + "%"
                        + "\n[Map: " + pl.zone.map.mapName + "(" + pl.zone.map.mapId + ") " + "Khu: " + pl.zone.zoneId + "]");
            }
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chat(Player player, String text) {
        if (text.equals("a")) {
            BossManager.gI().showListBoss(player);
            return;

        }
        if (text.equals("xsmb")) {
            test(player);
            return;
        }
        if (text.equals("tt")) {
            tt(player);
            return;
        }
        if (text.equals("dắt lân")) {
            datlan(player);
            return;
        }
        
    
        
        if (text.equals("nvt")) {
            showListPlayer(player);
            return;
        }
        if (player.getSession() != null && player.isAdmin()) {
            if (text.equals("logskill")) {
                Service.gI().sendThongBao(player, player.playerSkill.skillSelect.coolDown + "");
                return;
            }
            if (text.equals("client")) {
                Client.gI().show(player);
            } else if (text.equals("m")) {
                sendThongBao(player, "Map " + player.zone.map.mapName + " (" + player.zone.map.mapId + ")");
                return;
            } else if (text.equals("vt")) {
                sendThongBao(player, player.location.x + " - " + player.location.y + "\n"
                        + player.zone.map.yPhysicInTop(player.location.x, player.location.y));
            }
            if (text.equals("nrnm")) {
                Service.gI().activeNamecShenron(player);
            }
            if (text.equals("skillxd")) {
                SkillService.gI().learSkillSpecial(player, Skill.SUPER_ANTOMIC);
                return;
            }
            if (text.equals("skilltd")) {
                SkillService.gI().learSkillSpecial(player, Skill.SUPER_KAME);
                return;
            }
            if (text.equals("skillnm")) {
                SkillService.gI().learSkillSpecial(player, Skill.MAFUBA);
                return;
            }
            if (text.equals("a")) {
                BossManager.gI().showListBoss(player);
                return;

            }
            if (text.equals("askill")) {
                List<Skill> skfix2 = new ArrayList<>();
                if (player.gender == 2) {
                    skfix2.add(SkillUtil.createSkill(4, 7));
                    skfix2.add(SkillUtil.createSkill(5, 7));
                    skfix2.add(SkillUtil.createSkill(8, 7));
                    skfix2.add(SkillUtil.createSkill(13, 7));
                    skfix2.add(SkillUtil.createSkill(14, 7));
                    skfix2.add(SkillUtil.createSkill(21, 7));
                    skfix2.add(SkillUtil.createSkill(23, 7));
                    skfix2.add(SkillUtil.createSkill(25, 7));
                    skfix2.add(SkillUtil.createSkill(19, 7));
                    player.playerSkill.skills = skfix2;
                    sendThongBao(player, "All Skill Thành Công, Hãy Học Lại Skill");
                    return;
                }
                if (player.gender == 0) { // td
                    skfix2.add(SkillUtil.createSkill(0, 7));
                    skfix2.add(SkillUtil.createSkill(1, 7));
                    skfix2.add(SkillUtil.createSkill(6, 7));
                    skfix2.add(SkillUtil.createSkill(9, 7));
                    skfix2.add(SkillUtil.createSkill(10, 7));
                    skfix2.add(SkillUtil.createSkill(20, 7));
                    skfix2.add(SkillUtil.createSkill(22, 7));
                    skfix2.add(SkillUtil.createSkill(19, 7));
                    skfix2.add(SkillUtil.createSkill(24, 7));
                    player.playerSkill.skills = skfix2;
                    sendThongBao(player, "All Skill Thành Công, Hãy Học Lại Skill");
                    return;
                }
                if (player.gender == 1) { // namek 2, 3, 7, 11, 12, 17, 18,26, 19}
                    skfix2.add(SkillUtil.createSkill(2, 7));
                    skfix2.add(SkillUtil.createSkill(3, 7));
                    skfix2.add(SkillUtil.createSkill(7, 7));
                    skfix2.add(SkillUtil.createSkill(11, 7));
                    skfix2.add(SkillUtil.createSkill(12, 7));
                    skfix2.add(SkillUtil.createSkill(17, 7));
                    skfix2.add(SkillUtil.createSkill(18, 7));
                    skfix2.add(SkillUtil.createSkill(19, 7));
                    skfix2.add(SkillUtil.createSkill(26, 7));
                    player.playerSkill.skills = skfix2;
                    sendThongBao(player, "All Skill Thành Công, Hãy Học Lại Skill");
                    return;
                }
            }
            if (text.equals("loadsv")) {
                        Manager.loadPart();
                        DataGame.updateData(player.getSession());
                return;
            }
            if (text.equals("hskill")) {
                Service.getInstance().releaseCooldownSkill(player);
                return;
            }
            OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
            long totalPhysicalMemorySize = operatingSystemMXBean.getTotalPhysicalMemorySize();
            long freePhysicalMemorySize = operatingSystemMXBean.getFreePhysicalMemorySize();
            long usedPhysicalMemory = totalPhysicalMemorySize - freePhysicalMemorySize;
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String cpuUsage = decimalFormat.format(operatingSystemMXBean.getSystemCpuLoad() * 100);
            String usedPhysicalMemoryStr = decimalFormat.format((double) usedPhysicalMemory / (1024 * 1024 * 1024));
            if (text.equals("admin")) {
                NpcService.gI().createMenuConMeo(player, ConstNpc.MENU_ADMIN, 12691, "|7| Admin Trùng Sinh\b|2|Ánh Ngọc Zalo0343214443\b|4| Người Đang Chơi: " + Client.gI().getPlayers().size() + "\n" + "|8|Current thread: " + (Thread.activeCount() - ServerManager.gI().threadMap) + "\n"
                        + "|7|SeeSion " + GirlkunSessionManager.gI().getSessions().size()
                        + "\n|7|CPU : " + cpuUsage + "/100%"
                        + "\nRAM : " + usedPhysicalMemoryStr + "/8GB"
                        + "\n|7|Time start server: " + ServerManager.timeStart,
                        "Menu Admin", "Call Boss", "Buff Item", "GIFTCODE", "Nạp", "Thông Báo All","Thay Exp","Thu Item","Đóng");
                return;
            } else if (text.startsWith("upp")) {
                try {
                    long power = Long.parseLong(text.replaceAll("upp", ""));
                    addSMTN(player.pet, (byte) 2, power, false);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (text.startsWith("up")) {
                try {
                    long power = Long.parseLong(text.replaceAll("up", ""));
                    addSMTN(player, (byte) 2, power, false);
                    return;
                } catch (Exception e) {
                }

            } else if (text.startsWith("m")) {
                try {
                    int mapId = Integer.parseInt(text.replace("m", ""));
                    ChangeMapService.gI().changeMapInYard(player, mapId, -1, -1);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (text.startsWith("i")) {
                try {
                    String[] item = text.replace("i", "").split(" ");
                    if (Short.parseShort(item[0]) <= 2047) {
                        Item it = ItemService.gI().createNewItem((short) Short.parseShort(item[0]));
                        if (it != null && item.length == 1) {
                            InventoryServiceNew.gI().addItemBag(player, it);
                            InventoryServiceNew.gI().sendItemBags(player);
                            Service.gI().sendThongBao(player, "Đã nhận được " + it.template.name);
                        } else if (it != null && item.length == 2 && Client.gI().getPlayer(String.valueOf(item[1])) == null) {
                            it.quantity = Integer.parseInt(item[1]);
                            InventoryServiceNew.gI().addItemBag(player, it);
                            InventoryServiceNew.gI().sendItemBags(player);
                            Service.gI().sendThongBao(player, "Đã nhận được x" + Integer.valueOf(item[1]) + " " + it.template.name);
                        } else if (it != null && item.length == 2 && Client.gI().getPlayer(String.valueOf(item[1])) != null) {
                            String name = String.valueOf(item[1]);
                            InventoryServiceNew.gI().addItemBag(Client.gI().getPlayer(name), it);
                            InventoryServiceNew.gI().sendItemBags(Client.gI().getPlayer(name));
                            Service.gI().sendThongBao(player, "Đã buff " + it.template.name + " đến player " + name);
                            Service.gI().sendThongBao(Client.gI().getPlayer(name), "Đã nhận được " + it.template.name);
                        } else if (it != null && item.length == 3 && Client.gI().getPlayer(String.valueOf(item[2])) != null) {
                            String name = String.valueOf(item[2]);
                            it.quantity = Integer.parseInt(item[1]);
                            InventoryServiceNew.gI().addItemBag(Client.gI().getPlayer(name), it);
                            InventoryServiceNew.gI().sendItemBags(Client.gI().getPlayer(name));
                            Service.gI().sendThongBao(player, "Đã buff x" + Integer.valueOf(item[1]) + " " + it.template.name + " đến player " + name);
                            Service.gI().sendThongBao(Client.gI().getPlayer(name), "Đã nhận được x" + Integer.valueOf(item[1]) + " " + it.template.name);
                        } else {
                            Service.gI().sendThongBao(player, "Không tìm thấy player");
                        }
                    } else {
                        Service.gI().sendThongBao(player, "Không tìm thấy item");
                    }
                } catch (NumberFormatException e) {
                    Service.gI().sendThongBao(player, "Không tìm thấy player");
                }
                return;
            } else if (text.equals("buff")) {
                Input.gI().createFormSenditem(player);
                 } else if (text.contains("tsm ")) {
                    long power = Long.parseLong(text.replaceAll("tsm ", ""));
                    player.nPoint.power += (long) power;
                    addSMTN(player, (byte) 2, power, false);
                    sendThongBao(player, "Bạn vừa tăng thành công " + power + " sức mạnh");
                    return;
                } else if (text.contains("gsm ")) {
                    long power = Long.parseLong(text.replaceAll("gsm ", ""));
                    player.nPoint.power -= (long) power;
                    addSMTN(player, (byte) 2, -power, false);
                    sendThongBao(player, "Bạn vừa giảm thành công " + power + " sức mạnh");
                    return;
                } else if (text.contains("ttn ")) {
                    long power = Long.parseLong(text.replaceAll("ttn ", ""));
                    player.nPoint.tiemNang += (long) power;
                    addSMTN(player, (byte) 2, power, false);
                    sendThongBao(player, "Bạn vừa tăng thành công " + power + " tiềm năng");
                    return;
                    
                   
            } else if(text.equals("loadshop")){
                Manager.gI().loadShop();
                sendThongBao(player,"RELOAD SHOP SUCCESS!");
                return;
                } else if (text.equals("askill")) {
            List<Skill> skfix2 = new ArrayList<>();
                switch (player.gender) {
                    case 0:
                        skfix2.add(SkillUtil.createSkill(0,7));
                        skfix2.add(SkillUtil.createSkill(1,7));
                        skfix2.add(SkillUtil.createSkill(6,7));
                        skfix2.add(SkillUtil.createSkill(9,7));
                        skfix2.add(SkillUtil.createSkill(10,7));
                        skfix2.add(SkillUtil.createSkill(20,7));
                        skfix2.add(SkillUtil.createSkill(22,7));
                        skfix2.add(SkillUtil.createSkill(19,7));
                        skfix2.add(SkillUtil.createSkill(24,7));
                        player.playerSkill.skills = skfix2;
                        sendThongBao(player, "All Skill Thành Công, Hãy Học Lại Skill");
                    return;
                    case 1: // namek 2, 3, 7, 11, 12, 17, 18,26, 19}
                        skfix2.add(SkillUtil.createSkill(2,7));
                        skfix2.add(SkillUtil.createSkill(3,7));
                        skfix2.add(SkillUtil.createSkill(7,7));
                        skfix2.add(SkillUtil.createSkill(11,7));
                        skfix2.add(SkillUtil.createSkill(12,7));
                        skfix2.add(SkillUtil.createSkill(17,7));
                        skfix2.add(SkillUtil.createSkill(18,7));
                        skfix2.add(SkillUtil.createSkill(19,7));
                        skfix2.add(SkillUtil.createSkill(26,7));
                        player.playerSkill.skills = skfix2;
                        sendThongBao(player, "All Skill Thành Công, Hãy Học Lại Skill");
                    return;
                    case 2:
                        skfix2.add(SkillUtil.createSkill(4,7));
                        skfix2.add(SkillUtil.createSkill(5,7));
                        skfix2.add(SkillUtil.createSkill(8,7));
                        skfix2.add(SkillUtil.createSkill(13,7));
                        skfix2.add(SkillUtil.createSkill(14,7));
                        skfix2.add(SkillUtil.createSkill(21,7));
                        skfix2.add(SkillUtil.createSkill(23,7));
                        skfix2.add(SkillUtil.createSkill(25,7));
                        skfix2.add(SkillUtil.createSkill(19,7));
                        player.playerSkill.skills = skfix2;
                        sendThongBao(player, "All Skill Thành Công, Hãy Học Lại Skill");
                    return;
                }
                } else if (text.contains("gtn ")) {
                    long power = Long.parseLong(text.replaceAll("gtn ", ""));
                    player.nPoint.tiemNang -= (long) power;
                    addSMTN(player, (byte) 2, -power, false);
                    sendThongBao(player, "Bạn vừa giảm thành công " + power + " tiềm năng");
                    return;
                    }  else if (text.equals("nnv") && player.playerTask.taskMain.id <= 31) {
                    TaskService.gI().sendNextTaskMain(player);
                    return;
                } else if (text.equals("bnv") && player.playerTask.taskMain.id >= 1) {
                    TaskService.gI().sendBackTaskMain(player);
                    return;
                    } else if (text.equals("info") || text.equals("tt") || text.equals("thongtin")) {
            NpcService.gI().createMenuConMeo(player, ConstNpc.INFO, 12639,"|7|- •⊹٭DragonBall Kamui٭⊹• -\n"
                + "|2|Thông Tin Tổng\nChào bạn : " + player.name + " | ID: ("+player.id+") | " + "Map : " + player.zone.map.mapName +"\n" 
                + "Số dư : "+ Util.format(player.getSession().vnd) + " VNĐ" 
                
                + (player.vip==1?" [VIP]":player.vip==2?" [VIP2]":player.vip==3?" [VIP3]":player.vip==4?" [SVIP]":"")
                + "\n|-1|[DANH HIỆU] xem thông tin danh hiệu đang sở hữu\n"
                + "[ĐỆ TỬ] xem thông tin đệ tử\n"
                + "[NHÂN VẬT] xem thông tin nhân vật, nhập giftcode và các chức năng auto",
                "THÔNG TIN\nNHÂN VẬT","THÔNG TIN\nĐỆ TỬ");
            return;
                } else if (text.startsWith("call ")) {
                try {
                    String getID = text.replace("call ", "");
                        Service.gI().sendThongBao(player, "Call Boss\n|7|(" + BossManager.gI().getBossById(Integer.parseInt(getID)).name + ")\nSuccess!");
                        BossManager.gI().createBoss((int)BossManager.gI().getBossById(Integer.parseInt(getID)).id);
                } catch (NumberFormatException e) {
                }
                return;
                
            } else if (text.equals("op")) {
                Input.gI().createFormSenditem1(player);
            } else if (text.equals("skh")) {
                Input.gI().createFormSenditem2(player);
            } else if (text.equals("thread")) {
                sendThongBao(player, "Current thread: " + Thread.activeCount());
                Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
                return;
            } else if (text.startsWith("s")) {
                try {
                    player.nPoint.speed = (byte) Integer.parseInt(text.substring(1));
                    point(player);
                    return;
                } catch (Exception e) {
                }
            }
        }

        if (text.equals(
                "fixskillall")) {
            List<Skill> skfix = new ArrayList<>();
            if (player.gender == 0) { // td
                skfix.add(SkillUtil.createSkill(0, 1));
                skfix.add(SkillUtil.createSkillLevel0(1));
                skfix.add(SkillUtil.createSkillLevel0(6));
                skfix.add(SkillUtil.createSkillLevel0(9));
                skfix.add(SkillUtil.createSkillLevel0(10));
                skfix.add(SkillUtil.createSkillLevel0(20));
                skfix.add(SkillUtil.createSkillLevel0(22));
                skfix.add(SkillUtil.createSkillLevel0(19));
                skfix.add(SkillUtil.createSkillLevel0(24));
                player.playerSkill.skills = skfix;
                sendThongBao(player, "Fix Skill Thành Công, Hãy Học Lại Skill");
                return;
            }
            if (player.gender == 1) { // namek 2, 3, 7, 11, 12, 17, 18,26, 19}
                skfix.add(SkillUtil.createSkill(2, 1));
                skfix.add(SkillUtil.createSkillLevel0(3));
                skfix.add(SkillUtil.createSkillLevel0(7));
                skfix.add(SkillUtil.createSkillLevel0(11));
                skfix.add(SkillUtil.createSkillLevel0(12));
                skfix.add(SkillUtil.createSkillLevel0(17));
                skfix.add(SkillUtil.createSkillLevel0(18));
                skfix.add(SkillUtil.createSkillLevel0(19));
                skfix.add(SkillUtil.createSkillLevel0(26));
                player.playerSkill.skills = skfix;
                sendThongBao(player, "Fix Skill Thành Công, Hãy Học Lại Skill");
                return;
            }
            if (player.gender == 2) { // xd 4, 5, 8, 13, 14, 21, 23, 25, 19}
                skfix.add(SkillUtil.createSkill(4, 1));
                skfix.add(SkillUtil.createSkillLevel0(5));
                skfix.add(SkillUtil.createSkillLevel0(8));
                skfix.add(SkillUtil.createSkillLevel0(13));
                skfix.add(SkillUtil.createSkillLevel0(14));
                skfix.add(SkillUtil.createSkillLevel0(21));
                skfix.add(SkillUtil.createSkillLevel0(23));
                skfix.add(SkillUtil.createSkillLevel0(19));
                skfix.add(SkillUtil.createSkillLevel0(25));
                player.playerSkill.skills = skfix;
                sendThongBao(player, "Fix Skill Thành Công, Hãy Học Lại Skill");
                return;
            }
        }

        if (text.startsWith(
                "ten con la ")) {
            PetService.gI().changeNamePet(player, text.replaceAll("ten con la ", ""));
        }
        if (player.pet
                != null) {
            if (text.equals("di theo") || text.equals("follow")) {
                player.pet.changeStatus(Pet.FOLLOW);
            } else if (text.equals("bao ve") || text.equals("protect")) {
                player.pet.changeStatus(Pet.PROTECT);
            } else if (text.equals("tan cong") || text.equals("attack")) {
                player.pet.changeStatus(Pet.ATTACK);
            } else if (text.equals("ve nha") || text.equals("go home")) {
                player.pet.changeStatus(Pet.GOHOME);
            } else if (text.equals("bien hinh")) {
                player.pet.transform();
            }
        }

        if (text.length()
                > 100) {
            text = text.substring(0, 100);
        }
        Message msg;

        try {
            msg = new Message(44);
            msg.writer().writeInt((int) player.id);
            msg.writer().writeUTF(text);
            sendMessAllPlayerInMap(player, msg);
            msg.cleanup();
        } catch (Exception e) {
            Logger.logException(Service.class, e);
        }
    }

    public void chatJustForMe(Player me, Player plChat, String text) {
        Message msg;
        try {
            msg = new Message(44);
            msg.writer().writeInt((int) plChat.id);
            msg.writer().writeUTF(text);
            me.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void Transport(Player pl) {
        Message msg = null;
        try {
            msg = new Message(-105);
            msg.writer().writeShort(pl.maxTime);
            msg.writer().writeByte(pl.type);
            pl.sendMessage(msg);
        } catch (Exception e) {
        } finally {
            if (msg != null) {
                msg.cleanup();
                msg = null;
            }
        }
    }

    public long exp_level1(long sucmanh) {
        if (sucmanh < 3000) {
            return 3000;
        } else if (sucmanh < 15000) {
            return 15000;
        } else if (sucmanh < 40000) {
            return 40000;
        } else if (sucmanh < 90000) {
            return 90000;
        } else if (sucmanh < 170000) {
            return 170000;
        } else if (sucmanh < 340000) {
            return 340000;
        } else if (sucmanh < 700000) {
            return 700000;
        } else if (sucmanh < 1500000) {
            return 1500000;
        } else if (sucmanh < 15000000) {
            return 15000000;
        } else if (sucmanh < 150000000) {
            return 150000000;
        } else if (sucmanh < 1500000000) {
            return 1500000000;
        } else if (sucmanh < 5000000000L) {
            return 5000000000L;
        } else if (sucmanh < 10000000000L) {
            return 10000000000L;
        } else if (sucmanh < 40000000000L) {
            return 40000000000L;
        } else if (sucmanh < 50010000000L) {
            return 50010000000L;
        } else if (sucmanh < 60010000000L) {
            return 60010000000L;
        } else if (sucmanh < 70010000000L) {
            return 70010000000L;
        } else if (sucmanh < 80010000000L) {
            return 80010000000L;
        } else if (sucmanh < 200010000000L) {
            return 200010000000L;
        }
        return 1000;
    }

    public void point(Player player) {
        player.nPoint.calPoint();
        Send_Info_NV(player);
        if (!player.isPet && !player.isBoss && !player.isNewPet) {
            Message msg;
            try {
                msg = new Message(-42);
                msg.writer().writeInt(player.nPoint.hpg);
                msg.writer().writeInt(player.nPoint.mpg);
                msg.writer().writeInt(player.nPoint.dameg);
                msg.writer().writeInt(player.nPoint.hpMax);// hp full
                msg.writer().writeInt(player.nPoint.mpMax);// mp full
                msg.writer().writeInt(player.nPoint.hp);// hp
                msg.writer().writeInt(player.nPoint.mp);// mp
                msg.writer().writeByte(player.nPoint.speed);// speed
                msg.writer().writeByte(20);
                msg.writer().writeByte(20);
                msg.writer().writeByte(1);
                msg.writer().writeInt(player.nPoint.dame);// dam base
                msg.writer().writeInt(player.nPoint.def);// def full
                msg.writer().writeByte(player.nPoint.crit);// crit full
                msg.writer().writeLong(player.nPoint.tiemNang);
                msg.writer().writeShort(100);
                msg.writer().writeShort(player.nPoint.defg);
                msg.writer().writeByte(player.nPoint.critg);
                player.sendMessage(msg);
                msg.cleanup();

            } catch (Exception e) {
                Logger.logException(Service.class, e);
            }
        }
    }

    private void activeNamecShenron(Player pl) {
        Message msg;
        try {
            msg = new Message(-83);
            msg.writer().writeByte(0);

            msg.writer().writeShort(pl.zone.map.mapId);
            msg.writer().writeShort(pl.zone.map.bgId);
            msg.writer().writeByte(pl.zone.zoneId);
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeUTF("");
            msg.writer().writeShort(pl.location.x);
            msg.writer().writeShort(pl.location.y);
            msg.writer().writeByte(1);
            //   lastTimeShenronWait = System.currentTimeMillis();
            //   isShenronAppear = true;

            Service.gI().sendMessAllPlayerInMap(pl, msg);
        } catch (Exception e) {
        }
    }

    public void player(Player pl) {
        if (pl == null) {
            return;
        }
        Message msg;
        try {
            msg = messageSubCommand((byte) 0);
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeByte(pl.playerTask.taskMain.id);
            msg.writer().writeByte(pl.gender);
            msg.writer().writeShort(pl.head);
            msg.writer().writeUTF(pl.name);
            msg.writer().writeByte(0); //cPK
            msg.writer().writeByte(pl.typePk);
            msg.writer().writeLong(pl.nPoint.power);
            msg.writer().writeShort(0);
            msg.writer().writeShort(0);
            msg.writer().writeByte(pl.gender);
            //--------skill---------

            ArrayList<Skill> skills = (ArrayList<Skill>) pl.playerSkill.skills;

            msg.writer().writeByte(pl.playerSkill.getSizeSkill());

            for (Skill skill : skills) {
                if (skill.skillId != -1) {
                    msg.writer().writeShort(skill.skillId);
                }
            }

            //---vang---luong--luongKhoa
            if (pl.getSession().version >= 214) {
                msg.writer().writeLong(pl.inventory.gold);
            } else {
                msg.writer().writeInt((int) pl.inventory.gold);
            }
            msg.writer().writeInt(pl.inventory.ruby);
            msg.writer().writeInt(pl.inventory.gem);

            //--------itemBody---------
            ArrayList<Item> itemsBody = (ArrayList<Item>) pl.inventory.itemsBody;
            msg.writer().writeByte(itemsBody.size());
            for (Item item : itemsBody) {
                if (!item.isNotNullItem()) {
                    msg.writer().writeShort(-1);
                } else {
                    msg.writer().writeShort(item.template.id);
                    msg.writer().writeInt(item.quantity);
                    msg.writer().writeUTF(item.getInfo());
                    msg.writer().writeUTF(item.getContent());
                    List<ItemOption> itemOptions = item.itemOptions;
                    msg.writer().writeByte(itemOptions.size());
                    for (ItemOption itemOption : itemOptions) {
                        msg.writer().writeByte(itemOption.optionTemplate.id);
                        msg.writer().writeShort(itemOption.param);
                    }
                }

            }

            //--------itemBag---------
            ArrayList<Item> itemsBag = (ArrayList<Item>) pl.inventory.itemsBag;
            msg.writer().writeByte(itemsBag.size());
            for (int i = 0; i < itemsBag.size(); i++) {
                Item item = itemsBag.get(i);
                if (!item.isNotNullItem()) {
                    msg.writer().writeShort(-1);
                } else {
                    msg.writer().writeShort(item.template.id);
                    msg.writer().writeInt(item.quantity);
                    msg.writer().writeUTF(item.getInfo());
                    msg.writer().writeUTF(item.getContent());
                    List<ItemOption> itemOptions = item.itemOptions;
                    msg.writer().writeByte(itemOptions.size());
                    for (ItemOption itemOption : itemOptions) {
                        msg.writer().writeByte(itemOption.optionTemplate.id);
                        msg.writer().writeShort(itemOption.param);
                    }
                }

            }

            //--------itemBox---------
            ArrayList<Item> itemsBox = (ArrayList<Item>) pl.inventory.itemsBox;
            msg.writer().writeByte(itemsBox.size());
            for (int i = 0; i < itemsBox.size(); i++) {
                Item item = itemsBox.get(i);
                if (!item.isNotNullItem()) {
                    msg.writer().writeShort(-1);
                } else {
                    msg.writer().writeShort(item.template.id);
                    msg.writer().writeInt(item.quantity);
                    msg.writer().writeUTF(item.getInfo());
                    msg.writer().writeUTF(item.getContent());
                    List<ItemOption> itemOptions = item.itemOptions;
                    msg.writer().writeByte(itemOptions.size());
                    for (ItemOption itemOption : itemOptions) {
                        msg.writer().writeByte(itemOption.optionTemplate.id);
                        msg.writer().writeShort(itemOption.param);
                    }
                }
            }
            //-----------------
            DataGame.sendHeadAvatar(msg);
            //-----------------
            msg.writer().writeShort(514); //char info id - con chim thông báo
            msg.writer().writeShort(515); //char info id
            msg.writer().writeShort(12691); //char info id
            msg.writer().writeByte(pl.fusion.typeFusion != ConstPlayer.NON_FUSION ? 1 : 0); //nhập thể
            msg.writer().writeInt(333); //deltatime
            msg.writer().writeByte(pl.isNewMember ? 1 : 0); //is new member
            msg.writer().writeShort(pl.getAura()); //idauraeff
            msg.writer().writeByte(pl.getEffFront());
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            Logger.logException(Service.class, e);
        }
    }

    public Message messageNotLogin(byte command) throws IOException {
        Message ms = new Message(-29);
        ms.writer().writeByte(command);
        return ms;
    }

    public Message messageNotMap(byte command) throws IOException {
        Message ms = new Message(-28);
        ms.writer().writeByte(command);
        return ms;
    }

    public Message messageSubCommand(byte command) throws IOException {
        Message ms = new Message(-30);
        ms.writer().writeByte(command);
        return ms;
    }

    public void addSMTN(Player player, byte type, long param, boolean isOri) {
        if (player.isPet) {
            player.nPoint.powerUp(param);
            player.nPoint.tiemNangUp(param);
            Player master = ((Pet) player).master;

            param = master.nPoint.calSubTNSM(param);
            master.nPoint.powerUp(param);
            master.nPoint.tiemNangUp(param);
            addSMTN(master, type, param, true);
        } else {
            if (player.nPoint.power > player.nPoint.getPowerLimit()) {
                return;
            }
            switch (type) {
                case 1:
                    player.nPoint.tiemNangUp(param);
                    break;
                case 2:
                    player.nPoint.powerUp(param);
                    player.nPoint.tiemNangUp(param);
                    break;
                default:
                    player.nPoint.powerUp(param);
                    break;
            }
            PlayerService.gI().sendTNSM(player, type, param);
            if (isOri) {
                if (player.clan != null) {
                    player.clan.addSMTNClan(player, param);
                }
            }
        }
    }

    //    public void congTiemNang(Player pl, byte type, int tiemnang) {
//        Message msg;
//        try {
//            msg = new Message(-3);
//            msg.writer().writeByte(type);// 0 là cộng sm, 1 cộng tn, 2 là cộng cả 2
//            msg.writer().writeInt(tiemnang);// số tn cần cộng
//            if (!pl.isPet) {
//                pl.sendMessage(msg);
//            } else {
//                ((Pet) pl).master.nPoint.powerUp(tiemnang);
//                ((Pet) pl).master.nPoint.tiemNangUp(tiemnang);
//                ((Pet) pl).master.sendMessage(msg);
//            }
//            msg.cleanup();
//            switch (type) {
//                case 1:
//                    pl.nPoint.tiemNangUp(tiemnang);
//                    break;
//                case 2:
//                    pl.nPoint.powerUp(tiemnang);
//                    pl.nPoint.tiemNangUp(tiemnang);
//                    break;
//                default:
//                    pl.nPoint.powerUp(tiemnang);
//                    break;
//            }
//        } catch (Exception e) {
//
//        }
//    }
    public String get_HanhTinh(int hanhtinh) {
        switch (hanhtinh) {
            case 0:
                return "Trái Đất";
            case 1:
                return "Namếc";
            case 2:
                return "Xayda";
            default:
                return "";
        }
    }

    public String getCurrStrLevel(Player pl) {
        long sucmanh = pl.nPoint.power;
        if (sucmanh < 3000) {
            return "Tân thủ";
        } else if (sucmanh < 15000) {
            return "Tập sự sơ cấp";
        } else if (sucmanh < 40000) {
            return "Tập sự trung cấp";
        } else if (sucmanh < 90000) {
            return "Tập sự cao cấp";
        } else if (sucmanh < 170000) {
            return "Tân binh";
        } else if (sucmanh < 340000) {
            return "Chiến binh";
        } else if (sucmanh < 700000) {
            return "Chiến binh cao cấp";
        } else if (sucmanh < 1500000) {
            return "Vệ binh";
        } else if (sucmanh < 15000000) {
            return "Vệ binh hoàng gia";
        } else if (sucmanh < 150000000) {
            return "Siêu " + get_HanhTinh(pl.gender) + " cấp 1";
        } else if (sucmanh < 1500000000) {
            return "Siêu " + get_HanhTinh(pl.gender) + " cấp 2";
        } else if (sucmanh < 5000000000L) {
            return "Siêu " + get_HanhTinh(pl.gender) + " cấp 3";
        } else if (sucmanh < 10000000000L) {
            return "Siêu " + get_HanhTinh(pl.gender) + " cấp 4";
        } else if (sucmanh < 40000000000L) {
            return "Thần " + get_HanhTinh(pl.gender) + " cấp 1";
        } else if (sucmanh < 50010000000L) {
            return "Thần " + get_HanhTinh(pl.gender) + " cấp 2";
        } else if (sucmanh < 60010000000L) {
            return "Thần " + get_HanhTinh(pl.gender) + " cấp 3";
        } else if (sucmanh < 70010000000L) {
            return "Giới Vương Thần cấp 11";
        } else if (sucmanh < 80010000000L) {
            return "Giới Vương Thần cấp 2";
        } else if (sucmanh < 100010000000L) {
            return "Giới Vương Thần cấp 3";
        } else if (sucmanh < 150010000000L) {
            return "Vương Diệt Thần";
        } else if (sucmanh < 21100010000000L) {
            return "Hạn Diệt Thần";
        }
        return "Thần Huỷ Diệt cấp 2";
    }

    public int getCurrLevel(Player pl) {
        long sucmanh = pl.nPoint.power;
        if (sucmanh < 3000) {
            return 1;
        } else if (sucmanh < 15000) {
            return 2;
        } else if (sucmanh < 40000) {
            return 3;
        } else if (sucmanh < 90000) {
            return 4;
        } else if (sucmanh < 170000) {
            return 5;
        } else if (sucmanh < 340000) {
            return 6;
        } else if (sucmanh < 700000) {
            return 7;
        } else if (sucmanh < 1500000) {
            return 8;
        } else if (sucmanh < 15000000) {
            return 9;
        } else if (sucmanh < 150000000) {
            return 10;
        } else if (sucmanh < 1500000000) {
            return 11;
        } else if (sucmanh < 5000000000L) {
            return 12;
        } else if (sucmanh < 10000000000L) {
            return 13;
        } else if (sucmanh < 40000000000L) {
            return 14;
        } else if (sucmanh < 50010000000L) {
            return 15;
        } else if (sucmanh < 60010000000L) {
            return 16;
        } else if (sucmanh < 70010000000L) {
            return 17;
        } else if (sucmanh < 80010000000L) {
            return 18;
        } else if (sucmanh < 100010000000L) {
            return 19;
        } else if (sucmanh < 150010000000L) {
            return 20;
        } else if (sucmanh < 2100010000000L) {
            return 21;
        }
        return 21;
    }

    public void hsChar(Player pl, int hp, int mp) {
        Message msg;
        try {
            pl.setJustRevivaled();
            pl.nPoint.setHp(hp);
            pl.nPoint.setMp(mp);
            if (!pl.isPet && !pl.isNewPet) {
                msg = new Message(-16);
                pl.sendMessage(msg);
                msg.cleanup();
                PlayerService.gI().sendInfoHpMpMoney(pl);
            }

            msg = messageSubCommand((byte) 15);
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeInt(hp);
            msg.writer().writeInt(mp);
            msg.writer().writeShort(pl.location.x);
            msg.writer().writeShort(pl.location.y);
            sendMessAllPlayerInMap(pl, msg);
            msg.cleanup();

            Send_Info_NV(pl);
            PlayerService.gI().sendInfoHpMp(pl);

        } catch (Exception e) {
            Logger.logException(Service.class, e);
        }
    }

    public void charDie(Player pl) {
        Message msg;
        try {
            if (!pl.isPet && !pl.isNewPet) {
                msg = new Message(-17);
                msg.writer().writeByte((int) pl.id);
                msg.writer().writeShort(pl.location.x);
                msg.writer().writeShort(pl.location.y);
                pl.sendMessage(msg);
                msg.cleanup();
            } else if (pl.isPet) {
                ((Pet) pl).lastTimeDie = System.currentTimeMillis();
            }
            if (!pl.isPet && !pl.isBoss && pl.idNRNM != -1) {
                ItemMap itemMap = new ItemMap(pl.zone, pl.idNRNM, 1, pl.location.x, pl.location.y, -1);
                Service.gI().dropItemMap(pl.zone, itemMap);
                NgocRongNamecService.gI().pNrNamec[pl.idNRNM - 353] = "";
                NgocRongNamecService.gI().idpNrNamec[pl.idNRNM - 353] = -1;
                pl.idNRNM = -1;
                PlayerService.gI().changeAndSendTypePK(pl, ConstPlayer.NON_PK);
                Service.gI().sendFlagBag(pl);
            }
            if (pl.zone.map.mapId == 51) {
                ChangeMapService.gI().changeMapBySpaceShip(pl, 21 + pl.gender, 0, -1);
            }
            msg = new Message(-8);
            msg.writer().writeShort((int) pl.id);
            msg.writer().writeByte(0); //cpk
            msg.writer().writeShort(pl.location.x);
            msg.writer().writeShort(pl.location.y);
            sendMessAnotherNotMeInMap(pl, msg);
            msg.cleanup();

        } catch (Exception e) {
            Logger.logException(Service.class, e);
        }
    }

    public void attackMob(Player pl, int mobId) {
        if (pl != null && pl.zone != null) {
            for (Mob mob : pl.zone.mobs) {
                if (mob.id == mobId) {
                    SkillService.gI().useSkill(pl, null, mob, null);
                    break;
                }
            }
        }
    }
public void SendMsgUpdateHoaDa(Player player, byte typead, byte typeTar, byte type) {
        try {
            Message message = new Message(-124);
            message.writer().writeByte(typead);
            message.writer().writeByte(typeTar);
            if (typead == 2) {
                message.writer().writeInt((int) player.id);
            }
            message.writer().writeByte(type);
            message.writer().writeInt((int) player.id);
            sendMessAllPlayerInMap(player, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Send_Caitrang(Player player) {
        if (player != null) {
            Message msg;
            try {
                msg = new Message(-90);
                msg.writer().writeByte(1);// check type
                msg.writer().writeInt((int) player.id); //id player
                short head = player.getHead();
                short body = player.getBody();
                short leg = player.getLeg();

                msg.writer().writeShort(head);//set head
                msg.writer().writeShort(body);//setbody
                msg.writer().writeShort(leg);//set leg
                msg.writer().writeByte(player.effectSkill.isMonkey ? 1 : 0);//set khỉ
                sendMessAllPlayerInMap(player, msg);
                msg.cleanup();

            } catch (Exception e) {
                Logger.logException(Service.class, e);
            }
        }
    }

    public void setNotMonkey(Player player) {
        Message msg;
        try {
            msg = new Message(-90);
            msg.writer().writeByte(-1);
            msg.writer().writeInt((int) player.id);
            Service.gI().sendMessAllPlayerInMap(player, msg);
            msg.cleanup();

        } catch (Exception e) {
            Logger.logException(Service.class, e);
        }
    }

    public void sendFlagBag(Player pl) {
        Message msg;
        try {
            int flagbag = pl.getFlagBag();
            if (pl.isPl() && pl.getSession().version >= 222) {
                switch (flagbag) {
                    case 143:
                        flagbag = 205;
                        break;
                    case 2010:
                        flagbag = 246;
                        break;
                }
            }
            msg = new Message(-64);
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeByte(flagbag);
            sendMessAllPlayerInMap(pl, msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendThongBaoOK(Player pl, String text) {
        if (pl.isPet || pl.isNewPet) {
            return;
        }
        Message msg;
        try {
            msg = new Message(-26);
            msg.writer().writeUTF(text);
            pl.sendMessage(msg);
            msg.cleanup();

        } catch (Exception e) {
            Logger.logException(Service.class, e);
        }
    }

    public void sendThongBaoOK(MySession session, String text) {
        Message msg;
        try {
            msg = new Message(-26);
            msg.writer().writeUTF(text);
            session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendThongBaoAllPlayer(String thongBao) {
        Message msg;
        try {
            msg = new Message(-25);
            msg.writer().writeUTF(thongBao);
            this.sendMessAllPlayer(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendBigMessage(Player player, int iconId, String text) {
        try {
            Message msg;
            msg = new Message(-70);
            msg.writer().writeShort(iconId);
            msg.writer().writeUTF(text);
            msg.writer().writeByte(0);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (IOException e) {

        }
    }
public void ChatAll(int iconId, String text) {
        Message msg;
        try {
            msg = new Message(-70);
            msg.writer().writeShort(iconId);
            msg.writer().writeUTF(text);
            msg.writer().writeByte(0);
            this.sendMessAllPlayer(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }
    public void sendThongBaoFromAdmin(Player player, String text) {
        sendBigMessage(player, 12691, text);
    }

    public void sendThongBao(Player pl, String thongBao) {
        Message msg;
        try {
            msg = new Message(-25);
            msg.writer().writeUTF(thongBao);
            pl.sendMessage(msg);
            msg.cleanup();

        } catch (Exception e) {
        }
    }

    public void sendThongBao(List<Player> pl, String thongBao) {
        for (int i = 0; i < pl.size(); i++) {
            Player ply = pl.get(i);
            if (ply != null) {
                this.sendThongBao(ply, thongBao);
            }
        }
    }

    public void sendMoney(Player pl) {
        Message msg;
        try {
            msg = new Message(6);
            if (pl.getSession().version >= 214) {
                msg.writer().writeLong(pl.inventory.gold);
            } else {
                msg.writer().writeInt((int) pl.inventory.gold);
            }
            msg.writer().writeInt(pl.inventory.gem);
            msg.writer().writeInt(pl.inventory.ruby);
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {

        }
    }

    public void sendToAntherMePickItem(Player player, int itemMapId) {
        Message msg;
        try {
            msg = new Message(-19);
            msg.writer().writeShort(itemMapId);
            msg.writer().writeInt((int) player.id);
            sendMessAllPlayerIgnoreMe(player, msg);
            msg.cleanup();
        } catch (Exception e) {

        }
    }

    public static final int[] flagTempId = {363, 364, 365, 366, 367, 368, 369, 370, 371, 519, 520, 747};
    public static final int[] flagIconId = {2761, 2330, 2323, 2327, 2326, 2324, 2329, 2328, 2331, 4386, 4385, 2325};

    public void openFlagUI(Player pl) {
        Message msg;
        try {
            msg = new Message(-103);
            msg.writer().writeByte(0);
            msg.writer().writeByte(flagTempId.length);
            for (int i = 0; i < flagTempId.length; i++) {
                msg.writer().writeShort(flagTempId[i]);
                msg.writer().writeByte(1);
                switch (flagTempId[i]) {
                    case 363:
                        msg.writer().writeByte(73);
                        msg.writer().writeShort(0);
                        break;
                    case 371:
                        msg.writer().writeByte(88);
                        msg.writer().writeShort(10);
                        break;
                    default:
                        msg.writer().writeByte(88);
                        msg.writer().writeShort(5);
                        break;
                }
            }
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void changeFlag(Player pl, int index) {
        Message msg;
        try {
            pl.cFlag = (byte) index;
            msg = new Message(-103);
            msg.writer().writeByte(1);
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeByte(index);
            Service.gI().sendMessAllPlayerInMap(pl, msg);
            msg.cleanup();

            msg = new Message(-103);
            msg.writer().writeByte(2);
            msg.writer().writeByte(index);
            msg.writer().writeShort(flagIconId[index]);
            Service.gI().sendMessAllPlayerInMap(pl, msg);
            msg.cleanup();

            if (pl.pet != null) {
                pl.pet.cFlag = (byte) index;
                msg = new Message(-103);
                msg.writer().writeByte(1);
                msg.writer().writeInt((int) pl.pet.id);
                msg.writer().writeByte(index);
                Service.gI().sendMessAllPlayerInMap(pl.pet, msg);
                msg.cleanup();

                msg = new Message(-103);
                msg.writer().writeByte(2);
                msg.writer().writeByte(index);
                msg.writer().writeShort(flagIconId[index]);
                Service.gI().sendMessAllPlayerInMap(pl.pet, msg);
                msg.cleanup();
            }
            pl.iDMark.setLastTimeChangeFlag(System.currentTimeMillis());

        } catch (Exception e) {
            Logger.logException(Service.class, e);
        }
    }

    public void sendFlagPlayerToMe(Player me, Player pl) {
        Message msg;
        try {
            msg = new Message(-103);
            msg.writer().writeByte(2);
            msg.writer().writeByte(pl.cFlag);
            msg.writer().writeShort(flagIconId[pl.cFlag]);
            me.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void chooseFlag(Player pl, int index) {
        if (MapService.gI().isMapBlackBallWar(pl.zone.map.mapId) || MapService.gI().isMapMaBu(pl.zone.map.mapId) || MapService.gI().isMapMabu13h(pl.zone.map.mapId) || MapService.gI().isMapPVP(pl.zone.map.mapId) || MapService.gI().isMapBanDoKhoBau(pl.zone.map.mapId) || MapService.gI().isnguhs(pl.zone.map.mapId)) {
            sendThongBao(pl, "Không thể đổi cờ lúc này!");
            return;
        }
        if (Util.canDoWithTime(pl.iDMark.getLastTimeChangeFlag(), 60000)) {
            changeFlag(pl, index);
        } else {
            sendThongBao(pl, "Không thể đổi cờ lúc này! Vui lòng đợi " + TimeUtil.getTimeLeft(pl.iDMark.getLastTimeChangeFlag(), 60) + " nữa!");
        }
    }

    public void attackPlayer(Player pl, int idPlAnPem) {
        SkillService.gI().useSkill(pl, pl.zone.getPlayerInMap(idPlAnPem), null, null);
    }

    public void releaseCooldownSkill(Player pl) {
        Message msg;
        try {
            msg = new Message(-94);
            for (Skill skill : pl.playerSkill.skills) {
                skill.coolDown = 0;
                msg.writer().writeShort(skill.skillId);
                int leftTime = (int) (skill.lastTimeUseThisSkill + skill.coolDown - System.currentTimeMillis());
                if (leftTime < 0) {
                    leftTime = 0;
                }
                msg.writer().writeInt(leftTime);
            }
            pl.sendMessage(msg);
            pl.nPoint.setMp(pl.nPoint.mpMax);
            PlayerService.gI().sendInfoHpMpMoney(pl);
            msg.cleanup();

        } catch (Exception e) {
        }
    }

    public void sendTimeSkill(Player pl) {
        Message msg;
        try {
            msg = new Message(-94);
            for (Skill skill : pl.playerSkill.skills) {
                msg.writer().writeShort(skill.skillId);
                int timeLeft = (int) (skill.lastTimeUseThisSkill + skill.coolDown - System.currentTimeMillis());
                if (timeLeft < 0) {
                    timeLeft = 0;
                }
                msg.writer().writeInt(timeLeft);
            }
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void dropItemMap(Zone zone, ItemMap item) {
        Message msg;
        try {
            msg = new Message(68);
            msg.writer().writeShort(item.itemMapId);
            msg.writer().writeShort(item.itemTemplate.id);
            msg.writer().writeShort(item.x);
            msg.writer().writeShort(item.y);
            msg.writer().writeInt(3);//
            sendMessAllPlayerInMap(zone, msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void dropItemMapForMe(Player player, ItemMap item) {
        Message msg;
        try {
            msg = new Message(68);
            msg.writer().writeShort(item.itemMapId);
            msg.writer().writeShort(item.itemTemplate.id);
            msg.writer().writeShort(item.x);
            msg.writer().writeShort(item.y);
            msg.writer().writeInt(3);//
            player.sendMessage(msg);
            msg.cleanup();

        } catch (Exception e) {
            Logger.logException(Service.class, e);
        }
    }

    public void showInfoPet(Player pl) {
        if (pl != null && pl.pet != null) {
            Message msg;
            try {
                msg = new Message(-107);
                msg.writer().writeByte(2);
                msg.writer().writeShort(pl.pet.getAvatar());
                msg.writer().writeByte(pl.pet.inventory.itemsBody.size());

                for (Item item : pl.pet.inventory.itemsBody) {
                    if (!item.isNotNullItem()) {
                        msg.writer().writeShort(-1);
                    } else {
                        msg.writer().writeShort(item.template.id);
                        msg.writer().writeInt(item.quantity);
                        msg.writer().writeUTF(item.getInfo());
                        msg.writer().writeUTF(item.getContent());

                        int countOption = item.itemOptions.size();
                        msg.writer().writeByte(countOption);
                        for (ItemOption iop : item.itemOptions) {
                            msg.writer().writeByte(iop.optionTemplate.id);
                            msg.writer().writeShort(iop.param);
                        }
                    }
                }

                msg.writer().writeInt(pl.pet.nPoint.hp); //hp
                msg.writer().writeInt(pl.pet.nPoint.hpMax); //hpfull
                msg.writer().writeInt(pl.pet.nPoint.mp); //mp
                msg.writer().writeInt(pl.pet.nPoint.mpMax); //mpfull
                msg.writer().writeInt(pl.pet.nPoint.dame); //damefull
                msg.writer().writeUTF(pl.pet.name); //name
                msg.writer().writeUTF(getCurrStrLevel(pl.pet)); //curr level
                msg.writer().writeLong(pl.pet.nPoint.power); //power
                msg.writer().writeLong(pl.pet.nPoint.tiemNang); //tiềm năng
                msg.writer().writeByte(pl.pet.getStatus()); //status
                msg.writer().writeShort(pl.pet.nPoint.stamina); //stamina
                msg.writer().writeShort(pl.pet.nPoint.maxStamina); //stamina full
                msg.writer().writeByte(pl.pet.nPoint.crit); //crit
                msg.writer().writeShort(pl.pet.nPoint.def); //def
                int sizeSkill = pl.pet.playerSkill.skills.size();
                msg.writer().writeByte(5); //counnt pet skill
                for (int i = 0; i < pl.pet.playerSkill.skills.size(); i++) {
                    if (pl.pet.playerSkill.skills.get(i).skillId != -1) {
                        msg.writer().writeShort(pl.pet.playerSkill.skills.get(i).skillId);
                    } else {
                        switch (i) {
                            case 1:
                                msg.writer().writeShort(-1);
                                msg.writer().writeUTF("Cần đạt sức mạnh 150tr để mở");
                                break;
                            case 2:
                                msg.writer().writeShort(-1);
                                msg.writer().writeUTF("Cần đạt sức mạnh 1tỷ5 để mở");
                                break;
                            case 3:
                                msg.writer().writeShort(-1);
                                msg.writer().writeUTF("Cần đạt sức mạnh 20tỷ\nđể mở");
                                break;
                            default:
                                msg.writer().writeShort(-1);
                                msg.writer().writeUTF("Cần đạt sức mạnh 60tỷ\nđể mở");
                                break;
                        }
                    }
                }

                pl.sendMessage(msg);
                msg.cleanup();

            } catch (Exception e) {
                Logger.logException(Service.class, e);
            }
        }
    }

    public void sendSpeedPlayer(Player pl, int speed) {
        Message msg;
        try {
            msg = Service.gI().messageSubCommand((byte) 8);
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeByte(speed != -1 ? speed : pl.nPoint.speed);
            pl.sendMessage(msg);
            msg.cleanup();

        } catch (Exception e) {
            Logger.logException(Service.class, e);
        }
    }

    public void setPos(Player player, int x, int y) {
        player.location.x = x;
        player.location.y = y;
        Message msg;
        try {
            msg = new Message(123);
            msg.writer().writeInt((int) player.id);
            msg.writer().writeShort(x);
            msg.writer().writeShort(y);
            msg.writer().writeByte(1);
            sendMessAllPlayerInMap(player, msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void getPlayerMenu(Player player, int playerId) {
        Message msg;
        try {
            msg = new Message(-79);
            Player pl = player.zone.getPlayerInMap(playerId);
            if (pl != null) {
                msg.writer().writeInt(playerId);
                msg.writer().writeLong(pl.nPoint.power);
                msg.writer().writeUTF(Service.gI().getCurrStrLevel(pl));
                player.sendMessage(msg);
            }
            msg.cleanup();
            if (player.isAdmin()) {
                SubMenuService.gI().showMenuForAdmin(player);

            }
        } catch (Exception e) {
            Logger.logException(Service.class, e);
        }
    }

    public void hideWaitDialog(Player pl) {
        Message msg;
        try {
            msg = new Message(-99);
            msg.writer().writeByte(-1);
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void chatPrivate(Player plChat, Player plReceive, String text) {
        Message msg;
        try {
            msg = new Message(92);
            msg.writer().writeUTF(plChat.name);
            msg.writer().writeUTF("|7|" + text);
            msg.writer().writeInt((int) plChat.id);
            msg.writer().writeShort(plChat.getHead());
            msg.writer().writeShort(plChat.getBody());
            msg.writer().writeShort(plChat.getFlagBag()); //bag
            msg.writer().writeShort(plChat.getLeg());
            msg.writer().writeByte(1);
            plChat.sendMessage(msg);
            plReceive.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void changePassword(Player player, String oldPass, String newPass, String rePass) {
        if (player.getSession().pp.equals(oldPass)) {
            if (newPass.length() >= 5) {
                if (newPass.equals(rePass)) {
                    player.getSession().pp = newPass;
                    try {
                        GirlkunDB.executeUpdate("update account set password = ? where id = ? and username = ?",
                                rePass, player.getSession().userId, player.getSession().uu);
                        Service.gI().sendThongBao(player, "Đổi mật khẩu thành công!");
                    } catch (Exception ex) {
                        Service.gI().sendThongBao(player, "Đổi mật khẩu thất bại!");
                        Logger
                                .logException(Service.class, ex);
                    }
                } else {
                    Service.gI().sendThongBao(player, "Mật khẩu nhập lại không đúng!");
                }
            } else {
                Service.gI().sendThongBao(player, "Mật khẩu ít nhất 5 ký tự!");
            }
        } else {
            Service.gI().sendThongBao(player, "Mật khẩu cũ không đúng!");
        }
    }

    public void switchToCreateChar(MySession session) {
        Message msg;
        try {
            msg = new Message(2);
            session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendCaption(MySession session, byte gender) {
        Message msg;
        try {
            msg = new Message(-41);
            msg.writer().writeByte(Manager.CAPTIONS.size());
            for (String caption : Manager.CAPTIONS) {
                msg.writer().writeUTF(caption.replaceAll("%1", gender == ConstPlayer.TRAI_DAT ? "Trái đất"
                        : (gender == ConstPlayer.NAMEC ? "Namếc" : "Xayda")));
            }
            session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendHavePet(Player player) {
        Message msg;
        try {
            msg = new Message(-107);
            msg.writer().writeByte(player.pet == null ? 0 : 1);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendWaitToLogin(MySession session, int secondsWait) {
        Message msg;
        try {
            msg = new Message(122);
            msg.writer().writeShort(secondsWait);
            session.sendMessage(msg);
            msg.cleanup();

        } catch (Exception e) {
            Logger.logException(Service.class, e);
        }
    }

    public void sendMessage(MySession session, int cmd, String path) {
        Message msg;
        try {
            msg = new Message(cmd);
            msg.writer().write(FileIO.readFile(path));
            session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void createItemMap(Player player, int tempId) {
        ItemMap itemMap = new ItemMap(player.zone, tempId, 1, player.location.x, player.location.y, player.id);
        dropItemMap(player.zone, itemMap);
    }

    public void sendNangDong(Player player) {
        Message msg;
        try {
            msg = new Message(-97);
            msg.writer().writeInt(100);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void setClientType(MySession session, Message msg) {
        try {
            session.typeClient = (msg.reader().readByte());//client_type
            session.zoomLevel = msg.reader().readByte();//zoom_level
            msg.reader().readBoolean();//is_gprs
            msg.reader().readInt();//width
            msg.reader().readInt();//height
            msg.reader().readBoolean();//is_qwerty
            msg.reader().readBoolean();//is_touch
            String platform = msg.reader().readUTF();
            String[] arrPlatform = platform.split("\\|");
            session.version = Integer.parseInt(arrPlatform[1].replaceAll("\\.", ""));

//            System.out.println(platform);
        } catch (Exception e) {
        } finally {
            msg.cleanup();
        }
        DataGame.sendLinkIP(session);
    }

    public void DropVeTinh(Player pl, Item item, Zone map, int x, int y) {
        ItemMap itemMap = new ItemMap(map, item.template, item.quantity, x, y, pl.id);
        itemMap.options = item.itemOptions;
        map.addItem(itemMap);
        Message msg = null;
        try {
            msg = new Message(68);
            msg.writer().writeShort(itemMap.itemMapId);
            msg.writer().writeShort(itemMap.itemTemplate.id);
            msg.writer().writeShort(itemMap.x);
            msg.writer().writeShort(itemMap.y);
            msg.writer().writeInt(-2);
            msg.writer().writeShort(200);
            sendMessAllPlayerInMap(map, msg);
        } catch (Exception exception) {

        } finally {
            if (msg != null) {
                msg.cleanup();
                msg = null;
            }
        }
    }
}
