package edu.abc.raunjianbei.createdata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;

import edu.abc.ruanjianbei.model.bean.T_CORPBean;

public class T_CORP_DIST {
	
	private T_CORP corp;
	
	private int ORG;//�������ش���
	private int ID;//����ֹ�˾����
	private int SEQ_ID;//����˾����
	private String DIST_REG_NO;//��֧����ͳһ������ô���
	private String DIST_NAME;//��֧��������
	private String DIST_BELONG_ORG;
	private int DIST_CORP_ORG;
	private int DIST_CORP_ID;
	private int DIST_CORP_SEQ_ID;
	private String FARE_PLACE;//Ӫҵ����
	private String OPER_MAN_IDENT_NO;//������֤�����
	private String OPER_MAN_NAME;//����������
	private String FARE_SCOPE;//��Ӫ��Χ
	private String START_DATE;//��������
	private String CHECK_DATE;//��׼����
	private String CREATE_DATE;//����ʱ��
	private BufferedWriter bw;
	//����һ�����ϴ���������˾��ȫ����Ϣ
	public ArrayList<T_CORPBean> allZhuGongSi;
	
	public T_CORP_DIST() throws Exception {
		String firstname[]= {"��","Ǯ","��","��","��","��","֣","��","��","��","��","��","��","��","��","��","��","��"};
		String lastname1[]= {"��","��","��","�","��","��","��","��","��","ŵ","��","ҫ","��","��","��","ٻ","��","��","¹","��","��","��","��","ϼ"};
		String lastname2[]= {"ƽ","","չ","�","��","��","","","","ͩ","ͮ","��","ũ","","��","ٻ","��","��","","","","��","��","��"};
		
		String[] hubei={"�人","��ʯ","ʮ��","����","�˲�","����","����","Т��","����","�Ƹ�","����","����","��ʩ","����","Ǳ��","����","��ũ��"}; 
        String[] town= {"������","������","�ϳ���","������","���·","�����","��ƽ���","�����ֵ�","�ļ���ֵ�","������","������","������","������","��Ϫ�ֵ�","�Ļ��ֵ�","�ŵ��ֵ�","���ɽֵ�","��ɽ��","��¥��","�ٲ���","��������","ʤ��·","�˱���","��������","������"};
		
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
        
        //ʵ��������˾��Ϣ��
        corp=new T_CORP();
        allZhuGongSi=corp.allZhuGongSi;
        
        File fout = new File("E:\\file\\data\\T_CORP_DIST_DATA.txt");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fout);
			bw = new BufferedWriter(new OutputStreamWriter(fos));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(T_CORPBean corp:allZhuGongSi) {
			ORG=corp.getORG();
			SEQ_ID=corp.getSEQ_ID();
			DIST_CORP_ORG=corp.getORG();
			DIST_CORP_ID=corp.getID();
			DIST_CORP_SEQ_ID=corp.getSEQ_ID();
			
			String yue=null;
			String ri=null;
			Random random=new Random();
			int creditnum1=random.nextInt(10);
			int creditnum2=random.nextInt(10);
			int creditnum3=(int)((Math.random()*9+1)*100000);
			
//		}
//		
//        
//		for(int x=0;x<1;x++) {//ʡ�ݻ��ش���
//			String yue=null;
//			String ri=null;
//			ORG=876+x;
//			DIST_CORP_ORG=ORG;
//			Random random=new Random();
//			int creditnum1=random.nextInt(10);
//			
//			int companynum=random.nextInt(10);
//			for(int y=0;y<companynum;y++) {//ÿ��������˾�ĸ���
//				SEQ_ID=y+1;
//				DIST_CORP_ID=SEQ_ID+1000;
//				DIST_CORP_SEQ_ID=SEQ_ID;
//				int creditnum2=random.nextInt(10);
//				int creditnum3=(int)((Math.random()*9+1)*100000);
//				
				int distnum=random.nextInt(10);
				for(int z=0;z<distnum;z++) {//ÿ����˾�ֹ�˾����
					//������˾���
					ID=z+1000;
					
					//��֧����ͳһ������ô���
					/*   ͳһ������ô������Ϊ18λ��ʹ�ð��������ֻ�Ӣ����ĸ��ʾ�������������ɡ�
					 * ��һ���֣���1λ����Ϊ�Ǽǹ����Ŵ��룻
					 * �ڶ����֣���2λ����Ϊ��ҵ����˰�������룻
					 * �������֣���3-8λ����Ϊ�Ǽǹ���������������룻
					 * ���Ĳ��֣���9-17λ����Ϊ�����ʶ�룻
					 * ���岿�֣���18λ����ΪУ���룬��ϵͳ�Զ����ɡ�*/	
					int creditnum4=(int)((Math.random()*9+1)*100000000);
					String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
					char creditnum5=chars.charAt((int)(Math.random() * 26));
					DIST_REG_NO=""+creditnum1+creditnum2+creditnum3+creditnum4+creditnum5;
					//System.out.println("���ô��룺"+DIST_REG_NO);
					
					//������֤�����
					int s1=(int)((Math.random()*9+1)*100000);
					int s2=random.nextInt(80)+1918;				
					int s3=random.nextInt(12)+1;
					int s4=random.nextInt(28)+1;
					if(s3<10) {
						yue="0"+s3;
					}else {
						yue=""+s3;
					}
					if(s4<10) {
						ri="0"+s4;
					}else {
						ri=""+s4;
					}
					int s5=Math.round((random.nextFloat()+1)*1000);
					OPER_MAN_IDENT_NO=""+s1+s2+yue+ri+s5;
					//System.out.println("֤���ţ�"+OPER_MAN_IDENT_NO);
					
					//������
					int x1=random.nextInt(firstname.length);
					int y1=random.nextInt(lastname1.length);
					int z1=random.nextInt(lastname2.length);
					OPER_MAN_NAME=firstname[x1]+lastname1[y1]+lastname2[z1];
					//System.out.println("�����ˣ�"+OPER_MAN_NAME);
					
					//����
					int s6=random.nextInt(55)+1960;//��˾�������
					int s7=random.nextInt(24);//��˾����Сʱ
					int s8=random.nextInt(60);//��˾������
					START_DATE=""+s6+"/"+yue+"/"+ri+" "+s7+":"+s8;
					CHECK_DATE=""+s6+"/"+yue+"/"+ri+" "+s7+":"+s8;
					CREATE_DATE=corp.getCREATE_DATE();
					
					//System.out.println("�ֹ�˾��������"+CREATE_DATE);
					
					//Ӫҵ����
					int shiindex=random.nextInt(shi.length);
					int qvindex=random.nextInt(qv.length);
					int jiedaoindex=random.nextInt(jiedao.length);
					int menpaihao=random.nextInt(500);
					FARE_PLACE=shi[shiindex]+qv[qvindex]+jiedao[jiedaoindex]+menpaihao+"��";
					//System.out.println("Ӫҵ������"+FARE_PLACE);
					
					//��֧��������
					DIST_NAME=corp.getCORP_NAME()+shi[shiindex]+"�ֹ�˾";
					
					//��֧�����Ǽǻ���
					DIST_BELONG_ORG=shi[shiindex]+"��ҵ԰���г��ල�����";
					
					String sql="insert into T_CORP_DIST values("+ORG+", "+ID+", "+SEQ_ID+", '"+DIST_REG_NO+"', '"+DIST_NAME+"', '"+DIST_BELONG_ORG+"', "+DIST_CORP_ORG+", "+DIST_CORP_ID+", "+DIST_CORP_SEQ_ID+", '"+FARE_PLACE+"', '"+OPER_MAN_IDENT_NO+"', '"+OPER_MAN_NAME+"', '"+FARE_SCOPE+"', "+"to_date('"+START_DATE+"','yyyy-mm-dd hh24:mi')"+", "+"to_date('"+CHECK_DATE+"','yyyy-mm-dd hh24:mi')"+", "+"to_date('"+CREATE_DATE+"','yyyy-mm-dd hh24:mi')"+");";
					try {
						bw.write(sql);
						bw.newLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println(sql);
				}
				//System.out.println("----------------------------------------------");
			}
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}

	}
	
	public static void main(String[] args) throws Exception {
		new T_CORP_DIST();
	}
}
