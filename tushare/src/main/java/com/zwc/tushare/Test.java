package com.zwc.tushare;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;


@RestController
public class Test {

    @RequestMapping("test")
    public Object getTest()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("api_name","stock_basic");
        map.put("token","14cf327c88cf8fc8b37a65d3ee08d17070e9092249eb6a688312f996");
        String url = "http://api.tushare.pro/";
        JSONObject jsonObject = JSONObject.fromObject(map);
        RestTemplate restTemplate = new RestTemplate();
     //  return  restTemplate.postForObject("http://api.tushare.pro/",jsonObject,Object.class);
     //  restTemplate.postForEntity();
      //  return jsonObject;
        Map<String,Object> mm = (Map<String,Object>)restTemplate.postForObject(url,jsonObject,Object.class);
        Map<String,Object> mp =  (Map<String,Object>) mm.get("data");
      //  return mp.get("items");
        List<Map<String,Object>> list = (List<Map<String, Object>>) mp.get("items");
        map.put("api_name","daily");
        map.put("token","14cf327c88cf8fc8b37a65d3ee08d17070e9092249eb6a688312f996");
        Map<String,Object> map_code = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-20);
        String today = dateFormat.format(calendar.getTime());
        System.out.println(today);
        for(int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i));
            System.out.println(i);
            System.out.println(((List<String>)list.get(i)).get(0));
            String stock =((List<String>)list.get(i)).get(0).toString();
            System.out.println(stock);
            map_code.put("ts_code",stock);
            map_code.put("start_date",today);
            map.put("params",map_code);
            jsonObject = JSONObject.fromObject(map);
          //  System.out.println(restTemplate.postForObject(url,jsonObject,Object.class));
            mm =(Map<String,Object>)restTemplate.postForObject(url,jsonObject,Object.class);
            mp = (Map<String,Object>) mm.get("data");
            List<Map<String,Object>> list2 =  (List<Map<String, Object>>) mp.get("items");
            if(list2.size()<=10) continue;
            for(int j=0;j<10;j++)
            {

            }







        }
        return null;
    }


}
