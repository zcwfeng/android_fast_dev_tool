package com.zcwfeng.httplibs.okhttp.callback;

import com.squareup.okhttp.Response;
import com.zcwfeng.httplibs.okhttp.OkHttpUtils;
import com.zcwfeng.httplibs.utils.L;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhy on 15/12/15.
 */
public abstract class FileCallBack extends Callback<File>
{
    /**
     * 目标文件存储的文件夹路径
     */
    private String destFileDir;
    /**
     * 目标文件存储的文件名
     */
    private String destFileName;

    public abstract void inProgress(float progress);

    public FileCallBack(String destFileDir, String destFileName)
    {
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;
    }


    @Override
    public File parseNetworkResponse(Response response) throws IOException
    {
        return saveFile(response);
    }


    public File saveFile(Response response) throws IOException
    {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try
        {
            is = response.body().byteStream();
            final long total = response.body().contentLength();
            long sum = 0;

            L.e(total + "");

            File dir = new File(destFileDir);
            if (!dir.exists())
            {
                dir.mkdirs();
            }
            File file = new File(dir, destFileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1)
            {
                sum += len;
                fos.write(buf, 0, len);
                final long finalSum = sum;
                OkHttpUtils.getInstance().getDelivery().post(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        inProgress(finalSum * 1.0f / total);
                    }
                });
            }
            fos.flush();

            return file;

        } finally
        {
            try
            {
                if (is != null) is.close();
            } catch (IOException e)
            {
            }
            try
            {
                if (fos != null) fos.close();
            } catch (IOException e)
            {
            }

        }
    }


}
