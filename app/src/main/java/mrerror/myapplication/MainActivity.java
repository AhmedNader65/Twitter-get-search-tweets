package mrerror.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class MainActivity extends AppCompatActivity {
    EditText userView;
    EditText wordView;
    String username;
    String word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userView = (EditText)findViewById(R.id.user);
        wordView = (EditText)findViewById(R.id.word);



    }

    public void search(View view) {
        username= userView.getText().toString();
        word = wordView.getText().toString();
        a b = new a();
        b.execute(username,word);
    }

    public class a extends AsyncTask<String, Void, ArrayList<String>>{
        int count = 0;
        @Override
        protected ArrayList<String> doInBackground(String... param){
            ArrayList<String> tweets = new ArrayList<>();

            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("************")
                    .setOAuthConsumerSecret(
                            "***************")
                    .setOAuthAccessToken(
                            "*************")
                    .setOAuthAccessTokenSecret(
                            "*******************");
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
            int page = 1;
            while(true){
            try {
                List<twitter4j.Status> statuses;
                String user;
                Log.e("user",param[0]);
                Log.e("word",param[1]);
                user = param[0];
                statuses = twitter.getUserTimeline(user,new Paging(page++, 200));
                Log.i("Status Count", statuses.size() + " Feeds");
                for (int i = 0; i < statuses.size(); i++) {
                    twitter4j.Status status = statuses.get(i);
                    if(status.getText().contains(param[1])){
                        count ++;
                        tweets.add(status.getText());
                    }
                }
                if(statuses.size() == 0){
                    break;
                }
            } catch (TwitterException te) {
                te.printStackTrace();
            }}
            return tweets;
        }



        @Override
        protected void onPostExecute(ArrayList<String> o) {
            TextView textView = (TextView) findViewById(R.id.num);
            textView.setText("number of tweets contain "+word+" is "+ String.valueOf(count));
            ArrayAdapter aa = new ArrayAdapter(getApplicationContext(),R.layout.listitem,o);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(aa);
        }
    }
}
