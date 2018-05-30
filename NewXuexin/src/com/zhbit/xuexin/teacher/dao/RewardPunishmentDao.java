package com.zhbit.xuexin.teacher.dao;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.TeaRewardPunishment;

public interface RewardPunishmentDao {

	Page<TeaRewardPunishment> getList(Page<TeaRewardPunishment> page);

}
