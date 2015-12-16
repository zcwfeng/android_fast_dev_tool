package com.zcwfeng.httplibs.request;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.zcwfeng.httplibs.callback.Callback;
import com.zcwfeng.httplibs.utils.Exceptions;

import java.util.Map;

/**
 * Created by zhy on 15/11/6.
 */
public abstract class OkHttpRequest
{

    protected String url;
    protected Object tag;
    protected Map<String, String> params;
    protected Map<String, String> headers;


    protected Request.Builder builder = new Request.Builder();

    protected OkHttpRequest(String url, Object tag,
                            Map<String, String> params, Map<String, String> headers)
    {
        this.url = url;
        this.tag = tag;
        this.params = params;
        this.headers = headers;

        if (url == null)
        {
            Exceptions.illegalArgument("url can not be null.");
        }
    }


    protected abstract RequestBody buildRequestBody();

    protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback)
    {
        return requestBody;
    }

    protected abstract Request buildRequest(Request.Builder builder, RequestBody requestBody);

    public RequestCall build()
    {
        return new RequestCall(this);
    }


    public Request generateRequest(Callback callback)
    {
        RequestBody requestBody = wrapRequestBody(buildRequestBody(), callback);
        prepareBuilder();
        return buildRequest(builder, requestBody);
    }


    private void prepareBuilder()
    {
        builder.url(url).tag(tag);
        appendHeaders();
    }


    protected void appendHeaders()
    {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return;

        for (String key : headers.keySet())
        {
            headerBuilder.add(key, headers.get(key));
        }
        builder.headers(headerBuilder.build());
    }


}
