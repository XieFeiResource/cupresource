package edu.abc.raunjianbei.createdata;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import edu.abc.ruanjianbei.model.bean.OneGuongSiMoneyBean;
import edu.abc.ruanjianbei.model.bean.T_CORPBean;
import edu.abc.ruanjianbei.model.bean.T_CORP_STOCKBean;

public class T_CORP {
	private T_CORP_STOCK guDong;//Ӧ�ùɶ���
	
	private int ORG;//һ����˾Ψһ
	private int ID;//�����ҵ
	private int SEQ_ID;//ÿ���ܹ�˾����
	private String REG_NO;//ÿ����˾Ψһ
	private	String CORP_NAME;
	private	String ADDR;
	private	String BELONG_ORG;
	private	String BELONG_DIST_ORG;
	private	String BELONG_TRADE;
	private	String ECON_KIND;
	private	String ADMIT_MAIN;
	private	String START_DATE;
	private	String CHECK_DATE;
	private	String OPER_MAN_IDENT_NO;
	private	String OPER_MAN_NAME;
	private	String CORP_STATUS;
	private	float REG_CAPI;
	private	String FARE_TERM_START;
	private	String FARE_TERM_END;
	private	String FARE_SCOPE;
	private	String UNI_SCID;
	private	String TEL;
	private	String WEB_URL;
	private	String EMAIL;
	private	int PRAC_PERSON_NUM;
	private	String ORG_INST_CODE;
	private	String TAXPAY_NUM;
	private	String STAFF_SIZE;
	private	String ENGLISH_NAME;
	private	String FORMER_NAME;
	private	String CREATE_DATE;
	private	int CREATE_ORG;
	
	
	//�����ɵ����йɶ�ȫ����Ϣ
	private ArrayList<T_CORP_STOCKBean> touGuRens;
	//��һ����˾���Ͻɽ��ɶ���������������
	private ArrayList<OneGuongSiMoneyBean> allMenoy;
	//��һ����˾�Ĺɶ�����
	private ArrayList<T_CORP_STOCKBean> oneGongSiGuDongCount;
	//����һ�����ϴ���������˾��ȫ����Ϣ
	public ArrayList<T_CORPBean> allZhuGongSi;

	public static void main(String[] args) throws Exception {
		new T_CORP();
	}
	
