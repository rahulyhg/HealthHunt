package framework.retrofit;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.IOException;
import java.util.Map;

import in.healthhunt.model.beans.Constants;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.model.login.ForgotPasswordRequest;
import in.healthhunt.model.login.LoginRequest;
import in.healthhunt.model.login.SignUpRequest;
import in.healthhunt.model.login.User;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WebServicesWrapper {

    private final static String BASE_URL = "https://development.healthhunt.in/wp-json/sd2/v0.1/";

    private final String authUrl = "/wp-json/sd2/v0.1/";

    private final String privateKey = "Bd6723sXcVBg12Fe";

    private final String type = "Android";

    private final String apiVersion = "v0.1";

    private final String content_type = "application/json";

    private static WebServicesWrapper wrapper;

    protected WebServices webServices;

    private Gson gson;


    private WebServicesWrapper(String baseUrl) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder request = chain.request().newBuilder();


                String timeStamp = HealthHuntUtility.getUTCTimeStamp();

                String url = request.build().url().toString();
                String requestUrl = authUrl + url.substring(url.lastIndexOf("/") + 1, url.length());

                String authCode = requestUrl + privateKey + timeStamp;
                String md5 = HealthHuntUtility.getMD5(authCode);

                request.addHeader(Constants.DEVICE_TYPE, type);
                request.addHeader(Constants.AUTH_TOKEN, md5);
                request.addHeader(Constants.API_VERSION, apiVersion);
                request.addHeader(Constants.TIME_STAMP, timeStamp);
                request.addHeader(Constants.CONTENT_TYPE, content_type);

                Log.i("TAG123", " Md5 = " + md5);
                Log.i("TAG123", " authCode = " + authCode);
                Log.i("TAG123", " timeStamp = " + timeStamp);
                Log.i("TAG123", "Auth URL " + requestUrl);
                Log.i("TAG123", "URL " + request.build().url().toString());


                Response response = chain.proceed(request.build());
                return response;
            }
        });

        client.addInterceptor(interceptor);


        webServices = new Retrofit.Builder()

                .addConverterFactory(new RetrofitConverter())

                .addConverterFactory(GsonConverterFactory.create())

                .baseUrl(baseUrl)

                .client(client.build())

                .build().create(WebServices.class);


        gson = new Gson();

    }


    public static WebServicesWrapper getInstance() {

        if (wrapper == null)

            wrapper = new WebServicesWrapper(BASE_URL);

        return wrapper;

    }


    private Map<String, String> getPartMap(Object object) {

        return (LinkedTreeMap) gson.fromJson(gson.toJson(object), Object.class);

    }


//    private MultipartBody.Part getPart(String name, File file) {
//
//        if (file == null || name == null)
//
//            return null;
//
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//
//        MultipartBody.Part body = MultipartBody.Part.createFormData(name, file.getName(), requestFile);
//
//        return body;
//
//    }


    public Call<User> login(LoginRequest loginRequest, ResponseResolver<User> responseResponseResolver) {

        Call<User> loginResponseCall = webServices.login(loginRequest);

        loginResponseCall.enqueue(responseResponseResolver);

        return loginResponseCall;

    }

    public Call<User> signUp(SignUpRequest signUpRequest, ResponseResolver<User> responseResponseResolver) {

        Call<User> loginResponseCall = webServices.signUp(signUpRequest);

        loginResponseCall.enqueue(responseResponseResolver);

        return loginResponseCall;

    }

    public Call<String> forgotPassword(ForgotPasswordRequest forgotPasswordRequest, ResponseResolver<String> responseResponseResolver) {

        Call<String> loginResponseCall = webServices.forgotPassword(forgotPasswordRequest);

        loginResponseCall.enqueue(responseResponseResolver);

        return loginResponseCall;

    }

