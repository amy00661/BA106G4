

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iii.emp.model.EmpDAO;
import iii.emp.model.EmpDAO_interface;
import iii.emp.model.EmpVO;
import iii.opinion.model.OpinionDAO;
import iii.opinion.model.OpinionService;
import iii.opinion.model.OpinionVO;

@WebServlet("/TestOpinionService")
public class TestOpinionService extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = res.getWriter();
		OpinionService opinionSrv = new OpinionService();
		OpinionVO opinionVO = new OpinionVO();
		
		// 測試新增意見
		/*String opinion_id = "EMP003";
		String opinion_type = "100";
		String opinion_name = "曾鄢畇";
		String opinion_phone = "03-47686671";
		String opinion_cell = "0926-457-338";
		String opinion_email = "myforestory@gmail.com";
		String opinion_content = "請問包裹送貨進度?";
		
		int insertRow = opinionSrv.addOpinion(opinion_id, opinion_type, opinion_name, opinion_phone, opinion_cell, opinion_email, opinion_content);
		System.out.printf("新增了%d筆意見資料!%n",insertRow);*/
		
		// 測試修改意見
		/*String opinion_id = "OPI007";
		String emp_id = "EMP003";
		String opinion_type = "200";
		String opinion_phone = "07-25813396";
		String opinion_cell = "0912-345-678";
		String opinion_email = "jackHuang@yahoo.com.tw";
		String opinion_content = "謝謝您的建議, 我們下次會再改進!";
		String opinion_status = "02";
		int updateRow = opinionSrv.updateOpinion(opinion_id, emp_id, opinion_type, opinion_phone, opinion_cell, opinion_email, opinion_content, opinion_status);
		System.out.printf("已成功更新了%d筆意見資料%n",updateRow);*/
		
		// 查詢:以意見編號
		opinionVO = opinionSrv.getOneOpinion("OPI005");
		System.out.printf("意見資訊如下：%n",opinionVO.getOpinion_id());
		System.out.println(opinionVO);
		
		
		// 查詢:全部意見
		/*List<OpinionVO> list = opinionSrv.getAllOpinion();
		System.out.println("全部的意見列表如下：");
		for (OpinionVO aOpinion : list) {
			System.out.print(aOpinion.getOpinion_id() + "\t");
			System.out.print(aOpinion.getEmp_id() + "\t");
			System.out.print(aOpinion.getOpinion_type() + "\t");
			System.out.print(aOpinion.getOpinion_name() + "\t");
			System.out.print(aOpinion.getOpinion_phone() + "\t");
			System.out.print(aOpinion.getOpinion_cell() + "\t");
			System.out.print(aOpinion.getOpinion_email() + "\t");
			System.out.print(aOpinion.getOpinion_content() + "\t");
			System.out.print(aOpinion.getOpinion_status() + "\t");
			System.out.print(aOpinion.getOpinion_createTime() + "\t");
			System.out.println(aOpinion.getOpinion_updateTime() + "\t");
		}*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
