package com.mbc.team.FAQ;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

//<!-- �������� �ϴ� ���� �亯���� ���� -->
@Controller
public class FAQController {

	@Autowired
	SqlSession sqlSession;
	// ��� �����ؾ��մϴ�.
	String path = "C:\\mbc\\Somall\\Somall\\teamproject\\src\\main\\webapp\\image";

	// faqinput���� �̵�
	@RequestMapping(value = "/faqin")
	public String faq() {

		return "faqinput";
	}

	// faqinput���� ����
	@RequestMapping(value = "/faqsave", method = RequestMethod.POST)
	public String faq1(MultipartHttpServletRequest mul) throws IllegalStateException, IOException {
		String tab = mul.getParameter("tab");
		String title = mul.getParameter("title");
		String nickname = mul.getParameter("nickname");
		String fcontents = mul.getParameter("fcontents");
		MultipartFile fimg1 = mul.getFile("fimage1");
		MultipartFile fimg2 = mul.getFile("fimage2");
		MultipartFile fimg3 = mul.getFile("fimage3");

		String fname1 = fimg1.getOriginalFilename();
		String fname2 = fimg2.getOriginalFilename();
		String fname3 = fimg3.getOriginalFilename();
		FAQService fs = sqlSession.getMapper(FAQService.class);
		fimg1.transferTo(new File(path + "\\" + fname1));
		fimg2.transferTo(new File(path + "\\" + fname2));
		fimg3.transferTo(new File(path + "\\" + fname3));

		fs.faqinsert(tab, title, fcontents, nickname, fname1, fname2, fname3);

		return "redirect:/";
	}

	// ���峻�� ���
	@RequestMapping(value = "/faqout")
	public String faq2(HttpServletRequest request, PageDTO dto, Model mo) {
		String nowPage = request.getParameter("nowPage");
		String cntPerPage = request.getParameter("cntPerPage");

		FAQService fs = sqlSession.getMapper(FAQService.class);
		ArrayList<FAQDTO> list = fs.faqboard();
		mo.addAttribute("list", list);

		int total = fs.total();
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "15";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "15";
		}
		dto = new PageDTO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		mo.addAttribute("paging", dto);
		mo.addAttribute("list", fs.page(dto));