/*
    public Call<UserInfo> getMyProfile(ResponseResolver<UserInfo> responseResponseResolver) {

        Call<UserInfo> loginResponseCall = webServices.getmyProfile(getAuthKey());

        loginResponseCall.enqueue(responseResponseResolver);

        return loginResponseCall;

    }


    public Call<List<UserInfo>> discoverProfile(ResponseResolver<List<UserInfo>> responseResponseResolver) {

        Call<List<UserInfo>> loginResponseCall = webServices.discoverSitters(getAuthKey());

        loginResponseCall.enqueue(responseResponseResolver);

        return loginResponseCall;

    }


    public Call<UserInfo> createProfile(UserType userType, ResponseResolver<UserInfo> responseResolver) {

        Call<UserInfo> createProfileCall = webServices.createProfile(getAuthKey(), userType);

        createProfileCall.enqueue(responseResolver);

        return createProfileCall;

    }


    public Call<String> deleteAccount(ResponseResolver<String> responseResolver) {

        Call<String> deleteProfileCall = webServices.deleteAccount(getAuthKey());

        deleteProfileCall.enqueue(responseResolver);

        return deleteProfileCall;

    }


    public Call<UserInfo> updateChildCount(ResponseResolver<UserInfo> responseResolver, ParentalDetail.ChildCount childCount) {

        Call<UserInfo> childUpdateCall = webServices.updateChildCount(getAuthKey(), childCount);

        childUpdateCall.enqueue(responseResolver);

        return childUpdateCall;

    }


    public Call<UserInfo> updateDiscription(ResponseResolver<UserInfo> responseResolver, ParentalDetail discription) {

        Call<UserInfo> childUpdateCall = webServices.updateDiscription(getAuthKey(), discription);

        childUpdateCall.enqueue(responseResolver);

        return childUpdateCall;

    }


    public Call<Address[]> createAddresses(Address address, ResponseResolver<Address[]> responseResolver) {

        Call<Address[]> createAddressCall = webServices.createAddresses(getAuthKey(), address);

        createAddressCall.enqueue(responseResolver);

        return createAddressCall;


    }*/
