package org.springblade.coach.home.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.coach.home.entity.HumanPlusPCource;
import org.springblade.coach.home.entity.HumanPlusPCourceTask;
import org.springblade.coach.home.entity.HumanPlusPTask;
import org.springblade.coach.home.entity.pojo.CourceDto;
import org.springblade.coach.home.mapper.HumanPlusCourceMapper;
import org.springblade.coach.home.service.HumanPlusCourceService;
import org.springblade.coach.home.service.HumanPlusPCourceTaskService;
import org.springblade.coach.home.service.TaskService;
import org.springblade.core.mp.support.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author WZH
 * @version 1.0
 * @date 2022/5/12 20:21
 */
@Service
public class HumanPlusCourceServiceImpl extends ServiceImpl<HumanPlusCourceMapper, HumanPlusPCource>
    implements HumanPlusCourceService {
    @Autowired
    HumanPlusPCourceTaskService humanPlusPCourceTaskService;
    @Autowired
    TaskService taskService;


    /**
     * 传入课程id
     * @param courceId
     * @return
     */
    @Override
    public CourceDto cource_detail(String courceId){
        HumanPlusPCource humanPlusPCource=baseMapper.selectById(courceId);
        //封装wrapper
        Map<String, Object> cource_task= new HashMap<String, Object>();
        cource_task.put("courceId",courceId);

        /**
         * 操作表“human_plus_p_cource_task”，
         * 一个课程可能对应多个任务信息
         * 讲courceId对应的任务id存在一个List中
         */
        List<Long> taskIds=humanPlusPCourceTaskService.list(Condition.getQueryWrapper(cource_task, HumanPlusPCourceTask.class).lambda())
                .stream().map(HumanPlusPCourceTask::getTaskId).collect(Collectors.toList());
        /**
         * 通过taksId 查询human_plus_p_task
         */
        if (!taskIds.isEmpty()){
            List<HumanPlusPTask> tasks=new ArrayList<>();
            for(Long taskId:taskIds){
                tasks.add(taskService.getById(taskId.toString()));
            }
            CourceDto courceDto=new CourceDto();
            courceDto.from(humanPlusPCource,tasks);
            return courceDto;
        }
        return null;
    }
}
