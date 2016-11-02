package com.example.welcome.movieapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.models.moviesapp.MovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    HttpsURLConnection urlConnection;;
    BufferedReader reader;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.button);
        textView =(TextView)findViewById(R.id.textView2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new JsonTask().execute("https://api.themoviedb.org/3/movie/popular?api_key=18e04e15899be92b78b879f04b8b0934");
            }
        });




    }

    public class JsonTask extends AsyncTask<String,String,String >{
        @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL(params[0]);
                HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();

                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                String line ="";

                StringBuffer buffer = new StringBuffer();

                while ((line =reader.readLine())!=null){

                    buffer.append(line);

                }
               String finalJson =  buffer.toString();

                JSONObject parentJson = new JSONObject(finalJson);

                JSONArray movieArray = parentJson.getJSONArray("results");

                StringBuffer finalBufferedData = new StringBuffer();

                for(int i=0;i<movieArray.length();i++){

                    JSONObject jsonObject = movieArray.getJSONObject(i);

                    MovieModel movieModel = new MovieModel();

                    movieModel.setOriginal_title(jsonObject.getString("original_title"));

                    movieModel.setPoster_path(jsonObject.getString("poster_path"));

                    movieModel.setOverview(jsonObject.getString("overview"));

                    movieModel.setRelease_date(jsonObject.getString("release_date"));

                    movieModel.setVote_average(jsonObject.getInt("vote_average"));



                }

                return finalBufferedData.toString();



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {


                if(urlConnection!=null){
                    urlConnection.disconnect();
                }
                try {
                    if(reader!=null){
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            textView.setText(result);
            Log.i("appInfo",result);
        }
    }
}
