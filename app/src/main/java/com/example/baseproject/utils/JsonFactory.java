package com.example.baseproject.utils;

/**
 * 描述：Json转化工具
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonFactory {

    //object转Json
    public static String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    //获取单个值
    public static String getValue(String json, String key) {
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(json).getAsJsonObject();
        return jsonObject.get(key).toString();
    }

    //Json转Object
    public static Object jsonToObject(String json, Class<?> clazz) {
        Object obj = null;
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(String.class, new StringConverter());
        Gson gson = gb.create();

        obj = gson.fromJson(json, clazz);
        return obj;
    }

    //Json转数组
    public static <T> List<T> jsonToList(String json, Class<T[]> clazz) {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(String.class, new StringConverter());
        Gson gson = gb.create();
        T[] array = gson.fromJson(json, clazz);
        return Arrays.asList(array);
    }

    //Json转动态数组
    public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz) {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(String.class, new StringConverter());
        Gson gson = gb.create();


        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = gson.fromJson(json, type);

        ArrayList<T> arrayList = new ArrayList<>();
        try {
            for (JsonObject jsonObject : jsonObjects) {
                arrayList.add(gson.fromJson(jsonObject, clazz));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    //Json转字符数组
    public static ArrayList jsonToStringList(String json) {
        ArrayList<String> datas = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                String data = jsonArray.get(i).toString();
                datas.add(data);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return datas;
    }

    public static class StringConverter implements JsonSerializer<String>,
            JsonDeserializer<String> {
        public JsonElement serialize(String src, Type typeOfSrc,
                                     JsonSerializationContext context) {
            if (src == null) {
                return new JsonPrimitive("");
            } else {
                return new JsonPrimitive(src);
            }
        }

        public String deserialize(JsonElement json, Type typeOfT,
                                  JsonDeserializationContext context)
                throws JsonParseException {
            return json.getAsJsonPrimitive().getAsString();
        }
    }

}
