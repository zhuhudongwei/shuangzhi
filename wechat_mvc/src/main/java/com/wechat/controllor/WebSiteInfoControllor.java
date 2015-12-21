package com.wechat.controllor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wechat.model.Article;
import com.wechat.service.ArticleService;
import com.wechat.utils.DateUtil;

@Controller
@RequestMapping("/website")
public class WebSiteInfoControllor {

	@Autowired
	private ArticleService articleServiceImpl;
	
	/***
	 * 双至后台登陆
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/backendIndex", method = RequestMethod.GET)
	public String backendIndex(Integer expire, Model model)throws Exception{
		Map<String, Object> attrs = new HashMap<String, Object>();
		if(expire == null){
			expire = 0;
		}
		attrs.put("expire", expire);
		model.addAllAttributes(attrs);
		return "/backendLogin"; 
	}
	
	@RequestMapping(value ="/aboutMe", method = RequestMethod.GET)
	public String aboutMe(HttpServletRequest request){
		return "/aboutme";
//		return "/viewContent";
	}
	
	@RequestMapping(value ="/aboutWLY", method = RequestMethod.GET)
	public String aboutWLY(HttpServletRequest request){
		return "/aboutWLY";
//		return "/viewContent";
	}
	
	@RequestMapping(value ="/aboutTTS", method = RequestMethod.GET)
	public String aboutTTS(HttpServletRequest request){
		return "/aboutTTS";
	}
	
	@RequestMapping(value ="/downLoadWLY", method = RequestMethod.GET)
	public String downLoadWLY(HttpServletRequest request){
		return "/downLoadWLY";
	}
	
	@RequestMapping(value ="/downLoadTTS", method = RequestMethod.GET)
	public String downLoadTTS(HttpServletRequest request){
		return "/downLoadTTS";
	}
	
	@RequestMapping(value = "/downLoadPDF", method = RequestMethod.GET)
	public String downLoadPDF(Model model, HttpSession httpSession, HttpServletResponse response)throws Exception{
		Map<String, Object> attrs = new HashMap<String, Object>();
		List<Article> articles = articleServiceImpl.articles();
		for (int i = 0; i < articles.size(); i++) {
			articles.get(i).setCreate_time_str(DateUtil.convertLongToDateString2(articles.get(i).getCreate_time(), "yyyy-MM-dd HH:mm:ss"));
			if(articles.get(i).getArticle_title().length() > 12){
				articles.get(i).setArticle_title_min(articles.get(i).getArticle_title().substring(0, 12)+ "...");
			}else{
				articles.get(i).setArticle_title_min(articles.get(i).getArticle_title());
			}
		}
		model.addAttribute("articles", articles);
		return "/downLoadPDF";
	}
	
	@RequestMapping(value = "/viewArticle", method = RequestMethod.GET)
	public String viewArticle(long article_id, Model model, HttpSession httpSession, HttpServletResponse response)throws Exception{
		Map<String, Object> attrs = new HashMap<String, Object>();
		Article a = articleServiceImpl.getArticle(article_id);
		a.setCreate_time_str(DateUtil.convertLongToDateString(a.getCreate_time(), "yyyy-MM-dd HH:mm:ss"));
		model.addAttribute("article", a);
		return "/viewArticle";
	}
	
	@RequestMapping(value ="/downLoad", method = RequestMethod.GET)
	public void downLoad(String fileName, HttpServletRequest request, HttpServletResponse response){
		String uploadPath = "/usr/local/temp/";//文件来源
		response.setContentType("text/html; charset=GBK");
		try {
			//创建file对象
	        File file=new File(uploadPath + fileName);

	        //设置response的编码方式
	        response.setContentType("application/x-msdownload");

	        //写明要下载的文件的大小
	        response.setContentLength((int)file.length());

	        //设置附加文件名
	       // response.setHeader("Content-Disposition","attachment;filename="+filename);
	        
	        //解决中文乱码
	        response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("gbk"),"iso-8859-1"));       

	        //读出文件到i/o流
	        FileInputStream fis = new FileInputStream(file);
	        BufferedInputStream buff = new BufferedInputStream(fis);

	        byte [] b=new byte[1024];//相当于我们的缓存

	        long k=0;//该值用于计算当前实际下载了多少字节

	        //从response对象中得到输出流,准备下载

	        OutputStream myout = response.getOutputStream();

	        //开始循环下载

	        while(k<file.length()){

	            int j=buff.read(b,0,1024);
	            k+=j;

	            //将b中的数据写到客户端的内存
	            myout.write(b,0,j);

	        }

	        //将文件数据刷新到用户名电脑上
	        myout.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
        
	}
}
