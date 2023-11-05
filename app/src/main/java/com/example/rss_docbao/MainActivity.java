package com.example.rss_docbao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Documented;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.channels.AsynchronousChannelGroup;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    ArrayList<String> arrayTitle;
    ArrayList<String> arrayLink;
    AdapterLV adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv= (ListView) findViewById(R.id.danhsach);
        arrayTitle = new ArrayList<>();
        arrayLink = new ArrayList<>();

        adapter = new AdapterLV(this,arrayTitle);
        lv.setAdapter(adapter);

        new ReadRSS().execute("https://vtc.vn/rss/the-thao.rss");



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                intent.putExtra("linktintuc", arrayLink.get(position));
                startActivity(intent);
            }
        });
    }

    private class ReadRSS extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings){
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader((url.openConnection().getInputStream()));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line=bufferedReader.readLine()) != null){
                    content.append(line);
                }

                bufferedReader.close();
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodelist = document.getElementsByTagName("item");

            String title = "";
            String link = "";
            for(int i=0; i<nodelist.getLength();i++){
                Element element = (Element) nodelist.item(i);
                title = parser.getValue(element,"title");
                link = parser.getValue(element,"link");
                arrayTitle.add(title);
                arrayLink.add(link);
            }
            adapter.notifyDataSetChanged();

        }
    }
}