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
package run.mone.chaos.operator.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author caobaoyu
 * @description:
 * @date 2024-04-15 16:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChaosTaskInfoVO implements Serializable {

    /**
     * 实验id
     */
    private String id;

    /**
     * 实验名称
     */
    private String experimentName;

    private String projectName;

    private String pipLineId;

    private Integer type;

    private Integer mode;

    private Integer status;

    private String creator;

    private Long createTime;

    private Integer executedTimes;

    private Long updateTime;

    private String updateUser;

    private Long duration;

}