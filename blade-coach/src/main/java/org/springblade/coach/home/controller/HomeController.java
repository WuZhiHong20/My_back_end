package org.springblade.coach.home.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.coach.home.entity.HumanPlusPCource;
import org.springblade.coach.home.entity.pojo.CourceDto;
import org.springblade.coach.home.service.HumanPlusCourceService;
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
@RequestMapping("cource")
@AllArgsConstructor
public class HomeController {
    private HumanPlusCourceService humanPlusCourceService;

    /**
     * 操作表“human_plus_p_cource”，
     * 新增课程
     * @param humanPlusPCource
     * @return
     */
    @PostMapping("/save")
    public R save(@RequestBody HumanPlusPCource humanPlusPCource){
        humanPlusPCource.setCreatedTime(new Date());
        return R.status(humanPlusCourceService.save(humanPlusPCource));
    }

    /**
     * 传入要修改的课程信息，通过id修改
     * @param humanPlusPCource
     * @return R
     */
    @PostMapping("/update")
    public R update(@RequestBody HumanPlusPCource humanPlusPCource) {
        humanPlusPCource.setUpdatedTime(new Date());
        return R.status(humanPlusCourceService.updateById(humanPlusPCource));
    }

    /**
     *通过id删除课程
     * @param id
     * @return R
     */
    @PostMapping("/remove")
    public R remove(@RequestParam String id) {
        return R.status(humanPlusCourceService.removeByIds(Func.toLongList(id)));
    }

    /**
     *传入课程Id，查询该课程的信息及绑定任务的信息
     * @param id
     * @return R
     */
    @GetMapping("/detail")
    public R<CourceDto> detail(String id) {
        CourceDto detail = humanPlusCourceService.cource_detail(id);
        //System.out.println(detail.toString());
        //System.out.println("tasks");
        return R.data(detail);
    }

    /**操作表“human_plus_p_cource”，
     *查询全部课程
     * @return
     */
    @GetMapping("/list")
    public R<List<HumanPlusPCource>> list() {
        List<HumanPlusPCource> list = humanPlusCourceService.list();
        return R.data(list);
    }

    /**
     * 模糊查询
     *单表查询，通过wrapper查询表“human_plus_p_cource”
     * humanpluscource这个参数在传进来时就有一堆模糊查询的键值对
     * 例如：{"courceName":"跑步"}
     * @param humanpluscource
     * @return
     */
    @GetMapping("/info_list")
    public R<List<HumanPlusPCource>> list(@RequestParam Map<String, Object> humanpluscource) {
        List<HumanPlusPCource> list =
                humanPlusCourceService.list
                        (Condition.getQueryWrapper
                                (humanpluscource,HumanPlusPCource.class).
                                lambda().orderByDesc(HumanPlusPCource::getCreatedTime));
        return R.data(list);
    }

    /**
     *调用IService自带的page实现分页查询
     * 传入一个wrapper 和 一个封装有page信息的Query
     * page类：
     *  protected List<T> records;
     *     protected long total;
     *     protected long size;
     *     protected long current;
     *     protected List<OrderItem> orders;
     *     protected boolean optimizeCountSql;
     *     protected boolean isSearchCount;
     *     protected boolean hitCount;
     *     protected String countId;
     *     protected Long maxLimit;
     * @param humanpluscource
     * @param query
     * @return
     */
    @GetMapping("/page")
    public R<IPage<HumanPlusPCource>> page(@ApiIgnore @RequestParam Map<String, Object> humanpluscource, Query query) {
        /**
         *page函数传入两个参数，
         * <E extends IPage<T>> E page(E page, Wrapper<T> queryWrapper)
         * public static <T> IPage<T> getPage(Query query)，getPage返回一个IPage类
         */
        IPage<HumanPlusPCource> pages = humanPlusCourceService.page(Condition.getPage(query), Condition.getQueryWrapper(humanpluscource, HumanPlusPCource.class));
        return R.data(pages);
    }

}
