package Main.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import Main.service.CategoryService;
import Main.service.ProductReviewsService;

@Component
public class GlobalInterceptor implements HandlerInterceptor{
	@Autowired
	CategoryService cateSV;
	@Autowired
	ProductReviewsService prodreSV;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("cates", cateSV.findAll());
	}
}
