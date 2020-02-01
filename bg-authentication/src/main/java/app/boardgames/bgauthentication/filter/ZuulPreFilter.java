package app.boardgames.bgauthentication.filter;

import app.boardgames.bgauthentication.domain.CustomClaims;
import app.boardgames.bgauthentication.util.JwtTokenUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

public class ZuulPreFilter extends ZuulFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return !ctx.containsKey(FORWARD_TO_KEY) // a filter has already forwarded
                && !ctx.containsKey(SERVICE_ID_KEY); // a filter has already determined serviceId
    }
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if (request.getRequestURI().startsWith("/api/core")) {
            injectUserInfoIntoRequest(ctx);
        }
        return null;
    }

    private void injectUserInfoIntoRequest(RequestContext ctx) {
        String jwtToken = ctx.getRequest().getHeader("Authorization").substring(7);
        ctx.addZuulRequestHeader(CustomClaims.FIRST_NAME.toString(), jwtTokenUtil.getFirstNameFromToken(jwtToken));
        ctx.addZuulRequestHeader(CustomClaims.LAST_NAME.toString(), jwtTokenUtil.getLastNameFromToken(jwtToken));
        ctx.addZuulRequestHeader(CustomClaims.EMAIL.toString(), jwtTokenUtil.getEmailFromToken(jwtToken));
    }
}
