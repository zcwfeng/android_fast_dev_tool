package com.zcwfeng.httplibs.okhttp.request;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.zcwfeng.httplibs.okhttp.OkHttpUtils;
import com.zcwfeng.httplibs.okhttp.builder.PostFormBuilder;
import com.zcwfeng.httplibs.okhttp.callback.Callback;
import com.zcwfeng.httplibs.utils.Exceptions;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhy on 15/12/14.
 */
public class PostFormRequest extends OkHttpRequest
{
    private List<PostFormBuilder.FileInput> files = new ArrayList<>();

    public PostFormRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, List<PostFormBuilder.FileInput> files)
    {
        super(url, tag, params, headers);
        this.files = files;
    }

    @Override
    protected RequestBody buildRequestBody()
    {
        if (files == null)
        {
            FormEncodingBuilder builder = new FormEncodingBuilder();
            addParams(builder);
            return builder.build();

        } else
        {
            MultipartBuilder builder = new MultipartBuilder()
                    .type(MultipartBuilder.FORM);
            addParams(builder);

            for (int i = 0; i < files.size(); i++)
            {
                PostFormBuilder.FileInput fileInput = files.get(i);
                RequestBody fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileInput.filename)), fileInput.file);
                builder.addFormDataPart(fileInput.key, fileInput.filename, fileBody);
            }
            return builder.build();
        }
    }

    @Override
    protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback)
    {
        if (callback == null) return requestBody;
        CountingRequestBody countingRequestBody = new CountingRequestBody(requestBody, new CountingRequestBody.Listener()
        {
            @Override
            public void onRequestProgress(final long bytesWritten, final long contentLength)
            {

                OkHttpUtils.getInstance().getDelivery().post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        callback.inProgress(bytesWritten * 1.0f / contentLength);
                    }
                });

            }
        });
        return countingRequestBody;
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody)
    {
        return builder.post(requestBody).build();
    }

    private String guessMimeType(String path)
    {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null)
        {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    private void addParams(MultipartBuilder builder)
    {


        if (params != null && !params.isEmpty())
        {
            for (String key : params.keySet())
            {
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                        RequestBody.create(null, params.get(key)));
            }
        } else
        {
            builder.addFormDataPart("1", "1");
        }
    }

    private void addParams(FormEncodingBuilder builder)
    {
        if (params == null || !params.isEmpty())
        {
            Exceptions.illegalArgument("params in PostFormRequest can not be empty.");
        }

        for (String key : params.keySet())
        {
            builder.add(key, params.get(key));
        }
    }
}
