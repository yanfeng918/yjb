package com.yongjinbao.customerinfo.controller;

import com.yongjinbao.beans.Message;
import com.yongjinbao.customerinfo.dto.GetCustomerInfoDto;
import com.yongjinbao.customerinfo.entity.CustomerInfo;
import com.yongjinbao.customerinfo.service.ICustomerInfoService;
import com.yongjinbao.mybatis.entity.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * Created by fddxiaohui on 2015/8/24.
 */
@Controller
@RequestMapping("/customerInfo")
public class CustomerInfoController {
    @Inject
    private ICustomerInfoService customerInfoService;

    /**
     * 查询求房源人信息列表
     * @param getCustomerInfoDto
     * @return
     */
    @RequestMapping(value = "/getCustomerInfoList",method = RequestMethod.POST)
    @ResponseBody
    public Pager<CustomerInfo> getCustomerInfo(@ModelAttribute("getCustomerInfoDto") GetCustomerInfoDto getCustomerInfoDto){
        Pager<CustomerInfo> pager=customerInfoService.getCustomerInfo(getCustomerInfoDto);
        return pager;
    }

    /**
     * 新增求房源人信息
     * @param customerInfo
     * @return
     */
    @RequestMapping(value = "/addCustomerInfo",method = RequestMethod.POST)
    @ResponseBody
    public Message addCustomerInfo(@ModelAttribute("customerInfo") CustomerInfo customerInfo){
        try {
            customerInfoService.add(customerInfo);
        }catch (Exception e){
            return new Message(Message.Type.error,"添加用户信息失败");
        }
        return new Message(Message.Type.success,"添加用户信息成功");
    }

    /**
     * 修改求房源人信息
     * @param customerInfo
     * @return
     */
    @RequestMapping(value = "/updateCustomerInfo",method = RequestMethod.POST)
    @ResponseBody
    public Message updateCustomerInfo(@ModelAttribute("customerInfo") CustomerInfo customerInfo){
        try{
            customerInfoService.update(customerInfo);
        }catch (Exception e){
            return new Message(Message.Type.error,"更新用户信息失败");
        }
        return new Message(Message.Type.success,"更新用户信息成功");
    }
}
