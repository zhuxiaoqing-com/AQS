package com.youxi.AI.fsm.pet;

import com.sh.game.core.ai.AbstractFSMState;
import com.sh.game.system.buff.BuffManager;
import com.sh.game.system.map.fsm.state.PerformerActionState;
import com.sh.game.system.map.navigation.navmesh.RecastNavManager;
import com.sh.game.system.map.obj.Conjure;
import com.sh.game.system.map.obj.MapObjectType;
import com.sh.game.system.map.obj.Performer;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.system.map.util.MoveUtil;
import com.sh.game.system.map.vo.Vector3f;
import com.sh.game.system.move.MoveManager;
import com.sh.game.util.GeomUtil;
import com.sh.game.util.TimeUtil;

/**
 * 宠物出生,CD时间
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2017/9/11.
 */
public abstract class PetState extends AbstractFSMState<PerformerActionState, Conjure> {


    public PetState(PerformerActionState type, Conjure performer) {
        super(type, performer);
    }

    @Override
    public void update(GameMap map, int delta) {
        //怪物存活时间结束
        if (performer.getType() != MapObjectType.PET) {
            //Pet pet = (Pet) performer;
/*            IHeroEntity attendant = pet.getAttendant();
            Player master = pet.getMaster();
            if (!(attendant instanceof Attendant)) {
                return;
            }
            Attendant attendantObj = (Attendant) attendant;
            if (!GameManager.getInstance().get(AttendantFightManager.class).canFight(attendantObj)) {
                GameManager.getInstance().get(AttendantFightManager.class).rest(master, attendantObj.getConfigId());
            }*/
            if (!performer.isDead() && TimeUtil.getNowOfMills() - performer.getCreateTime() > performer.getLiveTime()) {
                performer.death();
            }
        }
        //更新宠物buff
        BuffManager.getInstance().updateBuffer(map, performer, delta);
    }

    protected void follow(GameMap map, Performer master) {
        //距离主角距离
        double masterDis = performer.getVector3().distance(master.getVector3());
        if (masterDis > performer.followMaxRange()) {
            //清除路径点
            performer.getNextPathVectors().clear();
            //宠物超过跟随范围、直接传送
            Vector3f checkV3 = GeomUtil.createPointFromDegrees(master.getVector3(), master.getDir() - 180, performer.followMinRange());
            Vector3f vector3 = RecastNavManager.getInstance().findNearestVector(map, checkV3, master.getVector3());
            vector3.setVector(vector3);
            //传送位置
            MoveManager.getInstance().changePos(map, performer, vector3, performer.getDir());
        } else if (masterDis > performer.followMinRange()) {
            //宠物在跟随的范围，则寻找主角跟随
            MoveUtil.chaseWalk(map, performer, master);
        }
    }

}
