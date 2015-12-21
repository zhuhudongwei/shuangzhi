package com.wechat.controllor;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wechat.model.Article;
import com.wechat.model.WechatCustomer;
import com.wechat.model.WechatCustomerResponse;
import com.wechat.service.ArticleService;
import com.wechat.service.WechatCustomerService;
import com.wechat.utils.Constants;
import com.wechat.utils.DataPage;
import com.wechat.utils.DateUtil;

/***
 * 双至后台
 * @author zdw
 *
 */
@Controller
@RequestMapping("/backend")
public class BackendControllor {
	
	@Autowired
	private WechatCustomerService wechatCustomerServiceImpl;
	
	@Autowired
	private WechatCustomerService wechatCustomerService;
	
	@Autowired
	private ArticleService articleServiceImpl;
	
	@RequestMapping(value = "/shuangzhiLogin", method = RequestMethod.POST)
	public String shuangzhiLogin(String loginName, String password, Model model, HttpSession httpSession, HttpServletResponse response)throws Exception{
		Map<String, Object> attrs = new HashMap<String, Object>();
		int expire = 3;
		if(loginName != null && password != null){
			if(loginName.trim().length() != 0 && password.trim().length() != 0){
				if(loginName.equals("paireach") && password.equals("xyz000")){
					expire = 0;
				}
			}
		}
		if(expire == 3){
			attrs.put("expire", expire);
			model.addAllAttributes(attrs);
			return "redirect:/website/backendIndex";
		}
		return "redirect:/backend/listArticles";
	}

