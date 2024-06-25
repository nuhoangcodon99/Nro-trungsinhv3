package com.girlkun.services.func;

import com.girlkun.consts.ConstNpc;
import com.girlkun.models.item.Item;
import com.girlkun.models.item.Item.ItemOption;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.npc.Npc;
import com.girlkun.models.npc.NpcManager;
import com.girlkun.models.player.Player;
import com.girlkun.server.Manager;
import com.girlkun.server.ServerNotify;
import com.girlkun.network.io.Message;
import com.girlkun.services.*;
import com.girlkun.utils.Logger;
import com.girlkun.utils.Util;

import java.util.*;
import java.util.stream.Collectors;

public class CombineServiceNew {

    private static final int COST_DOI_VE_DOI_DO_HUY_DIET = 500000000;
    private static final int COST_DAP_DO_KICH_HOAT = 500000000;
    private static final int COST_DOI_MANH_KICH_HOAT = 500000000;

    private static final int COST = 500000000;

    private static final int TIME_COMBINE = 1;

    private static final byte MAX_STAR_ITEM = 8;
    private static final byte MAX_LEVEL_ITEM = 8;

    private static final byte OPEN_TAB_COMBINE = 0;
    private static final byte REOPEN_TAB_COMBINE = 1;
    private static final byte COMBINE_SUCCESS = 2;
    private static final byte COMBINE_FAIL = 3;
    private static final byte COMBINE_CHANGE_OPTION = 4;
    private static final byte COMBINE_DRAGON_BALL = 5;
    public static final byte OPEN_ITEM = 6;

    public static final int EP_SAO_TRANG_BI = 500;
    public static final int PHA_LE_HOA_TRANG_BI = 501;
    public static final int THUC_TINH_DT = 529;

    public static final int GIA_HAN_VAT_PHAM = 506;
    public static final int HP = 507;
     public static final int SD = 5998;
      public static final int KI = 5673;
       public static final int GIAP = 5454;
    public static final int NANG_CAP_VAT_PHAM = 510;
    public static final int NANG_CAP_BONG_TAI = 511;
    public static final int MO_CHI_SO_BONG_TAI = 519;
    public static final int NANG_CAP_BONG_TAI_CAP3 = 517;
    public static final int MO_CHI_SO_BONG_TAI_CAP3 = 518;
    public static final int NANG_CAP_BONG_TAI_CAP4 = 523;
    public static final int MO_CHI_SO_BONG_TAI_CAP4 = 524;
    public static final int NANG_CAP_CHAN_MENH = 5380;
    public static final int NHAP_NGOC_RONG = 513;
    public static final int LAM_PHEP_NHAP_DA = 524;
    public static final int PHAN_RA_DO_THAN_LINH = 514;
    public static final int NANG_CAP_DO_TS = 515;
    public static final int NANG_CAP_SKH_VIP = 516;
    public static final int NANG_CAP_DO_THAN = 2512;
    public static final int CHE_TAO_TRANG_BI_TS = 520;
    public static final int LUYEN_HOA_CHIEN_LINH = 521;
    public static final int EP_AN_TRANG_BI = 522;
    public static final int DOI_DIEM = 502;
    public static final int NGUYET_AN = 512;
    public static final int DAP_SET_KICH_HOAT = 504;
    private static final int GOLD_BONG_TAI = 500_000_000;
    private static final int GEM_BONG_TAI = 5_000;
    private static final int RATIO_BONG_TAI = 50;
    private static final int RATIO_NANG_CAP = 45;
    private static final int RUBY_BONG_TAI = 10_000;
    public static final int NANG_HUY_DIET_LEN_SKH_VIP = 555;

    private final Npc baHatMit;
    private final Npc granala;
    private final Npc npsthiensu64;
    private final Npc Whis;
    private final Npc lmht;
    private final Npc dothan;

    private static CombineServiceNew i;

    public CombineServiceNew() {
        this.baHatMit = NpcManager.getNpc(ConstNpc.BA_HAT_MIT);
        this.granala = NpcManager.getNpc(ConstNpc.Granola);
        this.npsthiensu64 = NpcManager.getNpc(ConstNpc.NPC_64);
        this.lmht = NpcManager.getNpc(ConstNpc.cr2);
        this.Whis = NpcManager.getNpc(ConstNpc.WHIS);
        this.dothan = NpcManager.getNpc(ConstNpc.cr1);
    }

    public static CombineServiceNew gI() {
        if (i == null) {
            i = new CombineServiceNew();
        }
        return i;
    }

    /**
     * Mở tab đập đồ
     *
     * @param player
     * @param type kiểu đập đồ
     */
    public void openTabCombine(Player player, int type) {
        player.combineNew.setTypeCombine(type);
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(OPEN_TAB_COMBINE);
            msg.writer().writeUTF(getTextInfoTabCombine(type));
            msg.writer().writeUTF(getTextTopTabCombine(type));
            if (player.iDMark.getNpcChose() != null) {
                msg.writer().writeShort(player.iDMark.getNpcChose().tempId);
            }
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    /**
     * Hiển thị thông tin đập đồ
     *
     * @param player
     */
    public void showInfoCombine(Player player, int[] index) {
        player.combineNew.clearItemCombine();
        if (index.length > 0) {
            for (int i = 0; i < index.length; i++) {
                player.combineNew.itemsCombine.add(player.inventory.itemsBag.get(index[i]));
            }
        }
        switch (player.combineNew.typeCombine) {
            case THUC_TINH_DT:
                if (player.combineNew.itemsCombine.size() == 3) {
                    Item dathuctinh = null;
                    Item ngocrong = null;
                    Item tv = null;
                    Item ngocrongsp = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id == 16) {
                            ngocrong = item;
                        } else if (item.template.id == 14) {
                            ngocrongsp = item;
                        } else if (item.template.id == 1421) {
                            dathuctinh = item;
                        } else if (item.template.id == 457) {
                            tv = item;
                        }
                    }
                    if (ngocrongsp != null && ngocrongsp.quantity >= 3 && dathuctinh != null && dathuctinh.quantity >= 30
                            && tv != null && tv.quantity >= 3) {
                        if (player.nPoint.hpg >= 680000 && player.nPoint.mpg >= 680000 && player.nPoint.dameg >= 38000) {
                            this.lmht.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Bạn đã đạt cấp độ thức tỉnh tối đa tối đa", "Đóng");
                            return;
                        }
                        if (player.nPoint.dameg >= 32000 && player.nPoint.hpg >= 600000
                                && player.nPoint.mpg >= 600000 && player.nPoint.power >= 80000000000L) {
                            String npcSay = "|7|[ THỨC TỈNH BẢN THÂN ]"
                                    + "\n|5|HP: " + player.nPoint.hpg
                                    + "\nKI: " + player.nPoint.mpg
                                    + "\nSD: " + player.nPoint.dameg
                                    + "\n|7|Tỉ lệ thành công: "
                                    + (player.nPoint.hpg >= 600000 && player.nPoint.hpg < 620000
                                    && player.nPoint.mpg >= 600000 && player.nPoint.mpg < 620000
                                    && player.nPoint.dameg >= 32000 && player.nPoint.dameg < 34000 ? "50%"
                                            : player.nPoint.hpg >= 620000 && player.nPoint.hpg < 640000
                                            && player.nPoint.mpg >= 620000 && player.nPoint.mpg < 640000
                                            && player.nPoint.dameg >= 34000 && player.nPoint.dameg < 36000 ? "30%" : "10%") + "\n";
                            this.lmht.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                    "Nâng X1\nCần 100\nHồng Ngọc", "Nâng X10\nCần 1.000\nHồng Ngọc", "Nâng X100\nCần 10.000\nHồng Ngọc");
                        } else {
                            this.lmht.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "|7|Ngươi chưa đạt đủ điều kiện để thức tỉnh\n"
                                    + "Yêu cầu : Max chỉ số HP, KI, SD và sức mạnh đạt 80Tỷ trở lên", "Đóng");
                        }
                    } else if (ngocrong != null && ngocrong.quantity >= 3
                            && dathuctinh != null && dathuctinh.quantity >= 15
                            && tv != null && tv.quantity >= 1) {
                        if (player.pet.nPoint.dameg >= 32000 && player.pet.nPoint.hpg >= 600000
                                && player.pet.nPoint.mpg >= 600000 && player.pet.nPoint.power >= 80000000000L) {
                            String tsSay = "|7|[ THỨC TỈNH ĐỆ TỬ ]\n" + player.pet.name.substring(1)
                                    + "\n|5|Cấp bậc : " + player.pet.getNameThuctinh(player.pet.thuctinh)
                                    + "\nHP: " + (player.pet.nPoint.hpg >= 680000 ? Util.format(player.pet.nPoint.hpg)
                                            : Util.format(player.pet.nPoint.hpg) + " Cấp Sau: " + Util.format(player.pet.nPoint.hpg + 200))
                                    + "\nKI: " + (player.pet.nPoint.mpg >= 680000 ? Util.format(player.pet.nPoint.mpg)
                                            : Util.format(player.pet.nPoint.mpg) + " Cấp Sau: " + Util.format(player.pet.nPoint.mpg + 200))
                                    + "\nSD: " + (player.pet.nPoint.dameg >= 38000 ? Util.format(player.pet.nPoint.dameg)
                                            : Util.format(player.pet.nPoint.dameg) + " Cấp Sau: " + Util.format(player.pet.nPoint.dameg + 100))
                                    + "\n|7|Tỉ lệ thành công: "
                                    + (player.pet.nPoint.hpg >= 600000 && player.pet.nPoint.hpg < 620000
                                    && player.pet.nPoint.mpg >= 600000 && player.pet.nPoint.mpg < 620000
                                    && player.pet.nPoint.dameg >= 32000 && player.pet.nPoint.dameg < 34000 ? "50%"
                                            : player.pet.nPoint.hpg >= 620000 && player.pet.nPoint.hpg < 640000
                                            && player.pet.nPoint.mpg >= 620000 && player.pet.nPoint.mpg < 640000
                                            && player.pet.nPoint.dameg >= 34000 && player.pet.nPoint.dameg < 36000 ? "30%" : "10%") + "\n";
                            this.lmht.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, tsSay,
                                    "Nâng X1\nCần 100\nHồng Ngọc", "Nâng X10\nCần 1.000\nHồng Ngọc", "Nâng X100\nCần 10.000\nHồng Ngọc");
                        } else {
                            this.lmht.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "|7|Đệ tử ngươi chưa đạt đủ điều kiện để thức tỉnh\n"
                                    + "Yêu cầu : Max chỉ số HP, KI, SD và sức mạnh đạt 80Tỷ trở lên", "Đóng");
                        }
                    } else {
                        this.lmht.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Đối với đệ tử: Cần 30 Đá Thức Tỉnh, 3 viên ngọc rồng 3s và 1 thỏi vàng"
                                + "\nĐối với sư phụ: Cần 50 Đá Thức Tỉnh, 3 viên ngọc rồng 1s và 3 thỏi vàng", "Đóng");
                    }
                } else {
                    this.lmht.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Đối với đệ tử: Cần 30 Đá Thức Tỉnh, 3 viên ngọc rồng 3s và 1 thỏi vàng"
                            + "\nĐối với sư phụ: Cần 50 Đá Thức Tỉnh, 3 viên ngọc rồng 1s và 3 thỏi vàng", "Đóng");
                }
                break;
                case GIA_HAN_VAT_PHAM:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item thegh = null;
                    Item itemGiahan = null;
                    for (Item item_ : player.combineNew.itemsCombine) {
                        if (item_.template.id == 1346) {
                            thegh = item_;
                        } else if (item_.isTrangBiHSD()) {
                            itemGiahan = item_;
                        }
                    }
                    if (thegh == null) {
                        Service.getInstance().sendThongBaoOK(player, "Cần 1 trang bị có hạn sử dụng và 1 phiếu Gia hạn");
                        return;
                    }
                    if (itemGiahan == null) {
                        Service.getInstance().sendThongBaoOK(player, "Cần 1 trang bị có hạn sử dụng và 1 phiếu Gia hạn");
                        return;
                    }
                    for (ItemOption itopt : itemGiahan.itemOptions) {
                        if (itopt.optionTemplate.id == 93) {
                            if (itopt.param < 0 || itopt == null) {
                                Service.getInstance().sendThongBaoOK(player, "Trang bị này không phải trang bị có Hạn Sử Dụng");
                                return;
                            }
                        }
                    }
                    String npcSay = "Trang bị được gia hạn \"" + itemGiahan.template.name + "\"";
                    npcSay += itemGiahan.template.name + "\n|2|";
                    for (Item.ItemOption io : itemGiahan.itemOptions) {
                        npcSay += io.getOptionString() + "\n";
                    }
                    npcSay += "\n|0|Sau khi gia hạn +1 ngày\n";

                    npcSay += "|0|Tỉ lệ thành công: 100%" + "\n";
                    if (player.inventory.gold > 200000000) {
                        npcSay += "|2|Cần 200Tr vàng";
                        this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Nâng cấp", "Từ chối");

                    } else if (player.inventory.gold < 200000000) {
                        int SoVangThieu2 = (int) (200000000 - player.inventory.gold);
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Bạn còn thiếu " + SoVangThieu2 + " vàng");
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần 1 trang bị có hạn sử dụng và 1 phiếu Gia hạn");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hành trang cần ít nhất 1 chỗ trống");
                }
                break;
                case HP:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item thegh = null;
                    Item itemGiahan = null;
                    for (Item item_ : player.combineNew.itemsCombine) {
                        if (item_.template.id == 1346) {
                            thegh = item_;
                        } else if (item_.isTrangBiHP()) {
                            itemGiahan = item_;
                        }
                    }
                    if (thegh == null) {
                        Service.getInstance().sendThongBaoOK(player, "Cần 1 trang bị có chỉ số HP và 1 phiếu Naang Cấp HP");
                        return;
                    }
                    if (itemGiahan == null) {
                        Service.getInstance().sendThongBaoOK(player, "Cần 1 trang bị có chỉ số HP và 1 phiếu Nâng Cấp HP ");
                        return;
                    }
                    for (ItemOption itopt : itemGiahan.itemOptions) {
                        if (itopt.optionTemplate.id == 77) {
                            if (itopt.param < 0 || itopt == null) {
                                Service.getInstance().sendThongBaoOK(player, "Trang bị này không phải trang bị có chỉ số Hp");
                                return;
                            }
                        }
                    }
                    String npcSay = "Trang bị được nâng chỉ số HP \"" + itemGiahan.template.name + "\"";
                    npcSay += itemGiahan.template.name + "\n|2|";
                    for (Item.ItemOption io : itemGiahan.itemOptions) {
                        npcSay += io.getOptionString() + "\n";
                    }
                    npcSay += "\n|0|Sau khi Nâng Cấp +1%\n";

                    npcSay += "|0|Tỉ lệ thành công: 40%" + "\n";
                    if (player.inventory.ruby > 1500) {
                        npcSay += "|2|Cần 1k5 Hồng ngọc";
                        this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Nâng cấp", "Từ chối");

                    } else if (player.inventory.ruby < 1500) {
                        int SoVangThieu2 = (int) (1500 - player.inventory.ruby);
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Bạn còn thiếu " + SoVangThieu2 + " hồng ngọc");
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần 1 trang bị có chỉ số HP và 1 phiếu nâng cấp HP");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hành trang cần ít nhất 1 chỗ trống");
                }
                break;
                case SD:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item thegh = null;
                    Item itemGiahan = null;
                    for (Item item_ : player.combineNew.itemsCombine) {
                        if (item_.template.id == 1346) {
                            thegh = item_;
                        } else if (item_.isTrangBiSD()) {
                            itemGiahan = item_;
                        }
                    }
                    if (thegh == null) {
                        Service.getInstance().sendThongBaoOK(player, "Cần 1 trang bị có chỉ số Sức Đánh và 1 phiếu nâng cấp Sức Đánh");
                        return;
                    }
                    if (itemGiahan == null) {
                        Service.getInstance().sendThongBaoOK(player, "Cần 1 trang bị có số Sức Đánh và 1 phiếu nâng cấp Sức Đánh");
                        return;
                    }
                    for (ItemOption itopt : itemGiahan.itemOptions) {
                        if (itopt.optionTemplate.id == 50) {
                            if (itopt.param < 0 || itopt == null) {
                                Service.getInstance().sendThongBaoOK(player, "Trang bị này không phải trang bị có Chỉ số sức đánh");
                                return;
                            }
                        }
                    }
                    String npcSay = "Trang bị được nâng cấp \"" + itemGiahan.template.name + "\"";
                    npcSay += itemGiahan.template.name + "\n|2|";
                    for (Item.ItemOption io : itemGiahan.itemOptions) {
                        npcSay += io.getOptionString() + "\n";
                    }
                    npcSay += "\n|0|Sau khi nâng cấp +1% Sức đánh\n";

                    npcSay += "|0|Tỉ lệ thành công: 25%" + "\n";
                    if (player.inventory.ruby > 20000) {
                        npcSay += "|2|Cần 2k ruby";
                        this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Nâng cấp", "Từ chối");

                    } else if (player.inventory.ruby < 20000) {
                        int SoVangThieu2 = (int) (20000 - player.inventory.ruby);
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Bạn còn thiếu " + SoVangThieu2 + " hồng ngọc");
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần 1 trang bị có chỉ số sức đánh và 1 phiếu nâng cấp sức đánh");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hành trang cần ít nhất 1 chỗ trống");
                }
                break;
                case KI:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item thegh = null;
                    Item itemGiahan = null;
                    for (Item item_ : player.combineNew.itemsCombine) {
                        if (item_.template.id == 1346) {
                            thegh = item_;
                        } else if (item_.isTrangBiKI()) {
                            itemGiahan = item_;
                        }
                    }
                    if (thegh == null) {
                        Service.getInstance().sendThongBaoOK(player, "Cần 1 trang bị có chỉ số KI và 1 phiếu nâng cấp KI");
                        return;
                    }
                    if (itemGiahan == null) {
                        Service.getInstance().sendThongBaoOK(player, "Cần 1 trang bị có chỉ số KI và 1 phiếu nâng cấp KI");
                        return;
                    }
                    for (ItemOption itopt : itemGiahan.itemOptions) {
                        if (itopt.optionTemplate.id == 103) {
                            if (itopt.param < 0 || itopt == null) {
                                Service.getInstance().sendThongBaoOK(player, "Trang bị này không phải trang bị có chỉ số KI");
                                return;
                            }
                        }
                    }
                    String npcSay = "Trang bị được nâng cấp \"" + itemGiahan.template.name + "\"";
                    npcSay += itemGiahan.template.name + "\n|2|";
                    for (Item.ItemOption io : itemGiahan.itemOptions) {
                        npcSay += io.getOptionString() + "\n";
                    }
                    npcSay += "\n|0|Sau khi nâng cấp +1% KI\n";

                    npcSay += "|0|Tỉ lệ thành công: 50%" + "\n";
                    if (player.inventory.ruby > 1000) {
                        npcSay += "|2|Cần 1k hồng ngọc";
                        this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Nâng cấp", "Từ chối");

                    } else if (player.inventory.ruby < 1000) {
                        int SoVangThieu2 = (int) (1000 - player.inventory.ruby);
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Bạn còn thiếu " + SoVangThieu2 + " hoongf ngọc");
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần 1 trang bị có chỉ số KI và 1 phiếu nâng cấp KI ");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hành trang cần ít nhất 1 chỗ trống");
                }
                break;
                case GIAP:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item thegh = null;
                    Item itemGiahan = null;
                    for (Item item_ : player.combineNew.itemsCombine) {
                        if (item_.template.id == 1346) {
                            thegh = item_;
                        } else if (item_.isTrangBiGIAP()) {
                            itemGiahan = item_;
                        }
                    }
                    if (thegh == null) {
                        Service.getInstance().sendThongBaoOK(player, "Cần 1 trang bị có chỉ số sức đánh chí mạng và 1 phiếu nâng cấp SĐCM");
                        return;
                    }
                    if (itemGiahan == null) {
                        Service.getInstance().sendThongBaoOK(player, "Cần 1 trang bị có chỉ số SĐCM và 1 phiếu nâng cấp SĐCM");
                        return;
                    }
                    for (ItemOption itopt : itemGiahan.itemOptions) {
                        if (itopt.optionTemplate.id == 5) {
                            if (itopt.param < 0 || itopt == null) {
                                Service.getInstance().sendThongBaoOK(player, "Trang bị này không phải trang bị có chỉ số SĐCM ");
                                return;
                            }
                        }
                    }
                    String npcSay = "Trang bị được nâng cấp \"" + itemGiahan.template.name + "\"";
                    npcSay += itemGiahan.template.name + "\n|2|";
                    for (Item.ItemOption io : itemGiahan.itemOptions) {
                        npcSay += io.getOptionString() + "\n";
                    }
                    npcSay += "\n|0|Sau khi nâng cấp +1% SĐCM\n";

                    npcSay += "|0|Tỉ lệ thành công: 20%" + "\n";
                    if (player.inventory.ruby > 3000) {
                        npcSay += "|2|Cần 3k hồng ngọc";
                        this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Nâng cấp", "Từ chối");

                    } else if (player.inventory.ruby < 3000) {
                        int SoVangThieu2 = (int) (3000 - player.inventory.ruby);
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Bạn còn thiếu " + SoVangThieu2 + "hồng ngọc");
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần 1 trang bị có chỉ số SĐCM và 1 phiếu Nâng cấp SĐCM");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hành trang cần ít nhất 1 chỗ trống");
                }
                break;
                
            case NANG_CAP_CHAN_MENH:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Hãy đưa ta chân mệnh và 5 viên ngọc rồng 1s ta sẽ phù phép cho !",
                            "Đóng");
                    return;
                }

                if (player.combineNew.itemsCombine.size() == 2) {
                    if (player.combineNew.itemsCombine.stream().filter(
                            item -> item.isNotNullItem() && (item.template.id >= 1328 && item.template.id <= 1335))
                            .count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu chân mệnh", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.template.id == 674)
                            .count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đá ngũ sắc", "Đóng");
                        return;
                    }

                    String npcSay = "|2|Con có muốn đổi các món nguyên liệu ?\n|7|"
                            + "|7|Chân mệnh "
                            + "10 viên ngọc rồng 1 sao + 5k HN";

                    if (player.inventory.ruby < 1000) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con",
                                "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp\n" + "5k" + " hồng ngọc", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 2) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp",
                                "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case NANG_CAP_BONG_TAI:// Nâng cấp btc2
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item bongTai = null;
                    Item manhVo = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id == 454) {
                            bongTai = item;
                        } else if (item.template.id == 933) {
                            manhVo = item;
                        }
                    }
                    if (bongTai != null && manhVo != null && manhVo.quantity >= 99) {

                        player.combineNew.goldCombine = GOLD_BONG_TAI;
                        player.combineNew.gemCombine = GEM_BONG_TAI;
                        player.combineNew.ratioCombine = RATIO_BONG_TAI;

                        String npcSay = "Bông tai Porata cấp 2" + "\n|2|";
                        for (Item.ItemOption io : bongTai.itemOptions) {
                            npcSay += io.getOptionString() + "\n";
                        }
                        npcSay += "|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%" + "\n";
                        if (player.combineNew.goldCombine <= player.inventory.gold) {
                            npcSay += "|1|Cần " + Util.numberToMoney(player.combineNew.goldCombine) + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                    "Nâng cấp\ncần " + player.combineNew.gemCombine + " ngọc");
                        } else {
                            npcSay += "Còn thiếu " + Util.numberToMoney(player.combineNew.goldCombine - player.inventory.gold) + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 Bông tai Porata cấp 1 và X99 Mảnh vỡ bông tai", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 Bông tai Porata cấp 1 và X99 Mảnh vỡ bông tai", "Đóng");
                }
                break;
            case MO_CHI_SO_BONG_TAI:// mở chỉ số bông tai cấp 2
                if (player.combineNew.itemsCombine.size() == 3) {
                    Item bongTai = null;
                    Item manhHon = null;
                    Item daXanhLam = null;
                    Item thoivang = null;
                    Item thoivang2 = null;
                    thoivang = InventoryServiceNew.gI().findItemBag(player, 457);
                    thoivang2 = InventoryServiceNew.gI().findItemBag(player, 1108);

                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id == 921) {
                            bongTai = item;
                        } else if (item.template.id == 934) {
                            manhHon = item;
                        } else if (item.template.id == 935) {
                            daXanhLam = item;
                        }
                    }
                    if (bongTai != null && manhHon != null && manhHon.quantity >= 99 && daXanhLam != null && daXanhLam.quantity >= 10) {

                        player.combineNew.goldCombine = GOLD_BONG_TAI;
                        player.combineNew.gemCombine = GEM_BONG_TAI;
                        player.combineNew.ratioCombine = RATIO_NANG_CAP;
//                        player.combineNew.rubyCombine = RUBY_BONG_TAI;
                        String npcSay = "Bông tai Porata cấp 2" + "\n|2|";
                        for (Item.ItemOption io : bongTai.itemOptions) {
                            npcSay += io.getOptionString() + "\n";
                        }
                        npcSay += "|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%" + "\n";
                        npcSay += "|6|Cần x1 bông tai cấp 2 " + "\n";
                        npcSay += "|6|Cần x99 mảnh vỡ bông tai" + "\n";
                        npcSay += "|6|Cần x10 Đá Xanh Lam" + "\n";
                        npcSay += "|5| Ngươi có muốn nâng cấp mất 500 thỏi vàng " + "\n";
                        if (thoivang != null && thoivang.quantity < 500 || thoivang2 != null && thoivang2.quantity < 500) {
                            npcSay += "Không đủ 500 thỏi vàng";

                            baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                            return;
                        }
                        if (player.combineNew.rubyCombine <= player.inventory.ruby || (thoivang != null && thoivang.quantity >= 500 || thoivang2 != null && thoivang2.quantity >= 500)) {
                            npcSay += "|1|Cần " + Util.numberToMoney(player.combineNew.rubyCombine) + " ngọc hồng và 500 thỏi vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                    "Nâng cấp\ncần " + player.combineNew.gemCombine + " ngọc");
                        } else {
                            npcSay += "Còn thiếu " + Util.numberToMoney(player.combineNew.rubyCombine - player.inventory.ruby) + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 Bông tai Porata cấp 2, X99 Mảnh hồn bông tai, 10 Đá xanh lam", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 Bông tai Porata cấp 2, X99 Mảnh hồn bông tai, 10 Đá xanh lam", "Đóng");
                }
                break;
            case NANG_CAP_BONG_TAI_CAP3:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item bongTai = null;
                    Item mvbt = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id == 921) {
                            bongTai = item;
                        } else if (item.template.id == 933) {
                            mvbt = item;
                        }
                    }
                    if (bongTai != null && mvbt != null && mvbt.quantity >= 999) {

                        player.combineNew.goldCombine = GOLD_BONG_TAI;
                        player.combineNew.gemCombine = GEM_BONG_TAI;
                        player.combineNew.ratioCombine = RATIO_BONG_TAI;
                        player.combineNew.rubyCombine = RUBY_BONG_TAI;
                        String npcSay = "Bông tai Porata cấp 3" + "\n|2|";
                        for (Item.ItemOption io : bongTai.itemOptions) {
                            npcSay += io.getOptionString() + "\n";
                        }
                        npcSay += "|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%" + "\n";
                        if (player.inventory.ruby <= player.combineNew.rubyCombine) {
                            npcSay += "Còn thiếu " + Util.numberToMoney(player.combineNew.rubyCombine - player.inventory.ruby) + " Hồng ngọc";
                            baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                            return;
                        }
                        if (player.combineNew.goldCombine <= player.inventory.gold) {
                            npcSay += "|1|Cần " + Util.numberToMoney(player.combineNew.goldCombine) + " vàng và "
                                    + player.combineNew.rubyCombine + " hồng ngọc";
                            baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                    "Nâng cấp");
                        } else {
                            npcSay += "Còn thiếu " + Util.numberToMoney(player.combineNew.goldCombine - player.inventory.gold) + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 Bông tai Porata cấp 2 , 10K Hồng Ngọc, 500 Triệu Vàng , X999 Mảnh vỡ bông tai "
                                + " và X999 Mảnh hồn bông tai", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 Bông tai Porata cấp 2 , 10K Hồng Ngọc, 500 Triệu Vàng , X999 Mảnh vỡ bông tai "
                            + " và X999 Mảnh hồn bông tai", "Đóng");
                }
                break;

            case MO_CHI_SO_BONG_TAI_CAP3:
                if (player.combineNew.itemsCombine.size() == 3) {
                    Item bongTai = null;
                    Item thachPhu = null;
                    Item daXanhLam = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id == 1155) {
                            bongTai = item;
                        } else if (item.template.id == 934) {
                            thachPhu = item;
                        } else if (item.template.id == 935) {
                            daXanhLam = item;

                        }
                    }
                    if (bongTai != null && thachPhu != null && daXanhLam != null && thachPhu.quantity >= 499) {
                        player.combineNew.goldCombine = GOLD_BONG_TAI;
                        player.combineNew.gemCombine = GEM_BONG_TAI;
                        player.combineNew.ratioCombine = RATIO_NANG_CAP;
                        String npcSay = "Bông tai Porata cấp 3" + "\n|2|";
                        for (Item.ItemOption io : bongTai.itemOptions) {
                            npcSay += io.getOptionString() + "\n";
                        }
                        npcSay += "|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%" + "\n";
                        if (player.combineNew.goldCombine <= player.inventory.gold) {
                            npcSay += "|1|Cần " + Util.numberToMoney(player.combineNew.goldCombine) + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                    "Nâng cấp\ncần " + player.combineNew.gemCombine + " ngọc");
                        } else {
                            npcSay += "Còn thiếu " + Util.numberToMoney(player.combineNew.goldCombine - player.inventory.gold) + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 Bông tai Porata cấp 3, X999 Mảnh hồn bông tai và 1 Đá xanh lam", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 Bông tai Porata cấp 3, X999 Mảnh hồn bông tai và 1 Đá xanh lam", "Đóng");
                }
                break;
            case NANG_CAP_BONG_TAI_CAP4:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item bongTai = null;
                    Item mvbt = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id == 1155) {
                            bongTai = item;
                        } else if (item.template.id == 933) {
                            mvbt = item;
                        }
                    }
                    if (bongTai != null && mvbt != null && mvbt.quantity >= 999) {

                        player.combineNew.goldCombine = GOLD_BONG_TAI;
                        player.combineNew.gemCombine = GEM_BONG_TAI;
                        player.combineNew.ratioCombine = RATIO_BONG_TAI;
                        player.combineNew.rubyCombine = RUBY_BONG_TAI;
                        String npcSay = "Bông tai Porata cấp 3" + "\n|2|";
                        for (Item.ItemOption io : bongTai.itemOptions) {
                            npcSay += io.getOptionString() + "\n";
                        }
                        npcSay += "|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%" + "\n";
                        if (player.inventory.ruby <= player.combineNew.rubyCombine) {
                            npcSay += "Còn thiếu " + Util.numberToMoney(player.combineNew.rubyCombine - player.inventory.ruby) + " Hồng ngọc";
                            baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                            return;
                        }
                        if (player.combineNew.goldCombine <= player.inventory.gold) {
                            npcSay += "|1|Cần " + Util.numberToMoney(player.combineNew.goldCombine) + " vàng và "
                                    + player.combineNew.rubyCombine + " hồng ngọc";
                            baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                    "Nâng cấp");
                        } else {
                            npcSay += "Còn thiếu " + Util.numberToMoney(player.combineNew.goldCombine - player.inventory.gold) + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 Bông tai Porata cấp 2 , 10K Hồng Ngọc, 500 Triệu Vàng , X999 Mảnh vỡ bông tai "
                                + " và X999 Mảnh hồn bông tai", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 Bông tai Porata cấp 2 , 10K Hồng Ngọc, 500 Triệu Vàng , X999 Mảnh vỡ bông tai "
                            + " và X999 Mảnh hồn bông tai", "Đóng");
                }
                break;

            case MO_CHI_SO_BONG_TAI_CAP4:
                if (player.combineNew.itemsCombine.size() == 3) {
                    Item bongTai = null;
                    Item thachPhu = null;
                    Item daXanhLam = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id == 1156) {
                            bongTai = item;
                        } else if (item.template.id == 934) {
                            thachPhu = item;
                        } else if (item.template.id == 935) {
                            daXanhLam = item;

                        }
                    }
                    if (bongTai != null && thachPhu != null && daXanhLam != null && thachPhu.quantity >= 499) {
                        player.combineNew.goldCombine = GOLD_BONG_TAI;
                        player.combineNew.gemCombine = GEM_BONG_TAI;
                        player.combineNew.ratioCombine = RATIO_NANG_CAP;
                        String npcSay = "Bông tai Porata cấp 3" + "\n|2|";
                        for (Item.ItemOption io : bongTai.itemOptions) {
                            npcSay += io.getOptionString() + "\n";
                        }
                        npcSay += "|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%" + "\n";
                        if (player.combineNew.goldCombine <= player.inventory.gold) {
                            npcSay += "|1|Cần " + Util.numberToMoney(player.combineNew.goldCombine) + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                    "Nâng cấp\ncần " + player.combineNew.gemCombine + " ngọc");
                        } else {
                            npcSay += "Còn thiếu " + Util.numberToMoney(player.combineNew.goldCombine - player.inventory.gold) + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 Bông tai Porata cấp 3, X999 Mảnh hồn bông tai và 1 Đá xanh lam", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 Bông tai Porata cấp 3, X999 Mảnh hồn bông tai và 1 Đá xanh lam", "Đóng");
                }
                break;
            case NANG_CAP_DO_THAN:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.dothan.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy đưa ta 1 món thần linh, ta sẽ cho 1 món huỷ diệt tương ứng", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 1) {
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.ismanhthanlinh()).count() != 1) {
                        this.dothan.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ thần linh rồi", "Đóng");
                        return;
                    }
                    String npcSay = "|2|Con có muốn đổi các món nguyên liệu ?\n|7|"
                            + "Và nhận được " + player.combineNew.itemsCombine.stream().filter(Item::ismanhthanlinh).findFirst().get().typeName() + " Huỷ diệt tương ứng\n"
                            + "|1|Cần " + Util.numberToMoney(500000000) + " vàng";

                    if (player.inventory.gold < 500000000) {
                        this.dothan.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con", "Đóng");
                        return;
                    }
                    this.dothan.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp\n" + Util.numberToMoney(500000000) + " vàng", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 1) {
                        this.dothan.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp", "Đóng");
                        return;
                    }
                    this.dothan.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case CHE_TAO_TRANG_BI_TS:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.Whis.createOtherMenu(player, ConstNpc.IGNORE_MENU, "ọc ọc", "Yes");
                    return;
                }
                if (player.combineNew.itemsCombine.size() >= 2 && player.combineNew.itemsCombine.size() < 5) {
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isCongThucVip()).count() < 1) {
                        this.Whis.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu Công thức Vip", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isManhTS() && item.quantity >= 999).count() < 1) {
                        this.Whis.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu Mảnh đồ thiên sứ", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDaNangCap()).count() < 1 || player.combineNew.itemsCombine.size() == 4 && player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDaNangCap()).count() < 1) {
                        this.Whis.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu Đá nâng cấp", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDaMayMan()).count() < 1 || player.combineNew.itemsCombine.size() == 4 && player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDaMayMan()).count() < 1) {
                        this.Whis.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu Đá may mắn", "Đóng");
                        return;
                    }
                    Item mTS = null, daNC = null, daMM = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.isNotNullItem()) {
                            if (item.isManhTS()) {
                                mTS = item;
                            } else if (item.isDaNangCap()) {
                                daNC = item;
                            } else if (item.isDaMayMan()) {
                                daMM = item;
                            }
                        }
                    }
                    int tilemacdinh = 35;
                    int tilenew = tilemacdinh;
                    if (daNC != null) {
                        tilenew += (daNC.template.id - 1073) * 10;
                    }

                    String npcSay = "|2|Chế tạo " + player.combineNew.itemsCombine.stream().filter(Item::isManhTS).findFirst().get().typeNameManh() + " Thiên sứ "
                            + player.combineNew.itemsCombine.stream().filter(Item::isCongThucVip).findFirst().get().typeHanhTinh() + "\n"
                            + "|7|Mảnh ghép " + mTS.quantity + "/999\n";
