package com.jrt.invokeLot.test.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CodeUtil {
//这段主函数没有什么用，我把它放到测试用例里面去了。
//public static void main(String[]a){
//	CodeUtil codeUtil=new CodeUtil();
//	String kkString=codeUtil.getCodeStringForLiJinTao("D3", "0001010203^0001010203^");
//    System.out.println(kkString);
//    String xxx="13445^39565^oiggi^";
//    String[] xx=xxx.split("\\^");
//    System.out.println(xx.length);
//    for(int i=0;i<xx.length;i++)
//    {
//    	System.out.println(xx[i]);
//    }
//    
//} 
private static final int TYPE_3D = 47103;
private static final int TYPE_SSQ = 47104;
//3.23caoyongxing
private static final int TYPE_SLC = 47102;
private static final String TYPE_SSC = "47101";

	// 彩种 玩法 倍数 注码
	/**
	 * 参数 caizhong 彩种 双色球 B001, 参数 betCode 注码
	 * 0001010203^0103010202^0212040506^0018070809^0208000901^ return 注码解析详情
	 */
	public static String getCodeString(String caizhong, String betCode) {
		String bet[] = betCode.split("\\^");
		String wan1 = "1512-F47104-00-01-";//双色球
		String wan2 = "1512-F47103-00-01-";//3D
		//3.23caoyongxing
		String wan3 = "1512-F47102-00-01-";//七乐彩
		String wan4 = "1512-F47101-00-01-";//时时彩
		StringBuffer stb = new StringBuffer();
		String wbet = "";
		String wanfa = "";
		//caoyongxing
		String wanslc="";
		String wanssc="";
		try {
			
			if ("B001".equals(caizhong)|| "F47104".equals(caizhong)) {
				stb.append("彩种:双色球,");
			} 
			//caoyongxing
			else if("QL730".equals(caizhong)|| "F47102".equals(caizhong)){
				stb.append("彩种:七乐彩");
			}
			else if("DT5".equals(caizhong)|| "F47101".equals(caizhong)){
				stb.append("彩种:时时彩");
			}
			else if("D3".equals(caizhong)){
				stb.append("彩种:3D");
			}
			if(("DT5".equals(caizhong)|| "F47101".equals(caizhong))&&(betCode.startsWith("2")||betCode.startsWith("9")||betCode.startsWith("d7")||betCode.startsWith("d8")))
			{
				//位选
				String wxinfo=getWeiXuanInfo(wan4+betCode);
				stb.append(wxinfo);
				return stb.toString();
			}
			else if("DT5".equals(caizhong)|| "F47101".equals(caizhong))
			{
				//普通的时时彩
				for(int i=0;i<bet.length;i++)
				{
					wbet=wan4+bet[i]+"^";
					String wfinfo=getSSCString(wbet);
					stb.append(wfinfo);
				}
				return stb.toString();
			}
			else if("D3".equals(caizhong)&&betCode.startsWith("20"))
			{
				//单选按位包号：2012050102030405^06060708091000^0701020304050607^
				String dxbh=getDanBaohao(betCode);
				stb.append(dxbh);
				return stb.toString();
			}
			
			for (int i = 0; i < bet.length; i++) {
				if ("B001".equals(caizhong) || "F47104".equals(caizhong)) {
					wbet = wan1 + bet[i] + "^";
					int[] nums = getPoolInfo(wbet);
					if (nums[0] == 0) {
						stb.append("玩法:单式,");
						stb.append("倍数:" + nums[1]);
						stb.append("红球:");
						for (int j = 4; j < nums.length - 1; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append("蓝球:" + nums[nums.length - 1] + "	 ");
					} else if (nums[0] == 10) {
						stb.append("玩法:红复蓝单,");
						stb.append("倍数:" + nums[1]);
						stb.append("红球:");
						for (int j = 4; j < nums.length - 1; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append("蓝球:" + nums[nums.length - 1] + " ");
					} else if (nums[0] == 20) {
						stb.append("玩法:红单蓝复,");
						stb.append("倍数:" + nums[1]);
						stb.append("红球:");
						for (int j = 4; j < 10; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append("蓝球:");
						for (int j = 10; j < nums.length; j++) {
							stb.append(nums[j] + ",");
						}
					} else if (nums[0] == 30) {
						stb.append("玩法:红复蓝复,");
						stb.append("倍数:" + nums[1]);
						stb.append("红球:");
						for (int j = 4; j < 4 + nums[2]; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append("蓝球:");
						for (int j = 4 + nums[2]; j < nums.length; j++) {
							stb.append(nums[j] + ",");
						}
					} else if (nums[0] == 40) {
						stb.append("玩法:红胆拖蓝单,");
						stb.append("倍数:" + nums[1]);
						stb.append("红球胆码:");
						for (int j = 5; j < 5 + nums[2]; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append("红球拖码:");
						for (int j = 5 + nums[2]; j < nums.length - 1; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append("蓝球:" + nums[nums.length - 1]);
					} else if (nums[0] == 50) {
						stb.append("玩法:红胆拖蓝复式,");
						stb.append("倍数:" + nums[1]);
						stb.append("红球胆码:");
						for (int j = 5; j < 5 + nums[2]; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append("红球拖码:");
						for (int j = 5 + nums[2]; j < 5 + nums[2] + nums[3]; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append("蓝球:");
						for (int j = 5 + nums[2] + nums[3]; j < nums.length; j++) {
							stb.append(nums[j] + ",");
						}
					}

				} else if ("D3".equals(caizhong) || "F47103".equals(caizhong)) {
					wbet = wan2 + bet[i] + "^";
					int[] nums = getPoolInfo(wbet);
					if (nums[0] == 0) {
						stb.append("玩法:直选单式,");
						stb.append("倍数:" + nums[1]);
						stb.append("注码:");
						for (int j = 3; j < nums.length; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append(" ");
					} else if (nums[0] == 1) {
						stb.append("玩法:组3单式,");
						stb.append("倍数:" + nums[1]);
						stb.append("注码:");
						for (int j = 3; j < nums.length; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append(" ");
					} else if (nums[0] == 2) {
						stb.append("玩法:组6单式,");
						stb.append("倍数:" + nums[1]);
						stb.append("注码:");
						for (int j = 3; j < nums.length; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append(" ");
					} else if (nums[0] == 10) {
						stb.append("玩法:直选和值,");
						stb.append("倍数:" + nums[1]);
						stb.append("和值:" + nums[nums.length - 1]);

					} else if (nums[0] == 11) {
						stb.append("玩法:组3和值,");
						stb.append("倍数:" + nums[1]);
						stb.append("和值:" + nums[nums.length - 1]);

					} else if (nums[0] == 12) {
						stb.append("玩法:组6和值,");
						stb.append("倍数:" + nums[1]);
						stb.append("和值:" + nums[nums.length - 1]);

					} else if (nums[0] == 31) {
						stb.append("玩法:组3复式,");
						stb.append("倍数:" + nums[1]);
						stb.append("注码:");
						for (int j = 3; j < nums.length; j++) {
							stb.append(nums[j] + ",");
						}
					} else if (nums[0] == 32) {
						stb.append("玩法:组6复式,");
						stb.append("倍数:" + nums[1]);
						stb.append("注码:");
						for (int j = 3; j < nums.length; j++) {
							stb.append(nums[j] + ",");
						}
					} else if (nums[0] == 54) {
						stb.append("玩法:胆拖复式,");
						stb.append("倍数:" + nums[1]);
						stb.append("胆码:");
						for (int j = 4; j < 4 + nums[2]; j++) {
							stb.append(nums[j] + ",");
						}
						stb.append("拖码:");
						for (int j = 4 + nums[2]; j < nums.length; j++) {
							stb.append(nums[j] + ",");
						}
					}
					
				}
				//3.23 caoyongxing
				else if ("QL730".equals(caizhong) || "F47102".equals(caizhong)){
					wanslc = wan3 + bet[i] + "^";
					int[] nums = getPoolInfo(wanslc);
					if(nums[0]==0)
					{
						stb.append("玩法：单式");
						stb.append("倍数："+nums[1]);
						stb.append("注码：");
						for(int x=2;x<9;x++)
						{
							stb.append(nums[x]+",");
						}
					}
					if(nums[0]==10)
					{
						stb.append("玩法：复式");
						stb.append("倍数："+nums[1]);
						stb.append("注码：");
						for(int x=2;x<nums.length;x++)
						{
							stb.append(nums[x]+",");
						}
					}
					if(nums[0]==20)
					{
						stb.append("玩法：胆拖");
						stb.append("倍数："+nums[1]);
						stb.append("胆码：");
						
						for(int x=2;x<nums.length;x++)
						{
							if(nums[x]==-1)
							{
								stb.append("拖码：");
								continue;
							}
							stb.append(nums[x]+",");
						}
					
					}
				
					
				}
				
					
			}
		} catch (Exception e) {
			return "注码不合法:" + betCode;
		}
		return stb.toString();
	}

	private static String getDanBaohao(String betcode) {
		// TODO Auto-generated method stub
		String wei[]=betcode.split("\\^");
		StringBuffer codestring=new StringBuffer();
		codestring.append("玩法：单选按位包号");
	     
		int beinum=Integer.parseInt(wei[0].substring(2,4));
		codestring.append("倍数："+beinum+" ");
		int onenum=Integer.parseInt(wei[0].substring(4,6));
		int twonum=Integer.parseInt(wei[1].substring(0,2));
		int threenum=Integer.parseInt(wei[2].substring(0,2));
		codestring.append("第一位包号为:");
		for(int i=0;i<onenum;i++)
		{
			int zhuma=Integer.parseInt(wei[0].substring(6+2*i,8+2*i));
			codestring.append(zhuma+",");
		}
		codestring.append("第二位包号为：");
		for(int i=0;i<twonum;i++)
		{
			int zhuma=Integer.parseInt(wei[1].substring(2+2*i,4+2*i));
			codestring.append(zhuma+",");
		}
		codestring.append("第三位包号为：");
		for(int i=0;i<threenum;i++)
		{
			int zhuma=Integer.parseInt(wei[2].substring(2+2*i,4+2*i));
			codestring.append(zhuma+",");
		}
		
		return codestring.toString();
	}

	public static String getCodeStringForLiJinTao(String caizhong, String betCode) {
		   String bet[] = betCode.split("\\^");
			String wan1 = "1512-F47104-00-01-";
			String wan2 = "1512-F47103-00-01-";
			StringBuffer stb = new StringBuffer();
			String wbet = "";
			String wanfa = "";
			try {
//				if ("B001".equals(caizhong)) {
//					stb.append("彩种:双色球,");
//				} else {
//					stb.append("彩种:3D");
//				}
				for (int i = 0; i < bet.length; i++) {
					if ("B001".equals(caizhong) || "F47104".equals(caizhong)) {
						wbet = wan1 + bet[i] + "^";
						int[] nums = getPoolInfo(wbet);
						if (nums[0] == 0) {
//							stb.append("玩法:单式,");
//							stb.append("倍数:" + nums[1]);
//							stb.append("红球:");
							for (int j = 4; j < nums.length - 1; j++) {
								
								if(j==nums.length-2){
									stb.append(nums[j]+" ");
								}else{
									stb.append(nums[j] + ",");
								}
							}
							stb.append("+" + nums[nums.length - 1] + ";");
						} else if (nums[0] == 10) {
//							stb.append("玩法:红复蓝单,");
//							stb.append("倍数:" + nums[1]);
//							stb.append("红球:");
							for (int j = 4; j < nums.length - 1; j++) {
								stb.append(nums[j] + ",");
							}
//							stb.append("~" + nums[nums.length - 1] + " ");
						} else if (nums[0] == 20) {
//							stb.append("玩法:红单蓝复,");
//							stb.append("倍数:" + nums[1]);
//							stb.append("红球:");
							for (int j = 4; j < 10; j++) {
								stb.append(nums[j] + ",");
							}
							stb.append("+");
							for (int j = 10; j < nums.length; j++) {
								stb.append(nums[j] + ",");
							}
						} else if (nums[0] == 30) {
//							stb.append("玩法:红复蓝复,");
//							stb.append("倍数:" + nums[1]);
//							stb.append("红球:");
							for (int j = 4; j < 4 + nums[2]; j++) {
								stb.append(nums[j] + ",");
							}
							stb.append("+");
							for (int j = 4 + nums[2]; j < nums.length; j++) {
								stb.append(nums[j] + ",");
							}
						} else if (nums[0] == 40) {
//							stb.append("玩法:红胆拖蓝单,");
//							stb.append("倍数:" + nums[1]);
//							stb.append("红球胆码:");
							for (int j = 5; j < 5 + nums[2]; j++) {
								stb.append(nums[j] + ",");
							}
//							stb.append("红球拖码:");
							for (int j = 5 + nums[2]; j < nums.length - 1; j++) {
								stb.append(nums[j] + ",");
							}
							stb.append("+" + nums[nums.length - 1]);
						} else if (nums[0] == 50) {
//							stb.append("玩法:红胆拖蓝复式,");
//							stb.append("倍数:" + nums[1]);
//							stb.append("红球胆码:");
							for (int j = 5; j < 5 + nums[2]; j++) {
								stb.append(nums[j] + ",");
							}
//							stb.append("红球拖码:");
							for (int j = 5 + nums[2]; j < 5 + nums[2] + nums[3]; j++) {
								stb.append(nums[j] + ",");
							}
							stb.append("+");
							for (int j = 5 + nums[2] + nums[3]; j < nums.length; j++) {
								stb.append(nums[j] + ",");
							}
						}

					} else if ("D3".equals(caizhong) || "F47103".equals(caizhong)) {
						wbet = wan2 + bet[i] + "^";
						int[] nums = getPoolInfo(wbet);
						if (nums[0] == 0) {
							stb.append("直选单式");
//							stb.append("倍数:" + nums[1]);
//							stb.append("注码:");
							for (int j = 3; j < nums.length; j++) {
								if(j==nums.length-1){
									stb.append(nums[j]);
								}else{
									stb.append(nums[j] + ",");
								}
							}
							stb.append("; ");
						} else if (nums[0] == 1) {
							stb.append("组3单式");
//							stb.append("倍数:" + nums[1]);
//							stb.append("注码:");
							for (int j = 3; j < nums.length; j++) {
								stb.append(nums[j] + ",");
							}
							stb.append(" ");
						} else if (nums[0] == 2) {
							stb.append("组6单式");
//							stb.append("倍数:" + nums[1]);
//							stb.append("注码:");
							for (int j = 3; j < nums.length; j++) {
								stb.append(nums[j] + ",");
							}
							stb.append(" ");
						} else if (nums[0] == 10) {
//							stb.append("玩法:直选和值,");
//							stb.append("倍数:" + nums[1]);
							stb.append("和值:" + nums[nums.length - 1]);

						} else if (nums[0] == 11) {
//							stb.append("玩法:组3和值,");
//							stb.append("倍数:" + nums[1]);
							stb.append("和值:" + nums[nums.length - 1]);

						} else if (nums[0] == 12) {
//							stb.append("玩法:组6和值,");
//							stb.append("倍数:" + nums[1]);
							stb.append("和值:" + nums[nums.length - 1]);

						} else if (nums[0] == 31) {
//							stb.append("玩法:组3复式,");
//							stb.append("倍数:" + nums[1]);
							stb.append("注码:");
							for (int j = 3; j < nums.length; j++) {
								stb.append(nums[j] + ",");
							}
						} else if (nums[0] == 32) {
//							stb.append("玩法:组6复式,");
//							stb.append("倍数:" + nums[1]);
//							stb.append("注码:");
							for (int j = 3; j < nums.length; j++) {
								stb.append(nums[j] + ",");
							}
						} else if (nums[0] == 54) {
//							stb.append("玩法:胆拖复式,");
//							stb.append("倍数:" + nums[1]);
//							stb.append("胆码:");
							for (int j = 4; j < 4 + nums[2]; j++) {
								stb.append(nums[j] + ",");
							}
//							stb.append("拖码:");
							for (int j = 4 + nums[2]; j < nums.length; j++) {
								stb.append(nums[j] + ",");
							}
						}
					}
				}
			} catch (Exception e) {
				return "注码不合法:" + betCode;
			}
			return stb.toString();
	   }
	
	public  String getCodeStringForZhanzhou(String caizhong,String betCode){
	String bet[]=betCode.split("\\^");
	String wan1="1512-F47104-00-01-";
	String wan2="1512-F47103-00-01-";
	StringBuffer stb=new StringBuffer();
	String wbet="";
	String wanfa="";
try{
	if("B001".equals(caizhong)){
		//stb.append("彩种:双色球,");
	}else{
		//stb.append("彩种:3D");
	}
	for(int i=0;i<bet.length;i++){
		if("B001".equals(caizhong)||"F47104".equals(caizhong)){
			wbet=wan1+bet[i]+"^";
			int []nums=this.getPoolInfo(wbet);
			if(nums[0]==0){
				//stb.append("玩法:单式,");
				stb.append("倍数:"+nums[1]);
				stb.append("红球:");
				for(int j=4;j<nums.length-1;j++){
					stb.append(nums[j]+",");
				}
				stb.append("蓝球:"+nums[nums.length-1]+"	 ");
			}else if(nums[0]==10){
				//stb.append("玩法:红复蓝单,");
				stb.append("倍数:"+nums[1]);
				stb.append("红球:");
				for(int j=4;j<nums.length-1;j++){
					stb.append(nums[j]+",");
				}
				stb.append("蓝球:"+nums[nums.length-1]+" ");
			}else if(nums[0]==20){
				//stb.append("玩法:红单蓝复,");
				stb.append("倍数:"+nums[1]);
				stb.append("红球:");
				for(int j=4;j<10;j++){
					stb.append(nums[j]+",");
				}
				stb.append("蓝球:");
				for(int j=10;j<nums.length;j++){
					stb.append(nums[j]+",");
				}
			}else if(nums[0]==30){
				//stb.append("玩法:红复蓝复,");
				stb.append("倍数:"+nums[1]);
				stb.append("红球:");
				for(int j=4;j<4+nums[2];j++){
					stb.append(nums[j]+",");
				}
				stb.append("蓝球:");
				for(int j=4+nums[2];j<nums.length;j++){
					stb.append(nums[j]+",");
				}
			}else if(nums[0]==40){
				//stb.append("玩法:红胆拖蓝单,");
				stb.append("倍数:"+nums[1]);
				stb.append("红球胆码:");
				for(int j=5;j<5+nums[2];j++){
					stb.append(nums[j]+",");
				}
				stb.append("红球拖码:");
				for(int j=5+nums[2];j<nums.length-1;j++){
					stb.append(nums[j]+",");
				}
				stb.append("蓝球:"+nums[nums.length-1]);
			}else if(nums[0]==50){
				//stb.append("玩法:红胆拖蓝复式,");
				stb.append("倍数:"+nums[1]);
				stb.append("红球胆码:");
				for(int j=5;j<5+nums[2];j++){
					stb.append(nums[j]+",");
				}
				stb.append("红球拖码:");
				for(int j=5+nums[2];j<5+nums[2]+nums[3];j++){
					stb.append(nums[j]+",");
				}
				stb.append("蓝球:");
				for(int j=5+nums[2]+nums[3];j<nums.length;j++){
					stb.append(nums[j]+",");
				}
			}
			
			
		}else if("D3".equals(caizhong)||"F47103".equals(caizhong)){
			wbet=wan2+bet[i]+"^";
			int []nums=this.getPoolInfo(wbet);
			if(nums[0]==0){
				//stb.append("玩法:直选单式,");
				stb.append("倍数:"+nums[1]);
				stb.append("注码:");
				for(int j=3;j<nums.length;j++){
					stb.append(nums[j]+",");
				}
				stb.append(" ");
			}else if(nums[0]==1){
				//stb.append("玩法:组3单式,");
				stb.append("倍数:"+nums[1]);
				stb.append("注码:");
				for(int j=3;j<nums.length;j++){
					stb.append(nums[j]+",");
				}
				stb.append(" ");
			}else if(nums[0]==2){
				//stb.append("玩法:组6单式,");
				stb.append("倍数:"+nums[1]);
				stb.append("注码:");
				for(int j=3;j<nums.length;j++){
					stb.append(nums[j]+",");
				}
				stb.append(" ");
			}else if(nums[0]==10){
				//stb.append("玩法:直选和值,");
				stb.append("倍数:"+nums[1]);
				stb.append("和值:"+nums[nums.length-1]);
			    
			}else if(nums[0]==11){
				//stb.append("玩法:组3和值,");
				stb.append("倍数:"+nums[1]);
				stb.append("和值:"+nums[nums.length-1]);
			    
			}else if(nums[0]==12){
				//stb.append("玩法:组6和值,");
				stb.append("倍数:"+nums[1]);
				stb.append("和值:"+nums[nums.length-1]);
			    
			}else if(nums[0]==31){
				//stb.append("玩法:组3复式,");
				stb.append("倍数:"+nums[1]);
				stb.append("注码:");
				for(int j=3;j<nums.length;j++){
					stb.append(nums[j]+",");
				}
			}else if(nums[0]==32){
				//stb.append("玩法:组6复式,");
				stb.append("倍数:"+nums[1]);
				stb.append("注码:");
				for(int j=3;j<nums.length;j++){
					stb.append(nums[j]+",");
				}
			}else if(nums[0]==54){
				//stb.append("玩法:胆拖复式,");
				stb.append("倍数:"+nums[1]);
				stb.append("胆码:");
				for(int j=4;j<4+nums[2];j++){
					stb.append(nums[j]+",");
				}
				stb.append("拖码:");
				for(int j=4+nums[2];j<nums.length;j++){
					stb.append(nums[j]+",");
				}
			}
		}
	}
}catch (Exception e) {
	return "注码不合法:"+betCode;
}
	return stb.toString();
}
	
	/**
	 * 
	 * 用于解析时时彩位选，任选的注码
	 * @param 注码信息
	 * @return
	 */
	
	public static String getWeiXuanInfo(String s){
		String type = null;//彩种
		String t_stype = null;//玩法
		String t_selecttype=null;//选码类型
		String t_smul = null;//倍数
		StringBuffer result = new StringBuffer("");
		int t_add = 1;
//		System.out.println("s="+s);
		type = s.substring(5+t_add,10+t_add);
//		System.out.println("type:"+type);
		t_stype = s.substring(17+t_add,18+t_add);
		t_selecttype=s.substring(18+t_add,19+t_add);
		t_smul = s.substring(19+t_add,23+t_add);
		
		//caoyongxing
		String[] wfinfo=getWFInfo(t_stype,t_selecttype);
		result.append("玩法："+wfinfo[0]);
		result.append("选码类型："+wfinfo[1]);
		result.append("倍数："+Integer.valueOf(t_smul));
		String[] wei=s.split("\\^");
		int len=wei.length;
		
			if(t_stype=="2")
			{
				
				int geshu=Integer.parseInt(wei[0].substring(24,26));
				result.append("第1位:"+geshu+"个数:");
				for(int i=0;i<geshu;i++)
				{
					int temp=Integer.parseInt(wei[0].substring(26+i*2,28+i*2));
					result.append("为"+temp+",");
				}
				
				for(int i=1;i<len;i++)
				{
					result.append("第"+i+1+"位:");
					int geshu1=Integer.parseInt(wei[i].substring(0,2));
					result.append(geshu1+"个数:");
					for(int j=0;j<geshu;j++)
					{
						int temp=Integer.parseInt(wei[i].substring(2+j*2,4+j*2));
						result.append("为"+temp+",");
					}
				}
			}
			if(t_stype=="9")
			{
				
				
				int geshu=Integer.parseInt(wei[0].substring(24,26));
				result.append("第1位:"+geshu+"个数,");
				for(int i=0;i<geshu;i++)
				{
					int temp=Integer.parseInt(wei[0].substring(26+i*2,28+i*2));
					result.append("为"+temp+",");
				}
				
				for(int i=1;i<len;i++)
				{
					result.append("第"+i+1+"位:");
					int geshu1=Integer.parseInt(wei[i].substring(0,2));
					result.append(geshu1+"个数,");
					for(int j=0;j<geshu;j++)
					{
						int temp=Integer.parseInt(wei[i].substring(2+j*2,4+j*2));
						result.append("为"+temp+",");
					}
				}
			}
			
			if(t_stype=="d"&&(t_selecttype=="7"||t_selecttype=="8"))
			{
				String wz=s.substring(24,26);
				result.append("位置："+getWZInfo(wz));
				int geshu=Integer.parseInt(wei[0].substring(26,28));
				result.append("第1位:"+geshu+"个数,");
				for(int i=0;i<geshu;i++)
				{
					int temp=Integer.parseInt(wei[0].substring(28+i*2,30+i*2));
					result.append("为"+temp+",");
				}
				
				for(int i=1;i<len;i++)
				{
					result.append("第"+i+1+"位:");
					int geshu1=Integer.parseInt(wei[i].substring(0,2));
					result.append(geshu1+"个数,");
					for(int j=0;j<geshu;j++)
					{
						int temp=Integer.parseInt(wei[i].substring(2+j*2,4+j*2));
						result.append("为"+temp+",");
					}
				}
			}
			return result.toString();
			
			
			
			
		}
	public static String getWZInfo(String wz)
	{
		String rewz="";
		
		if(wz.equals("01"))
		{
			rewz="个位";
		}
		if(wz.equals("02"))
		{
			rewz="十位";
		}
		if(wz.equals("04"))
		{
			rewz="百位";
		}
		if(wz.equals("08"))
		{
			rewz="千位";
		}
		if(wz.equals("16"))
		{
			rewz="万位";
		}
		//任2
		if(wz.equals("03"))
		{
			rewz="十个";
		}
		if(wz.equals("05"))
		{
			rewz="百个";
		}
		if(wz.equals("06"))
		{
			rewz="百十";
		}
		if(wz.equals("09"))
		{
			rewz="千个";
		}
		if(wz.equals("10"))
		{
			rewz="千十";
		}
		if(wz.equals("12"))
		{
			rewz="千百";
		}
		if(wz.equals("17"))
		{
			rewz="万个";
		}
		if(wz.equals("18"))
		{
			rewz="万十";
		}
		if(wz.equals("20"))
		{
			rewz="万百";
		}
		if(wz.equals("24"))
		{
			rewz="万千";
		}
	
		//任选3位
		if(wz.equals("07"))
		{
			rewz="百十个";
		}
		if(wz.equals("11"))
		{
			rewz="千十个";
		}
		if(wz.equals("13"))
		{
			rewz="千百个";
		}
		if(wz.equals("14"))
		{
			rewz="千百十";
		}
		if(wz.equals("19"))
		{
			rewz="万十个";
		}
		if(wz.equals("21"))
		{
			rewz="万百个";
		}
		if(wz.equals("22"))
		{
			rewz="万百十";
		}
		if(wz.equals("25"))
		{
			rewz="万千个";
		}
		if(wz.equals("26"))
		{
			rewz="万千十";
		}
		if(wz.equals("28"))
		{
			rewz="万千百";
		}
		return rewz;
	}
	//时时彩的玩法
	public static String[] getWFInfo(String wf,String xuan)
	{
		String[] rewf=new String[2];
		rewf[0]="";
		rewf[1]="";
		
		if(wf.equals("0"))
		{
			rewf[0]="单式";
			if(xuan.equals("0"))
			{
				rewf[1]="一星";
			}
			if(xuan.equals("1"))
			{
				rewf[1]="二星";
			}
			if(xuan.equals("2"))
			{
				rewf[1]="三星";
			}
			if(xuan.equals("3"))
			{
				rewf[1]="四星";
			}
			if(xuan.equals("4"))
			{
				rewf[1]="五星";
			}
			
		}
		if(wf.equals("1"))
		{
			rewf[0]="复式";
			if(xuan.equals("1"))
			{
				rewf[1]="二星";
			}
			if(xuan.equals("2"))
			{
				rewf[1]="三星";
			}
			if(xuan.equals("3"))
			{
				rewf[1]="四星";
			}
			if(xuan.equals("4"))
			{
				rewf[1]="五星";
			}
			
		}
		if(wf.equals("2"))
		{
			rewf[0]="位选";
			if(xuan.equals("0"))
			{
				rewf[1]="一星";
			}
			if(xuan.equals("1"))
			{
				rewf[1]="二星";
			}
			if(xuan.equals("2"))
			{
				rewf[1]="三星";
			}
			if(xuan.equals("3"))
			{
				rewf[1]="四星";
			}
			if(xuan.equals("4"))
			{
				rewf[1]="五星";
			}
			
		}
		if(wf.equals("3"))
		{
			rewf[0]="大小奇偶";
			if(xuan.equals("1"))
			{
				rewf[1]="固定二星";
			}
			
		}
		if(wf.equals("4"))
		{
			rewf[0]="组选单式";
			if(xuan.equals("1"))
			{
				rewf[1]="二星组选";
			}
			if(xuan.equals("2"))
			{
				rewf[1]="三星组三";
			}
			if(xuan.equals("3"))
			{
				rewf[1]="三星组六";
			}
			if(xuan.equals("4"))
			{
				rewf[1]="四星组四";
			}
			if(xuan.equals("5"))
			{
				rewf[1]="四星组选六";
			}
			if(xuan.equals("6"))
			{
				rewf[1]="四星组选十二";
			}
			if(xuan.equals("7"))
			{
				rewf[1]="四星组选二十四";
			}
			if(xuan.equals("8"))
			{
				rewf[1]="五星组选五";
			}
			if(xuan.equals("9"))
			{
				rewf[1]="五星组选十";
			}
			if(xuan.equals("a"))
			{
				rewf[1]="五星组选二十";
			}
			if(xuan.equals("b"))
			{
				rewf[1]="五星组选三十";
			}
			if(xuan.equals("c"))
			{
				rewf[1]="五星组选六十";
			}
			if(xuan.equals("d"))
			{
				rewf[1]="五星组选一百二十";
			}
		}
		//任2
		if(wf.equals("5"))
		{
			rewf[0]="五星通选";
			if(xuan.equals("4"))
			{
				rewf[1]="固定五星";
			}
		}
		if(wf.equals("6"))
		{
			rewf[0]="组选复式";
			if(xuan.equals("1"))
			{
				rewf[1]="二星";
			}
			if(xuan.equals("2"))
			{
				rewf[1]="三星组选三";
			}
			if(xuan.equals("3"))
			{
				rewf[1]="三星组选六";
			}
			if(xuan.equals("4"))
			{
				rewf[1]="四星组选4复式";
			}
			if(xuan.equals("4"))
			{
				rewf[1]="四星组选4复式";
			}
			if(xuan.equals("5"))
			{
				rewf[1]="四星组选6复式";
			}
			if(xuan.equals("6"))
			{
				rewf[1]="四星组选12复式";
			}
			if(xuan.equals("7"))
			{
				rewf[1]="四星组选24复式";
			}
			if(xuan.equals("8"))
			{
				rewf[1]="五星星组选5复式";
			}
			if(xuan.equals("9"))
			{
				rewf[1]="五星组选10复式";
			}
			if(xuan.equals("a"))
			{
				rewf[1]="五星组选20复式";
			}
			if(xuan.equals("b"))
			{
				rewf[1]="五星组选30复式";
			}
			if(xuan.equals("c"))
			{
				rewf[1]="五星组选60复式";
			}
			if(xuan.equals("d"))
			{
				rewf[1]="五星组选120复式";
			}
			
			
		}
		if(wf.equals("7"))
		{
			rewf[0]="组选包点";
			if(xuan.equals("1"))
			{
				rewf[1]="二星组选包点";
			}
			if(xuan.equals("2"))
			{
				rewf[1]="三星组三包点";
			}
			if(xuan.equals("3"))
			{
				rewf[1]="三星组六包点";
			}
		}
		if(wf.equals("8"))
		{
			if(xuan.equals("1"))
			{
				rewf[1]="二星";
			}
		}
		if(wf.equals("9"))
		{
			rewf[0]="二星组选分位";
			if(xuan.equals("1"))
			{
				rewf[1]="二星";
			}
		}
		if(wf.equals("a"))
		{
			rewf[0]="保点";
			if(xuan.equals("1"))
			{
				rewf[1]="二星";
			}
			if(xuan.equals("2"))
			{
				rewf[1]="三星";
			}
		}
		if(wf.equals("b"))
		{
			rewf[0]="三星包对";
			if(xuan.equals("0"))
			{
				rewf[1]="固定";
			}
		}
		if(wf.equals("c"))
		{
			rewf[0]="组选胆拖";
			if(xuan.equals("4"))
			{
				rewf[1]="四星组选4胆拖";
			}
			if(xuan.equals("5"))
			{
				rewf[1]="四星组选6胆拖";
			}
			if(xuan.equals("6"))
			{
				rewf[1]="四星组选12胆拖";
			}
			if(xuan.equals("7"))
			{
				rewf[1]="四星组选24胆拖";
			}
			if(xuan.equals("8"))
			{
				rewf[1]="五星组选5胆拖";
			}
			if(xuan.equals("9"))
			{
				rewf[1]="五星组选10胆拖";
			}
			if(xuan.equals("a"))
			{
				rewf[1]="五星组选20胆拖";
			}
			if(xuan.equals("b"))
			{
				rewf[1]="五星组选30胆拖";
			}
			if(xuan.equals("c"))
			{
				rewf[1]="五星组选60胆拖";
			}
			if(xuan.equals("d"))
			{
				rewf[1]="五星组选120胆拖";
			}
		}
		if(wf.equals("d"))
		{
			rewf[0]="任选";
			if(xuan.equals("0"))
			{
				rewf[1]="任选一";
			}
			if(xuan.equals("1"))
			{
				rewf[1]="任选二";
			}
			if(xuan.equals("2"))
			{
				rewf[1]="任选三";
			}
			if(xuan.equals("3"))
			{
				rewf[1]="任选二包点";
			}
			if(xuan.equals("4"))
			{
				rewf[1]="任选三包点";
			}
			if(xuan.equals("5"))
			{
				rewf[1]="任选二跨度";
			}
			if(xuan.equals("6"))
			{
				rewf[1]="任选三跨度";
			}
			if(xuan.equals("7"))
			{
				rewf[1]="任选二位选";
			}if(xuan.equals("2"))
			{
				rewf[1]="任选三位选";
			}
		}
		if(wf.equals("e"))
		{
			rewf[0]="趣味/区间";
			if(xuan.equals("0"))
			{
				rewf[1]="趣味二星";
			}
			if(xuan.equals("1"))
			{
				rewf[1]="区间二星";
			}
			if(xuan.equals("2"))
			{
				rewf[1]="趣味二星包点";
			}
			if(xuan.equals("3"))
			{
				rewf[1]="区间二星包点";
			}
			if(xuan.equals("4"))
			{
				rewf[1]="趣味二星位选";
			}
			if(xuan.equals("5"))
			{
				rewf[1]="区间二星位选";
			}
		}
	
		if(wf.equals("f"))
		{
			rewf[0]="五星组选重号";
			if(xuan.equals("0"))
			{
				rewf[1]="好事成双";
			}
			if(xuan.equals("1"))
			{
				rewf[1]="三星报喜";
			}
			if(xuan.equals("2"))
			{
				rewf[1]="四季发财";
			}
		}
		
		return rewf;
	}
	
	/**
	 * 
	 * 用于解析时时彩的注码
	 * @param 注码信息
	 * @return
	 */
	
	
	public static ArrayList getSSCInfo(String s){
		//		System.out.println(s);
		String type = null;//彩种
		String t_stype = null;//玩法
		String t_selecttype=null;//选码类型
		String t_smul = null;//倍数
		ArrayList t_Pin = new ArrayList();
		int t_add = 1;
		type = s.substring(5+t_add,10+t_add);
		t_stype = s.substring(17+t_add,18+t_add);
		t_selecttype=s.substring(18+t_add,19+t_add);
		t_smul = s.substring(19+t_add,23+t_add);
		
		//caoyongxing
		if(type.equals(TYPE_SSC)){
			if(t_stype.equals("c"))
			{
				int len=s.length();
				int dan=s.indexOf("*");
				int danma=((dan-1)-24+1)/2;
				int tuoma=((len-1)-(dan+1)+1)/2;
				t_Pin.add(t_stype);
				t_Pin.add(t_selecttype);
				t_Pin.add(t_smul);
				for(int i=0;i<danma;i++)
				{
					//String s1="1512-F47102-00-01- 这是胆拖码2001010203*04050607080910^";
					
					t_Pin.add(s.substring(24+i*2,26+i*2));
				}
				t_Pin.add("拖码：");//区分胆码和托码的标志
				
				for(int i=0;i<tuoma;i++)
				{
					//String s1="1512-F47102-00-01- 这是胆拖码2001010203*04050607080910^";
					
					t_Pin.add(s.substring(dan+1+i*2,dan+3+i*2));
				}
			}
			else 
			{
				
				t_Pin.add(t_stype);
				t_Pin.add(t_selecttype);
				t_Pin.add(t_smul);
				int chang=(s.length()-24)/2;
				for(int i=0;i<chang;i++)
				{
					t_Pin.add(s.substring(24+i*2,24+2+i*2));
				}
			}
		}
			
		return t_Pin;
	}
	
	public static String getSSCString(String s)
	{
		
		ArrayList<String> list=getSSCInfo(s);
		StringBuffer result=new StringBuffer("");
		String[] wfinfo=getWFInfo(list.get(0),list.get(1));
		result.append("玩法："+wfinfo[0]);
		result.append("选码类型："+wfinfo[1]);
		result.append("倍数："+Integer.valueOf(list.get(2)));
		if(list.get(0).equals("c"))
		{
			result.append("胆码：");
			for(int i=3;i<list.size();i++)
			{
				result.append(list.get(i));
			}
		}
		else if(list.get(0).equals("e"))
		{
			if(list.get(1).equals("0")||list.get(1).equals("2"))
			{
				if(list.get(3).equals("00"))
				{
					result.append("百位选小号");
				}
				if(list.get(3).equals("01"))
				{
					result.append("百位选大号");
				}
				
			}
			if(list.get(1).equals("1")||list.get(1).equals("3"))
			{
				if(list.get(3).equals("00"))
				{
					result.append("百位选一区");
				}
				if(list.get(3).equals("01"))
				{
					result.append("百位选二区");
				}
				if(list.get(3).equals("02"))
				{
					result.append("百位选三区");
				}
				if(list.get(3).equals("03"))
				{
					result.append("百位选四区");
				}
				if(list.get(3).equals("04"))
				{
					result.append("百位选五区");
				}
				
			}
			result.append("注码："+list.get(4));
		}
		else
		{
			result.append("注码：");
			for(int i=3;i<list.size();i++)
			{
				result.append(list.get(i)+",");
			}
			
		}
		return result.toString();
		
	}

	

	public static int[] getPoolInfo(String s){
	//		System.out.println(s);
	int type = 0;//彩种
	int t_stype = 0;//玩法
	int t_smul = 0;//倍数
	String t_redN = "";//红色球字符串
	String t_blueN = "";//蓝色球字符串
	String t_tuoN = "";//拖码球
	StringBuffer t_sb = new StringBuffer("");
	int [] t_Pin = null;
	int t_add = 1;
//	System.out.println("s="+s);
	type = Integer.parseInt(s.substring(5+t_add,10+t_add));
//	System.out.println("type:"+type);
	t_stype = Integer.parseInt(s.substring(17+t_add,19+t_add));
	t_smul = Integer.parseInt(s.substring(19+t_add,21+t_add));
	if(type == TYPE_SSQ){
		if(t_stype == 00){//红单蓝单 
			for (int i = 21+t_add; i < s.length(); i++) {
				if(s.charAt(i)=='~'){
					t_redN = t_sb.toString();
					t_sb = null;
					t_sb = new StringBuffer("");
					continue;
				}
				if(s.charAt(i)=='^'){
					t_blueN = t_sb.toString();
					t_sb = null;
					t_sb = new StringBuffer("");
					break;
				}
				t_sb.append(s.charAt(i));
			}

			t_Pin = new int[4+(t_redN.length()/2)+(t_blueN.length()/2)];
			t_Pin[0] = t_stype;
			t_Pin[1] = t_smul;
			t_Pin[2] = t_redN.length()/2;
			t_Pin[3] = t_blueN.length()/2;
			for (int i = 0; i < t_redN.length(); i+=2) {
				t_Pin[4+i/2] = Integer.parseInt(t_redN.substring(i,i+2));
			}
			for (int i = 0; i < t_blueN.length(); i+=2) {
				t_Pin[4+t_Pin[2]+i/2] = Integer.parseInt(t_blueN.substring(i,i+2));
			}
		}else if(t_stype == 10){//红复蓝单
			for (int i = 22+t_add; i < s.length(); i++) {
				if(s.charAt(i)=='~'){
					t_redN = t_sb.toString();
					t_sb = null;
					t_sb = new StringBuffer("");
					continue;
				}
				if(s.charAt(i)=='^'){
					t_blueN = t_sb.toString();
					t_sb = null;
					t_sb = new StringBuffer("");
					break;
				}
				t_sb.append(s.charAt(i));
			}

			t_Pin = new int[4+(t_redN.length()/2)+(t_blueN.length()/2)];
			t_Pin[0] = t_stype;
			t_Pin[1] = t_smul;
			t_Pin[2] = t_redN.length()/2;
			t_Pin[3] = t_blueN.length()/2;
			for (int i = 0; i < t_redN.length(); i+=2) {
				t_Pin[4+i/2] = Integer.parseInt(t_redN.substring(i,i+2));
			}
			for (int i = 0; i < t_blueN.length(); i+=2) {
				t_Pin[4+t_Pin[2]+i/2] = Integer.parseInt(t_blueN.substring(i,i+2));
			}
		}else if(t_stype == 20){//红单蓝复 
			for (int i = 22+t_add; i < s.length(); i++) {
				if(s.charAt(i)=='~'){
					t_redN = t_sb.toString();
					t_sb = null;
					t_sb = new StringBuffer("");
					continue;
				}
				if(s.charAt(i)=='^'){
					t_blueN = t_sb.toString();
					t_sb = null;
					t_sb = new StringBuffer("");
					break;
				}
				t_sb.append(s.charAt(i));
			}

			t_Pin = new int[4+(t_redN.length()/2)+(t_blueN.length()/2)];
			t_Pin[0] = t_stype;
			t_Pin[1] = t_smul;
			t_Pin[2] = t_redN.length()/2;
			t_Pin[3] = t_blueN.length()/2;
			for (int i = 0; i < t_redN.length(); i+=2) {
				t_Pin[4+i/2] = Integer.parseInt(t_redN.substring(i,i+2));
			}
			for (int i = 0; i < t_blueN.length(); i+=2) {
				t_Pin[4+t_Pin[2]+i/2] = Integer.parseInt(t_blueN.substring(i,i+2));
			}
		}else if(t_stype == 30){//红复蓝复 
			for (int i = 22+t_add; i < s.length(); i++) {
				if(s.charAt(i)=='~'){
					t_redN = t_sb.toString();
					t_sb = null;
					t_sb = new StringBuffer("");
					continue;
				}
				if(s.charAt(i)=='^'){
					t_blueN = t_sb.toString();
					t_sb = null;
					t_sb = new StringBuffer("");
					break;
				}
				t_sb.append(s.charAt(i));
			}
			t_Pin = new int[4+(t_redN.length()/2)+(t_blueN.length()/2)];
			t_Pin[0] = t_stype;
			t_Pin[1] = t_smul;
			t_Pin[2] = t_redN.length()/2;
			t_Pin[3] = t_blueN.length()/2;
			for (int i = 0; i < t_redN.length(); i+=2) {
				t_Pin[4+i/2] = Integer.parseInt(t_redN.substring(i,i+2));
			}
			for (int i = 0; i < t_blueN.length(); i+=2) {
				t_Pin[4+t_Pin[2]+i/2] = Integer.parseInt(t_blueN.substring(i,i+2));
			}
		}else if(t_stype == 40){//胆拖蓝单
			for (int i = 21+t_add; i < s.length(); i++) {
				if(s.charAt(i)=='*'){
					t_redN = t_sb.toString();
					t_sb = null;
					t_sb = new StringBuffer("");
					continue;
				}
				if(s.charAt(i)=='~'){
					t_tuoN = t_sb.toString();
					t_sb = null;
					t_sb = new StringBuffer("");
					continue;
				}
				if(s.charAt(i)=='^'){
					t_blueN = t_sb.toString();
					t_sb = null;
					t_sb = new StringBuffer("");
					break;
				}
				t_sb.append(s.charAt(i));
			}

			t_Pin = new int[5+(t_redN.length()/2)+(t_tuoN.length()/2)+(t_blueN.length()/2)];
			t_Pin[0] = t_stype;
			t_Pin[1] = t_smul;
			t_Pin[2] = t_redN.length()/2;
			t_Pin[3] = t_tuoN.length()/2;
			t_Pin[4] = t_blueN.length()/2;
			for (int i = 0; i < t_redN.length(); i+=2) {
				t_Pin[5+i/2] = Integer.parseInt(t_redN.substring(i,i+2));
			}
			for (int i = 0; i < t_tuoN.length(); i+=2) {
				t_Pin[5+t_Pin[2]+i/2] = Integer.parseInt(t_tuoN.substring(i,i+2));
			}
			for (int i = 0; i < t_blueN.length(); i+=2) {
				t_Pin[5+t_Pin[2]+t_Pin[3]+i/2] = Integer.parseInt(t_blueN.substring(i,i+2));
			}
		}else if(t_stype == 50){//胆拖蓝复 
			for (int i = 21+t_add; i < s.length(); i++) {
				if(s.charAt(i)=='*'){
					t_redN = t_sb.toString();
					t_sb = null;
					t_sb = new StringBuffer("");
					continue;
				}
				if(s.charAt(i)=='~'){
					t_tuoN = t_sb.toString();
					t_sb = null;
					t_sb = new StringBuffer("");
					continue;
				}
				if(s.charAt(i)=='^'){
					t_blueN = t_sb.toString();
					t_sb = null;
					t_sb = new StringBuffer("");
					break;
				}
				t_sb.append(s.charAt(i));
			}

			t_Pin = new int[5+(t_redN.length()/2)+(t_tuoN.length()/2)+(t_blueN.length()/2)];
			t_Pin[0] = t_stype;
			t_Pin[1] = t_smul;
			t_Pin[2] = t_redN.length()/2;
			t_Pin[3] = t_tuoN.length()/2;
			t_Pin[4] = t_blueN.length()/2;
			for (int i = 0; i < t_redN.length(); i+=2) {
				t_Pin[5+i/2] = Integer.parseInt(t_redN.substring(i,i+2));
			}
			for (int i = 0; i < t_tuoN.length(); i+=2) {
				t_Pin[5+t_Pin[2]+i/2] = Integer.parseInt(t_tuoN.substring(i,i+2));
			}
			for (int i = 0; i < t_blueN.length(); i+=2) {
				t_Pin[5+t_Pin[2]+t_Pin[3]+i/2] = Integer.parseInt(t_blueN.substring(i,i+2));
			}
		}
	}else if(type == TYPE_3D){
		if(t_stype == 0||t_stype == 1||t_stype == 2
				||t_stype == 10||t_stype == 11||t_stype == 12
				||t_stype == 30){
			//单选//组3//组6//直和值//组3值//组6值//单选复式
			for (int i = 21+t_add; i < s.length(); i++) {
				if(s.charAt(i)=='^'){
					t_redN = t_sb.toString();
					t_sb = null;
					t_sb = new StringBuffer("");
					continue;
				}
				t_sb.append(s.charAt(i));
			}

			t_Pin = new int[3+(t_redN.length()/2)];
			t_Pin[0] = t_stype;
			t_Pin[1] = t_smul;
			t_Pin[2] = t_redN.length()/2;
			for (int i = 0; i < t_redN.length(); i+=2) {
				t_Pin[3+i/2] = Integer.parseInt(t_redN.substring(i,i+2));
			}
		}
		else if(t_stype == 31||t_stype == 32){//组3复式//组6复式
			for (int i = 23+t_add; i < s.length(); i++) {
				if(s.charAt(i)=='^'){
					t_redN = t_sb.toString();
					t_sb = null;
					t_sb = new StringBuffer("");
					continue;
				}
				t_sb.append(s.charAt(i));
			}

			t_Pin = new int[3+(t_redN.length()/2)];
			t_Pin[0] = t_stype;
			t_Pin[1] = t_smul;
			t_Pin[2] = t_redN.length()/2;
			for (int i = 0; i < t_redN.length(); i+=2) {
				t_Pin[3+i/2] = Integer.parseInt(t_redN.substring(i,i+2));
			}
		}else if(t_stype == 54){//胆拖
			for (int i = 21+t_add; i < s.length(); i++) {
				if(s.charAt(i)=='*'){
					t_redN = t_sb.toString();
					t_sb = null;
					t_sb = new StringBuffer("");
					continue;
				}
				if(s.charAt(i)=='^'){
					t_blueN = t_sb.toString();
					t_sb = null;
					t_sb = new StringBuffer("");
					break;
				}
				t_sb.append(s.charAt(i));
			}

			t_Pin = new int[4+(t_redN.length()/2)+(t_blueN.length()/2)];
			t_Pin[0] = t_stype;
			t_Pin[1] = t_smul;
			t_Pin[2] = t_redN.length()/2;
			t_Pin[3] = t_blueN.length()/2;
			for (int i = 0; i < t_redN.length(); i+=2) {
				t_Pin[4+i/2] = Integer.parseInt(t_redN.substring(i,i+2));
			}
			for (int i = 0; i < t_blueN.length(); i+=2) {
				t_Pin[4+t_Pin[2]+i/2] = Integer.parseInt(t_blueN.substring(i,i+2));
			}
		}
		
	}
	//caoyongxing
	else if(type == TYPE_SLC){
		if(t_stype==0)
		{
			t_Pin=new int[9];
			t_Pin[0]=t_stype;
			t_Pin[1]=t_smul;
			
			for(int i=0;i<7;i++)
			{
				//String s1="1512-F47102-00-01-这是单式0001 01020304050607";
				
				t_Pin[i+2]=Integer.parseInt(s.substring(22+i*2,24+2*i));
			}
			
			
		}
		if(t_stype==10)
		{
			
			
			int fu=s.indexOf("*");
			int len=s.length();
			int chang=((len-1)-(fu+1)+1)/2;
			t_Pin=new int[chang+2];
			t_Pin[0]=t_stype;
			t_Pin[1]=t_smul;
			
			
			for(int i=0;i<chang;i++)
			{
				//String s1="1512-F47102-00-01-前面18个字符    这是复式码1001*01020304050607080910^";
				
				t_Pin[i+2]=Integer.parseInt(s.substring(fu+1+i*2,fu+3+2*i));
			}
			
			
		}
		if(t_stype==20)
		{
			int len=s.length();
			int dan=s.indexOf("*");
			int danma=((dan-1)-22+1)/2;
			int tuoma=((len-1)-(dan+1)+1)/2;
			t_Pin=new int[danma+tuoma+2+1];
			t_Pin[0]=t_stype;
			t_Pin[1]=t_smul;
			for(int i=0;i<danma;i++)
			{
				//String s1="1512-F47102-00-01- 这是胆拖码2001010203*04050607080910^";
				
				t_Pin[i+2]=Integer.parseInt(s.substring(22+i*2,24+i*2));
			}
			t_Pin[danma+2]=-1;//区分胆码和托码的标志
			
			for(int i=0;i<tuoma;i++)
			{
				//String s1="1512-F47102-00-01- 这是胆拖码2001010203*04050607080910^";
				
				t_Pin[i+2+danma+1]=Integer.parseInt(s.substring(dan+1+i*2,dan+3+i*2));
			}
		}
		
	}
//	for (int i = 0; i < t_Pin.length; i++) {
//		System.out.println("t_Pin["+i+"]:"+t_Pin[i]);
//	}
	return t_Pin;
}
	
	public static String getString(String a){
	String bet_codes[]=a.split("\\^");//注码拆分
	StringBuffer betCode=new StringBuffer();
	for(int i=0;i<bet_codes.length;i++){
		String bet1=bet_codes[i].substring(4);
		 if(bet_codes[i].indexOf("*")!=-1){//复式或者胆拖
			String[] betCodes=bet_codes[i].split("\\*");
			StringBuffer stringBuffer=new StringBuffer();
			if(betCodes[0].length()>4){//胆拖
				int k=0;
				stringBuffer.append("胆码:");
				for(int j=0;j<betCodes[0].substring(4).length();j=j+2){
					k++;
					
					if(k==betCodes[0].substring(4).length()/2){
						stringBuffer.append(betCodes[0].substring(4).substring(j,j+2)+" ");
					}else{
						stringBuffer.append(betCodes[0].substring(4).substring(j,j+2)+",");
					}
				}
				if(betCodes[1].indexOf("~")!=-1){
					 k=0;
					 stringBuffer.append("拖码:");
					 String[]bets=betCodes[1].split("~");
					 for(int j=0;j<bets[0].length();j=j+2){
						 k++;
						 if(k==bets[0].length()/2){
							 stringBuffer.append(bets[0].substring(j,j+2)+" ");
						 }else{
							 stringBuffer.append(bets[0].substring(j,j+2)+",");
						 } 
					 }
					 k=0;
					 stringBuffer.append("蓝球:");
					 for(int j=0;j<bets[1].length();j=j+2){
						 k++;
						 if(k==bets[1].length()/2){
							 stringBuffer.append(bets[1].substring(j,j+2)+"	");
						 }else{
							 stringBuffer.append(bets[1].substring(j,j+2)+",");
						 }
					 }
				}else{
					 stringBuffer.append("拖码:");
					k=0;
					for(int j=0;j<betCodes[1].length();j=j+2){
					
						k++;
						if(k==betCodes[1].length()/2){
							stringBuffer.append(betCodes[1].substring(j,j+2)+" ");
						}else{
							stringBuffer.append(betCodes[1].substring(j,j+2)+",");
						}
					}
					
				}
				
			}else{//复式
				if(betCodes[1].indexOf("~")!=-1){
					stringBuffer.append("红球:");
					String bets[]=betCodes[1].split("~");
					int k=0;
					//StringBuffer sb=new StringBuffer();
					for(int j=0;j<bets[0].length();j=j+2){
						k++;
						if(k==bets[0].length()/2){
							stringBuffer.append(bets[0].substring(j,j+2)+" ");
						}else{
							stringBuffer.append(bets[0].substring(j,j+2)+",");
						}
					}
					k=0;
					stringBuffer.append("蓝球:");
					for(int j=0;j<bets[1].length();j=j+2){
						k++;
						if(k==bets[1].length()/2){
							stringBuffer.append(bets[1].substring(j,j+2)+"	");
						}else{
							stringBuffer.append(bets[1].substring(j,j+2)+",");
						}
					}
					//stringBuffer.append(bets[1]+"&nbsp;&nbsp;");
				}else{
					int k=0;
					for(int j=0;j<betCodes[1].length();j=j+2){
						k++;
						if(k==betCodes[1].length()/2){
							stringBuffer.append(betCodes[1].substring(j,j+2)+"	");
						}
						stringBuffer.append(betCodes[1].substring(j,j+2)+",");
					}
				}
			}
			betCode.append(stringBuffer);
		}else if(bet1.indexOf("~")!=-1){//双色球单式
			
			StringBuffer stringBuffer=new StringBuffer();
			stringBuffer.append("红球:");
			//stringBuffer.append("红球:");
			String bet2[]=bet1.split("~");
			for(int j=0;j<bet2[0].length();j=j+2){
			    String bet=bet1.substring(j,j+2);
			    stringBuffer.append(bet+",");
			}
			stringBuffer.append("蓝球:");
			stringBuffer.append(bet2[1]+" ");
			betCode.append(stringBuffer);
		}else{
			int k=0;
			StringBuffer sb=new StringBuffer();
			for(int j=0;j<bet1.length();j=j+2){
				String bet=bet1.substring(j,j+2);
				k++;
				if(k==bet1.length()/2){
					sb.append(bet+" ");
				}else{
					sb.append(bet+",");
				}
			}
			betCode.append(sb);
		}
//		if(i==2){
//			betCode.append("<br>");
//		}
	}
	return betCode.toString();
}


//彩种名称传换
public static String getLOTNOStr(String str){
	if("F47103".equals(str)) str="3D";
	if("F47104".equals(str)) str="双色球";
	//caoyongxing
	if("F47102".equals(str)) str="七乐彩";
	if("F47101".equals(str)) str="时时彩";
	if("F02903".equals(str)) str="3D";
	if("F02904".equals(str)) str="双色球";
	if("F02902".equals(str)) str="七乐彩";

	return str;
}
 
public static String getSettleFlagStr(String str){
	if("0".equals(str)) str="未兑奖";
	if("1".equals(str)) str="已兑奖";
	if("2".equals(str)) str="未开奖";
	if("9".equals(str)) str="已中大奖";
	if("3".equals(str) )str="未中奖";
	return str;
}
/**
 * 将分转换为元
 * @param amountStr
 * @return
 */
public static String parseAmountLong2Str(Long amountLong){
	DecimalFormat df = new DecimalFormat("0.00");
	double d = amountLong/100d;
	String s = df.format(d);
	return s;
}

//转换注册日期格式
public static String getTimeStr(Date date){
	SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");
	String regTimeStr="";
	if(date!=null && !"1000-01-01".equals(sdf2.format(date))){
		regTimeStr=sdf.format(date);
	}
	return regTimeStr;
}

public static String getCodeFormat(String code){
	if(code.indexOf(",")==-1){
		return code;
	}
	String []codes=code.split(",");
	StringBuffer stb=new StringBuffer();
	for(int i=0;i<codes.length;i++){
		String betGrade=codes[i];
		String message="";
		if(i==0){
			message="一等奖注数:";
			if(!betGrade.equals("0")){
				stb.append(message+betGrade);
			}
		}else if(i==1){
			message="二等奖注数:";
			if(!betGrade.equals("0")){
				stb.append(message+betGrade);
			}
		}else if(i==2){
			message="三等奖注数:";
			if(!betGrade.equals("0")){
				stb.append(message+betGrade);
			}
		}else if(i==3){
			message="四等奖注数:";
			if(!betGrade.equals("0")){
				stb.append(message+betGrade);
			}
		}else if(i==4){
			message="五等奖注数:";
			if(!betGrade.equals("0")){
				stb.append(message+betGrade);
			}
		}else if(i==5){
			message="六等奖注数:";
			if(!betGrade.equals("0")){
				stb.append(message+betGrade);
			}
		}else if(i==6){
			message="七等奖注数:";
			if(!betGrade.equals("0")){
				stb.append(message+betGrade);
			}
		}else if(i==7){
			message="八等奖注数:";
			if(!betGrade.equals("0")){
				stb.append(message+betGrade);
			}
		}else if(i==8){
			message="九等奖注数:";
			if(!betGrade.equals("0")){
				stb.append(message+betGrade);
			}
		}else if(i==9){
			message="十等奖注数:";
			if(!betGrade.equals("0")){
				stb.append(message+betGrade);
			}
		}
		
	}
	return stb.toString();
}


}

