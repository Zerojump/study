package com.cmy.factory.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Date;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/10/4
 */
public class GsonFactory {
    public static Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        //使用自定义的Date和json的序列化和反序列化方式
        gsonBuilder.setPrettyPrinting().registerTypeAdapter(Date.class, new CustomDateTypeAdapter());
        return gsonBuilder.create();
    }

    //自定义Date和json的序列化和反序列化方式为通过毫秒时间戳互转
    public static class CustomDateTypeAdapter extends TypeAdapter<Date> {
        @Override
        public void write(JsonWriter out, Date date) throws IOException {
            if (date == null) {
                out.nullValue();
            } else {
                out.value(date.getTime());
            }
        }

        @Override
        public Date read(JsonReader in) throws IOException {
            return new Date(in.nextLong());
        }
    }
}