		return "faqoutput";
	}

	// ���Ǳ� �Խ��� ���� �亯 �ۼ�
	@RequestMapping(value = "/faqreply", method = RequestMethod.POST)
	public String faq3(HttpServletRequest request, Model mo) {
		int cnum = Integer.parseInt(request.getParameter("cnum"));
		FAQService fs = sqlSession.getMapper(FAQService.class);
		ArrayList<FAQDTO> list = fs.faqreply(cnum);
		mo.addAttribute("faqlist", list);

		return "faqreplyview";
	}

	// ���Ǳ� �亯 ���� ����
	@RequestMapping(value = "/faqreplysave", method = RequestMethod.POST)
	public String faq4(HttpServletRequest request) throws IllegalStateException, IOException {
		int cnum = Integer.parseInt(request.getParameter("cnum"));
		String tab = request.getParameter("tab");
		String title = request.getParameter("title");
		String fcontents = request.getParameter("fcontents");
		String nickname = request.getParameter("nickname");
		int groups = Integer.parseInt(request.getParameter("groups"));
		int step = Integer.parseInt(request.getParameter("step"));
		int indent = Integer.parseInt(request.getParameter("indent"));

		FAQService fs = sqlSession.getMapper(FAQService.class);
		fs.faqstepup(groups, step);
		step++;
		indent++;
		;
		fs.faqreplysave(cnum, tab, title, fcontents, nickname, groups, step, indent);

		return "redirect:/faqdetail?cnum=" + cnum;
	}

	// ���峻�� ��� ��������(+�ϴܿ� ���� �亯���� �̿�)
	@RequestMapping(value = "/faqdetail")
	public String faq5(Model mo, HttpServletRequest request) {
		int cnum = Integer.parseInt(request.getParameter("cnum"));

		FAQService fs = sqlSession.getMapper(FAQService.class);
		ArrayList<FAQDTO> list = fs.faqdetail(cnum);
		mo.addAttribute("list", list);

		ArrayList<FAQDTO> replylist = fs.faqreplydetail(cnum);
		mo.addAttribute("replylist", replylist);

		return "faqDtailview";
	}

	// ���Ǳ� ����â
	@RequestMapping(value = "/faqupdate")
	public String faq6(HttpServletRequest request, Model mo) throws IllegalStateException, IOException {
		int cnum = Integer.parseInt(request.getParameter("cnum"));

		FAQService fs = sqlSession.getMapper(FAQService.class);

		FAQDTO dto = fs.faqupdate(cnum);
		mo.addAttribute("dto", dto);

		return "faqUpdateview";
	}

	// ���Ǳ� �����Ϸ�(+���� �Ϸ�ƴٴ� �˸� �ʿ�)
	@RequestMapping(value = "/faqupdate2", method = RequestMethod.POST)
	public String faq7(MultipartHttpServletRequest mul) throws IllegalStateException, IOException {
		int cnum = Integer.parseInt(mul.getParameter("cnum"));
		String tab = mul.getParameter("tab");
		String title = mul.getParameter("title");
		String nickname = mul.getParameter("nickname");
		String fcontents = mul.getParameter("fcontents");
		MultipartFile fimg1 = mul.getFile("fimage1");
		MultipartFile fimg2 = mul.getFile("fimage2");
		MultipartFile fimg3 = mul.getFile("fimage3");

		String fname1 = fimg1.getOriginalFilename();
		String fname2 = fimg2.getOriginalFilename();
		String fname3 = fimg3.getOriginalFilename();
		FAQService fs = sqlSession.getMapper(FAQService.class);
		fimg1.transferTo(new File(path + "\\" + fname1));
		fimg2.transferTo(new File(path + "\\" + fname2));
		fimg3.transferTo(new File(path + "\\" + fname3));

		fs.faqupdate2(cnum, tab, title, fcontents, nickname, fname1, fname2, fname3);

		return "redirect:/faqout";
	}

	// ���Ǳ� ����
	@RequestMapping(value = "/faqdelete")
	public String faq8(HttpServletRequest request) {
		int cnum = Integer.parseInt(request.getParameter("cnum"));
		FAQService fs = sqlSession.getMapper(FAQService.class);
		fs.faqdelete(cnum);

		return "redirect:/faqout";
	}

	// ���Ǳ� ��� �˻�
	@RequestMapping(value = "/faqsearch", method = RequestMethod.POST)
	public String faq9(HttpServletRequest request, Model mo) throws IllegalStateException, IOException {

		String faqkey = request.getParameter("faqkey");
		String faqvalue = request.getParameter("faqvalue");

		FAQService fs = sqlSession.getMapper(FAQService.class);

		ArrayList<FAQDTO> list;
		if (faqkey.equals("title")) {
			list = fs.faqtitlesearch(faqvalue);
		} else {
			list = fs.faqnicknamesearch(faqvalue);
		}
		mo.addAttribute("list", list);

		return "faqoutput";
	}

	// ������ Ȩ
	@RequestMapping(value = "/faq_community")
	public String faq10() {

		return "faq_main";
	}

	// FAQ-���� ���� ���� �ۼ�
	@RequestMapping(value = "/FAQ_in")
	public String faq11() {

		return "faq_admin_input";
	}

	// FAQ-���� ���� ���� ���� ����
	@RequestMapping(value = "/faq_admin_save", method = RequestMethod.POST)
	public String faq12(MultipartHttpServletRequest mul) throws IllegalStateException, IOException {
		String tab = mul.getParameter("tab");
		String title = mul.getParameter("title");
		String nickname = mul.getParameter("nickname");
		String fcontents = mul.getParameter("fcontents");
		MultipartFile fimg1 = mul.getFile("fimage1");
		MultipartFile fimg2 = mul.getFile("fimage2");
		MultipartFile fimg3 = mul.getFile("fimage3");

		String fname1 = fimg1.getOriginalFilename();
		String fname2 = fimg2.getOriginalFilename();
		String fname3 = fimg3.getOriginalFilename();
		FAQadminService fs2 = sqlSession.getMapper(FAQadminService.class);
		fimg1.transferTo(new File(path + "\\" + fname1));
		fimg2.transferTo(new File(path + "\\" + fname2));
		fimg3.transferTo(new File(path + "\\" + fname3));

		fs2.faqinsert(tab, title, fcontents, nickname, fname1, fname2, fname3);

		return "redirect:/";
	}

	// FAQ-���� ���� ���� ���� �Խ���
	@RequestMapping(value = "/faq")
	public String faq13(HttpServletRequest request, PageDTO dto, Model mo) {
		String tab = request.getParameter("tab");
		String nowPage = request.getParameter("nowPage");
		String cntPerPage = request.getParameter("cntPerPage");

		FAQadminService fs2 = sqlSession.getMapper(FAQadminService.class);
		ArrayList<FAQadminDTO> list = fs2.faqboard(tab);
		mo.addAttribute("faq_admin_board", list);

		int total = fs2.total();
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "15";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "15";
		}
		dto = new PageDTO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		mo.addAttribute("paging", dto);
		mo.addAttribute("list", fs2.page(dto));

		return "faq_questions";
	}

	// FAQ-���� ���� ���� ��������
	@RequestMapping(value = "/faq_quetions_detail")
	public String faq14(Model mo, HttpServletRequest request) {
		int cnum = Integer.parseInt(request.getParameter("cnum"));

		FAQadminService fs2 = sqlSession.getMapper(FAQadminService.class);
		FAQadminDTO faq = fs2.faq_quetions_detail(cnum);
		mo.addAttribute("faq", faq);

		ArrayList<FAQadminDTO> faqreply = fs2.faq_questions_reply(cnum);
		mo.addAttribute("faqreply", faqreply);

		return "faq_questions_Dtailview";
	}

	// FAQ-���� ���� ���� ����������
	@RequestMapping(value = "/faq_admin_update")
	public String faq15(Model mo, HttpServletRequest request) {
		int cnum = Integer.parseInt(request.getParameter("cnum"));

		FAQadminService fs2 = sqlSession.getMapper(FAQadminService.class);
		FAQadminDTO dto = fs2.faq_admin_update(cnum);
		mo.addAttribute("dto", dto);

		return "faq_questions_Updateview";
	}

	// FAQ-���� ���� ���� �����Ϸ�
	@RequestMapping(value = "/faq_admin_update2", method = RequestMethod.POST)
	public String faq16(MultipartHttpServletRequest mul) throws IllegalStateException, IOException {
		int cnum = Integer.parseInt(mul.getParameter("cnum"));
		String tab = mul.getParameter("tab");
		String title = mul.getParameter("title");
		String nickname = mul.getParameter("nickname");
		String fcontents = mul.getParameter("fcontents");
		MultipartFile fimg1 = mul.getFile("fimage1");
		MultipartFile fimg2 = mul.getFile("fimage2");
		MultipartFile fimg3 = mul.getFile("fimage3");

		String fname1 = fimg1.getOriginalFilename();
		String fname2 = fimg2.getOriginalFilename();
		String fname3 = fimg3.getOriginalFilename();
		FAQadminService fs2 = sqlSession.getMapper(FAQadminService.class);
		fimg1.transferTo(new File(path + "\\" + fname1));
		fimg2.transferTo(new File(path + "\\" + fname2));
		fimg3.transferTo(new File(path + "\\" + fname3));

		fs2.faq_admin_update2(cnum, tab, title, fcontents, nickname, fname1, fname2, fname3);

		return "redirect:/faq";
	}

	// FAQ-���� ���� ���� ����
	@RequestMapping(value = "/faq_admin_delete")
	public String faq17(Model mo, HttpServletRequest request) {
		int cnum = Integer.parseInt(request.getParameter("cnum"));

		FAQadminService fs2 = sqlSession.getMapper(FAQadminService.class);
		fs2.faq_admin_delete(cnum);

		return "redirect:/faq";
	}

	// ���Ǳ� �Խ��� ���� �亯 �ۼ�
	@RequestMapping(value = "/faq_questions_reply_save", method = RequestMethod.POST)
	public String faq18(HttpServletRequest request, Model mo) {

		int cnum = Integer.parseInt(request.getParameter("cnum"));
		String fcontents = request.getParameter("fcontents");
		String nickname = request.getParameter("nickname");
		int groups = Integer.parseInt(request.getParameter("groups"));
		int step = Integer.parseInt(request.getParameter("step"));
		int indent = Integer.parseInt(request.getParameter("indent"));

		FAQadminService fs2 = sqlSession.getMapper(FAQadminService.class);
		fs2.faq_questions_stepup(groups, step);
		step++;
		indent++;

		fs2.faq_questions_faqreplysave(cnum, fcontents, nickname, groups, step, indent);

		return "redirect:/faq_quetions_detail?cnum=" + cnum;
	}

}
