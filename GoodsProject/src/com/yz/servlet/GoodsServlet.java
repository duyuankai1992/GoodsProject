package com.yz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yz.dao.impl.GoodsDAOImpl;
import com.yz.entity.Goods;

public class GoodsServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 接收请求URI
		String uri = request.getRequestURI();//   /GoodsProject/select.do
		System.out.println("---->"+uri);
		String name = uri.substring(uri.lastIndexOf("/") + 1);
		if ("add.do".equals(name)) {

			add(request, response);
		} else if ("select.do".equals(name)) {
			select(request, response);
		}else if("delete.do".equals(name))
		{
			delete(request,response);
		}else if("update.do".equals(name))
		{
			update(request,response);
		}else if("updateData.do".equals(name))
		{
			updateData(request,response);
		}
	}
	/**
	 * 更新商品数据部分
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateData(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//取出值
		String goodsName=request.getParameter("goodsName");
		String price=request.getParameter("goodsPrice");
		String number=request.getParameter("goodsNumber");
		String id=request.getParameter("id");
		System.out.println("--->id:"+id+",goodsName:"+goodsName+",price:"+price+",number:"+number);
		//封装Goods对象
		Goods goods=new Goods(Integer.parseInt(id),goodsName,Double.parseDouble(price),Integer.parseInt(number));
		
		//执行更新操作
		GoodsDAOImpl impl=new GoodsDAOImpl();
		boolean flag=impl.updateGoodsById(goods);
		if(flag)
		{
			response.sendRedirect("select.do");
		}
	}

	/**
	 * 更新商品部分
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	//获取请求id的值
		String id = request.getParameter("id");
		System.out.println("======>updateId:"+id);
    	GoodsDAOImpl impl = new GoodsDAOImpl();
    	//查询一条记录
    	Goods goods=impl.findById(Integer.parseInt(id));
    	System.out.println(goods);
    	PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>商品信息</title></head>");
		out.println("<body>");
		out.println("<form action='updateData.do' method='post'>");
		out.println("商品名称:<input type='text' value='"+goods.getGoodsName()+"' name='goodsName'/><p></p>");
		out.println("商品价格:<input type='text' value='"+goods.getGoodsPrice()+"' name='goodsPrice'/><p></p>");
		out.println("商品数量:<input type='text' value='"+goods.getGoodsNumber()+"' name='goodsNumber'/><p></p>");
		out.println("<input type='hidden' name='id' value='"+id+"'/>");
		out.println("<input type='submit' value='更新'/>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
    
	/**
     * 实现的是商品的删除
     * @param request
     * @param response
     * @throws IOException 
     */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取请求id的值
		String id=request.getParameter("id");
		GoodsDAOImpl impl = new GoodsDAOImpl();
		boolean flag=impl.deleteGoodsById(Integer.parseInt(id));
		if(flag)
		{
			response.sendRedirect("select.do");
		}
	}
    
	/**
	 * 实现商品的查询
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void select(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		// 执行查询操作
		GoodsDAOImpl impl = new GoodsDAOImpl();
		List<Goods> lists = impl.findAll();
		// 使用输出将数据展示到页面中
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>商品列表</title></head>");
		out.println("<body>");
		out.println("<table border='1' width='60%' align='center'>");
		out.println("<tr><th>编号</th><th>名称</th><th>价格</th><th>数量</th><th>操作</th></tr>");
		// 使用for循环
		for (Goods goods2 : lists) {
			out.println("<tr><td>" + goods2.getGoodsId() + "</td><td>"
					+ goods2.getGoodsName() + "</td><td>"
					+ goods2.getGoodsPrice() + "</td><td>"
					+ goods2.getGoodsNumber() + "</td><td><a href='delete.do?id="+goods2.getGoodsId()+"'>删除</a>&nbsp;&nbsp;<a href='update.do?id="+goods2.getGoodsId()+"'>更新</a></td></tr>");
		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");

		out.flush();
		out.close();
	}

	/*
	 * 添加商品方法
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// 获取请求参数
		String goodsName = request.getParameter("goodsName");
		double price = Double.parseDouble(request.getParameter("price"));
		int number = Integer.parseInt(request.getParameter("number"));

		// 封装Goods对象
		Goods goods = new Goods();
		goods.setGoodsName(goodsName);
		goods.setGoodsPrice(price);
		goods.setGoodsNumber(number);

		// 数据库添加
		GoodsDAOImpl impl = new GoodsDAOImpl();
		boolean flag = impl.addGoods(goods);
		// 判断结果
		if (flag) {
			//重定向
			response.sendRedirect("select.do");
		}
	}
}
