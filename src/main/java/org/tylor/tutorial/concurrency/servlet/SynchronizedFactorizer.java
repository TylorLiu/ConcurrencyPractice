package org.tylor.tutorial.concurrency.servlet;

import org.tylor.tutorial.concurrency.annotation.ThreadSafe;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Tylor 2020/11/6
 */
@ThreadSafe
public class SynchronizedFactorizer extends AbstractServlet  {

    @Override
    public synchronized void  service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromReq(req);
        if (i.equals(lastNumber)){
            encodeIntoResponse(res,lastFactors);
        }else {
            BigInteger[] factors = factor(i);
            lastNumber = i;
            lastFactors = factors;
            encodeIntoResponse(res,factors);
        }
    }

}
