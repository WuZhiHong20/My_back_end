package org.springblade.coach.home.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.coach.home.entity.HumanPlusPUserCollection;
import org.springblade.coach.home.entity.pojo.CollectionDto;
import org.springblade.coach.home.mapper.CollectionMapper;
import org.springblade.coach.home.service.CollectionService;
import org.springblade.coach.home.service.HumanPlusCourceService;
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
 * @date 2022/5/10 17:41
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, HumanPlusPUserCollection>
    implements CollectionService {

    @Autowired
    private HumanPlusCourceService humanPlusCourceService;

    /**
     * 自定义函数实现多表查询，
     * 先通过UserId从表“human_plus_p_user_collection”中获取该用户所有课程id，
     * 存储在一个List<Long> cource_id中
     * 再通过cource_id从表“human_plus_p_courc”(存课程id和课程名字的表)获得信息封装给collectionDtos
     * @param UserId
     * @return
     */
    @Override
    public List<CollectionDto> userList(String UserId){
        Map<String,Object> humanPlusPUserCollection = new HashMap<>();
        humanPlusPUserCollection.put("userId",UserId);

        //通过QueryWrapper来进行查询
        List<HumanPlusPUserCollection> collectionList = baseMapper.selectList(Condition.getQueryWrapper(humanPlusPUserCollection,HumanPlusPUserCollection.class).lambda().orderByDesc(HumanPlusPUserCollection::getCreatedTime));

        List<Long> cource_id = collectionList.stream().map(HumanPlusPUserCollection::getCourceId).collect(Collectors.toList());
        List<CollectionDto> collectionDtos = new ArrayList<>();
        int size = 0;
        if(!collectionList.isEmpty()){
            for(Long id : cource_id){
                CollectionDto collectionDto = new CollectionDto();
                collectionDto.from(collectionList.get(size),humanPlusCourceService.getById(id.toString()));
                collectionDtos.add(collectionDto);
            }
            return collectionDtos;
        }
        return null;
    }


}
