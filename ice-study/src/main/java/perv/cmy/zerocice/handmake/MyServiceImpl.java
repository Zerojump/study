package perv.cmy.zerocice.handmake;

import Ice.Current;
import perv.cmy.zerocice.demo._MyServiceDisp;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2019/11/12
 */
public class MyServiceImpl extends _MyServiceDisp {
    @Override
    public String hellow(Current __current) {
        return "Hello world";
    }
}
