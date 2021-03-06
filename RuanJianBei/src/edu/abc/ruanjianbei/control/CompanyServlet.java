package edu.abc.ruanjianbei.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import edu.abc.ruanjianbei.model.bean.ChildrenBean;
import edu.abc.ruanjianbei.model.bean.Graphdata;
import edu.abc.ruanjianbei.model.bean.Links;
import edu.abc.ruanjianbei.model.bean.T_CORPBean;
import edu.abc.ruanjianbei.model.dao.CompanyDao;
import edu.abc.ruanjianbei.model.dao.CompanyDaoImpl;
import edu.abc.ruanjianbei.model.dao.CompanyGuanXiDao;
import edu.abc.ruanjianbei.model.dao.CompanyGuanXiDaoImpl;

@WebServlet("/CompanyServlet")
public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CompanyDao companydao;
    private CompanyGuanXiDao companyguanxi;
    public CompanyServlet() {
        super();
        companydao=new CompanyDaoImpl();
        companyguanxi=new CompanyGuanXiDaoImpl();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String methodName = request.getParameter("method");
		System.out.println("CompanyServlet:"+methodName);
		switch (methodName) {
		case "searchAll":
			searchAll(request,response);
			break;
		case "searchName":
			searchName(request,response);
			break;
		case "touzizupu":
			touzizupu(request,response);
			break;
		case "corpzupu":
			corpzupu(request,response);
			break;
		case "yisiguanxi":
			yisiguanxi(request,response);
			break;
		default:
			break;
		}
	}
	/*
	 * 根据输入的公司名查询公司表信息
	 */
	protected void searchAll(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String name = request.getParameter("CorpName");
		T_CORPBean companymeg=companydao.searchOneCompany(name);
		request.setAttribute("companymeg", companymeg);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	/*
	 * 搜索框公司名模糊匹配
	 */
	protected void searchName(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String name = request.getParameter("CorpName");
		ArrayList<T_CORPBean> companys = companydao.selectByCompanyName(name);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(companys);
		
		//返回jsonArray数据
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
		response.getWriter().print(jsonString);
	}
	/*
	 * 显示投资族谱中的投资信息
	 */
	protected void touzizupu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String CORP_NAME = request.getParameter("CORP_NAME");
		System.out.println("投资族谱查询公司名称："+CORP_NAME);
		String companyLevel=request.getParameter("companyLevel");
		String stockLevel=request.getParameter("stockLevel");
		System.out.println("股东层级："+stockLevel);
		System.out.println("对外投资层级："+companyLevel);

		//存储查询到的所有数据
		ArrayList<ChildrenBean> allData=new ArrayList<>();
		//回馈前端页面，是否还有层级关系
		ChildrenBean flagcom=new ChildrenBean();
		
		//添加节点数据
		ArrayList<ChildrenBean> node=levelNode(CORP_NAME);
		allData.addAll(node);
		
		//查询个人股东层级信息
		ArrayList<ChildrenBean> firstStockLevelData=new ArrayList<>();
		//存储每层查出来的个人股东数据
		ArrayList<ChildrenBean> stockData=new ArrayList<>();
		firstStockLevelData=companydao.selectGuDongRenByCompanyName("股东",CORP_NAME);
		System.out.println("&&&&"+firstStockLevelData);
		stockData=searchStock(firstStockLevelData,CORP_NAME, firstStockLevelData, Integer.parseInt(stockLevel));
		allData.addAll(stockData);
		
		//查询公司股东层级信息
		ArrayList<ChildrenBean> firstCompayLevelData=new ArrayList<>();
		//存储每层查出来的公司股东数据
		ArrayList<ChildrenBean> companyData=new ArrayList<>();
		firstCompayLevelData =companydao.selectGuDongComByCompanyName("对外投资",CORP_NAME);
		System.out.println("^^^^"+firstCompayLevelData);
		companyData=searchCompany(firstCompayLevelData,firstCompayLevelData, Integer.parseInt(companyLevel));
		allData.addAll(companyData);
		ToJSONString(response, allData);
		
	}

	/*
	 * 添加投资族谱节点信息
	 */
	public ArrayList<ChildrenBean> levelNode(String CORP_NAME) {
		ArrayList<ChildrenBean> node=new ArrayList<>();
		ChildrenBean root=new ChildrenBean(CORP_NAME, "", "");
		ChildrenBean root1=new ChildrenBean("股东", CORP_NAME, "");
		ChildrenBean root2=new ChildrenBean("对外投资", CORP_NAME, "");
		node.add(root);
		node.add(root1);
		node.add(root2);
		return node;
	}
	/*
	 *添加企业族谱节点信息 
	 */
	public ArrayList<ChildrenBean> corpLevelNode(String CORP_NAME){
		ArrayList<ChildrenBean> node=new ArrayList<>();
		ChildrenBean root=new ChildrenBean(CORP_NAME, "", "");
		ChildrenBean root1=new ChildrenBean("股东", CORP_NAME, "");
		ChildrenBean root2=new ChildrenBean("对外投资", CORP_NAME, "");
		ChildrenBean root3=new ChildrenBean("高管", CORP_NAME, "");
		ChildrenBean root4=new ChildrenBean("裁判文书", CORP_NAME, "");
		ChildrenBean root5=new ChildrenBean("法院公告", CORP_NAME, "");
		ChildrenBean root6=new ChildrenBean("历史股东", CORP_NAME, "");
		ChildrenBean root7=new ChildrenBean("历史法人", CORP_NAME, "");
		node.add(root);
		node.add(root1);
		node.add(root2);
		node.add(root3);
		node.add(root4);
		node.add(root5);
		node.add(root6);
		node.add(root7);
		return node;
	}
	/*
	 * 将查询到的数据转化为json格式返回前台
	 */
	protected void ToJSONString(HttpServletResponse response, ArrayList<ChildrenBean> stockData) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ObjectMapper mapper=new ObjectMapper();
		String jsonString =mapper.writeValueAsString(stockData);
		System.out.println("查询出的json数据"+jsonString);
		//返回jsonArray数据
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
		response.getWriter().print(jsonString);
		
	}
	/*
	 * 显示企业族谱信息
	 */
	protected void corpzupu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String CORP_ORG = request.getParameter("CORP_ORG");
		String CORP_SEQ_ID = request.getParameter("CORP_SEQ_ID");
		System.out.println("企业族谱："+CORP_ORG+","+CORP_SEQ_ID);
		String CORP_NAME = request.getParameter("CORP_NAME");
		System.out.println("企业族谱公司名："+CORP_NAME);
		//存储查询出来的所有信息
		ArrayList<ChildrenBean> allData=new ArrayList<>();
		
		
		ArrayList<ChildrenBean> CompayLevelData=companydao.selectGuDongComByCompany("对外投资", CORP_NAME, Integer.parseInt(CORP_ORG), Integer.parseInt(CORP_SEQ_ID));
		ArrayList<ChildrenBean> StockLevelData=companydao.selectGuDongRenByCompany("股东", CORP_NAME, Integer.parseInt(CORP_ORG), Integer.parseInt(CORP_SEQ_ID));
		
		System.out.println("第一层股东数据"+StockLevelData);
		
		allData.addAll(searchStock(StockLevelData,CORP_NAME,StockLevelData,2));
		allData.addAll(searchCompany(CompayLevelData,CompayLevelData,3));
		//添加节点信息
		allData.addAll(corpLevelNode(CORP_NAME));
		
		ToJSONString(response, allData);
	}
	/*
	 * 通过递归查询个人股东层级数据
	 */
	public ArrayList<ChildrenBean> searchStock(ArrayList<ChildrenBean> stockLevelData,String CORP_NAME,ArrayList<ChildrenBean> leveldata,int level){
		//存储一层的信息
		ArrayList<ChildrenBean> levelData=new ArrayList<>();
		//存储一层内单个信息
		ArrayList<ChildrenBean> levelDataOne=new ArrayList<>();
		if(level<=0) {
			System.out.println("递归结束!");
		}else {
			for (ChildrenBean childrenBean : leveldata) {
				levelDataOne=companydao.selectGuDongRenByCompanyName(CORP_NAME,childrenBean.getId());
				levelData.addAll(levelDataOne);
			}
			stockLevelData.addAll(levelData);
			searchStock(stockLevelData,CORP_NAME,levelData, (level-1));
		}
		return stockLevelData;
	}
	/*
	 * 通过递归查询公司股东层级数据
	 */
	public ArrayList<ChildrenBean> searchCompany(ArrayList<ChildrenBean> companyLevelData,ArrayList<ChildrenBean> leveldata,int level){
		ArrayList<ChildrenBean> levelData=new ArrayList<>();
		ArrayList<ChildrenBean> levelDataOne=new ArrayList<>();
		//当递归到第二层结束
		if(level<=0) {
			System.out.println("递归结束!");
		}else {
			for (ChildrenBean childrenBean : leveldata) {
				levelDataOne=companydao.selectGuDongComByCompanyName(childrenBean.getId(), childrenBean.getId());
				levelData.addAll(levelDataOne);
			}
			companyLevelData.addAll(levelData);
			searchCompany(companyLevelData,levelData,(level-1));
		}
		
		return companyLevelData;
	}
	
	/*
	 * 显示公司，股东之间的疑似关系
	 */
	protected void yisiguanxi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String CORP_NAME = request.getParameter("CORP_NAME");
		System.out.println("疑似关系主公司："+CORP_NAME);
		ArrayList<Links> graphlinks=new ArrayList<>();
		ArrayList<Links> GuDongTouZi=companyguanxi.selectTouZiOutside(CORP_NAME);
		ArrayList<Links> GuDongRenZhi=companyguanxi.selectGuDongRenZhiOutside(CORP_NAME);
		ArrayList<Links> GaoGuanRenZhi=companyguanxi.selectGaoGuanRenZhiOutside(CORP_NAME);
		System.out.println("GuDongTouZi "+GuDongTouZi);
		System.out.println("GuDongRenZhi "+GuDongRenZhi);
		System.out.println("GaoGuanRenZhi "+GaoGuanRenZhi);
		graphlinks.addAll(GuDongTouZi);
		graphlinks.addAll(GuDongRenZhi);
		graphlinks.addAll(GaoGuanRenZhi);
		HashSet<Graphdata> TouZiGuDong=new HashSet<>();
		HashSet<Graphdata> RenZhiGuDong=new HashSet<>();
		HashSet<Graphdata> RenZhiGaoGuan=new HashSet<>();
		for (Links link : GuDongTouZi) {
			String source=link.getSource();
			String target=link.getTarget();
			Graphdata gudong=new Graphdata(source, 0, "true");
			Graphdata gongsi=new Graphdata(target, 1, "true");
			TouZiGuDong.add(gudong);
			RenZhiGuDong.add(gongsi);
		}
		for (Links link : GuDongRenZhi) {
			String source=link.getSource();
			String target=link.getTarget();
			Graphdata gudong=new Graphdata(source, 0, "true");
			Graphdata gongsi=new Graphdata(target, 1, "true");
			TouZiGuDong.add(gudong);
			RenZhiGuDong.add(gongsi);
		}
		for (Links link : GaoGuanRenZhi) {
			String source=link.getSource();
			String target=link.getTarget();
			Graphdata gaoguan=new Graphdata(source, 2, "true");
			Graphdata gongsi=new Graphdata(target, 1, "true");
			RenZhiGaoGuan.add(gaoguan);
			RenZhiGuDong.add(gongsi);
		}
		System.out.println("%%% "+TouZiGuDong);
		System.out.println("%%% "+RenZhiGuDong);
		System.out.println("%%% "+RenZhiGaoGuan);
		
		TouZiGuDong.addAll(RenZhiGuDong);
		TouZiGuDong.addAll(RenZhiGaoGuan);
		
		Object[] alldata= {graphlinks,TouZiGuDong};
		ObjectMapper mapper=new ObjectMapper();
		String jsonString =mapper.writeValueAsString(alldata);
		System.out.println("查询出的json数据"+jsonString);
		//返回jsonArray数据
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
		response.getWriter().print(jsonString);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
