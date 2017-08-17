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
		// ��������URI
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
	 * ������Ʒ���ݲ���
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateData(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//ȡ��ֵ
		String goodsName=request.getParameter("goodsName");
		String price=request.getParameter("goodsPrice");
		String number=request.getParameter("goodsNumber");
		String id=request.getParameter("id");
		System.out.println("--->id:"+id+",goodsName:"+goodsName+",price:"+price+",number:"+number);
		//��װGoods����
		Goods goods=new Goods(Integer.parseInt(id),goodsName,Double.parseDouble(price),Integer.parseInt(number));
		
		//ִ�и��²���
		GoodsDAOImpl impl=new GoodsDAOImpl();
		boolean flag=impl.updateGoodsById(goods);
		if(flag)
		{
			response.sendRedirect("select.do");
		}
	}

	/**
	 * ������Ʒ����
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	//��ȡ����id��ֵ
		String id = request.getParameter("id");
		System.out.println("======>updateId:"+id);
    	GoodsDAOImpl impl = new GoodsDAOImpl();
    	//��ѯһ����¼
    	Goods goods=impl.findById(Integer.parseInt(id));
    	System.out.println(goods);
    	PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>��Ʒ��Ϣ</title></head>");
		out.println("<body>");
		out.println("<form action='updateData.do' method='post'>");
		out.println("��Ʒ����:<input type='text' value='"+goods.getGoodsName()+"' name='goodsName'/><p></p>");
		out.println("��Ʒ�۸�:<input type='text' value='"+goods.getGoodsPrice()+"' name='goodsPrice'/><p></p>");
		out.println("��Ʒ����:<input type='text' value='"+goods.getGoodsNumber()+"' name='goodsNumber'/><p></p>");
		out.println("<input type='hidden' name='id' value='"+id+"'/>");
		out.println("<input type='submit' value='����'/>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
    
	/**
     * ʵ�ֵ�����Ʒ��ɾ��
     * @param request
     * @param response
     * @throws IOException 
     */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//��ȡ����id��ֵ
		String id=request.getParameter("id");
		GoodsDAOImpl impl = new GoodsDAOImpl();
		boolean flag=impl.deleteGoodsById(Integer.parseInt(id));
		if(flag)
		{
			response.sendRedirect("select.do");
		}
	}
    
	/**
	 * ʵ����Ʒ�Ĳ�ѯ
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void select(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		// ִ�в�ѯ����
		GoodsDAOImpl impl = new GoodsDAOImpl();
		List<Goods> lists = impl.findAll();
		// ʹ�����������չʾ��ҳ����
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>��Ʒ�б�</title></head>");
		out.println("<body>");
		out.println("<table border='1' width='60%' align='center'>");
		out.println("<tr><th>���</th><th>����</th><th>�۸�</th><th>����</th><th>����</th></tr>");
		// ʹ��forѭ��
		for (Goods goods2 : lists) {
			out.println("<tr><td>" + goods2.getGoodsId() + "</td><td>"
					+ goods2.getGoodsName() + "</td><td>"
					+ goods2.getGoodsPrice() + "</td><td>"
					+ goods2.getGoodsNumber() + "</td><td><a href='delete.do?id="+goods2.getGoodsId()+"'>ɾ��</a>&nbsp;&nbsp;<a href='update.do?id="+goods2.getGoodsId()+"'>����</a></td></tr>");
		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");

		out.flush();
		out.close();
	}

	/*
	 * �����Ʒ����
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// ��ȡ�������
		String goodsName = request.getParameter("goodsName");
		double price = Double.parseDouble(request.getParameter("price"));
		int number = Integer.parseInt(request.getParameter("number"));

		// ��װGoods����
		Goods goods = new Goods();
		goods.setGoodsName(goodsName);
		goods.setGoodsPrice(price);
		goods.setGoodsNumber(number);

		// ���ݿ����
		GoodsDAOImpl impl = new GoodsDAOImpl();
		boolean flag = impl.addGoods(goods);
		// �жϽ��
		if (flag) {
			//�ض���
			response.sendRedirect("select.do");
		}
	}
}
