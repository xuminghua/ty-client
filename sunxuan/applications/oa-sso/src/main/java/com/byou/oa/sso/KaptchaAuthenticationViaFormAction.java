package com.byou.oa.sso;

import org.jasig.cas.CentralAuthenticationService;
import org.jasig.cas.Message;
import org.jasig.cas.authentication.AuthenticationException;
import org.jasig.cas.authentication.Credential;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.principal.WebApplicationService;
import org.jasig.cas.ticket.TicketCreationException;
import org.jasig.cas.ticket.TicketException;
import org.jasig.cas.ticket.TicketGrantingTicket;
import org.jasig.cas.ticket.registry.TicketRegistry;
import org.jasig.cas.web.bind.CredentialsBinder;
import org.jasig.cas.web.support.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.util.StringUtils;
import org.springframework.web.util.CookieGenerator;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by sunxuan on 16-7-16.
 * V0.0.0.1
 */
public class KaptchaAuthenticationViaFormAction   extends org.jasig.cas.web.flow.AuthenticationViaFormAction{

    public KaptchaAuthenticationViaFormAction() {
        super();
    }

    public static final String SUCCESS = "success";
    public static final String SUCCESS_WITH_WARNINGS = "successWithWarnings";
    public static final String WARN = "warn";
    public static final String AUTHENTICATION_FAILURE = "authenticationFailure";
    public static final String ERROR = "error";
    private CredentialsBinder credentialsBinder;
    @NotNull
    private CentralAuthenticationService centralAuthenticationService;
    @NotNull
    private TicketRegistry ticketRegistry;
    @NotNull
    private CookieGenerator warnCookieGenerator;
    private boolean hasWarningMessages;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());




    private void putWarnCookieIfRequestParameterPresent(RequestContext context) {
        HttpServletResponse response = WebUtils.getHttpServletResponse(context);
        if(StringUtils.hasText(context.getExternalContext().getRequestParameterMap().get("warn"))) {
            this.warnCookieGenerator.addCookie(response, "true");
        } else {
            this.warnCookieGenerator.removeCookie(response);
        }

    }

    private AuthenticationException getAuthenticationExceptionAsCause(TicketException e) {
        return (AuthenticationException)e.getCause();
    }

    private Event newEvent(String id) {
        return new Event(this, id);
    }

    private Event newEvent(String id, Exception error) {
        return new Event(this, id, new LocalAttributeMap("error", error));
    }



    private void addWarningToContext(MessageContext context, Message warning) {
        MessageBuilder builder = (new MessageBuilder()).warning().code(warning.getCode()).defaultText(warning.getDefaultMessage()).args(warning.getParams());
        context.addMessage(builder.build());
        this.hasWarningMessages = true;
    }

    public final String validatorCaptcha(final RequestContext context, final Credential credential,
            final MessageContext messageContext){
            final HttpServletRequest request = WebUtils.getHttpServletRequest(context);
            HttpSession session = request.getSession();
            String captcha = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
            session.removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

            UsernamePasswordCaptchaCredential upc = (UsernamePasswordCaptchaCredential)credential;
            String submitAuthcodeCaptcha =upc.getCaptcha();


            if(!StringUtils.hasText(submitAuthcodeCaptcha) || !StringUtils.hasText(submitAuthcodeCaptcha)){
                messageContext.addMessage(new MessageBuilder().code("required.captcha").build());
                return "error";
            }
            if(submitAuthcodeCaptcha.equals(captcha)){
                return "success";
            }
            messageContext.addMessage(new MessageBuilder().code("error.authentication.captcha.bad").build());
            return "error";
    }
}

