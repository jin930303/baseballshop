package com.mbc.team.FAQ;

import java.util.ArrayList;

public interface FAQadminService {
//1. FAQ-���ֹ������� ����(���Ǳ� �ۼ�)
	void faqinsert(String tab, String title, String fcontents, String nickname, String fname1, String fname2,
			String fname3);

//2. FAQ-���ֹ������� �Խ���
	ArrayList<FAQadminDTO> faqboard(String tab);

//3. FAQ-���ֹ������� ������	
	public int total();

	public ArrayList<FAQadminDTO> page(PageDTO dto);

//4. FAQ-���ֹ������� ��������
	FAQadminDTO faq_quetions_detail(int cnum);

//5. FAQ-���ֹ������� �������� ����(������)
	FAQadminDTO faq_admin_update(int cnum);

//5-1. ���� �Ϸ�(������)
	void faq_admin_update2(int cnum, String tab, String title, String fcontents, String nickname, String fname1,
			String fname2, String fname3);

//6. FAQ-���ֹ������� ����
	void faq_admin_delete(int cnum);

//7. FAQ-���ֹ������� ��� �ޱ�
	void faq_questions_stepup(int groups, int step);

	void faq_questions_faqreplysave(int cnum, String tab, String title, int groups,
			int step, int indent);

	ArrayList<FAQadminDTO> faq_questions_reply(int cnum);

	
}
