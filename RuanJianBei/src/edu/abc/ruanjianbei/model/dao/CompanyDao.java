package edu.abc.ruanjianbei.model.dao;

import java.util.ArrayList;

import edu.abc.ruanjianbei.model.bean.ChildrenBean;
import edu.abc.ruanjianbei.model.bean.T_CORPBean;

public interface CompanyDao extends BaseDao{
	
	/*
	 * ͨ�������ֵ����ƥ��Ĺ�˾��
	 */
	public ArrayList<T_CORPBean> selectByCompanyName(String name);
	/*
	 * ͨ����˾����ѯ��˾�����Ϣ
	 */
	public T_CORPBean searchOneCompany(String name);
	/*
	 * ͨ����˾����������ù�˾Ͷ�ʵĹɶ�
	 */
	public ArrayList<ChildrenBean> selectGuDongByCompanyName(String name,String type,String sql);
	/*
	 * ͨ����˾����������ù�˾Ͷ�ʵĹɶ�
	 */
	public ArrayList<ChildrenBean> selectGuDongRenByCompanyName(String name,String type);
	/*
	 * ͨ����˾����������ù�˾Ͷ�ʵĹ�˾
	 */
	public ArrayList<ChildrenBean> selectGuDongComByCompanyName(String name,String type);
	
	public ArrayList<ChildrenBean> selectGuDongRenByCompany(String type,String name,int org,int seq_id);
	public ArrayList<ChildrenBean> selectGuDongComByCompany(String type,String name,int org,int seq_id);
}
