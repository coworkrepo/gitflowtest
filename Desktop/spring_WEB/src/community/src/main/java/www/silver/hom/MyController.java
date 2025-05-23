package www.silver.hom;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	@Resource(name = "cname") // �����̳ʷκ��� ���� �޴´�
	String name; //���� null�� �ƴ� ���Թ��� ��ü�� �ּ� < DI����
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	//@ResponseBody // �޼����� ��ȯ���� HTTP ���� ������ ���� �ۼ� (�並 ���������� ����)
	public String home() {
		System.out.println("Name: " + name);
		// @ResponseBody�� ����Ǿ����Ƿ� "index"�� �� �̸��� �ƴ϶� ���� ������ ���� ��µ�
		// ���� @ResponseBody�� ���� ViewResolver�� �����Ǿ� �ִٸ�, "index"�� "index.jsp" ���� �� ������ ����Ŵ
		return "index"; // ResponseBody�� ���� �� HTTP ���� ������ "index" ���ڿ��� ��ȯ��, �Ϲ������� return ������ ���� ���� ���� �̸��� �ǹ�
	}
}
