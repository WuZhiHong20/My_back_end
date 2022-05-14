package org.springblade.coach.home.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.coach.home.entity.HumanPlusPUserCollection;
import org.springblade.coach.home.entity.pojo.CollectionDto;
import org.springblade.coach.home.service.CollectionService;
import org.springblade.coach.login.UserThreadLocal;
import org.springblade.coach.login.entity.pojo.UserDto;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author WZH
 * @version 1.0
 * @date 2022/5/10 15:05
 */
@RestController
@RequestMapping("collection")
@AllArgsConstructor
public class CollectionController {
    private CollectionService collectionService;

    /**
     * 插入功能，从前端返回一个带有humanPlusUserCollection的实体类
     * 从线程中获取data transfer object
     *
     * @param humanPlusPUserCollection
     * @return 操作的表是： human_plus_p_user_collection，插入用户 id和他收藏的课程 id
     */
    @PostMapping("save")
    public R save(@RequestBody HumanPlusPUserCollection humanPlusPUserCollection) {
        UserDto userDto = UserThreadLocal.get();
        humanPlusPUserCollection.setCreatedBy(userDto.getId());
        humanPlusPUserCollection.setCreatedTime(new Date());
        return R.status(collectionService.save(humanPlusPUserCollection));
    }

    /**
     * @param humanPlusPUserCollection
     * @return 操作的表是： human_plus_p_user_collection，
     * 通过用户 id更新他收藏的课程
     */
    @PostMapping("/update")
    public R update(HumanPlusPUserCollection humanPlusPUserCollection) {
        UserDto userDto = UserThreadLocal.get();
        humanPlusPUserCollection.setUpdatedTime(new Date());
        humanPlusPUserCollection.setUpdatedBy(userDto.getId());
        return R.status(collectionService.updateById(humanPlusPUserCollection));
    }

    /**
     * 调用自定义函数,通过传入用户id实现
     * 查看某一用户发的收藏夹所有课程信息
     * 多表查询
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查看某一用户发的收藏夹所有课程信息", notes = "")
    @ApiOperationSupport(order = 3)
    public R<List<CollectionDto>> list() {
        UserDto userDto = UserThreadLocal.get();
        List<CollectionDto> list = collectionService.userList(userDto.getId().toString());
        return R.data(list);
    }

    /**调用IService自带的page实现分页查询
     * 传入一个wrapper 和 一个封装有page类信息的Query
     * @param humanPlusPUserCollection
     * @param query
     * @return
     */
    @GetMapping("/page")
    public R<IPage<HumanPlusPUserCollection>> page(@RequestParam Map<String, Object> humanPlusPUserCollection, Query query) {
        UserDto userDto = UserThreadLocal.get();
        humanPlusPUserCollection.put("userId", userDto.getId().toString());
        IPage<HumanPlusPUserCollection> pages = collectionService.page(Condition.getPage(query), Condition.getQueryWrapper(humanPlusPUserCollection, HumanPlusPUserCollection.class));
        return R.data(pages);

    }
}
