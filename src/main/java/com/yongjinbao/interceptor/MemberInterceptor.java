package com.yongjinbao.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yongjinbao.beans.Principal;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.utils.RedisUtils;
import com.yongjinbao.utils.SerializeUtil;
import com.yongjinbao.utils.WebUtils;

/**
 * Interceptor - 会员权限
 * 
 */
public class MemberInterceptor extends HandlerInterceptorAdapter {

	/** 重定向视图名称前缀 */
	private static final String REDIRECT_VIEW_NAME_PREFIX = "redirect:";

	/** "重定向URL"参数名称 */
	private static final String REDIRECT_URL_PARAMETER_NAME = "redirectUrl";

	/** "会员"属性名称 */
	private static final String MEMBER_ATTRIBUTE_NAME = "member";

	/** 默认登录URL */
	private static final String DEFAULT_LOGIN_URL = "/common/error";

	/** 登录URL */
	private String loginUrl = DEFAULT_LOGIN_URL;

	@Value("${url_escaping_charset}")
	private String urlEscapingCharset;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.1、判断路径是否包含auth标志，包含则进行登入拦截
		if(request.getRequestURI().contains("auth")){
            //1.2、获取客户端token,并且判断redis中是否存在。
            String token = WebUtils.getCookie(request, Member.USERNAME_COOKIE_NAME);
            byte[] bs = RedisUtils.get(SerializeUtil.serialize(token));
            Principal principal = (Principal) SerializeUtil.unserialize(bs);
            response.setContentType("application/json; charset=utf-8");
            if (principal != null) {
                //1.2.1、从新生成token看是否和客户端保存的token相等来进行验证是否登入。
                String remoteHost = request.getRemoteAddr();
                String id = principal.getId()+"";
                String memberName = principal.getUsername();
                String reToken = DigestUtils.md5Hex(remoteHost + id + memberName);
                if(reToken.equals(token)) {
                    return true;
                }else {
                   //1.2.2、不相等，非法操作；
                    response.getWriter().append("{\"type\":\"401\",\"content\":\"登录过期\"}");
                    return false;
                }
            } else {
                //1.2.3、不存在token,登入，返回信息；
                response.getWriter().append("{\"type\":\"401\",\"content\":\"未登录\"}");
                return false;
            }
		}
        //2、无需拦截功能，直接访问；
        return true;

    }

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			String viewName = modelAndView.getViewName();
			if (!StringUtils.startsWith(viewName, REDIRECT_VIEW_NAME_PREFIX)) {
				//TODO
				//是相当于 request.setAttribute("",对象参数)。基本没区别
				//modelAndView.addObject(MEMBER_ATTRIBUTE_NAME, memberService.getCurrent());
			}
		}
	}

	/**
	 * 获取登录URL
	 * 
	 * @return 登录URL
	 */
	public String getLoginUrl() {
		return loginUrl;
	}

	/**
	 * 设置登录URL
	 * 
	 * @param loginUrl
	 *            登录URL
	 */
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

}