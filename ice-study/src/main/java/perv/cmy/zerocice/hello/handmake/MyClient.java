package perv.cmy.zerocice.hello.handmake;

import perv.cmy.zerocice.hello.meta.MyServicePrx;
import perv.cmy.zerocice.hello.meta.MyServicePrxHelper;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2019/11/12
 */
public class MyClient {
    public static void main(String[] args) {
        int status = 0;
        Ice.Communicator ic = null;


        try {
            ic = Ice.Util.initialize(args);
            Ice.ObjectPrx base = ic.stringToProxy("MyService:tcp -h 127.0.0.1 -p 8731");

            MyServicePrx prxy = MyServicePrxHelper.checkedCast(base);
            if (prxy == null) {
                throw new Exception("Invalid proxy");
            }
            String hellow = prxy.hellow();
            System.out.println(hellow);
        } catch (Exception e) {
            e.printStackTrace();
            status = 1;
        } finally {
            if (ic != null) {
                ic.destroy();
            }
        }
        System.exit(status);
    }
}
