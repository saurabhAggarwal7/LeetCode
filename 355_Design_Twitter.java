
/*Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.
Example:

Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1); */

/*SOLUTION IDEA: having tweets as linked-list. So, in the db, each tweet will have a link to it's next tweet. And when a tweet is deleted, all it should do is delete that row, and point to next. Also user table having a tweet head link is brilliant idea as well.

The only problem I see here, is that restriction of loading only 10 tweets in timeline. In reality, the user keeps scrolling.
So we should have a feature to get next 10 tweets or something. And this is when we need links to each tweet of her followees that was returned last time. */

import java.util.*;


class design_twitter{

    //timestamp:
    private static int timeStamp =0;

    //user map:
    private Map<Integer, User> userMap;

    //constructor:
    public design_twitter(){
        //whenevr a new initialzation is done, <> key is userId and value is User Object
        userMap = new HashMap<Integer, User>();
    }

    //CLASS-1
    //Linkedlist of Tweets 
    //Saves time when we want series of tweets (newsfeed)
    private class Tweet{
        public int id;
        public int time;
        public Tweet next; //next tweet reference link

        public Tweet(int id){
            this.id = id;
            time = timeStamp++; //keep up for most recent timestam used later for priority queue
            next = null; //next tweet 
        }
    }
    //end of class tweet


    //CLASS-2:
    //User can follow, unfollow and post itself
    public class User{
        public int id;

        //keep unique list of followed users
        public Set<Integer> followed;
        public Tweet tweet_head; //head of the Linkedlist of tweets

        public User(int id){
            this.id = id;
            followed = new HashSet<>();

            follow(id); //first follow itself //important !!
            tweet_head = null;  //tweet head of this user
        }

        //add follower:
        public void follow(int id){
            followed.add(id);
        }

        //remove follower:
        public void unfollow(int id){
            followed.remove(id);
        }

        //Users most recent tweet and updates the LinkedlIst of Tweets of users
        //note that its not the post tweet function, its per UserId:
        public void post(int id){
            Tweet tweet = new Tweet(id);
            //New Tweet and head is updated for the Linkedlist to point to the new tweet there by maintainingh always what is the latest tweet being done by user and always showing oit to the top:
            tweet.next = tweet_head; 
            tweet_head = tweet;
        }
    }


    //Methods:
    //Create and Post a tweet:
    public void postTweet(int userId, int tweetId){
        if(!userMap.containsKey(userId)){
            User u = new User(userId);
            userMap.put(userId, u);
        }
        //Post the tweet for this user and update the Reference in Linkedlist of tweets:
        userMap.get(userId).post(tweetId);
    }

    //FOLLOW:

    //follower always follow his followee:
    public void follow(int followerId, int followeeId){
        //FIRST Step is create {key} for the User in usermap for follower and followee:
        if(!userMap.containsKey(followerId)){
			User u = new User(followerId);
			userMap.put(followerId, u);
		}
		if(!userMap.containsKey(followeeId)){
			User u = new User(followeeId);
			userMap.put(followeeId, u);
        }
        
        //FOLLOW the User:
        //HASHSET<> followed inside the User Class where you add/remove
        userMap.get(followerId).follow(followeeId);
    }

    //UNFOLLOW:
    //Hashset<> followed inside the User Class where you add/remove 
	/** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
	public void unfollow(int followerId, int followeeId) {
		if(!userMap.containsKey(followerId) || followerId==followeeId)
			return;
		userMap.get(followerId).unfollow(followeeId);
    }
    
    //NEWSFEED:
    //Show top-10 recent tweets in the newsfeed:
    /*	// first get all tweets lists from one user including itself and all people it followed.
	// Second add all heads into a max heap. Every time we poll a tweet with 
	// largest time stamp from the heap, then we add its next tweet into the heap.
	// So after adding all heads we only need to add 9 tweets at most into this 
    // heap before we get the 10 most recent tweet.
    */

    public List<Integer> getNewsFeed(int userId){
        List<Integer> res = new LinkedList<>();
        
        //boundry condition:
        if(!userMap.containsKey(userId)){
            return res;
        }

        //For me being the userId what is the list for the followed ones:
        Set<Integer> users = userMap.get(userId).followed;

        //create a priority queue of size equal to followed users
        //compartor operator of queue is based on timestamp:
        PriorityQueue<Tweet> q = new PriorityQueue<Tweet>(users.size(), (a, b)->(b.time-a.time));

        for(int user: users){
            Tweet t = userMap.get(user).tweet_head;

            //Very Impritant dont add null to the head else all the tweets gets nulled:
            if(t != null){
                //fill the queue with the tweets:
                q.add(t);
            }
        }

        int n=0;
        //we need to show only first 10 tweets based on timestamp:
        while(!q.isEmpty() && n< 10){
            Tweet t = q.poll();
            //result tweets from queue:
            res.add(t.id);
            n++; //tweet count

            //add to queue the next ref of tweet:
            if(t.next !=null){
                q.add(t.next);
            }
        }
        return res;
    }
}

class run_twitter{
    public static void main(String args[]){
        design_twitter obj = new design_twitter();
        obj.postTweet(1, 1);
        obj.postTweet(2, 2);
        obj.postTweet(3, 3);

        //show nesfeed for user-2
        List<Integer> param_2 = obj.getNewsFeed(2);
        //user id 2 follows user id 1
        obj.follow(2, 1);
         //user id 3 follows user id 1
        obj.follow(3, 1);
         //user id 2 unfollows user id 1
        obj.unfollow(2,1);

        List<Integer> param_3 = obj.getNewsFeed(3);
        List<Integer> param_1 = obj.getNewsFeed(1);
        System.out.println(param_1);

    }

}