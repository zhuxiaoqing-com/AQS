package com.youxi.AI.fsm.pet;

import com.sh.game.constant.ConstAttribute;
import com.sh.game.system.fight.constant.FightConst;
import com.sh.game.system.map.constant.AIConst;
import com.sh.game.system.map.fsm.state.PerformerActionState;
import com.sh.game.system.map.navigation.navmesh.RecastNavManager;
import com.sh.game.system.map.obj.Conjure;
import com.sh.game.system.map.obj.MapObjectType;
import com.sh.game.system.map.obj.Performer;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.system.map.vo.Vector3f;
import com.sh.game.util.GeomUtil;
import com.sh.game.util.TimeUtil;

/**
 * 宠物出生,CD时间
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2017/9/11.
 */
public class PetBornState extends PetState {


    public PetBornState(PerformerActionState type, Conjure performer) {
        super(type, performer);
    }

    @Override
    public void enter(GameMap map) {
        performer.setDead(false);
        performer.setInvincible(true);
        performer.setHp(performer.getHp());
        performer.setMp(performer.getMp());
        if (!performer.isBolsterNpc() && performer.getType() != MapObjectType.MONSTER) {
            Performer master = performer.getMaster();
            Vector3f checkV3 = GeomUtil.createPointFromDegrees(master.getVector3(), master.getDir() - 180, performer.followMinRange());
            Vector3f vector3 = RecastNavManager.getInstance().findNearestVector(map, checkV3, master.getVector3());
            performer.setVector3(vector3);
            performer.setDir(master.getDir());
        }
        //设置可见
        performer.setVisible(true);
        //加入视野
        map.addView(performer, performer.getVector3().findV2());
        performer.sendAttrUpdateMsg(ConstAttribute.lifeType, FightConst.LifeType.LIFE_BORN);

        int bornDelay = performer.getBornDelay() < AIConst.delay ? AIConst.delay : performer.getBornDelay();
        performer.getActionMachine().getAiData().setBornTime(TimeUtil.getNowOfMills() + bornDelay);
    }

    @Override
    public void exit(GameMap map) {
        performer.setInvincible(false);
    }

    @Override
    public void update(GameMap map, int delta) {
        super.update(map, delta);
    }

    @Override
    public PerformerActionState checkTransition(GameMap map) {
        if (performer.isInit()) { // 刚初始化的对象
            performer.setInit(false);
            //初始化，之后需要手动进入enter模式
            enter(map);
            return PerformerActionState.born;
        }
        if (TimeUtil.getNowOfMills() >= performer.getActionMachine().getAiData().getBornTime()) {
            return PerformerActionState.active;
        }
        return PerformerActionState.born;
    }
}
