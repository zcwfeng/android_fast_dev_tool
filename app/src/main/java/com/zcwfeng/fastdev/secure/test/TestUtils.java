package com.zcwfeng.fastdev.secure.test;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.secure.skb.rsa.DES;

import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by David.zhang on 16/5/22.
 * Description：
 */
public class TestUtils {
    //    String static key = "12-101-VOD";
    public static String key = "12101VOD";

    /**
     * 最终结果对比串
     */
    String RSA_DES_ED = "12-101-VOD-40f3aa91f631e6ab2c5d7eadd3d0d131-1463885530748";

    /**
     * 加密的内容
     */
    public static String encrypt = "FRMs62gV+sc1zvZpSj5jOGrUm3HDpcdnIrcdCTHlj3Tet7NdYytVmCYPIusf0eIjZyMPjCBKUlqWkPTfMvlX/8mZeFSHzQnmuRulZs/5oCOc31Xt9C2YLVMRU72hokCLe6cslHCfEAMlMzSNgzsLN/70ASLuEA9cBAs5/C1XRNM=";

    public static void test(Context context) throws Exception {
        String publicKey = getFromRaw(context, R.raw.public_key);
        String result = DES.decrypt(encrypt, publicKey, "UTF-8");
        Log.e("zcw", "最终的RSA----->" + result);


        String key = "12-101-VOD";
//        String key1 = str2HexStr(key.split("-")[0]);
//        String key2 = str2HexStr(key.split("-")[1]);
//        String key3 = str2HexStr(key.split("-")[2]);
        String key1 = "0000000c";
        String key2 = "0000000000000065";
        String key3 = "564f44";
        getMd5AlgorimResult(key1 + key2 + key3);


    }

    /**
     * 获取秘钥，加密的公钥
     *
     * @param context
     * @param id
     * @return
     */
    public static String getFromRaw(Context context, int id) {
        try {
            InputStream inputStream = context.getResources().openRawResource(id);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) cf.generateCertificate(inputStream);
            RSAPublicKey publicKey = (RSAPublicKey) cert.getPublicKey();
            return Base64.encodeToString(publicKey.getEncoded(), Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String getMd5AlgorimResult(String inputMd5Str) {
        byte firstSort[] = BytesUtil.sortLH(hexStringToByteArray(inputMd5Str));
       byte[] firstMd5 = MD5Util.MD5Encrypt(firstSort);

        Log.e("zcw","第一次MD5："+CE1Util.getStringFromBytes(firstMd5));




        byte secondSort[] = BytesUtil.sortHL(firstMd5);
        byte[] secondMd5 = MD5Util.MD5Encrypt(secondSort);
        Log.e("zcw","第2次MD5："+CE1Util.getStringFromBytes(secondMd5));


        byte thirdSort[] = BytesUtil.sortLH(secondMd5);
        byte[] result = MD5Util.MD5Encrypt(thirdSort);

        Log.e("zcw", "MD5Result----->" + CE1Util.getStringFromBytes(result));

        return result.toString();

    }

    /**
     * 字符串转换成十六进制字符串
     *
     * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
     */
    public static String str2HexStr(String str) {

        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;

        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }


    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
