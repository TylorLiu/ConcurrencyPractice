package org.tylor.tutorial.concurrency.servlet;

import org.tylor.tutorial.concurrency.annotation.GuardedBy;
import org.tylor.tutorial.concurrency.annotation.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Tylor 2020/11/6
 */
@ThreadSafe
public class CachedFactorizer extends AbstractServlet {

    @GuardedBy("this")
    long hits;
    @GuardedBy("this")
    long cacheHits;

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromReq(req);
        BigInteger[] factors = null;
        synchronized (this){
            ++hits;
            if (i.equals(lastNumber)){
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }
        if (factors == null){
            //当执行的操作可能消耗较长实际时（复杂计算、IO、接口调用等），不要持有锁
            factors = this.factor(i);
            synchronized (this){
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }
        encodeIntoResponse(res,factors);

    }

}
