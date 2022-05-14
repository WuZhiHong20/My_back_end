package org.springblade.coach.home.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.coach.home.entity.HumanPlusPTask;
import org.springblade.coach.home.mapper.TaskMapper;
import org.springblade.coach.home.service.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, HumanPlusPTask>
        implements TaskService {
}
