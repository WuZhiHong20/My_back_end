package org.springblade.coach.home.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.coach.home.entity.HumanPlusPUserCollection;
import org.springblade.coach.home.entity.pojo.CollectionDto;

import java.util.List;

/**
 * @author WZH
 * @version 1.0
 * @date 2022/5/10 17:04
 */
public interface CollectionService extends IService<HumanPlusPUserCollection> {
    List<CollectionDto> userList(String UserId);
}
