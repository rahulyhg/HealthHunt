package framework.retrofit;

import java.util.List;
import java.util.Map;

import in.healthhunt.model.articles.articleResponse.ArticleData;
import in.healthhunt.model.articles.bookmarkResponse.BookMarkData;
import in.healthhunt.model.articles.bookmarkResponse.BookMarkResponse;
import in.healthhunt.model.articles.postResponse.PostResponse;
import in.healthhunt.model.articles.productResponse.ProductData;
import in.healthhunt.model.comment.AllCommentInfo;
import in.healthhunt.model.comment.CommentData;
import in.healthhunt.model.comment.CommentRequest;
import in.healthhunt.model.comment.CommentsItem;
import in.healthhunt.model.likes.LikesInfo;
import in.healthhunt.model.likes.LikesRequest;
import in.healthhunt.model.login.ForgotPasswordRequest;
import in.healthhunt.model.login.LoginRequest;
import in.healthhunt.model.login.SignUpRequest;
import in.healthhunt.model.login.UserData;
import in.healthhunt.model.response.HHResponse;
import in.healthhunt.model.tags.TagData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface WebServices {


 //@POST("/api/account-login")
 //Call<LoginResponse> login(@Field("access_token") String token);
 @POST("login")
 Call<HHResponse<UserData>> login(@Body LoginRequest loginRequest);

 @POST("signup")
 Call<HHResponse<UserData>> signUp(@Body SignUpRequest signUpRequest);

 @POST("forgot_password")
 Call<String> forgotPassword(@Body ForgotPasswordRequest forgotPasswordRequest);

 @GET("tags")
 Call<HHResponse<TagData>> fetchTags(@Query("page") int page, @Query("per_page") int per_page);

 @GET("posts")
 Call<HHResponse<ArticleData>> fetchArticles(@QueryMap Map<String, String> params);

 @GET("posts")
 Call<HHResponse<ProductData>> fetchProducts(@QueryMap Map<String, String> params);

 @GET("posts/{id}")
 Call<PostResponse> fetchFullArticle(@Path("id") String id);

 @POST("posts/{id}/save")
 Call<List<BookMarkResponse>>bookmark(@Path("id") String id);

 @POST("posts/{id}/unsave")
 Call<BookMarkData> unBookmark(@Path("id") String id);

 @GET("comments")
 Call<HHResponse<AllCommentInfo>> fetchComments(@QueryMap Map<String, String> params);

 @POST("comments/{id}/delete")
 Call<HHResponse<CommentsItem>> deleteComment(@Path("id") String id);

 @POST("comments/{id}")
 Call<HHResponse<CommentData>> updateComment(@Path("id") String id, @Body CommentRequest commentRequest);

 @POST("comments")
 Call<HHResponse<CommentData>> addNewComment(@Body CommentRequest commentRequest);

 @POST("likes/{id}")
 Call<HHResponse<LikesInfo>> updateLikes(@Path("id") String id, @Body LikesRequest likesRequest);


    /*@Header("authToken") String token,*/


 //Same api for sitter and parent with type difference

//    @POST("api/profiles/")
//    Call<UserInfo> createProfile(@LoginHeader("Authorization") String authToken, @Body UserType userType);
//
//
//    @POST("https://android.googleapis.com/gcm/notification")
//    Call<String> createUserAccount(@LoginHeader("Authorization") String token, @LoginHeader("project_id") String projectId, @Body String values);
//
//
//    @GET("/api/pictures")
//    Call<String> getPictures(@LoginHeader("Authorization") String token);
//
//
//    @POST("/api/pictures")
//    Call<String> savePictures(@LoginHeader("Authorization") String token);
//
//
//    @POST("/api/pictures/{id}")
//    Call<String> getPicturesForUser(@LoginHeader("Authorization") String token, @Path("id") int id);
//
//
//    @PUT("/api/pictures/{id}")
//    Call<String> updatePicturesForUser(@LoginHeader("Authorization") String token, @Path("id") int id);
//
//
//    @DELETE("/api/pictures/{id}")
//    Call<String> deletePicturesForUser(@LoginHeader("Authorization") String token, @Path("id") int id);
//
//
//    @GET("/api/addresses/{id}")
//    Call<String> getAddresses(@LoginHeader("Authorization") String token, @Path("id") int id);
//
//
//    @DELETE("/api/addresses/{id}")
//    Call<String> deleteAddresses(@LoginHeader("Authorization") String token, @Path("id") int id);
//
//
//    @POST("/api/addresses/")
//    Call<Address[]> createAddresses(@LoginHeader("Authorization") String token, @Body Address address);
//
//
//    @PUT("/api/addresses/{id}")
//    Call<Address[]> updateAddress(@LoginHeader("Authorization") String token, @Body Address address, @Path("id") int id);
//
//
//    @GET("/api/sitters/{id}")
//    Call<String> getSitter(@LoginHeader("Authorization") String token, @Path("id") int id);
//
//
//    @PUT("/api/sitters/{id}")
//    Call<String> updateSitter(@LoginHeader("Authorization") String token, @Path("id") int id);
//
//
//    @GET("/api/sitters/")
//    Call<String> getSitters(@LoginHeader("Authorization") String token);
//
//
//    @POST("/api/sitters/discovery/swipe/")
//    Call<String> swipeSitters(@LoginHeader("Authorization") String token);
//
//
//    @GET("/api/browse/")
//    Call<List<UserInfo>> discoverSitters(@LoginHeader("Authorization") String token);
//
//
//    @GET("/api/me/")
//    Call<UserInfo> getmyProfile(@LoginHeader("Authorization") String token);
//
//
//    @GET("/api/sitters/discovery/matches")
//    Call<String> discoverSittersMatches(@LoginHeader("Authorization") String token);
//
//
//    @PATCH("/api/sitters/{id}/favorite")
//    Call<String> addFavouriteSitters(@LoginHeader("Authorization") String token, @Path("id") String id);
//
//
//    @GET("/api/sitters/bookmarks")
//    Call<String> getBookMarkSitters(@LoginHeader("Authorization") String token, @Path("id") String id);
//
//
//    @GET("/api/payment/customer")
//    Call<String> getFavouritedSitters(@LoginHeader("Authorization") String token);
//
//
//    @POST("/api/me/customer/bank-account")
//    Call<String> addBaknAccountCustomer(@LoginHeader("Authorization") String token);
//
//
//    @GET("/api/me/customer/bank-account")
//    Call<String> getBankAccons(@LoginHeader("Authorization") String token);
//
//
//    @DELETE("/api/me/")
//    Call<String> deleteAccount(@LoginHeader("Authorization") String token);
//
//
//    @PATCH("/api/me/")
//    Call<UserInfo> updateChildCount(@LoginHeader("Authorization") String token, @Body ParentalDetail.ChildCount childCount);
//
//
//    @PATCH("/api/me/")
//    Call<UserInfo> updateDiscription(@LoginHeader("Authorization") String token, @Body ParentalDetail parentalDetail);
//
//
//    @PATCH("/api/me/")
//    Call<UserInfo> updateParentProfile(@LoginHeader("Authorization") String token, @Body ParentProfileUpdatePatch parentalDetail);
//
//
//    @POST("/api/me/customer/source/")
//    Call<String> updateToken(@LoginHeader("Authorization") String token, @Body CardToken stripeToken);
//
//
//    @PATCH("/api/me/")
//    Call<UserInfo> updateSitterProfile(@LoginHeader("Authorization") String token, @Body SitterProfileUpdatePatch sitterDetail);
//
//
//    @POST("/api/bookings/")
//    Call<BookingItem> reqeustBooking(@LoginHeader("Authorization") String token, @Body BookingRequest bookingRequest);
//
//
//    @GET("/api/bookings/")
//    Call<BookingItem[]> bookingList(@LoginHeader("Authorization") String token);
//
//    @GET("/api/bookingrequests/")
//    Call<SitterBookingItem[]> bookingRequestList(@LoginHeader("Authorization") String token);
//
//
//    @FormUrlEncoded
//    @POST("/api/rejects/")
//    Call<TinderSwipeListenerImpl.BookMark> rejectSitter(@LoginHeader("Authorization") String token, @Field("target_id") int id);
//
//    @FormUrlEncoded
//    @POST("/api/bookmarks/")
//    Call<TinderSwipeListenerImpl.BookMark> bookmarkSitter(@LoginHeader("Authorization") String token, @Field("target_id") int id);
//
//
//    @GET("/api/bookmarks/")
//    Call<List<UserInfo>> getBookMarkSitters(@LoginHeader("Authorization") String token);
//
//
//    @GET("/api/me/feed")
//    Call<HomeFeeds> getHomeFeeds(@LoginHeader("Authorization") String token);
//
//    @GET("/api/favorites/")
//    Call<List<UserInfo>> getFavouriteSitters(@LoginHeader("Authorization") String token);
//
//
//    @FormUrlEncoded
//    @POST("/api/favorites/")
//    Call<AddFriendFavourite> setFavourite(@LoginHeader("Authorization") String token, @Field("favorited") boolean favourited, @Field("target_id") Long targetId);
//
//    @FormUrlEncoded
//
//
//    @POST("/api/friends/")
//    Call<AddFriendFavourite> setFriends(@LoginHeader("Authorization") String token, @Field("is_friend") boolean favourited, @Field("target_id") Long targetId);
//
//
//    @GET("/api/friends/sitters")
//    Call<List<UserInfo>> getFriendsSitter(@LoginHeader("Authorization") String token);
//
//    @GET("/api/friends")
//    Call<List<UserInfo>> getFriends(@LoginHeader("Authorization") String token);
//
//
//    @GET("/api/parents/nearby")
//    Call<List<UserInfo>> getParentsNearBy(@LoginHeader("Authorization") String token);
//
//
//    @GET("api/inbox/calls")
//    Call<List<CallItem>> getCallingUserDetals(@LoginHeader("Authorization") String token);
//
//    @GET("/api/users/{id}/relationship")
//    Call<UserInfo.RelationShip> getRelationShip(@LoginHeader("Authorization") String token, @Path("id") String id);
//
//    @GET("/api/users/{id}/villagers")
//    Call<List<UserInfo>> getVillagers(@LoginHeader("Authorization") String token, @Path("id") String id);
//
//    @GET("/api/profiles/{id}/reviews")
//    Call<List<Review>> getReviews(@LoginHeader("Authorization") String token, @Path("id") String id);
//
//    @GET("/api/conversations")
//    Call<List<InboxItem>> getConversations(@LoginHeader("Authorization") String token);
//
//    @GET("/api/users/{id}/conversation")
//    Call<MessageItem> getUserConversation(@LoginHeader("Authorization") String token, @Path("id") String id);
//
//    @POST("/api/conversations/{id}/read")
//    Call<String> updateReadStatus(@LoginHeader("Authorization") String token, @Path("id") String id);
//
//
//    @POST("/api/users/{id}/messages")
//    Call<String> postMessage(@LoginHeader("Authorization") String token, @Path("id") String id, @Body Message message);
}