package org.tylor.tutorial.concurrency.servlet;

import org.tylor.tutorial.concurrency.annotation.GuardedBy;

import javax.servlet.*;
import java.math.BigInteger;
import java.util.Random;

/**
 * Tylor 2020/11/6
 */
public abstract class AbstractServlet implements Servlet {

    @GuardedBy("this")
    protected BigInteger lastNumber;

    @GuardedBy("this")
    protected BigInteger[] lastFactors;


    /**
     * 大数因式分解，可能消耗较长时间
     * @param i
     * @return
     */
    protected BigInteger[] factor(BigInteger i) {
        return new BigInteger[]{i};
    }

    protected void encodeIntoResponse(ServletResponse res, BigInteger[] lastFactors) {
    }

    protected BigInteger extractFromReq(ServletRequest req) {

        return   BigInteger.valueOf(new Random().nextLong());
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }


    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