//                            + "|2|Đá nâng cấp " + player.combineNew.itemsCombine.stream().filter(Item::isDaNangCap).findFirst().get().typeDanangcap()
//                            + " (+" + (daNC.template.id - 1073) + "0% tỉ lệ thành công)\n"
//                            + "|2|Đá may mắn " + player.combineNew.itemsCombine.stream().filter(Item::isDaMayMan).findFirst().get().typeDaMayman()
//                            + " (+" + (daMM.template.id - 1078) + "0% tỉ lệ tối đa các chỉ số)\n"
//                            + "|2|Tỉ lệ thành công: " + tilenew + "%\n"
//                            + "|7|Phí nâng cấp: 500 triệu vàng";

                    if (daNC != null) {

                        npcSay += "|2|Đá nâng cấp " + player.combineNew.itemsCombine.stream().filter(Item::isDaNangCap).findFirst().get().typeDanangcap()
                                + " (+" + (daNC.template.id - 1073) + "0% tỉ lệ thành công)\n";
                    }
                    if (daMM != null) {
                        npcSay += "|2|Đá may mắn " + player.combineNew.itemsCombine.stream().filter(Item::isDaMayMan).findFirst().get().typeDaMayman()
                                + " (+" + (daMM.template.id - 1078) + "0% tỉ lệ tối đa các chỉ số)\n";
                    }
                    if (daNC != null) {
                        tilenew += (daNC.template.id - 1073) * 10;
                        npcSay += "|2|Tỉ lệ thành công: " + tilenew + "%\n";
                    } else {
                        npcSay += "|2|Tỉ lệ thành công: " + tilemacdinh + "%\n";
                    }
                    npcSay += "|7|Phí nâng cấp: 500 triệu vàng";
                    if (player.inventory.gold < 500000000) {
                        this.Whis.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Bạn không đủ vàng", "Đóng");
                        return;
                    }
                    this.Whis.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp\n500 Tr vàng", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 4) {
                        this.Whis.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp", "Đóng");
                        return;
                    }
                    this.Whis.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Không đủ nguyên liệu, mời quay lại sau", "Đóng");
                }
                break;
