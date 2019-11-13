[["java:package:perv.cmy.zerocice.book"]]
module meta {
    struct Message {
        string name;
        int type;
        bool valid;
        double price;
        string content;
    };
    interface OnlineBook {
        Message bookTick(Message msg);
    };
};