	/***
	 * 客服后台
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listWechatCustomer", method = RequestMethod.GET)
	public String listWechatCustomer(Model model, HttpServletRequest request)throws Exception{
		Map<String, Object> attrs = new HashMap<String, Object>();
		List<WechatCustomer> wcs = wechatCustomerServiceImpl.listWechatCustomerAll();
		List<WechatCustomer> newwcs = new ArrayList<WechatCustomer>();
		for (int i = 0; i < wcs.size(); i++) {
			wcs.get(i).setCreate_time_str(DateUtil.convertLongToDateString2(wcs.get(i).getCreate_time(), "yyyy-MM-dd HH:mm:ss"));
			if(wcs.get(i).getWcrs().size() == 0){
				newwcs.add(wcs.get(i));
			}
		}
		model.addAttribute("wcs", newwcs);
		return "/listWechatCustomer";
	}
	
	/**
	 * 跳转至回复页面
	 * @param customer_id
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/openToResponse", method = RequestMethod.GET)
	public String openToResponse(long customer_id,Model model, HttpServletRequest request)throws Exception{
		Map<String, Object> attrs = new HashMap<String, Object>();
		WechatCustomer wc = wechatCustomerServiceImpl.getWechatCustomer(customer_id);
		//列出聊天记录
		List<WechatCustomer> wcList = wechatCustomerServiceImpl.listWechatCustomerByOpenId(wc.getOpen_id());
		if(wcList != null){
			for (WechatCustomer wechatCustomer : wcList) {
				List<WechatCustomerResponse> wcResponseList = 
						wechatCustomerServiceImpl.listWechatCustomerResponseByCustomerId(wechatCustomer.getCustomer_id());
				if(wcResponseList != null) {
					for (WechatCustomerResponse wechatCustomerResponse : wcResponseList) {
						wechatCustomerResponse.setResponse_time_str(DateUtil.convertLongToDateString2(
								wechatCustomerResponse.getResponse_time(), "yyyy-MM-dd HH:mm:ss"));
					}
					wechatCustomer.setWcrs(wcResponseList);
				}
				wechatCustomer.setCreate_time_str(DateUtil.convertLongToDateString2(
						wechatCustomer.getCreate_time(), "yyyy-MM-dd HH:mm:ss"));
			}
		}
		wc.setCreate_time_str(DateUtil.convertLongToDateString2(wc.getCreate_time(), "yyyy-MM-dd HH:mm:ss"));
		model.addAttribute("wc", wc);
		model.addAttribute("wcList", wcList);
		return "/addResponse";
	}
	
	/**
	 * 回复提问
	 * @param customer_id
	 * @param question
	 * @param httpSession
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/responseMsg", method = RequestMethod.POST)
	public String responseMsg(long customer_id, String question, HttpSession httpSession, HttpServletResponse response)throws Exception{
		WechatCustomerResponse wcr = new WechatCustomerResponse();
    	wcr.setCustomer_id(customer_id);
    	wcr.setResponse_content(question);
    	wcr.setResponse_name("系统客服");
    	wcr.setIssend(WechatCustomerResponse.RESPONSE_ISSEND_NO);
    	wcr.setResponse_time(System.currentTimeMillis());
    	wechatCustomerServiceImpl.addWechatCustomerResponse(wcr);
    	wechatCustomerServiceImpl.updateWechatCustomerByState(customer_id);
		return "redirect:/backend/listWechatCustomer";
	}
	
	/***
	 * 文章后台
	 * @param model
	 * @param httpSession
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listArticles", method = RequestMethod.GET)
	public String listArticles(Model model, HttpSession httpSession, HttpServletResponse response)throws Exception{
		Map<String, Object> attrs = new HashMap<String, Object>();
		List<Article> articles = articleServiceImpl.articles();
		for (int i = 0; i < articles.size(); i++) {
			articles.get(i).setCreate_time_str(DateUtil.convertLongToDateString2(articles.get(i).getCreate_time(), "yyyy-MM-dd HH:mm:ss"));
		}
		model.addAttribute("articles", articles);
		return "/listArticles";
	}
	
	/***
	 * 跳转至编辑文章
	 * @param article_id
	 * @param model
	 * @param httpSession
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/openToEditArticle", method = RequestMethod.GET)
	public String openToEditArticle(Long article_id, Model model, HttpSession httpSession, HttpServletResponse response)throws Exception{
		Map<String, Object> attrs = new HashMap<String, Object>();
		if(article_id != null){
			Article a = articleServiceImpl.getArticle(article_id);
			a.setCreate_time_str(DateUtil.convertLongToDateString2(a.getCreate_time(), "yyyy-MM-dd HH:mm:ss"));
			model.addAttribute("article", a);
			return "/editArticle";
		}
		return "/addArticle";
	}
	
	/**
	 * 删除文章
	 * @param article_id
	 * @param model
	 * @param httpSession
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delArticle", method = RequestMethod.GET)
	public String delArticle(long article_id, Model model, HttpSession httpSession, HttpServletResponse response)throws Exception{
		articleServiceImpl.delArticle(article_id);
		return "redirect:/backend/listArticles";
	}
	
	/**
	 * 增加文章
	 * @param article_title
	 * @param article_content
	 * @param httpSession
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addArticle", method = RequestMethod.POST)
	public String addArticle(String article_title, String article_content, HttpSession httpSession, HttpServletResponse response)throws Exception{
		Article a = new Article();
		a.setArticle_title(article_title);
		a.setArticle_content(article_content);
		a.setCreate_time(System.currentTimeMillis());
		a.setCteate_name("paireach");
		articleServiceImpl.addArticle(a);
		return "redirect:/backend/listArticles";
	}
	
	/**
	 * 修改文章
	 * @param article_id
	 * @param article_title
	 * @param article_content
	 * @param httpSession
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateArticle", method = RequestMethod.POST)
	public String updateArticle(long article_id, String article_title, String article_content, HttpSession httpSession, HttpServletResponse response)throws Exception{
		Article a = new Article();
		a.setArticle_id(article_id);
		a.setArticle_title(article_title);
		a.setArticle_content(article_content);
		a.setCreate_time(System.currentTimeMillis());
		a.setCteate_name("paireach");
		articleServiceImpl.updateArticle(a);
		return "redirect:/backend/listArticles";
	}
	
	/**
	 * 异步上传文章图片
	 * @param httpSession
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="uploadImage", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public void uploadImage(HttpSession httpSession, HttpServletResponse response, HttpServletRequest request)throws Exception{
		List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("imgFile");
		String path = "";
		String fileName = "";
		if(null != files){
			fileName = files.get(0).getOriginalFilename();
			path = System.currentTimeMillis()+"";
			this.addFile(files.get(0), path, fileName);
		}else{
			throw new Exception();
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("error", 0);
		jsonObject.put("url",Constants.image_url + java.io.File.separator + path + java.io.File.separator + fileName);
		response.getWriter().write(jsonObject.toString());
	}
	
	/**
	 * 图片处理
	 * @param mfile
	 * @param filePath
	 * @param fileName
	 * @throws Exception
	 */
	private void addFile(MultipartFile mfile, String filePath, String fileName) throws Exception {
		if(null != mfile){
			java.io.File parent = new java.io.File(Constants.file_root + java.io.File.separator + filePath);
			if(!parent.exists()){
				parent.mkdirs();
			}
			java.io.File sub = new java.io.File(parent, fileName);
			mfile.transferTo(sub);
		}
	}

}
