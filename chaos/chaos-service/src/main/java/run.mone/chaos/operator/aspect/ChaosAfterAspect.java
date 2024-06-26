/*
 * Copyright 2020 Xiaomi
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package run.mone.chaos.operator.aspect;

import com.xiaomi.youpin.docean.aop.ProceedingJoinPoint;
import com.xiaomi.youpin.docean.aop.anno.After;
import com.xiaomi.youpin.docean.aop.anno.Aspect;
import lombok.extern.slf4j.Slf4j;
import run.mone.chaos.operator.aspect.anno.ChaosAfter;
import run.mone.chaos.operator.bo.CreateChaosTaskBo;
import run.mone.chaos.operator.common.Config;
import run.mone.chaos.operator.service.RedisCacheService;

import javax.annotation.Resource;

@Aspect
@Slf4j
public class ChaosAfterAspect {

    @Resource
    private RedisCacheService cache;
    @After(anno = ChaosAfter.class)
    public Object after(ProceedingJoinPoint joinPoint) {
        // TODO: 执行完成后解锁
        log.info("after!");
        //chaosReentrantUnLock(args);
        return joinPoint.getRes();
    }

    private void chaosReentrantUnLock(Object[] args) {
        String isReentrantLock = Config.ins().get("enableChaosReentrantLock", "false");
        if (isReentrantLock.equals("true")) {
            for (Object arg : args) {
                if (arg instanceof CreateChaosTaskBo pipelineBO) {
                    Integer pipelineId = pipelineBO.getPipelineId();
                    Long duration = pipelineBO.getDuration();
                    Integer projectId = pipelineBO.getProjectId();
                    StringBuilder sb = new StringBuilder();
                    String key = sb.append(projectId).append("-").append(pipelineId).toString();
                    log.info("begin unlock key:{}",key);
                    if (cache.unlock(key)) {
                        throw new IllegalArgumentException("该实验已结束或未进行过实验！");
                    }
                }
            }
        }
    }

}