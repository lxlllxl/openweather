package com.example.administrator.openweather.util;

import android.text.TextUtils;

import com.example.administrator.openweather.db.City;
import com.example.administrator.openweather.db.County;
import com.example.administrator.openweather.db.Provice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.jar.JarException;

/**
 * Created by Administrator on 2017/7/24.
 */

public class Utility {
    /*
    解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allProvinces=new JSONArray(response);
                for(int i=0;i<allProvinces.length();i++){
                    JSONObject provincesObject=allProvinces.getJSONObject(i);
                    Provice provice=new Provice();
                    provice.setProvinceName(provincesObject.getString("name"));
                    provice.setProvinceCode(provincesObject.getInt("id"));
                    provice.save();
                }
                return  true;
            }catch (JSONException e){
                e.printStackTrace();

            }

        }
        return false;
    }
    /*
    解析服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCitites=new JSONArray(response);
                for(int i=0;i<allCitites.length();i++){
                    JSONObject citiesObject=allCitites.getJSONObject(i);
                    City city=new City();
                    city.setCityName(citiesObject.getString("name"));
                   city.setCityCode(citiesObject.getInt("id"));
                    city.save();
                }
                return  true;
            }catch (JSONException e){
                e.printStackTrace();

            }

        }
        return false;

    }
    /*
    解析服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCounties=new JSONArray(response);
                for(int i=0;i<allCounties.length();i++){
                    JSONObject contuiesObject=allCounties.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(contuiesObject.getString("name"));
                    county.setWeatherId(contuiesObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return  true;
            }catch (JSONException e){
                e.printStackTrace();

            }

        }
        return false;

    }
}