/*

    public Call<Address[]> updateAddress(Address address, int id, ResponseResolver<Address[]> responseResolver) {

        Call<Address[]> updateAddressCall = webServices.updateAddress(getAuthKey(), address, id);

        updateAddressCall.enqueue(responseResolver);

        return updateAddressCall;

    }

    public Call<String> updateToken(CardToken stripeToken, ResponseResolver<String> responseResolver) {

        Call<String> updateTokenCall = webServices.updateToken(getAuthKey(), stripeToken);

        updateTokenCall.enqueue(responseResolver);

        return updateTokenCall;

    }


    public Call<UserInfo> updateParentProfile(ParentProfileUpdatePatch parentalDetail, ResponseResolver<UserInfo> responseResolver) {

        Call<UserInfo> parentProfileUpdateCall = webServices.updateParentProfile(getAuthKey(), parentalDetail);

        parentProfileUpdateCall.enqueue(responseResolver);

        return parentProfileUpdateCall;

    }


    public Call<UserInfo> updateSitterProfile(SitterProfileUpdatePatch sitterDetail, ResponseResolver<UserInfo> responseResolver) {

        Call<UserInfo> parentProfileUpdateCall = webServices.updateSitterProfile(getAuthKey(), sitterDetail);

        parentProfileUpdateCall.enqueue(responseResolver);

        return parentProfileUpdateCall;

    }


    public Call<BookingItem> reqeustBooking(BookingRequest bookingRequest, ResponseResolver<BookingItem> responseResolver) {

        Call<BookingItem> bookingItemCall = webServices.reqeustBooking(getAuthKey(), bookingRequest);

        bookingItemCall.enqueue(responseResolver);

        return bookingItemCall;

    }


    public Call<BookingItem[]> bookingList(ResponseResolver<BookingItem[]> responseResolver) {

        Call<BookingItem[]> bookingItemRequests = webServices.bookingList(getAuthKey());

        bookingItemRequests.enqueue(responseResolver);

        return bookingItemRequests;

    }

    public Call<TinderSwipeListenerImpl.BookMark> rejectSitter(ResponseResolver<TinderSwipeListenerImpl.BookMark> responseResolver, TinderSwipeListenerImpl.Target target) {

        Call<TinderSwipeListenerImpl.BookMark> bookingItemRequests = webServices.rejectSitter(getAuthKey(), target.getTarget_id());

        bookingItemRequests.enqueue(responseResolver);

        return bookingItemRequests;

    }

    public Call<TinderSwipeListenerImpl.BookMark> bookMarkSitter(ResponseResolver<TinderSwipeListenerImpl.BookMark> responseResolver, TinderSwipeListenerImpl.Target target) {

        Call<TinderSwipeListenerImpl.BookMark> bookingItemRequests = webServices.bookmarkSitter(getAuthKey(), target.getTarget_id());

        bookingItemRequests.enqueue(responseResolver);

        return bookingItemRequests;

    }

    public Call<HomeFeeds> getHomeFeeds(ResponseResolver<HomeFeeds> responseResolver) {

        Call<HomeFeeds> homeFeedsRequest = webServices.getHomeFeeds(getAuthKey());

        homeFeedsRequest.enqueue(responseResolver);

        return homeFeedsRequest;

    }

    public Call<List<UserInfo>> getFavouriteSitters(ResponseResolver<List<UserInfo>> favouriteSitters) {
        Call<List<UserInfo>> favouriteSittersCall = webServices.getFavouriteSitters(getAuthKey());
        favouriteSittersCall.enqueue(favouriteSitters);
        return favouriteSittersCall;
    }


    public Call<List<UserInfo>> getFriendsSitter(ResponseResolver<List<UserInfo>> friendSitters) {
        Call<List<UserInfo>> friendSittersCall = webServices.getFriendsSitter(getAuthKey());
        friendSittersCall.enqueue(friendSitters);
        return friendSittersCall;
    }

    public Call<List<UserInfo>> getFriends(ResponseResolver<List<UserInfo>> friendSitters) {
        Call<List<UserInfo>> friendSittersCall = webServices.getFriends(getAuthKey());
        friendSittersCall.enqueue(friendSitters);
        return friendSittersCall;
    }

    public Call<List<UserInfo>> getBookMarkSitterList(ResponseResolver<List<UserInfo>> sitterList) {
        Call<List<UserInfo>> sitterListCall = webServices.getBookMarkSitters(getAuthKey());
        sitterListCall.enqueue(sitterList);
        return sitterListCall;
    }

    public Call<List<UserInfo>> getParentsNearBy(ResponseResolver<List<UserInfo>> parentLIst) {
        Call<List<UserInfo>> sitterListCall = webServices.getParentsNearBy(getAuthKey());
        sitterListCall.enqueue(parentLIst);
        return sitterListCall;
    }


    public Call<List<CallItem>> getCallingUserDetals(ResponseResolver<List<CallItem>> callee_Details) {
        Call<List<CallItem>> callUserDetailsCall = webServices.getCallingUserDetals(getAuthKey());
        callUserDetailsCall.enqueue(callee_Details);
        return callUserDetailsCall;
    }

    public Call<AddFriendFavourite> setFavourite(ResponseResolver<AddFriendFavourite> addFavouriteCallee, AddFriendFavourite addFriendFavourite) {
        Call<AddFriendFavourite> setFriedFavouriteDetailsCall = webServices.setFavourite(getAuthKey(), addFriendFavourite.isFavorited(), addFriendFavourite.getId());
        setFriedFavouriteDetailsCall.enqueue(addFavouriteCallee);
        return setFriedFavouriteDetailsCall;
    }

    public Call<AddFriendFavourite> setFriend(ResponseResolver<AddFriendFavourite> addFriendCallee, AddFriendFavourite addFriendFavourite) {
        Call<AddFriendFavourite> setFriedFavouriteDetailsCall = webServices.setFriends(getAuthKey(), addFriendFavourite.is_friend(), addFriendFavourite.getId());
        setFriedFavouriteDetailsCall.enqueue(addFriendCallee);
        return setFriedFavouriteDetailsCall;
    }


    public Call<SitterBookingItem[]> bookingRequestList(ResponseResolver<SitterBookingItem[]> sitterBookingItemCallee) {
        Call<SitterBookingItem[]> sitterBookingItems = webServices.bookingRequestList(getAuthKey());
        sitterBookingItems.enqueue(sitterBookingItemCallee);
        return sitterBookingItems;
    }

    public Call<UserInfo.RelationShip> getRelationShip(ResponseResolver<UserInfo.RelationShip> relationShipCallee, String id) {
        Call<UserInfo.RelationShip> relationShipItems = webServices.getRelationShip(getAuthKey(),id);
        relationShipItems.enqueue(relationShipCallee);
        return relationShipItems;
    }


    public Call<List<UserInfo>> getVillagers(ResponseResolver<List<UserInfo>> getVillagersList, String id) {
        Call<List<UserInfo>> villagersItem = webServices.getVillagers(getAuthKey(),id);
        villagersItem.enqueue(getVillagersList);
        return villagersItem;
    }


    public Call<List<Review>> getReviews(ResponseResolver<List<Review>> reviewsItemCallee, String id) {
        Call<List<Review>> reviewItems = webServices.getReviews(getAuthKey(),id);
        reviewItems.enqueue(reviewsItemCallee);
        return reviewItems;
    }


    public Call<List<InboxItem>> getConversations(ResponseResolver<List<InboxItem>> conversationItemsCallee) {
        Call<List<InboxItem>> inboxItems = webServices.getConversations(getAuthKey());
        inboxItems.enqueue(conversationItemsCallee);
        return inboxItems;
    }

    public Call<MessageItem> getMessages(int userId,ResponseResolver<MessageItem> conversationItemsCallee) {
        Call<MessageItem> messageItemCall = webServices.getUserConversation(getAuthKey(),userId+"");
        messageItemCall.enqueue(conversationItemsCallee);
        return messageItemCall;
    }

    public Call<String> updateReadStatus(int conversationId,ResponseResolver<String> readStatusCallee) {
        Call<String> readStatusCall = webServices.updateReadStatus(getAuthKey(),conversationId+"");
        readStatusCall.enqueue(readStatusCallee);
        return readStatusCall;
    }


    public String getAuthKey() {
        return "Token " + AppBaseApplication.getApplication().getmAuthID();
    }

*/


