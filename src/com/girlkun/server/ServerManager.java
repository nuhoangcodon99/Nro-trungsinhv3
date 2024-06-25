

package com.girlkun.server;

import com.arriety.MaQuaTang.MaQuaTangManager;
import com.girlkun.database.GirlkunDB;

import java.net.ServerSocket;

import com.girlkun.jdbc.daos.HistoryTransactionDAO;
import com.girlkun.models.boss.BossManager;
import com.girlkun.models.item.Item;
import com.girlkun.models.map.Zone;
import com.girlkun.models.map.challenge.MartialCongressManager;
import com.girlkun.models.map.daihoi.DaiHoiService;
import com.girlkun.models.map.sieuhang.SieuHangManager;
import com.girlkun.models.map.vodai.MartialCongressManagere;
import com.girlkun.models.matches.pvp.DaiHoiVoThuat;
import com.girlkun.models.npc.NauBanh;
import com.girlkun.models.npc.DuaHau;
import static com.girlkun.models.npc.NpcFactory.DuaHau;
import com.girlkun.models.player.Player;
import com.girlkun.network.session.ISession;
import com.girlkun.network.example.MessageSendCollect;
import com.girlkun.network.server.GirlkunServer;
import com.girlkun.network.server.IServerClose;
import com.girlkun.network.server.ISessionAcceptHandler;
import com.girlkun.result.GirlkunResultSet;
import static com.girlkun.server.Manager.MAPS;
import com.girlkun.server.io.MyKeyHandler;
import com.girlkun.server.io.MySession;
import com.girlkun.services.ClanService;
import com.girlkun.services.InventoryServiceNew;
import com.girlkun.services.NgocRongNamecService;
import com.girlkun.services.Service;
import com.girlkun.services.func.ChonAiDay;
import com.girlkun.services.func.TopService;
import com.girlkun.utils.GetSizeObject;
import com.girlkun.utils.Logger;
import com.girlkun.utils.TimeUtil;
import com.girlkun.utils.Util;
import com.kygui.ShopKyGuiManager;

import java.util.*;
import java.util.logging.Level;

public class ServerManager {

    public int threadMap;
    public static String timeStart;

    public static final Map CLIENTS = new HashMap();

    public static String NAME = "Trùng Sinh";
    public static int PORT = 12345;

    private static ServerManager instance;

    public static ServerSocket listenSocket;
    public static boolean isRunning;

    public void init() {
        Manager.gI();
        try {
            if (Manager.LOCAL) {
                return;
            }
            GirlkunDB.executeUpdate("update account set last_time_login = '2000-01-01', "
                    + "last_time_logout = '2001-01-01'");
        } catch (Exception e) {
        }
        HistoryTransactionDAO.deleteHistory();
    }

    public static ServerManager gI() {
        if (instance == null) {
            instance = new ServerManager();
            instance.init();
        }
        return instance;
    }

    public static void main(String[] args) {
        timeStart = TimeUtil.getTimeNow("dd/MM/yyyy HH:mm:ss");
        ServerManager.gI().run();
    }

