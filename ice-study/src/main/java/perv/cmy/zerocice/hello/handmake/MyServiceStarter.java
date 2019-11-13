package perv.cmy.zerocice.hello.handmake;

import Ice.ObjectAdapter;
import Ice.Util;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2019/11/12
 */
public class MyServiceStarter {
    public static void main(String[] args) {
        int status = 0;
        Ice.Communicator ic = null;


        try {
            ic = Util.initialize(args);
            ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints("MyServiceAdapter", "tcp -p 8731");
            MyServiceImpl servant = new MyServiceImpl();

            adapter.add(servant, Ice.Util.stringToIdentity("MyService"));
            adapter.activate();
            System.out.println("server started");
            ic.waitForShutdown();
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
