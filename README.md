cs356-a2-tigran
===============

## Patterns used

- **Singleton:**
 [src/com/tigrang/cs356/a2/view/AdminControlPanel.java#L44](https://github.com/tigrang/cs356-a2-tigran/blob/master/src/com/tigrang/cs356/a2/view/AdminControlPanel.java#L44) The main frame of the app

- **Composite:**
[src/com/tigrang/mvc/model/Entity.java](https://github.com/tigrang/cs356-a2-tigran/blob/master/src/com/tigrang/mvc/model/Entity.java) Used as a base class for all model entities ([User](https://github.com/tigrang/cs356-a2-tigran/blob/master/src/com/tigrang/cs356/a2/model/entity/User.java), [Group](https://github.com/tigrang/cs356-a2-tigran/blob/master/src/com/tigrang/cs356/a2/model/entity/Group.java), [Tweet](https://github.com/tigrang/cs356-a2-tigran/blob/master/src/com/tigrang/cs356/a2/model/entity/Tweet.java))

- **Visitor:**
[src/com/tigrang/cs356/a2/model/visitor/](https://github.com/tigrang/cs356-a2-tigran/tree/master/src/com/tigrang/cs356/a2/model/visitorhttps://github.com/tigrang/cs356-a2-tigran/tree/master/src/com/tigrang/cs356/a2/model/visitor) Visitor used to count total tweets and total positive tweets

- **Observer:**
[https://githsrc/com/tigrang/cs356/a2/model/entity/User.java](https://github.com/tigrang/cs356-a2-tigran/blob/master/src/com/tigrang/cs356/a2/model/entity/User.java) is an Observable objects (as all Entity objects are).
View models [src/com/tigrang/cs356/a2/model/UserFollowingListModel.java#L15](https://github.com/tigrang/cs356-a2-tigran/blob/master/src/com/tigrang/cs356/a2/model/UserFollowingListModel.java#L15) and [src/com/tigrang/cs356/a2/model/NewsFeedListModel.java#L23](https://github.com/tigrang/cs356-a2-tigran/blob/master/src/com/tigrang/cs356/a2/model/NewsFeedListModel.java#L23) observe a user and update the view
