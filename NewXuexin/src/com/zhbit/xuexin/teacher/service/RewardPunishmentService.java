package com.zhbit.xuexin.teacher.service;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.TeaRewardPunishment;

public interface RewardPunishmentService {


	Page<TeaRewardPunishment> getList(Page<TeaRewardPunishment> page);

	public int save(TeaRewardPunishment info, String userId, String happenedDateStr,
			String rewardDateStr, Page<TeaRewardPunishment> page)throws Exception;
}
