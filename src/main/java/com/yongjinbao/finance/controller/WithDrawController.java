package com.yongjinbao.finance.controller;

import com.yongjinbao.beans.Message;
import com.yongjinbao.finance.dto.GetFrozenDetailListDto;
import com.yongjinbao.finance.dto.GetFrozenWithDrawListDto;
import com.yongjinbao.finance.dto.GetWithDrawDto;
import com.yongjinbao.finance.entity.BankAccount;
import com.yongjinbao.finance.entity.WithDraw;
import com.yongjinbao.finance.service.IIncomeService;
import com.yongjinbao.finance.service.IWithDrawService;
import com.yongjinbao.finance.vo.FrozenDetailVO;
import com.yongjinbao.finance.vo.IncomeAmountVO;
import com.yongjinbao.finance.vo.WithDrawVO;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.member.service.IMemberService;
import com.yongjinbao.mybatis.entity.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by fddxiaohui on 2015/8/24.
 */
@Controller
@RequestMapping("/withDraw/auth")
public class WithDrawController {
	
    @Inject
    private IWithDrawService withDrawService;
 	@Inject
    private IMemberService memberService;
 	
 	@Inject
	private IIncomeService incomeService;
 	
 	/**
     * 获取提现列表信息
     * @param getWithDrawDto
     * @return
     */
    @RequestMapping(value = "/getWithDrawList",method = RequestMethod.POST)
    @ResponseBody
    public Pager<WithDrawVO> getWithDrawList(@ModelAttribute("getWithDrawDto") GetWithDrawDto getWithDrawDto,HttpServletRequest request){
        Long id=withDrawService.getMemberId(request);
        getWithDrawDto.setMember_id(id);
        Pager<WithDrawVO> pager = withDrawService.getWithDrawList(getWithDrawDto);
        return pager;
    }
    /**
     * 获取冻结资金列表信息
     * @param getFrozenWithDrawListDto
     * @return
     */
    @RequestMapping(value = "/getFrozenWithDrawList",method = RequestMethod.POST)
    @ResponseBody
    public Pager<WithDraw> getFrozenWithDrawList(@ModelAttribute("getFrozenWithDrawListDto") GetFrozenWithDrawListDto getFrozenWithDrawListDto,HttpServletRequest request){
        Long id=withDrawService.getMemberId(request);
        getFrozenWithDrawListDto.setMember_id(id);
        getFrozenWithDrawListDto.setStatus(WithDraw.WithDraw_STATUS.APPLY);
        Pager<WithDraw> pager = withDrawService.getFrozenWithDrawList(getFrozenWithDrawListDto);
        return pager;
    }
    
    /**
     * 获取冻结资金列表信息
     * 2015年10月7日 冻结列表新增征集冻结
     * @param getFrozenWithDrawListDto
     * @return
     */
    @RequestMapping(value = "/getFrozenDetailList",method = RequestMethod.POST)
    @ResponseBody
    public Pager<FrozenDetailVO> getFrozenDetailList( GetFrozenDetailListDto dto,HttpServletRequest request){
        Long id=withDrawService.getMemberId(request);
        dto.setMember_id(id);
        Pager<FrozenDetailVO> pager = withDrawService.getFrozenDetailList(dto);
        return pager;
    }
    
    /**
     * 新增提现记录
     */
    @RequestMapping(value = "/addWithDraw",method = RequestMethod.POST)
    @ResponseBody
    public Message addWithDraw(@ModelAttribute("withDraw") WithDraw withDraw,String bankAccount_id,float frozenAmount, HttpServletRequest request){
        try {
            Long id=withDrawService.getMemberId(request);
            Member member=memberService.load(id);
            BankAccount bankAccount=new BankAccount();
            bankAccount.setId(Long.parseLong(bankAccount_id));
            if(withDraw.getAmount()<50){
                return new Message(Message.Type.error,"最低提现50元！");
            }
            if(getFrozenAmount(member)!=frozenAmount){
            	 return new Message(Message.Type.error,"检测到非法操作。请勿改动冻结资金参数！");
            }
            if((member.getBalance()-frozenAmount)<withDraw.getAmount()){
            	if (member.getBalance()>withDraw.getAmount()&&frozenAmount>0) {
            		return new Message(Message.Type.error,"当前余额减去冻结余额后，已无法提现！请等待相关收入24小时解冻！");
				}
                return new Message(Message.Type.error,"余额不足");
            }
            withDraw.setMember(member);
            withDraw.setStatus(WithDraw.WithDraw_STATUS.APPLY);
            withDraw.setBankAccount(bankAccount);
            withDrawService.add(withDraw);
            Member member1=new Member();
            member1.setId(id);
            member1.setBalance(member.getBalance()-withDraw.getAmount());
            memberService.update(member1);
            return new Message(Message.Type.success,"提现申请已提交");
        }catch (Exception e){
        	e.printStackTrace();
            return new Message(Message.Type.error,"提交异常");
        }
    }
    
    @RequestMapping(value = "/getTotalWithdrawAmount",method = RequestMethod.GET)
    @ResponseBody
    public float getTotalWithdrawAmount(HttpServletRequest request){
    	Long memer_id=memberService.getMemberId(request);
    	if (memer_id!=null) {
    		return withDrawService.getTotalWithdrawAmount(memer_id);
		}else{
			return 0f;
		}
    }
    
    private float getFrozenAmount(Member member){
		IncomeAmountVO vo = incomeService.getIncomeAmountByDate(member.getId());
		vo.setBalance(member.getBalance());
		if (vo.getYesterdaySum()>vo.getBalance()) {
			vo.setYesterdaySum(vo.getBalance());
		}
		return vo.getYesterdaySum();
	}
}
