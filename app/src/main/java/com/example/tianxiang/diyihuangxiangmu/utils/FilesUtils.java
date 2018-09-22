package com.example.tianxiang.diyihuangxiangmu.utils;

import android.text.TextUtils;

import com.example.tianxiang.diyihuangxiangmu.R;
import com.example.tianxiang.diyihuangxiangmu.data.remote.NewsData;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class FilesUtils {

    public static void writeNewsDataToFile(NewsData data, File file) {
        if (file == null) {
            return;
        }

        Gson gson = new Gson();
        String json = gson.toJson(data);
        //文件输出流
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(json.getBytes("UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static NewsData getNewsFromFile(File file) {
        if (file == null) {
            return null;
        }
        //文件输入流

        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
            byte bytes[] = new byte[1024];
            int len = -1;
            StringBuilder builder = new StringBuilder();
            while ((len = fileInputStream.read(bytes)) != -1) {
                builder.append(new String(bytes, 0, len, "UTF-8"));
            }

            String json = builder.toString();

            if (!TextUtils.isEmpty(json)) {
                Gson gson = new Gson();
                NewsData data = gson.fromJson(json, NewsData.class);
                return data;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static class ParameterizedTyeImpl implements ParameterizedType {
        private final Class raw;
        private final Type[] args;

        public ParameterizedTyeImpl(Class raw, Type[] args) {
            this.raw = raw;
            this.args = args != null ? args : new Type[0];
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[0];
        }

        @Override
        public Type getRawType() {
            return null;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }

}