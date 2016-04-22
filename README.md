# Twitter-get-search-tweets
This is an android project to get specific number of tweets
which contains specific word and counts those tweets

you can change number of tweets 
```java

                statuses = twitter.getUserTimeline(user,new Paging(1,200));

```
change "200" to your number 
as '1' is number of pages to search , 200 is number of tweets per page . 

to get all tweets you have to use this code .  
```java
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
```

and to remove the search to get all tweets just remove the if statment ;) 