	public T_CORP() throws Exception {
		BufferedOutputStream  out=new BufferedOutputStream(new FileOutputStream("E:\\file\\data\\T_CORP_DATA.txt"));
		BufferedOutputStream  out1=new BufferedOutputStream(new FileOutputStream("E:\\file\\data\\T_M_CORP_CORP_STOCK.txt"));
		//ʵ�����ɶ���
		guDong=new T_CORP_STOCK();
		touGuRens=guDong.touGuRens;
		allMenoy=guDong.allMenoy;
		
		oneGongSiGuDongCount=new ArrayList<>();
		allZhuGongSi=new ArrayList<>();
		
		String[] zimu= {"A","B,","C","D","E","F","G","H","I","J","K","L",
				"M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String xiaozimu= "abcdefghijklmnopqrstuvwxyz";
		String[] sheng= {"������","�Ϻ���","�����","������","������ʡ","����ʡ",
				"����ʡ","����ʡ","ɽ��ʡ","����ʡ","�ӱ�ʡ","����ʡ","����ʡ",
				"����ʡ","����ʡ","����ʡ","ɽ��ʡ","�Ĵ�ʡ","�ຣʡ","����ʡ",
				"�㶫ʡ","����ʡ","�㽭ʡ","����ʡ","̨��ʡ","����ʡ","����ʡ"};
		String[] shi= {"�ɶ���","������","�人��","������","�Ͼ���","�����",
				"������","������","��ɳ��","������","�ൺ��","֣����","������","��ݸ��","������"};
		String[] qv= {"������","������","ɳƺ����","��������","�ϰ���","��ɿ���","�山��","������",
				"������","������","������","������","ǭ����","������","�뽭��","�ϴ���","������","������","�ϴ���"};
		String[] jiedao= {"������","�Ͼ�·","����·","���������","������","ת��·","ƽ��·","�ϴ��","��Ϫ�Ͻ�","�Ͻ�",
				"�����","����·","��ɽ·"};
		
		String[] hangye1= {"���","��Ϣ����","����","��������Ϣ","���������","�����ϵͳ","���ݴ���","���Ǵ���"};
		String[] hangye2= {"�������","��������","����ͷ���","רҵ��������","�������","����"};
		String[] jingyingfanwei1= {"��������������","���ۼ���ز�Ʒ��ѵ��","�����Ӧ�÷���","��·��","�ܵ���","�豸��װ��","���Ӳ�Ʒ��ͨ���豸��������","�������������������ۣ�","���Ӽ�����������"};
		String[] jingyingfanwei2= {"���","���硢","�ٻ���","����ԭ�ϼ���Ʒ��","������","�����������ۣ�","��Ʒ��Ϣ��ѯ��"};
		String[] jingyingfanwei3= {"�������Ƽ�,","ҽ����е��","�������","����Ļ���","��Ϸ��","���ڷ���","��ó��","����"};
		
		String[] gongsidalei= {"���ʹ�˾","���ʹ�˾","���ʹ�˾"};
		String guanlijv="���̹����";
		
		Random ran=new Random();
		int num=0;
		//�洢��ҵ������Ϣ
		Map<Integer, String> map=new HashMap<Integer, String>();
		for(int i=0;i<hangye1.length;i++) {
			for(int j=0;j<hangye2.length;j++)
				map.put(num+1000, hangye1[i]+"��"+hangye2[j]+"ҵ");
				num++;
		}
		
		int tal=0;//���й�˾����
		for(int i=0;i<10;i++)//ʡ��
		{
			ORG=876+i;
			
			
			int number=0;//ÿ��ʡ�Ĺ�˾����
			for(OneGuongSiMoneyBean o:allMenoy)
			{
				if(o.getORG()==ORG)
				{
					number++;
				}
			}
			tal=tal+number;
			//System.out.println("ÿ��ʡ��˾����"+number);
			
			for(int index=0;index<number;index++)//ÿ��ʡ���ж��ٹ�˾
			{
				T_CORPBean gongsi=new T_CORPBean();	
				
				Integer[] keys = map.keySet().toArray(new Integer[0]);
				Integer randomkey=keys[ran.nextInt(keys.length)];
				String randomvalue=map.get(randomkey);
				
				ID=randomkey;
				//������ҵ
				BELONG_TRADE=randomvalue;
		//		System.out.println("���ͣ�"+ID);
				
				SEQ_ID=index+1;
				//System.out.println("��˾��ţ�"+SEQ_ID);
				
				//���ô���
				Double dd=Math.random();
				String q10=dd.toString().substring(2, 12);
				dd=Math.random();
				String q7=dd.toString().substring(2, 9);
				int randindex=ran.nextInt(26);
				REG_NO=q10+q7+zimu[randindex];
		//		System.out.println("���ô��룺"+REG_NO);
				
				//��˾����
				int shengindex=ran.nextInt(sheng.length);
				int hangye1index=ran.nextInt(hangye1.length);
				CORP_NAME=sheng[shengindex]+hangye1[hangye1index]+"���޹�˾";
				
				//��˾��ַ
		//		System.out.println("��ҵ���ƣ�"+CORP_NAME);
				int shiindex=ran.nextInt(shi.length);
				int qvindex=ran.nextInt(qv.length);
				int jiedaoindex=ran.nextInt(jiedao.length);
				int qvhao=ran.nextInt(500);
				ADDR=shi[shiindex]+qv[qvindex]+jiedao[jiedaoindex]+qvhao+"��";
		//		System.out.println("��ҵ��ַ��"+ADDR);
				
				//�Ǽǻ���
				BELONG_ORG=shi[shiindex]+guanlijv;
		//		System.out.println("�Ǽǻ��أ�"+BELONG_ORG);
				
				//��������
				BELONG_DIST_ORG=shi[shiindex]+qv[qvindex];
		//		System.out.println("��������:"+BELONG_DIST_ORG);
				int hangye2index=ran.nextInt(hangye2.length);
				
				//��˾����
				ECON_KIND="�������ι�˾";
		//		System.out.println("��˾���ͣ�"+ECON_KIND);
				
				//��˾����
				int gongsidaleiindex=ran.nextInt(gongsidalei.length);
				ADMIT_MAIN=gongsidalei[gongsidaleiindex];
		//		System.out.println("��˾���ࣺ"+ADMIT_MAIN);
				
				//����
				int chenglinian=ran.nextInt(55)+1960;
				int chengliyue=ran.nextInt(12)+1;
				int chengliri=ran.nextInt(28)+1;
				String yue,ri;
				if(chengliyue<10) {
					yue="0"+chengliyue;
				}else {
					yue=""+chengliyue;
				}
				if(chengliri<10) {
					ri="0"+chengliri;
				}else {
					ri=""+chengliri;
				}
//				START_DATE=chenglinian+"/"+yue+"/"+ri;
//				System.out.println("�������ڣ�"+START_DATE);
//				int heizunnian=chenglinian+ran.nextInt(15)+1;
//				CHECK_DATE=heizunnian+"/"+yue+"/"+ri;
//				System.out.println("��׼���ڣ�"+CHECK_DATE);
				int gongSiGuDongcount=0;
	
				//ע���ʽ�/��������
				for(OneGuongSiMoneyBean one:allMenoy)
				{
					if(one.getORG()==ORG && one.getSEQ_ID()==SEQ_ID)
					{
						gongSiGuDongcount=one.getGuDongcount();
						REG_CAPI=one.getAllMoney();
						CREATE_DATE=one.getChenglishijian();
						for(Integer a:one.getID()) {
							out1.write(("insert into T_M_CORP_CORP_STOCK values("+ORG+", "+ID+", "+SEQ_ID+", "+one.getORG()+", "+a+", "+one.getSEQ_ID()+");\r\n").getBytes());
						}
						break;
					}
				}
	//			System.out.println("��˾�ɶ���"+gongSiGuDongcount);
				
				for(T_CORP_STOCKBean oneGuDong:touGuRens)
				{
					if(oneGuDong.getORG()==ORG && oneGuDong.getSEQ_ID()==SEQ_ID)
					{
						oneGongSiGuDongCount.add(oneGuDong);
					}
					if(oneGongSiGuDongCount.size()==gongSiGuDongcount)
					{
						break;
					}
				}
	//			System.out.println("��˾�ɶ�������"+oneGongSiGuDongCount.size());
				
				//����
				int faRenIndex=ran.nextInt(gongSiGuDongcount);
				OPER_MAN_IDENT_NO=oneGongSiGuDongCount.get(faRenIndex).getCERTIFICATE_NO();
				OPER_MAN_NAME=oneGongSiGuDongCount.get(faRenIndex).getSTOCK_NAME();	
				//System.out.println("���˱�ţ�"+OPER_MAN_IDENT_NO);
				//System.out.println("�������ƣ�"+OPER_MAN_NAME);
				
				//��Ӫ״̬
				CORP_STATUS="0"+(ran.nextInt(4)+1);
		//		System.out.println("��Ӫ״̬��"+CORP_STATUS);
		//		System.out.println("ע���ʽ�:"+REG_CAPI);
		//		FARE_TERM_START=START_DATE;
		//		System.out.println("��Ӫ������"+FARE_TERM_START);
				FARE_TERM_END="����";
		//		System.out.println("��Ӫ����ֹ��"+FARE_TERM_END);
				
				//��Ӫ��Χ
				int jingyingfanwei1index=ran.nextInt(jingyingfanwei1.length);
				int jingyingfanwei2index=ran.nextInt(jingyingfanwei2.length);
				int jingyingfanwei3index=ran.nextInt(jingyingfanwei3.length);
				FARE_SCOPE=hangye1[hangye1index]+"��"+jingyingfanwei1[jingyingfanwei1index]+jingyingfanwei2[jingyingfanwei2index]+jingyingfanwei3[jingyingfanwei3index];
				FARE_SCOPE=FARE_SCOPE.substring(0, FARE_SCOPE.length()-1)+"��";
		//		System.out.println("��Ӫ��Χ��"+FARE_SCOPE);
				
				//ע���
				dd=Math.random();
				String q11=dd.toString().substring(2, 13);
				String q4=dd.toString().substring(2, 6);
				String q15=q11+q4;
				UNI_SCID=q15;
		//		System.out.println("ע��ţ�"+UNI_SCID);
				
				//�绰
				dd=Math.random();
				String q3=dd.toString().substring(2, 5);
				String h7=dd.toString().substring(2, 9);
				TEL=q3+"-"+h7;
		//		System.out.println("�绰��"+TEL);
				
				//����/����
				StringBuffer zhongjianzifu = new StringBuffer();
				for(int zhongjian=0;zhongjian<(ran.nextInt(10)+5);zhongjian++)
				{				
					zhongjianzifu.append(xiaozimu.charAt(ran.nextInt(xiaozimu.length())));
				}
				WEB_URL="www."+zhongjianzifu.toString()+".com.cn";
		//		System.out.println("����:"+WEB_URL);
				EMAIL="jxsw@"+zhongjianzifu+".com.cn";
		//		System.out.println("���䣺"+EMAIL);
				
				//��ҵ����
				PRAC_PERSON_NUM=ran.nextInt(400)+100;
		//		System.out.println("��ҵ������"+PRAC_PERSON_NUM);
				
				//��֯��������
				dd=Math.random();
				String q8=dd.toString().substring(2, 10);
				ORG_INST_CODE=q8+"-"+ran.nextInt(10);
		//		System.out.println("��֯��������:"+ORG_INST_CODE);
				
				//��˰��ʶ���
				TAXPAY_NUM=REG_NO;
		//		System.out.println("��˰��ʶ��ţ�"+TAXPAY_NUM);
				
				//��Ա��ģ
				STAFF_SIZE="100-499��";
		//		System.out.println("��Ա��ģ��"+STAFF_SIZE);
				StringBuffer danci = new StringBuffer();
				
				//Ӣ����
				for(int dancishuliang=0;dancishuliang<ran.nextInt(3)+3;dancishuliang++)
				{
					for(int zhongjian=0;zhongjian<(ran.nextInt(5)+5);zhongjian++)
					{				
						danci.append(xiaozimu.charAt(ran.nextInt(xiaozimu.length())));
					}
					danci.append(' ');
				}
				ENGLISH_NAME=danci.toString();
		//		System.out.println("Ӣ������"+ENGLISH_NAME);
				
				//������
				FORMER_NAME="null";
		//		System.out.println("��������"+FORMER_NAME);
				
				String time=CREATE_DATE;
				String[] caifen1=time.split(" ");
				
				String[] caifen2=caifen1[0].split("/");
				int shengheinian=Integer.parseInt(caifen2[0]);
				int shengheiyue=Integer.parseInt(caifen2[1]);
				int shengheiri=Integer.parseInt(caifen2[2]);
				
				START_DATE=caifen1[0];				
		//		System.out.println("��������"+START_DATE);
				
				CHECK_DATE=shengheinian+(ran.nextInt(15)+1)+"/"+yue+"/"+ri;
		//		System.out.println("��׼���ڣ�"+CHECK_DATE);
				
				FARE_TERM_START=caifen1[0];
		//		System.out.println("��Ӫ������"+FARE_TERM_START);
				
				CREATE_ORG=ORG;
		//		System.out.println("�����������룺"+CREATE_ORG);
				
				oneGongSiGuDongCount.clear();
		//		System.out.println("���ȣ�"+oneGongSiGuDongCount.size());
			
				//��װ������˾
				T_CORPBean zhugongsi=new T_CORPBean();
				zhugongsi.setORG(ORG);
				zhugongsi.setID(ID);
				zhugongsi.setSEQ_ID(SEQ_ID);
				zhugongsi.setREG_NO(REG_NO);
				zhugongsi.setCORP_NAME(CORP_NAME);
				zhugongsi.setADDR(ADDR);
				zhugongsi.setBELONG_ORG(BELONG_ORG);
				zhugongsi.setBELONG_DIST_ORG(BELONG_DIST_ORG);
				zhugongsi.setBELONG_TRADE(BELONG_TRADE);
				zhugongsi.setECON_KIND(ECON_KIND);
				zhugongsi.setADMIT_MAIN(ADMIT_MAIN);
				zhugongsi.setSTART_DATE(START_DATE);
				zhugongsi.setCHECK_DATE(CHECK_DATE);
				zhugongsi.setOPER_MAN_IDENT_NO(OPER_MAN_IDENT_NO);
				zhugongsi.setOPER_MAN_NAME(OPER_MAN_NAME);
				zhugongsi.setCORP_STATUS(CORP_STATUS);
				zhugongsi.setREG_CAPI(REG_CAPI);
				zhugongsi.setFARE_TERM_START(FARE_TERM_START);
				zhugongsi.setFARE_TERM_END(FARE_TERM_END);
				zhugongsi.setFARE_SCOPE(FARE_SCOPE);
				zhugongsi.setUNI_SCID(UNI_SCID);
				zhugongsi.setTEL(TEL);
				zhugongsi.setWEB_URL(WEB_URL);
				zhugongsi.setEMAIL(EMAIL);
				zhugongsi.setPRAC_PERSON_NUM(PRAC_PERSON_NUM);
				zhugongsi.setORG_INST_CODE(ORG_INST_CODE);
				zhugongsi.setTAXPAY_NUM(TAXPAY_NUM);
				zhugongsi.setSTAFF_SIZE(STAFF_SIZE);
				zhugongsi.setENGLISH_NAME(ENGLISH_NAME);
				zhugongsi.setFORMER_NAME(FORMER_NAME);
				zhugongsi.setCREATE_DATE(CREATE_DATE);
				zhugongsi.setCREATE_ORG(CREATE_ORG);
				allZhuGongSi.add(zhugongsi);
				
			}
			
		}
	//	System.out.println("���ȣ�"+allZhuGongSi.size());
		//��ӡ����˾sql���
		for(T_CORPBean z:allZhuGongSi)
		{
			System.out.print("insert into T_CORP values( ");
			System.out.print(z.getORG()+", "+z.getID()+", "+z.getSEQ_ID()+", "+"'"+z.getREG_NO()+"', "+"'"+z.getCORP_NAME()+"', "+"'"+z.getADDR()+"', "+"'"+z.getBELONG_ORG()+"', "+"'"+z.getBELONG_DIST_ORG()+"', "+"'"+z.getBELONG_TRADE()+"', "
					+"'"+z.getECON_KIND()+"', "+"'"+z.getADMIT_MAIN()+"', "+"to_date('"+z.getSTART_DATE()+"','yyyy-mm-dd hh24:mi'), "+"to_date('"+z.getCHECK_DATE()+"','yyyy-mm-dd hh24:mi'), "+"'"+z.getOPER_MAN_IDENT_NO()+"', "+"'"+z.getOPER_MAN_NAME()+"', "
					+"'"+z.getCORP_STATUS()+"', "+z.getREG_CAPI()+", "+"to_date('"+z.getFARE_TERM_START()+"','yyyy-mm-dd hh24:mi'), "+"'"+z.getFARE_TERM_END()+"', "+"'"+z.getFARE_SCOPE()+"', "+"'"+z.getUNI_SCID()+"', "+"'"+z.getTEL()+"', "+"'"+z.getWEB_URL()+"', "
					+"'"+z.getEMAIL()+"', "+z.getPRAC_PERSON_NUM()+", "+"'"+z.getORG_INST_CODE()+"', "+"'"+z.getTAXPAY_NUM()+"', "+"'"+z.getSTAFF_SIZE()+"', "+"'"+z.getENGLISH_NAME()+"', "+z.getFORMER_NAME()+", "+"to_date('"+z.getCREATE_DATE()+"','yyyy-mm-dd hh24:mi'), "
					+z.getCREATE_ORG());
			System.out.println(" );");
			out.write(("insert into T_CORP values( "+z.getORG()+", "+z.getID()+", "+z.getSEQ_ID()+", "+"'"+z.getREG_NO()+"', "+"'"+z.getCORP_NAME()+"', "+"'"+z.getADDR()+"', "+"'"+z.getBELONG_ORG()+"', "+"'"+z.getBELONG_DIST_ORG()+"', "+"'"+z.getBELONG_TRADE()+"', "+"'"+z.getECON_KIND()+"', "+"'"+z.getADMIT_MAIN()+"', "+"to_date('"+z.getSTART_DATE()+"','yyyy-mm-dd hh24:mi'), "+"to_date('"+z.getCHECK_DATE()+"','yyyy-mm-dd hh24:mi'), "+"'"+z.getOPER_MAN_IDENT_NO()+"', "+"'"+z.getOPER_MAN_NAME()+"', "+"'"+z.getCORP_STATUS()+"', "+z.getREG_CAPI()+", "+"to_date('"+z.getFARE_TERM_START()+"','yyyy-mm-dd hh24:mi'), "+"'"+z.getFARE_TERM_END()+"', "+"'"+z.getFARE_SCOPE()+"', "+"'"+z.getUNI_SCID()+"', "+"'"+z.getTEL()+"', "+"'"+z.getWEB_URL()+"', "+"'"+z.getEMAIL()+"', "+z.getPRAC_PERSON_NUM()+", "+"'"+z.getORG_INST_CODE()+"', "+"'"+z.getTAXPAY_NUM()+"', "+"'"+z.getSTAFF_SIZE()+"', "+"'"+z.getENGLISH_NAME()+"', "+z.getFORMER_NAME()+", "+"to_date('"+z.getCREATE_DATE()+"','yyyy-mm-dd hh24:mi'), "+z.getCREATE_ORG()+" );\r\n").getBytes());
		}
		out.flush();
		out.close();
		out1.flush();
		out1.close();
	//	System.out.println("��������"+tal);
	}
}