//    public Call<BlossomResponse<LoginResponse>> login(String username, String password, ResponseResolver<LoginResponse> responseResolver) {

//        Call<BlossomResponse<LoginResponse>> loginCall = webServices.login(APP_HEADER, username, password);

//        loginCall.enqueue(responseResolver);

//        return loginCall;

//    }

//

//    public Call<BlossomResponse<ArrayList<School>>> getSchool(ResponseResolver<ArrayList<School>> responseResolver) {

//        Call<BlossomResponse<ArrayList<School>>> schoolResponseCall = webServices.getSchool(APP_HEADER, getAccessToken(), getCoachId());

//        schoolResponseCall.enqueue(responseResolver);

//        return schoolResponseCall;

//    }

//

//    public Call<BlossomResponse<ArrayList<Event>>> getEventList(int schoolId, ResponseResolver<ArrayList<Event>> responseResolver) {

//        Call<BlossomResponse<ArrayList<Event>>> eventResponseCall = webServices.getEventList(APP_HEADER, getAccessToken(), schoolId);

//        eventResponseCall.enqueue(responseResolver);

//        return eventResponseCall;

//    }

//

//    public Call<BlossomResponse<StudentListResponse>> getStudentList(int eventId, ResponseResolver<StudentListResponse> responseResolver) {

//        Call<BlossomResponse<StudentListResponse>> eventResponseCall = webServices.getStudentList(APP_HEADER, getAccessToken(), eventId);

//        eventResponseCall.enqueue(responseResolver);

//        return eventResponseCall;

//    }

//

//    public Call<BlossomResponse<AssessmentResponse>> getAssessment(int assessmentId, ResponseResolver<AssessmentResponse> responseResolver) {

//        Call<BlossomResponse<AssessmentResponse>> assessmentResponseCall = webServices.getAssessment(APP_HEADER, getAccessToken(), assessmentId);

//        assessmentResponseCall.enqueue(responseResolver);

//        return assessmentResponseCall;

//    }

//

//    public Call<BlossomResponse<LoginResponse>> ping(String accessToken, ResponseResolver<LoginResponse> responseResolver) {

//        Call<BlossomResponse<LoginResponse>> loginCall = webServices.ping(APP_HEADER, accessToken);

//        loginCall.enqueue(responseResolver);

//        return loginCall;

//    }

//

//    public Call<BlossomResponse<Child>> postChild(Child child, ResponseResolver<Child> responseResolver) {

//        Call<BlossomResponse<Child>> childResponseCall = webServices.postChild(APP_HEADER, getAccessToken(), child);

//        childResponseCall.enqueue(responseResolver);

//        return childResponseCall;

//    }

//

//    public Call<BlossomResponse<Child>> updateChildImage(File file, int childId, ResponseResolver<Child> responseResolver) {

//        Call<BlossomResponse<Child>> childResponseCall = webServices.updateChildImage

//                (APP_HEADER, getAccessToken(), getPart("photo", file), "child", childId);

//        childResponseCall.enqueue(responseResolver);

//        return childResponseCall;

//    }


}

