package org.springblade.coach.home.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.coach.home.entity.HumanPlusPCource;
import org.springblade.coach.home.entity.pojo.CourceDto;

/**
 * @author WZH
 * @version 1.0
 * @date 2022/5/12 20:18
 */
public interface HumanPlusCourceService extends IService<HumanPlusPCource>{
    CourceDto cource_detail(String courceId);
}
