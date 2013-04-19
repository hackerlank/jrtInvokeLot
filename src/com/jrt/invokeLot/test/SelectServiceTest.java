package com.jrt.invokeLot.test;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.jrt.invokeLot.service.JSonResultService;
import com.jrt.invokeLot.test.util.UserOperationUtil;
import com.jrt.invokeLot.util.JSONReslutUtil;


public class SelectServiceTest {
	
	private static final Logger logger = Logger.getLogger(SelectServiceTest.class);
	private  JSonResultService selectService  = new JSonResultService ();
    @Test
	public void betSelectTest(){
    	try {
    		JSONObject map = new JSONObject();
			map.put("mobile_code", "13436602479");
			map.put("password", "123456");
			map.put("lotNo","T01002");
			map.put("batchCode","");
			map.put("startDate","");
			map.put("stopDate","");
			map.put("startLine","");
			map.put("stopLine","");
    		
	    	String sessionid = UserOperationUtil.login(map);

	    	String arrayDrawalottery = JSONReslutUtil.getSelectJSONArray(map,map,sessionid,"TCallSelect.do", "lotterySelect");
			String arrayBet = JSONReslutUtil.getSelectJSONArray(map,map,sessionid,"TCallSelect.do", "touzhuSelect");
			String arrayWin = JSONReslutUtil.getSelectJSONArray(map,map,sessionid, "TCallSelect.do", "zhongjiangSelect");
			String arrayGift = JSONReslutUtil.getSelectJSONArray(map,map,sessionid, "TCallSelect.do", "giftAllSelect");
			String arrayGifted = JSONReslutUtil.getSelectJSONArray(map,map,sessionid ,"TCallSelect.do", "giftedAllSelect");
			String arrayTranckingNumber = JSONReslutUtil.getSelectJSONArray(map,map,sessionid, "TCallSelect.do", "trackingNumberSelect");
			System.out.println("开奖查询结果:"+arrayDrawalottery);
			System.out.println("投注查询结果:"+arrayBet);
			System.out.println("中奖查询结果:"+arrayWin);
			System.out.println("赠彩查询结果:"+arrayGift);
			System.out.println("被赠彩查询结果:"+arrayGifted);
			System.out.println("追号查询结果:"+arrayTranckingNumber);
			
//			for(int i=0;i<arrayBet.size();i++){
//				JSONObject obj = (JSONObject)arrayBet.get(i);
//				obj.getString("error_code");
//				obj.getString("betcode");
//				obj.getString("state");
//				obj.getString("amt");
//				obj.getString("sell_datetime");
//				obj.getString("batchcode");
//				obj.getString("cash_date_time");
//				obj.getString("play_name");
//				obj.getString("maxLine");
//			}
//			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	@Test
    public void bettingSelectTest(){
    	try {
    		JSONObject map = new JSONObject();
    		map.put("mobile_code", "13651262537");
			map.put("password", "123456");
			String sessionid = UserOperationUtil.login(map);
			
			map.put("sessionid", sessionid);
			map.put("lotNo","T01002");
			map.put("batchCode","");
			map.put("startDate","");
			map.put("stopDate","");
			map.put("startLine","");
			map.put("stopLine","");
			System.out.println(selectService.getAllJSonReuslt(map, map, sessionid, 15, "TCallSelect.do", "touzhuSelect"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