//            case NANG_CAP_LINH_THU:
//                if (player.combineNew.itemsCombine.size() == 3) {
//                    Item linhthu = null;
//                    Item thangtinhthach = null;
//                    Item thucan = null;
//                    for (Item item : player.combineNew.itemsCombine) {
//                        if (item.template.id >= 2019 && item.template.id <= 2026) {
//                            linhthu = item;
//                        } else if (item.template.id == 2030) {
//                            thangtinhthach = item;
//                        } else if (item.template.id >= 663 && item.template.id <= 667) {
//                            thucan = item;
//                        }
//                    }
//                    if (linhthu != null && thangtinhthach != null && thucan != null && thangtinhthach.quantity >= 99) {
//
//                        player.combineNew.goldCombine = GOLD_BONG_TAI;
//                        player.combineNew.gemCombine = GEM_BONG_TAI;
//                        player.combineNew.ratioCombine = RATIO_NANG_CAP;
//
//                        String npcSay = "Linh Thú Siêu Cấp" + "\n|2|";
//                        for (Item.ItemOption io : linhthu.itemOptions) {
//                            npcSay += io.getOptionString() + "\n";
//                        }
//                        npcSay += "|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%" + "\n";
//                        if (player.combineNew.goldCombine <= player.inventory.gold ) {
//                            if (player.combineNew.gemCombine <= player.inventory.gem) {
//                            npcSay += "|1|Cần " + Util.numberToMoney(player.combineNew.goldCombine) + " vàng";
//                            baHatMit.createOtherMenu(player, ConstNpc.MENU_NANG_CAP_LINH_THU, npcSay,
//                                    "Nâng cấp\ncần " + player.combineNew.gemCombine + " ngọc");
//                        } else {
//                          
//                            npcSay += "\n Còn thiếu " + Util.numberToMoney(player.combineNew.gemCombine - player.inventory.gem) + " Ngọc";
//                            baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
//                        } }else {
//                            npcSay += "Còn thiếu " + Util.numberToMoney(player.combineNew.goldCombine - player.inventory.gold) + " vàng";
//                        
//                            baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
//                        }
//                        
//                    } else {
//                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
//                                "Cần 1 Linh Thú, X99 Đá Ma Thuât và 1 Thức Ăn", "Đóng");
//                    }
//                } else {
//                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
//                            "Cần 1 Linh Thú, X99 Đá Ma Thuât và 1 Thức Ăn", "Đóng");
//                }
//                break;    
            case EP_SAO_TRANG_BI:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item trangBi = null;
                    Item daPhaLe = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (isTrangBiPhaLeHoa(item)) {
                            trangBi = item;
                        } else if (isDaPhaLe(item)) {
                            daPhaLe = item;
                        }
                    }
                    int star = 0; //sao pha lê đã ép
                    int starEmpty = 0; //lỗ sao pha lê
                    if (trangBi != null && daPhaLe != null) {
                        for (Item.ItemOption io : trangBi.itemOptions) {
                            if (io.optionTemplate.id == 102) {
                                star = io.param;
                            } else if (io.optionTemplate.id == 107) {
                                starEmpty = io.param;
                            }
                        }
                        if (star < starEmpty) {
                            player.combineNew.gemCombine = getGemEpSao(star);
                            String npcSay = trangBi.template.name + "\n|2|";
                            for (Item.ItemOption io : trangBi.itemOptions) {
                                if (io.optionTemplate.id != 102) {
                                    npcSay += io.getOptionString() + "\n";
                                }
                            }
                            if (daPhaLe.template.type == 30) {
                                for (Item.ItemOption io : daPhaLe.itemOptions) {
                                    npcSay += "|7|" + io.getOptionString() + "\n";
                                }
                            } else {
                                npcSay += "|7|" + ItemService.gI().getItemOptionTemplate(getOptionDaPhaLe(daPhaLe)).name.replaceAll("#", getParamDaPhaLe(daPhaLe) + "") + "\n";
                            }
                            npcSay += "|1|Cần " + Util.numberToMoney(player.combineNew.gemCombine) + " ngọc";
                            baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                    "Nâng cấp\ncần " + player.combineNew.gemCombine + " ngọc");

                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Cần 1 trang bị có lỗ sao pha lê và 1 loại đá pha lê để ép vào", "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 trang bị có lỗ sao pha lê và 1 loại đá pha lê để ép vào", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 trang bị có lỗ sao pha lê và 1 loại đá pha lê để ép vào", "Đóng");
                }
                break;
            case PHA_LE_HOA_TRANG_BI:
                if (player.combineNew.itemsCombine.size() == 1) {
                    Item item = player.combineNew.itemsCombine.get(0);
                    if (isTrangBiPhaLeHoa(item)) {
                        int star = 0;
                        for (Item.ItemOption io : item.itemOptions) {
                            if (io.optionTemplate.id == 107) {
                                star = io.param;
                                break;
                            }
                        }
                        if (star < MAX_STAR_ITEM) {
                            player.combineNew.goldCombine = getGoldPhaLeHoa(star);
                            player.combineNew.gemCombine = getGemPhaLeHoa(star);
                            player.combineNew.ratioCombine = getRatioPhaLeHoa(star);

                            String npcSay = item.template.name + "\n|2|";
                            for (Item.ItemOption io : item.itemOptions) {
                                if (io.optionTemplate.id != 102) {
                                    npcSay += io.getOptionString() + "\n";
                                }
                            }
                            npcSay += "|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%" + "\n";
                            if (player.combineNew.goldCombine <= player.inventory.gold) {
                                npcSay += "|1|Cần " + Util.numberToMoney(player.combineNew.goldCombine) + " vàng";
                                baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                        "Nâng cấp\n" + player.combineNew.gemCombine + " ngọc\n" + "1 lần", "Nâng cấp\n" + (player.combineNew.gemCombine * 10) + " ngọc\n" + "10 Lần", "Nâng cấp\n" + (player.combineNew.gemCombine * 100) + " ngọc\n" + "100 lần");
                            } else {
                                npcSay += "Còn thiếu " + Util.numberToMoney(player.combineNew.goldCombine - player.inventory.gold) + " vàng";
                                baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                            }

                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Vật phẩm đã đạt tối đa sao pha lê", "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Vật phẩm này không thể đục lỗ", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy hãy chọn 1 vật phẩm để pha lê hóa", "Đóng");
                }
                break;
            case NHAP_NGOC_RONG:
                if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                    if (player.combineNew.itemsCombine.size() == 1) {
                        Item item = player.combineNew.itemsCombine.get(0);
                        if (item != null && item.isNotNullItem() && (item.template.id > 14 && item.template.id <= 20) && item.quantity >= 7) {
                            String npcSay = "|2|Con có muốn biến 7 " + item.template.name + " thành\n"
                                    + "1 viên " + ItemService.gI().getTemplate((short) (item.template.id - 1)).name + "\n"
                                    + "|7|Cần 7 " + item.template.name;
                            this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay, "Làm phép", "Từ chối");
                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần 7 viên ngọc rồng 2 sao trở lên", "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần 7 viên ngọc rồng 2 sao trở lên", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hành trang cần ít nhất 1 chỗ trống", "Đóng");
                }
                break;
            case NANG_CAP_VAT_PHAM:
                if (player.combineNew.itemsCombine.size() >= 2 && player.combineNew.itemsCombine.size() < 4) {
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type < 5).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ nâng cấp", "Đóng");
                        break;
                    }
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type == 14).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đá nâng cấp", "Đóng");
                        break;
                    }
                    if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 987).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ nâng cấp", "Đóng");
                        break;
                    }
                    Item itemDo = null;
                    Item itemDNC = null;
                    Item itemDBV = null;
                    for (int j = 0; j < player.combineNew.itemsCombine.size(); j++) {
                        if (player.combineNew.itemsCombine.get(j).isNotNullItem()) {
                            if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.get(j).template.id == 987) {
                                itemDBV = player.combineNew.itemsCombine.get(j);
                                continue;
                            }
                            if (player.combineNew.itemsCombine.get(j).template.type < 5) {
                                itemDo = player.combineNew.itemsCombine.get(j);
                            } else {
                                itemDNC = player.combineNew.itemsCombine.get(j);
                            }
                        }
                    }
                    if (isCoupleItemNangCapCheck(itemDo, itemDNC)) {
                        int level = 0;
                        for (Item.ItemOption io : itemDo.itemOptions) {
                            if (io.optionTemplate.id == 72) {
                                level = io.param;
                                break;
                            }
                        }
                        if (level < MAX_LEVEL_ITEM) {
                            player.combineNew.goldCombine = getGoldNangCapDo(level);
                            player.combineNew.ratioCombine = (float) getTileNangCapDo(level);
                            player.combineNew.countDaNangCap = getCountDaNangCapDo(level);
                            player.combineNew.countDaBaoVe = (short) getCountDaBaoVe(level);
                            String npcSay = "|2|Hiện tại " + itemDo.template.name + " (+" + level + ")\n|0|";
                            for (Item.ItemOption io : itemDo.itemOptions) {
                                if (io.optionTemplate.id != 72) {
                                    npcSay += io.getOptionString() + "\n";
                                }
                            }
                            String option = null;
                            int param = 0;
                            for (Item.ItemOption io : itemDo.itemOptions) {
                                if (io.optionTemplate.id == 47
                                        || io.optionTemplate.id == 6
                                        || io.optionTemplate.id == 0
                                        || io.optionTemplate.id == 7
                                        || io.optionTemplate.id == 14
                                        || io.optionTemplate.id == 22
                                        || io.optionTemplate.id == 23) {
                                    option = io.optionTemplate.name;
                                    param = io.param + (io.param * 10 / 100);
                                    break;
                                }
                            }
                            npcSay += "|2|Sau khi nâng cấp (+" + (level + 1) + ")\n|7|"
                                    + option.replaceAll("#", String.valueOf(param))
                                    + "\n|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%\n"
                                    + (player.combineNew.countDaNangCap > itemDNC.quantity ? "|7|" : "|1|")
                                    + "Cần " + player.combineNew.countDaNangCap + " " + itemDNC.template.name
                                    + "\n" + (player.combineNew.goldCombine > player.inventory.gold ? "|7|" : "|1|")
                                    + "Cần " + Util.numberToMoney(player.combineNew.goldCombine) + " vàng";

                            String daNPC = player.combineNew.itemsCombine.size() == 3 && itemDBV != null ? String.format("\nCần tốn %s đá bảo vệ", player.combineNew.countDaBaoVe) : "";
                            if ((level == 2 || level == 4 || level == 6) && !(player.combineNew.itemsCombine.size() == 3 && itemDBV != null)) {
                                npcSay += "\nNếu thất bại sẽ rớt xuống (+" + (level - 1) + ")";
                            }
                            if (player.combineNew.countDaNangCap > itemDNC.quantity) {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        npcSay, "Còn thiếu\n" + (player.combineNew.countDaNangCap - itemDNC.quantity) + " " + itemDNC.template.name);
                            } else if (player.combineNew.goldCombine > player.inventory.gold) {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        npcSay, "Còn thiếu\n" + Util.numberToMoney((player.combineNew.goldCombine - player.inventory.gold)) + " vàng");
                            } else if (player.combineNew.itemsCombine.size() == 3 && Objects.nonNull(itemDBV) && itemDBV.quantity < player.combineNew.countDaBaoVe) {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        npcSay, "Còn thiếu\n" + (player.combineNew.countDaBaoVe - itemDBV.quantity) + " đá bảo vệ");
                            } else {
                                this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                        npcSay, "Nâng cấp\n" + Util.numberToMoney(player.combineNew.goldCombine) + " vàng" + daNPC, "Từ chối");
                            }
                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Trang bị của ngươi đã đạt cấp tối đa", "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn 1 trang bị và 1 loại đá nâng cấp", "Đóng");
                    }
                } else {
                    if (player.combineNew.itemsCombine.size() > 3) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cất đi con ta không thèm", "Đóng");
                        break;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn 1 trang bị và 1 loại đá nâng cấp", "Đóng");
                }
                break;
            case DAP_SET_KICH_HOAT:
                if (player.combineNew.itemsCombine.size() == 1 || player.combineNew.itemsCombine.size() == 2) {
                    Item dhd = null, dtl = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.isNotNullItem()) {
                            if (item.template.id >= 650 && item.template.id <= 662) {
                                dhd = item;
                            } else if (item.template.id >= 555 && item.template.id <= 567) {
                                dtl = item;
                            }
                        }
                    }
                    if (dhd != null) {
                        String npcSay = "|6|" + dhd.template.name + "\n";
                        for (Item.ItemOption io : dhd.itemOptions) {
                            npcSay += "|2|" + io.getOptionString() + "\n";
                        }
                        if (dtl != null) {
                            npcSay += "|6|" + dtl.template.name + "\n";
                            for (Item.ItemOption io : dtl.itemOptions) {
                                npcSay += "|2|" + io.getOptionString() + "\n";
                            }
                        }
                        npcSay += "Ngươi có muốn chuyển hóa thành\n";
                        npcSay += "|1|" + getNameItemC0(dhd.template.gender, dhd.template.type)
                                + " (ngẫu nhiên kích hoạt)\n|7|Tỉ lệ thành công " + (dtl != null ? "100%" : "40%") + "\n|2|Cần "
                                + Util.numberToMoney(COST_DAP_DO_KICH_HOAT) + " vàng";
                        if (player.inventory.gold >= COST_DAP_DO_KICH_HOAT) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay, "Cần " + Util.numberToMoney(COST_DAP_DO_KICH_HOAT) + " vàng");
                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Còn thiếu\n"
                                    + Util.numberToMoney(player.inventory.gold - COST_DAP_DO_KICH_HOAT) + " vàng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Ta cần 1 món đồ hủy diệt của ngươi để có thể chuyển hóa 1", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Ta cần 1 món đồ hủy diệt của ngươi để có thể chuyển hóa 2", "Đóng");
                }
                break;
            case PHAN_RA_DO_THAN_LINH:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Con hãy đưa ta đồ thần linh để phân rã", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 1) {
                    List<Integer> itemdov2 = new ArrayList<>(Arrays.asList(562, 564, 566));
                    int couponAdd = 0;
                    Item item = player.combineNew.itemsCombine.get(0);
                    if (item.isNotNullItem()) {
                        if (item.template.id >= 555 && item.template.id <= 567) {
                            couponAdd = itemdov2.stream().anyMatch(t -> t == item.template.id) ? 2 : item.template.id == 561 ? 3 : 1;
                        }
                    }
                    if (couponAdd == 0) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Ta chỉ có thể phân rã đồ thần linh thôi", "Đóng");
                        return;
                    }
                    String npcSay = "|2|Sau khi phân rã vật phẩm\n|7|"
                            + "Bạn sẽ nhận được : " + couponAdd + " đá ngũ sắc\n"
                            + (500000000 > player.inventory.gold ? "|7|" : "|1|")
                            + "Cần " + Util.numberToMoney(500000000) + " vàng";

                    if (player.inventory.gold < 500000000) {
                        this.baHatMit.npcChat(player, "Hết tiền rồi\nẢo ít thôi con");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_PHAN_RA_DO_THAN_LINH,
                            npcSay, "Nâng cấp\n" + Util.numberToMoney(500000000) + " vàng", "Từ chối");
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Ta chỉ có thể phân rã 1 lần 1 món đồ thần linh", "Đóng");
                }
                break;
            case DOI_DIEM:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Con hãy đưa cho ta thức ăn", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 1) {
                    List<Integer> itemdov2 = new ArrayList<>(Arrays.asList(663, 664, 665, 666, 667));
                    int couponAdd = 0;
                    Item item = player.combineNew.itemsCombine.get(0);
                    if (item.isNotNullItem()) {
                        if (item.template.id >= 663 && item.template.id <= 667) {
                            couponAdd = itemdov2.stream().anyMatch(t -> t == item.template.id) ? 1 : item.template.id <= 667 ? 1 : 1;
                        }
                    }
                    if (couponAdd == 0) {
                        this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU, "THỨC ĂN!!!!!!!!", "Đóng");
                        return;
                    }
                    String npcSay = "|2|Sau khi phân rã vật phẩm\n|7|"
                            + "Bạn sẽ nhận được : " + couponAdd + " điểm\n"
                            + (500000000 > player.inventory.gold ? "|7|" : "|1|")
                            + "Cần " + Util.numberToMoney(500000000) + " vàng";

                    if (player.inventory.gold < 500000000) {
                        this.npsthiensu64.npcChat(player, "Hết tiền rồi\nẢo ít thôi con");
                        return;
                    }
                    this.npsthiensu64.createOtherMenu(player, ConstNpc.MENU_PHAN_RA_DO_THAN_LINH,
                            npcSay, "Thức Ăn", "Từ chối");
                } else {
                    this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cái Đầu Buồi", "Đóng");
                }
                break;
            case NANG_CAP_DO_TS:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "1 công thức, 1 đá nâng cấp, 999 mảnh thiên sứ", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 4) {
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isCongThucVip()).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu Công Thức", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDaNangCap()).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ hủy diệt", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isManhTS() && item.quantity >= 999).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu mảnh thiên sứ", "Đóng");
                        return;
                    }

                    String npcSay = "|2|Con có muốn đổi các món nguyên liệu ?\n|7|"
                            + "Và nhận được " + player.combineNew.itemsCombine.stream().filter(Item::isManhTS).findFirst().get().typeNameManh() + " thiên sứ tương ứng\n"
                            + "|1|Cần " + Util.numberToMoney(COST) + " vàng";

                    if (player.inventory.gold < COST) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_NANG_CAP_DO_TS,
                            npcSay, "Nâng cấp\n" + Util.numberToMoney(COST) + " vàng", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 3) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cất đi con ta không thèm", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case NANG_CAP_SKH_VIP:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy đưa ta 1 món thiên sứ và 2 món SKH ngẫu nhiên", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 3) {
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDTS()).count() < 1) {
                        this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ thiên sứ", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isSKH()).count() < 2) {
                        this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ kích hoạt ", "Đóng");
                        return;
                    }

                    String npcSay = "|2|Con có muốn đổi các món nguyên liệu ?\n|7|"
                            + "Và nhận được " + player.combineNew.itemsCombine.stream().filter(Item::isDTS).findFirst().get().typeName() + " kích hoạt VIP tương ứng\n"
                            + "|1|Cần " + Util.numberToMoney(COST) + " vàng";

                    if (player.inventory.gold < COST) {
                        this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con", "Đóng");
                        return;
                    }
                    this.npsthiensu64.createOtherMenu(player, ConstNpc.MENU_NANG_DOI_SKH_VIP,
                            npcSay, "Nâng cấp\n" + Util.numberToMoney(COST) + " vàng", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 3) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case NANG_HUY_DIET_LEN_SKH_VIP:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy đưa ta 3 món huỷ diệt....", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 3) {
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDHD()).count() != 3) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ huỷ điệt rồi", "Đóng");
                        return;
                    }
                    Item item1 = player.combineNew.itemsCombine.get(0);
                    Item item2 = player.combineNew.itemsCombine.get(1);
                    Item item3 = player.combineNew.itemsCombine.get(2);

                    String npcSay = "|2|Con có muốn đổi các món nguyên liệu ?\n|7|"
                            + "Và nhận được " + player.combineNew.itemsCombine.stream().filter(Item::isDHD).findFirst().get().typeName() + " kích hoạt VIP tương ứng\n"
                            + "|1|Cần " + Util.numberToMoney(COST) + " vàng";

                    if (player.inventory.gold < COST) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp\n" + Util.numberToMoney(500000000) + " vàng", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 4) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case EP_AN_TRANG_BI:
                if (player.combineNew.itemsCombine.size() == 3) {
                    Item cailoz = null;
                    Item concak = null;
                    Item thoivang = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.isSKH() || item.isTrangBiAn()) {
                            cailoz = item;
                        } else if (item.template.id == 674) {
                            concak = item;
                        } else if (item.template.id == 457) {
                            thoivang = item;
                        }
                    }
                    if (cailoz != null && concak != null && thoivang != null && concak.quantity >= 25) {

                        player.combineNew.goldCombine = 50000;
                        player.combineNew.gemCombine = 0;
                        player.combineNew.ratioCombine = 50;

                        String npcSay = "Trang Bị" + "\n" + player.combineNew.ratioCombine + "%\n5000 Hồng Ngọc";
                        for (Item.ItemOption io : cailoz.itemOptions) {
                            if (player.combineNew.goldCombine <= player.inventory.ruby) {
                                baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                        "NÂNG CẤP!!!!");
                            } else {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        "Cần 1 trang bị cá5;i đéo j cũng được, 50000 hồng ngọc, 25 đá ngũ sắc và 1 thỏi vàng", "Đóng");
                            }
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 trang bị kích hoạt hoặc ấn, 50000 hồng ngọc, 25 đá ngũ sắc và 1 thỏi vàng", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 trang bị kích hoạt hoặc ấn, 50000 hồng ngọc, 25 đá ngũ sắc và 1 thỏi vàng", "Đóng");
                }
                break;
            case NGUYET_AN:
                if (player.combineNew.itemsCombine.size() >= 2 && player.combineNew.itemsCombine.size() < 4) {
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type < 5).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ nâng cấp", "Đóng");
                        break;
                    }
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type == 14).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đá nâng cấp", "Đóng");
                        break;
                    }
                    if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 987).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ nâng cấp", "Đóng");
                        break;
                    }
                    Item itemDo = null;
                    Item itemDNC = null;
                    Item itemDBV = null;
                    for (int j = 0; j < player.combineNew.itemsCombine.size(); j++) {
                        if (player.combineNew.itemsCombine.get(j).isNotNullItem()) {
                            if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.get(j).template.id == 987) {
                                itemDBV = player.combineNew.itemsCombine.get(j);
                                continue;
                            }
                            if (player.combineNew.itemsCombine.get(j).template.type < 5) {
                                itemDo = player.combineNew.itemsCombine.get(j);
                            } else {
                                itemDNC = player.combineNew.itemsCombine.get(j);
                            }
                        }
                    }
                    if (isCoupleItemNangCapCheck(itemDo, itemDNC)) {
                        int level = 0;
                        for (Item.ItemOption io : itemDo.itemOptions) {
                            if (io.optionTemplate.id >= 34 && io.optionTemplate.id <= 36) {
                                level = io.param;
                                break;
                            }
                        }
                        if (level < 1) {
                            player.combineNew.goldCombine = 50000;
                            player.combineNew.ratioCombine = 50;
                            player.combineNew.countDaNangCap = 99;
                            player.combineNew.countDaBaoVe = (short) getCountDaBaoVe(level);
                            String npcSay = "|2|Hiện tại " + itemDo.template.name + " (+" + level + ")\n|0|";
                            for (Item.ItemOption io : itemDo.itemOptions) {
                                if (io.optionTemplate.id != 34 && io.optionTemplate.id != 36 && io.optionTemplate.id != 35 && io.optionTemplate.id != 127 && io.optionTemplate.id != 128 && io.optionTemplate.id != 129 && io.optionTemplate.id != 130 && io.optionTemplate.id != 131 && io.optionTemplate.id != 132 && io.optionTemplate.id != 133 && io.optionTemplate.id != 134 && io.optionTemplate.id != 135) {
                                    npcSay += io.getOptionString() + "\n";
                                }
                            }
//                            String option = null;
//                            int param = 0;
//                            for (Item.ItemOption io : itemDo.itemOptions) {
//                                if (io.optionTemplate.id == 47
//                                        || io.optionTemplate.id == 6
//                                        || io.optionTemplate.id == 0
//                                        || io.optionTemplate.id == 7
//                                        || io.optionTemplate.id == 14
//                                        || io.optionTemplate.id == 22
//                                        || io.optionTemplate.id == 23) {
//                                    option = io.optionTemplate.name;
//                                    param = io.param + (io.param * 0 / 100);
//                                    break;
//                                }
//                            }

                            npcSay += "|2|Nguyệt Ấn"
                                    + "\n|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%\n"
                                    + (player.combineNew.countDaNangCap > itemDNC.quantity ? "|7|" : "|1|")
                                    + "Cần " + player.combineNew.countDaNangCap + " " + itemDNC.template.name
                                    + "\n" + (player.combineNew.goldCombine > player.inventory.ruby ? "|7|" : "|1|")
                                    + "Cần " + Util.numberToMoney(50000) + " Hòng Ngọc";
//                            for (Item.ItemOption io : itemDo.itemOptions) {
                            String daNPC = player.combineNew.itemsCombine.size() == 3 && itemDBV != null ? String.format("\nCần tốn %s đá bảo vệ", player.combineNew.countDaBaoVe) : "";
                            if ((level == 2 || level == 4 || level == 6) && !(player.combineNew.itemsCombine.size() == 3 && itemDBV != null)) {
                                npcSay += "\nNếu thất bại sẽ rớt xuống (+" + (level - 1) + ")";
                                break;
                            }
//                            for (Item.ItemOption io : itemDo.itemOptions) {
//                            else if (io.optionTemplate.id >= 35 && io.optionTemplate.id <= 36) {
//                                    npcSay += "|2|Tinh Ấn"
//                                    + "\n|7|Đã Ép Ấn";
//                                    break;
//                                }
                            if (player.combineNew.countDaNangCap > itemDNC.quantity) {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        npcSay, "Còn thiếu\n" + (player.combineNew.countDaNangCap - itemDNC.quantity) + " " + itemDNC.template.name);
                            } else if (player.combineNew.goldCombine > player.inventory.ruby) {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        npcSay, "Còn thiếu\n" + Util.numberToMoney((25000 - player.inventory.ruby)) + " Hòng Ngọc");
                            } else if (player.combineNew.itemsCombine.size() == 3 && Objects.nonNull(itemDBV) && itemDBV.quantity < player.combineNew.countDaBaoVe) {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        npcSay, "Còn thiếu\n" + (player.combineNew.countDaBaoVe - itemDBV.quantity) + " đá bảo vệ");
                            } else {
                                this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                        npcSay, "Nâng cấp\n" + Util.numberToMoney(player.combineNew.goldCombine) + " Hồng Ngọc" + daNPC, "Từ chối");
                            }
                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Trang bị này như cc tao đéo ép", "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn 1 trang bị và 1 loại đá nâng cấp", "Đóng");
                    }
                } else {
                    if (player.combineNew.itemsCombine.size() > 3) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cất đi con ta không thèm", "Đóng");
                        break;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn 1 trang bị và 1 loại đá nâng cấp", "Đóng");
                }
                break;
        }
    }

    /**
     * Bắt đầu đập đồ - điều hướng từng loại đập đồ
     *
     * @param player
     */
    public void startCombine(Player player) {
        switch (player.combineNew.typeCombine) {
            case EP_SAO_TRANG_BI:
                epSaoTrangBi(player);
                break;
            case PHA_LE_HOA_TRANG_BI:
                phaLeHoaTrangBi(player);
                break;
//            case CHUYEN_HOA_TRANG_BI:
//
//                break;
            case NHAP_NGOC_RONG:
                nhapNgocRong(player);
                break;

            case PHAN_RA_DO_THAN_LINH:
                phanradothanlinh(player);
                break;
            case DAP_SET_KICH_HOAT:
                dapDoKichHoat(player);
                break;
            case NANG_CAP_DO_TS:
                openDTS(player);
                break;
            case CHE_TAO_TRANG_BI_TS:
                cheTaoDoTS(player);
                break;
            case NANG_CAP_DO_THAN:
                nangcapdothan(player);
                break;
            case NANG_CAP_SKH_VIP:
                openSKHVIP(player);
                break;
            case NANG_CAP_VAT_PHAM:
                nangCapVatPham(player);
                break;
            case NANG_CAP_CHAN_MENH:
                nangCapChanMenh(player);
                break;
            case NANG_HUY_DIET_LEN_SKH_VIP:
                huyDietLenKichHoatVIP(player);
                break;
            case NANG_CAP_BONG_TAI:
                nangCapBongTai(player);
                break;
            case MO_CHI_SO_BONG_TAI:
                moChiSoBongTai(player);
                break;
            case NANG_CAP_BONG_TAI_CAP3:
                nangCapBongTaicap3(player);
                break;
            case MO_CHI_SO_BONG_TAI_CAP3:
                moChiSoBongTaicap3(player);
                break;
            case NANG_CAP_BONG_TAI_CAP4:
                nangCapBongTaicap4(player);
                break;
            case GIA_HAN_VAT_PHAM:
                GiaHanTrangBi(player);
                break;
                case HP:
                HP(player);
                break;
                case SD:
                SD(player);
                break;
                case KI:
                KI(player);
                break;
                case GIAP:
                GIAP(player);
                break;
            case MO_CHI_SO_BONG_TAI_CAP4:
                moChiSoBongTaicap4(player);
                break;
            case LUYEN_HOA_CHIEN_LINH:
                break;
            case EP_AN_TRANG_BI:
                epantrangbi(player);
                break;
            case NGUYET_AN:
                nguyetan(player);
                break;
            case DOI_DIEM:
                doidiem(player);
                break;
        }

        player.iDMark.setIndexMenu(ConstNpc.IGNORE_MENU);
        player.combineNew.clearParamCombine();
        player.combineNew.lastTimeCombine = System.currentTimeMillis();

    }

    public void GetTrangBiKichHoathuydiet(Player player, int id) {
        Item item = ItemService.gI().createNewItem((short) id);
        int[][] optionNormal = {{127, 128}, {130, 132}, {133, 135}};
        int[][] paramNormal = {{139, 140}, {142, 144}, {136, 138}};
        int[][] optionVIP = {{129}, {131}, {134}};
        int[][] paramVIP = {{141}, {143}, {137}};
        int random = Util.nextInt(optionNormal.length);
        int randomSkh = Util.nextInt(100);
        if (item.template.type == 0) {
            item.itemOptions.add(new ItemOption(47, Util.nextInt(1500, 2000)));
        }
        if (item.template.type == 1) {
            item.itemOptions.add(new ItemOption(22, Util.nextInt(100, 150)));
        }
        if (item.template.type == 2) {
            item.itemOptions.add(new ItemOption(0, Util.nextInt(9000, 11000)));
        }
        if (item.template.type == 3) {
            item.itemOptions.add(new ItemOption(23, Util.nextInt(90, 150)));
        }
        if (item.template.type == 4) {
            item.itemOptions.add(new ItemOption(14, Util.nextInt(15, 20)));
        }
        if (randomSkh <= 20) {//tile ra do kich hoat
            if (randomSkh <= 5) { // tile ra option vip
                item.itemOptions.add(new ItemOption(optionVIP[player.gender][0], 1));
                item.itemOptions.add(new ItemOption(paramVIP[player.gender][0], 1));
                item.itemOptions.add(new ItemOption(30, 0));
            } else {// 
                item.itemOptions.add(new ItemOption(optionNormal[player.gender][random], 1));
                item.itemOptions.add(new ItemOption(paramNormal[player.gender][random], 1));
                item.itemOptions.add(new ItemOption(30, 0));
            }
        }

        InventoryServiceNew.gI().addItemBag(player, item);
        InventoryServiceNew.gI().sendItemBags(player);
    }

    public void GetTrangBiKichHoatthiensu(Player player, int id) {
        Item item = ItemService.gI().createNewItem((short) id);
        int[][] optionNormal = {{127, 128}, {130, 132}, {133, 135}};
        int[][] paramNormal = {{139, 140}, {142, 144}, {136, 138}};
        int[][] optionVIP = {{129}, {131}, {134}};
        int[][] paramVIP = {{141}, {143}, {137}};
        int random = Util.nextInt(optionNormal.length);
        int randomSkh = Util.nextInt(100);
        if (item.template.type == 0) {
            item.itemOptions.add(new ItemOption(47, Util.nextInt(2000, 2500)));
        }
        if (item.template.type == 1) {
            item.itemOptions.add(new ItemOption(22, Util.nextInt(150, 200)));
        }
        if (item.template.type == 2) {
            item.itemOptions.add(new ItemOption(0, Util.nextInt(18000, 20000)));
        }
        if (item.template.type == 3) {
            item.itemOptions.add(new ItemOption(23, Util.nextInt(150, 200)));
        }
        if (item.template.type == 4) {
            item.itemOptions.add(new ItemOption(14, Util.nextInt(20, 25)));
        }
        if (randomSkh <= 20) {//tile ra do kich hoat
            if (randomSkh <= 5) { // tile ra option vip
                item.itemOptions.add(new ItemOption(optionVIP[player.gender][0], 1));
                item.itemOptions.add(new ItemOption(paramVIP[player.gender][0], 1));
                item.itemOptions.add(new ItemOption(30, 0));
            } else {// 
                item.itemOptions.add(new ItemOption(optionNormal[player.gender][random], 1));
                item.itemOptions.add(new ItemOption(paramNormal[player.gender][random], 1));
                item.itemOptions.add(new ItemOption(30, 0));
            }
        }

        InventoryServiceNew.gI().addItemBag(player, item);
        InventoryServiceNew.gI().sendItemBags(player);
    }

    public void khilv2(Player player, int id) {
        Item item = ItemService.gI().createNewItem((short) id);
        item.itemOptions.add(new ItemOption(50, 20));//sd
        item.itemOptions.add(new ItemOption(77, 20));//hp
        item.itemOptions.add(new ItemOption(103, 20));//ki
        item.itemOptions.add(new ItemOption(14, 20));//cm
        item.itemOptions.add(new ItemOption(5, 20));//sd cm
        item.itemOptions.add(new ItemOption(106, 0));
        item.itemOptions.add(new ItemOption(34, 0));
        InventoryServiceNew.gI().addItemBag(player, item);
        InventoryServiceNew.gI().sendItemBags(player);
    }

    public void khilv3(Player player, int id) {
        Item item = ItemService.gI().createNewItem((short) id);
        item.itemOptions.add(new ItemOption(50, 22));//sd
        item.itemOptions.add(new ItemOption(77, 22));//hp
        item.itemOptions.add(new ItemOption(103, 22));//ki
        item.itemOptions.add(new ItemOption(14, 22));//cm
        item.itemOptions.add(new ItemOption(5, 22));//sd cm
        item.itemOptions.add(new ItemOption(106, 0));
        item.itemOptions.add(new ItemOption(35, 0));
        InventoryServiceNew.gI().addItemBag(player, item);
        InventoryServiceNew.gI().sendItemBags(player);
    }

    public void khilv4(Player player, int id) {
        Item item = ItemService.gI().createNewItem((short) id);
        item.itemOptions.add(new ItemOption(50, 24));//sd
        item.itemOptions.add(new ItemOption(77, 24));//hp
        item.itemOptions.add(new ItemOption(103, 24));//ki
        item.itemOptions.add(new ItemOption(14, 24));//cm
        item.itemOptions.add(new ItemOption(5, 24));//sd cm
        item.itemOptions.add(new ItemOption(106, 0));
        item.itemOptions.add(new ItemOption(36, 0));
        InventoryServiceNew.gI().addItemBag(player, item);
        InventoryServiceNew.gI().sendItemBags(player);
    }

    public void khilv5(Player player, int id) {
        Item item = ItemService.gI().createNewItem((short) id);
        item.itemOptions.add(new ItemOption(50, 26));//sd
        item.itemOptions.add(new ItemOption(77, 26));//hp
        item.itemOptions.add(new ItemOption(103, 26));//ki
        item.itemOptions.add(new ItemOption(14, 26));//cm
        item.itemOptions.add(new ItemOption(5, 26));//sd cm
        item.itemOptions.add(new ItemOption(106, 0));
        item.itemOptions.add(new ItemOption(36, 0));
        InventoryServiceNew.gI().addItemBag(player, item);
        InventoryServiceNew.gI().sendItemBags(player);
    }

    private void doiKiemThan(Player player) {
        if (player.combineNew.itemsCombine.size() == 3) {
            Item keo = null, luoiKiem = null, chuoiKiem = null;
            for (Item it : player.combineNew.itemsCombine) {
                if (it.template.id == 2015) {
                    keo = it;
                } else if (it.template.id == 2016) {
                    chuoiKiem = it;
                } else if (it.template.id == 2017) {
                    luoiKiem = it;
                }
            }
            if (keo != null && keo.quantity >= 99 && luoiKiem != null && chuoiKiem != null) {
                if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                    sendEffectSuccessCombine(player);
                    Item item = ItemService.gI().createNewItem((short) 2018);
                    item.itemOptions.add(new Item.ItemOption(50, Util.nextInt(9, 15)));
                    item.itemOptions.add(new Item.ItemOption(77, Util.nextInt(8, 15)));
                    item.itemOptions.add(new Item.ItemOption(103, Util.nextInt(8, 15)));
                    if (Util.isTrue(80, 100)) {
                        item.itemOptions.add(new Item.ItemOption(93, Util.nextInt(1, 15)));
                    }
                    InventoryServiceNew.gI().addItemBag(player, item);

                    InventoryServiceNew.gI().subQuantityItemsBag(player, keo, 99);
                    InventoryServiceNew.gI().subQuantityItemsBag(player, luoiKiem, 1);
                    InventoryServiceNew.gI().subQuantityItemsBag(player, chuoiKiem, 1);

                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.gI().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }

    private void doiChuoiKiem(Player player) {
        if (player.combineNew.itemsCombine.size() == 1) {
            Item manhNhua = player.combineNew.itemsCombine.get(0);
            if (manhNhua.template.id == 2014 && manhNhua.quantity >= 99) {
                if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                    sendEffectSuccessCombine(player);
                    Item item = ItemService.gI().createNewItem((short) 2016);
                    InventoryServiceNew.gI().addItemBag(player, item);

                    InventoryServiceNew.gI().subQuantityItemsBag(player, manhNhua, 99);

                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.gI().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }

    private void doiLuoiKiem(Player player) {
        if (player.combineNew.itemsCombine.size() == 1) {
            Item manhSat = player.combineNew.itemsCombine.get(0);
            if (manhSat.template.id == 2013 && manhSat.quantity >= 99) {
                if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                    sendEffectSuccessCombine(player);
                    Item item = ItemService.gI().createNewItem((short) 2017);
                    InventoryServiceNew.gI().addItemBag(player, item);
                    InventoryServiceNew.gI().subQuantityItemsBag(player, manhSat, 99);

                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.gI().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }

    private void doiManhKichHoat(Player player) {
        if (player.combineNew.itemsCombine.size() == 2 || player.combineNew.itemsCombine.size() == 3) {
            Item nr1s = null, doThan = null, buaBaoVe = null;
            for (Item it : player.combineNew.itemsCombine) {
                if (it.template.id == 14) {
                    nr1s = it;
                } else if (it.template.id == 2010) {
                    buaBaoVe = it;
                } else if (it.template.id >= 555 && it.template.id <= 567) {
                    doThan = it;
                }
            }

            if (nr1s != null && doThan != null) {
                if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0
                        && player.inventory.gold >= COST_DOI_MANH_KICH_HOAT) {
                    player.inventory.gold -= COST_DOI_MANH_KICH_HOAT;
                    int tiLe = buaBaoVe != null ? 100 : 50;
                    if (Util.isTrue(tiLe, 100)) {
                        sendEffectSuccessCombine(player);
                        Item item = ItemService.gI().createNewItem((short) 2009);
                        item.itemOptions.add(new Item.ItemOption(30, 0));
                        InventoryServiceNew.gI().addItemBag(player, item);
                    } else {
                        sendEffectFailCombine(player);
                    }
                    InventoryServiceNew.gI().subQuantityItemsBag(player, nr1s, 1);
                    InventoryServiceNew.gI().subQuantityItemsBag(player, doThan, 1);
                    if (buaBaoVe != null) {
                        InventoryServiceNew.gI().subQuantityItemsBag(player, buaBaoVe, 1);
                    }
                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.gI().sendMoney(player);
                    reOpenItemCombine(player);
                }
            } else {
                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn 1 trang bị thần linh và 1 viên ngọc rồng 1 sao", "Đóng");
            }
        }
    }

    private void phanradothanlinh(Player player) {
        if (player.combineNew.itemsCombine.size() == 1) {
            player.inventory.gold -= 20000000;
            List<Integer> itemdov2 = new ArrayList<>(Arrays.asList(562, 564, 566));
            Item item = player.combineNew.itemsCombine.get(0);
            int couponAdd = itemdov2.stream().anyMatch(t -> t == item.template.id) ? 2 : item.template.id == 561 ? 3 : 1;
            sendEffectSuccessCombine(player);
            Item dangusac = ItemService.gI().createNewItem((short) 674);
            Item dangusac1 = ItemService.gI().createNewItem((short) 674);
            Item dangusac2 = ItemService.gI().createNewItem((short) 674);
            InventoryServiceNew.gI().addItemBag(player, dangusac);
            InventoryServiceNew.gI().sendItemBags(player);
            if (item.template.id == 561) {
                InventoryServiceNew.gI().addItemBag(player, dangusac);
                InventoryServiceNew.gI().addItemBag(player, dangusac1);
                InventoryServiceNew.gI().addItemBag(player, dangusac2);
                InventoryServiceNew.gI().sendItemBags(player);
            } else if (item.template.id == 562 || item.template.id == 564 || item.template.id == 566) {
                InventoryServiceNew.gI().addItemBag(player, dangusac);
                InventoryServiceNew.gI().addItemBag(player, dangusac1);
                InventoryServiceNew.gI().sendItemBags(player);
            }
            Service.gI().sendThongBaoOK(player, "Mày Nhận Được Đá Ngũ Sắc");
            InventoryServiceNew.gI().subQuantityItemsBag(player, item, 1);
            player.combineNew.itemsCombine.clear();
            InventoryServiceNew.gI().sendItemBags(player);
            Service.gI().sendMoney(player);
            reOpenItemCombine(player);
        }
    }

    private void doidiem(Player player) {
        if (player.combineNew.itemsCombine.size() == 1) {
            player.inventory.gold -= 0;
            List<Integer> itemdov2 = new ArrayList<>(Arrays.asList(663, 664, 665, 666, 667));
            Item item = player.combineNew.itemsCombine.get(0);
            sendEffectSuccessCombine(player);
            if (item.quantity < 99) {
                Service.gI().sendThongBaoOK(player, "Đéo Đủ Thức Ăn");
            } else if (item.quantity >= 99) {
                InventoryServiceNew.gI().sendItemBags(player);
                player.inventory.coupon += 1;
                Service.gI().sendThongBaoOK(player, "Bú 1 Điểm");
                InventoryServiceNew.gI().subQuantityItemsBag(player, item, 99);
                player.combineNew.itemsCombine.clear();
                InventoryServiceNew.gI().sendItemBags(player);
                Service.gI().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    public void openDTS(Player player) {
        //check sl đồ tl, đồ hd
        // new update 2 mon huy diet + 1 mon than linh(skh theo style) +  5 manh bat ki
        if (player.combineNew.itemsCombine.size() != 4) {
            Service.gI().sendThongBao(player, "Thiếu đồ");
            return;
        }
        if (player.inventory.gold < COST) {
            Service.gI().sendThongBao(player, "Ảo ít thôi con...");
            return;
        }
        if (InventoryServiceNew.gI().getCountEmptyBag(player) < 1) {
            Service.gI().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống hành trang");
            return;
        }
        Item itemTL = player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDaNangCap()).findFirst().get();
        List<Item> itemHDs = player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isCongThucVip()).collect(Collectors.toList());
        Item itemManh = player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isManhTS() && item.quantity >= 999).findFirst().get();

        player.inventory.gold -= COST;
        sendEffectSuccessCombine(player);
        short[][] itemIds = {{1048, 1051, 1054, 1057, 1060}, {1049, 1052, 1055, 1058, 1061}, {1050, 1053, 1056, 1059, 1062}}; // thứ tự td - 0,nm - 1, xd - 2

        Item itemTS = ItemService.gI().DoThienSu(itemIds[player.gender > 2 ? player.gender : player.gender][itemManh.typeIdManh()], player.gender);
        InventoryServiceNew.gI().addItemBag(player, itemTS);

        InventoryServiceNew.gI().subQuantityItemsBag(player, itemTL, 1);
        InventoryServiceNew.gI().subQuantityItemsBag(player, itemManh, 999);
        itemHDs.forEach(item -> InventoryServiceNew.gI().subQuantityItemsBag(player, item, 1));
        InventoryServiceNew.gI().sendItemBags(player);
        Service.gI().sendMoney(player);
        Service.gI().sendThongBao(player, "Bạn đã nhận được " + itemTS.template.name);
        player.combineNew.itemsCombine.clear();
        reOpenItemCombine(player);
    }

    private void nangcapdothan(Player player) {
        if (player.combineNew.itemsCombine.size() == 1) {
            Item mtl = null;
            mtl = player.combineNew.itemsCombine.get(0);
            if (mtl != null && mtl.ismanhthanlinh()) {
                if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0 //check chỗ trống hành trang
                        && player.inventory.gold >= COST_DAP_DO_KICH_HOAT) {
                    player.inventory.gold -= COST_DAP_DO_KICH_HOAT;
                    int tiLe = 100; // tỉ lệ
                    if (Util.isTrue(tiLe, 100)) {
                        sendEffectSuccessCombine(player);
                        ItemService.gI().OpenDTL(player, player.gender, mtl.template.type);
                        InventoryServiceNew.gI().subQuantityItemsBag(player, mtl, 55);
                    } else {
                        sendEffectFailCombine(player);
                    }
                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }

    public void cheTaoDoTS(Player player) {
        // Công thức vip + x999 Mảnh thiên sứ + đá nâng cấp + đá may mắn
        if (player.combineNew.itemsCombine.size() < 2 || player.combineNew.itemsCombine.size() > 4) {
            Service.getInstance().sendThongBao(player, "Thiếu vật phẩm, vui lòng thêm vào");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isCongThucVip()).count() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu Công thức Vip");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isManhTS() && item.quantity >= 999).count() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu Mảnh thiên sứ");
            return;
        }
        if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDaNangCap()).count() != 1 || player.combineNew.itemsCombine.size() == 4 && player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDaNangCap()).count() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu Đá nâng cấp");
            return;
        }
        if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDaMayMan()).count() != 1 || player.combineNew.itemsCombine.size() == 4 && player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDaMayMan()).count() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu Đá may mắn");
            return;
        }
        Item mTS = null, daNC = null, daMM = null, CtVip = null;
        for (Item item : player.combineNew.itemsCombine) {
            if (item.isNotNullItem()) {
                if (item.isManhTS()) {
                    mTS = item;
                } else if (item.isDaNangCap()) {
                    daNC = item;
                } else if (item.isDaMayMan()) {
                    daMM = item;
                } else if (item.isCongThucVip()) {
                    CtVip = item;
                }
            }
        }
        if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {//check chỗ trống hành trang
            if (player.inventory.gold < 500000000) {
                Service.getInstance().sendThongBao(player, "Không đủ vàng để thực hiện");
                return;
            }
            player.inventory.gold -= 500000000;

            int tilemacdinh = 35;
            int tileLucky = 20;
            if (daNC != null) {
                tilemacdinh += (daNC.template.id - 1073) * 10;
            } else {
                tilemacdinh = tilemacdinh;
            }
            if (daMM != null) {
                tileLucky += tileLucky * (daMM.template.id - 1078) * 10 / 100;
            } else {
                tileLucky = tileLucky;
            }

            if (Util.nextInt(0, 100) < tilemacdinh) {
                Item itemCtVip = player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isCongThucVip()).findFirst().get();
                if (daNC != null) {
                    Item itemDaNangC = player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDaNangCap()).findFirst().get();
                }
                if (daMM != null) {
                    Item itemDaMayM = player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDaMayMan()).findFirst().get();
                }
                Item itemManh = player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isManhTS() && item.quantity >= 999).findFirst().get();

                tilemacdinh = Util.nextInt(0, 50);
                if (tilemacdinh == 49) {
                    tilemacdinh = 20;
                } else if (tilemacdinh == 48 || tilemacdinh == 47) {
                    tilemacdinh = 19;
                } else if (tilemacdinh == 46 || tilemacdinh == 45) {
                    tilemacdinh = 18;
                } else if (tilemacdinh == 44 || tilemacdinh == 43) {
                    tilemacdinh = 17;
                } else if (tilemacdinh == 42 || tilemacdinh == 41) {
                    tilemacdinh = 16;
                } else if (tilemacdinh == 40 || tilemacdinh == 39) {
                    tilemacdinh = 15;
                } else if (tilemacdinh == 38 || tilemacdinh == 37) {
                    tilemacdinh = 14;
                } else if (tilemacdinh == 36 || tilemacdinh == 35) {
                    tilemacdinh = 13;
                } else if (tilemacdinh == 34 || tilemacdinh == 33) {
                    tilemacdinh = 12;
                } else if (tilemacdinh == 32 || tilemacdinh == 31) {
                    tilemacdinh = 11;
                } else if (tilemacdinh == 30 || tilemacdinh == 29) {
                    tilemacdinh = 10;
                } else if (tilemacdinh <= 28 || tilemacdinh >= 26) {
                    tilemacdinh = 9;
                } else if (tilemacdinh <= 25 || tilemacdinh >= 23) {
                    tilemacdinh = 8;
                } else if (tilemacdinh <= 22 || tilemacdinh >= 20) {
                    tilemacdinh = 7;
                } else if (tilemacdinh <= 19 || tilemacdinh >= 17) {
                    tilemacdinh = 6;
                } else if (tilemacdinh <= 16 || tilemacdinh >= 14) {
                    tilemacdinh = 5;
                } else if (tilemacdinh <= 13 || tilemacdinh >= 11) {
                    tilemacdinh = 4;
                } else if (tilemacdinh <= 10 || tilemacdinh >= 8) {
                    tilemacdinh = 3;
                } else if (tilemacdinh <= 7 || tilemacdinh >= 5) {
                    tilemacdinh = 2;
                } else if (tilemacdinh <= 4 || tilemacdinh >= 2) {
                    tilemacdinh = 1;
                } else if (tilemacdinh <= 1) {
                    tilemacdinh = 0;
                }
                short[][] itemIds = {{1048, 1051, 1054, 1057, 1060}, {1049, 1052, 1055, 1058, 1061}, {1050, 1053, 1056, 1059, 1062}}; // thứ tự td - 0,nm - 1, xd - 2

                Item itemTS = ItemService.gI().DoThienSu(itemIds[itemCtVip.template.gender > 2 ? player.gender : itemCtVip.template.gender][itemManh.typeIdManh()], itemCtVip.template.gender);

                tilemacdinh += 10;

                if (tilemacdinh > 0) {
                    for (byte i = 0; i < itemTS.itemOptions.size(); i++) {
                        if (itemTS.itemOptions.get(i).optionTemplate.id != 21 && itemTS.itemOptions.get(i).optionTemplate.id != 30) {
                            itemTS.itemOptions.get(i).param += (itemTS.itemOptions.get(i).param * tilemacdinh / 100);
                        }
                    }
                }
                tilemacdinh = Util.nextInt(0, 100);

                if (tilemacdinh <= tileLucky) {
                    if (tilemacdinh >= (tileLucky - 3)) {
                        tileLucky = 3;
                    } else if (tilemacdinh <= (tileLucky - 4) && tilemacdinh >= (tileLucky - 10)) {
                        tileLucky = 2;
                    } else {
                        tileLucky = 1;
                    }
                    itemTS.itemOptions.add(new Item.ItemOption(15, tileLucky));
                    ArrayList<Integer> listOptionBonus = new ArrayList<>();
                    listOptionBonus.add(50);
                    listOptionBonus.add(77);
                    listOptionBonus.add(103);
                    listOptionBonus.add(98);
                    listOptionBonus.add(99);
                    for (int i = 0; i < tileLucky; i++) {
                        tilemacdinh = Util.nextInt(0, listOptionBonus.size());
                        itemTS.itemOptions.add(new ItemOption(listOptionBonus.get(tilemacdinh), Util.nextInt(1, 5)));
                        listOptionBonus.remove(tilemacdinh);
                    }
                }

                InventoryServiceNew.gI().addItemBag(player, itemTS);
                sendEffectSuccessCombine(player);
            } else {
                sendEffectFailCombine(player);
            }
            if (mTS != null && daMM != null && daNC != null && CtVip != null) {
                InventoryServiceNew.gI().subQuantityItemsBag(player, CtVip, 1);
                InventoryServiceNew.gI().subQuantityItemsBag(player, daNC, 1);
                InventoryServiceNew.gI().subQuantityItemsBag(player, mTS, 999);
                InventoryServiceNew.gI().subQuantityItemsBag(player, daMM, 1);
            } else if (CtVip != null && mTS != null) {
                InventoryServiceNew.gI().subQuantityItemsBag(player, CtVip, 1);
                InventoryServiceNew.gI().subQuantityItemsBag(player, mTS, 999);
            } else if (CtVip != null && mTS != null && daNC != null) {
                InventoryServiceNew.gI().subQuantityItemsBag(player, CtVip, 1);
                InventoryServiceNew.gI().subQuantityItemsBag(player, mTS, 999);
                InventoryServiceNew.gI().subQuantityItemsBag(player, daNC, 1);
            } else if (CtVip != null && mTS != null && daMM != null) {
                InventoryServiceNew.gI().subQuantityItemsBag(player, CtVip, 1);
                InventoryServiceNew.gI().subQuantityItemsBag(player, mTS, 999);
                InventoryServiceNew.gI().subQuantityItemsBag(player, daMM, 1);
            }

            InventoryServiceNew.gI().sendItemBags(player);
            Service.getInstance().sendMoney(player);
            reOpenItemCombine(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống hành trang");
        }
    }

    public void huyDietLenKichHoatVIP(Player player) {
        // 3 đồ hd muốn bỏ gì bỏ món đầu k làm gốc
        if (player.combineNew.itemsCombine.size() != 3) {
            Service.gI().sendThongBao(player, "Sai nguyên liệu");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDHD()).count() != 3) {
            Service.gI().sendThongBao(player, "Thiếu đồ huỷ diệt");
            return;
        }
        if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
            if (player.inventory.gold < 1) {
                Service.gI().sendThongBao(player, "Con cần thêm vàng để đổi...");
                return;
            }
            player.inventory.gold -= COST;
            Item dohdodayne = player.combineNew.itemsCombine.stream().filter(Item::isDHD).findFirst().get();
            List<Item> itemdohdlucbovao = player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDHD()).collect(Collectors.toList());
            CombineServiceNew.gI().sendEffectOpenItem(player, dohdodayne.template.iconID, dohdodayne.template.iconID);
            short itemId;
            if (dohdodayne.template.gender == 3 || dohdodayne.template.type == 4) {
                itemId = Manager.radaSKHVip[0];
                if (player.getSession().bdPlayer > 0 && Util.isTrue(1, (int) (100 / player.getSession().bdPlayer))) {
                    itemId = Manager.radaSKHVip[0];
                }
            } else {
                itemId = Manager.doSKHVip[dohdodayne.template.gender][dohdodayne.template.type][0];
                if (player.getSession().bdPlayer > 0 && Util.isTrue(1, (int) (100 / player.getSession().bdPlayer))) {
                    itemId = Manager.doSKHVip[dohdodayne.template.gender][dohdodayne.template.type][0];
                }
            }
            int skhId = ItemService.gI().randomSKHId(player.gender);
            Item item = ItemService.gI().itemSKH(itemId, skhId);
            InventoryServiceNew.gI().addItemBag(player, item);
            InventoryServiceNew.gI().subQuantityItemsBag(player, dohdodayne, 1);
            itemdohdlucbovao.forEach(it -> InventoryServiceNew.gI().subQuantityItemsBag(player, it, 1));
            InventoryServiceNew.gI().sendItemBags(player);
            Service.gI().sendMoney(player);
            player.combineNew.itemsCombine.clear();
            reOpenItemCombine(player);
        } else {
            Service.gI().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống hành trang");
        }
    }

    public void openSKHVIP(Player player) {
        // 1 thiên sứ + 2 món kích hoạt -- món đầu kh làm gốc
        if (player.combineNew.itemsCombine.size() != 3) {
            Service.gI().sendThongBao(player, "Thiếu nguyên liệu");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDTS()).count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu đồ thiên sứ");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isSKH()).count() != 2) {
            Service.gI().sendThongBao(player, "Thiếu đồ kích hoạt");
            return;
        }
        if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
            if (player.inventory.gold < 1) {
                Service.gI().sendThongBao(player, "Con cần thêm vàng để đổi...");
                return;
            }
            player.inventory.gold -= COST;
            Item itemTS = player.combineNew.itemsCombine.stream().filter(Item::isDTS).findFirst().get();
            List<Item> itemSKH = player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isSKH()).collect(Collectors.toList());
            CombineServiceNew.gI().sendEffectOpenItem(player, itemTS.template.iconID, itemTS.template.iconID);
            short itemId;
            if (itemTS.template.gender == 3 || itemTS.template.type == 4) {
                itemId = Manager.radaSKHVip[Util.nextInt(0, 5)];
                if (player.getSession().bdPlayer > 0 && Util.isTrue(1, (int) (100 / player.getSession().bdPlayer))) {
                    itemId = Manager.radaSKHVip[6];
                }
            } else {
                itemId = Manager.doSKHVip[itemTS.template.gender][itemTS.template.type][Util.nextInt(0, 5)];
                if (player.getSession().bdPlayer > 0 && Util.isTrue(1, (int) (100 / player.getSession().bdPlayer))) {
                    itemId = Manager.doSKHVip[itemTS.template.gender][itemTS.template.type][6];
                }
            }
            int skhId = ItemService.gI().randomSKHId(itemTS.template.gender);
            Item item;
            if (new Item(itemId).isDTL()) {
                item = Util.ratiItemTL(itemId);
                item.itemOptions.add(new Item.ItemOption(skhId, 1));
                item.itemOptions.add(new Item.ItemOption(ItemService.gI().optionIdSKH(skhId), 1));
                item.itemOptions.remove(item.itemOptions.stream().filter(itemOption -> itemOption.optionTemplate.id == 21).findFirst().get());
                item.itemOptions.add(new Item.ItemOption(21, 15));
                item.itemOptions.add(new Item.ItemOption(30, 1));
            } else {
                item = ItemService.gI().itemSKH(itemId, skhId);
            }
            InventoryServiceNew.gI().addItemBag(player, item);
            InventoryServiceNew.gI().subQuantityItemsBag(player, itemTS, 1);
            itemSKH.forEach(i -> InventoryServiceNew.gI().subQuantityItemsBag(player, i, 1));
            InventoryServiceNew.gI().sendItemBags(player);
            Service.gI().sendMoney(player);
            player.combineNew.itemsCombine.clear();
            reOpenItemCombine(player);
        } else {
            Service.gI().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống hành trang");
        }
    }

    private void dapDoKichHoat(Player player) {
        if (player.combineNew.itemsCombine.size() == 1 || player.combineNew.itemsCombine.size() == 2) {
            Item dhd = null, dtl = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.isNotNullItem()) {
                    if (item.template.id >= 650 && item.template.id <= 662) {
                        dhd = item;
                    } else if (item.template.id >= 555 && item.template.id <= 567) {
                        dtl = item;
                    }
                }
            }
            if (dhd != null) {
                if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0 //check chỗ trống hành trang
                        && player.inventory.gold >= COST_DAP_DO_KICH_HOAT) {
                    player.inventory.gold -= COST_DAP_DO_KICH_HOAT;
                    int tiLe = dtl != null ? 100 : 50;
                    if (Util.isTrue(tiLe, 100)) {
                        sendEffectSuccessCombine(player);
                        Item item = ItemService.gI().createNewItem((short) getTempIdItemC0(dhd.template.gender, dhd.template.type));
                        RewardService.gI().initBaseOptionClothes(item.template.id, item.template.type, item.itemOptions);
                        RewardService.gI().initActivationOption(item.template.gender < 3 ? item.template.gender : player.gender, item.template.type, item.itemOptions);
                        InventoryServiceNew.gI().addItemBag(player, item);
                    } else {
                        sendEffectFailCombine(player);
                    }
                    InventoryServiceNew.gI().subQuantityItemsBag(player, dhd, 1);
                    if (dtl != null) {
                        InventoryServiceNew.gI().subQuantityItemsBag(player, dtl, 1);
                    }
                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.gI().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }

    private void doiVeHuyDiet(Player player) {
        if (player.combineNew.itemsCombine.size() == 1) {
            Item item = player.combineNew.itemsCombine.get(0);
            if (item.isNotNullItem() && item.template.id >= 555 && item.template.id <= 567) {
                if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0
                        && player.inventory.gold >= COST_DOI_VE_DOI_DO_HUY_DIET) {
                    player.inventory.gold -= COST_DOI_VE_DOI_DO_HUY_DIET;
                    Item ticket = ItemService.gI().createNewItem((short) (2001 + item.template.type));
                    ticket.itemOptions.add(new Item.ItemOption(30, 0));
                    InventoryServiceNew.gI().subQuantityItemsBag(player, item, 1);
                    InventoryServiceNew.gI().addItemBag(player, ticket);
                    sendEffectOpenItem(player, item.template.iconID, ticket.template.iconID);

                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.gI().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }

    public void nangCapChanMenh(Player player) {
        if (player.combineNew.itemsCombine.size() != 2) {
            Service.gI().sendThongBao(player, "Thiếu nguyên liệu");
            return;
        }
        if (player.combineNew.itemsCombine.stream()
                .filter(item -> item.isNotNullItem() && (item.template.id >= 1328 && item.template.id <= 1335))
                .count() != 1) {
            Service.gI().sendThongBao(player, "Chân mệnh");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 674)
                .count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu đá ngủ sắc");
            return;
        }

        Item chanMenh = null;
        Item ngoc1sao = null;

        for (Item item : player.combineNew.itemsCombine) {
            if (item.template.id >= 1328 && item.template.id <= 1335) {
                chanMenh = item;
            } else if (item.template.id == 674) {
                ngoc1sao = item;
            }
        }

        if (chanMenh != null && ngoc1sao != null && ngoc1sao.quantity >= 99 && player.inventory.ruby > 1000) {
            player.inventory.ruby -= 1000;
            player.inventory.gem -= 0;
            Item chanMenhMoi = ItemService.gI().createNewItem((short) (chanMenh.template.id + 1));
            int capChanMenh = chanMenhMoi.template.id - 1328;
            if (Util.isTrue(1, 100)) {
                chanMenhMoi.itemOptions.add(new ItemOption(50, 5 + capChanMenh * 2));
            }
            chanMenhMoi.itemOptions.add(new ItemOption(77, 5 + capChanMenh * 2));
            chanMenhMoi.itemOptions.add(new ItemOption(103, 5 + capChanMenh * 2));
            chanMenhMoi.itemOptions.add(new ItemOption(5, 5 + capChanMenh));
            chanMenhMoi.itemOptions.add(new ItemOption(14, 5 + capChanMenh));
            InventoryServiceNew.gI().addItemBag(player, chanMenhMoi);
            Service.gI().sendThongBao(player, "|7|Bạn nhận được " + chanMenhMoi.template.name);
            InventoryServiceNew.gI().subQuantityItemsBag(player, ngoc1sao, 99);
            InventoryServiceNew.gI().subQuantityItemsBag(player, chanMenh, 1);
            sendEffectSuccessCombine(player);
            InventoryServiceNew.gI().sendItemBags(player);
            Service.gI().sendMoney(player);
            reOpenItemCombine(player);
        } else {
            Service.gI().sendThongBao(player, "Không đủ nguyên liệu nâng cấp!");
            reOpenItemCombine(player);
        }
    }

    private void nangCapBongTai(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            int gold = player.combineNew.goldCombine;
            if (player.inventory.gold < gold) {
                Service.gI().sendThongBao(player, "Không đủ vàng để thực hiện");
                return;
            }
            int gem = player.combineNew.gemCombine;
            if (player.inventory.gem < gem) {
                Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                return;
            }
            Item bongTai = null;
            Item manhVo = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.template.id == 454) {
                    bongTai = item;
                } else if (item.template.id == 933) {
                    manhVo = item;
                }
            }
            if (bongTai != null && manhVo != null && manhVo.quantity >= 99) {
                player.inventory.gold -= gold;
                player.inventory.gem -= gem;
                InventoryServiceNew.gI().subQuantityItemsBag(player, manhVo, 99);
                if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                    bongTai.template = ItemService.gI().getTemplate(921);
                    bongTai.itemOptions.add(new Item.ItemOption(72, 2));
                    sendEffectSuccessCombine(player);
                } else {
                    sendEffectFailCombine(player);
                }
                InventoryServiceNew.gI().sendItemBags(player);
                Service.gI().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    private void moChiSoBongTai(Player player) {
        if (player.combineNew.itemsCombine.size() == 3) {
            int ruby = player.combineNew.rubyCombine;
            Item thoivang = null;
            Item thoivang2 = null;
            thoivang = InventoryServiceNew.gI().findItemBag(player, 457);
            thoivang2 = InventoryServiceNew.gI().findItemBag(player, 1108);
            if (thoivang != null && thoivang.quantity < 500 || thoivang2 != null && thoivang2.quantity < 500) {
                Service.getInstance().sendThongBao(player, "Không đủ 500 Thỏi Vàng để thực hiện");
                return;
            }
            if (player.inventory.ruby < ruby) {
                Service.gI().sendThongBao(player, "Không đủ vàng để thực hiện");
                return;
            }
            int gem = player.combineNew.gemCombine;
            if (player.inventory.gem < gem) {
                Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                return;
            }
            Item btc2 = null;
            Item Manhhon = null;
            Item daXanhLam = null;

            for (Item item : player.combineNew.itemsCombine) {
                if (item.template.id == 921) {
                    btc2 = item;
                } else if (item.template.id == 934) {
                    Manhhon = item;
                } else if (item.template.id == 935) {
                    daXanhLam = item;
                }
            }
            if (btc2 != null && Manhhon != null && daXanhLam != null && Manhhon.quantity >= 99 && daXanhLam.quantity >= 10) {
                player.inventory.ruby -= ruby;
                player.inventory.gem -= gem;
                InventoryServiceNew.gI().subQuantityItemsBag(player, Manhhon, 99);
                InventoryServiceNew.gI().subQuantityItemsBag(player, daXanhLam, 10);
                InventoryServiceNew.gI().subQuantityItemsBag(player, thoivang, 500);
                InventoryServiceNew.gI().subQuantityItemsBag(player, thoivang2, 500);
                if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                    btc2.itemOptions.clear();

                    int rdUp = Util.nextInt(0, 7);
                    if (rdUp == 0) {
                        btc2.itemOptions.add(new Item.ItemOption(50, Util.nextInt(5, 15)));
                    } else if (rdUp == 1) {
                        btc2.itemOptions.add(new Item.ItemOption(77, Util.nextInt(5, 15)));
                    } else if (rdUp == 2) {
                        btc2.itemOptions.add(new Item.ItemOption(103, Util.nextInt(5, 15)));
                    } else if (rdUp == 3) {
                        btc2.itemOptions.add(new Item.ItemOption(108, Util.nextInt(5, 15)));
                    } else if (rdUp == 4) {
                        btc2.itemOptions.add(new Item.ItemOption(94, Util.nextInt(5, 15)));
                    } else if (rdUp == 5) {
                        btc2.itemOptions.add(new Item.ItemOption(14, Util.nextInt(5, 15)));
                    } else if (rdUp == 6) {
                        btc2.itemOptions.add(new Item.ItemOption(80, Util.nextInt(5, 15)));
                    } else if (rdUp == 7) {
                        btc2.itemOptions.add(new Item.ItemOption(81, Util.nextInt(5, 15)));
                    }
                    sendEffectSuccessCombine(player);
                } else {
                    sendEffectFailCombine(player);
                }
                InventoryServiceNew.gI().sendItemBags(player);
                Service.gI().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }
    private void nangCapBongTaicap3(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            int gold = player.combineNew.goldCombine;
            if (player.inventory.gold < gold) {
                Service.gI().sendThongBao(player, "Không đủ vàng để thực hiện");
                return;
            }
            int gem = player.combineNew.gemCombine;
            if (player.inventory.gem < gem) {
                Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                return;
            }
            int ruby = player.combineNew.rubyCombine;
            if (player.inventory.ruby < ruby) {
                Service.gI().sendThongBao(player, "Không đủ  hồng ngọc để thực hiện");
                return;
            }
            Item bongTai = null;
            Item manhVo = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.template.id == 921) {
                    bongTai = item;
                } else if (item.template.id == 933) {
                    manhVo = item;
                }
            }
            if (bongTai != null && manhVo != null && manhVo.quantity >= 999) {
                player.inventory.gold -= gold;
                player.inventory.gem -= gem;
                player.inventory.ruby -= ruby;
                InventoryServiceNew.gI().subQuantityItemsBag(player, manhVo, 999);
                if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                    bongTai.template = ItemService.gI().getTemplate(1155);
                    bongTai.itemOptions.add(new Item.ItemOption(72, 3));
                    sendEffectSuccessCombine(player);
                } else {
                    sendEffectFailCombine(player);
                }
                InventoryServiceNew.gI().sendItemBags(player);
                Service.gI().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    private void moChiSoBongTaicap3(Player player) {
        if (player.combineNew.itemsCombine.size() == 3) {
            int gold = player.combineNew.goldCombine;
            if (player.inventory.gold < gold) {
                Service.gI().sendThongBao(player, "Không đủ vàng để thực hiện");
                return;
            }
            int gem = player.combineNew.gemCombine;
            if (player.inventory.gem < gem) {
                Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                return;
            }
            Item bongtai = null;
            Item thachPhu = null;
            Item daxanhlam = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.template.id == 1155) {
                    bongtai = item;
                } else if (item.template.id == 934) {
                    thachPhu = item;
                } else if (item.template.id == 935) {
                    daxanhlam = item;
                }
            }
            if (bongtai != null && thachPhu != null && thachPhu.quantity >= 499) {
                player.inventory.gold -= gold;
                player.inventory.gem -= gem;
                InventoryServiceNew.gI().subQuantityItemsBag(player, thachPhu, 499);
                InventoryServiceNew.gI().subQuantityItemsBag(player, daxanhlam, 1);
                if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                    bongtai.itemOptions.clear();
                    bongtai.itemOptions.add(new Item.ItemOption(72, 3));
                    int rdUp = Util.nextInt(0, 7);
                    if (rdUp == 0) {
                        bongtai.itemOptions.add(new Item.ItemOption(50, Util.nextInt(5, 20)));
                    } else if (rdUp == 1) {
                        bongtai.itemOptions.add(new Item.ItemOption(77, Util.nextInt(5, 20)));
                    } else if (rdUp == 2) {
                        bongtai.itemOptions.add(new Item.ItemOption(103, Util.nextInt(5, 20)));
                    } else if (rdUp == 3) {
                        bongtai.itemOptions.add(new Item.ItemOption(108, Util.nextInt(5, 20)));
                    } else if (rdUp == 4) {
                        bongtai.itemOptions.add(new Item.ItemOption(94, Util.nextInt(5, 15)));
                    } else if (rdUp == 5) {
                        bongtai.itemOptions.add(new Item.ItemOption(14, Util.nextInt(5, 15)));
                    } else if (rdUp == 6) {
                        bongtai.itemOptions.add(new Item.ItemOption(80, Util.nextInt(5, 20)));
                    } else if (rdUp == 7) {
                        bongtai.itemOptions.add(new Item.ItemOption(81, Util.nextInt(5, 20)));
                    }
                    sendEffectSuccessCombine(player);
                } else {
                    sendEffectFailCombine(player);
                }
                InventoryServiceNew.gI().sendItemBags(player);
                Service.gI().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    private void nangCapBongTaicap4(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            int gold = player.combineNew.goldCombine;
            if (player.inventory.gold < gold) {
                Service.gI().sendThongBao(player, "Không đủ vàng để thực hiện");
                return;
            }
            int gem = player.combineNew.gemCombine;
            if (player.inventory.gem < gem) {
                Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                return;
            }
            int ruby = player.combineNew.rubyCombine;
            if (player.inventory.ruby < ruby) {
                Service.gI().sendThongBao(player, "Không đủ  hồng ngọc để thực hiện");
                return;
            }
            Item bongTai = null;
            Item manhVo = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.template.id == 1155) {
                    bongTai = item;
                } else if (item.template.id == 933) {
                    manhVo = item;
                }
            }
            if (bongTai != null && manhVo != null && manhVo.quantity >= 999) {
                player.inventory.gold -= gold;
                player.inventory.gem -= gem;
                player.inventory.ruby -= ruby;
                InventoryServiceNew.gI().subQuantityItemsBag(player, manhVo, 999);
                if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                    bongTai.template = ItemService.gI().getTemplate(1156);
                    bongTai.itemOptions.add(new Item.ItemOption(72, 3));
                    sendEffectSuccessCombine(player);
                } else {
                    sendEffectFailCombine(player);
                }
                InventoryServiceNew.gI().sendItemBags(player);
                Service.gI().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    private void moChiSoBongTaicap4(Player player) {
        if (player.combineNew.itemsCombine.size() == 3) {
            int gold = player.combineNew.goldCombine;
            if (player.inventory.gold < gold) {
                Service.gI().sendThongBao(player, "Không đủ vàng để thực hiện");
                return;
            }
            int gem = player.combineNew.gemCombine;
            if (player.inventory.gem < gem) {
                Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                return;
            }
            Item bongtai = null;
            Item thachPhu = null;
            Item daxanhlam = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.template.id == 1156) {
                    bongtai = item;
                } else if (item.template.id == 934) {
                    thachPhu = item;
                } else if (item.template.id == 935) {
                    daxanhlam = item;
                }
            }
            if (bongtai != null && thachPhu != null && thachPhu.quantity >= 499) {
                player.inventory.gold -= gold;
                player.inventory.gem -= gem;
                InventoryServiceNew.gI().subQuantityItemsBag(player, thachPhu, 499);
                InventoryServiceNew.gI().subQuantityItemsBag(player, daxanhlam, 1);
                if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                    bongtai.itemOptions.clear();
                    bongtai.itemOptions.add(new Item.ItemOption(72, 3));
                    int rdUp = Util.nextInt(0, 8);
                    if (rdUp == 0) {
                        bongtai.itemOptions.add(new Item.ItemOption(50, Util.nextInt(5, 25)));
                    } else if (rdUp == 1) {
                        bongtai.itemOptions.add(new Item.ItemOption(77, Util.nextInt(5, 25)));
                    } else if (rdUp == 2) {
                        bongtai.itemOptions.add(new Item.ItemOption(103, Util.nextInt(5, 25)));
                    } else if (rdUp == 3) {
                        bongtai.itemOptions.add(new Item.ItemOption(108, Util.nextInt(5, 25)));
                    } else if (rdUp == 4) {
                        bongtai.itemOptions.add(new Item.ItemOption(94, Util.nextInt(5, 15)));
                    } else if (rdUp == 5) {
                        bongtai.itemOptions.add(new Item.ItemOption(14, Util.nextInt(5, 15)));
                    } else if (rdUp == 6) {
                        bongtai.itemOptions.add(new Item.ItemOption(80, Util.nextInt(5, 25)));
                    } else if (rdUp == 7) {
                        bongtai.itemOptions.add(new Item.ItemOption(81, Util.nextInt(5, 25)));
                    } else if (rdUp == 8) {
                        bongtai.itemOptions.add(new Item.ItemOption(5, Util.nextInt(5, 25)));
                    }
                    sendEffectSuccessCombine(player);
                } else {
                    sendEffectFailCombine(player);
                }
                InventoryServiceNew.gI().sendItemBags(player);
                Service.gI().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    private void moChiSolinhthu(Player player) {
        if (player.combineNew.itemsCombine.size() == 3) {
            int gold = player.combineNew.goldCombine;
            if (player.inventory.gold < gold) {
                Service.gI().sendThongBao(player, "Không đủ vàng để thực hiện");
                return;
            }
            int gem = player.combineNew.gemCombine;
            if (player.inventory.gem < gem) {
                Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                return;
            }
            Item linhthu = null;
            Item damathuat = null;
            Item thucan = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.template.id >= 2019 && item.template.id <= 2026) {
                    linhthu = item;
                } else if (item.template.id == 2030) {
                    damathuat = item;
                } else if (item.template.id >= 663 && item.template.id <= 667) {
                    thucan = item;
                }
            }
            if (linhthu != null && damathuat != null && damathuat.quantity >= 99) {
                player.inventory.gold -= gold;
                player.inventory.gem -= gem;
                InventoryServiceNew.gI().subQuantityItemsBag(player, damathuat, 99);
                InventoryServiceNew.gI().subQuantityItemsBag(player, thucan, 1);
                if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                    linhthu.itemOptions.clear();
                    linhthu.itemOptions.add(new Item.ItemOption(72, 2));
                    int rdUp = Util.nextInt(0, 7);
                    if (rdUp == 0) {
                        linhthu.itemOptions.add(new Item.ItemOption(50, Util.nextInt(5, 25)));
                    } else if (rdUp == 1) {
                        linhthu.itemOptions.add(new Item.ItemOption(77, Util.nextInt(5, 25)));
                    } else if (rdUp == 2) {
                        linhthu.itemOptions.add(new Item.ItemOption(103, Util.nextInt(5, 25)));
                    } else if (rdUp == 3) {
                        linhthu.itemOptions.add(new Item.ItemOption(108, Util.nextInt(5, 25)));
                    } else if (rdUp == 4) {
                        linhthu.itemOptions.add(new Item.ItemOption(94, Util.nextInt(5, 15)));
                    } else if (rdUp == 5) {
                        linhthu.itemOptions.add(new Item.ItemOption(14, Util.nextInt(5, 15)));
                    } else if (rdUp == 6) {
                        linhthu.itemOptions.add(new Item.ItemOption(80, Util.nextInt(5, 25)));
                    } else if (rdUp == 7) {
                        linhthu.itemOptions.add(new Item.ItemOption(81, Util.nextInt(5, 25)));
                    }
                    sendEffectSuccessCombine(player);
                } else {
                    sendEffectFailCombine(player);
                }
                InventoryServiceNew.gI().sendItemBags(player);
                Service.gI().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    private void epSaoTrangBi(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            int gem = player.combineNew.gemCombine;
            if (player.inventory.gem < gem) {
                Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                return;
            }
            Item trangBi = null;
            Item daPhaLe = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (isTrangBiPhaLeHoa(item)) {
                    trangBi = item;
                } else if (isDaPhaLe(item)) {
                    daPhaLe = item;
                }
            }
            int star = 0; //sao pha lê đã ép
            int starEmpty = 0; //lỗ sao pha lê
            if (trangBi != null && daPhaLe != null) {
                Item.ItemOption optionStar = null;
                for (Item.ItemOption io : trangBi.itemOptions) {
                    if (io.optionTemplate.id == 102) {
                        star = io.param;
                        optionStar = io;
                    } else if (io.optionTemplate.id == 107) {
                        starEmpty = io.param;
                    }
                }
                if (star < starEmpty) {
                    player.inventory.gem -= gem;
                    int optionId = getOptionDaPhaLe(daPhaLe);
                    int param = getParamDaPhaLe(daPhaLe);
                    Item.ItemOption option = null;
                    for (Item.ItemOption io : trangBi.itemOptions) {
                        if (io.optionTemplate.id == optionId) {
                            option = io;
                            break;
                        }
                    }
                    if (option != null) {
                        option.param += param;
                    } else {
                        trangBi.itemOptions.add(new Item.ItemOption(optionId, param));
                    }
                    if (optionStar != null) {
                        optionStar.param++;
                    } else {
                        trangBi.itemOptions.add(new Item.ItemOption(102, 1));
                    }

                    InventoryServiceNew.gI().subQuantityItemsBag(player, daPhaLe, 1);
                    sendEffectSuccessCombine(player);
                }
                InventoryServiceNew.gI().sendItemBags(player);
                Service.gI().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    public void thuctinhDT(Player player, int menu) {
        if (player.combineNew.itemsCombine.size() == 3) {
            Item dathuctinh = null;
            Item ngocrong = null;
            Item ngocrongsp = null;
            Item tv = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.template.id == 16) {
                    ngocrong = item;
                } else if (item.template.id == 14) {
                    ngocrongsp = item;
                } else if (item.template.id == 1421) {
                    dathuctinh = item;
                } else if (item.template.id == 457) {
                    tv = item;
                }
            }
            if (ngocrongsp != null && ngocrongsp.quantity >= (3) && dathuctinh != null && dathuctinh.quantity >= (30) && tv != null && tv.quantity >= (3)) {
                try {
                    int count = 0;
                    while (menu > 0) {
                        menu--;
                        count++;
                        if (player.nPoint.getHpMpLimit() >= 680000 && player.nPoint.dameg >= 38000) {
                            this.lmht.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Bạn đã đạt cấp độ thức tỉnh tối đa tối đa", "Đóng");
                            break;
                        }
                        String npcSay = "|7|[ THỨC TỈNH BẢN THÂN ]"
                                + "\n|5|HP: " + player.nPoint.hpg
                                + "\nKI: " + player.nPoint.mpg
                                + "\nSD: " + player.nPoint.dameg
                                + "\n|7|Tỉ lệ thành công: "
                                + (player.nPoint.getHpMpLimit() >= 600000 && player.nPoint.getHpMpLimit() < 620000
                                && player.nPoint.dameg >= 32000 && player.nPoint.dameg < 34000 ? "50%"
                                        : player.nPoint.getHpMpLimit() >= 620000 && player.nPoint.getHpMpLimit() < 640000
                                        && player.nPoint.dameg >= 34000 && player.nPoint.dameg < 36000 ? "30%" : "10%")
                                + "\nCount Đã Nâng: " + count + "\n|-1|Đá thức tỉnh: " + dathuctinh.quantity
                                + "\nThỏi vàng: " + tv.quantity + "\nNgọc rồng: " + ngocrongsp.quantity + "\n";
                        this.lmht.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Nâng X1\nCần 100\nHồng Ngọc", "Nâng X10\nCần 1.000\nHồng Ngọc", "Nâng X100\nCần 10.000\nHồng Ngọc");
                        if (Util.isTrue((player.nPoint.hpg >= 600000 && player.nPoint.hpg < 620000
                                && player.nPoint.mpg >= 600000 && player.nPoint.mpg < 620000
                                && player.nPoint.dameg >= 32000 && player.nPoint.dameg < 34000 ? 50
                                        : player.nPoint.hpg >= 620000 && player.nPoint.hpg < 640000
                                        && player.nPoint.mpg >= 620000 && player.nPoint.mpg < 640000
                                        && player.nPoint.dameg >= 34000 && player.nPoint.dameg < 36000 ? 30 : 5), 100)) {
                            int[][] randomcs = {{Util.nextInt(100, 500)}, {Util.nextInt(500, 1000)}};
                            InventoryServiceNew.gI().subQuantityItemsBag(player, ngocrongsp, (3));
                            InventoryServiceNew.gI().subQuantityItemsBag(player, dathuctinh, (30));
                            InventoryServiceNew.gI().subQuantityItemsBag(player, tv, (3));
                            player.inventory.ruby -= (100);
                            sendEffectSuccessCombine(player);
                            player.nPoint.hpg += Util.nextInt(randomcs[0][0], randomcs[1][0]);
                            player.nPoint.mpg += Util.nextInt(randomcs[0][0], randomcs[1][0]);
                            player.nPoint.dameg += Util.nextInt(randomcs[0][0], randomcs[1][0]);
                            if (player.nPoint.hpg > 680000) {
                                player.nPoint.hpg = 680000;
                            }
                            if (player.nPoint.mpg > 680000) {
                                player.nPoint.mpg = 680000;
                            }
                            if (player.nPoint.dameg > 38000) {
                                player.nPoint.dameg = 38000;
                            }
                            npcSay += "|7|Đột phá thành công, chỉ số đã tăng thêm 1 bậc!\nTiếp tục sau 3s";
                            this.lmht.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                    "Nâng X1\nCần 100\nHồng Ngọc", "Nâng X10\nCần 1.000\nHồng Ngọc", "Nâng X100\nCần 10.000\nHồng Ngọc");
                            Thread.sleep(2500);
                        } else {
                            InventoryServiceNew.gI().subQuantityItemsBag(player, dathuctinh, (30));
                            sendEffectFailCombine(player);
                        }
                        InventoryServiceNew.gI().sendItemBags(player);
                        Service.gI().sendMoney(player);
                        reOpenItemCombine(player);
                    }
                } catch (Exception e) {
                }
            } else if (ngocrong != null && ngocrong.quantity >= (3) && dathuctinh != null && dathuctinh.quantity >= (15) && tv != null && tv.quantity >= (1)) {
                try {
                    int count = 0;
                    while (menu > 0) {
                        menu--;
                        count++;
                        String tsSay = "|7|[ THỨC TỈNH ĐỆ TỬ ]\n" + player.pet.name.substring(1)
                                + "\n|5|Cấp bậc : " + player.pet.getNameThuctinh(player.pet.thuctinh)
                                + "\nHP: " + (player.pet.nPoint.hpg >= 680000 ? Util.format(player.pet.nPoint.hpg)
                                        : Util.format(player.pet.nPoint.hpg) + " Cấp Sau: " + Util.format(player.pet.nPoint.hpg + 200))
                                + "\nKI: " + (player.pet.nPoint.mpg >= 680000 ? Util.format(player.pet.nPoint.mpg)
                                        : Util.format(player.pet.nPoint.mpg) + " Cấp Sau: " + Util.format(player.pet.nPoint.mpg + 200))
                                + "\nSD: " + (player.pet.nPoint.dameg >= 38000 ? Util.format(player.pet.nPoint.dameg)
                                        : Util.format(player.pet.nPoint.dameg) + " Cấp Sau: " + Util.format(player.pet.nPoint.dameg + 100))
                                + "\n|7|Tỉ lệ thành công: "
                                + (player.pet.nPoint.hpg >= 600000 && player.pet.nPoint.hpg < 620000
                                && player.pet.nPoint.mpg >= 600000 && player.pet.nPoint.mpg < 620000
                                && player.pet.nPoint.dameg >= 32000 && player.pet.nPoint.dameg < 34000 ? "50%"
                                        : player.pet.nPoint.hpg >= 620000 && player.pet.nPoint.hpg < 640000
                                        && player.pet.nPoint.mpg >= 620000 && player.pet.nPoint.mpg < 640000
                                        && player.pet.nPoint.dameg >= 34000 && player.pet.nPoint.dameg < 36000 ? "30%" : "10%")
                                + "\nCount Đã Nâng: " + count + "\n|-1|Đá thức tỉnh: " + dathuctinh.quantity
                                + "\nThỏi vàng: " + tv.quantity + "\nNgọc rồng: " + ngocrong.quantity + "\n";
                        this.lmht.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, tsSay,
                                "Nâng X1\nCần 100\nHồng Ngọc", "Nâng X10\nCần 1.000\nHồng Ngọc", "Nâng X100\nCần 10.000\nHồng Ngọc");
                        if (Util.isTrue((player.pet.nPoint.hpg >= 600000 && player.pet.nPoint.hpg < 620000
                                && player.pet.nPoint.mpg >= 600000 && player.pet.nPoint.mpg < 620000
                                && player.pet.nPoint.dameg >= 32000 && player.pet.nPoint.dameg < 34000 ? 50
                                        : player.pet.nPoint.hpg >= 620000 && player.pet.nPoint.hpg < 640000
                                        && player.pet.nPoint.mpg >= 620000 && player.pet.nPoint.mpg < 640000
                                        && player.pet.nPoint.dameg >= 34000 && player.pet.nPoint.dameg < 36000 ? 30 : 10), 100)) {
                            InventoryServiceNew.gI().subQuantityItemsBag(player, ngocrong, 3);
                            InventoryServiceNew.gI().subQuantityItemsBag(player, dathuctinh, 15);
                            InventoryServiceNew.gI().subQuantityItemsBag(player, tv, 1);
                            player.inventory.ruby -= 100;
                            if (Util.isTrue(50, 100)) {
                                player.pet.nPoint.hpg += 200;
                                player.pet.nPoint.mpg += 200;
                                player.pet.nPoint.dameg += 100;
                            } else {
                                player.pet.nPoint.tiemNangUp(Util.nextInt(10000, 100000));
                            }
                            if (player.pet != null && player.pet.thuctinh < 30) {
                                if (player.pet.thuctinh >= 0 && player.pet.thuctinh < 10 && Util.isTrue(30, 100)) {
                                    player.pet.thuctinh++;
                                    tsSay += "\n|7|Đột phá thành công, cấp thức tỉnh tăng 1 bậc\nTiếp tục sau 3s\n";
                                    this.lmht.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, tsSay,
                                            "Nâng X1\nCần 100\nHồng Ngọc", "Nâng X10\nCần 1.000\nHồng Ngọc", "Nâng X100\nCần 10.000\nHồng Ngọc");
                                    Thread.sleep(2500);
                                } else if (player.pet.thuctinh >= 10 && player.pet.thuctinh < 20 && Util.isTrue(20, 100)) {
                                    player.pet.thuctinh++;
                                    tsSay += "\n|7|Đột phá thành công, cấp thức tỉnh tăng 1 bậc\nTiếp tục sau 3s\n";
                                    this.lmht.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, tsSay,
                                            "Nâng X1\nCần 100\nHồng Ngọc", "Nâng X10\nCần 1.000\nHồng Ngọc", "Nâng X100\nCần 10.000\nHồng Ngọc");
                                    Thread.sleep(2500);
                                } else if (player.pet.thuctinh >= 20 && player.pet.thuctinh < 30 && Util.isTrue(10, 100)) {
                                    player.pet.thuctinh++;
                                    tsSay += "\n|7|Đột phá thành công, cấp thức tỉnh tăng 1 bậc\nTiếp tục sau 3s\n";
                                    this.lmht.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, tsSay,
                                            "Nâng X1\nCần 100\nHồng Ngọc", "Nâng X10\nCần 1.000\nHồng Ngọc", "Nâng X100\nCần 10.000\nHồng Ngọc");
                                    Thread.sleep(2500);
                                }
                            }
                            if (player.pet.typePet == 0 && player.pet.thuctinh == 10) {
                                player.pet.typePet++;
                                player.pet.name = "$" + "Mabu";
                                ChangeMapService.gI().exitMap(player.pet);
                                tsSay += "\n|7|May mắn!\nĐệ tử đã tiến hoá thành Mabu!\n";
                                this.lmht.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, tsSay,
                                        "Nâng X1\nCần 100\nHồng Ngọc", "Nâng X10\nCần 1.000\nHồng Ngọc", "Nâng X100\nCần 10.000\nHồng Ngọc");
                                Thread.sleep(2500);
                            } else if (player.pet.typePet == 1 && player.pet.thuctinh == 20) {
                                player.pet.typePet++;
                                player.pet.name = "$" + "Gohan Buu";
                                ChangeMapService.gI().exitMap(player.pet);
                                tsSay += "\n|7|May mắn!\nĐệ tử đã tiến hoá thành Buu Han!\n";
                                this.lmht.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, tsSay,
                                        "Nâng X1\nCần 100\nHồng Ngọc", "Nâng X10\nCần 1.000\nHồng Ngọc", "Nâng X100\nCần 10.000\nHồng Ngọc");
                                Thread.sleep(2500);
                            } else if (player.pet.typePet == 2 && player.pet.thuctinh == 30) {
                                player.pet.typePet++;
                                player.pet.name = "$" + "Evil Buu";
                                ChangeMapService.gI().exitMap(player.pet);
                                tsSay += "\n|7|May mắn!\nĐệ tử đã tiến hoá thành Evio Buu!\n";
                                this.lmht.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, tsSay,
                                        "Nâng X1\nCần 100\nHồng Ngọc", "Nâng X10\nCần 1.000\nHồng Ngọc", "Nâng X100\nCần 10.000\nHồng Ngọc");
                                Thread.sleep(2500);
                            }
                            if (player.pet.nPoint.hpg > 680000) {
                                player.pet.nPoint.hpg = 680000;
                            }
                            if (player.pet.nPoint.mpg > 680000) {
                                player.pet.nPoint.mpg = 680000;
                            }
                            if (player.pet.nPoint.dameg > 36000) {
                                player.pet.nPoint.dameg = 36000;
                            }
                        } else {
                            InventoryServiceNew.gI().subQuantityItemsBag(player, dathuctinh, 15);
                            sendEffectFailCombine(player);
                        }
                        InventoryServiceNew.gI().sendItemBags(player);
                        Service.gI().sendMoney(player);
                        reOpenItemCombine(player);
                    }
                } catch (Exception e) {
                }
            } else {
                Service.gI().sendThongBao(player, "Không đủ nguyên liệu để thực hiện");
            }
        } else {
            Service.gI().sendThongBao(player, "Không đủ nguyên liệu để thực hiện");
        }
    }

    private void phaLeHoaTrangBi(Player player) {
        boolean flag = false;
        int solandap = player.combineNew.quantities;
        while (player.combineNew.quantities > 0 && !player.combineNew.itemsCombine.isEmpty() && !flag) {
            int gold = player.combineNew.goldCombine;
            int gem = player.combineNew.gemCombine;
            if (player.inventory.gold < gold) {
                Service.gI().sendThongBao(player, "Không đủ vàng để thực hiện");
                break;
            } else if (player.inventory.gem < gem) {
                Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                break;
            }
            Item item = player.combineNew.itemsCombine.get(0);
            if (isTrangBiPhaLeHoa(item)) {
                int star = 0;
                Item.ItemOption optionStar = null;
                for (Item.ItemOption io : item.itemOptions) {
                    if (io.optionTemplate.id == 107) {
                        star = io.param;
                        optionStar = io;
                        break;
                    }
                }
                if (star < MAX_STAR_ITEM) {
                    player.inventory.gold -= gold;
                    player.inventory.gem -= gem;
                    //float ratio = getRatioPhaLeHoa(star);
                    int epint = (int) getRatioPhaLeHoa(star);
                    flag = Util.isTrue(epint, 100);
                    if (flag) {
                        if (optionStar == null) {
                            item.itemOptions.add(new Item.ItemOption(107, 1));
                        } else {
                            optionStar.param++;
                        }
                        sendEffectSuccessCombine(player);
                        Service.getInstance().sendThongBao(player, "Lên cấp sau " + (solandap - player.combineNew.quantities + 1) + " lần đập");
                        if (optionStar != null && optionStar.param >= 7) {
                            ServerNotify.gI().notify("Chúc mừng " + player.name + " vừa pha lê hóa "
                                    + "thành công " + item.template.name + " lên " + optionStar.param + " sao pha lê");
                        }
                    } else {
                        sendEffectFailCombine(player);
                    }
                }
            }
            player.combineNew.quantities -= 1;
        }
        if (!flag) {
            sendEffectFailCombine(player);
        }
        InventoryServiceNew.gI().sendItemBags(player);
        Service.gI().sendMoney(player);
        reOpenItemCombine(player);
    }

    private void nhapNgocRong(Player player) {
        if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
            if (!player.combineNew.itemsCombine.isEmpty()) {
                Item item = player.combineNew.itemsCombine.get(0);
                if (item != null && item.isNotNullItem() && (item.template.id > 14 && item.template.id <= 20) && item.quantity >= 7) {
                    Item nr = ItemService.gI().createNewItem((short) (item.template.id - 1));
                    InventoryServiceNew.gI().addItemBag(player, nr);
                    InventoryServiceNew.gI().subQuantityItemsBag(player, item, 7);
                    InventoryServiceNew.gI().sendItemBags(player);
                    reOpenItemCombine(player);
//                    sendEffectCombineDB(player, item.template.iconID);
                }
            }
        }
    }
     private void HP(Player player) {
        if (player.combineNew.itemsCombine.size() != 2) {
            Service.getInstance().sendThongBao(player, "Thiếu nguyên liệu");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isTrangBiHP()).count() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu trang bị HP");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 1346).count() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu Phiếu Nâng Cấp HP");
            return;
        }
        if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
            Item thegh = player.combineNew.itemsCombine.stream().filter(item -> item.template.id == 1346).findFirst().get();
            Item tbiHSD = player.combineNew.itemsCombine.stream().filter(Item::isTrangBiHP).findFirst().get();
            if (thegh == null) {
                Service.getInstance().sendThongBao(player, "Thiếu Phiếu Nâng Cấp HP");
                return;
            }
            if (tbiHSD == null) {
                Service.getInstance().sendThongBao(player, "Thiếu trang bị HP");
                return;
            }
            if (tbiHSD != null) {
                for (ItemOption itopt : tbiHSD.itemOptions) {
                    if (itopt.optionTemplate.id == 77) {
                        if (itopt.param < 0 || itopt == null) {
                            Service.getInstance().sendThongBao(player, "Không Phải Trang Bị Có HP");
                            return;
                        }
                    }
                }
            }
            if (Util.isTrue(40, 100)) {
                sendEffectSuccessCombine(player);
                for (ItemOption itopt : tbiHSD.itemOptions) {
                    if (itopt.optionTemplate.id == 77) {
                        itopt.param += 1;
                        break;
                    }
                }
            } else {
                sendEffectFailCombine(player);
            }
            InventoryServiceNew.gI().subQuantityItemsBag(player, thegh, 1);
            InventoryServiceNew.gI().sendItemBags(player);
            Service.getInstance().sendMoney(player);
            reOpenItemCombine(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống hành trang");
        }
    }
 private void GiaHanTrangBi(Player player) {
        if (player.combineNew.itemsCombine.size() != 2) {
            Service.getInstance().sendThongBao(player, "Thiếu nguyên liệu");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isTrangBiHSD()).count() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu trang bị HSD");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 1346).count() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu Bùa Gia Hạn");
            return;
        }
        if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
            Item thegh = player.combineNew.itemsCombine.stream().filter(item -> item.template.id == 1346).findFirst().get();
            Item tbiHSD = player.combineNew.itemsCombine.stream().filter(Item::isTrangBiHSD).findFirst().get();
            if (thegh == null) {
                Service.getInstance().sendThongBao(player, "Thiếu Bùa Gia Hạn");
                return;
            }
            if (tbiHSD == null) {
                Service.getInstance().sendThongBao(player, "Thiếu trang bị HSD");
                return;
            }
            if (tbiHSD != null) {
                for (ItemOption itopt : tbiHSD.itemOptions) {
                    if (itopt.optionTemplate.id == 93) {
                        if (itopt.param < 0 || itopt == null) {
                            Service.getInstance().sendThongBao(player, "Không Phải Trang Bị Có HSD");
                            return;
                        }
                    }
                }
            }
            if (Util.isTrue(100, 100)) {
                sendEffectSuccessCombine(player);
                for (ItemOption itopt : tbiHSD.itemOptions) {
                    if (itopt.optionTemplate.id == 93) {
                        itopt.param += 1;
                        break;
                    }
                }
            } else {
                sendEffectFailCombine(player);
            }
            InventoryServiceNew.gI().subQuantityItemsBag(player, thegh, 1);
            InventoryServiceNew.gI().sendItemBags(player);
            Service.getInstance().sendMoney(player);
            reOpenItemCombine(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống hành trang");
        }
    }
 private void SD(Player player) {
        if (player.combineNew.itemsCombine.size() != 2) {
            Service.getInstance().sendThongBao(player, "Thiếu nguyên liệu");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isTrangBiSD()).count() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu trang bị SD");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 1346).count() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu Phiếu Nâng Cấp SD");
            return;
        }
        if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
            Item thegh = player.combineNew.itemsCombine.stream().filter(item -> item.template.id == 1346).findFirst().get();
            Item tbiHSD = player.combineNew.itemsCombine.stream().filter(Item::isTrangBiHP).findFirst().get();
            if (thegh == null) {
                Service.getInstance().sendThongBao(player, "Thiếu Phiếu Nâng Cấp SD");
                return;
            }
            if (tbiHSD == null) {
                Service.getInstance().sendThongBao(player, "Thiếu trang bị SD");
                return;
            }
            if (tbiHSD != null) {
                for (ItemOption itopt : tbiHSD.itemOptions) {
                    if (itopt.optionTemplate.id == 50) {
                        if (itopt.param < 0 || itopt == null) {
                            Service.getInstance().sendThongBao(player, "Không Phải Trang Bị Có SD");
                            return;
                        }
                    }
                }
            }
            if (Util.isTrue(25, 100)) {
                sendEffectSuccessCombine(player);
                for (ItemOption itopt : tbiHSD.itemOptions) {
                    if (itopt.optionTemplate.id == 50) {
                        itopt.param += 1;
                        break;
                    }
                }
            } else {
                sendEffectFailCombine(player);
            }
            InventoryServiceNew.gI().subQuantityItemsBag(player, thegh, 1);
            InventoryServiceNew.gI().sendItemBags(player);
            Service.getInstance().sendMoney(player);
            reOpenItemCombine(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống hành trang");
        }
    }
  private void KI(Player player) {
        if (player.combineNew.itemsCombine.size() != 2) {
            Service.getInstance().sendThongBao(player, "Thiếu nguyên liệu");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isTrangBiKI()).count() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu trang bị KI");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 1346).count() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu Phiếu Nâng Cấp KI");
            return;
        }
        if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
            Item thegh = player.combineNew.itemsCombine.stream().filter(item -> item.template.id == 1346).findFirst().get();
            Item tbiHSD = player.combineNew.itemsCombine.stream().filter(Item::isTrangBiHP).findFirst().get();
            if (thegh == null) {
                Service.getInstance().sendThongBao(player, "Thiếu Phiếu Nâng Cấp KI");
                return;
            }
            if (tbiHSD == null) {
                Service.getInstance().sendThongBao(player, "Thiếu trang bị KI");
                return;
            }
            if (tbiHSD != null) {
                for (ItemOption itopt : tbiHSD.itemOptions) {
                    if (itopt.optionTemplate.id == 103) {
                        if (itopt.param < 0 || itopt == null) {
                            Service.getInstance().sendThongBao(player, "Không Phải Trang Bị Có KI");
                            return;
                        }
                    }
                }
            }
            if (Util.isTrue(50, 100)) {
                sendEffectSuccessCombine(player);
                for (ItemOption itopt : tbiHSD.itemOptions) {
                    if (itopt.optionTemplate.id == 103) {
                        itopt.param += 1;
                        break;
                    }
                }
            } else {
                sendEffectFailCombine(player);
            }
            InventoryServiceNew.gI().subQuantityItemsBag(player, thegh, 1);
            InventoryServiceNew.gI().sendItemBags(player);
            Service.getInstance().sendMoney(player);
            reOpenItemCombine(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống hành trang");
        }
    }
   private void GIAP(Player player) {
        if (player.combineNew.itemsCombine.size() != 2) {
            Service.getInstance().sendThongBao(player, "Thiếu nguyên liệu");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isTrangBiGIAP()).count() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu trang bị SĐCM");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 1346).count() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu Phiếu Nâng Cấp SĐCM");
            return;
        }
        if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
            Item thegh = player.combineNew.itemsCombine.stream().filter(item -> item.template.id == 1346).findFirst().get();
            Item tbiHSD = player.combineNew.itemsCombine.stream().filter(Item::isTrangBiHP).findFirst().get();
            if (thegh == null) {
                Service.getInstance().sendThongBao(player, "Thiếu Phiếu Nâng Cấp SĐCM");
                return;
            }
            if (tbiHSD == null) {
                Service.getInstance().sendThongBao(player, "Thiếu trang bị SĐCM");
                return;
            }
            if (tbiHSD != null) {
                for (ItemOption itopt : tbiHSD.itemOptions) {
                    if (itopt.optionTemplate.id == 5) {
                        if (itopt.param < 0 || itopt == null) {
                            Service.getInstance().sendThongBao(player, "Không Phải Trang Bị Có SĐCM");
                            return;
                        }
                    }
                }
            }
            if (Util.isTrue(20, 100)) {
                sendEffectSuccessCombine(player);
                for (ItemOption itopt : tbiHSD.itemOptions) {
                    if (itopt.optionTemplate.id == 5) {
                        itopt.param += 1;
                        break;
                    }
                }
            } else {
                sendEffectFailCombine(player);
            }
            InventoryServiceNew.gI().subQuantityItemsBag(player, thegh, 1);
            InventoryServiceNew.gI().sendItemBags(player);
            Service.getInstance().sendMoney(player);
            reOpenItemCombine(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống hành trang");
        }
    }
    private void nangCapVatPham(Player player) {
        if (player.combineNew.itemsCombine.size() >= 2 && player.combineNew.itemsCombine.size() < 4) {
            if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type < 5).count() != 1) {
                return;
            }
            if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type == 14).count() != 1) {
                return;
            }
            if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 987).count() != 1) {
                return;//admin
            }
            Item itemDo = null;
            Item itemDNC = null;
            Item itemDBV = null;
            for (int j = 0; j < player.combineNew.itemsCombine.size(); j++) {
                if (player.combineNew.itemsCombine.get(j).isNotNullItem()) {
                    if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.get(j).template.id == 987) {
                        itemDBV = player.combineNew.itemsCombine.get(j);
                        continue;
                    }
                    if (player.combineNew.itemsCombine.get(j).template.type < 5) {
                        itemDo = player.combineNew.itemsCombine.get(j);
                    } else {
                        itemDNC = player.combineNew.itemsCombine.get(j);
                    }
                }
            }
            if (isCoupleItemNangCapCheck(itemDo, itemDNC)) {
                int countDaNangCap = player.combineNew.countDaNangCap;
                int gold = player.combineNew.goldCombine;
                short countDaBaoVe = player.combineNew.countDaBaoVe;
                if (player.inventory.gold < gold) {
                    Service.gI().sendThongBao(player, "Không đủ vàng để thực hiện");
                    return;
                }

                if (itemDNC.quantity < countDaNangCap) {
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 3) {
                    if (Objects.isNull(itemDBV)) {
                        return;
                    }
                    if (itemDBV.quantity < countDaBaoVe) {
                        return;
                    }
                }

                int level = 0;
                Item.ItemOption optionLevel = null;
                for (Item.ItemOption io : itemDo.itemOptions) {
                    if (io.optionTemplate.id == 72) {
                        level = io.param;
                        optionLevel = io;
                        break;
                    }
                }
                if (level < MAX_LEVEL_ITEM) {
                    player.inventory.gold -= gold;
                    Item.ItemOption option = null;
                    Item.ItemOption option2 = null;
                    for (Item.ItemOption io : itemDo.itemOptions) {
                        if (io.optionTemplate.id == 47
                                || io.optionTemplate.id == 6
                                || io.optionTemplate.id == 0
                                || io.optionTemplate.id == 7
                                || io.optionTemplate.id == 14
                                || io.optionTemplate.id == 22
                                || io.optionTemplate.id == 23) {
                            option = io;
                        } else if (io.optionTemplate.id == 27
                                || io.optionTemplate.id == 28) {
                            option2 = io;
                        }
                    }
                    if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                        option.param += (option.param * 10 / 100);
                        if (option2 != null) {
                            option2.param += (option2.param * 10 / 100);
                        }
                        if (optionLevel == null) {
                            itemDo.itemOptions.add(new Item.ItemOption(72, 1));
                        } else {
                            optionLevel.param++;
                        }
//                        if (optionLevel != null && optionLevel.param >= 5) {
//                            ServerNotify.gI().notify("Chúc mừng " + player.name + " vừa nâng cấp "
//                                    + "thành công " + trangBi.template.name + " lên +" + optionLevel.param);
//                        }
                        sendEffectSuccessCombine(player);
                    } else {
                        if ((level == 2 || level == 4 || level == 6) && (player.combineNew.itemsCombine.size() != 3)) {
                            option.param -= (option.param * 10 / 100);
                            if (option2 != null) {
                                option2.param -= (option2.param * 10 / 100);
                            }
                            optionLevel.param--;
                        }
                        sendEffectFailCombine(player);
                    }
                    if (player.combineNew.itemsCombine.size() == 3) {
                        InventoryServiceNew.gI().subQuantityItemsBag(player, itemDBV, countDaBaoVe);
                    }
                    InventoryServiceNew.gI().subQuantityItemsBag(player, itemDNC, player.combineNew.countDaNangCap);
                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.gI().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }

    private void epantrangbi(Player player) {
        if (player.combineNew.itemsCombine.size() == 3) {
            int gold = 5000;
            if (player.inventory.ruby < gold) {
                Service.gI().sendThongBao(player, "Không đủ vàng để thực hiện");
                return;
            }

            Item cailoz = null;
            Item concac = null;
            Item thoivang = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.template.type < 5) {
                    cailoz = item;
                } else if (item.template.id == 674) {
                    concac = item;
                } else if (item.template.id == 457) {
                    thoivang = item;
                }
            }
            if (cailoz != null && concac != null && concac.quantity >= 25) {
                player.inventory.ruby -= 50000;
                player.inventory.gem -= 0;
                InventoryServiceNew.gI().subQuantityItemsBag(player, concac, 25);
                InventoryServiceNew.gI().subQuantityItemsBag(player, thoivang, 1);
                if (Util.isTrue(100, 100)) {
                    cailoz.itemOptions.clear();
                    cailoz.itemOptions.add(new Item.ItemOption(72, 10));
                    cailoz.itemOptions.add(new Item.ItemOption(107, Util.nextInt(3, 6)));
                    cailoz.itemOptions.add(new Item.ItemOption(0, Util.nextInt(5000, 8000)));
                    cailoz.itemOptions.add(new Item.ItemOption(48, Util.nextInt(30000, 60000)));
                    cailoz.itemOptions.add(new Item.ItemOption(21, Util.nextInt(40, 60)));
                    cailoz.itemOptions.add(new Item.ItemOption(30, 0));

                    int rdUp = Util.nextInt(0, 2);
                    if (rdUp == 0) {
                        cailoz.itemOptions.add(new Item.ItemOption(34, 1));
                    } else if (rdUp == 1) {
                        cailoz.itemOptions.add(new Item.ItemOption(35, 1));
                    } else if (rdUp == 2) {
                        cailoz.itemOptions.add(new Item.ItemOption(36, 1));
                    }
                    sendEffectSuccessCombine(player);
                } else {
                    sendEffectFailCombine(player);
                }
                InventoryServiceNew.gI().sendItemBags(player);
                Service.gI().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    private void nguyetan(Player player) {
        if (player.combineNew.itemsCombine.size() >= 2 && player.combineNew.itemsCombine.size() < 4) {
            if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type < 5).count() != 1) {
                return;
            }
            if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type == 14).count() != 1) {
                return;
            }
            if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 987).count() != 1) {
                return;//admin
            }
            Item itemDo = null;
            Item itemDNC = null;
            Item itemDBV = null;
            for (int j = 0; j < player.combineNew.itemsCombine.size(); j++) {
                if (player.combineNew.itemsCombine.get(j).isNotNullItem()) {
                    if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.get(j).template.id == 987) {
                        itemDBV = player.combineNew.itemsCombine.get(j);
                        continue;
                    }
                    if (player.combineNew.itemsCombine.get(j).template.type < 5) {
                        itemDo = player.combineNew.itemsCombine.get(j);
                    } else {
                        itemDNC = player.combineNew.itemsCombine.get(j);
                    }
                }
            }
            if (isCoupleItemNangCapCheck(itemDo, itemDNC)) {
                int countDaNangCap = player.combineNew.countDaNangCap;
                int gold = 25000;
                short countDaBaoVe = player.combineNew.countDaBaoVe;
                if (player.inventory.ruby < gold) {
                    Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                    return;
                }

                if (itemDNC.quantity < countDaNangCap) {
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 3) {
                    if (Objects.isNull(itemDBV)) {
                        return;
                    }
                    if (itemDBV.quantity < countDaBaoVe) {
                        return;
                    }
                }

                int level = 0;
                Item.ItemOption optionLevel = null;
                for (Item.ItemOption io : itemDo.itemOptions) {
                    if (io.optionTemplate.id == 35) {
//                        player.inventory.ruby -= gold;
                        level = io.param;
                        optionLevel = io;
                        break;
                    }

                }
                if (level <= 2) {

                    Item.ItemOption option = null;
                    Item.ItemOption option2 = null;
                    for (Item.ItemOption io : itemDo.itemOptions) {
                        if (io.optionTemplate.id == 47
                                || io.optionTemplate.id == 6
                                || io.optionTemplate.id == 0
                                || io.optionTemplate.id == 7
                                || io.optionTemplate.id == 14
                                || io.optionTemplate.id == 22
                                || io.optionTemplate.id == 23) {
                            option = io;
                        } else if (io.optionTemplate.id == 27
                                || io.optionTemplate.id == 28) {
                            option2 = io;
                        }
                    }
                    if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                        option.param += (option.param * 10 / 100);
                        if (option2 != null) {
                            option2.param += (option2.param * 10 / 100);
                        }
                        if (optionLevel == null) {
                            itemDo.itemOptions.add(new Item.ItemOption(Util.nextInt(35, 35), 1));
                        } else {
                            optionLevel.param++;
                        }
//                        if (optionLevel != null && optionLevel.param >= 5) {
//                            ServerNotify.gI().notify("Chúc mừng " + player.name + " vừa nâng cấp "
//                                    + "thành công " + trangBi.template.name + " lên +" + optionLevel.param);
//                        }
                        sendEffectSuccessCombine(player);
                    } else {
                        if ((level == 2 || level == 4 || level == 6) && (player.combineNew.itemsCombine.size() != 3)) {
                            option.param -= (option.param * 10 / 100);
                            if (option2 != null) {
                                option2.param -= (option2.param * 10 / 100);
                            }
                            optionLevel.param--;
                        }
                        sendEffectFailCombine(player);
                    }
                    if (player.combineNew.itemsCombine.size() == 3) {
                        InventoryServiceNew.gI().subQuantityItemsBag(player, itemDBV, countDaBaoVe);
                    }
                    player.inventory.ruby -= gold;
                    InventoryServiceNew.gI().subQuantityItemsBag(player, itemDNC, player.combineNew.countDaNangCap);
                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.gI().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }

    private void nhatan(Player player) {
        if (player.combineNew.itemsCombine.size() >= 2 && player.combineNew.itemsCombine.size() < 4) {
            if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type < 5).count() != 1) {
                return;
            }
            if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type == 14).count() != 1) {
                return;
            }
            if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 987).count() != 1) {
                return;//admin
            }
            Item itemDo = null;
            Item itemDNC = null;
            Item itemDBV = null;
            for (int j = 0; j < player.combineNew.itemsCombine.size(); j++) {
                if (player.combineNew.itemsCombine.get(j).isNotNullItem()) {
                    if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.get(j).template.id == 987) {
                        itemDBV = player.combineNew.itemsCombine.get(j);
                        continue;
                    }
                    if (player.combineNew.itemsCombine.get(j).template.type < 5) {
                        itemDo = player.combineNew.itemsCombine.get(j);
                    } else {
                        itemDNC = player.combineNew.itemsCombine.get(j);
                    }
                }
            }
            if (isCoupleItemNangCapCheck(itemDo, itemDNC)) {
                int countDaNangCap = player.combineNew.countDaNangCap;
                int gold = 25000;
                short countDaBaoVe = player.combineNew.countDaBaoVe;
                if (player.inventory.ruby < gold) {
                    Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                    return;
                }

                if (itemDNC.quantity < countDaNangCap) {
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 3) {
                    if (Objects.isNull(itemDBV)) {
                        return;
                    }
                    if (itemDBV.quantity < countDaBaoVe) {
                        return;
                    }
                }

                int level = 0;
                Item.ItemOption optionLevel = null;
                for (Item.ItemOption io : itemDo.itemOptions) {
                    if (io.optionTemplate.id == 36) {
//                        player.inventory.ruby -= gold;
                        level = io.param;
                        optionLevel = io;
                        break;
                    }

                }
                if (level <= 2) {

                    Item.ItemOption option = null;
                    Item.ItemOption option2 = null;
                    for (Item.ItemOption io : itemDo.itemOptions) {
                        if (io.optionTemplate.id == 47
                                || io.optionTemplate.id == 6
                                || io.optionTemplate.id == 0
                                || io.optionTemplate.id == 7
                                || io.optionTemplate.id == 14
                                || io.optionTemplate.id == 22
                                || io.optionTemplate.id == 23) {
                            option = io;
                        } else if (io.optionTemplate.id == 27
                                || io.optionTemplate.id == 28) {
                            option2 = io;
                        }
                    }
                    if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                        option.param += (option.param * 10 / 100);
                        if (option2 != null) {
                            option2.param += (option2.param * 10 / 100);
                        }
                        if (optionLevel == null) {
                            itemDo.itemOptions.add(new Item.ItemOption(Util.nextInt(36, 36), 1));
                        } else {
                            optionLevel.param++;
                        }
//                        if (optionLevel != null && optionLevel.param >= 5) {
//                            ServerNotify.gI().notify("Chúc mừng " + player.name + " vừa nâng cấp "
//                                    + "thành công " + trangBi.template.name + " lên +" + optionLevel.param);
//                        }
                        sendEffectSuccessCombine(player);
                    } else {
                        if ((level == 2 || level == 4 || level == 6) && (player.combineNew.itemsCombine.size() != 3)) {
                            option.param -= (option.param * 10 / 100);
                            if (option2 != null) {
                                option2.param -= (option2.param * 10 / 100);
                            }
                            optionLevel.param--;
                        }
                        sendEffectFailCombine(player);
                    }
                    if (player.combineNew.itemsCombine.size() == 3) {
                        InventoryServiceNew.gI().subQuantityItemsBag(player, itemDBV, countDaBaoVe);
                    }
                    player.inventory.ruby -= gold;
                    InventoryServiceNew.gI().subQuantityItemsBag(player, itemDNC, player.combineNew.countDaNangCap);
                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.gI().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }

    //--------------------------------------------------------------------------
    /**
     * r
     * Hiệu ứng mở item
     *
     * @param player
     */
    public void sendEffectOpenItem(Player player, short icon1, short icon2) {
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(OPEN_ITEM);
            msg.writer().writeShort(icon1);
            msg.writer().writeShort(icon2);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    /**
     * Hiệu ứng đập đồ thành công
     *
     * @param player
     */
    private void sendEffectSuccessCombine(Player player) {
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(COMBINE_SUCCESS);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    /**
     * Hiệu ứng đập đồ thất bại
     *
     * @param player
     */
    private void sendEffectFailCombine(Player player) {
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(COMBINE_FAIL);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    /**
     * Gửi lại danh sách đồ trong tab combine
     *
     * @param player
     */
    private void reOpenItemCombine(Player player) {
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(REOPEN_TAB_COMBINE);
            msg.writer().writeByte(player.combineNew.itemsCombine.size());
            for (Item it : player.combineNew.itemsCombine) {
                for (int j = 0; j < player.inventory.itemsBag.size(); j++) {
                    if (it == player.inventory.itemsBag.get(j)) {
                        msg.writer().writeByte(j);
                    }
                }
            }
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    /**
     * Hiệu ứng ghép ngọc rồng
     *
     * @param player
     * @param icon
     */
    private void sendEffectCombineDB(Player player, short icon) {
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(COMBINE_DRAGON_BALL);
            msg.writer().writeShort(icon);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    //--------------------------------------------------------------------------Ratio, cost combine
    private int getGoldPhaLeHoa(int star) {
        switch (star) {
            case 0:
                return 5000000;
            case 1:
                return 20000000;
            case 2:
                return 35000000;
            case 3:
                return 50000000;
            case 4:
                return 75000000;
            case 5:
                return 90000000;
            case 6:
                return 115000000;
            case 7:
                return 150000000;
            case 8:
                return 240000000;
            case 9:
                return 300000000;
            case 10:
                return 450000000;
            case 11:
                return 500000000;
            case 12:
                return 1000000000;
        }
        return 0;
    }

    private float getRatioPhaLeHoa(int star) { //tile dap do chi hat mit
        switch (star) {
            case 0:
                return 100f;
            case 1:
                return 80f;
            case 2:
                return 50f;
            case 3:
                return 30f;
            case 4:
                return 10f;
            case 5:
                return 5f;
            case 6:
                return 2f;
            case 7:
                return 1f;
            case 8:
                return 0.5f;
            case 9:
                return 1f;
            case 10:
                return 0.9f;
            case 11:
                return 0.8f;
            case 12:
                return 0.8f;
        }

        return 0;
    }

    private int getGemPhaLeHoa(int star) {
        switch (star) {
            case 0:
                return 10;
            case 1:
                return 20;
            case 2:
                return 30;
            case 3:
                return 40;
            case 4:
                return 50;
            case 5:
                return 60;
            case 6:
                return 70;
            case 7:
                return 80;
            case 8:
                return 50;
            case 9:
                return 50;
            case 10:
                return 50;
            case 11:
                return 50;
            case 12:
                return 50;
        }
        return 0;
    }

    private int getGemEpSao(int star) {
        switch (star) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 5;
            case 3:
                return 10;
            case 4:
                return 25;
            case 5:
                return 50;
            case 6:
                return 100;
        }
        return 0;
    }

    private double getTileNangCapDo(int level) {
        switch (level) {
            case 0:
                return 100;
            case 1:
                return 80;
            case 2:
                return 50;
            case 3:
                return 30;
            case 4:
                return 20;
            case 5:
                return 10;
            case 6:
                return 5;
            case 7: // 7 sao
                return 1;
            case 8:
                return 0.5;
            case 9:
                return 0.5;
            case 10: // 7 sao
                return 0.3;
            case 11: // 7 sao
                return 1;
            case 12: // 7 sao
                return 1;
        }
        return 0;
    }

    private int getCountDaNangCapDo(int level) {
        switch (level) {
            case 0:
                return 3;
            case 1:
                return 7;
            case 2:
                return 11;
            case 3:
                return 17;
            case 4:
                return 23;
            case 5:
                return 35;
            case 6:
                return 50;
            case 7:
                return 70;
        }
        return 0;
    }

    private int getCountDaBaoVe(int level) {
        return level + 1;
    }

    private int getGoldNangCapDo(int level) {
        switch (level) {
            case 0:
                return 50000;
            case 1:
                return 200000;
            case 2:
                return 800000;
            case 3:
                return 5000000;
            case 4:
                return 30000000;
            case 5:
                return 50000000;
            case 6:
                return 80000000;
            case 7:
                return 300000000;
        }
        return 0;
    }

    //--------------------------------------------------------------------------check
    private boolean isCoupleItemNangCap(Item item1, Item item2) {
        Item trangBi = null;
        Item daNangCap = null;
        if (item1 != null && item1.isNotNullItem()) {
            if (item1.template.type < 5) {
                trangBi = item1;
            } else if (item1.template.type == 14) {
                daNangCap = item1;
            }
        }
        if (item2 != null && item2.isNotNullItem()) {
            if (item2.template.type < 5) {
                trangBi = item2;
            } else if (item2.template.type == 14) {
                daNangCap = item2;
            }
        }
        if (trangBi != null && daNangCap != null) {
            if (trangBi.template.type == 0 && daNangCap.template.id == 223) {
                return true;
            } else if (trangBi.template.type == 1 && daNangCap.template.id == 222) {
                return true;
            } else if (trangBi.template.type == 2 && daNangCap.template.id == 224) {
                return true;
            } else if (trangBi.template.type == 3 && daNangCap.template.id == 221) {
                return true;
            } else if (trangBi.template.type == 4 && daNangCap.template.id == 220) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isCoupleItemNangCapCheck(Item trangBi, Item daNangCap) {
        if (trangBi != null && daNangCap != null) {
            if (trangBi.template.type == 0 && daNangCap.template.id == 223) {
                return true;
            } else if (trangBi.template.type == 1 && daNangCap.template.id == 222) {
                return true;
            } else if (trangBi.template.type == 2 && daNangCap.template.id == 224) {
                return true;
            } else if (trangBi.template.type == 3 && daNangCap.template.id == 221) {
                return true;
            } else if (trangBi.template.type == 4 && daNangCap.template.id == 220) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isDaPhaLe(Item item) {
        return item != null && (item.template.type == 30 || (item.template.id >= 14 && item.template.id <= 20));
    }

    private boolean isTrangBiPhaLeHoa(Item item) {
        if (item != null && item.isNotNullItem()) {
            if (item.template.type <= 5 || item.template.type == 32) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private int getParamDaPhaLe(Item daPhaLe) {
        if (daPhaLe.template.type == 30) {
            return daPhaLe.itemOptions.get(0).param;
        }
        switch (daPhaLe.template.id) {
            case 20:
                return 5; // +5%hp
            case 19:
                return 5; // +5%ki
            case 18:
                return 5; // +5%hp/30s
            case 17:
                return 5; // +5%ki/30s
            case 16:
                return 3; // +3%sđ
            case 15:
                return 2; // +2%giáp
            case 14:
                return 1; // +5%né đòn
            default:
                return -1;
        }
    }

    private int getOptionDaPhaLe(Item daPhaLe) {
        if (daPhaLe.template.type == 30) {
            return daPhaLe.itemOptions.get(0).optionTemplate.id;
        }
        switch (daPhaLe.template.id) {
            case 20:
                return 77;
            case 19:
                return 103;
            case 18:
                return 80;
            case 17:
                return 81;
            case 16:
                return 50;
            case 15:
                return 94;
            case 14:
                return 108;
            default:
                return -1;
        }
    }

    /**
     * Trả về id item c0
     *
     * @param gender
     * @param type
     * @return
     */
    private int getTempIdItemC0(int gender, int type) {
        if (type == 4) {
            return 12;
        }
        switch (gender) {
            case 0:
                switch (type) {
                    case 0:
                        return 0;
                    case 1:
                        return 6;
                    case 2:
                        return 21;
                    case 3:
                        return 27;
                }
                break;
            case 1:
                switch (type) {
                    case 0:
                        return 1;
                    case 1:
                        return 7;
                    case 2:
                        return 22;
                    case 3:
                        return 28;
                }
                break;
            case 2:
                switch (type) {
                    case 0:
                        return 2;
                    case 1:
                        return 8;
                    case 2:
                        return 23;
                    case 3:
                        return 29;
                }
                break;
        }
        return -1;
    }

    //Trả về tên đồ c0
    private String getNameItemC0(int gender, int type) {
        if (type == 4) {
            return "Rada cấp 1";
        }
        switch (gender) {
            case 0:
                switch (type) {
                    case 0:
                        return "Áo vải 3 lỗ";
                    case 1:
                        return "Quần vải đen";
                    case 2:
                        return "Găng thun đen";
                    case 3:
                        return "Giầy nhựa";
                }
                break;
            case 1:
                switch (type) {
                    case 0:
                        return "Áo sợi len";
                    case 1:
                        return "Quần sợi len";
                    case 2:
                        return "Găng sợi len";
                    case 3:
                        return "Giầy sợi len";
                }
                break;
            case 2:
                switch (type) {
                    case 0:
                        return "Áo vải thô";
                    case 1:
                        return "Quần vải thô";
                    case 2:
                        return "Găng vải thô";
                    case 3:
                        return "Giầy vải thô";
                }
                break;
        }
        return "";
    }

    //--------------------------------------------------------------------------Text tab combine
    private String getTextTopTabCombine(int type) {
        switch (type) {
            case EP_SAO_TRANG_BI:
                return "Ta sẽ phù phép\ncho trang bị của ngươi\ntrở lên mạnh mẽ";
                case GIA_HAN_VAT_PHAM:
                return "Ta sẽ phù phép\ncho trang bị của ngươi\nthêm sức mạnh";  
                case HP:
                return "Ta sẽ phù phép\ncho trang bị của ngươi\nthêm sức mạnh"; 
                case SD:
                return "Ta sẽ phù phép\ncho trang bị của ngươi\nthêm sức mạnh"; 
                case KI:
                return "Ta sẽ phù phép\ncho trang bị của ngươi\nthêm sức mạnh"; 
                case GIAP:
                return "Ta sẽ phù phép\ncho trang bị của ngươi\nthêm sức mạnh"; 
            case PHA_LE_HOA_TRANG_BI:
                return "Ta sẽ phù phép\ncho trang bị của ngươi\ntrở thành trang bị pha lê";
            case NHAP_NGOC_RONG:
                return "Ta sẽ phù phép\ncho 7 viên Ngọc Rồng\nthành 1 viên Ngọc Rồng cấp cao";
            case NANG_CAP_VAT_PHAM:
                return "Ta sẽ phù phép cho trang bị của ngươi trở lên mạnh mẽ";
            case PHAN_RA_DO_THAN_LINH:
                return "Ta sẽ phân rã \n  ngọc của người thành điểm!";
            case DAP_SET_KICH_HOAT:
                return "Ta sẽ giúp ngươi chuyển hóa\n1 món đồ hủy diệt\nthành 1 món đồ kích hoạt";
            case NANG_CAP_DO_TS:
                return "Ta sẽ nâng cấp \n  trang bị của người thành\n đồ thiên sứ!";
            case NANG_CAP_SKH_VIP:
                return "Thiên sứ nhờ ta nâng cấp \n  trang bị của người thành\n SKH VIP!";
            case NANG_CAP_BONG_TAI:
                return "Ta sẽ phù phép\ncho bông tai Porata của ngươi\nthành cấp 2";
            case MO_CHI_SO_BONG_TAI:
                return "Ta sẽ phù phép\ncho bông tai Porata cấp 2 của ngươi\ncó 1 chỉ số ngẫu nhiên";
//            case NANG_CAP_LINH_THU:
//                return "Ta sẽ phù phép\ncho Linh Thú cùi bắp của ngươi\ncó 1 chỉ số ngẫu nhiên";
            case CHE_TAO_TRANG_BI_TS:
                return "Chế tạo\ntrang bị thiên sứ";
            case NANG_CAP_DO_THAN:
                return "Chế tạo\nđồ thần linh";
            case NANG_CAP_BONG_TAI_CAP3:
                return "Ta sẽ phù phép\ncho bông tai Porata cấp 2 của ngươi\nthành cấp 3";
            case MO_CHI_SO_BONG_TAI_CAP3:
                return "Ta sẽ phù phép\ncho bông tai Porata cấp 3 của ngươi\ncó 1 chỉ số ngẫu nhiên";
            case NANG_CAP_BONG_TAI_CAP4:
                return "Ta sẽ phù phép\ncho bông tai Porata cấp 3 của ngươi\nthành cấp 4";
            case MO_CHI_SO_BONG_TAI_CAP4:
                return "Ta sẽ phù phép\ncho bông tai Porata cấp 4 của ngươi\ncó 1 chỉ số ngẫu nhiên";
            case LUYEN_HOA_CHIEN_LINH:
                return "Ta sẽ cùng người luyện hóa\nchiến linh";
            case EP_AN_TRANG_BI:
                return "Ép Ấn Trang Bị";
            case NGUYET_AN:
                return "Ép Ấn Trang Bị";
            case DOI_DIEM:
                return "Thức Ăn";
            case NANG_CAP_CHAN_MENH:
                return "Ta sẽ giúp ngươi\nnâng cấp Chân Mệnh\ncủa ngươi lên 1 cấp bậc";
            case NANG_HUY_DIET_LEN_SKH_VIP:
                return "Ta sẽ nâng cấp đồ huỷ diệt của con lên đồ kích hoạt VIP";
            case THUC_TINH_DT:
                return "Ta sẽ giúp Thức Tỉnh chỉ số\ncủa ngươi hoặc đệ tử\nGiúp gia tăng chỉ số cao hơn";
            default:
                return "";
        }
    }

    private String getTextInfoTabCombine(int type) {
        switch (type) {
            case EP_SAO_TRANG_BI:
                return "Chọn trang bị\n(Áo, quần, găng, giày hoặc rađa) có ô đặt sao pha lê\nChọn loại sao pha lê\n"
                        + "Sau đó chọn 'Nâng cấp'";
            case PHA_LE_HOA_TRANG_BI:
                return "Chọn trang bị\n(Áo, quần, găng, giày hoặc rađa)\nSau đó chọn 'Nâng cấp'";
            case NHAP_NGOC_RONG:
                return "Vào hành trang\nChọn 7 viên ngọc cùng sao\nSau đó chọn 'Làm phép'";
            case NANG_CAP_VAT_PHAM:
                return "vào hành trang\nChọn trang bị\n(Áo, quần, găng, giày hoặc rađa)\nChọn loại đá để nâng cấp\n"
                        + "Sau đó chọn 'Nâng cấp'";
            case PHAN_RA_DO_THAN_LINH:
                return "vào hành trang\nChọn item\n(Đồ Thần)\nChọn Đồ Thần Rồi Tách\n"
                        + "Sau đó chọn 'Tách'";
            case NANG_CAP_DO_TS:
                return "vào hành trang\nChọn 2 trang bị hủy diệt bất kì\nkèm 1 món đồ thần linh\n và 5 mảnh thiên sứ\n "
                        + "sẽ cho ra đồ thiên sứ từ 0-15% chỉ số"
                        + "Sau đó chọn 'Nâng Cấp'";
            case NANG_CAP_SKH_VIP:
                return "vào hành trang\nChọn 1 trang bị thiên sứ bất kì\nChọn tiếp ngẫu nhiên 2 món SKH thường \n "
                        + " đồ SKH VIP sẽ cùng loại \n với đồ thiên sứ!"
                        + "Chỉ cần chọn 'Nâng Cấp'";
            case NANG_CAP_BONG_TAI:
                return "Vào hành trang\nChọn bông tai Porata\nChọn mảnh bông tai để nâng cấp, số lượng\n99 cái\nSau đó chọn 'Nâng cấp'";
            case MO_CHI_SO_BONG_TAI:
                return "Vào hành trang\nChọn bông tai Porata\nChọn mảnh hồn bông tai số lượng 99 cái\nvà đá xanh lam để nâng cấp\nSau đó chọn 'Nâng cấp'";
//            case NANG_CAP_LINH_THU:
//                return "Vào hành trang\nChọn Linh Thú\nChọn đá ma thuật  số lượng 99 cái\nvà thức ăn để nâng cấp\nSau đó chọn 'Nâng cấp'";  
            case CHE_TAO_TRANG_BI_TS:
                return "Cần 1 công thức vip\nMảnh trang bị tương ứng\n"
                        + "Số Lượng\n999"
                        + "Có thể thêm\nĐá nâng cấp (tùy chọn) để tăng tỉ lệ chế tạo\n"
                        + "Đá may mắn (tùy chọn) để tăng tỉ lệ các chỉ số cơ bản và chỉ số ẩn\n"
                        + "Sau đó chọn 'Nâng cấp'";
            case NANG_CAP_DO_THAN:
                return "Cần mảnh thần linh\ntương ứng\n"
                        + "Số Lượng\n55"
                        + "Sau đó chọn 'Nâng cấp'";
            case NANG_CAP_BONG_TAI_CAP3:
                return "Vào hành trang\nchọn bông tai Porata cấp 2\nchọn x999 MVBT C3\nSau đó chọn 'Nâng cấp'";
            case MO_CHI_SO_BONG_TAI_CAP3:
                return "Vào hành trang\nChọn bông tai Porata cấp 3\nChọn thạch phù để nâng cấp, số lượng 99 viên\nđá xanh lam\nSau đó chọn 'Nâng cấp'";
            case NANG_CAP_BONG_TAI_CAP4:
                return "Vào hành trang\nchọn bông tai Porata cấp 3\nchọn x999 MVBT C4\nSau đó chọn 'Nâng cấp' ";
            case MO_CHI_SO_BONG_TAI_CAP4:
                return "Vào hành trang\nChọn bông tai Porata cấp 4\nChọn thạch phù số lượng 99 \nvà đá xanh lam số lượng 15 viên\nSau đó chọn 'Nâng cấp'";
            case EP_AN_TRANG_BI:
                return "Vào hành trang\nChọn Trang Bị\nChọn Đá Ngũ Sắc Số Lượng 25 \nThêm 1 Thỏi Vàng Vào\nSau đó chọn 'Nâng cấp'";
            case DAP_SET_KICH_HOAT:
                return "Vào hành trang\nChọn món đồ hủy diệt tương ứng\n(Áo, quần, găng, giày hoặc nhẫn)\n"
                        + "(Có thể thêm 1 món đồ thần linh bất kỳ để tăng tỉ lệ)\nSau đó chọn 'Đập'";
            case NGUYET_AN:
                return "Vào hành trang\nChọn Trang Bị\nChọn Đá Nâng Cấp Số Lượng 99 \nMấy Viên Kiểu Titan Ruby Các Thứ Ấy\nSau đó chọn 'Nâng cấp'\nCHÚ Ý KHÔNG DÙNG\nTRANG BỊ KÍCH HOẠT";
            case DOI_DIEM:
                return "Vào hành trang\nChọn x99 Thức Ăn\nSau đó chọn 'Nâng cấp'";
            case NANG_CAP_CHAN_MENH:
                return "vào hành trang\nChọn Chân Mệnh\nVà 99 Đá Ngũ Sắc "
                        + "Bấm Nâng Cấp";
            case NANG_HUY_DIET_LEN_SKH_VIP:
                return "Vào hành trang\n Chọn 3 món huỷ diệt bất kỳ, món đầu tiên sẽ làm gốc, sau đó chọn 'Nâng cấp'";
            case THUC_TINH_DT:
                return "Nguyên liệu cần có:\n\n"
                        + "[Đối với Đệ Tử]\nx15 Đá Thức Tỉnh\nx3 Ngọc Rồng 3 Sao\nx1 Thỏi Vàng\n"
                        + "Khi thức tỉnh sẽ có khả năng Đột Phá\nGiúp tăng thêm 1 cấp bậc đệ tử\n"
                        + "[Đối với Sư Phụ]\nx30 Đá Thức Tỉnh\nx3 Ngọc Rồng 1 Sao\nx3 Thỏi Vàng\n"
                        + "(Nếu thất bại sẽ chì mất đá thức tỉnh)\n\n"
                        + "Sau khi đủ nguyên liệu chọn 'Nâng cấp'";
                case GIA_HAN_VAT_PHAM:
                return "Vào hành trang\n"
                        + "Chọn 1 trang bị có hạn sử dụng\n"
                        + "Chọn thẻ gia hạn\n"
                        + "Sau đó chọn 'Gia hạn'";   
                case HP:
                return "Vào hành trang\n"
                        + "Chọn 1 trang bị có chỉ số HP\n"
                        + "Chọn thẻ nâng cấp Hp\n"
                        + "Sau đó chọn ' Nâng Cấp'";
                case SD:
                return "Vào hành trang\n"
                        + "Chọn 1 trang bị có chỉ số Sức đánh\n"
                        + "Chọn thẻ nâng cấp sức đánh\n"
                        + "Sau đó chọn ' Nâng Cấp'";
                case KI:
               return "Vào hành trang\n"
                        + "Chọn 1 trang bị có chỉ số KI\n"
                        + "Chọn thẻ nâng cấp KI\n"
                        + "Sau đó chọn ' Nâng Cấp'";
                case GIAP:
                return "Vào hành trang\n"
                        + "Chọn 1 trang bị có chỉ số SDCM\n"
                        + "Chọn thẻ nâng cấp SDCM\n"
                        + "Sau đó chọn ' Nâng Cấp'";
            default:
                return "";
        }
    }

}
