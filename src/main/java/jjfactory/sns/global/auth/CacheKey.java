package jjfactory.sns.global.auth;


public class CacheKey {
    public static final int DEFAULT_EXPIRE_SEC = 60; // 1 minutes
    public static final String USER = "user";
    public static final int USER_EXPIRE_SEC = 60 * 5; // 5 minutes
    public static final String TICKET = "ticket";
    public static final String TICKETS = "tickets";
    public static final int TICKET_EXPIRE_SEC = 60 * 3; // 3 minutes
    public static final String COMMENTS = "comments";
    public static final int COMMENTS_EXPIRE_SEC = 60 * 3; // 3 minuets
    public static final String SALES_HISTORIES = "sales_histories";
    public static final int SALES_HISTORIES_EXPIRE_SEC = 60 * 3; // 3 minuets
    public static final String PURCHASE_HISTORIES = "purchase_histories";
    public static final int PURCHASE_HISTORIES_EXPIRE_SEC = 60 * 3; // 3 minuets
    public static final String TYPING_REVIEWS = "typing_reviews";
    public static final int TYPING_REVIEWS_EXPIRE_SEC = 60 * 3; // 3 minuets
    public static final String TYPED_REVIEWS = "typed_reviews";
    public static final int TYPED_REVIEWS_EXPIRE_SEC = 60 * 3; // 3 minuets
    public static final String REGIONS_WITH_TOWNS = "regions_with_towns";
    public static final int REGIONS_WITH_TOWNS_EXPIRE_SEC = 60 * 10; // 10 minutes
    public static final String REGIONS = "regions";
    public static final int REGIONS_EXPIRE_SEC = 60 * 10; // 10 minutes
    public static final String TOWNS = "towns";
    public static final int TOWNS_EXPIRE_SEC = 60 * 10; // 10 minutes
    public static final String SENT_MESSAGES = "sent_messages";
    public static final int SENT_MESSAGES_EXPIRE_SEC = 60 * 3; // 3 minutes
    public static final String RECEIVED_MESSAGES = "received_messages";
    public static final int RECEIVED_MESSAGES_EXPIRE_SEC = 60 * 3; // 3 minutes
    public static final String MESSAGE = "message";
    public static final int MESSAGE_EXPIRE_SEC = 60 * 3; // 3 minutes
    public static final String TOKEN = "token";
}
