package org.springblade.coach.home;

import org.springblade.common.constant.CommonConstant;
import org.springblade.core.cloud.feign.EnableBladeFeign;
import org.springblade.core.launch.BladeApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author WZH
 * @version 1.0
 * @date 2022/5/10 15:09
 * Home启动器
 */
@EnableBladeFeign
@SpringCloudApplication
/**CLoud版本*/
public class CoachApplication {
    public static void main(String[] args){
        BladeApplication.run(CommonConstant.APPLICATION_Coach_NAME, CoachApplication.class,args);
    }
}
