package org.tylor.tutorial.concurrency.servlet;

import org.tylor.tutorial.concurrency.annotation.ThreadSafe;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Tylor 2020/11/7
 */
@ThreadSafe
public class VolatileCachedFactorizer extends AbstractServlet {
    private volatile  OneValueCache cache = new OneValueCache(null,null);
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromReq(req);
        //只能调用一次
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null){
            factors = factor(i);
            encodeIntoResponse(res,factors);
            cache = new OneValueCache(i,factors);
        }else {
            encodeIntoResponse(res,factors);
        }
    }
}
