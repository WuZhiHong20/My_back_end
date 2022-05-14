package org.springblade.coach.home.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.coach.home.entity.HumanPlusPCourceTask;

import java.util.List;

public interface HumanPlusPCourceTaskMapper extends BaseMapper<HumanPlusPCourceTask> {

    /**
     * @author JShawn
     * @param list
     * @return boolean
     */
    boolean insertMultiTask(List<HumanPlusPCourceTask> list);

}
