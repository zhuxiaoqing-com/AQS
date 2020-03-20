package com.example.demo1.util;


/**
 * 地图常量
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2017/11/8.
 */
public interface MapConst {

    enum InstanceState {

        /**
         * 副本准备阶段
         */
        WAITING,

        /**
         * 副本初始化阶段
         */
        INIT,

        /**
         * 副本进行阶段
         */
        RUNNING,

        /**
         * 副本结算阶段
         */
        EVALUATE,

        /**
         * 副本关闭阶段
         */
        CLOSE,

        /**
         * 副本销毁阶段
         */
        DESTROY,
    }

    /**
     * 自定义
     */
    interface Define {

        int INSTANCE_ID = 100000; //副本开始ID

        int HUNDRED_MINUTE = 6000000; //100分钟

        int PRISON_ID = 19999;  //监狱ID

        int MAINCITY = 10002;  //主城ID

        int GROUP_LIMIT = 1000;//每个地图的怪物组上限
    }

    interface Scene {
        int LINE_NUM = 50; //大世界场景分线数量
        int SCENE_MIN_NUM = 50; // 单场景最小分线人数
        int SCENE_MAX_NUM = 80; // 单场景最大分线人数
        int SCENE_MAX_NUM_INCLUDE = 150; // 单场景最大人数
    }


    /**
     * 线
     */
    interface Line {

        int DEFAULT = 1;

    }

    /**
     * 地图类型
     */
    interface MapType {

        //大世界
        int WORLD = 1;

        //副本
        int INSTANCE = 2;

        //主城
        int MAJOR_CITY = 4;

        //位面
        int PLANES = 5;

        //监狱
        int PRISON = 6;

        //公会驻地
        int GUILD = 7;

        // 世界BOSS
        int WORLD_BOSS = 9;

        // 地牢刷怪
        int DUNGEON = 10;

        // 遗迹古战场
        int RUIN = 11;
    }

    /**
     * 场景传送类型
     */
    interface TeleportType {
        //场景出生点
        int SCENE_BORN = 1;
        //回城
        int HOME_CITY = 2;
    }


    /**
     * 地图区域触发器类型
     */
    interface TriggerType {
        //安全区
        int SAFE_AREA = 4;
        //陷阱
        int TRAP = 8;
        //boss_room,客户端为了区别重叠的触发器，服务端同陷阱
        int BOSS_ROOM = 6;
    }


    /**
     * 远程战斗服副本类型
     */
    interface BattleInstanceType {
        int BORDER = 1;     //圣域（边境）
        int CARFT = 2;      //圣域争霸(城战)
    }

    /**
     * 游戏副本类型
     */
    interface GameInstanceType {

    }

    /**
     * 方向
     */
    enum Dir implements MapConst {

        NONE(0, -1) {// 同一个点

            @Override
            public Dir left() {
                return NONE;
            }

            @Override
            public Dir right() {
                return NONE;
            }

            @Override
            public Dir behind() {
                return NONE;
            }

        }, TOP(0x10000000, 0) {// 上

            @Override
            public Dir left() {
                return LEFT_TOP;
            }

            @Override
            public Dir right() {
                return RIGHT_TOP;
            }

            @Override
            public Dir behind() {
                return BOTTOM;
            }
        }, RIGHT_TOP(0x01000000, 1) {// 右上

            @Override
            public Dir left() {
                return TOP;
            }

            @Override
            public Dir right() {
                return RIGHT;
            }

            @Override
            public Dir behind() {
                return LEFT_BOTTOM;
            }
        }, RIGHT(0x00100000, 2) {// 右

            @Override
            public Dir left() {
                return RIGHT_TOP;
            }

            @Override
            public Dir right() {
                return RIGHT_BOTTOM;
            }

            @Override
            public Dir behind() {
                return LEFT;
            }
        }, RIGHT_BOTTOM(0x00010000, 3) {// 右下

            @Override
            public Dir left() {
                return RIGHT;
            }

            @Override
            public Dir right() {
                return BOTTOM;
            }

            @Override
            public Dir behind() {
                return LEFT_TOP;
            }
        }, BOTTOM(0x00001000, 4) {// 下

            @Override
            public Dir left() {
                return RIGHT_BOTTOM;
            }

            @Override
            public Dir right() {
                return LEFT_BOTTOM;
            }

            @Override
            public Dir behind() {
                return TOP;
            }
        }, LEFT_BOTTOM(0x00000100, 5) {// 左下

            @Override
            public Dir left() {
                return BOTTOM;
            }

            @Override
            public Dir right() {
                return LEFT;
            }

            @Override
            public Dir behind() {
                return RIGHT_TOP;
            }
        }, LEFT(0x00000010, 6) {// 左

            @Override
            public Dir left() {
                return LEFT_BOTTOM;
            }

            @Override
            public Dir right() {
                return LEFT_TOP;
            }

            @Override
            public Dir behind() {
                return RIGHT;
            }
        }, LEFT_TOP(0x00000001, 7) {// 左上

            @Override
            public Dir left() {
                return LEFT;
            }

            @Override
            public Dir right() {
                return TOP;
            }

            @Override
            public Dir behind() {
                return RIGHT_BOTTOM;
            }
        },
        ;
        /**
         * 方向的值
         */
        private int value;
        /**
         * 八叉树数组中的下表索引
         */
        private int index;

        /**
         * 左侧方向(逆时针)
         *
         * @return
         */
        public abstract Dir left();

        /**
         * 右侧方向（顺时针）
         *
         * @return
         */
        public abstract Dir right();

        /**
         * 相反方向
         *
         * @return
         */
        public abstract Dir behind();

        public static Dir getByIndex(int index) {
            for (Dir dir : values()) {
                if (dir.index == index) {
                    return dir;
                }
            }
            return null;
        }

        private Dir(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}
