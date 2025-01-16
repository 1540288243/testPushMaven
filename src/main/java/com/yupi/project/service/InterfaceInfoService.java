package com.yupi.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.project.model.entity.InterfaceInfo;

/**
 * @author LMS
 * @description 针对表【interface_info(接口信息)】的数据库操作Service
 * @createDate 2025-01-10 15:46:21
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