    public void run() {
        long delay = 500;
        isRunning = true;
        new Thread(() -> {
            while (true) {
                try {
                    SieuHangManager.gI().update();
                    Thread.sleep(50);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        activeCommandLine();
        activeGame();
        activeServerSocket();
        Logger.log(Logger.PURPLE,"\n      ▄█████ ]▄▄▄▄▄▄▃\n ▂▄▅███████▅▄▃▂\nI████[Ánh Ngọc Zalo0343214443]████]\n ◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲⊙◤\n");
        new Thread(DaiHoiVoThuat.gI(), "Thread DHVT").start();

        ChonAiDay.gI().lastTimeEnd = System.currentTimeMillis() + 300000;
        new Thread(ChonAiDay.gI(), "Thread CAD").start();

        NgocRongNamecService.gI().initNgocRongNamec((byte) 0);

        new Thread(NgocRongNamecService.gI(), "Thread NRNM").start();

        new Thread(TopService.gI(), "Thread TOP").start();

        new Thread(NauBanh.gI(), "Thread NauBanh").start();
        
         new Thread(DuaHau.gI(), "Thread DuaHau").start();

        new Thread(() -> {
            while (isRunning) {
                try {
                    long start = System.currentTimeMillis();
                    MartialCongressManager.gI().update();
                    MartialCongressManagere.gI().update();
                    long timeUpdate = System.currentTimeMillis() - start;
                    if (timeUpdate < delay) {
                        Thread.sleep(delay - timeUpdate);
                    }
                } catch (InterruptedException e) {
                }
            }
        }, "Update đại hội võ thuật").start();
        try {
            Thread.sleep(1000);
            BossManager.gI().loadBoss();
            Manager.MAPS.forEach(com.girlkun.models.map.Map::initBoss);
            DaiHoiService.gI().initDaiHoiVoThuat();
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(BossManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (isRunning) {
                for (com.girlkun.models.map.Map map : MAPS) {
                    for (Zone zone : map.zones) {
                        try {
                            zone.update();
                        } catch (Exception e) {
                        }
                    }
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
        }
    }

    private void act() throws Exception {
        GirlkunServer.gI().init().setAcceptHandler(new ISessionAcceptHandler() {
            @Override
            public void sessionInit(ISession is) {
//                antiddos girlkun
                if (!canConnectWithIp(is.getIP())) {
                    is.disconnect();

                    return;
                }

                is = is.setMessageHandler(Controller.getInstance())
                        .setSendCollect(new MessageSendCollect())
                        .setKeyHandler(new MyKeyHandler())
                        .startCollect();
            }

            @Override
            public void sessionDisconnect(ISession session) {
                Client.gI().kickSession((MySession) session);
            }
        }).setTypeSessioClone(MySession.class)
                .setDoSomeThingWhenClose(new IServerClose() {
                    @Override
                    public void serverClose() {
                        System.out.println("Ánh Ngọc Zalo0343214443");
                        System.exit(0);
                    }
                })
                .start(PORT);

    }

    private void activeServerSocket() {
        if (true) {
            try {
                this.act();
            } catch (Exception e) {
            }
            return;
        }
    }

    private boolean canConnectWithIp(String ipAddress) {
        Object o = CLIENTS.get(ipAddress);
        if (o == null) {
            CLIENTS.put(ipAddress, 1);
            return true;
        } else {
            int n = Integer.parseInt(String.valueOf(o));
            if (n < Manager.MAX_PER_IP) {
                n++;
                CLIENTS.put(ipAddress, n);
                return true;
            } else if (n > Manager.MAX_PER_IP) {
                GirlkunServer.gI().stopConnect();
            } else {
//                
                return false;
            }
        }
        return false;
    }

    public void disconnect(MySession session) {
        Object o = CLIENTS.get(session.getIP());
        if (o != null) {
            int n = Integer.parseInt(String.valueOf(o));
            n--;
            if (n < 0) {
                n = 0;
            }
            CLIENTS.put(session.getIP(), n);
        }
    }

    private void activeCommandLine() {
        new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            while (true) {
                String line = sc.nextLine();
                if (line.equals("baotri")) {
                    Maintenance.gI().start(15);
                } else if (line.equals("baotrinhanh")) {
                    Maintenance.gI().start(0);
                } else if (line.equals("size")) {
                    System.out.println("Size List PLayer: " + GetSizeObject.sizeListPlayer());
                    System.out.println("Size List Boss: " + GetSizeObject.sizeListBoss());
                }else if (line.equals("Trùng sinh")) {
                    ServerNotify.gI().notify(" Trùng Sinh Debug Server: " + Thread.activeCount());
                } else if (line.equals("nplayer")) {
                    Logger.error("Player in game: " + Client.gI().getPlayers().size() + "\n");
                } else if (line.equals("admin")) {
                    new Thread(() -> {
                        Client.gI().close();
                    }).start();
                } else if (line.startsWith("bang")) {
                    new Thread(() -> {
                        try {
                            ClanService.gI().close();
                            Logger.error("Save " + Manager.CLANS.size() + " bang");
                        } catch (Exception e) {
                            Logger.error("Lỗi save clan!...................................\n");
                        }
                    }).start();
                }
            }
        }, "Active line").start();
    }

     private void activeGame() {
    }

    public void close(long delay) {
        GirlkunServer.gI().stopConnect();
        isRunning = false;
        try {
            ClanService.gI().close();
        } catch (Exception e) {
            Logger.error("Lỗi save clan!...................................\n");
        }
        Client.gI().close();
        ShopKyGuiManager.gI().save();
        Logger.success("SUCCESSFULLY MAINTENANCE!...................................\n");
        System.exit(0);
    }
    public long getNumPlayer() {
        long num = 0;
        try {
            GirlkunResultSet rs = GirlkunDB.executeQuery("SELECT COUNT(*) FROM `player`");
            rs.first();
            num = rs.getLong(1);
        } catch (Exception e) {
        }
        return num;
    }
}
